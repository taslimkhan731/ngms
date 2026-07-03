$(document).ready(function(){
	
//	https://css-tricks.com/snippets/javascript/javascript-keycodes/
	$.validator.addMethod('charname', function (value, element) {
		return value.match(/^[0-9 a-z A-z-'_]*$/);
	});
	
	var table = $('#tblBlockDetails').DataTable({
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		   {"className": "dt-center", "targets": "_all"},  
		   /*{"targets":0,"data":"id"},*/
           {"targets":0,"data":"district_name"},
           {"targets":1,"data":"block_id"},
           {"targets":2,"data":"block_name"},
           /*{"targets":3,"data":"rural_urban"},*/
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
	
	$('#editBlockModal').on('hidden.bs.modal', function(){
		var validator = $( "#formUpdateBlockDetails" ).validate();
		 validator.resetForm();
	})
	
	var DistrictName = function() {
		$.ajax({
			 url: "./mastersetup/getDistrictDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			success: function(result)
            {	
				var option = '<option value="">Select Any One</option>'
				result.data.forEach(function(data){
					console.log(data);
                	option += '<option value="'+data.district_id+'">'+data.district_name+'</option>'
                });
            $("#district_id").html("");
            $("#district_id").append(option);
            },
		    failure: function () {
		        swal("Failed!");
		    }
			 
		});
	}
	DistrictName();
	
	var getDataToBlockDetailsTable = function() {
		$.ajax({
			 url: "./mastersetup/getBlockDetails.do",
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
	
	getDataToBlockDetailsTable();
	
	function objectifyForm(formArray) {//serialize data function

	   var returnArray = {};
	   for (var i = 0; i < formArray.length; i++){
	     returnArray[formArray[i]['name']] = formArray[i]['value'];
	   }
	   return returnArray;
	 }
	
	var blockFormValidate = $("#formBlockDetails");
	
	blockFormValidate.validate({
		rules: {	        	 
			block_id:{
				required: true,
				maxlength: 6,
				minlength: 6,
				onlyNumber:true
			},   	 
			block_name:{
				required: true,
				maxlength: 50,
				charname:true
			},
			district_id:{
				required: true
			}
		},
		messages:{        	 
			block_id:{
				required: "Block Id is required.",
				onlyNumber:"Only numbers allowed. ",
				maxlength: "Block Id must be of 6 digits."
			},   	 
			block_name:{
				required: "Block name is required.",
				maxlength: "Block name must be within 50 charchter.",
				charname:"Block name contain only (-_')special char"
			},
			district_id:{
				required: "Please select a district.",
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

	$('#block_id').keypress(function(){
		$('#block_id').valid();
	});

	$('#block_name').keypress(function(){
		$('#block_name').valid();
	});
	
	$('#district_id').keypress(function(){
		$('#district_id').valid();
	});
	$("#block_form_button").click(function(){
		$('#district_id').valid();
		var isBlockFormValid = blockFormValidate.valid();
		if(isBlockFormValid != false){
			saveBlock();
		}
	});
	
	var saveBlock = function saveBlock() {
		var postData = $("#formBlockDetails").serializeArray();
		console.log(postData);
		$.ajax({
			 url: "./mastersetup/saveBlockDetails.do",
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
	                	resetForm_CreateBlock();
	                	swal(data.msg);
	                	getDataToBlockDetailsTable();
	                }else{
	                	swal(data.msg);
	                }
	            },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 	resetForm_CreateBlock();
			 }
		});
		}
	
	$('#district_id').on('change', function(){
		$('#block_id').val($(this).val());
		$('#id-inst').css("display", "block");
		$('#id-inst').html('Enter last two digits of block id.');
	})
	$('#block_id').on('keydown', function(key){
		var keycode = (key.which) ? key.which : key.keyCode;
		if($(this).val().length == 6){
			$('#id-inst').css("display", "none");
		}	
		if($(this).val().length <= 4){
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
	

	/*Method to update Block Details*/
	$.validator.addMethod('onlyNumber', function (value, element) {
		return value.match(/^[0-9]*$/);
	});
	var blockUpdateFormValidate = $("#formUpdateBlockDetails");
	
	blockUpdateFormValidate.validate({
		rules: {	        	 
			block_id:{
				required: true,
				maxlength: 6,
				minlength:6,
				onlyNumber:true
			},   	 
			block_name:{
				required: true,
				maxlength: 50,
				charname:true
			},
			district_id:{
				required: true
			}
		},
		messages:{        	 
			block_id:{
				required: "Block Id is required.",
				maxlength: "Block Id must be of 6 digits.",
				minlength:"Block Id must be of 6 digits.",
				onlyNumber:"Block id consist of only number"
			},   	 
			block_name:{
				required: "Block name is required.",
				maxlength: "Block name within 50 charchter.",
				charname:"Block name contain only (-_')special char"
			},
			district_id:{
				required: "Please select a district.",
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

	$('#block_id1').keypress(function(){
		$('#block_id1').valid();
	});

	$('#block_name1').keypress(function(){
		$('#block_name1').valid();
	});


	
	$('#update-block').on('click', function(){
		var isBlockUpdateFormValid = blockUpdateFormValidate.valid();
		if(isBlockUpdateFormValid != false){
			updateBlockDetails();
		}
	})
	var updateBlockDetails = function updateBlockDetails(){
		var postData = $('#formUpdateBlockDetails').serializeArray();
		console.log(postData);
		$.ajax({
			 type: "POST",
			 url: "./mastersetup/updateBlockDetails.do",
			 data: JSON.stringify(objectifyForm(postData)),
	         contentType:"application/json",
	         dataType:"JSON",
	         beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
				},
			 success: function(data) {
				 
				 if (data.success) {
					 	resetForm_CreateBlock();
	                	swal(data.msg);
	                	$('#editBlockModal').modal('hide');
	                	getDataToBlockDetailsTable();
	                	
		            }else{
		            	$('#editBlockModal').modal('hide');
		            	resetForm_CreateBlock();
		            	  swal(data.msg);
		              }
	            },
		 error:function(jqXHR, status, error){
		 	swal(error);
		 }
	});
	}
	
	
	$('#tblBlockDetails tbody').on( 'click', 'button[action=edit]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		$('#district_id1').attr('readonly','readonly');
		$('#district_id1').val(data.district_id);
		console.log(data.block_id)
		$('#block_name1').val(data.block_name);
		$('#block_id1').val(data.block_id);
		$('#editBlockModal').modal();

		$('#block_id1').on('keydown', function(key){
			if($(this).val().length <= 4){
				var keycode = (key.which) ? key.which : key.keyCode;
		        if (keycode == 8){
		        	key.preventDefault();
		        	return false;
		        }
			}
		})
	});	
	
	var deleteBlockDetails = function deleteBlockDetails(block_id){
        var data = {
        		block_id:block_id
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
    	        	deleteBlock(data);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
       
	}
	
	function deleteBlock(data){
		 $.ajax({
			 url: "./mastersetup/deleteBlock.do",
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
				 	getDataToBlockDetailsTable();
				 }else{
				 	swal(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	swal(error);
			 }
		});
	}
	
	$('#tblBlockDetails tbody').on( 'click', 'button[action=delete]', function () {
        var data = table.row( $(this).parents('tr') ).data();
		deleteBlockDetails(data.block_id);
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
	                	resetForm_CreateBlock();
	                	 $.alert(data.msg);
	                	 getDataToBlockDetailsTable();
		              }
	            },
			 error:function(jqXHR, status, error){
				 $.alert(error);
				 resetForm_CreateBlock();
			 }
		});
	}
	
	
	$('#tblBlockDetails tbody').on( 'click', 'button[action=toggle]', function () {
		var data = table.row( $(this).parents('tr') ).data(); 
		var blockId = data.block_id;
		var toggle = $(this).data('active');
		var query = "blockMasterSetup";
		
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
	            	 console.log(toggle, blockId, query)
	            	 toggleStatus(toggle, blockId, query);
	             }
	         },
	    	cancel: function () {
	    		btnClass: 'btn-red'
	    		$.alert('Cancelled!');
	    	},
    	    }
    	});
		
	});
	
	function resetForm_CreateBlock(){
		$("#formBlockDetails")[0].reset();
		
	}
	
	
});