/****************************************************************************
 * Used for Hybrid Seeded Excel Upload
 * @date 18-July-2018
 * @author Vamsi Krish
 ***************************************************************************/
package com.stl.ngms.setting.model;

public class HybridSeededSurveyConfigDto {

	private long hybrid_user_id;
	private long survey_id;
	private String remote_search_filters;
	private String seed_users;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getHybrid_user_id() {
		return hybrid_user_id;
	}
	public void setHybrid_user_id(long hybrid_user_id) {
		this.hybrid_user_id = hybrid_user_id;
	}
	public long getSurvey_id() {
		return survey_id;
	}
	public void setSurvey_id(long survey_id) {
		this.survey_id = survey_id;
	}
	public String getRemote_search_filters() {
		return remote_search_filters;
	}
	public void setRemote_search_filters(String remote_search_filters) {
		this.remote_search_filters = remote_search_filters;
	}
	public String getSeed_users() {
		return seed_users;
	}
	public void setSeed_users(String seed_users) {
		this.seed_users = seed_users;
	}
}
