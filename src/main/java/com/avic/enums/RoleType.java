package com.avic.enums;

public enum RoleType {

	MANAGER("MANAGER", "管理员"), SUPER("SUPER", "超级用户"), DEFAULT("DEFAULT", "普通用户");

	private String value;
	private String name;

	private RoleType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.value;
	}
}
