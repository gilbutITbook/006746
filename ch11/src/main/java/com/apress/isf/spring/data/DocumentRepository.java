package com.apress.isf.spring.data;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apress.isf.java.model.Document;

@Repository("documentDAO")
public class DocumentRepository implements DocumentDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private String query;
	@Autowired
	private String insert;
	@Autowired
	private String find;
	@Autowired
	private String update;

	public List<Document> getAll() {
		return new JdbcTemplate(dataSource).query(query, new DocumentRowMapper());
	}

	public Document findById(String id) {
		Document updateDocument = null;
		JdbcTemplate template = new JdbcTemplate(dataSource);

		try {
			updateDocument = template.queryForObject(find,
					new Object[] { id },
					new DocumentRowMapper());
		} catch (EmptyResultDataAccessException ex) {}
		return updateDocument;
	}

	public void save(Document document) {
		try {
			JdbcTemplate template = new JdbcTemplate(dataSource);
			if (null == findById(document.getDocumentId()))
				template.update(
						insert,
						new Object[] { document.getDocumentId(),
								document.getName(), document.getLocation(),
								document.getDescription(),
								document.getType().getTypeId(),
								document.getCreated(), document.getModified() });
			else
				template.update(
						update,
						new Object[] { document.getName(),
								document.getLocation(),
								document.getDescription(),
								document.getType().getTypeId(), new Date(),
								document.getDocumentId() });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
