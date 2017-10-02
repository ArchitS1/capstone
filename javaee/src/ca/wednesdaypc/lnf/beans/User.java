package ca.wednesdaypc.lnf.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name="User.exists", query="select count(*) from User where username = :username and password = :password")
public class User implements Serializable{
	@Id
	@GeneratedValue 
	private int id;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String email;
	private String phone;
	private String twitter;
	private String facebook;
	private String instagram;
	private String tumblr;
	
	public User(String username, String password, String email, String phone, String twitter, String facebook,
			String instagram, String tumblr) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.twitter = twitter;
		this.facebook = facebook;
		this.instagram = instagram;
		this.tumblr = tumblr;
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
}	
