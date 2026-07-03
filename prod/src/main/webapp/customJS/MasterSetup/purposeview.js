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
		"scrollX": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"}, 
			{"targets":0,"data":"purposeId"},
			{"targets":1,"data":"purposeName"},
			{"targets":2,"data": null, 
				"defaultContent": '<button type="button" action="deletePurpose" id="deletePurpose" title="delete" class="btn btn-secondary btn-sm btn-dlt"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" title="edit" class="btn btn-sm btn-edit ml-1"><i class="fa fa-edit"></i></button></span></button>'}            
			],
	});
	getPurposeTableData(table);
	
	$('#new_city_form').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		fields : {
			purpose_name: {
				validators : {
                    stringLength: {
                        min: 3,
                        max: 20,
                        message: 'Purpose must be more than 2 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'Purpose name must not be empty'
					},
					regexp : {
						regexp :/^[A-Za-z\s]*$/,
						message : 'Purpose name must contain only letters'
					}
				}
			},
		}
	}).on('success.form.bv', function (e) {
        e.preventDefault();
        submitPurposeDetails();
    });
	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/
	var submitPurposeDetails = function () {
	
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

		/*if(purposename==''){
			swal("Please Enter Purpose Name!");
			$("#purpose_name").focus();
		}else{*/
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
						$('#new_city_form').bootstrapValidator('resetForm', true);
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
			url: "./mdm/getPurposeDetails.do",
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

var resetFormCreatePurpose = function(){
	isEdit = false;
	$("#purpose_name").val(''); 
	$("#purposeId").val('');
	$('#addPurpose').html('Add'); //default value
}


