package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class ApplicantVO {
	private String teamId;
	private String applicationId;
	private String memberId;
	private String memberPic;
	private String memberName;
	private String applicationDate;
	private String applicationStatus;
	private String roleId;
	private String applicationFreewriting;
	public ApplicantVO() {
		super();
	}
	public ApplicantVO(JSONObject json) throws Exception{
		teamId=json.getString("teamId");
		applicationId=json.getString("applicationId");
		memberId=json.getString("memberId");
		memberPic=json.getString("memberPic");
		memberName=json.getString("memberName");
		applicationDate=json.getString("applicationDate");
		applicationStatus=json.getString("applicationStatus");
		roleId=json.getString("roleId");
		applicationFreewriting=json.getString("applicationFreewriting");
	}
	public ApplicantVO(String teamId, String applicationId, String memberId, String memberPic, String memberName,
			String applicationDate, String applicationStatus, String roleId, String applicationFreewriting) {
		super();
		this.teamId = teamId;
		this.applicationId = applicationId;
		this.memberId = memberId;
		this.memberPic = memberPic;
		this.memberName = memberName;
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
		this.roleId = roleId;
		this.applicationFreewriting = applicationFreewriting;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPic() {
		return memberPic;
	}
	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getApplicationFreewriting() {
		return applicationFreewriting;
	}
	public void setApplicationFreewriting(String applicationFreewriting) {
		this.applicationFreewriting = applicationFreewriting;
	}
	@Override
	public String toString() {
		return "ApplicantVO [teamId=" + teamId + ", applicationId=" + applicationId + ", memberId=" + memberId
				+ ", memberPic=" + memberPic + ", memberName=" + memberName + ", applicationDate=" + applicationDate
				+ ", applicationStatus=" + applicationStatus + ", roleId=" + roleId + ", applicationFreewriting="
				+ applicationFreewriting + "]";
	}
}
