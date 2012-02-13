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
		CRUDManager manager = CRUDManager.Instance();
		JsonObject result = new JsonObject();
		try{
			result = manager.Create(tableName, object);
		}catch(Exception ex){
		}
		renderJSON(result);
	}
	
	public static void delete(String id)
	{
	
	}
	
	public static void update(JsonObject object)
	{
	
	}
}
