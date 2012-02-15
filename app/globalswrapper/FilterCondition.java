package globalswrapper;

import com.google.gson.JsonObject;

public class FilterCondition {
	public FilterCondition AND;
	public FilterCondition OR;
	public boolean IsNegative;
	
	public String FieldName;
	public String TableName;
	public Long ProjectId;
	
	public String CondType;
	public Object FilterValue;

	public static FilterCondition getFromJsonObject(Long projectId, String tableName, JsonObject object)
	{
		FilterCondition result = new FilterCondition();
		result.ProjectId = projectId;
		result.TableName = tableName;
		result.FieldName = object.get("fieldName").getAsString();
		result.CondType = object.get("conditionType").getAsString();
		result.FilterValue = object.get("filterValue").getAsString();
			
		return result;
	}
}
