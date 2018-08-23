package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class RequireSkillVO {
	private String recruitId;
	private String skillId;

	public RequireSkillVO() {
	}
	public RequireSkillVO(JSONObject json)throws Exception{
		recruitId=json.getString("recruitId");
		skillId=json.getString("skillIds");
	}

	public RequireSkillVO(String recruitId, String skillId) {
		this.recruitId = recruitId;
		this.skillId = skillId;
	}

	public String getRecruitId() {
		return recruitId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "RequireSkillVO{" +
				"recruitId='" + recruitId + '\'' +
				", skillId='" + skillId + '\'' +
				'}';
	}
}
