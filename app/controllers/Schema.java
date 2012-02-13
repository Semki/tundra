package controllers;

import globalswrapper.SchemaManager;
import globalswrapper.Utils;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Schema extends Controller {
	
	public static void index() {
		System.out.println("dasfds");
		render();
	}
	
	public static void new_scheme() {
		render();
	}
	
	public static void create(JsonObject body, Long projectId) {
		System.out.println(body.toString());
		SchemaManager mr = new SchemaManager();
		try {
			
			mr.InitSchema(body, projectId);
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
