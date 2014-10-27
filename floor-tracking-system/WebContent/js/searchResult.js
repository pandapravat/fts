$("button#addNewEmp").click(function() {
		
		var emptyCubeId = $(this).parent("div").attr("id");
		alert(emptyCubeId);
});

$("#exportSearch").click(function() {
	var tableData =  getGridData();;
	$("#exportdata").val(tableData);
	$("#exportForm").submit();
});
$("#submitUpdateForm").click(function() {
	$("#updateForm").submit();
});

$("#anotherSrchBtn").click(function(){
	showSearch();
});

function getGridData() {
	var xml = "<table><headers>";
	$("table.tablesorter").find("thead tr th").each(function(index) {
		if(0 != index && 1 != index) { // not the first two columns
			xml += "<header>";
			var aHeader = $(this).text();
			xml += aHeader;
			xml += "</header>";
		}
	});
	xml += "</headers>";
	xml += "<datas>";
	$("table.tablesorter").find("tbody tr").each(function() {
		xml += "<aRowData>";
		$(this).find("td").each(function(index) {
			if(0 != index && 1 != index) { 
				xml += "<aCell>";
					
				var adata = $(this).text();
				adata = adata.replace("&", "&amp;");
				xml += adata;
				xml += "</aCell>";
			}
		});
		xml += "</aRowData>";
	});
	xml += "</datas>";
	xml += "</table>";
	return xml;
};
