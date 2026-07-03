$('document').ready(function() {

	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		$($.fn.dataTable.tables(true)).DataTable().columns.adjust()
	});

	var cityData = $('#cityUserlocssd').val().trim();
	var cityDetails = JSON.parse(cityData);

	$("#city").html("");
	$("#city").append('<option value="">--Select City--</option>');
	cityDetails.forEach(option => {
		$("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
	});

	let size = Object.keys(cityDetails).length;
	if (size == 1) {
		$('#city').val(cityDetails[0].gh_id);
		setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}

	var appData;
	var blockId = 0;

	var guestCount = 0;
	var guestDelIds = [];
	var guestParameters = 7;
	var bookingReqId = null;
	var guestList;
	var suppDoc = null;
	var catgDoc = null;
	var gDoc = null;
	var typeOfRoom = null;

	var convertingGuesetDetailsObjectToArrayOfObjects = function(data) {
		console.log(data);
		var array = [];
		var count = data.length / guestParameters;
		console.log(data.length);
		for (var i = 0; i < count; i++) {
			array.push(convertSerializeArrayToObject(data.slice((i * guestParameters), ((i + 1) * guestParameters))));
		}
		return array;
	}
	$("#filterBookingDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
	$("#filterBookingDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
	$("#filterFromDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
	$("#modifyDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
	$("#editModList").hide();
	$('#modifyList').hide();
	$('#confList').hide();
	$("#cancelReservationList").hide();
	$('#printBtnForModList').hide();

	var table = $("#allotmentTableDtls").DataTable({
		"paging": true,
		"searching": false,
		"scrollX": true,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"autoWidth": true,
		"bLengthChange": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"serverSide": true,
		"ajax": {
			"url": "./admin/getAllotmentLstDtlsAccToReq.do",
			"type": "POST",
			"beforeSend": function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			"complete": function() {
				$('#loading').hide();
			},
			"data": function(d) {
				d.city = $('#city').val();
				d.gHouse = $('#gHouse').val();
				d.bookingId = $('#filterBookingId').val();
				d.bookingDate = $('#filterBookingDate').val();
				d.frmDate = $('#filterFromDate').val();
				d.purpose = $('#filterPurpose').val();
				d.mobNo = $('#filterMobNum').val();
				d.name = $('#filterGuestName').val();
				d.roomType = $('#filterRoomType').val();
				d.category = $('#filterCategory').val();
				d.divObj = $('#headerName').html();
				d.type = (d.divObj === 'Moderation List') ? 'MA' : 'MM';
			},
			"error": function (jqXHR, textStatus, errorThrown) {
	            // Hide the loading indicator
	            $('#loading').hide();
	            if (jqXHR.status === 400) {
	                // If status 400 (Bad Request), show custom Swal error
	                swal("Invalid Request");
	            } 
	        }
		},
		"columnDefs": [
			{
				"targets": 0,
				"data": null,
				"width": "5%",
				"className": "text-center text-nowrap",
				render: function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{ "width": "5%", "targets": 1, "data": "bookingId", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 2, "data": "name", "className": "text-left text-nowrap" },
			{ "width": "10%", "targets": 3, "data": "fromDate", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 4, "data": "toDate", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 5, "data": "catgId", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 6, "data": "purpose", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 7, "data": "mobile", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 8, "data": "roomOrBed", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 9, "data": "typeOfRoom", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 10, "data": "bookingDt", "className": "text-center text-nowrap" },
			{
				"width": "10%",
				"targets": 11,
				"data": null,
				"className": "text-center text-nowrap",
				render: function(data, type, row, meta) {
					return '<button type="button" id="saveMod" title="Save Moderator Details" action="saveMod" class="btn btn-sm btn-edit btn-save-mod mr-1"><i class="fa fa-save" aria-hidden="true"></i> </button>' +
						'<button type="button" id="editGust" title="Edit Guest Details" action="editGust" class="btn btn-sm btn-view mr-1"><i class="fa fa-edit" aria-hidden="true"></i></button>' +
						'<button type="button" id="cancelDtls" title="Cancel Guest Details" action="cnclGust" class="btn-dlt btn-sm"><i class="fa fa-times" aria-hidden="true"></i></button>';
				}
			}
		]
	});


	var table1 = $("#moderatorTableDtls").DataTable({
    "paging": true,
	"searching": false,
	"scrollX": true,
	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
	"processing": false,
	"ordering": true,
	"info": false,
	"bFilter": false,
	"autoWidth": true,
	"bLengthChange": false,
	"fixedHeader": {
		header: true,
		footer: true
	},
	"scrollCollapse": true,
	"serverSide": true,
	"ajax": {
			"url": "./admin/getAllotmentLstDtlsAccToReq.do",
			"type": "POST",
			"beforeSend": function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			"complete": function() {
				$('#loading').hide();
			},
			"data": function(d) {
				d.city = $('#city').val();
				d.gHouse = $('#gHouse').val();
				d.bookingId = $('#filterBookingId').val();
				d.bookingDate = $('#filterBookingDate').val();
				d.purpose = $('#filterPurpose').val();
				d.mobNo = $('#filterMobNum').val();
				d.name = $('#filterGuestName').val();
				d.roomType = $('#filterRoomType').val();
				d.divObj = $('#headerName').html();
				d.type = (d.divObj === 'Moderation List') ? 'MA' : 'MM';
			},
			"error": function (jqXHR, textStatus, errorThrown) {
				            // Hide the loading indicator
				            $('#loading').hide();
				            if (jqXHR.status === 400) {
				                // If status 400 (Bad Request), show custom Swal error
				                swal("Invalid Request");
				            } 
				        },
			"dataSrc": function(result) {
            // Toggle print button based on the result length
            if ($('#headerName').html() === 'Edit Moderation List') {
                $('#printBtnForModList').toggle(result.data.length > 0);
            }
            return result.data;
        }
		},
    	"columnDefs": [
        {
            "className": "text-center text-nowrap",
            "targets": [0, 1, 3, 4, 5, 6, 7, 8, 9]
        },
        {
            "className": "text-left text-nowrap",
            "targets": [2]
        },
        {
            "width": "5%", "targets": 0, "data": null,
            render: function(data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },
        { "width": "5%", "targets": 1, "data": "bookingId" },
        { "width": "10%", "targets": 2, "data": "name" },
        { "width": "10%", "targets": 3, "data": "fromDate" },
        { "width": "10%", "targets": 4, "data": "toDate" },
        { "width": "10%", "targets": 5, "data": "purpose" },
        { "width": "10%", "targets": 6, "data": "mobile" },
        { "width": "10%", "targets": 7, "data": "email" },
        { "width": "10%", "targets": 8, "data": "typeOfRoom" },
        { "width": "10%", "targets": 9, "data": "bookingDt" },
        {
            "width": "10%", "targets": 10, "data": null,
            render: function(data, type, row, meta) {
                return '<button type="button" id="modifyMod" title="Modify Moderator Details" action="modifyMod" class="btn btn-sm btn-view btn-save-mod mr-1"><i class="fa fa-edit" aria-hidden="true"></i></button> '
                    + '<button type="button" id="delete" title="Delete Guest Details" action="delete" class="btn btn-dlt btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>';
            }
        }
    ]
});


	var canTable = $("#cancelReservationTable").DataTable({
		"paging": true,
		"searching": false,
		"scrollX": false,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": true,
		"bLengthChange": true,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs": [
			{
				"className": "text-center text-nowrap",
				"targets": "_all"
			},
			{
				"width": "5%", "targets": 0, "data": null,
				render: function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{ "width": "5%", "targets": 1, "data": "bookingId" },
			{ "width": "5%", "targets": 2, "data": "bookingDt" },
			{ "width": "10%", "targets": 3, "data": "name" },
			{ "width": "10%", "targets": 4, "data": "fromDate" },
			{ "width": "10%", "targets": 5, "data": "toDate" },
			{ "width": "10%", "targets": 6, "data": "desg" },
			{ "width": "10%", "targets": 7, "data": "mobile" },
			{ "width": "10%", "targets": 8, "data": "email" },
			{ "width": "10%", "targets": 9, "data": "statusDesc" },
			{
				"width": "10%", "targets": 10, "data": null,
				render: function(data, type, row, meta) {
					if (data.status == 'RCAN') {
						return '<button type="button" id="cancel" title="Cancel" class="btn btn-sm btn-cancel" action="cancel" ><i class="fa fa-times" aria-hidden="true"></i></button> '
					} else {
						return '<button type="button" id="partialCancel" title="Partial Cancel" class="btn btn-sm btn-cancel" action="partialCancel" ><i class="fa fa-times" aria-hidden="true"></i></button> '
					}
				}
			}
		],
	});

	var confTable = $("#confListDataTable").DataTable({
		"paging": true,
		"searching": false,
		"scrollX": true,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": true,
		"autoWidth": true,
		"bLengthChange": true,
		"fixedHeader": {
			header: true,
			footer: true
		},

		"scrollCollapse": true,
		"columnDefs": [
			{
				"className": "text-center text-nowrap",
				"targets": [0, 1, 3, 4, 5, 6, 7, 8, 9]
			},
			{
				"className": "text-left text-nowrap",
				"targets": [2]
			},
			{
				"width": "5%", "targets": 0, "data": null,
				render: function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{ "width": "5%", "targets": 1, "data": "bookingId" },
			{ "width": "10%", "targets": 2, "data": "name" },
			{ "width": "10%", "targets": 3, "data": "fromDate" },
			{ "width": "10%", "targets": 4, "data": "toDate" },
			{ "width": "10%", "targets": 5, "data": "category" },
			{ "width": "10%", "targets": 6, "data": "mobile" },
			{ "width": "10%", "targets": 7, "data": "typeOfRoom" },
			{ "width": "10%", "targets": 8, "data": "bookingDt" },
			{
				"width": "10%", "targets": 9, "data": null,
				render: function(data, type, row, meta) {
					return '<button type="button" id="saveMod" title="Save Moderator Details" action="saveMod" class="btn btn-sm btn-edit btn-save-mod"><i class="fa fa-save" aria-hidden="true"></i> </button> ' +
						'<button type="button" id="billView" title="Bill View" name="billView" action="Select" class="btn btn-sm btn-secondary d-inline payment"><i class="fa fa-credit-card" aria-hidden="true"></i></button>' +
						'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
				}
			}
		],
	});

	$('#confSaveDtls').on('click', function() {
		var postData = {
			bookingId: appData.bookingId,
			catg: appData.catgId,
		}
		$.ajax({
			url: './admin/confModeration.do',
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				swal("Moderator Details Saved Successfully");
				$('#confModModal').modal('hide');
				getConferenceList();
			},
			error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	});

	$('#confCancelDtls').on('click', function() {
		$('#cancelConfBooking').modal('show');
	});

	$('#rejectConfBooking').on('click', function() {
		let reqId = $('#reqId1').val();
		let ghId = $('#ghId').val();
		let blockId = $('#blockId').val();
		let remarksReason = $('#convRemarksReason').val();
		let modReason = $('input[name="reasonModConfCancel"]:checked').val() ? $('input[name="reasonModConfCancel"]:checked').val() : null;
		if (modReason == null) {
			swal("Select atleast one value");
			return false;
		}
		let postData = {
			bookingId: reqId,
			cityId: ghId,
			ghId: blockId,
			remarks: remarksReason,
			modReason: modReason
		}
		$.ajax({
			url: "./admin/saveCancelModeration.do",
			type: "POST",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType: "application/json",
			dataType: "JSON",
			success: function(result) {
				if (result.success) {
					swal(result.msg);
					$('#cancelConfBooking').modal('hide');
					$('#confModModal').modal('hide');
					getConferenceList();
				} else {
					swal(result.msg);
				}
			}
		});
	});

	var getDataToCancelReservationTable = function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var postData = {
			cId: city,
			gId: gHouse,
			type: "MOD"
		}
		$.ajax({
			url: './admin/getCancellationModLstDtls.do',
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result);
				canTable.clear();
				canTable.rows.add(result.data).draw();
			},
			error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
		});
	}



	$('#allotmentTableDtls tbody').on('click', '#saveMod', function() {
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		typeOfRoom = data.typeOfRoom;
		fromDate = data.fromDate;
		let current = new Date();
		let cDate = current.getDate() + '-' + (current.getMonth() + 1) + '-' + current.getFullYear();
		let cTime = current.getHours() + ":" + current.getMinutes();
		let dateTime = cDate;
		console.log(convertStringToDatetime1(fromDate));
		console.log(convertStringToDatetime1(dateTime));
		if (convertStringToDatetime1(fromDate) < convertStringToDatetime1(dateTime)) {
			swal("Please Change the From date");
			return false;
		}
		if (typeOfRoom == 'Conveyance') {
			$('#convAddOrModifyModModal').modal('show');
			$('#convSaveDtls').show();
			$('#convModifyDtls').hide();
			getAndSetGuestConveyanceDetails(data.bookingId);
		} else {
			$('#addOrModifyModModal').modal('show');
			$('#saveDtls').show();
			$('#modifyDtls').hide();
			getAndSetGuestRoomDetails(data.bookingId, 'ADD');
		}
	});

	$('#moderatorTableDtls tbody').on('click', '#modifyMod', function() {
		var data = table1.row($(this).closest('tr')).data() != undefined ? table1.row($(this).closest('tr')).data() : table1.row($(this)).data();
		typeOfRoom = data.typeOfRoom;
		if (typeOfRoom == 'Conveyance') {
			$('#convAddOrModifyModModal').modal('show');
			$('#convSaveDtls').hide();
			$('#convModifyDtls').show();
			getAndSetGuestConveyanceDetails(data.bookingId);
		} else {
			$('#addOrModifyModModal').modal('show');
			$('#saveDtls').hide();
			$('#modifyDtls').show();
			getAndSetGuestRoomDetails(data.bookingId, 'UPDATE');
		}
	});

	$('#allotmentTableDtls tbody').on('click', '#editGust', function() {
		$('#modifyGuestDtlsModal').modal('show');
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		blockId = data.ghId;
		console.log(data);
		setGuestDetails(data.bookingId, false);
		$('#arrivalRow').addClass('disableElement');
		$('#departureRow').addClass('disableElement');
	});

	$('#allotmentTableDtls tbody').on('click', '#cancelDtls', function() {

		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		blockId = data.ghId;
		ghId = data.cityId;
		var bookingId = data.bookingId;
		$('#reqId1,#reqId2').val(data.bookingId);
		$('#ghId,#ghId1').val(data.ghId);
		$('#blockId,#blockId1').val(data.cityId);
		console.log("data " + data.typeOfRoom);
		if (data.typeOfRoom == "Conveyance") {
			$('#cancelConvBooking').modal('show');
		} else {
			$('#cancelGuestBooking').modal('show');
		}
		// console.log(data);

	});

	$('#moderatorTableDtls tbody').on('click', '#delete', function() {
		var data = table1.row($(this).closest('tr')).data() != undefined ? table1.row($(this).closest('tr')).data() : table1.row($(this)).data();
		swal({
			title: "Are you sure? ",
			text: "Are you sure to delete the moderation request!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, delete it!",
			closeOnConfirm: false
		},
			function() {
				$('#loading').show();
				$.ajax({
					url: './admin/cancelModerationRequest/' + data.bookingId + '.do',
					type: "POST",
					headers: { "X-CSRF-TOKEN": token },
					success: function(data) {
						$('#loading').hide();
						if (data.success) {
							swal(data.msg);
							table.ajax.reload();
							table1.ajax.reload();
							/*getModeratorTableData();*/
						} else {
							swal(data.msg);
						}
					}
				});
			});
	});

	$('#cancelReservationTable').on('click', 'tbody #cancel', function() {
		var data = canTable.row($(this).closest('tr')).data() != undefined ? canTable.row($(this).closest('tr')).data() : canTable.row($(this)).data();
		swal({
			title: "Are you sure? ",
			text: "Are you sure to cancel!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, cancel it!",
			closeOnConfirm: false
		},
			function() {
				$('#loading').show();
				$.ajax({
					url: './admin/cancelModRequest/' + data.bookingId + '.do',
					type: "GET",
					headers: { "X-CSRF-TOKEN": token },
					success: function(result) {
						$('#loading').hide();
						if (result.success) {
							swal(result.msg);
							getDataToCancelReservationTable();
							table.ajax.reload();
						} else {
							swal(result.msg);
						}
					}
				});
			});
	});

	$('#cancelReservationTable').on('click', 'tbody #partialCancel', function() {
		var data = canTable.row($(this).closest('tr')).data() != undefined ? canTable.row($(this).closest('tr')).data() : canTable.row($(this)).data();
		swal({
			title: "Are you sure? ",
			text: "Are you sure to partial cancel!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, cancel it!",
			closeOnConfirm: false
		},
			function() {
				$('#loading').show();
				$.ajax({
					url: './admin/partialCancelById/' + data.bookingId + '/MOD.do',
					type: "GET",
					headers: { "X-CSRF-TOKEN": token },
					success: function(result) {
						$('#loading').hide();
						if (result.success) {
							swal(result.msg);
							getDataToCancelReservationTable();
							table.ajax.reload();
						} else {
							swal(result.msg);
						}
					}
				});
			});
	});

	let isCityAndGHSelected = function() {
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
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

	$('#confView').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		$('#headerName').html("Conference List");
		$('#editModBtn').removeClass('custom-btn-active');
		$('#ModBtn').removeClass('custom-btn-active');
		$('#cancelReservation').removeClass('custom-btn-active');
		$('#modify').removeClass('custom-btn-active');
		$('#confView').addClass('custom-btn-active');
		$("#modList").hide();
		$("#editModList").hide();
		$("#moderatorTableDtls").hide();
		$("#allotmentTableDtls").hide();
		$('#modifyList').hide();
		$('#confList').show();
		$('#printBtnForModList').hide();
		getConferenceList();
	});
	$('#modify').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		$('#headerName').html("Modify Allotment");
		$('#editModBtn').removeClass('custom-btn-active');
		$('#ModBtn').removeClass('custom-btn-active');
		$('#cancelReservation').removeClass('custom-btn-active');
		$('#confView').removeClass('custom-btn-active');
		$('#modify').addClass('custom-btn-active');
		$("#modList").hide();
		$("#editModList").hide();
		$("#moderatorTableDtls").hide();
		$("#allotmentTableDtls").hide();
		$('#modifyList').show();
		$('#confList').hide();
		$('#printBtnForModList').hide();
		getAllotmentList(1);
	});
	$('#cancelReservation').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		$("#cancelReservationModal").modal('show');
		getDataToCancelReservationTable();
	});
	$('#editModBtn').on('click', function() {
		document.getElementById("headerName").innerHTML = "Edit Moderation List";
		$('#editModBtn').addClass('custom-btn-active');
		$('#ModBtn').removeClass('custom-btn-active');
		$('#cancelReservation').removeClass('custom-btn-active');
		$('#confView').removeClass('custom-btn-active');
		$('#modify').removeClass('custom-btn-active');
		$("#modList").hide();
		$("#editModList").show();
		$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
		$("#moderatorTableDtls").show();
		$('#modifyList').hide();
		$('#confList').hide();
		/*getModeratorTableData();*/
		table1.ajax.reload();
		/*table.ajax.reload();*/
	});
	$('#ModBtn').on('click', function() {
		document.getElementById("headerName").innerHTML = "Moderation List";
		$('#editModBtn').removeClass('custom-btn-active');
		$('#ModBtn').addClass('custom-btn-active');
		$('#cancelReservation').removeClass('custom-btn-active');
		$('#confView').removeClass('custom-btn-active');
		$('#modify').removeClass('custom-btn-active');
		$("#modList").show();
		$("#editModList").hide();
		$($.fn.dataTable.tables(true)).DataTable()
			.columns.adjust();
		$("#allotmentTableDtls").show();
		$('#modifyList').hide();
		$('#confList').hide();
		$('#printBtnForModList').hide();
		table.ajax.reload();
	});

	/* $('#cancelDtls').on('click', function () {        
		 $('#cancelGuestBooking').modal('show');           
	 });*/

	$('#cancelGuestBooking, #cancelConvBooking').on('hidden.bs.modal', function(e) {
		$(this)
			.find("input[type=text], textarea, select")
			.val('')
			.end()
			.find(":radio[name='reasonModCancel'],:radio[name='reasonModConvCancel']")
			.prop("checked", false)
			.end()
			.find("input[type=checkbox]")
			.prop("checked", false);
	});



	/* $('#convCancelDtls').on('click', function () {
		 $('#cancelConvBooking').modal('show');
	 });
		 */
	$('#rejectConvBooking').on('click', function() {
		var reqId = $('#reqId2').val();
		var ghId = $('#ghId1').val();
		var blockId = $('#blockId1').val();
		var remarksReason = $('#convRemarksReason').val();
		var modReason = $('input[name="reasonModConvCancel"]:checked').val() ? $('input[name="reasonModConvCancel"]:checked').val() : null;
		console.log("value " + reqId + "" + ghId + " " + blockId);
		if (modReason == null) {
			swal("Select atleast one value");
			return false;
		}

		let postData = {
			bookingId: reqId,
			cityId: ghId,
			ghId: blockId,
			remarks: remarksReason,
			modReason: modReason
		}
		$.ajax({
			url: "./admin/saveCancelModeration.do",
			type: "POST",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType: "application/json",
			dataType: "JSON",
			success: function(result) {
				if (result.success) {
					swal(result.msg);
					$('#cancelConvBooking').modal('hide');
					$('#convAddOrModifyModModal').modal('hide');
					table.ajax.reload();
				} else {
					swal(result.msg);
				}
			}
		});
	});

	$('#rejectBooking').on('click', function() {
		let reqId = $('#reqId1').val();
		let ghId = $('#ghId').val();
		let blockId = $('#blockId').val();
		let remarksReason = $('#remarksReason').val();
		console.log(reqId + "value" + ghId + " " + blockId);
		var modReason = $('input[name="reasonModCancel"]:checked').val() ? $('input[name="reasonModCancel"]:checked').val() : null;
		if (modReason == null) {
			swal("Select atleast one value");
			return false;
		}
		if (modReason == "others" && (remarksReason == "" || remarksReason == null)) {
			swal("Please provide some remark");
			return false;
		}
		let postData = {
			bookingId: reqId,
			cityId: ghId,
			ghId: blockId,
			remarks: remarksReason,
			modReason: modReason
		}
		$.ajax({
			url: "./admin/saveCancelModeration.do",
			type: "POST",
			data: JSON.stringify(postData),
			contentType: "application/json",
			dataType: "JSON",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				if (result.success) {
					swal(result.msg);
					$('#cancelGuestBooking').modal('hide');
					$('#addOrModifyModModal').modal('hide');
					table.ajax.reload();
				} else {
					swal(result.msg);
				}
			}
		});
	});


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

	var getDesignationListDtls = function(id, optVal, isLoaderReq) {
		$.ajax({
			url: "./guest/getDesignationDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				if (isLoaderReq) {
					$('#loading').show();
				}
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#desg").html("");
				$("#desg").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (optVal == option.id) {
						$("#desg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
					} else {
						$("#desg").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				});
				if (isLoaderReq) {
					$('#loading').hide();
				}
			}, error: function(jqXHR, status, error) {
				swal(error);
				if (isLoaderReq) {
					$('#loading').hide();
				}
			}
		});
	}

	var getDesignation = function(id, value) {
		$.ajax({
			url: "./guest/getDesignationDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#desg").html("");
				$("#desg").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (option.id == value) {
						$("#desg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
					} else {
						$("#desg").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				})
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	var getCatgoryListDtls = function(optVal, isLoaderReq) {
		$.ajax({
			url: "./guest/getCategoryDetails.do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				if (isLoaderReq) {
					$('#loading').show();
				}
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#modCatg").html("");
				$("#modCatg").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (optVal == option.id) {
						$("#modCatg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
					} else {
						$("#modCatg").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				});
				if (isLoaderReq) {
					$('#loading').hide();
				}
			}, error: function(jqXHR, status, error) {
				swal(error);
				if (isLoaderReq) {
					$('#loading').hide();
				}
			}
		});
	}

	$("#filterBookingDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
	});

	$('#filterCategory').append(
		"<option value='C1'>Category1</option>" +
		"<option value='C2'>Category2</option>" +
		"<option value='C3'>Category3</option>" +
		"<option value='C4'>Category4</option>"
	);

	$('#filterPurpose').append(
		"<option value='1'>Official</option>" +
		"<option value='2'>Private</option>" +
		"<option value='3'>Patient</option>" +
		"<option value='4'>Student</option>"
	);

	$('#filterBookingId').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});

	$('#filterRoomType').append(
		"<option value='R'>Room</option>" +
		"<option value='C'>Conveyance</option>" +
		"<option value='RC'>Room With Conveyance</option>"
	);

	$('#filterSearch').click(function() {
		table.ajax.reload();
		table1.ajax.reload();
		/*getModeratorTableData();*/
		getAllotmentList(1);
		getConferenceList();
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
		$('#filterBookingDate').val('');
		$('#filterFromDate').val('');
		$('#filterPurpose').val('');
		$('#filterMobNum').val('');
		$('#filterRoomType').val('');
		$('#filterGuestName').val('');
		$('#filterCategory').val('');
		table.ajax.reload();
		table1.ajax.reload();
		/*getModeratorTableData();*/
		getAllotmentList(1);
		getConferenceList();
	});


    /*var getModeratorTableData = function () {    	
    	let bookingId = $('#filterBookingId').val();
        let bookingDate = $('#filterBookingDate').val();
        let purpose = $('#filterPurpose').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
        let roomType = $('#filterRoomType').val();        
        let divObj = $('#headerName').html();
        let cityId = $('#city').val();
        let ghId = $('#gHouse').val();
        var req = ''
        if(divObj == 'Moderation List'){
        	req = 'MA'
        }else{
        	req = 'MM'
        }
        
        let postData = {            
            bookingId: bookingId,
            bookingDate: bookingDate,
            purpose:purpose,
            mobNo:mobNo,
            name:name,
            reqType: req,
            roomType:roomType,
            cId: cityId,
            gId: ghId
        };
    	
        $.ajax({
            url: './admin/getAllotmentLstDtlsAccToReq.do',
            type: "POST",
            contentType: "application/json",
            dataType: "JSON",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
                table1.clear();
                table1.rows.add(result.data).draw();
                if ($('#headerName').html() === 'Edit Moderation List') {
                    if (result.data.length > 0) {
                        $('#printBtnForModList').show();
                    } else {
                        $('#printBtnForModList').hide();
                    }
                }
            },
            error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }*/

    /*
	 * var getBhawanListDtls = function(id){ $.ajax({ url:
	 * "./admin/getGuestHouseListDetails/" + id + ".do", type: "GET",
	 * contentType:"application/json", beforeSend: function() {
	 * $('#loading').show(); }, success: function(data, textStatus, jqXHR) {
	 * console.log(data); }, error:function(jqXHR, status, error){ swal(error); }
	 * }); }
	 */

	$('#categoryTabledetails').hide();
	$('#categoryFourTabledetails').hide();
	var getAndSetGuestRoomDetails = function(bookingId, type) {
		$('#loading').show();
		$.ajax({
			url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				console.log(r);
				$('.clearIdValClass').text('');
				$('.clearIdValClass').val('');
				appData = r.data;
				bookingReqId = r.data.bookingId;
				let modeOfTravelFrom = r.data.travelModeFr == 'T' ? 'Train' : r.data.travelModeFr == 'F' ? 'Flight' : '';
				let modeOfTravelTo = r.data.travelModeTo == 'T' ? 'Train' : r.data.travelModeTo == 'F' ? 'Flight' : '';
				let flOrTrainNoFrom = r.data.flNoFr;
				let flOrTrainNoTo = r.data.flNoTo;
				let arrDt = r.data.arrDt;
				let arrTm = r.data.arrTm;
				let arrPla = r.data.arrPla;
				let depDt = r.data.depDt;
				let depPla = r.data.depPla;
				let depTm = r.data.depTm;
				var suppdocname = null;
				if (r.data.purp == "1") {
					$('#purposedoc').text("Official Doc");
				} else if (r.data.purp == "3") {
					$('#purposedoc').text("Patient Doc")
				} else if (r.data.purp == "4") {
					$('#purposedoc').html("Student Doc")
				} else {
					$('#purposedoc').html("Support Doc")
				}
				if (r.data.suppDocName != null) {
					var fileArr = r.data.suppDocName.split(/(\\|\/)/);
					suppdocname = fileArr[fileArr.length - 1];
				}
				var catgdocname = null;
				if (r.data.catgDocName != null) {
					var fileArr = r.data.catgDocName.split(/(\\|\/)/);
					catgdocname = fileArr[fileArr.length - 1];
				}

				var gdocname = null;
				if (r.data.gDocName != null) {
					var fileArr = r.data.gDocName.split(/(\\|\/)/);
					gdocname = fileArr[fileArr.length - 1];
				}
				if (r.data.ext == '2') {
					$("#extension").prop("checked", true);
				} else {
					$("#extension").prop("checked", false);
				}
				setTimeout(function() {
					if (r.data.duringstay == 1) {
						$('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
					} else {
						$('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
						$('#yesDuringStay').hide();
					}
					var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
					if (convay == 1) {
						$('#Yesconve').show();
						if (r.data.isArrivalConv) {
							$('#modArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
						} else {
							$('#modArrivalDetails').html("Arrival Not Requested");
						}
						if (r.data.isDepartueConv) {
							$('#modDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
						} else {
							$('#modDepartureDetails').html("Departure Not Requested");
						}
						$('input[name="conveyance"][type="radio"][value="' + convay + '"]').prop('checked', true);
					} else {
						$('#modArrivalDetails').html("No Coveyance Requested");
						$('#modDepartureDetails').html("No Coveyance Requested");
						$('input[name="conveyance"][type="radio"][value="' + convay + '"]').prop('checked', true);
						$('#Yesconve').hide();
					}

					$('#modBookId').text(r.data.bookingId);
					$('#modRoomType').text(r.data.roomType == 'R' ? 'Room Only' : r.data.roomType == 'RC' ? 'Room and Convayance' : '');
					$('#modCity').text(r.data.cityName);
					$('#modGH').text(r.data.blockName);
					$('#modFrDt').text(r.data.frmDt);
					$('#modToDt').text(r.data.toDt);
					$('#modNOfDays').text(r.data.noOfDays);
					$('#modMobNo').text(r.data.mob);
					$('#modEmailId').text(r.data.emailId);
					$('#modNor').text(r.data.noOfRooms);
					$('#modNoOfAdults').text(r.data.noOfAdults);
					$('#modNoOfChildren').text(r.data.noOfChildren);
					$('#modDesg').text(r.data.desgName);
					$('#modCatg').text(r.data.catgName);
					$('#modNob').text(r.data.noOfBedsDormitory);
					$('#modPurpose').text(r.data.purpName);
					$('#modOffAddr').text(r.data.offAddr);
					$('#modDepPlace').text(r.data.depPla);
					if (r.data.bookingId.startsWith("P")) {
						$("#profileDoc").html('<a href="./imagedownload/user_' + r.data.userId + '.do" target="_blank" class="download-btn">View</a>');
					} else {
						$("#profileDoc").html('');
					}
					if (suppdocname != null) {
						$("#suppDocDtls").show();
						$("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
					} else {
						$("#suppDocDtls").hide();
					}
					if (catgdocname != null) {
						$("#catDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
						$("#catDocDtls").show();
					} else {
						$("#catDocDtls").hide();
					}
					if (gdocname != null) {
						$("#guestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
						$("#guestDocDtls").show();
					} else {
						$("#guestDocDtls").hide();
					}
					r.data.ghList.forEach((item, index) => {
						$('#count').val(r.data.ghList.length);
						$('#roomDetails').append(
							`<tr class="text-center text-nowrap resp">
								<input type="hidden" id="blockId${index + 1}"  name="blockId${index + 1}" value="${item.id}">
								<input type="hidden" id="blockId"  name="blockId" value="${r.data.blockId}">
								<input type="hidden" id="ghId"  name="cityId" value="${r.data.cityId}">
								<input type="hidden" id="reqId"  name="bookingId" value="${r.data.bookingId}">
								<td scope="col">${item.name}</td>
								<td scope="col"><input type="number" min="0" placeholder="0" id="noOfRooms${index + 1}"  name="noOfRooms${index + 1}" class="test" value="" autocomplete="off" onkeyup="return restrictInput(${index + 1});"></td>
								<td scope="col"><input type="number" min="0" placeholder="0" id="noOfBedsDormitory${index + 1}" name="noOfBedsDormitory${index + 1}" class="test" autocomplete="off" value="" onkeyup="return restrictInput(${index + 1});"></td>								
							</tr>`
						);
					});
					if (type == 'UPDATE') {
						if (r.data.cityId == '01') {
							if (r.data.blockId == '01') {
								$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
								$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
							}
						}
						if (r.data.cityId == '01') {
							if (r.data.blockId == '02') {
								$('#roomDetails #noOfBedsDormitory2').val(r.data.noOfBedsDormitory);
								$('#roomDetails #noOfRooms2').val(r.data.noOfRooms);
							}
						}
						if (r.data.cityId == '01') {
							if (r.data.blockId == '03') {
								$('#roomDetails #noOfBedsDormitory3').val(r.data.noOfBedsDormitory);
								$('#roomDetails #noOfRooms3').val(r.data.noOfRooms);
							}
						}
						if (r.data.cityId == '02') {
							if (r.data.blockId == '04') {
								$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
								$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
							}
						}
						if (r.data.cityId == '03') {
							if (r.data.blockId == '05') {
								$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
								$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
							}
						}
						if (r.data.cityId == '04') {
							if (r.data.blockId == '06') {
								$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
								$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
							}
						}
					}

					if (r.data.catg == "C4") {
						$('#guestDetailsWrapperr').html('');
						$('#categoryFourTabledetails').show();
						$('#categoryTabledetails').hide();
						r.data.list.forEach((item, index) => {
							$('#guestDetailsWrapperr').append(
								`<tr class="text-center text-nowrap resp">
									<td scope="col">${index + 1}</td>
									<td scope="col">${item.guestName}</td>
									<td scope="col">${item.guestAge}</td>
									<td scope="col">${item.guestGender == 'M' ? 'Male' : 'Female'}</td>								
								</tr>`
							);
						});
					} else {
						$('#guestDetailsWrapper').html('');
						$('#categoryTabledetails').show();
						$('#categoryFourTabledetails').hide();
						r.data.list.forEach((item, index) => {
							$('#guestDetailsWrapper').append(
								`<tr class="text-center text-nowrap resp">
									<td scope="col">${index + 1}</td>
									<td scope="col">${item.guestName}</td>
									<td scope="col">${item.guestAge}</td>
									<td scope="col">${item.guestGender == 'M' ? 'Male' : 'Female'}</td>
									<td scope="col">${item.guestDesignation}</td>
									<td scope="col">${item.guestDepartment}</td>								
								</tr>`
							);
						});
					}
					$('#loading').hide();
				}, 1000);
			}, error: function(jqXHR, status, error) {
				swal(error);
				$('#loading').hide();
			}
		});
	}

	// Coveyance Details
	var getAndSetGuestConveyanceDetails = function(bookingId) {
		$('#loading').show();
		$.ajax({
			url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				console.log(r);
				$('.clearIdValClass').text('');
				$('.clearIdValClass').val('');
				appData = r.data;
				bookingReqId = r.data.bookingId;
				let modeOfTravelFrom = r.data.travelModeFr == 'T' ? 'Train' : r.data.travelModeFr == 'F' ? 'Flight' : '';
				let modeOfTravelTo = r.data.travelModeTo == 'T' ? 'Train' : r.data.travelModeTo == 'F' ? 'Flight' : '';
				let flOrTrainNoFrom = r.data.flNoFr;
				let flOrTrainNoTo = r.data.flNoTo;
				let arrDt = r.data.arrDt;
				let arrTm = r.data.arrTm;
				let arrPla = r.data.arrPla;
				let depDt = r.data.depDt;
				let depPla = r.data.depPla;
				let depTm = r.data.depTm;
				var suppdocname = null;
				if (r.data.purp == "1") {
					$('#convPurposeDoc').text("Official Doc");
				} else if (r.data.purp == "3") {
					$('#convPurposeDoc').text("Patient Doc")
				} else if (r.data.purp == "4") {
					$('#convPurposeDoc').html("Student Doc")
				} else {
					$('#convPurposeDoc').html("Support Doc")
				}
				if (r.data.suppDocName != null) {
					var fileArr = r.data.suppDocName.split(/(\\|\/)/);
					suppdocname = fileArr[fileArr.length - 1];
				}
				var catgdocname = null;
				if (r.data.catgDocName != null) {
					var fileArr = r.data.catgDocName.split(/(\\|\/)/);
					catgdocname = fileArr[fileArr.length - 1];
				}

				var gdocname = null;
				if (r.data.gDocName != null) {
					var fileArr = r.data.gDocName.split(/(\\|\/)/);
					gdocname = fileArr[fileArr.length - 1];
				}
				setTimeout(function() {
					if (r.data.duringstay == 1) {
						$('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
						$('#convModYesDuringStay').show();
					} else {
						$('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
						$('#convModYesDuringStay').hide();
					}
					var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
					if (convay == 1) {
						//if(r.data.roomType == "C"){
						$('#convModNoconve').hide();
						//}
						$('#convModYesconve').show();
						if (r.data.isArrivalConv) {
							$('#convModArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
						} else {
							$('#convModArrivalDetails').html("Arrival Not Requested");
						}
						if (r.data.isDepartueConv) {
							$('#convModDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
						} else {
							$('#convModDepartureDetails').html("Departure Not Requested");
						}
						//$('#convModArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
						//$('#convModDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
						$('input[name="convModconveyance"][type="radio"][value="' + convay + '"]').prop('checked', true);
					} else {
						$('#convModArrivalDetails').html("No Coveyance Requested");
						$('#convModDepartureDetails').html("No Coveyance Requested");
						$('input[name="convModconveyance"][type="radio"][value="' + convay + '"]').prop('checked', true);
						$('#convModYesconve').hide();
						$('#convModNoconve').hide();
					}

					$('#convModBookId').text(r.data.bookingId);
					$('#convModRoomType').text(r.data.roomType == 'R' ? 'Room Only' : r.data.roomType == 'RC' ? 'Room and Convayance' : 'Conveyance');
					$('#convModCity').text(r.data.cityName);
					$('#convModGH').text(r.data.blockName);
					$('#convModMobNo').text(r.data.mob);
					$('#convModEmailId').text(r.data.emailId);
					$('#convModNoOfAdults').text(r.data.noOfAdults);
					$('#convModNoOfChildren').text(r.data.noOfChildren);
					$('#convModDesg').text(r.data.desgName);
					$('#convModCatg').text(r.data.catgName);
					$('#convModPurpose').text(r.data.purpName);
					$('#convModOffAddr').text(r.data.offAddr);
					$('#convModDepPlace').text(r.data.depPla);
					if (r.data.bookingId.startsWith("P")) {
						$("#convProfileDoc").html('<a href="./imagedownload/user_' + r.data.userId + '.do" target="_blank" class="download-btn">View</a>');
					}
					if (suppdocname != null) {
						$("#convSuppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
					};
					if (catgdocname != null) {
						$("#convCatDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
					};
					if (gdocname != null) {
						$("#convGuestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
					}
					if (r.data.catg == "C4") {
						$('#convGuestDetailsWrapperr').html('');
						$('#convCategoryFourTabledetails').show();
						$('#convCategoryTabledetails').hide();
						r.data.list.forEach((item, index) => {
							$('#convGuestDetailsWrapperr').append(
								`<tr class="text-center text-nowrap resp">
									<td scope="col">${index + 1}</td>
									<td scope="col">${item.guestName}</td>
									<td scope="col">${item.guestAge}</td>
									<td scope="col">${item.guestGender == 'M' ? 'Male' : 'Female'}</td>								
								</tr>`
							);
						});
					} else {
						$('#convGuestDetailsWrapper').html('');
						$('#convCategoryTabledetails').show();
						$('#convCategoryFourTabledetails').hide();
						r.data.list.forEach((item, index) => {
							$('#convGuestDetailsWrapper').append(
								`<tr class="text-center text-nowrap resp">
                            		<input type="hidden" id="blockId"  name="blockId" value="${r.data.blockId}">
									<input type="hidden" id="ghId"  name="cityId" value="${r.data.cityId}">
									<input type="hidden" id="reqId" name="bookingId" value="${r.data.bookingId}">
									<td scope="col">${index + 1}</td>
									<td scope="col">${item.guestName}</td>
									<td scope="col">${item.guestAge}</td>
									<td scope="col">${item.guestGender == 'M' ? 'Male' : 'Female'}</td>
									<td scope="col">${item.guestDesignation}</td>
									<td scope="col">${item.guestDepartment}</td>								
								</tr>`
							);
						});
					}
					$('#loading').hide();
				}, 1000);
			}, error: function(jqXHR, status, error) {
				swal(error);
				$('#loading').hide();
			}
		});
	}

	var getAndSetGuestLstDetails = function(bookingId) {
		$('#loading').show();
		$.ajax({
			url: "./guest/getBookingDetailsByBookingId/" + bookingId + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				console.log(r);
				appData = r.data;
				r.data.list.forEach((item, index) => {
					guestCount++;
					addGuestDetails(item);
				});
				$('#loading').hide();
			}, error: function(jqXHR, status, error) {
				swal(error);
				$('#loading').hide();
			}
		});
	}

	var submitModeratorDetails = function(data, type) {
		if (isNaN(parseInt(noOfRooms)) ? 0 : parseInt(noOfRooms) < 1 && isNaN(parseInt(noOfBedsDormitory)) ? 0 : parseInt(noOfBedsDormitory) < 1) {
			swal('You have to add atleast one room or bed');
			return false;
		}
		var extApplyOrNot = '0';
		if ($("#extension").is(':checked')) {
			extApplyOrNot = '2';
		}
		var jsonData = {
			"type": type,
			"cityId": data.cityId,
			"blockId": blockIdd,
			"bookingId": data.bookingId,
			"noOfRooms": noOfRooms,
			"noOfBedsDormitory": noOfBedsDormitory,
			"catg": data.catg,
			"desg": data.desg,
			"duringstay": $('input[name="duringstay"][type="radio"]:checked').val(),
			"convayance": $('input[name="conveyance"][type="radio"]:checked').val(),
			"roomType": data.roomType,
			"isArrivalConv": data.isArrivalConv,
			"isDepartueConv": data.isDepartueConv,
			"ext": extApplyOrNot

		};
		console.log(jsonData);
		$.ajax({
			url: "./admin/addOrModifyModeratorDetails.do",
			type: "POST",
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(jsonData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				console.log(data);
				$("#modModalCloseBtn").click();
				table.ajax.reload();
				table1.ajax.reload();
				/*getModeratorTableData();*/
				swal('Successfuly submitted Moderation data');
			}, error: function(jqXHR, status, error) {
				if (jqXHR.status == 400) {
					swal(jqXHR.responseJSON.msg);
				} else if (jqXHR.status == 404) {
					swal('Requested page not found. [404]');
				}
				console.log(jqXHR);
				$('#loading').hide();
			}
		});
	}

	var submitConvModeratorDetails = function(data, type) {
		var jsonData = {
			"type": type,
			"cityId": data.cityId,
			"blockId": data.blockId,
			"bookingId": data.bookingId,
			// "noOfRooms": noOfRooms,
			// "noOfBeds": noOfBeds,
			"catg": data.catg,
			"desg": data.desg,
			"duringstay": $('input[name="convduringstay"][type="radio"]:checked').val(),
			"convayance": $('input[name="convModconveyance"][type="radio"]:checked').val(),
			"roomType": data.roomType,
			"isArrivalConv": data.isArrivalConv,
			"isDepartueConv": data.isDepartueConv

		};
		console.log(jsonData);
		$.ajax({
			url: "./admin/addOrModifyModeratorDetails.do",
			type: "POST",
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(jsonData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				console.log(data);
				$("#convModModalCloseBtn").click();
				table.ajax.reload();
				table1.ajax.reload();
				/*getModeratorTableData();*/
				swal('Successfuly submitted Moderation data');
			}, error: function(jqXHR, status, error) {
				if (jqXHR.status == 400) {
					swal(jqXHR.responseJSON.msg);
				} else if (jqXHR.status == 404) {
					swal('Requested page not found. [404]');
				}
				console.log(jqXHR);
				$('#loading').hide();
			}
		});
	}

	$("#city").change(function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
		table.ajax.reload();
		table1.ajax.reload();
		/*table.ajax.reload();*/
		/*getModeratorTableData();*/
	});
	$("#gHouse").change(function() {
		table.ajax.reload();
		table1.ajax.reload();
		/*getModeratorTableData();*/
		$('#ModBtn').click();
	});

	$("#saveDtls").on('click', function() {
		submitModeratorDetails(appData, 'ADD');
	});
	$("#convSaveDtls").on('click', function() {
		submitConvModeratorDetails(appData, 'ADD');
	});
	$("#modifyDtls").on('click', function() {
		submitModeratorDetails(appData, 'EDIT');
	});
	$("#convModifyDtls").on('click', function() {
		submitConvModeratorDetails(appData, 'UPDATE');
	});
	$("#modifyGuestDtlsBtn").on('click', function() {
		submitGuestLstDtls(appData);
	});
	$("#modModalCloseBtn").on('click', function() {
		$('#saveDtls').show();
		$('#modifyDtls').hide();
		$('#guestDetailsWrapper').html('');
		$('.clearIdValClass').text('');
		$('.clearIdValClass').val('');
		$('#addOrModifyModModal').modal('hide');
	});

	$("#convModModalCloseBtn").on('click', function() {
		$('#convSaveDtls').show();
		$('#convModifyDtls').hide();
		$('#convGuestDetailsWrapper').html('');
		$('.clearIdValClass').text('');
		$('.clearIdValClass').val('');
		$('#convAddOrModifyModModal').modal('hide');
	});

    /*table.ajax.reload();*/

	$('#addOrModifyModModal').on('hidden.bs.modal', function() {
		$('#roomDetails').html('');
		$("#extension").prop("checked", false);
		$("#yesDuringStay").show();
	});

	var setGuestDetails = function(bookingId, isReadonly) {
		var formData = $('#existingBookingApplication').serializeArray();
		var otherDetails = formData.filter(d => !d.name.match(/guest/g));

		console.log(otherDetails);
		$.ajax({
			url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				$('#loading').hide();
				console.log(r);
				if (r.status) {
					editOrViewGuestDetails(r, otherDetails, isReadonly);
				} else {
					swal(r.msg);
				}
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	function editOrViewGuestDetails(r, otherDetails, isReadonly) {
		bookingReqId = r.data.bookingId;
		guestList = r.data.list;
		var id = r.data.catg;
		$('#catg').val(id);
		$('#catg').trigger('change');
		getDesignation(id, r.data.desg);
		getPurposeListDtls(id, r.data.purp);
		if (r.data.ext == '2') {
			$("#extension").prop("checked", true);
		} else {
			$("#extension").prop("checked", false);
		}
		setTimeout(function() {
			$('#purp').trigger('change');
		}, 1000);
		$('#noOfDays').val(r.data.noOfDays);
		var type = r.data.roomType;
		$('#roomTyp').val(type);
		if (type == 'RC') {
			$('#roomCon').click();
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convFrDtls').show();
			$('#convToDtls').show();
			$('#GuestDetails').show();
			if (isReadonly) {
				$('.cat_all').prop('disabled', true);
				$('#roomCon').prop('disabled', false);
				$('#roomCon').addClass('disableElement');
			}
		} else if (type == 'R') {
			$('#onlyRoom').click();
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convFrDtls').hide();
			$('#convToDtls').hide();
			$('#GuestDetails').show();
			if (isReadonly) {
				$('.cat_all').prop('disabled', true);
				$('#onlyRoom').prop('disabled', false);
				$('#onlyRoom').addClass('disableElement');
			}
		} else if (type == 'C') {
			$('#conv').click();
			$('#roomWrapper').hide();
			$('#dormintoryWrapper').hide();
			if (isReadonly) {
				$('.cat_all').prop('disabled', true);
				$('#conv').prop('disabled', false);
				$('#conv').addClass('disableElement');
			}
		}
		// $('input[name="bookingCond"][type="radio"][value="' + valu
		// +'"]').prop('checked', true);
		if (r.data.bookingType != null) {
			if (r.data.bookingType == "dormitory") {
				$('#dormitoryWrapper').show();
			}
			$('input[name="bookingType"][type="radio"][value="' + r.data.bookingType + '"]').prop('checked', true);
		}
		if (r.data.duringstay != null) {
			$('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
		}
		otherDetails.forEach(item => {
			console.log(item.name);
			$('input[name="' + item.name + '"][type!="radio"]').val(r.data[item.name]);
			$('input[name="' + item.name + '"][type="radio"][value="' + r.data[item.name] + '"]').prop('checked', true);
			$('input[name="' + item.name + '"][type="checkbox"][value="' + r.data[item.name] + '"]').prop('checked', true);
			$('select[name="' + item.name + '"]').val(r.data[item.name]);
			if (item.name == 'travelModeFr') {
				var purpose = $('#travelModeFr').val();
				var valu = r.data.arrPla;
				if (purpose == 'F') {
					getFlightTrainList('f', 'arr', valu)
				} else if (purpose == 'T') {
					getFlightTrainList('t', 'arr', valu)
				}
			}

			if (item.name == 'travelModeTo') {
				var purpose = $('#travelModeTo').val();
				var valu = r.data.depPla;
				if (purpose == 'F') {
					getFlightTrainList('f', 'dep', valu)
				} else if (purpose == 'T') {
					getFlightTrainList('t', 'dep', valu)
				}
			}

			$('textarea[name="' + item.name + '"]').val(r.data[item.name]);
			if (isReadonly) {
				$("#desg").addClass('disableElement');
				$("#viewCate").addClass('disableElement');
				$("#travelModeFr").addClass('disableElement');
				$("#travelModeTo").addClass('disableElement');
				$("#arrPla").addClass('disableElement');
				$("#duringstay").addClass('disableElement');
				$("#depPla").addClass('disableElement');
				$('input[name="' + item.name + '"]').addClass('disableElement');
				$('select[name="' + item.name + '"]').addClass('disableElement');
				$('textarea[name="' + item.name + '"]').addClass('disableElement');
				$('.viewOnly').addClass('disableElement');
			} else {
				$("#desg").removeClass('disableElement');
				$("#viewCate").removeClass('disableElement');
				$("#travelModeFr").removeClass('disableElement');
				$("#travelModeTo").removeClass('disableElement');
				$("#duringstay").removeClass('disableElement');
				$("#arrPla").removeClass('disableElement');
				$("#depPla").removeClass('disableElement');
				$('#checkRoomWith').removeClass('disableElement');
				$('input[name="' + item.name + '"]').removeClass('disableElement');
				$('select[name="' + item.name + '"]').removeClass('disableElement');
				$('textarea[name="' + item.name + '"]').removeClass('disableElement');
				$('.viewOnly').removeClass('disableElement');
			}
		});
		if (bookingReqId.charAt(0) === 'G') {
			$("#bookingFor").addClass("disableElement");
			$('#mobileNumber').hide();
		} else {
			$("#bookingFor").removeClass("disableElement");
			$('#mobileNumber').show();
		}
		for (var i = 0; i < r.data.list.length; i++) {
			guestCount++;
			addGuestDetails(guestCount, r.data.list[i], isReadonly);
		}
		var suppdocname = null;
		if (r.data.suppDocName != null) {
			var fileArr = r.data.suppDocName.split(/(\\|\/)/);
			suppdocname = fileArr[fileArr.length - 1];
		}
		var catgdocname = null;
		if (r.data.catgDocName != null) {
			var fileArr = r.data.catgDocName.split(/(\\|\/)/);
			catgdocname = fileArr[fileArr.length - 1];
		}
		var gdocname = null;
		if (r.data.gDocName != null) {
			var fileArr = r.data.gDocName.split(/(\\|\/)/);
			gdocname = fileArr[fileArr.length - 1];
		}
		if (r.data.bookingType == "room") {
			$('#roomWrapper').show();
			$('#dormitoryWrapper').hide();
		} else if (r.data.bookingType == "dormitory") {
			$('#roomWrapper').hide();
			$('#dormintoryWrapper').show();
		}
		if (r.data.bookingFor == "others") {
			$('#otherGuestDetails').show();
		} else {
			$('#otherGuestDetails').hide();
		}
		if (isReadonly) {
			$("#bookingTypeShowDtls, #suppDocDtls1,#desgDocDtls, #gDocDtls").show();
			$("#bookingTypeDtls, #guestMangCtrl, #formSubmissionBtn, #suppDocEdit,#desgDocEdit, #gDocEdit").hide();
			$("#bookingTypeShowDtls").html(r.data.bookingType == 'room' ? 'Book Room' : 'Book Bed');
			if (!r.data.isArrivalConv) {
				$("#convFrDtls").hide()
			}
			;
			if (!r.data.isDepartueConv) {
				$("#convToDtls").hide()
			}
			;
			if (suppdocname != null) {
				$("#suppDocDtls1").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + suppdocname + '</a>');
			}
			;
			if (catgdocname != null) {
				$("#desgDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + catgdocname + '</a>');
			}
			;
			if (gdocname != null) {
				$("#gDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + gdocname + '</a>');
			}
			;
		} else {
			$("#bookingTypeDtls,#guestMangCtrl,#formSubmissionBtn,#suppDocEdit,#desgDocEdit,#gDocEdit").show();
			$("#bookingTypeShowDtls, #suppDocDtls1,#desgDocDtls,#gDocDtls").hide();
			$("#bookingTypeShowDtls,#suppDocDtls1,#desgDocDtls,#gDocDtls").html("");
			if (suppdocname != null) {
				$("#supportDocName").html(suppdocname);
			}
			if (catgdocname != null) {
				$("#categoryDocName").html(catgdocname);
			}
			if (gdocname != null) {
				$("#gDocName").html(gdocname);
			}
			if (r.data.arrivalConv === true) {
				$('#arrivalRow').removeClass("disableElement");
				$('#arrTravel').prop('checked', true);
				$('#arrTravel').val('1');
			}
			if (r.data.departueConv === true) {
				$('#departureRow').removeClass("disableElement");
				$('#deptTravel').prop('checked', true);
				$('#deptTravel').val('1');
			}
		}

		var ext = r.data.ext;
		console.log("ext :" + ext);

		if (ext == 1) {

			$('#toDt').data("DateTimePicker").minDate(moment(r.data.frmDt, 'DD-MM-YYYY').add(1, 'days'));

			$('#onlyRoom').addClass('disableElement');
			$('#roomCon').addClass('disableElement');
			$('#conv').addClass('disableElement');


			$('#catg').addClass('disableElement');
			$('#viewCate').addClass('disableElement');
			$('#mobileNumber').addClass('disableElement');
			$('#desg').addClass('disableElement');
			$('#purp').addClass('disableElement');

			$('#dormitory').addClass('disableElement');
			$('#noOfRooms').addClass('disableElement');
			$('.viewOnly').addClass('disableElement');
			$('#YesC').addClass('disableElement');
			$('#noC').addClass('disableElement');

			$('#noOfDays').addClass('disableElement');
			$('#noOfAdults').addClass('disableElement');
			$('#noOfChildren').addClass('disableElement');
			$('#emailId').addClass('disableElement');
			$('#offAddr').addClass('disableElement');
			$('#mob').addClass('disableElement');
			$('#catgType').addClass('disableElement');
			$('#categoryDoc').addClass('disableElement');
			$('#categoryDocName').addClass('disableElement');
			$('#supportDoc').addClass('disableElement');
			$('#supportDocName').addClass('disableElement');
			$('#gMobile').addClass('disableElement');
			$('#gDocType').addClass('disableElement');
			$('#gDoc').addClass('disableElement');
			$('#gDocName').addClass('disableElement');
			$('#guestTableCat4').addClass('disableElement');
			$('#guestTableCat123').addClass('disableElement');


			$('#travelModeFr').addClass('disableElement');
			$('#flNoFr').addClass('disableElement');
			$('#arrDt').addClass('disableElement');
			$('#arrTm').addClass('disableElement');
			$('#arrPla').addClass('disableElement');

			$('#travelModeTo').addClass('disableElement');
			$('#flNoTo').addClass('disableElement');
			$('#depDt').addClass('disableElement');
			$('#depTm').addClass('disableElement');
			$('#depPla').addClass('disableElement');
		}

	}

	var getPurposeListDtls = function(id, value) {
		$.ajax({
			url: "./guest/getPurposeDetails.do?blockId=" + blockId + "&catgId=" + id,
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#purp").html("");
				$("#purp").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (option.id == value) {
						$("#purp").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
						// $("#desg").append('<option value="'+option.id+'"
						// selected="selected">'+option.name+'</option>');
					} else {
						$("#purp").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				})
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	$('#onlyRoom').on('click', function() {
		$('#onlyRoom').addClass('segment-active');
		$('#roomCon').removeClass('segment-active');
		$('#conv').removeClass('segment-active');
		$('#conference').removeClass('segment-active');

		$('#roomBed').prop('checked', true);

		$('#roomTyp').val($(this).val()); // onlyRoom
		console.log($('#roomTyp').val());
		$('#conveyance').hide();
		$('#GuestDetails').show();
		$('#btnSub').show();
		$('.roomBook').show();
		$('#arrTravel').prop('checked', false);
		$('#deptTravel').prop('checked', false);
	});

	$('#roomCon').on('click', function() {
		$('#roomCon').addClass('segment-active');
		$('#onlyRoom').removeClass('segment-active');
		$('#conv').removeClass('segment-active');
		$('#conference').removeClass('segment-active');

		$('#roomBed').prop('checked', true);

		$('#roomTyp').val($(this).val()); // roomCon
		console.log($('#roomTyp').val());
		$('#conveyance').show();
		$('#GuestDetails').show();
		$('#btnSub').show();
		$('.roomBook').show();
	});

	$('#conv').on('click', function() {
		$('#conv').addClass('segment-active');
		$('#onlyRoom').removeClass('segment-active');
		$('#roomCon').removeClass('segment-active');
		$('#conference').removeClass('segment-active');

		$('#roomTyp').val($(this).val()); // conv
		console.log($('#roomTyp').val());
		$('#conveyance').show();
		$('#GuestDetails').show();
		$('#btnSub').show();
		$('.roomBook').hide();
		$('#roomWrapper').hide();
		$('#dormitoryWrapper').hide();
	});

	$('#conference').on('click', function() {
		$('#conference').addClass('segment-active');
		$('#onlyRoom').removeClass('segment-active');
		$('#roomCon').removeClass('segment-active');
		$('#conv').removeClass('segment-active');

		$('#roomTyp').val($(this).val()); // onlyRoom
		console.log($('#roomTyp').val());
	});

	$("#arrDt, #depDt").datetimepicker({
		useCurrent: false,
		minDate: moment().millisecond(0).second(0).minute(0).hour(0),
		format: 'DD-MM-YYYY'
	});

	$("#frmDt").datetimepicker({
		useCurrent: false,
		minDate: moment().millisecond(0).second(0).minute(0).hour(0),
		format: 'DD-MM-YYYY 09:00'
	});

	$("#toDt").datetimepicker({
		useCurrent: false,
		minDate: moment().millisecond(0).second(0).minute(0).hour(0),
		format: 'DD-MM-YYYY 08:00'
	});

	$("#extFrmDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY 09:00'
	});

	$("#extToDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY 08:00'
	});

	$("#depTm, #arrTm").datetimepicker({
		format: 'hh:mm A'
	});

	$("#frmDt").on("dp.change", function(e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
		$("#toDt").data("DateTimePicker").minDate(e.date);
		if ($('#toDt').val() != '') {
			var date1 = convertStringToDatetime($('#frmDt').val());
			var date2 = convertStringToDatetime($('#toDt').val());
			var days = daysBetween(date1, date2);
			$('#noOfDays').val(days);
		}
	});
	$("#toDt").on("dp.change", function(e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'toDt');
		$("#frmDt").data("DateTimePicker").maxDate(e.date);
		if ($('#frmDt').val() != '') {
			var date1 = convertStringToDatetime($('#frmDt').val());
			var date2 = convertStringToDatetime($('#toDt').val());
			var days = daysBetween(date1, date2);
			$('#noOfDays').val(days);
		}
	});

	$("#arrDt").on("dp.change", function(e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
		$("#depDt").data("DateTimePicker").minDate(e.date);
	});
	$("#depDt").on("dp.change", function(e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'depDt');
		$("#arrDt").data("DateTimePicker").maxDate(e.date);
	});

	$("#extToDt").on("dp.change", function(e) {
		// $('#newRoomBookingApplication').bootstrapValidator('revalidateField',
		// 'toDt');
		if ($('#extFrmDt').val() != '') {
			var date1 = convertStringToDatetime($('#extFrmDt').val());
			var date2 = convertStringToDatetime($('#extToDt').val());
			var days = daysBetween(date1, date2);
			$('#extNoOfDays').val(days);
		}
	});

	$("#noOfRooms").bind('keyup keypress mouseup', function() {
		var room = $("#noOfRooms").val();
		if (room == '') {
			$("#noOfRooms").removeClass('disableElement');
			$("#noOfBeds").removeClass('disableElement');
		} else {
			$("#noOfBeds").addClass('disableElement');
			$("#noOfBeds").val('')
		}
	});

	$("#noOfBeds").bind('keyup keypress mouseup', function() {
		var room = $("#noOfBeds").val();
		if (room == '') {
			$("#noOfRooms").removeClass('disableElement');
			$("#noOfBeds").removeClass('disableElement');
		} else {
			$("#noOfRooms").addClass('disableElement');
			$("#noOfRooms").val('')
		}
	});

	$("input[name='bookingCond']").click(function() {
		if ($(this).val() == "roomCon") {
			$('#roomTyp').val($(this).val());
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convFrDtls').show();
			$('#convToDtls').show();
			$('#GuestDetails').show();
			$('#addressLine').hide();
		} else if ($(this).val() == "onlyRoom") {
			$('#roomTyp').val($(this).val());
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convFrDtls').hide();
			$('#convToDtls').hide();
			$('#GuestDetails').show();
			$('#addressLine').hide();
		}
	});


	$("input[name='bookingType']").click(function() {
		if ($(this).val() == "room") {
			$('#noOfRooms').val('1');
			$('#noOfBedsDormitory').val('');
			$('#noOfBeds').val('');
			$('#roomWrapper').show();
			$('#dormitoryWrapper').hide();
		} else {
			$('#noOfRooms').val('');
			$('#noOfBedsDormitory').val('1');
			$('#noOfBeds').val('');
			$('#roomWrapper').hide();
			$('#dormitoryWrapper').show();
		}
	});

	$('#supportDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math.round((file.size / (1024 * 1024)));
			if (size > 2) {
				swal('Supporting document should not be greater than 2 mb');
				$('#supportDocName').html('Choose file');
				$('#supportDoc').val('');
			} else {
				suppDoc = file;
				$('#supportDocName').html(suppDoc.name);
			}
		} else {
			$('#supportDocName').html('Choose file');
		}
	});

	$('#categoryDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math.round((file.size / (1024 * 1024)));
			if (size > 2) {
				swal('Category document should not be greater than 2 mb');
				$('#categoryDocName').html('Choose file');
				$('#categoryDoc').val('');
			} else {
				catgDoc = file;
				$('#categoryDocName').html(catgDoc.name);
			}
		} else {
			$('#categoryDocName').html('Choose file');
		}
	});

	$('#gDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math.round((file.size / (1024 * 1024)));
			if (size > 2) {
				swal('Category document should not be greater than 2 mb');
				$('#gDocName').html('Choose file');
				$('#gDoc').val('');
			} else {
				gDoc = file;
				$('#gDocName').html(gDoc.name);
			}
		} else {
			$('#gDocName').html('Choose file');
		}
	});

	$('#catg').on('change', function() {
		var id = $('#catg').val();
		onCatgChange(id);
		if (id != "") {
			getDesignationListDtls(id, '');
			getPurposeListDtls(id, '');
		} else {
			$('#onlyRoom').removeClass('segment-active');
			$('.cat_1').prop('disabled', true);
			$('.cat_234').prop('disabled', true);
		}
	});

	function onCatgChange(id) {
		if (id == 'C1') {
			$('.cat_1').prop('disabled', false);
			$('.cat_234').prop('disabled', false);
			$('#duringStayDiv').removeClass("disableElement");
		} else {
			$('.cat_1').prop('disabled', true);
			$('.cat_234').prop('disabled', false);
			$('input[name="duringstay"][type="radio"][value="0"]').prop('checked', true);
			$('#duringStayDiv').addClass("disableElement");
		}
		if (id == 'C1' || id == 'C2' || id == 'C3') {
			$('#docTypediv').show();
			$('#category_supporting').show();
			$('#guestTableCat123').show();
			$('#guestTableCat4').hide();
		} else {
			$('#docTypediv').hide();
			$('#category_supporting').hide();
			$('#guestTableCat123').hide();
			$('#guestTableCat4').show();
		}
		$('#noOfAdults').trigger('blur');
		$('#supporting').hide();
		if ($('#roomTyp').val() !== 'conference') {
			$('#onlyRoom').click();
		}
	}

	$('#purp').on('change', function() {
		var purpose = $('#purp').val();
		if (purpose == '1') {
			$('#supporting').show();
			$('#purposeDocLabel').html('Official Document Upload');
		} else if (purpose == '3' || purpose == '4') {
			$('#supporting').show();
			if (purpose == '3') {
				$('#purposeDocLabel').html('Patient Document Upload');
			} else {
				$('#purposeDocLabel').html('Student Document Upload');
			}
		} else {
			$('#supporting').hide();
		}
	});

	var getFlightTrainList = function(type, cond, sel) {
		$.ajax({
			url: "./guest/getFlightTrainList/" + blockId + "/" + type + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				if (cond == 'arr') {
					$("#arrPla").html("");
					$("#arrPla").append('<option value="">---Select---</option>');
					data.forEach(option => {
						if (option.name == sel) {
							$("#arrPla").append('<option value="' + option.name + '" selected="selected">' + option.name + '</option>');
						} else {
							$("#arrPla").append('<option value="' + option.name + '">' + option.name + '</option>');
						}
					})
				} else if (cond == 'dep') {
					$("#depPla").html("");
					$("#depPla").append('<option value="">---Select---</option>');
					data.forEach(option => {
						if (option.name == sel) {
							$("#depPla").append('<option value="' + option.name + '" selected="selected">' + option.name + '</option>');
						} else {
							$("#depPla").append('<option value="' + option.name + '">' + option.name + '</option>');
						}
					})
				}
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	$('#travelModeFr').on('change', function() {
		var mode = $('#travelModeFr').val();
		if (mode == 'T') {
			getFlightTrainList('t', 'arr', '')
		} else if (mode == 'F') {
			getFlightTrainList('f', 'arr', '')
		}
	});

	$('#travelModeTo').on('change', function() {
		var mode = $('#travelModeTo').val();
		if (mode == 'T') {
			getFlightTrainList('t', 'dep', '')
		} else if (mode == 'F') {
			getFlightTrainList('f', 'dep', '')
		}
	});


	$('#purp').on('change', function() {
		var purpose = $('#purp').val();
		if (purpose == '1') {
			$('#office').show();
			$('#supporting').show();
		} else if (purpose == '3' || purpose == '4') {
			$('#office').show();
			$('#supporting').show();
		} else {
			$('#office').show();
			$('#supporting').hide();
		}

	});

	$('#catg').on('change', function() {
		var category = $('#catg').val();
		if (category == 'C1' || category == 'C2' || category == 'C3') {
			$('#docTypediv').show();
			$('#category_supporting').show();
		} else {
			$('#docTypediv').hide();
			$('#category_supporting').hide();
		}

	});

	var addGuestDetails = function(count, data, readonly) {
		let category = $('#catg').val();
		console.log("category  " + category);
		if (category == 'C1' || category == 'C2' || category == 'C3') {
			// $('#guestDetailsWrapper').html('');
			$('#guestDetailsWrapperN').append(
				'<tr id=tr' + count + '> ' +
				'<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="hidden" name="guestId" id="guestId' + count + '">' +
				'<input type="hidden" name="guestSlNo" value="' + count + '">' +
				'<input type="text" placeholder="Name" id="guestName' + count + '" name="guestName" autocomplete="off">' +
				'</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="number" placeholder="Age in years" id="guestAge' + count + '" name="guestAge" autocomplete="off">' +
				'</td> ' +
				'<td class="text-center text-nowrap"> ' +
				'<select id="guestGender' + count + '" name="guestGender"> ' +
				'<option>---Select---</option> ' +
				'<option value="M">Male</option> ' +
				'<option value="F">Female</option> ' +
				'<option value="O">Other</option> ' +
				'</select> ' +
				'</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="text" placeholder="Designation" name="guestDesignation" id="guestDesignation' + count + '" autocomplete="off">' +
				'</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="text" placeholder="Department" name="guestDepartment" id="guestDepartment' + count + '" autocomplete="off">' +
				'</td> ' +
                /*
				 * '<td class="text-center text-nowrap">' + '<input
				 * type="text" placeholder="Address" name="guestAddr"
				 * id="guestAddr' + count + '">' + '</td> ' +
				 */
				'</tr> '
			);
		} else if (category == 'C4') {
			// $('#guestDetailsWrapper1').html('');
			$('#guestDetailsWrapper1').append(
				'<tr id=tr' + count + '> ' +
				'<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="hidden" name="guestId" id="guestId' + count + '">' +
				'<input type="hidden" name="guestSlNo" value="' + count + '">' +
				'<input type="text" placeholder="Name" id="guestName' + count + '" name="guestName" autocomplete="off">' +
				'</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="number" placeholder="Age in years" id="guestAge' + count + '" name="guestAge" autocomplete="off">' +
				'</td> ' +
				'<td class="text-center text-nowrap"> ' +
				'<select id="guestGender' + count + '" name="guestGender"> ' +
				'<option>---Select---</option> ' +
				'<option value="M">Male</option> ' +
				'<option value="F">Female</option> ' +
				'<option value="O">Other</option> ' +
				'</select> ' +
				'<input type="hidden" id="guestDesignation' + count + '" name="guestDesignation" value="" autocomplete="off">' +
				'<input type="hidden" id="guestDepartment' + count + '" name="guestDepartment" value="" autocomplete="off">' +
				'</td> ' +
				/*
				  * '<input type="text" placeholder="Address" name="guestAddr"
				  * id="guestAddr' + count + '">' +
				  */
				'</tr> '
			);
		}
		if (data != undefined) {
			$("#guestId" + count).val(data.guestId);
			$("#guestName" + count).val(data.guestName);
			$("#guestAge" + count).val(data.guestAge);
			$("#guestGender" + count).val(data.guestGender);
			$("#guestAddr" + count).val(data.guestAddr);
			$("#guestDesignation" + count).val(data.guestDesignation);
			$("#guestDepartment" + count).val(data.guestDepartment);
		}
		if (readonly != undefined ? readonly : false) {
			$("#guestId" + count).addClass('disableElement');
			$("#guestName" + count).addClass('disableElement');
			$("#guestAge" + count).addClass('disableElement');
			$("#guestGender" + count).addClass('disableElement');
			$("#guestAddr" + count).addClass('disableElement');
			$("#guestDesignation" + count).addClass('disableElement');
			$("#guestDepartment" + count).addClass('disableElement');
			$('#checkRoomWith').addClass('disableElement');
		}
	}

	$('#noOfAdults, #noOfChildren').on('click', function() {
		if ($('#catg').val() == "") {
			swal("Please select category");
			$('#noOfAdults').val('');
			$('#noOfChildren').val('');
		}
	});

	$('#noOfAdults, #noOfChildren').on('blur', function() {
		let noOfAdults = $('#noOfAdults').val() == '' ? 0 : $('#noOfAdults').val();
		let noOfChildren = $('#noOfChildren').val() == '' ? 0 : $('#noOfChildren').val();
		let totalGuests = parseInt(noOfAdults) + parseInt(noOfChildren);
		$('#guestDetailsWrapperN').html('');
		$('#guestDetailsWrapper1').html('');

		if (totalGuests > guestCount) {
			let count = 1;
			for (let i = 0; i < guestCount; i++) {
				addGuestDetails(count, guestList[i], false);
				count++;
			}
			for (let i = 0; i < totalGuests - guestCount; i++) {
				addGuestDetails(count);
				count++;
			}
		} else {
			if (guestCount == 0) {
				for (let i = 0; i < totalGuests; i++) {
					addGuestDetails(i + 1);
				}
			} else {
				for (let i = 0; i < totalGuests; i++) {
					addGuestDetails(i + 1, guestList[i], false);
				}
			}
		}
	});

	$('#bookingFor').change(function() {
		var bookingFor = $('#bookingFor').val();
		if (bookingFor == 'others') {
			$('#otherGuestDetails').show();
		} else {
			$('#otherGuestDetails').hide();
		}
		$('#guestDetailsWrapperN').html('');
		$('#guestDetailsWrapper1').html('');
		$('#noOfAdults').val('');
		$('#noOfChildren').val('');
		if (bookingFor == "self") {
			$('#noOfAdults').val(1);
			$('#noOfAdults').trigger('blur');
		}
	});

	var getCatgoryListDtls = function(type) {
		$.ajax({
			url: "./guest/getCategoryDetails.do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#catg").html("");
				$("#catg").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (('C' + type) == option.id) {
						$("#catg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
					} else {
						$("#catg").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				})
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	getCatgoryListDtls('');

	var submitBookingDetails = function(type) {
		if ($("#bookingFor").val() == "" || $("#bookingFor").val() == null) {
			swal("Please select Booking For");
			return false;
		}
		if ($("#purp").val() == "" || $("#purp").val() == null) {
			swal("Please select Purpose of visit");
			return false;
		}
		if ($("#desg").val() == "" || $("#desg").val() == null) {
			swal("Please select Designation");
			return false;
		}
		var formData = $('#existingBookingApplication').serializeArray();
		var rawGuestDetails = formData.filter(d => d.name.match(/guest/g));
		var otherDetails = formData.filter(d => !d.name.match(/guest/g));
		var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
		console.log(guestDtls);
		for (let guest of guestDtls) {
			if (guest.guestAge == "") {
				swal('Please enter age of all guests');
				return false;
			}
			if (guest.guestGender == "" || guest.guestGender == '---Select---') {
				swal('Please provide gender of all guests');
				return false;
			}
			if (guest.guestName == "") {
				swal('Please provide name of all guests');
				return false;
			}
		}
		var fd = new FormData();
		fd = convertSerializeArrayToFd(otherDetails);
		fd.append("guestDtlsStr", JSON.stringify(guestDtls));
		fd.append("bookingId", bookingReqId);
		fd.append("noOfDays", $('#noOfDays').val());
		if (suppDoc != null) { fd.append("suppDoc", suppDoc, suppDoc.name) };
		if (catgDoc != null) { fd.append("catgDoc", catgDoc, catgDoc.name) };
		if (gDoc != null) fd.append("gDoc", gDoc, gDoc.name);
		let adultCount = $('#noOfAdults').val() == "" ? 0 : $('#noOfAdults').val();
		let childCount = $('#noOfChildren').val() == "" ? 0 : $('#noOfChildren').val();
		if (!validateChildrenAndAdultCount(adultCount, childCount, guestDtls))
			return false;

		$.ajax({
			url: "./admin/adminsaveBookingDetails/edit/" + type + ".do",
			type: "POST",
			enctype: 'multipart/form-data',
			processData: false,  // Important!
			contentType: false,
			data: fd,
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				if (data.status) {
					$('#modifyGuestDtlsModal').modal('hide');
					swal(data.msg);
					table.ajax.reload();
				} else {
					swal(data.msg);
				}
			}, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);

                $('#loading').hide();
            }
		});
	};

	$('#modifyGuestDtlsModal').on('hidden.bs.modal', function() {
		$('#existingBookingApplication').bootstrapValidator('resetForm', true);
		$('#existingBookingApplication')[0].reset();
		$('#guestDetailsWrapperN').html('');
		$('#guestDetailsWrapper1').html('');
		$('#suppDoc').val('');
		$('#catgDoc').val('');
		$('#gDoc').val('');
		$('#suppDocName').html('');
		$('#catgDocName').html('');
		$('#gDocName').html('');
		suppDoc = null;
		catgDoc = null;
		gDoc = null;
		guestCount = 0;
		$('#arrPla').val('');
		$('#depPla').val('');
	});

	$('#existingBookingApplication').bootstrapValidator({
		btnSubmit: 'button[type="submit"]',
		fields: {
			bookingType: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, frmDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, toDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, bookingFor: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, noOfRooms: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					greaterThan: {
						value: 0,
						message: 'field must be greater than 0'
					}
				}
			}, noOfAdults: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					greaterThan: {
						value: 0,
						message: 'field must be greater than 0'
					}
				}
			}, noOfDays: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					greaterThan: {
						value: 0,
						message: 'field must be greater than 0'
					}
				}
			}, mob: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp: /^[9 8 7 6]\d{9}$/,
						message: 'only 10 digits are allowed'
					}
				}
			}, gMobile: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp: /^[9 8 7 6]\d{9}$/,
						message: 'only 10 digits are allowed'
					}
				}
			}, catg: {
				validators: {
					notEmpty: {
						message: 'select a valid option'
					}
				}
			}, purp: {
				validators: {
					notEmpty: {
						message: 'select a valid option'
					}
				}
			}, desg: {
				validators: {
					notEmpty: {
						message: 'select a valid option'
					}
				}
			},
			offAddr: {
                validators: {
					notEmpty: {
						message: 'Field can\'t be empty'
					}/*,
					regexp: {
						regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
						message: 'Please enter a valid address followed by a 6-digit pincode, separated by a space'
					}*/
				}
			 }
			// travelModeFr: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         }
			//     }
			// }, flNoFr: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         },
			//         regexp: {
			//             regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
			//             message: 'field is not valid'
			//         }
			//     }
			// }, arrDt: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         }
			//     }
			// }, arrPla: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         },
			//         regexp: {
			//             regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
			//             message: 'field is not valid'
			//         }
			//     }
			// }, travelModeTo: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         }
			//     }
			// }, flNoTo: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         },
			//         regexp: {
			//             regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
			//             message: 'field is not valid'
			//         }
			//     }
			// }, depDt: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         }
			//     }
			// }, depPla: {
			//     validators: {
			//         notEmpty: {
			//             message: 'field can\'t be empty'
			//         },
			//         regexp: {
			//             regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
			//             message: 'field is not valid'
			//         }
			//     }
			/*}*/
		}
	}).on('error.field.bv', function(e, data) {
		if (data.bv.getSubmitButton()) {
			data.bv.disableSubmitButtons(false);
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var type = $('#roomTyp').val();
		if (type == 'conv') {
			if (!checkTravel())
				return false;
			submitBookingDetails('C');
		} else if (type == 'onlyRoom') {
			submitBookingDetails('R');
		} else {
			if (!checkTravel())
				return false;
			submitBookingDetails('RC');
		}
	});

	let checkTravel = () => {
		let arrTravel = $('#arrTravel').is(':checked');
		let deptTravel = $('#deptTravel').is(':checked');
		if (!arrTravel && !deptTravel) {
			swal("Please select conveyance details");
			return false;
		}
		if (arrTravel) {
			let travelModeFr = $('#travelModeFr').val();
			let flNoFr = $('#flNoFr').val();
			let arrDt = $('#arrDt').val();
			let arrTm = $('#arrTm').val();
			let arrPla = $('#arrPla').val();

			if (travelModeFr == '' || flNoFr == '' || arrDt == '' || arrTm == '' || arrPla == '') {
				swal("Please fill arrival details");
				return false;
			}
		}
		if (deptTravel) {
			let travelModeTo = $('#travelModeTo').val();
			let flNoTo = $('#flNoTo').val();
			let depDt = $('#depDt').val();
			let depTm = $('#depTm').val();
			let depPla = $('#depPla').val();

			if (travelModeTo == '' || flNoTo == '' || depDt == '' || depTm == '' || depPla == '') {
				swal("Please fill departure details");
				return false;
			}
		}
		return true;
	}

	$('#arrTravel').on('click', function() {
		if ($(this).is(':checked')) {
			$('#arrivalRow').removeClass('disableElement');
			$(this).val('1');
		} else {
			$('#arrivalRow').addClass('disableElement');
			$(this).val('');
		}
	});

	$('#deptTravel').on('click', function() {
		if ($(this).is(':checked')) {
			$('#departureRow').removeClass('disableElement');
			$(this).val('1');
		} else {
			$('#departureRow').addClass('disableElement');
			$(this).val('');
		}
	});

	// function to check how many guests have age < 12
	function validateChildrenAndAdultCount(adultCount, childCount, guestDtls) {
		if (adultCount < 1) {
			swal('Please add at least 1 adult');
			return false;
		}
		var i, child = 0, adult = 0;
		for (i = 0; i < guestDtls.length; i++) {
			if (guestDtls[i].guestAge < 12) {
				child++;
			} else {
				adult++;
			}
		}
		if (childCount < child || adultCount < adult) {
			swal('Please check the number of children and adults');
			return false;
		}
		return true;
	}


	var desAccToCatTable = $("#guestCatDataTable").removeAttr('width').DataTable({
		"paging": true,
		"searching": true,
		"responsive": true,
		"lengthMenu": [[100, 200, 300, -1], [100, 200, 300, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"stateSave": true,
		"bDestroy": true,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"columnDefs": [
			{ className: "all", "targets": [0, 1, 2] },
			{
				"targets": 0, "data": null,
				render: function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{
				"targets": 1, "data": null,
				render: function(data, type, row, meta) {
					return "Cat" + data.desc
				}
			},
			{ "targets": 2, "data": "name" },
			{
				"targets": 3, "data": null,
				render: function(data, type, row, meta) {
					return '<button type="button" id="cateType" title="Select Category" action="cateType" class="btn btn-sm btn-edit"><i class="fa fa-edit" aria-hidden="true"></i></button>'
				}
			}
		],
	});

	$('#guestCatDataTable').on('click', 'tbody #cateType', function() {
		$('#myModalViewForCategory').modal('hide');
		var data_row = desAccToCatTable.row($(this).closest('tr')).data();
		console.log("data_row-----------------" + data_row);
		getDesgAccToCat(data_row.desc);
		getDesignationListDtls(('C' + data_row.desc), data_row.id);
		getPurposeListDtls(('C' + data_row.desc), '');
		onCatgChange('C' + data_row.desc);
		// getGuestDesignationDetails(data_row.id);
	});

	var viewDesg;
	$('#viewCate').click(function() {
		viewDesg = "All";
		var cat = $('#catg').val();
		console.log(cat);
		if (cat != "") {
			console.log("value of catg")
			viewDesg = cat;
		}
		getDesgAccToCat(viewDesg);
		$('#myModalViewForCategory').modal('show');

	});

	var getDesgAccToCat = function(type) {
		$.ajax({
			url: "./guest/getDesgList/" + type + ".do",
			type: "GET",
			headers: { "X-CSRF-TOKEN": token },
			success: function(result) {
				desAccToCatTable.clear().draw();
				desAccToCatTable.rows.add(result).draw();
				/* table.columns.adjust().responsive.recalc(); */
			}
		});
	};

	let getAllotmentList = (pageNo) => {
		let bookingId = $('#filterBookingId').val();
		let bookingDate = $('#filterBookingDate').val();
		let category = $('#filterCategory').val();
		let purpose = $('#filterPurpose').val();
		let mobNo = $('#filterMobNum').val();
		let name = $('#filterGuestName').val();
		let roomType = $('#filterRoomType').val();
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		let postData = {
			cId: city,
			gId: gHouse,
			bookingId: bookingId,
			bookingDate: bookingDate,
			category: category,
			purpose: purpose,
			mobNo: mobNo,
			name: name,
			pageNo: pageNo,
			type: "modifyListMOD",
			roomType: roomType
		};

		$.ajax({
			url: "./admin/getModListForAllotment.do",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				$('#loading').hide();
				if (r.status) {
					console.log(r);

					// load data into modify list table
					$('#modifyListTableBody').empty();
					let reqId = '';
					r.page.data.forEach(function(row, i) {
						let rowHtml = '<tr>';
						if (reqId != row.bookingId) {
							if (row.designation != "" && row.department != "") {
								reqId = row.bookingId;
								rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
									'<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +
									'<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + "," + row.designation + "," + row.department + '</td>'
							} else {
								reqId = row.bookingId;
								rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
									'<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +
									'<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + '</td>'
							}
						}
						rowHtml += '<td>' + row.bookingDt + '</td>' +
							'<td>' + row.fromDate + '</td>' +
							'<input type="hidden" id="modInput' + (i + 1) + '" name="data" />' +
							'<td>' + row.toDate + '</td>' +
							'<td>' + row.category + '</td>' +
							'<td>' + row.purpose + '</td>' +
							'<td>' + row.type + '</td>' +
							'<td>' + row.statusDesc + '</td>' +
							'<td>' +
							'<button type="button" id="modify" action="Modify" title="Modify Allotment" class="btn btn-sm btn-view"><i class="fa fa-refresh" aria-hidden="true"></i></button> ' +
							'</td>' +
							'</tr>';
						$('#modifyListDataTable').append(rowHtml);
						$('#modInput' + (i + 1)).val(JSON.stringify(row));
					});

					let pageNo = $("#modPageNo").html();
					if (pageNo >= r.page.totalPages) {
						$("#modPageNext").attr("disabled", true);
					} else {
						$("#modPageNext").attr("disabled", false);
					}

					if (pageNo == 1) {
						$("#modPagePrev").attr("disabled", true);
					} else {
						$("#modPagePrev").attr("disabled", false);
					}
				} else {
					swal(r.msg);
				}
			}, error: function (jqXHR, status, error) {
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

	let bookingId = '';
	let modId = '';
	let mrrId = '';
	let roomType = 'room';
	let type = "modify";

	var guestTable = $("#guestDetailsTable").DataTable({
		"paging": true,
		"searching": true,
		/*"scrollX": true,*/
		"lengthMenu": [[10, 20, 30, -1],
		[10, 20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs": [{
			"targets": 0, "data": null,
			render: function(data, type, row, meta) {
				return meta.row + meta.settings._iDisplayStart + 1;
			}
		},
		{ "targets": 1, "data": "bookingId" },
		{ "targets": 2, "data": "name" },
		{ "targets": 3, "data": "fromDate" },
		{ "targets": 4, "data": "toDate" },
		{ "targets": 5, "data": "noOfDays" },
		{ "targets": 6, "data": "type" },
		{ "targets": 7, "data": "statusDesc" }
		],
	});

	$('#modifyListDataTable tbody').on('click', '#modify', function() {
		// let data = table.row($(this).parents('tr')).data();
		let data = JSON.parse($(this).parents('tr').find('input[name=data]').val());
		bookingId = data.bookingId;
		modId = data.modId;
		mrrId = data.mrrId;
		allotRooms(data);
	});
	let reqIdForMod = "";
	let allotRooms = (data) => {
		$('#allotmentModal').modal('show');
		let list = [];
		list.push(data);
		guestTable.clear();
		guestTable.rows.add(list).draw();
		$('#prevOrderNo').val(data.rowNo);
		$('#modifyDate').val(data.prevOrderDt);
		reqIdForMod = data.bookingId;
		$('#reason').val(data.remarks);
		if (data.type == "Room") {
			$('#roomView').trigger('click');
			$('#roomView').removeClass("disableElement")
			$('#dormitoryView').addClass("disableElement")
		} else {
			$('#dormitoryView').trigger('click');
			$('#dormitoryView').removeClass("disableElement")
			$('#roomView').addClass("disableElement")
		}
	}

	$('#roomView').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		let cityId = $('#city').val();
		let ghId = $('#gHouse').val();
		roomType = "room";
		getTotalRooms(bookingId, cityId, ghId, type);
	});
	
	$('#dormitoryView').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		let cityId = $('#city').val();
		let ghId = $('#gHouse').val();
		roomType = "bed";
		getTotalBeds(bookingId, cityId, ghId, type);
	});
	/*$("#revertModifyBooking").on("click",function(){
		swal({
			title: "Are you sure? ",
			text: "Are you sure to Cancel Modified Allotment Details!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, Cancel it!",
			closeOnConfirm: false
		},
		function() {
			$('#loading').show();
			$.ajax({
				url: './admin/cancelModifiedRoomOrBedRequest/' + bookingId + '.do',
				type: "POST",
				headers: { "X-CSRF-TOKEN": token },
				success: function(data) {
					$('#allotmentModal').modal("hide");
					$('#loading').hide();
					getAllotmentList(1);
					if (data.success) {
						swal(data.msg);
					} else {
						swal(data.msg);
					}
				}
			});
		});
	});*/
	let getTotalRooms = function(bookingId, cityId, ghId, type) {
		$.ajax({
			url: './admin/getTotalRooms/' + bookingId + '/' + cityId + '/' + ghId + '.do',
			type: 'GET',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data) {
				$('#loading').hide();
				console.log(data);
				$('#roomListTable thead').empty();
				let headStr = '';
				headStr += '<tr>' +
					'<th class="text-truncate text-center">Date</th>';
				for (let item of data.roomsModel.roomList) {
					headStr += '<th class="text-truncate text-center">' + item.room_no + '</th>';
				}
				headStr += '</tr>';
				$('#roomListTable thead').append(headStr);

				$('#roomListTable tbody').empty();
				let bodyStr = '';
				data.roomsModel.drmList.forEach(function(drm, i) {
					bodyStr += '<tr>';
					bodyStr += '<td class="roomDormfont text-truncate text-center">' + drm.dateStr + '</td>';
					for (let roomDetail of drm.roomDetails) {
						bodyStr += '<td class=" text-truncate text-center">' +
							'<input type="hidden" name="roomNo" value="' + roomDetail.roomNo + '">'
						if (roomDetail.reason != null) {
							if (type == "allot") {
								if (roomDetail.reason == 'full') {
									bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon-booked disabled" title="' + roomDetail.guestName + '"><img src="./img/carbon_hotel.png"></button>' +
										'<div class="mt-1">'
									for (let bedDetail of roomDetail.roomDetails) {
										bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + roomDetail.guestName + '">' + bedDetail.bedNo + '</button>';
									}
									bodyStr += '</div>';
								} else {
									bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon-booked disabled"><img src="./img/carbon_hotel.png"></button>' +
										'<div class="mt-1">'
									for (let bedDetail of roomDetail.roomDetails) {
										if (bedDetail.occupied) {
											bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + bedDetail.guestName + '">' + bedDetail.bedNo + '</button>';
										} else {
											bodyStr += ' <button class="btn btn-sm hotel-icon bedBtn date' + (i + 1) + '">' + bedDetail.bedNo + '</button>';
										}
									}
									bodyStr += '</div>';
								}
							} else {
								if (roomDetail.reason == 'full') {
									if (roomDetail.mrrId == mrrId) {
										bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon hotel-icon-active roomBtn date' + (i + 1) + '"><img src="./img/carbon_hotel.png"></button>' +
											'<div class="mt-1">';
										for (let bedDetail of roomDetail.roomDetails) {
											bodyStr += ' <button class="btn btn-sm hotel-icon hotel-icon-active bedBtn date' + (i + 1) + '">' + bedDetail.bedNo + '</button>';
										}
										bodyStr += '</div>';
									} else {
										bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon-booked disabled" title="' + roomDetail.guestName + '"><img src="./img/carbon_hotel.png"></button>' +
											'<div class="mt-1">';
										for (let bedDetail of roomDetail.roomDetails) {
											bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + roomDetail.guestName + '">' + bedDetail.bedNo + '</button>';
										}
										bodyStr += '</div>';
									}
								} else {
									let count = 0;
									for (let bedDetail of roomDetail.roomDetails) {
										if (bedDetail.mrrId != null && bedDetail.mrrId != mrrId)
											count++;
									}
									if (count > 0) {
										bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon-booked disabled"><img src="./img/carbon_hotel.png"></button>';
									} else {
										bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon roomBtn date' + (i + 1) + '"><img src="./img/carbon_hotel.png"></button>';
									}
									bodyStr += '<div class="mt-1">';
									for (let bedDetail of roomDetail.roomDetails) {
										if (bedDetail.mrrId == mrrId) {
											bodyStr += ' <button class="btn btn-sm hotel-icon hotel-icon-active bedBtn date' + (i + 1) + '">' + bedDetail.bedNo + '</button>';
										} else if (bedDetail.guestName == null) {
											bodyStr += ' <button class="btn btn-sm hotel-icon bedBtn date' + (i + 1) + '">' + bedDetail.bedNo + '</button>';
										}
										else {
											bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + bedDetail.guestName + '">' + bedDetail.bedNo + '</button>';
										}
									}
									bodyStr += '</div>';
								}
							}
						} else {
							bodyStr += '<button style="width: 100%" class="btn btn-sm hotel-icon roomBtn date' + (i + 1) + '"><img src="./img/carbon_hotel.png"></button>' +
								'<div class="mt-1">';
							for (let j = 1; j <= roomDetail.maxBeds; j++) {
								bodyStr += ' <button class="btn btn-sm hotel-icon bedBtn date' + (i + 1) + '">' + 'B' + j + '</button>';
							}
							bodyStr += '</div>';
						}
						// bodyStr += '<img src="./img/carbon_hotel.png">' +
						//     '</button>' +
						bodyStr += '</td>';
					}
					bodyStr += '</tr>';
				});
				$('#roomListTable tbody').append(bodyStr);
				roomBedAllotment(data);
			},
			error: function(e) {
				$('#loading').hide();
				console.log("ERROR: ", e);
			}
		});
	}

	let roomBedAllotment = (data) => {
		$('#roomBedAllotment tbody').empty();
		let bedStr = '';
		data.roomsModel.drmList.forEach(function(drm, i) {
			bedStr += '<tr>' +
				'<td class="roomDormfont text-truncate text-center">' + drm.dateStr + '</td>' +
				'<td class="text-truncate text-center">';
			if (type == "allot") {
				bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" disabled>' +
					'</td> ' +
					'<td class="text-truncate text-center">' +
					'<input type="text" placeholder="Rent" name="rent" autocomplete="off" onkeypress="return onlyNumberKey(event)">';
			} else {
				let booked = false;
				for (let roomDetail of drm.roomDetails) {
					if (roomDetail.reason != null) {
						if (roomDetail.reason == 'full') {
							if (roomDetail.mrrId == mrrId) {
								bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" value="' + roomDetail.roomNo + '" disabled>' +
									'</td> ' +
									'<input type="hidden" name="allotId" value="' + roomDetail.allotId + '">' +
									'<td class="text-truncate text-center">' +
									'<input type="text" placeholder="Rent" name="rent" autocomplete="off" value="' + roomDetail.roomRent + '" onkeypress="return onlyNumberKey(event)">';
								booked = true;
								break;
							}
						} else {
							let bedBooked = false;
							for (let bedDetail of roomDetail.roomDetails) {
								if (bedDetail.occupied) {
									if (bedDetail.mrrId == mrrId) {
										bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" value="' + bedDetail.roomNo + "," + bedDetail.bedNo + '" disabled>' +
											'</td> ' +
											'<input type="hidden" name="allotId" value="' + bedDetail.allotId + '">' +
											'<td class="text-truncate text-center">' +
											'<input type="text" placeholder="Rent" name="rent" autocomplete="off" value="' + bedDetail.roomRent + '" onkeypress="return onlyNumberKey(event)">';
										bedBooked = true;
										break;
									}
								}
							}
							if (bedBooked) {
								booked = true;
								break;
							}
						}
					} else {
						if (roomDetail.mrrId == mrrId) {
							if (roomDetail.bedNo == null) {
								bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" value="' + roomDetail.roomNo + '" disabled>';
							} else {
								bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" value="' + roomDetail.roomNo + "," + roomDetail.bedNo + '" disabled>';
							}
							bedStr += '</td> ' +
								'<input type="hidden" name="allotId" value="' + roomDetail.allotId + '">' +
								'<td class="text-truncate text-center">' +
								'<input type="text" placeholder="Rent" name="rent" autocomplete="off" value = "' + roomDetail.roomRent + '" onkeypress="return onlyNumberKey(event)">';
							booked = true;
							break;
						}
					}
				}
				if (!booked) {
					bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" disabled>' +
						'</td> ' +
						'<td class="text-truncate text-center">' +
						'<input type="text" placeholder="Rent" name="rent" autocomplete="off" onkeypress="return onlyNumberKey(event)">';
				}
			}
			bedStr += '</td> ' +
				'</tr>';
		});
		$('#roomBedAllotment tbody').append(bedStr);
	}

	let getTotalBeds = (bookingId, cityId, ghId, type) => {
		$.ajax({
			url: './admin/getTotalBeds/' + bookingId + '/' + cityId + '/' + ghId + '.do',
			type: 'GET',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data) {
				$('#loading').hide();
				$('#bedListTable thead').empty();
				let headStr = '';
				headStr += '<tr>' +
					'<th class="text-truncate text-center">Date</th>';
				for (let item of data.roomsModel.roomNos) {
					headStr += '<th class="text-truncate text-center">' + item + '</th>';
				}
				headStr += '</tr>';
				$('#bedListTable thead').append(headStr);

				$('#bedListTable tbody').empty();
				let bodyStr = '';
				data.roomsModel.drmList.forEach(function(drm, j) {
					bodyStr += '<tr>';
					bodyStr += '<td class="roomDormfont text-truncate text-center">' + drm.dateStr + '</td>';
					let roomNo = '';
					let count = 0;
					for (let i = 0; i < drm.roomDetails.length; i++) {
						if (roomNo != drm.roomDetails[i].roomNo) {
							count = 1;
							roomNo = drm.roomDetails[i].roomNo;
							bodyStr += '<td class=" text-truncate text-center">' +
								'<input type="hidden" name="roomNo" value="' + drm.roomDetails[i].roomNo + '">';
							if (drm.roomDetails[i].occupied) {
								if (drm.roomDetails[i].mrrId == mrrId) {
									bodyStr += ' <button class="btn btn-sm hotel-icon hotel-icon-active roomBtn date' + (j + 1) + '">' + 'B' + count;
								} else {
									bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + drm.roomDetails[i].guestName + '">' + 'B' + count;
								}
							} else {
								bodyStr += ' <button class="btn btn-sm hotel-icon roomBtn date' + (j + 1) + '">' + 'B' + count;
							}
							bodyStr += '</button>';
						} else {
							count++;
							if (drm.roomDetails[i].occupied) {
								if (type == "allot") {
									bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + drm.roomDetails[i].guestName + '">' + 'B' + count;
								} else {
									if (drm.roomDetails[i].mrrId == mrrId) {
										bodyStr += ' <button class="btn btn-sm hotel-icon hotel-icon-active roomBtn date' + (j + 1) + '">' + 'B' + count;
									} else {
										bodyStr += ' <button class="btn btn-sm hotel-icon-booked disabled" title="' + drm.roomDetails[i].guestName + '">' + 'B' + count;
									}
								}
							} else {
								bodyStr += ' <button class="btn btn-sm hotel-icon roomBtn date' + (j + 1) + '">' + 'B' + count;
							}
							bodyStr += '</button>';
							if (i != drm.roomDetails.length - 1) {
								if (roomNo != drm.roomDetails[i + 1].roomNo) {
									bodyStr += '</td>';
									continue;
								}
							}
						}
					}
					bodyStr += '</td></tr>';
				});
				$('#bedListTable tbody').append(bodyStr);
				roomBedAllotment(data);
			},
			error: function(e) {
				$('#loading').hide();
				console.log("ERROR: ", e);
			}
		});
	}

	$('#roomListTable tbody').on('click', '.roomBtn', function() {
		roomBtnClick.call(this, roomType);
	});

	$('#roomListTable tbody').on('click', '.bedBtn', function() {
		bedBtnClick.call(this, roomType);
	});

	let bedBtnClick = function(roomType) {
		$(this).closest('tr').find('.hotel-icon-active').removeClass('hotel-icon-active');
		$(this).addClass('hotel-icon-active');

		let dateClass = $(this).attr('class').split(' ').filter(function(item) {
			return item.indexOf('date') === 0;
		});

		let roomNo = $(this).closest('td').find('input[name="roomNo"]').val();
		roomNo += "," + $(this).text();
		$('#' + dateClass[0]).val(roomNo);
	}

	let roomBtnClick = function(roomType) {
		$(this).closest('tr').find('.hotel-icon-active').removeClass('hotel-icon-active');
		$(this).addClass('hotel-icon-active');

		let dateClass = $(this).attr('class').split(' ').filter(function(item) {
			return item.indexOf('date') === 0;
		});

		let roomNo = $(this).closest('td').find('input[name="roomNo"]').val();
		if (roomType == "bed") {
			roomNo += "," + $(this).text();
		}
		$('#' + dateClass[0]).val(roomNo);
	}

	$('#bedListTable tbody').on('click', '.roomBtn', function() {
		roomBtnClick.call(this, roomType);
	});

	$("#modifyRoomBtn").on('click', function() {
		let prevOrderNo = $('#prevOrderNo').val();
		let reason = $('#reason').val();
		let prevOrderDt = $('#modifyDate').val();
		if (prevOrderNo == '' || reason == '' || prevOrderDt == '') {
			swal('Please provide previous order no and reason and previous order date');
			return false;
		}
		let modifySubmit = { prevOrderNo: prevOrderNo, reason: reason, prevOrderDt: prevOrderDt, bookingId: reqIdForMod };
		let data = [];
		let correct = true;
		$('#roomBedAllotment tbody tr').each(function() {
			let date = $(this).find('td:eq(0)').text();
			let roomNo = $(this).find('input[name="roomNo"]').val();
			let rent = $(this).find('input[name="rent"]').val();
			let allotId = $(this).find('input[name="allotId"]').val();
			let bedNo = null;
			if (roomNo == '' || rent == '') {
				correct = false;
				return false;
			} else {
				bedNo = roomNo.split(",");
				roomNo = bedNo[0];
				bedNo = bedNo[1];
				data.push({
					fromDate: date,
					roomNo: roomNo,
					bedNo: bedNo,
					roomRent: rent,
					allotId: allotId
				});
			}
		});
		if (!correct) {
			swal('Please provide room no and rent');
			return false;
		}
		modifySubmit.roomDetails = data;
		$.ajax({
			url: './admin/modModifyRoom.do',
			type: 'POST',
			data: JSON.stringify(modifySubmit),
			contentType: 'application/json',
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(data) {
				$('#loading').hide();
				if (data.status) {
					$('#allotmentModal').modal('hide');
					swal('Rooms modified successfully');
					$('#modify').click();
				} else {
					swal(data.msg);
				}
			}, error: function(e) {
				$('#loading').hide();
				console.log("ERROR: ", e);
			}
		});
	});

	let getConferenceList = () => {
		let bookingId = $('#filterBookingId').val();
		let bookingDate = $('#filterBookingDate').val();
		let category = $('#filterCategory').val();
		let mobNo = $('#filterMobNum').val();
		let name = $('#filterGuestName').val();
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		let postData = {
			cId: city,
			gId: gHouse,
			bookingId: bookingId,
			bookingDate: bookingDate,
			category: category,
			mobNo: mobNo,
			name: name
		};

		$.ajax({
			url: "./admin/getConfListForModeration.do",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				$("#loading").hide();
				confTable.clear();
				confTable.rows.add(r.data).draw();
			}, error: function (jqXHR, status, error) {
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

	$('#confListDataTable tbody').on('click', '#saveMod', function() {
		let data = confTable.row($(this).parents('tr')).data();
		console.log(data.bookingId);
		appData = data;
		bookingId = data.bookingId;
		$('#confModModal').modal('show');
		getAndSetGuestConferenceDetails(bookingId);
	});

	$('#confListDataTable tbody').on('click', '#billView', function() {
		$('#billingDetailsFinal').modal('show');
		let data = confTable.row($(this).parents('tr')).data();
		console.log(data);
		$("#billFiName").html(data.name);
		$("#billFiId").html(data.bookingId);
		$("#billFiMobile").html(data.mobile);
		$("#billFiAddress").html(data.address);
		$("#billFiHall").html(data.typeOfRoom);
		$("#billFiPeriod").html(data.confUseDesc);
		$("#billFiAmount").html(data.totalAmt);
		$("#billFiAdvnc").html(data.advanceAmount);
		$("#additionalFi").html(data.additionalCharge);
		$("#billFiMode").html(data.modeOfPayment);
		$("#needTextForView").html("Amount Paid:");
		$("#needFi").html(data.advanceAmount);
		$("#billFiTotal").html(parseInt(data.additionalCharge) + parseInt(data.totalAmt));
	});

	$('#confListDataTable tbody').on('click', '#payment', function() {
		var data = confTable.row($(this).parents('tr')).data();
		console.log(data);
		viewRecipetDetails(data);
	});

	var getAndSetGuestConferenceDetails = function(bookingId) {
		$('#loading').show();
		$.ajax({
			url: "./admin/getConferenceBookingDetailsByBookingId/" + bookingId + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				console.log(r);
				setTimeout(function() {
					$('#confBookId').text(r.data.bookingId);
					$('#confCity').text(r.data.cityName);
					$('#confGH').text(r.data.guestHouseName);
					$('#confName').text(r.data.name);
					$('#confMobNumber').text(r.data.mobile);
					$('#confBookDate').text(r.data.bookingDt);
					$('#confFrom').text(r.data.fromDate);
					$('#confTo').text(r.data.toDate);
					$('#confCategory').text(r.data.caetgoryType);
					$('#confCapacity').text(r.data.capacity);
					$('#confBookingType').text(r.data.confreqnceUseDesc);
					$('#confHallCategory').text(r.data.confrenceDesc);
					$('#confAddress').text(r.data.address);
					$('#confBookingAmount').text(r.data.charge);
					$('#confAdvanceAmount').text(r.data.advanceAmount);
					$('#loading').hide();
				}, 1000);
			}, error: function(jqXHR, status, error) {
				swal(error);
				$('#loading').hide();
			}
		});
	}

	var viewRecipetDetails = function(data) {
		console.log(data);
		$.ajax({
			url: './admin/getRecieptDetails/' + data.confBillId + '.do',
			type: "GET",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				$('#loading').hide();
				console.log(result);
				$('#printReceiptModal').modal('show');
				$('#printDate').text("Date:" + moment().format('DD-MM-YYYY'));
				$('#receiptDate').text(moment().format('DD-MM-YYYY'));
				$("#pBillDate").text(moment().format('DD-MM-YYYY'));
				$("#pGuestNames").html(data.name + "," + data.address);
				$("#guestHouseDetails").html(data.guestHouseName + "," + data.cityName);
				$("#pBillNo").html(data.confBillId);
				var words = inWords(data.advanceAmount);
				$("#pAmountAndWords").html(data.advanceAmount + "(" + words + ")");
				$("#cod").prop("disabled", false);
				$("#POS").prop("disabled", false);

				for (var i = 0; i < result.data.length; i++) {
					if (result.data[i].recStatus == "A") {
						$(".finalPaymentReceipt").hide();
						$('#receiptDate').text(result.data[i].receiptDate);
						$("#pBillDate").text(result.data[i].billDate);
						$("#pRectId").text(result.data[i].recId);
						if (result.data[i].modeOfpayment === 'cash') {
							$('#cashShow').show();
							$('#pCashAmount').val(result.data[i].amount);
						}
						if (result.data[i].modeOfpayment === 'pos') {
							$('#posShow').show();
							$('#pPostx').show();
							$('#pPosAmount').val(result.data[i].amount);
							$('#pPosId').val(result.data[i].pos_tr_num);
						}
						if (result.data[i].modeOfpayment === 'online') {
							$('#onlineShow').show();
							$('#pOnlineAmount').val(result.data[i].amount);
						}
					}
				}
			},
			error: function(jqXHR, status, error) {
				$('#loading').hide();
				$("#vehicleModal").modal("hide");
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	}

	$('#printBtnForModList').click(function() {
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		if (city == "") {
			swal("Select City");
			return false;
		}
		if (gHouse == "") {
			swal("Select Guest House");
			return false;
		}
		window.open('./admin/downloadModListInPDF.do?cityName=' + city + '&guesthouseName=' + gHouse, '_blank');
	});
	$("#addOrModifyModModal").on("hidden.bs.modal", function() {
		noOfRooms = 0;
		noOfBedsDormitory = 0;
	});
});// end of document.ready()

$('#roomDetails').on('keyup', '.test', function() {
	let value = $(this).val();
	$('.test').val('');
	$(this).val(value);
});

var blockIdd = null;
let noOfRooms = 0;
let noOfBedsDormitory = 0;

function restrictInput(id) {
	blockIdd = $('#blockId' + id).val();
	noOfRooms = $('#noOfRooms' + id).val();
	noOfBedsDormitory = $('#noOfBedsDormitory' + id).val();
	console.log('id' + id)
	if (id == 1) {
		console.log('inside 1')
		$('#noOfRooms2').val('')
		$('#noOfBedsDormitory2').val('');
		$('#noOfRooms3').val('')
		$('#noOfBedsDormitory2').val('');
	} if (id == 2) {
		console.log('inside 2')
		$('#noOfRooms3').val('')
		$('#noOfBedsDormitory3').val('');
		$('#noOfRooms1').val('')
		$('#noOfBedsDormitory1').val('');
	}
	if (id == 3) {
		console.log('inside 3')
		$('#noOfRooms2').val('')
		$('#noOfBedsDormitory2').val('');
		$('#noOfRooms1').val('')
		$('#noOfBedsDormitory1').val('');
	}
	console.log(blockIdd);
	console.log(noOfRooms);
	console.log(noOfBedsDormitory);
}
function convertStringToDatetime(dateString) {
	return moment(dateString, 'DD-MM-YYYY HH:mm').toDate();
}
function convertStringToDatetime1(dateString) {
	return moment(dateString, 'DD-MM-YYYY').toDate();
}
//function to calculate no of days between two dates
function daysBetween(date1, date2) {
	// The number of milliseconds in one day
	var ONE_DAY = 1000 * 60 * 60 * 24;
	// Convert both dates to milliseconds
	var date1_ms = date1.getTime();
	var date2_ms = date2.getTime();
	// Calculate the difference in milliseconds
	var difference_ms = Math.abs(date1_ms - date2_ms);
	// Convert back to days and return
	return Math.round(difference_ms / ONE_DAY);
}
function onlyNumberKey(evt) {
	var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) {
		return false;
	}
	return true;
}