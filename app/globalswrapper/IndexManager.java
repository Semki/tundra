package globalswrapper;

import java.lang.reflect.Field;
import java.util.Date;

import com.google.gson.JsonObject;
import com.intersys.globals.NodeReference;


public class IndexManager {
	
	JsonObject TableInfo;
	Long ProjectId;
	
	public IndexManager(JsonObject _tableInfo, Long projectId)
	{
		TableInfo = _tableInfo;
		this.ProjectId = projectId;
	}

	public void OnCreateRecord(JsonObject newRecord)
	{
		NodeReference node = GetNode();
		
		String fieldName = "";
		String fieldValue = "";
		Long Id = newRecord.get("Id").getAsLong();
		
		
		SetIndexValue(node, Id, fieldName, fieldValue);
		
		
		
		node.close();
	}
	
	public void OnUpdateRecord(JsonObject oldRecord, JsonObject newRecord)
	{
		NodeReference node = GetNode();
		
		
				
		
		node.close();
		
	}
	
	public void OnDeleteRecord(Long Id)
	{
		NodeReference node = GetNode();
		node.close();
	}
	
	public NodeReference GetNode()
	{
		String globalName = GetIndexGlobalName();
		NodeReference node = ConnectionManager.Instance().getConnection().createNodeReference(globalName);
		return node;
	}
	
	public String GetIndexGlobalName()
	{
		return "";
	}
	
	public void SetIndexValue(NodeReference indexNode, Long objectId, String indexFieldName, String object)
	{
		indexNode.set(object, objectId, indexFieldName);
		
	}
	
	public void KillIndexValue(NodeReference indexNode, Long objectId, String indexFieldName, JsonObject object)
	{
		indexNode.setSubscriptCount(0);
		indexNode.appendSubscript(indexFieldName);
		///
		/// to do - перебор до ключа
		///indexNode.appendSubscript();
		
		
	}
	/*
    private void UpdateIndexForField(Persistent obj, Field field, Persistent oldObj) 
    		throws IllegalAccessException
    {
    	if (field.getName() == "Id")
    		return;
    	
    	Index indexAnnotation = field.getAnnotation(Index.class);
    	
    	if (indexAnnotation == null)
    		return;
    	
    	String indexName = indexAnnotation.IndexName();
    	
    	NodeReference node = null;
        try
        {
            String globalName = obj.GetIndexGlobalName();
            node = connection.createNodeReference(globalName);
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
        }
        
        if (oldObj != null)
        {
        	Object oldVal = field.get(oldObj);
        	if (oldVal != null)
        	{
        		Object val = ConvertValueForIndexing(oldVal);
	        	if (node.exists(indexName, val, oldObj.Id))
	        		node.kill(indexName, val, oldObj.Id);
        	}
	    }
        
        if (obj != null)
        {
        	Object newVal = field.get(obj);
        	if (newVal != null)
        	{
        		Object val = ConvertValueForIndexing(newVal);
        		if (!node.exists(indexName, val, obj.Id))
        			node.set("", indexName, val, obj.Id);
        	}
        }
    }
    
    public static Object ConvertValueForIndexing(Object value)
    {
    	if (value instanceof String)
    	{
    		//System.out.println(">"+value.toString().toUpperCase()+"<");
    		
    		String str = " ".concat(value.toString().toUpperCase());
    		//System.out.println(">"+str+"<");
    		if (str.length() > 500)
    			return str.substring(0, 500);
    		return str;
    	}
    	else if (value instanceof Date)
    	{
    		Date val = (Date) value;
    		return " ".concat(DateHelper.DateToString(val));
    	}
    	else
    	{
    		return value;
    	}
    }
    
    
    
    public void UpdateIndicesOnUpdate(Persistent oldObj, Persistent obj)
    {
    	
    }
    
    public void UpdateIndicesOnDelete(Persistent obj) throws IllegalArgumentException, IllegalAccessException
    {
    	String globalName = obj.GetIndexGlobalName();
        NodeReference node = connection.createNodeReference(globalName);
        
    	Field[] fields = obj.getClass().getFields();
    	for (int i =0; i< fields.length; i++)
    	{
    		Field field = fields[i];
    		Index annotation = field.getAnnotation(Index.class);
    		if (annotation != null)
    			node.kill(annotation.IndexName(), ConvertValueForIndexing(field.get(obj).toString()), obj.Id);
    	}
    }*/
}
