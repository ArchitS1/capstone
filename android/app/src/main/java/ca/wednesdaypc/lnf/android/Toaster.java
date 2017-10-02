package ca.wednesdaypc.lnf.android;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

class Toaster {
	static void declaredError(Context ctx) {
		Toast.makeText(ctx, R.string.msg_genericerror, Toast.LENGTH_SHORT).show();
	}
	
	static void httpError(Context ctx, VolleyError e) {
		String message;
		if (e instanceof TimeoutError) {
			message = ctx.getString(R.string.msg_timeout);
		} else {
			message = ctx.getString(R.string.msg_errorcode, e.networkResponse.statusCode);
		}
		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}
}
