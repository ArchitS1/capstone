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
public class WelcomeActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		final TextView mTextView = (TextView)findViewById(R.id.helloTextView);
		
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://10.46.26.44:8080/capstone_server/Hello";
		//String url = "http://google.ca";
		
		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// Display the first 500 characters of the response string.
						mTextView.setText(response);
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				mTextView.setText("Couldn't retrieve data");
			}
		});
		
		// Add the request to the RequestQueue.
		queue.add(stringRequest);
	}
}
