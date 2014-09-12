/**
 * 
 */
package com.apress.isf.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.DocumentService;
import com.apress.isf.spring.data.DocumentDAO;

/**
 * @author Felipe Gutierrez
 *
 */
@Service("documentFacade")
public class DocumetServiceFacade implements DocumentService {

	@Autowired
	DocumentDAO documentDAO;
	
	public List<Document> getAllDocuments(){
		return documentDAO.getAll();
	}
	
	public Document saveDocument(String id, Document document) {
		return documentDAO.save(id, document);
	}

	public Document removeDocumentById(String id) {
		return documentDAO.removeById(id);
	}

	public Document findDocumentById(String id){
		return documentDAO.findById(id);
	}
	
	public boolean updateLocationFromDocumentId(String documentId, String location) {
		Document document = documentDAO.findById(documentId);
		if(null == document)
			return false;
		document.setLocation(location);
		saveDocument(documentId, document);		
		return true;
	}

	public List<Document> findByType(Type type) {
		return documentDAO.findByTypeName(type.getName());
	}

}
