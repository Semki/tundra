package controllers;

import jsGenerate.JSGenerator;
import globalswrapper.SchemaManager;
import globalswrapper.Utils;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class Schema extends BaseController {
	
	public static void index() {
		render();
	}
	
	public static void new_scheme(long project_id) {
		render(project_id);
	}
	
	public static void create(JsonObject body) {
		SchemaManager mr = new SchemaManager();
		Long projectId = body.get("project_id").getAsLong();
		
		try {			
			mr.InitSchema(body, projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
			Utils.writeToFile("", e.toString());
		}

		
		// generate JavaScript
		JSGenerator generator = new JSGenerator();
		try {
			generator.Generate(body, projectId.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		renderJSON("[]");
	}

	public static void read(JsonObject body){
		Long projectId = body.get("project_id").getAsLong();
		SchemaManager mr = SchemaManager.Instance();
		body = mr.ReadSchema(projectId);		
		renderJSON(body);	
	}
}
