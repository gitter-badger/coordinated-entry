package com.servinglynk.hmis.warehouse.core.model;

import java.time.LocalDateTime;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class ClientModel {
	
	@JsonSerialize(using=JsonDateSerializer.class)
	 LocalDateTime dateCreated;
	@JsonSerialize(using=JsonDateSerializer.class)	
	LocalDateTime dateUpdated;
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}



	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}



	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}



	@Override
	public String toString() {
		try{
			return toJSONString();
		}catch(Exception e){ return ToStringBuilder.reflectionToString(this);}
	}
	

	
	public String toJSONString() throws Exception {
		JSONObjectMapper objectMapper = new JSONObjectMapper();
		String jsonString = objectMapper.writeValueAsString(this);
		return jsonString;
	}
}