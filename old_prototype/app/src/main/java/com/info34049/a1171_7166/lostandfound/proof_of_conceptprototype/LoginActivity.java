package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	public void onClickCreateAccount(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
	
	public void onClickLogin(View view) {
		//just make sure there's a username
		
		Intent intent = new Intent();
		intent.putExtra(WelcomeActivity.KEY_LOG_FLAG, true);
		setResult(RESULT_OK, intent);
		finish();
	}
}
