$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserPurposeDetails").DataTable({   	
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
				"defaultContent": '<button type="button" action="deletePurpose" id="deletePurpose" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],
	});
	getPurposeTableData(table);

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addPurpose').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var purposeid = $("#purposeId").val();
		var purposename = $("#purpose_name").val();
		var Data =
		{
			purposeId:purposeid,
			purposeName:purposename,
			type:type
		};

		if(purposename==''){
			swal("Please Enter Purpose Name!");
			$("#purpose_name").focus();
		}else{
			$.ajax({
				url: "./mdm/savePurpose.do",
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
						resetFormCreatePurpose();
						getPurposeTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreatePurpose();
						getPurposeTableData(table);	
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
	$('#UserPurposeDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#purpose_name").val(data.name); 
		$("#purposeId").val(data.id);
		$('#addPurpose').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserPurposeDetails tbody').on( 'click', 'button[action=deletePurpose]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				purposeId: data.id
		};
		
		$.ajax({
			url: "./mdm/deletePurpose.do",
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
					getPurposeTableData(table);
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

function getPurposeTableData(table){
	$.ajax({
		url: "./guest/getPurposeDetails.do",
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

var resetFormCreatePurpose = function(){
	isEdit = false;
	$("#purpose_name").val(''); 
	$("#purposeId").val('');
	$('#addPurpose').html('Add'); //default value
}


