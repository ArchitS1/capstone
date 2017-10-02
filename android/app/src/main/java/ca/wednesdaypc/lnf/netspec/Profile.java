package ca.wednesdaypc.lnf.netspec;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Profile {
	public String email, phone, twitter, facebook, instagram, tumblr;
	//Later add date of account creation
	
	public Profile(LinkedTreeMap map) {
		LinkedTreeMap<String, String> sMap = (LinkedTreeMap<String, String>)map;
		email = sMap.getOrDefault("email", null);
		phone = sMap.getOrDefault("phone", null);
		twitter = sMap.getOrDefault("twitter", null);
		facebook = sMap.getOrDefault("facebook", null);
		instagram = sMap.getOrDefault("instagram", null);
		tumblr= sMap.getOrDefault("tumblr", null);
	}
}
