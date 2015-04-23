package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import play.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "authentication")
public class User {

	@Id
	private String id;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;

	private static JacksonDBCollection<User, String> col = JacksonDBCollection
			.wrap(ConnectMongo.getCollection("authentication", "user"),
					User.class, String.class);

	public static boolean authentication(String name, String pass) {
		Query queryUser = DBQuery.in("username", name);
		Query queryPass = DBQuery.in("password", getSHAPass(pass));
		DBCursor<User> authentication = col.find(DBQuery.and(queryUser,
				queryPass));
		if (authentication.hasNext())
			return true;
		return false;
	}
	
	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private static String getSHAPass(String pass) {
		try {
			byte[] digest = MessageDigest.getInstance("SHA-512").digest(pass.getBytes("UTF-8"));
			StringBuffer sbPass = new StringBuffer();
			for (byte b : digest) {
				sbPass.append(b);
			}
			return sbPass.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			Logger.error(e.toString());
			return null;
		}
	}
	
}
