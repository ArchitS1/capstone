package ca.wednesdaypc.lnf.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
				response -> handleResponse(response, responseHandler),
				error -> errorHandler.accept(error));
		request.setTag(REQUEST_TAG);
		GlobalData.getRequestQueue().add(request);
	}
	
	@SafeVarargs
	static void sendPostRequest(String servletName,
	                            Consumer<String> responseHandler,
	                            Consumer<VolleyError> errorHandler,
	                            Map.Entry<String, String>... params) {
		PostRequest request = new PostRequest(GlobalData.getProperty("serverUrl") + servletName,
				response -> handleResponse(response, responseHandler),
				error -> errorHandler.accept(error));
		request.setTag(REQUEST_TAG);
		for (Map.Entry<String, String> me : params) request.setParam(me.getKey(), me.getValue());
		GlobalData.getRequestQueue().add(request);
	}
	
	private static void handleResponse(String response, Consumer<String> handler) {
		try {
			handler.accept(response);
		} catch (JsonParseException e) {
			Log.wtf(GlobalData.LOG_TAG, "Couldn't parse response from server.", e);
		}
	}
	
	static void cancelRequests(Object tag) {
		if (tag == null) tag = REQUEST_TAG;
		GlobalData.getRequestQueue().cancelAll(tag);
	}
	
	static Map.Entry<String, String> makeKV(String k, String v) {
		return new AbstractMap.SimpleEntry<>(k, v);
	}
	
	static void sendLoginRequest(String username, String password, Consumer<String> responseHandler,
	                             Consumer<VolleyError> errorHandler) {
		sendPostRequest(GlobalData.getProperty("servletNameLogin"), responseHandler, errorHandler,
				makeKV(GlobalData.getProperty("serverParamUsername"), username),
				makeKV(GlobalData.getProperty("serverParamPassword"), password));
	}
	
	static void saveCreds(Activity act, String username, String password) {
		SharedPreferences.Editor ed = act.getSharedPreferences(
				GlobalData.getProperty("prefsNameLogin"), Activity.MODE_APPEND).edit();
		ed.putString(GlobalData.getProperty("prefsUsername"), username);
		ed.putString(GlobalData.getProperty("prefsPassword"), password);
		ed.commit();
	}
	
	static void clearCreds(Activity act) {
		SharedPreferences.Editor ed = act.getSharedPreferences(
				GlobalData.getProperty("prefsNameLogin"), Activity.MODE_APPEND).edit();
		ed.remove(GlobalData.getProperty("prefsUsername"));
		ed.remove(GlobalData.getProperty("prefsPassword"));
		ed.commit();
	}
	
	static String[] getCreds(Context ctx) {
		SharedPreferences prefs = ctx.getSharedPreferences(GlobalData.getProperty("prefsNameLogin"),
				Activity.MODE_APPEND);
		return new String[]{prefs.getString(GlobalData.getProperty("prefsUsername"), null),
				prefs.getString(GlobalData.getProperty("prefsPassword"), null)};
	}
}
