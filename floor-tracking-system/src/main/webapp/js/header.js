
$(document).ready(function() {
	$("#credits #contents").hide();
	$("#credits #title").click(function() {
		if("none" == $("#contents").css("display")) {
			$("#credits #title").text("- Credits");
		} else {
			$("#credits #title").text("+ Credits");
		}
		$("#contents").slideToggle(1000);
	});

	$("#helpPage #helpcontents").hide();
	$("#message").show(100).fadeOut(400).fadeIn(400)
				.fadeOut(400).fadeIn(400)
				.fadeOut(400).fadeIn(400)
				.fadeOut(400).fadeIn(400).hide(500);
	$("#helpPage #helpimage img").click(function() {
		$("#helpPage #helpcontents").show();
	});

	$("#helpcontents #helptitle img").click(function() {
		$("#helpPage #helpcontents").hide();
	});
	var affectedcube = $("#affectedCube").html();
	 $("#"+affectedcube+"").fadeOut(400).fadeIn(400)
		.fadeOut(500).fadeIn(500)
		.fadeOut(500).fadeIn(500)
		.fadeOut(500).fadeIn(500);
	 $("#faq .question").click(function() {
		if("none" == $(this).next().css("display")) {
			$(this).next().show(); 
			$(this).css("background-position", "0px -16px");
		} else {
			$(this).next().hide();
			$(this).css("background-position", "0px 4px");
		}
	 });
		
});
