$(document).ready(function () {
	
	$(window).scroll(function(){
		  var sticky = $('.miniHeader'),
		      scroll = $(window).scrollTop();

		  if (scroll >= 100) sticky.addClass('stickToTop');
		  else sticky.removeClass('stickToTop');
		});
});

function leftScroll(s) {
	const left = document.querySelector('.' + s);
	left.scrollBy(310, 0);
}
function rightScroll(s) {
	const right = document.querySelector("." + s);
	right.scrollBy(-310, 0);
}

