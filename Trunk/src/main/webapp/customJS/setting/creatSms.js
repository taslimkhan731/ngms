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
		   {"targets":0,"data":"sms_url"},
           {"targets":1,"data":"sms_user"},
           {"targets":2,"data":"sms_pwd"},
           {"targets":3,"data":"user_agent"},
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
			 url: "./setting/getSMSDetails.do",
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
		var formURL = "./setting/saveSMSDetails.do";
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
				 url: "./setting/updateSMSDetails.do",
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
		var sms_url=$("#sms_url").val();
		var sms_user=$("#sms_user").val();
		var sms_pwd=$("#sms_pwd").val();
		var user_agent=$("#user_agent").val();
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
  			if($("#sms_url").val()==''){
				alert("Enter Your sms url");
				$("#sms_url").focus();
			}
			else if($("#sms_user").val()==''){
				alert("Enter Your User name");
				$("#sms_user").focus();
			}
			else if($("#sms_pwd").val()==''){
				alert("Enter Your Password");
				$("#sms_pwd").focus();
			}
			else if($("#user_agent").val()==''){
				alert("Enter your  user agent");
				$("#user_agent").focus();
			}
			else{
				saveOrUpdateUser(type);
			}
  		}else{
  			console.log("inside add.")
  			type = 'ADD'
  				if($("#sms_url").val()==''){
  					alert("Enter Your sms url");
  					$("#sms_url").focus();
  				}
  				else if($("#sms_user").val()==''){
  					alert("Enter Your User name");
  					$("#sms_user").focus();
  				}
  				else if($("#sms_pwd").val()==''){
  					alert("Enter Your Password");
  					$("#sms_pwd").focus();
  				}
  				else if($("#user_agent").val()==''){
  					alert("Enter your  user agent");
  					$("#user_agent").focus();
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
	 console.log(data);

	    $('#sms_url').val(data.sms_url);
		$('#sms_user').val(data.sms_user);
		$('#sms_pwd').val(data.sms_pwd);
		$('#user_agent').val(data.user_agent);
		isEdit = true;
		  })
	
	/********************************************************
	 * function to delete button click event
	 *******************************************************/
	
	$('#tblUserDetails tbody').on('click', 'button[action=Change]', function () {
        var data = table.row( $(this).parents('tr') ).data();
        //var userIdData =table.row( $(this).parents('tr') ).data();
        /*{
        		sms_user:data.sms_user
        };*/
        $.ajax({
			 url: "./setting/deleteSMS.do",
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
	 /*$('#tblUserDetails tbody').on('click', 'button[action=ChangeF]', function () {
	        var data = table.row( $(this).parents('tr') ).data();
	        var userIdData =
	        {
	        		sms_user:data.sms_user
	        };
	        $.ajax({
				 url: "./setting/deleteSMSF.do",
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
	 /*$('#tblUserDetails tbody').on( 'click', 'button[action=Change]', function () {		
		 var data = table.row( $(this).parents('tr') ).data();
		 var b1 = document.getElementById("Change");
		 console.log("Change");
		 b1.style.background = "red";
	        $.ajax({
				 url: "./setting/deleteSMS.do",
				 type: "POST",		
				 data: {
				 	userid: data.userid
				 },
				 success:function(result) {
					 console.log(result);
					 alert("De-activated");
					 getDataToEmployeeDetailsTable();
					 
				 },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 }
			});
	 });
*/
});


/********************************************************
 * function to change password to SHA512
 *******************************************************/
var resetForm_CreateUser = function(){
	$("input[name=sms_url]").val('');
	$("input[name=sms_user]").val('');
	$("input[name=sms_pwd]").val('');
	$("input[name=user_agent]").val('');
	$("#sms_user").prop('readonly', false);
	$('#user_form_button').val('Add');
}

