package com.avic.enums;

public enum ActionType {
	
	ADD("ADD", "添加"), UPDATE("UPDATE", "修改"), DEL("DEL", "删除"), LOGIN("LOGIN", "登录"), LOGOUT("LOGOUT", "登出");

	private String value;
	private String name;

	private ActionType(String value, String name) {
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
