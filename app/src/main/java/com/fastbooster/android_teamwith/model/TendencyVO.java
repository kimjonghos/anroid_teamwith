package com.fastbooster.android_teamwith.model;


import org.json.JSONException;
import org.json.JSONObject;

public class TendencyVO {
	private String tendency1;
	private String tendency2;
	private String tendency3;
	private String tendency4;
	private String tendency5;

	public TendencyVO() {
		super();
	}

	public TendencyVO(String tendency1, String tendency2, String tendency3, String tendency4, String tendency5) {
		super();
		this.tendency1 = tendency1;
		this.tendency2 = tendency2;
		this.tendency3 = tendency3;
		this.tendency4 = tendency4;
		this.tendency5 = tendency5;
	}
	public TendencyVO(JSONObject j) throws JSONException {
		JSONObject j2=j.getJSONObject("tendency");
		this.tendency1 = j2.getString("tendency-1");
		this.tendency2 = j2.getString("tendency-2");
		this.tendency3 = j2.getString("tendency-3");
		this.tendency4 = j2.getString("tendency-4");
		this.tendency5 = j2.getString("tendency-5");
	}

	public final String getTendency1() {
		return tendency1;
	}

	public final String getTendency2() {
		return tendency2;
	}

	public final String getTendency3() {
		return tendency3;
	}

	public final String getTendency4() {
		return tendency4;
	}

	public final String getTendency5() {
		return tendency5;
	}

	public final void setTendency1(String tendency1) {
		this.tendency1 = tendency1;
	}

	public final void setTendency2(String tendency2) {
		this.tendency2 = tendency2;
	}

	public final void setTendency3(String tendency3) {
		this.tendency3 = tendency3;
	}

	public final void setTendency4(String tendency4) {
		this.tendency4 = tendency4;
	}

	public final void setTendency5(String tendency5) {
		this.tendency5 = tendency5;
	}

	@Override
	public String toString() {
		return "TendencyVO [tendency1=" + tendency1 + ", tendency2=" + tendency2 + ", tendency3=" + tendency3
				+ ", tendency4=" + tendency4 + ", tendency5=" + tendency5 + "]";
	}

}
