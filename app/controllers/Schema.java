package controllers;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Schema extends Controller {
	
	public static void index() {
		//System.out.println("dasfds");
		render();
	}
	
	public static void create(JsonObject body) {
		if (body == null){
			System.out.println("trololo");
		}
		renderJSON(body);
	}
	
	public static void read(){
		System.out.println();
		renderJSON("[]");
	}

}
