$('document').ready(function () {
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });

    var cityData = $('#cityUserlocssd').val().trim();
    var cityDetails = JSON.parse(cityData);
    fixShift();
    $("#city").html("");
    $("#city").append('<option value="">--Select City--</option>');
    cityDetails.forEach(option => {
        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
    })
    let size = Object.keys(cityDetails).length;
    if (size == 1) {
        $('#city').val(cityDetails[0].gh_id);
        setTimeout(function () {
            $('#city').trigger('change');
        }, 1000);
    }

    $("#closeShift").hide();
    $("#addShift").show();
    $("#changeShift").hide();
    $("#addShifting").hide();
    $("#shiftReport").hide();

 	$("#fromDt, #toDt, #changeFrmDt, #changeToDt").datetimepicker({
        useCurrent: false,
        minDate: moment(),
        maxDate: moment().add(1, 'second'),
        format: 'DD-MM-YYYY HH:mm'
    });

    $('#change').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    $('#change').addClass('custom-btn-active');
                    $('#add').removeClass('custom-btn-active');
                    $('#close').removeClass('custom-btn-active');
                    $('#report').removeClass('custom-btn-active');
                    $("#addShift").hide();
                    $("#shiftReport").hide();
                    $("#changeShift").show();
                    $('#closeShiftBtn').hide();
                    $('#updateShift').show();
                    $('#headerName').html("Change Shift");
                    getCurrentShift(cityId, blockId, "UPDATE");
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
    })
    $('#close').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    $('#change').removeClass('custom-btn-active');
                    $('#add').removeClass('custom-btn-active');
                    $('#close').addClass('custom-btn-active');
                    $('#report').removeClass('custom-btn-active');
                    $("#addShift").hide();
                    $("#changeShift").show();
                    $('#closeShiftBtn').show();
                    $('#updateShift').hide();
                    $("#shiftReport").hide();
                    $('#headerName').html("Close Shift");
                    // $("#closeShift").show();
                    getCurrentShift(cityId, blockId, "CLOSE");
                } else if (status == "false") {
                    if ($('#sameUser').val() == "true") {
                        $('#change').removeClass('custom-btn-active');
                        $('#add').removeClass('custom-btn-active');
                        $('#close').addClass('custom-btn-active');
                        $('#report').removeClass('custom-btn-active');
                        $("#addShift").hide();
                        $("#shiftReport").hide();
                        $("#changeShift").show();
                        $('#closeShiftBtn').show();
                        $('#updateShift').hide();
                        $('#headerName').html("Close Shift");
                        getCurrentShift(cityId, blockId, "CLOSE");
                        // $("#closeShift").show();
                    } else {
                        let msg = $('#shiftMsg').val();
                        swal(msg);
                        return false;
                    }
                } else {
                    swal("Please open shift");
                    return false;
                }
            }, 1000);
        }
    });
    $('#add').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    swal("Shift already open");
                    return false;
                } else if (status == "false") {
                    let msg = $('#shiftMsg').val();
                    if (msg == "Please Open a Shift") {
                        $('#add').addClass('custom-btn-active');
                        $('#change').removeClass('custom-btn-active');
                        $('#close').removeClass('custom-btn-active');
                        $('#report').removeClass('custom-btn-active');
                        $("#addShift").show();
                        $("#changeShift").hide();
                        $("#shiftReport").hide();
                        // $("#closeShift").hide();
                        $("#addShifting").show();
                    } else {
                        swal(msg);
                        return false;
                    }
                } else {
                    swal("Please open shift");
                    return false;
                }
            }, 1000);
        }
    })
    
    $('#report').on('click', function () {
        /*if (!isCityAndGHSelected())
            return false;*/
    	 $('#add').removeClass('custom-btn-active');
         $('#change').removeClass('custom-btn-active');
         $('#close').removeClass('custom-btn-active');
         $('#report').addClass('custom-btn-active');
         
        $("#addShift").hide();
        $("#changeShift").hide();
        $("#shiftReport").show();
        /*getShiftReportList();*/
		shiftReportTable.ajax.reload();
    })
               
   var shiftReportTable = $("#shiftReportDataTable").DataTable({
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
        "url": "./admin/getShiftReportList.do",
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
            d.shiftId = $('#filterShiftId').val();
            d.createdBy = $('#filterCreatedBy').val();
            d.createdDate = $('#filterCreatedDate').val();
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
            "className": "text-center text-nowrap",
            "targets": "_all"
        },
        { "targets": 0, "data": "shiftId" },
        { "targets": 1, "data": "userId" },
        { "targets": 2, "data": "cityId" },
        { "targets": 3, "data": "blockId" },
        { "targets": 4, "data": "createdTime" },
        { "targets": 5, "data": "closeTime" }
    ]
});

	
	$('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });   
	
	$("#filterCreatedDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
    $('#filterSearch').click(function () {        
    	/*getShiftReportList();*/
		shiftReportTable.ajax.reload();
    	$('#filter-popover').unbind();   
    	setTimeout(function(){
    		$('#filter-popover').bind('click',preventClick);
    	}, 1000);
    });
    
    function preventClick() {
       event.stopPropagation();
    }
    
	
	$('#resetFiterBtn').click(function () {
        $('#filterShiftId').val('');      
        $('#filterCreatedBy').val('');
        $('#filterCreatedDate').val('');
        $('#city').val('');
        $('#gHouse').val('');
        /*getShiftReportList();*/
		shiftReportTable.ajax.reload();
    });
	

    $('#city').on('change', function () {
        var id = $('#city').val();
        getGuestHouseListDtls(id);
    });
    var getGuestHouseListDtls = function (id) {
        $.ajax({
            url: "./admin/getGuestHouseListDetails/" + id + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
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

    var getCityListDtls = function () {
        $.ajax({
            url: "./admin/getCityDetails.do",
            type: "POST",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $("#city").html("");
                $("#city").append('<option value="">Select City</option>');
                data.forEach(option => {
                    $("#city").append('<option value="' + option.id + '">' + option.name + '</option>');
                })
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

    let isCityAndGHSelected = function () {
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

    // $('#add').on('click', function () {
    //     if (!isCityAndGHSelected()){
    //     	$("#add").removeClass("custom-btn-active");
    //         return false;
    //     }
    //     if (gHouse == "") {
    //         $("#addShifting").hide();
    //     } else {
    //         $("#addShifting").show();
    //     }
    // });

    $("#gHouse").change(function () {
        let gHouse = $('#gHouse').val();
        $('#add').removeClass('custom-btn-active');
        $('#change').removeClass('custom-btn-active');
        $('#close').removeClass('custom-btn-active');
        $('#report').removeClass('custom-btn-active');
        $("#addShift").show();
        $("#changeShift").hide();
        $("#closeShift").hide();
        $("#addShifting").hide();
        $("#shiftReport").hide();
        console.log(gHouse.length)
    });

    $('#addShifting').on('click', function () {
        if (!isCityAndGHSelected()) {
            fixShift();
            return false;
        }
        saveShiftData();
    });

    $('#opeBal').on('keyup keydown', function () {
        var openBalRefund = parseInt($('#opeBal').val());
        var openBalRegular = parseInt($('#opeBalR').val());
        var total = openBalRefund + openBalRegular
        $('#opeBalT').val(total);
    });

    $('#opeBalR').on('keyup keydown', function () {
        var openBalRefund = parseInt($('#opeBal').val());
        var openBalRegular = parseInt($('#opeBalR').val());
        var total = openBalRefund + openBalRegular;
        $('#opeBalT').val(total);
    });

    $('#changeOpenBal').on('keyup', function () {
        var openBalRefund = parseInt($('#changeOpenBal').val());
        var openBalRegular = parseInt($('#changeOpeBalR').val());
        var total = openBalRefund + openBalRegular
        $('#changeOpeBalT').val(total);
    });

    $('#changeOpeBalR').on('keyup', function () {
        var openBalRefund = parseInt($('#changeOpenBal').val());
        var openBalRegular = parseInt($('#changeOpeBalR').val());
        var total = openBalRefund + openBalRegular;
        $('#changeOpeBalT').val(total);
    });

    function fixShift() {
        var shiftNo = $('#shiftNo').val();
        console.log('called')
        $("#firstSft").prop("disabled", true);
        $("#secondSft").prop("disabled", true);
        $("#thirdSft").prop("disabled", true);
        $("#firstShift, #secondShift, #ThirdShift").addClass("text-secondary");
        if (shiftNo == 1) {
            $("#firstSft").prop("disabled", false);
            $("#firstSft").prop("checked", true);
            $("#firstShift").removeClass("text-secondary");
        } else if (shiftNo == 2) {
            $("#secondSft").prop("disabled", false);
            $("#secondSft").prop("checked", true);
            $("#secondShift").removeClass("text-secondary");
        } else {
            $("#thirdSft").prop("disabled", false);
            $("#thirdSft").prop("checked", true);
            $("#ThirdShift").removeClass("text-secondary");
        }
    }

    var saveShiftData = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        var opentime = $('#fromDt').val();
        var openBalRefund = $('#opeBal').val();
        var openBalRegular = $('#opeBalR').val();
        var openBalTotal = $('#opeBalT').val();
        var shift = $('input[name="receptionistShift"]:checked').val();
        if (opentime == '') {
            swal("Provide Open time");
            return false;

        }
        if (openBalRefund == '') {
            swal("Provide Opening Balance Refund");
            return false;
        }
        if (openBalRegular == '') {
            swal("Provide Opening Balance Regular");
            return false;
        }

        var postData = {
            cityName: city,
            guesthouseName: gHouse,
            openTime: opentime,
            openBalRefund: openBalRefund,
            openBalRegular: openBalRegular,
            openBalTotal: openBalTotal,
            shift: shift
        }
        $.ajax({
            url: './admin/saveNewShiftData.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                console.log(data);
                swal(data.msg);
                $('#receptionistShift').trigger("reset");
                fixShift();
                $("#addShifting").hide();
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

    let getCurrentShift = (cityId, blockId, type) => {
        $.ajax({
            url: './admin/getCurrentShift/' + cityId + '/' + blockId + '.do',
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                console.log(data);
                if (type == 'UPDATE') {
                    $('#changeToDt').prop('disabled', true);
                    $('#changeFrmDt').prop('disabled', false);
                    $('#changeToDt').val('');
                    $('#changeOpenBal').prop('disabled', false);
                } else if (type == 'CLOSE') {
                    $('#changeFrmDt').prop('disabled', true);
                    $('#changeFrmDt').val(data.data.createdTime);
                    $('#changeToDt').prop('disabled',false);
                    $('#changeOpenBal').prop('disabled', true);
                }
                $('#changeFrmDt').val(data.data.createdTime);
                $('#changeShiftId').val(data.data.shiftId);
                $('#changeOpenBal').val(data.data.openBalRefund);
                $('#changeOpeBalR').val(data.data.openBalRegular);
                $('#changeOpeBalT').val(data.data.openBalTotal);
                let shift = data.data.shiftNum;
                if (shift == 1) {
                    $("#changeFirstSft").prop("checked", true);
                    $("#changeShift1").prop("hidden", false);
                } else if (shift == 2) {
                    $("#changeSecondSft").prop("checked", true);
                    $("#changeShift2").prop("hidden", false);
                } else {
                    $("#changeThirdSft").prop("checked", true);
                    $("#changeShift3").prop("hidden", false);
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

    $('#updateShift').on('click', function () {
        let shiftId = $('#changeShiftId').val();
        let fromTime = $('#changeFrmDt').val();
        let openBalRefund = $('#changeOpenBal').val();
        let openBalRegular = $('#changeOpeBalR').val();
        let openBalTotal = $('#changeOpeBalT').val();

        if (fromTime == '') {
            swal("Provide Open Time");
            return false;

        }
        if (openBalRefund == '') {
            swal("Provide Opening Balance Refund");
            return false;
        }
        if (openBalRegular == '') {
            swal("Provide Opening Balance Regular");
            return false;
        }

        let postData = {
            shiftId: shiftId,
            createdTime: fromTime,
            openBalRefund: openBalRefund,
            openBalRegular: openBalRegular,
            openBalTotal: openBalTotal
        }

        $.ajax({
            url: './admin/updateShiftData.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                console.log(data);
                $('#loading').hide();
                swal(data.msg);
            }
        });
    });

    $('#closeShiftBtn').on('click', function () {
        let shiftId = $('#changeShiftId').val();
        let toTime = $('#changeToDt').val();
        let openBalRefund = $('#changeOpenBal').val();
        let openBalRegular = $('#changeOpeBalR').val();
        let openBalTotal = $('#changeOpeBalT').val();

        if (toTime == '') {
            swal("Provide Close Time");
            return false;

        }
        if (openBalRefund == '') {
            swal("Provide Opening Balance Refund");
            return false;
        }
        if (openBalRegular == '') {
            swal("Provide Opening Balance Regular");
            return false;
        }

        let postData = {
            shiftId: shiftId,
            closeTime: toTime,
            openBalRefund: openBalRefund,
            openBalRegular: openBalRegular,
            openBalTotal: openBalTotal
        }

        $.ajax({
            url: './admin/closePreviousShift.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                console.log(data);
                $('#loading').hide();
                swal(data.msg);
                $('#gHouse').trigger("change");
            }
        });
    });
});