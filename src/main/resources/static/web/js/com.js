// JavaScript Document


$(document).ready(function(e) {
	$(".w-sear-item").each(function() {
		var height = $(this).find(".region_m").height()
	    $(this).find(".pic_icon").click(function(){
			$(this).prev(".region_m").toggleClass("on");
		});
		if( height > 33){
			$(this).find(".region_m").addClass("in");
			$(this).find(".pic_icon").show();
		}
		$(this).find(".region_m a:gt(15)").append('<span class="dian"></span>');
    });
	$('.pic_icon').click(function() {
		$(this).toggleClass('in');
	});
	$(".region_m .on").each(function() {
		if($(this).find("span").hasClass("dian")){
			$(this).parent(".region_m").addClass("on");
		}
	});


});


