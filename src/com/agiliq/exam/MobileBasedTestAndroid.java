package com.agiliq.exam;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agiliq.exam.net.RestJsonClient;


public class MobileBasedTestAndroid extends Activity implements OnClickListener {

	EditText edit_text_username;
	EditText edit_text_password;

	String username;
	String password;

	TextView error;
	Button login_button;

	JSONObject json;
	String result;

	private SharedPreferences settings;
	private ProgressDialog progress;       

	public static final String PREFS_NAME = "MBT_Android_prefs";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		// Restore preferences
		settings = getSharedPreferences(PREFS_NAME, 0);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		login_button = (Button)findViewById(R.id.button_login);
		error = (TextView)findViewById(R.id.text_view_error);

		login_button.setOnClickListener(this);
	}

	public void onClick(View v) {

		edit_text_username = (EditText) findViewById(R.id.edit_text_username);
		edit_text_password = (EditText) findViewById(R.id.edit_text_password);

		username = edit_text_username.getText().toString();
		password = edit_text_password.getText().toString();

		if(edit_text_username == null || edit_text_password == null){
			// show some warning
		}
		else {

			try {
				showBusyCursor(true);
				progress = ProgressDialog.show(this, "Please wait...", "Login in process", true);
				json = RestJsonClient.getQuestions(username, password);
				result = json.toString();
			}
			catch(Exception e) {
				e.printStackTrace();
				showBusyCursor(false);
			}
			progress.dismiss();

			SharedPreferences.Editor editor = settings.edit();
			editor.putString("Username", username);
			editor.putString("Password", password);
			editor.commit();
			showBusyCursor(false);

			next(result);
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

	private void next(String result){
		Intent myIntent = new Intent( this.getBaseContext() , MBTExam.class);
		myIntent.putExtra("result", result);
		startActivityForResult(myIntent, 0);
	}

}


