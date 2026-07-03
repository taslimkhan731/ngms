$(document).ready(function(){
	
	$.validator.addMethod('validateFinYear', function (value, element) {
		return value.match(/^([0-9]{4})+-([0-9]{2})*$/);
	});
	
	var table = $('#tblYearDetails').DataTable({
		
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		   {"className": "dt-center", "targets": "_all"},  
           {"targets":0,"data":"year_id"},
           {"targets":1,"data":"year_desc"},
           {"targets":2,"data": null, 
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
	
	var getDataToYearDetailsTable = function() {
		$.ajax({
			 url: "./mastersetup/getYearDetails.do",
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
	
	getDataToYearDetailsTable();
	
	$('#year_desc').on('keyup', function(){
		if($(this).val().substring(0,4).toString() === "0000" || $(this).val().substring(0,4).toString() === "1111" || $(this).val().substring(2,4) >= $(this).val().substring(5,8)){
			$('#id-inst').css("display", "block");
			$('#id-inst').html("Invalid financial year.")
			$('#year_form_button').attr("disabled", true);
		}else{
			$('#id-inst').css("display", "none");
			$('#year_form_button').attr("disabled", false);
		}
		
		if($(this).val() == ''){
			$('#id-inst').css("display", "none");
		}
		/*console.log("one "+$(this).val().substring(2,4))
		console.log("two "+$(this).val().substring(5,8))*/
		
	});
	
	function objectifyForm(formArray) {//serialize data function

	   var returnArray = {};
	   for (var i = 0; i < formArray.length; i++){
	     returnArray[formArray[i]['name']] = formArray[i]['value'];
	   }
	   return returnArray;
	 }
	
	var finYearFormValidate = $("#formFinYearDetails");
	finYearFormValidate.validate({
		rules: {	        	 
			year_desc:{
				required: true,
				validateFinYear: true,
				maxlength: 7,
			},
		},
		messages:{        	 
			year_desc:{
				required: "Please enter a financial year.",
				validateFinYear: "Invalid Financial Year Format. Try: XXXX-XX",
				maxlength: "Only 7 characters allowed!"
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

	$('#year_desc').keypress(function(){
		$('#year_desc').valid();
	});
	
	
	$("#year_form_button").click(function(){
		var isFinancialYearFormValid = finYearFormValidate.valid();
		if(isFinancialYearFormValid != false){
			saveFinancialYearDetails();
		}
	})
	
	function saveFinancialYearDetails() {
			console.log('year desc'+$('#year_desc').val())
			var postData = $("#formFinYearDetails").serializeArray();
			console.log(postData);
			$.ajax({
				 url: "./mastersetup/saveYearDetails.do",
				 type: "POST",
				 dataType:"JSON",
				 data: JSON.stringify(objectifyForm(postData)),
	             contentType:"application/json",
	             beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
		                if (data.success) {
		                	resetForm_CreateYear();
		                	swal(data.msg);
		                	getDataToYearDetailsTable();
			              }else{
			                	swal(data.msg);
			              }
		            },
				 error:function(jqXHR, status, error){
				 	swal(error);
				 	resetForm_CreateYear();
				 }
			});
		};

	/*Method to update Year Details*/
		var finYearUpdateFormValidate = $("#formUpdateYearDetails");
		finYearUpdateFormValidate.validate({
			rules: {	        	 
				year_desc:{
					required: true,
					validateFinYear: true,
					maxlength: 7,
					minlength:7,
				},
			},
			messages:{        	 
				year_desc:{
					required: "Please enter a financial year.",
					validateFinYear: "Invalid Financial Year Format. Try: XXXX-XX",
					maxlength: "Only 7 characters allowed!",
					minlength: "Only 7 characters allowed!",
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

		$('#year_desc').keyup(function(){
			$('#year_desc').valid();
		});
		$('#update-year').on('click', function(){
			var isFinYearUpdateFormValid = finYearUpdateFormValidate.valid();
			if(isFinYearUpdateFormValid != false){
				updateYearDetails();
			}
		})
	
		
	var updateYearDetails = function updateYearDetails(){
		var postData = $('#formUpdateYearDetails').serializeArray();
		console.log(postData);
		$.ajax({
			 type: "POST",
			 url: "./mastersetup/updateYearDetails.do",
			 data: JSON.stringify(objectifyForm(postData)),
	         contentType:"application/json",
	         dataType:"JSON",
	         beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
				},
			 success: function(data) {
				 if (data.success) {
					 resetForm_CreateYear();
	                	swal(data.msg);
	                	$('#editYearModal').modal('hide');
	                	getDataToYearDetailsTable();
	                	
		            }else{
		            	resetForm_CreateYear();
		            	  swal(data.msg);
		              }
	            },
		 error:function(jqXHR, status, error){
		 	swal(error);
		 }
	});
	}
	
	
	$('#tblYearDetails tbody').on( 'click', 'button[action=edit]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		$('#year_id_hidden').val(data.year_id);
		$('#year_desc1').val(data.year_desc)
		console.log(data.year_id)
		$('#editYearModal').modal();
	});	


	var deleteYearDetails = function deleteYearDetails(year_id){
        var data =
        {
        		year_id:year_id
        };
        $.ajax({
			 url: "./mastersetup/deleteYear.do",
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
					 	resetForm_CreateYear();
					 	getDataToYearDetailsTable();
				 }else{
				 	swal(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	$('#tblYearDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = table.row( $(this).parents('tr') ).data();
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
    	        	deleteYearDetails(data.year_id);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
        
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
	                	 resetForm_CreateYear();
	                	 $.alert(data.msg);
					 	getDataToYearDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
            	 resetForm_CreateYear();
			 }
		});
	}
	
	
	$('#tblYearDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		var finYrId = data.year_id;
		var toggle = $(this).data('active');
		var query = "FinYrMasterSetup";
		
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
	            	 console.log(toggle, finYrId, query)
	            	 toggleStatus(toggle, finYrId, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	
	function resetForm_CreateYear(){
		$("#formFinYearDetails")[0].reset();
		
	}
	
	
});