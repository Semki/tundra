$(document).ready(function() {

	$.get("/project/get_projects_list", function (data) {
		if (data.elements.length != 0) {
			$.each(data.elements, function () {
				$("#projects_list").append("<li><span>" + this.project_name + "</span>" + "<h3>" + this.project_id + "</h3>" + "</li>");
			});
		}
		else {
			$("#projects_list").append("<h2>No projects created yet...</h2>");
		}
	});
	
});