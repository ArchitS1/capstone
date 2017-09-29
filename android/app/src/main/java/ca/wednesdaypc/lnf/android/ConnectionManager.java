package ca.wednesdaypc.lnf.android;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
	
	public static void cancelRequests(Object tag) {
		if (tag == null) tag = REQUEST_TAG;
		GlobalData.getRequestQueue().cancelAll(tag);
	}
}
