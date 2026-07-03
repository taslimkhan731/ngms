$(document).ready(function() {
	 var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$('#qrCodeValidationKt')
		.bootstrapValidator(
				{
					btnSubmit : 'button[type="submit"]',
					fields : {
						mobile : {
							validators : {
								notEmpty : {
									message : 'mobile is required and can\'t be empty'
								},
								digits : {
									message : 'Only digits are allowed in mobile number.'
								},
								regexp : {
									regexp : /^[9 8 7 6]\d{9}$/,
									message : 'Mobile number should be of 10 digits only.'
								}
							}

						},
						name : {
							validators : {
								notEmpty : {
									message : 'Name can\'t be empty'
								},
							}

						},
						reqId : {
							validators : {
								notEmpty : {
									message : 'Booking ID is required!'
								},

							}

						},
						otp : {
							validators : {
								notEmpty : {
									message : 'OTP is required!'
								},

							}

						},
					}
				})
				
				
	$(window).on("load", function() {
		$(".modalView").hide();
	});

	$('#captchaImgDtls').mousedown(function(e) {
		e.preventDefault();
	});
	var getCaptchaCode= function() {
		$('#captchaImgDtls').attr('src','./captcha/getCaptcha.html?'+Math.random());
	}
	$("#captchaBtnReload").click(function(){
		getCaptchaCode();
	})

	$("#captcha").bind("paste", function(e) {
		e.preventDefault();
	});   
   

//	var getDataToGadRequestTable = function() {
//		 var city=$('#city').val();
//		 var gHouse = $('#gHouse').val();
//		  console.log(city);
//		  console.log(gHouse);
//		$.ajax({
//			url : './allDataListforQr.do',
//			type : "GET",
//			contentType : "application/json",
//			dataType : "JSON",
//			data:{
//		        cityValue:city,
//		        guesthouseValue:gHouse
//		    },
//		
//			success : function(result) {
//				console.log(result);
//				//table.clear();
//				//table.rows.add(result.data).draw();
//			},
//			/*error : function(jqXHR, status, error) {
//				swal(error);
//			}*/
//		});
//
//	}
//
//	getDataToGadRequestTable ();
	
	
	$('#city').on('change', function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
	});

	var getGuestHouseListDtls = function(id){
		$.ajax({
			url: "./getGuestHouseListDetailsforQr/" + id + ".do",
			type: "GET",
			contentType:"application/json",
		
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$('#loading').hide();
				$("#gHouse").html("");
				$("#gHouse").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					$("#gHouse").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			},
		});
	}
	var getCityListDtls = function(){
		$.ajax({
			url: "./getCityDetailsforQr.do",
			type: "GET",
			contentType:"application/json",
						success: function(data, textStatus, jqXHR) {
				console.log(data);
				$("#city").html("");
				$("#city").append('<option value="">Select City</option>');
				data.forEach(option=>{
					$("#city").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			}, 
		});
	}
	getCityListDtls ();
	
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
	 				    }, 120*1000);
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

	

	
})	

function changeform1() {
	
    $.LoadingOverlay("show");
    $.LoadingOverlay("hide");
	return true;
}	
	




	
