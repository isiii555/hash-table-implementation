package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HashTableData {
    boolean keyNotNull() default true;
    boolean valueNotNull() default true;
    boolean keyMustBePositiveInteger() default true;
}
