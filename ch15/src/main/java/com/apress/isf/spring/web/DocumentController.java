/**
 * 
 */
package com.apress.isf.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.service.DocumentService;

/**
 * @author Felipe Gutierrez
 *
 */
@Controller
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	DocumentService documentFacade;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Document> getDocuments(){		
		return documentFacade.getAllDocuments();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody Document findDocument(@PathVariable String id){
		return documentFacade.findDocumentById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Document addDocument(@RequestBody Document document){
		String id = document.getDocumentId();
		return documentFacade.saveDocument(id,document);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public @ResponseBody Document updateDocument(@RequestBody Document document, @PathVariable String id){
		return documentFacade.saveDocument(id,document);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody Document removeDocument(@PathVariable String id){
		return documentFacade.removeDocumentById(id);
	}
}
