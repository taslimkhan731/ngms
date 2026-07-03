$('document').ready(function () {
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });

    let block = "";

    let cityData = $('#cityUserlocssd').val().trim();
    let cityDetails = JSON.parse(cityData);

    let receipt = {};
	var receiptIdForRect=null;
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

    $('#showReceiptBody').hide();

    $('#addReceipt').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        $('#addReceipt').addClass('custom-btn-active');
        $('#showReceipt').removeClass('custom-btn-active');
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                $('#addReceiptBody').show();
                $('#showReceiptBody').hide();
                $('#headerName').html('Select Bill to Generate Receipt')
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    getAllReceiptList();
                    block = blockId;
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

    $('#showReceipt').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        $('#addReceipt').removeClass('custom-btn-active');
        $('#showReceipt').addClass('custom-btn-active');
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                $('#addReceiptBody').hide();
                $('#showReceiptBody').show();
                $('#headerName').html('Receipt List')
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    showReceiptList();
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

    let getGuestHouseListDtls = function (id) {
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

    $("#selectReceipt").on("click", function () {
        $('#receiptModal').modal('show');
    });

    var table = $("#receiptDataTable").DataTable({
        "paging": true,
        "searching": true,
        "scrollX": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [

        	{
                "className": "text-center text-nowrap",
                "targets": [0,1,2,3,4,5,7,8,9,11]
            },
            {
                "className": "text-left text-nowrap",
                "targets": [6]
            },
            {
                "className": "text-right text-nowrap",
                "targets": [10]
            },

            {"targets": 0, "data": "billId"},
            {"targets": 1, "data": "billNo"},
            {"targets": 2, "data": "createdBy"},
            {"targets": 3, "data": "date"},
            {"targets": 4, "data": "bookingId"},
            {"targets": 5, "data": "category"},
            {"targets": 6, "data": "name"},
            {"targets": 7, "data": "fromDate"},
            {"targets": 8, "data": "toDate"},
            {"targets": 9, "data": "roomNo"},
            {"targets": 10, "data": "total"}, {
                "targets": 11, "data": null, render: function (data, type, row, meta) {
                    return '<button type="button" id="select" title="Select" action="Select" class="btn btn-sm payment"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ' +
                        '<button type="button" id="cancel" title="Cancel" action="Cancel" class="btn btn-sm btn-dlt"><i class="fa fa-close" aria-hidden="true"></i></button>'
                }
            },],
    });

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
    	getAllReceiptList();
        showReceiptList();
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
        $('#filterBookingDate').val('');
        $('#filterCategory').val('');
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        getAllReceiptList();
    });

    var getAllReceiptList = function () {
    	let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let category = $('#filterCategory').val();
        let fromDate = $('#filterFromDate').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();

        let postData = {
    		cId: city,
            gId: gHouse,
            bookingId: bookingId,
            fromDate: fromDate,
            category:category,
            mobNo:mobNo,
            name:name,
            type: 'BILL'
        };
        $.ajax({
            url: './admin/getAllReceiptList.do',
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

    $('#receiptDataTable tbody').on('click', '.payment', function () {
        let data = table.row($(this).parents('tr')).data();
        console.log(data);
        $('#receiptModal').modal('show');
        $.ajax({
            url: './admin/getReceiptDetails/' + data.bookingId + '/' + data.billId + '.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                $('#loading').hide();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $("#loading").hide();
                console.log(result);
                receipt = result.data;
                $('#innerReceiptBody').empty();
                let col = result.data.guest.length;
                let str = '';
                result.data.guest.forEach(function (guest, i) {
                    str += '<tr>';
                    if (i === 0) {
                            str += '<td rowspan="' + col + '">' + result.data.billId + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.billNo + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.createdBy + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.date + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.bookingId + '</td>';
                    }
                    str += '<td>' + guest + '</td>';
                    if (i === 0) {
                        str += '<td rowspan="' + col + '">' + result.data.fromDate + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.toDate + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.roomNo + '</td>' +
                            '<td rowspan="' + col + '">' + result.data.total + '</td>';
                    }
                    str += '</tr>';
                });
                str += '<tr>' +
                    '<td colspan="9" style="text-align: right">Total</td>' +
                    '<td>' + result.data.total + '</td>' +
                    '</tr>'
                $('#innerReceiptBody').append(str);
            }
        });
    });

    $('#receiptDataTable tbody').on('click', '.btn-dlt', function () {
        let data = table.row($(this).parents('tr')).data();
        console.log(data);
        swal({
                title: "Are you sure? ",
                text: "Are you sure to cancel the bill!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, cancel it!",
                closeOnConfirm: false
            },
            function () {
                $('#loading').show();
                $.ajax({
                    url: './admin/cancelBill/' + data.bookingId + '/' + data.billId + '.do',
                    type: "GET",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (result) {
                        $('#loading').hide();
                        if (result.success) {
                            swal(result.msg);
                            getAllReceiptList()
                        } else {
                            swal(result.msg);
                        }
                    }
                });
            });
    });

    $("#makeReceipt").on("click", function () {
        $('#receiptModal').modal('hide');
        $('#makeReceiptModal').modal('show');
        receipt.guest = receipt.guest.map(function (guest) {
            if (guest.indexOf(',') !== -1) {
                return guest.slice(0, guest.indexOf(','));
            } else {
                return guest;
            }
        });
        $('#guestNames').text(receipt.guest);
        $('#amountAndWords').text(receipt.total);
        $('#date').text(moment().format('DD-MM-YYYY HH:mm'));
        $('#billNo').text(receipt.billNo);
        $('#billDate').text(receipt.date);
        $('#billAmount').text('Rs. ' + receipt.total);
    });

    /*
        $('#cash').on('change', function () {
            if($(this).is(':checked')) {
                $('#cashAmount').prop('disabled', false);
                $('#cashAmount').val(receipt.total);
            }
        });
    */

    $('#makeReceiptModal').on('hidden.bs.modal', function () {
        $('#cash').prop('checked', false);
        $('#pos').prop('checked', false);
        $('#online').prop('checked', false);
        $('#cashAmount').prop('disabled', true);
        $('#posAmount').prop('disabled', true);
        $('#onlineAmount').prop('disabled', true);
        $('#cashAmount').val('');
        $('#posAmount').val('');
        $('#onlineAmount').val('');
        $('#postx').prop('hidden', true);
        $('#posId').val('');
        $('#payOnline').prop('hidden', true);
        $('#checkStatus').prop('hidden', true);
        $('#submitReceipt').prop('disabled', false);
        $('#showStatus').html('');
    });

    $('input[name="pay"]').on('change', function () {
        let total = receipt.total;
        let id = $(this).attr('id');
        if (id === 'cash') {
            if ($(this).is(':checked')) {
                $('#cashAmount').prop('disabled', false);
                let posAmount = $('#posAmount').val();
                let onlineAmount = $('#onlineAmount').val();
                let cashAmount = total - posAmount - onlineAmount;
                $('#cashAmount').val(cashAmount);
            } else {
                $('#cashAmount').prop('disabled', true);
                $('#cashAmount').val('');
            }
        } else if (id === 'pos') {
            if ($(this).is(':checked')) {
                $('#posAmount').prop('disabled', false);
                let cashAmount = $('#cashAmount').val();
                let onlineAmount = $('#onlineAmount').val();
                let posAmount = total - cashAmount - onlineAmount;
                $('#posAmount').val(posAmount);
                $('#postx').prop('hidden', false);
            } else {
                $('#posAmount').prop('disabled', true);
                $('#posAmount').val('');
                $('#postx').prop('hidden', true);
            }
        } else if (id === 'online') {
            if ($(this).is(':checked')) {
                $('#onlineAmount').prop('disabled', false);
                let cashAmount = $('#cashAmount').val();
                let posAmount = $('#posAmount').val();
                let onlineAmount = total - cashAmount - posAmount;
                $('#onlineAmount').val(onlineAmount);
                $('#payOnline').prop('hidden', false);
                $('#submitReceipt').prop('disabled', true);
            } else {
                $('#onlineAmount').prop('disabled', true);
                $('#onlineAmount').val('');
                $('#payOnline').prop('hidden', true);
                $('#submitReceipt').prop('disabled', false);
            }
        }
    });

    $('input[name="amount"]').on('keyup', function () {
        let total = parseFloat(receipt.total);
        let cashAmount = parseFloat($('#cashAmount').val() || 0);
        let posAmount = parseFloat($('#posAmount').val() || 0);
        let onlineAmount = parseFloat($('#onlineAmount').val() || 0);
        let totalAmount = cashAmount + posAmount + onlineAmount;
        if (totalAmount > total) {
            swal('Amount should not be greater than total');
            $(this).val('');
        }
    });

    $('#submitReceipt').on('click', function () {
        let count = 0;
        let mode = {};
        let transactionNo = $('#posId').val();
        if ($('#cash').is(':checked')) {
            if ($('#cashAmount').val() === '' || $('#cashAmount').val() === '0') {
                swal('Cash amount should not be empty or zero if cash is selected');
                return false;
            }
            count++;
            mode['cash'] = parseFloat($('#cashAmount').val());
        }
        if ($('#pos').is(':checked')) {
            if ($('#posAmount').val() === '' || $('#posAmount').val() === '0') {
                swal('POS amount should not be empty or zero if POS is selected');
                return false;
            }
            if (transactionNo === '') {
                swal('Please provide transaction number');
                return false;
            }
            count++;
            mode['pos'] = parseFloat($('#posAmount').val());
        }
        if ($('#online').is(':checked')) {
            if ($('#onlineAmount').val() === '' || $('#onlineAmount').val() === '0') {
                swal('Online amount should not be empty or zero if online is selected');
                return false;
            }
            count++;
            mode['online'] = parseFloat($('#onlineAmount').val());
        }
        if (count === 0) {
            swal('Please select payment mode');
            return false;
        }
        let data = {
            billId: receipt.billId,
            total: receipt.total,
            mobileNo: receipt.mobileNo,
            cityId: receipt.cityId,
            ghId: receipt.ghId,
            bookingId: receipt.bookingId,
            mode: mode,
            transactionNo: transactionNo
        };
        $.ajax({
            url: './admin/saveReceipt.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                $('#loading').hide();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                $("#loading").hide();
                if (result.success) {
                    swal('Receipt generated successfully');
                    $('#makeReceiptModal').modal('hide');
                    $('#receiptModal').modal('hide');
                    getAllReceiptList();
                } else {
                    swal(result.msg);
                }
            },
            error: function (e) {
                $("#loading").hide();
                swal('Something went wrong');
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

    let showReceiptList = () => {
        let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let category = $('#filterCategory').val();
        let fromDate = $('#filterFromDate').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();

        let postData = {
            cId: city,
            gId: gHouse,
            bookingId: bookingId,
            fromDate: fromDate,
            category:category,
            mobNo:mobNo,
            name:name,
            type: 'RECT'
        };
        $.ajax({
            url: './admin/getAllReceiptList.do',
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
                table2.clear();
                table2.rows.add(result.data).draw();
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

    var table2 = $("#showReceiptTable").DataTable({
        "paging": true,
        "searching": true,
        "scrollX": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true, footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [

            {
                "className": "text-center text-nowrap",
                "targets": [0,1,2,3,4,7,8,11]
            },
            {
                "className": "text-left text-nowrap",
                "targets": [5]
            },
            {
                "className": "text-right text-nowrap",
                "targets": [9, 10]
            },

            {"targets": 0, "data": "recId"},
            {"targets": 1, "data": "rectDate"},
            {"targets": 2, "data": "billId"},
            {"targets": 3, "data": "bookingId"},
            {"targets": 4, "data": "category"},
            {"targets": 5, "data": "name"},
            {"targets": 6, "data": "fromDate"},
            {"targets": 7, "data": "toDate"},
            {"targets": 8, "data": "roomNo"},
            {"targets": 9, "data": "total"},
            {"targets": 10, "data": "paidAmount"}, {
                "targets": 11, "data": null, render: function (data, type, row, meta) {
                    return '<button type="button" id="select" title="Show" action="Select" class="btn btn-sm print"><i class="fa fa-print" aria-hidden="true"></i></button> ';
                }
            },],
    });

    $('#showReceiptTable tbody').on('click', '#select', function () {
        var data = table2.row($(this).parents('tr')).data();
        console.log(data);
        $('.hideAmount').hide();
        $('#printReceiptModal').modal('show');
        receiptIdForRect = data.recId;
        data.guest = data.guest.map(function (guest) {
            if (guest.indexOf(',') !== -1) {
                return guest.slice(0, guest.indexOf(','));
            } else {
                return guest;
            }
        });
        $('#cityAndGuestHouse').text(data.guestHouseName+", "+data.cityName);
        $('#pRectId').text(data.recId);
        $('#pGuestNames').text(data.guest);
        $('#pAmountAndWords').text(data.paidAmount);
        $('#receiptDate').text(data.rectDate);
        $('#pBillNo').text(data.billNo);
        $('#pBillDate').text(data.date);
        $('#pBillAmount').text('Rs. ' + data.total);
        $('#printDate').text('Date: ' + moment().format('DD-MM-YYYY'));
        data.paymentModes.forEach(function (mode) {
            console.log(mode);
            if (mode.mode_of_payment === 'cash') {
                $('#cashShow').show();
                $('#pCashAmount').val(mode.amount);
            }
            if (mode.mode_of_payment === 'pos') {
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
    });

    $('#printReceipt').click(function(){
        console.log("ReceiptId"+receiptIdForRect);
		window.open('./admin/getReceiptPdf/' + receiptIdForRect + '.do','_blank');
	});

    $('#payOnline').on('click', function () {
        let amount = $('#onlineAmount').val();
        if (amount == "" || amount == 0) {
            swal("Amount must be greater than 0");
            return false;
        }
        $('#amount').val(amount);
        $('#blockId').val(block);
        $('#onlineBillId').val(receipt.billId);
        $('#loading').show();
        $('#payOnlineForm').submit();
        $('#showStatus').html('');
        connect();
    });

});