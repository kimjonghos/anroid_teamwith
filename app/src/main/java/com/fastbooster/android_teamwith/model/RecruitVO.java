package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class RecruitVO {
	private String recruitId;
	private String teamId;
	private String recruitPeopleNum;
	private String recruitPreference;
	private String recruitExplain;
	private String roleId;
	public RecruitVO() {
		super();
	}
	public RecruitVO(JSONObject json) throws Exception{
		recruitId=json.getString("recruitId");
		teamId=json.getString("teamId");
		recruitPeopleNum=json.getString("recruitPeopleNum");
		recruitPreference=json.getString("recruitPreference");
		recruitExplain=json.getString("recruitExplain");
		roleId=json.getString("roleId");
	}
	public RecruitVO(String recruitId, String teamId, String recruitPeopleNum, String recruitPreference,
			String recruitExplain, String roleId) {
		super();
		this.recruitId = recruitId;
		this.teamId = teamId;
		this.recruitPeopleNum = recruitPeopleNum;
		this.recruitPreference = recruitPreference;
		this.recruitExplain = recruitExplain;
		this.roleId = roleId;
	}
	public String getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getRecruitPeopleNum() {
		return recruitPeopleNum;
	}
	public void setRecruitPeopleNum(String recruitPeopleNum) {
		this.recruitPeopleNum = recruitPeopleNum;
	}
	public String getRecruitPreference() {
		return recruitPreference;
	}
	public void setRecruitPreference(String recruitPreference) {
		this.recruitPreference = recruitPreference;
	}
	public String getRecruitExplain() {
		return recruitExplain;
	}
	public void setRecruitExplain(String recruitExplain) {
		this.recruitExplain = recruitExplain;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "RecruitVO [recruitId=" + recruitId + ", teamId=" + teamId + ", recruitPeopleNum=" + recruitPeopleNum
				+ ", recruitPreference=" + recruitPreference + ", recruitExplain=" + recruitExplain + ", roleId="
				+ roleId + "]";
	}
}
