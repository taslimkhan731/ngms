$(document).ready(function(){	
	$('#modifyGuestDtlsModal').modal('show');
	var userId = $("userId").val();
	console.log("dfdf_______" +mobile);
	
	/*var suppdocname = null;
		if(result.data.docPath!=null){
			var fileArr = result.data.docPath.split(/(\\|\/)/);
			suppdocname = fileArr[fileArr.length -1];
		}
		if(suppdocname!=null){$("#supportDocName").html(suppdocname);}
	if(result.data.docPath == null){
		$('#viewBtn').hide();
	}else{
		$('#viewBtn').show();
	}*/
	 /*$('.modal-content').find('.close').on('click',function(){
		   $(this).parents('#adminRegistrationModal').hide();
		  });*/
	 
	 /*$('#close').on('click',function(){
		 alert("inside button")
		 //$('#adminRegistrationModal').modal('hide');
		 $('#adminRegistrationModal').hide();
	 });*/
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var suppDoc = null;
	$('#supportDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math.round((file.size / (1024 * 1024)));
			if (size > 2) {
				swal('Identity document should not be greater than 2 mb');
				$('#supportDoc').html('Upload proof (image/pdf)');
				$('#supportDoc').val('');
			} else {
				suppDoc = file;
				$('#supportDoc').html(suppDoc.name);
			}
		} else {
			$('#supportDoc').html('Upload proof (jpg/pdf)');
		}
	});
	
	$("#dob").datepicker({
		dateFormat : 'dd-mm-yy',
		changeMonth : true,
		changeYear : true,
		yearRange: '1753 :+nn',
		minDate: new Date(1753, 1 - 1, 1), 
		maxDate : new Date(),
	});

	$("#dob").on(
			"change",
			function(e) {
				$('#adminFrmRegister').bootstrapValidator(
						'revalidateField', 'dob');

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
	
	$('#adminFrmRegister').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields : {
			    
			dob: {
				validators: {
					notEmpty: {
						message: 'dob can\'t be empty'
					},
					date : {
						format : 'DD-MM-YYYY'
					}
				}
			},
			officeAddr: {
				validators: {
					notEmpty: {
						message: 'Office Address can\'t be empty'
					},
				}
			},
			resAddr: {
				validators: {
					notEmpty: {
						message: 'Residence Address can\'t be empty'
					},
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'Please enter a password .'
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'Password must contain atleast one uppercase, one number and atleast one special character like (@,_,.,-)'
					},
				}
			},
			cpassword: {
				validators: {
					notEmpty: {
						message: 'Please enter a Confirm password '
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'password must contain only letters, numbers and special characters like (@,_,.,-)'
					},
					identical: {
						field: 'password',
						message: 'password and its Confirm Password are not  same'
					}
				}
			}, 
			
			docType: {
				validators: {
					notEmpty: {
						message: 'Please enter Document Type .'
					}
					
				}
			},
			supportDoc : {
				validators : {
					notEmpty : {
						message : 'Please select Document .'
					}

				}
			},
			guestGender : {
				validators : {
					notEmpty : {
						message : 'Please enter Gender .'
					}

				}
			}
			
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitadmindetails();
    });
	
	//$("#adminFrmRegisterr").click(
	
var submitadmindetails = function() {
		$("#password").val(sha512Hash($("#password").val().trim()));
		$("#cpassword").val(sha512Hash($("#cpassword").val().trim()));
		var form = $('#adminFrmRegister')[0];
		var podata = new FormData(form);
		if (suppDoc != null ) {		
			podata.append("supportDoc", suppDoc, suppDoc.name);
		}
		;
		console.log("podata________________________"+podata);
		$.ajax({
			url : './guestUpload.do',
			type : "POST",
			data : podata,
			enctype : 'multipart/form-data',
			processData : false, // Important!
			contentType : false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data, textStatus, jqXHR) {
				if (data.status) {					
					swal({
						title : "Success!",
						text : data.message,
						type : "success"
					}, function() {
						window.location = "./buildSession.html";
					});
				} else {
					swal(data.message);
					$("#password").val('');
					$("#cpassword").val('');
				}
			},
			error : function(jqXHR, status, error) {
				swal(error);
			}
		});
	}	
if (suppdocname != null) {
    $("#supportDocName").html(suppdocname);
}
if (suppdocname != null) {
    $("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + suppdocname + '</a>');
}
/*$("#viewBtn").attr("href", './imagedownload/user_' + userModel.userId + '.do');*/
});

