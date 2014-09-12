/**
 * 
 */
package com.apress.isf.spring.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.data.DocumentRepository;
import com.apress.isf.spring.service.SearchEngineService;

/**
 * @author Felipe Gutierrez
 *
 */
@Configuration
public class MyDocumentsContext {
	private static final Logger log = LoggerFactory.getLogger(MyDocumentsContext.class);
	
	private Map<String,Document> documents = new HashMap<String,Document>();
	private Map<String,Type> types = new HashMap<String,Type>();

	@Bean
	public Type webType(){		
		return getTypeFromMap("web");
	}
	
	@Bean
	@Scope("prototype")
	public SearchEngine engine(){
		SearchEngineService engine = new SearchEngineService();
		engine.setDocumentDAO(documentDAO());
		
		if(log.isDebugEnabled())
			log.debug("SearchEngine created: " + engine);
		
		return engine;
	}
	
	public MyDocumentsContext(){
		Type type = new Type();
		type.setName("PDF");
		type.setDesc("Portable Document Format");
		type.setExtension(".pdf");
		
		
		Document document = new Document();
		document.setName("Book Template");
		document.setType(type);
		document.setLocation("/Users/felipeg/Documents/Random/Book Template.pdf");
		
		documents.put("doc1", document);
		types.put("pdf",type);
		
		document = new Document();
		document.setName("Sample Contract");
		document.setType(type);
		document.setLocation("/Users/felipeg/Documents/Contracts/Sample Contract.pdf");
		
		documents.put("doc2",document);
		
		type = new Type();
		type.setName("NOTE");
		type.setDesc("Text Notes");
		type.setExtension(".txt");
		
		document = new Document();
		document.setName("Clustering with RabbitMQ");
		document.setType(type);
		document.setLocation("/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt");
		
		documents.put("doc3",document);
		types.put("note",type);
		
		type = new Type();
		type.setName("WEB");
		type.setDesc("Web Link");
		type.setExtension(".url");
		
		document = new Document();
		document.setName("Pro Spring Security Book");
		document.setType(type);
		document.setLocation("http://www.apress.com/9781430248187");
		
		documents.put("doc4",document);
		types.put("web",type);
	}
	
	private DocumentDAO documentDAO(){
		DocumentRepository documentDAO = new DocumentRepository();
		documentDAO.setDocuments(new ArrayList<Document>(documents.values()));
		return documentDAO;
	}
	
	private Type getTypeFromMap(String typeKey){		
		return types.get(typeKey);
	}
}
