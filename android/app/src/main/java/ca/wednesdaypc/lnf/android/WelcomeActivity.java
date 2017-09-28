package ca.wednesdaypc.lnf.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import ca.wednesdaypc.lnf.json.JsonResponse;

public class WelcomeActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		final TextView mTextView = (TextView)findViewById(R.id.helloTextView);
		
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://192.168.2.36:8080/capstone_server/Hello";
		//String url = "http://google.ca";
		
		// Request a string response from the provided URL.
		StringRequest request = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						JsonResponse jr = JsonResponse.createFromJson(response);
						if (jr.resultCode == JsonResponse.CODE_NOMINAL) {
							mTextView.setText((String) jr.payload);
						} else {
							mTextView.setText("The server was unable to complete your request.");
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				mTextView.setText("Couldn't retrieve data");
			}
		});
		
		
		// Add the request to the RequestQueue.
		queue.add(request);
	}
}
