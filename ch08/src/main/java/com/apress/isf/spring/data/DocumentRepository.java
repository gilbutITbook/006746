/**
 * 
 */
package com.apress.isf.spring.data;

import java.util.List;

import com.apress.isf.java.model.Document;

/**
 * @author Felipe Gutierrez
 *
 */
public class DocumentRepository implements DocumentDAO {
	private List<Document> documents = null;
	
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document[] getAll() {
		Document[] result = documents.toArray(new Document[documents.size()]);
		return result;
	}

}
