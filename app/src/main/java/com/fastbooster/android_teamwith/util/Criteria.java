package com.fastbooster.android_teamwith.util;

import java.util.HashMap;
import java.util.Map;

public class Criteria {
	private int page;
	private int perPageNum;
	private Map<String, Object> criteria;
	public Criteria() {
		page = 1;
		perPageNum = 10;
		criteria = new HashMap<String, Object>();
	}
	public Criteria(int page, int perPageNum) {
		super();
		this.page = page;
		this.perPageNum = perPageNum;
		this.criteria = new HashMap<String, Object>();
	}
	public Criteria(int page, int perPageNum, Map<String, Object> criteria) {
		super();
		this.page = page;
		this.perPageNum = perPageNum;
		this.criteria = criteria;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum >= 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	public Map<String, Object> getCriteria() {
		return criteria;
	}
	public void setCriteria(Map<String, Object> criteria) {
		this.criteria = criteria;
	}
	public void addCriteria(String condition, Object keyword) {
		this.criteria.put(condition, keyword);
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", criteria=" + criteria + "]";
	}
}
