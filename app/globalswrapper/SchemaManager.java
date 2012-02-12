package globalswrapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import sun.org.mozilla.javascript.internal.json.JsonParser;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.intersys.globals.NodeReference;

public class SchemaManager {
	public static String TABLES_NAME = "tables";
	public static String COLUMNS_NAME = "columns";
	public void InitSchema(JsonObject schemaObject) throws Exception
	{
		 JsonArray tables = schemaObject.get(TABLES_NAME).getAsJsonArray();
		 for (int i=0; i<tables.size(); i++)
		 {
			 //JsonElement table = tables.get(i);
			JsonObject table = tables.get(i).getAsJsonObject();
			 
			 //System.out.println(table.toString());
			 CreateTable2(table);
			 
		 }
		 
		 ReadTable("Person");
		
	}
	

	public String GetTableStorageGlobalsName()
	{
		return "UserTables";
		
	}
	
	// Создаем информацию о схеме данных через типовую структуру глобалов
	public void CreateTable(JsonObject table)
	{
		System.out.println("step1");
		System.out.println(table.toString());
	    
		NodeReference node = ConnectionManager.Instance().getConnection().createNodeReference(GetTableStorageGlobalsName());
	    
	    String tableName = table.get("table_name").getAsString();
	    System.out.println("step2"+tableName);
	    
	    JsonArray columns = table.get(COLUMNS_NAME).getAsJsonArray();
	    node.set(columns.size(), tableName, "ColumnsCount");
	   
	    for (int i=0; i<columns.size(); i++)
	    {
	    	System.out.println("column^"+columns.get(i).toString());
	    	JsonObject column = columns.get(i).getAsJsonObject();
	    	String columnName = column.get("column_name").getAsString();
	    	node.set("", tableName, COLUMNS_NAME, columnName);
	    	node.set(column.get("type").getAsString(), tableName,   COLUMNS_NAME,  columnName, "ColumnType");
	    	node.set(0, tableName,  COLUMNS_NAME,  columnName, "Required");
	    }
	    
	    node.close();
	 
	}
	
	public static String TABLE_NAME = "table_name";
	public static String COLUMN_TYPE = "ColumnType";
	
	// Создаем информацию о схеме данных через типовую структуру глобалов
	public void CreateTable2(JsonObject table)
	{
		NodeReference node = ConnectionManager.Instance().getConnection().createNodeReference(GetTableStorageGlobalsName());
	    
	    String tableName = table.get("table_name").getAsString();
	    
	    JsonArray columns = table.get(COLUMNS_NAME).getAsJsonArray();
	    node.set(columns.toString(), tableName, COLUMNS_NAME);
	   
	   
	    node.close();
	 
	}
	
	public JsonObject ReadTable(String tableName)
	{
		NodeReference node = ConnectionManager.Instance().getConnection().createNodeReference(GetTableStorageGlobalsName());
	    JsonObject object = new JsonObject();
	    object.addProperty(TABLE_NAME, tableName);
        
        node.setSubscriptCount(0);
        node.appendSubscript(tableName);
        String nodeValue = "";
        String subscript = "";
        do {
            subscript = node.nextSubscript(subscript);
            if (subscript.length() > 0) 
            {
            	
            	nodeValue = node.getObject(subscript).toString();
            	JsonElement o = new com.google.gson.JsonParser().parse(nodeValue); 
            	object.add(subscript, o);
            }
         }while (subscript.length() > 0);
       
        //System.out.println(object.toString());
       
	    node.close();
	    return object;
	 
	}
}
