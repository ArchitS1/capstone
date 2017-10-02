package ca.wednesdaypc.lnf.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonParseException;

import ca.wednesdaypc.lnf.netspec.JsonResponse;

public class WelcomeActivity extends LnfActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		final TextView mTextView = (TextView)findViewById(R.id.helloTextView);
		
		GlobalData.init(getApplicationContext());
		
		//ConnectionManager.clearCreds(this);
		/*
		ConnectionManager.sendGetRequest("Hello",
				response -> {
					try {
						JsonResponse jr = JsonResponse.createFromJson(response);
						if (jr.resultCode == JsonResponse.CODE_NOMINAL) {
							mTextView.setText((String)jr.payload);
						} else {
							mTextView.setText("The server reported that it was unable to " +
									"complete your request.");
						}
					} catch (JsonParseException e) {
						mTextView.setText("Couldn't parse the response from the server:\n" +
								response);
					}
				},
				error -> mTextView.setText("Error: " + error.networkResponse.statusCode));
		*/
		mTitleTextView.setText(R.string.title_welcome);
		
		findViewById(R.id.accountImageView).setOnClickListener(v -> {
			Class destination;
			if (getSharedPreferences(GlobalData.getProperty("prefsNameLogin"), MODE_APPEND)
					.contains(GlobalData.getProperty("prefsUsername"))) {
				destination = AccountActivity.class;
			} else {
				destination = LoginActivity.class;
			}
			startActivity(new Intent(WelcomeActivity.this, destination));
		});
	}
	
}
