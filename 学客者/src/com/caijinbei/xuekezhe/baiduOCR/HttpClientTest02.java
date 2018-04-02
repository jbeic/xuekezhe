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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClientTest02 {

	public static void main(String[] args) {
		String getMsg =getMsg("��尮��");
		String startIndex="result_episode_list";
		String endIndex="playSrcName";
		int start=getMsg.indexOf(startIndex);
		int end=getMsg.indexOf(endIndex);
		while(start>0){
			if(end<0||start>=end){
				getMsg=getMsg.substring(end+endIndex.length(), getMsg.length()-1);
			}else{
				String msg=getMsg.substring(start+startIndex.length(), end);
				String titlestart="title: '";
				int titlestartindex=msg.indexOf(titlestart);
				System.err.println(msg.substring(titlestartindex+"title: '".length(), msg.length()-1).replace("';", "").replace("\t", "").replace("\r\n", "").replace("\r", ""));
				getMsg=getMsg.substring(start+startIndex.length(), getMsg.length()-1);
				start=getMsg.indexOf(startIndex);
				String startIndexI="<div class=\"item\">";
				String endIndexI="_stat=\"video:poster_num\">";
				int startI=getMsg.indexOf(startIndexI);
				int endI=getMsg.indexOf(endIndexI);
				while (startI>0&&(startI<start||start<0)&&endI-startI<200) {
					if(startI<endI){
						msg=getMsg.substring(startI+startIndexI.length(), endI);
						System.err.println(msg.replace(" ","").replace("\t","").replace("\r","").replace("\n","").replace("<ahref=\"", "").replace("\"target=\"_blank\"", "").substring(0, 60));
						getMsg=getMsg.substring(endI+endIndexI.length(), getMsg.length()-1);
						int nameIdex=getMsg.indexOf("</a>");
						msg=getMsg.substring(0,nameIdex);
						System.err.println(msg);
					}else{
						getMsg=getMsg.substring(endI+endIndexI.length(), getMsg.length()-1);
					}
					startI=getMsg.indexOf(startIndexI);
					endI=getMsg.indexOf(endIndexI);
					start=getMsg.indexOf(startIndex);
				}
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
		String url = "https://v.qq.com/x/search";
		//��װ�������
		List<NameValuePair> params =new  ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("q", seach));
		String str = "";
		try {
			//ת��Ϊ��ֵ��
			str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
			System.out.println(str);
			//����Get����
			HttpGet httpGet = new HttpGet(url+"?"+str);
			//ִ��Get����
			response = httpClient.execute(httpGet);
			//�õ���Ӧ��
			HttpEntity entity = response.getEntity();
			if(entity != null){
				is = entity.getContent();
				//ת��Ϊ�ֽ�������
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
			//�ر����������ͷ���Դ
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//����ʵ������
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//�ر���Ӧ ����http����
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
}
