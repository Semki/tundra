package controllers;

import globalswrapper.CRUDManager;

import com.google.gson.JsonObject;

import play.mvc.Controller;

public class CRUD extends Controller{
	
	public static void show(String id)
	{
		CRUDManager manager = CRUDManager.Instance();
		renderJSON("");
	}
	
	public static void create(String tableName, JsonObject object)
	{
		System.out.println("try");
		System.out.println(tableName +"  fgdffg ");
		CRUDManager manager = CRUDManager.Instance();
		try{
			//JsonObject obj = manager.Create(tableName, object);
		}catch (Exception e) {
		
		}
		
		renderJSON("");
	}
	
	public static void delete(String id)
	{
	
	}
	
	public static void update(JsonObject body)
	{
	
	}
}
