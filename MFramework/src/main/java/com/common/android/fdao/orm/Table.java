
package com.common.android.fdao.orm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc



/*
 * 注解表
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { java.lang.annotation.ElementType.TYPE })
public @interface Table {
	
	/**
	 * 表名.
	 *
	 * @return the string
	 */
	public abstract String name();
}
