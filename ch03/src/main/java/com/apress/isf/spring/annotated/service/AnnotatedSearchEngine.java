package com.apress.isf.spring.annotated.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;

@Service("engine")
public class AnnotatedSearchEngine implements SearchEngine {

    @Autowired
    private DocumentDAO documentDAO;
    
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
