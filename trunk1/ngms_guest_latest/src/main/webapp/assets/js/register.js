$(document).ready(function(){
	$(window).on("load", function() {
		$(".modalView").hide();
	});
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
//	$("#dob").datetimepicker({
//		useCurrent: true,
//		minDate: moment(),
//		format: 'DD-MM-YYYY'
//	});
	/*$('#otpEntry').hide();
	$('#submitBtn').hide();
	$('#getOtpBtn').show();
	$('#getReOtpBtn').hide();
	$('#fileData').hide();
	$('#agree').show();*/
	var suppDoc = null;
	$('#supportDoc').change(function(e){
		var file = e.target.files[0];
        if(file!=undefined){
        	var size = Math.round((file.size / (1024 * 1024)));
        	if(size > 2){
        		alert('Identity document should not be greater than 2 mb');
        		$('#supportDoc').val('');
        	}else{
        		/*suppDoc = file;
            	$('#supportDocName').html(suppDoc.name);*/
        	}
        } 
	});
	$('#captchaImgDtls').mousedown(function(e) {
		e.preventDefault();
	});
	var getCaptchaCode= function() {
		$('#captchaImgDtls').attr('src','./captcha/getCaptcha.html?'+Math.random());
	}
	$("#captchaBtnReload").click(function(){
//		alert(10);
		getCaptchaCode();
	})

	$("#captcha").bind("paste", function(e) {
		e.preventDefault();
	});
	$('#frmRegister').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: 'name is required and can\'t be empty'
					},
					stringLength: {
						min: 6,
						max: 30,
						message: 'name must be more than 6 and less than 30 characters long'
					},
				}

			},
			mobile: {
				validators: {
					notEmpty: {
						message: 'mobile is required and can\'t be empty'
					},
					digits: {
						message: 'Only digits are allowed in mobile number.'
					},
					regexp: {
						regexp: /^[9 8 7 6]\d{9}$/,
						message: 'Mobile number should be of 10 digits only.'
					}
				}
			},
			email: {
				validators: {
					notEmpty: {
						message: 'The email address is required and can\'t be empty'
					/* },
					emailAddress: {
						message: 'The input is not a valid email address' */
					},regexp: {
						regexp:/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
						message: 'field is not valid'
					}
				}
			},
			dob: {
				validators: {
					notEmpty: {
						message: 'dob can\'t be empty'
					},
				}
			},
			captcha: {
				validators: {
					notEmpty: {
						message: 'captcha can\'t be empty'
					},
				}
			},
			/*officeAddr: {
				validators: {
					notEmpty: {
						message: 'dob can\'t be empty'
					},
				}
			},
			resAddr: {
				validators: {
					notEmpty: {
						message: 'dob can\'t be empty'
					},
				}
			},*/
			guestGender: {
				validators: {
					notEmpty: {
						message: 'Gender can\'t be empty'
					},
				}
			},
			/*dept: {
				validators: {
					notEmpty: {
						message: 'dob can\'t be empty'
					},
				}
			},*/
			
			docType: {
				validators: {
					notEmpty: {
						message: 'document type is required and can\'t be empty'
					}
				}
			},
			supportDoc: {
				validators: {
					notEmpty: {
						message: 'file is required and can\'t be empty'
					}
				}
			},otp : {
				validators : {
					notEmpty : {
						message : 'otp can\'t be Empty'
					},
				}
			}
		}
	});
//	.on('success.form.bv', function (e) {
//		e.preventDefault();
//		//saveToDraftFAR(suppDoc);
//	});
	
	$('#getOtpBtn').click(function(){
		var userId = $("#mobile").val();
		postData = {
				userId : $("#mobile").val(),
				email: $("#email").val(),
		}
		if(userId == ''){
			alert('mobile number should not be blank');
			return false;
		}
        if($('#invalidCheck2').prop("checked") == false){
            alert("please check the terms and condition");
            return false
        }
		$.ajax({
			url : "./generateOtp.do",
			type : "POST",
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
					$('#otpEntry').show();
					$('#submitBtn').show();
					$('#getOtpBtn').hide();
					$('#getReOtpBtn').show();
					$('#agree').hide();
					$('#fileData').show();
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
	
	$("#getReOtpBtn").click(function(){
		var userId = $("#mobile").val();
		postData = {
				userId : $("#mobile").val(),
				email: $("#email").val(),
		}
		if(userId == ''){
			alert('mobile number should not be blank');
			return false;
		}
		$.ajax({
			url : "./generateOtp.do",
			type : "POST",
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
					$('#otpEntry').show();
					$('#submitBtn').show();
					$('#getOtpBtn').hide();
					$('#getReOtpBtn').show();
					$('#agree').hide();
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
	
	/*var saveToDraftFAR = function(suppDoc) {
		console.log('Registration Form submit');
		var otpData = $("#otp").val();
		$("#password").val(sha512Hash($("#password").val().trim()));
		$("#cpassword").val(sha512Hash($("#cpassword").val().trim()))
		
		var form = $('#frmRegister')[0];
		// Create an FormData object 
        var podata = new FormData(form);
        if(suppDoc!=null){podata.append("suppDoc", suppDoc, suppDoc.name)};
        
		postData = {
				userId : $("#mobile").val(),
				otp: otpData,
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
				$(".modalView").hide();
				if (data.status) {
					$.ajax({
						url : "./saveGuestDetails.do",
						type : "POST",
						enctype: 'multipart/form-data',
				        processData: false,  // Important!
				        contentType: false,
						beforeSend: function(xhr) {
							$(".modalView").show();
							xhr.setRequestHeader(header, token);
						},
						data: podata,
						success: function(data) {
							$(".modalView").hide();
							if (data.status) {
								alert(data.message);
								$('#frmRegister')[0].reset()
								window.location="login.jsp";
							}else{
								alert(data.message);
								$("#password").val('');
								$("#cpassword").val('')
								$('#otpEntry').show();
								$('#submitBtn').show();
								$('#getOtpBtn').hide();
								$('#getReOtpBtn').show();
								$('#fileData').show();
								$('#agree').hide();
							}
						},
						error:function(jqXHR, status, error){
							console.log(error);
							alert(error);
						}
					});
				}else{
					alert(data.message);
					$("#password").val('');
					$("#cpassword").val('')
					$('#otpEntry').show();
					$('#submitBtn').show();
					$('#getOtpBtn').hide();
					$('#getReOtpBtn').show();
					$('#agree').hide();
					$('#fileData').show();
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
	var userId = $("#mobile").val();
//	if(userId == ''){
//		alert('mobile number should not be blank');
//		return false;
//	}
	if($('#invalidCheck2').prop("checked") == false){
        swal("please check the terms and condition");
        return false
    }
	var dobbb = $("#dob").val();
	if(dobbb != ''){
	var now = new Date;
	var target = new Date(dobbb);
	if (target.getFullYear() > now.getFullYear()) {
		swal("Please change the DOB beacause Don't allow Future Date Of Year");
		return false;
	} else if (target.getFullYear() == now.getFullYear()) {
		if (target.getMonth() > now.getMonth()) {
			swal("Please change the DOB beacause Don't allow Future Date of Month");
			return fasle;
		} else if (target.getMonth() == now.getMonth()) {
			if (target.getDate() >= now.getDate()) {
				swal("Please change the DOB beacause Don't allow Future Date");
				return false;
			} else {
				return true
			}
		}
	} else {
		return true;
	}
	}
    
    $.LoadingOverlay("show");
    $.LoadingOverlay("hide");
	return true;
}
 
