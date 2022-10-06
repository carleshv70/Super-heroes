package org.chuix.superheros.model.dto;

public enum OwnerEnum {

	DC("02"),
	Marvel("01");

	public final String value;
	
	private OwnerEnum(String value) {
		this.value = value;
	}
	
	public String getValue() { return this.value; }
	
	public static OwnerEnum getOwnerEnum(String id) {
		for(OwnerEnum item : values() ) {
			if(item.getValue().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	
}
