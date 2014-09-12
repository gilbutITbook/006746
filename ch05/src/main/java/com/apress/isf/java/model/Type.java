package com.apress.isf.java.model;

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
	
	public String toString() {
		StringBuilder builder = new StringBuilder("Type Definition:");
		builder.append("\nName: ");
		builder.append(name);
		builder.append("\nDescription: ");
		builder.append(desc);
		builder.append("\nExtension: ");
		builder.append(extension);
		return builder.toString();
	}
	
}
