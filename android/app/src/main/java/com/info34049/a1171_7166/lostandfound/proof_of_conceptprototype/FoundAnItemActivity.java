package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FoundAnItemActivity extends AppCompatActivity {
	
	private boolean logged;
	private Button btnAct, btnDesc;
	
	private static final String DESCRIBE_TEXT = "Describe the item";
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		state.putBoolean(WelcomeActivity.KEY_LOG_FLAG, logged);
	}
	
	@Override
	protected void onActivityResult(int rqc, int rsc, Intent data) {
		if (data != null && data.getIntExtra(WelcomeActivity.KEY_BACKTO, 0) ==
				WelcomeActivity.VAL_BACKTO_WELCOME) {
			setResult(RESULT_OK, data);
			finish();
			return;
		}
		
		logged = data != null && data.getBooleanExtra(WelcomeActivity.KEY_LOG_FLAG, false);
		onLoggedChange();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found_an_item);
		
		btnDesc = (Button)findViewById(R.id.btnDescribe);
		
		btnAct = (Button)findViewById(R.id.btnAct);
		if (savedInstanceState != null) {
			logged = savedInstanceState.getBoolean(WelcomeActivity.KEY_LOG_FLAG, false);
		} else {
			logged = getIntent().getBooleanExtra(WelcomeActivity.KEY_LOG_FLAG, false);
		}
		onLoggedChange();
	}
	
	public void onClickSettings(View view) {
		WelcomeActivity.notImplementedToast(FoundAnItemActivity.this);
	}
	
	private void onLoggedChange() {
		WelcomeActivity.simpleOnLoggedChange(this, btnAct, logged);
		btnDesc.setText(logged ? DESCRIBE_TEXT : DESCRIBE_TEXT +
				" (you must be logged in to do this)");
		btnDesc.setEnabled(logged);
	}
	
	public void onClickAccount(View view) {
		WelcomeActivity.simpleAccountClick(this, logged);
	}
	
	public void onClickDesc(View view) {
		WelcomeActivity.simpleNextScreen(this, SubmitFoundActivity.class, logged);
	}
	
	public void onClickBrowse(View view) {
		WelcomeActivity.simpleNextScreen(this, BrowseLostActivity.class, logged);
	}
}
