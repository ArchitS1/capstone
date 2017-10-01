package ca.wednesdaypc.lnf.android;

import android.os.Bundle;

public class AccountActivity extends LnfActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		mTitleTextView.setText(R.string.title_account);
	}
}
