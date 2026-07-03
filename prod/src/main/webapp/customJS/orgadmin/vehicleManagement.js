var totalVehicleRequestedGuest = '';
var requestedGuestDetails = '';
var arrivalRequest = '';
var departureRequest = '';
var arrivalTimeReport='';
var departureTimeReport='';
var type="";
$('document').ready(function() {

	 $('#filter-popover').on('click', function () {
        event.stopPropagation();
     });
/*	$(" #startTime, #endTime").focus(function(){

		var d=	convertStringToDatetime(arrivalTimeReport.slice(0,10));

		var month = d.getMonth()+1;
		var day = d.getDate();

		var output = d.getFullYear() + '/' +
		(month<10 ? '0' : '') + month + '/' +
		(day<10 ? '0' : '') + day;
		console.log(d);
		console.log(arrivalTimeReport);
		$(" #startTime, #endTime").datetimepicker({
		minDate:d,
		maxDate:moment(),
		format: 'DD-MM-YYYY HH:mm'
		})
	});*/
	$(" #startTime, #endTime").datetimepicker({
		useCurrent:false,
		format: 'DD-MM-YYYY HH:mm'
		})
	$(" #timeReport, #timeReport2").datetimepicker({
		useCurrent:false,
		format: 'DD-MM-YYYY HH:mm'
		})
	$(" #startTime1, #endTime1").datetimepicker({
		useCurrent:false,
		format: 'DD-MM-YYYY HH:mm'
		})
	/*$(" #startTime1, #endTime1").focus(function(){

		var d=	convertStringToDatetime(departureTimeReport.slice(0,10));

		var month = d.getMonth()+1;
		var day = d.getDate();

		var output = d.getFullYear() + '/' +
		(month<10 ? '0' : '') + month + '/' +
		(day<10 ? '0' : '') + day;
		console.log(d);
		console.log(arrivalTimeReport);
		$(" #startTime1, #endTime1").datetimepicker({
		minDate:d,
		maxDate:moment(),
		format: 'DD-MM-YYYY HH:mm'
		})
	});*/
	var x = [{
		"proName": "Test",
		"proNo": "9337104758",
		"vNo": "OD-02-AA-2789",
		"vDes": "INNOVA"
	}]
	$("#editVehicleDetail").hide();
	var table = null;
	table = $("#requestListDataTable")
		.DataTable(
			{

				"lengthMenu": [[10, 20, 30, -1],
				[10, 20, 30, "All"]],
				"processing": false,
				"ordering": false,
				"info": false,
				"bFilter": false,
				//					"scrollX": true,
				"paging": true,
				"searching": false,
				"fixedHeader": {
					header: true,
					footer: true
				},
				"scrollCollapse": true,
				"columnDefs": [
					{
						"targets": 0,
						"data": "driverId"
					}, {
						"targets": 1,
						"data": "driverName"
					},
					{
						"targets": 2,
						"data": "driverMobileNo"
					},
					{
						"targets": 3,
						"data": "protocolName"
					},
					{
						"targets": 4,
						"data": "protocolMobile"
					},
					{
						"targets": 5,
						"data": "vehicleNo"
					},
					{
						"targets": 6,
						"data": "vehicledesc"
					},
					{
						"targets": 7, "data": null, render: function(data, type, row, meta) {
							return '<button type="button" id="select" title="Select" action="Select" class="btn btn-sm btn-add">Add</button> '
						}
					}],
			});
	var table1 = null;
	table1 = $("#requestListDataTable1")
		.DataTable(
			{

				"lengthMenu": [[10, 20, 30, -1],
				[10, 20, 30, "All"]],
				"processing": false,
				"ordering": false,
				"info": false,
				"bFilter": false,
				//					"scrollX": true,
				"paging": true,
				"searching": false,
				"fixedHeader": {
					header: true,
					footer: true
				},
				"scrollCollapse": true,
				"columnDefs": [
					{
						"targets": 0,
						"data": "driverId"
					}, {
						"targets": 1,
						"data": "driverName"
					},
					{
						"targets": 2,
						"data": "driverMobileNo"
					},
					{
						"targets": 3,
						"data": "protocolName"
					},
					{
						"targets": 4,
						"data": "protocolMobile"
					},
					{
						"targets": 5,
						"data": "vehicleNo"
					},
					{
						"targets": 6,
						"data": "vehicledesc"
					},
					{
						"targets": 7, "data": null, render: function(data, type, row, meta) {
							return '<button type="button" id="select1" title="Select" action="Select" class="btn btn-sm btn-add">Add</button> '
						}
					}],
			});



	var table3 = null;
	table3 = $("#vehicleDataTableReport")
		.DataTable(
			{

				"lengthMenu": [[10, 20, 30, -1],
				[10, 20, 30, "All"]],
				"processing": false,
				"ordering": false,
				"info": false,
				"bFilter": false,
				//					"scrollX": true,
				"paging": true,
				"searching": false,
				"fixedHeader": {
					header: true,
					footer: true
				},
				"scrollCollapse": true,
				"columnDefs": [
					   {
			                 "className": "text-center text-nowrap",
			                 "targets": [0]
			             },
					{
						"targets": 0,
						"data": function(row, type, val, meta) {
							return [row.reqId];
						},
						"render": function(data, type, full, meta) {
							return "<b style='text-align:center;'>"+data[0]+"</b>";
						}
					}, {
						"targets": 1,
						"data": function(row, type, val, meta) {
							return [row.name, row.age, row.department,row.designation];
						},
						"render": function(data, type, full, meta) {
							var department=data[2]
							if(department==""||department==null){
								department="";
							}
							var designation=data[3]
							if(designation==""||designation==null){
								designation="";
							}
							return "<b>Guest Name:</b>"+data[0]+"<br><b>Age:</b>"+data[1]+"<br><b>Department:</b>"+department+"<br><b>Designation:</b>"+designation;
						}
					},
					{
					    "targets": 2,
					    "width": 30,
					    "data": function(row, type, val, meta) {
					        return [row.arrivalPlace, row.arrTimeOfReport, row.arrFtNo, row.arrModOfTravel];
					    },
					    "render": function(data, type, full, meta) {
					        var trType = "";
					        if (data[3] == "T") {
					            trType = "Train";
					        } else {
					            trType = "Flight";
					        }

					        if (data[0] !== null) {
					            return "<b>Place:</b>" + data[0] + "<br><b>Time of Report:</b>" + data[1] + "<br><b>Mod OF Travel:</b>" + trType + "<br><b>Ft No.:</b>" + data[2];
					        } else {
					            return "<b>No data available</b>";
					        }
					    }
					},
					{
					    "targets": 3,
					    "data": function(row, type, val, meta) {
					        return [row.arrDriverName, row.arrDriverNo, row.arrProtocolName, row.arrProtocolNo];
					    },
					    "render": function(data, type, full, meta) {
					        if (data[0] !== null) {
					            return "<b>Protocol Name:</b>" + data[3] + "<br><b>Protocol No.:</b>" + data[2] + "<br><b>Driver Name:</b>" + data[0] + "<br><b>Driver No.:</b>" + data[1];
					        } else {
					            return "<b>No data available</b>";
					        }
					    }
					},

					{
					    "targets": 4,
					    "data": function(row, type, val, meta) {
					        return [row.arrStrtTime, row.arrEndTime, row.arrVehDetails, row.arrVehNo];
					    },
					    "render": function(data, type, full, meta) {
					        if (data[0] !== null) {
					            return "<b>Vehicle Name:</b>" + data[2] + "<br><b>Vehicle No.:</b>" + data[3] + "<br><b>Vehicle Start Time:</b>" + data[0] + "<br><b>End Time:</b>" + data[1];
					        } else {
					            return "<b>No data available</b>";
					        }
					    }
					},
					{
				    "targets": 5,
				    "data": function(row, type, val, meta) {
				        return [row.deparrivalPlace, row.depTimeOfReport, row.depFtNo, row.depModOfTravel];
				    },
				    "render": function(data, type, full, meta) {
				        var place = data[0] || "";
				        var timeOfReport = data[1] || "";
				        var ftNo = data[2] || "";
				        var modOfTravel = data[3] || "";

				        var trType = modOfTravel === "T" ? "Train" : "Flight";

				        var html = "<b>Place:</b> " + place + "<br>";
				        html += "<b>Time of Report:</b> " + timeOfReport + "<br>";
				        html += "<b>Mod OF Travel:</b> " + trType + "<br>";
				        html += "<b>Ft No.:</b> " + ftNo;

				        if (place || timeOfReport || ftNo || modOfTravel) {
				            return html;
				        } else {
				            return "<b class='text-center'>No data available</b>";
				        }
				    }
				},
					{
					    "targets": 6,
					    "data": function(row, type, val, meta) {
					        return [row.depDriverName, row.depDriverNo, row.depProtocolName, row.depProtocolNo];
					    },
					    "render": function(data, type, full, meta) {
					        if (data[0] !== null) {
					            return "<b>Protocol Name:</b>" + data[2] + "<br><b>Protocol No.:</b>" + data[3] + "<br><b>Driver Name:</b>" + data[0] + "<br><b>Driver No.:</b>" + data[1];
					        } else {
					            return "<b class='text-center'>No data available</b>";
					        }
					    }
					},
					{
					    "targets": 7,
					    "data": function(row, type, val, meta) {
					        return [row.depStrtTime, row.depEndTime, row.depVehDetails, row.depVehNo];
					    },
					    "render": function(data, type, full, meta) {
					        if (data[0] !== null) {
					            return "<b>Vehicle Name:</b>" + data[2] + "<br><b>Vehicle No.:</b>" + data[3] + "<br><b>Vehicle Start Time:</b>" + data[0] + "<br><b>End Time:</b>" + data[1];
					        } else {
					            return "<b class='text-center'>No data available</b>";
					        }
					    }

					}],
			});






	/*get city data*/

	var cityData = $('#cityUserlocssd').val().trim();
	var cityDetails = JSON.parse(cityData);
	$("#city").append('<option value="">--Select City--</option>');
	cityDetails.forEach(option => {
		$("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
	})
	let size = Object.keys(cityDetails).length;
	if (size == 1) {
		$('#city').val(cityDetails[0].gh_id);
			setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}

	$("#city").change(function() {
		var id = $('#city').val();
		console.log(id);
		getGuestHouseListDtls(id);
	});

	let isCityAndGHSelected = function (city, gHouse) {
		if (city == "") {
			swal("Select City");
			return false;
		}
		if (gHouse == "") {
			swal("Select Guest House");
			return false;
		}
		return true;
	}

	/* get guest house details through city id  */
	var getGuestHouseListDtls = function(id) {
		$.ajax({
			url: "./admin/getGuestHouseListDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function() {
				$('#loading').show();
			},
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$('#loading').hide();
				$("#gHouse").html("");
				$("#gHouse").append('<option value="">---Select---</option>');
				data.forEach(option => {
					$("#gHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
				})
				let size = Object.keys(data).length;
				if (size == 1) {
					$('#gHouse').val(data[0].id);
				}
				$('#gHouse').trigger('change');
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	$(".closemodal").click(function(){
		reset();
	});
	
	$("#gHouse").change(function() {
	 $("#viewAllotmentReport").removeClass("custom-btn-active");
     $("#viewAllAlotment").removeClass("custom-btn-active");
     $("#vehicleTable").html("");
     $("#vehicleTableReport").html("");
	});



	// $("#gHouse").change(function() {
	// 	$("#vehicleTable").html("");
	// 	var cityId = $('#city').val();
	// 	var GuestId = $('#gHouse').val();
	//
	// 	if (cityId != "" && GuestId != "") {
	// 		checkForShift(cityId, GuestId);
	// 		setTimeout(function() {
	// 			let status = $('#shiftStatus').val();
	// 			if (status == "true") {
	// 				var postData = {
	// 					cId: cityId,
	// 					gId: GuestId
	// 				}
	// 				getRequstedVehicleData(postData);
	// 			} else if (status == "false") {
	// 				let msg = $('#shiftMsg').val();
	// 				swal(msg);
	// 				return false;
	// 			} else {
	// 				swal("Please open shift");
	// 				return false;
	// 			}
	// 		}, 1000);
	// 	}
	// });
	//
	$('.driveDetails').on('click', function() {
		console.log("iam call");
		var cityId = $('#city').val();
		var GuestId = $('#gHouse').val();
		var postData = {
			cId: cityId,
			gId: GuestId,
		}
		var boolean=false;
		if(cityId==''&&GuestId==''){
			boolean=true;
		}
		if(boolean){
			$("#vehicleModal").modal('hide');
			swal("please enter city or guest house");
		return false;
		}
		$.ajax({
			url: './admin/getDriverDetails.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result.data);
				table.clear();
				table.rows.add(result.data).draw();
				table1.clear();
				table1.rows.add(result.data).draw();
			}
		})
	});

	$('#addVehicleDetail').on('click', function() {
		var cityId = $('#city').val();
		var blockId = $('#gHouse').val();
		var postData = {
			cId: cityId,
			gId: blockId,
			reqId: requestedGuestDetails.reqId,
			vehId: requestedGuestDetails.vehId,
		}
		var postdata1 = {
			cId: cityId,
			gId: blockId,
			pageNo:1
		}

		var checkDeparture = false;
		var checkArrival = false;
		if (requestedGuestDetails.arrival == "1" && $("#arrivalCheckBox").is(":checked")) {
			var protocolName = $("#arrproName").val();
			var protocolNo = $("#arrmobileNo").val();
			var driverName = $("#arrdrivName").val();
			var driverMobile = $("#arrdrivmobileNo").val();
			var vehDetails = $("#vehicleDt").val();
			var vehNo = $("#vehicleNo").val();
			var reqTime = $("#timeReport").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var place = $("#place").val();
			var arrival = requestedGuestDetails.arrival;

			postData.arrivalDetails = {
				proName: protocolName,
				proNo: protocolNo,
				driverName: driverName,
				driverNo: driverMobile,
				vehDetails: vehDetails,
				vehicleNo: vehNo,
				reqTime: reqTime,
				startTime: startTime,
				endTime: endTime,
				place: place
			};
			postData.arrival= requestedGuestDetails.arrival;
			console.log(postData.arrivalDetails);
			checkArrival= checkFromValidation(protocolName,protocolNo,driverName,driverMobile,vehDetails,vehNo,startTime,endTime);
		}else{
			postData.arrival= "0";
		}
		if (requestedGuestDetails.departure == "1" && $("#departureCheckBox").is(":checked")) {
			var protocolName = $("#proName").val();
			var protocolNo = $("#promobileNo").val();
			var driverName = $("#drivName").val();
			var driverMobile = $("#drivmobileNo").val();
			var vehDetails = $("#depvehicleDt").val();
			var vehNo = $("#depvehicleNo").val();
			var reqTime = $("#timeReport2").val();
			var startTime = $("#startTime1").val();
			var endTime = $("#endTime1").val();
			var departure = requestedGuestDetails.departure;
			var place = $("#place2").val();
			checkDeparture= checkFromValidation(protocolName,protocolNo,driverName,driverMobile,vehDetails,vehNo,startTime,endTime);
			postData.departureDetails = {
				proName: protocolName,
				proNo: protocolNo,
				driverName: driverName,
				driverNo: driverMobile,
				vehDetails: vehDetails,
				vehicleNo: vehNo,
				reqTime: reqTime,
				startTime: startTime,
				endTime: endTime,
				place: place
			};
			console.log(postData.departureDetails);
			postData.departure= requestedGuestDetails.departure;
		}else{
			postData.departure="0";
		}

		if (checkArrival) {
			swal("Please provide arrival details");
			return false;
		}
		if (checkDeparture) {
			swal("Please provide departure details");
			return false;
		}

		var validDateStartTimeArr=false;
		var validDateEndtimeArr=false;
		var validDateStartTimeDrr=false;
		var validDateEndtimeDrr=false;


		if(requestedGuestDetails.arrival == "1" &&  $("#arrivalCheckBox").is(":checked")){
			var validDateStartTimeArr=dateCompairisionForFromDate($("#startTime").val(),$("#timeReport").val());
			var validDateEndtimeArr=dateCompairisionForToDate($("#endTime").val(),$("#timeReport").val());
		}

		if(requestedGuestDetails.departure == "1" &&  $("#departureCheckBox").is(":checked")){
			var validDateStartTimeDrr=dateCompairisionForFromDate($("#startTime1").val(),$("#timeReport2").val());
			var validDateEndtimeDrr=dateCompairisionForToDate($("#endTime1").val(), $("#timeReport2").val());
		}
		if(requestedGuestDetails.arrival == "1" &&  $("#arrivalCheckBox").is(":checked")){
			if(!validDateStartTimeArr){
				swal("Vehicle start time must be less from report time In Arrival !");
				return false;
			}
			if(!validDateEndtimeArr){
				swal("Vehicle end time must be greater from report time In Arrival !");
				return false;
			}
		}
		if(requestedGuestDetails.departure == "1" &&  $("#departureCheckBox").is(":checked")){
			if(!validDateStartTimeDrr){
				swal("Vehicle start time must be less from report time In Departure !");
				return false;
			}
	
			if(!validDateEndtimeDrr){
				swal("Vehicle end time must be greater from report time In Departure !");
				return false;
			}
		}
		console.log(postData);
		$.ajax({
			url: './admin/saveVehileAllotMent.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				$('#loading').hide();
				$("#vehicleModal").modal("hide");
				console.log(result);
				reset();
				if (result.error == 0) {
					swal("something went wrong please enter after some time");
				} else {
					swal(result.status);
				}

				getRequstedVehicleData(postdata1);
			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#vehicleModal").modal("hide");
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		})
	});
	$('#editVehicleDetail').on('click', function() {
	/*debugger;*/
		var cityId = $('#city').val();
		var blockId = $('#gHouse').val();
		var reqId = $('#reqIdInEdit').val();
		var postData = {
			cId: cityId,
			gId: blockId,
			reqId: reqId,
		}
		var postdata1 = {
			cId: cityId,
			gId: blockId,
			pageNo:1
		}
		var arrivals = "0";
		var departure = "0";
		checkDeparture=false;
		checkArrival=false;
		if ($("#arrival").val() == "A" && $("#arrivalCheckBox").is(":checked")) {
			arrivals = "1";
			var protocolName = $("#arrproName").val();
			var protocolNo = $("#arrmobileNo").val();
			var driverName = $("#arrdrivName").val();
			var driverMobile = $("#arrdrivmobileNo").val();
			var vehDetails = $("#vehicleDt").val();
			var vehNo = $("#vehicleNo").val();
			var reqTime = $("#timeReport").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var place = $("#place").val();
			var vehReqId = $("#arrVehicleReqId").val();

			postData.arrivalDetails = {
				proName: protocolName,
				proNo: protocolNo,
				driverName: driverName,
				driverNo: driverMobile,
				vehDetails: vehDetails,
				vehicleNo: vehNo,
				reqTime: reqTime,
				startTime: startTime,
				endTime: endTime,
				place: place,
				vehReqId: vehReqId
			};
			checkArrival= checkFromValidation(protocolName,protocolNo,driverName,driverMobile,vehDetails,vehNo,startTime,endTime);
		}else{
			arrivals = "0";
		}
		if ($("#departure").val() == "D" && $("#departureCheckBox").is(":checked")) {
			departure = "1";
			var protocolName = $("#proName").val();
			var protocolNo = $("#promobileNo").val();
			var driverName = $("#drivName").val();
			var driverMobile = $("#drivmobileNo").val();
			var vehDetails = $("#depvehicleDt").val();
			var vehNo = $("#depvehicleNo").val();
			var reqTime = $("#timeReport2").val();
			var startTime = $("#startTime1").val();
			var endTime = $("#endTime1").val();
			var place = $("#place2").val();
			var vehReqId = $("#arrVehicleReqId2").val();
			checkDeparture= checkFromValidation(protocolName,protocolNo,driverName,driverMobile,vehDetails,vehNo,startTime,endTime);
			postData.departureDetails = {
				proName: protocolName,
				proNo: protocolNo,
				driverName: driverName,
				driverNo: driverMobile,
				vehDetails: vehDetails,
				vehicleNo: vehNo,
				reqTime: reqTime,
				startTime: startTime,
				endTime: endTime,
				place: place,
				vehReqId: vehReqId
			};
		}else{
			departure = "0";
		}

		if (checkArrival) {
			swal("Please provide arrival details");
			return false;
		}
		if (checkDeparture) {
			swal("Please provide departure details");
			return false;
		}


		postData.arrival = arrivals;
		postData.departure = departure;
		console.log(postData);
		$.ajax({
			url: './admin/updateVehileAllotMent.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				$('#loading').hide();
				$("#vehicleModal").modal("hide");
				console.log(result);
				swal(result.status);
				reset();
				getRequstedVehicleData(postdata1);

			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#vehicleModal").modal("hide");
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		})
	});

	$("#requestListDataTable tbody").on("click", ".btn-add", function() {
		let data = table.row($(this).parents('tr')).data();
		console.log(data);
		$("#requestModal").modal("hide");
		putDataInArrivalData(data);
	});
	$("#requestListDataTable1 tbody").on("click", ".btn-add", function() {
		let data = table1.row($(this).parents('tr')).data();
		console.log(data);
		$("#requestModal1").modal("hide");
		putDataInDepartureData(data);
	})

	$("#viewAllotmentReport").on('click', function() {
	 $("#viewAllotmentReport").addClass("custom-btn-active");
     $("#viewAllAlotment").removeClass("custom-btn-active");
		var cityId = $('#city').val();
		var blockId = $('#gHouse').val();
		if (isCityAndGHSelected(cityId, blockId)) {
			checkForShift(cityId, blockId);
			setTimeout(function() {
				let status = $('#shiftStatus').val();
				if (status == "true") {
					var postData = {
						cId: cityId,
						gId: blockId
					}
					viewVehicleAllotmentReport(postData);
				} else if (status == "false") {
					let msg = $('#shiftMsg').val();
					swal(msg);
					return false;
				} else {
					swal("Please open shift");
					return false;
				}
			}, 1000);
		}
	});
	var viewVehicleAllotmentReport=function(postData){
		$.ajax({
			url: './admin/viewVehicleAllotmentReport.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result);
				$('#loading').hide();
				$('#dueList').hide();
				table3.clear();
				table3.rows.add(result.Data).draw();
				$("#reportList").show();

			},
			error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
		})
	}
	$("#vehicleModal").on("hidden.bs.modal",function(){
		console.log("iam close");
		$("#arrivalBox").hide();
		$("#departureBox").hide();
		$("#arrivalCheckBox").prop("checked", false);
		$("#departureCheckBox").prop("checked", false);
		$("#arrTbl").hide();
		$("#depTbl").hide();
		$(".radioArrDep").prop("checked", false);
		requestedGuestDetails={};
		requestedGuestDetails.departure="0";
		requestedGuestDetails.arrival="0";
	});
	$("#viewAllAlotment").on('click',function(){

		 $("#viewAllotmentReport").removeClass("custom-btn-active");
    	 $("#viewAllAlotment").addClass("custom-btn-active");
    	 $('#dueList').show();
    	 $('#reportList').hide();
    	 var cityId = $('#city').val();
		 var blockId = $('#gHouse').val();
		 var bookingId = $('#filterBookingId').val();
		 var name = $('#filterGuestName').val();
		if (isCityAndGHSelected(cityId, blockId)) {
			checkForShift(cityId, blockId);
			setTimeout(function() {
				let status = $('#shiftStatus').val();
				if (status == "true") {
					var postData = {
						cId: cityId,
						gId: blockId,
						bookingId:bookingId,
						name:name,
						pageNo:1
					}
					getRequstedVehicleData(postData);
				} else if (status == "false") {
					let msg = $('#shiftMsg').val();
					swal(msg);
					return false;
				} else {
					swal("Please open shift");
					return false;
				}
			}, 1000);
		}
	});

	$("#pdfbutton").on('click',function(){
		 var cityId = $('#city').val();
		 var blockId = $('#gHouse').val();
		 var bookingId=$("#filterBookingIdReport").val();
		var name=$("#filterGuestNameReport").val();
		 if(cityId==""){
			cityId="ALL"
		}

		if(blockId==""){
			blockId="ALL"
		}
		 if(bookingId==""){
			bookingId="ALL"
		}

		if(name==""){
			name="ALL"
		}
		
		var cId= cityId;
		var gId=  blockId;

		window.open('./admin/VehicleAllotmentPdf/' + cId + "/" + gId + "/" + bookingId  + "/" + name +'.html');

	});


$('#filterSearch').click(function () {
var cityId = $('#city').val();
var blockId = $('#gHouse').val();
var postData={
	cId:cityId,
	gId:blockId,
	bookingId:$("#filterBookingId").val(),
	name:$("#filterGuestName").val(),
	pageNo:1
}
getRequstedVehicleData(postData);
$('#filter-popover').unbind();
setTimeout(function(){
$('#filter-popover').bind('click',preventClick);
	}, 1000);
});

function preventClick() {
   event.stopPropagation();
}
$('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });
$('#resetFiterBtn').click(function () {
    $('#filterBookingId').val('');
    $('#filterGuestName').val('');
    var cityId = $('#city').val();
	var blockId = $('#gHouse').val();
	var postData={
	cId:cityId,
	gId:blockId,
	bookingId:$("#filterBookingId").val(),
	name:$("#filterGuestName").val(),
	pageNo:1
}
    getRequstedVehicleData(postData)
});
$('#filterBookingIdReport').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});
/*fliter for report*/
$('#filterSearchReport').click(function () {
var cityId = $('#city').val();
var blockId = $('#gHouse').val();
var postData={
	cId:cityId,
	gId:blockId,
	bookingId:$("#filterBookingIdReport").val(),
	name:$("#filterGuestNameReport").val()
}
viewVehicleAllotmentReport(postData);
$('#filter-popover').unbind();
setTimeout(function(){
$('#filter-popover').bind('click',preventClick);
	}, 1000);
});

function preventClick() {
   event.stopPropagation();
}
$('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
});
$('#resetFiterBtnReport').click(function () {
    $('#filterBookingIdReport').val('');
    $('#filterGuestNameReport').val('');
    var cityId = $('#city').val();
	var blockId = $('#gHouse').val();
	var postData={
	cId:cityId,
	gId:blockId
}
  viewVehicleAllotmentReport(postData);
});

$("#checkPageNext").on("click", function() {
		let pageNo = parseInt($("#checkPageNo").html());
		var cityId = $('#city').val();
		var blockId = $('#gHouse').val();
		var postData1 = {
			cId: cityId,
			gId: blockId,
			pageNo:pageNo + 1
		}
		getRequstedVehicleData(postData1);
		$("#checkPageNo").html(pageNo + 1);
	});

$("#checkPagePrev").on("click", function() {
		let pageNo = parseInt($("#checkPageNo").html());
		if (pageNo > 1) {
			var cityId = $('#city').val();
			var blockId = $('#gHouse').val();
			var postData1 = {
				cId: cityId,
				gId: blockId,
				pageNo:pageNo - 1
			}
			getRequstedVehicleData(postData1);;
			$("#checkPageNo").html(pageNo - 1);
		}
	});
	$("#arrivalCheckBox").on("change",function(){
			if ($("#arrivalCheckBox").is(":checked")) {
				$("#arrTbl").show();
			}else{
				$("#arrTbl").hide();
			}
	});
	$("#departureCheckBox").on("change",function(){
		if ($("#departureCheckBox").is(":checked") && type=='') {
			$("#depTbl").show();
		}else{
			$("#depTbl").hide();
		}
	});
	$(".radioArrDep").on("change",function(){
		if($("input[name='convRadio']:checked").val()=="arrivalRadio"){
			$("#departureBox").hide();
			$("#arrivalBox").show();
			if($("#arrival").val()=="A"){
				$("#arrivalCheckBox").prop('checked',true);
				$("#addVehicleDetail").hide();
				$("#editVehicleDetail").show();
			}
			if($("#arrival").val()==""){
				$("#arrivalCheckBox").prop('checked',true);
				$("#addVehicleDetail").show();
				$("#editVehicleDetail").hide();
				requestedGuestDetails.arrival='1';
			}
		}else{
			$("#arrivalBox").hide();
			$("#departureBox").show();
			if($("#departure").val()=="D"){
				$("#departureCheckBox").prop('checked',true);
				$("#addVehicleDetail").hide();
				$("#editVehicleDetail").show();
			}
			if($("#departure").val()==""){
				$("#addVehicleDetail").show();
				$("#editVehicleDetail").hide();
				$("#departureCheckBox").prop('checked',true);
				requestedGuestDetails.departure='1';
			}
		}
	});
	
});



