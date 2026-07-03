$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserCityDetails").DataTable({   	
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
			{"targets":0,"data":"cityId"},
			{"targets":1,"data":"cityName"},
			{"targets":2,"data":"districtName"}, 
			{"targets":3,"data":"state"},
			{"targets":4,"data":"pinCode"},
			{"targets":5,"data": null, 
				"defaultContent": '<button type="button" action="deleteCity" id="deleteCity" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],

	});
	getCityTableData(table);
	getAllState();

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addCity').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var cityname = $("#city_name").val();

		var districtname = $("#district_name").val();
		var stateId= $("#state").val();
		var id= $("#cityId").val();
		var pincode= $("#pin_code").val();
		var Data =
		{
				cityName:cityname,
				districtName:districtname,
				state:stateId,
				type:type,
				cityId:id,
				pinCode: pincode
		};

		if(cityname==''){
			swal("Please Enter City Name!");
			$("#city_name").focus();
		}else if(districtname==''){
			swal("Please Enter District Name!");
			$("#district_name").focus();
		}else if(stateId==''){
			swal("Please Select State!");
			$("#state").focus();
		}else if(pincode==''){
			swal("Please Enter Pin Code!");
			$("#pin_code").focus();
		}else{
			$.ajax({
				url: "./mdm/saveUserCityTable.do",
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
						resetFormCreateCity();
						getCityTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateCity();
						getCityTableData(table);	
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
	$('#UserCityDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#city_name").val(data.cityName); 
		$("#district_name").val(data.districtName);
		$("#state").val(data.state);
		$("#pin_code").val(data.pinCode);
		$("#cityId").val(data.cityId);
		$('#addCity').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserCityDetails tbody').on( 'click', 'button[action=deleteCity]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				cityId: data.cityId
		};
		
		$.ajax({
			url: "./mdm/deleteCreateUserCity.do",
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
					getCityTableData(table);
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

function getCityTableData(table){
	$.ajax({
		url: "./mdm/getDataCityTable.do",
		type: "GET",		
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

//get all district details
var getAllState = function getAllState() {	
	$.ajax({
		url: "./setting/getAllState.do",
		type: "GET",	
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			var stateList = result.data;
			var option = '<option value="">---SELECT----</option>';
			for(var i = 0;i<stateList.length;i++) {
				option += '<option value="'+ stateList[i].cityName + '">' + stateList[i].cityName + '</option>';
			}
			$('#state').html("");
			$('#state').empty();
			$('#state').append(option);
		},
		error:function(jqXHR, status, error){
			(error);
		}
	});
}

var resetFormCreateCity = function(){
	$('#addCity').html('Add');
	isEdit = false;
	$("#city_name").val(''); 
	$("#district_name").val('');
	$("#state").val('');
	$("#pin_code").val('');
	$("#cityId").val('');
	$('#addCity').html('Add'); //default value
}


