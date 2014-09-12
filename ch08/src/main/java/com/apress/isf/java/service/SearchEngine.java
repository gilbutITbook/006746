/**
 * 
 */
package com.apress.isf.java.service;

import java.util.List;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

/**
 * @author Felipe Gutierrez
 *
 */
public interface SearchEngine {

	public List<Document> findByType(Type documentType);
	
	public List<Document> listAll();
	
	public List<Document> findByLocation(String location);
}
