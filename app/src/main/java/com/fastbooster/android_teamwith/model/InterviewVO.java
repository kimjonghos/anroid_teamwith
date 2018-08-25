package com.fastbooster.android_teamwith.model;

import org.json.JSONException;
import org.json.JSONObject;

public class InterviewVO {
    private String teamId;
    private String interviewQuestionContent;
    private String interviewAnswerContent;
    private String applicationId;
    private String interviewQuestionId;

    public InterviewVO() {
        super();
    }

    public InterviewVO(JSONObject jo) throws JSONException {
        this.teamId = jo.getString("teamId");
        this.interviewQuestionContent = jo.getString("interviewQuestionContent");
        this.interviewAnswerContent = jo.getString("interviewAnswerContent");
        this.applicationId = jo.getString("applicationId");
        this.interviewQuestionId = jo.getString("interviewQuestionId");
    }

    public InterviewVO(String teamId, String interviewQuestionContent, String interviewAnswerContent,
                       String applicationId, String interviewQuestionId) {
        super();
        this.teamId = teamId;
        this.interviewQuestionContent = interviewQuestionContent;
        this.interviewAnswerContent = interviewAnswerContent;
        this.applicationId = applicationId;
        this.interviewQuestionId = interviewQuestionId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getInterviewQuestionContent() {
        return interviewQuestionContent;
    }

    public String getInterviewAnswerContent() {
        return interviewAnswerContent;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getInterviewQuestionId() {
        return interviewQuestionId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setInterviewQuestionContent(String interviewQuestionContent) {
        this.interviewQuestionContent = interviewQuestionContent;
    }

    public void setInterviewAnswerContent(String interviewAnswerContent) {
        this.interviewAnswerContent = interviewAnswerContent;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setInterviewQuestionId(String interviewQuestionId) {
        this.interviewQuestionId = interviewQuestionId;
    }

    @Override
    public String toString() {
        return "InterviewVO [teamId=" + teamId + ", interviewQuestionContent=" + interviewQuestionContent
                + ", interviewAnswerContent=" + interviewAnswerContent + ", applicationId=" + applicationId
                + ", interviewQuestionId=" + interviewQuestionId + "]";
    }


}
