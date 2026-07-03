$('document').ready(function () {
	
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
    $("#allotmentListDataTable").show();
    $("#modifyListDataTable").hide();
    let bookingId = '';
    let modId = '';
    let mrrId = '';
    let type = '';
    let pageType = 'allotmentList';
    let roomType = 'room';
    var cityData = $('#cityUserlocssd').val().trim();
    var cityDetails = JSON.parse(cityData);

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

    $("#allotPagePrev").attr("disabled", true);
    $("#allotPageNext").attr("disabled", true);

    $("#avail_list").hide();
    $("#changesList").hide();
    $("#cancelList").hide();
    $("#modifyList").hide();
    $("#modifyBlock").hide();
    $("#modifyRoomBtn").hide();

    $("#allot").on("hidden.bs.modal", function () {
        $("#formPassword")[0].reset();
        var validator = $("#formPassword").validate();
        validator.resetForm();
    });
    $('#avail').on('click', function () {
        $("#avail_list").toggle();
    });
    
    $("#filterBookingDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
        // format: 'YYYY-MM-DD'
    });
    $("#modifyDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
        // format: 'YYYY-MM-DD'
    });
    
    $('#allotmentListDataTable tbody').on('click', '#allot', function () {
        let data = JSON.parse($(this).parents('tr').find('input[name=data]').val());
        // let data = table.row($(this).parents('tr')).data();
        bookingId = data.bookingId;
        modId = data.modId;
        mrrId = data.mrrId;
        type = "allot";
        allotRooms(data);
    });
    $('#allotmentListDataTable tbody, #changeListDataTable tbody').on('click', '#cancel', function () {
        // var data = table.row($(this).parents('tr')).data();
        let data = JSON.parse($(this).parents('tr').find('input[name=data]').val());
        swal({
                title: "Are you sure? ",
                text: "Are you sure to cancel the Allotment!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, Cancel It!",
                cancelButtonText: "Dismiss",
                closeOnConfirm: false
            },
            function () {
                $('#loading').show();
                $.ajax({
                    url: './admin/cancelAllotment/' + data.bookingId + '/' + data.modId + '/' + data.mrrId + '.do',
                    type: "GET",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (data) {
                        $('#loading').hide();
                        if (data.success) {
                            swal(data.msg);
                            $('#allotment').click();
                        } else {
                            swal(data.msg);
                        }
                    }
                });
            });
    });
    $('#allotmentListDataTable tbody, #changeListDataTable tbody').on('click', '#change', function () {
        // let data = table.row($(this).parents('tr')).data();
        let data = JSON.parse($(this).parents('tr').find('input[name=data]').val());
        bookingId = data.bookingId;
        modId = data.modId;
        mrrId = data.mrrId;
        type = "change";
        allotRooms(data);
        $($.fn.dataTable.tables(true)).DataTable()
        .columns.adjust();
        $(".forPreview").show();
        $("#ApproveSectionContainer").hide();
        $('#preview').removeClass('custom-btn-active');
    });

    $('#modifyListDataTable tbody').on('click', '#modify', function () {
    	$(".forPreview").show();
        $("#ApproveSectionContainer").hide();
        // let data = table.row($(this).parents('tr')).data();
        let data = JSON.parse($(this).parents('tr').find('input[name=data]').val());
        bookingId = data.bookingId;
        modId = data.modId;
        mrrId = data.mrrId;
        type = "modify";
        $('#modifyBlock').show();
        $("#allotRoomBtn").hide();
        $("#modifyRoomBtn").show();
        allotRooms(data);
       
    });

