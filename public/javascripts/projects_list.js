$(document).ready(function() {
	alert("ololo");

	$.get("/project/get_projects_list", function (data) {
		$.each(data, function () {
			$("#projects_list").append("<li><span>" + this.project_name + "</span>" + "<h3>" + this.project_id + "</h3>" + "</li>");
		});
	});
	
});