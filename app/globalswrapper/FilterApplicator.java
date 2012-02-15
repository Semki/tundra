package globalswrapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FilterApplicator {

	
	private String DataType;
	FilterCondition Condition;
	
	
	public FilterApplicator(FilterCondition condition)
	{
		this.DataType = SchemaManager.Instance().GetFieldType(condition.ProjectId, condition.TableName, condition.FieldName);
		this.Condition = condition;
		
	}
	
	public Boolean IsFiltered(JsonObject object)
	{
		return IsValid(object.get(Condition.FieldName));
		
	}
	private Boolean IsValid(JsonElement nodeValue)
	{
		return ApplyTypedFilter(nodeValue);
	}
	
	private Boolean ApplyTypedFilter(JsonElement nodeValue)
	{
		if (DataType == "string")
		{
			return ApplyStringFilter(nodeValue.getAsString());
		}
		
		return true;
		
	}

	private Boolean ApplyStringFilter(String nodeValue)
	{
		if (Condition.CondType.equalsIgnoreCase(ConditionType.EQUAL))
		{
			return StringIsEqual(nodeValue);
		}
		
		if (Condition.CondType.toString() == ConditionType.CONTAINS)
		{
			return StringContains(nodeValue);
		}
		return false;
		
	}
	
	private Boolean StringContains(String nodeValue)
	{
		return nodeValue.contains(Condition.FilterValue.toString()); 
	}
	
	
	private Boolean StringIsEqual(String nodeValue)
	{
		Boolean result = nodeValue.equalsIgnoreCase(Condition.FilterValue.toString()); 
 		return  result;
	}
	
	
}
