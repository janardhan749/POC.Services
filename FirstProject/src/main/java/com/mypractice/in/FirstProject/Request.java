package com.mypractice.in.FirstProject;

import org.codehaus.jackson.JsonNode;

public class Request {
	private String action;
	private String endpoint;
	private JsonNode payload;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public JsonNode getPayload() {
		return payload;
	}
	public void setPayload(JsonNode payload) {
		this.payload = payload;
	}
	
}
