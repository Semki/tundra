package controllers;

import globalswrapper.CRUDManager;

import com.google.gson.JsonObject;

import play.mvc.Controller;
import play.mvc.Http.StatusCode;
import play.mvc.results.Error;

public class CRUD extends BaseController{

	public static void show(Long projectId, String tableName, long id)
	{
		try
		{
			CRUDManager manager = CRUDManager.Instance();
			JsonObject result = manager.Read(projectId, tableName, id);
			if (result == null)
			{
				notFound();
			}
			else
			{
				renderJSON(result);
			}
		}
		catch (Exception ex)
		{
			internalError();
		}
	}
	
	

	public static void create(Long projectId, String tableName, JsonObject object)
	{
		try{
			CRUDManager manager = CRUDManager.Instance();
			JsonObject result = new JsonObject();
			result = manager.Create(projectId, tableName, object);
			if (result != null)
			{
				ok();
			}
		}catch(Exception ex){
			internalError();
		}
	}
	
	public static void delete(String id)
	{
	
	}
	
	public static void update(JsonObject object)
	{
	
	}
}
