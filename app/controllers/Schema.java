package controllers;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Schema extends Controller {
	
	public static void index() {
		//System.out.println("dasfds");
		render();
	}
	
	public static void create(JsonObject body) {
		
		renderJSON("[]");
	}

}
