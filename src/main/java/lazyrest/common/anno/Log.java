package lazyrest.common.anno;

import java.lang.annotation.*;

/**
 * Created by Melon on 17/2/15.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value() default "";
}
