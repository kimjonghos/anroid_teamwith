package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;
    private String memberBirth;
    private String memberPic;
    private String memberActive;
    private String memberPhone;
    private String memberPublic;
    private String memberIntro;
    private String memberOutDate;
    private String memberAuth;
    private String roleId;
    private String regionId1;
    private String regionId2;


    public MemberVO() {
        super();
    }

    public MemberVO(JSONObject object) {
        try {
            this.memberId = object.getString("memberId");
            this.memberName = object.getString("memberName");
            this.memberPassword = object.getString("memberPassword");
            this.memberEmail = object.getString("memberEmail");
            this.memberBirth = object.getString("memberBirth");
            this.memberPic = object.getString("memberPic");
            this.memberActive = object.getString("memberActive");
            this.memberPhone = object.getString("memberPhone");
            this.memberPublic = object.getString("memberPublic");
            this.memberIntro = object.getString("memberIntro");
            this.memberOutDate = object.getString("memberOutDate");
            this.memberAuth = object.getString("memberAuth");
            this.roleId = object.getString("roleId");
            this.regionId1 = object.getString("regionId1");
            this.regionId2 = object.getString("regionId2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberVO(String memberId, String memberName, String memberPassword, String memberEmail, String memberBirth,
                    String memberPic, String memberActive, String memberPhone, String memberPublic, String memberIntro,
                    String memberOutDate, String memberAuth, String roleId, String regionId1, String regionId2) {
        super();
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberBirth = memberBirth;
        this.memberPic = memberPic;
        this.memberActive = memberActive;
        this.memberPhone = memberPhone;
        this.memberPublic = memberPublic;
        this.memberIntro = memberIntro;
        this.memberOutDate = memberOutDate;
        this.memberAuth = memberAuth;
        this.roleId = roleId;
        this.regionId1 = regionId1;
        this.regionId2 = regionId2;
    }

    public MemberVO(String memberId, String memberPhone, String memberPublic, String memberIntro,
                    String roleId, String regionId1, String regionId2) {
        this.memberId = memberId;
        this.memberPhone = memberPhone;
        this.memberPublic = memberPublic;
        this.memberIntro = memberIntro;
        this.roleId = roleId;
        this.regionId1 = regionId1;
        this.regionId2 = regionId2;

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

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberBirth() {
        return memberBirth;
    }

    public void setMemberBirth(String memberBirth) {
        this.memberBirth = memberBirth;
    }

    public String getMemberPic() {
        return memberPic;
    }

    public void setMemberPic(String memberPic) {
        this.memberPic = memberPic;
    }

    public String getMemberActive() {
        return memberActive;
    }

    public void setMemberActive(String memberActive) {
        this.memberActive = memberActive;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberPublic() {
        return memberPublic;
    }

    public void setMemberPublic(String memberPublic) {
        this.memberPublic = memberPublic;
    }

    public String getMemberIntro() {
        return memberIntro;
    }

    public void setMemberIntro(String memberIntro) {
        this.memberIntro = memberIntro;
    }

    public String getMemberOutDate() {
        return memberOutDate;
    }

    public void setMemberOutDate(String memberOutDate) {
        this.memberOutDate = memberOutDate;
    }

    public String getMemberAuth() {
        return memberAuth;
    }

    public void setMemberAuth(String memberAuth) {
        this.memberAuth = memberAuth;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRegionId1() {
        return regionId1;
    }

    public void setRegionId1(String regionId1) {
        this.regionId1 = regionId1;
    }

    public String getRegionId2() {
        return regionId2;
    }

    public void setRegionId2(String regionId2) {
        this.regionId2 = regionId2;
    }

    @Override
    public String toString() {
        return "MemberVO [memberId=" + memberId + ", memberName=" + memberName + ", memberPassword=" + memberPassword
                + ", memberEmail=" + memberEmail + ", memberBirth=" + memberBirth + ", memberPic=" + memberPic
                + ", memberActive=" + memberActive + ", memberPhone=" + memberPhone + ", memberPublic=" + memberPublic
                + ", memberIntro=" + memberIntro + ", memberOutDate=" + memberOutDate + ", memberAuth=" + memberAuth
                + ", roleId=" + roleId + ", regionId1=" + regionId1 + ", regionId2=" + regionId2 + "]";
    }
}
