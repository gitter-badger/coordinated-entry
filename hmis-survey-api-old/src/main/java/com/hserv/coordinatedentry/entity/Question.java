package com.hserv.coordinatedentry.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.util.JsonDateSerializer;

/**
 * Model class of Question.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Table(name = "question")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Question implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** question_id. */
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="question_id_seq")
//	@SequenceGenerator(name="question_id_seq", sequenceName="question_id_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer questionId;

	/** question_name. */
	@NotEmpty(message = "question.name.not.empty")
	@Length(max = 255, message = "question.name.max.length")
	private String questionName;

	/** display_text. */
	@NotEmpty(message = "question.display_text.not.empty")
	@Length(max = 255, message = "question.display_text.max.length")
	private String displayText;

	/** question_data_type. */
	@NotEmpty(message = "question.question_data_type.not.empty")
	@Length(max = 255, message = "question.question_data_type.max.length")
	private String questionDataType;

	/** question_group_id. */
	@NotEmpty(message = "question.question_group_id.not.empty")
	@Length(max = 255, message = "question.question_group_id.max.length")
	private String questionGroupId;

	/** options_single_multiple_select. */
	private Boolean optionsSingleMultipleSelect = Boolean.TRUE;

	/** is_copy_question_id. */
	private Boolean isCopyQuestionId =  Boolean.FALSE;

	/** hud_boolean. */
	private Boolean hudBoolean = Boolean.TRUE;

	/** locked. */
	private Boolean locked =  Boolean.FALSE;

	/** inactive. */
	private Boolean inactive =  Boolean.TRUE;

	/** label_value. */
	@NotEmpty(message = "question.name.not.empty")
	@Length(max = 255, message = "question.name.max.length")
	private String labelValue;

	/** date_created. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateCreated;

	/** date_updated. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateUpdated;

	/** user_id. */
	@NotEmpty(message = "survey.userid.not.empty")
	@Length(max = 255, message = "survey.userid.max.length")
	private String userId;


	/** The set of Survey_Question. */
	//@OneToMany(mappedBy="question", cascade=CascadeType.ALL)
	//@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="section_fk_id")
	@JsonBackReference
	private SurveySection surveySection;
	
	/*@ManyToMany(mappedBy="questions")
	private List<SurveySection> surveySection;*/
	
	@OneToMany(mappedBy = "question")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<SectionQuestionMapping> sectionQuestionMappings;

	@OneToMany(mappedBy="question", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<CustomPicklist> customPicklist;
	/**
	 * Constructor.
	 */
	public Question() {
	}


	/**
	 * Set the question_name.
	 * 
	 * @param questionName
	 *            question_name
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	/**
	 * Get the question_name.
	 * 
	 * @return question_name
	 */
	public String getQuestionName() {
		return this.questionName;
	}

	/**
	 * Set the display_text.
	 * 
	 * @param displayText
	 *            display_text
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	/**
	 * Get the display_text.
	 * 
	 * @return display_text
	 */
	public String getDisplayText() {
		return this.displayText;
	}

	/**
	 * Set the question_data_type.
	 * 
	 * @param questionDataType
	 *            question_data_type
	 */
	public void setQuestionDataType(String questionDataType) {
		this.questionDataType = questionDataType;
	}

	/**
	 * Get the question_data_type.
	 * 
	 * @return question_data_type
	 */
	public String getQuestionDataType() {
		return this.questionDataType;
	}

	
	

	public String getQuestionGroupId() {
		return questionGroupId;
	}


	public void setQuestionGroupId(String questionGroupId) {
		this.questionGroupId = questionGroupId;
	}


	public Boolean getOptionsSingleMultipleSelect() {
		return optionsSingleMultipleSelect;
	}


	public void setOptionsSingleMultipleSelect(Boolean optionsSingleMultipleSelect) {
		this.optionsSingleMultipleSelect = optionsSingleMultipleSelect;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Boolean getIsCopyQuestionId() {
		return isCopyQuestionId;
	}


	public void setIsCopyQuestionId(Boolean isCopyQuestionId) {
		this.isCopyQuestionId = isCopyQuestionId;
	}


	public Boolean getHudBoolean() {
		return hudBoolean;
	}


	public void setHudBoolean(Boolean hudBoolean) {
		this.hudBoolean = hudBoolean;
	}


	public Boolean getLocked() {
		return locked;
	}


	public void setLocked(Boolean locked) {
		this.locked = locked;
	}


	public Boolean getInactive() {
		return inactive;
	}


	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}


	/**
	 * Set the label_value.
	 * 
	 * @param labelValue
	 *            label_value
	 */
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

	/**
	 * Get the label_value.
	 * 
	 * @return label_value
	 */
	public String getLabelValue() {
		return this.labelValue;
	}

	/**
	 * Set the date_created.
	 * 
	 * @param dateCreated
	 *            date_created
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Get the date_created.
	 * 
	 * @return date_created
	 */
	public Date getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * Set the date_updated.
	 * 
	 * @param dateUpdated
	 *            date_updated
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	/**
	 * Get the date_updated.
	 * 
	 * @return date_updated
	 */
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	

	/**
	 * Set the survey_id.
	 * 
	 * @param surveyId
	 *            survey_id
	 */

	
	
	public Integer getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public SurveySection getSurveySection() {
		return surveySection;
	}

	public void setSurveySection(SurveySection surveySection) {
		this.surveySection = surveySection;
	}
	
	/*public List<SurveySection> getSurveySection() {
		return surveySection;
	}


	public void setSurveySection(List<SurveySection> surveySection) {
		this.surveySection = surveySection;
	}*/
	

	public List<SectionQuestionMapping> getSectionQuestionMappings() {
		return sectionQuestionMappings;
	}


	public void setSectionQuestionMappings(
			List<SectionQuestionMapping> sectionQuestionMappings) {
		this.sectionQuestionMappings = sectionQuestionMappings;
	}


	public List<CustomPicklist> getCustomPicklist() {
		return customPicklist;
	}

	


	public void setCustomPicklist(List<CustomPicklist> customPicklist) {
		this.customPicklist = customPicklist;
	}


}
