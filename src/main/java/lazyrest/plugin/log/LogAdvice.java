package lazyrest.plugin.log;

import lazyrest.common.anno.Log;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 记录正常的业务日志
 * <p/>
 * Created by Melon on 17/2/15.
 */
@Aspect
@Component
public class LogAdvice {

    public static final Logger logger = Logger.getLogger(LogAdvice.class);


    @Pointcut("@annotation(lazyrest.common.anno.Log)")
    public void controllerAspect() {

    }


    /**
     * @param joinPoint
     */
    @AfterReturning("controllerAspect()")
    public void afterReturn(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        if (null != log) {
            logger.info(String.format("业务日志 : [%s]", log.value()));
        }
    }


}
