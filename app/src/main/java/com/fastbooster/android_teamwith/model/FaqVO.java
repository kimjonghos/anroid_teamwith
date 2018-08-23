package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class FaqVO {
	private String faqId;
	private String faqQuestion;
	private String faqAnswer;
	private String teamId;

	public FaqVO() {
		super();
	}
	public FaqVO(JSONObject json) throws Exception{
		faqId=json.getString("faqId");
		faqQuestion=json.getString("faqQuestion");
		faqAnswer=json.getString("faqAnswer");
		teamId=json.getString("teamId");
	}
	public FaqVO(String faqId, String faqQuestion, String faqAnswer, String teamId) {
		super();
		this.faqId = faqId;
		this.faqQuestion = faqQuestion;
		this.faqAnswer = faqAnswer;
		this.teamId = teamId;
	}
	public String getFaqId() {
		return faqId;
	}
	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}
	public String getFaqQuestion() {
		return faqQuestion;
	}
	public void setFaqQuestion(String faqQuestion) {
		this.faqQuestion = faqQuestion;
	}
	public String getFaqAnswer() {
		return faqAnswer;
	}
	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	@Override
	public String toString() {
		return "FaqVO [faqId=" + faqId + ", faqQuestion=" + faqQuestion + ", faqAnswer=" + faqAnswer + ", teamId="
				+ teamId + "]";
	}
}
