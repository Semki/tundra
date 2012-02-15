$(document).ready(function () {	 
	
	var project_id = $("#project_id").text();
	var result = {};
	
	var generateScheme = function () {
		result.project_id = project_id;
		result.user_id = "kvitunov";
		result.tables = [];
		
		$.each($('.project_table_wrapper'), function () {
			
			var table = {};
			table.table_name = $('#' + this.id + ' table_name_h3').text();
			table.columns = [];
			$.each($('#' + this.id + ' .project_column_name'), function () {
				var currentColumn = {};
				currentColumn.column_name = this.text();
				currentColumn.type = "string";
				table.columns.push(currentColumn);
			});
			result.tables.push(table);
		});
	};
	
	$.get("/schema/read/" + project_id, function (data) {
		if (data != null) {
			var tablesWrapper = $("#tables_wrapper")
			$.each(data.tables, function () {
				tablesWrapper.append('<div class="project_table_wrapper" id="table_wrapper' + this.table_name + '">');
				tablesWrapper.append('<h3 id="table_' + this.table_name +'" class="table_name_h3">' + this.table_name + '</h3>');
				tablesWrapper.append('<table id="columns_' + this.table_name + '" class="project_table"><thead><tr><th>Column name</th><th>Data type</th><th></th></tr></thead></table>');
				
				var currentTable = $("#columns_" + this.table_name);
				
				$.each(this.columns, function () {
					currentTable.append('<tr><td class="project_column_name">' + this.column_name + '</td><td>' + this.type + '</td><td></td></tr>');
				});
				tablesWrapper.append('</div>');
			});
			
			$("#actions_wrapper").empty();
			$("#actions_wrapper").append('<br/><button class="medium green" id="generate_scheme"><span class="icon"><span aria-hidden="true">F</span></span>Apply changes</button>')
		}
		
	});
});