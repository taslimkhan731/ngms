$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserRoomDetails").DataTable({   	
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
			{"targets":0,"data":"roomNo"},
			{"targets":1,"data":"roomName"},
			{"targets":2,"data":"roomType"}, 
			{"targets":3,"data":"roomNoOfBeds"},
			{"targets":4,"data":"ghName"},
			{"targets":5,"data":"blockName"},
			{"targets":6,"data": null, 
				"defaultContent": '<button type="button" action="deleteRoom" id="deleteRoom" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],

	});
	getRoomTableData(table);
	getAllCity();

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addRoomDetails').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var cityname = $("#city_name").val();
		var ghid = $("#gh_name").val();
		var roomno= $("#room_no").val();
		var no_beds= $("#no_beds").val();
		var roomname= $("#room_name").val();
		var roomtype= $("#room_type").val();
		var floor_no= $("#floor_no").val();
		var Data =
		{
				type:type,
				ghId: cityname,
				blockId: ghid,
				roomNo: roomno,
				roomName: roomname,
				roomType: roomtype,
				roomNoOfBeds: no_beds,
				roomFloor: floor_no
		};

		if(cityname==''){
			swal("Please Select City!");
			$("#city_name").focus();
		}else if(ghid==''){
			swal("Please Select Guest House!");
			$("#gh_name").focus();
		}else if(roomno==''){
			swal("Please Enter Room Number!");
			$("#room_no").focus();
		}else if(roomname==''){
			swal("Please Enter Room Name!");
			$("#room_name").focus();
		}else if(roomtype==''){
			swal("Please Select Room Type!");
			$("#room_type").focus();
		}else if(floor_no==''){
			swal("Please Enter Floor Number!");
			$("#floor_no").focus();
		}else{
			$.ajax({
				url: "./mdm/saveRoomDetails.do",
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
						resetFormCreateRoom();
						getRoomTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateRoom();
						getRoomTableData(table);	
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
	$('#UserRoomDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		getAllBlock(data.ghId,data.blockId);
		isEdit = true;
		$("#city_name").val(data.ghId); 
		$("#gh_name").val(data.blockId);
		$("#room_no").val(data.roomNo);
		$("#room_name").val(data.roomName);
		$("#room_type").val(data.roomType);
		$("#floor_no").val(data.roomFloor);
		$("#no_beds").val(data.roomNoOfBeds);
		$('#addRoomDetails').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserRoomDetails tbody').on( 'click', 'button[action=deleteRoom]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				blockId: data.blockId,
				roomNo: data.roomNo
		};
		
		$.ajax({
			url: "./mdm/deleteRoomDetails.do",
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
					getRoomTableData(table);
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

function getRoomTableData(table){
	$.ajax({
		url: "./mdm/getRoomDetails.do",
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

var resetFormCreateRoom = function(){
	isEdit = false;
	$("#city_name").val(''); 
	$("#gh_name").val('');
	$("#room_no").val('');
	$("#room_name").val('');
	$("#room_type").val('');
	$("#floor_no").val('');
	$("#no_beds").val('');
	$("#roomId").val('');
	$('#addRoomDetails').html('Add'); //default value
}


