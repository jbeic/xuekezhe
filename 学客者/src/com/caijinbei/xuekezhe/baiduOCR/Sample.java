package com.caijinbei.xuekezhe.baiduOCR;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class Sample {
	// 设置APPID/AK/SK
	public static final String APP_ID = "11013335";
	public static final String API_KEY = "Ze21tEQq2hbWonWW2KMuGkyF";
	public static final String SECRET_KEY = "BrwunVMnGWSKITgFXljGCDjxbPHiplog";

	public static void main(String[] args) {
		// 初始化一个OcrClient
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		/*// 调用身份证识别接口
		String idFilePath = "test.jpg";
		JSONObject idcardRes =client.id client.idcard(idFilePath, true);
		System.out.println(idcardRes.toString(2));
		// 调用银行卡识别接口
		String bankFilePath = "test_bank.jpg";
		JSONObject bankRes = client.bankcard(bankFilePath);
		System.out.println(bankRes.toString(2));*/
		// 调用通用识别接口
		String genFilePath = "D:\\code1.jpg";
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("detect_direction", "true");
		//options.put("language_type", "ENG");
		options.put("words_result_num", "6");
		JSONObject genRes = client.basicGeneral(genFilePath, options);
		System.out.println(genRes.toString(2));
		
		
		String imagePath =  "D:\\code1.jpg";
		HashMap<String, String> option = new HashMap<String, String>();
		option.put("detect_direction", "true");
		options.put("language_type", "ENG");
		option.put("words_result_num", "1");
		JSONObject response = client.webImage(imagePath,option);
		System.out.println(response.toString()) ;
		
		
		/*// 调用通用识别（含位置信息）接口
		String genFilePath = "test_general.jpg";
		JSONObject genRes = client.general(genFilePath, new HashMap<String, String>());
		System.out.println(genRes.toString(2));*/
	}
}