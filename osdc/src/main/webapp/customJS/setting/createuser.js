//var token = $("meta[name='_csrf']").attr("content");
//var header = $("meta[name='_csrf_header']").attr("content");
$(document).ready(function(){
	var isEdit = false;
	var type = "";
	/***************************************************************************
	 * dynamic table creation in user module
	 **************************************************************************/
	var table = $('#tblUserDetails').DataTable({

		"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"pageLength": 10,	
		"bFilter": true,
		"bLengthChange": false,
		"scrollX": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"},  
			{"width":"20%","targets":0,"data":"userId"},
			{"width":"20%","targets":1,"data":"firstName"},
			{"width":"15%","targets":2,"data":"email"},
			{"width":"20%","targets":3,"data":"contactNo"},
			{"width":"20%","targets":4,"data":"userType"},
			{"width":"15%","targets":5,"data": null,
				
				render: function (data, type, row) {
                    if(data.active == false){
                        return '<button type="button" id="activate" action="activate" title="activate user" class="btn btn-view btn-sm mr-1"><i class="fa fa-check" ></i></button> '
                    }else{
                    	return '<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-sm btn-edit"><i class="fa fa-edit"></i></button>'+
                    	'<button type="button" action="Delete" id="Delete" title="Delete" class="btn btn-sm btn-dlt ml-1"><i class="fa fa-trash"></i></button>'
                    }
                }
			}                  

			]
	});
	



	/***************************************************************************
	 * function to get all user details present in the table table
	 * used:admin.user AccessControllerController
	 **************************************************************************/

	var getDataToUserDetailsTable = function() {
		var type="user";
		$.ajax({
			url: "./setting/getUserDetails/"+type+".do",
			type: "POST",			
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				table.clear();
				table.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal(error,"","error");
			}
		});
	}

	getDataToUserDetailsTable();


	/***************************************************************************
	 * This method url is present in AccessControllerController.java class Table
	 * Used admin.user taking the input value FormData modified on: 26-Oct-2017
	 * By: Vamsi Krish
	 **************************************************************************/

	function objectifyForm(formArray) {// serialize data function

		var returnArray = {};
		for (var i = 0; i < formArray.length; i++){
			returnArray[formArray[i]['name']] = formArray[i]['value'];
		}
		return returnArray;
	}
	
	$('#formUserDetails').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields : {
			firstName: {
				validators : {
					stringLength: {
                        min: 3,
                        max: 20,
                        message: 'The answer must be more than 2 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'The District name must not be empty'
					},
					regexp : {
						regexp : /^[a-z ,.'-]+$/i,
						message : 'District name must contain only letters'
					}
				}
			},
			email: {
				validators: {
					notEmpty: {
						message: 'The email address is required and can\'t be empty'
					},regexp: {
						regexp:/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
						message: 'field is not valid'
					}
				}
			},
			contactNo: {
				validators : {
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
			/*password: {
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
			confirm_password: {
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
			},*/      
    		user_type: {
      				validators : {
      					notEmpty: {
      						message: 'Select  a valid option'
      					}
      					
      				}
      			},
    			
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitUserDetails();
    });

	// var saveOrUpdateUser = function saveOrUpdateUser(type) {
		
var submitUserDetails = function () {
		
	if(isEdit){
		type = 'UPDATE';
	}else{
		type = 'ADD'
	}
	
		
		console.log("postData++++++++++++"+postData);
		if(type=="ADD"){
			var mobileno=$("#contact_no").val();
			$("#user_id").val(mobileno);
			var postData = $("#formUserDetails").serializeArray();
			$.ajax({
				url: "./setting/saveUserDetails.do",
				type: "POST",
				data: JSON.stringify(objectifyForm(postData)),
				contentType:"application/json",
				dataType:"JSON",
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(data, textStatus, jqXHR) {
					$('#loading').hide();
					if (data.success) {
						resetForm_CreateUser();
						swal(data.msg,"","success");
						$('#formUserDetails').bootstrapValidator('resetForm', true);
						$('#contact_no').prop("disabled",false);
						getDataToUserDetailsTable();
					}else{
						swal(data.msg,"","success");
						$('#formUserDetails').bootstrapValidator('resetForm', true);
						$('#contact_no').prop("disabled",false);
					}
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
					resetForm_CreateUser();
				}
			});
		}
		else if(type=="UPDATE"){
			var postData = $("#formUserDetails").serializeArray();
			$.ajax({
				url: "./setting/updateUserDetails.do",
				type: "POST",
				data: JSON.stringify(objectifyForm(postData)),
				contentType:"application/json",
				dataType:"JSON",
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(data, textStatus, jqXHR) {
					$('#loading').hide();
					if (data.success) {
						resetForm_CreateUser();
						swal(data.msg,"","success");
						$('#formUserDetails').bootstrapValidator('resetForm', true);
						getDataToUserDetailsTable();

					}else{
						resetForm_CreateUser();
						swal(data.msg,"","error");
					}
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
				}
			});
		}
		else{
			swal("error");
		}

	}


	/***************************************************************************
	 * function to push form submit on button submit event
	 **************************************************************************/
/*
 * $("#user_form_button").click(function() { var email_id=$("#email_id").val();
 * var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/; var
 * contact_no=$("#contact_no").val(); var pattern = /^\d{10}$/;
 * 
 * var type = ""; if(isEdit){ type = 'UPDATE' console.log("inside update.");
 * type = 'UPDATE'; if($("#user_id").val()==''){ swal("Enter Your User Id");
 * $("#user_id").focus(); } else if($("#first_name").val()==''){ swal("Enter
 * Your First Name"); $("#first_name").focus(); } else
 * if($("#last_name").val()==''){ swal("Enter Your Last Name");
 * $("#last_name").focus(); } else if($("#email_id").val()=='' ||
 * !emailReg.test( email_id )){ swal("Enter a Valid Email_Id");
 * $("#email_id").focus(); } else if($("#contact_no").val()=='' ||
 * !pattern.test(contact_no)){ swal("Enter Your Valid Contact Number");
 * $("#contact_no").focus(); }else{ saveOrUpdateUser(type); } }else{
 * console.log("inside add.") type = 'ADD' if($("#user_id").val()==''){
 * swal("Enter Your User Id"); $("#user_id").focus(); } else
 * if($("#first_name").val()==''){ swal("Enter Display Name");
 * $("#first_name").focus(); } else if($("#email_id").val()=='' ||
 * !emailReg.test( email_id )){ swal("Enter a Valid Email_Id");
 * $("#email_id").focus(); } else if($("#password").val()==''){ swal("Enter Your
 * Password"); $("#password").focus(); } else if($("#con_pwd").val()==''){
 * swal("Enter Confirm Password"); $("#con_pwd").focus(); } else
 * if($("#contact_no").val()=='' || !pattern.test(contact_no)){ swal("Enter Your
 * Valid Contact Number"); $("#contact_no").focus(); }else
 * if($("#user_type").val()==''){ swal("Select user type");
 * $("#user_type").focus(); }else{ saveOrUpdateUser(type); } }
 * 
 * 
 * });
 */
	/***************************************************************************
	 * Edit button click function
	 **************************************************************************/

	$('#tblUserDetails tbody').on( 'click', 'button[action=Edit]', function () {		
		//swal(" edit button click..........");
		var data = table.row( $(this).parents('tr') ).data();
		$('#user_id').val(data.userId);
		$('#first_name').val(data.firstName);
		$('#email_id').val(data.email);
		$('#contact_no').val(data.contactNo);
		$('#contact_no').prop("disabled",true);
		$('#user_form_button').html('Update');
		$("#user_id").prop('readonly', true);
		$("#user_type").hide();
		$("#user_type").val(data.userType);
		$("#userTypeEdit").val(data.userType);
		$("#user_type_label").hide();
		$('#div_password').hide();
		$('#div_conf_password').hide();
		isEdit = true;

	})

	/***************************************************************************
	 * function to delete button click event
	 **************************************************************************/

	$('#tblUserDetails tbody').on('click', 'button[action=Delete]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		swal({
			title: "Are you sure? ",
			text: "Deactivate User",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Deactivate",
			closeOnConfirm: false
		},function(){
		var userIdData =
		{
				userId:data.userId,
				userType:data.userType
		};
		$.ajax({
			url: "./setting/deleteUser.do",
			type: "POST",		
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			contentType : 'application/json',
			dataType: "json",
			data: JSON.stringify(userIdData),
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				if(result.success){
					swal(result.msg,"","success");
					getDataToUserDetailsTable();
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error,"","error");
			}
		});
	});
	});
	getGroupDetails();
	/***********************************************************************
	 * action button for activate user
	 ***********************************************************************/
	$('#tblUserDetails tbody').on( 'click', 'button[action=activate]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		swal({
			title: "Are you sure? ",
			text: "Activate User",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-primary",
			confirmButtonText: "Activate",
			closeOnConfirm: false
		},function(){
			var userIdData =
			{
					userId:data.userId,
					userType:data.userType
			};
			$.ajax({
				url: "./setting/activate.do",
				type: "POST",		
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				contentType : 'application/json',
				dataType: "json",
				data: JSON.stringify(userIdData),
				success:function(result) {
					$('#loading').hide();
					console.log(result);
					if(result.success){
						swal(result.msg,"","success");
						getDataToUserDetailsTable();
					}else{
						swal(result.msg,"","error");	
					}
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
				}
			})
		});
	});
	
});//end of document.ready()

var resetForm_CreateUser = function(){
	$("#formUserDetails")[0].reset();
	$("#user_id").prop('readonly', false);
	$('#user_form_button').html('Add');
	$('#div_password').show();
	$('#div_conf_password').show();
	$("#user_type").show();
	$("#user_type_label").show();
	$('#contact_no').prop("disabled",false);
	isEdit = false;
}

var getGroupDetails = function() {		
	$.ajax({
		"url": "./setting/getGroupDetails.do",
		"type": "POST",
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			$("#user_type").html("");
			$("#user_type").append('<option value="">---Select---</option>');
			result.data.forEach(option=>{
				$("#user_type").append('<option value="'+option.groupId+'">'+option.groupName+'</option>');
			})
		},
		error:function(jqXHR, status, error){
			swal(error,"","error");
		}
	});
}		
