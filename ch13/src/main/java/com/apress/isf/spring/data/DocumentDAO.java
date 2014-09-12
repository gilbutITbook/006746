/**
 * 
 */
package com.apress.isf.spring.data;

import java.util.List;

import com.apress.isf.java.model.Document;

/**
 * @author Felipe Gutierrez
 *
 */
public interface DocumentDAO {
	public List<Document> getAll();
	public Document save(String id, Document document);
	public Document findById(String id);
	public Document removeById(String id);
	public List<Document> findByTypeName(String name);
}
