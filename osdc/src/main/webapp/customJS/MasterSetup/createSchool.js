$(document).ready(function(){
	$.validator.addMethod('charname', function (value, element) {
		return value.match(/^[0-9 a-z A-z-'_]*$/);
	});
	//var isEdit = false;
	$('#sid').attr('disabled', true);
	/****************************************************
	 * dynamic table creation in School module
	 *****************************************************/
	var table = $('#tblSchoolDetails').DataTable({

		"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"pageLength": 10,	
		"bFilter": true,
		"bLengthChange": false,
		"scrollX": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"},  
			{"targets":0,"data":"school_id"},
			{"targets":1,"data":"school_name"},
			{"targets":2,"data":"block_id"},
			{"targets":3,"data":"block_name"},
			{"targets":4,"data": null, 
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
	
	$("#editSchoolModal").on("hidden.bs.modal", function(){
		 $('#formSchoolUpdateDetails')[0].reset();
	});
	
	/**********************************************************
	 * function to get all school present in the table
	 * table used:mst.school 
	 * MasterSetupController
	 **********************************************************/
	
	var getDataToSchoolDetailsTable = function() {	
		$('#loading').show();
		//swal("Inside getDataToSchoolDetailsTable");
		$.ajax({
			 url: "./mastersetup/getSchoolDetails.do",
			 type: "POST",	
			 async:false,
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 $('#loading').hide();
				 table.clear();
				 table.rows.add(result.data).draw();
			 },
			 error:function(jqXHR, status, error){
				 $('#loading').hide();
			 	swal(error);
			 }
		});
	}
	
	getDataToSchoolDetailsTable();
	
	/**********************************************************
	 * function to get all blocks present in the table
	 * table used:master_block 
	 * MasterSetupController
	 **********************************************************/
	
	var getDataToDistrictDropdown = function() {	
		//swal("Inside getDataToSchoolDetailsTable");
		$.ajax({
			 url: "./mastersetup/getDistrictDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
					if(result.status == false){
						 swal(result.msg);
					}else{
						var data = result.data;
						
						var option = '<option value="">Select a District</option>'
						result.data.forEach(function(data){
							console.log(data);
		                	option += '<option value="'+data.district_id+'">'+data.district_name+'</option>'
		                });
			            $("#district_id").html("");
			            $("#district_id").append(option);
			            
					}
				},
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	getDataToDistrictDropdown();
	
	$('#block_id').on('change', function(){
		$('#sid').attr('disabled', false);
		$('#sid').val($(this).val())
		$('#id-inst').css("display", "block");
		$('#id-inst').html('Enter last 5 digits of school id.');
	})
	$('#sid').on('keydown', function(key){
		var keycode = (key.which) ? key.which : key.keyCode;
		
		if($(this).val().length <= 6){
			$('#id-inst').css("display", "block");
			$('#id-inst').html('Enter last 5 digits of school id.');
	        if (keycode == 8 || keycode == 37 || keycode == 46 || keyCode == 65){
	        	key.preventDefault();
				key.disableTextSelect();
	        	return false;
	        }
		}

		 if (key.ctrlKey) { 
			 if (keycode == 65 || keycode == 97){ 
				 	key.preventDefault();
					key.disableTextSelect();
		        	return false;
		        }
		 }
	})
	
	$('#sid').on('keyup', function(key){
		if($(this).val().length == 11){
			$('#id-inst').css("display", "none");
		}	
	});
	
	var getBlockByDistrictId = function(distId) {	
		//swal("Inside getDataToSchoolDetailsTable");
		$.ajax({
			url: "./mastersetup/getBlockDetailsByDistrictID/"+distId+".do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
					if(result.status == false){
						 swal(result.msg);
					}else{
						var data = result.data;
						var option = '<option value="All">--Select Block--</option>';
						for(var i = 0;i<data.length;i++){
							option += '<option value="'+data[i].id+'">'+data[i].name+'</option>';
						}
						$('#block_id').val('');
						$('#block_id').empty();
						$('#block_id').append(option);
						$("#block_id").prop("selectedIndex", 0);
					}
				},
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}

	$('#district_id').on('change', function(){
		var distId = $(this).val();
		getBlockByDistrictId(distId);
	})

	
	
	/*******************************************************************************************
	 * This method url is present in MasterSetupController.java class
	 * Table Used admin.user
	 * taking the input value FormData
	 * modified on: 25-AUG-18 By: Suvrata Kumar Hada
	 ********************************************************************************************/

	 function objectifyForm(formArray) {//serialize data function

		   var returnArray = {};
		   for (var i = 0; i < formArray.length; i++){
		     returnArray[formArray[i]['name']] = formArray[i]['value'];
		   }
		   return returnArray;
		 }
	
 /*******************************************************************************************
	 * This methodis used to save School details in the master_school table
	 * taking the input value FormData
	 * modified on: 18-Sep-8 By: Bishwajeet
	 ********************************************************************************************/
	 
	 var schoolFormValidate = $("#formSchoolDetails");
		
	 schoolFormValidate.validate({
			rules: {	         	 
				district_id:{
					required: true,
				},         	 
				block_id:{
					required: true,
				},   	 
				school_id:{
					required: true,
					number: true,
					maxlength:11,
					minlength:11
				},
				school_name:{
					required: true,
					maxlength:50,
					charname:true
				}
			},
			messages:{        	 
				district_id:{
					required: "Please select a District.",
				},       	 
				block_id:{
					required: "Please select a block.",
				},   	 
				school_id:{
					required: "School Id is required.",
					number: "Only numeric values allowed.",
					minlength:"School Id should be 11 digit",
					maxlength:"School Id should be 11 digit"
				},
				school_name:{
					required: "School name is required.",
					maxlength:"School name within 50 char",
					charname:"school name contain only (-_') special char"
				}
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

		$('#sid').keypress(function(){
			$('#sid').valid();
		});

		$('#sname').keypress(function(){
			$('#sname').valid();
		});
		
		
		$("#school_form_button").click(function(){
			$('#district_id').valid();
			$('#block_id').valid();
			var isSchoolFormValid = schoolFormValidate.valid();
			if(isSchoolFormValid != false){
				saveOrUpdateSchool();
			}
		});
	 
	var saveOrUpdateSchool = function saveOrUpdateSchool() {
		$('#loading').show();
		var postData = $("#formSchoolDetails").serializeArray();
		console.log('===================');
		console.log(postData);
		var formURL = "./mastersetup/saveSchoolDetails.do";
			$.ajax({
				 url: formURL,
				 type: "POST",
				 beforeSend: function(xhr) {
		                xhr.setRequestHeader(header, token);
				 },
				 data: JSON.stringify(objectifyForm(postData)),
	             contentType:"application/json",
	             dataType:"JSON",
	             beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
					 $('#loading').hide();
		                if (data.success) {
		                	resetForm_CreateSchool();
		                	swal(data.msg);
		                	console.log("======schoolData=====");
		                	getDataToSchoolDetailsTable();
			              }else{
		                	swal(data.msg);
			              }
		            },
				 error:function(jqXHR, status, error){
					 $('#loading').hide();
				 	swal(error);
				 	resetForm_CreateSchool();
				 }
			});
		}
	
	
	
	/*Method to update School Details*/
	 
	 var schoolUpdateFormValidate = $("#formSchoolUpdateDetails");
		
	 schoolUpdateFormValidate.validate({
			rules: {	        	 
				school_id:{
					required: true,
					number: true,
					maxlength:11,
					minlength:11
				},
				school_name:{
					required: true,
					maxlength:50,
					charname:true
				}
			},
			messages:{        	 
				school_id:{
					required: "School Id is required.",
					number: "Only numeric values allowed.",
					minlength:"School Id should be 11 digit",
					maxlength:"School Id should be 11 digit"
				},
				school_name:{
					required: "School name is required.",
					maxlength:"School name contain within 50 charchter",
					charname:"school name contain only (-_') special char"
				}
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

		$('#school_id1').keypress(function(){
			$('#school_id1').valid();
		});

		$('#school_name1').keypress(function(){
			$('#school_name1').valid();
		});
		
		$('#update-school').on('click', function(){
			var isSchoolUpdateFormValid = schoolUpdateFormValidate.valid();
			if(isSchoolUpdateFormValid != false){
				updateSchoolDetails();
			}
			
			
		})
		
	
	var updateSchoolDetails = function updateSchoolDetails(){
		$.LoadingOverlay("show");
		var postData = $('#formSchoolUpdateDetails').serializeArray();
		console.log(postData);
		$.ajax({
			 type: "POST",
			 url: "./mastersetup/updateSchoolDetails.do",
			 data: JSON.stringify(objectifyForm(postData)),
	         contentType:"application/json",
	         dataType:"JSON",
	         async:false,
	         beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
				},
			 success: function(data) {
				 $.LoadingOverlay("hide");
				 if (data.success) {
					 	resetForm_CreateSchool();
	                	swal(data.msg);
	                	$('#editSchoolModal').modal('hide');
	                	getDataToSchoolDetailsTable();
	                	
		            }else{
		            	resetForm_CreateSchool();
		            	  swal(data.msg);
		              }
	            },
		 error:function(jqXHR, status, error){
			 $.LoadingOverlay("hide");
		 	swal(error);
		 }
	});
	}
	
	
	$('#tblSchoolDetails tbody').on( 'click', 'button[action=edit]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		$('#school_id').val(data.school_id);
		console.log(data.school_id)
		$('#school_id1').val(data.school_id);
		//$('#school_id1').attr('readonly',true);
		$('#school_name1').val(data.school_name);
		$('#editSchoolModal').modal();
	});	
	
	var deleteSchoolDetails = function deleteSchoolDetails(school_id){
        var data = {
        		school_id:school_id
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
    	        	deleteSchool(data);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
	}
	
	function deleteSchool(data){
		$('#loading').show();
		$.ajax({
			 url: "./mastersetup/deleteSchool.do",
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
					 $('#loading').hide();
					 swal(result.msg);	
				 	getDataToSchoolDetailsTable();
				 }else{
				 	swal(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	$('#tblSchoolDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = table.row( $(this).parents('tr') ).data();
		deleteSchoolDetails(data.school_id);
	});	
	

	//Status toggle
	var toggleStatus = function(toggle, schoolID, query){
		$('#loading').show();
		$.ajax({
			 url: "./mastersetup/toggleStatus/"+toggle+"/"+schoolID+"/"+query+".do",
			 type: "POST",
			 dataType:"JSON",
            contentType:"application/json",
            beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
 			},
			 success: function(data, textStatus, jqXHR) {
	                if (data.success) {
	                	resetForm_CreateSchool();
	                	$('#loading').hide();
	                	 $.alert(data.msg);
	                	 getDataToSchoolDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
				 resetForm_CreateSchool();
			 }
		});
	}
	
	$('#tblSchoolDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		var schoolId = data.school_id;
		var toggle = $(this).data('active');
		var query = "schoolMasterSetup";
		
		var record_status = data.record_status;
		
		var content;
		var type;
		var button;
		var btn_class;
		
		if (record_status==1) {
			content='The record will be DeActivated!';
			//icon='glyphicon glyphicon-off';
			type='red';
			button='DeActivate';
			btn_class='btn-red'
		} else {
			content='The record will be Activated!';
			//icon='glyphicon glyphicon-off';
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
	            	 toggleStatus(toggle, schoolId, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	
	function resetForm_CreateSchool(){
		$("#formSchoolDetails")[0].reset();
		
	}
	
});