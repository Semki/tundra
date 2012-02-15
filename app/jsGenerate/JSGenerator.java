package jsGenerate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.corba.se.spi.activation.Server;


import freemarker.template.*;


public class JSGenerator {
	
	
	public String Generate(JsonObject schema, String projectId) throws IOException, TemplateException
	{
		Configuration cfg = new Configuration();	
		cfg.setDirectoryForTemplateLoading(new File(System.getProperty("application.path"), "/app/jsGenerate/templates"));	
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		Template template = cfg.getTemplate("test.ftl");
		Map<String, Object> model = convertJsonToModel(schema);
		
		model.put("project_id", projectId);
		
		/* Merge data-model with template */
		
		String path = System.getProperty("application.path");
		String fileName =  "/public/js/models/models"+projectId+".js";
		
		Writer out = new FileWriter(new File(path, fileName));		
	    template.process(model, out);
	    out.flush();

	    return fileName;
			
	}
	
	private Map<String,Object> convertJsonToModel(JsonObject schema)
	{
		Map<String,Object> model = new HashMap<String,Object>();
		Map<String,Object> tableModel;
		List<Object> tables = new ArrayList<Object>();
		
		JsonArray jsonTables = schema.getAsJsonArray("tables");
		JsonObject jsonTable;
		
		for (int i=0; i < jsonTables.size(); i++)
		{
			jsonTable = jsonTables.get(i).getAsJsonObject();
			
			tableModel = new HashMap<String,Object>();	
			tableModel.put("table_name", jsonTable.get("table_name").getAsString());
			tables.add(tableModel);
		}
		
		model.put("tables", tables);
		return model;
	}

}
