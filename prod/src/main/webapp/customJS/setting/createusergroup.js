$(document).ready(function(){
	
	var isEdit = false;
	var type="";
	/****************************************************
	 * dynamic table creation in usergroup module
	 *****************************************************/
	var table = $('#tblUserGroupDetails').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"pageLength": 5,	
		"bFilter": false,
		"bLengthChange": false,
		"scrollX": true,
		"bDestroy": true,
		"paging": true,
		"scrollX": true,
		"searching": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"},
			{"targets":0,"data":"groupId"},
			{"targets":1,"data":"groupName"},
			{"targets":2,"data":"description"},
			{"targets":3,"data": "noOfUsers"},
			{"targets":4, "data": null, "defaultContent": '<button type="button" title="Map User" class="btn btn-sm btn-usermap map"><i class="fa fa-user"></i></button>'},
			{"targets":5,"data": null, 
				"defaultContent": '<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-sm btn-edit"><i class="fa fa-edit"></i></button>'+ 
				'<button type="button" action="Delete" id="Delete" title="Delete" class="btn btn-delete btn-sm btn-dlt ml-1 "><i class="fa fa-trash"></i></button>'
			} 
			]

	});

	/***************************************************
	 * Get the details to table in usergroup module
	 * url is present in AccessControllerController 
	 * Two table is used admin.group and admin.usergroup
	 ***************************************************/

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
				swal(error,"","error");
			}
		});
	}		
	getGroupDetails();
	
	$('#formGroupDetails').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields : {
			groupName: {
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
						regexp : /^[A-Za-z\s]*$/,
						message : 'City name must contain only letters'
					}
				}
			},
			description: {
				validators : {
					stringLength: {
                        min: 3,
                        max: 20,
                        message: 'The answer must be more than 2 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'Description name must not be empty'
					},
					regexp : {
						regexp : /^[a-z ,.'-]+$/i,
						message : 'description name must contain only letters'
					}
				}
			},
			
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitGroupDetails();
    });

	/*******************************************************************************************
	 * This method url is present in AccessControllerController.java class
	 * Table Used admin.group
	 * taking the input value FormData
	 ********************************************************************************************/

	//var saveOrUpdateUserGroup = function saveOrUpdateUserGroup(type) {
	var submitGroupDetails = function () {
		//var type = "";	
		if(isEdit){
			type = 'UPDATE';
		}else{
			type = 'ADD'
		}
			
		var grpName = $("#groupName").val();
		var description = $("#description").val();
		var postData = $("#formGroupDetails").serializeArray();
		console.log(postData);

		if(type=="ADD"){
			$.ajax({
				url: "./setting/saveGroupDetails.do",
				type: "POST",
				data: postData,
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(data, textStatus, jqXHR) {
					$('#loading').hide();
					if (data.success) {
						swal(data.msg,"","success");
						$("input[name=groupName]").val('');
						$("#description").val('');
						$('#formGroupDetails').bootstrapValidator('resetForm', true);
						$('#userGroup_form_button').html('Add');
						getGroupDetails();
					}
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
				}
			});
		}
		else if(type=="UPDATE"){
			$.ajax({
				url: "./setting/updateGroupDetails.do",
				type: "POST",
				data: postData,
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(data, textStatus, jqXHR) {
					$('#loading').hide();
					if (data.success) {
						swal(data.msg,"","success");
						$("input[name=groupName]").val('');
						$("#description").val('');
						$('#formGroupDetails').bootstrapValidator('resetForm', true);
						$('#userGroup_form_button').html('Add');
						getGroupDetails();
					}
				},
				error:function(jqXHR, status, error){
					swal(error,"","error");
				}
			});
		}
		else{
			swal("error","error");
		}

	}


	/********************************************************
	 * function to push form submit on button submit event
	 *******************************************************/
