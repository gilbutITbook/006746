package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;

public class DocumentRepository implements DocumentDAO {
	
    private Document doc1;
    private Document doc2;
    private Document doc3;
    private Document doc4;

    public Document getDoc1() {
        return doc1;
    }
    
    public void setDoc1(Document doc1) {
        this.doc1 = doc1;
    }
    
    public Document getDoc2() {
        return doc2;
    }
    
    public void setDoc2(Document doc2) {
        this.doc2 = doc2;
    }
    
    public Document getDoc3() {
        return doc3;
    }
    
    public void setDoc3(Document doc3) {
        this.doc3 = doc3;
    }
    
    public Document getDoc4() {
        return doc4;
    }
    
    public void setDoc4(Document doc4) {
        this.doc4 = doc4;
    }
    
    public Document[] getAll() {
        return new Document[] { doc1, doc2, doc3, doc4 };
    }

}
