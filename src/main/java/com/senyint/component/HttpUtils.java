package com.senyint.component;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static String sendPost(String url,String msg) throws ClientProtocolException, IOException{
		String resMsg = "";
		HttpPost httpPost = new HttpPost(url);  
		
		StringEntity entity = new StringEntity(msg, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        
        HttpResponse result = HttpClientBuilder.create().build().execute(httpPost);
        int statusCode = result.getStatusLine().getStatusCode();
        if(statusCode==200){
        	resMsg =  EntityUtils.toString(result.getEntity(),"utf-8");
        }else{
        	resMsg = "error:"+statusCode+"  "+EntityUtils.toString(result.getEntity(),"utf-8");
        }
        
        return resMsg;
	}
}
