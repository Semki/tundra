package controllers;

import globalswrapper.SchemaManager;
import globalswrapper.Utils;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Project extends Controller {
	
	public static void index() {
		render();
	}
	
	public static void create() {
		render();
	}
	
	public static void new_project(JsonObject data) {
		renderJSON("ok");
	}
}
