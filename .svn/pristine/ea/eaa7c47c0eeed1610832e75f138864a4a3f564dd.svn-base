$('document').ready(function(){
	
	$("#changePasswordModal").on("hidden.bs.modal", function(){
		 $("#formPassword")[0].reset();
		 var validator = $( "#formPassword" ).validate();
		 validator.resetForm();
	});
	$.validator.addMethod('passwordValidate', function (value, element) {
		return value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/);
	});
	$('#formPassword').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		fields: {
			oldpsw: {
				validators: {
					notEmpty: {
						message: 'Please enter your old password.'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9_@.-]+$/ ,
						message: 'password must contain only letters, numbers and special characters like (@,_,.,-)'
					},
				}
			},
				
			newpsw: {
				validators: {
					notEmpty: {
						message: 'Please enter a new password .'
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'Password must contain atleast one uppercase, one number and atleast one special character like (@,_,.,-)'
					},
					/*identical: {
						field: 'oldpsw',
						message: 'The Current password and New Password are not same'
					}*/
				}
			},
			conpsw: {
				validators: {
					notEmpty: {
						message: 'Please enter a Confirm password '
					},
					regexp: {
						regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/,
						message: 'password must contain only letters, numbers and special characters like (@,_,.,-)'
					},
					identical: {
						field: 'newpsw',
						message: 'New password and its Confirm Password are not  same'
					}
				}
			},
		}
	}).on('success.form.bv', function (e) {
		e.preventDefault();
		checkPasswordValidity();
	});
	
	
	
	
	var checkPasswordValidity = function(){
		var old_password = $("#old_psw").val();
		var password = $("#new_psw").val();
		var cnf_password = $("#con_psw").val();
		var USERNAME= $('#USERNAME').val();
		
		if(password.toLowerCase().includes(USERNAME.toLowerCase())){
			swal("Password should not be derivatives of user name");
			return false;
			
		} 
		if(old_password.trim() == password.trim()){
			swal("Old password and New Password should not be Same..");
			return false;
		} 
		if(password.trim() != cnf_password.trim()){
			swal("New password and its Confirm Password doesn't match..");
		}else{
			var oldPassword = sha512Hash(old_password);
			var password = sha512Hash(password);
			var confPassword = sha512Hash(cnf_password);
			
			$("#old_psw").val(oldPassword);//changed
			$("#new_psw").val(password);//changed
			$("#con_psw").val(confPassword);//changed
			
			
			postData = {
					oldPassword : $("#old_psw").val(),
					password : $("#new_psw").val(),
					confPassword : $("#con_psw").val()
			}
			
			console.log($("#old_psw").val())
			
			$.ajax({
				 url: './passwordcontroller/changePasswordOnValid.do',
				 type: "POST",
				 beforeSend: function(xhr) {
		                xhr.setRequestHeader(header, token);
		  		},
		  		data: JSON.stringify(postData),
		        contentType:"application/json",
		        dataType:"JSON",
				 success: function(data) {
					 $("#formPassword")[0].reset();
					 $('#changePasswordModal').modal('hide');
		                if (data.status) {
		                	swal({
								title : "Success!",
								text : data.msg,
								type : "success"
							}, function() {
								
								window.location = "./login.html?logout=true";
							});
			              }else{
			            	  swal(data.msg);
			              }
		            },
				 error:function(jqXHR, status, error){
				 	swal(error);
				 }
			});
		}
	}
	
	
	$('#cancelbutton').on('click', function (e){
		e.preventDefault();
		$.ajax({
			 url: './orissa-one/backToOdishaOne.do',
			 type: "POST",
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
	  		},
			 success: function(data) {
				 console.log("data cancel"+data);
				 $("#encDataCancelData").val(data.cancelEncData);
				 $('#cancelBacktoOdishaOne').attr('action', data.cancelUrl).submit();
				 
				 
	            },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
		
	});
	
})

