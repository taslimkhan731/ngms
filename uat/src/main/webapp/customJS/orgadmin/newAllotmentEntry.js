$(document).ready(function () {

	let guestName;
	let guestAddr;
	let guestGender;
	let guestDepartment;
	let guestAge;
	let adultCount = 0;
	let childCount = 0;

	//$('#office').hide();
	$('#supporting').hide();
	$('#docTypediv').hide();
	$('#category_supporting').hide();
	$('#supporting').hide();
	$('#conveyance').hide();
	$('#desgHidden').hide();
	$('#noOfRooms').val('1');
	$("#noOfBeds").addClass('disableElement');
	$('#otherGuestDetails').hide();
	$('#guestTableCat4').hide();
	$('#onlyRoom').removeClass('segment-active');
	$('#roomCon').removeClass('segment-active');
	$('#conv').removeClass('segment-active');
	$('#conference').removeClass('segment-active');

	$('.cat_1').prop('disabled', true);
	$('.cat_234').prop('disabled', true);
	$('#formDetails').hide();

	$("#arrDt, #depDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY'
	});

	$("#frmDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY 09:00'
	});

	$("#toDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY 08:00'
	});

	$("#depTm, #arrTm").datetimepicker({
		format: 'hh:mm A'
	});

	$("#frmDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
		$("#toDt").data("DateTimePicker").minDate(e.date);
		if ($('#toDt').val() != '') {
			var date1 = convertStringToDatetime($('#frmDt').val());
			var date2 = convertStringToDatetime($('#toDt').val());
			var days = daysBetween(date1, date2);
			$('#noOfDays').val(days);
		}
	});
	$("#toDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'toDt');
		$("#frmDt").data("DateTimePicker").maxDate(e.date);
		if ($('#frmDt').val() != '') {
			var date1 = convertStringToDatetime($('#frmDt').val());
			var date2 = convertStringToDatetime($('#toDt').val());
			var days = daysBetween(date1, date2);
			$('#noOfDays').val(days);
		}
	});

	$("#arrDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
		$("#depDt").data("DateTimePicker").minDate(e.date);
	});
	$("#depDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'depDt');
		$("#arrDt").data("DateTimePicker").maxDate(e.date);
	});

	// $("#noOfRooms").bind('keyup keypress mouseup', function () {
	//     var room = $("#noOfRooms").val();
	//     if (room == '') {
	//         $("#noOfRooms").removeClass('disableElement');
	//         $("#noOfBeds").removeClass('disableElement');
	//     } else {
	//         $("#noOfBeds").addClass('disableElement');
	//     }
	// });
	//
	// $("#noOfBeds").bind('keyup keypress mouseup', function () {
	//     var room = $("#noOfBeds").val();
	//     if (room == '') {
	//         $("#noOfRooms").removeClass('disableElement');
	//         $("#noOfBeds").removeClass('disableElement');
	//     } else {
	//         $("#noOfRooms").addClass('disableElement');
	//     }
	// });

	/*
        $("input[name='bookingCond']").click(function() {
            if($(this).val() == "roomCon"){
                $('#roomTyp').val($(this).val()); // roomCon
                $('#reqDetails').show();
                $('#addressDetails').show();
                $('#convenceFrom').show();
                $('#convenceTo').show();
                $('#GuestDetails').show();
                $('#btnSub').show();
                $('#addressLine').hide();
            }else if($(this).val() == "onlyRoom"){
                $('#roomTyp').val($(this).val()); // onlyRoom
                $('#reqDetails').show();
                $('#addressDetails').show();
                $('#convenceFrom').hide();
                $('#convenceTo').hide();
                $('#GuestDetails').show();
                $('#btnSub').show();
                $('#addressLine').hide();
            } else {
                $('#roomTyp').val($(this).val()); // conv
                $('#reqDetails').hide();
                $('#addressLine').show();
                $('#addressDetails').show();
                $('#convenceFrom').show();
                $('#convenceTo').show();
                $('#GuestDetails').show();
                $('#btnSub').show();
            }
        });
    */

	$('#onlyRoom').on('click', function () {
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
	});

	$('#roomCon').on('click', function () {
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

	$('#conv').on('click', function () {
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
		$('#dormitoryWrapper').hide();
	});

	$('#conference').on('click', function () {
		$('#conference').addClass('segment-active');
		$('#onlyRoom').removeClass('segment-active');
		$('#roomCon').removeClass('segment-active');
		$('#conv').removeClass('segment-active');

		$('#roomTyp').val($(this).val()); // onlyRoom
		console.log($('#roomTyp').val());
	});

	var guestParameters = 8;
	var guestCount = 0;
	var blockId = getUrlParameter('id');
	var suppDoc = null;
	var catgDoc = null;
	var gDoc = null;
	var officAddress = '';

	//$('#roomWrapper').show();
	$('#dormitoryWrapper').hide();

	$("input[name='bookingType']").click(function () {
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

	$('#supportDoc').change(function (e) {
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

	$('#categoryDoc').change(function (e) {
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

	$('#gDoc').change(function (e) {
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

	$('#catg').on('change', function () {
		var id = $('#catg').val();
		onCatgChange(id);
		if (id != "") {
			getDesignationListDtls(id, '');
			getPurposeListDtls(id);
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
		} else {
			$('.cat_1').prop('disabled', true);
			$('.cat_234').prop('disabled', false);
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
		$('#supporting').hide();
		$('#onlyRoom').click();
	}

//	$('#desg').on('change', function() {
//		var id = $('#desg').val();
//		getGuestDesignationDetails(id);
//	});

	var getGuestDesignationDetails = function (id) {
		$.ajax({
			url: "./guest/getGuestDesignationDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function (xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function (data, textStatus, jqXHR) {
				$('#loading').hide();
				$('#guestDesignation').val(data == null ? '' : data);
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

	var getDesignationListDtls = function (id, type) {
		$.ajax({
			url: "./guest/getDesignationDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function (xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function (data, textStatus, jqXHR) {
				$('#loading').hide();
				$("#desg").html("");
				$("#desg").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (type == option.id) {
						$("#desg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
					} else {
						$("#desg").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				})
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

	var getCatgoryListDtls = function (type) {
		$.ajax({
			url: "./guest/getCategoryDetails.do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function (data, textStatus, jqXHR) {
				$("#catg").html("");
				$("#catg").append('<option value="">---Select---</option>');
				data.forEach(option => {
					if (('C' + type) == option.id) {
						$("#catg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
					} else {
						$("#catg").append('<option value="' + option.id + '">' + option.name + '</option>');
					}
				})
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

	var getFlightTrainList = function (type, cond) {
		$.ajax({
			url: "./guest/getFlightTrainList/" + blockId + "/" + type + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function (xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function (data, textStatus, jqXHR) {
				$('#loading').hide();
				if (cond == 'arr') {
					$("#arrPla").html("");
					$("#arrPla").append('<option value="">---Select---</option>');
					data.forEach(option => {
						$("#arrPla").append('<option value="' + option.name + '">' + option.name + '</option>');
					})
				} else if (cond == 'dep') {
					$("#depPla").html("");
					$("#depPla").append('<option value="">---Select---</option>');
					data.forEach(option => {
						$("#depPla").append('<option value="' + option.name + '">' + option.name + '</option>');
					})
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

	$('#purp').on('change', function () {
		var purpose = $('#purp').val();
		if (purpose == '1') {
			$('#office').show();
			$('#offAddr').val(officAddress);
			$('#supporting').show();
			$('#purposeDocLabel').html('Official Document Upload');
		} else if (purpose == '3' || purpose == '4') {
			$('#office').show();
			$('#offAddr').val(officAddress);
			$('#supporting').show();
			if (purpose == '3') {
				$('#purposeDocLabel').html('Patient Document Upload');
			} else {
				$('#purposeDocLabel').html('Student Document Upload');
			}
		} else {
			$('#office').show();
			$('#offAddr').val(officAddress);
			$('#supporting').hide();
		}
	});

	$('#travelModeFr').on('change', function () {
		var mode = $('#travelModeFr').val();
		if (mode == 'T') {
			getFlightTrainList('t', 'arr')
		} else if (mode == 'F') {
			getFlightTrainList('f', 'arr')
		}
	});

	$('#travelModeTo').on('change', function () {
		var mode = $('#travelModeTo').val();
		if (mode == 'T') {
			getFlightTrainList('t', 'dep')
		} else if (mode == 'F') {
			getFlightTrainList('f', 'dep')
		}
	});

	var getPurposeListDtls = function (id) {
		$.ajax({
			url: "./guest/getPurposeDetails.do?blockId=" + blockId + "&catgId=" + id,
			type: "GET",
			contentType: "application/json",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function (data, textStatus, jqXHR) {
				$("#purp").html("");
				$("#purp").append('<option value="">---Select---</option>');
				data.forEach(option => {
					$("#purp").append('<option value="' + option.id + '">' + option.name + '</option>');
				})
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

	var getUserDtls = function () {
		$.ajax({
			url: "./userProfile.do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function (result) {
				if (result.success) {
					$("#mob").val(result.data.contactNo);
					$("#catgType").val(result.data.catgType);
					if (result.data.email != null) {
						$("#emailId").val(result.data.email);
					}
					// $('#offAddr').val(result.data.officeAddr == null ? '' : result.data.officeAddr);
					if (result.data.firstName != null) {
						// $("#guestName").val(result.data.firstName);
						guestName = result.data.firstName;
					}
					officAddress = result.data.officeAddr == null ? '' : result.data.officeAddr;
					// $('#guestAddr').val(result.data.resAddr == null ? '' : result.data.resAddr);
					// $('#guestGender').val(result.data.guestGender == null ? '' : result.data.guestGender);
					// $('#guestDepartment').val(result.data.dept == null ? '' : result.data.dept);
					guestAddr = result.data.resAddr == null ? '' : result.data.resAddr;
					guestGender = result.data.guestGender == null ? '' : result.data.guestGender;
					guestDepartment = result.data.dept == null ? '' : result.data.dept;
					if (result.data.dob != null) {
						// $('#guestAge').val(year)
						guestAge = dayDiff(new Date(), result.data.dob);
					}
				} else {
					swal(result.msg);
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

	getCatgoryListDtls('');
//	getPurposeListDtls();
	getUserDtls();

	var convertingGuesetDetailsObjectToArrayOfObjects = function (data) {
		var array = [];
		var count = data.length / guestParameters;
		console.log(data.length);
		for (var i = 0; i < count; i++) {
			array.push(convertSerializeArrayToObject(data.slice((i * guestParameters), ((i + 1) * guestParameters))));
		}
		return array;
	}

	$('#noOfAdults, #noOfChildren').on('blur', function () {
		let category = $('#catg').val();
		adultCount = $('#noOfAdults').val() == "" ? 0 : $('#noOfAdults').val();
		childCount = $('#noOfChildren').val() == "" ? 0 : $('#noOfChildren').val();
		let total = parseInt(adultCount) + parseInt(childCount);
		if (category == 'C1' || category == 'C2' || category == 'C3') {
			$('#guestDetailsWrapper').html('');
			for (let i = 0; i < total; i++) {
				let count = i + 1;
				$('#guestDetailsWrapper').append(
					'<tr id=tr' + count + '> ' +
					'<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="hidden" name="guestId">' +
					'<input type="hidden" name="guestSlNo" value="' + count + '">' +
					'<input type="text" placeholder="Name" name="guestName" id="guestName">' +
					'</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="number" placeholder="Age in years" name="guestAge" id="guestAge">' +
					'</td> ' +
					'<td class="text-center text-nowrap"> ' +
					'<select name="guestGender" id="guestGender"> ' +
					'<option>---Select---</option> ' +
					'<option value="M">Male</option> ' +
					'<option value="F">Female</option> ' +
					'<option value="O">Other</option> ' +
					'</select> ' +
					'</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="text" placeholder="Designation" id="guestDesignation" name="guestDesignation">' +
					'</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="text" placeholder="Department" id="guestDepartment" name="guestDepartment">' +
					'</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="text" placeholder="Address" id="guestAddr" name="guestAddr">' +
					'</td> ' +
					'</tr> '
				);
			}
			if (total > 0 && $('#bookingFor').val() == 'self') {
				$('#guestName').val(guestName);
				$('#guestAddr').val(guestAddr);
				$('#guestGender').val(guestGender);
				$('#guestDepartment').val(guestDepartment);
				$('#guestAge').val(guestAge);
			}
		} else if (category == 'C4') {
			$('#guestDetailsWrapper1').html('');
			for (let i = 0; i < total; i++) {
				let count = i + 1;
				$('#guestDetailsWrapper1').append(
					'<tr id=tr' + count + '> ' +
					'<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="hidden" name="guestId">' +
					'<input type="hidden" name="guestSlNo" value="' + count + '">' +
					'<input type="text" placeholder="Name" name="guestName" id="guestName">' +
					'</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="number" placeholder="Age in years" name="guestAge" id="guestAge">' +
					'</td> ' +
					'<td class="text-center text-nowrap"> ' +
					'<select name="guestGender" id="guestGender"> ' +
					'<option>---Select---</option> ' +
					'<option value="M">Male</option> ' +
					'<option value="F">Female</option> ' +
					'<option value="O">Other</option> ' +
					'</select> ' +
					'</td> ' +
					'<td class="text-center text-nowrap">' +
					'<input type="hidden" name="guestDesignation" value="">' +
					'<input type="hidden" name="guestDepartment" value="">' +
					'<input type="text" placeholder="Address" id="guestAddr" name="guestAddr">' +
					'</td> ' +
					'</tr> '
				);
			}
			if (total > 0 && $('#bookingFor').val() == 'self') {
				$('#guestName').val(guestName);
				$('#guestAddr').val(guestAddr);
				$('#guestGender').val(guestGender);
				$('#guestAge').val(guestAge);
			}
		} else {
			swal('Please select category');
			$('#noOfAdults').val('');
			$('#noOfChildren').val('');
		}
	});

	$('#bookingFor').on('change', function() {
		$('#guestDetailsWrapper').html('');
		$('#guestDetailsWrapper1').html('');
		$('#noOfAdults').val('');
		$('#noOfChildren').val('');
	});

	// $('#addGuestDetailsBtn').on('click', function () {
	//     guestCount++;
	//     $('#guestDetailsWrapper').append(
	//         '<tr id=tr' + guestCount + '> ' +
	//         '<th scope="row"><span class="mt-2" id=guestCo' + guestCount + '>' + guestCount + '</span></th> ' +
	//         '<th scope="row"><span class="mt-2">' +
	//         '<input class="form-check-input" type="checkbox" value=' + guestCount + ' id=check' + guestCount + '>' +
	//         '</span></th> ' +
	//         '<td> ' +
	//         '<div class="row"> ' +
	//         '<input type="hidden" name="guestId"> ' +
	//         '<input type="text" name="guestName" id="guestName" class="form-control col-12" ' +
	//         'placeholder="Enter name"> ' +
	//         '</div> ' +
	//         '</td> ' +
	//         '<td> ' +
	//         '<div class="row"> ' +
	//         '<input type="number" name="guestAge" id="guestAge" class="form-control col-12" ' +
	//         'placeholder="Enter age" autocomplete="off" min="1"> ' +
	//         '</div> ' +
	//         '</td> ' +
	//         '<td> ' +
	//         '<div class="row"> ' +
	//         '<select class="form-control col-12" name="guestGender" id="guestGender"> ' +
	//         '<option value=""> --- Select --- </option> ' +
	//         '<option value="M">Male</option> ' +
	//         '<option value="F">Female</option> ' +
	//         '<option value="O">Others</option> ' +
	//         '</select> ' +
	//         '</div> ' +
	//         '</td> ' +
	//         '<td> ' +
	//         '<div class="row"> ' +
	//         '<input type="text" name="guestDesignation" class="form-control col-12" id="guestDesignation"' +
	//         'placeholder="Enter Designation"> ' +
	//         '</div> ' +
	//         '</td> ' +
	//         '<td> ' +
	//         '<div class="row"> ' +
	//         '<input type="text" name="guestDepartment" id="guestDepartment" class="form-control col-12" ' +
	//         'placeholder="Enter Department"> ' +
	//         '</div> ' +
	//         '</td> ' +
	//         '<td> ' +
	//         '<div class="row"> ' +
	//         '<input type="text" name="guestAddr" id="guestAddr" class="form-control col-12" ' +
	//         'placeholder="Enter address"> ' +
	//         '</div> ' +
	//         '</td> ' +
	//         '</tr>'
	//     );
	// });

	$('#addGuestDetailsBtn').click();

	$('#deleteGuestDtlsBtn').on('click', function () {
		var i, j, len = 0;
		if (guestCount > 1) {
			for (i = 1; i <= guestCount + len; i++) {
				if ($('#check' + (i - len)).prop("checked")) {
					$('#tr' + (i - len)).remove();
					for (j = (i + 1 - len); j <= guestCount; j++) {
						$('#check' + j).val(j - 1);
						$('#check' + j).attr('id', 'check' + (j - 1));
						$('#tr' + j).attr('id', 'tr' + (j - 1));
						$('#guestCo' + j).html(j - 1);
						$('#guestCo' + j).attr('id', 'guestCo' + (j - 1));
					}
					len = len + 1;
					guestCount--;
				}
			}
		}
	});


	//function to check how many guests have age < 12
	function validateChildrenAndAdultCount(adultCount, childCount, guestDtls) {
		var i, child = 0, adult = 0;
		for (i = 0; i < guestDtls.length; i++) {
			if (guestDtls[i].guestAge < 12) {
				child++;
			} else {
				adult++;
			}
		}
		if(childCount < child || adultCount < adult){
			alert('Please check the number of children and adults');
			return false;
		}
		return true;
	}

	var submitBookingDetails = function (type) {
		console.log('form valid');
		var formData = $('#newRoomBookingApplication').serializeArray();
		var rawGuestDetails = formData.filter(d => d.name.match(/guest/g));
		var otherDetails = formData.filter(d => !d.name.match(/guest/g));
		var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
		console.log(guestDtls);
		var fd = new FormData();
		fd = convertSerializeArrayToFd(otherDetails);
		fd.append("guestDtlsStr", JSON.stringify(guestDtls));
		fd.append("blockId", blockId);
		fd.append("noOfDays", $('#noOfDays').val());

		if (suppDoc != null) {
			fd.append("suppDoc", suppDoc, suppDoc.name)
		}

		if (catgDoc != null) {
			fd.append("catgDoc", catgDoc, catgDoc.name)
		}

		if(gDoc != null) {
			fd.append("gDoc", gDoc, gDoc.name)
		}

		if(!validateChildrenAndAdultCount(adultCount, childCount, guestDtls))
			return false;

		$.ajax({
			url: "./guest/saveBookingDetails/add/" + type + ".do",
			type: "POST",
			enctype: 'multipart/form-data',
			processData: false,  // Important!
			contentType: false,
			data: fd,
			beforeSend: function (xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function (data, textStatus, jqXHR) {
				console.log("Successs : " + data);
				$('#loading').hide();
				if (data.status && data.isThirdParty == false) {
					$(this).find('form').trigger('reset');
					$('#newRoomBookingApplication').bootstrapValidator('resetForm', true);
					resetForm();
					$('#supportDocName').html('Choose file');
					$('#categoryDocName').html('Choose file');
					guestCount = 0;
					$('#addGuestDetailsBtn').click();
					swal(data.msg);
				} else if (data.status && data.isThirdParty) {
					console.log(data.successEncData);
					$("#encDataSuccessData").val(data.successEncData);
					$('#successBacktoOdishaOne').attr('action', data.successUrl).submit();
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
				/*swal(jqXHR.responseText);*/
				$('#loading').hide();
			}
		});
	}

	$('#newRoomBookingApplication').bootstrapValidator({
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
			}, toDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					date: {
						format: 'DD-MM-YYYY HH:MM'
					}
				}
			}, mob: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp: /^[9 8 7]\d{9}$/,
						message: 'only 10 digits are allowed'
					}
				}
			}, emailId: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
						message: 'field is not valid'
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
						message: 'field cannot be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}, duringstay: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}

//			},supportDoc : {
//				validators : {
//					notEmpty : {
//						message : 'Document Type is required and cannot be Empty.'
//					},
//
//				}
//			},
//			categoryDoc : {
//				validators : {
//					notEmpty : {
//						message : 'file  is required and cannot be Empty.'
//					},
//
//				}
			}, travelModeFr: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, flNoFr: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
						message: 'field is not valid'
					}
				}
			}, arrDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, arrPla: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}, travelModeTo: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, flNoTo: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
						message: 'field is not valid'
					}
				}
			}, depDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, depPla: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}, guestName: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}, guestAge: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}, regexp: {
					regexp: /^[0-9]*$/,
					message: 'field is not valid'
				}
			}, guestGender: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, guestAddr: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}
		}
	}).on('success.form.bv', function (e) {
		e.preventDefault();
		var type = $('#roomTyp').val();
		if (type == 'conv') {
			submitBookingDetails('C');
		} else if (type == 'onlyRoom') {
			submitBookingDetails('R');
		} else {
			submitBookingDetails('RC');
		}
	});

	var table = $("#guestCatDataTable").removeAttr('width').DataTable({
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
			{className: "all", "targets": [0, 1, 2]},
			{
				"targets": 0, "data": null,
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{
				"targets": 1, "data": null,
				render: function (data, type, row, meta) {
					return "Cat" + data.desc
				}
			},
			{"targets": 2, "data": "name"},
			{
				"targets": 3, "data": null,
				render: function (data, type, row, meta) {
					return '<button type="button" id="cateType" title="Select Category" action="cateType" class="btn btn-sm btn-edit"><i class="fa fa-edit" aria-hidden="true"></i></button>'
				}
			}
		],
	});

	$("#myModalView").on("hidden.bs.modal", function () {
		$(this).find('form').trigger('reset');
	});

	$('#guestCatDataTable').on('click', 'tbody #cateType', function () {
		$('#myModalView').modal('hide');
		var data_row = table.row($(this).closest('tr')).data();
		getCatgoryListDtls(data_row.desc);
		getDesignationListDtls(('C' + data_row.desc), data_row.id);
		getPurposeListDtls(('C' + data_row.desc));
		onCatgChange('C' + data_row.desc);
//		getGuestDesignationDetails(data_row.id);
	});

	$('#view').click(function () {
		$('#myModalView').modal('show');
		$.ajax({
			url: './guest/getDesgList.do',
			type: "GET",
			headers: {"X-CSRF-TOKEN": token},
			success: function (result) {
				table.clear().draw();
				table.rows.add(result).draw();
				table.columns.adjust().responsive.recalc();
			}
		});
	});

	//if booking for other show otherGuestDetails
	$('#bookingFor').change(function () {
		var bookingFor = $('#bookingFor').val();
		if (bookingFor == 'others') {
			$('#otherGuestDetails').show();
		} else {
			$('#otherGuestDetails').hide();
		}
	});
})

function dayDiff(d1, d2) {
	d2 = new Date(d2);
	return new Number((d1.getTime() - d2.getTime()) / 31536000000).toFixed(0);
}

//function to convert string date and time to date and time object
function convertStringToDatetime(dateString) {
	return moment(dateString, 'DD-MM-YYYY HH:mm').toDate();
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

function resetForm() {
	// $('#guestDetailsWrapper').html('');
	$('#newRoomBookingApplication').trigger("reset");
	$('#roomWrapper').show();
	$('#dormitoryWrapper').hide();
	$('#noOfRooms').val(1);
	// $('#noOfBedsDormitory').val('');
	// $('#depTm').val('');
	// $('#arrTm').val('');
	// $("#duringstay").val('');
}