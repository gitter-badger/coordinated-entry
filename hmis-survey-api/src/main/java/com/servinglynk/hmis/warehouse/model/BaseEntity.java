package com.servinglynk.hmis.warehouse.model;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name="CREATED_AT")
	protected LocalDateTime createdAt;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name="UPDATED_AT")
	protected LocalDateTime updatedAt;
	@Column(name="IS_ACTIVE")	
	protected boolean isActive=true;
	@Column(name="USER_ID")	
	protected String user;
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}