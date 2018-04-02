package com.caijinbei.xuekezhe.bean;

import java.util.ArrayList;
import java.util.List;

public class WeatherMsgBean {
	private String retCode;
	private String  msg;
	private List<WeatherBean> result=new ArrayList<WeatherBean>();
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<WeatherBean> getResult() {
		return result;
	}
	public void setResult(List<WeatherBean> result) {
		this.result = result;
	}


}
