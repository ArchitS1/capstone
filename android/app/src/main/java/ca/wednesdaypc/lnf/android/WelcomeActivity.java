package ca.wednesdaypc.lnf.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonParseException;

import ca.wednesdaypc.lnf.json.JsonResponse;

public class WelcomeActivity extends LnfActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		final TextView mTextView = (TextView)findViewById(R.id.helloTextView);
		
		GlobalData.init(getApplicationContext());
		
		ConnectionManager.sendRequest("Hello",
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
				error -> mTextView.setText("Error: " + error.getMessage()));
		
		mTitleTextView.setText(R.string.title_welcome);
		
		findViewById(R.id.accountImageView).setOnClickListener(v -> {
			Class destination;
			if (getPreferences(MODE_PRIVATE).contains(GlobalData.getProperty("prefsUsername"))) {
				destination = AccountActivity.class;
			} else {
				destination = LoginActivity.class;
			}
			startActivity(new Intent(WelcomeActivity.this, destination));
		});


		findViewById(R.id.Lost).setOnClickListener(v -> {
			Class destination;
				destination = Ticket.class;

			startActivity(new Intent(WelcomeActivity.this, destination));
		});
	}
	
}
