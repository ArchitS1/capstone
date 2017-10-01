package ca.wednesdaypc.lnf.android;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.Properties;

/**
 * Singleton class to hold data that persists across Activities
 */
class GlobalData {
	static final String LOG_TAG = "WPC_SR";
	private static GlobalData mInstance;
	private Properties mProperties;
	private RequestQueue mQueue;
	
	private GlobalData(){}
	
	/**
	 * You must call this method before attempting to access any of the data, or else an
	 * {@link IllegalStateException} will be thrown when you do. If this method is called multiple
	 * times, all but the first will be no-ops. It is therefore recommended that this method be
	 * called in your starting Activity's onCreate() method.
	 * @param context The current context.
	 */
	static synchronized void init(Context context) {
		if (mInstance != null) return;
		
		context = context.getApplicationContext();
		
		mInstance = new GlobalData();
		mInstance.mQueue = Volley.newRequestQueue(context);
		
		try {
			mInstance.mProperties = new Properties();
			mInstance.mProperties.load(context.getAssets().open("config.properties"));
		} catch (IOException e) {
			Log.wtf(LOG_TAG, "Could not load properties from file.", e);
			mInstance.mProperties = null;
		}
	}
	
	private static IllegalStateException exceptionFactory() {
		return new IllegalStateException("GlobalData instance has not been initialized.");
	}
	
	static RequestQueue getRequestQueue() throws IllegalStateException {
		if (mInstance == null) throw exceptionFactory();
		return mInstance.mQueue;
	}
	
	static String getProperty(String key) throws IllegalStateException {
		if (mInstance == null || mInstance.mProperties == null) throw exceptionFactory();
		return mInstance.mProperties.getProperty(key);
	}
}
