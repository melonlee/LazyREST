package lazyrest.plugin.token;

import lazyrest.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Melon on 17/2/16.
 */
@ControllerAdvice
public class TokenExceptionHandler {


    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public Result handleBizExp(HttpServletRequest request, Exception ex) {

        return new Result().failure(ex.getMessage());
    }

}
