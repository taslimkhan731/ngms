$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserRentDetails").DataTable({   	
		"paging":  true,
		"lengthMenu": [[5,10, 20, 50, 100, -1], [5,10, 20, 50, 100, "All"]],
		"processing": false,
		"ordering": false,
		"info": true,
		"bFilter": true,
		"searching": true,
		"scrollX": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"}, 
			{"targets":0,"data":"id"},
			{"targets":1,"data":"amount"},
			{"targets":2,"data": null, 
				"defaultContent": '<button type="button" action="deleteRent" id="deleteRent" title="Delete" class="btn btn-sm btn-dlt"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-sm ml-1 btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],
	});
	getRentTableData(table);
	
	$('#new_city_form')
	.bootstrapValidator(
			{
				btnSubmit : 'button[type="submit"]',
				framework : 'bootstrap',
				fields : {
					rent_amount : {
						validators : {
							regexp : {
								regexp : /^[0-9.]*$/,
								message : 'Room Rent must contain only number'
							},
							notEmpty : {
								message : 'Room rent must not be empty'
							},
						}
					},
					
				}
			}).on('success.form.bv', function(e) {
		e.preventDefault();
		submitRoomRentDetails();
	});


	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/
	var submitRoomRentDetails = function () {
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var rentid = $("#rentId").val();
		var rentamount = $("#rent_amount").val();
		var Data =
		{
			id:rentid,
			amount:rentamount,
			type:type
		};

		/*if(rentamount==''){
			swal("Please Enter Rent Amount!");
			$("#rent_amount").focus();
		}else{*/
			$.ajax({
				url: "./mdm/saveMasterRoomRent.do",
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
						resetFormCreateRent();
						getRentTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateRent();
						getRentTableData(table);	
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
	$('#UserRentDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#rent_amount").val(data.amount); 
		$("#rentId").val(data.id);
		$('#addRent').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserRentDetails tbody').on( 'click', 'button[action=deleteRent]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				id: data.id
		};
		
		$.ajax({
			url: "./mdm/deleteRent.do",
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
					getRentTableData(table);
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

function getRentTableData(table){
	$.ajax({
		url: "./mdm/getRentDetails.do",
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

var resetFormCreateRent = function(){
	isEdit = false;
	$("#rent_amount").val(''); 
	$("#rentId").val('');
	$('#addRent').html('Add'); //default value
}


