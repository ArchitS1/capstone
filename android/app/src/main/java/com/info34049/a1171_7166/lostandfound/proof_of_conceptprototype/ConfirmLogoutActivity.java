package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmLogoutActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_logout);
	}
	
	public void onClickYes(View view) {
		Intent intent = new Intent();
		intent.putExtra(WelcomeActivity.KEY_LOG_FLAG, false);
		intent.putExtra(WelcomeActivity.KEY_BACKTO, AccountActivity.VAL_BACKTO_PREV);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void onClickNo(View view) {
		finish();
	}
}
