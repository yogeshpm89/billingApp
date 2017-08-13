package com.prounited.billingapp.helpers;

public class Criterion {

	private String key;
	private Object value;
	private String type;
	
	public Criterion() {
		super();
	}
	public Criterion(String key, Object value, String type) {
		super();
		this.key = key;
		this.value = value;
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Criterion [key=" + key + ", value=" + value + ", type=" + type
				+ "]";
	}
	
	
}
