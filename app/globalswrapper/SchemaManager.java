package globalswrapper;

import java.util.Iterator;



import com.google.gson.JsonObject;
import com.intersys.globals.NodeReference;

public class SchemaManager {
	public static String TABLES_NAME = "tables";
	public static String COLUMNS_NAME = "columns";
	public void InitSchema(JsonObject schemaObject) throws Exception
	{
		//JSONObject tables = (JSONObject) schemaObject.get(TABLES_NAME);
		if (schemaObject != null)
		{
			Utils.writeToFile("", schemaObject.toString());
		}
		else
		{
			Utils.writeToFile("", "schema is null");
		}
		
		Iterator iter = schemaObject.entrySet().iterator();
	    
		while (iter.hasNext()) {
			Utils.writeToFile("", iter.next().toString());
			Utils.writeToFile("", iter.toString());
			System.out.println(iter.next());
			System.out.println(iter.toString());
			
			String globalName = "TESTTABLES";
	        NodeReference node = null;
		    node = ConnectionManager.Instance().getConnection().createNodeReference(globalName);
		    node.increment(1);
		    Long id = node.getLong();
		    
		    node.set(iter.toString(), id, "key");
			
	    }
	    
	}
}