function addvehicle(reqId) {
		
		$("#timeReport").prop("disabled",false);
		$("#timeReport1").prop("disabled",false);
		var cityId = $('#city').val();
		var blockId = $('#gHouse').val();
		if(cityId==""&blockId==""){
		swal("please select city or guest house");
		$("#vehicleModal").modal("hide");
		return false;
		}
		type='';
		$(".radioArrDep").hide();
		$("#vehicleModal").modal("show");
		$("#editVehicleDetail").hide();
		$("#addVehicleDetail").show();
		$("#for-title").html("ADD VEHICLE ALLOTMENT");
		for (var i = 0; i < totalVehicleRequestedGuest.length; i++) {
		if (totalVehicleRequestedGuest[i].reqId == reqId) {
			requestedGuestDetails = totalVehicleRequestedGuest[i];
				if(requestedGuestDetails.arrival=="1"){
					arrivalTimeReport=requestedGuestDetails.arrivalDetails.stratTime;
				}
				if(requestedGuestDetails.departure=="1"){
					departureTimeReport=requestedGuestDetails.departureDetails.stratTime;
				}
			}
		}


	console.log(requestedGuestDetails);
	if(requestedGuestDetails.arrival=="1"){
		$(".reqId").html("");
		$(".reqId").append(requestedGuestDetails.reqId);
		$("#timeReport").val(requestedGuestDetails.arrivalDetails.stratTime);
		$("#place").val(requestedGuestDetails.arrivalDetails.place);
		$("#arrivalBox").show();
		$("#arrivalCheckBox").prop("checked", true);
		$("#arrivalCheckBox").show();
		$("#arrTbl").show();
	}else{
		$("#arrTbl").hide();
		$("#arrivalBox").hide();
		$("#arrivalCheckBox").prop("checked", false);
	}
	if(requestedGuestDetails.departure=="1"){
		$("#departureBox").show();
		$("#depTbl").show();
		$(".req1").html("");
		$(".req1").append(requestedGuestDetails.reqId);
		$("#timeReport2").val(requestedGuestDetails.departureDetails.stratTime);
		$("#place2").val(requestedGuestDetails.departureDetails.place);
		$("#departureCheckBox").prop("checked", true);
		$("#departureCheckBox").show();
	}else{
		$("#departureBox").hide();
		$("#departureCheckBox").prop("checked", false);
		$("#depTbl").hide();
	}


}

