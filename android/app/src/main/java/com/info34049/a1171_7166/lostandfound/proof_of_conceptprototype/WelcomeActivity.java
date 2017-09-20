package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
	
	public static final String KEY_LOG_FLAG = "isLoggedIn";
	
	public static final int RQC_DEFAULT = 0;
	public static final String KEY_BACKTO = "go back further";
	public static final int VAL_BACKTO_WELCOME = 1;
	
	
	private boolean logged;
	private Button btnAct;
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		state.putBoolean(KEY_LOG_FLAG, logged);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		btnAct = (Button)findViewById(R.id.btnAct);
		logged = savedInstanceState != null && savedInstanceState.getBoolean(KEY_LOG_FLAG, false);
		simpleOnLoggedChange(this, btnAct, logged);
	}
	
	public static void simpleOnLoggedChange(AppCompatActivity activity, Button btnAct, boolean newLogged) {
		btnAct.setText(newLogged ? "Account" : "Login");
		activity.setResult(RESULT_OK, (new Intent()).putExtra(KEY_LOG_FLAG, newLogged));
	}
	
	public static void notImplementedToast(Context context) {
		Toast.makeText(context, "This feature not implemented yet", Toast.LENGTH_SHORT).show();
	}
	
	public static void simpleNextScreen(AppCompatActivity activity, Class<?> cls, boolean logged) {
		Intent intent = new Intent(activity, cls);
		intent.putExtra(KEY_LOG_FLAG, logged);
		activity.startActivityForResult(intent, RQC_DEFAULT);
	}
	
	public static void simpleAccountClick(AppCompatActivity activity, boolean logged) {
		simpleNextScreen(activity, logged ? AccountActivity.class : LoginActivity.class, logged);
	}
	
	public void onClickLost(View view) {
		notImplementedToast(this);
	}
	
	public void onClickSettings(View view) {
		notImplementedToast(this);
	}
	
	public void onClickFound(View view) {
		simpleNextScreen(this, FoundAnItemActivity.class, logged);
	}
	
	public void onClickSearch(View view) {
		simpleNextScreen(this, BrowseLostActivity.class, logged);
	}
	
	public void onClickAccount(View view) {
		simpleAccountClick(this, logged);
	}
	
	@Override
	protected void onActivityResult(int rqc, int rsc, Intent intent) {
		logged = intent != null && intent.getBooleanExtra(KEY_LOG_FLAG, false);
		simpleOnLoggedChange(this, btnAct, logged);
	}
}
