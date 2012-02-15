package controllers;

import java.net.InetAddress;

import jsGenerate.JSGenerator;
import globalswrapper.SchemaManager;
import globalswrapper.Utils;

import com.google.gson.JsonObject;
import com.sun.corba.se.spi.activation.Server;

import play.mvc.Controller;

public class Schema extends BaseController {
	
	public static void index() {
		
		System.out.println(request);
		render();
	}
	
	public static void new_scheme(long project_id) {
		render(project_id);
	}
	
	public static void show(long project_id) {
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
		String url = "";
		try {
			
			String fileName = generator.Generate(body, projectId.toString());
			InetAddress addr = InetAddress.getLocalHost();
			String port = request.port.toString();					
			String hostname = addr.getHostName();	
			url = hostname+":"+port+fileName;
			System.out.println(url);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		renderJSON(url);
	}

	public static void read(Long projectId){
		SchemaManager mr = SchemaManager.Instance();
		JsonObject body = mr.ReadSchema(projectId);		
		renderJSON(body.toString());	
	}
}
