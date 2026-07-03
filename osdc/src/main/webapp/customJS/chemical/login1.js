/**
 * 
 */
$(document).ready(function() {
	
	$('#captcha-dtls').hide();
	getCaptchaCode();
	var getCaptchaCode= function() {
		$('#captchaImgDtls').attr('src','../../captcha/getCaptcha.html?'+Math.random());
		$('#captchaImgDtls').hide();
	}
	
})