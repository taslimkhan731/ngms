$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserDesignationDetails").DataTable({   	
		"paging":  true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": true,
		"bFilter": true,
		"searching": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"}, 
			{"targets":0,"data":"id"},
			{"targets":1,"data":"desgName"},
			{"targets":2,"data":"categoryName"},
			{"targets":3,"data":"priority"},
			{"targets":4,"data": null, 
				"defaultContent": '<button type="button" action="deleteDesignation" id="deleteDesignation" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],
	});
	getDesignationTableData(table);
	getAllCategory();	

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addDesignation').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}
		var desgId = $("#desgId").val();
		var catId = $("#category_type").val();
		var desgName = $("#desg_name").val();
		var priority = $("#priority").val();
		var Data =
		{
			id: desgId,
			categoryId:catId,
			desgName:desgName,
			priority: priority,
			type:type
		};

		if(catId==''){
			swal("Please Select Category Type!");
			$("#category_type").focus();
		}else if(desgName==''){
			swal("Please Enter Designation Name!");
			$("#desg_name").focus();
		}else if(priority==''){
			swal("Please Enter Priority!");
			$("#priority").focus();
		}else{
			$.ajax({
				url: "./mdm/saveDesignation.do",
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
						resetFormCreateDesignation();
						getDesignationTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateDesignation();
						getDesignationTableData(table);	
					}
				},
				error:function(jqXHR, status, error){
					swal(error);
				}
			});
		}
	}) ; 

	/*********************************************
	 * Edit button click function
	 *********************************************/
	$('#UserDesignationDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#desg_name").val(data.desgName); 
		$("#desgId").val(data.id); 
		$("#priority").val(data.priority); 
		$("#category_type").val(data.categoryId); 
		$('#addDesignation').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserDesignationDetails tbody').on( 'click', 'button[action=deleteDesignation]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				id: data.id
		};
		
		$.ajax({
			url: "./mdm/deleteDesignation.do",
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
					getDesignationTableData(table);
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

function getDesignationTableData(table){
	$.ajax({
		url: "./mdm/getDesignationDetails.do",
		type: "GET",
		contentType:"application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			table.clear().draw();
			table.rows.add(result.data).draw();
		}
	});
}

//get all district details
var getAllCategory = function getAllCategory() {	
	$.ajax({
		url: "./guest/getCategoryDetails.do",
		type: "GET",	
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			var catList = result;
			var option = '<option value="">---SELECT----</option>';
			for(var i = 0;i<catList.length;i++) {
				option += '<option value="'+ catList[i].id + '">' + catList[i].name + '</option>';
			}
			$('#category_type').html("");
			$('#category_type').empty();
			$('#category_type').append(option);
		},
		error:function(jqXHR, status, error){
			(error);
		}
	});
}

var resetFormCreateDesignation = function(){
	isEdit = false;
	$("#desg_name").val(''); 
	$("#desgId").val(''); 
	$("#priority").val(''); 
	$("#category_type").val(''); 
	$('#addDesignation').html('Add'); //default value
}


