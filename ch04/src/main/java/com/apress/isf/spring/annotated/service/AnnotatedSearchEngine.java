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
@Scope("prototype")
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
        List<Document>  result = new ArrayList<Document>();
        for (Document doc : listAll()) {
            if (doc.getType().getName().equals(documentType.getName()))
                result.add(doc);
        }
        return result;
    }

    public List<Document> listAll() {
        return Arrays.asList(documentDAO.getAll());
    }

}
