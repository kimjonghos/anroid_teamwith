package com.fastbooster.android_teamwith.model;

import org.json.JSONObject;

public class MemberSearchVO {
    private String memberId;
    private String memberPic;
    private String memberName;
    private String roleId;
    private String memberRegisterDate;
    private String regionId1;
    private String regionId2;
    private String totalPraiseCnt;

    public MemberSearchVO() {
        super();
    }

    public MemberSearchVO(JSONObject f) {
        try {
            if (f.getString("roleId") != null) {
                setRoleId(f.getString("roleId"));
            }
            setMemberName(f.getString("memberName"));
            setMemberId(f.getString("memberId"));
            setMemberPic(f.getString("memberPic"));
            setMemberRegisterDate(f.getString("memberRegisterDate"));
            if (f.getString("regionId1") != null) {
                setRegionId1(f.getString("regionId1"));
            }
            if (f.getString("regionId2") != null) {
                setRegionId2(f.getString("regionId2"));
            }
            setTotalPraiseCnt(f.getString("totalPraiseCnt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberSearchVO(String memberId, String memberPic, String memberName, String roleId,
                          String memberRegisterDate, String regionId1, String regionId2, String totalPraiseCnt) {
        super();
        this.memberId = memberId;
        this.memberPic = memberPic;
        this.memberName = memberName;
        this.roleId = roleId;
        this.memberRegisterDate = memberRegisterDate;
        this.regionId1 = regionId1;
        this.regionId2 = regionId2;
        this.totalPraiseCnt = totalPraiseCnt;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPic() {
        return memberPic;
    }

    public void setMemberPic(String memberPic) {
        this.memberPic = memberPic;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMemberRegisterDate() {
        return memberRegisterDate;
    }

    public void setMemberRegisterDate(String memberRegisterDate) {
        this.memberRegisterDate = memberRegisterDate;
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

    public String getTotalPraiseCnt() {
        return totalPraiseCnt;
    }

    public void setTotalPraiseCnt(String totalPraiseCnt) {
        this.totalPraiseCnt = totalPraiseCnt;
    }

    @Override
    public String toString() {
        return "MemberSearchVO [memberId=" + memberId + ", memberPic=" + memberPic + ", memberName=" + memberName
                + ", roleId=" + roleId + ", memberRegisterDate=" + memberRegisterDate + ", regionId1=" + regionId1
                + ", regionId2=" + regionId2 + ", totalPraiseCnt=" + totalPraiseCnt + "]";
    }
}
