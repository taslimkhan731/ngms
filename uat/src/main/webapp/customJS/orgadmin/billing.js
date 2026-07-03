$('document').ready(function () {
    var bookingIdForBill;
    var billIdForBill;
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });

    let cityData = $('#cityUserlocssd').val().trim();
    let cityDetails = JSON.parse(cityData);
    let guestDetails;
    let adv = 0;
    let noc = 0;
    let nocDate = '';

    $("#city").html("");
    $("#city").append('<option value="">--Select City--</option>');
    cityDetails.forEach(option => {
        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
        $("#splitCity").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
    });
    let size = Object.keys(cityDetails).length;
    if (size == 1) {
        $('#city').val(cityDetails[0].gh_id);
        setTimeout(function () {
            $('#city').trigger('change');
        }, 1000);
    }

    $('#city').on('change', function () {
        let id = $('#city').val();
        getGuestHouseListDtls(id);
    });

    $('#showBillBody').hide();    
    
    let getGuestHouseListDtls = function (id) {
        blockId = id;
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

    $("#gHouse").change(function () {
        $('#addBillBody').show();
        $('#showBillBody').hide();
        table.clear().draw();
    });

    $('#addBill').on('click', function () {
        if(!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        $('#addBill').addClass('custom-btn-active');
        $('#showBill').removeClass('custom-btn-active');
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                $('#addBillBody').show();
                $('#showBillBody').hide();
                $('#headerName').html('Select Guests to Make Bill');
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    getAllBillingList();
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

    $('#showBill').on('click', function () {
        if(!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        $('#addBill').removeClass('custom-btn-active');
        $('#showBill').addClass('custom-btn-active');
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                $('#addBillBody').hide();
                $('#showBillBody').show();
                $('#headerName').html('Select Guest to Show Bill')
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    getShowBillList();
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

    var table = $("#billingDataTable").DataTable(
        {
            "paging": true,
            "searching": true,
            "scrollX": true,
            "lengthMenu": [[10, 20, 30, -1],
                [10, 20, 30, "All"]],
            "processing": false,
            "ordering": false,
            "info": false,
            "bFilter": false,
            "fixedHeader": {
                header: true,
                footer: true
            },
            "scrollCollapse": true,
            "columnDefs": [

            	{
                    "className": "text-center",
                    "targets": [0,2,3,4,5,6,7,8,9]
                },
                {
                    "className": "text-left text-nowrap",
                    "targets": [1]
                },
                {
                    "className": "text-right text-nowrap",
                    "targets": [8]
                },

                {"targets": 0, "data": "bookingId"},
                {"targets": 1, "data": "name"},
                {"targets": 2, "data": "fromDate"},
                {"targets": 3, "data": "toDate"},
                {"targets": 4, "data": "catgId"},
                {"targets": 5, "data": "category"},
                {"targets": 6, "data": "purpose"},
                /*                {"targets": 7, "data": "billingDt"},
               {"targets": 8, "data": "noOfChildren"},*/
                {"targets": 7, "data": "advanceAmount"},
                {"targets": 8, "data": "totalAmt"},
                {
                    "targets": 9, "data": null, render: function (data, type, row, meta) {
                        return '<button type="button" id="select" title="Billing" action="Select" class="btn btn-sm btn-secondary d-inline bill ml-1"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ' +
                            '<button type="button" id="cancel" title="Cancel Checkout" action="Cancel" class="btn btn-sm btn-dlt"><i class="fa fa-close" aria-hidden="true"></i></button>'
                    }
                }],
        });

    let showBillTable = $("#showBillTable").DataTable(
        {
            "paging": true,
            "searching": true,
            "scrollX": true,
            "lengthMenu": [[10, 20, 30, -1],
                [10, 20, 30, "All"]],
            "processing": false,
            "ordering": false,
            "info": false,
            "bFilter": false,
            "fixedHeader": {
                header: true,
                footer: true
            },
            "scrollCollapse": true,
            "columnDefs": [
            	
            	{
                    "className": "text-center",
                    "targets": [0,2,3,4,5,6,7,8,9]
                },
                {
                    "className": "text-left text-nowrap",
                    "targets": [1]
                },
                /*{
                    "className": "text-right text-nowrap",
                    "targets": [9]
                },*/
            	
                {"targets": 0, "data": "bookingId"},
                {"targets": 1, "data": "name"},
                {"targets": 2, "data": "fromDate"},
                {"targets": 3, "data": "toDate"},
                {"targets": 4, "data": "catgId"},
                {"targets": 5, "data": "category"},
                {"targets": 6, "data": "purpose"},
/*                {"targets": 7, "data": "noOfAdults"},
                {"targets": 8, "data": "noOfChildren"},*/
                {"targets": 7, "data": "billingDt"},
                {"targets": 8, "data": "totalAmt"},
                {
                    "targets": 9, "data": null, render: function (data, type, row, meta) {
                        return '<button type="button" id="select" title="Select" action="Select" class="btn btn-sm print"><i class="fa fa-print" aria-hidden="true"></i></button> ';
                    }
                }],
        });

    let loadBillDetails = (bookingId) => {
        $.ajax({
            url: "./admin/getBillDetailsByBookingId/" + bookingId + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                $('#loading').hide();
                console.log(data);
                guestDetails = data.data.guestDetails;
                $('#billModalTbody').html("");
                let str = '';
                adv = 0;
                data.data.guestDetails.forEach(function (row, i) {
                    adv += parseFloat(row.advanceAmount);
                    noc = parseFloat(data.data.nocCharge);
                    nocDate = data.data.nocDate;
                    str += '<tr>' + '' +
                        '<td>' + row.bookingId + '/' + row.guestId + '</td>' +
                        '<td>' + row.rowNo + '</td>' +
                        '<td>' + row.name + '</td>' +
                        '<td>' + row.fromDate + '</td>' +
                        '<td>' + row.toDate + '</td>' +
                        '<td>' + row.arrivalDate + '</td>' +
                        '<td>' + row.departureDate + '</td>' +
                        '<td>' + nocDate + '</td>' +
                        '<td>' + row.desg + '</td>' +
                        '<td>' + row.purpose + '</td>' +
                        '<td>' + row.noRooms + '</td>' +
                        '<td>' + row.advanceAmount + '</td>';
                    if (i === 0) {
                        str += '<td rowspan="' + data.data.guestDetails.length + '"><input type="number" min="0" name="roomRent" style="text-align: center" value="' + data.data.roomRent + '" class="inputBorder"></td>' +
                            '<td rowspan="' + data.data.guestDetails.length + '"><input type="number" min="0" name="foodCharge" style="text-align: center" value="' + data.data.foodCharge + '" class="inputBorder"></td>' +
                            '<td rowspan="' + data.data.guestDetails.length + '"><input type="number" min="0" name="newspaperCharge" style="text-align: center" value="0" class="inputBorder"></td>' +
                            '<td rowspan="' + data.data.guestDetails.length + '"><input type="number" min="0" name="travellingCharge" style="text-align: center" value="0" class="inputBorder"></td>' +
                            '<td rowspan="' + data.data.guestDetails.length + '"><input type="number" min="0" name="telephoneCharge" style="text-align: center" value="0" class="inputBorder"></td>' +
                            '<td rowspan="' + data.data.guestDetails.length + '"><input type="number" min="0" name="otherCharge" style="text-align: center" value="0" class="inputBorder"></td>' +
                            '<td rowspan="' + data.data.guestDetails.length + '">' + noc + '</td>';
                    }
                    str += '</tr>';
                });
                str += '<tr>' +
                    '<td colspan="11" style="text-align: right">Total</td>' +
                    '<td id="totalAdvance">' + adv + '</td>' +
                    '<td id="roomTotal">' + data.data.roomRent + '</td>' +
                    '<td id="foodTotal">' + data.data.foodCharge + '</td>' +
                    '<td id="newsTotal">0</td>' +
                    '<td id="travelTotal">0</td>' +
                    '<td id="telephoneTotal">0</td>' +
                    '<td id="otherTotal">0</td>' +
                    '<td id="nocTotal">' + data.data.nocCharge + '</td>' +
                    '</tr>';
                $('#billModalTbody').append(str);
            }, error: function (jqXHR, status, error) {
                $('#loading').hide();
                swal(error);
            }
        });
    };

    $('#billModalTbody').on('keyup', 'input', function () {
        if (this.name === 'roomRent') {
            $('#roomTotal').text(this.value);
        } else if (this.name === 'foodCharge') {
            $('#foodTotal').text(this.value);
        } else if (this.name === 'newspaperCharge') {
            $('#newsTotal').text(this.value);
        } else if (this.name === 'travellingCharge') {
            $('#travelTotal').text(this.value);
        } else if (this.name === 'telephoneCharge') {
            $('#telephoneTotal').text(this.value);
        } else if (this.name === 'tvCharge') {
            $('#tvTotal').text(this.value);
        } else if (this.name === 'otherCharge') {
            $('#otherTotal').text(this.value);
        }
    });

    $('#billModalTbody').on('blur', 'input', function () {
        if (this.value === '' || parseInt(this.value) < 0) {
            this.value = 0;
            if (this.name === 'roomRent') {
                $('#roomTotal').text(0);
            } else if (this.name === 'foodCharge') {
                $('#foodTotal').text(0);
            } else if (this.name === 'newspaperCharge') {
                $('#newsTotal').text(0);
            } else if (this.name === 'travellingCharge') {
                $('#travelTotal').text(0);
            } else if (this.name === 'telephoneCharge') {
                $('#telephoneTotal').text(0);
            } else if (this.name === 'tvCharge') {
                $('#tvTotal').text(0);
            } else if (this.name === 'otherCharge') {
                $('#otherTotal').text(0);
            }
        }
    });

    $("#billingDataTable tbody").on("click", ".bill", function () {
        if (!isCityAndGHSelected())
            return false;
        let data = table.row($(this).parents('tr')).data();
        console.log(data);
        $('#billModal').modal('show');
        loadBillDetails(data.bookingId);
    });

    $("#billingDataTable tbody").on("click", ".btn-dlt", function () {
        let data = table.row($(this).parents('tr')).data();
        console.log(data);
        loadCancelGuestDetails(data.bookingId);
    });

    $('#showBillTable tbody').on("click", ".print", function () {
        let data = showBillTable.row($(this).parents('tr')).data();
        bookingIdForBill = data.bookingId;
        billIdForBill = data.billId;
        console.log("Booking id for booking"+bookingIdForBill+"bill id for booking"+billIdForBill);
        $('#submitBill').hide();
        $('#printBtn').show();
        $('#createBillModal').modal('show');        
        $.ajax({
            url: './admin/showBillDetails/' + data.bookingId + '/' + data.billId + '.do',
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
                console.log(result.data.charges);
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
                $('#billNo').html(result.data.billNo);
                $('#nocDate').html('NOC Date : ' + result.data.nocDate);
                var today = new Date();
                var dd = String(today.getDate()).padStart(2, '0');
                var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                var yyyy = today.getFullYear();

                today = dd + '/' + mm + '/' + yyyy;
                console.log(today);
                console.log(result.data.guestDetails[0].ghName);
                $('#currentDate').html(today);
                $('#cityAndGuestHouse').html(result.data.guestDetails[0].ghName+", "+result.data.guestDetails[0].cityName);
            }
        });
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
    
    $('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });    
    
    $('#filterSearch').click(function () {
    	getAllBillingList();
    	getShowBillList();
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
        $('#filterBillDate').val('');
        $('#filterCategory').val('');
        $('#filterPurpose').val('');
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        $('#filterstatus').val('');
        getAllBillingList();
        getShowBillList();
    });
    
    $("#filterFromDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    $("#filterBillDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    var getAllBillingList = function () {
    	let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let fromDate = $('#filterFromDate').val();       
        let category = $('#filterCategory').val();
        let purpose = $('#filterPurpose').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();               
        
        let postData = { 
    		cId: city,
            gId: gHouse,
            bookingId: bookingId,
            fromDate: fromDate,
            category:category,
            purpose:purpose,
            mobNo:mobNo,
            name:name
        };
        $.ajax({
            url: './admin/getAllBillingList.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $("#loading").hide();
                console.log(result);
                table.clear();
                table.rows.add(result.data).draw();
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

    let getShowBillList = () => {
    	let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let fromDate = $('#filterFromDate').val();
        let billDate = $('#filterBillDate').val();
        let category = $('#filterCategory').val();
        let purpose = $('#filterPurpose').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();               
        
        let postData = { 
    		cId: city,
            gId: gHouse,
            bookingId: bookingId,
            fromDate: fromDate,
            billDate:billDate,
            category:category,
            purpose:purpose,
            mobNo:mobNo,
            name:name
        };
        $.ajax({
            url: './admin/getShowBillList.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                $('#loading').hide();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $("#loading").hide();
                console.log(result);
                showBillTable.clear();
                showBillTable.rows.add(result.data).draw();
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
    };

    $("#cancelChckBtn").on("click", function () {
        data = [];
        $('#cancelCheckOutDetails tr').each(function () {
            let trId = $(this).find('td:eq(0)').text();
            data.push(trId);
        });

        $.ajax({
            url: './admin/cancelCheckoutDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                $('#loading').hide();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
                $("#loading").hide();
                $('#cancelcheckin').modal('hide');
                if(result.status){
                swal("checkout cancel successfull");
                }else{
                 swal("Some thing went wrong");
                }
                
                getAllBillingList();
            },
            error: function (jqXHR, status, error) {
                $("#loading").hide();
                swal(error);
            }
        });

    });
    $("#createBill").on("click", function () {
        console.log(guestDetails);
        let room = parseFloat($('#roomTotal').text());
        let food = parseFloat($('#foodTotal').text());
        let news = parseFloat($('#newsTotal').text());
        let travel = parseFloat($('#travelTotal').text());
        let telephone = parseFloat($('#telephoneTotal').text());
        let other = parseFloat($('#otherTotal').text());
        let total = room + food + news + travel + telephone + other + noc;
        $('#billNo').text('');
        $('#date').text(moment().format('DD-MM-YYYY HH:mm'));
        $('#submitBill').show();
        $('#printBtn').hide();
        $('#createBillModal').modal('show');
        $('#createBillModalTbody').empty();
        $('#particulars').empty();
        let str = '';
        let row = guestDetails[0];
        str += '<tr>' +
            '<td>' + row.rowNo + '</td>' +
            '<td>' + row.name + '</td>' +
            '<td>' + row.noRooms + '</td>' +
            '<td>' + row.category + '</td>' +
            '<td>' + row.purpose + '</td>' +
            '<td>' + (guestDetails.length + row.count) + '</td>' +
            '<td>' + row.arrivalDate + '</td>' +
            '<td>' + row.departureDate + '</td>' +
            '</tr>';
        $('#createBillModalTbody').append(str);

        let s = '';
        charges.forEach(function (item, i) {
            s += '<tr>'
            if (item.name === 'Room Rent') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + room + '</td>';
            } else if (item.name === 'Food') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + food + '</td>';
            } else if (item.name === 'News paper') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + news + '</td>';
            } else if (item.name === 'Vehicle') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + travel + '</td>';
            } else if (item.name === 'Telephone') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + telephone + '</td>';
            }else if (item.name === 'Additional') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + other + '</td>';
            } else if (item.name === 'NOC') {
                s += '<td>' + item.name + '</td>' +
                    '<td><input type="hidden" name="charge" value="' + item.id + '">' + noc + '</td>';
            }
            s += '</tr>';
        });
        s += '<tr>' +
            '<td style="font-weight: bold">Total</td>' +
            '<td style="font-weight: bold">' + Math.round(total) + '</td>' +
            '</tr>';
        $('#particulars').append(s);

        // adv = parseFloat($('#totalAdvance').text());
        $('#nocDate').html('NOC Date : ' + nocDate);
        $('#deduct').html('Deduct Advance Collected : ' + adv)
        $('#release').html('Balance to be paid : ' + Math.round(total - adv));
        $('#rupees').html(inWords(Math.round(total - adv)));
    });

    $('#submitBill').on('click', function () {
        let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId;
        let guestId = [];
        guestDetails.forEach(function (row, i) {
            bookingId = row.bookingId;
            guestId.push(row.guestId);
        });
        let obj = $('input[name="charge"]');
        let charges = {};
        obj.each(function (i, item) {
            charges[parseInt(item.value)] = parseFloat(item.parentElement.innerText);
        });

        console.log(charges);
        let postData = {
            bookingId: bookingId,
            cityId: city,
            ghId: gHouse,
            guestIds: guestId,
            charges: charges,
            advance: adv,
            nocDate: nocDate
        };
        $("#loading").show();
        $.ajax({
            url: './admin/saveBillDetails.do',
            type: 'POST',
            data: JSON.stringify(postData),
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                $("#loading").hide();
                console.log(data);
                if (data.success) {
                    swal(data.msg);
                } else {
                    swal(data.msg);
                }
                $('#createBillModal').modal('hide');
                $('#billModal').modal('hide');
                getAllBillingList();
            },
            error: function (err) {
                $("#loading").hide();
                console.log(err);

            }
        });
    });

    let isCityAndGHSelected = () => {
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

    //bill pdf generation
    $('#printBtn').click(function(){     
		window.open('./guest/downloadBillInPDF/' + bookingIdForBill + '/' + billIdForBill + '.html','_blank');
	})
});

function loadCancelGuestDetails(bookingId) {
    let city = $('#city').val();
    let gHouse = $('#gHouse').val();
    let postData = {
        cId: city,
        gId: gHouse,
        bookingId: bookingId
    }
    console.log(postData);
    $.ajax({
        url: "./admin/getBillDetailsByBookingId/" + bookingId + ".do",
        type: "GET",
        contentType: "application/json",
        beforeSend: function (xhr) {
            $('#loading').show();
            xhr.setRequestHeader(header, token);
        },
        success: function (result, textStatus, jqXHR) {
            $('#loading').hide();
            $('#cancelcheckin').modal('show');
            $("#cancelCheckOutDetails").html("");
            console.log(result);
            for (var i = 0; i < result.data.guestDetails.length; i++) {
                $("#cancelCheckOutDetails").append("<tr>" +
                    "<td style='display:none;'>" + result.data.guestDetails[i].trId + "</td>" +
                    "<td>" + result.data.guestDetails[i].bookingId + "</td>" +
                    "<td>" + result.data.guestDetails[i].rowNo + "</td>" +
                    "<td>" + result.data.guestDetails[i].name + "</td>" +
                    "<td>" + result.data.guestDetails[i].arrivalDate + "</td>" +
                    "<td>" + result.data.guestDetails[i].departureDate + "</td>" +
                    "<td>" + result.data.guestDetails[i].advanceAmount + "</td>" +
                    "<td>" + result.data.guestDetails[i].noOfAdults + "</td>" +
                    "<td>" + result.data.guestDetails[i].noOfChildren + "</td>" +
                    "<td>" + result.data.guestDetails[i].noRooms + "</td>"
                );
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