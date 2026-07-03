$(document).ready(function(){
	$.validator.addMethod('charname', function (value, element) {
		return value.match(/^[0-9 a-z A-z-'_]*$/);
	});
	var table = $('#tblDistrictDetails').DataTable({
		
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		   {"className": "dt-center", "targets": "_all"},  
		   /*{"targets":0,"data":"id"},*/
           {"targets":0,"data":"district_id"},
           {"targets":1,"data":"district_name"},
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
	
	$('#editDistrictModal').on('hidden.bs.modal', function(){
		var validator = $( "#formDistrictUpdateDetails" ).validate();
		 validator.resetForm();
	})
	
	var getDataToDistrictDetailsTable = function() {	
		$.ajax({
			 url: "./mastersetup/getDistrictDetails.do",
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
	
	getDataToDistrictDetailsTable();
	
		$('#district_id').keydown(function(key){	
			var keycode = (key.which) ? key.which : key.keyCode;
			if($(this).val().length <= 2){
		        if (keycode == 8 || keycode == 37 || keycode == 46){
		        	key.preventDefault();
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
		});
	
	$('#district_id').keyup(function(key){	
		 if (key.ctrlKey) {  
			 if (keycode == 65 || keycode == 97){
					key.disableTextSelect();
		        	return false;
		        }
		 }
		
		if($(this).val().length <= 2){
			$('#id-inst').css("display", "block");
			$('#district_form_button').attr("disabled", true);
			var keycode = (key.which) ? key.which : key.keyCode;
	        if (keycode == 8 || keycode == 37 || keycode == 46 || keycode == 65){
	        	key.preventDefault();
				key.disableTextSelect();
	        	return false;
	        }
		}else{
			$('#id-inst').css("display", "none");
			$('#district_form_button').attr("disabled", false);
		}
		
		if($(this).val() == ''){
			$('#id-inst').css("display", "none");
			$('#district_form_button').attr("disabled", false);
		}
	});
	
	function objectifyForm(formArray) {//serialize data function

    var returnArray = {};
	   for (var i = 0; i < formArray.length; i++){
	     returnArray[formArray[i]['name']] = formArray[i]['value'];
	   }
	   return returnArray;
	 }
	$.validator.addMethod('onlyNumber', function (value, element) {
		return value.match(/^[0-9]*$/);
	});
	var districtFormValidate = $("#formDistrictDetails");
		
	districtFormValidate.validate({
		rules: {	        	 
			district_id:{
				required: true,
				maxlength: 4,
				minlength: 4,
				onlyNumber:true
			},   	 
			district_name:{
				required: true,
				maxlength: 50,
				charname:true
			},
		},
		messages:{        	 
			district_id:{
				onlyNumber: "Only numeric values allowed.",
				required: "District Id is required.",
				maxlength: "District Id must be of 4 digits.",
				minlength: "District Id must be of 4 digits."
			},   	 
			district_name:{
				required: "District name is required.",
				maxlength: "District name within 50 char.",
				charname:" District name contain (-_')special char"
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

	$('#district_id').keypress(function(){
		$('#district_id').valid();
	});

	$('#district_name').keypress(function(){
		$('#district_name').valid();
	});
	
	
	$("#district_form_button").click(function(){
		var isDistrictFormValid = districtFormValidate.valid();
		if(isDistrictFormValid != false){
			saveDistrict();
		}
	});
	
	var saveDistrict = function saveDistrict() {
		var postData = $("#formDistrictDetails").serializeArray();
		console.log(postData);
		$.ajax({
			 url: "./mastersetup/saveDistrictDetails.do",
			 type: "POST",
//				 beforeSend: function(xhr) {
//		                xhr.setRequestHeader(header, token);
//	      		},
			 dataType:"JSON",
			 data: JSON.stringify(objectifyForm(postData)),
             contentType:"application/json",
             beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
  			},
			 success: function(data, textStatus, jqXHR) {
	                if (data.success) {
	                	resetForm_CreateDistrict();
	                	swal(data.msg);
	                	getDataToDistrictDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 	resetForm_CreateDistrict();
			 }
		});
		}
	
	
	/*Method to update District Details*/
	var districtUpdateFormValidate = $("#formDistrictUpdateDetails");
	
	districtUpdateFormValidate.validate({
		rules: {	        	 
			district_id:{
				required: true,
				maxlength: 4,
				minlength: 4,
				onlyNumber:true
			},   	 
			district_name:{
				required: true,
				maxlength: 50,
				charname:true
			},
		},
		messages:{        	 
			district_id:{
				onlyNumber: "Only numeric values allowed.",
				required: "District Id is required.",
				maxlength: "District Id must be of 4 digits.",
				minlength: "District Id must be of 4 digits."
			},   	 
			district_name:{
				required: "District name is required.",
				maxlength: "District name within 50 char.",
				charname:" District name contain (-_')special char"
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

	$('#district_id1').keypress(function(){
		$('#district_id1').valid();
	});

	$('#district_name1').keypress(function(){
		$('#district_name1').valid();
	});
	
	$('#update-district').on('click', function(){
		var isUpdateDistrictValid = districtUpdateFormValidate.valid();
		if(isUpdateDistrictValid != false){
			updateDistrictDetails();
		}
		
		
	})
	
	var updateDistrictDetails = function updateDistrictDetails(){
		var postData = $('#formDistrictUpdateDetails').serializeArray();
		console.log(postData);
		$.ajax({
			 type: "POST",
			 url: "./mastersetup/updateDistrictDetails.do",
			 data: JSON.stringify(objectifyForm(postData)),
	         contentType:"application/json",
	         dataType:"JSON",
	         beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
				},
			 success: function(data) {
				 if (data.success) {
					 	resetForm_CreateDistrict();
	                	swal(data.msg);
	                	$('#editDistrictModal').modal('hide');
	                	getDataToDistrictDetailsTable();
	                	
		            }else{
		            	resetForm_CreateDistrict();
		            	  swal(data.msg);
		              }
	            },
		 error:function(jqXHR, status, error){
		 	swal(error);
		 }
	});
	}
	
	
	$('#tblDistrictDetails tbody').on( 'click', 'button[action=edit]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		$('#district_id1').val(data.district_id);
		console.log(data.district_id)
		$('#district_name1').val(data.district_name);
		$('#editDistrictModal').modal();

		
		$('#district_id1').keydown(function(key){	
			if($(this).val().length <= 2){
				var keycode = (key.which) ? key.which : key.keyCode;
		        if (keycode == 8 || keycode == 37 ||keycode == 46){
		        	key.preventDefault();
		        	return false;
		        }
			}
		});
	});	
	
	var deleteDistrictDetails = function deleteDistrictDetails(district_id){
        var data = {
        		district_id:district_id
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
    	        	deleteDistrict(data);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
	}
	
	function deleteDistrict(data){
		 $.ajax({
			 url: "./mastersetup/deleteDistrict.do",
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
					 swal(result.msg)
				 	getDataToDistrictDetailsTable();
				 }else{
				 	swal(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	$('#tblDistrictDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = table.row( $(this).parents('tr') ).data();
		deleteDistrictDetails(data.district_id);
	});	
	

	//Status toggle
	var toggleStatus = function(toggle, districtID, query){
		$.ajax({
			 url: "./mastersetup/toggleStatus/"+toggle+"/"+districtID+"/"+query+".do",
			 type: "POST",
			 dataType:"JSON",
            contentType:"application/json",
            beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
 			},
			 success: function(data, textStatus, jqXHR) {
	                if (data.success) {
	                	resetForm_CreateDistrict();
	                	 $.alert(data.msg);
	                	 getDataToDistrictDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
				 resetForm_CreateDistrict();
			 }
		});
	}
	
	
	$('#tblDistrictDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		var districtId = data.district_id;
		var toggle = $(this).data('active');
		var query = "districtMasterSetup";
		
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
	            	 toggleStatus(toggle, districtId, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	var resetForm_CreateDistrict = function(){
		$("#formDistrictDetails")[0].reset();
	}
	
	
});