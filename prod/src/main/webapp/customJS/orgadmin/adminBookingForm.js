$('document').ready(function() {
	var blockId = 0;
	var cityData = $('#cityUserlocssd').val().trim();
	var cityDetails = JSON.parse(cityData);

	$('#splitCityGH').hide();

	$("#city").html("");
	$("#city").append('<option value="">--Select City--</option>');
	$("#splitCity").append('<option value="">--Select City--</option>');
	cityDetails.forEach(option => {
		$("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
		$("#splitCity").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
	})
	let size = Object.keys(cityDetails).length;
	if (size == 1) {
		$('#city').val(cityDetails[0].gh_id);
		setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}

	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});

	$('#bookingFor').addClass('disableElement');

	$('#createDE').on('click', function() {
		$("#extension").show();
		$("#extnText").show();
		// window.location="admin/createDataEntry.html";
		if (!isCityAndGHSelected())
			return false;
		$('.cat_1').prop('disabled', true);
		$('.cat_234').prop('disabled', true);
		$('#editDetails').modal('show');
		$('#addGuestModalTitle').html("Add Guest Details");
		$('#onlyRoom').click();
		$('#dormitoryWrapper').hide();
		$('#guestTableCat4').hide();
		$('#bookingFor').val('others');
		$('#bookingFor').addClass('disableElement');
		$('#gDocEdit').show();
		$('#gDocDtlsBox').hide();
		$('#desgDocDtlsBox').hide();
		$('#suppDocDtlsBox').hide();
		$('#otherGuestDetails').show();
		$('#mobileNumber').hide();
		$('#formSubmissionBtn').html('Submit');
		$('#formSubmissionBtn').show();
		$('#category_supporting').hide();
		$('#docTypediv').hide();
		$('#supporting').hide();
		$('#guestTableCat123').show();
		$('#duringStayDiv').addClass("disableElement");
		$('#arrivalRow').addClass('disableElement');
		$('#departureRow').addClass('disableElement');
	});

	$('#cancelReservation').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		$('#cancelReservationModal').modal('show');
		getDataToCancelReservationTable();
	});

	$('#splitReservation').on('click', function() {
		if (!isCityAndGHSelected())
			return false;
		$('#splitReservationModal').modal('show');
	});

	$("#extfromDate").datetimepicker({
		useCurrent: false,
		// minDate: moment(),
		format: 'DD-MM-YYYY'
	});

	$("#extSubmitBtn").hide();

	$('#extension').on('click', function() {
		$('#extensionModal').modal('show');
	});

	$('#extensionBtn').on('click', function() {
		var reqId = $('#extReqId').val();
		var guestName = $('#extGstName').val();
		var guestDesg = $('#extDesgName').val();
		var fromDate = $('#extfromDate').val();
		if (reqId == "" && guestName == "" && guestDesg == "" && fromDate == "") {
			swal("Please provide atleast one field");
			$("#extSubmitBtn").hide();
			return false;
		}
		getGadRequestTableData();
	});

	$('#resetBtn').click(function() {
		$('#extReqId').val('');
		$('#extGstName').val('');
		$('#extDesgName').val('');
		$('#extfromDate').val('');
		extensionTable.clear().draw();
		//getGadRequestTableData();
	})

	$('#extensionReservationTable tbody').on('click', '#select', function() {
		$("#extSubmitBtn").show();
		$('#extension').prop('disabled', true);
		$('#extension').toggleClass('btn btn-secondary');
		$('#extensionModal').modal('hide');
		$('#editDetails').modal('show');
		$('#addGuestModalTitle').html("Extension CheckIn");
		$('#arrivalRow').addClass('disableElement');
		$('#departureRow').addClass('disableElement');
		var data = extensionTable.row($(this).closest('tr')).data() != undefined ? extensionTable.row($(this).closest('tr')).data() : extensionTable.row($(this)).data();
		blockId = data.blockId;
		setExtensionGuestDetails(data.bookingId, true)
	});

	$('#extSubmitBtn').on('click', function() {
		var frmDt = $('#frmDt').val();
		var toDt = $('#toDt').val();
		var noOfDays = $('#noOfDays').val();
		if (toDt == '') {
			swal('To Date can not be blank');
			return false;
		}
		var g1 = convertStringToDatetime(frmDt);
		var g2 = convertStringToDatetime(toDt);
		if (g1.getTime() >= g2.getTime()) {
			swal('To Date must be grater than from date.');
			return false;
		}
		postData = {
			reqId: bookingReqId,
			toDate: g2,
			noOfDays: noOfDays
		}
		$.ajax({
			url: "./admin/updateBookingExtension.do",
			type: "POST",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType: "application/json",
			dataType: "JSON",
			success: function(result) {
				if (result.success) {
					$('#editDetails').modal('hide');
					swal(result.msg);
					table.ajax.reload()
				} else {
					swal(result.msg);
				}
			}
		});
	});

	var setExtensionGuestDetails = function(bookingId, isReadonly) {
		console.log(bookingId);
		$('#mobileNumber').show();

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
				blockId = r.data.blockId;
				if (r.status) {
					viewExtensionGuestDetails(r, otherDetails, isReadonly);
				} else {
					swal(r.msg);
				}
			}, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
		});
	}

	function viewExtensionGuestDetails(r, otherDetails, isReadonly) {
		bookingReqId = r.data.bookingId;
		guestList = r.data.list;
		var id = r.data.catg;
		$('#catg').val(id);
		$('#catg').trigger('change');
		getDesignationListDtls(id, r.data.desg);
		getPurposeListDtls(id, r.data.purp);
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

			console.log(item);
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
		$("#frmDt").val(r.data.toDt);
		$("#toDt").val('');

		console.log('frmDt...' + r.data.frmDt)
		$("#toDt").removeClass('disableElement');
		$('#toDt').data("DateTimePicker").minDate(moment(r.data.toDt, 'DD-MM-YYYY').add(1, 'days'));
		$("#bookingFor").addClass('disableElement');

		for (var i = 0; i < r.data.list.length; i++) {
			guestCount++;
			addGuestDetails(guestCount, r.data.list[i], isReadonly);
		}
		if (r.data.suppDocName != null) {
			var fileArr = r.data.suppDocName.split(/(\\|\/)/);
			suppdocname = fileArr[fileArr.length - 1];
		}
		if (r.data.catgDocName != null) {
			var fileArr = r.data.catgDocName.split(/(\\|\/)/);
			catgdocname = fileArr[fileArr.length - 1];
		}
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
			$("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls, #gDocDtls").show();
			$("#bookingTypeDtls, #guestMangCtrl,#formSubmissionBtn, #suppDocEdit,#desgDocEdit, #gDocEdit").hide();
			$("#bookingTypeShowDtls").html(r.data.bookingType == 'room' ? 'Book Room' : 'Book Bed');
			if (!r.data.isArrivalConv) {
				$("#convFrDtls").hide()
			}
			if (!r.data.isDepartueConv) {
				$("#convToDtls").hide()
			}
			if (suppdocname != null) {
				$("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + suppdocname + '</a>');
			}
			if (catgdocname != null) {
				$("#desgDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + catgdocname + '</a>');
			}
			if (gdocname != null) {
				$("#gDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + gdocname + '</a>');
			}
		} else {
			$("#bookingTypeDtls,#guestMangCtrl,#formSubmissionBtn,#suppDocEdit,#desgDocEdit,#gDocEdit").show();
			$("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls,#gDocDtls").hide();
			$("#bookingTypeShowDtls,#suppDocDtls,#desgDocDtls,#gDocDtls").html("");
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
			}
			if (r.data.departueConv === true) {
				$('#departureRow').removeClass("disableElement");
			}
		}
	}

	var extensionTable = $("#extensionReservationTable").DataTable({
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
			{ "width": "5%", "targets": 2, "data": "name" },
			{ "width": "10%", "targets": 3, "data": "bookingDt" },
			{ "width": "10%", "targets": 4, "data": "fromDate" },
			{ "width": "10%", "targets": 5, "data": "toDate" },
			{ "width": "10%", "targets": 6, "data": "desg" },
			{ "width": "10%", "targets": 7, "data": "mobile" },
			{
				"width": "10%", "targets": 8, "data": null,
				render: function(data, type, row, meta) {
					return '<button type="button" id="select" title="Select" class="btn btn-edit btn-sm" action="select" ><i class="fa fa-paper-plane" aria-hidden="true"></i></button> '
				}
			}
		],
	});

	var getGadRequestTableData = function() {
		var reqId = $('#extReqId').val();
		var guestName = $('#extGstName').val();
		var guestDesg = $('#extDesgName').val();
		var fromDate = $('#extfromDate').val();
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var postData = {
			cId: city,
			gId: gHouse,
			bookingId: reqId,
			name: guestName,
			desg: guestDesg,
			fromDate: fromDate
		}
		$.ajax({
			url: './admin/getGadRequestTableDataForExtension.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result);
				extensionTable.clear();
				extensionTable.rows.add(result.data).draw();
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

var table = $("#dataEntryDataTable").DataTable({
	"paging": true,
	"searching": false,
	"scrollX": true,
	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
	"processing": false,
	"ordering": true,
	"info": false,
	"bFilter": false,
	"bLengthChange": false,
	"fixedHeader": {
		header: true,
		footer: true
    },
    "scrollCollapse": true,
    "serverSide": true,
	"autoWidth": true, 
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
            d.category = $('#filterCategory').val();
            d.type = "AL";
        },"error": function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
    },
    "columnDefs": [
        {
			 "targets": 0, "data": null,
			render: function(data, type, row, meta) {
				return meta.row + meta.settings._iDisplayStart + 1;
			}
		},
        { "targets": 1, "data": "bookingId" },
		{ "targets": 2, "data": "name" },
		{ "targets": 3, "data": "bookingDt" },
		{ "targets": 4, "data": "fromDate" },
		{ "targets": 5, "data": "toDate" },
		{ "targets": 6, "data": "catgId" },
		{ "targets": 7, "data": "purpose" },
		{ "targets": 8, "data": "mobile" },
		{ "targets": 9, "data": "typeOfRoom" },
		{ "targets": 10, "data": "statusDesc" },
        {
			
            "targets": 11,
            "data": null,
            render: function(data, type, row, meta) {
				let str = '<button type="button" id="allot-view" title="View Allotment Details" class="btn btn-view btn-sm mr-1"><i class="fa fa-eye" aria-hidden="true"></i></button> '
				if (data.status == "REQ") {
						str += '<button type="button" id="allot-edit" title="Edit Allotment Details" class="btn btn-edit btn-sm mr-1"><i class="fa fa-edit" aria-hidden="true"></i></button> ' +
							'<button type="button" id="delete" title="Delete" class="btn btn-dlt btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>'
				}
				return str;                
            }
        }
    ]
});


	var canTable = $("#cancelReservationTable").DataTable({
		"paging": true,
		"searching": true,
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
					var btn = '<button type="button" id="cancel" title="Cancel" class="btn btn-sm btn-cancel" action="cancel" ><i class="fa fa-times" aria-hidden="true"></i></button> ';
					if (data.status == 'PRO') {
						let date1 = convertStringToDatetime(data.fromDate);
						let date2 = convertStringToDatetime(data.toDate);
						let days = daysBetween(date1, date2);
						if (days != 1) {
							btn += '<button type="button" id="partialCancel" title="Partial Cancel" class="btn btn-sm btn-cancel-partial" action="partialCancel"><i class="fa fa-times" aria-hidden="true"></i></button>';
						}
						return btn;

					} else {
						return '<button type="button" id="partialCancel" title="Partial Cancel" class="btn btn-sm btn-cancel-partial" action="partialCancel" ><i class="fa fa-times" aria-hidden="true"></i></button> '
					}
				}
			}
		],
	});

	$('#dataEntryDataTable tbody').on('click', '#allot-edit', function() {
		$("#extension").hide();
		$("#extnText").hide();
		$('#addGuestModalTitle').html("Edit Guest Details");
		$('#formSubmissionBtn').html('Update Details');
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		blockId = data.blockId;
		console.log(data);
		setGuestDetails(data.bookingId, false);
		$('#editDetails').modal('show');
		$('#arrivalRow').addClass('disableElement');
		$('#departureRow').addClass('disableElement');
	});

	$('#dataEntryDataTable tbody').on('click', '#allot-view', function() {
		$("#extension").hide();
		$("#extnText").hide();
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		console.log(data);
		blockId = data.blockId;
		// getAndSetGuestRoomDetails(data.bookingId);
		setGuestDetails(data.bookingId, true)
		$("#addGuestModalTitle").html("VIEW DETAILS");
		$('#editDetails').modal('show');
	});

	$('#dataEntryDataTable tbody').on('click', '#delete', function() {
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		swal({
			title: "Are you sure? ",
			text: "Are you sure to delete the booking request!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, delete it!",
			closeOnConfirm: false
		},
			function() {
				$('#loading').show();
				$.ajax({
					url: './admin/cancelRequest/' + data.bookingId + '/' + data.mobile + '.do',
					type: "GET",
					headers: { "X-CSRF-TOKEN": token },
					success: function(data) {
						$('#loading').hide();
						if (data.success) {
							swal(data.msg);
							table.ajax.reload()
						} else {
							swal(data.msg);
						}
					}
				});
			});
	});

	$('#cancelReservationTable').on('click', 'tbody #cancel', function() {
		var data = canTable.row($(this).closest('tr')).data() != undefined ? canTable.row($(this).closest('tr')).data() : canTable.row($(this)).data();
		console.log(data);
		/*       $('#cFromDate').val(moment().format('DD-MM-YYYY 09:00'));*/

		var formattedDate = moment(data.fromDate, 'DD-MM-YYYY').format('DD-MM-YYYY 09:00');
		$('#cFromDate').val(formattedDate);
        /*$('#cFromDate').data("DateTimePicker").minDate(moment(data.fromDate, 'DD-MM-YYYY'));
        $('#cFromDate').data("DateTimePicker").maxDate(moment(data.toDate, 'DD-MM-YYYY').subtract(1, 'days'));*/
		$("#cFromDate").datetimepicker({
			useCurrent: false,
			format: 'DD-MM-YYYY 09:00',
			minDate: moment(data.fromDate, 'DD-MM-YYYY'),
			maxDate: moment(data.toDate, 'DD-MM-YYYY').subtract(1, 'days')
		});
		$('#actualFrDt').val(data.fromDate);
		$('#cToDate').val(data.toDate);
		$('#cancelModal').modal('show');
		let date1 = convertStringToDatetime($('#cFromDate').val());
		let date2 = convertStringToDatetime(data.toDate);
		let days = daysBetween(date1, date2);
		$('#cTotalDays').val(days);
		$('#cBookingId').val(data.bookingId);
		$('#cMobileNo').val(data.mobile);
		$('#cName').val(data.name);
	});



	$('#cFromDate').on('dp.change', function(e) {
		let date1 = convertStringToDatetime($('#cFromDate').val());
		let date2 = convertStringToDatetime($('#cToDate').val());
		let days = daysBetween(date1, date2);
		$('#cTotalDays').val(days);
	});

	$('#cancelBtn').on('click', function() {
		let fromDate = $('#cFromDate').val();
		let bookingId = $('#cBookingId').val();
		let toDate = $('#cToDate').val();
		let frDt = moment(fromDate, 'DD-MM-YYYY').format('DD-MM-YYYY');
		let actualFrDt = moment($('#actualFrDt').val(), 'DD-MM-YYYY').format('DD-MM-YYYY');
		let postData = {
			bookingId: bookingId,
			fromDate: fromDate,
			toDate: toDate
		}
		let cancelMessage = "Cancellation after from date will result in non occupancy charges, Are you sure to cancel!";
		if (frDt <= actualFrDt) {
			postData.twentyFourCancel = 1;
			cancelMessage = "Are you sure to cancel!";
		} else {
			postData.twentyFourCancel = 0;
		}
		swal({
			title: "Are you sure? ",
			text: cancelMessage,
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, cancel it!",
			closeOnConfirm: false
		},
			function() {
				$('#loading').show();
				$.ajax({
					url: './admin/cancelRequest1.do',
					type: "POST",
					data: JSON.stringify(postData),
					contentType: "application/json",
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token);
						$('#loading').show();
					},
					success: function(result) {
						$('#loading').hide();
						if (result.success) {
							swal(result.msg);
							$('#cancelModal').modal('hide');
							getDataToCancelReservationTable();
							table.ajax.reload()
						} else {
							swal(result.msg);
						}
					},error: function (jqXHR, status, error) {
		                if (jqXHR.status == 400) {
		                    swal(jqXHR.responseJSON.msg);
		                } else if (jqXHR.status == 404) {
		                    swal('Requested page not found. [404]');
		                }
		                $('#loading').hide();
		            }
				});
			});
	});



	$('#partialFromDate').on('dp.change', function(e) {
		let date1 = convertStringToDatetime($('#partialFromDate').val());
		let date2 = convertStringToDatetime($('#partialToDate').val());
		let days = daysBetween(date1, date2);
		$('#partialTotalDays').val(days);
	});

	$('#cancelReservationTable').on('click', 'tbody #partialCancel', function() {
		var data = canTable.row($(this).closest('tr')).data() != undefined ? canTable.row($(this).closest('tr')).data() : canTable.row($(this)).data();
		console.log(data);
		var newDate = moment(data.fromDate, 'DD-MM-YYYY').add(1, 'days');
		var formattedDate = newDate.format('DD-MM-YYYY 9:00');
		$('#partialFromDate').val(formattedDate);
		/* $('#partialFromDate').data("DateTimePicker").minDate(moment(data.fromDate, 'DD-MM-YYYY').add(1, 'days'));
		 $('#partialFromDate').data("DateTimePicker").maxDate(moment(data.toDate, 'DD-MM-YYYY').subtract(1, 'days'));
		  $('#partialFromDate').datetimepicker({
			 useCurrent: false,
			 format: 'DD-MM-YYYY 09:00',
			 minDate:moment(data.fromDate, 'DD-MM-YYYY').add(1, 'days'),
			 maxDate:moment(data.toDate, 'DD-MM-YYYY').subtract(1, 'days')
		 });*/
		var fromDate = moment(data.fromDate, 'DD-MM-YYYY').add(1, 'days');
		var toDate = moment(data.toDate, 'DD-MM-YYYY').subtract(1, 'days');
		if (fromDate.isSame(toDate, 'day')) {
			$('#partialFromDate').datetimepicker({
				useCurrent: false,
				format: 'DD-MM-YYYY 09:00',
				minDate: fromDate,
				maxDate: fromDate
			});
		} else {
			$('#partialFromDate').datetimepicker({
				useCurrent: false,
				format: 'DD-MM-YYYY 09:00',
				minDate: fromDate,
				maxDate: toDate
			});
		}

		$('#partialToDate').val(data.toDate);
		$('#partialCancelModal').modal('show');
		let date1 = convertStringToDatetime($('#partialFromDate').val());
		let date2 = convertStringToDatetime(data.toDate);
		let days = daysBetween(date1, date2);
		$('#partialTotalDays').val(days);
		$('#partialBookingId').val(data.bookingId);
		$('#partialMobileNo').val(data.mobile);
		$('#partialName').val(data.name);
	});
	$('#partialCancelModal').on('hidden.bs.modal', function() {
		$('#partialFromDate').data("DateTimePicker").destroy();
	});
	$('#cancelModal').on('hidden.bs.modal', function() {
		$('#cFromDate').data("DateTimePicker").destroy();
	});
	$('#partialCancelBtn').click(function() {
		let bookingId = $('#partialBookingId').val();
		let fromDate = $('#partialFromDate').val();
		let toDate = $('#partialToDate').val();

		let postData = {
			bookingId: bookingId,
			fromDate: fromDate,
			toDate: toDate
		}
		$.ajax({
			url: "./admin/partialCancel.do",
			type: "POST",
			data: JSON.stringify(postData),
			contentType: "application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
				$('#loading').show();
			},
			success: function(result) {
				$('#loading').hide();
				if (result.success) {
					swal(result.msg);
					$('#partialCancelModal').modal('hide');
					getDataToCancelReservationTable();
				} else {
					swal(result.msg);
				}
			},
			error: function(e) {
				$('#loading').hide();
				console.log("ERROR: ", e);
				swal("Error!", "Something went wrong", "error");
			}
		});
	});

	function populateSplitReservationModal(city, gHouse, nameForSplit, bookingIdForSplit) {
		console.log("city  " + city + " gHouse " + gHouse + " nameForSplit " + nameForSplit + " bookingIdForSplit " + bookingIdForSplit);


		console.log("city (after assign) " + city + " gHouse " + gHouse + " nameForSplit " + nameForSplit + " bookingIdForSplit " + bookingIdForSplit);

		$.ajax({
			url: './admin/splitView.do?city=' + city + '&gHouse=' + gHouse + '&nameForSplit=' + nameForSplit + '&bookingIdForSplit=' + bookingIdForSplit,
			type: "GET",
			headers: { "X-CSRF-TOKEN": token },
			success: function(data) {
				$('#loading').hide();
				if (data.status) {
					console.log(data.roomBookingList);
					let str = '';
					$("#splitTableBody").empty();
					data.roomBookingList.forEach(function(item, i) {
						str += '<tr> ' +
							'<td class="text-center">' + (i + 1) + '</td> ' +
							'<td class="text-center">' + item.bookingId + '</td> ' +
							'<td class="text-center">' + item.frmDt + '</td> ' +
							'<td class="text-center">' + item.toDt + '</td> ' +
							'<td class="text-center"> ' +
							'<table width="100%"> ';
						for (let guest of item.list) {
							str += '<tr> ' +
								'<td class="text-center">' + guest.guestName + '</td> ' +
								/* '<td class="text-center">' + guest.guestAddr + '</td> ' +*/
								'<td class="text-center"><input type="checkbox" class="splitCheckBox" ' +
								'value="' + guest.guestId + '"></td> ' +
								'</tr> ';
						}
						str += '</table> ' +
							'</td> ' +
							'<td class="text-center"> ' +
							'<button type="button" class="btn btn-primary splitBtn"> ' +
							'<i class="fa fa-scissors"></i> Split ' +
							'</button> ' +
							'</td> ' +
							'</tr> ';
					});
					$("#splitTableBody").append(str);
				}
			},error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
		});
	}

	var cityForSplit = null;
	var gHouseForSplit = null;

	$('#splitReservationModal').on('show.bs.modal', function(event) {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		cityForSplit = city;
		gHouseForSplit = gHouse;
		console.log("inside modal closed");
		populateSplitReservationModal(city, gHouse);
	});

	$('#filterSearchForSplit').click(function() {
		var nameForSplit = $('#userNameForSplit').val();
		var bookingIdForSplit = $('#filterBookingIdForSplit').val();
		console.log("city  " + city + " gHouse " + gHouse + " nameForSplit " + nameForSplit + " bookingIdForSplit " + bookingIdForSplit);
		if (nameForSplit == 'undefined' && bookingIdForSplit == 'undefined') {
			swal("Please enter one of the data to search");
			return false;
		}
		$("#splitTableBody").html("");
		populateSplitReservationModal(cityForSplit, gHouseForSplit, nameForSplit, bookingIdForSplit);
		$('#filter-popover').unbind();
		setTimeout(function() {
			$('#filter-popover').bind('click', preventClick);
		}, 1000);
	});


	let selected = "";

	$("#splitReservationTable tbody").on("change", ".splitCheckBox", function() {
		selected = $(".splitCheckBox:checked").map(function() {
			return this.value
		}).get().join("', '");
		console.log(selected);
	});

	$("#splitReservationTable tbody").on("click", ".splitBtn", function() {
		if (selected == "") {
			swal("Please select guests to split");
			return false;
		}
		$('#splitReservationModal').modal("hide");
		let bookingId = $(this).closest("tr").find("td").eq(1).html();
		selected = "'" + selected + "'";
		let data = {
			"bookingId": bookingId,
			"guestList": selected
		}
		$.ajax({
			url: "./admin/getSplitBookingDetails.do",
			type: "GET",
			contentType: "application/json",
			data: data,
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			}, success: function(r, textStatus, jqXHR) {
				$('#loading').hide();
				$('#editDetails').modal('show');
				$('#arrivalRow').addClass('disableElement');
				$('#departureRow').addClass('disableElement');
				$("#splitCityGH").show();
				$('#formSubmissionBtn').html('Save');
				let formData = $('#existingBookingApplication').serializeArray();
				let otherDetails = formData.filter(d => !d.name.match(/guest/g));
				if (r.status) {
					getGuestHouseListDtlsForSplit(r.data.cityId);
					setTimeout(function() {
						$("#splitCity").val(r.data.cityId);
						$("#splitGHouse").val(r.data.blockId);
					}, 1000);
					editOrViewGuestDetails(r, otherDetails, false);
					$("#noOfAdults").addClass("disableElement");
					$("#noOfChildren").addClass("disableElement");
					$("#frmDt").addClass("disableElement");
					$("#toDt").addClass("disableElement");
					$("#noOfDays").addClass("disableElement");
				}
			}, error: function(jqXHR, status, error) {
				$('#loading').hide();
				swal(error);
			}
		});
	});

	$("#splitReservationModal").on("hidden.bs.modal", function() {
		$("#splitTableBody").html("");
	});

	$("#filterBookingDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
	});

	$('#filterPurpose').append(
		"<option value='1'>Official</option>" +
		"<option value='2'>Private</option>" +
		"<option value='3'>Patient</option>" +
		"<option value='4'>Student</option>"
	);

	$('#filterCategory').append(
		"<option value='C1'>Category1</option>" +
		"<option value='C2'>Category2</option>" +
		"<option value='C3'>Category3</option>" +
		"<option value='C4'>Category4</option>"
	);

	$('#filterBookingId').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});

	$('#filterBookingIdForSplit').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});

	$('#filterSearch').click(function() {
		table.ajax.reload()
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
		$('#filterCategory').val('');
		$('#filterPurpose').val('');
		$('#filterMobNum').val('');
		$('#filterGuestName').val('');
		$('#filterstatus').val('');
		table.ajax.reload()
	});

	$('#resetFiterBtnForSplit').click(function() {
		$('#filterBookingIdForSplit').val('');
		$('#userNameForSplit').val('');
		populateSplitReservationModal(cityForSplit, gHouseForSplit);
	});

	/*var getDataToGadRequestTable = function() {
		let bookingId = $('#filterBookingId').val();
		let bookingDate = $('#filterBookingDate').val();
		let purpose = $('#filterPurpose').val();
		let mobNo = $('#filterMobNum').val();
		let name = $('#userName').val();
		let category = $('#filterCategory').val();
		let cityId = $('#city').val();
		let ghId = $('#gHouse').val();
		console.log("name value userName " + name);
		let postData = {
			bookingId: bookingId,
			bookingDate: bookingDate,
			category: category,
			purpose: purpose,
			mobNo: mobNo,
			name: name,
			reqType: 'AL',
			cId: cityId,
			gId: ghId
		};

		$.ajax({
			url: './admin/getAllotmentLstDtlsAccToReq.do',
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
				$('#loading').show()
			},
			success: function(result) {
				$('#loading').hide()
				console.log(result);
				table.clear();
				table.rows.add(result.data).draw();
			},
			error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}*/

	var getDataToCancelReservationTable = function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		// var bookingId = $('#filterBookingId').val();
		// var date = $('#filterBookingDate').val();
		var postData = {
			cId: city,
			gId: gHouse,
			// reqType: 'AL',
			// bookingId: bookingId,
			// bookingDate: date
		}
		$.ajax({
			url: './admin/getCancellationLstDtlsAccToReq.do',
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

	var getGuestHouseListDtlsForSplit = function(id) {
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
				$("#splitGHouse").html("");
				$("#splitGHouse").append('<option value="">---Select---</option>');
				data.forEach(option => {
					$("#splitGHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
				})
				let size = Object.keys(data).length;
				if (size == 1) {
					blockId = (data[0].id)
					$('#splitGHouse').val(blockId);
				}
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}

	

	$('#city').on('change', function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
	});

	$('#splitCity').on('change', function() {
		var id = $('#splitCity').val();
		let catg = $('#catg').val();
		getGuestHouseListDtlsForSplit(id);
		// setTimeout(function () {
		//     getPurposeListDtls(catg, '');
		// }, 500);
	});

	/*
		$('#splitGHouse').on('change', function () {
			let catg = $('#catg').val();
			blockId = $(this).val();
			getPurposeListDtls(catg, '');
		});
	*/

	$("#gHouse").change(function() {
		blockId = $(this).val();
		table.ajax.reload()
	});

    /*
	 * ---------------------------- Copied from viewapplication.js
	 * --------------------------
	 */

	$('#conReq').hide();
	$('#chec').show();


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
		$('#roomWrapper').show();
		$('#dormintoryWrapper').hide();
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
		$('#roomWrapper').show();
		$('#dormintoryWrapper').hide();
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
		$('#duringStayDiv').show();
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
		minDate: moment().millisecond(0).second(0).minute(0).hour(0),
		format: 'DD-MM-YYYY 09:00'
	});

	$("#extToDt").datetimepicker({
		useCurrent: false,
		minDate: moment().millisecond(0).second(0).minute(0).hour(0),
		format: 'DD-MM-YYYY 08:00'
	});

	$("#depTm, #arrTm").datetimepicker({
		format: 'hh:mm A'
	});
	$("#frmDt").on("change", function() {
		$('#toDt').prop("disabled", false);
	});
	$("#frmDt").on("dp.change", function(e) {
		$("#toDt").val("");
		$('#toDt').prop("disabled", false);
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
		$('#toDt').data("DateTimePicker").minDate(e.date.add(1, 'days').millisecond(0).second(0).minute(0).hour(0));

		// $("#arrDt").data("DateTimePicker").minDate(e.date);
		// $("#depDt").data("DateTimePicker").minDate(e.date);
		if ($('#toDt').val() != '') {
			var date1 = convertStringToDatetime($('#frmDt').val());
			var date2 = convertStringToDatetime($('#toDt').val());
			var days = daysBetween(date1, date2);
			$('#noOfDays').val(days);
		}
	});
	$("#toDt").on("dp.change", function(e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'toDt');
		//        $("#frmDt").data("DateTimePicker").maxDate(e.date);
		//         $("#arrDt").data("DateTimePicker").maxDate(e.date);
		//         $("#depDt").data("DateTimePicker").maxDate(e.date);
		if ($('#frmDt').val() != '') {
			var date1 = convertStringToDatetime($('#frmDt').val());
			var date2 = convertStringToDatetime($('#toDt').val());
			var days = daysBetween(date1, date2);
			$('#noOfDays').val(days);
		}
	});

	$("#arrDt").on("dp.change", function(e) {
		//        $('#existingBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
		//        $("#depDt").data("DateTimePicker").minDate(e.date);
	});
	$("#depDt").on("dp.change", function(e) {
		//        $('#existingBookingApplication').bootstrapValidator('revalidateField', 'depDt');
		//        $("#arrDt").data("DateTimePicker").maxDate(e.date);
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

	var guestParameters = 7;
	var bookingReqId = null;
	var guestCount = 0;
	var guestList;
	var suppDoc = null;
	var catgDoc = null;
	var gDoc = null;
	var suppdocname = null;
	var catgdocname = null;
	var gdocname = null;

	// var token = $("meta[name='_csrf']").attr("content");

	// $("input[name='bookingType']").change(function() {
	// if($(this).val() == "room"){
	// $('#roomWrapper').show();
	// $('#dormintoryWrapper').hide();
	// } else {
	// $('#roomWrapper').hide();
	// $('#dormintoryWrapper').show();
	// }
	// });

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

	// $('#catg').on('change', function() {
	// var id = $('#catg').val();
	// getDesignationListDtls(id,'');
	// getPurposeListDtls(id);
	// });

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
			$('#purposeDocLabel').html('Upload Official Document (jpg/png/pdf)<small class="small">*</small>');
			$('#purposeDocBoxLabel').html('Upload Official Document (jpg/png/pdf)<small class="small">*</small>');
		} else if (purpose == '3' || purpose == '4') {
			$('#supporting').show();
			if (purpose == '3') {
				$('#purposeDocLabel').html('Upload Patient Document (jpg/png/pdf)<small class="small">*</small>');
				$('#purposeDocBoxLabel').html('Upload Patient Document (jpg/png/pdf)<small class="small">*</small>');
			} else {
				$('#purposeDocLabel').html('Upload Student Document (jpg/png/pdf)<small class="small">*</small>');
				$('#purposeDocBoxLabel').html('Upload Student Document (jpg/png/pdf)<small class="small">*</small>');
			}
		} else {
			$('#supporting').hide();
		}
	});

	var getDesignationListDtls = function(id, value) {
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

	var getPurposeListDtls = function(id, value) {
		console.log(blockId);
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

	getCatgoryListDtls('');
	// getPurposeListDtls();

	var convertingGuesetDetailsObjectToArrayOfObjects = function(data) {
		var array = [];
		var count = data.length / guestParameters;
		console.log(data.length);
		for (var i = 0; i < count; i++) {
			array.push(convertSerializeArrayToObject(data.slice((i * guestParameters), ((i + 1) * guestParameters))));
		}
		return array;
	}

	var addGuestDetails = function(count, data, readonly) {
		let category = $('#catg').val();
		console.log("category  " + category);
		if (category == 'C1' || category == 'C2' || category == 'C3') {
			// $('#guestDetailsWrapper').html('');
			$('#guestDetailsWrapper').append(
				'<tr id=tr' + count + '> ' +
				'<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
				'<td class="text-center text-nowrap">' +
				'<input type="hidden" name="guestId" id="guestId' + count + '">' +
				'<input type="hidden" name="guestSlNo" value="' + count + '">' +
				'<input type="text" placeholder="Name" id="guestName' + count + '" name="guestName" autocomplete="off" onkeypress="toCapitaLiaze(event)">' +
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
                /*'<td class="text-center text-nowrap">' +
                '<input type="text" placeholder="Address" name="guestAddr" id="guestAddr' + count + '">' +
                '</td> '*/ +
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
				'<input type="text" placeholder="Name" id="guestName' + count + '" name="guestName" autocomplete="off" onkeypress="toCapitaLiaze(event)">' +
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
				// '<td class="text-center text-nowrap">' +
				/* '<input type="text" placeholder="Address" name="guestAddr" id="guestAddr' + count + '">' +*/
				// '</td> ' +
				'</tr> '
			);
		}
		if (data != undefined) {
			$("#guestId" + count).val(data.guestId);
			$("#guestName" + count).val(data.guestName);
			$("#guestAge" + count).val(data.guestAge);
			$("#guestGender" + count).val(data.guestGender);
			// $("#guestAddr" + count).val(data.guestAddr);
			$("#guestDesignation" + count).val(data.guestDesignation);
			$("#guestDepartment" + count).val(data.guestDepartment);
		}
		if (readonly != undefined ? readonly : false) {
			$("#guestId" + count).addClass('disableElement');
			$("#guestName" + count).addClass('disableElement');
			$("#guestAge" + count).addClass('disableElement');
			$("#guestGender" + count).addClass('disableElement');
			//$("#guestAddr" + count).addClass('disableElement');
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
		$('#guestDetailsWrapper').html('');
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
		$('#guestDetailsWrapper').html('');
		$('#guestDetailsWrapper1').html('');
		$('#noOfAdults').val('');
		$('#noOfChildren').val('');
		if (bookingFor == "self") {
			$('#noOfAdults').val(1);
			$('#noOfAdults').trigger('blur');
		}
	});

	var setGuestDetails = function(bookingId, isReadonly) {
		console.log(bookingId);
		$('#mobileNumber').show();

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
				blockId = r.data.blockId;
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
		console.log("r :" + r);
		console.log("otherDetails: " + otherDetails);


		var ext = r.data.ext;
		console.log("ext :" + ext);



		blockId = r.data.blockId;
		bookingReqId = r.data.bookingId;
		guestList = r.data.list;
		var id = r.data.catg;
		$('#catg').val(id);
		$('#catg').trigger('change');
		getDesignationListDtls(id, r.data.desg);
		getPurposeListDtls(id, r.data.purp);
		setTimeout(function() {
			$('#purp').trigger('change');
		}, 1000);
		$('#noOfDays').val(r.data.noOfDays);
		var type = r.data.roomType;
		$('#roomTyp').val(type);
		// var valu = '';
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
			// if(item.name == 'purp'){
			// $('#purp').val(r.data[item.name]);
			// // var purpose = $('#purp').val();
			// if(purpose == '1'){
			// $('#office').show();
			// $('#supporting').show();
			// }else if(purpose == '3' || purpose == '4'){
			// $('#office').hide();
			// $('#supporting').show();
			// }else{
			// $('#office').show();
			// $('#supporting').hide();
			// }
			// }
			// if(item.name == 'catg'){
			// var category = $('#catg').val();
			// if(category == 'C1'||category == 'C2'||category == 'C3'){
			// $('#docTypediv').show();
			// $('#category_supporting').show();
			// }else{
			// $('#docTypediv').hide();
			// $('#category_supporting').hide();
			// }
			//
			// }
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
		$('#bookingFor').addClass('disableElement');

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

		if (bookingReqId.charAt(0) === 'G') {
			$('#mobileNumber').hide();
		} else {
			$('#mobileNumber').show();
		}
		for (var i = 0; i < r.data.list.length; i++) {
			guestCount++;
			addGuestDetails(guestCount, r.data.list[i], isReadonly);
		}
		if (r.data.suppDocName != null) {
			var fileArr = r.data.suppDocName.split(/(\\|\/)/);
			suppdocname = fileArr[fileArr.length - 1];
		}
		if (r.data.catgDocName != null) {
			var fileArr = r.data.catgDocName.split(/(\\|\/)/);
			catgdocname = fileArr[fileArr.length - 1];
		}
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
			$("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls, #gDocDtls").show();
			$("#bookingTypeDtls, #guestMangCtrl, #formSubmissionBtn, #suppDocEdit,#desgDocEdit, #gDocEdit").hide();
			$("#bookingTypeShowDtls").html(r.data.bookingType == 'room' ? 'Book Room' : 'Book Bed');
			if (!r.data.isArrivalConv) {
				$("#convFrDtls").hide();
			}
			if (!r.data.isDepartueConv) {
				$("#convToDtls").hide();
			}
			if (suppdocname != null) {
				$("#suppDocDtlsBox").show();
				$("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + suppdocname + '</a>');
			}
			if (catgdocname != null) {
				$("#desgDocDtlsBox").show();
				$("#desgDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + catgdocname + '</a>');
			}
			if (gdocname != null) {
				$("#gDocDtlsBox").show();
				$("#gDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + gdocname + '</a>');
			}
			if (r.data.arrivalConv === true) {
				$('#arrivalRow').removeClass("disableElement");
				$('#arrTravel').prop('checked', true);
			}
			if (r.data.departueConv === true) {
				$('#departureRow').removeClass("disableElement");
				$('#deptTravel').prop('checked', true);
			}
		} else {
			$("#bookingTypeDtls,#guestMangCtrl,#formSubmissionBtn,#suppDocEdit,#desgDocEdit,#gDocEdit").show();
			$("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls,#gDocDtls, #suppDocDtlsBox, #desgDocDtlsBox, #gDocDtlsBox").hide();
			$("#bookingTypeShowDtls,#suppDocDtls,#desgDocDtls,#gDocDtls").html("");
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
	}

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

	$("#editDetails").on("hide.bs.modal", function() {
		$(this).find('form').trigger('reset');
		$('#existingBookingApplication').bootstrapValidator('resetForm', true);
		$('#editDetails').find('*').removeClass("disableElement");
		$('#guestDetailsWrapper').html('');
		$('#guestDetailsWrapper1').html('');
		guestCount = 0;
		$("#splitCityGH").hide();
		$("#extSubmitBtn").hide();
	});

	var desAccToCatTable = $("#guestCatDataTable").removeAttr('width').DataTable({
		"paging": true,
		"searching": true,
		"responsive": true,
		"lengthMenu": [[100, 200, 300, -1], [100, 200, 300, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
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
					return '<button type="button" id="cateType" title="Select Category" action="cateType" class="btn btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button>'
				}
			}
		],
	});

	$("#myModalViewForCategory").on("hidden.bs.modal", function() {
		$(this).find('form').trigger('reset');
	});

	$("#myModalView").on("hidden.bs.modal", function() {
		$(this).find('form').trigger('reset');
	});

	$('#guestCatDataTable').on('click', 'tbody #cateType', function() {
		$('#myModalViewForCategory').modal('hide');
		var data_row = desAccToCatTable.row($(this).closest('tr')).data();
		getDesgAccToCat(data_row.desc);
		getDesignationListDtls(('C' + data_row.desc), data_row.id);
		getPurposeListDtls(('C' + data_row.desc), '');
		// getGuestDesignationDetails(data_row.id);
	});

	var viewDesg;
	$('#viewCate').click(function() {
		viewDesg = "All";
		var cat = $('#catg').val();
		console.log("value of the category" + cat);
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
				/*table.columns.adjust().responsive.recalc();*/
			}
		});
	};

	/*   $('#catg').change(function(){
		   viewDesg = $(this).val();
		   console.log(viewDesg);
		   getDesgAccToCat(viewDesg);
		   if(viewDesg !=""){
		   $('#myModalViewForCategory').modal('show');
		   }
		  
	   });*/

    /*
	 * -------------------------- Copied from createdataentry.js
	 * ------------------------------
	 */
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

		console.log('form valid');
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
		console.log(otherDetails);
		var fd = new FormData();
		fd = convertSerializeArrayToFd(otherDetails);
		fd.append("guestDtlsStr", JSON.stringify(guestDtls));
		fd.append("blockId", $('#gHouse').val());
		fd.append("bookingId", bookingReqId);
		fd.append("noOfDays", $('#noOfDays').val());
		if ($('#mob').val() == "") {
			fd.delete('mob');
			fd.append("mob", $('#gMobile').val());
		}
		let purpose = $('#purp').val();
		let category = $('#catg').val();
		let bookingFor = $('#bookingFor').val();
		if (purpose != "" && purpose != "2") {
			if (suppDoc == null) {
				if (suppdocname == null) {
					swal("Please provide purpose document");
					return false;
				}
			} else {
				fd.append("suppDoc", suppDoc, suppDoc.name);
			}
		}
		if (category != "" && category != "C4") {
			if (catgDoc == null) {
				if (catgdocname == null) {
					swal("Please provide category document");
					return false;
				}
			} else {
				fd.append("catgDoc", catgDoc, catgDoc.name);
			}
		}
		if (bookingFor == "others") {
			if (gDoc == null) {
				if (gdocname == null) {
					swal("Please provide guest document");
					return false;
				}
			} else {
				fd.append("gDoc", gDoc, gDoc.name);
			}
		}

		fd.delete('bookingFor');
		fd.append("bookingFor", bookingFor);
		let adultCount = $('#noOfAdults').val() == "" ? 0 : $('#noOfAdults').val();
		let childCount = $('#noOfChildren').val() == "" ? 0 : $('#noOfChildren').val();
		if (!validateChildrenAndAdultCount(adultCount, childCount, guestDtls))
			return false;

		let url = addOrUpdateUrl(type);

		console.log(fd);
		$.ajax({
			url: url,
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
				console.log(data);
				$('#loading').hide();
				if (data.status) {
					$('#editDetails').modal('hide');
					swal(data.msg);
					$(this).find('form').trigger('reset');
					$('#existingBookingApplication').bootstrapValidator('resetForm', true);
					guestCount = 0;
					// $('#addGuestDetailsBtn').click();
					table.ajax.reload()
				} else {
					swal(data.msg);
				}
			}, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
		});
	}
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
					},
					date: {
						format: 'DD-MM-YYYY HH:MM'
					}
				}
			}, toDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					date: {
						format: 'DD-MM-YYYY HH:MM'
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
			}, noOfChildren: {
				validators: {
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
						message: 'only 10 digits are allowed starting with 9,8,7,6'
					}
				}
			}, gMobile: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp: /^[9 8 7 6]\d{9}$/,
						message: 'only 10 digits are allowed starting with 9,8,7,6'
					}
				}
			}, emailId: {
				validators: {
					// notEmpty: {
					//     message: 'field can\'t be empty'
					// },
					emailAddress: {
						message: 'invalid email id'
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
			}, offAddr: {
				validators: {
					notEmpty: {
						message: 'Field can\'t be empty'
					},
					regexp: {
						regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
						message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
					}
				}
			}, catgType: {
				validators: {
					notEmpty: {
						message: 'select a valid option'
					},
				}
			}, gDocType: {
				validators: {
					notEmpty: {
						message: 'select a valid option'
					},
				}
				// }, travelModeFr: {
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
			}
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

	$("#editDetails").on("hidden.bs.modal", function() {
		$('#existingBookingApplication').bootstrapValidator('resetForm', true);
		$('#existingBookingApplication')[0].reset();
		$('#guestDetailsWrapper').html('');
		$('#guestDetailsWrapper1').html('');
		$('#categoryDoc').val('');
		$('#supportDoc').val('');
		$('#gDoc').val('');
		$('#supportDocName').html('Choose File');
		$('#categoryDocName').html('Choose File');
		$('#gDocName').html('Choose File');
		$('#desgDocDtls').html('');
		$('#suppDocDtls').html('');
		$('#gDocDtls').html('');
		$('#desgDocDtlsBox').hide();
		$('#suppDocDtlsBox').hide();
		$('#gDocDtlsBox').hide();
		suppDoc = null;
		catgDoc = null;
		gDoc = null;
		suppdocname = null;
		catgdocname = null;
		gdocname = null;
		guestCount = 0;
		$('#arrPla').val('');
		$('#depPla').val('');
	});


	$('#extReqId').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});
	$("#partialCancelModal").on("hidden.bs.modal", function() {
		$("#partialFromDate").val("");
	});


});

// function to convert string date and time to date and time object
function convertStringToDatetime(dateString) {
	return moment(dateString, 'DD-MM-YYYY HH:mm').toDate();
}

// function to calculate no of days between two dates
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

function addOrUpdateUrl(type) {
	if ($('#formSubmissionBtn').html() == 'Update Details') {
		return "./admin/adminsaveBookingDetails/EDIT/" + type + ".do";
	} else if ($('#formSubmissionBtn').html() == 'Save') {
		return "./admin/adminsaveBookingDetails/editSplit/" + type + ".do";
	} else {
		return "./admin/adminsaveBookingDetails/add/" + type + ".do";
	}
}
function toCapitaLiaze(event) {
	var str = event.target.value;
	const arr = str.split(" ");
	for (var i = 0; i < arr.length; i++) {
		arr[i] = arr[i].charAt(0).toUpperCase() + arr[i].slice(1);

	}
	const str2 = arr.join(" ");
	document.getElementById(event.target.id).value = str2;
}

