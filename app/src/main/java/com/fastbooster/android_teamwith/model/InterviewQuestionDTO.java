package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class InterviewQuestionDTO {
	private String interviewQuestionId;
	private String teamId;
	private String interviewQuestionContent;
	public InterviewQuestionDTO() {
		super();
	}
	public InterviewQuestionDTO(JSONObject json)throws Exception{
		interviewQuestionId=json.getString("interviewQuestionId");
		teamId=json.getString("teamId");
		interviewQuestionContent=json.getString("interviewQuestionContent");
	}
	public InterviewQuestionDTO(String interviewQuestionId, String teamId, String interviewQuestionContent) {
		super();
		this.interviewQuestionId = interviewQuestionId;
		this.teamId = teamId;
		this.interviewQuestionContent = interviewQuestionContent;
	}
	public String getInterviewQuestionId() {
		return interviewQuestionId;
	}
	public String getTeamId() {
		return teamId;
	}
	public String getInterviewQuestionContent() {
		return interviewQuestionContent;
	}
	public void setInterviewQuestionId(String interviewQuestionId) {
		this.interviewQuestionId = interviewQuestionId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public void setInterviewQuestionContent(String interviewQuestionContent) {
		this.interviewQuestionContent = interviewQuestionContent;
	}
	public String toString() {
		return "InterviewQuestionDTO [interviewQuestionId=" + interviewQuestionId + ", teamId=" + teamId
				+ ", interviewQuestionContent=" + interviewQuestionContent + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interviewQuestionContent == null) ? 0 : interviewQuestionContent.hashCode());
		result = prime * result + ((interviewQuestionId == null) ? 0 : interviewQuestionId.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterviewQuestionDTO other = (InterviewQuestionDTO) obj;
		if (interviewQuestionContent == null) {
			if (other.interviewQuestionContent != null)
				return false;
		} else if (!interviewQuestionContent.equals(other.interviewQuestionContent))
			return false;
		if (interviewQuestionId == null) {
			if (other.interviewQuestionId != null)
				return false;
		} else if (!interviewQuestionId.equals(other.interviewQuestionId))
			return false;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		return true;
	}
	
}
