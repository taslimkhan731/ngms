/**
 *
 */
$('document').ready(function () {
    /*Validation ends*/
    $('#office').hide();
    $('#supporting').hide();
    $('#convenceFrom').hide();
    $('#convenceTo').hide();
    $('#desgHidden').hide();
    $('#noOfRooms').val('0');
    $("#noOfBeds").addClass('disableElement');
    $("#frmDt, #toDt, #arrDt, #depDt").datetimepicker({
        useCurrent: false,
        minDate: moment(),
        format: 'DD-MM-YYYY'
    });

    $("#depTm, #arrTm").datetimepicker({
        format: 'hh:mm A'
    });
    $("#frmDt").on("dp.change", function (e) {
        $('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'frmDt');
        $("#toDt").data("DateTimePicker").minDate(e.date);
    });
    $("#toDt").on("dp.change", function (e) {
        $('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'toDt');
        $("#frmDt").data("DateTimePicker").maxDate(e.date);
    });

    $("#arrDt").on("dp.change", function (e) {
        $('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'arrDt');
        $("#depDt").data("DateTimePicker").minDate(e.date);
    });
    $("#depDt").on("dp.change", function (e) {
        $('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'depDt');
        $("#arrDt").data("DateTimePicker").maxDate(e.date);
    });

    $("#noOfRooms").bind('keyup keypress mouseup', function () {
        var room = $("#noOfRooms").val();
        if (room == '') {
            $("#noOfRooms").removeClass('disableElement');
            $("#noOfBeds").removeClass('disableElement');
        } else {
            $("#noOfBeds").addClass('disableElement');
        }
    });

    $("#noOfBeds").bind('keyup keypress mouseup', function () {
        var room = $("#noOfBeds").val();
        if (room == '') {
            $("#noOfRooms").removeClass('disableElement');
            $("#noOfBeds").removeClass('disableElement');
        } else {
            $("#noOfRooms").addClass('disableElement');
        }
    });


    $("input[name='bookingCond']").click(function () {
        if ($(this).val() == "roomCon") {
            $('#roomTyp').val($(this).val()); //Room and Conveyance

            $('#reqDetails').show();
            $('#convenceFrom').show();
            $('#convenceTo').show();
            $('#GuestDetails').show();
            $('#btnSub').show();
        } else if ($(this).val() == "onlyRoom") {
            $('#roomTyp').val($(this).val());//only room
            $('#reqDetails').show();
            $('#convenceFrom').hide();
            $('#convenceTo').hide();
            $('#GuestDetails').show();
            $('#btnSub').show();
        } else {
            $('#roomTyp').val($(this).val());
            $('#reqDetails').hide();
            $('#convenceFrom').show();
            $('#convenceTo').show();
            $('#GuestDetails').hide();
            $('#btnSub').show();
        }
    });

    var guestParameters = 5;
    var guestCount = 0;
    var blockId = getUrlParameter('id');
    var suppDoc = null;
    var officAddress = '';

    $('#roomWrapper').show();
    $('#dormintoryWrapper').hide();

    $("input[name='bookingType']").click(function () {
        if ($(this).val() == "room") {
            $('#noOfRooms').val('1');
            $('#noOfBedsDormitory').val('0');
            $('#noOfBeds').val('');
            $('#roomWrapper').show();
            $('#dormintoryWrapper').hide();
        } else {
            $('#noOfRooms').val('0');
            $('#noOfBedsDormitory').val('1');
            $('#noOfBeds').val('');
            $('#roomWrapper').hide();
            $('#dormintoryWrapper').show();
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
//    **************************************************


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
                //    $("#gHouse").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    $("#gHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
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
    getCityListDtls();


    $('#catg').on('change', function () {
        var id = $('#catg').val();
        console.log(id);
        getDesignationListDtls(id);
    });

    var getDesignationListDtls = function (id) {
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
                //        $("#desg").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    $("#desg").append('<option value="' + option.id + '">' + option.name + '</option>');
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
    var getCatgoryListDtls = function () {
        $.ajax({
            url: "./guest/getCategoryDetails.do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $("#catg").html("");
                $("#catg").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    $("#catg").append('<option value="' + option.id + '">' + option.name + '</option>');
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
//    **************************
    var getFlightTrainList = function (type, cond) {
        $.ajax({
            url: "./guest/getFlightTrainList/" + $('#gHouse').val() + "/" + type + ".do",
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

    $('#purp').on('change', function () {
        var purpose = $('#purp').val();
        if (purpose == '1') {
            $('#office').show();
            $('#offAddr').val(officAddress);
            $('#supporting').hide();
        } else if (purpose == '3' || purpose == '4') {
            $('#office').hide();
            $('#offAddr').val('');
            $('#supporting').show();
        } else {
            $('#office').hide();
            $('#offAddr').val('');
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


    var getPurposeListDtls = function () {
        $.ajax({
            url: "./guest/getPurposeDetails.do",
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
                swal(error);
            }
        });
    }

    var getUserDtls = function () {
//        $.ajax({
//            url: "./userProfile.do",
//            type: "GET",
//            contentType:"application/json",
//            beforeSend: function(xhr) {
//                xhr.setRequestHeader(header, token);
//            },
//            success:function(result) {
//                if(result.success){
//                    $("#mob").val(result.data.contactNo);
//                    if(result.data.email != null){
//                        $("#emailId").val(result.data.email);
//                    }
//                    if(result.data.firstName != null){
//                        $("#guestName").val(result.data.firstName);
//                    }
//                    officAddress = result.data.officeAddr == null ? '' : result.data.officeAddr;
//                    $('#guestAddr').val(result.data.resAddr == null ? '' : result.data.resAddr);
//                    $('#guestGender').val(result.data.guestGender == null ? '' : result.data.guestGender);
//                    $('#guestDepartment').val(result.data.dept == null ? '' : result.data.dept);
//                    if(result.data.dob != null){
//                        var year = dayDiff(new Date(),result.data.dob)
//                        $('#guestAge').val(year)
//                    }
//                }else{
//                    swal(result.msg);
//                }
//            }, error:function(jqXHR, status, error){
//                swal(error);
//            }
//        });
    }

    getCatgoryListDtls();
    getPurposeListDtls();
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

    $('#addGuestDetailsBtn').on('click', function () {
        guestCount++;
        $('#guestDetailsWrapper').append(
            '<tr> ' +
            '<th scope="row"><span class="mt-2">' + guestCount + '</span></th> ' +
            '<td> ' +
            '<div class="row"> ' +
            '<input type="hidden" name="guestId"> ' +
            '<input type="text" name="guestName" id="guestName" class="form-control col-12" ' +
            'placeholder="Enter name" autocomplete="off"> ' +
            '</div> ' +
            '</td> ' +
            '<td> ' +
            '<div class="row"> ' +
            '<input type="text" name="guestAge" class="form-control col-12" ' +
            'placeholder="Enter age" autocomplete="off"> ' +
            '</div> ' +
            '</td> ' +
            '<td> ' +
            '<div class="row"> ' +
            '<select class="form-control col-12" name="guestGender"> ' +
            '<option value=""> --- Select --- </option> ' +
            '<option value="M">Male</option> ' +
            '<option value="F">Female</option> ' +
            '<option value="O">Others</option> ' +
            '</select> ' +
            '</div> ' +
            '</td> ' +
            '<td> ' +
            '<div class="row"> ' +
            '<input type="text" name="guestAddr" class="form-control col-12" ' +
            'placeholder="Enter address" autocomplete="off"> ' +
            '</div> ' +
            '</td> ' +
            '</tr>'
        );
    });

    $('#addGuestDetailsBtn').click();


    $('#deleteGuestDtlsBtn').on('click', function () {
        if (guestCount > 1) {
            guestCount--;
            $('#guestDetailsWrapper').children().last().remove();
        }
    });


    var submitBookingDetails = function (type) {
        console.log('form valid');
        var formData = $('#newDataEntrygApplication').serializeArray();
        var rawGuestDetails = formData.filter(d => d.name.match(/guest/g));
        var otherDetails = formData.filter(d => !d.name.match(/guest/g));
        var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
        console.log(guestDtls);
        console.log(otherDetails);
        var fd = new FormData();
        fd = convertSerializeArrayToFd(otherDetails);
        fd.append("guestDtlsStr", JSON.stringify(guestDtls));
        fd.append("blockId", $('#gHouse').val());
        if (suppDoc != null) {
            fd.append("suppDoc", suppDoc, suppDoc.name)
        }
        ;
        console.log(fd);
        $.ajax({
            url: "./admin/adminsaveBookingDetails/add/" + type + ".do",
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
                console.log(data);
                $('#loading').hide();
                if (data.status) {
                    $(this).find('form').trigger('reset');
                    $('#newDataEntrygApplication').bootstrapValidator('resetForm', true);
                    resetForm();
                    guestCount = 0;
                    $('#addGuestDetailsBtn').click();
                    swal(data.msg);
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
    $('#newDataEntrygApplication').bootstrapValidator({
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
                    }, emailAddress: {
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
                    regexp: {
                        regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
                        message: 'field is not valid'
                    }
                }
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
});

//******************************

//*********************************
function resetForm() {
    $('#guestDetailsWrapper').html('');
    $('#noOfBedsDormitory').val('');
    $('#depTm').val('');
    $('#arrTm').val('');
}