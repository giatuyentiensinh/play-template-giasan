package controllers;

import models.ModuleMongo;
import models.User;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	private static Form<User> form = Form.form(User.class);
	
    public static Result index() {	
    	return ok("Tuyen-ng");
    }

    public static Result showCollection() {
    	return ok(Json.toJson(ModuleMongo.getCollections()));
    }
    
    public static Result login() {
    	return redirect("template#/access/signin");
    }
    
    public static Result authenticator() {
    	Form<User> authentication = form.bindFromRequest();
    	if(authentication.hasErrors()) {
    		Logger.error("Form not validated");
    		return badRequest("Error");
    	}
    	String username = authentication.get().getUsername();
    	String password = authentication.get().getPassword();
    	
    	if(User.authentication(username, password)) {
    		return redirect("/template");
    	} else {
    		Logger.warn(username + " : " + password);
    		return notFound("You not account");
    	}
    	
    }
    
}
