var totalExtendsGuest = '';
var extendFromDate = '';
$('document').ready(function () {
            $('#filter-popover').on('click', function () {
                event.stopPropagation();
            });
            isEdit = false;
            var x = [{
                "requestId": "101",
                "fromDt": "21-03-2020",
                "toDt": "30-03-2020",
                "guestDt": "Amrita"
            }, {
                "requestId": "102",
                "fromDt": "21-03-2020",
                "toDt": "30-03-2020",
                "guestDt": "Amrita"
            }, {
                "requestId": "103",
                "fromDt": "21-03-2020",
                "toDt": "30-03-2020",
                "guestDt": "Amrita"
            }, {
                "requestId": "104",
                "fromDt": "21-03-2020",
                "toDt": "30-03-2020",
                "guestDt": "Amrita"
            }]


            var y = [{
                "reqId": "G38",
                "guestId": "G1",
                "guest": "Amrita ,xyz ,pqr ,bbsr",
                "rollNo": "123",
                "time": "4:00",
                "advAmount": "2000",
                "adults": "1",
                "children": "0",
                "room": "1"
            }]
            $("#editOtherDataTable").hide();            

            $("#extensionModal").on("hidden.bs.modal", function () {
                $("#editOtherDataTable").hide();
            });

            var table2 = null;
            table2 = $("#otherDataTable").DataTable({
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
                    "data": "adults"
                }, {
                    "targets": 7,
                    "data": "children"
                }, {
                    "targets": 8,
                    "data": "room"
                }

                ],
            });