// ***************************

    $('#city').on('change', function () {
        var id = $('#city').val();
        console.log(id);
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

                $("#gHouse").append('<option value="">--Select Guest House--</option>');
                data.forEach(option => {
                    $("#gHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
                })
                let size = Object.keys(data).length;
                if (size == 1) {
                    $('#gHouse').val(data[0].id);
                }
                $('#gHouse').trigger('change');
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

    $('#gHouse').on('change', function () {
        $('#allotment').removeClass('custom-btn-active');
        $('#change').removeClass('custom-btn-active');
        $('#cancel').removeClass('custom-btn-active');
        $('#modify').removeClass('custom-btn-active');
        $('#preview').removeClass('custom-btn-active');
        $("#changesList").hide();
        $("#cancelList").hide();
        $("#modifyList").hide();
        $("#allotmentList").show();
        $('#allotmentTableBody').html("");
    });

    $('#change').on('click', function () {
    	$(".forPreview").show();
        $("#ApproveSectionContainer").hide();
        if(!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId, "ALLOTMENT");
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    document.getElementById("headerName").innerHTML = "List of Allotment - Change ";
                    $('#change').addClass('custom-btn-active');
                    $('#allotment').removeClass('custom-btn-active');
                    $('#cancel').removeClass('custom-btn-active');
                    $('#report').removeClass('custom-btn-active');
                    $('#modify').removeClass('custom-btn-active');
                    $('#preview').removeClass('custom-btn-active');
                    $("#avail_list").hide();
                    $("#allotmentList").hide();
                    $("#cancelList").hide();
                    $("#modifyList").hide();
                    $($.fn.dataTable.tables(true)).DataTable()
                        .columns.adjust();
                    $("#changesList").show();
                    $("#changeListDataTable").show();
                    pageType = 'changeList';
                    $("#changePageNo").html(1);
                    getAllotmentList(1, "changeList");
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

    $("#changePageNext").on("click", function () {
        let pageNo = parseInt($("#changePageNo").html());
        getAllotmentList(pageNo + 1, "changeList");
        $("#changePageNo").html(pageNo + 1);
    });

    $("#changePagePrev").on("click", function () {
        let pageNo = parseInt($("#changePageNo").html());
        if (pageNo > 1) {
            getAllotmentList(pageNo - 1, "changeList");
            $("#changePageNo").html(pageNo - 1);
        }
    });

    $('#allotment').on('click', function () {
    	$(".forPreview").show();
        $("#ApproveSectionContainer").hide();
        if(!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId, "ALLOTMENT");
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    document.getElementById("headerName").innerHTML = "Room Allotment List";
                    $('#allotment').addClass('custom-btn-active');
                    $('#change').removeClass('custom-btn-active');
                    $('#cancel').removeClass('custom-btn-active');
                    $('#report').removeClass('custom-btn-active');
                    $('#modify').removeClass('custom-btn-active');
                    $('#preview').removeClass('custom-btn-active');
                    $("#avail_list").hide();
                    $("#allotmentList").show();
                    $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
                    $("#allotmentListDataTable").show();
                    $("#cancelList").hide();
                    $("#modifyList").hide();
                    $("#changesList").hide();
                    pageType = 'allotmentList';
                    getAllotmentList(1, "allotmentList");
                    $("allotPageNo").html(1);
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
    
    $('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });    
    
    $('#filterRoomType').append(
        "<option value='R'>Room</option>" +
        "<option value='C'>Conveyance</option>" +
        "<option value='RC'>Room With Conveyance</option>"
    );      
    
    $('#filterSearch').click(function () {
    	if(!isCityAndGHSelected())
            return false;
    	pageNo = 1;
    	getAllotmentList(pageNo,pageType);
    	$('#filter-popover').unbind();
    	setTimeout(function(){
    		$('#filter-popover').bind('click',preventClick);
    	}, 1000);       
    });
    
    function preventClick() {
	    event.stopPropagation();
	 }
    
    $('#resetFiterBtn').click(function () {
    	pageNo=1;
        $('#filterBookingId').val('');
        $('#filterBookingDate').val('');
        $('#filterPurpose').val('');
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        $('#filterstatus').val('');
        getAllotmentList(pageNo,pageType);
    });

    let getAllotmentList = function (pageNo, type) {       
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
            category:category,
            purpose:purpose,
            mobNo:mobNo,
            name:name,
            pageNo: pageNo,
            type: type,
            roomType:roomType
        };

        $.ajax({
            url: "./admin/getModListForAllotment.do",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (r, textStatus, jqXHR) {
                $('#loading').hide();
                if (r.status) {
                    console.log(r);
                    if (type == "allotmentList") {
                        $('#allotmentTableBody').empty();
                        let reqId = '';
                        r.page.data.forEach(function (row, i) {
                            let rowHtml = '<tr>';
                            if (reqId != row.bookingId) {
                            	if(row.designation != "" && row.department != ""){
	                                reqId = row.bookingId;
	                                rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
	                                    '<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +
	                                    '<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + "," + row.designation + "," + row.department + '</td>'
                            	}else{
                            		reqId = row.bookingId;
                                    rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
                                        '<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +                                        
                                        '<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + '</td>'                             		
                            	}
                            }                            
                            rowHtml += '<td>' + row.bookingDt + '</td>' +
                            	'<td>' + row.fromDate + '</td>' +
                                '<input type="hidden" id="input' + (i + 1) + '" name="data" />' +
                                '<td>' + row.toDate + '</td>' +
                                '<td>' + row.category + '</td>' +
                                '<td>' + row.purpose + '</td>' +
                                '<td>' + row.type + '</td>' +
                                '<td>' + row.statusDesc + '</td>' +
                                '<td>';
                            if (row.status == 'ALO') {
                                rowHtml += '<button type="button" id="change" action="Change" title="Change Allotment" class="btn btn-sm btn-view"><i class="fa fa-refresh" aria-hidden="true"></i></button> ' +
                                    '<button type="button" id="cancel" action="Cancel" title="Cancel" class="btn btn-sm btn-cancel"><i class="fa fa-times" aria-hidden="true"></i></button> '
                            } else {
                                rowHtml += '<button type="button" id="allot" action="View" title="Allotment" class="btn btn-sm btn-view"><i class="fa fa-map-marker" aria-hidden="true"></i></button> ';
                            }
                            rowHtml += '</td>' +
                                '</tr>';
                            $('#allotmentTableBody').append(rowHtml);
                            $('#input' + (i + 1)).val(JSON.stringify(row));
                        });

                        let pageNo = $("#allotPageNo").html();
                        if (pageNo >= r.page.totalPages) {
                            $("#allotPageNext").attr("disabled", true);
                        } else {
                            $("#allotPageNext").attr("disabled", false);
                        }

                        if (pageNo == 1) {
                            $("#allotPagePrev").attr("disabled", true);
                        } else {
                            $("#allotPagePrev").attr("disabled", false);
                        }
                    } else if (type == "changeList") {

                        // load data into change list table
                        $('#changeListTableBody').empty();
                        let reqId = '';
                        r.page.data.forEach(function (row, i) {
                            let rowHtml = '<tr>';                            
                            if (reqId != row.bookingId) {
                            	if(row.designation != "" && row.department != ""){
	                                reqId = row.bookingId;
	                                rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
	                                    '<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +
	                                    '<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + "," + row.designation + "," + row.department + '</td>'
                            	}else{
                            		reqId = row.bookingId;
                                    rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
                                        '<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +                                        
                                        '<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + '</td>'                             		
                            	}
                            } 
                            rowHtml += '<td>' + row.bookingDt + '</td>' +
                            	'<td>' + row.fromDate + '</td>' +
                                '<input type="hidden" id="changeInput' + (i + 1) + '" name="data" />' +
                                '<td>' + row.toDate + '</td>' +
                                '<td>' + row.category + '</td>' +
                                '<td>' + row.purpose + '</td>' +
                                '<td>' + row.type + '</td>' +
                                '<td>' + row.statusDesc + '</td>' +
                                '<td>' +
                                '<button type="button" id="change" action="Change" title="Change Allotment" class="btn btn-sm btn-view"><i class="fa fa-refresh" aria-hidden="true"></i></button> ' +
                                '<button type="button" id="cancel" action="Cancel" title="Cancel" class="btn btn-sm btn-cancel"><i class="fa fa-times" aria-hidden="true"></i></button> ' +
                                '</td>' +
                                '</tr>';
                            $('#changeListTableBody').append(rowHtml);
                            $('#changeInput' + (i + 1)).val(JSON.stringify(row));
                        });

                        let pageNo = $("#changePageNo").html();
                        if (pageNo >= r.page.totalPages) {
                            $("#changePageNext").attr("disabled", true);
                        } else {
                            $("#changePageNext").attr("disabled", false);
                        }

                        if (pageNo == 1) {
                            $("#changePagePrev").attr("disabled", true);
                        } else {
                            $("#changePagePrev").attr("disabled", false);
                        }
                    } else if (type == "modifyList"){

                        // load data into modify list table
                        $('#modifyListTableBody').empty();
                        let reqId = '';
                        r.page.data.forEach(function (row, i) {
                            let rowHtml = '<tr>';                            
                            if (reqId != row.bookingId) {
                            	if(row.designation != "" && row.department != ""){
	                                reqId = row.bookingId;
	                                rowHtml += '<td rowspan="' + row.noRooms + '">' + (i + 1) + '</td>' +
	                                    '<td rowspan="' + row.noRooms + '">' + row.bookingId + '</td>' +
	                                    '<td rowspan="' + row.noRooms + '" style="text-align:left;">' + row.name + "," + row.designation + "," + row.department + '</td>'
                            	}else{
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

    $("#allotPageNext").on("click", function () {
        let pageNo = parseInt($("#allotPageNo").html());
        getAllotmentList(pageNo + 1, "allotmentList");
        $("#allotPageNo").html(pageNo + 1);
    });

    $("#allotPagePrev").on("click", function () {
        let pageNo = parseInt($("#allotPageNo").html());
        if (pageNo > 1) {
            getAllotmentList(pageNo - 1, "allotmentList");
            $("#allotPageNo").html(pageNo - 1);
        }
    });

    /*
     * mod(table) function mod(table){ }
     */

    $('#modify').on('click', function () {
   		 $(".forPreview").show();
        $("#ApproveSectionContainer").hide();
        
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId, "ALLOTMENT");
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    document.getElementById("headerName").innerHTML = "Allotment List - Modify";
                    $('#modify').addClass('custom-btn-active');
                    $('#change').removeClass('custom-btn-active');
                    $('#allotment').removeClass('custom-btn-active');
                    $('#report').removeClass('custom-btn-active');
                    $('#cancel').removeClass('custom-btn-active');
                    $('#preview').removeClass('custom-btn-active');
                    $("#avail_list").hide();
                    $("#allotmentList").hide();
                    $("#cancelList").hide();
                    $("#modifyList").show();
                    $("#modifyListDataTable").show();
                    $($.fn.dataTable.tables(true)).DataTable()
                        .columns.adjust();
                    $("#addListDataTable").show();
                    $("#changesList").hide();
                    pageType = 'modifyList';
                    getAllotmentList(1, "modifyList");
                    $("allotPageNo").html(1);
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

    $("#modPageNext").on("click", function () {
        let pageNo = parseInt($("#modPageNo").html());
        getAllotmentList(pageNo + 1, "modifyList");
        $("#modPageNo").html(pageNo + 1);
    });

    $("#modPagePrev").on("click", function () {
        let pageNo = parseInt($("#modPageNo").html());
        if (pageNo > 1) {
            getAllotmentList(pageNo - 1, "modifyList");
            $("#modPageNo").html(pageNo - 1);
        }
    });

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
            render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },
            {"targets": 1, "data": "bookingId"},
            {"targets": 2, "data": "name"},
            {"targets": 3, "data": "fromDate"},
            {"targets": 4, "data": "toDate"},
            {"targets": 5, "data": "noOfAdults"},
            {"targets": 6, "data": "noOfDays"},
            {"targets": 7, "data": "type"},
            {"targets": 8, "data": "statusDesc"}
        ],
    });

    let allotRooms = function (data) {
        let list = [];
        list.push(data);
        guestTable.clear();
        guestTable.rows.add(list).draw();
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

    $("#allotmentModal").on("hidden.bs.modal", function () {
        $("#modifyBlock").hide();
        $("#allotRoomBtn").show();
        $("#modifyRoomBtn").hide();
        $("#prevOrderNo").val("");
        $("#modifyDate").val("");
        $("#reason").val("");
    });

    $('#roomView').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let ghId = $('#gHouse').val();
        roomType = "room";
        getTotalRooms(bookingId, cityId, ghId, type);
    });

    let getTotalRooms = function (bookingId, cityId, ghId, type) {
        $.ajax({
            url: './admin/getTotalRooms/' + bookingId + '/' + cityId + '/' + ghId + '.do',
            type: 'GET',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                $('#loading').hide();
                console.log(data);
                $('#allotmentModal').modal('show');
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
                data.roomsModel.drmList.forEach(function (drm, i) {
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
            error: function (e) {
                $('#loading').hide();
                console.log("ERROR: ", e);
            }
        });
    }

    let roomBedAllotment = (data) => {
        $('#roomBedAllotment tbody').empty();
        let bedStr = '';
        data.roomsModel.drmList.forEach(function (drm, i) {
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
                                    '<input type="text" onkeypress="return onlyNumberKey(event)" placeholder="Rent" name="rent" autocomplete="off" value="' + roomDetail.roomRent + '">';
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
                                            '<input type="text" onkeypress="return onlyNumberKey(event)" placeholder="Rent" name="rent" autocomplete="off" value="' + bedDetail.roomRent + '">';
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
                                '<input type="text" placeholder="Rent" onkeypress="return onlyNumberKey(event)" name="rent" autocomplete="off" value = "' + roomDetail.roomRent + '">';
                            booked = true;
                            break;
                        }
                    }
                }
                if (!booked) {
                    bedStr += '<input type="text" placeholder="Room No" name="roomNo" id="date' + (i + 1) + '" disabled>' +
                        '</td> ' +
                        '<td class="text-truncate text-center">' +
                        '<input type="text" placeholder="Rent" name="rent" autocomplete="off">';
                }
            }
            bedStr += '</td> ' +
                '</tr>';
        });
        $('#roomBedAllotment tbody').append(bedStr);
    }

    $('#dormitoryView').on('click', function() {
        if(!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let ghId = $('#gHouse').val();
        roomType = "bed";
        getTotalBeds(bookingId, cityId, ghId, type);
    });

    let getTotalBeds = (bookingId, cityId, ghId, type) => {
        $.ajax({
            url: './admin/getTotalBeds/' + bookingId + '/' + cityId + '/' + ghId + '.do',
            type: 'GET',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                $('#loading').hide();
                $('#bedListTable thead').empty();
                if(data.roomsModel.roomNos.length == 0) {
                    swal("No beds available in this guest house");
                    $('#allotmentModal').modal('hide');
                    return false;
                }
                $('#allotmentModal').modal('show');
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
                data.roomsModel.drmList.forEach(function (drm, j) {
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
                                if (roomNo != drm.roomDetails[i+1].roomNo) {
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
            error: function (e) {
                $('#loading').hide();
                console.log("ERROR: ", e);
            }
        });
    }

    $('#roomListTable tbody').on('click', '.roomBtn', function () {
        roomBtnClick.call(this, roomType);
    });

    $('#roomListTable tbody').on('click', '.bedBtn', function () {
        bedBtnClick.call(this, roomType);
    });

    let bedBtnClick = function (roomType) {
        $(this).closest('tr').find('.hotel-icon-active').removeClass('hotel-icon-active');
        $(this).addClass('hotel-icon-active');

        let dateClass = $(this).attr('class').split(' ').filter(function (item) {
            return item.indexOf('date') === 0;
        });

        let roomNo = $(this).closest('td').find('input[name="roomNo"]').val();
        roomNo += "," + $(this).text();
        $('#' + dateClass[0]).val(roomNo);
    }

    let roomBtnClick = function (roomType) {
        $(this).closest('tr').find('.hotel-icon-active').removeClass('hotel-icon-active');
        $(this).addClass('hotel-icon-active');

        let dateClass = $(this).attr('class').split(' ').filter(function (item) {
            return item.indexOf('date') === 0;
        });

        let roomNo = $(this).closest('td').find('input[name="roomNo"]').val();
        if(roomType == "bed") {
           roomNo += "," + $(this).text();
        }
        $('#' + dateClass[0]).val(roomNo);
    }

    $('#bedListTable tbody').on('click', '.roomBtn', function () {
        roomBtnClick.call(this, roomType);
    });

    //collect all data from roomBedAllotment and submit
    $('#allotRoomBtn').on('click', function () {
        let allotSubmit = {bookingId: bookingId, modId: modId, mrrId: mrrId};
        let data = [];
        let correct = true;
        $('#roomBedAllotment tbody tr').each(function () {
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
            if (roomType == "bed") {
                swal('Please provide bed no and rent');
            } else {
                swal('Please provide room no and rent');
            }
            return false;
        }
        allotSubmit.roomDetails = data;
        $.ajax({
            url: './admin/allotRooms/' + type + '.do',
            type: 'POST',
            data: JSON.stringify(allotSubmit),
            contentType: 'application/json',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            }, success: function (data) {
                $('#loading').hide();
                if (data.status) {
                    $('#allotmentModal').modal('hide');
                    swal(data.msg);
                    $('#allotment').click();
                } else {
                    swal(data.msg);
                }
            }, error: function (e) {
                $('#loading').hide();
                console.log("ERROR: ", e);
            }
        });
    });

    $("#modifyRoomBtn").on('click', function () {
        let prevOrderNo = $('#prevOrderNo').val();
        let reason = $('#reason').val();
        let prevOrderDt= $('#modifyDate').val();
        if(prevOrderNo == '' || reason == '' ||prevOrderDt==''){
            swal('Please provide previous order no and reason and previous order date');
            return false;
        }
        let modifySubmit = {prevOrderNo: prevOrderNo, reason: reason,prevOrderDt:prevOrderDt};
        let data = [];
        let correct = true;
        $('#roomBedAllotment tbody tr').each(function () {
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
        if(!correct){
            swal('Please provide room no and rent');
            return false;
        }
        modifySubmit.roomDetails = data;
        $.ajax({
            url: './admin/allotRooms/' + type + '.do',
            type: 'POST',
            data: JSON.stringify(modifySubmit),
            contentType: 'application/json',
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            }, success: function (data) {
                $('#loading').hide();
                if (data.status) {
                    $('#allotmentModal').modal('hide');
                    swal('Rooms modified successfully');
                    $('#modify').click();
                } else {
                    swal(data.msg);
                }
            }, error: function (e) {
                $('#loading').hide();
                console.log("ERROR: ", e);
            }
        });
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
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {"width": "5%", "targets": 1, "data": "bookingId"},
            {"width": "5%", "targets": 2, "data": "bookingDt"},
            {"width": "10%", "targets": 3, "data": "name"},
            {"width": "10%", "targets": 4, "data": "fromDate"},
            {"width": "10%", "targets": 5, "data": "toDate"},
            {"width": "10%", "targets": 6, "data": "desg"},
            {"width": "10%", "targets": 7, "data": "mobile"},
            {"width": "10%", "targets": 8, "data": "statusDesc"},
            {"width": "10%", "targets": 9, "data": "typeOfRoom"},
            {
                "width": "10%", "targets": 10, "data": null,
                render: function (data, type, row, meta) {
                    if (data.canStatus == 'MCAN') {
                        return '<button type="button" id="cancel" title="Cancel" class="btn btn-sm btn-cancel" action="cancel" ><i class="fa fa-times" aria-hidden="true"></i></button> '
                    } else {
                        return '<button type="button" id="partialCancel" title="Partial Cancel" class="btn btn-sm btn-cancel" action="partialCancel" ><i class="fa fa-times" aria-hidden="true"></i></button> '
                    }
                }
            }
        ],
    });

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

    $('#cancelReservation').on('click', function () {
    	$(".forPreview").show();
        $("#ApproveSectionContainer").hide();
        $('#preview').removeClass('custom-btn-active');
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId, "ALLOTMENT");
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    $("#cancelReservationModal").modal('show');
                    getDataToCancelReservationTable();
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

    let getDataToCancelReservationTable = function () {
        let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let postData = {
            cId: city,
            gId: gHouse,
            type: "ALO"
        }
        $.ajax({
            url: './admin/getCancellationModLstDtls.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
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
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
        });
    }

    var canGuestTable = $("#canGuestDtlsTable").DataTable({
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
            render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },
            {"targets": 1, "data": "bookingId"},
            {"targets": 2, "data": "name"},
            {"targets": 3, "data": "fromDate"},
            {"targets": 4, "data": "toDate"},
            {"targets": 5, "data": "noOfDays"},
            {"targets": 6, "data": "statusDesc"}
        ],
    });

    $('#cancelReservationTable').on('click', 'tbody #cancel', function () {
        let data = canTable.row($(this).closest('tr')).data() != undefined ? canTable.row($(this).closest('tr')).data() : canTable.row($(this)).data();
        loadDataIntoCanModal(data, "C");
       $('#cancelShowModal').modal('show');
    });

    let canBookingId = "";

    let loadDataIntoCanModal = (data, type) => {
        $("loading").show();
        console.log(data);
        canBookingId = data.bookingId;
        let list = [];
        list.push(data);
        canGuestTable.clear();
        canGuestTable.rows.add(list).draw();

        $.ajax({
            url: './admin/getCanRoomDetails/' + data.bookingId + "/" + type + '.do',
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                $('#loading').hide();
                $('#roomBedCancel tbody').empty();
                $('#cancelType').val(type);
                let bedStr = '';
                result.data.forEach(function (drm) {
                    bedStr += '<tr>' +
                        '<td class="roomDormfont text-truncate text-center">' + drm.toDate + '</td>' +
                        '<td class="text-truncate text-center">' +
                        '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>' +
                        '</td> ' +
                        '<td class="text-truncate text-center">' +
                        '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                        '</td> ' +
                        '<td class="text-truncate text-center">';
                    if (drm.occupied) {
                        bedStr += '<input type="checkbox" class="text-center selCan" name="cancel" /> ' +
                            '<input type="hidden" name="allotId" value="' + drm.allotId + '" />';
                    } else {
                        if (type == 'C') {
                            bedStr += 'NOC';
                        } else {
                        	if (data.status == 'PRO') {
                            	bedStr += 'PRO';
                        	} else {
                        		bedStr += 'CIN';
                        	}
                            
                        }
                    }
                    bedStr += '</td> ' +
                        '</tr>';
                });
                $('#roomBedCancel tbody').append(bedStr);
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

    let aId = '';

    $('#roomBedCancel tbody').on('change', '.selCan', function () {
        aId = $(".selCan:checked").map(function () {
            return $(this).closest('tr').find('input[name="allotId"]').val();
        }).get().join(",");

        if ($('.selCan').length == $('.selCan:checked').length) {
            $('#cancelBtn').prop('hidden', false);
        } else {
            $('#cancelBtn').prop('hidden', true);
        }
    });

    $('#cancelBtn').on('click', function () {
        let type = $('#cancelType').val();
        if (type === 'C') {
            cancelReservation();
        } else if (type === 'P') {
            partialCancelReservation();
        }
    });

    let cancelReservation = () => {
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
                    url: './admin/cancelReservationForAllot/' + canBookingId + '.do',
                    type: "GET",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (result) {
                        $('#loading').hide();
                        if (result.success) {
                            swal(result.msg);
                            $('#cancelShowModal').modal('hide');
                            getDataToCancelReservationTable();
                        } else {
                            swal(result.msg);
                        }
                    }
                });
            });
    }

    let partialCancelReservation = () => {
        swal({
                title: "Are you sure? ",
                text: "Are you sure to partial cancel!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, cancel it!",
                closeOnConfirm: false
            },
            function () {
                let postData = {
                    allotId: aId,
                    bookingId: canBookingId
                }
                $.ajax({
                    url: './admin/partialCancelAllot.do',
                    data: JSON.stringify(postData),
                    contentType: "application/json",
                    type: "POST",
                    beforeSend: function (xhr) {
                        $('#loading').show();
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (result) {
                        $('#loading').hide();
                        if (result.success) {
                            swal(result.msg);
                            $('#cancelShowModal').modal('hide');
                            getDataToCancelReservationTable();
                        } else {
                            swal(result.msg);
                        }
                    }
                });
            });
    }

    $('#cancelShowModal').on('hidden.bs.modal', function () {
        $('#cancelBtn').prop("hidden", true);
    });

    $('#cancelReservationTable').on('click', 'tbody #partialCancel', function () {
        var data = canTable.row($(this).closest('tr')).data() != undefined ? canTable.row($(this).closest('tr')).data() : canTable.row($(this)).data();
        loadDataIntoCanModal(data, "P");
        $('#cancelShowModal').modal('show');
    });
 // SANDIP
$('#preview').on('click',  function () {
		if (!isCityAndGHSelected())
            return false;
        $('#modifyBlock').hide();
        $("#modifyRoomBtn").hide();
        $('#allotment').removeClass('custom-btn-active');
        $('#change').removeClass('custom-btn-active');
        $('#cancel').removeClass('custom-btn-active');
        $('#modify').removeClass('custom-btn-active');
        $('#preview').addClass('custom-btn-active');
         $($.fn.dataTable.tables(true)).DataTable()
        .columns.adjust();
        $(".forPreview").hide();
        $("#ApproveSectionContainer").show();
        getallProcesslist();
        getAllCancelList();
        getAllModifiedList();
        getAllCoveyanceList();
        getAllConferenceList();
    });
	var table1 = null;
    table1 = $("#draftListDataTable").DataTable({
        "paging": true,
        "searching": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        }, {
            "targets": 1, "data": "cityName"
        },{
            "targets": 2, "data": "blockName"
        },{
            "targets": 3, "data": "goSign"
        }, {
            "targets": 4, "data": "createdBy"
        },
        {
            "targets": 5, "data": "createdDate"
        },
        {
            "targets": 6, "data": "status"
        },{
            "targets": 7, "data": null, render: function (data, type, row, meta) {             
                return `<input type="hidden" value="${data.processId}"><button type="button" id="view" action="View" title="View" class="btn btn-view btn-sm "><i class="fa fa-eye" aria-hidden="true"></i></button> ` + `<button type="button" id="accept" action="accept"  title="Approve" class="btn btn-edit btn-sm "><i class="fa fa-check" aria-hidden="true"></i></button> ` + `<button type="button" id="cancel" action="cancel" title="Cancel"  class="btn btn-dlt btn-sm "><i class="fa fa-close" aria-hidden="true"></i></button> `

            }
        }],
    });

    var table6 = null;
    table6 = $("#modListDataTable").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": false,
        "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [ {
            "width":"5%","targets": 0, "data": "bookingId"
        }, {
			"targets":1,"data": function(row, type, val, meta) {
					return [row.name];
			},
			"render": function(data, type, full, meta) {
					return data[0].split(',')[0].trim();
			}
		}, {
        	"width":"10%", "targets": 2, "data": "reqDate"
        }, {
        	"width":"35%","targets": 3, "data": "frmDt"
        },{
        	"width":"30%","targets": 4, "data": "toDt"
        },{
        	"width":"10%","targets": 5, "data": "quotatype"
        }, {
        	"width":"10%", "targets": 6, "data": null, render: function (data, type, row, meta) {
                return '<button type="button" id="viewApproveListData" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> '
            }
        }
        ],
    });

    //table6.clear();
    //table6.rows.add(x6).draw();

    var table7 = null;
    table7 = $("#modifiedListDatatable").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": false,
        "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "width": "5%", "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        }, {
            "width": "5%", "targets": 1, "data": "bookingId"
        },  {
			"targets": 2,"data": function(row, type, val, meta) {
					return [row.name];
			},
			"render": function(data, type, full, meta) {
					return data[0].split(',')[0].trim();
			}
		}, {
            "width": "10%", "targets": 3, "data": "bookingDt"
        }, {
            "width": "30%", "targets": 4, "data": "fromDate"
        }, {
            "width": "30%", "targets": 5, "data": "toDate"
        }, {
            "width": "10%", "targets": 6, "data": "category"
        },{
            "width": "10%", "targets": 7, "data": null, render: function (data, type, row, meta) {
                return '<button type="button" id="viewModifiedListData" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> '
            }
        }
        ],
    });

    var table8 = null;
    table8 = $("#cancelListDataTable").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": false,
        "lengthMenu": [[5, 10, 20, 30, -1], [5, 10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "width": "5%", "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        }, {
            "width": "5%", "targets": 1, "data": "bookingId"
        },{
			"targets": 2,"data": function(row, type, val, meta) {
					return [row.name];
			},
			"render": function(data, type, full, meta) {
					return data[0].split(',')[0].trim();
			}
		},{
            "width": "10%", "targets": 3, "data": "bookingDt"
        }, {
            "width": "30%", "targets": 4, "data": "fromDate"
        }, {
            "width": "30%", "targets":5, "data": "toDate"
        }, {
            "width": "10%", "targets": 6, "data": "status",
        },{
            "width": "10%", "targets": 7, "data": null, render: function (data, type, row, meta) {
                return '<button type="button" id="viewCancelListData" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> '
            }
        }
        ],
    });
    
    var table9 = null;
    table9 = $("#convListDataTable").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": false,
        "lengthMenu": [[5, 10, 20, 30, -1], [5, 10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "width": "5%", "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        }, {
            "width": "5%", "targets": 1, "data": "bookingId"
        },{
			"targets": 2,"data": function(row, type, val, meta) {
					return [row.name];
			},
			"render": function(data, type, full, meta) {
					return data[0].split(',')[0].trim();
			}
		}, {
            "width": "10%", "targets": 3, "data": "bookingDt"
        }, {
            "width": "30%", "targets": 4, "data": "arrivalDate"
        }, {
            "width": "30%", "targets": 5, "data": "departureDate"
        }, {
            "width": "10%", "targets": 6, "data": "category",
        },{
            "width": "10%", "targets": 7, "data": null, render: function (data, type, row, meta) {
                return '<button type="button" id="viewConveyanceListData" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> '
            }
        }
        ],
    });

    var confTable = $("#conferenceListDatatable").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": false,
        "lengthMenu": [[5, 10, 20, 30, -1], [5, 10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "width": "5%", "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        }, {
            "width": "5%", "targets": 1, "data": "bookingId"
        },{
			"targets": 2,"data": function(row, type, val, meta) {
					return [row.name];
			},
			"render": function(data, type, full, meta) {
					return data[0].split(',')[0].trim();
			}
		}, {
            "width": "10%", "targets": 3, "data": "bookingDt"
        }, {
            "width": "30%", "targets": 4, "data": "fromDate"
        }, {
            "width": "30%", "targets": 5, "data": "toDate"
        }, {
            "width": "10%", "targets": 6, "data": "category",
        },{
            "width": "10%", "targets": 7, "data": null, render: function (data, type, row, meta) {
                return '<button type="button" title="View" id="confView" class="btn btn-view btn-sm confView"><i class="fa fa-eye" aria-hidden="true"></i></button> '
            }
        }
        ],
    });
