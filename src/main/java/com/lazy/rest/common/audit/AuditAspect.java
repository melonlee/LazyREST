package com.lazy.rest.common.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final ObjectMapper objectMapper;
    private final AuditLogRepository auditLogRepository;

    @Around("@annotation(com.lazy.rest.common.audit.Auditable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        
        // Get request info
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        // Get method info
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Auditable auditable = method.getAnnotation(Auditable.class);
        
        // Get username
        String username = "anonymous";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        
        // Create audit log
        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);
        auditLog.setOperation(auditable.operation());
        auditLog.setMethod(request.getMethod());
        auditLog.setPath(request.getRequestURI());
        auditLog.setIp(getIpAddress(request));
        
        // Log parameters
        try {
            String params = objectMapper.writeValueAsString(Arrays.asList(point.getArgs()));
            auditLog.setParams(params);
        } catch (Exception e) {
            log.warn("Failed to serialize request parameters", e);
        }
        
        // Execute method
        Object result = null;
        try {
            result = point.proceed();
            // Log result
            try {
                String resultJson = objectMapper.writeValueAsString(result);
                auditLog.setResult(resultJson);
            } catch (Exception e) {
                log.warn("Failed to serialize response", e);
            }
        } catch (Throwable throwable) {
            auditLog.setResult("Error: " + throwable.getMessage());
            throw throwable;
        } finally {
            // Calculate duration and save log
            long duration = System.currentTimeMillis() - beginTime;
            auditLog.setDuration(duration);
            auditLogRepository.save(auditLog);
        }
        
        return result;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
} 