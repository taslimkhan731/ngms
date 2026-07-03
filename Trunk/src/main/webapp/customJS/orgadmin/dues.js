$('document').ready(function() {
	 $('#filter-popover').on('click', function () {
	     event.stopPropagation();
	 });
	$("#duesSection").hide();
	$('#duesListSection').hide();
	var cityData = $('#cityUserlocssd').val().trim();
    var cityDetails = JSON.parse(cityData);
    var dueAmount;
    var billId;
    var mobileNo;
    var bookingId;
    var fromDate;
	let blockId;

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

    $('#city').on('change', function () {
        var id = $('#city').val();
        getGuestHouseListDtls(id);
    });


	$("#dueList").show();

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
	$("#gHouse").on("change",function(){
		getPartialDuesReport();
		getDuesListReport();
		getDuesReport();
	});
	var table = null;
	table = $("#partialDueDataTable").DataTable({
			"paging" : true,
			"searching" : true,
			"scrollX": true,
			"lengthMenu" : [ [ 10, 20, 30, -1 ],
				[ 10, 20, 30, "All" ] ],
				"processing" : false,
				"ordering" : true,
				"info" : false,
				"bFilter" : false,
				"fixedHeader" : {
					header : true,
					footer : true
				},
				"scrollCollapse" : true,
				"columnDefs" : [
					{
						"targets" : 0,
						"data" : null,
						render : function(data,type, row, meta) {
							return meta.row + meta.settings._iDisplayStart + 1;
						}
					},
					{
						"targets" : 1,
						"data" : "bookingId"
					},
					{
						"targets" : 2,
						"data" : "billNo"
					},
					{
						"targets" : 3,
						"data" : "name"
					},
					{
						"targets" : 4,
						"data" : "fromDate"
					},
					{
						"targets" : 5,
						"data" : "toDate"
					},
					{
						"targets" : 6,
						"data" : "total"
					},
					{
						"targets" : 7,
						"data" : "dueAmount"
					},
					{
						"targets" : 8,
						"data" : null,
						render : function(data,type, row, meta) {
							return  '<button type="button" id="payment" title="Payment" action="Edit" class="btn btn-sm payment"><i class="fa fa-credit-card" aria-hidden="true"></i></button> '
							}
						}
					],
			});

	var duesTable = null;
	duesTable = $("#duesDataTable").DataTable({
			"paging" : true,
			"searching" : true,
			"scrollX": true,
			"lengthMenu" : [ [ 10, 20, 30, -1 ],
				[ 10, 20, 30, "All" ] ],
				"processing" : false,
				"ordering" : true,
				"info" : false,
				"bFilter" : false,
				"fixedHeader" : {
					header : true,
					footer : true
				},
				"scrollCollapse" : true,
				"columnDefs" : [
					{
						"targets" : 0,
						"data" : null,
						render : function(data,type, row, meta) {
							return meta.row + meta.settings._iDisplayStart + 1;
						}
					},
					{
						"targets" : 1,
						"data" : "bookingId"
					},
					{
						"targets" : 2,
						"data" : "billNo"
					},
					{
						"targets" : 3,
						"data" : "name"
					},
					{
						"targets" : 4,
						"data" : "fromDate"
					},
					{
						"targets" : 5,
						"data" : "toDate"
					},
					{
						"targets" : 6,
						"data" : "total"
					},
					{
						"targets" : 7,
						"data" : "paidAmount"
					}
					],
			});

	var duesListTable = null;
	duesListTable = $("#duesListDataTable").DataTable({
			"paging" : true,
			"searching" : true,
			"scrollX": true,
			"lengthMenu" : [ [ 10, 20, 30, -1 ],
				[ 10, 20, 30, "All" ] ],
				"processing" : false,
				"ordering" : true,
				"info" : false,
				"bFilter" : false,
				"fixedHeader" : {
					header : true,
					footer : true
				},
				"scrollCollapse" : true,
				"columnDefs" : [
					{
						"targets" : 0,
						"data" : null,
						render : function(data,type, row, meta) {
							return meta.row + meta.settings._iDisplayStart + 1;
						}
					},
					{
						"targets" : 1,
						"data" : "bookingId"
					},
					{
						"targets" : 2,
						"data" : "name"
					},
					{
						"targets" : 3,
						"data" : "fromDate"
					},
					{
						"targets" : 4,
						"data" : "toDate"
					},
					{
						"targets" : 5,
						"data" : "category"
					},
					{
						"targets" : 6,
						"data" : "billId"
					},
					{
						"targets" : 7,
						"data" : "recId"
					},
					{
						"targets" : 8,
						"data" : "rectDate"
					},
					{
						"targets" : 9,
						"data" : "paidAmount"
					},
					{
						"targets" : 10,
						"data" : null,
						render : function(data,type, row, meta) {
							return  '<button type="button" id="select" title="view" action="view" class="btn btn-sm payment"><i class="fa fa-credit-card" aria-hidden="true"></i></button> '
							}
						}
					],
			});

	$('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });

	$("#filterFromDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });

	$('#filterSearch').click(function () {
		getPartialDuesReport();
		getDuesReport();
		getDuesListReport();
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
        getPartialDuesReport();
        getDuesReport();
        getDuesListReport();
    });

	$('#partialDues').on('click', function () {
        if(!isCityAndGHSelected())
            return false;
        $('#partialDues').addClass("custom-btn-active");
        $('#dues').removeClass("custom-btn-active");
        $('#duesList').removeClass("custom-btn-active");
        document.getElementById("headerName").innerHTML = "Partial Dues";
        $('#partialDueSection').show();
        $('#duesSection').hide();
        $('#duesListSection').hide();
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
        	getPartialDuesReport();
        }
    });

	 var getPartialDuesReport = function () {
	    	var city = $('#city').val();
	        var gHouse = $('#gHouse').val();
	        let bookingId = $('#filterBookingId').val();
	        let fromDate = $('#filterFromDate').val();
	        let postData = {
	        	gId:gHouse,
	        	cId:city,
	        	bookingId: bookingId,
	        	fromDate: fromDate
	        };

	        $.ajax({
	            url: './admin/getPartialDuesReportDetails.do',
	            type: "POST",
	            contentType: "application/json",
	            data: JSON.stringify(postData),
	            beforeSend: function (xhr) {
	                xhr.setRequestHeader(header, token);
	            },
	            success: function (result) {
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
	getPartialDuesReport();

	$('#partialDueDataTable tbody').on('click', '#payment', function () {
		if(!isCityAndGHSelected())
	            return false;
	    var data = table.row( $(this).parents('tr') ).data();
		console.log(data);
	    $('#makeReceiptModal').modal('show');
	    dueAmount = data.dueAmount;
	    billId = data.billId;
	    mobileNo = data.mobileNo;
	    bookingId = data.bookingId;
		blockId = data.ghId;
	    $('#guestNames').text(data.onlyName);
        $('#amountAndWords').text(data.dueAmount);
        $('#date').text(moment().format('DD-MM-YYYY HH:mm'));
        $('#billNo').text(data.billNo);
        $('#billDate').text(data.date);
        $('#currentDate').text(moment().format('DD-MM-YYYY HH:mm'));
        $('#billAmount').text('Rs. ' + data.dueAmount);
	});


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
        let total = dueAmount;
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
        let total = parseFloat(dueAmount);
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
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
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
            posAmount = parseFloat($('#posAmount').val())
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
        /*let total = parseInt(cashAmount + posAmount + onlineAmount);
        let dues = parseInt(dueAmount);*/

        if(cashAmount + posAmount + onlineAmount != dueAmount) {
        	swal('Total amount must be equal to Due Amount ');
        	return false;
        }
        let data = {
            billId: billId,
            total: dueAmount,
            mobileNo: mobileNo,
            cityId: cityId,
            ghId: blockId,
            bookingId: bookingId,
            mode: mode,
            transactionNo: transactionNo
        };
        $.ajax({
            url: './admin/savePartialDueReceipt.do',
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
                    getPartialDuesReport();
                } else {
                    swal(result.msg);
                }
            },
           /* error: function (e) {
                $("#loading").hide();
                swal('Something went wrong');
            }*/
        });

    });

    $('#dues').on('click', function () {
        if(!isCityAndGHSelected())
            return false;
        $('#partialDues').removeClass("custom-btn-active");
        $('#dues').addClass("custom-btn-active");
        $('#duesList').removeClass("custom-btn-active");
        document.getElementById("headerName").innerHTML = "Dues";
        $('#duesSection').show();
        $('#partialDueSection').hide();
        $('#duesListSection').hide();
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
        	getDuesReport();
        }
    });

    var getDuesReport = function () {
    	var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let fromDate = $('#filterFromDate').val();
        let postData = {
        	gId:gHouse,
        	cId:city,
        	bookingId: bookingId,
        	fromDate: fromDate
        };

        $.ajax({
            url: './admin/getDuesReportDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
                duesTable.clear();
                duesTable.rows.add(result.data).draw();
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

    getDuesReport();

    $('#duesList').on('click', function () {
        if(!isCityAndGHSelected())
            return false;
        $('#partialDues').removeClass("custom-btn-active");
        $('#dues').removeClass("custom-btn-active");
        $('#duesList').addClass("custom-btn-active");
        document.getElementById("headerName").innerHTML = "Dues List";
        $('#duesSection').hide();
        $('#partialDueSection').hide();
        $('#duesListSection').show();
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
        	getDuesListReport();
        }
    });

    var getDuesListReport = function () {
    	var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let fromDate = $('#filterFromDate').val();
        let postData = {
        	gId:gHouse,
        	cId:city,
        	bookingId: bookingId,
        	fromDate: fromDate
        };

        $.ajax({
            url: './admin/getDuesListReportDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
                duesListTable.clear();
                duesListTable.rows.add(result.data).draw();
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

    getDuesListReport();

    $('#duesListDataTable tbody').on('click', '#select', function () {
        var data = duesListTable.row($(this).parents('tr')).data();
        console.log("data value   "+data);
        bookingId = data.bookingId;
        $('.hideAmount').hide();
        $('#printReceiptModal').modal('show');
        $('#pGuestNames').text(data.onlyName);
        $('#pRectId').text(data.recId);
        $('#pAmountAndWords').text(data.paidAmount);
        $('#receiptDate').text(data.rectDate);
        $('#pBillNo').text(data.billNo);
        $('#pBillDate').text(data.date);
        $('#pBillAmount').text('Rs. ' + data.paidAmount);
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

	$('#printReceipt').click(function(){
		console.log(bookingId+"");
			window.open('./admin/downloadDuesListInPDF/'+bookingId+ '.html','_blank');
	});

    $('#payOnline').on('click', function () {
        let amount = $('#onlineAmount').val();
        if (amount == "" || amount == 0) {
            swal("Amount must be greater than 0");
            return false;
        }
        $('#amount').val(amount);
        $('#blockId').val(blockId);
        $('#billId').val(billId);
        $('#loading').show();
        $('#payOnlineForm').submit();
        $('#showStatus').html('');
        connect();
    });
});