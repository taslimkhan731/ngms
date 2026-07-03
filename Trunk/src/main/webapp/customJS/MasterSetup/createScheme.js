$(document).ready(function(){
	var table = $('#tblSchemeDetails').DataTable({
		
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		   {"className": "dt-center", "targets": "_all"},  
           {"targets":0,"data":"scheme_id"},
           {"targets":1,"data":"scheme"},
           {"targets":2,"data":"scheme_desc"},
           {"targets":3,"data": null, 
        	   "render":function(data){
        		   var buttons='';
        		   if(data.record_status == 1){
   			    	buttons= '<button type="button" id="Edit" action="edit" class="btn btn-default btn-sm btn-edit" title="Update"><span class="glyphicon glyphicon-edit"></span></button>'+ 
   			    		'&nbsp;<button type="button" action="delete" id="Delete" class="btn btn-default btn-sm btn-delete" title="Delete"><span class="glyphicon glyphicon-trash"></span></button>'+
   			    		'&nbsp;<button type="button" action="toggle" id="toggle" class="btn btn-success btn-sm" data-active="active" title="Click to Deactivate."><span class="glyphicon glyphicon-off"></span></button>';
           			   
           		   }else{
   			    	buttons= '<button type="button" id="Edit" action="edit" class="btn btn-default btn-sm btn-edit" title="Update"><span class="glyphicon glyphicon-edit"></span></button>'+
   			    		'&nbsp;<button type="button" action="delete" id="Delete" class="btn btn-default btn-sm btn-delete" title="Delete"><span class="glyphicon glyphicon-trash"></span></button>'+
   			    		'&nbsp;<button type="button" action="toggle" id="toggle" class="btn btn-danger btn-sm" data-active="inactive" title="Click to Activate."><span class="glyphicon glyphicon-off"></span></button>';
           		   }
        		   return buttons;
			   }                  
           }                    
		  
        ]
	});
	
	var getDataToSchemeDetailsTable = function() {
		$.ajax({
			 url: "./mastersetup/getSchemeDetails.do",
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
			 	swal(error);
			 }
		});
	}
	
	getDataToSchemeDetailsTable();
	
	$('#scheme').on('keyup', function(){
		if($(this).val().substring(0,1).toString() === "0" || $(this).val() == 0){
			$('#id-inst').css("display", "block");
			$('#id-inst').html("Invalid scheme.");
			$('#scheme_form_button').attr("disabled", true);
		}else{
			$('#id-inst').css("display", "none");
			$('#scheme_form_button').attr("disabled", false);
		}
		
		if($(this).val() == ''){
			$('#id-inst').css("display", "none");
		}
		
	});
	
	function objectifyForm(formArray) {//serialize data function

	   var returnArray = {};
	   for (var i = 0; i < formArray.length; i++){
	     returnArray[formArray[i]['name']] = formArray[i]['value'];
	   }
	   return returnArray;
	 }
	
	var schemeFormValidate = $("#formSchemeDetails");
	
	schemeFormValidate.validate({
		rules: {	        	 
			scheme:{
				required: true,
				maxlength: 4
			},   	 
			scheme_desc:{
				required: true,
				/*maxlength: 40*/
			},
			
		},
		messages:{	        	 
			scheme:{
				required: "Please enter scheme.",
				maxlength: "Scheme id can contain only 4 digits."
			},   	 
			scheme_desc:{
				required: "Please enter Scheme Description.",
				/*maxlength: "Only 40 characters allowed."*/
			},
			
		},
		highlight: function (input) {
			$(input).parents('.validation_error_msg').addClass('error');
		},
		unhighlight: function (input) {
			$(input).parents('.validation_error_msg').removeClass('error');
		},
		errorPlacement: function (error, element) {
			$(element).parents('.form-group').append(error);
		}
	});

	$('#scheme').keypress(function(){
		$('#scheme').valid();
	});

	$('#scheme_desc').keypress(function(){
		$('#scheme_desc').valid();
	});
	
	
	$("#scheme_form_button").click(function(){
		var isSchemeFormValid = schemeFormValidate.valid();
		if(isSchemeFormValid != false){
			console.log('scheme'+$('#scheme').val())
			var postData = $("#formSchemeDetails").serializeArray();
			console.log(postData);
				$.ajax({
					 url: "./mastersetup/saveSchemeDetails.do",
					 type: "POST",
					 dataType:"JSON",
					 data: JSON.stringify(objectifyForm(postData)),
		             contentType:"application/json",
		             beforeSend: function(xhr) {
			               xhr.setRequestHeader(header, token);
		  			},
					 success: function(data, textStatus, jqXHR) {
						 if(data.success){
		                	swal(data.msg);
		                	resetForm_CreateScheme();
		                	getDataToSchemeDetailsTable();
						 }else{
		                	swal(data.msg);
			              }
			            },
					 error:function(jqXHR, status, error){
					 	swal(error);
		            	resetForm_CreateScheme();
					 }
				});
			}
	});
	

	/*Method to update Scheme Details*/
	var schemeUpdateFormValidate = $("#formUpdateSchemeDetails");
	
	schemeUpdateFormValidate.validate({
		rules: {	        	 
			scheme:{
				required: true,
			},   	 
			scheme_desc:{
				required: true,
				/*maxlength: 40*/
			},
			
		},
		messages:{	        	 
			scheme:{
				required: "Please enter scheme.",
			},   	 
			scheme_desc:{
				required: "Please enter Scheme Description.",
				/*maxlength: "Only 40 characters allowed."*/
			},
			
		},
		highlight: function (input) {
			$(input).parents('.validation_error_msg').addClass('error');
		},
		unhighlight: function (input) {
			$(input).parents('.validation_error_msg').removeClass('error');
		},
		errorPlacement: function (error, element) {
			$(element).parents('.form-group').append(error);
		}
	});

	$('#scheme1').keypress(function(){
		$('#scheme1').valid();
	});

	$('#scheme_desc1').keypress(function(){
		$('#scheme_desc1').valid();
	});
	

	$('#update-scheme').on('click', function(){
		var isSchemeUpdateFormValid = schemeUpdateFormValidate.valid();
		if(isSchemeUpdateFormValid != false){
			updateSchemeDetails();
		}
		
		
	})
	var updateSchemeDetails = function updateSchemeDetails(){
		var postData = $('#formUpdateSchemeDetails').serializeArray();
		console.log(postData);
		$.ajax({
			 type: "POST",
			 url: "./mastersetup/updateSchemeDetails.do",
			 data: JSON.stringify(objectifyForm(postData)),
	         contentType:"application/json",
	         dataType:"JSON",
	         beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
				},
			 success: function(data) {
				 if (data.success) {
	                	swal(data.msg);
	                	$('#editSchemeModal').modal('hide');
	                	resetForm_CreateScheme();
	                	getDataToSchemeDetailsTable();
	                	
		            }else{
		            	resetForm_CreateScheme();
		            	  swal(data.msg);
		              }
	            },
		 error:function(jqXHR, status, error){
		 	swal(error);
		 }
	});
	}
	
	$('#tblSchemeDetails tbody').on( 'click', 'button[action=edit]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		$('#scheme_id_hidden').val(data.scheme_id);
		console.log("scheme_id_hidden " +data.scheme_id)
		$('#scheme1').val(data.scheme)
		$('#scheme_desc1').val(data.scheme_desc)
		console.log(data.scheme_id)
		$('#editSchemeModal').modal();
	});	

	
	var deleteSchemeDetails = function deleteSchemeDetails(scheme_id){
        var data = {
        		scheme_id:scheme_id
        };
        
        $.confirm({
			icon: 'glyphicon glyphicon-trash',/*'fa fa-spinner fa-spin',*/
    	    title: 'Confirm!',
    	    content: 'This record will be deleted!',
    	    draggable: true,
    	    theme: "modern",
    	    type: 'red',
    	    typeAnimated: true,
    	    buttons: {
    	        'delete': function () {
    	        	deleteScheme(data);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
	}
	
	function deleteScheme(data){
		$.ajax({
			 url: "./mastersetup/deleteScheme.do",
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
					 	swal(result.msg);	
					 	getDataToSchemeDetailsTable();
				 }else{
				 	swal(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	$('#tblSchemeDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = table.row( $(this).parents('tr') ).data();
        deleteSchemeDetails(data.scheme_id);
	});	
	
	//Status toggle
	var toggleStatus = function(toggle, id, query){
		$.ajax({
			 url: "./mastersetup/toggleStatus/"+toggle+"/"+id+"/"+query+".do",
			 type: "POST",
			 dataType:"JSON",
            contentType:"application/json",
            beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
 			},
			 success: function(data, textStatus, jqXHR) {
	                if (data.success) {
	                	 resetForm_CreateScheme();
	                	 $.alert(data.msg);
	                	 getDataToSchemeDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
            	 resetForm_CreateScheme();
			 }
		});
	}
	
	
	$('#tblSchemeDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		var schemeId = data.scheme_id;
		var toggle = $(this).data('active');
		var query = "schemeMasterSetup";
		
		var record_status = data.record_status;
		
		var content;
		var type;
		var button;
		var btn_class;
		
		if (record_status==1) {
			content='The record will be DeActivated!';
			type='red';
			button='DeActivate';
			btn_class='btn-red'
		} else {
			content='The record will be Activated!';
			type='green';
			button='Activate';
			btn_class='btn-green'
		}
		
		$.confirm({
    	    title: 'Confirm!',
    	    content: content,
    	    draggable: true,
    	    theme: "modern",
    	    type: type,
    	    typeAnimated: true,
    	    buttons: {
   	    	 specialKey: {
	             text: button,
	             btnClass: btn_class,
	             action: function(){
	            	 console.log(toggle, schemeId, query)
	            	 toggleStatus(toggle, schemeId, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	function resetForm_CreateScheme(){
		$("#formSchemeDetails")[0].reset();
		
	}
	
	
});