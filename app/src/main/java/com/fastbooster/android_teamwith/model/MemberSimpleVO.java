package com.fastbooster.android_teamwith.model;

import org.json.JSONException;
import org.json.JSONObject;

public class MemberSimpleVO {
	private String memberId;
	private String memberName;
	private String memberPic;
	private String memberAuth;
	public MemberSimpleVO() {
		super();
	}
	public MemberSimpleVO(JSONObject jsonObject) throws JSONException {
		this.memberId = jsonObject.getString("memberId");
		this.memberName = jsonObject.getString("memberName");
		this.memberPic = jsonObject.getString("memberPic");
		this.memberAuth = jsonObject.getString("memberAuth");
	}
	public MemberSimpleVO(String memberId, String memberName, String memberPic, String memberAuth) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPic = memberPic;
		this.memberAuth = memberAuth;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPic() {
		return memberPic;
	}
	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	@Override
	public String toString() {
		return "MemberSimpleVO [memberId=" + memberId + ", memberName=" + memberName + ", memberPic=" + memberPic
				+ ", memberAuth=" + memberAuth + "]";
	}
}
