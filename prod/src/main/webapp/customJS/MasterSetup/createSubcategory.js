$('document').ready(function(){
	
	/*Financial Year*/
	
	var getFinancialYearFromMasterFinYear = function(){
		$.ajax({
			 url: "./projectmonitoring/getFinancialYear.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 console.log(result);
				 var data = result.data;
				 var option = '<option  value="">--Select Financial Year--</option>';
					for(var i = 0;i<data.length;i++){
						option += '<option value="'+data[i].year_id+'">'+data[i].year_desc+'</option>';
					}
					$('#finyr_id').val('');
					$('#finyr_id').empty();
					$('#finyr_id').append(option);
					$("#finyr_id").prop("selectedIndex", 0);
					

					$('#finyear').val('');
					$('#finyear').empty();
					$('#finyear').append(option);
					$("#finyear").prop("selectedIndex", 0);
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	getFinancialYearFromMasterFinYear();
	
	/*Category*/
	var getCategoryFromMasterWorkCatg = function(){
		$.ajax({
			 url: "./mastersetup/getCategoryDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 console.log(result.data);
				 var data = result.data;
				 var option = '<option  value="">--Select Category--</option>';
					for(var i = 0;i<data.length;i++){
						option += '<option value="'+data[i].workcatg_id+'">'+data[i].catg_desc+'</option>';
					}
					$('#workcatg_id').val('');
					$('#workcatg_id').empty();
					$('#workcatg_id').append(option);
					$("#workcatg_id").prop("selectedIndex", 0);

					$('#category').val('');
					$('#category').empty();
					$('#category').append(option);
					$("#category").prop("selectedIndex", 0);
					
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	getCategoryFromMasterWorkCatg();
	

	/*SubCategory*/
	var getSubCategoryFromMasterWorkDesc = function(){
		$.ajax({
			 url: "./mastersetup/getAllSubcategoryFormMasterWorkDesc.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 console.log(result.data);
				 var data = result.data;
				 var option = '<option value="">--Select Subcategory--</option>';
					for(var i = 0;i<data.length;i++){
						option += '<option value="'+data[i].work_desc_id+'">'+data[i].work_desc+'</option>';
					}
					$('#sub_work_desc').val('');
					$('#sub_work_desc').empty();
					$('#sub_work_desc').append(option);
					$("#sub_work_desc").prop("selectedIndex", 0);

					$('#subcategory_dd').val('');
					$('#subcategory_dd').empty();
					$('#subcategory_dd').append(option);
					$("#subcategory_dd").prop("selectedIndex", 0);
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	getSubCategoryFromMasterWorkDesc();
	
	$('#sub_work_desc').on('change', function(){
		var work_descId = $('#sub_work_desc').val();
		$('#work_desc_id').val(work_descId);
		$('#work_desc').val($("#sub_work_desc option:selected").text());
		console.log($('#work_desc').val());	
	})
	
	
	/*get data to tblSubcategoryDetails table*/
	
	var tblSubcategoryDetails = $('#tblSubcategoryDetails').DataTable({
		
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		  {"className": "dt-center", "targets": "_all"},  
		  {"targets":0,"data":"work_desc_id", "visible":false},
		  {"targets":1,"data":"work_id"},
		  {"targets":2,"data":"year_desc"},
          {"targets":3,"data":"catg_desc"},
          {"targets":4,"data":"work_desc"},
          {"targets":5,"data":"cost_pu"},
          {"targets":6,"data": null, 
          	   "render":function(data){
          		   var buttons='';
          		   if(data.record_status == 1){
     			    	buttons= '<button type="button" action="delete" id="Delete" class="btn btn-default btn-sm btn-delete" title="Delete"><span class="glyphicon glyphicon-trash"></span></button>'+
     			    		'&nbsp;<button type="button" action="toggle" id="toggle" class="btn btn-success btn-sm" data-active="active" title="Click to Deactivate."><span class="glyphicon glyphicon-off"></span></button>';
             			   
             		   }else{
     			    	buttons= '<button type="button" action="delete" id="Delete" class="btn btn-default btn-sm btn-delete" title="Delete"><span class="glyphicon glyphicon-trash"></span></button>'+
     			    		'&nbsp;<button type="button" action="toggle" id="toggle" class="btn btn-danger btn-sm" data-active="inactive" title="Click to Activate."><span class="glyphicon glyphicon-off"></span></button>';
             		   }
          		   return buttons;
   			   }                  
             }                   
		  
       ]
	});
	
	var getDataToSubCategoryDetailsTable = function() {
		$.ajax({
			 url: "./mastersetup/getAllSubCategoryWorkDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 console.log(result);
				 tblSubcategoryDetails.clear();
				 tblSubcategoryDetails.rows.add(result.data).draw();
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	getDataToSubCategoryDetailsTable();
	
	function objectifyForm(formArray) {//serialize data function
	   var returnArray = {};
	   for (var i = 0; i < formArray.length; i++){
	     returnArray[formArray[i]['name']] = formArray[i]['value'];
	   }
	   return returnArray;
	 }
	
	var subCategoryFormValidate = $("#formSubcategoryDetails");
		
	subCategoryFormValidate.validate({
		rules: {	        	 
			finyr_id:{
				required: true,
			},   	 
			workcatg_id:{
				required: true
			},
			sub_work_desc:{
				required: true
			},
			cost_pu:{
				required: true
			}
		},
		messages:{	        	 
			finyr_id:{
				required: "Please select a financial year.",
			},   	 
			workcatg_id:{
				required: "Please select category.",
			},
			sub_work_desc:{
				required: "Please select subcategory.",
			},
			cost_pu:{
				required: "Cost per unit is required."
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

	$('#cost_pu').keypress(function(){
		$('#cost_pu').valid();
	});
	
	
	$("#subcategory_form_button").click(function(){
		$('#finyr_id').valid();
		$('#workcatg_id').valid();
		$('#sub_work_desc').valid();
		var isFormValid = subCategoryFormValidate.valid();
		if(isFormValid != false){
			var postData = $("#formSubcategoryDetails").serializeArray();
			console.log(postData);
			$.ajax({
				 url: "./mastersetup/saveSubcategoryDetailsToMasterWork.do",
				 type: "POST",
				 dataType:"JSON",
				 data: JSON.stringify(objectifyForm(postData)),
	             contentType:"application/json",
	             beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
		                if (data.success) {
		                	resetForm_CreateSubcategory();
		                	swal(data.msg);
		                	getDataToSubCategoryDetailsTable();
			              }
		            },
				 error:function(jqXHR, status, error){
				 	swal(error);
				 	resetForm_CreateSubcategory();
				 }
			});
		}
	});
	
	
	/*Method to update Subcategory Details*/
	
	var updateSubcategoryDetails = function updateSubcategoryDetails(){
		var postData = $('#formUpdateSubcategoryDetails').serializeArray();
		console.log(postData);
		$.ajax({
			 type: "POST",
			 url: "./mastersetup/updateSubcategoryDetails.do",
			 data: JSON.stringify(objectifyForm(postData)),
	         contentType:"application/json",
	         dataType:"JSON",
	         beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
				},
			 success: function(data) {
				 if (data.success) {
					 resetForm_CreateSubcategory();
	                	swal(data.msg);
	                	getDataToSubCategoryDetailsTable();
	                	
		            }else{
		            	resetForm_CreateSubcategory();
		            	  swal(data.msg);
		              }
	            },
		 error:function(jqXHR, status, error){
		 	swal(error);
		 }
	});
	}
	
	
	$('#tblSubcategoryDetails tbody').on( 'click', 'button[action=edit]', function () {
		var data = tblSubcategoryDetails.row( $(this).parents('tr') ).data(); 
		$('#update_work_id_hidden').val(data.work_id);
		$('#finyear').val(data.finyr_id);
		$('#category').val(data.workcatg_id);
		$('#subcategory_dd').val(data.work_desc_id);
		$('#cost_per_unit').val(data.cost_pu);
		$('#work_desc_hidden').val($("#subcategory_dd option:selected").text());
		$('#editSubcategoryModal').modal();
	});	

	$('#update_subcategory').on('click', function(){
		updateSubcategoryDetails();
		$('#editSubcategoryModal').modal('hide');
	})
	
	var deleteSubCategoryDetails = function deleteSubCategoryDetails(work_id){
        var data = {
        		work_id:work_id
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
    	        	deleteSubcategory(data);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
	}
	
	function deleteSubcategory(data){
		 $.ajax({
			 url: "./mastersetup/deleteSubcategoryDetails.do",
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
					 	resetForm_CreateSubcategory();
					 	getDataToSubCategoryDetailsTable();
				 }else{
				 	swal(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	$('#tblSubcategoryDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = tblSubcategoryDetails.row( $(this).parents('tr') ).data();
        deleteSubCategoryDetails(data.work_id);
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
					 	resetForm_CreateSubcategory();
	                	 $.alert(data.msg);
					 	getDataToSubCategoryDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
			 	resetForm_CreateSubcategory();
			 }
		});
	}
	
	
	$('#tblSubcategoryDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = tblSubcategoryDetails.row( $(this).parents('tr') ).data(); 
		var work_id = data.work_id;
		var toggle = $(this).data('active');
		var query = "subCategoryMasterSetup";
		
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
	            	 console.log(toggle, work_id, query)
	            	 toggleStatus(toggle, work_id, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	
	function resetForm_CreateSubcategory(){
		$("#formSubcategoryDetails")[0].reset();
		
	}
	
})