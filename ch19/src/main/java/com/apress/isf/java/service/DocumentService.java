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
	public List<Document> getAllDocuments();
	public Document findDocumentById(String id);
	public Document saveDocument(String id, Document document);
	public Document removeDocumentById(String id);
	public List<Document> findByType(Type type);
	public boolean updateLocationFromDocumentId(String documentId, String location);
}
