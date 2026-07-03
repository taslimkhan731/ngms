$('document').ready(function(){
	
	
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
	
	
	$('#sub_work_desc').on('change', function(){
		var work_descId = $('#sub_work_desc').val();
		$('#work_desc_id').val(work_descId);
		$('#work_desc').val($("#sub_work_desc option:selected").text());
		console.log($('#work_desc').val());	
	})
	
	
	/*get data to tblSubcategoryDescDetails table*/
	
	var tblSubcategoryDescDetails = $('#tblSubcategoryDescDetails').DataTable({
		
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		  {"className": "dt-center", "targets": "_all"},  
          {"targets":0,"data":"workcatg_id"},
          {"targets":1,"data":"work_desc"},
          {"targets":2,"data":"work_desc_id"},
          {"targets":3,"data": null, 
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
		 url: "./mastersetup/getAllSubcategoryFormMasterWorkDesc.do",
		 type: "POST",			
		 beforeSend: function(xhr) {
               xhr.setRequestHeader(header, token);
		},
		 success:function(result) {
			 console.log(result.data);
			 var data = result.data;
			 tblSubcategoryDescDetails.clear();
			 tblSubcategoryDescDetails.rows.add(result.data).draw();
		 },
		 error:function(jqXHR, status, error){
		 	swal(error);
		 }
	});}
	
	getDataToSubCategoryDetailsTable();
	
	function objectifyForm(formArray) {//serialize data function

		   var returnArray = {};
		   for (var i = 0; i < formArray.length; i++){
		     returnArray[formArray[i]['name']] = formArray[i]['value'];
		   }
		   return returnArray;
		 }
	
	var subCatDescFormValidate = $("#formWorkDescDetails");
	
	subCatDescFormValidate.validate({
		rules: {  	 
			work_desc:{
				required: true
			},	        	 
			workcatg_id:{
				required: true,
			}
		},
		messages:{  	 
			work_desc:{
				required: "Please enter subcategory name."
			},	        	 
			workcatg_id:{
				required: "Please select category.",
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

	$('#work_desc').keypress(function(){
		$('#work_desc').valid();
	});
	
	
	$("#subcatdesc_form_button").click(function(){
		$('#workcatg_id').valid();
		var issubCatFormValid = subCatDescFormValidate.valid();
		if(issubCatFormValid != false){
			saveSubCategorydescDetails();
		}
	});
	
	function saveSubCategorydescDetails(){
		var postData = $("#formWorkDescDetails").serializeArray();
		console.log(postData);
		$.ajax({
			 url: "./mastersetup/saveSubcategoryDescriptionToMasterWorkDesc.do",
			 type: "POST",
			 dataType:"JSON",
			 data: JSON.stringify(objectifyForm(postData)),
             contentType:"application/json",
             beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
  			},
			 success: function(data, textStatus, jqXHR) {
	                if (data.success) {
	                	resetForm_SubcatDesc();
	                	swal(data.msg);
	                	getDataToSubCategoryDetailsTable();
		              }else{
		                	swal(data.msg);
		              }
	            },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 	resetForm_SubcatDesc();
			 }
		});
	}

	var deleteSubCategoryDescDetails = function deleteSubCategoryDetails(work_desc_id){
        var data = {
        		work_desc_id:work_desc_id
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
    	        	deleteSubcategoryDesc(data);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
	}
	
	function deleteSubcategoryDesc(data){
		$.ajax({
			 url: "./mastersetup/deleteSubcategoryDescDetails.do",
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
					 	resetForm_SubcatDesc();
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
	
	$('#tblSubcategoryDescDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = tblSubcategoryDescDetails.row( $(this).parents('tr') ).data();
        deleteSubCategoryDescDetails(data.work_desc_id);
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
					 	resetForm_SubcatDesc();
	                	 $.alert(data.msg);
					 	getDataToSubCategoryDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
			 		resetForm_SubcatDesc();
			 }
		});
	}
	
	
	$('#tblSubcategoryDescDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = tblSubcategoryDescDetails.row( $(this).parents('tr') ).data(); 
		var catId = data.work_desc_id;
		var toggle = $(this).data('active');
		var query = "subCatDescMasterSetup";
		
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
	            	 console.log(toggle, catId, query)
	            	 toggleStatus(toggle, catId, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	
	
	function resetForm_SubcatDesc(){
		$("#formWorkDescDetails")[0].reset();
		
	}
	
})