$(document).ready(function () {	 
	
	var project_id = $("#project_id").text();
	
	$.get("/schema/read/" + project_id, function (data) {
		if (data != null) {
			var tablesWrapper = $("#tables_wrapper")
			$.each(data.tables, function () {
				tablesWrapper.append('<h3 id="table_' + this.table_name +'">' + this.table_name + '</h3>');
				tablesWrapper.append('<table id="columns_' + this.table_name + '"><thead><tr><th>Column name</th><th>Data type</th><th></th></tr></thead></table>');
				
				var currentTable = $("#columns_" + this.table_name);
				
				$.each(this.columns, function () {
					currentTable.append('<tr><td>' + this.column_name + '</td><td>' + this.type + '</td><td></td></tr>');
				});
			});
			
			$("#actions_wrapper").empty();
			$("#actions_wrapper").append('<br/><button class="medium green" id="generate_scheme"><span class="icon"><span aria-hidden="true">F</span></span>Generate scheme</button>')
		}
		
	});
});