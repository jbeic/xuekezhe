package com.caijinbei.xuekezhe.bean;

import java.util.ArrayList;
import java.util.List;

public class WeatherBean {
	private String airCondition	;//	空气质量
	private String city	;//	城市
	private String coldIndex	;//	感冒指数
	private String date	;//	日期
	private String distrct	;//	区县
	private String dressingIndex	;//	穿衣指数
	private String exerciseIndex	;//	运动指数
	private String humidity	;//	湿度
	private String pollutionIndex	;//	空气质量指数
	private String province	;//	省份
	private String sunrise	;//	日出时间
	private String sunset	;//	日落时间
	private String temperature	;//	温度
	private String time	;//	时间
	private String updateTime	;//	更新时间
	private String washIndex	;//	洗车指数
	private String weather	;//	天气
	private String week	;//	星期
	private String wind	;//	风向
	private List<FutureBean> future=new ArrayList<FutureBean>();
	
	public String getAirCondition() {
		return airCondition;
	}
	public void setAirCondition(String airCondition) {
		this.airCondition = airCondition;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getColdIndex() {
		return coldIndex;
	}
	public void setColdIndex(String coldIndex) {
		this.coldIndex = coldIndex;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDistrct() {
		return distrct;
	}
	public void setDistrct(String distrct) {
		this.distrct = distrct;
	}
	public String getDressingIndex() {
		return dressingIndex;
	}
	public void setDressingIndex(String dressingIndex) {
		this.dressingIndex = dressingIndex;
	}
	public String getExerciseIndex() {
		return exerciseIndex;
	}
	public void setExerciseIndex(String exerciseIndex) {
		this.exerciseIndex = exerciseIndex;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPollutionIndex() {
		return pollutionIndex;
	}
	public void setPollutionIndex(String pollutionIndex) {
		this.pollutionIndex = pollutionIndex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getWashIndex() {
		return washIndex;
	}
	public void setWashIndex(String washIndex) {
		this.washIndex = washIndex;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public List<FutureBean> getFuture() {
		return future;
	}
	public void setFuture(List<FutureBean> future) {
		this.future = future;
	}

	
}
