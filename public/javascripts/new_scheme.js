$(document).ready(function() {
	
	var result = {project_id: "", tables: []};
	var tables = {};
	
	var addTable = function (tableName) {
		result.tables.push({table_name: tableName, columns: []});
		//alert(JSON.stringify(result));
		var id = result.tables.length - 1;
		
		$('#tables_wrapper').append('<div id="' + id + '" class="table_wrapper"><h3 class="table_header">' + tableName + '</h3>' + 
				                    '<table><thead><tr><th>Column name</th><th>Data type</th></tr></thead></table>' +
				                    '<input class="column_name col_3" id="' + id + '" type="text" placeholder="Column name" />' +
				                    '<button class="small add_column" id="' + id + '"><span class="icon"><span aria-hidden="true">+</span></span>Add column</button></div>');
		
		
		
	};
	 
	$('button.add_column').live('click', function () {
		var columnName = $('.column_name#' + this.id).val();
		alert(columnName);
		/*
		if (tableName == "") {
			$('#table_name').addClass("error");
		}
		else {
			$('#table_name').removeClass("error");
			$('#table_name').val('');
			
			addTable(tableName);
		}
		*/
	});
	
	$('button').unbind("click");
	$('#create_table').click(function () {
		var tableName = $('#table_name').val();
		
		if (tableName == "") {
			$('#table_name').addClass("error");
		}
		else {
			$('#table_name').removeClass("error");
			$('#table_name').val('');
			
			addTable(tableName);
		}
	});
});