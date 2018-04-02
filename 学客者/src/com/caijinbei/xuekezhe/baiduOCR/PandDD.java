package com.caijinbei.xuekezhe.baiduOCR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class PandDD {

	public static void main(String[] args) {
		String getMsg =getMsg("JAVA");
		String startIndex="href=\"/r/";
		String endIndex="</a>";
		int start=getMsg.indexOf(startIndex);
		int end=getMsg.indexOf(endIndex);
		while(start>0){
			if(end<0||start>=end){
				getMsg=getMsg.substring(end+endIndex.length(), getMsg.length()-1);
			}else{
				String msg=getMsg.substring(start+startIndex.length(), end);
				String titlestart="title: '";
				int titlestartindex=msg.indexOf(titlestart);
				System.err.println(msg.replace("';", "").replace("\t", "").replace("\r\n", "").replace("\r", ""));
				String [] reStrings=msg.split("\">");
				if(reStrings.length>=2) {
					BaiduRes res = new BaiduRes();
					res.setYurl("http://pdd.19mi.net/go/" + reStrings[0]);
					getBaiduUrl(res);
					if(res.isGetUrlSuccess()){
						System.err.println(res.getBurl());
					}
				}
				
				getMsg=getMsg.substring(end+endIndex.length(), getMsg.length()-1);
			}
			start=getMsg.indexOf(startIndex);
			end=getMsg.indexOf(endIndex);
		}
	}
	
	public static String getMsg(String seach) {
		StringBuilder msg=new StringBuilder();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;
		String url = "http://www.panduoduo.net/s/name/"+seach+"/1";
		//封装请求参数
		try {
			//创建Get请求
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			//执行Get请求，
			response = httpClient.execute(httpGet);
			//得到响应体
			HttpEntity entity = response.getEntity();
			if(entity != null){
				is = entity.getContent();
				//转换为字节输入流
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
				String body = null;
				while((body=br.readLine()) != null){
					System.out.println(body);
					msg.append(body);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//关闭输入流，释放资源
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//消耗实体内容
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//关闭相应 丢弃http连接
			if(httpClient != null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return msg.toString();
	}
	/**
	 * 
	 * 获取百度云地址
	 * 
	 * @return
	 */
	private static String getBaiduUrl(BaiduRes res) {
		// 43739544
		String baidu = "";
		CloseableHttpClient httpClient = new DefaultHttpClient();
		// 执行请求
		CloseableHttpResponse response = null;
		try {
			
			httpClient.getParams().setParameter("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
			HttpGet httpGet = new HttpGet(res.getYurl());
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			//System.err.println(responseContent);
			//进行处理验证码
			if (responseContent.indexOf("请输入验证码") > 0) {
				return "";
				//new DoWithCode().dowithCodeTobaiduURL(res);
			}
			// 截取数据 找到百度云地址
			String startIndex = "<a href=\"";
			int start = responseContent.indexOf(startIndex);
			int end = responseContent.indexOf("\" rel=\"");
			baidu = responseContent.substring(start + startIndex.length(), end);
			//System.err.println(baidu);
			response.close();
			httpClient.close();
		} catch (Exception e) {

		}finally{
			try {
				response.close();
				httpClient.close();
				Thread.sleep(1000);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (baidu.length() > 10) {
			res.setGetUrlSuccess(true);
			res.setBurl(baidu);
		}
		return baidu;
	}
}
