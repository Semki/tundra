package globalswrapper;

import globalswrapper.DataTypesHelper.FieldType;
import globalswrapper.ConditionType;
import java.util.Date;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FilterCondition {
	public boolean IsNegative;
	public String FieldName;
	public FieldType DataType;
	public String TableName;
	public String CondType;
	public Object FilterValue;

	public static FilterCondition getFromJsonObject(String tableName, JsonObject object)
	{
		FilterCondition result = new FilterCondition();
		//result.ProjectId = projectId;
		result.TableName = tableName;
		result.FieldName = object.get("fieldName").getAsString();
		result.CondType = object.get("conditionType").getAsString();
		result.FilterValue = object.get("filterValue").getAsString();
			
		return result;
	}
	
	
	public Boolean IsValid(JsonObject record)
	{
		JsonElement nodeValue = record.get(FieldName);
		switch (DataType)
		{
			case STRING_TYPE: 
					return ApplyStringFilter(nodeValue.getAsString());
			case DATE_TYPE: Date date = DataTypesHelper.StringToDate(nodeValue.getAsString());
					return ApplyDateFilter(date);
			case BOOLEAN_TYPE: 
					return ApplyBooleanFilter(nodeValue.getAsBoolean());
			default: 
					return true;
		}
		
		
		
	}

	// coming soon
	private Boolean ApplyDateFilter(Date date)
	{
		// 1 > 0 = -1 <
		Date filterValue = DataTypesHelper.StringToDate(FilterValue.toString());
		
		if (CondType.equalsIgnoreCase(ConditionType.GRETATEOREQUAL))
		{
			return (date.compareTo(filterValue) >= 0);
		}
		
		if (CondType.equalsIgnoreCase(ConditionType.GREATER))
		{
			return (date.compareTo(filterValue) > 0);
		}

		if (CondType.equalsIgnoreCase(ConditionType.LESOREQUAL))
		{
			return (date.compareTo(filterValue) >= 0);
		}
		
		if (CondType.equalsIgnoreCase(ConditionType.LESS))
		{
			return (date.compareTo(filterValue) > 0);
		}

		if (CondType.equalsIgnoreCase(ConditionType.EQUAL))
		{
			return (date.compareTo(filterValue) == 0);
		}
		
		return false;
	}
	
	private Boolean ApplyBooleanFilter(Boolean nodeValue)
	{
		Boolean filterValue = Boolean.parseBoolean(FilterValue.toString());
		
		if (CondType.equalsIgnoreCase(ConditionType.EQUAL))
		{
			return nodeValue == filterValue;
		}
		
		if (CondType.equalsIgnoreCase(ConditionType.NOTEQUAL))
		{
			return nodeValue != filterValue;
		}
		
	
		return false;
		
	}
	
	
	private Boolean ApplyStringFilter(String nodeValue)
	{
		if (CondType.equalsIgnoreCase(ConditionType.EQUAL))
		{
			return StringIsEqual(nodeValue);
		}
		
		if (CondType.toString() == ConditionType.CONTAINS)
		{
			return StringContains(nodeValue);
		}
		return false;
		
	}
	
	private Boolean StringContains(String nodeValue)
	{
		return nodeValue.contains(FilterValue.toString()); 
	}
	
	
	private Boolean StringIsEqual(String nodeValue)
	{
		Boolean result = nodeValue.equalsIgnoreCase(FilterValue.toString()); 
 		return  result;
	}
	
}
