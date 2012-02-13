package controllers;

import globalswrapper.SchemaManager;
import globalswrapper.Utils;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Schema extends BaseController {
	
	public static void index() {
		render();
	}
	
	public static void create(JsonObject body) {
		System.out.println(body.toString());
		SchemaManager mr = new SchemaManager();
		try {			
			mr.InitSchema(body);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
			Utils.writeToFile("", e.toString());
		}
		
		renderJSON("[]");
	}

	public static void read(){
		
	}
}
