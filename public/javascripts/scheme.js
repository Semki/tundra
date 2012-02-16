$(document).ready(function () {
	
	var project_id = $('#project_id').text();
	
	var is_creating = (project_id == "0");

	// Gets scheme from server, then parse it
	var getScheme = function () {
		if (project_id != "") {
			$.get("/schema/read/" + project_id, function (data) {
				parseScheme(data);
			});
		} else {
			// do nothing
		}
	};
	
	// Generate scheme from elements
	var generateScheme = function () {
		var scheme = {};
		if (project_id == "0") {
			scheme.project_id = "";
		} else {
			scheme.project_id = project_id;
		}
		scheme.user_id = "kvitunov";
		scheme.tables = [];
		
		$.each($('.table_wrapper'), function () {
			var table = {};
			table.table_name = $(this).children("h3").text();
			table.columns = [];
			
			$.each($('.table_column_name_' + table.table_name), function () {
				var column = {};
				column.column_name = $(this).text();
				column.type = "string";
				table.columns.push(column);
			});
			
			scheme.tables.push(table);
		});
		
		return scheme;
	};
	
	// Parse scheme and create elements
	var parseScheme = function(scheme) {
		project_id = scheme.project_id;
		
		$.each(scheme.tables, function () {
			addTable(this);
		});
	};
	
	// Refreshes actions buttons
	var refreshActions = function () {
		$("#actions_wrapper").empty();
		
		
		var button_html = '<br/>';
		button_html += '<input id="table_name" type="text" class="col_3" placeholder="Table name" />';
		button_html += '<button id="create_table" class="medium blue"><span class="icon"><span aria-hidden="true">+</span></span>Create table</button>';
		button_html += '<button class="medium green" id="generate_scheme"><span class="icon"><span aria-hidden="true">F</span></span>';
		if (is_creating == true) {
			button_html += 'Generate scheme</button>';
		} else {
			button_html += 'Modify scheme</button>';
		}
		
		$("#actions_wrapper").append(button_html);
	};
	
	// Create table button click handler
	$('#create_table').live('click', function () {
		var table = {};
		table.table_name = $('#table_name').val();
		if (table.table_name == "") {
			$('#table_name').addClass("error");
		} else {
			$('#table_name').removeClass("error");
			$('#table_name').val('');
			table.columns = [];
			
			addTable(table);
		}
	});
	
	// Add column button click handler
	$('button.add_column').live('click', function () {
		var table_name = this.id.replace('add_column_', '');
		var columnNameInput = $('#column_name_tb_' + table_name);
		var columnName = columnNameInput.val();
		
		if (columnName == "") {
			columnNameInput.addClass("error");
		}
		else {
			columnNameInput.removeClass("error");
			columnNameInput.val('');
			
			var column = {};
			column.column_name = columnName;
			column.type = "string";
			
			addColumn(table_name, column);
		}
	});
	
	// Remove column click handler
	$('.remove_column').live('click', function () {
		$(this).parent().parent().remove();
	});
	
	// Remove table click handler
	$('.remove_table').live('click', function () {
		$(this).parent().remove();
	});
	
	// Action button click handler
	$('#generate_scheme').live('click', function () {
		$.post('/schema/create', JSON.stringify(generateScheme()), function (data) {
			// do nothing
		}, "json");
		
		location.reload(true);
	});
	
	// Add table
	var addTable = function(table) {
		var table_name = table.table_name;
		var table_html = "";
		
		table_html += '<div class="table_wrapper" id="table_wrapper_' + table_name +'"><br />';
		table_html += '<h3 class="table_header">' + table_name + '</h3>';
		table_html += '<span class="icon gray remove_table"><span aria-hidden="true">X</span></span>';
		table_html += '<table class="table_table" id="table_table_' + table_name + '"><thead>';
		table_html += '<tr><th>Column name</th><th>Data type</th><th></th></tr>';
		table_html += '</thead></table>';
		table_html += '<input id="column_name_tb_' + table_name + '" class="col_3 column_name_tb" type="text" placeholder="Column name"></input>';
		table_html += '<button class="small add_column" id="add_column_' + table_name + '"><span class="icon"><span aria-hidden="true">+</span></span>Add column</button>';
		table_html += '</div>';
		
		$("#tables_wrapper").append(table_html);
		
		if (table.columns.length != 0) {
			$.each(table.columns, function () {
				addColumn(table_name, this);
			});
		}
	};
	
	// Appends column to table
	var addColumn = function(table_name, column) {
		var column_name = column.column_name;
		var column_datatype = column.type;
		var column_html = '<tr>';
		column_html += '<td class="table_column_name_' + table_name + '">' + column_name + '</td><td>' + column_datatype + '</td>';
		column_html += '<td><span class="icon gray remove_column"><span aria-hidden="true">X</span></span></td>';
		column_html += '</tr>';
		
		$('#table_table_' + table_name).append(column_html);
	};
		
	
	if (is_creating == true) {
		
	} else {
		getScheme();
	}
	refreshActions();
});