function putDataInArrivalData(data) {
	$("#arrproName").val(data.protocolName);
	$("#arrmobileNo").val(data.protocolMobile);
	$("#arrdrivName").val(data.driverName);
	$("#arrdrivmobileNo").val(data.driverMobileNo);
	$("#vehicleDt").val(data.vehicledesc);
	$("#vehicleNo").val(data.vehicleNo);
}
function putDataInDepartureData(data) {
	$("#proName").val(data.protocolName);
	$("#promobileNo").val(data.protocolMobile);
	$("#drivName").val(data.driverName);
	$("#drivmobileNo").val(data.driverMobileNo);
	$("#depvehicleDt").val(data.vehicledesc);
	$("#depvehicleNo").val(data.vehicleNo);
}




function getRequstedVehicleData(postData) {
	$.ajax({
		url: './admin/getVehicleAllotedData.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			
		
		
			totalVehicleRequestedGuest = result.data.data;
			$("#vehicleTable").html("");
			console.log(result);
			
			let pageNo = $("#checkPageNo").html();
			if (pageNo >= result.data.totalPages) {
				$("#checkPageNext").attr("disabled", true);
			} else {
				$("#checkPageNext").attr("disabled", false);
			}
	
			if (pageNo == 1) {
				$("#checkPagePrev").attr("disabled", true);
			} else {
				$("#checkPagePrev").attr("disabled", false);
			}
			
			
			
			for (var i = 0; i < result.data.data.length; i++) {

				var gender = '';

				if (result.data.data[i].guestDetails.gender == "M") {
					gender = "male";
				} else if (result.data.data[i].guestDetails.gender == "F") {
					gender = "female";
				} else {
					gender = "other";
				}
				var arrivalModofTravel = '';
				if(result.data.data[i].arrival!=0){
					if (result.data.data[i].arrivalDetails.modeOfTravel == "F") {
					arrivalModofTravel = "FLIGHT";
					} else if (result.data.data[i].arrivalDetails.modeOfTravel == "T") {
					arrivalModofTravel = "TRAIN";
					} else {
					arrivalModofTravel = "other";
					}
				}




				var departureModofTravel = '';
				if(result.data.data[i].departure!=0){
					if (result.data.data[i].departureDetails.modeOfTravel == "F") {
					departureModofTravel = "FLIGHT";
					} else if (result.data.data[i].departureDetails.modeOfTravel == "T") {
					departureModofTravel = "TRAIN";
					} else {
					departureModofTravel = "other";
					}
				}
				var button = '';

				if (result.data.data[i].checkStatus == "0") {
					button = "<button id='openVehicleModal' title='Allot Vehicle' onclick=addvehicle('" + result.data.data[i].reqId + "') class='btn btn-sm btn-secondary btn-locmaptouser btn-view' type='button'><i class='fa fa-plus'></i></button> ";
				} else {
					button = "<button id='openVehicleModalForEdit' title='Update Allot Vehicle' onclick=editvehicle('" + result.data.data[i].reqId + "') class='btn btn-sm edit-btn' type='button'><i class='fa fa-edit'></i></button>  <button id='openVehicleModal2' title='Cancel Vehicle' onclick=deletevehicle('" + result.data.data[i].reqId + "') class='btn btn-sm btn-dlt mr-1'type='button'><i class='fa fa-close'></i></button> ";
				}
				$("#vehicleTable").append("<tr>" +
  					"<td class='text-center text-sm text-truncate'>" + result.data.data[i].reqId + "</td>" +
 					 "<td class='text-left text-sm text-truncate'><span><b>GuestName :</b>" + result.data.data[i].guestDetails.name + "</span><br /> <span><b>Gender :</b>" + gender + "</span><br /> <span><b>Department :</b>" +
 				 result.data.data[i].guestDetails.department + "</span><br /> <span><b>Designation :</b>" + result.data.data[i].guestDetails.designation + "</span><br /></td>" +
  				(result.data.data[i].arrival != 0 ? "<td class='text-left text-sm text-truncate'><span><b>Mode of Travel :</b>" + arrivalModofTravel + "</span><br /> <span><b>Train/Flight Number :</b>" + result.data.data[i].arrivalDetails.vehNumber + "</span><br /> <span><b>Arrival Place :</b>" + result.data.data[i].arrivalDetails.place + "</span><br />" +
  				"<span><b>Arrival Time :</b>" + result.data.data[i].arrivalDetails.stratTime + "</span><br /></td>" : "<td class='text-center text-sm text-truncate'>No data Available</td>") +
 				 (result.data.data[i].departure != 0 ? "<td class='text-left text-sm text-truncate'><span><b>Mode of Travel :</b>" + departureModofTravel + "</span><br /> <span><b>Train/Flight Number :</b>" + result.data.data[i].departureDetails.vehNumber + "</span><br /> <span><b>Departure Place :</b>" + result.data.data[i].departureDetails.place + "</span><br />" +
  				"<span><b>Departure Time :</b>" + result.data.data[i].departureDetails.stratTime + "</span><br /></td>" : "<td class='text-center text-sm text-truncate'>No data Available</td>") +
  				"<td class='text-center text-sm text-truncate'>" + button + "</td>" +
  			"</tr>");

			}
		},error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
	});
}


