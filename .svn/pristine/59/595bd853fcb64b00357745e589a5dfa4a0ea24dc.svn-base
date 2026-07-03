//var token = $("meta[name='_csrf']").attr("content");
//var header = $("meta[name='_csrf_header']").attr("content");
$(document).ready(function(){
	var isEdit = false;
	/****************************************************
	 * dynamic table creation in user module
	 *****************************************************/
	var table = $('#tblUserDetails').DataTable({

		"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"pageLength": 10,	
		"bFilter": true,
		"bLengthChange": false,
		"scrollX": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"},  
			{"targets":0,"data":"userId"},
			{"targets":1,"data":"firstName"},
			{"targets":2,"data":"email"},
			{"targets":3,"data":"contactNo"},
			{"targets":4,"data":"userType"},
			{"targets":5,"data": null, 
				"defaultContent": '<button type="button" id="Edit" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-pencil"></i></button>'+
			'<button type="button" action="Delete" id="Delete" class="btn btn-secondary btn-sm btn-delete ml-1 mr-1"><i class="fa fa-trash"></i></button>'}                  

			]
	});


	/**********************************************************
	 * function to get all user details present in the table
	 * table used:admin.user 
	 * AccessControllerController
	 **********************************************************/

	var getDataToUserDetailsTable = function() {		
		$.ajax({
			url: "./setting/getUserDetails.do",
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


	/*******************************************************************************************
	 * This method url is present in AccessControllerController.java class
	 * Table Used admin.user
	 * taking the input value FormData
	 * modified on: 26-Oct-2017 By: Vamsi Krish
	 ********************************************************************************************/

	function objectifyForm(formArray) {//serialize data function

		var returnArray = {};
		for (var i = 0; i < formArray.length; i++){
			returnArray[formArray[i]['name']] = formArray[i]['value'];
		}
		return returnArray;
	}

	var saveOrUpdateUser = function saveOrUpdateUser(type) {
		var password = $("#password").val();
		var cpassword = $("#confirm_password").val();
		if(password != cpassword){
			swal('Confirm password does not match');
			return false;
		}
		var encSaltSHAPass = sha512Hash(password);
		$("#password").val(encSaltSHAPass);
		//swal(type);
		var postData = $("#formUserDetails").serializeArray();
		console.log(postData);
		var formURL = "./setting/saveUserDetails.do";
		if(type=="ADD"){
			$.ajax({
				url: formURL,
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
						getDataToUserDetailsTable();
					}
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
					resetForm_CreateUser();
				}
			});
		}
		else if(type=="UPDATE"){
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


	/********************************************************
	 * function to push form submit on button submit event
	 *******************************************************/

	$("#user_form_button").click(function() {
		var email_id=$("#email_id").val();
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		var contact_no=$("#contact_no").val();
		var pattern = /^\d{10}$/;

		var type = "";	
		if(isEdit){
			type = 'UPDATE'
				console.log("inside update.");
			type = 'UPDATE';
			if($("#user_id").val()==''){
				swal("Enter Your User Id");
				$("#user_id").focus();
			}
			else if($("#first_name").val()==''){
				swal("Enter Your First Name");
				$("#first_name").focus();
			}
			else if($("#last_name").val()==''){
				swal("Enter Your Last Name");
				$("#last_name").focus();
			}
			else if($("#email_id").val()==''  || !emailReg.test( email_id )){
				swal("Enter a Valid Email_Id");
				$("#email_id").focus();
			}
			else if($("#contact_no").val()=='' || !pattern.test(contact_no)){
				swal("Enter Your Valid Contact Number");
				$("#contact_no").focus();
			}else{
				saveOrUpdateUser(type);
			}
		}else{
			console.log("inside add.")
			type = 'ADD'
				if($("#user_id").val()==''){
					swal("Enter Your User Id");
					$("#user_id").focus();
				}
				else if($("#first_name").val()==''){
					swal("Enter Display Name");
					$("#first_name").focus();
				}
				else if($("#email_id").val()==''  || !emailReg.test( email_id )){
					swal("Enter a Valid Email_Id");
					$("#email_id").focus();
				}
				else if($("#password").val()==''){
					swal("Enter Your Password");
					$("#password").focus();
				}
				else if($("#con_pwd").val()==''){
					swal("Enter Confirm Password");
					$("#con_pwd").focus();
				}
				else if($("#contact_no").val()=='' || !pattern.test(contact_no)){
					swal("Enter Your Valid Contact Number");
					$("#contact_no").focus();
				}else if($("#user_type").val()==''){
					swal("Select user type");
					$("#user_type").focus();
				}else{
					saveOrUpdateUser(type);
				}
		}


	});

	/*********************************************
	 * Edit button click function
	 *********************************************/

	$('#tblUserDetails tbody').on( 'click', 'button[action=Edit]', function () {		
		//swal(" edit button click..........");
		var data = table.row( $(this).parents('tr') ).data();

		$('#user_id').val(data.userId);
		$('#first_name').val(data.firstName);
		$('#email_id').val(data.email);
		$('#contact_no').val(data.contactNo);
		$('#user_form_button').html('Update');
		$("#user_id").prop('readonly', true);
		$("#user_type").hide();
		$("#user_type_label").hide();
		$('#div_password').hide();
		$('#div_conf_password').hide();
		isEdit = true;

	})

	/********************************************************
	 * function to delete button click event
	 *******************************************************/

	$('#tblUserDetails tbody').on('click', 'button[action=Delete]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var userIdData =
		{
				userId:data.userId
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
	getGroupDetails();
});

var resetForm_CreateUser = function(){
	$("#formUserDetails")[0].reset();
	$("#user_id").prop('readonly', false);
	$('#user_form_button').html('Add');
	$('#div_password').show();
	$('#div_conf_password').show();
	$("#user_type").show();
	$("#user_type_label").show();
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
