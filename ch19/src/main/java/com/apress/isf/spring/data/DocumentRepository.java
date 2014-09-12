/**
 * 
 */
package com.apress.isf.spring.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apress.isf.java.model.Document;

/**
 * @author Felipe Gutierrez
 * 
 */
@Repository("documentDAO")
public class DocumentRepository implements DocumentDAO {

	@Autowired
	private DataSource dataSource;

	@Resource
	private Map<String,String> sql;
	
	public List<Document> getAll() {
		return new JdbcTemplate(dataSource).query(sql.get("query"),
				new DocumentRowMapper());
	}
	
	public List<Document> findByTypeName(String name){
		return new JdbcTemplate(dataSource).query(sql.get("type-name"), new Object[] {name},
				new DocumentRowMapper());
	}

	public Document findById(String id) {
		Document document = null;
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try {
			document = template.queryForObject(sql.get("find"),
					new Object[] { id },
					new DocumentRowMapper());
		} catch (EmptyResultDataAccessException ex) {}
		return document;
	}
	
	public Document save(String id, Document document) {
		try {
			JdbcTemplate template = new JdbcTemplate(dataSource);
			Document _document = findById(id);
			if (null == _document)
				template.update(
						sql.get("insert"),
						new Object[] { id,
								document.getName(), document.getLocation(),
								document.getDescription(),
								document.getType().getTypeId(),
								document.getCreated(), document.getModified() });
			else{
				_document.setName((null==document.getName())?_document.getName():document.getName());
				_document.setLocation((null==document.getLocation())?_document.getLocation():document.getLocation());
				_document.setDescription((null==document.getDescription())?_document.getDescription():document.getDescription());
				_document.setType((null==document.getType())?_document.getType():document.getType());
				_document.setModified(new Date());
				template.update(
						sql.get("update"),
						new Object[] { _document.getName(),
								_document.getLocation(),
								_document.getDescription(),
								_document.getType().getTypeId(), new Date(),
								id });
				document = _document;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return document;
	}

	public Document removeById(String id){
		Document document = findById(id);
		if(null != document){
			try{
				JdbcTemplate template = new JdbcTemplate(dataSource);
				int rows  = template.update(sql.get("delete"), new Object[] { id});
				if(rows <= 0)
					document = null;
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return document;
	}
}
