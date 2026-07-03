$(function() {
	jQuery('ul.nav li.dropdown').hover(function() {
		jQuery(this).find('.dropdown-menu').stop(true, true).delay(100).fadeIn();
		//  $('.dropdown-toggle').css('background-color','#ec8a4d');
		$( this ).prepend().addClass("backg" );
	}, function() {
		jQuery(this).find('.dropdown-menu').stop(true, true).delay(100).fadeOut();
		$('.dropdown-toggle').css('background-color','auto');
		$( this ).prepend().removeClass("backg" );
	});
});

function replacezero(textboxid)
{
	if(document.getElementById(textboxid).value==0 || document.getElementById(textboxid).value==0.0 || document.getElementById(textboxid).value==0.00)
		document.getElementById(textboxid).value="";
}
function placezero(textboxid)
{

	if(document.getElementById(textboxid).value=="")
		document.getElementById(textboxid).value=0;

}

var digitsOnly = /[1234567890]/g;

function restrictInput(myfield, e, restrictionType, checkdot) {
	var mobilepattern = /^[6-9]{1}[0-9]{9}$/;
	var mobile = $('#mobile').val();
	console.log(mobile);
	var valid=mobilepattern.test(mobile);
	console.log(valid);
	/*if (!e)
				var e = window.event
			if (e.keyCode)
				code = e.keyCode;
			else if (e.which)
				code = e.which;
			var character = String.fromCharCode(code);

			// if user pressed esc... remove focus from field...
			if (code == 27) {
				this.blur();
				return false;
			}
			if ((code == 46)) {
		        var strvalue = myfield.value;
		        if (strvalue.indexOf(".") > 0){
		            return false;}
		    }*/
	if (event.keyCode == 8) {
		// let it happen, don't do anything
	}
	else {
		// Ensure that it is a number and stop the keypress
		if (event.keyCode < 48 || event.keyCode > 57) {
			nic_alert("Character Not Permitted in this field. Enter Number.","V")
			return false;
		}
	}
	/*if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && 
					code != 38 &&(code != 39 || (code == 39 && character == "'"))
					&& code != 40 && code != 46) {
				if (character.match(restrictionType)) {
					if (checkdot == "checkdot") {
						return !isNaN(myfield.value.toString() + character);
					} else {
						return true;
					}
				} else {nic_alert("Character Not Permitted in this field. Enter Number.","V");
					return false;
				}
			}*/

}

var validateEmail = function(elementValue) {
	var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	
	return emailPattern.test(elementValue);
}

function restrictInputForEmail() {
	/*if (event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 64 || event.keyCode == 95 || event.keyCode == 45) {
				// let it happen, don't do anything
			}else if(event.keyCode >= 48 && event.keyCode <= 57){

			}else if(event.keyCode >= 65 && event.keyCode <= 90){

			}else if(event.keyCode >= 97 && event.keyCode <= 122){
				// Ensure that it is a number and stop the keypress
			}else{
				nic_alert("Please enter valid email","V")
				return false;
			}*/

	var value = $('#email').val();
	var valid = validateEmail(value);
	if (!valid) {
		$('#email').css('color', 'red');
		$('#smalll').show();
	} else {
		$('#email').css('color', '#000');
		$('#smalll').hide();
	}
}

function restrictInputForNormal(text) {
	if (event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 32 || event.keyCode == 45) {
		// let it happen, don't do anything
		//alert("1");
//	}else if(event.keyCode >= 182 && event.keyCode <= 222){
//		alert("2");
	}else if(event.keyCode >= 65 && event.keyCode <= 90){
		//alert("3");
	}else if(event.keyCode >= 97 && event.keyCode <= 122){
		// Ensure that it is a number and stop the keypress
		//alert("4");
	}else{
		//alert("5");
		nic_alert("Please enter valid " + text ,"V")
		return false;
	}
}
function restrictInputForNormal1(text) {
	if (event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 32 || event.keyCode == 45) {
		// let it happen, don't do anything
		alert("1");
	
	}else if(event.keyCode >= 48 && event.keyCode <= 90){
		alert("33");
	}else if(event.keyCode >= 97 && event.keyCode <= 221){
		// Ensure that it is a number and stop the keypress
		alert("4");
	}else if(event.keyCode >= 200 && event.keyCode <= 222){
		alert("2");
	}else{
		alert("5");
		nic_alert("Please enter valid " + text ,"V")
		return false;
	}
}
function restrictInputForNormalPlace(text) {
	if (event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 32 || event.keyCode == 45) {
		// let it happen, don't do anything
	}else if(event.keyCode >= 65 && event.keyCode <= 90){

	}else if(event.keyCode >= 48 && event.keyCode <= 57){
		// Ensure that it is a number and stop the keypress
	}else if(event.keyCode >= 97 && event.keyCode <= 122){
		// Ensure that it is a number and stop the keypress
	}else{
		nic_alert("Please enter valid " + text ,"V")
		return false;
	}
}

/*Common Alert NIC_ALERT Code - Anzarriyaz . Pls dont change*/		

function stopAlert() {
	$("#messagefg").css("display", "none");
	$("#alertcontainer").css("display", "none");
}

function nic_alert(str, options) {
	if(options =='V'){
		Valert(str); 
		return false;
	}
	else if(options =='N'){
		Nalert(str); 
		return false;
	}
	else if(options =='C'){
		Calert(str); 
	}
}

function main_alert(str, options) {
	htmldata = str+"<p class='calertbuttons'><input type='button' class='btn btn-primary' value='OK' id='YesButton'></p>";
	$("#alertcontainer").css("display", "block");
	$("#messagefg div div").html(htmldata);
	$("#messagefg").css("display", "block");

	$("#YesButton").one("mouseup", function() {
		stopAlert();
		return true;
	});

}


function nic_confirm(str) {

	htmldata = str+"<p class='calertbuttons'><input type='button'  class='btn btn-primary' value='Yes' id='YesButton'> <input type='button' class='btn btn-danger' value='No' id='NoButton'></p>";

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
	main_alert("<div class='heading error'><span class='alerts error'></span><h3><b>Message</b></h3></div><p>"+ message+ "</p>", "OK");

}

function Nalert(message){
	main_alert("<div class='heading notification'><span class='alerts notification'></span><h3><b>Message</b></h3></div><p>"+ message+ "</p>", "OK");

}

function Calert(message) {
	ngm_confirm(message);
};

function go(function_name,page)
{
	var page_n= document.getElementById("pageno").value;
	if(page_n<=page && page_n>0)
	{
		function_name(page_n) ;
	}
	else
	{
		nic_alert("Invalid Page Number","V");
	}
}

/*Common Alert NIC_ALERT Code - Anzarriyaz . Pls dont change*/	