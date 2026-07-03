var selectCheckInGstId = '';
var selectCheckOutGstId = '';
var selectCheckAllotRoom='';
var totalCheckOutGuest = '';
var fromDateSetValidation = '';
var toDateSetValidation = '';
var checkFromDate = '';
var checkToDate = '';
var editFromDt = '';
var editToDt = '';
var trId='';
$('document').ready(function() {
	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});
	$('#checkInList').show();
	$('#checkOutList').hide();
	$('#gHouse').on("change",function(){
	$("#showCheckInData").removeClass("custom-btn-active");
	$("#showCheckOutData").removeClass("custom-btn-active");
	$("#showCheckcheckInSlipData").removeClass("custom-btn-active");
	$("#tbody_checkin").html("");
	$("#tbody_checkOut").html("");
	$("#checkInslipListbody").html("");
	});
		$('#showCheckInData').on('click', function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		if (city == '' || gHouse == '') {
			swal("Please provide City or Guest House");
		} else {
			$('#checkInList').show();
			document.getElementById("headerName").innerHTML = "List of Request for Check-In";
			$('#checkOutList').hide();
			$("#showCheckInData").addClass("custom-btn-active");
			$("#showCheckOutData").removeClass("custom-btn-active");
			$("#showCheckcheckInSlipData").removeClass("custom-btn-active");
			checkForShift(city, gHouse);
			setTimeout(function() {
				let status = $('#shiftStatus').val();
				if (status == "true") {
					getCheckinDetails(1);
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


	$('#showCheckOutData').on('click', function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		if (city == '' || gHouse == '') {
			swal("Please provide City or Guest House");
		} else {
			$("#checkInslipList").hide();
			$('#roomDetails').html('');
			selectCheckOutGstId = '';
			$('#checkOutList').show();
			document.getElementById("headerName").innerHTML = "List of Request for Check-Out";
			$('#checkInList').hide();
			$("#showCheckInData").removeClass("custom-btn-active");
			$("#showCheckOutData").addClass("custom-btn-active");
			$("#showCheckcheckInSlipData").removeClass("custom-btn-active");
			checkForShift(city, gHouse);
			setTimeout(function() {
				let status = $('#shiftStatus').val();
				if (status == "true") {
					getCheckOutDetails(1);
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
	$('#currentDataTable2 tbody').on(
		'click',
		'.checkout',
		function() {
			var data = table.row($(this).parents('tr'))
				.data();
			$("#checkouttime").datetimepicker({
				format: 'hh:mm A'
			});
		});
	table = $("#checkInOutDataTable")
		.DataTable(
			{

				"lengthMenu": [[10, 20, 30, -1],
				[10, 20, 30, "All"]],
				"processing": false,
				"ordering": true,
				"info": false,
				"bFilter": true,
				"scrollX": true,
				"paging": true,
				"searching": true,
				"fixedHeader": {
					header: true,
					footer: true
				},
				"scrollCollapse": true,
				"columnDefs": [
					{
						"targets": 0,
						"data": null,
						render: function(data,
							type, row, meta) {
							return meta.row
								+ meta.settings._iDisplayStart
								+ 1;
						}
					},
					{
						"targets": 1,
						"data": "reqId"
					},
					{
						"targets": 2,
						"data": "fromDt"
					},
					{
						"targets": 3,
						"data": "toDt"
					},
					{
						"targets": 4,
						"data": "room"
					},
					{
						"targets": 5,
						"data": "guest"
					},
					{
						"targets": 6,
						"data": null,
						render: function(data,
							type, row, meta) {
							// if(data.status ==
							// 'REQ'){
							return '<input type="checkbox" id="check" action="Check" class=""/>'
						}
					},
					{
						"targets": 7,
						"data": null,
						render: function(data,
							type, row, meta) {
							// if(data.status ==
							// 'REQ'){
							return '<button type="button" id="checkIn" action="CheckIn" title="CheckIn" class="btn btn-sm btn-view"><i class="fa fa-plug" aria-hidden="true"></i></button> '
								+ '<button type="button" action="CheckOut" id="checkOut" title="CheckOut" class="btn btn-sm btn-edit "><i class="fa fa-calendar-check-o" aria-hidden="true"></i></button> '
								+ '<button type="button" id="cancel" title="Cancel" action="Cancel" class="btn btn-sm btn-dlt"><i class="fa fa-close" aria-hidden="true"></i></button> '

						}
					}],
			});

	var table2 = null;
	table2 = $("#otherDataTable").DataTable({
		"bFilter": false,
		"info": false,
		// "scrollX": true,
		"paging": false,
		"searching": false,
		// "fixedHeader" : {
		// header : true,
		// footer : true
		// },
		// "scrollCollapse" : true,
		"columnDefs": [{
			"targets": 0,
			"data": "reqId"
		}, {
			"targets": 1,
			"data": "guestId"
		}, {
			"targets": 2,
			"data": "guest"
		}, {
			"targets": 3,
			"data": "rollNo"
		}, {
			"targets": 4,
			"data": "time"
		}, {
			"targets": 5,
			"data": "advAmount"
		}, {
			"targets": 6,
			"data": "room"
		}

		],
	});

	var table4 = null;

	table4 = $("#otherDataTable2").DataTable({
		"bFilter": false,
		// "scrollX": true,
		"info": false,
		"paging": false,
		"searching": false,
		// "fixedHeader" : {
		// header : true,
		// footer : true
		// },
		// "scrollCollapse" : true,
		"columnDefs": [{
			"targets": 0,
			"data": "reqId"
		}, {
			"targets": 1,
			"data": "guestId"
		}, {
			"targets": 2,
			"data": "guest"
		}, {
			"targets": 3,
			"data": "rollNo"
		}, {
			"targets": 4,
			"data": "time"
		}, {
			"targets": 5,
			"data": "advAmount"
		},/* {
			"targets": 6,
			"data": "adults"
		}, {
			"targets": 7,
			"data": "children"
		},*/ {
			"targets": 6,
			"data": "room"
		}

		],
	});

	var table5 = null;
	table5 = $("#currentDataTable2")
		.DataTable(
			{
				  "paging": false,
                  "searching": false,
                  "scrollX": false,
                  "processing": false,
                  "ordering": false,
                  "info": false,
                  "bFilter": false,
                  "bLengthChange": true,
                  "fixedHeader": {
                      header: true,
                      footer: true
                  },
                  "scrollCollapse": true,
				"columnDefs": [
					{
						"targets": 0,
						"data": "reqId"
					},
					{
						"targets": 1,
						"data": "guestId"
					},
					{
						"targets": 2,
						"data": "guest"
					},
					{
						"targets": 3,
						"data": "rollNo"
					},
					{
						"targets": 4,
						"data": "time"
					},
					{
						"targets": 5,
						"data": "advAmount"
					},
                    /*{
                        "targets": 6,
                        "data": "adults"
                    },
                    {
                        "targets": 7,
                        "data": "children"
                    },*/
					{
						"targets": 6,
						"data": "room"
					},
					{
						"targets": 7,
						"data": null,
						render: function(data,
							type, row, meta) {
							return '<input type="text" placeholder="time" id="checkouttime" name="checkouttime" class="form-control checkout">'

						}
					}],
			});



	/*var table6 = null;
	table6 = $("#checkInslipListtable")
		.DataTable(
			{
				"bFilter": false,
				// "scrollX": true,
				"info": false,
				"paging": true,
				"searching": false,
				// "fixedHeader" : {
				// header : true,
				// footer : true
				// },
				// "scrollCollapse" : true,
				"columnDefs": [
					{
						"targets": 0,
						"name": "reqId",
						"data": "reqId"
					},
					{
						"targets": 1,
						"name": "arrivalDate",
						"data": "arrivalDate"
					},
					{
						"targets": 2,
						"name": "departureDate",
						"data": "departureDate"
					},
					{
						"targets": 3,
						"name": "name",
						"data": "name"
					},
					{
						"targets": 4,
						"data": function(row, type, val, meta) {
							return [row.trId];
						},
						render: function(data,
							type, row, meta) {
							return '<button class="btn btn-sm print" action="check In slip" onclick="viewCheckSlip(' + data[0] + ')"><i class="fa fa-print" aria-hidden="true"></i></button>'

						}
					}],
			});*/

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
		getGuestHouseListDtls(id);
	});

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


	$("#filterFromDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
	});

	$('#filterBookingId').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});

	$('#filterCategory').append(
		"<option value='C1'>Category1</option>" +
		"<option value='C2'>Category2</option>" +
		"<option value='C3'>Category3</option>" +
		"<option value='C4'>Category4</option>"
	);

	$('#filterSearch').click(function() {
		getCheckinDetails(1);
		getCheckOutDetails(1);
		getCheckinSlipDetails(1);
		$('#filter-popover').unbind();
		setTimeout(function() {
			$('#filter-popover').bind('click', preventClick);
		}, 1000);
	});

	function preventClick() {
		event.stopPropagation();
	}

	$('#resetFiterBtn').click(function() {
		$('#filterBookingId').val('');
		$('#filterFromDate').val('');
		$('#filterCategory').val('');
		$('#filterGuestName').val('');
		getCheckinDetails(1);
		getCheckOutDetails(1);
		getCheckinSlipDetails(1);
	});

	var getCheckinDetails = function(pageNo) {
		let bookingId = $('#filterBookingId').val();
		let fromDate = $('#filterFromDate').val();
		let name = $('#filterGuestName').val();
		let category = $('#filterCategory').val();
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		if (city == '' || gHouse == '') {
			swal("Please provide City or Guest House");
		}
		let postData = {
			cId: city,
			gId: gHouse,
			bookingId: bookingId,
			fromDate: fromDate,
			name: name,
			category: category,
			pageNo: pageNo
		};
		$.ajax({
			url: './admin/getCheckinDetails.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result);
				$("#tbody_checkin").html("");
				if(result.data.data.length=="0"){
				$("#tbody_checkin").html("<tr><td colspan='11' class='no-data'>No Data Available</tr></td>");
				}
				for (var i = 0; i < result.data.data.length; i++) {
					var rows = result.data.data[i].guestDetails.length;
					var temp_room = "";
					var temp_bed = "";
					var temprb = "";
					for (j = 0; j < result.data.data[i].roomDetails.length; j++) {
						temp_room = result.data.data[i].roomDetails[j].roomNo;
						if (result.data.data[i].roomDetails[j].type != "R") {
							temp_bed = result.data.data[i].roomDetails[j].bedNo;
						} else {
							temp_bed = "R";
						}

						if (temprb == "") {
							temprb = temp_room + "-" + temp_bed;
						} else {
							temprb = temprb + "," + temp_room + "-" + temp_bed;
						}
					}
					var details = "";

					if (result.data.data[i].guestDetails[0].address != "") {
						details = details + "," + result.data.data[i].guestDetails[0].address;
					}
					if (result.data.data[i].guestDetails[0].department != "") {
						details = details + "," + result.data.data[i].guestDetails[0].department;
					}
					if (result.data.data[i].guestDetails[0].designation != "") {
						details = details + "," + result.data.data[i].guestDetails[0].designation;
					}
					var status = "";
					if (result.data.data[i].guestDetails[0].status == "null") {
						status = "<input type='checkbox' class='chckcls' value='" + result.data.data[i].guestDetails[0].guestId + "'>";
					} else if (result.data.data[i].guestDetails[0].status == "CIN") {
						status = "<p style='color:red;font-weight:bold;'>CHECKED IN</p>";
					} else {
						status = "<p style='color:red;font-weight:bold;'>CHECKED OUT</p>";
					}
					$("#tbody_checkin").append("<tr>" +
						"<td rowspan='" + rows + "'>" + (i + 1) + "</td>" +
						"<td rowspan='" + rows + "'>" + result.data.data[i].req_id + "</td>" +
						"<td rowspan='" + rows + "'>" + result.data.data[i].from_date + "</td>" +
						"<td rowspan='" + rows + "'>" + result.data.data[i].to_date + "</td>" +
						"<td rowspan='" + rows + "'>" + result.data.data[i].categoryType + "</td>" +
						"<td rowspan='" + rows + "'>" + temprb + "</td>" +
						"<td>" + result.data.data[i].guestDetails[0].name + "</td>" +
						"<td>" + result.data.data[i].guestDetails[0].designation + "</td>" +
						"<td>" + result.data.data[i].guestDetails[0].department + "</td>" +
						"<td>" + status + "</td>" +
						"<td class='text-nowrap' rowspan='" + rows + "'>" +
						"<button type='button' id='checkIn' onclick=viewdata('" + result.data.data[i].req_id + "','" + result.data.data[i].from_date + "','" + result.data.data[i].to_date + "') " +
						
						" title='CheckIn' class='btn btn-sm d-inline extension ml-1'>" +
						"<i class='fa fa-plug' aria-hidden='true'></i>" +
						"</button> " +
						"<button type='button' id='viewAllotRoom' onclick=viewAllotData('" + result.data.data[i].req_id+"') " +
						" title='Cancel Allot Room' class='btn btn-sm d-inline btn-dlt ml-1'>" +
						"<i class='fa fa-close' aria-hidden='true'></i>" +
						"</button> " +
						"</td>" +
						"</tr>");
					for (var k = 1; k < result.data.data[i].guestDetails.length; k++) {
						var status = "";
						if (result.data.data[i].guestDetails[k].status == "null") {
							status = "<input type='checkbox' class='chckcls' value='" + result.data.data[i].guestDetails[k].guestId + "'>";
						} else if (result.data.data[i].guestDetails[k].status == "CIN") {
							status = "<p style='color:red;font-weight:bold;'>CHECKED IN</p>";
						} else {
							status = "<p style='color:red;font-weight:bold;'>CHECKED OUT</p>";
						}

						$("#tbody_checkin").append("<tr>" +
							"<td>" + result.data.data[i].guestDetails[k].name + "</td>" +
							"<td>" + result.data.data[i].guestDetails[k].designation + "</td>" +
							"<td>" + result.data.data[i].guestDetails[k].department + "</td>" +
							"<td>" + status + "</td>" +
							"</tr>");
					}
				}

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
		});
	}

	var getCheckOutDetails = function(pageNo) {
		let bookingId = $('#filterBookingId').val();
		let fromDate = $('#filterFromDate').val();
		let name = $('#filterGuestName').val();
		let category = $('#filterCategory').val();
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		if (city == '' || gHouse == '') {
			swal("Please provide City or Guest House");
		}
		let postData = {
			cId: city,
			gId: gHouse,
			bookingId: bookingId,
			fromDate: fromDate,
			name: name,
			category: category,
			pageNo: pageNo
		};

		$.ajax({
			url: './admin/getCheckOutDetails.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
				 $('#loading').show()
			},
			success: function(result) {
				/*console.log(result);*/
				 $('#loading').hide()
				totalCheckOutGuest = result.data.data;
				$("#tbody_checkOut").html("");
				if(result.data.data.length=="0"){
				$("#tbody_checkOut").html("<tr><td colspan='11' class='no-data'>No Data Available</tr></td>");
				}
				for (var i = 0; i < result.data.data.length; i++) {
					var rows = result.data.data[i].guestDetails.length;
					var temp_room = "";
					var temp_bed = "";
					var temprb = "";
					for (j = 0; j < result.data.data[i].roomDetails.length; j++) {
						temp_room = result.data.data[i].roomDetails[j].roomNo;
						if (result.data.data[i].roomDetails[j].type != "R") {
							temp_bed = result.data.data[i].roomDetails[j].bedNo;
						} else {
							temp_bed = "R";
						}

						if (temprb == "") {
							temprb = temp_room + "-" + temp_bed;
						} else {
							temprb = temprb + "," + temp_room + "-" + temp_bed;
						}
					}
					var details = "";

					if (result.data.data[i].guestDetails[0].address != "") {
						details = details + "," + result.data.data[i].guestDetails[0].address;
					}
					if (result.data.data[i].guestDetails[0].department != "") {
						details = details + "," + result.data.data[i].guestDetails[0].department;
					}
					if (result.data.data[i].guestDetails[0].designation != "") {

						details = details + "," + result.data.data[i].guestDetails[0].designation;
					}

					var status = "";
					if (result.data.data[i].guestDetails[0].status == "null") {
						status = "<p style='color:red;font-weight:bold;'>NOT CHECKED IN</p>";
					} else if (result.data.data[i].guestDetails[0].status == "CIN") {
						status = "<input type='checkbox' id='chck' class='checkOutBox' name='chck' value=" + result.data.data[i].guestDetails[0].guestId + ">";
						;
					} else {
						status = "<p style='color:red;font-weight:bold;'>CHECKED OUT</p>";
					}
					var editButton = '';
					if (result.data.data[i].ext == '1') {
						editButton = '';
					} else {
						editButton = "<button type='button' id='edit' title='Edit CheckIn'" +
							"action='Edit' class='btn btn-sm edit-btn' onclick=viewEditListDetails('" + result.data.data[i].req_id + "','" + result.data.data[i].from_date + "','" + result.data.data[i].to_date + "')>" +
							"<i class='fa fa-edit' aria-hidden='true'></i>" +
							"</button>";
					}
					let checkoutBtn = '';
					if (result.data.data[i].canStatus == "PENDING") {
						checkoutBtn = '';
					} else {
						checkoutBtn = "<button type='button' id='checkOut' onclick=viewCheckOutDateValidate('" + result.data.data[i].req_id + "','" + result.data.data[i].from_date + "','" + result.data.data[i].to_date + "') " +
							" title='checkOut' class='btn btn-sm d-inline extension ml-1'>" +
							"<i class='fa fa-plug' aria-hidden='true'></i>" +
							"</button> ";
					}
					/*debugger;*/
			
					$("#tbody_checkOut").append("<tr>" +
						"<td rowspan='" + rows + "'>" + (i + 1) + "</td>" +
						"<td rowspan='" + rows + "'  >" + result.data.data[i].req_id + "</td>" +
						"<td rowspan='" + rows + "'>" + result.data.data[i].from_date + "</td>" +
						"<td rowspan='" + rows + "' >" + result.data.data[i].to_date + "</td>" +
						"<td rowspan='" + rows + "'>" + result.data.data[i].categoryType + "</td>" +
						"<td rowspan='" + rows + "' >" + temprb + "</td>" +
						"<td>" + result.data.data[i].guestDetails[0].name + "</td>" +
						"<td>" + result.data.data[i].guestDetails[0].designation + "</td>" +
						"<td>" + result.data.data[i].guestDetails[0].department + "</td>" +
						"<td>" + status + "</td>" +
						"<td class='text-nowrap' rowspan='" + rows + "'>" +
						checkoutBtn +
						editButton +
						"  <button type='button' id='cancel' title='Cancel Checkin'" +
						"action='Cancel' class='btn btn-sm btn-dlt' onclick=viewCancelListDetail('" + result.data.data[i].req_id + "','" + result.data.data[i].ext + "')>" +
						"<i class='fa fa-close' aria-hidden='true'></i>" +
						"</button>" +
						"</td>" +
						"</tr>");

					for (var k = 1; k < result.data.data[i].guestDetails.length; k++) {
						var status = "";
						if (result.data.data[i].guestDetails[k].status == "null") {
							status = "<p style='color:red;font-weight:bold;'>NOT CHECKED IN</p>";
						} else if (result.data.data[i].guestDetails[k].status == "CIN") {
							status = "<input type='checkbox' id='chck' class='checkOutBox' name='chck' value=" + result.data.data[i].guestDetails[k].guestId + ">";
						} else {
							status = "<p style='color:red;font-weight:bold;'>CHECKED OUT</p>";
						}

						$("#tbody_checkOut").append("<tr>" +
							"<td>" + result.data.data[i].guestDetails[k].name + "</td>" +
							"<td>" + result.data.data[i].guestDetails[k].designation+ "</td>" +
							"<td>" + result.data.data[i].guestDetails[k].department + "</td>" +
							"<td>" + status + "</td>" +
							"</tr>");
					}
				}
				let pageNo = $("#checkOutPageNo").html();
				if (pageNo >= result.data.totalPages) {
					$("#checkOutPageNext").attr("disabled", true);
				} else {
					$("#checkOutPageNext").attr("disabled", false);
				}

				if (pageNo == 1) {
					$("#checkOutPagePrev").attr("disabled", true);
				} else {
					$("#checkOutPagePrev").attr("disabled", false);
				}
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
		});
	}


	$('#guestcheckin').on('click', function() {
		var today1 = new Date();
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var saveCheckin = { ReqId: ReqId, city: city, gHouse: gHouse,extraGuest:$("#addGuest1").val()};
		let data = [];
		var boolean = true;
		var prevdate = true;
		var sameDate = true;
		var totalAdvnc=0;
		$('#currentCheckin tr').each(function() {
			let gId = $(this).find('td:eq(1)').text();
			let roll = $(this).find('input[name="roll"]').val();
			let Intime = $(this).find('input[name="checkinDt"]').val();
			let children = $(this).find('input[name="children"]').val();
			let advncamount = $(this).find('input[name="amount"]').val();
			totalAdvnc=parseInt(totalAdvnc)+parseInt(advncamount);
			let adult = $(this).find('input[name="adult"]').val();
			let category = $(this).find('select[name="category"]').val();
			let purpose = $(this).find('input[name="purpose"]').val();
			let room = $(this).find('input[name="room"]').val();
			let extraGuest=$(this).find('input[name="extraGuest"]').val();
			/*  debugger;*/
			if (Intime !== '') {
				console.log(Intime);
				const splitted = Intime.split("-");
				var checkDt = new Date();
				checkDt.setDate(splitted[0]);
				checkDt.setMonth(splitted[1] - 1);
				checkDt.setFullYear(splitted[2].slice(0, 4));

				var fromDt = new Date();
				const splitfromDt = fromDateSetValidation.split("-");
				fromDt.setDate(splitfromDt[0]);
				fromDt.setMonth(splitfromDt[1] - 1);
				fromDt.setFullYear(splitfromDt[2]);
				console.log(today1);
				console.log(fromDt);
				if (today1.setHours(0, 0, 0, 0) == fromDt.setHours(0, 0, 0, 0) || today1.setHours(0, 0, 0, 0) > fromDt.setHours(0, 0, 0, 0)) {
					if (fromDt.setHours(0, 0, 0, 0) !== checkDt.setHours(0, 0, 0, 0)) {
						sameDate = false;
					}
				} else {
					prevdate = false;
				}
			}
			
			
			if (roll !== '' && Intime !== '') {
				data.push({
					gstId: gId,
					rollNo: roll,
					intime: Intime,
					children: children,
					advncamount: advncamount,
					adults: adult,
					category: category,
					purpose: purpose,
					room: room
				});
			} else {
				boolean = false;
			}
		});
		if(totalAdvnc>parseInt($("#totalAmount").html())){
			swal("Advance Amount cann't be greater than total amount");
			return false;
		}
		if (!boolean) {
			swal('Please provide registration number and time');
			return false;
		}
		if (!prevdate) {
			swal("you can't check someone before it's from Date");
			return false;
		}
		/*if (!sameDate) {
			swal("From Date and Checkin Date Must be Same");
			return false;
		}*/
		saveCheckin.roomDetails = data;
		console.log(saveCheckin);
		$.ajax({
			url: './admin/saveCheckinDetail.do',
			type: 'POST',
			data: JSON.stringify(saveCheckin),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(result) {
				$('#loading').hide();
				console.log(result);
				$("#checkInModal").modal("hide");
				if(result.status==true){
					swal('CheckIn Successfull!');
				}else{
					swal('Something went wrong');
				}
				selectCheckInGstId = '';
				getCheckinDetails(1);
			},
			error: function(jqXHR, status, error) {
			$('#loading').hide();
				$("#checkInModal").modal("hide");
				swal("Not checked in.");
			}

		});
	});
	$('#guestcheckout').on('click', function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var saveCheckin = { city: city, gHouse: gHouse };
		let data = [];
		var boolean = true;
		var toerror = true;
		var beforeDtError = true;
		var today = new Date();
        /*var date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();
        var time = today.getHours() + ":" + today.getMinutes();
        var dateTime = date + ' ' + time;
        var today1 = new Date(dateTime);*/
		$('#checkOutGstBody tr').each(function() {
			let ReqId = $(this).find('td:eq(0)').text();
			let gstId = $(this).find('td:eq(1)').text();
			let trId = $(this).find('td:eq(3)').text();
			let checkOut = $(this).find('input[name="checkOutDt"]').val();
			let parentTrId = $(this).find('td:eq(9)').text();
			/*debugger;*/




			if (checkOut !== '') {
				const splitted = checkOut.split("-");
				var checkOutDt = new Date();
				checkOutDt.setDate(splitted[0]);
				checkOutDt.setMonth(splitted[1] - 1);
				checkOutDt.setFullYear(splitted[2].slice(0, 4));

				var toDt = new Date();
				const splitfromDt = checkToDate.split("-");
				toDt.setDate(splitfromDt[0]);
				toDt.setMonth(splitfromDt[1] - 1);
				toDt.setFullYear(splitfromDt[2]);
				console.log(toDt);
				if (today.setHours(0, 0, 0, 0) >= checkOutDt.setHours(0, 0, 0, 0)) {
					if (checkOutDt.setHours(0, 0, 0, 0) <= toDt.setHours(0, 0, 0, 0)) {
					} else {
						toerror = false;
					}
				} else {
					beforeDtError = false;
				}

				data.push({
					gstId: gstId,
					trId: trId,
					ReqId: ReqId,
					checkOut: checkOut,
					parentTrId: parentTrId
				});
			} else {
				boolean = false;
			}
		});
		if (!boolean) {
			swal('Please provide checkout time');
			return false;
		}
		if (!toerror) {
			swal("checkOut date can't be greater than to date");
			return false;
		}
		if (!beforeDtError) {
			swal("you can't provide greater data from today");
			return false;
		}
		saveCheckin.roomDetails = data;
		console.log(saveCheckin);
		$.ajax({
			url: './admin/saveCheckOutDetail.do',
			type: 'POST',
			data: JSON.stringify(saveCheckin),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(result) {
				$('#loading').hide();
				console.log(result);
				$("#checkOutModal").modal("hide");
				if(result.status==true){
					swal('CheckOut Successfull!');
				}else{
					swal('Something went wrong');
				}
				selectCheckOutGstId = '';
				getCheckOutDetails(1);
			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#checkOutModal").modal("hide");
				swal('Something went wrong');
			}

		});
	});

	$('#guestcancelcheckin').on('click', function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var saveCheckin = { city: city, gHouse: gHouse };
		let data = [];
		$('#currentCancelCheckin tr').each(function() {
			let ReqId = $(this).find('td:eq(0)').text();
			let gstId = $(this).find('td:eq(1)').text();
			let trId = $(this).find('td:eq(3)').text();
			let parentTrId = $(this).find('td:eq(7)').text();
			let ext = $(this).find('td:eq(6)').text();
			let advncAmount = $(this).find('td:eq(8)').text();
			/*debugger;*/
			data.push({
				gstId: gstId,
				trId: trId,
				ReqId: ReqId,
				parentTrId: parentTrId,
				ext: ext,
				advncAmount: advncAmount
			});
		});
		saveCheckin.roomDetails = data;
		console.log(saveCheckin);
		$.ajax({
			url: './admin/updateCheckOutCancelDetail.do',
			type: 'POST',
			data: JSON.stringify(saveCheckin),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(result) {
				$('#loading').hide();
				console.log(result);
				$("#cancelCheckInModal").modal("hide");
				if(result.status==true){
					swal('Cancel Checkin Successfull!');
				}else{
					swal('Something went wrong');
				}
			
				getCheckOutDetails(1);
				selectCheckOutGstId = '';
			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#cancelCheckInModal").modal("hide");
				swal('Something went wrong');
			}

		});
	});
	
	$('#cancelAllotRoom').on('click', function() {
		if(selectCheckAllotRoom==''){
			swal("Please select atleast one room");
			return false;
		}
		params={
			selectCheckAllotRoom:selectCheckAllotRoom
		}
		$.ajax({
			url: './admin/cancelAllotRoomPerReqId.do',
			type: 'POST',
			data: JSON.stringify(params),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(result) {
				$('#loading').hide();
				console.log(result);
				$("#changeAllotRoom").modal("hide");
				if(result.status){
					swal('Cancel Allotment Successfull!');
				}else{
					swal('Something went wrong');
				}
				getCheckinDetails(1);
				selectCheckAllotRoom='';
			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#editCheckInModal").modal("hide");
				swal('Cancel Allotment not successful');
			}

		});
	});
	
	
	
	
	$('#showCheckcheckInSlipData').on('click', function() {
		$("#headerName").html("LIST OF REQUEST FOR CHECK-IN-SLIP");
		$("#showCheckInData").removeClass("custom-btn-active");
		$("#showCheckOutData").removeClass("custom-btn-active");
		$("#showCheckcheckInSlipData").addClass("custom-btn-active");
		$("#checkInslipList").show();
		$("#checkInList").hide();
		$("#checkOutList").hide();
		getCheckinSlipDetails(1);

	});
	
	

	$('#guesteditcheckin').on('click', function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var saveCheckin = { city: city, gHouse: gHouse,extraGuest:$("#editExtraGst").val() };
		let data = [];
		var totalAdvnc=0;
		$('#currentEditCheckin tr').each(function() {
			let ReqId = $(this).find('td:eq(0)').text();
			let gstId = $(this).find('td:eq(1)').text();
			let trId = $(this).find('td:eq(3)').text();
			let roll = $(this).find('input[name="roll"]').val();
			let Intime = $(this).find('input[name="checkinDt"]').val();
			let advncamount = $(this).find('input[name="advncAmount"]').val();
			totalAdvnc=parseInt(totalAdvnc)+parseInt(advncamount);
			/*debugger;*/
			data.push({
				gstId: gstId,
				trId: trId,
				ReqId: ReqId,
				roll: roll,
				Intime: Intime,
				advncAmount: advncamount
			});
		});
		if(totalAdvnc>parseInt($("#totalAmountAdvnc").html())){
			swal("Advance Amount cann't be greater than total amount");
			return false;
		}
		saveCheckin.guestDetails = data;
		console.log(saveCheckin);
		$.ajax({
			url: './admin/editCheckInCancelDetail.do',
			type: 'POST',
			data: JSON.stringify(saveCheckin),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(result) {
				$('#loading').hide();
				console.log(result);
				$("#editCheckInModal").modal("hide");
				if(result.status){
					swal('Edit Checkin Data Successfull!');
				}else{
					swal('Some thing went wrong');
				}
				
				getCheckOutDetails(1);
				selectCheckOutGstId = '';
			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#editCheckInModal").modal("hide");
				swal('Edit not successful');
			}

		});
	});


	$("#currentCheckin").on("focus", ".checkinDt", function() {
		const splitted = fromDateSetValidation.split("-");
		var frmDt = new Date();
		frmDt.setDate(splitted[0]);
		frmDt.setMonth(splitted[1] - 1);
		frmDt.setFullYear(splitted[2]);

		const tosplitted = toDateSetValidation.split("-");
		console.log(toDateSetValidation);
		var toDt = new Date();
		toDt.setDate(tosplitted[0]);
		toDt.setMonth(tosplitted[1] - 1);
		toDt.setFullYear(tosplitted[2]);

		var today = new Date();
		var date = today.getDate() + '-' + (today.getMonth()) + '-' + today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes();

		const todaysplit = date.split("-");
		var today1 = new Date();
		today1.setDate(todaysplit[0]);
		today1.setMonth(todaysplit[1]);
		today1.setFullYear(todaysplit[2]);
		console.log(today1);

		if (today1.setHours(0, 0, 0, 0) < frmDt.setHours(0, 0, 0, 0)) {
			$(".checkinDt").attr('disabled', 'disabled');
			swal("You can't checkin someone before it's from date.");
		}
		if (today1.setHours(0, 0, 0, 0) == frmDt.setHours(0, 0, 0, 0)) {
			$(this).datetimepicker({
				minDate: moment(),
				maxDate: moment(),
				format: 'DD-MM-YYYY HH:mm'
			})
		}
		
		if (today1.setHours(0, 0, 0, 0) > frmDt.setHours(0, 0, 0, 0)) {
			if (today1.setHours(0, 0, 0, 0) < toDt.setHours(0, 0, 0, 0)) {
				console.log("com to console  if");
				$(this).datetimepicker({
					minDate: frmDt,
					maxDate: moment(),
					format: 'DD-MM-YYYY HH:mm'
				})
			} else if (today1.setHours(0, 0, 0, 0) > toDt.setHours(0, 0, 0, 0)) {
				console.log(today1);
				console.log(toDt);
				console.log("com to console else if");
				$(this).datetimepicker({
					minDate: frmDt,
					maxDate: toDt,
					showTime:true,		
					format: 'DD-MM-YYYY HH:mm'
				})
			} else {
				var cHour = today.getHours();
				var cMnt = today.getMinutes();
				$(this).datetimepicker({
					minDate:  moment(frmDt).millisecond(0).second(0).minute(cMnt).hour(cHour),
					maxDate:  moment(toDt).millisecond(0).second(0).minute(cMnt).hour(cHour),
					format: 'DD-MM-YYYY HH:mm'
				})
			}
		}

	});
	$("#checkOutGstBody").on("focus", ".checkOutDt", function() {
		const splitted = checkFromDate.split("-");
		var minfr = new Date();
		minfr.setDate(splitted[0]);
		minfr.setMonth(splitted[1] - 1);
		minfr.setFullYear(splitted[2]);
		const split = checkToDate.split("-");
		var maxto = new Date();

		maxto.setDate(parseInt(split[0]));
		maxto.setMonth(split[1] - 1);
		maxto.setFullYear(split[2]);

		var today = new Date();
		var date = today.getDate() + '-' + (today.getMonth()) + '-' + today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes();
		var cHour = today.getHours();
		var cMnt = today.getMinutes();
		
		const todaysplit = date.split("-");
		var today1 = new Date();
		today1.setDate(todaysplit[0]);
		today1.setMonth(todaysplit[1]);
		if (today1.setHours(0, 0, 0, 0) <= maxto.setHours(0, 0, 0, 0)) {
			console.log("in both case in if");
			$(this).datetimepicker({
				minDate: moment(minfr).millisecond(0).second(0).minute(cMnt).hour(cHour),
				maxDate: moment(),
				format: 'DD-MM-YYYY HH:mm'
			})
		} else {
			console.log("comes to here....");
			console.log(minfr);
			console.log(maxto);
			$(this).datetimepicker({
				minDate: moment(minfr).millisecond(0).second(0).minute(cMnt).hour(cHour),
				maxDate: moment(maxto).millisecond(0).second(0).minute(cMnt).hour(cHour),
				format: 'DD-MM-YYYY HH:mm'
			})
		}

	});
	$("#currentEditCheckin").on("focus", ".checkInEditDt", function() {
		const splitted = editFromDt.split("-");
		var frmDt = new Date();
		frmDt.setDate(splitted[0]);
		frmDt.setMonth(splitted[1] - 1);
		frmDt.setFullYear(splitted[2]);

		const tosplitted = editToDt.split("-");
		var toDt = new Date();
		toDt.setDate(tosplitted[0]);
		toDt.setMonth(tosplitted[1] - 1);
		toDt.setFullYear(tosplitted[2]);

		var today = new Date();
		var date = today.getDate() + '-' + (today.getMonth()) + '-' + today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes();

		const todaysplit = date.split("-");
		var today1 = new Date();
		today1.setDate(todaysplit[0]);
		today1.setMonth(todaysplit[1]);
		today1.setFullYear(todaysplit[2]);
		console.log(today1);
		if (today1.setHours(0, 0, 0, 0) < frmDt.setHours(0, 0, 0, 0)) {
			$(".checkinDt").attr('disabled', 'disabled');
		}
		if (today1.setHours(0, 0, 0, 0) == frmDt.setHours(0, 0, 0, 0)) {
			$(this).datetimepicker({
				minDate: moment(),
				maxDate: moment(),
				format: 'DD-MM-YYYY HH:mm'
			})
		}
		if (today1.setHours(0, 0, 0, 0) > frmDt.setHours(0, 0, 0, 0)) {
			if (today1.setHours(0, 0, 0, 0) < toDt.setHours(0, 0, 0, 0)) {
				$(this).datetimepicker({
					minDate: frmDt,
					maxDate: moment(),
					format: 'DD-MM-YYYY HH:mm'
				})
			} else if (today1.setHours(0, 0, 0, 0) > toDt.setHours(0, 0, 0, 0)) {
				var cHour = today.getHours();
				var cMnt = today.getMinutes();
				$(this).datetimepicker({
					minDate: moment(frmDt).millisecond(0).second(0).minute(cMnt).hour(cHour),
					maxDate: moment(toDt).millisecond(0).second(0).minute(cMnt).hour(cHour),
					format: 'DD-MM-YYYY HH:mm'
				})
			} else {
				var cHour = today.getHours();
				var cMnt = today.getMinutes();
				$(this).datetimepicker({
					minDate: moment(frmDt).millisecond(0).second(0).minute(cMnt).hour(cHour),
					maxDate: moment(toDt).millisecond(0).second(0).minute(cMnt).hour(cHour),
					format: 'DD-MM-YYYY HH:mm'
				})
			}
		}
	});
	$("#checkInTable tbody").on("change", ".chckcls", function() {
		selectCheckInGstId = $('.chckcls:checked').map(function() {
			return this.value
		}).get().join("','");
		console.log(selectCheckInGstId);
	})

	$("#checkOutTable tbody").on("change", ".checkOutBox", function() {
		selectCheckOutGstId = $('.checkOutBox:checked').map(function() {
			return this.value
		}).get().join("','");
		console.log(selectCheckOutGstId);
	})
	$("#allotRoomTable tbody").on("change", ".allotView", function() {
		selectCheckAllotRoom = $('.allotView:checked').map(function() {
			return this.value
		}).get().join("','");
		console.log(selectCheckAllotRoom);
	})
	$('#showCheckInData').on('click', function() {
		$('#roomDetails').html('');
		$("#checkInslipList").hide();
		selectCheckOutGstId = '';
		selectCheckInGstId = '';
	});
/*	$('#showCheckOutData').on('click', function() {
		
		$("#checkInslipList").hide();
		$('#roomDetails').html('');
		selectCheckOutGstId = '';

	});
*/
	$("#checkPageNext").on("click", function() {
		let pageNo = parseInt($("#checkPageNo").html());
		getCheckinDetails(pageNo + 1);
		$("#checkPageNo").html(pageNo + 1);
	});

	$("#checkPagePrev").on("click", function() {
		let pageNo = parseInt($("#checkPageNo").html());
		if (pageNo > 1) {
			getCheckinDetails(pageNo - 1);
			$("#checkPageNo").html(pageNo - 1);
		}
	});
	
	$("#slipPageNext").on("click", function() {
		let pageNo = parseInt($("#slipPageNo").html());
		getCheckinSlipDetails(pageNo + 1);
		$("#slipPageNo").html(pageNo + 1);
	});

	$("#slipPagePrev").on("click", function() {
		let pageNo = parseInt($("#slipPageNo").html());
		if (pageNo > 1) {
			getCheckinSlipDetails(pageNo - 1);
			$("#slipPageNo").html(pageNo - 1);
		}
	});

	$("#checkOutPageNext").on("click", function() {
		let pageNo = parseInt($("#checkOutPageNo").html());
		getCheckOutDetails(pageNo + 1);
		$("#checkOutPageNo").html(pageNo + 1);
	});

	$("#checkOutPagePrev").on("click", function() {
		let pageNo = parseInt($("#checkOutPageNo").html());
		if (pageNo > 1) {
			getCheckOutDetails(pageNo - 1);
			$("#checkOutPageNo").html(pageNo - 1);
		}
	});
	
	
	$('#generatePdf').on('click', function() {
	/*	let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		if (city == "") {
			swal("Please Select City");
			return false;
		}
		if (gHouse == "") {
			swal("Please Select Guest House");
			return false;
		}*/
		$("#CheckInSlipModal").modal("hide");
		console.log(trId);
		window.open('./admin/viewIndividualCheckinSlipDetailPdf/' + trId + '.do');
	});
});
var ReqId = "";
var viewdata = function(val, fromDate, toDate) {
	if (selectCheckInGstId == '') {
		swal("Please select atleast one guest");
		$("#checkInModal").modal('hide');
		return false;
	}
	var req_id = val;
	var city = $('#city').val();
	var gHouse = $('#gHouse').val();
	var hidval = selectCheckInGstId;
	var postData = {
		cId: city,
		gId: gHouse,
		req_id: req_id,
		fromDate: fromDate,
		toDate: toDate,
		hidval: hidval
	}
	$.ajax({
		url: './admin/viewCheckinDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			toDateSetValidation = result.toDate;
			fromDateSetValidation = result.fromDate;
			$('#checkInModal').modal('show');
			$("#otherGuestData").html("");
			$("#currentCheckin").html("");
			if(result.data.guestDetails.length!="0"){
				ReqId = result.data.guestDetails[0].reqId;
				$("#guestcheckin").show();
			}
			
			console.log(result)
			for (let i = 0; i < result.data.otherguestCheckinDetails.length; i++) {
				var details = "";
				if (result.data.otherguestCheckinDetails[i].name != "") {
					details = details + result.data.otherguestCheckinDetails[i].name;
				}
				if (result.data.otherguestCheckinDetails[i].department != "") {
					details = details + "," +  result.data.otherguestCheckinDetails[i].department;
				}
				if (result.data.otherguestCheckinDetails[i].desgination != "") {
					details = details + "," + result.data.otherguestCheckinDetails[i].desgination;
				}
				var col = "";
				if (result.data.otherguestCheckinDetails[i].status == null) {
					col += " <td colspan='6'> Not checked in...</td> ";
				} else {
					col += " <td> " + result.data.otherguestCheckinDetails[i].rollNo + " </td> " +
						"  <td> " + result.data.otherguestCheckinDetails[i].arrivalTimeDate + " </td> " +
						"	<td> " + result.data.otherguestCheckinDetails[i].advanceAmount + " </td> " +
						"	<td> " + result.data.otherguestCheckinDetails[i].roomDetails.roomNo + "</td> ";
				}
				$("#otherGuestData").append("<tr>" +
				
					"<td>" + result.data.otherguestCheckinDetails[i].reqId + "</td>" +
					"<td>" + result.data.otherguestCheckinDetails[i].guestId + "</td>" +
					"<td>" + details + "</td>" +
					col
					+ "</tr>");
			}


			var gstLength=result.data.guestDetails.length;
			for (let j = 0; j < result.data.guestDetails.length; j++) {

				var details = "";
				if (result.data.guestDetails[j].name != "") {
					details = details + result.data.guestDetails[j].name;
				}
				if (result.data.guestDetails[j].department != "") {
					details = details + "," + result.data.guestDetails[j].department;
				}
				if (result.data.guestDetails[j].designation != "") {
					details = details + "," + result.data.guestDetails[j].designation;
				}



				var dyn = j + 1;
				 $("#currentCheckin").append("<tr>" +
    				"<td>" + result.data.guestDetails[j].reqId + "</td>" +
   	 				"<td>" + result.data.guestDetails[j].guestId + "</td>" +
   					"<td>" + details + "</td>" +
    				"<td>" + "<input type='text' autocomplete='off' id=roll" + dyn + " name=roll size='10' style='text-align:center;' placeholder='Enter a Register No.'>" + "</td>" +
    				"<td>" + "<input type='text' id =checkinDt" + dyn + " placeholder='DD-MM-YYYY' name=checkinDt class='checkinDt' style='text-align:center;' autocomplete='off'>" + "</td>" +
    				(j === 0 ? "<td rowspan='" + result.data.guestDetails.length + "' id='totalAmount'>" + result.data.roomRent + "</td>" : "") +
    				 "<td>" + "<input type='text' autocomplete='off' onkeypress='return onlyNumberKey(event)'  id=amount" + dyn + " name=amount max-lenght='10' placeholder='Enter Amount'  style='text-align:center;'  autocomplete='off' class='amntGt'>" + "</td>" +
    				(j === 0 ? "<td rowspan='" + result.data.guestDetails.length + "'>" + "<input type='text' autocomplete='off' id=addGuest" + dyn + " name=extraGuest size='10' placeholder='Enter Extra Guest'  style='text-align:center;'>" + "</td>"  : "") +
    				"<td style='display:none;'>" + "<input type='hidden' id=roll" + dyn + " name=adult size='2' value='1' disabled></td>" +
    				"<td style='display:none;'>" + "<input type='hidden' id=children" + dyn + " name=children size='2' value='0' disabled></td>" +
    				"<td>" + "<select name=category><option value=" + result.data.selectDesgination.id + " selected>" + result.data.selectDesgination.desgName + "</option></select></td>" +
    				"<td>" + "<input type='text' id=purpose" + dyn + " name=purpose size='2' value=" + result.data.selectPurpose + " disabled>" + "</td > " +
    				"<td>" + "<input type='text' id=room" + dyn + " name=room size='2' value=" + result.data.checkinRoom[0].type + " disabled>" + "</td>" +
    				
    				"</tr>");
			}
			if(result.data.otherguestCheckinDetails.length=="0"){
				$("#otherGuestData").html("<tr><td colspan='7' class='no-data'>No Data Available</tr></td>");
			}
			if(result.data.guestDetails.length=="0"){
				$("#guestcheckin").hide();
				$("#currentCheckin").html("<tr><td colspan='11' class='no-data'>No Data Available</tr></td>");
			}
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
var viewCheckOutData = function(val, frmData, toData) {
	if (selectCheckOutGstId == '') {
		swal("Please select atleast one guest");
		$("#checkOutModal").modal('hide');
		return false;
	}

	var req_id = val;
	var city = $('#city').val();
	var gHouse = $('#gHouse').val();
	var hidval = selectCheckOutGstId;
	var postData = {
		ghId: city,
		blockId: gHouse,
		reqId: req_id,
		hidval: hidval,
		frmdata: frmData,
		toData: toData
	}
	console.log(postData);
	$.ajax({
		url: './admin/viewCheckOutDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			checkFromDate = result.fromDate;
			checkToDate = result.toDate;
			$('#checkOutModal').modal('show');
			ReqId = result.data.guestDetails[0].reqId;
			console.log(result)
			$("#checkOutOtherGstBody").html("");
			$("#checkOutGstBody").html("");

			for (let i = 0; i < result.data.otherguestCheckinDetails.length; i++) {
				var col = "";
				var advanceamount = '';
				if (result.data.otherguestCheckinDetails[i].advanceAmount == '') {
					advanceamount = 0
				} else {
					advanceamount = result.data.otherguestCheckinDetails[i].advanceAmount;
				}
				var gstDetails = "";
				if (result.data.otherguestCheckinDetails[i].name != '') {
					gstDetails = gstDetails + result.data.otherguestCheckinDetails[i].name;
				}
				if (result.data.otherguestCheckinDetails[i].desgination != '') {
					gstDetails = gstDetails + "," + result.data.otherguestCheckinDetails[i].desgination;
				}
				if (result.data.otherguestCheckinDetails[i].department != '') {
					gstDetails = gstDetails + "," + result.data.otherguestCheckinDetails[i].department;
				}


				if (result.data.otherguestCheckinDetails[i].status == null) {
					col += " <td colspan='6'> Not checked in...</td> ";
				} else {
					"  <td> " + result.data.otherguestCheckinDetails[i].arrivalTimeDate + " </td> " +
						"	<td> " + advanceamount + " </td> " +
						/* "	<td> " + result.data.otherguestCheckinDetails[i].adults + " </td> " +
						"<td>" + result.data.otherguestCheckinDetails[i].noOfChildrenBelow12 + " </td> " + */
						"<td>" + result.data.otherguestCheckinDetails[i].roomDetails.blockName + ' - ' + result.data.otherguestCheckinDetails[i].roomDetails.roomNo + "</td> ";
				}
				$("#checkOutOtherGstBody").append("<tr>" +
					"<td>" + result.data.otherguestCheckinDetails[i].reqId + "</td>" +
					"<td>" + result.data.otherguestCheckinDetails[i].guestId + "</td>" +
					"<td>" + gstDetails + "</td>" +
					col
					+ "</tr>");
			}

			for (let j = 0; j < result.data.guestDetails.length; j++) {
				var dyn = j + 1;
				var advanceamount1 = '';
				if (result.data.guestDetails[j].advncAmount == '') {
					advanceamount1 = 0
				} else {
					advanceamount1 = result.data.guestDetails[j].advncAmount;
				}
				/*  debugger;*/
				var gstDetails = "";
				if (result.data.guestDetails[j].name != "") {
					gstDetails = gstDetails + result.data.guestDetails[j].name;
				}
				if (result.data.guestDetails[j].designation != "") {
					gstDetails = gstDetails + "," + result.data.guestDetails[j].designation;
				}
				if (result.data.guestDetails[j].department != "") {
					gstDetails = gstDetails + "," + result.data.guestDetails[j].department;
				}
				$("#checkOutGstBody").append("<tr>" +
					"<td>" + result.data.guestDetails[j].reqId + "</td>" +
					"<td>" + result.data.guestDetails[j].guestId + "</td>" +
					"<td>" + gstDetails + "</td>" +
					"<td style='display:none;'>" + result.data.guestDetails[j].trId + "</td>" +
					"<td>" + result.data.guestDetails[j].rollNo + "</td>" +
					"<td>" + result.data.guestDetails[j].arrivalTime + "</td>" +
					"<td>" + advanceamount1 + "</td>" +
					"<td>" + result.data.guestDetails[j].roomDetails.blockName + "-" + result.data.guestDetails[j].roomDetails.roomNo + "</td>" +
					"<td>" + "<input type='text' id =checkOutDt" + dyn + " placeholder='DD-MM-YYYY' name=checkOutDt class='checkOutDt' style='text-align:center;' autocomplete='off'>" + "</td >" +
					"<td  style='display:none;'>" + result.data.guestDetails[j].parentTrId + "</td>" +
					+"</tr>");
			}

		},
		error: function(jqXHR, status, error) {
			swal(error);
		}
	})

}
var viewCancelListDetails = function(val) {
	if (selectCheckOutGstId == '') {
		swal("Please select atleast one guest");
		$("#checkOutModal").modal('hide');
		return false;
	}

	var req_id = val;
	var city = $('#city').val();
	var gHouse = $('#gHouse').val();
	var hidval = selectCheckOutGstId;
	var postData = {
		ghId: city,
		blockId: gHouse,
		reqId: req_id,
		hidval: hidval
	}
	console.log(postData);
	$.ajax({
		url: './admin/viewCheckInCancelDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			$('#cancelCheckInModal').modal('show');
			console.log(result)
			$("#currentCancelCheckin").html("");
			for (let j = 0; j < result.data.length; j++) {
				var dyn = j + 1;
				var advanceamount1 = '';
				if (result.data[j].advncAmount == '') {
					advanceamount1 = 0
				} else {
					advanceamount1 = result.data[j].advncAmount;
				}
				var gstDetails = "";
				if (result.data[j].name != '') {
					gstDetails = gstDetails + result.data[j].name;
				}
				if (result.data[j].designation != '') {
					gstDetails = gstDetails + "," + result.data[j].designation;
				}
				if (result.data[j].department != '') {
					gstDetails = gstDetails + "," + result.data[j].department;
				}
				$("#currentCancelCheckin").append("<tr>" +
					"<td>" + result.data[j].reqId + "</td>" +
					"<td>" + result.data[j].guestId + "</td>" +
					"<td>" + gstDetails + "</td>" +
					"<td style='display:none;'>" + result.data[j].trId + "</td>" +
					"<td>" + result.data[j].rollNo + "</td>" +
					"<td>" + result.data[j].arrivalTime + "</td>" +
					"<td style='display:none;'>" + result.data[j].ext + "</td>" +
					"<td style='display:none;'>" + result.data[j].parentTrId + "</td>" +
					"<td>" + result.data[j].advncAmount + "</td>" +
					"<td>" + result.data[j].desgCategory + "</td>" +
					"<td>" + result.data[j].purposes + "</td>" +
					"<td>" + result.data[j].roomDetails.blockName + "-" + result.data[j].roomDetails.roomNo + "</td>"+
					(j === 0 ? "<td rowspan='" +result.data.length + "'>" + result.data[j].extraGuest + "</td>" : "") 
					
					+ "</tr>");
			}
		},
		error: function(jqXHR, status, error) {
			swal(error);
		}
	})

}
var viewEditListDetails = function(val, fromDate, toDate) {
	if (selectCheckOutGstId == '') {
		swal("Please select atleast one guest");
		$("#checkOutModal").modal('hide');
		return false;
	}

	var req_id = val;
	var city = $('#city').val();
	var gHouse = $('#gHouse').val();
	var hidval = selectCheckOutGstId;
	var postData = {
		ghId: city,
		blockId: gHouse,
		reqId: req_id,
		hidval: hidval,
		fromDate: fromDate,
		toDate: toDate
	}
	console.log(postData);
	$.ajax({
		url: './admin/viewCheckInCancelDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			editFromDt = result.fromDate;
			editToDt = result.toDate;
			$('#editCheckInModal').modal('show');
			console.log(result)
			$("#currentEditCheckin").html("");
			for (let j = 0; j < result.data.length; j++) {
				var dyn = j + 1;
				var advanceamount1 = '';
				if (result.data[j].advncAmount == '' || result.data[j].advncAmount == null) {
					advanceamount1 = "<input type='text' autocomplete='off' onkeypress='return onlyNumberKey(event)' name='advncAmount' style='text-align:center;' placeholder='Enter Amount' >";
				} else {
					advanceamount1 = "<input type='text' autocomplete='off'  onkeypress='return onlyNumberKey(event)'  name='advncAmount' style='text-align:center;' value='" + result.data[j].advncAmount + "'>";
				}
				var gstDetails = "";
				if (result.data[j].name != '') {
					gstDetails = gstDetails + result.data[j].name;
				}
				if (result.data[j].designation != '') {
					gstDetails = gstDetails + "," + result.data[j].designation;
				}
				if (result.data[j].department != '') {
					gstDetails = gstDetails + "," + result.data[j].department;
				}

				$("#currentEditCheckin").append("<tr style='text-align:center;'>" +
					"<td>" + result.data[j].reqId + "</td>" +
					"<td>" + result.data[j].guestId + "</td>" +
					"<td>" + gstDetails + "</td>" +
					"<td style='display:none;'>" + result.data[j].trId + "</td>" +
					"<td><input type='text' autocomplete='off' name='roll'  style='text-align:center;' value='" + result.data[j].rollNo + "'></td>" +
					"<td> <input type='text' name='checkinDt' class='checkInEditDt' style='text-align:center;' value='" + result.data[j].arrivalTime + "'></td>" +
					"<td style='display:none;'>" + result.data[j].trId + "</td>" +
					(j === 0 ? "<td rowspan='" + result.data.length + "' id='totalAmountAdvnc'>" + result.data[j].totalAmount + "</td>" : "") +
					"<td>" + advanceamount1 + "</td>" +
					"<td>" + result.data[j].desgCategory + "</td>" +
					"<td>" + result.data[j].purposes + "</td>" +
					"<td>" + result.data[j].roomDetails.blockName + "-" + result.data[j].roomDetails.roomNo + "</td>"+
					(j === 0 ? "<td rowspan='" +result.data.length + "'><input type='text' name='extraGuest' id='editExtraGst' value='" + result.data[j].extraGuest + "' style='text-align:center;'></td>" : "") 
					+ "</tr>");
			}
		},
		error: function(jqXHR, status, error) {
			swal(error);
		}
	})

}