//            table2.clear();
//            table2.rows.add(y).draw();

            $("#fromDt, #toDt").datetimepicker({
                minDate: new Date(),
                format: 'DD-MM-YYYY'
            });

            $("#fromTm, #toTm").datetimepicker({
                format: 'hh:mm A'
            });

            $("#extensionList").show();
            $('#extensionTable tbody').on('click', '#extension', function () {
                var data = table.row($(this).parents('tr')).data();
                $('#extensionModal').modal('show');
            });
            var table = $("#extensionDataTable")
                .DataTable(
                    {
                        "paging": true,
                        "searching": true,
                        "scrollX": true,
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
                        "columnDefs": [
                            {
                                "targets": 0,
                                "data": null,
                                render: function (data,
                                                  type, row, meta) {
                                    return meta.row
                                        + meta.settings._iDisplayStart
                                        + 1;
                                }
                            },
                            {
                                "targets": 1,
                                "data": "requestId"
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
                                "data": "guestDt"
                            },
                            {
                                "targets": 5,
                                "data": null,
                                render: function (data,
                                                  type, row, meta) {
                                    // if(data.status ==
                                    // 'REQ'){
                                    return '<button type="button" id="extension" action="View" title="Extension Checkin" class="btn btn-sm btn-edit text-center"><i class="fa fa-edit" aria-hidden="true"></i></button> '

                                }

                            }
                        ],
                    });
            table.clear();
            table.rows.add(x).draw();

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
                setTimeout(function () {
                    $('#city').trigger('change');
                }, 1000);
            }

            $("#city").change(function () {
                var id = $('#city').val();
                getGuestHouseListDtls(id);
            });

            /* get guest house details through city id  */
            var getGuestHouseListDtls = function (id) {
                $.ajax({
                    url: "./admin/getGuestHouseListDetails/" + id + ".do",
                    type: "GET",
                    contentType: "application/json",
                    beforeSend: function () {
                        $('#loading').show();
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

            $('#extensionCheckIn').on('click', function () {
            	$('#extensionCheckIn').addClass("custom-btn-active");
                var city = $('#city').val();
                var gHouse = $('#gHouse').val();
                if (city == '' || gHouse == '') {
                    swal("Please provide City or Guest House");
                } else {
                    checkForShift(city, gHouse);
                    setTimeout(function () {
                        let status = $('#shiftStatus').val();
                        if (status == "true") {
                            getExtensionCheckinDetails();
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

            $(" #extensionTable #extensionbody ").on("change", ".chckcls", function () {
                selectExtensionCheckInGstId = $('.chckcls:checked').map(function () {
                    return this.value
                }).get().join("','");
                console.log(selectExtensionCheckInGstId);
            })
         
            
            $("#filterBookingDate").datetimepicker({
                useCurrent: false,
                format: 'DD-MM-YYYY'
            });

            $('#filterBookingId').on('keyup', function () {
                this.value = this.value.toUpperCase();
            });
            
            $('#filterCategory').append(
                "<option value='C1'>Category1</option>" +
                "<option value='C2'>Category2</option>" +
                "<option value='C3'>Category3</option>" +
                "<option value='C4'>Category4</option>"
            );

            $('#filterSearch').click(function () {
            	getExtensionCheckinDetails();
            	$('#filter-popover').unbind();   
            	setTimeout(function(){
            		$('#filter-popover').bind('click',preventClick);
            	}, 1000);       
            });
            
            function preventClick() {
        	    event.stopPropagation();
        	 }

            $('#resetFiterBtn').click(function () {
                $('#filterBookingId').val('');
                $('#filterFromDate').val('');
                $('#filterCategory').val('');
                $('#filterMobNum').val('');
                $('#filterGuestName').val('');
                getExtensionCheckinDetails();
            });

            $("#submitCheckInData").on('click', function () {
                var city = $('#city').val();
                var gHouse = $('#gHouse').val();
                var saveCheckin = {city: city, gHouse: gHouse, newReqId: newRequestId};
                let data = [];
                var boolean = true;
                const split = extendFromDate.split("-");
                var fromDt = new Date();
                fromDt.setDate(parseInt(split[0]));
                fromDt.setMonth(split[1] - 1);
                fromDt.setFullYear(split[2]);

                var today = new Date();
                var date = today.getDate() + '-' + (today.getMonth()) + '-' + today.getFullYear();
                var time = today.getHours() + ":" + today.getMinutes();

                const todaysplit = date.split("-");
                var today1 = new Date();
                today1.setDate(todaysplit[0]);
                today1.setMonth(todaysplit[1]);
                today1.setFullYear(todaysplit[2]);
                console.log(today1);
                console.log(fromDt);
                if (today1.setHours(0, 0, 0, 0) < fromDt.setHours(0, 0, 0, 0)) {
                    boolean = false;
                }
                if (!boolean) {
                    swal("You can't checkin before it's from date");
                    return false;
                }
                $('#selectedExtendsGuestData tr').each(function () {
                    let ReqId = $(this).find('td:eq(0)').text();
                    let gstId = $(this).find('td:eq(1)').text();
                    let roll = $(this).find('input[name="roll"]').val();
                    let time = $(this).find('input[name="arrival"]').val();
                    let amount = $(this).find('input[name="amount"]').val();
                    let adults = $(this).find('td:eq(5)').text();
                    let children = $(this).find('td:eq(6)').text();
                    let newGstId = $(this).find('td:eq(8)').text();
                    data.push({
                        gstId: gstId,
                        ReqId: ReqId,
                        roll: roll,
                        intime: time,
                        advncAmount: amount,
                        adults: adults,
                        children: children,
                        newGstId: newGstId
                    });
                });
                saveCheckin.guestDetails = data;
                console.log(saveCheckin);
                $.ajax({
                    url: './admin/saveExtensionCheckinDetails.do',
                    type: "POST",
                    contentType: "application/json",
                    dataType: "JSON",
                    data: JSON.stringify(saveCheckin),
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (result) {
                        console.log(result);
                        $("#extensionModal").modal("hide");
                        getExtensionCheckinDetails();
                        
                        if(result.status){
                        	swal("Extension Checkin Succesfull!");
                        }else{
                        	swal("Extension Checkin not done!");
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
            });

        });



selectExtensionCheckInGstId = '';
var getExtensionCheckinDetails = function () {
   /* var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var postData = {
        cId: city,
        gId: gHouse,
    }*/
	
	let city = $('#city').val();
    let gHouse = $('#gHouse').val();
    let bookingId = $('#filterBookingId').val();
    let fromDate = $('#filterFromDate').val();
    let category = $('#filterCategory').val();
    let mobNo = $('#filterMobNum').val();
    let name = $('#filterGuestName').val();               
    
    let postData = { 
		cId: city,
        gId: gHouse,
        bookingId: bookingId,
        category:category,
        fromDate: fromDate,
        mobNo:mobNo,
        name:name,
    };
    $.ajax({
        url: './admin/getExtensionCheckinDetails.do',
        type: "POST",
        contentType: "application/json",
        dataType: "JSON",
        data: JSON.stringify(postData),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (result) {
            totalExtendsGuest = result.data;
            console.log(result);
            $("#extensionbody").html("");
            for (var i = 0; i < result.data.length; i++) {
                var rows = result.data[i].guestDetails.length;
                var details = "";

                if (result.data[i].guestDetails[0].address != "") {
                    details = details + "," + result.data[i].guestDetails[0].address;
                }
                if (result.data[i].guestDetails[0].department != "") {
                    details = details + "," + result.data[i].guestDetails[0].department;
                }
                if (result.data[i].guestDetails[0].designation != "") {
                    details = details + "," + result.data[i].guestDetails[0].designation;
                }
                var status = "";
                if (result.data[i].guestDetails[0].status == null) {
                    status = "<p style='color:red;font-weight:bold;'>NOT CHECKED IN</p>";
                } else if (result.data[i].guestDetails[0].status == "CIN") {
                    status = "<input type='checkbox' class='chckcls' value='" + result.data[i].guestDetails[0].guestId + "'>";
                } else {
                    status = "<p style='color:red;font-weight:bold;'>CHECKED OUT</p>";
                }
                $("#extensionbody").append("<tr>" +
                    "<td rowspan='" + rows + "'>" + (i + 1) + "</td>" +
                    "<td rowspan='" + rows + "'>" + result.data[i].req_id + "</td>" +
                    "<td rowspan='" + rows + "'>" + result.data[i].from_date + "</td>" +
                    "<td rowspan='" + rows + "'>" + result.data[i].to_date + "</td>" +
                    "<td rowspan='" + rows + "'>" + result.data[i].categoryType + "</td>" +
                    "<td>" + result.data[i].guestDetails[0].name + "</td>" +
                    "<td>" + result.data[i].guestDetails[0].designation + "</td>" +
                    "<td>" + result.data[i].guestDetails[0].department + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td class='text-nowrap' rowspan='" + rows + "'>" +
                    "<button type='button' id='checkIn' onclick=viewExtensionSelectedGuest('" + result.data[i].req_id + "'" + "," + "'" + result.data[i].ext_req_id + "','" + result.data[i].from_date + "','1') " +
                    " title='CheckIn' class='btn btn-sm extension ml-1'>" +
                    "<i class='fa fa-check-square-o' aria-hidden='true'></i>" +
                    "</button>" +
                    "</td>" +
                    "</tr>");
                for (var k = 1; k < result.data[i].guestDetails.length; k++) {
                    var status = "";
                    if (result.data[i].guestDetails[k].status == null) {
                        status = "<p style='color:red;font-weight:bold;'>NOT CHECKED IN</p>";
                    } else if (result.data[i].guestDetails[k].status == "CIN") {
                        status = "<input type='checkbox' class='chckcls' value='" + result.data[i].guestDetails[k].guestId + "'>";
                    } else {
                        status = "<p style='color:red;font-weight:bold;'>CHECKED OUT</p>";
                    }

                    $("#extensionbody").append("<tr>" +
                        "<td>" + result.data[i].guestDetails[k].name + "</td>" +
                        "<td>" + result.data[i].guestDetails[k].designation + "</td>" +
                        "<td>" + result.data[i].guestDetails[k].department + "</td>" +
                        "<td>" + status + "</td>" +
                        "</tr>");
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
var guestDataLength = '';
var newRequestId = '';
var viewExtensionCheckindata = function (nowId, ExistingId, fromDate) {
    if (selectExtensionCheckInGstId == '') {
        swal("Please select atleast one guest");
        $("#extensionModal").modal('hide');
        return false;
    }
    var req_id = nowId;
    var ExitingId = ExistingId;
    var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var hidval = selectExtensionCheckInGstId;
    var postData = {
        cId: city,
        gId: gHouse,
        req_id: req_id,
        existing_req_id: ExitingId,
        hidval: hidval,
        fromDate: fromDate
    }
    console.log(postData);
    $.ajax({
        url: './admin/viewExtensionCheckinDetails.do',
        type: "POST",
        contentType: "application/json",
        dataType: "JSON",
        data: JSON.stringify(postData),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (result) {
            console.log(result);
            $("#extensionModal").modal('show');
            $("#extensionCheckin").html("");
            guestDataLength = result.data;
            newRequestId = result.reqId;
            extendFromDate = result.fromDate;
            addForExtension();
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

function viewExtensionSelectedGuest(reqId, existReqId, fromDate, ext) {


    if (ext == '1') {
        for (var i = 0; i < totalExtendsGuest.length; i++) {
            if (reqId == totalExtendsGuest[i].req_id) {
                data = [];
                for (var j = 0; j < totalExtendsGuest[i].guestDetails.length; j++) {
                    if (totalExtendsGuest[i].guestDetails[j].status == "CIN") {
                        data.push("1");
                    }	
                }
                const splitted = selectExtensionCheckInGstId.split("','");
                if (data.length == splitted.length) {
                    viewExtensionCheckindata(reqId, existReqId, fromDate);
                } else {
                    swal("please select all guest");
                }
            }
        }
    } else {
        /*	viewCancelListDetails(reqId);*/
    }
}

 function addForExtension(){
    const splitted = selectExtensionCheckInGstId.split("','");
    console.log(splitted);
    if (guestDataLength.length == splitted.length) {
        $("#editOtherDataTable").show();
        $("#selectedExtendsGuestData").html("");
        for (var i = 0; i < guestDataLength.length; i++) {
            $("#selectedExtendsGuestData").append(
                "<tr>" +
                "<td style='text-align:center;'>" + guestDataLength[i].reqId + "</td>" +
                "<td style='text-align:center;'>" + guestDataLength[i].guestId + "</td>" +
                "<td>" + "<input type='text' style='text-align:center;' name='roll' id=roll" + (i) + " value='" + guestDataLength[i].rollNo + "' disabled></td>" +
                "<td>" + "<input type='text' style='text-align:center;' name='arrival' id=arrival" + (i) + " value='" + guestDataLength[i].arrivalTime + "' disabled></td>" +
                "<td>" + "<input type='text' style='text-align:center;' name='amount' id=amount" + (i) + " value='" + guestDataLength[i].advncAmount + "' disabled></td>" +
                "<td style='text-align:center;'>" + guestDataLength[i].adults + "</td>" +
                "<td style='text-align:center;'>" + guestDataLength[i].childrenBelow + "</td>" +
                "<td style='text-align:center;'>" + guestDataLength[i].roomDetails.roomNo + "</td>" +
                "<td style='display:none;'>" + splitted[i] + "</td>"
                + "</tr>");
        }
    } 
}