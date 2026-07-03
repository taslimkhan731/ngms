$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
    $("#myModalViewForCategory").on("hidden.bs.modal", function () {
        $(this).find('form').trigger('reset');
    });
    $('#cashShow').hide();
	$('#posShow').hide();
	$('#pPostx').hide();
	$('#onlineShow').hide();
    var blockId = 0;
    var current_fs, next_fs, previous_fs; // fieldsets
    var opacity;
    var bookingIdForBill;
    var billIdForBill;
    var current = 1;
    var steps = $("fieldset").length;
    var feedbackData;
    setProgressBar(current);

    $('body').on('click', "input[name='foodradio']", function () {
        /* $("input[name='foodradio']").click(function () { */
        /* emoji color */
        $(this).parent().parent().children('label').each(function (index) {
            i = parseInt(index + 1);
            $(this).removeClass('label-radio' + i).addClass('lbl-grey');
            if (index == 0) {
                $(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
            } else if (index == 1) {
                $(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
            } else if (index == 2) {
                $(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
            } else if (index == 3) {
                $(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
            } else if (index == 4) {
                $(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
            }
        });

        if ($('input[name="foodradio"]:checked').val() == 1) {

            $(this).parent().removeClass('lbl-grey').addClass('label-radio5');
            $(this).prev().addClass('fifthicon-bold');
        } else if ($('input[name="foodradio"]:checked').val() == 2) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio4');
            $(this).prev().addClass('fouricon-bold');
        } else if ($('input[name="foodradio"]:checked').val() == 3) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio3');
            $(this).prev().addClass('thirdicon-bold');
        } else if ($('input[name="foodradio"]:checked').val() == 4) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio2');
            $(this).prev().addClass('secondicon-bold');
        } else if ($('input[name="foodradio"]:checked').val() == 5) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio1');
            $(this).prev().addClass('firsticon-bold');
        }

    });


    $('body').on('click', "input[name='housekeeping']", function () {
        /* $("input[name='housekeeping']").click(function () { */
        /* emoji color */
        $(this).parent().parent().children('label').each(function (index) {
            i = parseInt(index + 1);
            $(this).removeClass('label-radio' + i).addClass('lbl-grey');
            /*
		 * console.log(i); console.log($(this).attr('class'));
		 */
            if (index == 0) {
                $(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
            } else if (index == 1) {
                $(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
            } else if (index == 2) {
                $(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
            } else if (index == 3) {
                $(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
            } else if (index == 4) {
                $(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
            }
        });

        if ($('input[name="housekeeping"]:checked').val() == 1) {

            $(this).parent().removeClass('lbl-grey').addClass('label-radio5');
            $(this).prev().addClass('fifthicon-bold');
        } else if ($('input[name="housekeeping"]:checked').val() == 2) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio4');
            $(this).prev().addClass('fouricon-bold');
        } else if ($('input[name="housekeeping"]:checked').val() == 3) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio3');
            $(this).prev().addClass('thirdicon-bold');
        } else if ($('input[name="housekeeping"]:checked').val() == 4) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio2');
            $(this).prev().addClass('secondicon-bold');
        } else if ($('input[name="housekeeping"]:checked').val() == 5) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio1');
            $(this).prev().addClass('firsticon-bold');
        }

    });


    $('body').on('click', "input[name='checkincheckout']", function () {
        console.log("ok working")
        /*
	 * }); $("input[name='checkincheckout']").click(function () {
	 */
        /* emoji color */
        $(this).parent().parent().children('label').each(function (index) {
            i = parseInt(index + 1);
            $(this).removeClass('label-radio' + i).addClass('lbl-grey');
            console.log(i);
            console.log($(this).attr('class'));
            if (index == 0) {
                $(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
            } else if (index == 1) {
                $(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
            } else if (index == 2) {
                $(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
            } else if (index == 3) {
                $(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
            } else if (index == 4) {
                $(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
            }
        });

        if ($('input[name="checkincheckout"]:checked').val() == 1) {

            $(this).parent().removeClass('lbl-grey').addClass('label-radio5');
            $(this).prev().addClass('fifthicon-bold');
        } else if ($('input[name="checkincheckout"]:checked').val() == 2) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio4');
            $(this).prev().addClass('fouricon-bold');
        } else if ($('input[name="checkincheckout"]:checked').val() == 3) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio3');
            $(this).prev().addClass('thirdicon-bold');
        } else if ($('input[name="checkincheckout"]:checked').val() == 4) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio2');
            $(this).prev().addClass('secondicon-bold');
        } else if ($('input[name="checkincheckout"]:checked').val() == 5) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio1');
            $(this).prev().addClass('firsticon-bold');
        }

    });

    $('body').on('click', "input[name='conduct']", function () {
        /* $("input[name='conduct']").click(function () { */
        /* emoji color */
        $(this).parent().parent().children('label').each(function (index) {
            i = parseInt(index + 1);
            $(this).removeClass('label-radio' + i).addClass('lbl-grey');
            console.log(i);
            console.log($(this).attr('class'));
            if (index == 0) {
                $(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
            } else if (index == 1) {
                $(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
            } else if (index == 2) {
                $(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
            } else if (index == 3) {
                $(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
            } else if (index == 4) {
                $(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
            }
        });

        if ($('input[name="conduct"]:checked').val() == 1) {

            $(this).parent().removeClass('lbl-grey').addClass('label-radio5');
            $(this).prev().addClass('fifthicon-bold');
        } else if ($('input[name="conduct"]:checked').val() == 2) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio4');
            $(this).prev().addClass('fouricon-bold');
        } else if ($('input[name="conduct"]:checked').val() == 3) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio3');
            $(this).prev().addClass('thirdicon-bold');
        } else if ($('input[name="conduct"]:checked').val() == 4) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio2');
            $(this).prev().addClass('secondicon-bold');
        } else if ($('input[name="conduct"]:checked').val() == 5) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio1');
            $(this).prev().addClass('firsticon-bold');
        }
    });

    $('body').on('click', "input[name='clean']", function () {
        /* $("input[name='clean']").click(function () { */
        /* emoji color */
        $(this).parent().parent().children('label').each(function (index) {
            i = parseInt(index + 1);
            $(this).removeClass('label-radio' + i).addClass('lbl-grey');
            console.log(i);
            console.log($(this).attr('class'));
            if (index == 0) {
                $(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
            } else if (index == 1) {
                $(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
            } else if (index == 2) {
                $(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
            } else if (index == 3) {
                $(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
            } else if (index == 4) {
                $(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
            }
        });

        if ($('input[name="clean"]:checked').val() == 1) {

            $(this).parent().removeClass('lbl-grey').addClass('label-radio5');
            $(this).prev().addClass('fifthicon-bold');
        } else if ($('input[name="clean"]:checked').val() == 2) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio4');
            $(this).prev().addClass('fouricon-bold');
        } else if ($('input[name="clean"]:checked').val() == 3) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio3');
            $(this).prev().addClass('thirdicon-bold');
        } else if ($('input[name="clean"]:checked').val() == 4) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio2');
            $(this).prev().addClass('secondicon-bold');
        } else if ($('input[name="clean"]:checked').val() == 5) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio1');
            $(this).prev().addClass('firsticon-bold');
        }

    });

    $('body').on('click', "input[name='overall']", function () {
        /* $("input[name='overall']").click(function () { */
        /* emoji color */
        var i = 0;
        $(this).parent().parent().children('label').each(function (index) {
            i = parseInt(index + 1);
            $(this).removeClass('label-radio' + i).addClass('lbl-grey');
            console.log(i);
            console.log($(this).attr('class'));
            if (index == 0) {
                $(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
            } else if (index == 1) {
                $(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
            } else if (index == 2) {
                $(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
            } else if (index == 3) {
                $(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
            } else if (index == 4) {
                $(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
            }
        });

        if ($('input[name="overall"]:checked').val() == 1) {

            $(this).parent().removeClass('lbl-grey').addClass('label-radio5');
            $(this).prev().addClass('fifthicon-bold');
        } else if ($('input[name="overall"]:checked').val() == 2) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio4');
            $(this).prev().addClass('fouricon-bold');
        } else if ($('input[name="overall"]:checked').val() == 3) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio3');
            $(this).prev().addClass('thirdicon-bold');
        } else if ($('input[name="overall"]:checked').val() == 4) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio2');
            $(this).prev().addClass('secondicon-bold');
        } else if ($('input[name="overall"]:checked').val() == 5) {
            $(this).parent().removeClass('lbl-grey').addClass('label-radio1');
            $(this).prev().addClass('firsticon-bold');
        }

    });
    $('body').on('click', "#select", function () {
        /* $("#select").click(function() { */

        console.log("clicked");
        console.log(feedbackData);
        $('#myModalVieww').modal('show');
        $('#name1').val(feedbackData.name);
        $('#mobile1').val(feedbackData.mobile);
        $('#requestId1').val(feedbackData.bookingId);
        $('#desig').val(feedbackData.desg);
        var foodvalueval = $("input[name='foodradio']:checked").val();
        var housekeepingvalue = $("input[name='housekeeping']:checked").val();
        var checkincheckoutvalue = $("input[name='checkincheckout']:checked").val();
        var conductvalue = $("input[name='conduct']:checked").val();
        var cleanvalue = $("input[name='clean']:checked").val();
        var overall = $("input[name='overall']:checked").val();
        var sms = $('#description').val();
        $('input[name="foodradio"][type="radio"][value="' + foodvalueval + '"]').prop('checked', true);
        $('input[name="housekeeping"][type="radio"][value="' + housekeepingvalue + '"]').prop('checked', true);
        $('input[name="checkincheckout"][type="radio"][value="' + checkincheckoutvalue + '"]').prop('checked', true);
        $('input[name="conduct"][type="radio"][value="' + conductvalue + '"]').prop('checked', true);
        $('input[name="clean"][type="radio"][value="' + cleanvalue + '"]').prop('checked', true);
        $('input[name="overall"][type="radio"][value="' + overall + '"]').prop('checked', true);
        $('#description1').val(sms);
    })
    $(function () {

        $('body').on('change', "input[name='checkincheckout']", function () {
            $("#checkinbtn").prop('disabled', false);
        });
        $('body').on('change', "input[name='housekeeping']", function () {
            $("#housekeepingbtn").prop('disabled', false);
        });
        $('body').on('change', "input[name='clean']", function () {
            $("#cleanbtn").prop('disabled', false);
        });
        $('body').on('change', "input[name='foodradio']", function () {
            $("#foodbtn").prop('disabled', false);
        });
        $('body').on('change', "input[name='conduct']", function () {
            $("#conductbtn").prop('disabled', false);
        });
        $('body').on('change', "input[name='overall']", function () {
            $("#overallbtn").prop('disabled', false);
        });
    });
    $('body').on('click', ".next", function () {
        current_fs = $(this).parent();
        next_fs = $(this).parent().next();
        // Add Class Active
        $("#msform").find("#progressbar li").eq(current).addClass("active");
        $("#msform").find("#progressbar li strong").eq(current).addClass("text-dark");
        next_fs.show();
        // hide the current fieldset with style
        current_fs.animate({
            opacity: 0
        }, {
            step: function (now) {
                // for making fielset appear
                // animation
                opacity = 1 - now;

                current_fs.css({
                    'display': 'none',
                    'position': 'relative'
                });
                next_fs.css({
                    'opacity': opacity
                });
            },
            duration: 500
        });
        setProgressBar(++current);
    });

    $('body').on('click', ".previous", function () {

        current_fs = $(this).parent();
        previous_fs = $(this).parent().prev();

        $("#msform").find("#progressbar li").eq(--current).removeClass("active");

        $("#msform").find("#progressbar li strong").eq(current).removeClass("text-dark");
        previous_fs.show();

        current_fs.animate({
            opacity: 0
        }, {
            step: function (now) {
                opacity = 1 - now;
                current_fs.css({
                    'display': 'none',
                    'position': 'relative'
                });
                previous_fs.css({
                    'opacity': opacity
                });
            },
            duration: 500
        });
        setProgressBar(current);
    });

    function setProgressBar(curStep) {
        var percent = parseFloat(100 / steps) * curStep;
        percent = percent.toFixed();
        $(".progress-bar").css("width",
            percent + "%")
    }


    var originalModal = $('#feedbackModal').clone();
    var viewModal = $('#myModalVieww').clone();
    $('body').on('hidden.bs.modal', "#feedbackModal", function () {
        /* $("#feedbackModal").on("hidden.bs.modal", function(){ */
        $('#feedbackModal').remove();
        $('#myModalVieww').remove();
        var myClone = originalModal.clone();
        $("body").append(myClone);
        $("body").append(viewModal);
        current = 1;
        setProgressBar(current);

    });
    $('body').on('click', "#submitbtn", function () {
        postData = {
            "name": $('#name').val(),
            "mobile": $('#mobileNO').val(),
            "requestId": $('#requestId').val(),
            "food": $("input[name='foodradio']:checked").val(),
            "housekeeping": $("input[name='housekeeping']:checked").val(),
            "checkincheckout": $("input[name='checkincheckout']:checked").val(),
            "conduct": $("input[name='conduct']:checked").val(),
            "clean": $("input[name='clean']:checked").val(),
            "overall": $("input[name='overall']:checked").val(),
            "feedbacksms": $('#description').val()
        }
        $.ajax({
            url: './guest/savefeedbackDetails.do',
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(postData),
            contentType: "application/json",
            dataType: "JSON",
            success: function (result) {
                console.log(result);
                swal(result.message);
                $('#myModalView').modal('hide');
                /*
										getFeedbackDetails();*/

                $('#feedbackModal').modal('hide');
                /*getFeedbackDetails();*/
                getNomineeRequestList(table, contable);
            }
        });

    });


    // $('#docTypediv').hide();
    $('#conReq').hide();
    $('#chec').show();
// $("#frmDt, #toDt, #arrDt, #depDt").datetimepicker({
// useCurrent: false,
// minDate: moment(),
// format: 'DD-MM-YYYY'
// });
//
// $("#depTm, #arrTm").datetimepicker({
// format: 'hh:mm A'
// });


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
        $('#arrTravel').prop('checked', false);
        $('#deptTravel').prop('checked', false);
        $('#roomWrapper').show();
        $('#dormintoryWrapper').hide();
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
        $('#roomWrapper').show();
        $('#dormintoryWrapper').hide();
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
        $('#roomWrapper').hide();
        $('#dormitoryWrapper').hide();
    });

    $("#arrDt, #depDt").datetimepicker({
        useCurrent: false,
        minDate: moment(),
        format: 'DD-MM-YYYY'
    });

    $("#frmDt").datetimepicker({
        useCurrent: false,
        minDate: moment().millisecond(0).second(0).minute(0).hour(0),
       /* maxDate: moment().add(7, 'days').endOf('day'),*/
        format: 'DD-MM-YYYY 09:00'
    });

    $("#toDt").datetimepicker({
        useCurrent: false,
        minDate: moment(),
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

    $("#searchDt").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });

    $("#frmDt").on("dp.change", function (e) {
    	$('#toDt').val("");
        $('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
        $('#toDt').data("DateTimePicker").minDate(e.date.add(1, 'days'));
        if ($('#toDt').val() != '') {
            var date1 = convertStringToDatetime($('#frmDt').val());
            var date2 = convertStringToDatetime($('#toDt').val());
            var days = daysBetween(date1, date2);
            $('#noOfDays').val(days);
        }
    });
    $("#toDt").on("dp.change", function (e) {
        $('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'toDt');
        /*$("#frmDt").data("DateTimePicker").maxDate(e.date);*/
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

    $("#extToDt").on("dp.change", function (e) {
        // $('#newRoomBookingApplication').bootstrapValidator('revalidateField',
        // 'toDt');
        if ($('#extFrmDt').val() != '') {
            var date1 = convertStringToDatetime($('#extFrmDt').val());
            var date2 = convertStringToDatetime($('#extToDt').val());
            var days = daysBetween(date1, date2);
            $('#extNoOfDays').val(days);
        }
    });

    // $("#frmDt").on("dp.change", function (e) {
    // $('#existingBookingApplication').bootstrapValidator('revalidateField',
    // 'frmDt');
    // $("#toDt").data("DateTimePicker").minDate(e.date);
    // });
    // $("#toDt").on("dp.change", function (e) {
    // $('#existingBookingApplication').bootstrapValidator('revalidateField',
    // 'toDt');
    // $("#frmDt").data("DateTimePicker").maxDate(e.date);
    // });
    //
    // $("#arrDt").on("dp.change", function (e) {
    // $('#existingBookingApplication').bootstrapValidator('revalidateField',
    // 'arrDt');
    // $("#depDt").data("DateTimePicker").minDate(e.date);
    // });
    // $("#depDt").on("dp.change", function (e) {
    // $('#existingBookingApplication').bootstrapValidator('revalidateField',
    // 'depDt');
    // $("#arrDt").data("DateTimePicker").maxDate(e.date);
    // });

    $("#noOfRooms").bind('keyup keypress mouseup', function () {
        var room = $("#noOfRooms").val();
        if (room == '') {
            $("#noOfRooms").removeClass('disableElement');
            $("#noOfBeds").removeClass('disableElement');
        } else {
            $("#noOfBeds").addClass('disableElement');
            $("#noOfBeds").val('')
        }
    });

    $("#noOfBeds").bind('keyup keypress mouseup', function () {
        var room = $("#noOfBeds").val();
        if (room == '') {
            $("#noOfRooms").removeClass('disableElement');
            $("#noOfBeds").removeClass('disableElement');
        } else {
            $("#noOfRooms").addClass('disableElement');
            $("#noOfRooms").val('')
        }
    });

    $("input[name='bookingCond']").click(function () {
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

    var token = $("meta[name='_csrf']").attr("content");

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


    // $('#catg').on('change', function() {
    // var id = $('#catg').val();
    // getDesignationListDtls(id,'');
    // getPurposeListDtls(id);
    // });

    $('#catg').on('change', function () {
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

    $('#purp').on('change', function () {
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
// $('#desg').on('change', function() {
// var id = $('#desg').val();
// getGuestDesignationDetails(id);
// });

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
                $('#guestDesignation1').val(data == null ? '' : data);
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

    var getDesignationListDtls = function (id, value) {
        $.ajax({
            url: "./guest/getDesignationDetails/" + id + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                $("#desg").html("");
                $("#desg").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    if (option.id == value) {
                        $("#desg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                    } else {
                        $("#desg").append('<option value="' + option.id + '">' + option.name + '</option>');
                    }
                })
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
                swal(error);
            }
        });
    }

    var getFlightTrainList = function (type, cond, sel) {
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

    $('#travelModeFr').on('change', function () {
        var mode = $('#travelModeFr').val();
        if (mode == 'T') {
            getFlightTrainList('t', 'arr', '')
        } else if (mode == 'F') {
            getFlightTrainList('f', 'arr', '')
        }
    });

    $('#travelModeTo').on('change', function () {
        var mode = $('#travelModeTo').val();
        if (mode == 'T') {
            getFlightTrainList('t', 'dep', '')
        } else if (mode == 'F') {
            getFlightTrainList('f', 'dep', '')
        }
    });


    $('#purp').on('change', function () {
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
    $('#catg').on('change', function () {
        var category = $('#catg').val();
        if (category == 'C1' || category == 'C2' || category == 'C3') {
            $('#docTypediv').show();
            $('#category_supporting').show();
        } else {
            $('#docTypediv').hide();
            $('#category_supporting').hide();
        }

    });

    var getPurposeListDtls = function (id, value) {
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
                    if (option.id == value) {
                        $("#purp").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                        // $("#desg").append('<option value="'+option.id+'"
                        // selected="selected">'+option.name+'</option>');
                    } else {
                        $("#purp").append('<option value="' + option.id + '">' + option.name + '</option>');
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

    getCatgoryListDtls('');
    // getPurposeListDtls();

    var convertingGuesetDetailsObjectToArrayOfObjects = function (data) {
        var array = [];
        var count = data.length / guestParameters;
        console.log(data.length);
        for (var i = 0; i < count; i++) {
            array.push(convertSerializeArrayToObject(data.slice((i * guestParameters), ((i + 1) * guestParameters))));
        }
        return array;
    }

    var addGuestDetails = function (count, data, readonly) {
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
                '<input type="text" placeholder="Name" id="guestName' + count + '" name="guestName" onkeypress="toCapitaLiaze(event)">' +
                '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="number" placeholder="Age in years" id="guestAge' + count + '" name="guestAge">' +
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
                '<input type="text" placeholder="Designation" name="guestDesignation" id="guestDesignation' + count + '">' +
                '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="text" placeholder="Department" name="guestDepartment" id="guestDepartment' + count + '">' +
                '</td> ' +
                /*'<td class="text-center text-nowrap">' +
				'<input type="text" placeholder="Address" name="guestAddr" id="guestAddr' + count + '">' +
				'</td> ' +*/
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
                '<input type="text" placeholder="Name" id="guestName' + count + '" name="guestName" onkeypress="toCapitaLiaze(event)">' +
                '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="number" placeholder="Age in years" id="guestAge' + count + '" name="guestAge">' +
                '</td> ' +
                '<td class="text-center text-nowrap"> ' +
                '<select id="guestGender' + count + '" name="guestGender"> ' +
                '<option>---Select---</option> ' +
                '<option value="M">Male</option> ' +
                '<option value="F">Female</option> ' +
                '<option value="O">Other</option> ' +
                '<input type="hidden" id="guestDesignation' + count + '" name="guestDesignation" value="">' +
                '<input type="hidden" id="guestDepartment' + count + '" name="guestDepartment" value="">' +
                '</select> ' +
                '</td> ' +
                /*'<td class="text-center text-nowrap">' +
				'<input type="text" placeholder="Address" name="guestAddr" id="guestAddr' + count + '">' +
				'</td> ' +*/
                '</tr> '
            );
        }
        // $('#guestDetailsWrapper').append(
        // '<tr id=tr' + count + '> '+
        // '<th scope="row"><span class="mt-2" id=guestCo' + count
        // +'>'+count+'</span></th> '+
        // '<th scope="row"><span class="mt-2">'+
        // '<input class="form-check-input" type="checkbox" value=' + count + '
        // id=check' + count + '>'+
        // '</span></th> '+
        // '<td> '+
        // '<div class="row"> '+
        // '<input type="hidden" name="guestId" id="guestId'+count+'"> '+
        // '<input type="text" name="guestName" id="guestName'+count+'"
        // class="form-control col-12" '+
        // 'placeholder="Enter name"> '+
        // '</div> '+
        // '</td> '+
        // '<td> '+
        // '<div class="row"> '+
        // '<input type="number" name="guestAge" id="guestAge'+count+'"
        // class="form-control col-12" '+
        // 'placeholder="Enter age" autocomplete="off" min="1"> '+
        // '</div> '+
        // '</td> '+
        // '<td> '+
        // '<div class="row"> '+
        // '<select class="form-control col-12" id="guestGender'+count+'"
        // name="guestGender"> '+
        // '<option value=""> --- Select --- </option> '+
        // '<option value="M">Male</option> '+
        // '<option value="F">Female</option> '+
        // '<option value="O">Others</option> '+
        // '</select> '+
        // '</div> '+
        // '</td> '+
        // '<td> '+
        // '<div class="row"> '+
        // '<input type="text" name="guestDesignation"
        // id="guestDesignation'+count+'" class="form-control col-12" '+
        // 'placeholder="Enter Designation"> '+
        // '</div> '+
        // '</td> '+
        // '<td> '+
        // '<div class="row"> '+
        // '<input type="text" name="guestDepartment"
        // id="guestDepartment'+count+'" class="form-control col-12" '+
        // 'placeholder="Enter Department"> '+
        // '</div> '+
        // '</td> '+
        // '<td> '+
        // '<div class="row"> '+
        // '<input type="text" name="guestAddr" id="guestAddr'+count+'"
        // class="form-control col-12" '+
        // 'placeholder="Enter address"> '+
        // '</div> '+
        // '</td> '+
        // '</tr>'
        // );
        if (data != undefined) {
            $("#guestId" + count).val(data.guestId);
            $("#guestName" + count).val(data.guestName);
            $("#guestAge" + count).val(data.guestAge);
            $("#guestGender" + count).val(data.guestGender);
            //$("#guestAddr"+count).val(data.guestAddr);
            $("#guestDesignation" + count).val(data.guestDesignation);
            $("#guestDepartment" + count).val(data.guestDepartment);
        }
        if (readonly != undefined ? readonly : false) {
            $("#guestId" + count).addClass('disableElement');
            $("#guestName" + count).addClass('disableElement');
            $("#guestAge" + count).addClass('disableElement');
            $("#guestGender" + count).addClass('disableElement');
            //$("#guestAddr"+count).addClass('disableElement');
            $("#guestDesignation" + count).addClass('disableElement');
            $("#guestDepartment" + count).addClass('disableElement');
            $('#checkRoomWith').addClass('disableElement');
        }
    }

    $('#noOfAdults, #noOfChildren').on('focus', function () {
        if ($('#catg').val() == "") {
            swal("Please select category");
            $('#noOfAdults').val('');
            $('#noOfChildren').val('');
        }
    });

    $('#noOfAdults, #noOfChildren').on('blur', function () {
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

    // $('#addGuestDetailsBtn').on('click', function(){
    // guestCount++;
    // addGuestDetails();
    // });

    // $('#deleteGuestDtlsBtn').on('click', function(){
    // var i,j,len=0;
    // if(guestCount>1){
    // for (i=1; i <= guestCount + len; i++) {
    // if($('#check'+(i-len)).prop("checked")){
    // $('#tr'+(i-len)).remove();
    // for (j=(i+1-len); j <= guestCount; j++) {
    // $('#check'+j).val(j-1);
    // $('#check'+j).attr('id', 'check'+(j-1));
    // $('#tr'+j).attr('id', 'tr'+(j-1));
    // $('#guestCo'+j).html(j-1);
    // $('#guestCo'+j).attr('id', 'guestCo'+(j-1));
    // }
    // len = len + 1;
    // guestCount--;
    // }
    // }
    // }
    // });

    $('#bookingFor').change(function () {
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

    var setGuestDetails = function (bookingId, isReadonly) {
        console.log(bookingId);
        var formData = $('#existingBookingApplication').serializeArray();
        var otherDetails = formData.filter(d => !d.name.match(/guest/g));

        console.log(otherDetails);
        $.ajax({
            url: "./guest/getBookingDetailsByBookingId/" + bookingId + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (r, textStatus, jqXHR) {
                $('#loading').hide();
                console.log(r);
                if (r.status) {
                    bookingReqId = r.data.bookingId;
                    guestList = r.data.list;
                    var id = r.data.catg;
                    $('#catg').val(id);
                    $('#catg').trigger('change');
                    getDesignationListDtls(id, r.data.desg);
                    getPurposeListDtls(id, r.data.purp);
                    setTimeout(function () {
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
                        }
                    } else if (type == 'C') {
                        $('#conv').click();
                        $('#roomWrapper').hide();
                        $('#dormintoryWrapper').hide();
                        if (isReadonly) {
                            $('.cat_all').prop('disabled', true);
                            $('#conv').prop('disabled', false);
                        }
                    }
                    // $('input[name="bookingCond"][type="radio"][value="' +
                    // valu +'"]').prop('checked', true);
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
                        // if(category == 'C1'||category == 'C2'||category ==
                        // 'C3'){
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
                            $('input[name="bookingType"]').removeClass('disableElement');
                            $('select[name="' + item.name + '"]').removeClass('disableElement');
                            $('textarea[name="' + item.name + '"]').removeClass('disableElement');
                            $('.viewOnly').removeClass('disableElement');
                        }
                    });
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
                            $("#convFrDtls").hide()
                        }
                        ;
                        if (!r.data.isDepartueConv) {
                            $("#convToDtls").hide()
                        }
                        ;
                        if (suppdocname != null) {
                            $("#suppDocDtlsBox").show();
                            $("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + suppdocname + '</a>');
                        }
                        if (catgdocname != null) {
                            $("#desgDocDtlsBox").show();
                            $("#desgDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + catgdocname + '</a>')
                        }
                        if (gdocname != null) {
                            $("#gDocDtlsBox").show();
                            $("#gDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + gdocname + '</a>')
                        }
                        if (r.data.status == "PRO" || r.data.status == "CIN" || r.data.status == "COUT" || r.data.status == "BILL" || r.data.status == "RECT") {
                        	if (r.data.roomType == 'C') {
                        		$('#allotmentBlock').hide();
                        	} else {
                        		$('#allotmentBlock').show();
                                $('#roomBedAllotment tbody').empty();
                                let bedStr = '';
                                r.data.roomDetails.forEach(function (drm) {
                                    bedStr += '<tr>' +
                                        '<td class="roomDormfont text-truncate text-center">' + drm.fromDate + '</td>' +
                                        '<td class="text-truncate text-center">' +
                                        '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>' +
                                        '</td> ' +
                                        '<td class="text-truncate text-center">' +
                                        '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                                        '</td> ' +
                                        '</tr>';
                                });
                                $('#roomBedAllotment tbody').append(bedStr);
                        	}
                        }
                        else
                            $('#allotmentBlock').hide();

                        if (r.data.arrivalConv === true) {
                            $('#arrivalRow').removeClass("disableElement");
                            $('#arrTravel').prop('checked', true);
                        }
                        if (r.data.departueConv=== true) {
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
                        $('#allotmentBlock').hide();
                        if (r.data.arrivalConv === true) {
                            $('#arrivalRow').removeClass("disableElement");
                            $('#arrTravel').prop('checked', true);
                            $('#arrTravel').val('1');
                        }
                        if (r.data.departueConv=== true) {
                            $('#departureRow').removeClass("disableElement");
                            $('#deptTravel').prop('checked', true);
                            $('#deptTravel').val('1');
                        }
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

    var table = $("#guestDataTable").DataTable({
        "paging": true,
        "searching": false,
        "responsive": true,
        "scrollX": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "bInfo": false,
        "bFilter": false,
        "bPaginate": false,
        "lengthChange": false,
        "fixedHeader": {
            header: true,
            footer: true
        },
        "scrollCollapse": false,
        "columnDefs": [
            {
                "targets": 0, "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {"targets": 1, "data": "bookingId"},
            {"targets": 2, "data": "ghName"},
            {"targets": 3, "data": "bookingDt"},
            {"targets": 4, "data": "fromDate"},
            {"targets": 5, "data": "toDate"},
            {"targets": 6, "data": "purpose"},
            {"targets": 7, "data": "duringStay"},
            {"targets": 8, "data": "bookingType"},
            {"targets": 9, "data": "statusDesc"},
            {
                "targets": 10, "data": null,
                render: function (data, type, row, meta) {
                    if (data.bookingType == 'Conveyance') {
                        if (data.status == 'REQ') {
                            return '<button type="button" id="view" action="View" title="View" class="btn btn-sm view"><i class="fa fa-eye" aria-hidden="true"></i></button> ' +
                                '<button type="button" id="edit" title="Edit" action="Edit" class="btn btn-sm edit-btn"><i class="fa fa-pencil" aria-hidden="true"></i></button> ' +
                                '<button type="button" action="delete" id="delete" title="Delete" class="btn btn-sm btn-cancel"><i class="fa fa-times" aria-hidden="true"></i></button> ';
                        } else {
                            return '<button type="button" id="view" action="View" title="View" class="btn btn-sm view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
                        }
                    }

                    let str = `<button type="button" id="view" action="View" title="View" class="btn btn-sm view" action="View"><i class="fa fa-eye" aria-hidden="true"></i></button> `
                    if (data.status == 'REQ') {
                        str += `<button type="button" id="edit" title="Edit" class="btn btn-sm edit-btn" action="Edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> ` +
                            `<button type="button" id="delete" title="Delete Application" class="btn btn-sm btn-cancel" action="delete" ><i class="fa fa-trash" aria-hidden="true"></i></button> `
                    } else if (data.status == 'PRO') {
                        if (data.twentyFourCancel > 0) {
                            str += `<button type="button" id="cancel" title="Cancel Reservation" class="btn btn-sm btn-cancel" action="cancel" ><i class="fa fa-times" aria-hidden="true"></i></button> `
                        }
                        // str += `<button type="button" id="cancel" title="Cancel Reservation" class="btn btn-sm btn-cancel" action="cancel" ><i class="fa fa-times" aria-hidden="true"></i></button> `
                    } else if (data.status == 'CIN' || data.status == 'PCIN') {
                        if (data.partialCancel > 0) {
                            str += `<button type="button" id="partialCancel" title="Partial Cancel" class="btn btn-sm btn-cancel" action="partialCancel" ><i class="fa fa-times" aria-hidden="true"></i></button> `
                        }
                        if (data.canStatus == null) {
                            str += `<button type="button" id="extension" action="Extension" title="Extension" class="btn btn-sm extension"><i class="fa fa-plug" aria-hidden="true"></i></button> `
                        }
                    } else if (data.status == 'COUT' || data.status == 'BILL' || data.status == 'RECT') {
                        if (data.feedbackId == null) {
                            str += `<button type="button" id='feedback' action="Feedback" title="Feedback" class="btn btn-sm feedback"><i class="fa fa-comments-o" aria-hidden="true"></i></button> `
                        }
                        if (data.status == 'BILL') {
                            str += `<button type="button" id="billdetails" action="PaymentReceipt" title="Bill Details" class="btn btn-sm bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button> `
                        }
                        if (data.status == 'RECT') {
                            str += `<button type="button" id="billdetails" action="Bill Details" title="Bill Details" class="btn btn-sm bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ` +
                                `<button type="button" id="paymentReceipt" action="PaymentReceipt" title="Payment Receipt" class="btn btn-sm payment"><i class="fa fa-inr" aria-hidden="true"></i></button> `
                        }
                    }
                    if(data.noc == 'UNPAID' && data.pgVerifyStatus=='P'){
                    	str+=`<button type="button" id="noc-payment-check" action="NOC" title="Check NOC Status" class="btn btn-sm noc_sts"><i class="fa fa-money" aria-hidden="true"></i></button> `;
                    }
                    else if(data.noc == 'UNPAID' && data.pgVerifyStatus==null) {
                        str += `<button type="button" id="noc" action="NOC" title="NOC" class="btn btn-sm noc"><i class="fa fa-money" aria-hidden="true"></i></button> `;
                    } else if (data.noc == 'PAID') {
                        str += `<button type="button" id="noc-receipt" action="NOC" title="NOC" class="btn btn-sm noc-receipt"><i class="fa fa-money" aria-hidden="true"></i></button> `;
                    }
                    if(data.duesStatus == 'PAID'){
                       str+= `<button type="button" id="duesReceipt" action="duesReceipt" title="Dues Receipt" class="btn btn-sm duesPayment"><i class="fa fa-inr" aria-hidden="true"></i></button> `
                    }
                    str += `<button type="button" id="receipt" action="Receipt" title="Print" class="btn btn-sm print"><i class="fa fa-print" aria-hidden="true"></i></button> `;
                    return str;
                }
            }
        ],
        createdRow: function (row, data, index) {
            $('td', row).eq(10).addClass('text-nowrap');
        },
    });

    var contable = $("#conDataTable").DataTable({
        "paging": false,
        "searching": false,
        "responsive": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true,
            footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [
            {className: "all", "targets": [0, 1, 2]},
            {
                "targets": 0, "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {"targets": 1, "data": "bookingId"},
            {"targets": 2, "data": "ghName"},
            {"targets": 3, "data": "fromDate"},
            {"targets": 4, "data": "toDate"},
            {"targets": 5, "data": "conveyance"},
            {"targets": 6, "data": "statusDesc"},
            {
                "targets": 7, "data": null,
                render: function (data, type, row, meta) {
                    if (data.status == 'REQ') {
                        return '<button type="button" id="edit" title="Edit" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> ' +
                            '<button type="button" action="cancel" id="cancel" title="Cancel" class="btn btn-secondary btn-sm btn-cancel"><i class="fa fa-times" aria-hidden="true"></i></button> ' +
                            '<button type="button" id="view" action="View" title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
                    } else {
                        return '<button type="button" id="view" action="View" title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
                    }
                }
            }
        ],
    });

    getNomineeRequestList(table, contable);
    $('#guestDataTable').on('click', 'tbody #view', function () {
        $('#applicationFormModal').modal('show');
        $('#applicationHeader').html('View Form')
        var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        blockId = data.cityName;
        guestCount = 0;
        $('#guestDetailsWrapper').html("");
        setGuestDetails(data.bookingId, true);
    });

    $('#guestDataTable').on('click', 'tbody #edit', function () {
        $('#applicationFormModal').modal('show');
        var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        blockId = data.cityName;
        console.log("booking id of the edit button" + data.bookingId);
        guestCount = 0;
        $('#guestDetailsWrapper').html("");
        $('#applicationHeader').html('Application Form Update');
        setGuestDetails(data.bookingId, false);
        $('#arrivalRow').addClass('disableElement');
        $('#departureRow').addClass('disableElement');
    });
 
    $('#guestDataTable').on('click', 'tbody #extension', function () {
        $('#extensionModalView').modal('show');
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        $('#bookingId').val(data_row.bookingId);
        $('#extFrmDt').val(data_row.toDate);
        $('#extToDt').val('');
        $('#extNoOfDays').val('');
        var date = convertStringToDatetime(data_row.toDate);
        date.setDate(date.getDate() + 1);
        $("#extToDt").data("DateTimePicker").minDate(date);
    });

    var getFeedbackDetails = function (id) {
        $.ajax({
            url: "./guest/getFeedbackDetails/" + id + ".do",
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data_row) {
                feedbackData = data_row.data;
                console.log(feedbackData)
                $('#requestId').val(data_row.data.bookingId);
                $('#name').val(data_row.data.name);
                $('#mobileNO').val(data_row.data.mobile);
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


    $('#guestDataTable').on('click', 'tbody  #feedback', function () {
        $('#feedbackModal').modal('show');
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        getFeedbackDetails(data_row.bookingId);

    });

    $("#extBtn").click(function () {
        var bookingId = $('#bookingId').val();
        var frmDt = $('#extFrmDt').val();
        var toDt = $('#extToDt').val();
        var noOfDays = $('#extNoOfDays').val();
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
            reqId: bookingId,
            toDate: g2,
            noOfDays: noOfDays
        }
        $.ajax({
            url: "./guest/updateBookingExtension.do",
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(postData),
            contentType: "application/json",
            dataType: "JSON",
            success: function (result) {
                if (result.success) {
                    $('#extensionModalView').modal('hide');
                    swal(result.msg);
                    getNomineeRequestList(table);
                } else {
                    swal(result.msg);
                }
            }
        });
    });

    $('#guestDataTable').on('click', 'tbody #delete', function () {
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        swal({
                title: "Are you sure? ",
                text: "Are you sure to delete the booking request!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false
            },
            function () {
                $('#loading').show();
                $.ajax({
                    url: './guest/cancelRequest/' + data_row.bookingId + ".do",
                    type: "GET",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (data) {
                        $('#loading').hide();
                        console.log(data);

                        if (data.status && data.isThirdParty == false) {
                            swal(data.msg);
                            getNomineeRequestList(table, contable);
                        } else if (data.status && data.isThirdParty) {
                            console.log(data.cancelEncData);
                            alert(data.cancelEncData);
                            $("#encDataSuccessData").val(data.cancelEncData);
                            $('#successBacktoOdishaOne').attr('action', data.successUrl).submit();
                        } else {
                            swal(data.msg);
                        }
                    }
                });
            });
    });

    $('#guestDataTable').on('click', 'tbody #cancel', function () {
        var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        let checkInDate = moment(data.fromDate, 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY HH:mm');
       /* debugger;*/
        let cancelFromDate = moment().format('DD-MM-YYYY 08:00');
        let current = moment().format('DD-MM-YYYY HH:mm');
        var a=convertStringToDatetime(current);
        var b=convertStringToDatetime(checkInDate);
        if (a < b) {
            cancelFromDate = checkInDate;
        } else {
            if(current > cancelFromDate) {
                cancelFromDate = moment(cancelFromDate, 'DD-MM-YYYY HH:mm').add(1, 'days').format('DD-MM-YYYY HH:mm');
            }
        }
        $('#cFromDate').val(cancelFromDate);
        $('#actualFrDt').val(data.fromDate);
        $('#cToDate').val(data.toDate);
        $('#cBookingId').val(data.bookingId);
        $('#cancelModal').modal('show');
        let date1 = convertStringToDatetime($('#cFromDate').val());
        let date2 = convertStringToDatetime(data.toDate);
        let days = daysBetween(date1, date2);
        $('#cTotalDays').val(days);
    });

    $('#cancelBtn').on('click', function () {
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
            function () {
                $('#loading').show();
                $.ajax({
                    url: './guest/cancelRequest1.do',
                    type: "POST",
                    data: JSON.stringify(postData),
                    contentType: "application/json",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                        $('#loading').show();
                    },
                    success: function (result) {
                        $('#loading').hide();
                        if (result.success) {
                            swal(result.msg);
                            $('#cancelModal').modal('hide');
                            getNomineeRequestList(table);
                        } else {
                            swal(result.msg);
                        }
                    }
                });
            });
    });

    $('#guestDataTable').on('click', 'tbody #partialCancel', function () {
        let data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        console.log(data);
        $('#partialFromDate').data("DateTimePicker").maxDate(moment(data.toDate, 'DD-MM-YYYY').subtract(1, 'days'));
        let date2 = convertStringToDatetime(data.toDate);
        let current = moment().format('DD-MM-YYYY');
        if (moment().format('HH:mm') <= '08:00') {
            $('#partialFromDate').data("DateTimePicker").minDate(moment(current, 'DD-MM-YYYY'));
            $('#partialFromDate').val(moment().format('DD-MM-YYYY 09:00'));
        } else {
            $('#partialFromDate').data("DateTimePicker").minDate(moment(current, 'DD-MM-YYYY').add(1, 'days'));
            $('#partialFromDate').val(moment().add(1, 'days').format('DD-MM-YYYY 09:00'));
        }
        $('#partialToDate').val(data.toDate);
        $('#partialCancelModal').modal('show');
        let date1 = convertStringToDatetime($('#partialFromDate').val());
        let days = daysBetween(date1, date2);
        $('#partialTotalDays').val(days);
        $('#partialCancelBookingId').val(data.bookingId);
    });

    $("#partialFromDate").on("dp.change", function (e) {
        let date1 = convertStringToDatetime($('#partialFromDate').val());
        let date2 = convertStringToDatetime($('#partialToDate').val());

        let days = daysBetween(date1, date2);
        $('#partialTotalDays').val(days);
    });

    $('#partialCancelBtn').click(function () {
        let bookingId = $('#partialCancelBookingId').val();
        let fromDate = $('#partialFromDate').val();
        let toDate = $('#partialToDate').val();

        if (fromDate == '' || fromDate == null) {
            swal("Please select from date");
            return false;
        }

        let postData = {
            bookingId: bookingId,
            fromDate: fromDate,
            toDate: toDate
        }
        $.ajax({
            url: "./guest/partialCancel.do",
            type: "POST",
            data: JSON.stringify(postData),
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
                $('#loading').show();
            },
            success: function (result) {
                $('#loading').hide();
                if (result.success) {
                    $('#partialCancelModal').modal('hide');
                    swal(result.msg);
                    getNomineeRequestList(table);
                } else {
                    swal(result.msg);
                }
            },
            error: function (e) {
                $('#loading').hide();
                console.log("ERROR: ", e);
                swal("Error!", "Something went wrong", "error");
            }
        });
    });

    $('#partialFromDate').datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY 09:00'
    });

    $('#guestDataTable').on('click', 'tbody #receipt', function () {
        $('#myModalView').modal('show');
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        $('#loading').show();
        $.ajax({
            url: './guest/getRequest/' + data_row.bookingId + ".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                $('#loading').hide();
                if (result.success) {
                    if (result.data != null) {
                        var date = '';
                        if (result.data.bookingDt != null) {
                            date = result.data.bookingDt.split(".");
                        }
                        $('#printName').html(result.data.name);
                        $('#printBookingId').html(result.data.bookingId);
                        $('#bookingDt').html(date[0]);
                        $('#ghName').html(result.data.cityName);
                        $('#blockName').html(result.data.ghName);
                        $('#fromDt').html(result.data.fromDate);
                        $('#toDate').html(result.data.toDate);
                        $('#noRooms').html(result.data.noRooms== ""?"0":result.data.noRooms);
                        $('#noBeds').html(result.data.noBeds==""?"0":result.data.noBeds);
                        $('#mobile').html(result.data.mobile);
                        $('#purpose').html(data_row.purpose);
                        $('#category').html(result.data.category);
                        $('#catgType').html(result.data.catgType);
                        $('#desgignation').html(result.data.desg);
                        $('#officeAddress').html(result.data.office);
                        $('#convence').html(result.data.conveyance);
                        $("#pdfPrintBtn").attr("href", './guest/getPdfPrintView/' + result.data.bookingId + '.do');
                    } else {
                        $('#printForm').html('');
                    }
                } else {
                    swal(result.msg);
                }
            }
        });
    })

    
    $('#guestDataTable').on('click', 'tbody #paymentReceipt', function () {
    	
        $('#myModalViewForPayment').modal('show');
        let current_datetime = new Date()
        let formatted_date = current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear()
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        console.log(" guest id "+data_row.bookingId);
        $('#paymentReceiptDetails').html(data_row.ghName + "Receipt");
        $('#loading').show();
        $.ajax({
            url: './guest/getPaymentReceipt/' + data_row.bookingId + ".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                $('#loading').hide();
                if (result.success) {
                    if (result.data != null) {
                        if (result.data.receiptId != null) {
                            $('#receiptNo').html('' + result.data.receiptId)
                        }
                        /*result.data.guest = result.data.guest.map(function (guest) {
                            if (guest.indexOf(',') !== -1) {
                                return guest.slice(0, guest.indexOf(','));
                            } else {
                                return guest;
                            }
                        });*/ 
                        $('#dateFormat').html('' + (result.data == null ? formatted_date : result.data.recDate));
                        $('#guestName').html(result.data.guestName == null ? '' : result.data.guestName);
                        $('#payRuppess').html(result.data.amountPaid == null ? '' : result.data.amountPaid);
                        var textRuppess = inWords(result.data.amountPaid == null ? '' : result.data.amountPaid);
                        $('#inrupees').html(' (Rupees ' + (textRuppess == null ? '' : textRuppess) + ')');
                        $('#mode').html(result.data.mode == null ? '' : result.data.mode);
                        $('#inrPrint').html('INR' + (result.data.amountPaid == null ? '' : result.data.amountPaid));
                        $('#pBillNo').html(result.data.billNo == null ? '' : result.data.billNo);
                        $('#pBillDate').html(result.data.billDate == null ? '' : result.data.billDate);
                        $('#pBillAmount').html('Total amount paid: '+(result.data.amountPaid == null ? '' : result.data.amountPaid));
                        $('#printDate').text('Date: ' + moment().format('DD-MM-YYYY'));
                        $('#cityAndGuestHouseForReceipt').html(result.data.guestHouse+", "+result.data.cityName);
                        result.data.receiptModel.forEach(function(mode) {
							console.log("mode    "+JSON.stringify(mode));
							if (mode.modeOfPayment === 'cash') {
								$('#cashShow').show();
								$('#pCashAmount').val(mode.amounts);
							}
							if (mode.modeOfPayment === "pos") {
								$('#posShow').show();
								$('#pPostx').show();
								$('#pPosAmount').val(mode.amounts);
								$('#pPosId').val(mode.pos_tr_num);
							}
							if (mode.modeOfPayment === 'online') {
								$('#onlineShow').show();
								$('#pOnlineAmount').val(mode.amounts);
							}
						});                   
                        $("#pdfPrintBtnForPayment").attr("href", './guest/getPdfPaymentReceipt/' + data_row.bookingId + '.do');
                    } else {
                        $('#printFormPayment').html('');
                    }
                } else {
                    swal(result.msg);
                }
            }
        });
    });

    $('#guestDataTable').on('click', 'tbody .bill', function () {
        var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        $('#createBillModal').modal('show');
        bookingIdForBill = data.bookingId;
        billIdForBill = data.billId;
        console.log("Booking id for booking"+bookingIdForBill+"bill id for booking"+billIdForBill);

        $.ajax({
            url: './guest/showBillDetails/' + data.bookingId + '/' + data.billId + '.do',
            type: 'GET',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $('#loading').hide();
                console.log(result);
                let total = parseFloat(result.data.total);
                let adv = parseFloat(result.data.advanceAmount);
                $('#createBillModalTbody').empty();
                let str = '';
                let row = result.data.guestDetails[0];
                str += '<tr>' +
                    '<td>' + row.rowNo + '</td>' +
                    '<td>' + row.name + '</td>' +
                    '<td>' + row.noRooms + '</td>' +
                    '<td>' + row.category + '</td>' +
                    '<td>' + row.purpose + '</td>' +
                    '<td>' + (result.data.guestDetails.length + row.count) + '</td>' +
                    '<td>' + row.arrivalDate + '</td>' +
                    '<td>' + row.departureDate + '</td>' +
                    '</tr>';
                $('#createBillModalTbody').append(str);
                $('#particulars').empty();
                result.data.charges.forEach(function (row, i) {
                    $('#particulars').append('<tr>' +
                        '<td>' + row.name + '</td>' +
                        '<td>' + row.desc + '</td>' +
                        '</tr>'
                    );
                });
                $('#particulars').append('<tr>' +
                    '<td><b>Total</b></td>' +
                    '<td><b>' + total + '</b></td>' +
                    '</tr>'
                );
                $('#deduct').html('Deduct Advance Collected : ' + adv)
                $('#release').html('Balance to be paid : ' + Math.round(total - adv));
                $('#rupees').html(inWords(Math.round(total - adv)));
                $('#date').html(result.data.billDate);
                $('#nocDate').html('NOC Date : ' + result.data.nocDate);
                $('#cityAndGuestHouse').html(result.data.guestDetails[0].ghName+", "+result.data.guestDetails[0].cityName);
            }
        });
    });
	 $('#guestDataTable tbody').on('click', '.noc_sts', function () {
		/*$("#nocPaymentCheck").modal("show");*/
		let data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        /*console.log(data);*/
        var postData={
        	reqId:data.bookingId
        };
        $.ajax({
            url: './guest/checkPaymentSatusOfNOC.do',
            method: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
                $('#loading').show();
            },
            data: JSON.stringify(postData),
            contentType: "application/json",
            dataType: "json", 
            success: function (result) {
                $('#loading').hide();
        		getNomineeRequestList(table, contable);
                swal(result.msg);
            }
        });
	});
    $('#guestDataTable tbody').on('click', '.noc', function () {
        let data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        console.log(data);
        $.ajax({
            url: './guest/getNoc/' + data.bookingId + '.do',
            type: 'GET',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $('#loading').hide();
                if (result.status) {
                    console.log(result);
                    $("#nocPaymentModal").modal('show');
                    $('#guestNames').text(result.data.name);
                    $('#amountAndWords').text(result.data.total);
                    $('#nocRDate').text(moment().format('DD-MM-YYYY HH:mm'));
                    $('#billAmount').text('Rs. ' + result.data.total);
                    $('#onlineAmount').val(result.data.total);
                    $('#nocReqId').val(data.bookingId);
                    $('#nocBlockId').val(data.cityName);
                } else {
                    swal(result.msg);
                }
            }
        });
    });

    let nocBookingId;

    $('#guestDataTable tbody').on('click', '.noc-receipt', function () {
        let data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        console.log(data);
        $.ajax({
            url: './guest/getNoc/' + data.bookingId + '.do',
            type: 'GET',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $('#loading').hide();
                if (result.status) {
                    let data = result.data;
                    nocBookingId = data.bookingId;
                    $('.hideAmount').hide();
                    $('#printNocModal').modal('show');
                    $('#nRectId').text(data.recId);
                    $('#nGuestNames').text(data.name);
                    $('#nAmountAndWords').text(data.paidAmount);
                    $('#nReceiptDate').text(data.rectDate);
                    $('#nBillAmount').text('Rs. ' + data.total);
                    $('#nPrintDate').text('Date: ' + moment().format('DD-MM-YYYY'));
                    $('#nCityAndGuestHouse').text(data.guestHouseName+", "+data.cityName);
                    data.paymentModes.forEach(function (mode) {
                        console.log(mode);
                        if (mode.mode_of_payment === 'cash') {
                            $('#nCashShow').show();
                            $('#nCashAmount').val(mode.amount);
                        }
                        if (mode.mode_of_payment === 'pos') {
                            $('#nPosShow').show();
                            $('#nPostx').show();
                            $('#nPosAmount').val(mode.amount);
                            $('#nPosId').val(mode.pos_tr_num);
                        }
                        if (mode.mode_of_payment === 'online') {
                            $('#nOnlineShow').show();
                            $('#nOnlineAmount').val(mode.amount);
                        }
                    });
                } else {
                    swal(result.msg);
                }
            }
        });
    });
    
    $(".modal").on("hidden.bs.modal", function(){
    	
			$('#cashShow').hide();
			$('#pCashAmount').val('');
		
	
			$('#posShow').hide();
			$('#pPostx').hide();
			$('#pPosAmount').val('');
			$('#pPosId').val('');
		
		
			$('#onlineShow').hide();
			$('#pOnlineAmount').val('');
		
    });
    $('#guestDataTable').on('click', 'tbody #duesReceipt', function () {
    	
        $('#myModalViewForPayment').modal('show');
        let current_datetime = new Date()
        let formatted_date = current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear()
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        console.log(" guest id "+data_row.bookingId);
        $('#paymentReceiptDetails').html(data_row.ghName + "Dues Receipt");
        $('#loading').show();
        $.ajax({
            url: './guest/getDuesPaymentReceipt/' + data_row.bookingId + ".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                $('#loading').hide();
                if (result.success) {
                    if (result.data != null) {
                        if (result.data.receiptId != null) {
                            $('#receiptNo').html('' + result.data.recId)
                        }
                        $('#dateFormat').html('' + (result.data == null ? formatted_date : result.data.rectDate));
                        $('#guestName').html(result.data.onlyName == null ? '' : result.data.onlyName);
                        $('#payRuppess').html(result.data.total == null ? '' : result.data.total);
                        var textRuppess = inWords(result.data.total == null ? '' : result.data.total);
                        $('#inrupees').html(' (Rupees ' + (textRuppess == null ? '' : textRuppess) + ')');
                        //$('#mode').html(result.data.paymentModes == null ? '' : result.data.paymentModes);
                        $('#inrPrint').html('INR' + (result.data.total == null ? '' : result.data.total));
                        $('#pBillNo').html(result.data.billNo == null ? '' : result.data.billNo);
                        $('#pBillDate').html(result.data.date == null ? '' : result.data.date);
                        $('#pBillAmount').html('Total amount paid: '+(result.data.total == null ? '' : result.data.total));
                        $('#printDate').text('Date: ' + moment().format('DD-MM-YYYY'));
                        $('#cityAndGuestHouseForReceipt').html(result.data.guestHouseName+", "+result.data.cityName);
                        result.data.paymentModes.forEach(function(mode) {
							console.log("mode    "+JSON.stringify(mode));
							if (mode.mode_of_payment === 'cash') {
								$('#cashShow').show();
								$('#pCashAmount').val(mode.amount);
							}
							if (mode.mode_of_payment === "pos") {
								$('#posShow').show();
								$('#pPostx').show();
								$('#pPosAmount').val(mode.amount);
								$('#pPosId').val(mode.pos_tr_num);
							}
							if (mode.mode_of_payment === 'online') {
								$('#onlineShow').show();
								$('#pOnlineAmount').val(mode.amount);
							}
						});                   
                        $("#pdfPrintBtnForPayment").attr("href", './guest/getPdfDuesPaymentReceipt/' + data_row.bookingId + '.do');
                    } else {
                        $('#printFormPayment').html('');
                    }
                } else {
                    swal(result.msg);
                }
            }
        });
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

	var submitBookingDetails = function(type){
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

		var formData = $('#existingBookingApplication').serializeArray();
		var rawGuestDetails = formData.filter(d=>d.name.match(/guest/g));
		var otherDetails = formData.filter(d=>!d.name.match(/guest/g));
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
		fd.append("bookingId", bookingReqId);
		fd.append("noOfDays", $('#noOfDays').val());
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
		console.log(fd);
		let adultCount = $('#noOfAdults').val() == "" ? 0 : $('#noOfAdults').val();
		let childCount = $('#noOfChildren').val() == "" ? 0 : $('#noOfChildren').val();
		if(!validateChildrenAndAdultCount(adultCount, childCount, guestDtls))
			return false;

		$.ajax({
			url: "./guest/saveBookingDetails/edit/" + type + ".do",
			type : "POST",
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
					console.log(data);
					$('#applicationFormModal').modal('hide');
					swal(data.msg);
					getNomineeRequestList(table);
				} else {
					swal(data.msg);
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
	};

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
			},emailId: {
				validators: {
					// notEmpty: {
					// 	message: 'field can\'t be empty'
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
			            regexp: /^[a-zA-Z0-9\s,.-]+ [1-9][0-9]{2}-?[0-9]{3}$/,
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
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		}
			// 	}
			// }, flNoFr: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		},
			// 		regexp: {
			// 			regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
			// 			message: 'field is not valid'
			// 		}
			// 	}
			// }, arrDt: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		}
			// 	}
			// }, arrPla: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		},
			// 		regexp: {
			// 			regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
			// 			message: 'field is not valid'
			// 		}
			// 	}
			// }, travelModeTo: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		}
			// 	}
			// },flNoTo: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		},
			// 		regexp: {
			// 			regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
			// 			message: 'field is not valid'
			// 		}
			// 	}
			// },depDt: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		}
			// 	}
			// }, depPla: {
			// 	validators: {
			// 		notEmpty: {
			// 			message: 'field can\'t be empty'
			// 		},
			// 		regexp: {
			// 			regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
			// 			message: 'field is not valid'
			// 		}
			// 	}
			}
		}
	}).on('error.field.bv', function(e, data) {
		if (data.bv.getSubmitButton()) {
			data.bv.disableSubmitButtons(false);
		}
	}).on('success.form.bv', function (e) {
		e.preventDefault();
		var status = guestValidation(guestCount)
		if(status){
			var type = $('#roomTyp').val();
			if(type == 'onlyRoom' || type == 'R'){
				submitBookingDetails('R');
			} else if(type == 'C' || type == 'conv'){
                if (!checkTravel())
                    return false;
				submitBookingDetails('C');
			} else{
                if (!checkTravel())
                    return false;
				submitBookingDetails('RC');
			}
		}else{
			console.log('Test')
			$('#applicationUpdateBtn').removeAttr("disabled");
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

    $('#conDataTable').on('click', 'tbody #view', function () {
        $('#applicationFormModal').modal('show');
        $('#applicationHeader').html('View Form')
        $('#chec').hide();
        $('#convFrDtls').show();
        $('#convToDtls').show();
        $('#reqDetails').hide();
        $('#GuestDetails').show();
        var data = contable.row($(this).closest('tr')).data() != undefined ? contable.row($(this).closest('tr')).data() : contable.row($(this)).data();
        guestCount = 0;
        $('#guestDetailsWrapper').html("");
        blockId = data.cityName;
        setGuestDetails(data.bookingId, true);
    });

    $('#conDataTable').on('click', 'tbody #edit', function () {
        $('#applicationFormModal').modal('show');
        $('#chec').hide();
        $('#convFrDtls').show();
        $('#convToDtls').show();
        $('#reqDetails').hide();
        $('#GuestDetails').show();
        var data = contable.row($(this).closest('tr')).data() != undefined ? contable.row($(this).closest('tr')).data() : contable.row($(this)).data();
        blockId = data.cityName;
        guestCount = 0;
        $('#guestDetailsWrapper').html("");
        setGuestDetails(data.bookingId, false);
        $('#arrivalRow').addClass('disableElement');
        $('#departureRow').addClass('disableElement');
    });

    $('#conDataTable').on('click', 'tbody #cancel', function () {
        var data_row = contable.row($(this).closest('tr')).data() != undefined ? contable.row($(this).closest('tr')).data() : contable.row($(this)).data();
        console.log(data_row);
        swal({
                title: "Are you sure? ",
                text: "Are you sure to cancel!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, cancel it!",
                closeOnConfirm: false
            },
            function () {
                $('#loading').show();
                $.ajax({
                    url: './guest/cancelRequest/' + data_row.bookingId + ".do",
                    type: "GET",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (result) {
                        $('#loading').hide();
                        if (result.success) {
                            swal(result.msg);
                            getNomineeRequestList(table, contable);
                        } else {
                            swal(result.msg);
                        }
                    }
                });
            });
    })

    $("#applicationFormModal").on("hidden.bs.modal", function () {
        $(this).find('form').trigger('reset');
        $('#existingBookingApplication').bootstrapValidator('resetForm', true);
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

    $("#myModalViewForCategory").on("hidden.bs.modal", function () {
        $(this).find('form').trigger('reset');
    });

    var viewDesg;
    $('#viewCate').click(function () {
        viewDesg = "All";
        var cat = $('#catg').val();
        if (cat != "") {
            viewDesg = cat;
        }
        getCatgoryListDtls12(viewDesg);
        $('#myModalViewForCategory').modal('show');

    });

    var getCatgoryListDtls12 = function (type) {
        $.ajax({
            url: "./guest/getDesgList/" + type + ".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                desAccToCatTable.clear().draw();
                desAccToCatTable.rows.add(result).draw();
                /*desAccToCatTable.columns.adjust().responsive.recalc();*/
            }
        });
    };

    $('#guestCatDataTable').on('click', 'tbody #cateType', function () {
        $('#myModalViewForCategory').modal('hide');
        var data_row = desAccToCatTable.row($(this).closest('tr')).data();
        console.log("data_row-----------------" + data_row);
        getCatgoryListDtls12(data_row.desc);
        getDesignationListDtls(('C' + data_row.desc), data_row.id);
        getPurposeListDtls(('C' + data_row.desc));
        onCatgChange('C' + data_row.desc);
//		getGuestDesignationDetails(data_row.id);
    });

    $.ajax({
        url: "./mdm/getDataCityTable.do",
        type: "GET",
        headers: {"X-CSRF-TOKEN": token},
        success: function (data) {
            $('#city').empty();
            $('#city').append('<option value="">-- Select City --</option>');
            $.each(data.data, function (i, item) {
                $('#city').append($('<option>', {
                    value: item.cityId,
                    text: item.cityName
                }));
            });
        }
    });

    $('#city').on('change', function () {
        let cityId = this.value;
        loadGhIntoFilter(cityId);
    });

    let loadGhIntoFilter = (cityId) => {
        $.ajax({
            url: "./guest/getGuestHouseList/" + cityId +".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (data) {
                $('#gHouse').empty();
                $('#gHouse').append('<option value="">-- Select Guest House --</option>');
                $.each(data, function (i, item) {
                    $('#gHouse').append($('<option>', {
                        value: item.id,
                        text: item.name
                    }));
                });
            }
        });
    }

    $('#fBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });

    $('#status').append(
        "<option value='REQ'>Not taken up for processing</option>" +
        "<option value='CAN'>Cancelled</option>" +
        "<option value='MOD'>Under processing</option>" +
        "<option value='ALO'>Alloted</option>" +
        "<option value='PRO'>Processed</option>" +
        "<option value='CIN'>Checked In</option>" +
        "<option value='COUT'>Checked Out</option>" +
        "<option value='BILL'>Billing</option>" +
        "<option value='RECT'>Payment Successfully</option>"
    );

    $('#filterSearch').click(function () {
        let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#fBookingId').val();
        let bookingDate = $('#searchDt').val();
        let status = $('#status').val();

        let postData = {
            cityId: city,
            blockId: gHouse,
            bookingId: bookingId,
            bookingDate: bookingDate,
            status: status
        };

        $.ajax({
            url: './guest/getUserRequestList.do',
            type: "POST",
            data: JSON.stringify(postData),
            contentType: "application/json",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
            	console.log(result);
                table.clear().draw();
                table.rows.add(result.data).draw();
                contable.clear().draw();
                contable.rows.add(result.data).draw();
            },error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                $('#loading').hide();
            }
        });
    });

    $('#resetBtn').click(function () {
        $('#city').val('');
        $('#gHouse').val('');
        $('#fBookingId').val('');
        $('#searchDt').val('');
        $('#status').val('');
        getNomineeRequestList(table, contable);
    });
    
    $('#printBtn').click(function(){     
    		window.open('./guest/downloadBillInPDF/' + bookingIdForBill + '/' + billIdForBill + '.html','_blank');
    	})

    $('#sidebarShow').click(function(){     
    	$('#addSliderIn').addClass('sidebar-slide-in')
    })
     $('#sidebar-close').click(function(){     
    	$('#addSliderIn').removeClass('sidebar-slide-in')
    })
    
    $(document).on("click", function () {
    	$('#addSliderIn').removeClass('sidebar-slide-in')
    });

    $('#printNoc').click(function(){
        window.open('./guest/getNocPdf/' + nocBookingId + '.do','_blank');
    });
    	
});

/*******************************************************************************
 * Get The PEDNING REQUESTS FROM ADDRESS ERROR MARK
 ******************************************************************************/
function getNomineeRequestList(table, contable) {
    let postData = {};
    $.ajax({
        url: './guest/getUserRequestList.do',
        type: "POST",
        data: JSON.stringify(postData),
        contentType: "application/json",
        headers: {"X-CSRF-TOKEN": token},
        success: function (result) {
        	console.log(result);
            table.clear().draw();
            table.rows.add(result.data).draw();
// table.columns.adjust().responsive.recalc();
        }
    });
}

function guestValidation(guestCount) {
    console.log('guestCount' + guestCount);
    if (guestCount >= 1) {
        for (i = 1; i <= guestCount; i++) {
            console.log('Guest1' + $('#guestName' + i).val())
            if ($('#guestName' + i).val() == '') {
                swal("Please Enter Guest Name!");
                return false;
            } else if ($('#guestAge' + i).val() == '') {
                swal("Please Enter Guest Age!");
                return false;
            } else if ($('#guestGender' + i).val() == '') {
                swal("Please Enter Guest Gender!");
                return false;
            } else if ($('#guestAddr' + i).val() == '') {
                swal("Please Enter Guest Address!");
                return false;
            }
        }
    }
    return true;
}

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
    return Math.ceil(difference_ms / ONE_DAY);
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