function viewCancelListDetail(reqId, ext) {
	if (ext == '1') {
		for (var i = 0; i < totalCheckOutGuest.length; i++) {
			if (reqId == totalCheckOutGuest[i].req_id) {
				data = [];
				for (var j = 0; j < totalCheckOutGuest[i].guestDetails.length; j++) {
					if (totalCheckOutGuest[i].guestDetails[j].status == "CIN") {
						data.push("1");
					}
				}
				const splitted = selectCheckOutGstId.split("','");
				if (data.length == splitted.length) {
					viewCancelListDetails(reqId);
				} else {
					swal("Please select all guest");
				}
			}
		}
	} else {
		viewCancelListDetails(reqId);
	}

}

function viewCheckOutDateValidate(reqId, frmData, toData) {
	data = [];
	const splitted = selectCheckOutGstId.split("','");
	for (var i = 0; i < totalCheckOutGuest.length; i++) {
		if (totalCheckOutGuest[i].req_id == reqId) {
			for (var j = 0; j < totalCheckOutGuest[i].guestDetails.length; j++) {
				if (totalCheckOutGuest[i].guestDetails[j].status == 'CIN') {
					data.push("1");
				}
			}
		}
	}
	if (data.length == splitted.length) {
		viewCheckOutData(reqId, frmData, toData);
	} else {
		swal("Please select all guest");
	}
}
function viewCheckSlip(trId1) {
	$("#CheckInSlipModal").modal("show");
	var city = $('#city').val();
	var gHouse = $('#gHouse').val();
	var postData = {
		cId: city,
		gId: gHouse,
		trId:trId1.toString()
	}
	$.ajax({
		url: './admin/viewIndividualCheckinSlipDetail.do',
		type: 'POST',
		data: JSON.stringify(postData),
		contentType: 'application/json',
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		}, success: function(result) {
			trId=result.data.trId;
			$("#bookingId").html("");
			$("#gstDetails").html("");
			$("#roll").html("");
			$("#arrTime").html("");
			$("#depTime").html("");
			$("#advncAmt").html("");
			$("#adult").html("");
			$("#room").html("");
			$("#gstName").html("");
			$("#ruppese").html("");
			$("#guestPaymentTime").html("");
			$("#currentDate").html("");
			$("#children").html("");
			$('#loading').hide();
			console.log(result);
			var gstDetails=result.data.name;
			if(result.data.department!=""&&result.data.department!=null){
			gstDetails=gstDetails+","+result.data.department;
			}
			if(result.data.designation!=""&&result.data.designation!=null){
			gstDetails=gstDetails+","+result.data.designation;
			}
			$("#bookingId").html(result.data.reqId);
			$("#gstDetails").html(gstDetails);
			$("#roll").html(result.data.registerNo);
			$("#arrTime").html(result.data.arrivalTime);
			$("#depTime").html(result.data.departureTime);
			$("#advncAmt").html(result.data.advanceAmount);
			$("#adult").html(result.data.noOfAdults);
			$("#room").html(result.data.room);
			$("#children").html(result.data.nochildrenBelow);
			
		
			$("#gstName").html(gstDetails);
			$("#ruppese").html(result.data.advanceAmount);
			$("#guestPaymentTime").html(result.data.arrivalTime);
			$("#currentDate").html(moment().format('DD-MM-YYYY'));
		},
		error: function(jqXHR, status, error) {
			swal(error);
		}

	});
}
function getCheckinSlipDetails(pageNo) {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		let bookingId = $('#filterBookingId').val();
		postdata = {
			cId: city,
			gId: gHouse,
			pageNo:pageNo,
			bookingId:bookingId
		}
		$.ajax({
			url: './admin/viewCheckinSlipDetails.do',
			type: 'POST',
			data: JSON.stringify(postdata),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(result) {
			
				$('#loading').hide();
				console.log(result);
				$("#checkInslipListbody").html("");
				for(var i=0;i<result.Data.data.length;i++){
				var dep="";
					if(result.Data.data[i].checkInSlip[0].departureDate==null){
						dep="";
					}else{
						dep=result.Data.data[i].checkInSlip[0].departureDate;
					}
					$("#checkInslipListbody").append(
					"<tr>"+
						"<td rowspan='"+result.Data.data[i].checkInSlip.length+"'>"+result.Data.data[i].reqId+"</td>"+
						"<td>"+result.Data.data[i].checkInSlip[0].name+"</td>"+
						"<td>"+result.Data.data[i].checkInSlip[0].arrivalDate+"</td>"+
						"<td>"+dep+"</td>"+
						"<td>"+"<button class='btn btn-sm print' action='check In slip' onclick=viewCheckSlip(" + result.Data.data[i].checkInSlip[0].trId + ")><i class='fa fa-print' aria-hidden='true'></i></button></td>"+
						"</tr>");		
						
					for(var j=1;j<result.Data.data[i].checkInSlip.length;j++){
					if(result.Data.data[i].checkInSlip[j].departureDate==null){
						dep="";
					}else{
						dep=result.Data.data[i].checkInSlip[j].departureDate;
					}
					$("#checkInslipListbody").append(
					"<tr>"+
						"<td>"+result.Data.data[i].checkInSlip[j].name+"</td>"+
						"<td>"+result.Data.data[i].checkInSlip[j].arrivalDate+"</td>"+
						"<td>"+dep+"</td>"+
						"<td>"+"<button class='btn btn-sm print' action='check In slip' onclick=viewCheckSlip(" + result.Data.data[i].checkInSlip[j].trId + ")><i class='fa fa-print' aria-hidden='true'></i></button></td>"+
						"</tr>");					
					}
					
					
				let pageNo = $("#slipPageNo").html();
				if (pageNo >= result.Data.totalPages) {
					$("#slipPageNext").attr("disabled", true);
				} else {
					$("#slipPageNext").attr("disabled", false);
				}

				if (pageNo == 1) {
					$("#slipPagePrev").attr("disabled", true);
				} else {
					$("#slipPagePrev").attr("disabled", false);
				}
								
				}
				
				
					
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
		});
}
function viewAllotData(reqId){
	var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		postdata = {
			cId: city,
			gId: gHouse,
			reqId:reqId
		}
		$.ajax({
			url: './admin/viewAllotmentRoomByReqId.do',
			type: 'POST',
			data: JSON.stringify(postdata),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},success: function(result) {
			  $('#loading').hide();
			  $('#changeAllotRoom').modal("show");
			  console.log(result);
			  $("#allotRoomTableBody").html("");
			  var allotRoomIn = result.data[0].allotRoom.split(",");
			  $("#allotHeader").html("");
			  $("#allotHeader").append("<th>Date</th><th colspan='" + allotRoomIn.length + "'>Rooms</th>");
			  
			  for (var i = 0; i < result.data.length; i++) {
			    var allotRoomInArray = result.data[i].allotRoom.split(",");
			    var allotIdInArray = result.data[i].allotId.split(",");
			    console.log(allotRoomInArray);
			    console.log(allotIdInArray);
			    if(allotRoomInArray.length>=20){
			    	$("#allotRoomTable").css("width","300%");
			    }else if(allotRoomInArray.length>=10){
			    	$("#allotRoomTable").css("width","200%");
			    }else{
			    	$("#allotRoomTable").css("width","100%");
			    }
			    var tableRow = "<tr>" +
			      "<td>" + result.data[i].fromDate + " to " + result.data[i].toDate + "</td>";
			    
			    for (var k = 0; k < allotRoomInArray.length; k++) {
			      tableRow += "<td nowrap> <b>" +
			        allotRoomInArray[k] + "</b> : ";
			        
			      if (k === 0) {
			        tableRow += "<input type='checkbox' name='allotId' class='allotView' value='" + allotIdInArray[k] + "' disabled>";
			      } else {
			        tableRow += "<input type='checkbox' name='allotId' class='allotView' value='" + allotIdInArray[k] + "'>";
			      }
			      
			      tableRow += "</td>";
			    }
			    
			    tableRow += "</tr>";
			    $("#allotRoomTableBody").append(tableRow);
			  }
			},

			error:function(jqXHR, status, error){
				swal(error);
			}
		});
}
function onlyNumberKey(evt) {
    // Only ASCII character in that range allowed
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
}