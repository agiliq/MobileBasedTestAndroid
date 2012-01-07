package com.agiliq.exam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
 
public class MBTExam extends ListActivity {
	
	JSONObject result;
	String exam_id;
	JSONArray question_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.test);
    	
		Bundle extras = getIntent().getExtras();
		try {
			result = new JSONObject(extras.getString("result"));
			exam_id = result.getString("exam_id");
			question_list = result.getJSONArray("questions");
		}
		catch(JSONException e) {
		}
 
    }
}