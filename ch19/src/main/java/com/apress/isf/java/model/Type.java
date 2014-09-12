/**
 * 
 */
package com.apress.isf.java.model;

/**
 * @author Felipe Gutierrez
 *
 */
public class Type {
	
	private String typeId;
	private String name;	
	private String desc;
	private String extension;
	
	public Type(){
		this(null,null);
	}
	
	public Type(String name,String extension){
		this(java.util.UUID.randomUUID().toString(), name, extension, null);
	}
	
	public Type(String id, String name,String extension, String description){
		this.typeId = id;
		this.name = name;
		this.extension = extension;
		this.desc = description;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder("Type(");
		builder.append("id: ");
		builder.append(typeId);
		builder.append(", name: ");
		builder.append(name);
		builder.append(", description: ");
		builder.append(desc);
		builder.append(", extension: ");
		builder.append(extension);
		builder.append(")");
		return builder.toString();
	}
}