wholeDataView={
	canList:[],
	conferenceList:[],
	conveyanceList:[],
	guestDetails:[],
	modList:[],
	prcmodList:[],
};
var getAllCoveyanceList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/getAllotTimeAllConveyanceDataForApprove.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
            $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
            	wholeDataView.conveyanceList=data.data;
                console.log(data);
                if (data.data.length > 0) {
                    emptyList = false;
                }
                table9.clear();
                table9.rows.add(data.data).draw();
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

    var getAllCancelList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        var postData = {
            cId: city, gId: gHouse
        }
        $.ajax({
            url: './admin/getAllCancelDataForApproveAllotTime.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
            $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
                console.log(result);
                wholeDataView.canList=result.data;
                if(result.data.length > 0) {
                    emptyList = false;
                }
                table8.clear();
                table8.rows.add(result.data).draw();
            },
            error: function (jqXHR, status, error) {
                swal(error);
            }
        });

    }      

    var getAllModifiedList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        var postData = {
            cId: city, gId: gHouse
        }
        $.ajax({
            url: './admin/getAllModifiedDataForApproveAllotTime.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
            $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
                console.log(result);
                wholeDataView.modList=result.data;
                if (result.data.length > 0) {
                    emptyList = false;
                }
                table7.clear();
                table7.rows.add(result.data).draw();
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
     var getAllConferenceList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/getAllConferenceDataForApprove.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
            $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
                console.log(data);
                wholeDataView.conferenceList=data.data;
                if (data.data.length > 0) {
                    emptyList = false;
                }
                confTable.clear();
                confTable.rows.add(data.data).draw();
            },
            error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }
     var getallProcesslist = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/allProcessApplicationListmoderatorView.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
                console.log(data);
                wholeDataView.guestDetails=data.data;
                if(data.data.length > 0) {
                    emptyList = false;
                }
                $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
                table6.clear();
                table6.rows.add(data.data).draw();
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
  $('#conferenceListDatatable tbody').on('click', '#confView', function () {
        let data = confTable.row($(this).parents('tr')).data();
        console.log(data.bookingId);
        //appData = data;
        bookingId = data.bookingId;
        $('#confModModal').modal('show');
        getAndSetGuestConferenceDetails(bookingId);
    });
  var getAndSetGuestConferenceDetails = function (bookingId) {
        $('#loading').show();
        $.ajax({
            url: "./admin/getConferenceBookingDetailsByBookingId/" + bookingId + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (r, textStatus, jqXHR) {
                console.log(r);                                             
                setTimeout(function () {                                        
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
  $('#modListDataTable tbody').on('click', '#viewApproveListData', function () {
        var data = table6.row($(this).parents('tr')).data();    
        $('#viewApproveModal').modal('show');
        if(data.status = 'ALO'){
        	$("#allotmentBlock").show();
        }
        $("#modifiedBlock").hide();
        $("#fullCanBlock").hide();
        getAndSetGuestViewDetails(data.bookingId);
    });  
 var getAndSetGuestViewDetails = function (bookingId) {
        $('#loading').show();
        $.ajax({
            url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (r, textStatus, jqXHR) {
                console.log(r);
                $('.clearIdValClass').text('');
                $('.clearIdValClass').val('');
                appData = r.data;
                bookingReqId = r.data.bookingId;
                let modeOfTravelFrom = r.data.travelModeFr == 'T' ? 'Train' : r.data.travelModeFr == 'F' ? 'Flight' : ''
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

                setTimeout(function () {
                    if (r.data.duringstay == 1) {
                        $('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                    } else {
                        $('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                        $('#yesDuringStay').hide();
                    }
                    var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
                    if (convay == 1) {
                    	if(r.data.isArrivalConv){
                    		$('#modArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
                    	}else{
                    		$('#modArrivalDetails').html("Arrival Not Requested");
                    	}
                    	if(r.data.isDepartueConv){
                    		$('#modDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
                    	}else{
                    		$('#modDepartureDetails').html("Departure Not Requested");
                    	} 
                        //$('#modArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
                        //$('#modDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
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
                    $('#modNob').text(r.data.noOfBeds);
                    $('#modPurpose').text(r.data.purpName);
                    $('#modOffAddr').text(r.data.offAddr);
                    $('#modDepPlace').text(r.data.depPla);
                    if(r.data.bookingId.startsWith("P")){
                    	$("#profileDoc").html('<a href="./imagedownload/user_' + r.data.userId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#profileDoc").html('');
                    }
                   
                    if (suppdocname != null) {
                        $("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#suppDocDtls").html('');
                    }
                    if (catgdocname != null) {
                        $("#catDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#catDocDtls").html('');
                    }
                    if (gdocname != null) {
                        $("#guestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#guestDocDtls").html('');
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
                    if (r.data.status == "ALO") {
	                    $('#allotmentBlock').show();
	                    $('#roomBedAllotment1 tbody').empty();
	                    let bedStr = '';
	                    r.data.roomDetails.forEach(function (drm) {
                            bedStr += '<tr>' +
                                '<td class="text-truncate text-center">' + drm.fromDate + '</td>' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>' +
                                '</td> ' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                                '</td> ' +
                                '</tr>';
	                    });
	                    $('#roomBedAllotment1 tbody').append(bedStr);
                    }
                    
                    if ((r.data.status == "PRO" || r.data.status == "CIN") && r.data.modifiedDetails!=0	) {
	                    $('#modifiedBlock').show();
	                    $('#modifiedRoomBed tbody').empty();
	                    let modStr = '';
	                    r.data.modifiedDetails.forEach(function (mod) {
                            modStr += '<tr>' +
                                '<td class="roomDormfont text-truncate text-center">' + mod.prevOrderNo + '</td>' +
                                '<td class="roomDormfont text-truncate text-center">' + mod.reason + '</td>' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + mod.roomNo + '" disabled>' +
                                '</td> ' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + mod.roomRent + '" disabled>' +
                                '</td> ' +
                                '<td class="text-truncate text-center">';
		                        if(mod.modifiedRoomNo == null || mod.modifiedRoomNo == '') {
		                        	modStr += '<input type="text" class="text-center" placeholder="Bed No" name="modifiedBedNo" value="' + mod.modifiedBedNo + '" disabled>';
		                        } else {
		                        	modStr += '<input type="text" class="text-center" placeholder="Room No" name="modifiedRoomNo" value="' + mod.modifiedRoomNo + '" disabled>';
		                        }
		                        modStr += '</td> ' +
		                            '<td class="text-truncate text-center">' +
		                            '<input type="text" class="text-center" placeholder="Rent" name="modifiedRoomRent" value = "' + mod.modifiedRoomRent + '" disabled>' +
		                            '</td> ' +
		                            '</tr>';
	                         
	                    });
	                    $('#modifiedRoomBed tbody').append(modStr);
                    }else{
                    	$('#modifiedBlock').show();
                    }

                    $('#fullCanRoomBed tbody').empty();
                    let bedStr = '';
                    r.data.partialCancelRoomDetails.forEach(function (drm) {
                        bedStr += '<tr>' +
                            '<td class="roomDormfont text-truncate text-center">' + drm.fromDate + '</td>' +
                            '<td class="text-truncate text-center">' +
                            '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>' +
                            '</td> ' +
                            '<td class="text-truncate text-center">' +
                            '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                            '<td class="text-truncate text-center">' +
                            '<input type="text" class="text-center" name="status" value = "' + drm.type + '" disabled>' +
                            '</td> ' +
                            '</tr>';
                    });
                    $('#fullCanRoomBed tbody').append(bedStr);
                    $('#loading').hide();
                }, 1000);
            }, error: function (jqXHR, status, error) {
                swal(error);
                $('#loading').hide();
            }
        });
    }
  $('#convListDataTable tbody').on('click', '#viewConveyanceListData', function () {
        var data = table9.row($(this).parents('tr')).data();
        $('#viewConveyanceApproveModal').modal('show');
        getAndSetGuestConveyanceDetails(data.bookingId);
    });
    var getAndSetGuestConveyanceDetails = function (bookingId) {
        $('#loading').show();
        $.ajax({
            url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (r, textStatus, jqXHR) {
                console.log(r);
                $('.clearIdValClass').text('');
                $('.clearIdValClass').val('');
                appData = r.data;
                bookingReqId = r.data.bookingId;
                let modeOfTravelFrom = r.data.travelModeFr == 'T' ? 'Train' : r.data.travelModeFr == 'F' ? 'Flight' : ''
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
                
                setTimeout(function () {
                    if (r.data.duringstay == 1) {
                        $('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                         $('#convModYesDuringStay').show();
                    } else {
                        $('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                        $('#convModYesDuringStay').hide();
                    }
                    var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
                    if (convay == 1) {
                    	$('#convModYesconve').show();
                    	$('#convModNoconve').hide();
                    	if(r.data.isArrivalConv){
                    		$('#convModArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
                    	}else{
                    		$('#convModArrivalDetails').html("Arrival Not Requested");
                    	}
                    	if(r.data.isDepartueConv){
                    		$('#convModDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
                    	}else{
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
                    if(r.data.bookingId.startsWith("P")){
                    	$("#convProfileDoc").html('<a href="./imagedownload/user_' + r.data.userId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#convProfileDoc").html('');
                    }
                    if (suppdocname != null) {
                        $("#convSuppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	 $("#convSuppDocDtls").html("");
                    }
                    if (catgdocname != null) {
                        $("#convCatDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	 $("#convCatDocDtls").html("");
                    }
                    if (gdocname != null) {
                        $("#convGuestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	 $("#convGuestDocDtls").html("");
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
  $('#cancelListDataTable tbody').on('click', '#viewCancelListData', function () {
        var data = table8.row($(this).parents('tr')).data();
        $('#viewApproveModal').modal('show');
        $("#modifiedBlock").hide();
        $("#allotmentBlock").hide();
        $("#fullCanBlock").show();
        getAndSetGuestViewDetails(data.bookingId);
    });
     var getAndSetGuestViewDetails = function (bookingId) {
        $('#loading').show();
        $.ajax({
            url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (r, textStatus, jqXHR) {
                console.log(r);
                $('.clearIdValClass').text('');
                $('.clearIdValClass').val('');
                appData = r.data;
                bookingReqId = r.data.bookingId;
                let modeOfTravelFrom = r.data.travelModeFr == 'T' ? 'Train' : r.data.travelModeFr == 'F' ? 'Flight' : ''
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

                setTimeout(function () {
                    if (r.data.duringstay == 1) {
                        $('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                    } else {
                        $('input[name="duringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                        $('#yesDuringStay').hide();
                    }
                    var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
                    if (convay == 1) {
                    	if(r.data.isArrivalConv){
                    		$('#modArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
                    	}else{
                    		$('#modArrivalDetails').html("Arrival Not Requested");
                    	}
                    	if(r.data.isDepartueConv){
                    		$('#modDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
                    	}else{
                    		$('#modDepartureDetails').html("Departure Not Requested");
                    	} 
                        //$('#modArrivalDetails').html('<p> ' + modeOfTravelFrom + ' : ' + flOrTrainNoFrom + ' at ' + arrDt + ' ' + arrTm + ' in ' + arrPla + '</p>');
                        //$('#modDepartureDetails').html('<p> ' + modeOfTravelTo + ' : ' + flOrTrainNoTo + ' at ' + depDt + ' ' + depTm + ' in ' + depPla + '</p>');
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
                    $('#modNob').text(r.data.noOfBeds);
                    $('#modPurpose').text(r.data.purpName);
                    $('#modOffAddr').text(r.data.offAddr);
                    $('#modDepPlace').text(r.data.depPla);
                    if(r.data.bookingId.startsWith("P")){
                    	$("#profileDoc").html('<a href="./imagedownload/user_' + r.data.userId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#profileDoc").html('');
                    }
                   
                    if (suppdocname != null) {
                        $("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#suppDocDtls").html('');
                    }
                    if (catgdocname != null) {
                        $("#catDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#catDocDtls").html('');
                    }
                    if (gdocname != null) {
                        $("#guestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#guestDocDtls").html('');
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
                    if (r.data.status == "ALO") {
	                    $('#allotmentBlock').show();
	                    $('#roomBedAllotment1 tbody').empty();
	                    let bedStr = '';
	                    r.data.roomDetails.forEach(function (drm) {
                            bedStr += '<tr>' +
                                '<td class="text-truncate text-center">' + drm.fromDate + '</td>' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>' +
                                '</td> ' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                                '</td> ' +
                                '</tr>';
	                    });
	                    $('#roomBedAllotment1 tbody').append(bedStr);
                    }
                    
                    if (r.data.status == "PRO" || r.data.status == "CIN") {
	                    $('#modifiedBlock').show();
	                    $('#modifiedRoomBed tbody').empty();
	                    let modStr = '';
	                    r.data.modifiedDetails.forEach(function (mod) {
                            modStr += '<tr>' +
                                '<td class="roomDormfont text-truncate text-center">' + mod.prevOrderNo + '</td>' +
                                '<td class="roomDormfont text-truncate text-center">' + mod.prevOrderDate + '</td>' +
                                '<td class="roomDormfont text-truncate text-center">' + mod.reason + '</td>' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + mod.roomNo + '" disabled>' +
                                '</td> ' +
                                '<td class="text-truncate text-center">' +
                                '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + mod.roomRent + '" disabled>' +
                                '</td> ' +
                                '<td class="text-truncate text-center">';
		                        if(mod.modifiedRoomNo == null || mod.modifiedRoomNo == '') {
		                        	modStr += '<input type="text" class="text-center" placeholder="Bed No" name="modifiedBedNo" value="' + mod.modifiedBedNo + '" disabled>';
		                        } else {
		                        	modStr += '<input type="text" class="text-center" placeholder="Room No" name="modifiedRoomNo" value="' + mod.modifiedRoomNo + '" disabled>';
		                        }
		                        modStr += '</td> ' +
		                            '<td class="text-truncate text-center">' +
		                            '<input type="text" class="text-center" placeholder="Rent" name="modifiedRoomRent" value = "' + mod.modifiedRoomRent + '" disabled>' +
		                            '</td> ' +
		                            '</tr>';
	                         
	                    });
	                    $('#modifiedRoomBed tbody').append(modStr);
                    }

                    $('#fullCanRoomBed tbody').empty();
                    let bedStr = '';
                    r.data.partialCancelRoomDetails.forEach(function (drm) {
                        bedStr += '<tr>' +
                            '<td class="roomDormfont text-truncate text-center">' + drm.fromDate + '</td>' +
                            '<td class="text-truncate text-center">' +
                            '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>' +
                            '</td> ' +
                            '<td class="text-truncate text-center">' +
                            '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                            '<td class="text-truncate text-center">' +
                            '<input type="text" class="text-center" name="status" value = "' + drm.type + '" disabled>' +
                            '</td> ' +
                            '</tr>';
                    });
                    $('#fullCanRoomBed tbody').append(bedStr);
                    $('#loading').hide();
                }, 1000);
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
  $('#modifiedListDatatable tbody').on('click', '#viewModifiedListData', function () {
        var data = table7.row($(this).parents('tr')).data();       
        $('#viewApproveModal').modal('show');
        $("#allotmentBlock").hide();
        $("#fullCanBlock").hide();
        getAndSetGuestViewDetails(data.bookingId);
    });
   $("#viewApproveModal").on("hidden.bs.modal",function(){
   		$("#yesDuringStay").show();
   });
//view time total result
    
var table4 = null;
    table4 = $("#viewCancelListDataTable").DataTable({
        "paging": true,
        "searching": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },{
            "targets": 1, "data": "noOfChildren"
        }, {
            "targets": 2,
            "data": function(row, type, val, meta) {
				return [row.fromDate, row.toDate];
			},
			"render": function(data, type, full, meta) {
				return data[0]+" to "+data[1];
			}
        }, {
            "targets": 3, "data": "noRooms"
        }, {
            "targets": 4, "data": "status"
        }],
    });

    var table5 = $("#viewModifiedListDataTable").DataTable({
        "paging": true,
        "searching": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },{
            "targets": 1, "data": "name"
        }, {
            "targets": 2,
             "data": function(row, type, val, meta) {
				return [row.fromDate, row.toDate];
			},
			"render": function(data, type, full, meta) {
				return data[0]+" to "+data[1];
			}
        }, {
            "targets": 3, "data": "submittedOn"
        }, {
            "targets": 4, "data": "prevOrderDt"
        },
        {
            "targets": 5, "data": "remarks",
        },
        {
            "targets": 6, "data": "partialCancelFromDt",
        },{
            "targets": 7, "data": "confBillId",
        },{
            "targets": 8, "data": "confCatId",
        },{
            "targets": 9, "data": "confUseId",
        }],
    });
    
     var conferenceTbl = $("#viewConferenceListTable").DataTable({
        "paging": true,
        "searching": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        }, {
            "targets": 1, "data": "name"
        }, {
            "targets": 2, "data": "fromDate"
        }, {
            "targets": 3, "data": "toDate"
        }, {
            "targets": 4, "data": "confUseDesc",
        },{
            "targets": 5, "data": "confCatDesc",
        },{
            "targets": 6, "data": "capacity",
        },{
        	"targets":7,"data":"advanceAmount"
        }],
    });
    
     var table3 = null;
    table3 = $("#viewConfirmListDataTable").DataTable({
        "paging": true,
        "searching": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
        	"width":"10%","targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },{
        	"width":"10%","targets": 1, "data": "name"
        }, {
        	"width":"10%","targets": 2, 
        	"data": function(row, type, val, meta) {
				return [row.frmDt, row.toDt];
			},
			"render": function(data, type, full, meta) {
				return data[0]+" to "+data[1];
			}
        }, {
        	"width":"10%", "targets": 3, "data": "roomNo"
        }, {
        	"width":"10%","targets": 4,
        	"data": function(row, type, val, meta) {
				return [row.goSignDesig];
			},
			"render": function(data, type, full, meta) {
				if( data[0]=="0"){
					return "No";
				}else{
					return "Yes";
				}
				
			}
        }, {
        	"width":"10%", "targets": 5,
        	 	"data": function(row, type, val, meta) {
				return [row.officeorderno];
			},

        	 "render": function(data, type, full, meta) {
				if( data[0]=="Flight/Train No:"){
					return "";
				}else{
					return data[0];
				}
				
			}
        },{
        	"width":"10%","targets": 6,
        	 	"data": function(row, type, val, meta) {
				return [row.periodOfStay];
			},

        	 "render": function(data, type, full, meta) {
				if( data[0]=="Flight/Train No:"){
					return "";
				}else{
					return data[0];
				}
				
			}
        },{
        	"width":"10%","targets": 7, "data": "roomRent",
        }],
    });
    
    
    var conTable = $("#viewConveyanceList").DataTable({
        "paging": true,
        "searching": true,
        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [{
            "targets": 0, "data": null, render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }
        },{
            "targets": 1, "data": "name"
        }, {
            "targets": 2,
            "data":"prevOrderDt"
        },
        {
            "targets": 3,
            "data": function(row, type, val, meta) {
					return [row.modId];
				},
				"render": function(data, type, full, meta) {
					if(data[0]=="Flight/Train No:"){
						return "Not Available"
					}else{
						return data[0];
					}
				}
        }, {
            "targets": 4, 
            "data": function(row, type, val, meta) {
					return [row.mrrId];
				},
				"render": function(data, type, full, meta) {
					if(data[0]=="Flight/Train No:"){
						return "Not Available"
					}else{
						return data[0];
					}
				}
        }],
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
   $('#viewBtn').on('click', function () {
        getAllDetailsByProcessid(wholeDataView);
        $('#viewModal').modal('show');
    });
    var getAllDetailsByProcessid = function (data) {
                console.log(data);
                var countForConfirm = data.guestDetails.length;
                var countForCancel = data.canList.length;
                var countForMod = data.modList.length;
                var countForConveyance = data.conveyanceList.length;
                var countForConference = data.conferenceList.length;
                $('#conferenceListDiv').hide();
                if ( countForConfirm == 0 ) {
			    	$('#overflow_div').hide();
			    }else{
			    	$('#overflow_div').show();
			    	table3.clear();
			    	table3.rows.add(data.guestDetails).draw();
			    }
			    if ( countForCancel == 0 ) {
			    	$('#cancelLstDiv').hide();
			    }else{
			    	$('#cancelLstDiv').show();
			    	table4.clear();
			    	table4.rows.add(data.canList).draw();
			    }
			    if ( countForMod == 0 ) {
			    	$('#modifiedLstDiv').hide();
			    }else{
			    	$('#modifiedLstDiv').show();
			    	 table5.clear();
		             table5.rows.add(data.modList).draw();
			    }
			    if ( countForConveyance == 0 ) {
			    	$('#conveyance_list_div').hide();
			    }else{
			    	$('#conveyance_list_div').show();
			    	conTable.clear();
			    	conTable.rows.add(data.conveyanceList).draw();
			    }
			    
			    if ( countForConference === 0 ) {
			    	console.log("countForConference "+countForConference);
			    	$('#conferenceListDiv').hide();
			    }else{
			    	$('#conferenceListDiv').show();
			    	conferenceTbl.clear();
			    	conferenceTbl.rows.add(data.conferenceList).draw();
			    }
          }
     
    //print pdf 
	$('#printPDF').click(function(){
		console.log("Inside clicked");
		var city = $('#city').val();
        var gHouse = $('#gHouse').val();
		console.log(city+" and "+gHouse);
			window.open('./admin/downloadPreviewOrderListInPDF.do?cityName='+city+'&guesthouseName='+gHouse,'_blank');
	})
	

});
function onlyNumberKey(evt) {
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)){
        return false;
    }
    return true;
}