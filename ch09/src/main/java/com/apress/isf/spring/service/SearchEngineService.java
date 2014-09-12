/**
 * 
 */
package com.apress.isf.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;

/**
 * @author Felipe Gutierrez
 *
 */
public class SearchEngineService implements SearchEngine {
	private DocumentDAO documentDAO;

	
	public SearchEngineService(){
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	public List<Document> findByType(Type documentType) {
		List<Document>  result = new ArrayList<Document>();
		for(Document doc : listAll()){
			if(doc.getType().getName().equals(documentType.getName()))
				result.add(doc);
		}
		return result;
	}
	
	public List<Document> listAll() {
		List<Document> result = documentDAO.getAll();
		return result;
	}

	public List<Document> findByLocation(String location) {
		throw new UnsupportedOperationException("findByLocation not yet implemented.");
	}
}
