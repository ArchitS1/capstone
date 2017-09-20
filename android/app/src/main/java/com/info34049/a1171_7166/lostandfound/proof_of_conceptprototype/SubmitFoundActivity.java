package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SubmitFoundActivity extends AppCompatActivity {
	
	public static String KEY_CANNOT_LOGOUT = "cannot logout";
	private Intent resData;
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		//save values of input fields
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_found);
		ItemSubmission.singletonInit();
		resData = new Intent();
		resData.putExtra(WelcomeActivity.KEY_LOG_FLAG, true);
		setResult(RESULT_OK, resData);
		
		if (savedInstanceState != null) {
			//restore values of input fields
		}
	}
	
	public void onClickAccount(View view) {
		Intent intent = new Intent(this, AccountActivity.class);
		intent.putExtra(KEY_CANNOT_LOGOUT, true);
		startActivity(intent);
	}
	
	public void onClickSettings(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
	
	public void onClickSubmit(View view) {
		
		//validate values of input fields
		
		
		ItemSubmission sub = new ItemSubmission();
		//set fields of sub from input fields
		
		
		ItemSubmission.addToDB(sub);
		resData.putExtra(WelcomeActivity.KEY_BACKTO, WelcomeActivity.VAL_BACKTO_WELCOME);
		setResult(RESULT_OK, resData);
		finish();
	}
}
