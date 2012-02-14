package controllers;

import java.util.ArrayList;

import globalswrapper.CRUDManager;
import globalswrapper.FilterCondition;
import globalswrapper.ListWorker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import play.mvc.Controller;
import play.mvc.Http.StatusCode;
import play.mvc.results.Error;

public class CRUD extends BaseController{

	// TODO: conditions
	public static void list(Long projectId, String tableName)
	{
		try {
			JsonArray jsonArray = new JsonArray(); 
			
			//JsonObject resultJson = new JsonObject();
			
			ListWorker listWorker = new ListWorker();
			FilterCondition condition = new FilterCondition();
			condition.ProjectId = projectId;
			condition.TableName = tableName;
			
					
			ArrayList<JsonObject> result = listWorker.GetList(condition, null, null);
			
			System.out.println(result.get(0).toString());
			
			
			
			//renderJSON(result.get(0));
			
			
			
			for (int i=0; i<result.size(); i++)
			{
				//result.get(i).;
				jsonArray.add(result.get(i));
				//result.get
			}
			
			renderJSON(jsonArray.toString());
			/*
			//resultJson.
			//jsonArray.
			
			renderJSON(jsonArray);
			*/
		}
		catch (Exception ex){
			System.out.println(ex.toString());
			internalError();
		}
	}
	
	public static void show(Long projectId, String tableName, long id)
	{
		try	{
			CRUDManager manager = CRUDManager.Instance();
			JsonObject result = manager.Read(projectId, tableName, id);
			if (result == null){
				notFound();
			}
			else{
				renderJSON(result);
			}
		}catch (Exception ex){
			internalError();
		}
	}

	public static void create(Long projectId, String tableName, JsonObject body)
	{
		try{
			CRUDManager manager = CRUDManager.Instance();
			JsonObject result = new JsonObject();
			result = manager.Create(projectId, tableName, body);
			if (result != null){
				ok();
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
			internalError();
		}
	}
	
	public static void delete(Long projectId, String tableName, long id)
	{
		try{
			CRUDManager manager = CRUDManager.Instance();
			manager.Delete(projectId, tableName, id);
			ok();
		}catch(Exception ex){
			internalError();
		}
	}
	
	public static void update(Long projectId, String tableName, JsonObject object)
	{	
		try{
			CRUDManager manager = CRUDManager.Instance();
			manager.Update(projectId, tableName, object);
			ok();
		}catch(Exception ex){
			internalError();
		}
	}
}
