$(document).ready(function(){
	getUserDetails();
	var suppDoc = null;
	$('#customFile').change(function(e){
		var file = e.target.files[0];
        if(file!=undefined){
        	var size = Math.round((file.size / (1024 * 1024)));
        	if(size > 2){
        		swal('Identity document should not be greater than 2 mb');
        		$('#supportDocName').html('Upload proof (image/pdf)');
        		$('#supportDoc').val('');
        	}else{
        		suppDoc = file;
            	$('#supportDocName').html(suppDoc.name);
        	}
        } else {
        	$('#supportDocName').html('Upload proof (image/pdf)');
        }
    });
	
	$('#frmRegister').bootstrapValidator({
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
			officeAddr: {
				validators: {
					notEmpty: {
						message: 'officeAddr can\'t be empty'
					},
				}
			},
			resAddr: {
				validators: {
					notEmpty: {
						message: 'resAddr can\'t be empty'
					},
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
						message: 'Dept can\'t be empty'
					},
				}
			},
		dob: {
			validators: {
				notEmpty: {
					message: 'dob can\'t be empty'
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
	}).on('success.form.bv', function (e) {
		e.preventDefault();
		saveToDraftFAR(suppDoc);
	});
})

function getUserDetails(){
	$.ajax({
		url: './userProfile.do',
		type: "GET",	
		headers : {"X-CSRF-TOKEN" : token},		 
		success:function(result) {
			$('#userId').val(result.data.userId);
			$('#name').val(result.data.firstName);
			$('#mobile').val(result.data.contactNo);
			$('#email').val(result.data.email);
			$('#docType').val(result.data.docType);
			$("#viewBtn").attr("href", './imagedownload/user_' + result.data.userId + '.do');
			$('#officeAddr').val(result.data.officeAddr == null ? '' : result.data.officeAddr);
			$('#resAddr').val(result.data.resAddr == null ? '' : result.data.resAddr);
			$('#guestGender').val(result.data.guestGender == null ? '' : result.data.guestGender);
			$('#dept').val(result.data.dept == null ? '' : result.data.dept);
			$('#dob').val(result.data.dob == null ? '' : result.data.dob);
			var suppdocname = null;
   			if(result.data.docPath!=null){
   				var fileArr = result.data.docPath.split(/(\\|\/)/);
   				suppdocname = fileArr[fileArr.length -1];
   			}
   			if(suppdocname!=null){$("#supportDocName").html(suppdocname);}
			if(result.data.docPath == null){
				$('#viewBtn').hide();
			}else{
				$('#viewBtn').show();
			}
		}
	});
}

var saveToDraftFAR = function(suppDoc) {
	
	console.log('Registration Form submit');
	var otpData = $("#otp").val();
	var form = $('#frmRegister')[0];
	// Create an FormData object 
    var podata = new FormData(form);
    if(suppDoc!=null){podata.append("suppDoc", suppDoc, suppDoc.name)};
	$.ajax({
		url : "./updateProfile.do",
		type : "POST",
		enctype: 'multipart/form-data',
        processData: false,  // Important!
        contentType: false,
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		data: podata,
		success: function(data, textStatus, jqXHR) {
			$('#loading').hide();
			if (data.status) {
				$('#frmRegister')[0].reset()
				swal({
					title : "Success!",
					text : data.message,
					type : "success"
				}, function() {
					window.location="guest/guestDashboard.html";
				});
			}else{
				swal(data.message);
			}
		},
		error:function(jqXHR, status, error){
			if (jqXHR.status == 400) {
				swal(jqXHR.responseJSON.msg);
		           /*swal('Not connect.\n Verify Network.');*/
		        } else if (jqXHR.status == 404) {
		        	swal('Requested page not found. [404]');
		        } 
				console.log(jqXHR);
				/*swal(jqXHR.responseText);*/
				$('#loading').hide();
		}
	});
}