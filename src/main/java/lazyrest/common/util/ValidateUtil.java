package lazyrest.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Melon on 17/2/15.
 */
public class ValidateUtil {


    public static Map<String, Object> toStringJson(BindingResult result) {

        Map<String, Object> map = new HashMap<String, Object>();

        for (FieldError fieldError : result.getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return map;

    }

}