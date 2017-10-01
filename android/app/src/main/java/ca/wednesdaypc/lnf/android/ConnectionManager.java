package ca.wednesdaypc.lnf.android;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonParseException;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Consumer;

class ConnectionManager {
	private static final String REQUEST_TAG = "RQ_ALL";
	
	@SafeVarargs
	static void sendGetRequest(String servletName, Consumer<String> responseHandler,
	                           Consumer<VolleyError> errorHandler,
	                           Map.Entry<String, String>... params) {
		String url = GlobalData.getProperty("serverUrl") + servletName;
		if (params.length > 0) {
			//Would use Java.util.StringJoiner, but it requires min API 24
			url += "?";
			for (int i = 0; ; i++) {
				url += params[i].getKey() + "=" + params[i].getValue();
				if (i == params.length - 1) break;
				url += "&";
			}
		}
		
		StringRequest request = new StringRequest(Request.Method.GET, url,
				response -> responseHandler.accept(response), error -> errorHandler.accept(error));
		request.setTag(REQUEST_TAG);
		GlobalData.getRequestQueue().add(request);
	}
	
	@SafeVarargs
	static void sendPostRequest(String servletName,
	                            Consumer<String> responseHandler,
	                            Consumer<VolleyError> errorHandler,
	                            Map.Entry<String, String>... params) {
		PostRequest request = new PostRequest(GlobalData.getProperty("serverUrl") + servletName,
				response -> {
					try {
						responseHandler.accept(response);
					} catch (JsonParseException e) {
						Log.wtf("LNF", "Couldn't parse response from server.", e);
					}
				}, error -> errorHandler.accept(error));
		request.setTag(REQUEST_TAG);
		for (Map.Entry<String, String> me : params) request.setParam(me.getKey(), me.getValue());
		GlobalData.getRequestQueue().add(request);
	}
	
	static void cancelRequests(Object tag) {
		if (tag == null) tag = REQUEST_TAG;
		GlobalData.getRequestQueue().cancelAll(tag);
	}
	
	static Map.Entry<String, String> makeKV(String k, String v) {
		return new AbstractMap.SimpleEntry<String, String>(k, v);
	}
}
