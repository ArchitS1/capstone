package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AccountActivity extends AppCompatActivity {
	
	private boolean logoutPrevented;
	public static final int VAL_BACKTO_PREV = 12;
	
	@Override
	protected void onActivityResult(int rqc, int rsc, Intent data) {
		if (data != null && data.getIntExtra(WelcomeActivity.KEY_BACKTO, 0) ==
				VAL_BACKTO_PREV) {
			setResult(RESULT_OK, data);
			finish();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		if (savedInstanceState != null) {
			logoutPrevented = savedInstanceState.getBoolean(SubmitFoundActivity.KEY_CANNOT_LOGOUT, false);
		} else {
			logoutPrevented = getIntent().getBooleanExtra(SubmitFoundActivity.KEY_CANNOT_LOGOUT, false);
		}
		findViewById(R.id.btnLogout).setEnabled(!logoutPrevented);
	}
	
	public void onClickMyItems(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
	
	public void onClickLogout(View view) {
		startActivityForResult(new Intent(this, ConfirmLogoutActivity.class), WelcomeActivity.RQC_DEFAULT);
	}
}
