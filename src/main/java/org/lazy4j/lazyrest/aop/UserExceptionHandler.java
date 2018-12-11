package org.lazy4j.lazyrest.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Melon on 17/2/16.
 */
@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler(UserException.class)
    @ResponseBody
    public String handleUserException(HttpServletRequest request, Exception ex) {

        return ex.getMessage();
    }
}
