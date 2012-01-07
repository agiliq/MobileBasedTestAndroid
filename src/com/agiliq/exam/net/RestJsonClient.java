package com.agiliq.exam.net;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestJsonClient {
	
	static String url = "http://178.79.174.19/api/login/";

    public static JSONObject connect(String username, String password)
    {
 
        HttpResponse response;
        
        JSONObject json = new JSONObject();

        
        
    	DefaultHttpClient http_client = new DefaultHttpClient();
    	
        HttpParams params = http_client.getParams();  
        
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 5000);

        HttpGet http_get = new HttpGet(url);  
        URI get_uri = http_get.getURI();
        
        UsernamePasswordCredentials credential = new UsernamePasswordCredentials(username, password);
        AuthScope scope = new AuthScope(get_uri.getHost(), get_uri.getPort());
        http_client.getCredentialsProvider().setCredentials(scope, credential);

  /*
        conn.setRequestProperty(
               "Authorization",
               "basic " +  Base64.encode("user1:pass1".getBytes(),
                                         Base64.DEFAULT)
              );*/

        try {
        	
        	response = http_client.execute(http_get);

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