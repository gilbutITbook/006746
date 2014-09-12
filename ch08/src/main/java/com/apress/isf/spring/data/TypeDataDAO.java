/**
 * 
 */
package com.apress.isf.spring.data;

import com.apress.isf.java.model.Type;

/**
 * @author Felipe Gutierrez
 *
 */
public interface TypeDataDAO {
	public Type[] getAll();
	public Type findById(String id);
}
