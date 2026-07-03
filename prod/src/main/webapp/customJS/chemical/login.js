$(document).ready(function() {
	
	$("#Loginmodal").modal(); // login Modal Open
	$(window).on("load", function() {
		$(".modalView").hide();
	});
	/* Form validation */
	$('#frmLogin').bootstrapValidator({
		framework : 'bootstrap',
		row : {
			valid : 'field-success',
			invalid : 'field-error'
		},
		fields : {
			password : {
				validators : {
					notEmpty : {
						message : 'The password can\'t be Empty'
					},
				}
			},
			captcha : {
				validators : {
					notEmpty : {
						message : 'The Captcha can\'t be Empty'
					},
				}
			},
			otp : {
				validators : {
					notEmpty : {
						message : 'otp can\'t be Empty'
					},
				}
			},
			j_username : {
				validators : {
					notEmpty : {
						message : 'User Id is required and cannot be Empty.'
					},

					regexp : {
						regexp : /^[a-zA-Z0-9_@.-]+$/,
						message : 'Username must contain only letters, numbers and special characters like (@,_,.,-)'
					}

				}
			},
			userType : {
				validators : {
					notEmpty : {
						message : 'UserType  is required and cannot be Empty.'
					},

				}
			},
		}
	})
	/* Captcha validation */

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
	
})	

function changeform() {
	var username = $("input[name=j_username]").val();
		var password = $("input[name=password]").val();
		var saltKey = $("input[name=saltKey]").val();
		var encSaltSHAPass = sha512LoginHash(password, saltKey);
		if($("input[name=password]").val()!=""){
			var password=$("input[name=password]").val(encSaltSHAPass);// changed
			var original=$("input[name=j_password]").val(encSaltSHAPass);// changed
		}
		return true;
}
