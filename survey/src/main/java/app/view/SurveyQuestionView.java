package app.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class SurveyQuestionView implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer surveyQuestionId;

	/** survey_id. */
	private Integer surveyId;

	/** question_id. */
	private Integer questionId;

	/** question_parent. */
	private String questionParent;

	/** question_child. */
	private String questionChild;

	/** required. */
	private String required;

	/** date_created. */
	private Date dateCreated;

	/** date_updated. */
	private Date dateUpdated;

	/** user_id. */
	private String userId;
	
	/** section_id. */
	private String sectionId;

	private SurveyView surveyView;

	private QuestionView questionView;

	/**
	 * Constructor.
	 */
	public SurveyQuestionView() {
	}

	
	public Integer getSurveyQuestionId() {
		return surveyQuestionId;
	}


	public void setSurveyQuestionId(Integer surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}


	public Integer getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}


	public Integer getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


	/**
	 * Set the question_parent.
	 * 
	 * @param questionParent
	 *            question_parent
	 */
	public void setQuestionParent(String questionParent) {
		this.questionParent = questionParent;
	}

	/**
	 * Get the question_parent.
	 * 
	 * @return question_parent
	 */
	public String getQuestionParent() {
		return this.questionParent;
	}

	/**
	 * Set the question_child.
	 * 
	 * @param questionChild
	 *            question_child
	 */
	public void setQuestionChild(String questionChild) {
		this.questionChild = questionChild;
	}

	/**
	 * Get the question_child.
	 * 
	 * @return question_child
	 */
	public String getQuestionChild() {
		return this.questionChild;
	}

	/**
	 * Set the required.
	 * 
	 * @param required
	 *            required
	 */
	public void setRequired(String required) {
		this.required = required;
	}

	/**
	 * Get the required.
	 * 
	 * @return required
	 */
	public String getRequired() {
		return this.required;
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
	 * Set the Survey.
	 * 
	 * @param surveyView
	 *            Survey
	 */
	public void setSurvey(SurveyView surveyView) {
		this.surveyView = surveyView;
	}

	/**
	 * Get the Survey.
	 * 
	 * @return Survey
	 */
	public SurveyView getSurvey() {
		return this.surveyView;
	}

	/**
	 * Set the Question.
	 * 
	 * @param questionView
	 *            Question
	 */
	public void setQuestion(QuestionView questionView) {
		this.questionView = questionView;
	}

	/**
	 * Get the Question.
	 * 
	 * @return Question
	 */
	public QuestionView getQuestion() {
		return this.questionView;
	}


	public String getSectionId() {
		return sectionId;
	}


	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}


}
