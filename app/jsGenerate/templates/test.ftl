/* auto-generated file */

tundraProjectId = ${project_id};

<#list tables as table>

	${table.table_name} = function(fields) {
	    TActiveRecord.call(this, fields);
	}   
	    
	${table.table_name}.prototype = new TActiveRecord();
	${table.table_name}.prototype.constructor = ${table.table_name};    
	
	${table.table_name}.open = function(id) {
	    return TActiveRecord.open(this, id);
	}
</#list>
