package controllers;

import globalswrapper.SchemaManager;
import globalswrapper.Utils;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Schema extends Controller {
	
	public static void index() {
		//System.out.println("dasfds");
		render();
	}
	
	public static void create(JsonObject body) {
		System.out.println("Test");
		SchemaManager mr = new SchemaManager();
		try {
			
			mr.InitSchema(body);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utils.writeToFile("", e.toString());
		}
	}

	public static void read(){
		
	}
}
