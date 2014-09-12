package com.apress.isf.spring.annotated.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.service.SearchEngineService;

@Service("engine")
@Scope
public class AnnotatedSearchEngine implements SearchEngine {
	
	private static final Logger log = 
	    LoggerFactory.getLogger(SearchEngineService.class);
	
	@Autowired
	private DocumentDAO documentDAO;
	
	public AnnotatedSearchEngine() {
		if (log.isDebugEnabled())
			log.debug("AnnotatedSearchEngine created: " + this);
	}
	
	public List<Document> findByType(Type documentType) {
		if (log.isDebugEnabled())
			log.debug("Start <findByType> Params: " + documentType.getName());
		
		List<Document>  result = new ArrayList<Document>();
		for (Document doc : listAll()) {
			if (doc.getType().getName().equals(documentType.getName()))
				result.add(doc);
		}
		
		if (log.isDebugEnabled())
			log.debug("End <findByType> Result:" + result);
		return result;
	}
	
	public List<Document> listAll() {
		if (log.isDebugEnabled())
			log.debug("Start <listAll> Params: ");
		
		List<Document> result = Arrays.asList(documentDAO.getAll());
		
		if (log.isDebugEnabled())
			log.debug("End <listAll> Params: " + result);
		return result;
	}
	
}
