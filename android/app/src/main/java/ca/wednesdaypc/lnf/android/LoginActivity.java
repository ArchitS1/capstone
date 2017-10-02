package ca.wednesdaypc.lnf.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ca.wednesdaypc.lnf.netspec.JsonResponse;

public class LoginActivity extends LnfActivity {
	private boolean mIsInCreate = false;
	private EditText mPasswordEditText;
	private EditText mConfirmPasswordEditText;
	private EditText mUsernameEditText;
	private EditText mEmailEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			mIsInCreate = savedInstanceState.getBoolean("mIsInCreate");
		}
		
		if (mIsInCreate) {
			setContentView(R.layout.activity_login_createacct);
			mTitleTextView.setText(R.string.title_createacct);
			mConfirmPasswordEditText = (EditText)findViewById(R.id.confirmPasswordEditText);
			mEmailEditText = (EditText)findViewById(R.id.emailEditText);
		} else {
			setContentView(R.layout.activity_login);
			mTitleTextView.setText(R.string.title_login);
		}
		
		mUsernameEditText = (EditText)findViewById(R.id.usernameEditText);
		mPasswordEditText = (EditText)findViewById(R.id.passwordEditText);
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("mIsInCreate", mIsInCreate);
	}
	
	public void swapLayouts(View v) {
		mIsInCreate = !mIsInCreate;
		recreate();
	}
	
	public void createAccount(View v) {
		String username = mUsernameEditText.getText().toString().trim();
		String password = mPasswordEditText.getText().toString();
		String cPassword = mConfirmPasswordEditText.getText().toString();
		String email = mEmailEditText.getText().toString().trim();
		
		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			Toast.makeText(this, R.string.msg_allfieldsreqd, Toast.LENGTH_SHORT).show();
		} else if (!password.equals(cPassword)) {
			Toast.makeText(this, R.string.msg_passwdmismatch, Toast.LENGTH_SHORT).show();
		} else if (!email.toUpperCase()
				//regex from http://www.regular-expressions.info/email.html
				.matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}")) {
			Toast.makeText(this, R.string.msg_invalidemail, Toast.LENGTH_SHORT).show();
		} else {
			ConnectionManager.sendPostRequest(
					GlobalData.getProperty("servletNameCreateAcct"), response -> {
						JsonResponse jr = JsonResponse.createFromJson(response);
						switch (jr.resultCode) {
							case JsonResponse.CODE_NOMINAL: {
								Intent i = new Intent(LoginActivity.this, AccountActivity.class);
								Toast.makeText(getApplicationContext(),
										R.string.msg_acctcreated,
										Toast.LENGTH_SHORT).show();
								getSharedPreferences(GlobalData.getProperty("prefsNameLogin"),
										MODE_APPEND).edit()
										.putString(GlobalData.getProperty("prefsUsername"), username)
										/* This is bad security: anyone who steals your phone can
										* steal your account. We'll replace it with login tokens
										* later. */
										.putString(GlobalData.getProperty("prefsPassword"), password)
										.commit();
								finish();
								startActivity(i);
								break;
							}
							case JsonResponse.CODE_DUPE_USERNAME: {
								Toast.makeText(this,
										R.string.msg_dupeusername,
										Toast.LENGTH_SHORT).show();
								break;
							}
							default:
								Toaster.declaredError(this);
						}
					}, error -> {
						Toaster.httpError(this, error);
					},
					ConnectionManager.makeKV(GlobalData.getProperty("serverParamUsername"), username),
					ConnectionManager.makeKV(GlobalData.getProperty("serverParamPassword"), password),
					ConnectionManager.makeKV(GlobalData.getProperty("serverParamEmail"), email));
		}
	}
	
	public void login(View v) {
		String username = mUsernameEditText.getText().toString().trim();
		String password = mPasswordEditText.getText().toString();
		
		if (username.isEmpty() || password.isEmpty()) {
			Toast.makeText(this, R.string.msg_allfieldsreqd, Toast.LENGTH_SHORT).show();
		} else {
			ConnectionManager.sendLoginRequest(username, password,
					response -> {
						JsonResponse jr = JsonResponse.createFromJson(response);
						switch (jr.resultCode) {
							case JsonResponse.CODE_NOMINAL: {
								Intent i = new Intent(LoginActivity.this, AccountActivity.class);
								getSharedPreferences(GlobalData.getProperty("prefsNameLogin"),
										MODE_APPEND).edit()
										.putString(GlobalData.getProperty("prefsUsername"), username)
										.putString(GlobalData.getProperty("prefsPassword"), password)
										.commit();
								finish();
								startActivity(i);
								Toast.makeText(getApplicationContext(), R.string.msg_loginsuccess,
										Toast.LENGTH_SHORT).show();
								break;
							}
							case JsonResponse.CODE_INVALID_CREDS: {
								Toast.makeText(this, R.string.msg_invalidcreds,
										Toast.LENGTH_SHORT).show();
								break;
							}
							default:
								Toaster.declaredError(this);
						}
					}, error -> {
						Toaster.httpError(this, error);
					});
		}
	}
}
