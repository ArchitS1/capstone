package ca.wednesdaypc.lnf.android;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Adam on 2017-09-30.
 */

public class PostRequest extends StringRequest {
	private Map<String, String> mParams;
	
	public PostRequest(String url, Response.Listener<String> listener, Response.ErrorListener
			errorListener) {
		super(Method.POST, url, listener, errorListener);
		mParams = new Hashtable<>();
	}
	
	public void setParam(String key, String val) {
		mParams.put(key, val);
	}
	
	@Override
	public Map<String, String> getParams() {
		return mParams;
	}
}
