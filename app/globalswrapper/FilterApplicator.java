package globalswrapper;

public class FilterApplicator {

	
	private String DataType;
	FilterCondition Condition;
	
	
	public FilterApplicator(FilterCondition condition)
	{
		this.DataType = SchemaManager.Instance().GetFieldType(condition.ProjectId, condition.TableName, condition.FieldName);
		this.Condition = condition;
		
	}
	
	public Boolean IsValid(Object nodeValue)
	{
		return ApplyTypedFilter(nodeValue);
	}
	
	private Boolean ApplyTypedFilter(Object nodeValue)
	{
		if (DataType == "string")
		{
			return ApplyStringFilter(nodeValue.toString());
		}
		
		return true;
		
	}

	private Boolean ApplyStringFilter(String nodeValue)
	{
		if (Condition.CondType.toString() == ConditionType.EQUAL)
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
		return nodeValue.equalsIgnoreCase(Condition.FilterValue.toString()); 
	}
	
	
}
