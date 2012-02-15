package controllers;

import globalswrapper.ProjectManager;
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
	
	public static void new_project(JsonObject body) {
		System.out.println("data");
		System.out.println(body);
		body = ProjectManager.Instance().CreateProject(body);
		
		renderJSON("ok");
	}
	
	public static void get_projects_list() {
		renderJSON(ProjectManager.Instance().GetProjectsList());
	}
	
	public static void list() {
		render();
	}
}
