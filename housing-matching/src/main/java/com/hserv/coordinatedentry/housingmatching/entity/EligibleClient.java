package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "eligible_clients", schema = "housing_match")
public class EligibleClient implements Serializable {

	private UUID clientId;
	private String surveyType;
	private Integer surveyScore;
	private String programType;
	private Boolean matched;
	private LocalDateTime surveyDate;
	private String spdatLabel;
	private int zipCode;
	
	private List<Match> matchs = new ArrayList<>(); 
	
	public EligibleClient() {
	}

	public EligibleClient(UUID clientId) {
		this.clientId = clientId;
	}

	public EligibleClient(UUID clientId, String surveyType, Integer surveyScore, String programType,
			Boolean matched, LocalDateTime surveyDate, String spdatLabel, Set<Match> matchReservations
			,int zipCode) {
		this.clientId = clientId;
		this.surveyType = surveyType;
		this.surveyScore = surveyScore;
		this.programType = programType;
		this.matched = matched;
		this.surveyDate = surveyDate;
		this.spdatLabel = spdatLabel;
		this.zipCode = zipCode;
	}

	@Id
	@Column(name = "client_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getClientId() {
		return this.clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	@Column(name = "survey_type")
	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	@Column(name = "survey_score")
	public Integer getSurveyScore() {
		return this.surveyScore;
	}

	public void setSurveyScore(Integer surveyScore) {
		this.surveyScore = surveyScore;
	}

	@Column(name = "program_type")
	public String getProgramType() {
		return this.programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	@Column(name = "matched")
	public Boolean getMatched() {
		return this.matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "survey_date", length = 13)
	public LocalDateTime getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(LocalDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}

	@Column(name = "spdat_label")
	public String getSpdatLabel() {
		return this.spdatLabel;
	}

	public void setSpdatLabel(String spdatLabel) {
		this.spdatLabel = spdatLabel;
	}

	//@Transient
	@Column(name = "zip_code")
	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@OneToMany(mappedBy="eligibleClient",cascade= CascadeType.REMOVE)
	public List<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}
}