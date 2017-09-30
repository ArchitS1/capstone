package ca.wednesdaypc.lnf.android;

import android.os.Bundle;
import android.view.View;

public class LoginActivity extends LnfActivity {
	
	private boolean mIsInCreate = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			mIsInCreate = savedInstanceState.getBoolean("mIsInCreate");
		}
		
		if (mIsInCreate) {
			setContentView(R.layout.activity_login_createacct);
			mTitleTextView.setText(R.string.title_createacct);
		} else {
			setContentView(R.layout.activity_login);
			mTitleTextView.setText(R.string.title_login);
		}
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("mIsInCreate", mIsInCreate);
	}
	
	public void swapLayouts(View v) {
		mIsInCreate = !mIsInCreate;
		recreate();
	}
}
