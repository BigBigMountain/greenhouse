package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class ClimaticDataType implements Serializable {
	private static final long serialVersionUID = 1053217762877768954L;
	
	private Integer id;
	
	private String name;
	
	private String propertyName;

	private Byte isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
	}

	
	
	
	

	
	


}
