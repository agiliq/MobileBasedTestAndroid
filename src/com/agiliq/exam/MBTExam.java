package com.agiliq.exam;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class MBTExam extends ListActivity {

	JSONObject result;
	String exam_id;
	JSONArray question_list_json;
	ArrayList<String> question_list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		Bundle extras = getIntent().getExtras();
		try {
			result = new JSONObject(extras.getString("result"));
			exam_id = result.getString("exam_id");
			question_list_json = result.getJSONArray("questions");

			ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

			for (int i = 1; i <= question_list_json.length(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				JSONObject e = question_list_json.getJSONObject(i - 1);

				map.put("id", new Integer(i).toString());
				map.put("question", e.getString("question"));
				map.put("A", e.getString("A"));
				map.put("B", e.getString("B"));
				map.put("C", e.getString("C"));
				map.put("D", e.getString("D"));

				mylist.add(map);
			}

			ListAdapter adapter = new SimpleAdapter(this, mylist,
					R.layout.mbt_test, new String[] { "id", "question", "A",
							"B", "C", "D" }, new int[] { R.id.question_no,
							R.id.question, R.id.A, R.id.B, R.id.C, R.id.D });

			setListAdapter(adapter);

		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

	}
}