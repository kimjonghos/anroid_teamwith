package com.fastbooster.android_teamwith.model;

import org.json.JSONException;
import org.json.JSONObject;

public class MyApplicationVO {
    private String teamId;
    private String teamPic;
    private String teamName;
    private String roleId;
    private String applicationDate;
    private String applicationFreewriting;
    private String applicationStatus;
    private String applicationId;
    private String memberId;

    public MyApplicationVO() {
        super();
    }

    public MyApplicationVO(JSONObject jo) throws JSONException {
        this.teamId = jo.getString("teamId");
        this.teamPic = jo.getString("teamPic");
        this.teamName = jo.getString("teamName");
        this.roleId = jo.getString("roleId");
        this.applicationDate = jo.getString("applicationDate");
        this.applicationFreewriting = jo.getString("applicationFreewriting");
        this.applicationStatus = jo.getString("applicationStatus");
        this.applicationId = jo.getString("applicationId");
        this.memberId = jo.getString("memberId");
    }

    public MyApplicationVO(String teamId, String teamPic, String teamName, String roleId, String applicationDate,
                           String applicationFreewriting, String applicationStatus, String applicationId, String memberId) {
        super();
        this.teamId = teamId;
        this.teamPic = teamPic;
        this.teamName = teamName;
        this.roleId = roleId;
        this.applicationDate = applicationDate;
        this.applicationFreewriting = applicationFreewriting;
        this.applicationStatus = applicationStatus;
        this.applicationId = applicationId;
        this.memberId = memberId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamPic() {
        return teamPic;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationFreewriting() {
        return applicationFreewriting;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setTeamPic(String teamPic) {
        this.teamPic = teamPic;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setApplicationFreewriting(String applicationFreewriting) {
        this.applicationFreewriting = applicationFreewriting;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String toString() {
        return "MyApplicationVO [teamId=" + teamId + ", teamPic=" + teamPic + ", teamName=" + teamName + ", roleId="
                + roleId + ", applicationDate=" + applicationDate + ", applicationFreewriting=" + applicationFreewriting
                + ", applicationStatus=" + applicationStatus + ", applicationId=" + applicationId + ", memberId="
                + memberId + "]";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((applicationDate == null) ? 0 : applicationDate.hashCode());
        result = prime * result + ((applicationFreewriting == null) ? 0 : applicationFreewriting.hashCode());
        result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
        result = prime * result + ((applicationStatus == null) ? 0 : applicationStatus.hashCode());
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
        result = prime * result + ((teamPic == null) ? 0 : teamPic.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyApplicationVO other = (MyApplicationVO) obj;
        if (applicationDate == null) {
            if (other.applicationDate != null)
                return false;
        } else if (!applicationDate.equals(other.applicationDate))
            return false;
        if (applicationFreewriting == null) {
            if (other.applicationFreewriting != null)
                return false;
        } else if (!applicationFreewriting.equals(other.applicationFreewriting))
            return false;
        if (applicationId == null) {
            if (other.applicationId != null)
                return false;
        } else if (!applicationId.equals(other.applicationId))
            return false;
        if (applicationStatus == null) {
            if (other.applicationStatus != null)
                return false;
        } else if (!applicationStatus.equals(other.applicationStatus))
            return false;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (teamId == null) {
            if (other.teamId != null)
                return false;
        } else if (!teamId.equals(other.teamId))
            return false;
        if (teamName == null) {
            if (other.teamName != null)
                return false;
        } else if (!teamName.equals(other.teamName))
            return false;
        if (teamPic == null) {
            if (other.teamPic != null)
                return false;
        } else if (!teamPic.equals(other.teamPic))
            return false;
        return true;
    }


}
