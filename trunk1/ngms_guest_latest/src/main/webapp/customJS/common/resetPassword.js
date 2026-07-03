/*
 * Create Password JS file
 * 
 */


$(document).ready(function () {
   $("#newPassword, #confirmPassword").keyup(checkPasswordMatch);
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");
   $('#hideTimer').show();
   otpTimer(); 
   
   setTimeout(function(){
	   $('#btnResendOtp').prop('disabled', false);
    }, 30*1000);
   
   /*********************************************
    * Resend OTP On button Click
    *********************************************/
   $('#btnResendOtp').on( 'click', function () {
	   $('#loading').show();
	   var id = $('[name="id"]').val();
//       console.log(id);
       $.ajax({
    	   url: "./retryOtpOne.do",
			 type: "POST",
			 dataType: 'json',
			 contentType : 'application/json',
 			 beforeSend: function(xhr) {
 	                xhr.setRequestHeader(header, token);
   			 },
 			 data: JSON.stringify({uid:id}),
 			 success:function(result) {
 				$('#loading').hide();
 				 console.log(result);
 				 if(result.status){
 					 $('#btnResendOtp').prop('disabled', true);
 					 setTimeout(function(){
 						$('#btnResendOtp').prop('disabled', false);
 				    }, 30*1000);
 					$('#hideTimer').show();
 					otpTimer();
 				 }else{
 					alert(result.message);
 				 }
 			 },
 			 error:function(jqXHR, status, error){
 				$('#loading').hide();
 			 	alert(error);
 			 }
 		});
   });
   
});

function otpTimer(){
	   var timeleft = 30;
    var downloadTimer = setInterval(function(){
	    timeleft--;
	    document.getElementById("countdowntimer").textContent = timeleft;
	    if(timeleft <= 0){
	        clearInterval(downloadTimer);
	    	$('#hideTimer').hide();
	    } 
    },1000);
}

function changePwdReset()
{
	//alert($('#newPassword').val())
	var password = $("input[name=newPassword]").val();
	var cnf_password = $("input[name=confirmPassword]").val();
	
	var encSaltSHAPass = sha512Hash(password);
	var encSaltSHACnfPass = sha512Hash(cnf_password);
	
	$("input[name=newPassword]").val(encSaltSHAPass);//changed
	$("input[name=confirmPassword]").val(encSaltSHACnfPass);//changed
	
	console.log(encSaltSHAPass);
	console.log(encSaltSHACnfPass);
	return true;
}
/*function checkPasswordMatch() {
    var password = $("#newPassword").val();
    var confirmPassword = $("#confirmPassword").val();

    $('#text').keyup(function() {
    	  $(this).css('border', this.value.match(/^(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{6,}$/) ? '5px solid green' : '5px solid red');
    	});
    
    if (password != confirmPassword){
    	$("#passwordMatch").html("Passwords do not match!");
    	$("#btnSubmitPassword").attr("disabled","disabled");
    }else{
    	$("#passwordMatch").html("Passwords match.");
    	$("#btnSubmitPassword").removeAttr("disabled");
    }
}
*/
function checkPasswordMatch() {
    var password = $("#newPassword").val();
    var confirmPassword = $("#confirmPassword").val();

    if ((password == confirmPassword) && (this.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/))){
    	$("#passwordMatch").html("Passwords matched.");
    	$("#btnSubmitPassword").removeAttr("disabled");
    }else{
    	$("#passwordMatch").html("Passwords do not match!");
    	$("#btnSubmitPassword").attr("disabled","disabled");
    }
}

function checkform()
{
//	  $('#loading').show();
	  return true;
}