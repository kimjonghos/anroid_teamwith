package com.fastbooster.android_teamwith.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class PortfolioSimpleVO {
	private String portfolioId;
	private String portfolioPic;
	private String memberId;
	private String memberName;
	private String portfolioTitle;
	private String portfolioBest;
	private String projectCategoryId;
	private String portfolioIntro;
	private Date portfolioUpdateDate;
	public PortfolioSimpleVO() {
		super();
	}
	public PortfolioSimpleVO(String portfolioId, String portfolioPic, String memberId, String memberName,
			String portfolioTitle, String portfolioBest, String projectCategoryId, String portfolioIntro,Date portfolioUpdateDate) {
		super();
		this.portfolioId = portfolioId;
		this.portfolioPic = portfolioPic;
		this.memberId = memberId;
		this.memberName = memberName;
		this.portfolioTitle = portfolioTitle;
		this.portfolioBest = portfolioBest;
		this.projectCategoryId = projectCategoryId;
		this.portfolioIntro = portfolioIntro;
		this.portfolioUpdateDate=portfolioUpdateDate;
	}
	public PortfolioSimpleVO(JSONObject j) throws JSONException {
		super();
		this.portfolioId = j.getString("portfolioId");
		this.portfolioPic = j.getString("portfolioPic");
		this.memberId = j.getString("memberId");
		this.memberName = j.getString("memberName");
		this.portfolioTitle = j.getString("portfolioTitle");
		this.portfolioBest = j.getString("portfolioBest");
		this.projectCategoryId = j.getString("projectCategoryId");
		this.portfolioIntro = j.getString("portfolioIntro");
		if(j.getString("portfolioUpdateDate")!=null){
			this.portfolioUpdateDate=Date.valueOf(j.getString("portfolioUpdateDate"));
		}

	}
	public Date getPortfolioUpdateDate() {
		return portfolioUpdateDate;
	}
	public void setPortfolioUpdateDate(Date portfolioUpdateDate) {
		this.portfolioUpdateDate = portfolioUpdateDate;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getPortfolioPic() {
		return portfolioPic;
	}
	public void setPortfolioPic(String portfolioPic) {
		this.portfolioPic = portfolioPic;
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
	public String getPortfolioTitle() {
		return portfolioTitle;
	}
	public void setPortfolioTitle(String portfolioTitle) {
		this.portfolioTitle = portfolioTitle;
	}
	public String getPortfolioBest() {
		return portfolioBest;
	}
	public void setPortfolioBest(String portfolioBest) {
		this.portfolioBest = portfolioBest;
	}
	public String getProjectCategoryId() {
		return projectCategoryId;
	}
	public void setProjectCategoryId(String projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}
	public String getPortfolioIntro() {
		return portfolioIntro;
	}
	public void setPortfolioIntro(String portfolioIntro) {
		this.portfolioIntro = portfolioIntro;
	}
	@Override
	public String toString() {
		return "PortfolioSimpleVO [portfolioId=" + portfolioId + ", portfolioPic=" + portfolioPic + ", memberId="
				+ memberId + ", memberName=" + memberName + ", portfolioTitle=" + portfolioTitle + ", portfolioBest="
				+ portfolioBest + ", projectCategoryId=" + projectCategoryId + ", portfolioIntro=" + portfolioIntro
				+ ", portfolioUpdateDate=" + portfolioUpdateDate + "]";
	}
	
}
