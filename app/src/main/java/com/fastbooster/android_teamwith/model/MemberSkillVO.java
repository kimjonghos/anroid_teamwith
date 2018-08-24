package com.fastbooster.android_teamwith.model;


import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberSkillVO {
	private String memberId;
	private String[] skill;



	public MemberSkillVO() {

	}

	public MemberSkillVO(JSONObject j) throws JSONException {
		this.memberId=j.getString("memberId");
		JSONArray ja=j.getJSONArray("skill");
		skill=new String[ja.length()];
		for(int i=0;i<ja.length();i++){
			skill[i]=ja.getString(i);
		}
	}
	public MemberSkillVO(String memberId, String[] skill) {
		super();
		this.memberId = memberId;
		this.skill = skill;
	}

	public final String getMemberId() {
		return memberId;
	}

	public final String[] getSkill() {
		return skill;
	}

	public final void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public final void setSkill(String[] skill) {
		this.skill = skill;
	}

	public final String[] getSkillName(){
		String [] skillName=new String[skill.length];
		for(int i=0;i<skill.length;i++){
			skillName[i]=(String)ApplicationShare.skillList.get(skill[i]);
		}
		return skillName;
	}
	@Override
	public String toString() {
		return "MemberSkillVO [memberId=" + memberId + ", skill=" + skill + "]";
	}

}
