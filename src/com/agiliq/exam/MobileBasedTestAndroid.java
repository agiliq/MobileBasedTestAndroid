package com.agiliq.exam;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agiliq.exam.net.RestJsonClient;


public class MobileBasedTestAndroid extends Activity implements OnClickListener {
	EditText username, password;

	TextView error;
	Button login_button;

	private SharedPreferences settings;
	private ProgressDialog progress;       

	private static final String DEB_TAG = "Json_MBT_Android";
	public static final String PREFS_NAME = "MBT_Android_prefs";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		// Restore preferences
		settings = getSharedPreferences(PREFS_NAME, 0);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		username = (EditText)findViewById(R.id.edit_text_username);
		password = (EditText)findViewById(R.id.edit_text_password);

		login_button = (Button)findViewById(R.id.button_login);
		error = (TextView)findViewById(R.id.text_view_error);

		login_button.setOnClickListener(this);

	}

	public void onClick(View v) {

		EditText edit_text_username = (EditText) findViewById(R.id.edit_text_username);
		EditText edit_text_password = (EditText) findViewById(R.id.edit_text_password);

		String username = edit_text_username.getText().toString();
		String password = edit_text_password.getText().toString();

		if(edit_text_username == null || edit_text_password == null){
			// show some warning
		}
		else {

            try {
				showBusyCursor(true);

				progress = ProgressDialog.show(this, "Please wait...", "Login in process", true);

				Log.i(DEB_TAG, "Username: " + username + "Password: " + password);
				//Log.i(DEB_TAG, "Requesting to " + address);

				JSONObject json = RestJsonClient.connect(username, password);
				
				String exam_id = json.getString("exam_id");
				
				Log.i("exam_id", exam_id);
				
				JSONArray question_array = json.getJSONArray("questions"); 

			}
			catch(/*JSONException */Exception e) {
				e.printStackTrace();
				showBusyCursor(false);
			}
			progress.dismiss();

			SharedPreferences.Editor editor = settings.edit();
			editor.putString("Username", username);
			editor.putString("Password", password);
			editor.commit();
			showBusyCursor(false);
			next();
		}
	}

	public void setUserNameText(String username){
		EditText usernameEditText = (EditText) findViewById(R.id.edit_text_username);
		usernameEditText.setText(username);
	}

	public void setPasswordText(String username){
		EditText passwordEditText = (EditText) findViewById(R.id.edit_text_password);
		passwordEditText.setText(username);

	}

	private void showBusyCursor(Boolean show){
		setProgressBarIndeterminateVisibility(show);
	}

	private void next(){
		// you can call another activity by uncommenting the above lines
		//Intent myIntent = new Intent( this.getBaseContext() , LoggedActivity.class);
		//startActivityForResult(myIntent, 0);
	}

}


