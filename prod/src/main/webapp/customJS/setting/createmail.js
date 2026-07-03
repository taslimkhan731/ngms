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
		   {"targets":0,"data":"email_name"},
           {"targets":1,"data":"email_pwd"},
           {"targets":2,"data":"port"},
           {"targets":3,"data":"description"},
           {"targets":4,"data": null, 
		    	"defaultContent": '<button type="button" id="Edit" action="Edit" class="btn btn-default btn-sm btn-edit"><span class="glyphicon glyphicon-edit"></span></button>'},                  
           {"targets":5,"data": null,
			   "render": function (data, type, row) {
			        if (data.isactive) {
			            return '<button type="button" id="Change" action="Change" class="btn btn-success btn-sm btn-edit"><span class="glyphicon glyphicon-ok"></span></button>';}
			 
			            else {
			 
			            	return '<button type="button" id="ChangeF" action="Change" class="btn btn-danger btn-sm btn-edit"><span class="glyphicon glyphicon-remove"></span></button>';}
			 
			}
	    	}	
        ]
	});
	
	
	/**********************************************************
	 * function to get all user details present in the table
	 * table used:admin.user 
	 * AccessControllerController
	 **********************************************************/
	
	var getDataToUserDetailsTable = function() {		
		$.ajax({
			 url: "./setting/getEmailDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 console.log(result);
				 table.clear();
				 table.rows.add(result.data).draw();
			 },
			 error:function(jqXHR, status, error){
			 	alert(error);
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
		//alert(type);
		var postData = $("#formUserDetails").serializeArray();
		console.log(postData);
		var formURL = "./setting/saveEmailDetails.do";
		if(type=="ADD"){
			//alert("hiii");
			$.ajax({
				 url: formURL,
				 type: "POST",
//				 beforeSend: function(xhr) {
//		                xhr.setRequestHeader(header, token);
//	      		},
				 data: JSON.stringify(objectifyForm(postData)),
	             contentType:"application/json",
	             dataType:"JSON",
	             beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
		                if (data.success) {
		                	resetForm_CreateUser();
		                	alert(data.msg);
		                	getDataToUserDetailsTable();
			              }
		            },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 	resetForm_CreateUser();
				 }
			});
		}
		else if(type=="UPDATE"){
			$.ajax({
				 url: "./setting/updateEmailDetails.do",
				 type: "POST",
//				 beforeSend: function(xhr) {
//		                xhr.setRequestHeader(header, token);
//	      		},
				 data: JSON.stringify(objectifyForm(postData)),
		         contentType:"application/json",
		         dataType:"JSON",
		         beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
					 if (data.success) {
						 	resetForm_CreateUser();
		                	alert(data.msg);
		                	getDataToUserDetailsTable();
		                	
			            }else{
			            	  resetForm_CreateUser();
			            	  alert(data.msg);
			              }
		            },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 }
			});
		}
		else{
			alert("error");
		}

	}

	
	/********************************************************
	 * function to push form submit on button submit event
	 *******************************************************/
        
	$("#user_form_button").click(function() {
		var uid=$("#email_name").val();
		var email_pwd=$("#email_pwd").val();
		var port=$("#port").val();
		var description=$("#description").val();
		/*var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		var contact_no=$("#contact_no").val();
		var pattern = /^\d{10}$/;
		var password=$("#password").val();
		var con_pwd=$("#confirm_Password").val();
		//Store the password field objects into variables ...
	    var pass1 = document.getElementById('password');
	    var pass2 = document.getElementById('con_pwd');*/
	    //Store the Confirmation Message Object ...
	    var message = document.getElementById('confirmMessage');
	    //Set the colors we will be using ...
	    var goodColor = "#66cc66";
	    var badColor = "#ff6666";
	    
		var type = "";	
  		if(isEdit){
  			type = 'UPDATE'
  			console.log("inside update.");
  			type = 'UPDATE';
  			if($("#email_name").val()==''){
				alert("Enter Your email Id");
				$("#email_name").focus();
			}
			else if($("#email_pwd").val()==''){
				alert("Enter Your email password");
				$("#email_pwd").focus();
			}
			else if($("#port").val()==''){
				alert("Enter Your port Number");
				$("#port").focus();
			}
			else if($("#description").val()==''){
				alert("Enter a Valid description");
				$("#description").focus();
			}
			else{
				saveOrUpdateUser(type);
			}
  		}else{
  			console.log("inside add.")
  			type = 'ADD'
  				if($("#email_name").val()==''){
  					alert("Enter Your email Id");
  					$("#email_name").focus();
  				}
  				else if($("#email_pwd").val()==''){
  					alert("Enter Your email password");
  					$("#email_pwd").focus();
  				}
  				else if($("#port").val()==''){
  					alert("Enter Your port Number");
  					$("#port").focus();
  				}
  				else if($("#description").val()==''){
  					alert("Enter a Valid description");
  					$("#description").focus();
  				}else{
  					 
  					saveOrUpdateUser(type);
  					//$("#formUserDetails").submit(type);	
  				}
  		}
			
			
    });
	
	/*********************************************
	 * Edit button click function
	 *********************************************/

	 $('#tblUserDetails tbody').on( 'click', 'button[action=Edit]', function () {		
	 //alert(" edit button click..........");
	 var data = table.row( $(this).parents('tr') ).data();

	    $('#email_name').val(data.email_name);
		$('#email_pwd').val(data.email_pwd);
		$('#port').val(data.port);
		$('#description').val(data.description);
		isEdit = true;
		  })
	
	/********************************************************
	 * function to delete button click event
	 *******************************************************/
	
	/*$('#tblUserDetails tbody').on('click', '.btn-delete', function () {
        var data = table.row( $(this).parents('tr') ).data();
        var userIdData =
        {
        		email_name:data.email_name
        };
        $.ajax({
			 url: "./setting/deleteEmail.do",
			 type: "POST",		
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			 },
			 contentType : 'application/json',
	   		 dataType: "json",
	   		 data: JSON.stringify(userIdData),
			 success:function(result) {
				 console.log(result);
				 if(result.success){
					 alert("Data deleted Sucessfully");
					 getDataToUserDetailsTable();
				 }else{
				 	alert(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	alert(error);
			 }
		});
    });*/
	 $('#tblUserDetails tbody').on('click', 'button[action=Change]', function () {
	        var data = table.row( $(this).parents('tr') ).data();
	        /*var userIdData =
	        {
	        		sms_user:data.sms_user
	        };*/
	        $.ajax({
				 url: "./setting/deleteEmail.do",
				 type: "POST",		
				 beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
				 },
				 contentType : 'application/json',
		   		 dataType: "json",
		   		 data: JSON.stringify(data),
				 success:function(result) {
					 console.log(result);
					 if(result.success){
						 alert("Status changed Sucessfully");
					 	getDataToUserDetailsTable();
					 }else{
					 	alert(result.msg);	
					 }
				 },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 }
			});
	    });
		/* $('#tblUserDetails tbody').on('click', 'button[action=ChangeF]', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		        var userIdData =
		        {
		        		sms_user:data.sms_user
		        };
		        $.ajax({
					 url: "./setting/deleteEmailF.do",
					 type: "POST",		
					 beforeSend: function(xhr) {
			               xhr.setRequestHeader(header, token);
					 },
					 contentType : 'application/json',
			   		 dataType: "json",
			   		 data: JSON.stringify(userIdData),
					 success:function(result) {
						 console.log(result);
						 if(result.success){
							 alert("Status changed to true Sucessfully");
						 	getDataToUserDetailsTable();
						 }else{
						 	alert(result.msg);	
						 }
					 },
					 error:function(jqXHR, status, error){
					 	alert(error);
					 }
				});
		    });*/

});


/********************************************************
 * function to change password to SHA512
 *******************************************************/
var resetForm_CreateUser = function(){
	$("input[name=email_pwd]").val('');
	$("input[name=port]").val('');
	$("input[name=description]").val('');
	$("#email_name").prop('readonly', false);
	$('#user_form_button').val('Add');
}

