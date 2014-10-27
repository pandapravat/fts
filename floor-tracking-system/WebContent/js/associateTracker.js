$(document).ready(function() {
	var maxCubes=0;
	$(".hz-scroll-inner").children().each(function(index) {
        
		if($(this).children().size() > maxCubes) {
			maxCubes = $(this).children().size(); // need to set the width of inner scroll
		}
        
    });
	$(".hz-scroll-inner").css("width" , (maxCubes)* ($(".squarecube").width() + 10) + "px");

    //On click of the search button

	$("button.cubeBtn, button.multiOcuupied, button.emptyCube").click(function(e) {
		sendSearch($(this).attr("id"), "", "", "", "", "", false, "map");
	});

	$("#backToChooseFloor").click(function() {
		location.replace("./chooseFloor.ats");
	});
	
});


function trace(assetId) {
	
	$("button#" +assetId + "").focus();

	$("button#" +assetId + "").fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100);
}