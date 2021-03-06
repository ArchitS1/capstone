package ca.wednesdaypc.lnf.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import ca.wednesdaypc.lnf.netspec.JsonResponse;
import ca.wednesdaypc.lnf.netspec.Profile;

public class AccountActivity extends LnfActivity {
	
	private EditText mEtEmail;
	private EditText mEtPhone;
	private EditText mEtTwitter;
	private EditText mEtFacebook;
	private EditText mEtInstagram;
	private EditText mEtTumblr;
	
	private EditText[] mEditTexts;
	
	private Button mBtnEdit;
	private Button mBtnLogout;
	
	private TextView mTvTheUsername;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		mEtEmail = (EditText)findViewById(R.id.etEmail);
		mEtPhone = (EditText)findViewById(R.id.etPhone);
		mEtTwitter = (EditText)findViewById(R.id.etTwitter);
		mEtFacebook = (EditText)findViewById(R.id.etFacebook);
		mEtInstagram = (EditText)findViewById(R.id.etInstagram);
		mEtTumblr = (EditText)findViewById(R.id.etTumblr);
		
		mEditTexts = new EditText[]{mEtEmail, mEtPhone, mEtTwitter, mEtFacebook, mEtInstagram,
				mEtTumblr};
		
		mBtnEdit = (Button)findViewById(R.id.btnEdit);
		mBtnLogout = (Button)findViewById(R.id.btnLogout);
		
		mTvTheUsername = (TextView)findViewById(R.id.tvTheUsername);
		
		ConnectionManager.sendGetRequest(GlobalData.getProperty("servletNameAccount"), response -> {
			JsonResponse jr = JsonResponse.createFromJson(response);
			switch (jr.resultCode) {
				case JsonResponse.CODE_NOMINAL: {
					Profile profile = new Profile((LinkedTreeMap)jr.payload);
					String[] fields = new String[]{profile.email, profile.phone, profile.twitter,
							profile.facebook, profile.instagram, profile.tumblr};
					
					for (int i = 0; i < mEditTexts.length; i++) {
						if (fields[i] == null) {
							mEditTexts[i].setHint(R.string.hint_nonegiven);
						} else {
							mEditTexts[i].setHint("");
							mEditTexts[i].setText(fields[i]);
						}
					}
					
					mTvTheUsername.setText(ConnectionManager.getCreds(this)[0]);
					
					mBtnEdit.setEnabled(true);
					mBtnLogout.setEnabled(true);
					break;
				}
				case JsonResponse.CODE_NEED_LOGIN: {
					String[] creds = ConnectionManager.getCreds(this);
					ConnectionManager.sendLoginRequest(creds[0], creds[1], lgResp -> {
						JsonResponse lr = JsonResponse.createFromJson(lgResp);
						switch (lr.resultCode) {
							case JsonResponse.CODE_NOMINAL: {
								recreate();
								break;
							}
							default:
								Toaster.declaredError(this);
								ConnectionManager.clearCreds(this);
						}
					}, lgErr -> {
						Toaster.httpError(this, lgErr);
						ConnectionManager.clearCreds(this);
					});
					break;
				}
				default:
					Toaster.declaredError(this);
			}
		}, error -> Toaster.httpError(this, error));
		
		mTitleTextView.setText(R.string.title_account);
	}
	
	public void logout(View view) {
		ConnectionManager.sendGetRequest(GlobalData.getProperty("servletNameLogout"), response -> {
			JsonResponse jr = JsonResponse.createFromJson(response);
			switch (jr.resultCode) {
				//Trying to logout when the server has already logged you out
				case JsonResponse.CODE_NEED_LOGIN:
				case JsonResponse.CODE_NOMINAL: {
					Toast.makeText(getApplicationContext(), R.string.msg_logoutsuccess,
							Toast.LENGTH_SHORT).show();
					ConnectionManager.clearCreds(this);
					finish();
					break;
				}
				default:
					Toaster.declaredError(this);
			}
		}, error -> Toaster.httpError(this, error));
	}
}
