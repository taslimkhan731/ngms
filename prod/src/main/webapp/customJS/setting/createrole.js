$(document).ready(function(){
	isEdit = false;
	var type = "";
	var table = $("#UserRoleDetails").DataTable({   	
		"paging":  true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": false,
		"scrollX": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"},
			{"targets":0,"data":"role_id"},
			{"targets":1,"data":"role_name"},
			{"targets":2,"data":"description"},
			{ "targets":3,
				"data" : null,
				"defaultContent" : '<button type="button" title="Map resource" class="btn btn-sm btn-map-resource map"><i class="fa fa-random"></i></button>'
			},
			{"targets":4,"data": null, 
				"defaultContent": '<button type="button"   action="Delete" id="Delete" title="Delete" class="btn btn-sm btn-dlt"><span class="fa fa-trash"></span></button>'+
				'<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-sm btn-edit ml-1 "><i class="fa fa-edit"></i></button>'}             

			],

	});
	getRoleDetails(table);


	$('#new_user_form').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields : {
			role_name: {
				validators : {
                    stringLength: {
                        min: 3,
                        max: 20,
                        message: 'The answer must be more than 2 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'The role name must not be empty'
					},
					regexp : {
						regexp :/^[a-z ,.'-]+$/i,
						message : 'City name must contain only letters'
					}
				}
			},
			description: {
				validators : {
					stringLength: {
                        min: 3,
                        max: 20,
                        message: 'Description must be more than 2 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'Description name must not be empty'
					},
					regexp : {
						regexp : /^[a-z ,.'-]+$/i,
						message : 'Description name must contain only letters'
					}
				}
			},
			
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitRoleDetails();
    });
	/********************************************
	 *    Form Data to the Create Group table
	 ***********************************************/
	var submitRoleDetails = function () {
	
//		swal("clicked");
		if(isEdit){
			type = "UPDATE"
//				swal(type);	
		}else{
			type = "ADD"
//				swal(type);	
		}
		var role_name = $("#role_name").val();
		var description= $("#description").val();

		var id= $("#role_id").val();

		var Data =
		{
				roleName:role_name,
				description:description,
				type:type,
				id:id
		};

		/*if(role_name==''){
			swal("Please Enter Resource Name!");
			$("#role_name").focus();
		}
		else if(description==''){
			swal("Please Enter Description!");
			$("#description").focus();
		}else{*/
			$.ajax({
				url: "./setting/saveUserRoleTable.do",
				type: "POST",
//				data:{role_name:role_name,description:description,type:type,id:id},
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
						resetFormCreateRole();
						getRoleDetails(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateRole();
						getRoleDetails(table);
					}
				},
				error:function(jqXHR, status, error){
					swal(error);
					resetFormCreateRole();
					getRoleDetails(table);
				}
			});
		}

	/*********************************************
	 * Edit button click function
	 *********************************************/
	$('#UserRoleDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('77789')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#role_name").val(data.role_name); 
		$("#description").val(data.description);
		$("#role_id").val(data.role_id);
		$('#add_User_Role').html('Update');
	});  


	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserRoleDetails tbody').on( 'click', 'button[action=Delete]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				roleId: data.role_id
		};
		$.ajax({
			url: "./setting/deleteCreateUserRole.do",
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
					getRoleDetails(table);
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	});

	var table2 = null;
	$('#mappedResourcesModal').on('shown.bs.modal', function (e) {
		table2 = $('#mappedResourcesTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"bDestroy": true,
			"paging": true,
			"scrollX": true,
			"searching": true,
			"columnDefs":[		 	
				{"targets":0,"data":"resourceName"},
				{"targets":1,"data":"resourceType"},	        	
				{"targets": -1, "data": null, "defaultContent": '<button type="button" title="Delete" class="btn btn-sm btn-delete btn-dlt"><i class="fa fa-trash"></i></button>'}
				]
		});
		loadMappedResources();
	});
	var loadMappedResources = function(){
		var Data =
		{
				roleId: selectedRoleId
		};
		$.ajax({
			url: "./setting/getMappedResources.do",
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
				//console.log(result);
				table2.clear();
				table2.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});	
	}
	var selectedRoleId=0, selectedRoleName='';
	$('#UserRoleDetails tbody').on('click', '.btn-map-resource', function () {
		var data = table.row( $(this).parents('tr') ).data();
		$('#mappedResourcesModal').modal('show');
		selectedRoleId=data.role_id;
		selectedRoleName=data.role_name;
		$('#roleNameMapScr').val(selectedRoleName);
	});

	$('#mappedResourcesTbl tbody').on('click', '.btn-delete', function () {
		var data = table2.row( $(this).parents('tr') ).data();
		var Data =
		{
				roleId: selectedRoleId,
				resource_id: data.resourceId
		};
		$.ajax({
			url: "./setting/deleteMappedResources.do",
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
					loadMappedResources();
				}else{
					swal(result.msg,"","success");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	});

	$("#btn_addresources").click(function() {	
		$('#chooseResourcesModal').modal('show');
	});

	var table3 = null;		
	$('#chooseResourcesModal').on('shown.bs.modal', function (e) {
		table3 = $('#resourcesTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"bDestroy": true,
			"paging": true,
			"scrollX": true,
			"searching": true,
			"columnDefs":[		 
				{"targets":0, "data": null,
					'className': 'dt-body-center',
					'render': function (data, type, full, meta){
						return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
					}
				},
				{"targets":1,"data":"resourceName"},
				{"targets":2,"data":"resourceType"}
				]
		});
		var Data =
		{
				roleId: selectedRoleId
		};
		$.ajax({
			url: "./setting/getUnselectedResourceList.do",
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
				table3.clear();
				table3.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	});


	// Handle click on "Select all" control
	$('#resources-select-all').on('click', function() {
		// Get all rows with search applied
		var rows = table3.rows({
			'search' : 'applied'
		}).nodes();
		// Check/uncheck checkboxes for all rows in the table
		$('input[type="checkbox"]', rows).prop('checked', this.checked);
	});

	// Handle click on checkbox to set state of "Select all" control
	$('#resourcesChooseUsersTbl tbody').on('change', 'input[type="checkbox"]', function() {
		// If checkbox is not checked
		if (!this.checked) {
			var el = $('#resources-select-all').get(0);
			// If "Select all" control is checked and has 'indeterminate' property
			if (el && el.checked && ('indeterminate' in el)) {
				// Set visual state of "Select all" control as 'indeterminate'
				el.indeterminate = true;
			}
		}
	});

	$("#btn_savemapresouces").click(function() {			
		var selectedResources = [];
		table3.$('input[type="checkbox"]').each(function(){
			if(this.checked){
				var data = table3.row( $(this).parents('tr') ).data();		
				selectedResources.push(data);					    	
			}	         
		});	      

		if(selectedResources.length>0){	    	
			$.ajax({
				url: "./setting/mapResourcesToRole.do?roleId="+selectedRoleId,
				type: "POST",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				data: JSON.stringify(selectedResources),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success:function(result) {
					$('#loading').hide();
					if(result.success){
						$('#chooseResourcesModal').modal('hide');
						loadMappedResources();
					}
					swal(result.msg,"","success");
				},
				error:function(jqXHR, status, error){
					swal(error);
				}
			});
		}else{
			swal("Please select resources for mapping.......")
		}
	});

})

function getRoleDetails(table){
	//swal('hii')
	$.ajax({
		url: "./setting/getRoleDetails.do",
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


var resetFormCreateRole = function(){
	isEdit = false;
	$('#add_User_Role').html('Add');
	$("#role_name").val(''); 
	$("#description").val('');
	$("#role_id").val('123'); //default value

}
