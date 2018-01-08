package com.springjpa.tree.model;

import java.util.List;

public class Parent extends Node {
	
	
	private String folder;
	private List<Node> children;
	
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folde) {
		this.folder = folde;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	
	

}
