package com.fastbooster.android_teamwith.model;


import com.fastbooster.android_teamwith.share.ApplicationShare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberProjectCategoryVO {
	private String memberId;
	private String[] projectCategoryId;

	public MemberProjectCategoryVO() {
		super();
	}


	public MemberProjectCategoryVO(String memberId, JSONArray ja) throws JSONException {
		this.memberId=memberId;
		projectCategoryId=new String[ja.length()];
		for(int i=0;i<ja.length();i++){
			projectCategoryId[i]=(String) ApplicationShare.categoryList.get(ja.getString(i));

		}
	}

	public MemberProjectCategoryVO(String memberId, String[] projectCategoryId) {
		super();
		this.memberId = memberId;
		this.projectCategoryId = projectCategoryId;
	}



	public final String getMemberId() {
		return memberId;
	}

	public final String[] getProjectCategoryId() {
		return projectCategoryId;
	}

	public final void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public final void setProjectCategoryId(String[] projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	@Override
	public String toString() {
		return "MemberProjectCategoryVO [memberId=" + memberId + ", projectCategoryId=" + projectCategoryId + "]";
	}

}
