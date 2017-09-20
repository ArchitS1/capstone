package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LostDetailsActivity extends AppCompatActivity {
	
	private static final String FOUND_TEXT = "I've found this";
	
	private boolean logged;
	private Button btnAct, btnFound;
	private ItemSubmission item;
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		state.putBoolean(WelcomeActivity.KEY_LOG_FLAG, logged);
		state.putSerializable(BrowseLostActivity.KEY_ITEM_SELECTED, item);
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
		setContentView(R.layout.activity_lost_details);
		
		btnFound = (Button)findViewById(R.id.btnFound);
		
		btnAct = (Button)findViewById(R.id.btnAct);
		
		if (savedInstanceState != null) {
			item = (ItemSubmission)savedInstanceState.getSerializable(BrowseLostActivity.KEY_ITEM_SELECTED);
			logged = savedInstanceState.getBoolean(WelcomeActivity.KEY_LOG_FLAG, false);
		} else {
			item = (ItemSubmission)getIntent().getSerializableExtra(BrowseLostActivity.KEY_ITEM_SELECTED);
			logged = getIntent().getBooleanExtra(WelcomeActivity.KEY_LOG_FLAG, false);
		}
		onLoggedChange();
	}
	
	public void onClickFound(View view) {
		Intent intent = new Intent(this, ConfirmFoundActivity.class);
		intent.putExtra(BrowseLostActivity.KEY_ITEM_SELECTED, item);
		startActivityForResult(intent, WelcomeActivity.RQC_DEFAULT);
	}
	
	private void onLoggedChange() {
		WelcomeActivity.simpleOnLoggedChange(this, btnAct, logged);
		btnFound.setText(logged ? FOUND_TEXT : FOUND_TEXT +
				" (you must be logged in to do this)");
		btnFound.setEnabled(logged);
	}
	
	public void onClickSettings(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
	
	public void onClickAccount(View view) {
		Intent intent = new Intent(this, logged ? AccountActivity.class : LoginActivity.class);
		intent.putExtra(WelcomeActivity.KEY_LOG_FLAG, logged);
		startActivityForResult(intent, WelcomeActivity.RQC_DEFAULT);
	}
}
