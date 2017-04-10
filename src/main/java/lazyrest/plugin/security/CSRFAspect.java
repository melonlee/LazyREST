package lazyrest.plugin.security;

import lazyrest.common.util.EndecryptUtil;
import lazyrest.web.exception.TokenException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by Melon on 2017/4/10.
 */
public class CSRFAspect {

    private static final String SIGN = "X-Sign";

    private static final String TIMESTAMP = "X-Timestamp";


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerAspect() {

    }

    /**
     * 验证时间戳
     * 验证签名
     * 验证ip
     * .....
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sign = request.getHeader(SIGN);
        String timestamp = request.getHeader(TIMESTAMP).substring(0, 11);

        if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp)) {
            throw new TokenException("客户端参数异常");
        }

        /**
         *
         * 验证时间戳如果跟当前时间相差2秒则视为请求无效
         *
         */

        long serverTimestamp = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 11));

        if ((serverTimestamp - Long.parseLong(timestamp) > 2000)) {
            throw new TokenException("客户端参数异常");
        }

        /**
         *
         * 验证签名  sign =  HTTPMETHOD（GET/POST/DELETE/PUT）+ uri（API的访问URI）+timestamp（UNIX时间戳）+length（发送body的数据长度--post才有）\
         *
         * 按照自己项目需求可加入一些动态的验证token机制
         *
         */

        String serverSign = EndecryptUtil.encrytMD5(request.getMethod() + request.getRequestURI() + timestamp);

        if (!(serverSign.equals(sign))) {
            throw new TokenException("客户端参数异常");
        }

        // 调用目标方法
        return pjp.proceed();
    }
}
