package com.agiliq.exam.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestJsonClient {
	
	static String url = "http://178.79.174.19/api/login/";

    public static JSONObject connect(String username, String password)
    {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url); 
        HttpResponse response;
        
        JSONObject json = new JSONObject();

        try {
        	
        	// Add your data
        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        	nameValuePairs.add(new BasicNameValuePair("username", username));
        	nameValuePairs.add(new BasicNameValuePair("password", password));

        	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        	response = httpclient.execute(httppost);

        	// A Simple JSON Response Read
        	String result = EntityUtils.toString(response.getEntity());
        	
        	Log.i("result", result);

        	json = new JSONObject(result);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return json;
    }
}