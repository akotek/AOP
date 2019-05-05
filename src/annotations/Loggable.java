package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Loggable {

    // Levels of logging
    int INFO = 0;

    int WARNING = 1;

    int SEVERE = 2;

    // Defines level of logging... @Loggable(value = INFO)
    int value() default Loggable.INFO;
}
