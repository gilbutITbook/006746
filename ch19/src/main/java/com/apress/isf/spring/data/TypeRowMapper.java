/**
 * 
 */
package com.apress.isf.spring.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.apress.isf.java.model.Type;

/**
 * @author Felipe Gutierrez
 *
 */
public class TypeRowMapper implements RowMapper<Type>{

	@Override
	public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
		Type type = new Type(rs.getString("typeId"),
				rs.getString("name"),
				rs.getString("description"),
				rs.getString("extension"));
		return type;
	}

}
