/* Auto-generated file. You should not to modify it. */

tundraProjectId = ${project_id};

<#list tables as table>

	${table.table_name} = function(fields) {
	    TActiveRecord.call(this, fields);
	}
	${table.table_name}.tableName = "${table.table_name}";
	    
	${table.table_name}.prototype = new TActiveRecord();
	${table.table_name}.prototype.constructor = ${table.table_name};    
	
	${table.table_name}.getAll = function(successCallback) {
	  TActiveRecord.getAll(this, successCallback);
	}	
	${table.table_name}.open = function(id) {
	    return TActiveRecord.open(this, id);
	}
	${table.table_name}.method = function(methodName, methodBody) {
    	TActiveRecord.method(this, methodName, methodBody);
  	}
  	
  	${table.table_name}.deleteId = function(id, successCallback) {
    	TActiveRecord.deleteId(this, id, successCallback);
   	}
  
  	
</#list>
