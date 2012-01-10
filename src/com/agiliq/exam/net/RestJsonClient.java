package com.agiliq.exam.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestJsonClient {
	
	private static String url = "http://178.79.174.19/api/login/";

    public static JSONObject getQuestions(String username, String password){

    	InputStream is = null;
    	String result = "";
    	JSONObject jArray = null;

    	try{
    		DefaultHttpClient http_client = new DefaultHttpClient();

            HttpGet http_get = new HttpGet(url);  
            URI get_uri = http_get.getURI();
            
            UsernamePasswordCredentials credential = new UsernamePasswordCredentials(username, password);
            AuthScope scope = new AuthScope(get_uri.getHost(), get_uri.getPort());
            
            http_client.getCredentialsProvider().setCredentials(scope, credential);

    		HttpResponse response = http_client.execute(http_get);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();

    	} catch(Exception e){
    		Log.e("log_tag", "Error in http connection " + e.toString());
    	}

    	try{
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
    		StringBuilder sb = new StringBuilder();
    		String line = null;
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    		}
    		is.close();
    		result=sb.toString();
    		Log.i("result",result);
    	} catch(Exception e){
    		Log.e("log_tag", "Error converting result "+e.toString());
    	}

    	//try parse the string to a JSON object
    	try{
            	jArray = new JSONObject(result);
    	}catch(JSONException e){
    		Log.e("log_tag", "Error parsing data "+e.toString());
    	}

    	return jArray;
    }
}