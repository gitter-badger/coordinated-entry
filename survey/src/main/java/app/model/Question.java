package app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class of Question.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
public class Question implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** question_id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String questionId;

	/** question_name. */
	private String questionName;

	/** display_text. */
	private String displayText;

	/** question_data_type. */
	private String questionDataType;

	/** question_group_id. */
	private String questionGroupId;

	/** options_single_multiple_select. */
	private String optionsSingleMultipleSelect;

	/** is_copy_question_id. */
	private String isCopyQuestionId;

	/** hud_boolean. */
	private String hudBoolean;

	/** locked. */
	private String locked;

	/** inactive. */
	private String inactive;

	/** label_value. */
	private String labelValue;

	/** date_created. */
	private Date dateCreated;

	/** date_updated. */
	private Date dateUpdated;

	/** user_id. */
	private String userId;

	/** survey_id. */
	private String surveyId;

	/** The set of Survey_Question. */
	private List<SurveyQuestion> surveyQuestion;

	/**
	 * Constructor.
	 */
	public Question() {
		this.surveyQuestion = new ArrayList<SurveyQuestion>();
	}

	/**
	 * Set the question_id.
	 * 
	 * @param questionId
	 *            question_id
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	/**
	 * Get the question_id.
	 * 
	 * @return question_id
	 */
	public String getQuestionId() {
		return this.questionId;
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

	/**
	 * Set the question_group_id.
	 * 
	 * @param questionGroupId
	 *            question_group_id
	 */
	public void setQuestionGroupId(String questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	/**
	 * Get the question_group_id.
	 * 
	 * @return question_group_id
	 */
	public String getQuestionGroupId() {
		return this.questionGroupId;
	}

	/**
	 * Set the options_single_multiple_select.
	 * 
	 * @param optionsSingleMultipleSelect
	 *            options_single_multiple_select
	 */
	public void setOptionsSingleMultipleSelect(String optionsSingleMultipleSelect) {
		this.optionsSingleMultipleSelect = optionsSingleMultipleSelect;
	}

	/**
	 * Get the options_single_multiple_select.
	 * 
	 * @return options_single_multiple_select
	 */
	public String getOptionsSingleMultipleSelect() {
		return this.optionsSingleMultipleSelect;
	}

	/**
	 * Set the is_copy_question_id.
	 * 
	 * @param isCopyQuestionId
	 *            is_copy_question_id
	 */
	public void setIsCopyQuestionId(String isCopyQuestionId) {
		this.isCopyQuestionId = isCopyQuestionId;
	}

	/**
	 * Get the is_copy_question_id.
	 * 
	 * @return is_copy_question_id
	 */
	public String getIsCopyQuestionId() {
		return this.isCopyQuestionId;
	}

	/**
	 * Set the hud_boolean.
	 * 
	 * @param hudBoolean
	 *            hud_boolean
	 */
	public void setHudBoolean(String hudBoolean) {
		this.hudBoolean = hudBoolean;
	}

	/**
	 * Get the hud_boolean.
	 * 
	 * @return hud_boolean
	 */
	public String getHudBoolean() {
		return this.hudBoolean;
	}

	/**
	 * Set the locked.
	 * 
	 * @param locked
	 *            locked
	 */
	public void setLocked(String locked) {
		this.locked = locked;
	}

	/**
	 * Get the locked.
	 * 
	 * @return locked
	 */
	public String getLocked() {
		return this.locked;
	}

	/**
	 * Set the inactive.
	 * 
	 * @param inactive
	 *            inactive
	 */
	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	/**
	 * Get the inactive.
	 * 
	 * @return inactive
	 */
	public String getInactive() {
		return this.inactive;
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
	 * Set the user_id.
	 * 
	 * @param userId
	 *            user_id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Get the user_id.
	 * 
	 * @return user_id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Set the survey_id.
	 * 
	 * @param surveyId
	 *            survey_id
	 */
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	/**
	 * Get the survey_id.
	 * 
	 * @return survey_id
	 */
	public String getSurveyId() {
		return this.surveyId;
	}

	/**
	 * Set the set of the Survey_Question.
	 * 
	 * @param surveyQuestionSet
	 *            The set of Survey_Question
	 */
	public void setSurveyQuestionSet(List<SurveyQuestion> surveyQuestionSet) {
		this.surveyQuestion = surveyQuestionSet;
	}

	/**
	 * Add the Survey_Question.
	 * 
	 * @param surveyQuestion
	 *            Survey_Question
	 */
	public void addSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion.add(surveyQuestion);
	}

	/**
	 * Get the set of the Survey_Question.
	 * 
	 * @return The set of Survey_Question
	 */
	public List<SurveyQuestion> getSurveyQuestionSet() {
		return this.surveyQuestion;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((surveyId == null) ? 0 : surveyId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Question other = (Question) obj;
		if (surveyId == null) {
			if (other.surveyId != null) {
				return false;
			}
		} else if (!surveyId.equals(other.surveyId)) {
			return false;
		}
		return true;
	}

}
