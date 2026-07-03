$(document).ready(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var blockId = 0;
    var bookingIdNoc=null;
    $('#filter-popover').on('click', function() {
        event.stopPropagation();
    });

    var cityData = $('#cityUserlocssd').val().trim();
    var cityDetails = JSON.parse(cityData);
    $("#city").html("");
    $("#city").append('<option value="">--Select City--</option>');
    cityDetails.forEach(option => {
        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
    });

    let size = Object.keys(cityDetails).length;
    if (size == 1) {
        $('#city').val(cityDetails[0].gh_id);
        	setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
    }
    $('#city').on('change', function() {
        var id = $('#city').val();
        getGuestHouseListDtls(id);
        /*getNocList("");*/
		nocTable.ajax.reload();
    });
    $('#gHouse').on('change', function() {
        var id = $('#city').val();
        /*getNocList("");*/
		nocTable.ajax.reload();
    });
    var getGuestHouseListDtls = function(id) {
        blockId = id;
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

	var nocType = "";

    $('#nocList').on("click", function () {
        $("#nocReport").removeClass("custom-btn-active");
        $("#nocList").addClass("custom-btn-active");
        /*getNocList("");*/
		nocType = "";
    	nocTable.ajax.reload();
    });

    $('#nocReport').on("click", function () {
        $("#nocList").removeClass("custom-btn-active");
        $("#nocReport").addClass("custom-btn-active");
        /*getNocList("PAID");*/
		nocType = "PAID";
    	nocTable.ajax.reload();
    });

    var nocTable = $("#nocDataTable").DataTable({
        "paging": true,
		"searching": false,
		"scrollX": true,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"autoWidth": true,
		"bLengthChange": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"serverSide": true,
		"ajax": {
			"url": "./admin/getNocList.do",
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
       			d.mobNo = $('#filterMobNum').val();
        		d.name = $('#filterGuestName').val();
				d.type = nocType;
			},"error": function (jqXHR, textStatus, errorThrown) {
				            // Hide the loading indicator
				            $('#loading').hide();
				            if (jqXHR.status === 400) {
				                // If status 400 (Bad Request), show custom Swal error
				                swal("Invalid Request");
				            } 
				    }
		},
        "columnDefs": [
            {
                "targets": 0, "data": null, render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                },
            },{
                "targets": 1, "data": "mobileNo"
            }, {
                "targets": 2, "data": "bookingId"
            }, {
                "targets": 3, "data": "name"
            }, {
                "targets": 4, "data": "fromDate"
            }, {
                "targets": 5, "data": "toDate"
            },{
                "targets": 6, "data": "total"
            },{
                "targets": 7, "data": null, render: function (data, type, row, meta) {
                    // return '<button type="button" class="btn btn-sm btn-view viewNoc" data-toggle="modal" data-target="#viewNocModal" data-bookingId="'+row.bookingId+'"><i class="fa fa-eye" aria-hidden="true"></i></button> ' +
                    if(row.status == "PAID") {
                        return '<button type="button" class="btn btn-sm print" data-bookingId="'+row.bookingId+'"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ';
                    } else {
                        return '<button type="button" title="NOC Payment" class="btn btn-sm bill" data-bookingId="'+row.bookingId+'"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ' +
                            '<button type="button" title="Cancel NOC" class="btn btn-sm btn-dlt cancelNoc" data-toggle="modal" data-target="#rejectNocModal" data-bookingId="'+row.bookingId+'"><i class="fa fa-times" aria-hidden="true"></i></button>';
                    }
                }
            }
        ],
    });


    let noc = {};

    $('#nocDataTable').on('click', '.print', function () {
        var data = nocTable.row($(this).parents('tr')).data();
        console.log(data);
        bookingIdNoc = data.bookingId;
        $('.hideAmount').hide();
        $('#printReceiptModal').modal('show');
        $('#pRectId').text(data.recId);
        $('#pGuestNames').text(data.name);
        $('#pAmountAndWords').text(data.paidAmount);
        $('#receiptDate').text(data.rectDate);
        $('#pBillAmount').text('Rs. ' + data.total);
        $('#printDate').text('Date: ' + moment().format('DD-MM-YYYY'));
        $('#cityAndGuestHouse').text(data.guestHouseName+", "+data.cityName);
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

    $("#nocDataTable").on('click', '.bill', function () {
        let data = nocTable.row($(this).parents('tr')).data();
        noc = data;
        console.log(data);
        $("#nocPaymentModal").modal('show');
        $('#guestNames').text(data.name);
        $('#amountAndWords').text(data.total);
        $('#date').text(moment().format('DD-MM-YYYY HH:mm'));
        $('#billAmount').text('Rs. ' + data.total);
    });

    $("#nocDataTable").on('click', '.cancelNoc', function () {
        let data = nocTable.row($(this).parents('tr')).data();
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
                $.ajax({
                    url: "./admin/cancelNoc/" + data.billId + ".do",
                    type: "GET",
                    contentType: "application/json",
                    beforeSend: function (xhr) {
                        $('#loading').show();
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#loading').hide();
                        swal("Cancelled!", "NOC has been cancelled.", "success");
                        /*getNocList("");*/
						nocTable.ajax.reload();
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
        );
    });

    $('#nocPaymentModal').on('hidden.bs.modal', function () {
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
        let total = noc.total;
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
        let total = parseFloat(noc.total);
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
        let total = parseFloat(noc.total);
        let count = 0;
        let mode = {};
        let transactionNo = $('#posId').val();
        let cashAmount = 0;
        let posAmount = 0;
        let onlineAmount = 0;
        if ($('#cash').is(':checked')) {
            if ($('#cashAmount').val() === '' || $('#cashAmount').val() === '0') {
                swal('Cash amount should not be empty or zero if cash is selected');
                return false;
            }
            count++;
            cashAmount = parseFloat($('#cashAmount').val());
            mode['cash'] = cashAmount;
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
            posAmount = parseFloat($('#posAmount').val());
            mode['pos'] = posAmount;
        }
        if ($('#online').is(':checked')) {
            if ($('#onlineAmount').val() === '' || $('#onlineAmount').val() === '0') {
                swal('Online amount should not be empty or zero if online is selected');
                return false;
            }
            count++;
            onlineAmount = parseFloat($('#onlineAmount').val());
            mode['online'] = onlineAmount;
        }
        if (count === 0) {
            swal('Please select payment mode');
            return false;
        }
        console.log(total);
        console.log(cashAmount + posAmount + onlineAmount);
        if (total !== cashAmount + posAmount + onlineAmount) {
            swal('Amount should be equal to NOC amount');
            return false;
        }
        let data = {
            billId: noc.billId,
            total: noc.total,
            mobileNo: noc.mobile,
            cityId: noc.cityId,
            ghId: noc.ghId,
            bookingId: noc.bookingId,
            mode: mode,
            transactionNo: transactionNo
        };
        $.ajax({
            url: './admin/saveNocPayment.do',
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
                    swal('Payment Successful');
                    $('#nocPaymentModal').modal('hide');
                    /*getNocList("");*/
					nocTable.ajax.reload();
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

    $('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });

    $('#filterSearch').click(function () {
        /*getNocList("");*/
		nocTable.ajax.reload();
        $('#filter-popover').unbind();
        setTimeout(function(){
            $('#filter-popover').bind('click',preventClick);
        }, 1000);
    });

    function preventClick() {
        event.stopPropagation();
    }

    $('#resetFilterBtn').click(function () {
        $('#filterBookingId').val('');
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        $('#city').val('');
        $('#gHouse').val('');
        /*getNocList("");*/
		nocTable.ajax.reload();
    });

    /*var getNocList = function(type) {
        let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
        let data = {
            cId: city,
            gId: gHouse,
            bookingId: bookingId,
            mobNo: mobNo,
            name: name,
            type: type
        }
        $.ajax({
            url: "./admin/getNocList.do",
            type: "POST",
            contentType: "application/json",
            dataType: "JSON",
            data: JSON.stringify(data),
            beforeSend: function(xhr) {
                $('#loading').show();
                xhr.setRequestHeader('X-CSRF-TOKEN', token)
            },
            success: function(result) {
                $('#loading').hide();
                console.log(result.data);
                nocTable.clear().draw();
                nocTable.rows.add(result.data).draw();

            }, error: function(jqXHR, status, error) {
                $('#loading').hide();
                swal(error);
            }
        });

    }*/
    /*getNocList("");*/
	nocTable.ajax.reload();
    
    $('#printReceipt').click(function(){    
		console.log("bookingIdNoc "+bookingIdNoc);    
		window.open('./admin/getNocPdf/' + bookingIdNoc + '.do','_blank');
	});

    $('#payOnline').on('click', function () {
        let amount = $('#onlineAmount').val();
        if (amount == "" || amount == 0) {
            swal("Amount must be greater than 0");
            return false;
        }
        $('#amount').val(amount);
        $('#blockId').val(noc.ghId);
        $('#nocId').val(noc.billId);
        $('#loading').show();
        $('#payOnlineForm').submit();
        $('#showStatus').html('');
        connect();
    });
});
