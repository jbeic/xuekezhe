package com.caijinbei.xuekezhe;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
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
import org.junit.Test;

import com.caijinbei.xuekezhe.bean.FutureBean;
import com.caijinbei.xuekezhe.bean.WeatherBean;
import com.caijinbei.xuekezhe.bean.WeatherMsgBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PanddUtil {

	@Test
	public void testvip() {
		getVIP();
	}

	private boolean getVIP() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			// ������ʼ��--HttpClient�������Ϣ�Զ�����
			HttpGet get = new HttpGet("http://www.panduoduo.net/s/name/maven/1");
			// �����ײ�--��ѡ�ģ�User-Agent����һЩ��������ѡ�����ӿ��ܲ��᷵����ȷ���
			get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			// ִ������
			CloseableHttpResponse response = client.execute(get);
			//�����ʼ��  
			System.out.println(response.getStatusLine().toString()+"\n");  
			//����ײ�---��ȻҲ����ʹ������������ȡ  
			Header[] hs=response.getAllHeaders();  
			for(Header h:hs){  
			    System.out.println(h.getName()+":\t"+h.getValue()+"\n");  
			}  
			//��ȡʵ��  
			HttpEntity ety=response.getEntity();  
			System.out.println(EntityUtils.toString(ety,"UTF-8"));//�������ı����ʽ��GBK  
			EntityUtils.consume(ety);//�ͷ�ʵ��  
			response.close();//�ر���Ӧ  
			client.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
