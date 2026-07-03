
$(document).ready(function () {
    let guestName;
    let guestAddr;
    let resAddr;
    let guestGender;
    let guestDepartment;
    let guestAge;
    let adultCount = 0;
    let childCount = 0;
	blockIdForTerm = getUrlParameter('id');
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

	
	$('#submitBookingDetailsConfrence').bootstrapValidator({
        btnSubmit: 'button[type="submit"]',
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            }, confCatId: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    },
                   
                }
            },address: {
				validators: {
                    notEmpty: {
                        message: 'field cannot be empty'
                    },
                     regexp: {
					   regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
					   message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
					}
                }
            },mobileNo: {
                validators: {
                   notEmpty: {
                        message: 'field can\'t be empty'
                    }, regexp: {
                        regexp: /^[9 8 7 6]\d{9}$/,
                        message: 'only 10 digits are allowed starting with 9,8,7,6'
                    }
                }
            },
            confUseId: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            },
            amount: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            },
            checkTerm: {
                validators: {
                    notEmpty: {
                        message: 'Please check terms&condition'
                    }
                }
            },
             capacity: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    },
                    greaterThan: {
                        value: 0,
                        message: 'field must be greater than 0'
                    }
                }
            }
          }
		}).on('error.field.bv', function(e, data) {
        if (data.bv.getSubmitButton()) {
           return false;
        }
    }).on('success.form.bv', function (e) {
        return true;
    });


	$("#frmDtCon").datetimepicker({
    useCurrent: false,
    minDate: moment().add(1, 'day'),
    format: 'DD-MM-YYYY'
	});

	
	$("#catg").on("change",function(){
		$("#category").val($("#catg").val());
	});
    $("#arrDt, #depDt").datetimepicker({
        useCurrent: false,
        minDate: moment().millisecond(0).second(0).minute(0).hour(0),
        format: 'DD-MM-YYYY'
    });

    $("#frmDt").datetimepicker({
        useCurrent: false,
        minDate: moment().millisecond(0).second(0).minute(0).hour(0),
        /*maxDate: moment().add(7, 'days').endOf('day'),*/
        format: 'DD-MM-YYYY 09:00'
    });

    $("#toDt").datetimepicker({
        useCurrent: false,
        minDate: moment().millisecond(0).second(0).minute(0).hour(0),
        format: 'DD-MM-YYYY 08:00'
    });

    $("#depTm, #arrTm").datetimepicker({
        format: 'hh:mm A'
    });

    $("#frmDt").on("dp.change", function (e) {
    	$("#toDt").val("");
        $('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
        $('#toDt').data("DateTimePicker").minDate(e.date.add(1, 'days'));
        // $("#toDt").data("DateTimePicker").minDate(e.date);
		
        if ($('#toDt').val() != '') {
            var date1 = convertStringToDatetime($('#frmDt').val());
            var date2 = convertStringToDatetime($('#toDt').val());
            var days = daysBetween(date1, date2);
            $('#noOfDays').val(days);
        }
    });
    $("#toDt").on("dp.change", function (e) {
        $('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'toDt');
       /* $("#frmDt").data("DateTimePicker").maxDate(e.date);*/
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

    $("#frmDtCon").on("dp.change", function (e) {
	    	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	        $("#formSubmissionBtnForConfrence").hide();
	        forDisabledProperty();
	    });
	$("#frmDtCon, #hallType, #timePeriod,#startTime").on("change", function () {
	  		 forDisabledProperty();
	    });
	    
	    
	var forDisabledProperty=function(){

		if($("#frmDtCon").val()!="" && $("#hallType").val()!="" && $("#timePeriod").val()!=""){
				$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",false);  
	         }else{
	         	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",true);
	         	$("formSubmissionBtnForConfrence").hide();
		}
		if($('#timePeriod').val()=="4"){
			if($("#frmDtCon").val()!="" && $("#hallType").val()!="" && $("#timePeriod").val()!="" && $("#startTime").val()){
				$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",false);  
	         }else{
	         	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",true);
	         	$("formSubmissionBtnForConfrence").hide();
			}
		}
	}
	 $('#timePeriod').on('change', function () {
	 	if($('#timePeriod').val()=="4"){
	 		if($("#frmDtCon").val()!="" && $("#hallType").val()!="" && $("#timePeriod").val()!="" && $("#startTime").val()){
				$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",false);  
	         }else{
	         	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",true);
	         	$("formSubmissionBtnForConfrence").hide();
			}
	 	}
	 });
    $('body').on('blur', "#guestAge", function(){
    	var age = $("#guestAge").val();
    	if(age < 12){
    		 $('#guestDesignation').prop('disabled', true);
    		 $('#guestDepartment').prop('disabled', true);
    	}
    });
    
    
    $('#onlyRoom').on('click', function () {
    	$("#view").show();
        $('#onlyRoom').addClass('segment-active');
        $('#roomCon').removeClass('segment-active');
        $('#conv').removeClass('segment-active');
        $('#conference').removeClass('segment-active');
		 $('#conferenceTerrace').hide();
        $('#roomBed').prop('checked', true);
        $('#roomTyp').val($(this).val()); // onlyRoom
        console.log($('#roomTyp').val());
        $('#conveyance').hide();
        $('#GuestDetails').show();
        $('#btnSub').show();
        $('.roomBook').show();
        $('#arrTravel').prop('checked', false);
        $('#deptTravel').prop('checked', false);
        $('.hideOnConfrence').show();
        $('#roomWrapper').show();
        $('#dormintoryWrapper').hide();
    });

    $('#roomCon').on('click', function () {
    	$("#view").show();
        $('#roomCon').addClass('segment-active');
        $('#onlyRoom').removeClass('segment-active');
        $('#conv').removeClass('segment-active');
        $('#conference').removeClass('segment-active');
		 $('#conferenceTerrace').hide();
        $('#roomBed').prop('checked', true);
		$('.hideOnConfrence').show();
        $('#roomTyp').val($(this).val()); // roomCon
        console.log($('#roomTyp').val());
        $('#conveyance').show();
        $('#GuestDetails').show();
        $('#btnSub').show();
        $('.roomBook').show();
        $('#roomWrapper').show();
        $('#dormintoryWrapper').hide();
    });

    $('#conv').on('click', function () {
    	$("#view").show();
        $('#conv').addClass('segment-active');
        $('#onlyRoom').removeClass('segment-active');
        $('#roomCon').removeClass('segment-active');
        $('#conference').removeClass('segment-active');
		 $('#conferenceTerrace').hide();
		 $('.hideOnConfrence').show();
        $('#roomTyp').val($(this).val()); // conv
        console.log($('#roomTyp').val());
        $('#conveyance').show();
        $('#GuestDetails').show();
        $('#btnSub').show();
        $('.roomBook').hide();
        $('#roomWrapper').hide();
        $('#dormitoryWrapper').hide();
    });


	$('#conference').on('click', function () {
		$("#view").hide();
		getConfrenceHalltype();
        $('#onlyRoom').removeClass('segment-active');
        $('#roomCon').removeClass('segment-active');
        $('#conv').removeClass('segment-active');
        $('#conference').addClass('segment-active');
		$('.hideOnConfrence').hide();
        $('#roomBed').prop('checked', true);
        $('#roomTyp').val($(this).val()); // onlyRoom
        console.log($('#roomTyp').val());
        $('#conveyance').hide();
        $('#GuestDetails').hide();
        $('#btnSub').hide();
        $('.roomBook').hide();
        $('#arrTravel').prop('checked', false);
        $('#deptTravel').prop('checked', false);
        $("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	   	$("#formSubmissionBtnForConfrence").hide();
        $('#conferenceTerrace').show();
        	if($("#city").val()!="04"){
    		$("#inclusivexclusive").html("The tariff will be inclusive of Electricity and Service charges.");
    	}else{
    		$("#inclusivexclusive").html("The tariff will be exclusive of Electricity, AC and Service charges.");
    	}
    	if($("#city").val()=="02"){
    		$(".mumbaiPoint").show();
    	}else{
    	 $(".mumbaiPoint").hide();
    	}
    	forTermCondition();
    });


    var guestParameters = 7;
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
	$("#blockid").val(getUrlParameter('id'));
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
        $('#supporting').hide();
        $('#onlyRoom').click();
        $('#noOfAdults').blur();
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
            $('#offAddr').val(resAddr);
            $('#supporting').show();
            $('#purposeDocLabel').html('Upload Official Document (jpg/png/pdf)<small class="small">*</small>');
        } else if (purpose == '3' || purpose == '4') {
            $('#office').show();
            $('#offAddr').val(resAddr);
            $('#supporting').show();
            if (purpose == '3') {
                $('#purposeDocLabel').html('Upload Patient Document (jpg/png/pdf)<small class="small">*</small>');
            } else {
                $('#purposeDocLabel').html('Upload Student Document (jpg/png/pdf)<small class="small">*</small>');
            }
        } else {
            $('#office').show();
            $('#offAddr').val(resAddr);
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
    
    $("#advncAamount").on("change",function(){
    	console.log($("#amount").val());
    	console.log($("#advncAamount").val());
    	if(parseInt($("#amount").val())<parseInt($("#advncAamount").val())){
    	swal("Advance Amount Cann't be greater than Amount");
    	$("#advncAamount").val("");
    	return false;
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
                    if (result.data.firstName != null) {
                        guestName = result.data.firstName;
                    }
                    $('#offAddr').val(result.data.resAddr == null ? '' : result.data.resAddr);
                    //guestAddr = result.data.resAddr == null ? '' : result.data.resAddr;
                    resAddr = result.data.resAddr == null ? '' : result.data.resAddr
                    guestGender = result.data.guestGender == null ? '' : result.data.guestGender;
                    guestDepartment = result.data.dept == null ? '' : result.data.dept;
                    console.log(result.data.dateOfBirth);
                    if (result.data.dateOfBirth != null) {
                        guestAge = dayDiff(new Date(), result.data.dateOfBirth);
                        console.log(guestAge);
                    }
                } else {
                    swal(result.msg);
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

    getCatgoryListDtls('');
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

    $('#noOfAdults, #noOfChildren').on('click', function () {
        if ($('#catg').val() == "") {
            swal("Please select category");
            $('#noOfAdults').val('');
            $('#noOfChildren').val('');
        }
    });

    $('#noOfAdults, #noOfChildren').on('blur', function () {
        let category = $('#catg').val();
        adultCount = $('#noOfAdults').val() == "" ? 0 : $('#noOfAdults').val();
        childCount = $('#noOfChildren').val() == "" ? 0 : $('#noOfChildren').val();
        let total = parseInt(adultCount) + parseInt(childCount);
        if (category == 'C1' || category == 'C2' || category == 'C3') {
            $('#guestDetailsWrapper').html('');
            $('#guestDetailsWrapper1').html('');
            for (let i = 0; i < total; i++) {
                let count = i + 1;
                $('#guestDetailsWrapper').append(
                    '<tr id=tr' + count + '> ' +
                    '<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
                    '<td class="text-center text-nowrap">' +
                    '<input type="hidden" name="guestId">' +
                    '<input type="hidden" name="guestSlNo" value="' + count + '">' +
                    '<input type="text" placeholder="Name"  min="1" name="guestName" id="guestName'+count+'" autocomplete="off" onkeypress="toCapitaLiaze(event)">' +
                    '</td> ' +
                    '<td class="text-center text-nowrap">' +
                    '<input type="number" placeholder="Age in years" name="guestAge" min="1" maxlength="2" id="guestAge'+count+'" autocomplete="off">' +
                    '</td> ' +
                    '<td class="text-center text-nowrap"> ' +
                    '<select name="guestGender" id="guestGender'+count+'"> ' +
                    '<option>---Select---</option> ' +
                    '<option value="M">Male</option> ' +
                    '<option value="F">Female</option> ' +
                    '<option value="O">Other</option> ' +
                    '</select> ' +
                    '</td> ' +
                    '<td class="text-center text-nowrap">' +
                    '<input type="text" placeholder="Designation" id="guestDesignation'+count+'" name="guestDesignation" autocomplete="off">' +
                    '</td> ' +
                    '<td class="text-center text-nowrap">' +
                    '<input type="text" placeholder="Department" id="guestDepartment'+count+'" name="guestDepartment" autocomplete="off">' +
                    '</td> ' +
                    /*'<td class="text-center text-nowrap">' +
                    '<input type="text" placeholder="Address" id="guestAddr'+count+'" name="guestAddr">' +
                    '</td> ' +*/
                    '</tr> '
                );
            }
            if (total > 0 && $('#bookingFor').val() == 'self') {
                $('#guestName1').val(guestName);
                //$('#guestAddr1').val(guestAddr);
                $('#guestGender1').val(guestGender);
                $('#guestDepartment1').val(guestDepartment);
                $('#guestAge1').val(guestAge);
            }
        } else if (category == 'C4') {
        	$('#guestDetailsWrapper').html('');
            $('#guestDetailsWrapper1').html('');
            for (let i = 0; i < total; i++) {
                let count = i + 1;
                $('#guestDetailsWrapper1').append(
                    '<tr id=tr' + count + '> ' +
                    '<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
                    '<td class="text-center text-nowrap">' +
                    '<input type="hidden" name="guestId">' +
                    '<input type="hidden" name="guestSlNo" value="' + count + '">' +
                    '<input type="text" placeholder="Name" name="guestName" id="guestName'+count+'" autocomplete="off" onkeypress="toCapitaLiaze(event)">' +
                    '</td> ' +
                    '<td class="text-center text-nowrap">' +
                    '<input type="number" placeholder="Age in years"  min="1" name="guestAge" id="guestAge'+count+'" autocomplete="off">' +
                    '</td> ' +
                    '<td class="text-center text-nowrap"> ' +
                    '<select name="guestGender" id="guestGender'+count+'"> ' +
                    '<option>---Select---</option> ' +
                    '<option value="M">Male</option> ' +
                    '<option value="F">Female</option> ' +
                    '<option value="O">Other</option> ' +
                    '</select> ' +
                    '<input type="hidden" name="guestDesignation" value="">' +
                    '<input type="hidden" name="guestDepartment" value="">' +
                    '</td> ' +
                    /*'<td class="text-center text-nowrap">' +
                    
                    '<input type="text" placeholder="Address" id="guestAddr'+count+'" name="guestAddr">' +
                    '</td> ' +*/
                    '</tr> '
                );
            }
            if (total > 0 && $('#bookingFor').val() == 'self') {
                $('#guestName1').val(guestName);
               // $('#guestAddr1').val(guestAddr);
                $('#guestGender1').val(guestGender);
                $('#guestAge1').val(guestAge);
            }
        }
    });

    $('#bookingFor').on('change', function() {
        var bookingFor = $('#bookingFor').val();
        if (bookingFor == 'others') {
            $('#otherGuestDetails').show();
            $('#offAddr').val('');
        } else {
            $('#otherGuestDetails').hide();
        }
        $('#guestDetailsWrapper').html('');
        $('#guestDetailsWrapper1').html('');
        $('#noOfAdults').val('');
        $('#noOfChildren').val('');
        if($(this).val() == "self") {
            $('#noOfAdults').val(1);
            $('#noOfAdults').trigger('blur');
        }
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
    //         'placeholder="Enter name" > ' +
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
        if(adultCount < 1) {
            alert('Please add at least 1 adult');
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
        if(childCount < child || adultCount < adult){
            alert('Please check the number of children and adults');
            return false;
        }
        return true;
    }

    var submitBookingDetails = function (type) {
    	if($("#bookingFor").val()=="" ||  $("#bookingFor").val()==null){
			swal("Please select Booking For");
			return false;
		}
		if($("#purp").val()=="" || $("#purp").val()==null){
			swal("Please select Purpose of visit");
			return false;
		}
		if($("#desg").val()=="" || $("#desg").val()==null){
			swal("Please select Designation");
			return false;
		}
		var bookingFor = $('#bookingFor').val();
        if (bookingFor == 'others') {
        	if($("#gMobile").val()=="" || $("#gMobile").val()==null){
				swal("Please fill Guest Mobile No");
				return false;
			}
			if($("#gDocType").val()=="" || $("#gDocType").val()==null){
				swal("Please select Guest Document");
				return false;
			}
			if($("#gDoc").val()=="" || $("#gDoc").val()==null){
				swal("Please Select Guest Document");
				return false;
			}
        }
        console.log('form valid');
        var formData = $('#newRoomBookingApplication').serializeArray();
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
                        message: 'field cannot be empty'
                    },
                     regexp: {
					   regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
					   message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
					}
                }
            }, duringstay: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }

            }, supportDoc: {
                validators: {
                    notEmpty: {
                        message: 'Document is required and cannot be Empty.'
                    },

                }
            }, catgType: {
                validators: {
                    notEmpty: {
                        message: 'Document is required and cannot be Empty.'
                    },

                }
            }, categoryDoc: {
                validators: {
                    notEmpty: {
                        message: 'Document is required and cannot be Empty.'
                    },

                }
            }, gDocType: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            }, gDoc: {
                validators: {
                    notEmpty: {
                        message: 'Document is required and cannot be Empty.'
                    },

                }
//             }, travelModeFr: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     }
//                 }
//             }, flNoFr: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     },
//                     regexp: {
//                         regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
//                         message: 'field is not valid'
//                     }
//                 }
//             }, arrDt: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     }
//                 }
//             }, arrPla: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     },
//                     regexp: {
//                         regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
//                         message: 'field is not valid'
//                     }
//                 }
//             }, travelModeTo: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     }
//                 }
//             }, flNoTo: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     },
//                     regexp: {
//                         regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
//                         message: 'field is not valid'
//                     }
//                 }
//             }, depDt: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     }
//                 }
//             }, depPla: {
//                 validators: {
//                     notEmpty: {
//                         message: 'field can\'t be empty'
//                     },
//                     regexp: {
//                         regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
//                         message: 'field is not valid'
//                     }
//                 }
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
    }).on('error.field.bv', function(e, data) {
        if (data.bv.getSubmitButton()) {
            data.bv.disableSubmitButtons(false);
        }
    }).on('success.form.bv', function (e) {
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

            if(travelModeFr == '' || flNoFr == '' || arrDt == '' || arrTm == '' || arrPla == ''){
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

            if(travelModeTo == '' || flNoTo == '' || depDt == '' || depTm == '' || depPla == '') {
                swal("Please fill departure details");
            	return false;
            }
        }
        return true;
    }

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
    var viewDesg;
    $('#view').click(function () {
    	viewDesg = "All";
    	var cat=$('#catg').val();
    	console.log(cat);
    	if(cat != "") {
        	console.log("value of catg")
           viewDesg = cat;
        }
    	 getCatgoryListDtls(viewDesg);
        $('#myModalView').modal('show');           
      
    }); 
    
      var getCatgoryListDtls = function (type) {
        $.ajax({
            url: "./guest/getDesgList/"+type+".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                table.clear().draw();
                table.rows.add(result).draw();
                /*table.columns.adjust().responsive.recalc();*/
            }
        });
    };
    $('#catg').change(function(){
        viewDesg = $(this).val();
        console.log(viewDesg);
        getCatgoryListDtls(viewDesg);
        if(viewDesg !=""){
        $('#myModalView').modal('show');
        }
       
    });

    $('#arrivalRow').addClass('disableElement');
    $('#departureRow').addClass('disableElement');
    $('#arrTravel').on('click', function () {
        if ($(this).is(':checked')) {
            $('#arrivalRow').removeClass('disableElement');
            $(this).val('1');
        } else {
            $('#arrivalRow').addClass('disableElement');
            $(this).val('');
        }
    });
    
    $('#deptTravel').on('click', function () {
        if ($(this).is(':checked')) {
            $('#departureRow').removeClass('disableElement');
            $(this).val('1');
        } else {
            $('#departureRow').addClass('disableElement');
            $(this).val('');
        }
    });
    
    
    $("#hallType").change(function () {
     	 let conCatId=$('#hallType').val();
      params={
      	 gId:getUrlParameter('id'),
     	 conCatId:conCatId
      	};
 		$.ajax({
		url: './guest/getConfrenceTimePeriodDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(params),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
				$("#amount").val("0");
                $('#loading').hide();
               	console.log(result);
               	$("#timePeriod").html("");
               	$("#timePeriod").append(
               	 "<option value=''>---select---</option>"
               	);
               	for(var i=0;i<result.data.length;i++){
               	$("#timePeriod").append(
               		"<option value="+result.data[i].confUseId+">"+result.data[i].confUseName+"</option>"
               	);
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
    });
    
    $("#timePeriod").change(function () {
        
     	 let blockId = $('#gHouse').val();
     	 let conCatId=$('#hallType').val();
     	 let conUseId=$("#timePeriod").val();
     	 if(conUseId=="4"){
     	 	$(".forTwoHrs").show();
     	 }else{
     	 	$(".forTwoHrs").hide();
     	 }
      params={
      	 gId:getUrlParameter('id'),
     	 conCatId:conCatId,
     	 conUseId:conUseId
      	};
      	console.log(params);
 		$.ajax({
		url: './guest/getConfrenceAmount.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(params),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
                $('#loading').hide();
               	var amountWithCapacity=result.data.split(",");
                console.log(amountWithCapacity);
                $("#amount,#amount1").val(amountWithCapacity[0]);
             	$("#capacity").val(amountWithCapacity[1]);  
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
    });
    $("#formSubmissionBtnForConfrence").on("click",function(){
    	if($("#startTime").val()==""&&$("#timePeriod").val()=="4"){
		swal("Please provide start time");
		 $('#formSubmissionBtnForConfrence').prop("disabled", false);
		return false;
	}
	if($("#frmDtCon").val()==""){
		swal("Please provide from date");
		 $('#formSubmissionBtnForConfrence').prop("disabled", false);
		return false;
		
	}
	if($("#advncAamount").val()==""||$("#advncAamount").val()==0){
		 swal("Please provide Advance Amount");
		 $('#formSubmissionBtnForConfrence').prop("disabled", false);
		return false;
		
	}
    });
    	
    $("#startTime").datetimepicker({
       format: 'HH:mm',
       minDate: moment({ hour: 8, minute: 0 }),
       maxDate: moment({ hour: 20, minute: 0 })
    });
    $("#startTime").on("dp.change", function (e) {
	 	const timeString =  $("#startTime").val();

		// Parse the time string into a Date object
		const time = new Date(`2000-01-01T${timeString}:00`);

		// Add two hours to the time using setHours()
		time.setHours(time.getHours() + 2);

		// Format the resulting date object back into HH:mm format
		const newTimeString = time.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", hour12: false });
		$("#endTime").val(newTimeString);
		$("#endTimehidden").val(newTimeString);
		forDisabledProperty();
	 }); 
	 // Define the forTermCondition function
	var forTermCondition = function() {
		let blockIdForTerm=getUrlParameter('id');
	    if (blockIdForTerm != "06") {
	        $("#inclusivexclusive").html("The tariff will be inclusive of Electricity and Service charges.");
	    } else {
	        $("#inclusivexclusive").html("The tariff will be exclusive of Electricity, AC, and Service charges.");
	    }
	    if (blockIdForTerm == "04") {
	        $(".mumbaiPoint").show();
	    } else {
	        $(".mumbaiPoint").hide();
	    }
}
    forTermCondition();
});

function dayDiff(d1, d2) {
    d2 = moment(d2, 'DD-MM-YYYY').toDate();
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

function getConfrenceHalltype(){
      var blockId = getUrlParameter('id');
      if(blockId==null || blockId==""){
    	  blockId=$("#blockid").val();
      }
      params={
      gId:blockId
      };
 		$.ajax({
		url: './guest/getConfrenceTypeDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(params),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
                $('#loading').hide();
               	console.log(result);
               	$("#hallType").html("");
               	$("#hallType").append(
               	 "<option value=''>---select---</option>"
               	);
               	for(var i=0;i<result.data.length;i++){
               	$("#hallType").append(
               		"<option value="+result.data[i].confCatId+">"+result.data[i].confCatName+"</option>"
               	);
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
function checkAvailbility(){
	     var postData={
	     	conUseId:$("#timePeriod").val(),
	     	conCatId:$("#hallType").val(),
	     	formDate:$("#frmDtCon").val(),
	     	startTime:$("#startTime").val(),
	     	endTime:$("#endTime").val(),
	     	gId:getUrlParameter('id')
	     };
	   $("#checkAvb").hide();
	   $("#forIconChange").html('<i class="fa fa-circle-o-notch fa-spin" style="font-size:20px;margin-top:10px;"></i>');
       $.ajax({
            url: "./guest/checkAvailability.do",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
            	console.log(data);
            	if(data=="0"){
            		$("#forIconChange").html('<i class="fa fa-check" style="font-size:20px;margin-top:10px;color:green;"></i>');
            		$("#aviabCheck").html("Conference Hall Available this time");
            		$("#formSubmissionBtnForConfrence").show();
            	}else{
            		$("#forIconChange").html('<i class="fa fa-close" style="font-size:20px;margin-top:10px;color:red;"></i>');
            		$("#aviabCheck").html("Conference Hall Not Available this time");
            		$("#formSubmissionBtnForConfrence").hide();
            	}
            },
            error:function(error, textStatus, jqXHR){
            
            }
         })
}
function toCapitaLiaze(event){
var str=event.target.value;
const arr = str.split(" ");
for (var i = 0; i < arr.length; i++) {
    arr[i] = arr[i].charAt(0).toUpperCase() + arr[i].slice(1);
}
const str2 = arr.join(" ");
document.getElementById(event.target.id).value=str2;
}


