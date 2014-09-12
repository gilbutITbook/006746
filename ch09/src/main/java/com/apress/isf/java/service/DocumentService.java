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
public interface DocumentService {
	public void createNewType(Type type);
	public void updateType(Type type);
	public void removeTypeById(String id);
	public List<Type> getAllDefinedTypes();
	public Type getTypeById(String id);
	
	public void createNewDocument(Document document);
	public void removeDocumentById(String id);
	public void updateDocument(Document document);
	public void updateLocationFromDocumentId(String documentId, String location);
}
