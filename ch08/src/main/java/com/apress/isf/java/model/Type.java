/**
 * 
 */
package com.apress.isf.java.model;

/**
 * @author Felipe Gutierrez
 *
 */
public class Type {
	
	private String name;	
	private String desc;
	private String extension;

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
		builder.append("name: ");
		builder.append(name);
		builder.append(", description: ");
		builder.append(desc);
		builder.append(", extension: ");
		builder.append(extension);
		builder.append(")");
		return builder.toString();
	}
}
