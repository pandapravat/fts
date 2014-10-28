$(document).ready(function() {
	$("#srchBtn").click(function() {
		shifts = "";
		if(true == $("#srchShiftA").attr('checked')) {
			shifts += "A";
		}
		if(true == $("#srchShiftB").attr('checked')) {
			shifts += "B";
		}
		if(true == $("#srchShiftC").attr('checked')) {
			shifts += "C";
		}
		if(true == $("#srchShiftD").attr('checked')) {
			shifts += "D";
		}
		sendSearch($("#srchncubeno").val(), 
				$("#srchmachineno").val(), 
				$("#srchname").val(), 
				$("#srchempid").val(),
				$("#srchproject").val(),
				shifts, 
				$("#srchdetailview").attr("checked")
				);
		
	});
	$("#clrSrch").click(function() {
		$("#srchncubeno").val('');
		$("#srchmachineno").val('');
		$("#srchname").val('');
		$("#srchempid").val('');
		$("#srchproject").val('');
		$("#srchproject").val('');
		$("#srchShiftA").attr('checked', false);
		$("#srchShiftB").attr('checked', false);
		$("#srchShiftC").attr('checked', false);
		$("#srchShiftD").attr('checked', false);
		$("#srchdetailview").attr('checked', false);
		$("#searchresults").hide();
	});
});
function showSearch(){
	$("#buttonsPanel").hide();
	$("#searchresults").hide();
	$("#searchpane").show("1000");
}
function sendSearch(cubeNo, machineNo, name, employeeId, project, shift, viewdetail, source) {
	var params = "";
	if(!validate(cubeNo, machineNo, name, employeeId, project, shift, viewdetail, source)) return false;
	if("" != cubeNo) {
		params += ("&cubeId=" + cubeNo);
	}
	if("" != machineNo) {
		params += ("&assetId=" + machineNo);
	}
	if("" != name) {
		params += ("&name=" + name);
	}
	if("" != employeeId) {
		params += ("&empid=" + employeeId);
	}
	if("" != project) {
		params += ("&projName=" + project);
	}
	if("" != shift) {
		params += ("&shift=" + shift);
	}
	if("" == params) {
		alert("please provide atleast one search parameter");
		return false;
	}
	if(true == viewdetail) {
		params += ("&viewdetail=" + true);
	}
	if(source) {
		params += ("&source=" + source);
	}
	
	$("#searchpane").hide("1000");
	$("#searchresults").show("1000");
	var loading = '<div style="width:10%; margin:auto; margin-top:30px; margin-bottom:30px;">' +	
				'<img src="../images/loading.gif" title="loading.." alt="loading Data"  ></div>';
	$("#searchresults").html(loading);
	$.ajax({
		url:"./searchresults.ats", 
		data:params.substring(1),
		cache:false, 
		success:function(result){
			$("#buttonsPanel").show();
			//alert(result);
	    	$("#searchresults").html(result);
	    	//Enable sorting on tables
	    	$("table.tablesorter").tablesorter({ 
	            // pass the headers argument and assing a object 
	            headers: { 
	                // assign the secound column (we start counting zero) 
	                0: { sorter: false}, 
	                1: { sorter: false}, 
	                //2: { sorter: true}, 
	               // 3: { sorter: true}, 
	                4: { sorter: false}, 
	                5: { sorter: false}, 
	                //6: { sorter: true}, 
	                7: { sorter: false}, 
	                8: { sorter: false}, 
	                //09: { sorter: true}, 
	                10: { sorter: false}, 
	                11: { sorter: false}, 
	                12: { sorter: false}, 
	                13: { sorter: false}, 
	                14: { sorter: false}, 
	                15: { sorter: false}, 
	                16: { sorter: false}, 
	                17: { sorter: false}, 
	                18: { sorter: false}, 
	                19: { sorter: false}, 
	                20: { sorter: false}, 
	                21: { sorter: false}, 
	                22: { sorter: false}, 
	                23: { sorter: false}, 
	                24: { sorter: false}, 
	                25: { sorter: false}, 
	                26: {sorter: false} 
	            } 
	        }); 
		},
		error: function(jqXHR, textStatus, errorThrown) {
			var errorMsg = "An error occurred while processing the request. <div onclick='showSearch()' class='backSearch'>Please try again.</div>";
			$("#searchresults").html(errorMsg);
		}});

}

function validate(cubeNo, machineNo, name, employeeId, project) {
	if(!IsNumeric(employeeId)) {
		alert("Employee id should be numeric");
		return false;
	} 
	return true;
}
function IsNumeric(sText)

{
   var ValidChars = "0123456789";
   var IsNumber=true;
   var Char;

 	var i;
   for(i = 0; i < sText.length && IsNumber == true; i++) 
      { 
      Char = sText.charAt(i); 
      if (ValidChars.indexOf(Char) == -1) 
         {
         IsNumber = false;
         }
      }
   return IsNumber;
   
   }