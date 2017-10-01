package ca.wednesdaypc.lnf.android;

import android.content.Context;
import android.widget.Toast;

class Toaster {
	static void declaredError(Context ctx) {
		Toast.makeText(ctx, R.string.msg_genericerror, Toast.LENGTH_SHORT).show();
	}
	
	static void httpError(Context ctx, int code) {
		Toast.makeText(ctx, ctx.getString(R.string.msg_errorcode, code), Toast.LENGTH_SHORT).show();
	}
}
