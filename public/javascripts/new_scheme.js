$(document).ready(function() {
	
	var result = {project_id: "", user_id: "kvitunov", tables: []};
	var tables = {};
	var tablesCount = 0;
	
	var addTable = function (tableName) {
		result.tables.push({table_name: tableName, columns: []});
		//alert(JSON.stringify(result));
		var id = result.tables.length - 1;
		
		$('#tables_wrapper').append('<div id="' + id + '" class="table_wrapper"><h3 class="table_header" id="table_name'+id+'">' + tableName + '</h3>' + 
				                    '<table id="table_body' + id + '"><thead><tr><th>Column name</th><th>Data type</th><th></th></tr></thead></table>' +
				                    '<input id="column_name_tb' + id + '" class="col_3" id="' + id + '" type="text" placeholder="Column name" />' +
				                    '<button class="small add_column" id="' + id + '"><span class="icon"><span aria-hidden="true">+</span></span>Add column</button></div>');
		
		
		
		$("#actions_wrapper").empty();
		$("#actions_wrapper").append('<br/><button class="medium green" id="generate_scheme"><span class="icon"><span aria-hidden="true">F</span></span>Generate scheme</button>')
	};
	
	$('#generate_scheme').live('click', function () {
		result.project_id = $('#project_id_container').text();
		for (var i = 0; i < tablesCount; ++i) {
			$.each($(".column_name"+i), function() {
				result.tables[i].columns.push({column_name: this.textContent, type: "string"});
			});
		}
		
		//alert(JSON.stringify(result));
		$.post('/schema/create', JSON.stringify(result), function (data) {
			//alert(data);
		}, "json");
	});
	 
	$('button.add_column').live('click', function () {
		var tableId = this.id;
		var columnNameInput = $('#column_name_tb' + this.id);
		var columnName = columnNameInput.val();
		
		if (columnName == "") {
			columnNameInput.addClass("error");
		}
		else {
			columnNameInput.removeClass("error");
			columnNameInput.val('');
			
			addColumnToTable(tableId, columnName);
		}

	});
	
	var addColumnToTable = function (tableId, columnName) {
		$("#table_body" + tableId).append('<tr><td class="column_name'+tableId+'">' + columnName + '</td><td class="column_datatype'+tableId+'">String</td><td><span class="icon gray small"><span aria-hidden="true">x</span></span></td></tr>');
	};
	
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
			++tablesCount;
		}
	});
});