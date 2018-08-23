package com.fastbooster.android_teamwith.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TeamDetailVO {
    private String teamId;
    private String teamProjectName;
    private String teamName;
    private String teamSummary;
    private String teamContent;
    private String projectCategoryId;
    private String regionId;
    private String teamEndDate;
    private String teamStatus;
    private String teamPic;
    private String teamContestName;
    private String teamContestLink;
    private String memberId;
    private String memberName;
    private String memberPic;
    private String roleId;
    public TeamDetailVO() {
        super();
    }
    public TeamDetailVO(JSONObject json) throws JSONException{
        teamId=json.getString("teamId");
        teamProjectName=json.getString("teamProjectName");
        teamName=json.getString("teamName");
        teamSummary=json.getString("teamSummary");
        teamContent=json.getString("teamContent");
        projectCategoryId=json.getString("projectCategoryId");
        regionId=json.getString("regionId");
        teamEndDate=json.getString("teamEndDate");
        teamStatus=json.getString("teamStatus");
        teamPic=json.getString("teamPic");
        teamContestName=json.getString("teamContestName");
        teamContestLink=json.getString("teamContestLink");
        memberId=json.getString("memberId");
        memberName=json.getString("memberName");
        memberPic=json.getString("memberPic");
        roleId=json.getString("roleId");
    }
    public TeamDetailVO(String teamId, String teamProjectName, String teamName, String teamSummary, String teamContent,
                        String projectCategoryId, String regionId, String teamEndDate, String teamStatus, String teamPic,
                        String teamContestName, String teamContestLink, String memberId, String memberName, String memberPic,
                        String roleId) {
        super();
        this.teamId = teamId;
        this.teamProjectName = teamProjectName;
        this.teamName = teamName;
        this.teamSummary = teamSummary;
        this.teamContent = teamContent;
        this.projectCategoryId = projectCategoryId;
        this.regionId = regionId;
        this.teamEndDate = teamEndDate;
        this.teamStatus = teamStatus;
        this.teamPic = teamPic;
        this.teamContestName = teamContestName;
        this.teamContestLink = teamContestLink;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPic = memberPic;
        this.roleId = roleId;
    }
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public String getTeamProjectName() {
        return teamProjectName;
    }
    public void setTeamProjectName(String teamProjectName) {
        this.teamProjectName = teamProjectName;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getTeamSummary() {
        return teamSummary;
    }
    public void setTeamSummary(String teamSummary) {
        this.teamSummary = teamSummary;
    }
    public String getTeamContent() {
        return teamContent;
    }
    public void setTeamContent(String teamContent) {
        this.teamContent = teamContent;
    }
    public String getProjectCategoryId() {
        return projectCategoryId;
    }
    public void setProjectCategoryId(String projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public String getTeamEndDate() {
        return teamEndDate;
    }
    public void setTeamEndDate(String teamEndDate) {
        this.teamEndDate = teamEndDate;
    }
    public String getTeamStatus() {
        return teamStatus;
    }
    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }
    public String getTeamPic() {
        return teamPic;
    }
    public void setTeamPic(String teamPic) {
        this.teamPic = teamPic;
    }
    public String getTeamContestName() {
        return teamContestName;
    }
    public void setTeamContestName(String teamContestName) {
        this.teamContestName = teamContestName;
    }
    public String getTeamContestLink() {
        return teamContestLink;
    }
    public void setTeamContestLink(String teamContestLink) {
        this.teamContestLink = teamContestLink;
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
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return "TeamDetailVO [teamId=" + teamId + ", teamProjectName=" + teamProjectName + ", teamName=" + teamName
                + ", teamSummary=" + teamSummary + ", teamContent=" + teamContent + ", projectCategoryId="
                + projectCategoryId + ", regionId=" + regionId + ", teamEndDate=" + teamEndDate + ", teamStatus="
                + teamStatus + ", teamPic=" + teamPic + ", teamContestName=" + teamContestName + ", teamContestLink="
                + teamContestLink + ", memberId=" + memberId + ", memberName=" + memberName + ", memberPic=" + memberPic
                + ", roleId=" + roleId + "]";
    }
}
