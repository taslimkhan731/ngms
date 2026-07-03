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
		"scrollX": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"}, 
			{"width": "2%","targets":0,"data":"purposeId"},
			{"width": "10%","targets":1,"data":"purposeName"},
			{"width": "70%","targets":2,"data":"type"},
			{"width": "15%","targets":3,"data": null, 
				"defaultContent": '<button type="button" action="deleteCategory" id="deleteCategory" title="Delete" class="btn btn-secondary btn-sm btn-delete btn-dlt"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-secondary btn-sm btn-edit ml-1"><i class="fa fa-edit"></i></button></span></button>'}            
			],
	});
	getCategoryTableData(table);

	$('#new_city_form').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		/*row : {
			valid : 'field-success',
			invalid : 'field-error'
		},*/
		fields : {
			category_type: {
				validators : {
                    stringLength: {
                        min: 2,
                        max: 5,
                        message: 'Category type must be more than 1 and less than 5 characters long'
                    },
					notEmpty : {
						message : 'Category type must not be empty'
					},
					regexp : {
						regexp :/^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]*)$/,
						message : 'Category Type must contain only letters & number'
					}
				}
			},
			category_name: {
				validators : {
					stringLength: {
                        min: 5,
                        max: 20,
                        message: 'Category name must be more than 5 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'The Category name must not be empty'
					},
					regexp : {
						regexp : /^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]*)$/,
						message : 'Category name must contain only letters & number'
					}
				}
			},	
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitCategoryDetails();
    });
	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/
	var submitCategoryDetails = function () {
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
		/*if(purposeid==''){
			swal("Please Enter Category Type!");
			$("#category_type").focus();
		}else if(purposename==''){
			swal("Please Enter Category Name!");
			$("#category_name").focus();
		}else{*/
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
						$('#new_city_form').bootstrapValidator('resetForm', true);
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
		url: "./mdm/getCategoryDetails.do",
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

var resetFormCreateCategory = function(){
	isEdit = false;
	$("#category_name").val(''); 
	$("#category_type").val(''); 
	$('#addCategory').html('Add'); //default value
}


