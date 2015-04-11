package controllers;

import models.ModuleMongo;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {	
    	return ok("Tuyen-ng");
    }

    public static Result showCollection() {
    	return ok(Json.toJson(ModuleMongo.getCollections()));
    }
    
}
