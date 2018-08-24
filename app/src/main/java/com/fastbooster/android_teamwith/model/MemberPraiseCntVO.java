package com.fastbooster.android_teamwith.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberPraiseCntVO {
	private String memberId;
	private String praiseId;
	private String praiseCnt;
	public MemberPraiseCntVO() {
		super();
	}
	public MemberPraiseCntVO(String memberId, String praiseId, String praiseCnt) {
		super();
		this.memberId = memberId;
		this.praiseId = praiseId;
		this.praiseCnt = praiseCnt;
	}
	public MemberPraiseCntVO(JSONObject j) throws JSONException {
		super();
		this.memberId = j.getString("memberId");
		this.praiseId = j.getString("praiseId");
		this.praiseCnt = j.getString("praiseCnt");
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPraiseId() {
		return praiseId;
	}
	public void setPraiseId(String praiseId) {
		this.praiseId = praiseId;
	}
	public String getPraiseCnt() {
		return praiseCnt;
	}
	public void setPraiseCnt(String praiseCnt) {
		this.praiseCnt = praiseCnt;
	}
	@Override
	public String toString() {
		return "MemberPraiseVO [memberId=" + memberId + ", praiseId=" + praiseId + ", praiseCnt=" + praiseCnt + "]";
	}
}
