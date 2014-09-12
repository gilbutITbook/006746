/**
 * 
 */
package com.apress.isf.spring.data;

import java.util.List;

import com.apress.isf.java.model.Type;

/**
 * @author Felipe Gutierrez
 *
 */
public interface TypeDAO {
	public List<Type> getAll();
	public Type findById(String id);
	public Type save(Type type);
}
