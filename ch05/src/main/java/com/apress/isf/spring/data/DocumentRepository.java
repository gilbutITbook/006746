package com.apress.isf.spring.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.isf.java.model.Document;

public class DocumentRepository implements DocumentDAO {

	private static final Logger log =
	    LoggerFactory.getLogger(DocumentRepository.class);
	private List<Document> documents = null;

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document[] getAll() {
		if (log.isDebugEnabled())
			log.debug("Start <getAll> Params: ");
		Document[] result = documents.toArray(new Document[documents.size()]);

		if (log.isDebugEnabled())
			log.debug("End <getAll> Result:" + result);
		return result;
	}

}
