package com.java.plus.test;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.EBodyFormat;
import com.baidu.aip.http.Headers;
import com.baidu.aip.http.HttpCharacterEncoding;
import com.baidu.aip.http.HttpContentType;
import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.nlp.ESimnetType;

public class WbinBdAiClient extends AipNlp {
    protected WbinBdAiClient(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    public JSONObject commentTagCustomer(String text, ESimnetType type, HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("text", text);
        if (type != null) {
            request.addBody("type", type.ordinal());
        }
        if (options != null) {
            request.addBody(options);
        }
        request.setUri("https://aip.baidubce.com/rpc/2.0/nlp/v2/comment_tag_custom");
        request.addHeader(Headers.CONTENT_ENCODING, HttpCharacterEncoding.ENCODE_GBK);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        postOperation(request);
        return requestServer(request);
    }
}