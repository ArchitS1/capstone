package ca.wednesdaypc.lnf.android;

import android.content.SharedPreferences;
import android.os.Bundle;

import ca.wednesdaypc.lnf.json.JsonResponse;

public class AccountActivity extends LnfActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		ConnectionManager.sendPostRequest(GlobalData.getProperty("servletNameAccount"), response -> {
			JsonResponse jr = JsonResponse.createFromJson(response);
			switch (jr.resultCode) {
				case JsonResponse.CODE_NOMINAL: {
					
					break;
				}
				case JsonResponse.CODE_NEED_LOGIN: {
					SharedPreferences prefs = getPreferences(MODE_PRIVATE);
					ConnectionManager.sendLoginRequest(prefs.getString("prefsUsername", null),
							prefs.getString("prefsPassword", null), lgResp -> {
								JsonResponse lr = JsonResponse.createFromJson(lgResp);
								switch (lr.resultCode) {
									case JsonResponse.CODE_NOMINAL: {
										recreate();
										break;
									}
									default:
										Toaster.declaredError(this);
								}
							}, lgErr -> Toaster.httpError(this, lgErr.networkResponse.statusCode));
					break;
				}
				default:
					Toaster.declaredError(this);
			}
		}, error -> Toaster.httpError(this, error.networkResponse.statusCode));
		
		mTitleTextView.setText(R.string.title_account);
	}
}
