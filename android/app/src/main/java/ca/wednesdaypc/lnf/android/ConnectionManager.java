package ca.wednesdaypc.lnf.android;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonParseException;

import java.util.Map;
import java.util.function.Consumer;

public class ConnectionManager {
	private static final String REQUEST_TAG = "RQ_ALL";
	
	public static void sendRequest(String servletName, final Consumer<String>
			responseHandler, final Consumer<VolleyError> errorHandler) {
		StringRequest request = new StringRequest(Request.Method.GET,
				GlobalData.getProperty("serverUrl") + servletName,
				response -> responseHandler.accept(response), error -> errorHandler.accept(error));
		request.setTag(REQUEST_TAG);
		GlobalData.getRequestQueue().add(request);
	}
	
	public static void sendPostRequest(String servletName,
	                                   final Consumer<String> responseHandler,
	                                   final Consumer<VolleyError> errorHandler,
	                                   Map<String, String> params) {
		PostRequest request = new PostRequest(GlobalData.getProperty("serverUrl") + servletName,
				response -> {
					try {
						responseHandler.accept(response);
					} catch (JsonParseException e) {
						Log.wtf("LNF", "Couldn't parse response from server.", e);
					}
				}, error -> errorHandler.accept(error));
		request.setTag(REQUEST_TAG);
		params.forEach((k,v)->request.setParam(k,v));
		GlobalData.getRequestQueue().add(request);
	}
	
	public static void cancelRequests(Object tag) {
		if (tag == null) tag = REQUEST_TAG;
		GlobalData.getRequestQueue().cancelAll(tag);
	}
}
