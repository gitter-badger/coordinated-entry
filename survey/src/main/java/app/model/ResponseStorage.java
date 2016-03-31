package app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class of Response_Storage.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
public class ResponseStorage implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** survey_question_id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String surveyQuestionId;

	/** response_value. */
	private String responseValue;

	/** response_subassessment. */
	private String responseSubassessment;

	/** client_id. */
	private String clientId;

	/** app_id. */
	private String appId;

	/** effective_date. */
	private Date effectiveDate;

	/** date_created. */
	private Date dateCreated;

	/** date_updated. */
	private Date dateUpdated;

	/** user_id. */
	private String userId;

	/**
	 * Constructor.
	 */
	public ResponseStorage() {
	}

	/**
	 * Set the survey_question_id.
	 * 
	 * @param surveyQuestionId
	 *            survey_question_id
	 */
	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	/**
	 * Get the survey_question_id.
	 * 
	 * @return survey_question_id
	 */
	public String getSurveyQuestionId() {
		return this.surveyQuestionId;
	}

	/**
	 * Set the response_value.
	 * 
	 * @param responseValue
	 *            response_value
	 */
	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}

	/**
	 * Get the response_value.
	 * 
	 * @return response_value
	 */
	public String getResponseValue() {
		return this.responseValue;
	}

	/**
	 * Set the response_subassessment.
	 * 
	 * @param responseSubassessment
	 *            response_subassessment
	 */
	public void setResponseSubassessment(String responseSubassessment) {
		this.responseSubassessment = responseSubassessment;
	}

	/**
	 * Get the response_subassessment.
	 * 
	 * @return response_subassessment
	 */
	public String getResponseSubassessment() {
		return this.responseSubassessment;
	}

	/**
	 * Set the client_id.
	 * 
	 * @param clientId
	 *            client_id
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * Get the client_id.
	 * 
	 * @return client_id
	 */
	public String getClientId() {
		return this.clientId;
	}

	/**
	 * Set the app_id.
	 * 
	 * @param appId
	 *            app_id
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * Get the app_id.
	 * 
	 * @return app_id
	 */
	public String getAppId() {
		return this.appId;
	}

	/**
	 * Set the effective_date.
	 * 
	 * @param effectiveDate
	 *            effective_date
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Get the effective_date.
	 * 
	 * @return effective_date
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
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


}
