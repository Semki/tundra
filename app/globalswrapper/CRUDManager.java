package globalswrapper;

import com.google.gson.JsonObject;
import com.intersys.globals.*;

public class CRUDManager {
	
	   
    private static CRUDManager manager;
    private static ConnectionManager connectionManager;
    public static CRUDManager Instance()
    {
        if (manager == null)
        {
            manager = Init();
        }
        return manager;
    }
    
    private static CRUDManager Init()
    {
        CRUDManager _manager = new CRUDManager();
        _manager.connectionManager = ConnectionManager.Instance();
        return _manager;
    }
    
    public JsonObject Create(String tableName, JsonObject object) throws Exception
    {
    	JsonObject response = new JsonObject();
        String globalName = Utils.TableNameToGlobalsName(tableName);
        
        //NodeReference node = connectionManager.getConnection().createNodeReference(globalsName);
        //node.appendSubscript("");
        //String subscr = "";
        //subscr = node.nextSubscript();   
        NodeReference node = null;
        node = connectionManager.getConnection().createNodeReference(globalName);
        node.increment(1);
        Long id = node.getLong();
     
       
        node.set("TESTTT", id, "Field1");

        
        return response;
    }
    
    public JsonObject Read(String tableName, long id) throws Exception
    {
    	JsonObject response = new JsonObject();
    	return response;
    }
    
    
    public JsonObject Update(String tableName, JsonObject object) throws Exception
    {
    	JsonObject response = new JsonObject();
    	return response;

    }
    
    
    public Boolean Delete(String tableName, long id) throws Exception
    {
    	return true;
    }
    
    
    

}
