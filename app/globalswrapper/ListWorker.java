package globalswrapper;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intersys.globals.NodeReference;

public class ListWorker {

	public ArrayList<JsonObject> GetList(FilterCondition condition, SortCondition sort, PageInfo requiredPage)
	{
		ArrayList<JsonObject> list = ApplyFilter(condition);
		list = SortItems(list, sort);
		list = PaginateItems(list, requiredPage);
		return list;
	}
	
	public ArrayList<JsonObject> ApplyFilter(FilterCondition condition)
	{
		ArrayList<JsonObject> list = new ArrayList<JsonObject>();
		
		String globalName = Utils.TableNameToGlobalsName(condition.TableName+SchemaManager.Instance().GetProjectPrefix(condition.ProjectId));  
		NodeReference node = ConnectionManager.Instance().getConnection().createNodeReference(globalName);
		
		Long key = (long)0;
		while (true)
		{
			String strKey = node.nextSubscript(key);
			if (strKey.equals(""))
				break;
			key = Long.parseLong(strKey);
			JsonObject obj = new JsonParser().parse(node.getObject(key).toString()).getAsJsonObject();
			list.add(obj);
		}
		
		return list;
		
	}
	
	public ArrayList<JsonObject> SortItems(ArrayList<JsonObject> items, SortCondition condition)
	{
		return items;
	}
	
	public ArrayList<JsonObject> PaginateItems(ArrayList<JsonObject> items, PageInfo requiredPage)
	{
		
		return items;
		
	}
	
	public void WriteList(ArrayList<JsonObject> items)
	{
		for (int i=0; i<items.size(); i++)
		{
			System.out.println(items.get(i));
		}
		
	}
}