function editvehicle(reqId) {
	$("#timeReport").prop("disabled",true);
	$("#timeReport1").prop("disabled",true);
	var cityId = $('#city').val();
	var blockId = $('#gHouse').val();
	if(cityId==""&blockId==""){
		swal("please select city or guest  house");
		$("#vehicleModal").modal("hide");
		return false;
	}
	var postData = {
		cId: cityId,
		gId: blockId,
		reqId: reqId,
	}
	console.log(postData);
	$.ajax({
		url: './admin/editVehileAllotMentView.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			$("#arrivalCheckBox").hide();
			$("#departureCheckBox").hide();
			$(".radioArrDep").show();
			$('#loading').hide();
			console.log(result);
			$("#editVehicleDetail").show();
			$("#for-title").html("EDIT VEHICLE ALLOTMENT");
			$("#addVehicleDetail").hide();
			requestedGuestDetails={};
			for (var i = 0; i < result.length; i++) {
				$("#vehicleModal").modal("show");
				
				if (result[i].tripStatus == "A") {
					requestedGuestDetails.reqId=result[i].reqId;
					$(".reqId").html("");
					$('#reqIdInEdit').val(result[i].reqId);
					$(".reqId").append(result[i].reqId);
					$("#timeReport").val(result[i].reqTime);
					$("#place").val(result[i].place);
					$("#arrproName").val(result[i].protocolName);
					$("#arrmobileNo").val(result[i].protocolNo);
					$("#arrdrivName").val(result[i].driverName);
					$("#arrdrivmobileNo").val(result[i].driverNo);
					$("#vehicleDt").val(result[i].vehicleDetails);
					$("#vehicleNo").val(result[i].vehicleNo);
					$("#arrVehicleReqId").val(result[i].vehicleRequestId);
					$("#startTime").val(result[i].startTime);
					$("#endTime").val(result[i].endTime);
					$("#arrival").val(result[i].tripStatus);
					$("#arrivalBox").show();
					$("#arrTbl").show();
					if(result[i].departure=='1'){
						$(".req1").html("");
						$(".req1").append(result[i].reqId);
						$("#timeReport2").val(result[i].unexceptedTime);
						$("#place2").val(result[i].unexceptedPlace);
						$("#departureBox").show();
						$("#depTbl").show();
					}else{
						$("#departureBox").hide();
					}
				}
				if (result[i].tripStatus == "D") {
					$(".req1").html("");
					requestedGuestDetails.reqId=result[i].reqId;
					$(".req1").append(result[i].reqId);
					$("#departure").val(result[i].tripStatus);
					$("#timeReport2").val(result[i].reqTime);
					$("#place2").val(result[i].place);
					$("#proName").val(result[i].protocolName);
					$("#promobileNo").val(result[i].protocolNo);
					$("#drivName").val(result[i].driverName);
					$("#drivmobileNo").val(result[i].driverNo);
					$("#depvehicleDt").val(result[i].vehicleDetails);
					$("#depvehicleNo").val(result[i].vehicleNo);
					$("#arrVehicleReqId2").val(result[i].vehicleRequestId);
					$("#startTime1").val(result[i].startTime);
					$("#endTime1").val(result[i].endTime);
					departureRequest = $("#timeReport2").val();
					$("#depTbl").show();
					if(result[i].arrival=='1'){
						$(".reqId").html("");
						$('#reqIdInEdit').val(result[i].reqId);
						$(".reqId").append(result[i].reqId);
						$("#timeReport").val(result[i].unexceptedTime);
						$("#place").val(result[i].unexceptedPlace);
						$("#arrivalBox").show();
						$("#arrTbl").show();
					}else{
						$("#arrivalBox").hide();
					}
						$("#departureBox").show();
					
				}
			}
			$("#addVehicleDetail").hide();
			$("#editVehicleDetail").hide();
		},
		error: function(jqXHR, status, error) {
			$('#loading').hide();
			$("#vehicleModal").modal("hide");
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	})

}

