$(document).ready(function() {

	$.get("/project/get_projects_list", function (data) {
		if (data.length != 0) {
			$.each(data, function () {
				$("#projects_list").append(						
						'<li id="project' + this.project_id + '">' +
						'<button class="small create_scheme_button"><span class="icon"><span aria-hidden="true">f</span></span>create scheme</button>' +
						'<span>' + this.project_name + '</span>'
						+"</li>");
			});
		}
		else {
			$("#projects_list").append("<h2>No projects created yet...</h2>");
		}
	});
	
});