/*	$("#userGroup_form_button").click(function() {
		var type = "";	
		if(isEdit){
			type = 'UPDATE';
		}else{
			type = 'ADD'
		}
		console.log(type);
		var grpName = $("#groupName").val();
		var description = $("#description").val();

		if($("#groupName").val()==''){
			swal("Please Enter Your Group Name");
			$("#groupName").focus();
		}
		else if($("#description").val()==''){
			swal("Enter Description");
			$("#description").focus();
		}
		else{
			saveOrUpdateUserGroup(type);
		}
	})
*/
	/*********************************************
	 * Edit button click function
	 *********************************************/


	$('#tblUserGroupDetails tbody').on( 'click', 'button[action=Edit]', function () {		
		var data = table.row( $(this).parents('tr') ).data();
		$('#grpId').val(data.groupId);
		$('#groupName').val(data.groupName);
		$('#description').val(data.description);
		$('#userGroup_form_button').html('Update');
		isEdit = true;
	})

	/********************************************************
	 * function to delete button click event
	 *******************************************************/

	$('#tblUserGroupDetails tbody').on('click', '.btn-delete', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data = {
				groupId: data.groupId
		}
		$.ajax({
			url: "./setting/deleteGroup.do",
			type: "POST",	
			dataType: "json",
			contentType:"application/json",
			data: JSON.stringify(Data),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				if(result.success){
					swal(result.msg,"","success");
					getGroupDetails();
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error,"","error");
			}
		});
	});	

	//MAPPING DETAILS
	$('#tblUserGroupDetails tbody').on('click', '.btn-usermap', function () {
		var data = table.row( $(this).parents('tr') ).data();
		$('#mappedUsersModal').modal('show');
		selectedGroupId = data.groupId;
	});
	/************************************************
	 * click on adduser button of mapped user modal
	 * choose users modal will show
	 ************************************************/

	$("#btn_addusers").click(function() {		
		$('#chooseUsersModal').modal('show');		
	});
	/************************************************
	 * create a table  in mapped users modal
	 ************************************************/

	var table3 = null, selectedGroupId=0;
	$('#mappedUsersModal').on('shown.bs.modal', function (e) {	
		table3 = $('#resourcesMapdxdTbl').DataTable({	 
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
			"bDestroy": true,
			"paging": true,
			"scrollX": true,
			"searching": true,
			"columnDefs":[		 	
				{"targets":0,"data":"userId"},
				{"targets":1,"data":"firstName"},
				{"targets":2,"data":"userType"},	        	
				{"targets": -1, "data": null, "defaultContent": '<button type="button" title="delete" class="btn btn-secondary btn-sm btn-delete btn-dlt"><i class="fa fa-trash"></i></button>'}
				]
		});

		loadMappedUserDetails();// function is defined to get the data for mapped users modal table
	});
	$('#resourcesMapdxdTbl tbody').on('click', '.btn-delete', function () {
		var data = table3.row( $(this).parents('tr') ).data();
		var Data = {
				userId: data.userId,
				userType:data.userType
		}
		var id=data.userId;
		$.ajax({
			url: "./setting/deleteMappedUser/"+id+"/"+data.userType+".do",
			type: "POST",	
			dataType: "json",
			contentType:"application/json",
			data: JSON.stringify(Data),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				if(result.success){
					loadMappedUserDetails();
					getGroupDetails();
					swal("User Removed","success");
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error,"error");
			}
		});
	});	
	/**************************************************
	 * url is present in AccessControllerController
	 * table used:admin.user table
	 **************************************************/
	var loadMappedUserDetails = function(){
		var Data = {
				groupId: selectedGroupId
		}
		$.ajax({
			url: "./setting/getMappedUserDetails/"+selectedGroupId+".do",
			type: "POST",	
			dataType: "json",
			contentType:"application/json",
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
				swal(error,"error");
			}
		});	
	}
	/*************************
	 * data constructs to the table 
	 * url is present in  AccessControllerAccessController
	 * table used:-admin.user table
	 **************************/

	var table4 = null;		
	$('#chooseUsersModal').on('shown.bs.modal', function (e) {
		table4 = $('#resourcesChooseUsersTbl').DataTable({			
			"lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			"pageLength": 5,	
			"bFilter": false,
			"bLengthChange": false,
			"scrollX": true,
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
				{"targets":1,"data":"userId"},
				{"targets":2,"data":"firstName"},
				{"targets":3,"data":"userType"}	        	
				]
		});	
		var type="userGroup";
		$.ajax({
			url: "./setting/getUserDetails/"+type+".do",
			type: "POST",	
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				console.log(result);
				table4.clear();
				table4.rows.add(result.data).draw();
			},
			error:function(jqXHR, status, error){
				swal(error,"error");
			}
		});
	});


	// Handle click on "Select all" control
	$('#chooseuser-select-all').on('click', function() {
		// Get all rows with search applied
		var rows = table4.rows({
			'search' : 'applied'
		}).nodes();
		// Check/uncheck checkboxes for all rows in the table
		$('input[type="checkbox"]', rows).prop('checked', this.checked);
	});

	/*******************************************************************************
	 * submit button click on Add Group modal the modal form submit will call
	 *******************************************************************************/

	$("#btn_save_group").click(function() {		
		$("#formGroupDetails").submit();		
	});

	$("#btn_save_mapuser").click(function() {			
		var selectedUsers = [];
		table4.$('input[type="checkbox"]').each(function(){
			if(this.checked){
				var data = table4.row( $(this).parents('tr') ).data();		
				selectedUsers.push(data);					    	
			}	         
		});	      

		if(selectedUsers.length>0){	    	
			$.ajax({
				url: "./setting/mapUsersToGroup.do?groupId="+selectedGroupId,
				type: "POST",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				data: JSON.stringify(selectedUsers),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success:function(result) {
					$('#loading').hide();
					console.log(result);
					if(result.success){
						$('#chooseUsersModal').modal('hide');
						loadMappedUserDetails();
						getGroupDetails();
					}
					swal(result.msg,"","success");
				},
				error:function(jqXHR, status, error){
					swal(error,"error");
				}
			});
		}else{
			swal("Please select user for mapping in group.........")
		}
	});

	function resetForm_CreateGroup(){
		$('#userGroup_form_button').html('Add');
		$("input[name=groupName]").val('');
		$("input[name=description]").val('');
	}
});	