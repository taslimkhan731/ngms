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
		 	{ "targets": 0, "data": null,
            render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
            },
			{"targets":1,"data":"cityName"},
			{"targets":2,"data":"guestHouseName"},
			{"targets":3,"data":"confrenceDesc"}, 
			{"targets":4,"data":"confreqnceUseDesc"},
			{"targets":5,"data":"charge"},
			{"targets":6,"data":"capacity"},
			{"targets":7,
			"data": function(row, type, val, meta) {
					return [row.status];
			},
			render: function (data, type, row) {
				if(data[0]=="1"){
					return "Active";
				}else{
					return "Not Active";
				}
            }
			},
			{"targets":8,"data": null, 
				         
				
			render: function (data, type, row) {
				var str ='<button type="button" id="Edit" action="Edit" class="btn btn-sm btn-edit ml-1 edit" title="Edit Room Details"><i class="fa fa-edit"></i></button></span></button>';
				return  str;
            }
			} 
			],

	});
	getRoomTableData(table);
	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
	getAllCity();
	getAllHallType();
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
			hall: {
				validators : {
					stringLength: {
                        message: 'select a valid option'
                    },
					notEmpty : {
						message : 'Hall type must not be empty'
					}
				}
			},
			use_name: {
				validators : {
					stringLength: {
                        message: 'select a valid option'
                    },
					notEmpty : {
						message : 'Category must not be empty'
					}
				}
			},
            floor_no: {
				validators : {
					notEmpty : {
						message : 'Conference Rent must not be empty'
					},
					regexp : {
						regexp : /^[0-9.]*$/,
						message : 'Conference Rent number must contain only Number'
					}
				}
			},
			capacity: {
				validators : {
					notEmpty : {
						message : 'Conference Rent must not be empty'
					},
					regexp : {
						regexp : /^[0-9.]*$/,
						message : 'Conference Rent number must contain only Number'
					}
				}
			},
			active: {
				validators : {
					notEmpty : {
						message : 'select a valid option'
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
		var hallType= $("#hall_name").val();
		var timePeriod= $("#use_name").val();
		var rent= $("#floor_no").val();
		var active= $("#active").val();
		var ccapacity= $("#capacity").val();
		var Data =
		{
				type:type,
				cityId: cityname,
				blockId: ghid,
				confCatId: hallType,
				confUseId: timePeriod,
				amount: rent,
				active: active,
				capacity:ccapacity
		};

			$.ajax({
				url: "./mdm/saveConfrenceMasterData.do",
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
					console.log(result);
					if(result.status){
						swal("Data Saved Successfully");
						$('#new_city_form').bootstrapValidator('resetForm', true);
						resetFormCreateRoom();
						getRoomTableData(table);
					}else{
						swal("Data Not Saved");
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
		getAllBlock(data.cityId,data.blockId);
		isEdit = true;
		$("#city_name").val(data.cityId); 
		$("#gh_name").val(data.blockId);
		$("#hall_name").val(data.confrenceId);
		$("#use_name").val(data.confreqnceUseId);
		
		/*disabled property*/
		$("#city_name").prop("disabled",true); 
		$("#gh_name").prop("disabled",true);
		$("#hall_name").prop("disabled",true);
		$("#use_name").prop("disabled",true);
		$("#capacity").val(data.capacity);
		$("#floor_no").val(data.charge);
		$("#active").val(data.status);
		$('#addBtn').show();
		$('#addRoomDetails').html('Update');
	});   

	$('#city_name').on('change', function() {
		var id = $('#city_name').val();
		getAllBlock(id,'');
	});
})

function getRoomTableData(table){
	$.ajax({
		url: "./mdm/getConferenceDetails.do",
		type: "GET",		
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			console.log(result);
			$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
			table.clear().draw();
			table.rows.add(result.data).draw();
		}
	});
}
/*get all confrence cat type*/
var getAllHallType = function getAllHallType() {	
	$.ajax({
		url: "./mdm/getConfrenceHallType.do",
		type: "GET",	
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			console.log(result);
			$("#hall_name").html("");
			$("#use_name").html("");
			$("#hall_name").append("<option value selected>---select---</option>");
			$("#use_name").append("<option value selected>---select---</option>");
			for(var i=0;i<result.hall.length;i++){
				
				$("#hall_name").append("<option value="+result.hall[i].confCatId+">"+result.hall[i].confCatName+"</option>");
			}
			for(var i=0;i<result.use.length;i++){
				
				$("#use_name").append("<option value="+result.use[i].confUseId+">"+result.use[i].confUseName+"</option>");
			}
		},
		error:function(jqXHR, status, error){
			(error);
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
	$("#hall_name").val('');
	$("#use_name").val('');
	$("#active").val('');
	$("#floor_no").val('');
	$("#capacity").val('');
	$('#addRoomDetails').html('Add'); 
	$("#city_name").prop("disabled",false); 
	$("#gh_name").prop("disabled",false);
	$("#hall_name").prop("disabled",false);
	$("#use_name").prop("disabled",false);
	//default value
}


