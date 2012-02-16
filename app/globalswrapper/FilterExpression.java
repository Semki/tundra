package globalswrapper;

import java.util.ArrayList;

import com.google.gson.JsonObject;
// Набор условий для фильтрации
public class FilterExpression {
	
	public ArrayList<FilterCondition> conditions;
	
	public FilterExpression(ArrayList<FilterCondition> conditions, Long projectId)
	{
		if (conditions != null)
		{
			this.conditions = conditions;
			FillDataTypesInfo(projectId);
		}
		
		
		
	}
	
	
	private void FillDataTypesInfo(Long projectId)
	{
		if (conditions == null)
			return;
		for (int i=0;i<conditions.size(); i++)
		{
			FilterCondition condition = conditions.get(i);
			condition.DataType = SchemaManager.Instance().GetFieldTypeAsEnum(projectId, condition.TableName, condition.FieldName);
		}
		
	}
	
	public Boolean IsValid(JsonObject record)
	{
		if (conditions == null)
			return true;

		Boolean conditionIsValid;
		for (int i=0;i<conditions.size(); i++)
		{
			FilterCondition condition = conditions.get(i);

			conditionIsValid = condition.IsValid(record);
			if (condition.IsNegative)
				conditionIsValid = !conditionIsValid;
		
			if (!conditionIsValid)
			{
				return false;
			}
		}
		
		return true;
		
	}
	
	
	
	
}