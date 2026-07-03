$(document)
		.ready(
				function() {
					$(window).on("load", function() {
						$(".modalView").hide();
					});
					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");
					var suppDoc = null;
					$('#supportDoc')
							.change(
									function(e) {
										var file = e.target.files[0];
										if (file != undefined) {
											var size = Math
													.round((file.size / (1024 * 1024)));
											if (size > 2) {
												alert('Identity document should not be greater than 2 mb');
												$('#supportDoc').val('');
											} else {
												/*
												 * suppDoc = file;
												 * $('#supportDocName').html(suppDoc.name);
												 */
											}
										}
									});

					$("#dob").datepicker({
						dateFormat : 'dd-mm-yy',
						changeMonth : true,
						changeYear : true,
						yearRange: '1900 :+nn',
						minDate: new Date(1900, 1 - 1, 1),
						maxDate : -1,
					});
					$('#frmRegister')
							.bootstrapValidator(
									{
										framework : 'bootstrap',
										row : {
											valid : 'field-success',
											invalid : 'field-error'
										},
										fields : {
											name : {
												validators : {
													notEmpty : {
														message : 'name is required and can\'t be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'name must be more than 6 and less than 30 characters long'
													},
													regexp: {
													    regexp: /^[^0-9!@#$%^&*()_+\-=\[\]{}|;':",.<>?]*$/,
													    message: 'Name should not contain numbers and should allow only specified special characters.'
													}
												}
											},
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
														message : 'Mobile number should be of 10 digits only and Start with 6 onwards.'
													}
												}
											},
											email : {
												validators : {
													/*notEmpty : {
														message : 'field can\'t be empty'
													},*/
													regexp : {
														regexp : /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
														message : 'field is not valid'
													}
												}
											},

											dob : {
												validators : {
													notEmpty : {
														message : 'dob can\'t be empty'
													},
													date : {
														format : 'DD-MM-YYYY'
													}
												}
											},
											resAddr : {
												validators : {
													notEmpty: {
                        								message: 'field cannot be empty'
                    								},
                     								regexp: {
					  									regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
					  									message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
													}
												}
											},
											guestGender : {
												validators : {
													notEmpty : {
														message : 'Gender can\'t be empty'
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
											/*
											 * docType: { validators: {
											 * notEmpty: { message: 'document
											 * type is required and can\'t be
											 * empty' } } }, supportDoc: {
											 * validators: { notEmpty: {
											 * message: 'file is required and
											 * can\'t be empty' } } },
											 */
											otp : {
												validators : {
													notEmpty : {
														message : 'otp can\'t be Empty'
													},
												}
											}
										}
									})

					$("#dob").on(
							"change",
							function(e) {
								$('#frmRegister').bootstrapValidator(
										'revalidateField', 'dob');

							});

					$('#getOtpBtn').click(function() {
						var userId = $("#mobile").val();
						postData = {
							userId : $("#mobile").val(),
							email : $("#email").val(),
						}
						if ($('#invalidCheck2').prop("checked") == false) {
							alert("please check the terms and condition");
							return false
						}
						$.ajax({
							url : "./generateOtp.do",
							type : "POST",
							beforeSend : function(xhr) {
								$(".modalView").show();
								xhr.setRequestHeader(header, token);
							},
							data : JSON.stringify(postData),
							contentType : "application/json",
							dataType : "JSON",
							success : function(data) {
								$(".modalView").hide();
								if (data.status) {
									swal(data.message);
									$('#otpEntry').show();
									$('#submitBtn').show();
									$('#getOtpBtn').hide();
									$('#getReOtpBtn').show();
									$('#agree').hide();
									$('#fileData').show();
								} else {
									swal(data.message);
								}
							},
							error : function(jqXHR, status, error) {
								console.log(error);
								swal(error);
							}
						});
					})

					$("#getReOtpBtn").click(function() {
						var userId = $("#mobile").val();
						postData = {
							userId : $("#mobile").val(),
							email : $("#email").val(),
						}
						if (userId == '') {
							alert('mobile number should not be blank');
							return false;
						}
						$.ajax({
							url : "./generateOtp.do",
							type : "POST",
							beforeSend : function(xhr) {
								$(".modalView").show();
								xhr.setRequestHeader(header, token);
							},
							data : JSON.stringify(postData),
							contentType : "application/json",
							dataType : "JSON",
							success : function(data) {
								$(".modalView").hide();
								if (data.status) {
									swal(data.message);
									$('#otpEntry').show();
									$('#submitBtn').show();
									$('#getOtpBtn').hide();
									$('#getReOtpBtn').show();
									$('#agree').hide();
								} else {
									swal(data.message);
								}
							},
							error : function(jqXHR, status, error) {
								console.log(error);
								swal(error);
							}
						});
					})

					/* Captcha validation */

					$('#captchaImgDtls').mousedown(function(e) {
						e.preventDefault();
					});
					var getCaptchaCode = function() {
						$('#captchaImgDtls').attr('src',
								'./captcha/getCaptcha.html?' + Math.random());
					}
					$("#captchaBtnReload").click(function() {
						getCaptchaCode();
					})

					$("#captcha").bind("paste", function(e) {
						e.preventDefault();
					});
					

					let textareas = document.querySelectorAll('.txta'),
					    hiddenDiv = document.createElement('div'),
					    content = null;
					for (let j of textareas) {
					    j.classList.add('txtstuff');
					}
					hiddenDiv.classList.add('txta');
					hiddenDiv.style.display = 'none';
					hiddenDiv.style.whiteSpace = 'pre-wrap';
					hiddenDiv.style.wordWrap = 'break-word';
					for (let i of textareas) {
					    (function (i) {
					        i.addEventListener('input', function () {
					            i.parentNode.appendChild(hiddenDiv);
					            i.style.resize = 'none';
					            i.style.overflow = 'hidden';
					            content = i.value;
					            content = content.replace(/\n/g, '<br>');
					            hiddenDiv.innerHTML = content + '<br style="line-height: 5px;">';

					            hiddenDiv.style.visibility = 'hidden';
					            hiddenDiv.style.display = 'block';
					            i.style.height = hiddenDiv.offsetHeight+ 6 + 'px';
					            hiddenDiv.style.visibility = 'visible';
					            hiddenDiv.style.display = 'none';
					        });
					    })(i);
					}

				})
function changeform() {
	var userId = $("#mobile").val();
	return true;
}
