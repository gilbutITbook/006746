package com.apress.isf.spring.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

public class DocumentRepository implements DocumentDAO {

	private String queryAll;
	private DataSource dataSource;
	private Resource schema;
	private Resource data;

	public void setQueryAll(String queryAll) {
		this.queryAll = queryAll;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setSchema(Resource schema) {
		this.schema = schema;
	}

	public void setData(Resource data) {
		this.data = data;
	}

	public void initialize() {
		try {
			InputStream stream = schema.getInputStream();
			Scanner scanner = new Scanner(stream);
			StringBuilder sql = new StringBuilder();
			while (scanner.hasNext()) {
				sql.append(scanner.nextLine());
				sql.append("\n");
			}
			scanner.close();
			stream.close();
			Connection connection = null;
			Statement statement = null;
			try {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				statement.execute(sql.toString());
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			} finally {
				if (null != connection) {
					try {
						connection.close();
					} catch (SQLException ex) {
					}
				}
			}

			stream = data.getInputStream();
			scanner = new Scanner(stream);
			sql = new StringBuilder();
			while (scanner.hasNext()) {
				sql.append(scanner.nextLine());
				sql.append("\n");
			}
			scanner.close();
			stream.close();
			connection = null;
			statement = null;
			try {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			} finally {
				if (null != connection) {
					try {
						connection.close();
					} catch (SQLException ex) {
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Document> getAll() {
		List<Document> result = new ArrayList<Document>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Document document = null;
		Type type=null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(queryAll);
			while (resultSet.next()) {
				document = new Document();
				document.setDocumentId(resultSet.getString("documentId"));
				document.setName(resultSet.getString("name"));
				document.setLocation(resultSet.getString("location"));
				document.setCreated(resultSet.getDate("created"));
				document.setModified(resultSet.getDate("modified"));
				document.setDescription("doc_desc");
				type = new Type();
				type.setTypeId(resultSet.getString("typeId"));
				type.setName(resultSet.getString("type_name"));
				type.setDesc(resultSet.getString("type_desc"));
				type.setExtension(resultSet.getString("extension"));
				document.setType(type);

				result.add(document);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException ex) {
				}
			}
		}
		return result;
	}

}