function deletevehicle(reqId) {
	var cityId = $('#city').val();
	var blockId = $('#gHouse').val();
	var postData1 = {
		cId: cityId,
		gId: blockId,
		pageNo:1
	}
	swal({
		title: "Are you sure? ",
		text: "Are you sure to cancel the Vehicle Details!",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "Yes, cancel it!",
		closeOnConfirm: false
	},
		function() {
			$('#loading').show();
			$.ajax({
				url: './admin/cancelVehicleAllotment/' + reqId + '.do',
				type: "GET",
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(result) {
					$('#loading').hide();
					console.log(result);
					swal(result.status);
					getRequstedVehicleData(postData1);
				},
				error: function(jqXHR, status, error) {
					$('#loading').hide();
					$("#vehicleModal").modal("hide");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
		});
}


function reset() {
	$(".reqId").html("");
	$('#reqIdInEdit').val("");
	$(".reqId").html("");
	$("#timeReport").val("");
	$("#place").val("");
	$("#arrproName").val("");
	$("#arrmobileNo").val("");
	$("#arrdrivName").val("");
	$("#arrdrivmobileNo").val("");
	$("#vehicleDt").val("");
	$("#vehicleNo").val("");
	$("#arrVehicleReqId").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	$("#arrival").val("");
	$(".req1").html("");
	$("#departure").val("");
	$("#timeReport2").val("");
	$("#place2").val("");
	$("#proName").val("");
	$("#promobileNo").val("");
	$("#drivName").val("");
	$("#drivmobileNo").val("");
	$("#depvehicleDt").val("");
	$("#depvehicleNo").val("");
	$("#arrVehicleReqId2").val("");
	$("#startTime1").val("");
	$("#endTime1").val("");
}

function convertStringToDatetime(dateString) {
    return moment(dateString, 'DD-MM-YYYY HH:mm').toDate();
}

function dateCompairisionForFromDate(strtDt,reportTime){
	if( strtDt < reportTime){
		return true;
	}else{
		return false;
	}
}

function dateCompairisionForToDate(endTime,reportTime){
	if(reportTime < endTime){
		return true;
	}else{
		return false;
	}
}
function checkFromValidation(protocolName,protocolNo,driverName,driverMobile,vehDetails,vehNo,startTime,endTime){
if (protocolName !== "" && protocolNo !== "" && driverName !== "" && driverMobile !== "" && vehDetails !== "" && vehNo !== "" && startTime !== "" && endTime !== "") {
	return false;
	}else{
	return true;
	}
}