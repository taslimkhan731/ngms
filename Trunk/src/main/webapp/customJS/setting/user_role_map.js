$(document).ready(function() {
	$("#blockMapModal").on("hidden.bs.modal", function(){
		$('#blockForm')[0].reset();
		$('#allBlockLocationTable tbody').html('');
	});
	/******************************************
	 * Constructing group details table
	 ******************************************/	
	var table = $('#grpRoleParamMapTbl').DataTable({		
		"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"pageLength": 5,	
		"bFilter": false,
		"bLengthChange": false,
		"bDestroy": false,
		"paging": true,
		"scrollX": true,
		"scrollCollapse": true,
		"searching": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"},         
			{"targets":0,"data":"groupName"},
			{"targets":1,"data":"description"},
			{"targets":2,"data":"noOfUsers"},
			{"targets":3, "data": null, "defaultContent": '<button type="button" title="Map role" class="btn btn-sm btn-rolemap map"><i class="fa fa-user"></i></button>'},
			{"targets":4,"data": null, 
				render: function (data, type, row, meta) {
					console.log(data.groupId);
					if(data.groupId == 3){
						return '<button type="button" title="Map Location" class="btn btn-sm btn-parametermap map"><i class="fa fa-random"></i></button>'
					}else{
						return ''
					}
				}
			}  
			]
	});

	var getGroupDetails = function() {		
		$.ajax({
			"url": "./setting/getGroupDetails.do",
			"type": "POST",	
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				table.clear();
				table.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal("error");
			}
		});
	}		
	getGroupDetails();


	/*****************************************************
	 * Map role click on group details table
	 *****************************************************/

	var selectedGroupId = 0;
	$('#grpRoleParamMapTbl tbody').on('click', '.btn-rolemap', function () {
		var data = table.row( $(this).parents('tr') ).data();
		$('#groupRoleMapModal').modal('show');
		selectedGroupId = data.groupId;
		//(selectedGroupId);
	});

	/*******************************************************************
	 * Map Parameter click on group details table
	 *******************************************************************/
	var groupName ;
	$('#grpRoleParamMapTbl tbody').on('click', '.btn-parametermap', function () {
		var data = table.row( $(this).parents('tr') ).data();
		console.log(data)
		$('#groupParamMapModal').modal('show');
		selectedGroupId = data.groupId;

	});

	/***********************************************
	 * Construct Mapped parameter to usergroup table
	 ***********************************************/
	var mappedParamTbl = null;
	$('#groupParamMapModal').on('shown.bs.modal', function (e) {
		mappedParamTbl = $('#groupParamMapTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"bDestroy": true,
			"scrollX": true,
			"columnDefs":[
				{"className": "dt-center", "targets": "_all"},
				{"targets":0,"data":"userId"},
				{"targets":1,"data":"firstName"},
				{"targets":2,"data":"email"},
				{"targets": -1, "data": null, 
					render: function (data, type, row, meta) {
						console.log(data.locationId);
						if(data.locationId != 2){
						return '<button type="button" class="btn  btn-sm btn-getlocation btn-location mr-1" style="padding-left:10px !important,padding-right:10px !important"  title="Map Location"><i class="fa fa-map-marker"></i></button>' + 
						'<button type="button" class="btn btn-sm btn-viewlocation btn-view" title="View Location"><i class="fa fa-eye"></i></button>'
						}else{
							return '<button type="button" class="btn btn-secondary btn-sm btn-getlocation" title="Map Location"><i class="fa fa-map-marker"></i></button>'
						}
					}
				}
			]
		});	
		loadMappedParameters();
	})

	var loadMappedParameters = function() {	
		$.ajax({
			url: "./setting/getMappedParametersToGroup.do",
			type: "POST",	
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				mappedParamTbl.clear();
				mappedParamTbl.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal("error");
			}
		});
	}



	/************************************************
	 * construct mapped role to usergroup table
	 *************************************************/
	var table2 = null;
	$('#groupRoleMapModal').on('shown.bs.modal', function (e) {
		table2 = $('#groupRoleMapTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"columnDefs":[
				{"className": "dt-center", "targets": "_all"},
				{"targets":0,"data":"roleName"},
				{"targets": -1, "data": null, "defaultContent": '<button type="button" title="Delete" class="btn btn-sm btn-delete btn-dlt"><i class="fa fa-trash"></i></button>'}        
				]
		});	

		loadMappedRoles();	

	});

	var loadMappedRoles = function() {	
		var Data =
		{
				groupId: selectedGroupId
		};
		$.ajax({
			url: "./setting/getMappedRolesToGroup.do",
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
				console.log("datatable result__________"+result);
				table2.clear();
				table2.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal("error");
			}
		});
	}

	$('#groupRoleMapTbl tbody').on('click', '.btn-delete', function () {
		var data = table2.row( $(this).parents('tr') ).data();
		var Data =
		{
				groupId: selectedGroupId,
				roleId: data.roleId
		};
		$.ajax({
			url: "./setting/deleteMappedRole.do",
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
					loadMappedRoles();
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	});

	/*********************************************************************
	 * method is used to get all district and block details
	 * on click of action button of group param map
	 *********************************************************************/
	var mapUserId;
	var mapUserType;
	$('#groupParamMapTbl tbody').on('click', '.btn-getlocation', function () {
		var data = mappedParamTbl.row( $(this).parents('tr') ).data();
		mapUserId = data.userId;
		mapUserType = data.userType;
		console.log("selected group id__________"+selectedGroupId);
		/*selectedGroupId =  data.groupId;*/
		console.log("Selected groupID____"+selectedGroupId);
		getAllDistrictList();
		if(mapUserType == "ORGADMIN" || mapUserType == "APPROVER") {
			$('#parameterMapModal').modal('show');
		}else {
			$('#blockMapModal').modal('show');
		}
	})

	var tableLocationDetails = null;
	$('#parameterMapModal').on('shown.bs.modal', function (e) {
		tableLocationDetails = $('#allLocationTable').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"paging": true,
			"searching": true,
			"columnDefs":[		 
				{"targets":0,"data":"cityId"},
				{"targets":1,"data":"cityName"},
				{"targets":-1, "data": null, "defaultContent": '<button type="button" title="Add" class="btn btn-sm btn-secondary btn-locmaptouser btn-view"><i class="fa fa-plus"></i></button>'
					
				}
					
				]
		});
		$.ajax({
			url: "./setting/"+selectedGroupId+"/getAllLocationAccordingToRole.do",
			type: "GET",		
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result.data);
				tableLocationDetails.clear();
				tableLocationDetails.rows.add(result.data).draw();

			},
			error:function(jqXHR, status, error){
				(error);
			}
		});
	});

	//get all district details
	var getAllDistrictList = function getAllDistrictList() {	
		$.ajax({
			url: "./setting/getAllDistrict.do",
			type: "GET",	
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				var districtList = result.data;
				var option = '<option value="select">---SELECT----</option>';
				for(var i = 0;i<districtList.length;i++) {
					option += '<option value="'+ districtList[i].cityId + '">' + districtList[i].cityName + '</option>';
				}
				$('#district_dpdn').html("");
				$('#district_dpdn').empty();
				$('#district_dpdn').append(option);
			},
			error:function(jqXHR, status, error){
				(error);
			}
		});
	}

	var tableBlockLocationDetails;
	$("#district_dpdn").change(function() {
		console.log("dropdown__________");
		var districtcode = $('#district_dpdn').val();
		tableBlockLocationDetails = $('#allBlockLocationTable').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"paging": true,
			"searching": true,
			"columnDefs":[	
				{"targets":0,"data":"cityId"},
				{"targets":1,"data":"cityName"},
				{"targets": -1, "data": null, "defaultContent": '<button type="button" title="Add" class="btn btn-secondary btn-sm btn-locmaptouser btn-view"><i class="fa fa-plus"></i></button>'}
				]
		});
		$.ajax({
			url: "./setting/"+districtcode+"/getAllBlockLocationAccordingToRole.do",
			type: "GET",		
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result.data);
				tableBlockLocationDetails.clear();
				tableBlockLocationDetails.rows.add(result.data).draw();

			},
			error:function(jqXHR, status, error){
				(error);
			}
		});

	});


	$('#allBlockLocationTable tbody').on('click', '.btn-locmaptouser', function () { 
		console.log("for adding block");
		var data = tableBlockLocationDetails.row( $(this).parents('tr') ).data();
		var cityId = data.cityId;
		$.ajax({
			url: "./setting/"+cityId+"/"+mapUserId+"/"+mapUserType+"/mapLocationToUserForBlock.do",
			type: "GET",
			dataType: "json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log("result for block______________"+result);
				if(result.status=='Success'){
				loadMappedParameters();
				swal(result.msg,"","success");
				$('#blockMapModal').modal('hide');
				}else{
					swal(result.msg,"","success");
				}
			}
		});
	})


	$('#allLocationTable tbody').on('click', '.btn-locmaptouser', function () { 
		var data = tableLocationDetails.row( $(this).parents('tr') ).data();
		//console.log(data);
		var cityId = data.cityId;
		///swal(mapUserId)
		$.ajax({
			url: "./setting/"+cityId+"/"+mapUserId+"/"+mapUserType+"/mapLocationToUser.do",
			type: "GET",
			dataType: "json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				console.log(result);
				if(result.status=='Success'){
				$('#loading').hide();
				loadMappedParameters();
				swal(result.msg,"","success");
				$('#parameterMapModal').modal('hide');
				}else{
					swal(result.msg,"","success");
					$('#loading').hide();
				}
			}
		});


	})

	/****************************************
	 * add role button click on Assigned Role Modal
	 ***************************************/
	$("#btn_addrolestogroup").click(function (){
		$('#roleMapModal').modal('show');

	});

	var table3 = null;
	$('#roleMapModal').on('shown.bs.modal', function (e) {
		table3 = $('#modalRolesTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"paging": true,
			"searching": true,
			"columnDefs":[		 
				{"targets":0, "data": null,
					'className': 'dt-body-center',
					'render': function (data, type, full, meta){
						return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
					}
				},
				{"targets":1,"data":"roleId"},
				{"targets":2,"data":"roleName"},
				{"targets":3,"data":"description"},
				{"targets": -1, "data": null, "defaultContent": '<button type="button" title="View" class="btn btn-sm btn-preview btn-view"><i class="fa fa-eye"></i></button>'}
				]
		});

		$.ajax({
			url: "./setting/getRoleList.do",
			type: "POST",		
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				table3.clear();
				table3.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				(error);
			}
		});

	});

	// Handle click on "Select all" control
	$('#roles-select-all').on('click', function() {
		// Get all rows with search applied
		var rows = table3.rows({
			'search' : 'applied'
		}).nodes();
		// Check/uncheck checkboxes for all rows in the table
		$('input[type="checkbox"]', rows).prop('checked', this.checked);
	});

	// Handle click on checkbox to set state of "Select all" control
	$('#modalRolesTbl tbody').on('change', 'input[type="checkbox"]', function() {
		// If checkbox is not checked
		if (!this.checked) {
			var el = $('#roles-select-all').get(0);
			// If "Select all" control is checked and has 'indeterminate' property
			if (el && el.checked && ('indeterminate' in el)) {
				// Set visual state of "Select all" control as 'indeterminate'
				el.indeterminate = true;
			}
		}
	});

	$("#btn_savemappedroles").click(function() {			
		var selectedRoles = [];
		table3.$('input[type="checkbox"]').each(function(){
			if(this.checked){
				var data = table3.row( $(this).parents('tr') ).data();		
				selectedRoles.push(data);					    	
			}	         
		});	      

		if(selectedRoles.length>0){	    	
			$.ajax({
				url: "./setting/mapRoleToGroup.do?groupId="+selectedGroupId,
				type: "POST",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				data: JSON.stringify(selectedRoles),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success:function(result) {
					$('#loading').hide();
					if(result.success){
						$('#roleMapModal').modal('hide');
						loadMappedRoles();
					}
					swal(result.msg,"","success");
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
				}
			});
		}else{
			("Please select roles for mapping..............")
		}
	});

	var selectedRoleId = 0;
	$('#modalRolesTbl tbody').on('click', '.btn-preview', function () {		
		var data = table3.row( $(this).parents('tr') ).data();
		$('#mappedResourcesModal').modal('show');
		selectedRoleId = data.roleId;
	});
	var table5 = null;
	$('#mappedResourcesModal').on('shown.bs.modal', function (e) {
		table5 = $('#mappedResourcesTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"paging": true,
			"searching": true,
			"columnDefs":[		 	
				{"targets":0,"data":"resourceName"},
				{"targets":1,"data":"resourceType"}
				]
		});

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
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				console.log(result);
				table5.clear();
				table5.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal("error");
			}
		});
	});
	
	var userId;
	var userType;
	$('#groupParamMapTbl tbody').on('click', '.btn-viewlocation', function () {
		var data = mappedParamTbl.row( $(this).parents('tr') ).data();
		userId = data.userId;
		userType = data.userType;
		$('#ghViewModel').modal('show');
	})
	
	var tableLocatedDetails = null;
	$('#ghViewModel').on('shown.bs.modal', function (e) {
		tableLocatedDetails = $('#allLocatedTable').DataTable({			
			"lengthMenu": [[6, 12, 18, -1], [6, 12, 18, "All"]],
			"pageLength": 6,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"paging": true,
			"searching": true,
			"columnDefs":[		 
				{"targets":0,"data":"cityId"},
				{"targets":1,"data":"cityName"},
				{"targets": -1, "data": null, "defaultContent": '<button type="button" title="Delete" class="btn btn-sm btn-delmaptouser btn-dlt" style="padding:5px 7px 5px 7px !important"><i class="fa fa-trash"></i></button>'}
				]
		});
		getAllLocation(userId, userType);
	});
	
	$('#allLocatedTable tbody').on('click', '.btn-delmaptouser', function () {
		var data = tableLocatedDetails.row( $(this).parents('tr') ).data();
		$.ajax({
			url: "./setting/deleteLoc/" + data.id + ".do",
			type: "GET",		
			contentType : 'application/json',
			dataType: "json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				if(result.success){
					swal(result.msg,"","success");
					getAllLocation(data.userId);
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	})
	
	//get all district details
	var getAllLocation = function getAllLocation(userId, userType) {
		$.ajax({
			url: "./setting/"+userId+ "/" + userType +"/getAllLocationAccordingToUser.do",
			type: "GET",		
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result.data);
				tableLocatedDetails.clear();
				tableLocatedDetails.rows.add(result.data).draw();

			},
			error:function(jqXHR, status, error){
				(error);
			}
		});
	}
	
});