package com.synectiks.pref.domain.vo;

import java.io.Serializable;

public class QueryResult implements Serializable {
	int statusCode;
	String statusDesc;
	String entity;
	String status;
	
	public QueryResult() {
		
	}
	public QueryResult(String entity, String status){
		this.entity = entity;
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
}
