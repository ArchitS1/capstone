package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BrowseLostActivity extends AppCompatActivity {
	
	private static final String KEY_ITEM_LIST = "item list";
	public static final String KEY_ITEM_SELECTED = "selected item";
	
	private boolean logged;
	private Button btnAct;
	private ListView itemContainer;
	private ItemSubmission[] itemList;
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		state.putBoolean(WelcomeActivity.KEY_LOG_FLAG, logged);
		state.putSerializable(KEY_ITEM_LIST, itemList);
	}
	
	@Override
	protected void onActivityResult(int rqc, int rsc, Intent data) {
		if (data != null && data.getIntExtra(WelcomeActivity.KEY_BACKTO, 0) ==
				WelcomeActivity.VAL_BACKTO_WELCOME) {
			setResult(RESULT_OK, data);
			finish();
			return;
		}
		
		logged = data != null && data.getBooleanExtra(WelcomeActivity.KEY_LOG_FLAG, false);
		WelcomeActivity.simpleOnLoggedChange(this, btnAct, logged);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_lost);
		ItemSubmission.singletonInit();
		
		itemContainer = (ListView)findViewById(R.id.lvItems);
		
		btnAct = (Button)findViewById(R.id.btnAct);
		
		if (savedInstanceState != null) {
			itemList = (ItemSubmission[])savedInstanceState.getSerializable(KEY_ITEM_LIST);
			logged = savedInstanceState.getBoolean(WelcomeActivity.KEY_LOG_FLAG, false);
		} else {
			ArrayList<ItemSubmission> al = new ArrayList<>();
			for (ItemSubmission sub : ItemSubmission.getSingletonDatabase()) {
				if (sub.wasLost) {
					al.add(sub);
				}
			}
			itemList = al.toArray(new ItemSubmission[al.size()]);
			logged = getIntent().getBooleanExtra(WelcomeActivity.KEY_LOG_FLAG, false);
		}
		WelcomeActivity.simpleOnLoggedChange(this, btnAct, logged);
		
		ArrayAdapter<ItemSubmission> adapter = new ArrayAdapter<ItemSubmission>
				(this, android.R.layout.simple_list_item_1, itemList);
		itemContainer.setAdapter(adapter);
		itemContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(BrowseLostActivity.this, LostDetailsActivity.class);
				intent.putExtra(KEY_ITEM_SELECTED, itemList[position]);
				intent.putExtra(WelcomeActivity.KEY_LOG_FLAG, logged);
				startActivityForResult(intent, WelcomeActivity.RQC_DEFAULT);
			}
		});
	}
	
	public void onClickSettings(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
	
	public void onClickFilter(View view) {
		WelcomeActivity.notImplementedToast(this);
	}
	
	public void onClickAccount(View view) {
		WelcomeActivity.simpleAccountClick(this, logged);
	}
}
