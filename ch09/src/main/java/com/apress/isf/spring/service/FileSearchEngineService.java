/**
 * 
 */
package com.apress.isf.spring.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;

/**
 * @author Felipe Gutierrez
 *
 */
@Component
@Profile("qa")
public class FileSearchEngineService implements SearchEngine {
	
	public List<Document> findByType(Type documentType) {
		throw new UnsupportedOperationException("QA Environment. Not yet implemented operation.");
	}
	
	public List<Document> listAll() {
		throw new UnsupportedOperationException("QA Environment. Not yet implemented operation.");
	}

	public List<Document> findByLocation(String location) {
		throw new UnsupportedOperationException("findByLocation not yet implemented.");
	}
}
