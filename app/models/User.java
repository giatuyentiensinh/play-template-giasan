package models;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;

import play.data.validation.Constraints;

public class User {

	@Id
	private String id;
	@Constraints.Required
	private String username;
	@Constraints.Required
	private String password;

	private static JacksonDBCollection<User, String> col = JacksonDBCollection
			.wrap(ModuleMongo.getCollection("authentication", "user"),
					User.class, String.class);

	public static boolean authentication(String name, String pass) {
		Query queryUser = DBQuery.in("username", name);
		Query queryPass = DBQuery.in("password", pass);
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

}
