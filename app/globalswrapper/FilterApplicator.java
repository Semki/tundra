package globalswrapper;

import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FilterApplicator {

	
	private String DataType;
	FilterCondition Condition;
	Long projectId;
	String tableName;
	
	public FilterApplicator(Long projectId, String tableName, FilterCondition condition)
	{
		if (condition != null)
		{
			this.DataType = SchemaManager.Instance().GetFieldType(projectId, tableName, condition.FieldName);
		}
		this.projectId = projectId;
		this.tableName = tableName;
		this.Condition = condition;
		
	}
	
	public Boolean IsFiltered(JsonObject object)
	{
		if (Condition == null)
		{
			return true;
		}
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
		
		if (DataType == "date")
		{
			Date date = DataTypesHelper.StringToDate(nodeValue.getAsString());
			return ApplyDateFilter(date);
		}
		
		if (DataType == DataTypesHelper.FieldType.BOOLEAN_TYPE.getTypeValue())
		{
			Boolean value = nodeValue.getAsBoolean();
			return ApplyBooleanFilter(value);
		}
		
		return true;
		
	}

	// coming soon
	private Boolean ApplyDateFilter(Date date)
	{
		// 1 > 0 = -1 <
		Date filterValue = DataTypesHelper.StringToDate(Condition.FilterValue.toString());
		
		if (Condition.CondType.equalsIgnoreCase(ConditionType.GRETATEOREQUAL))
		{
			return (date.compareTo(filterValue) >= 0);
		}
		
		if (Condition.CondType.equalsIgnoreCase(ConditionType.GREATER))
		{
			return (date.compareTo(filterValue) > 0);
		}

		if (Condition.CondType.equalsIgnoreCase(ConditionType.LESOREQUAL))
		{
			return (date.compareTo(filterValue) >= 0);
		}
		
		if (Condition.CondType.equalsIgnoreCase(ConditionType.LESS))
		{
			return (date.compareTo(filterValue) > 0);
		}

		if (Condition.CondType.equalsIgnoreCase(ConditionType.EQUAL))
		{
			return (date.compareTo(filterValue) == 0);
		}
		
		return false;
	}
	
	private Boolean ApplyBooleanFilter(Boolean nodeValue)
	{
		Boolean filterValue = Boolean.parseBoolean(Condition.FilterValue.toString());
		
		if (Condition.CondType.equalsIgnoreCase(ConditionType.EQUAL))
		{
			return nodeValue == filterValue;
		}
		
		if (Condition.CondType.equalsIgnoreCase(ConditionType.NOTEQUAL))
		{
			return nodeValue != filterValue;
		}
		
	
		return false;
		
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
