package controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.ConnectMongo;
import models.User;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.DBObject;

public class Application extends Controller {

	@Authenticated(Secured.class)
	public static Result index() {
		return ok("Tuyen-ng");
	}

	@Authenticated(Secured.class)
	public static Result showCollection() {
		return ok(Json.toJson(ConnectMongo.getCollections()));
	}

	public static Result elementCollection() {

		DynamicForm requestForm = Form.form().bindFromRequest();
		Map<String, String> map = requestForm.data();
		for (String key : map.keySet())
			Logger.info(key + " : " + map.get(key));
		List<DBObject> document = ConnectMongo.getDocument(map.get("db"),
				map.get("collection"));
		Logger.warn(document.toString());

		return ok(Json.toJson(document));
	}

	public static Result logout() {
		session().clear();
		return ok();
	}

	public static Result login() {
		return redirect("template#/access/signin");
	}

	public static Result authenticator() {
		Form<Login> authentication = Form.form(Login.class).bindFromRequest();
		if (authentication.hasErrors()) {
			Logger.error("Form not validated");
			return badRequest("Error");
		}

		ObjectNode json = Json.newObject();
		Login account = authentication.get();

		if (User.authentication(account.username, account.password)) {
			session("sessionUser", UUID.randomUUID().toString());
			json.put("user", "success");
			return ok(json);
		} else {
			json.put("user", "error");
			return ok(json);
		}

	}

	public static class Login {
		@Required
		@Email
		public String username;
		@Required
		public String password;

	}

}
