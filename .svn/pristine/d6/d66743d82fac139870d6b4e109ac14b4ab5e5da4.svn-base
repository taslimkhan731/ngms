$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserCategoryDetails").DataTable({   	
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
			{"targets":1,"data":"name"},
			{"targets":2,"data": null, 
				"defaultContent": '<button type="button" action="deleteCategory" id="deleteCategory" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],
	});
	getCategoryTableData(table);

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addCategory').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var purposeid = $("#category_type").val();
		var purposename = $("#category_name").val();
		var Data =
		{
			purposeId:purposeid,
			purposeName:purposename,
			type:type
		};

		if(purposeid==''){
			swal("Please Enter Category Type!");
			$("#category_type").focus();
		}else if(purposename==''){
			swal("Please Enter Category Name!");
			$("#category_name").focus();
		}else{
			$.ajax({
				url: "./mdm/saveCategory.do",
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
						resetFormCreateCategory();
						getCategoryTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateCategory();
						getCategoryTableData(table);	
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
	$('#UserCategoryDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#category_name").val(data.name); 
		$("#category_type").val(data.id);
		$('#addCategory').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserCategoryDetails tbody').on( 'click', 'button[action=deleteCategory]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				purposeId: data.id
		};
		
		$.ajax({
			url: "./mdm/deleteCategory.do",
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
					getCategoryTableData(table);
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

function getCategoryTableData(table){
	$.ajax({
		url: "./guest/getCategoryDetails.do",
		type: "GET",
		contentType:"application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			table.clear().draw();
			table.rows.add(result).draw();
		}
	});
}

var resetFormCreateCategory = function(){
	isEdit = false;
	$("#category_name").val(''); 
	$("#category_type").val(''); 
	$('#addCategory').html('Add'); //default value
}


