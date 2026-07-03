$(document)
	.ready(
		function() {
			getUserDetails();
			var suppDoc = null;
			$('#customFile')
				.change(
					function(e) {
						var file = e.target.files[0];
						if (file != undefined) {
							var size = Math
								.round((file.size / (1024 * 1024)));
							if (size > 2) {
								swal('Identity document should not be greater than 2 mb');
								$('#supportDocName')
									.html(
										'Upload proof (image/pdf)');
								$('#supportDoc').val('');
							} else {
								suppDoc = file;
								$('#supportDocName').html(
									suppDoc.name);
							}
						} else {
							$('#supportDocName').html(
								'Upload proof (jpg/pdf)');
						}
					});

		
			$("#dob").datetimepicker({
				useCurrent: false,
				minDate: moment().subtract(100, 'years').startOf('day').toDate(),
				maxDate: moment().subtract(1, 'days').toDate(),
				format: 'DD-MM-YYYY'
			});

			$("#dob").on(
				"dp.change",
				function(e) {
					$('#frmRegister').bootstrapValidator(
						'revalidateField', 'dob');
				});

			$('#frmRegister')
				.bootstrapValidator(
					{
						btnSubmit: 'button[type="submit"]',
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
										regexp: /^[9 8 7]\d{9}$/,
										message: 'Mobile number should be of 10 digits only.'
									}
								}
							},
							dob: {
								validators: {
									notEmpty: {
										message: 'dob can\'t be empty'
									},
									date: {
										format: 'DD-MM-YYYY'
									}
								}
							},
							email: {
								validators: {
									notEmpty: {
										message: 'The email address is required and can\'t be empty'
									},
									emailAddress: {
										message: 'The input is not a valid email address'
									}
								}
							},
							/*
							 * officeAddr: { validators: {
							 * notEmpty: { message: 'dob can\'t
							 * be empty' }, } },
							 */
							resAddr: {
								validators: {
									notEmpty: {
                        				message: 'field cannot be empty'
                    				},
                     				regexp: {
					   					regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
					   					message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
									}
								}
							},
							guestGender: {
								validators: {
									notEmpty: {
										message: 'Gender can\'t be empty'
									},
								}
							},
							dept: {
								validators: {
									notEmpty: {
										message: 'Department can\'t be empty'
									},
								}
							},
							docType: {
								validators: {
									notEmpty: {
										message: 'document type is required and can\'t be empty'
									}
								}
							},
						}
					}).on('success.form.bv', function(e) {
						e.preventDefault();
						saveToDraftFAR(suppDoc);
					});
		})

function getUserDetails() {
	$.ajax({
		url: './userProfile.do',
		type: "GET",
		headers: {
			"X-CSRF-TOKEN": token
		},
		success: function(result) {
			$('#userType').val(result.data.userType)
			$('#userId').val(result.data.userId);
			$('#name').val(result.data.firstName);
			$('#mobile').val(result.data.contactNo);
			$('#email').val(result.data.email);
			$('#docType').val(result.data.docType);
			$("#viewBtn").attr("href",
				'./imagedownload/user_' + result.data.userId + '.do');
			/*
			 * $('#officeAddr').val(result.data.officeAddr == null ? '' :
			 * result.data.officeAddr);
			 */
			$('#resAddr').val(
				result.data.resAddr == null ? '' : result.data.resAddr);
			$('#guestGender').val(
				result.data.guestGender == null ? ''
					: result.data.guestGender);
			$('#dept').val(result.data.dept == null ? '' : result.data.dept);
			$('#dob').val(
				result.data.dateOfBirth == null ? ''
					: result.data.dateOfBirth);
			console.log("dob", result.data.dob);
			var suppdocname = null;
			if (result.data.docPath != null) {
				var fileArr = result.data.docPath.split(/(\\|\/)/);
				suppdocname = fileArr[fileArr.length - 1];
			}
			if (suppdocname != null) {
				$("#supportDocName").html(suppdocname);
			}
			if (result.data.docPath == null) {
				$('#viewBtn').hide();
			} else {
				$('#viewBtn').show();
			}
		}
	});
}

var saveToDraftFAR = function(suppDoc) {
	var otpData = $("#otp").val();
	var form = $('#frmRegister')[0];
	// Create an FormData object
	var podata = new FormData(form);
	if (suppDoc != null) {
		podata.append("supportDoc", suppDoc, suppDoc.name)
	}
	;
	$.ajax({
		url: "./updateProfile.do",
		type: "POST",
		enctype: 'multipart/form-data',
		processData: false, // Important!
		contentType: false,
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		data: podata,
		success: function(data, textStatus, jqXHR) {
			$('#loading').hide();
			if (data.status) {
				swal(data.message);
			} else {
				swal(data.message);
			}
		},
		error: function(jqXHR, status, error) {
			if (jqXHR.status == 400) {
				swal(jqXHR.responseJSON.msg);
			} else if (jqXHR.status == 404) {
				swal('Requested page not found. [404]');
			}
			$('#loading').hide();
		}
	});
}