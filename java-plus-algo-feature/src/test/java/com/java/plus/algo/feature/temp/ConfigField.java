package com.java.plus.algo.feature.temp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigField {
	  /**
     * 是否允许为空
     * @return
     */
    boolean allowNull() default true;

    /**
     * 特殊备注
     *
     * @return
     */
    String document() default "";

    String defaultValue() default "";

    String[] selectType() default {};
}
