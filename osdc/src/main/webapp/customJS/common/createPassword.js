/**
 * 
 */

/*	
$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$('#myModalInstruction').modal();
	$("#new_psw, #con_psw").keyup(checkPasswordMatch);
	
	  $('#hideTimer').show();
	   otpTimer(); 
	   
	   setTimeout(function(){
		   $('#btnResendOtp').prop('disabled', false);
	    }, 60*1000);
	   
	   *//*********************************************
	    * Resend OTP On button Click
	    *********************************************//*
	   $('#btnResendOtp').on( 'click', function () {
		   $('#loading').show();
//	       var id = $('[name="id"]').val();
//	       console.log(id);
	       $.ajax({
	 			 url: "./retryOtp.do",
	 			 type: "POST",
	 			 dataType: 'json',
	 			 contentType : 'application/json',
	 			 beforeSend: function(xhr) {
	 	                xhr.setRequestHeader(header, token);
	   			 },
	   			 data: JSON.stringify({uid:''}),
	 			 success:function(result) {
	 				$('#loading').hide();
	 				 console.log(result);
	 				 if(result.status){
	 					 $('#btnResendOtp').prop('disabled', true);
	 					 setTimeout(function(){
	 						$('#btnResendOtp').prop('disabled', false);
	 				    }, 60*1000);
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
$(window).load(function() {
     		$('#loading').hide();
     		
  		});
     	//Disable cut copy paste
        $('body').bind('cut copy paste', function (e) {
            e.preventDefault();
        });
       
        //Disable mouse right click
        $("body").on("contextmenu",function(e){
        	alert("Right click is disabled.");
            return false;
        });
   
        
function checkPasswordMatch() {
    var password = $("#new_psw").val();
    var confirmPassword = $("#con_psw").val();
    console.log((password != confirmPassword))
    console.log(!this.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/))
    if ((password == confirmPassword) && (this.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/))){
    	$("#passwordMatch").html("Passwords matched.");
    	$("#btnSubmitPassword").removeAttr("disabled");
    }else{
    	$("#passwordMatch").html("Passwords do not match!");
    	$("#btnSubmitPassword").attr("disabled","disabled");
    }
}

function changePwdToSha()
{
	var password = $("#new_psw").val();
	var cnf_password = $("#con_psw").val();
	
	var encSaltSHAPass = sha512Hash(password);
	var encSaltSHACnfPass = sha512Hash(cnf_password);
	
	$("#new_psw").val(encSaltSHAPass);//changed
	$("#con_psw").val(encSaltSHACnfPass);//changed
	
	console.log(encSaltSHAPass);
	console.log(encSaltSHACnfPass);
	return true;
}


function otpTimer(){
	   var timeleft = 60;
var downloadTimer = setInterval(function(){
	    timeleft--;
	    document.getElementById("countdowntimer").textContent = timeleft;
	    if(timeleft <= 0){
	        clearInterval(downloadTimer);
	    	$('#hideTimer').hide();
	    } 
},1000);
}*/

$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$('#hideTimer').show();
   	otpTimer(); 
	   
    setTimeout(function(){
	    $('#btnResendOtp').prop('disabled', false);
    }, 120*1000);

	function otpTimer(){
	   var timeleft = 120;
		var downloadTimer = setInterval(function(){
	    timeleft--;
	    document.getElementById("countdowntimer").textContent = timeleft;
	    if(timeleft <= 0){
	        clearInterval(downloadTimer);
	    	$('#hideTimer').hide();
	    } 
		},1000);
	}
$('#frmregister').bootstrapValidator({
		framework : 'bootstrap',
		row : {
			valid : 'field-success',
			invalid : 'field-error'
		},
		fields: {
			password: {
				validators: {
					notEmpty: {
						message: 'password can\'t be empty'
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'password must contain atleast 8 charecters including letters, numbers, special characters like (@,_,.,-)'
					},
					identical: {
						field: 'confirmPassword',
						message: 'The password and its confirm are not the same'
					}
				}
			},
			cpassword: {
				validators: {
					notEmpty: {
						message: 'confirm password is required and can\'t be empty'
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'password must contain only letters, numbers and special characters like (@,_,.,-)'
					},
					identical: {
						field: 'password',
						message: 'password and its confirm are not the same'
					}
				}
			},
		otp : {
				validators : {
					notEmpty : {
						message : 'otp can\'t be Empty'
					},
				}
			}
		}
	})
	 $('#btnResendOtp').on( 'click', function () {
		  $.LoadingOverlay("show");

			let postData = {
				"uid": $('#userId').val(),
				"emailId": $('#email').val(),
				"mobileNo": $('#mobile').val()
			}

	       $.ajax({
	 			 url: "./retryOtp.do",
	 			 type: "POST",
	 			 dataType: 'json',
	 			 contentType : 'application/json',
	 			 beforeSend: function(xhr) {
	 	                xhr.setRequestHeader(header, token);
	   			 },
	   			 data: JSON.stringify(postData),
	 			 success:function(result) {
	 				$.LoadingOverlay("hide");
	 				 console.log(result);
					 swal(result.message);
	 				 if(result.status){
	 					 $('#btnResendOtp').prop('disabled', true);
	 					 setTimeout(function(){
	 						$('#btnResendOtp').prop('disabled', false);
	 				    }, 60*1000);
	 					$('#hideTimer').show();
	 					otpTimer();
	 				 }else{
	 					 alert(result.message);
	 				 }
	 			 },
	 			 error:function(error){
	 				$.LoadingOverlay("hide");
	 			 	alert(error);
	 			 }
	 		});
	   });
	
	//add view button
	$(".togglePassword").click(function () {
            var fieldId = $(this).closest(".form-group").find("input").attr("id");
            var field = $("#" + fieldId);
            var fieldType = field.attr("type");

            if (fieldType === "password") {
                field.attr("type", "text"); // Show the password
                $(this).find("i").removeClass("fa-eye").addClass("fa-eye-slash");
            } else {
                field.attr("type", "password"); // Hide the password
                $(this).find("i").removeClass("fa-eye-slash").addClass("fa-eye");
            }
        });
	
});
function changeform() {	
	if($("#password").val()!=''&&$("#cpassword").val()!=''&&$("#otp").val()!=''){
	$("#password").val(sha512Hash($("#password").val().trim()));
	$("#cpassword").val(sha512Hash($("#cpassword").val().trim()));
	$.LoadingOverlay("show");
	$.LoadingOverlay("hide");
	return true;
	}
}