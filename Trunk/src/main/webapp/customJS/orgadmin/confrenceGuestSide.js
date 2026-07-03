/**
 * 
 */
$('document')
		.ready(
				function() {
					   $('#filter-popover').on('click', function () {
					        event.stopPropagation();
					    });
					   var billIdForPdf=null;
					   var bookingIdForPdf = null;
					var table2 = $("#currentBookingTable")
							.DataTable(
									{
										"paging" : true,
										"searching" : false,
										"scrollX" : true,
										"lengthMenu" : [ [ 10, 20, 30, -1 ],
												[ 10, 20, 30, "All" ] ],
										"processing" : false,
										"ordering" : false,
										"info" : false,
										"bFilter" : true,
										"serverSide" : false,
										"autoWidth" : true,
										"bLengthChange" : false,
										"fixedHeader" : {
											header : true,
											footer : true
										},
										"scrollCollapse" : true,
										"columnDefs" : [
												{
													"className" : "text-center text-nowrap",
													"targets" : [ 0, 1, 3, 4,
															5, 6, 7, 8, 9 ]
												},
												{
													"className" : "text-left text-nowrap",
													"targets" : [ 2 ]
												},
												{
													"width" : "2%",
													"targets" : 0,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return meta.row
																+ meta.settings._iDisplayStart
																+ 1;
													}
												},
												{
													"width" : "5%",
													"targets" : 1,
													"data" : "bookingId"
												},
												{
													"width" : "10%",
													"targets" : 2,
													"data" : "name"
												},
												{
													"width" : "8%",
													"targets" : 3,
													"data" : "bookingDt"
												},
												{
													"width" : "9%",
													"targets" : 4,
													"data" : "fromDate"
												},
												{
													"width" : "9%",
													"targets" : 5,
													"data" : "toDate"
												},
												{
													"width" : "5%",
													"targets" : 6,
													"data" : "mobile"
												},
												{
													"width" : "5%",
													"targets" : 7,
													"data" : "cityName"
												},
												{
													"width" : "5%",
													"targets" : 8,
													"data" : "guestHouseName"
												},
												{
													"width" : "10%",
													"targets" : 9,
													"data" : "confrenceDesc"
												},
												{
													"width" : "16%",
													"targets" : 10,
													"data" : "statusDesc",
													"class": "text-center"
												},
												{
													"width" : "15%",
													"targets" : 11,
													"class": "text-center text-nowrap",
													"data" : null,
													render : function(data,
															type, row, meta) {
														let str = '<button type="button" id="view" title="View Allotment Details" class="btn btn-view btn-sm mr-1"><i class="fa fa-eye" aria-hidden="true"></i></button>';
														if (data.billStatus == "P" || data.billStatus == "FB" || data.billStatus == "F") {
															str +='<button type="button" id="billView" title="Bill View" name="billView" action="Select" class="btn btn-sm btn-secondary d-inline bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'
																	+'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
														}
														/*if (data.billStatus == "FB") {
															str +='<button type="button" id="billView" title="Bill View" name="billView" action="Select" class="btn btn-sm btn-secondary d-inline bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'
																	+'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
														}
														if (data.billStatus == "F") {
															str +='<button type="button" id="billView" title="Bill View" name="billView" action="Select" class="btn btn-sm btn-secondary d-inline bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'
																	+'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
														}*/
														return str;

													}
												} ],
									});

					var getDataToGadRequestTable = function() {

						var city = $('#city').val();
						var gHouse = getUrlParameter('id');
						let bookingId = $('#filterBookingId').val();
						let bookingDate = $('#filterBookingDate').val();
						let mobNo = $('#filterMobNum').val();
						let postData = {
							cId : city,
							gId : gHouse,
							bookingId : bookingId,
							bookingDate : bookingDate,
							mobNo : mobNo,
							reqType : 'GH'

						};
						$.ajax({
							url : './guest/getConfrenceRequestDetails.do',
							type : "POST",
							contentType : "application/json",
							data : JSON.stringify(postData),
							beforeSend : function(xhr) {
								xhr.setRequestHeader(header, token);
							},
							success : function(result) {
								console.log(result.data);
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
					getDataToGadRequestTable();
					$('#editDetails').on("hidden.bs.modal",function(){
					$("input").prop("disabled", false);
					$("select").prop("disabled", false);		
					$(".forTwoHrs").hide();		
					});
					var editReqId = '';
					$('#currentBookingTable tbody').on(
							'click',
							'#edit',
							function() {
								$("#addGuestModalTitle").html("EDIT GUEST DETAILS");
								$('#editDetails').modal('show');
								// var data=table.row( $(this).parents('tr')
								// ).data();
								var data = table2.row($(this).closest('tr'))
										.data() != undefined ? table2.row(
										$(this).closest('tr')).data() : table2
										.row($(this)).data();
								console.log(data);
								editReqId = data.bookingId;
								$("#confrenceGuestName").val(data.name);
								$("#mobileNo").val(data.mobile);
								$("#address").val(data.address);							
								$("#capacity").val(data.capacity);
								$("#amount").val(data.charge);
								$("#startTime").val();
								$("#endTime").val();
								$("#hallType").append(
										"<option value=" + data.confrenceId
												+ "  selected>"
												+ data.confrenceDesc
												+ "</option>");
								$("#timePeriod").append(
										"<option value=" + data.confreqnceUseId
												+ " selected>"
												+ data.confreqnceUseDesc
												+ "</option>");
								$("#advncAamount").val(data.advanceAmount);
								$(
										"input[name=mode][value="
												+ data.modeOfPayment + "]")
										.prop('checked', true);
								$("#additionalCharge")
										.val(data.additonalCharge);
								$("#editConfrence").html("Update Details");
								$("#editConfrence").show();
								$("#confrenceGuestName")
										.prop("disabled", false);
								$("input").prop("disabled", true);
								$("select").prop("disabled", true);
								$("#mobileNo").prop("disabled", false);
								$("#address").prop("disabled", false);
								$("#confrenceGuestName")
										.prop("disabled", false);
								// to set fromDate		
								// to set fromDate				
								const formattedDate  =getDate(data.fromDate);
								$("#frmDtCon").val(formattedDate);
								if(data.confreqnceUseId=="4"){
									const time= getTime(data.fromDate);
									$("#startTime").val(time);
									const time1=getTime(data.toDate);
									$("#endTime").val(time1);
									$(".forTwoHrs").show();
								}else{
									$(".forTwoHrs").hide();
								}
								if(data.billStatus=="F"||data.billStatus=="FB"){
									$("#additionalCharge")
										.val(data.additonalCharge);
									$(".baseOnCond").show();
								}else{
									$(".baseOnCond").hide();
								}
							});
					$('#currentBookingTable tbody').on(
							'click',
							'#view',
							function() {
							$("#addGuestModalTitle").html("VIEW GUEST DETAILS");
								$('#editDetails').modal('show');
								// var data=table.row( $(this).parents('tr')
								// ).data();
								var data = table2.row($(this).closest('tr'))
										.data() != undefined ? table2.row(
										$(this).closest('tr')).data() : table2
										.row($(this)).data();
								console.log(data);

								$("#confrenceGuestName").val(data.name);
								$("#mobileNo").val(data.mobile);
								$("#address").val(data.address);
								$("#frmDtCon").val(data.fromDate);
								$("#capacity").val(data.capacity);
								$("#amount").val(data.charge);
								$("#startTime").val();
								$("#endTime").val();
								$("#hallType").append(
										"<option value=" + data.confrenceId
												+ "  selected>"
												+ data.confrenceDesc
												+ "</option>");
								$("#timePeriod").append(
										"<option value=" + data.confreqnceUseId
												+ " selected>"
												+ data.confreqnceUseDesc
												+ "</option>");
								$("#advncAamount").val(data.advanceAmount);
								$(
										"input[name=mode][value="
												+ data.modeOfPayment + "]")
										.prop('checked', true);
								if(data.billStatus=="F"||data.billStatus=="FB"){
									$("#additionalCharge")
										.val(data.additonalCharge);
									$(".baseOnCond").show();
								}else{
									$(".baseOnCond").hide();
								}
								$("#additionalCharge")
										.val(data.additonalCharge);
								$("#editConfrence").hide();
								$(".disable").prop("disabled", true);
								if(data.billStatus=="D"){
									$(".atTimeOfDelete").show();
									$(".reAdvanceAmount").html(data.advanceAmount);
									$(".refundAmount").html(data.advanceAmount);
								}else{
									$(".atTimeOfDelete").hide();
								}
								// to set fromDate				
								const formattedDate  =getDate(data.fromDate);
								$("#frmDtCon").val(formattedDate);
								if(data.confreqnceUseId=="4"){
									const time= getTime(data.fromDate);
									$("#startTime").val(time);
									const time1=getTime(data.toDate);
									$("#endTime").val(time1);
									$(".forTwoHrs").show();
								}else{
									$(".forTwoHrs").hide();
								}
									
								});

					var pendingAmount = '';
					var advanceAmount = '';
					var status = '';

					$("#editConfrence").on("click", function() {
						params = {
							reqId : editReqId,
							mobileNo : $("#mobileNo").val(),
							address : $("#address").val(),
							capacity : $("#capacity").val(),
							name : $("#confrenceGuestName").val()
						};
						$.ajax({
							url : './guest/editBillingDetails.do',
							type : "POST",
							contentType : "application/json",
							data : JSON.stringify(params),
							beforeSend : function(xhr) {
								xhr.setRequestHeader(header, token);
							},
							success : function(result) {
							console.log(result);
							$('#editDetails').modal('hide');
							if(result.status==true){
								swal(result.msg);
							}else{
								swal(result.msg);
							}
							getDataToGadRequestTable();
							},
							error : function(jqXHR, status, error) {
								swal(error);
							}
						});

					});

					var paymentReqId = "";
					var billId = "";
					var globalData = "";
					$('#currentBookingTable tbody').on(
							'click',
							'#payment',
							function() {
								var data = table2.row($(this).closest('tr'))
										.data() != undefined ? table2.row(
										$(this).closest('tr')).data() : table2
										.row($(this)).data();
								viewRecipetDetails(data);
							});
					paymentTimeBillStatus = "";
					
					$('#currentBookingTable tbody').on(
							'click',
							'#payment1',
							function() {
								var data = table2.row($(this).closest('tr'))
										.data() != undefined ? table2.row(
										$(this).closest('tr')).data() : table2
										.row($(this)).data();
								$('input[name="pay"]').prop("disabled", false);
								$('#paymentDetails').modal('show');
								globalData = data;
								console.log(data);
								paymentReqId = data.bookingId;
								billId = data.billId;
								$('#date').text(moment().format('DD-MM-YYYY'));
								$("#billDate").text(
										moment().format('DD-MM-YYYY'));
								$("#guestNames").html(
										data.name + "," + data.address);
								$("#guestHouseDetails").html(
										data.guestHouseName + ","
												+ data.cityName);
								$("#billNo").html(data.billId);
								paymentTimeBillStatus = data.billStatus;
								if (data.billStatus == "B") {
									var words = inWords(data.advanceAmount);
									$("#amountAndWords").html(
											data.advanceAmount + "(" + words
													+ ")");
									$("#amountINR").html(data.advanceAmount);
								}
								if (data.billStatus == "FB") {
									var words = inWords(data.pendingAmount);
									$("#amountAndWords").html(
											data.pendingAmount + "(" + words
													+ ")");
									$("#amountINR").html(data.pendingAmount);
								}

							});

					$('#currentBookingTable tbody')
							.on(
									'click',
									'#delete',
									function() {
										var data = table2.row(
												$(this).closest('tr')).data() != undefined ? table2
												.row($(this).closest('tr'))
												.data()
												: table2.row($(this)).data();
										swal(
												{
													title : "Are you sure? ",
													text : "Are you sure to cancel the Confrence Details!",
													type : "warning",
													showCancelButton : true,
													confirmButtonClass : "btn-danger",
													confirmButtonText : "Yes, cancel it!",
													closeOnConfirm : false
												},
												function() {
													$('#loading').show();
													$
															.ajax({
																url : './guest/cancelConfrenceDetails/'
																		+ data.bookingId
																		+ '.do',
																type : "GET",
																beforeSend : function(
																		xhr) {
																	$(
																			'#loading')
																			.show();
																	xhr
																			.setRequestHeader(
																					header,
																					token);
																},
																success : function(
																		result) {
																	$(
																			'#loading')
																			.hide();
																	console
																			.log(result);
																	swal(result.msg);
																	getDataToGadRequestTable();
																},
																error : function(
																		jqXHR,
																		status,
																		error) {
																	$(
																			'#loading')
																			.hide();
																	$(
																			"#vehicleModal")
																			.modal(
																					"hide");
																	console
																			.log(jqXHR);
																	console
																			.log(status);
																	console
																			.log(error);
																}
															});
												});
									});
					$(".paymentType").on(
							'change',
							function() {
								if ($("input[name='paymentType']:checked")
										.val() == 'POS') {
									$('#statement').prop("disabled", false);
								} else {
									$('#statement').prop("disabled", true);
								}
							});
				$("#searchDt").datetimepicker({
       			 useCurrent: false,
        		format: 'DD-MM-YYYY'
    			});

					var viewRecipetDetails = function(data) {
						$
								.ajax({
									url : './admin/getRecieptDetails/'
											+ data.billId + '.do',
									type : "GET",
									beforeSend : function(xhr) {
										$('#loading').show();
										xhr.setRequestHeader(header, token);
									},
									success : function(result) {

										$('#loading').hide();
										console.log(result);
										billIdForPdf = data.billId;
										$('#printReceiptModal').modal('show');
										$('#printDate').text(
												"Date:"
														+ moment().format(
																'DD-MM-YYYY'));
										$('#receiptDate').text(
												moment().format('DD-MM-YYYY'));
										$("#pBillDate").text(
												moment().format('DD-MM-YYYY'));
										$("#pGuestNames").html(
												data.name + "," + data.address);
										$("#guestHouseDetails").html(
												data.guestHouseName + ","
														+ data.cityName);
										$("#pBillNo").html(data.billId);
										var words = inWords(data.advanceAmount);
										$("#pAmountAndWords").html(
												data.advanceAmount + "("
														+ words + ")");

										var wordsf = inWords(data.pendingAmount);
										$("#fAmountAndWords").html(
												data.pendingAmount + "("
														+ wordsf + ")");

										$("#cod").prop("disabled", false);
										$("#POS").prop("disabled", false);

										for (var i = 0; i < result.data.length; i++) {
											if (result.data[i].recStatus == "A") {
												$(".finalPaymentReceipt")
														.hide();
												$('#receiptDate')
														.text(
																result.data[i].receiptDate);
												$("#pBillDate")
														.text(
																result.data[i].billDate);
												$("#pRectId").text(
														result.data[i].recId);
												if (result.data[i].modeOfpayment === 'cash') {
													$('#cashShow').show();
													$('#pCashAmount')
															.val(
																	result.data[i].amount);
													$('#pCashAmount').prop("disabled",true);
												}else{
													$('#pCashAmount').prop("disabled",true);
												}
												if (result.data[i].modeOfpayment === 'pos') {
													$('#posShow').show();
													$('#pPostx').show();
													$('#pPosAmount')
															.val(
																	result.data[i].amount);
													$('#pPosId')
															.val(
																	result.data[i].pos_tr_num);
													$('#pPosAmount').prop("disabled",true);	
													$('#pPosId').prop("disabled",true);		
												}else{
													$('#pPosAmount').prop("disabled",true);	
													$('#pPosId').prop("disabled",true);	
												}
												if (result.data[i].modeOfpayment === 'online') {
													$('#onlineShow').show();
													$('#pOnlineAmount')
															.val(
																	result.data[i].amount);
																	$('#pOnlineAmount').prop("disabled",true);
												}else{
													$('#pOnlineAmount').prop("disabled",true);
												}
											}

											if (result.data[i].recStatus == "F") {
												$(".finalPaymentReceipt")
														.show();
												$('#freceiptDate')
														.text(
																result.data[i].receiptDate);
												$("#fBillDate")
														.text(
																result.data[i].billDate);
												$("#fRectId").text(
														result.data[i].recId);
												if (result.data[i].modeOfpayment === 'cash') {
													$('#fcashShow').show();
													$('#fCashAmount')
															.val(
																	result.data[i].amount);
													$('#fCashAmount').prop("disabled",true);
												}else{
													$('#fCashAmount').prop("disabled",true);
												}
												if (result.data[i].modeOfpayment === 'pos') {
													$('#fposShow').show();
													$('#fPostx').show();
													$('#fPosAmount')
															.val(
																	result.data[i].amount);
													$('#fPosId')
															.val(
																	result.data[i].pos_tr_num);
													$('#fPosAmount').prop("disabled",true);
													$('#fPosId').prop("disabled",true);
												}else{
													$('#fPosAmount').prop("disabled",true);
													$('#fPosId').prop("disabled",true);
												}
												if (result.data[i].modeOfpayment === 'online') {
													$('#fonlineShow').show();
													$('#fOnlineAmount')
															.val(
																	result.data[i].amount);
													$('#fOnlineAmount').prop("disabled",true);
												}else{
													$('#fOnlineAmount').prop("disabled",true);
												}
											}

										}

									},
									error : function(jqXHR, status, error) {
										$('#loading').hide();
										$("#vehicleModal").modal("hide");
										console.log(jqXHR);
										console.log(status);
										console.log(error);
									}
								});
					}

					$('#currentBookingTable tbody')
							.on(
									'click',
									'#billView',
									function() {
										$('#billingDetailsFinal').modal('show');
										// var data=table.row(
										// $(this).parents('tr') ).data();
										var data = table2.row(
												$(this).closest('tr')).data() != undefined ? table2
												.row($(this).closest('tr'))
												.data()
												: table2.row($(this)).data();
										console.log(data);
										bookingIdForPdf= data.bookingId;
										$("#additionalFi").prop("disabled",
												true);
										$("#billFiName").html(data.name);
										$("#billFiId").html(data.bookingId);
										$("#billFiMobile").html(data.mobile);
										$("#billFiAddress").html(data.address);
										$("#billFiHall").html(
												data.confrenceDesc);
										$("#billFiPeriod").html(
												data.confreqnceUseDesc);
										$("#billFiAmount").html(data.charge);
										$("#billFiAdvnc").html(
												data.advanceAmount);
										$("#additionalFi").html(
												data.additonalCharge);
										$("#billFiMode").html(
												data.modeOfPayment);
										$("#fromDate").html(data.fromDate);
										if (data.billStatus == "P"
												|| data.billStatus == "B") {
											$("#amountRecord").html("Total Amount:");
											$(".showInFinal").hide();
											if (data.billStatus == "B") {
												$("#needTextForView").text(
														"Amount Need To Pay:");
											}
											if (data.billStatus == "P") {
												$("#needTextForView").html(
														"Amount Paid:");
											}
											$(".header-data")
													.html(
															"Bill Details For Advance Payment");

											$("#billFiTotal")
													.html(
															parseInt(data.pendingAmount)
																	+ parseInt(data.advanceAmount));
											$("#needFi").html(
													data.advanceAmount);
										}
										if (data.billStatus == "F"
												|| data.billStatus == "FB") {
											$("#billViewAdditional").show();
											if(data.additonalCharge=="0"){
												$("#ifNullAdd").hide();
											}else{
												$("#ifNullAdd").show();
											}
											$("#amountRecord").html("Total Amount(Amount+Additional Charge):");
											$(".showInFinal").hide();
											$(".header-data")
													.html(
															"Bill Details For Total Payment");
											$("#billFiTotal")
													.html(
															parseInt(data.pendingAmount)
																	+ parseInt(data.advanceAmount));
											if (data.billStatus == "FB") {
												$("#needTextForView").text(
														"Amount Need To Pay:");
												$("#needFi").html(
														data.pendingAmount);
											}
											if (data.billStatus == "F") {
												$(".showInFinal").show();
												$("#totalFi")
														.html(
																parseInt(data.pendingAmount)
																		+ parseInt(data.advanceAmount));
												$("#needTextForView").text(
														"Amount Paid:");
												$("#needFi").html(
														data.pendingAmount);
											}

										}else{
											$("#billViewAdditional").hide();
										}
									});
/*filter work start here	*/	



										
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
	$('#fBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
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
									
 $('#filterSearch').click(function () {
        let city = $('#city').val();
        let gHouse = $('#gHouse').val();
        let bookingId = $('#fBookingId').val();
        let bookingDate = $('#searchDt').val();
        let status = $('#status').val();

        let postData = {
            cId: city,
            gId: gHouse,
            bookingId: bookingId,
            bookingDate: bookingDate,
        };

        $.ajax({
            url: './guest/getConfrenceRequestDetails.do',
            type: "POST",
            data: JSON.stringify(postData),
            contentType: "application/json",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
            	console.log(result);
                table2.clear().draw();
                table2.rows.add(result.data).draw();
                /*contable.clear().draw();
                contable.rows.add(result.data).draw();*/
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

    $('#resetBtn').click(function () {
        $('#city').val('');
        $('#gHouse').val('');
        $('#fBookingId').val('');
        $('#searchDt').val('');
        $('#status').val('');
        getDataToGadRequestTable();
    });
    $('#printReceipt').click(function(){    
    	console.log("billIdForConfrence"+billIdForPdf);    
    	window.open('./admin/getReceiptForConFrencePdf/' + billIdForPdf + '.do','_blank');
    })
    $('#printPdf').click(function(){    
    	console.log("bookingIdForConfrence"+bookingIdForPdf);    
    	window.open('./admin/getBillForConFrencePdf/' + bookingIdForPdf + '.do','_blank');
    })							
var getTime=function(param){
	const [day, month, year, hoursFromDateString, minutesFromDateString] = param.split(/-|:|\s/);
	const formattedDateString = `${year}-${month}-${day}T${hoursFromDateString}:${minutesFromDateString}`;
	const date = new Date(formattedDateString);
	const hours = date.getHours();
	const minutes = date.getMinutes();
	const time = `${hours}:${minutes}`;
	return time;
}
var getDate=function(param){
	const [datePart, timePart] = param.split(" ");
	const [day, month, year] = datePart.split("-");
	const formattedDate = `${day}-${month}-${year}`;
	console.log(formattedDate);
	return formattedDate;
}	

});

function onlyNumberKey(evt) {  
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
}

