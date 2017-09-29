package ca.wednesdaypc.lnf.android;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Superclass to all activities. For now, it provides a common action bar.
 */
public abstract class LnfActivity extends AppCompatActivity {
	
	protected ImageView mBackImageView;
	protected TextView mTitleTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar bar = getSupportActionBar();
		bar.setDisplayShowCustomEnabled(true);
		bar.setDisplayShowTitleEnabled(false);
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		View v = LayoutInflater.from(this).inflate(R.layout.action_bar, null);
		bar.setCustomView(v, lp);
		
		mTitleTextView = (TextView)findViewById(R.id.titleTextView);
		mBackImageView = (ImageView)findViewById(R.id.backImageView);
		
		//Default behaviour for back button: close current Activity
		mBackImageView.setOnClickListener(v1 -> finish());
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		ConnectionManager.cancelRequests(null);
	}
	
	/**
	 * Provides an opportunity to bypass the behaviour of this class's own no-argument onStop()
	 * @param bypass If true, this method calls super.onStop(). Otherwise, this method has identical
	 *               behaviour to the no-argument version.
	 */
	protected void onStop(boolean bypass) {
		if (bypass)
			super.onStop();
		else
			onStop();
	}
	
}
