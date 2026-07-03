$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserVehicleDetails").DataTable({   	
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
			{"targets":0,"data":"vehId"},
			{"targets":1,"data":"driverName"},
			{"targets":2,"data":"driverMob"}, 
			{"targets":3,"data":"vehNo"},
			{"targets":4,"data":"ghName"},
			{"targets":5,"data":"blockName"},
			{"targets":6,"data": null, 
				"defaultContent": '<button type="button" action="deleteVehicle" id="deleteVehicle" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],

	});
	getVehicleTableData(table);
	getAllCity();

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addVehicle').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var cityname = $("#city_name").val();
		var ghid = $("#gh_name").val();
		var drName= $("#driver_name").val();
		var id= $("#vehId").val();
		var drMob= $("#driver_mob").val();
		var vehno= $("#veh_no").val();
		var vehdesc= $("#veh_desc").val();
		
		var Data =
		{
				driverName: drName,
				driverMob: drMob,
				vehNo: vehno,
				type:type,
				vehId:id,
				ghId: cityname,
				blockId: ghid,
				vehDesc: vehdesc
		};

		if(cityname==''){
			swal("Please Select City!");
			$("#city_name").focus();
		}else if(ghid==''){
			swal("Please Select Guest House!");
			$("#gh_name").focus();
		}else if(drName==''){
			swal("Please Enter Driver/Protocol Name!");
			$("#driver_name").focus();
		}else if(drMob==''){
			swal("Please Enter Driver/Protocol Mobile Number!");
			$("#driver_mob").focus();
		}else if(vehno==''){
			swal("Please Enter Vehicle Number!");
			$("#veh_no").focus();
		}else if(vehdesc==''){
			swal("Please Enter Vehicle Description!");
			$("#veh_desc").focus();
		}else{
			$.ajax({
				url: "./mdm/saveVehicle.do",
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
						resetFormCreateVehicle();
						getVehicleTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateVehicle();
						getVehicleTableData(table);	
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
	$('#UserVehicleDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		getAllBlock(data.ghId,data.blockId);
		isEdit = true;
		$("#city_name").val(data.ghId); 
		$("#gh_name").val(data.blockId);
		$("#driver_name").val(data.driverName);
		$("#driver_mob").val(data.driverMob);
		$("#veh_no").val(data.vehNo);
		$("#veh_desc").val(data.vehDesc);
		$("#vehId").val(data.vehId);
		$('#addVehicle').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserVehicleDetails tbody').on( 'click', 'button[action=deleteVehicle]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				vehId: data.vehId
		};
		
		$.ajax({
			url: "./mdm/deleteVehicle.do",
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
					getVehicleTableData(table);
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
	
	$('#city_name').on('change', function() {
		var id = $('#city_name').val();
		getAllBlock(id,'');
	});
})

function getVehicleTableData(table){
	$.ajax({
		url: "./mdm/getVehicleDetails.do",
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
var getAllCity = function getAllCity() {	
	$.ajax({
		url: "./mdm/getCityDropDown.do",
		type: "GET",	
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			var stateList = result.data;
			$('#city_name').html("");
			$('#city_name').empty();
			if(stateList.length == 1){
				stateList.forEach(option=>{
					$("#city_name").append('<option value="'+option.ghId+'">'+option.ghName+'</option>').addClass('disableElement');
				})
			}else{
				$("#city_name").append('<option value="">---Select---</option>');
				stateList.forEach(option=>{
					$("#city_name").append('<option value="'+option.ghId+'">'+option.ghName+'</option>');
				})
			}
			
			if(stateList.length == 1){
				if(result.gh != null){
					var ghList = result.gh;
					$("#gh_name").html("");
					if(ghList.length == 1){
						ghList.forEach(option=>{
							$("#gh_name").append('<option value="'+option.cityId+'">'+option.cityName+'</option>').addClass('disableElement');;
						})
					}
				}else{
					var id = $("#city_name").val();
					getAllBlock(id,'');
				}
			}else{
				$("#gh_name").html("");
				$("#gh_name").append('<option value="">---Select---</option>');
			}
		},
		error:function(jqXHR, status, error){
			(error);
		}
	});
}

var getAllBlock = function getAllBlock(id,value) {	
	console.log("Id " + id + " Value " + value);
	$.ajax({
		url: "./mdm/" + id +"/getGhDropDown.do",
		type: "GET",		
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			$("#gh_name").html("");
			$("#gh_name").append('<option value="">---Select---</option>');
			result.data.forEach(option=>{
				if(option.cityId == value){
					$("#gh_name").append('<option value="'+option.cityId+'" selected="selected">'+option.cityName+'</option>');
				}else{
					$("#gh_name").append('<option value="'+option.cityId+'">'+option.cityName+'</option>')
				}
			})
		}
	});
}

var resetFormCreateVehicle = function(){
	isEdit = false;
	$("#city_name").val(''); 
	$("#gh_name").val('');
	$("#driver_name").val('');
	$("#driver_mob").val('');
	$("#veh_no").val('');
	$("#veh_desc").val('');
	$("#vehId").val('');
	$('#addVehicle').html('Add'); //default value
}


