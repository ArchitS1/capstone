package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmFoundActivity extends AppCompatActivity {
	
	private ItemSubmission item;
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		state.putSerializable(BrowseLostActivity.KEY_ITEM_SELECTED, item);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_found);
		
		if (savedInstanceState != null) {
			item = (ItemSubmission)savedInstanceState.getSerializable(BrowseLostActivity.KEY_ITEM_SELECTED);
		} else {
			item = (ItemSubmission)getIntent().getSerializableExtra(BrowseLostActivity.KEY_ITEM_SELECTED);
		}
		setResult(RESULT_OK, (new Intent()).putExtra(WelcomeActivity.KEY_LOG_FLAG, true));
	}
	
	public void onClickAccount(View view) {
		Intent intent = new Intent(this, AccountActivity.class);
		intent.putExtra(SubmitFoundActivity.KEY_CANNOT_LOGOUT, true);
		startActivity(intent);
	}
	
	public void onClickConfirm(View view) {
		//validate contact info
		
		Intent intent = new Intent();
		intent.putExtra(WelcomeActivity.KEY_LOG_FLAG, true);
		intent.putExtra(WelcomeActivity.KEY_BACKTO, WelcomeActivity.VAL_BACKTO_WELCOME);
		setResult(RESULT_OK, intent);
		ItemSubmission.removeFromDb(item);
		finish();
	}
	
	public void onClickSettings(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
}
