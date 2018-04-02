package com.caijinbei.xuekezhe;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.caijinbei.xuekezhe.bean.FutureBean;
import com.caijinbei.xuekezhe.bean.WeatherBean;
import com.caijinbei.xuekezhe.bean.WeatherMsgBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherUtil {

	private WeatherMsgBean weatherMsgBean;
	List<WeatherBean> wList = new ArrayList<WeatherBean>();
	List<FutureBean> fList = new ArrayList<FutureBean>();

	public boolean sendMsg(String name,String phone) {
		if (weatherMsgBean == null || !weatherMsgBean.getRetCode().equals("200")) {
			return false;
		} else {

			if (wList.size() > 0) {
				if (fList.size() > 0) {
					sendWeaMsg(phone, name, fList.get(0).getTemperature(), wList.get(0).getPollutionIndex()+"("+wList.get(0).getAirCondition()+")", "出门携带口罩");
				} else {
					return false;
				}

			} else {
				return false;
			}

		}
		return true;
	}

	private boolean sendWeaMsg(String phone, String name, String weat, String air, String warn) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://route.showapi.com/28-1");
			List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
			// 您好[name],天气[weat],空气[air],请注意[warn]!
			// {"name":"某某","code":"123456","minute":"3"}
			formparams.add(new BasicNameValuePair("showapi_appid", "60096"));
			formparams.add(new BasicNameValuePair("showapi_sign", "cddab3c259e344b1851d68d5d4393087"));
			formparams.add(new BasicNameValuePair("mobile", phone));
			formparams.add(new BasicNameValuePair("content", "{\"name\":\"" + name + "\",\"weat\":\"" + weat + "\",\"air\":\"" + air + "\",\"warn\":\"" + warn + "\"}"));
			formparams.add(new BasicNameValuePair("tNum", "T170317002147"));
			// formparams.add(new BasicNameValuePair("big_msg", "true"));
			UrlEncodedFormEntity uefEntity;
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("请求：" + httppost.getURI());
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String msg = EntityUtils.toString(entity, "UTF-8");
				System.out.println(msg);
				//{"showapi_res_code":-1,"showapi_res_error":"验证有误!showapi_appid不是数字!","showapi_res_body":{}}
			    //
				//{"showapi_res_error":"","showapi_res_code":0,"showapi_res_body":{"ret_code":"0","successCounts":1,"showapi_fee_num":1,"remark":"提交成功!","taskID":"1803281735311307206"}}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// 关闭连接,释放资源
			try {
				response.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean getWeather() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpget = new HttpGet("http://apicloud.mob.com/v1/weather/query?key=1451a222e4a5fb766dd090acd8e4bcb0&city=昌平&province=北京");
			System.out.println("请求： " + httpget.getURI());
			response = httpclient.execute(httpget);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			System.out.println(response.getStatusLine());
			if (entity != null) {
				// 打印响应内容
				String returnMsg = EntityUtils.toString(entity);
				System.out.println("Response content: " + returnMsg);
				JSONObject obj = JSONObject.fromObject(returnMsg);
				wList = (List<WeatherBean>) JSONArray.toList(obj.getJSONArray("result"), WeatherBean.class);
				if (obj.getJSONArray("result").size() > 0) {
					JSONObject wea = (JSONObject) obj.getJSONArray("result").get(0);
					fList = (List<FutureBean>) JSONArray.toList(wea.getJSONArray("future"), FutureBean.class);
				}
				weatherMsgBean = (WeatherMsgBean) JSONObject.toBean(obj, WeatherMsgBean.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// 关闭连接,释放资源
			try {
				response.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
