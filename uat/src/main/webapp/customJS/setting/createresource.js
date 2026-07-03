$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserResourceDetails").DataTable({   	
		"paging":  true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": true,
		"bFilter": true,
		"scrollX": true,
		"searching": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"}, 
			{"targets":0,"data":"resource_id"},
			{"targets":1,"data":"resource_name"},
			{"targets":2,"data":"resource_type"}, 
			{"targets":3,"data":"description"},
			{"targets":4,"data": null, 
				"defaultContent": '<button type="button" action="deleteResource" title="Delete" id="deleteResource" class="btn btn-sm btn-dlt"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-sm ml-1 btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],

	});
	getResourceTableData(table);


	$('#new_user_form').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields : {
			resource_name: {
				validators : {
                    stringLength: {
                        min: 3,
                        max: 50,
                        message: 'The answer must be more than 2 and less than 50 characters long'
                    },
					notEmpty : {
						message : 'The resource name must not be empty'
					},
				}
			},
			resource_type:{
                validators: {
                    notEmpty: {
                        message: 'select a valid option'
                    }
                }
            },
			descriptaion: {
				validators : {
                    stringLength: {
                        min: 3,
                        max: 100,
                        message: 'Description must be more than 2 and less than 100 characters long'
                    },
                    regexp : {
						regexp : /^[A-Za-z\s]*$/,
						message : 'Descriptaion must contain only letters'
					},
					notEmpty : {
						message : 'Descriptaion must not be empty'
					}	
				}
			},
			
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitResourceDetails();
    });
	
	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/
	var submitResourceDetails = function () {
//		swal("Add button click.....................");
		if(isEdit){
			type = "UPDATE"
//				swal(type);
		}else{
			type = "ADD"
//				swal(type);	
		}

		var resourcename = $("#resource_name").val();

		var resourceType = $("#resource_type").val();
		//swal(resourceType);
		var description= $("#descriptaion").val();
		var id= $("#resourceId").val();

		var Data =
		{
				resource_name:resourcename,
				resource_type:resourceType,
				description:description,
				type:type,
				id:id
		};

		/*if(resourcename==''){
			swal("Please Enter Resource Name!");
			$("#resource_name").focus();
		}
		else if(description==''){
			swal("Please Enter Description!");
			$("#descriptaion").focus();
		}else{*/
			$.ajax({
				url: "./setting/saveUserResourceTable.do",
				type: "POST",
				contentType : 'application/json',
				dataType: "json",
				data: JSON.stringify(Data),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success:function(result) {
					$('#loading').hide();
					if(result.success){
						swal(result.msg,"","success");
						$('#new_user_form').bootstrapValidator('resetForm', true);
						resetFormCreateResource();
						getResourceTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateResource();
						getResourceTableData(table);	
					}
				},
				error:function(jqXHR, status, error){
					swal(error);
				}
			});
		}

	/*********************************************
	 * Edit button click function
	 *********************************************/
	$('#UserResourceDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#resource_name").val(data.resource_name); 
		$("#resource_type").val(data.resource_type);
		$("#descriptaion").val(data.description);
		$("#resourceId").val(data.resource_id);
		$('#addResource').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserResourceDetails tbody').on( 'click', 'button[action=deleteResource]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				resource_id: data.resource_id
		};
		$.ajax({
			url: "./setting/deleteCreateUserResource.do",
			type: "POST",		
			contentType : 'application/json',
			dataType: "json",
			data: JSON.stringify(Data),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				if(result.success){
					getResourceTableData(table);
					swal(result.msg,"","success");
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	});
})

function getResourceTableData(table){
	//swal('hii')
	$.ajax({
		url: "./setting/getDataResourceTable.do",
		type: "POST",		
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			table.clear().draw();
			table.rows.add(result.data).draw();
		}
	});
}

var resetFormCreateResource = function(){
	$('#addResource').html('Add');
	isEdit = false;
	$("#resource_name").val(''); 
	$("#descriptaion").val('');
	$("#resourceId").val('123'); //default value
}


