/**
 * 
 */
package com.apress.isf.java.service;

import java.util.List;

import com.apress.isf.java.model.Type;

/**
 * @author Felipe Gutierrez
 *
 */
public interface TypeService {
	public void createNewType(Type type);
	public void updateType(Type type);
	public void removeTypeById(String id);
	public List<Type> getAllDefinedTypes();
	public Type getTypeById(String id);
}
