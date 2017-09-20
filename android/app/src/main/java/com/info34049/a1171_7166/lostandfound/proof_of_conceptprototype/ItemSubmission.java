package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Adam on 2017-04-11.
 */

public class ItemSubmission implements Serializable{
	private double id;
	public Category category;
	public double latitude, longitude; //place it was lost or found. You can use Google Maps to get values for these
	public String desc; //description
	public GregorianCalendar dateLostOrFound;
	public boolean wasLost; //true if it's a lost submission, false if it's a found submission
	
	public ItemSubmission() {
		id = Math.random();
	}
	
	private static String distFormat(double distance) {
		return String.format("%.1f",((double)Math.round(distance*10))/10);
	}
	
	public String toString() {
		return category.getName() + ": " + desc.substring(0, Math.min(20, desc.length())) + "\n" +
				DateFormat.getDateInstance(DateFormat.MEDIUM).format(dateLostOrFound.getTime()) + " - " +
				distFormat(distance(latitude, longitude, myLat, myLong)) + "km";
	}
	
	private static final double myLat = 43.655885, myLong = -79.738647; //Location of Sheridan
	
	public static ItemSubmission[] getSingletonDatabase() {
		return singletonDatabase.toArray(new ItemSubmission[singletonDatabase.size()]);
	}
	private static ArrayList<ItemSubmission> singletonDatabase = null;
	private static boolean isInit = false;
	public static void singletonInit() {
		if (isInit) return;
		isInit = true;
		Category.singletonInit();
		singletonDatabase = new ArrayList<ItemSubmission>();
		
		
		//create a bunch of ItemSubmission objects here and add them to the ArrayList
		ItemSubmission ds = new ItemSubmission();
		ds.wasLost = true;
		ds.dateLostOrFound = new GregorianCalendar(2016, GregorianCalendar.JUNE, 7);
		ds.category = Category.getSingletonRoot().getChild("Electronics").getChild("Smartphones")
				.getChild("Android Phones");
		ds.latitude = 43.6659189;
		ds.longitude = -79.7338574; //brampton gateway terminal
		ds.desc = "Black Galaxy S7, small crack in top-left corner of screen";
		singletonDatabase.add(ds);
		//Log.i("temp", ds.toString());
	}
	
	public static void addToDB(ItemSubmission sub) {
		singletonDatabase.add(sub);
	}
	
	public static void removeFromDb(ItemSubmission sub) {
		for(int i=0; i<singletonDatabase.size(); i++) {
			if (singletonDatabase.get(i).id == sub.id) {
				singletonDatabase.remove(i);
				return;
			}
		}
	}
	
	
	
	//the following three methods taken from http://www.geodatasource.com/developers/java
	//they compute the distance in km between two locations given by latitude and longitude
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) *
				Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return dist;
	}
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
