$(document).ready(function(){
	$(window).on("load", function() {
		$(".modalView").hide();
	});
	$('#hideTimer').show();
   	otpTimer(); 
	   
    setTimeout(function(){
	    $('#btnResendOtp').prop('disabled', false);
    }, 60*1000);

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
	}
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	/*$('#submitBtn').hide();
	$('#otpScreen').hide();
	$('#CPasswordDet').hide();
	$('#PasswordDet').hide();
	$('#getOtpBtn').show();
	$('#getReOtpBtn').hide();*/
	$('#frmFgPass').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields: {
			mobile: {
				validators: {
					notEmpty: {
						message: 'mobile is required and can\'t be empty'
					},
					digits: {
						message: 'Only digits are allowed in mobile number.'
					},
					regexp: {
						regexp: /^[9 8 7]\d{9}$/,
						message: 'Mobile number should be of 10 digits only.'
					}
				}

			},
			password: {
				validators: {
					notEmpty: {
						message: 'password is required and can\'t be empty'
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'password must contain only letters, numbers and special characters like (@,_,.,-)'
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
			otp: {
				validators: {
					notEmpty: {
						message: 'otp is required and can\'t be empty'
					}
				}
			}
		}
	}).on('success.form.bv', function (e) {
//		e.preventDefault();
//		changeform();
	});

	/*$("#getOtpBtn").click(function(){
		var userId = $("#mobile").val();
		postData = {
				userId : $("#mobile").val(),
		}
		if(userId == ''){
			alert('mobile number should not be blank');
			return false;
		}
		$.ajax({
			url : "./forgetPassword.do",
			type : "POST",
			headers : {
				"X-CSRF-TOKEN" : token
			},
			beforeSend: function(xhr) {
				$(".modalView").show();
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType:"application/json",
			dataType:"JSON",
			success: function(data) {
				$(".modalView").hide();
				if (data.status) {
					swal(data.message);
					$('#submitBtn').show();
					$('#otpScreen').show();
					$('#getOtpBtn').hide();
					$('#getReOtpBtn').show();
					$('#CPasswordDet').show();
					$('#PasswordDet').show();
				}else{
					swal(data.message);
				}
			},
			error:function(jqXHR, status, error){
				console.log(error);
				swal(error);
			}
		});
	})
*/
	 $('#btnResendOtp').on( 'click', function () {
		  $.LoadingOverlay("show");

			let postData = {
				"uid": $('#userId').val()
				
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
	

/*	var saveToDraftFAR = function(){
		console.log('Submitting the form details');
		var userId = $("#mobile").val();
		postData = {
				userId : $("#mobile").val(),
				password: sha512Hash($("#password").val().trim()),
				cpassword: sha512Hash($("#cpassword").val().trim()),
				otp: $("#otp").val(),
		}
		if(userId == ''){
			alert('mobile number should not be blank');
			return false;
		}
		$.ajax({
			url : "./validateOtp.do",
			type : "POST",
			beforeSend: function(xhr) {
				$(".modalView").show();
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType:"application/json",
			dataType:"JSON",
			success: function(data) {
				if (data.status) {
					$.ajax({
						url : "./savePassword.do",
						type : "POST",
						beforeSend: function(xhr) {
							xhr.setRequestHeader(header, token);
						},
						data: JSON.stringify(postData),
						contentType:"application/json",
						dataType:"JSON",
						success: function(data) {
							$(".modalView").hide();
							if (data.status) {
								alert(data.message);
								$('#frmFgPass')[0].reset()
								window.location="login.jsp";
							}else{
								alert(data.message);
							}
						},
						error:function(jqXHR, status, error){
							console.log(error);
							alert(error);
						}
					});
				}else{
					alert(data.message);
				}
			},
			error:function(jqXHR, status, error){
				console.log(error);
				alert(error);
			}
		});
	}*/
})
function changeform() {
	$("#password").val(sha512Hash($("#password").val().trim()));
	$("#cpassword").val(sha512Hash($("#cpassword").val().trim()));
	$.LoadingOverlay("show");
	$.LoadingOverlay("hide");
	return true;
	
}