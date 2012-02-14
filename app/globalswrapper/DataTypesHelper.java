package globalswrapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.JsonObject;

public class DataTypesHelper {
	
	public static String STRING_TYPE = "string";
	public static String DATE_TYPE = "date";
	public static String INT_TYPE = "integer";
	public static String NUMBER_TYPE = "number";
	public static String BOOLEAN_TYPE = "boolean";
	
	public static String GetStringFromJSONObject(String fieldName, JsonObject object)
	{
		if (object.has(fieldName))
		{
			return object.get(fieldName).getAsString();
		}
		
		return "";
	}
	
	public static Boolean GetBooleanFromJSONObject(String fieldName, JsonObject object)
	{
		if (object.has(fieldName))
		{
			return object.get(fieldName).getAsBoolean();
		}
		
		return false;
	}
	
	public static int GetIntFromJSONObject(String fieldName, JsonObject object)
	{
		if (object.has(fieldName))
		{
			return object.get(fieldName).getAsInt();
		}
		
		return 0;
	}
	
	public static Date GetDateFromJSONObject(String fieldName, JsonObject object)
	{
		if (object.has(fieldName))
		{
			return StringToDate(object.get(fieldName).getAsString());
		}
		
		return null;
	}
	
	private static String DateFormatString = "yyyy-MM-dd HH:mm:ss";
	
	public static String DateToString(Date dateValue)
	{
		if (dateValue == null)
			return "";
    	DateFormat df = new SimpleDateFormat(DateFormatString);
    	df.setTimeZone(TimeZone.getTimeZone("Asia/Irkutsk"));
    	return df.format(dateValue);
	}
	
	public static Date StringToDate(String strValue)
	{
		DateFormat df = new SimpleDateFormat(DateFormatString);
	  	Date dateValue = null;
	  	try 
	  	{
	  		dateValue = df.parse(strValue.toString());
	  	} 
	  	catch (ParseException e) 
	  	{
	  		e.printStackTrace();
	  	}
	  	
	  	return dateValue;
	  	
	}
	


}
