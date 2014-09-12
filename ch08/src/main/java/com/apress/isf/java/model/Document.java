/**
 * 
 */
package com.apress.isf.java.model;

/**
 * @author Felipe Gutierrez
 *
 */
public class Document {
	
	private String name;
	private Type type;
	private String location;
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder("Documents(");
		builder.append("name: ");
		builder.append(name);
		builder.append(", type: ");
		builder.append(type);
		builder.append(", location: ");
		builder.append(location);
		builder.append(")");
		return builder.toString();
	}
}
