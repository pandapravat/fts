var loadingHtml = '<div style="width:10%; margin:auto; margin-top:30px; margin-bottom:30px;">' +	
					'<img src="../images/loading.gif" title="loading.." alt="loading Data"  ></div>';

/*
 * Showing the add update form
 */
function showUpdate(slNo) {
	showAddUpdate("updateFormHolder", "./update.ats", "recordId=" + slNo +"&action=update");
}
function showAdd(slNo) {
	showAddUpdate("addFormHolder", "./add.ats", "cubeId=" + slNo +"&action=add");
}

$("#updateFormHolder #title .closeView").click(function() {
	close("updateFormHolder");
});

$("#addFormHolder #title .closeView").click(function() {
	close("addFormHolder");
});

function close(formHolder){
	$("#" + formHolder).fadeOut(500);
	$(".backgroundHider").fadeOut(500);
}

/*
 * Submitting the add update form.
 */
$(document).ready(function() {
	
	$("#submitAdd").click(function(event) {
		processAddUpdate(event, "addFormHolder", "./processAdd.ats", 'addForm');
	});
	
	$("#submitUpdate").click(function(event) {
		processAddUpdate(event, "updateFormHolder", "./processUpdate.ats", 'updateForm');
	});
	
	$("#submitRemove").click(function(event) {
		if(confirm("Are you sure to deallocate the employee from the cube?")) {
			processAddUpdate(event, "updateFormHolder", "./processDelete.ats", 'deleteForm');
		}
	});
	
});
function showAddUpdate(formHolder, formAction, formData) {
	$("#" + formHolder).fadeIn(500);
	$(".backgroundHider").fadeIn(500);
	
	$("#" + formHolder+" #contents").html(loadingHtml);
	$.ajax({
		url:formAction, 
		data:formData,
		cache:false, 
		success:function(result){
			//alert(result);
	    	$("#"+ formHolder +" #contents").html(result);
	    	//Enable sorting on tables
	    	
		},
		error: function(jqXHR, textStatus, errorThrown) {
			var errorMsg = "An error occurred while processing the request. ";
			$("#"+ formHolder +" #contents").html(errorMsg);
		}});
}
function processAddUpdate(event, formHolder, formAction, formId) {
	event.preventDefault();
	var formData = $("#" + formId).serialize();
	$("#" + formHolder+" #contents").html(loadingHtml);
	
	$.ajax({
		url:formAction, 
		data:formData,
		cache:false, 
		success:function(result){
			$("#" + formHolder+" #contents").html(result);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			var errorMsg = "An error occurred while processing the request. ";
			$("#" + formHolder + " #contents").html(errorMsg);
		}});
}
