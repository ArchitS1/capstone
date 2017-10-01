package ca.wednesdaypc.lnf.json;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
	public static final int CODE_NOMINAL = 0;
	public static final int CODE_NEED_LOGIN = 1;
	public static final int CODE_DB_ERROR = 2;
	public static final int CODE_DUPE_USERNAME = 3;
	private static final Gson gson = new Gson();
	
	public int resultCode;
	public Object payload;
	
	public String toJson() {
		return gson.toJson(this);
	}
	
	public static JsonResponse createFromJson(String json) throws JsonParseException {
		return gson.fromJson(json, JsonResponse.class);
	}
}
