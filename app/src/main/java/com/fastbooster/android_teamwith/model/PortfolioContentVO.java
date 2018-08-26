package com.fastbooster.android_teamwith.model;


import org.json.JSONException;
import org.json.JSONObject;

public class PortfolioContentVO {
	private String portfolioContentId;
	private String portfolioId;
	private String portfolioContentOrder;
	private String layoutId;
	private String portfolioContentName;
	private String portfolioContentValue;
	private String portfolioContentIntro;

	public PortfolioContentVO() {
		super();
	}
	public PortfolioContentVO(String portfolioContentId, String portfolioId, String portfolioContentOrder,
			String layoutId, String portfolioContentName, String portfolioContentValue,String portfolioContentIntro) {
		super();
		this.portfolioContentId = portfolioContentId;
		this.portfolioId = portfolioId;
		this.portfolioContentOrder = portfolioContentOrder;
		this.layoutId = layoutId;
		this.portfolioContentName = portfolioContentName;
		this.portfolioContentValue = portfolioContentValue;
		this.portfolioContentIntro=portfolioContentIntro;
		
	}
	public PortfolioContentVO(JSONObject j) throws JSONException {
		super();
		this.portfolioContentId = j.getString("portfolioContentId");
		this.portfolioId = j.getString("portfolioId");
		this.portfolioContentOrder = j.getString("portfolioContentOrder");
		this.layoutId = j.getString("layoutId");
		this.portfolioContentName = j.getString("portfolioContentName");
		this.portfolioContentValue = j.getString("portfolioContentValue");
		this.portfolioContentIntro=j.getString("portfolioContentIntro");

	}
	public String getPortfolioContentId() {
		return portfolioContentId;
	}
	public void setPortfolioContentId(String portfolioContentId) {
		this.portfolioContentId = portfolioContentId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	
	public String getPortfolioContentIntro() {
		return portfolioContentIntro;
	}
	public void setPortfolioContentIntro(String portfolioContentIntro) {
		this.portfolioContentIntro = portfolioContentIntro;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getPortfolioContentOrder() {
		return portfolioContentOrder;
	}
	public void setPortfolioContentOrder(String portfolioContentOrder) {
		this.portfolioContentOrder = portfolioContentOrder;
	}
	public String getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
	public String getPortfolioContentName() {
		return portfolioContentName;
	}
	public void setPortfolioContentName(String portfolioContentName) {
		this.portfolioContentName = portfolioContentName;
	}
	public String getPortfolioContentValue() {
		return portfolioContentValue;
	}
	public void setPortfolioContentValue(String portfolioContentValue) {
		this.portfolioContentValue = portfolioContentValue;
	}
	@Override
	public String toString() {
		return "PortfolioContentVO [portfolioContentId=" + portfolioContentId + ", portfolioId=" + portfolioId
				+ ", portfolioContentOrder=" + portfolioContentOrder + ", layoutId=" + layoutId
				+ ", portfolioContentName=" + portfolioContentName + ", portfolioContentValue=" + portfolioContentValue
				+ ", portfolioContentIntro=" + portfolioContentIntro + "]";
	}

}
