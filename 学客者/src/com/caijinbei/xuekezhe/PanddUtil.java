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
			// 请求起始行--HttpClient会根据信息自动构建
			HttpGet get = new HttpGet("http://www.panduoduo.net/s/name/maven/1");
			// 请求首部--可选的，User-Agent对于一些服务器必选，不加可能不会返回正确结果
			get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			// 执行请求
			CloseableHttpResponse response = client.execute(get);
			//获得起始行  
			System.out.println(response.getStatusLine().toString()+"\n");  
			//获得首部---当然也可以使用其他方法获取  
			Header[] hs=response.getAllHeaders();  
			for(Header h:hs){  
			    System.out.println(h.getName()+":\t"+h.getValue()+"\n");  
			}  
			//获取实体  
			HttpEntity ety=response.getEntity();  
			System.out.println(EntityUtils.toString(ety,"UTF-8"));//新浪网的编码格式个GBK  
			EntityUtils.consume(ety);//释放实体  
			response.close();//关闭响应  
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
