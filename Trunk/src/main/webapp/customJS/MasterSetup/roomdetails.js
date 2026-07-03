$(document).ready(function(){
	//swal("44444")
	$("#repair_div").hide();
	$("#repairBtnshow").hide();
	isEdit = false;
	var table = $("#UserRoomDetails").DataTable({   	
		"paging":  true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": true,
		"bFilter": true,
		"scrollX": true,
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
				         
				
			render: function (data, type, row) {
				var str = '<button type="button" action="deleteRoom" id="deleteRoom" class="btn btn-sm btn-dlt" title="Delete Room Details"><i class="fa fa-trash"></i></button>'+
				'<button type="button" id="Edit" action="Edit" class="btn btn-sm btn-edit ml-1 edit" title="Edit Room Details"><i class="fa fa-edit"></i></button></span></button>' 
                if(data.status == false){
                	 str +=  '<button type="button" id="activate" action="activate" title="activate room" class="btn btn-view btn-sm ml-1"><i class="fa fa-check" ></i></button>'
                	return str;
                }else{
                	str+= '<button type="button" id="repair" action="repair" class="btn btn-sm btn-repair ml-1" title="Block Room"><i class="fa fa-wrench"></i></button></span></button>'
                	return str;

                }
            }
			} 
			],

	});
	getRoomTableData(table);
	getAllCity();

	$('#new_city_form').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		framework : 'bootstrap',
		row : {
			valid : 'field-success',
			invalid : 'field-error'
		},
		fields : {
			city_name:{
                validators: {
                    notEmpty: {
                        message: 'select a valid option'
                    }
                }
            },
            gh_name: {
            	validators: {
                    notEmpty: {
                        message: 'select a valid option'
                    }
                }
			},
			room_no: {
				validators : {
					stringLength: {
                        min: 1,
                        max: 20,
                        message: 'Room number must be more than 1 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'Room number must not be empty'
					},
					/*regexp : {
						regexp : /^[0-9.]*$/,
						message : 'Room number must contain only Number'
					}*/
				}
			},
			room_name: {
				validators : {
					stringLength: {
                        min: 1,
                        max: 20,
                        message: 'Room name must be more than 1 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'Room name must not be empty'
					},
					regexp : {
						regexp :  /^[A-Za-z\s]*$/,
						message : 'Room name must contain only letters'
					}
				}
			},
			room_type: {
                validators: {
                    notEmpty: {
                        message: 'select a valid option'
                    }
                }
            },
			
            floor_no: {
				validators : {
					notEmpty : {
						message : 'Floor number must not be empty'
					},
					regexp : {
						regexp : /^[0-9.]*$/,
						message : 'Floor number must contain only Number'
					}
				}
			},
			no_beds: {
				validators : {
					/*stringLength: {
                        min: 1,
                        max: 20,
                        message: 'The answer must be more than 2 and less than 20 characters long'
                    },*/
					notEmpty : {
						message : 'Bed number must not be empty'
					},
					regexp : {
						regexp : /^[0-9.]*$/,
						message : 'Bed number must contain only Number'
					}
				}
			},
			repairReason: {
				validators : {
					stringLength: {
                        min: 1,
                        max: 200,
                        message: 'The answer must be more than 2 and less than 20 characters long'
                    },
					notEmpty : {
						message : 'Repair Reason must not be empty'
					}
				}
			}
			
		}
	}).on('error.field.bv', function(e, data) {
        if (data.bv.getSubmitButton()) {
            data.bv.disableSubmitButtons(false);
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var reasonForRepair = $('#repairReason').val();
        if ( reasonForRepair == null || reasonForRepair == "") {
        	console.log("Submit room details ");
            submitRoomDetails();
        } else {
        	console.log("Submit repair details ");
        	submitRepairDetails();
        }
    });

//	$('#addRoomDetails').on('click', function () {
//		console.log("Value of isEdit "+isEdit);
//		$('#new_city_form').bootstrapValidator('validate');
//		submitRoomDetails();
//	 });

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	var submitRoomDetails = function () {
	
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}
		console.log("value of type "+type);
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
						$('#new_city_form').bootstrapValidator('resetForm', true);
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
		$("#room_name_div").show();
		$("#room_type_div").show();
		$("#floor_no_div").show();
		$("#no_beds_div").show();
		$("#repair_div").hide();
		$('#repairBtnshow').hide();
		$('#addBtn').show();
		$('#addRoomDetails').html('Update');
	});   
	
	
	/*********************************************
	 * Repair button click function
	 *********************************************/
	$('#UserRoomDetails tbody').on( 'click', 'button[action=repair]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		getAllBlock(data.ghId,data.blockId);
		$("#city_name").val(data.ghId); 
		$("#gh_name").val(data.blockId);
		$("#room_no").val(data.roomNo);
		$("#room_name").val(data.roomName);
		$("#room_type").val(data.roomType);
		$("#floor_no").val(data.roomFloor);
		$("#no_beds").val(data.roomNoOfBeds);
		$("#room_name_div").hide();
		$("#room_type_div").hide();
		$("#floor_no_div").hide();
		$("#no_beds_div").hide();
		$("#repair_div").show();
		$('#repairBtnshow').show();
		$('#addRoomDetails').html('Block');

	}); 
	
	
	/*********************************************
	 * activate button click function
	 *********************************************/
	$('#UserRoomDetails tbody').on( 'click', 'button[action=activate]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		getAllBlock(data.ghId,data.blockId);
		$("#city_name").val(data.ghId); 
		$("#gh_name").val(data.blockId);
		console.log(data.ghId+"  "+data.blockId);
		console.log("block id "+ $("#gh_name").val());
		$("#room_no").val(data.roomNo);
		$("#room_name").val(data.roomName);
		$("#room_type").val(data.roomType);
		$("#floor_no").val(data.roomFloor);
		$("#no_beds").val(data.roomNoOfBeds);
		activateRoom();
	});  
	
	 $('#repairBtn').on('click', function () {
		 submitRepairDetails();
	 });
	 
	var submitRepairDetails = function () {
		var cityname = $("#city_name").val();
		var ghid = $("#gh_name").val();
		var roomno= $("#room_no").val();
		var no_beds= $("#no_beds").val();
		var roomname= $("#room_name").val();
		var roomtype= $("#room_type").val();
		var floor_no= $("#floor_no").val();
		var reason = $("#repairReason").val();
		console.log("data value is "+cityname+"  "+ghid+" "+"  "+roomno+"  "+no_beds+"  "+roomname+"   "+roomtype+"  "+floor_no);

		var Data =
		{
				ghId: cityname,
				blockId: ghid,
				roomNo: roomno,
				roomName: roomname,
				roomType: roomtype,
				roomNoOfBeds: no_beds,
				roomFloor: floor_no,
				repairReason:reason			
		};

			$.ajax({
				url: "./mdm/saveRepairDetails.do",
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
	
	/*********************************************
	 * activate function
	 *********************************************/   
	var activateRoom = function () {

		var cityname = $("#city_name").val();
		var ghid = $("#gh_name").val();
		console.log("hghghgh          "+$("#gh_name").val());
		var roomno= $("#room_no").val();
		var no_beds= $("#no_beds").val();
		var roomname= $("#room_name").val();
		var roomtype= $("#room_type").val();
		var floor_no= $("#floor_no").val();
		console.log("data value is "+cityname+"  "+ghid+" "+"  "+roomno+"  "+no_beds+"  "+roomname+"   "+roomtype+"  "+floor_no);

		var Data =
		{
				ghId: cityname,
				blockId: ghid,
				roomNo: roomno,
				roomName: roomname,
				roomType: roomtype,
				roomNoOfBeds: no_beds,
				roomFloor: floor_no		
		};
			$.ajax({
				url: "./mdm/activateRoom.do",
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
	$("#repairReason").val('');
	$('#addRoomDetails').html('Add'); //default value
}


