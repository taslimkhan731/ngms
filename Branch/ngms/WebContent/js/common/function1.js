$(function() {
	jQuery('ul.nav li.dropdown').hover(function() {
		  jQuery(this).find('.dropdown-menu').stop(true, true).delay(100).fadeIn();
		}, function() {
		  jQuery(this).find('.dropdown-menu').stop(true, true).delay(100).fadeOut();
		});

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
$(function() {
    $(window).bind("load resize", function() {
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.sidebar-collapse').addClass('collapse')
        } else {
            $('div.sidebar-collapse').removeClass('collapse')
        }
    })
})



function stopAlert() {
	$("#messagefg").css("display", "none");
	$("#alertcontainer").css("display", "none");
}
//if(main)'yn')
//{
//	}
//else
//	{}

function main_alert(str, options) {

	if (options=="YN") htmldata = str+"<p class='calertbuttons'><input type='button'  class='btn btn-primary' value='Yes' id='YesButton'> <input type='button' class='btn btn-danger' value='No' id='NoButton'></p>";
	else if (options=="OK") htmldata = str+"<p class='calertbuttons'><input type='button' class='btn btn-primary' value='OK' id='YesButton'></p>";

	$("#alertcontainer").css("display", "block");
	$("#messagefg div div").html(htmldata);
	$("#messagefg").css("display", "block");

	$("#YesButton").one("mouseup", function() {
		stopAlert();
		return true;
	});

	$("#NoButton").one("mouseup", function() {
		stopAlert();
		return false;
	});
}

function Valert(message) {
	main_alert("<span class='alerts error'></span><h1>Validation Alert</h1><p>"+ message+ "</p>", "OK");
	return false;
}

function Nalert(message)
{
	main_alert("<span class='alerts notification'></span> <h1> Notification</h1><p>"+ message+ "</p>", "OK");
	return false;
	}
function Calert(message,callback) {
	main_alert("<span class='alerts confirmation'></span><h1> Confirmation</h1><p>"+message+"</p>", "YN");
};
