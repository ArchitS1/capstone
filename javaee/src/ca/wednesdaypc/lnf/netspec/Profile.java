package ca.wednesdaypc.lnf.netspec;

import ca.wednesdaypc.lnf.beans.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	public String email, phone, twitter, facebook, instagram, tumblr;
	//Later add date of account creation
	
	public Profile(User source) {
		email = source.getEmail();
		phone = source.getPhone();
		twitter = source.getTwitter();
		facebook = source.getFacebook();
		instagram = source.getInstagram();
		tumblr = source.getTumblr();
	}
}
