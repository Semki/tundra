package globalswrapper;


import globalswrapper.*;
import com.google.gson.JsonElement;
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
    
    public static String ID = "Id";
    public JsonObject Create(Long projectId, String tableName, JsonObject object) throws Exception
    {
    	String globalName = SchemaManager.GetGlobalDataByTableNameAndProjectId(tableName, projectId);
        NodeReference node = null;
        try
        {
        	
        	node = connectionManager.getConnection().createNodeReference(globalName);
        	connectionManager.getConnection().startTransaction();
        	node.increment(1);
	        object.addProperty(ID, node.getString());
	        node.set(object.toString(), node.getLong(), "JSON");
	        
	        IndexManager manager = new IndexManager(tableName, projectId);
	        manager.AfterCreateRecord(object);
	        
	        connectionManager.getConnection().commit();
        }
        catch (Exception ex) 
        {
        	connectionManager.getConnection().rollback();
        	throw ex;
        }
        
         
        return object;
    }
    
    public JsonObject Read(Long projectId, String tableName, long id) throws Exception
    {
    	
    	//System.out.println("start read");
    	
        String globalName = SchemaManager.GetGlobalDataByTableNameAndProjectId(tableName, projectId);
        NodeReference node = connectionManager.getConnection().createNodeReference(globalName);
        
       // System.out.println("node = "+node);
        
        node.setSubscriptCount(0);
        //System.out.println("set count = 0");
        
        //node.appendSubscript(id);
        
        //System.out.println("appended id"+id+" "+globalName);
        
        
        //System.out.println("node = "+node.getString());
        //System.out.println("node = "+node.getString("JSON"));
        
        //Object obj2 = node.getObject("JSON");
        //System.out.println("obj2= "+obj2);
        
        
        String nodeValue = node.getObject(id, "JSON").toString(); 
        		///node.getObject("JSON").toString();
        
        //System.out.println("nodeValue is "+nodeValue);
        
        JsonObject response = new com.google.gson.JsonParser().parse(nodeValue).getAsJsonObject(); 
    	return response;
    }
    
    
    public JsonObject Update(Long projectId, String tableName, JsonObject object) throws Exception
    {
    
        String globalName = SchemaManager.GetGlobalDataByTableNameAndProjectId(tableName, projectId);
        
        if (!object.has(ID))
        {
        	return null;
        }
        
        String sid = object.get(ID).getAsString();
        System.out.println("sid = "+sid);
        Long id = Long.parseLong(sid);
        
        
        try
        {
        	connectionManager.getConnection().startTransaction();

            NodeReference node =   connectionManager.getConnection().createNodeReference(globalName);
            node.setSubscriptCount(0);
            node.appendSubscript(id);

            //System.out.println("try read "+projectId + tableName + id);
            JsonObject oldRecord = Read(projectId, tableName, id);
            //System.out.println("readed = "+oldRecord);
            
            IndexManager manager = new IndexManager(tableName, projectId);
	        manager.OnUpdateRecord(oldRecord, object);
	        //System.out.println("put index data dine ");
	        
	        
            node.set(object.toString(), id, "JSON"); 
            
            //System.out.println("put new data");
        	
            connectionManager.getConnection().commit();
        }
        catch (Exception ex) 
        {
        	connectionManager.getConnection().rollback();
        	throw ex;
        }
        
        return object;

    }
    
    
    public Boolean Delete(Long projectId, String tableName, long id) throws Exception
    {

        String globalName = SchemaManager.GetGlobalDataByTableNameAndProjectId(tableName, projectId);
        
        try
        {
        	connectionManager.getConnection().startTransaction();

            NodeReference node = connectionManager.getConnection().createNodeReference(globalName);

            JsonObject deletedRecord = Read(projectId, tableName, id);
            IndexManager manager = new IndexManager(tableName, projectId);
	        manager.BeforeDeletingRecord(deletedRecord);

            node.setSubscriptCount(0);
            node.appendSubscript(id);
            node.kill();       

        	connectionManager.getConnection().commit();
        }
        catch (Exception ex) 
        {
        	connectionManager.getConnection().rollback();
        	throw ex;
        }
        
    
        return true;
    }
    
    
    

}
