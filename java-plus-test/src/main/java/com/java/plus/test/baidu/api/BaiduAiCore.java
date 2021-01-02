package com.java.plus.test.baidu.api;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.nlp.ESimnetType; 
import com.java.plus.test.baidu.api.model.BaiduItem;

import org.json.JSONObject; 
import java.util.HashMap;
import java.util.Map;
import java.util.Random; 

public class BaiduAiCore {

    //设置APPID/AK/SK
    public static final String APP_ID = "23117676";
    public static final String API_KEY = "b6tGpqhtjEBQVK0Qrrx0yVma";
    public static final String SECRET_KEY = "5wOUaD8HEBRRZhz7YsGuYmgv2va3Ol1F";
    
//    public static final String APP_ID = "22914285";
//    public static final String API_KEY = "Q5g7RH9tKdW7SM6Ru20eRKG9";
//    public static final String SECRET_KEY = "uOUWawd6CTAWavNe73Ca3xSaXjGx2QmS";
    // 初始化一个AipNlp
    private static WbinBdAiClient client = new WbinBdAiClient(APP_ID, API_KEY, SECRET_KEY);

    static {
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        HashMap<String, Object> options   = new HashMap<String, Object>();
        String text = "本来今天高高兴兴";
        // 对话情绪识别接口
        JSONObject res = client.emotion(text, options);
        System.out.println(res.toString(2));
    }
  
    public static BaiduItem getBaiduTags(String commentContent){

    	try {
    		Random rand= new Random();
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        String tag = queryBaiduAi(commentContent);
        BaiduItem items= JSON.parseObject(tag, BaiduItem.class);
        items.setBaseJson(tag);
        return items;
    }
     
    private static String queryBaiduAi(String sentence) {
        HashMap<String, Object> options = new HashMap<String, Object>();
        JSONObject response = client.commentTagCustomer(sentence, ESimnetType.CAR, options);
        System.out.println("评论: " + sentence+ "    tag: " + response.toString());
        System.out.println("==========================================================================\n\n");
     
        return response.toString();
    }
 
}
