/**
 * 
 */
$('document').ready(function () {
	var billIdForPdf=null;
	var bookingIdForBillPdf=null;
	var table2 = $("#currentBookingTable").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": true,
        "serverSide": false,
        "autoWidth": true,
        "bLengthChange": true,
        "fixedHeader": {
            header: true,
            footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [
        	{
                "className": "text-center text-nowrap",
                "targets": [0,1,3,4,5,6,7,8,9]
            },
            {
                "className": "text-left text-nowrap",
                "targets": [2]
            },
            {
                "width": "5%", "targets": 0, "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }	
            },
            {"width": "2%", "targets": 1, "data": "bookingId"},
            {"width": "10%", "targets": 2,"className":"text-center", "data": "name"},
            {"width": "8%", "targets": 3, "data": "bookingDt"},
            {"width": "8%", "targets": 4, "data": "fromDate"},
            {"width": "8%", "targets": 5, "data": "toDate"},
            {"width": "8%", "targets": 6, "data": "mobile"},
            {"width": "8%", "targets": 7, "data": "cityName"},
            {"width": "8%", "targets": 8, "data": "guestHouseName"},
            {"width": "8%", "targets": 9, "data": "confrenceDesc"},
            {"width": "12%", "targets": 10,"className":"text-center", "data": "statusDesc"},
            {
                "width": "11%", "targets":11,"className":"text-center", "data": null,
                render: function (data, type, row, meta) {
                    let str = '<button type="button" id="view" title="View Allotment Details" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button>';					
                    if (data.billStatus == "B"&&data.status!="CAN") {
                        str +=' <button type="button" id="delete" title="Cancel" class="btn btn-dlt btn-sm"><i class="fa fa-close" aria-hidden="true"></i></button>'+
                        '<button type="button" id="billView" title="Bill View" name="billView" action="Select" class="btn btn-sm btn-secondary d-inline payment  ml-1"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'+
                        		'<button type="button" id="payment1" title="payment" action="Select" class="btn btn-sm btn-secondary d-inline bill ico-size ml-1"><i class="fa fa-inr"></i></button> ';                 
                    }
				   if(data.billStatus == "NB"&&data.status!="CAN"){
					str +=' <button type="button" id="edit" title="Edit Allotment Details" class="btn btn-edit btn-sm"><i class="fa fa-edit" aria-hidden="true"></i></button>'	+
                            '<button type="button" id="delete" title="Cancel" class="btn btn-dlt btn-sm  ml-1 mr-1"><i class="fa fa-close" aria-hidden="true"></i></button>'+
							'<button type="button" id="bill"  title="Billing" action="Select" value="something" class="btn btn-sm btn-secondary d-inline bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ';
					}
					if(data.billStatus=="P"&&(data.status=="REQ"||data.status=="MOD")){
						str +=' <button type="button" id="delete" title="Cancel" class="btn btn-dlt btn-sm"><i class="fa fa-close" aria-hidden="true"></i></button>'+
						'<button type="button" id="billView" title="Bill View" name="billView"   action="Select" class="btn btn-sm btn-secondary d-inline payment	 ml-1"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'+
						'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
					}
					if(data.billStatus=="P"&&data.status=="PRO"){
						str +=' <button type="button" id="delete" title="Cancel" class="btn btn-dlt btn-sm mr-1"><i class="fa fa-close" aria-hidden="true"></i></button>'+
						'<button type="button" id="billView" title="Bill View" name="billView"   action="Select" class="btn btn-sm btn-secondary d-inline payment"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'+
						'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> '+
						'<button type="button" id="bill" title="Billing" action="Select"  value="something" class="btn btn-sm btn-secondary d-inline bill"><i class="fa fa-credit-card" aria-hidden="true"></i></button> ';
					}
					if(data.billStatus=="FB"&&data.status!="CAN"){
						str +='<button type="button" id="billView" title="Bill View" name="billView" action="Select" class="btn btn-sm btn-secondary d-inline payment	 ml-1"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'+
						'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
						if(data.additonalCharge!="0"){
							str +='<button type="button" id="payment1" title="payment" action="Select" class="btn btn-sm btn-secondary d-inline bill ico-size"><i class="fa fa-inr"></i></button> ';
						}
					}
					if(data.billStatus=="F"&&data.status!="CAN"){
						str +='<button type="button" id="billView" title="Bill View" name="billView"   action="Select" class="btn btn-sm btn-secondary d-inline payment	 ml-1"><i class="fa fa-credit-card" aria-hidden="true"></i></button>'+
						'<button type="button" id="payment" title="Reciept View" value="paymentView" action="Select" class="btn btn-sm btn-secondary d-inline payment ico-size ml-1"><i class="fa fa-inr"></i></button> ';
					}
                    return str;
                    
                }
            }
        ],
    });


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
			setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}

	$("#city").change(function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
	});

	/* get guest house details through city id  */
	var getGuestHouseListDtls = function(id) {
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
	$('#submitBookingDetailsConfrence').bootstrapValidator({
        btnSubmit: 'button[type="submit"]',
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            }, confCatId: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    },
                   
                }
            },address: {
                validators: {
					notEmpty: {
						message: 'Field can\'t be empty'
					},
					regexp: {
						regexp: /^[\s\S]+ [1-9][0-9]{2}-?[0-9]{3}$/,
						message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
					}
				}
            },mobileNo: {
                validators: {
                   notEmpty: {
                        message: 'field can\'t be empty'
                    }, regexp: {
                        regexp: /^[9 8 7 6]\d{9}$/,
                        message: 'only 10 digits are allowed starting with 9,8,7,6'
                    }
                }
            },
            confUseId: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            },
            amount: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            },
            checkTerm: {
                validators: {
                    notEmpty: {
                        message: 'Please check terms&condition'
                    }
                }
            },
             capacity: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    },
                    greaterThan: {
                        value: 0,
                        message: 'field must be greater than 0'
                    }
                }
            }
          }
		}).on('error.field.bv', function(e, data) {
        if (data.bv.getSubmitButton()) {
           return false;
        }
    }).on('success.form.bv', function (e) {
        return true;
    });
	$("#filterBookingDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
        // format: 'YYYY-MM-DD'
    });

	
	$('#printReceiptModal').on("hidden.bs.modal",function(){
		forReset()
	});
	$("#paymentDetails").on("hidden.bs.modal",function(){
		forReset()
	});

	var	getDataToGadRequestTable=function () {   
        
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let bookingDate = $('#filterBookingDate').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
     
        let postData = { 
    		cId: city,
            gId: gHouse,	
            bookingId: bookingId,
            bookingDate: bookingDate,
            mobNo:mobNo,
            name:name,
            reqType: 'GH'
      
        };
        $.ajax({
            url: './admin/getConfrenceRequestDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
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
$("#gHouse").on('change',function(){
	getDataToGadRequestTable();
});
var editReqId='';
 $('#currentBookingTable tbody').on('click', '#edit', function () {
 		$(".atTimeOfDelete").hide();
        $('#editDetails').modal('show');
        // var data=table.row( $(this).parents('tr') ).data();
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
       	editReqId=data.bookingId;
       	$("#addGuestModalTitle").html("EDIT GUEST DETAILS");
		$("#confrenceGuestName").val(data.name);
		$("#mobileNo").val(data.mobile);
		$("#address").val(data.address);
		$("#frmDtCon").val(data.fromDate);
		$("#capacity").val(data.capacity);	
		$("#amount").val(data.charge);
		$("#startTime").val();
		$("#endTime").val();
		$("#hallType").append("<option value="+data.confrenceId+"  selected>"+data.confrenceDesc+"</option>");
		$("#timePeriod").append("<option value="+data.confreqnceUseId+" selected>"+data.confreqnceUseDesc+"</option>");
		$("#advncAamount").val(data.advanceAmount);
		$("input[name=mode][value=" + data.modeOfPayment + "]").prop('checked', true);
		$("#additionalCharge").val(data.additonalCharge);
		$("#editConfrence").html("Update Details");
		$("#editConfrence").show();
		$("#confrenceGuestName").prop("disabled", false);
		$("input").prop("disabled", true);
		$("select").prop("disabled", true);
		$("#mobileNo").prop("disabled", false);
		$("#address").prop("disabled", false);
		$("#confrenceGuestName").prop("disabled", false);
		
		
		const formattedDate  =getDate(data.fromDate);
		$("#frmDtCon").val(formattedDate);
		if(data.billStatus=="F"||data.billStatus=="FB"){
			$("#additionalCharge").val(data.additonalCharge);
			$("#addCharge").show();
		}else{
			$("#additionalCharge").val(data.additonalCharge);
			$("#addCharge").hide();
		}
		if(data.confreqnceUseId=="4"){
			const time= getTimegetTime(data.fromDate);
			time=$("#startTime").val(time);
			const time1=getTime(data.toDate);
			$("#endTime").val(time1);
			$(".forTwoHrs").show();
		}else{
			$(".forTwoHrs").hide();
		}
    });
    $("#editDetails").on("hidden.bs.modal",function(){
    	$("select").prop("disabled", false);
    });
    var getDate=function(time){
		const [datePart, timePart] = time.split(" ");
		const [day, month, year] = datePart.split("-");
		const formattedDate = `${day}-${month}-${year}`;
		console.log(formattedDate);
    	return formattedDate;
    }
	$('#currentBookingTable tbody').on('click', '#view', function () {
        $('#editDetails').modal('show');
        // var data=table.row( $(this).parents('tr') ).data();
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
        $("#addGuestModalTitle").html("VIEW GUEST DETAILS");
		$("#confrenceGuestName").val(data.name);
		$("#mobileNo").val(data.mobile);
		$("#address").val(data.address);
		$("#frmDtCon").val(data.fromDate);
		$("#capacity").val(data.capacity);	
		$("#amount").val(data.charge);
		$("#startTime").val();
		$("#endTime").val();
		$("#hallType").append("<option value="+data.confrenceId+"  selected>"+data.confrenceDesc+"</option>");
		$("#timePeriod").append("<option value="+data.confreqnceUseId+" selected>"+data.confreqnceUseDesc+"</option>");
		$("#advncAamount").val(data.advanceAmount);
		$("input[name=mode][value=" + data.modeOfPayment + "]").prop('checked', true);
		if(data.billStatus=="F"||data.billStatus=="FB"){
			$("#additionalCharge").val(data.additonalCharge);
			$("#addCharge").show();
		}else{
			$("#additionalCharge").val(data.additonalCharge);
			$("#addCharge").hide();
		}
		$("#additionalCharge").val(data.additonalCharge);
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
    
	var pendingAmount='';
	var advanceAmount='';
	var status='';
	$('#currentBookingTable tbody').on('click', '#bill', function () {
	if($("#cityId").val()=="" || $("#gHouse").val()==""){
			swal("please provide city or guest house");
			return false;
	}
        $('#billingDetails').modal('show');
        // var data=table.row( $(this).parents('tr') ).data();
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
       $("#additional").prop("disabled", false);
		$("#billName").html(data.name);
		$("#billId").html(data.bookingId);
		$("#billMobile").html(data.mobile);
		$("#billAddress").html(data.address);
		$("#billHall").html(data.confrenceDesc);	
		$("#billPeriod").html(data.confreqnceUseDesc);
		$("#bookedDate").html(data.fromDate);
		$("#billAmount").html(data.charge);
		$("#additional").val(data.additonalCharge);
		pendingAmount=data.charge;
		advanceAmount=data.advanceAmount;
		status=data.billStatus;
		if(data.billStatus=="NB"){
		$(".header-data").html("Bill Details For Advance Payment");		
		$("#billMode").html(data.modeOfPayment);
		var pendAmount=parseInt(data.charge)+parseInt(data.additonalCharge);		
		$("#billTotal").html(pendAmount);
		$("#need").html(data.advanceAmount);
		$("#additionalOnBill").hide();
		$("#amountRecord").html("Total Amount:");
		}
		if(data.billStatus=="P"){
		$(".header-data").html("Bill Details For Total Payment");
		$("#billTotal").html(parseInt(data.charge)+parseInt(data.additonalCharge));
		$("#need").html(data.pendingAmount);
		$("#additionalOnBill").show();
		$("#amountRecord").html("Total Amount(Amount+Additional Charge):");
		}
		
		
    });

	$('#additional').on('keyup , keydown', function () {
		var pendAmount=parseInt(pendingAmount)+parseInt($("#additional").val());
		$("#billTotal").html(pendAmount);
		if(status!="NB"){
			$("#need").html(pendAmount-parseInt(advanceAmount));
		}
		
		
	});

	$("#billSave").on("click",function(){
		if($("#cityId").val()=="" || $("#gHouse").val()==""){
			swal("please provide city or guest house");
			return false;
		}
		params={
			reqId:$("#billId").html(),
			additonalCharges:$("#additional").val(),
			status:status
		};
		 $.ajax({
            url: './admin/saveBillingDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(params),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                if(result.status){
                swal(result.msg);
                getDataToGadRequestTable();
                }else{
               	swal(result.msg);
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
	
	$("#editConfrence").on("click",function(){
		params={
			reqId:editReqId,
			mobileNo:$("#mobileNo").val(),
			address:$("#address").val(),
			capacity:$("#capacity").val(),
			name:$("#confrenceGuestName").val()
		};
		if($("#mobileNo").val()=="" || $("#address").val()=="" || $("#confrenceGuestName").val()==""){
			swal("Please provide all data");
			return false;
		}
		 $.ajax({
            url: './admin/editBillingDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(params),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
	       		swal(result.msg);
	        	$('#editDetails').modal('hide');		
				getDataToGadRequestTable();
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
	var paymentReqId="";
	var billId="";
	var globalData="";
	$('#currentBookingTable tbody').on('click', '#payment', function () {
	 	var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
		viewRecipetDetails(data);
	});
	paymentTimeBillStatus="";
	$('#currentBookingTable tbody').on('click', '#payment1', function () {
		if($("#cityId").val()=="" || $("#gHouse").val()==""){
			swal("please provide city or guest house");
			return false;
		}
   		var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
   		$('input[name="pay"]').prop("disabled",false);
   		$('#paymentDetails').modal('show');
        globalData=data;
        paymentReqId=data.bookingId;
        billId=data.billId;
        $('#date').text(moment().format('DD-MM-YYYY'));
       	$("#billDate").text(moment().format('DD-MM-YYYY'));
	 	$("#guestNames").html(data.name+","+data.address);
	 	$("#guestHouseDetails").html(data.guestHouseName+","+data.cityName);
	 	$("#billNo").html(data.billId);
	 	paymentTimeBillStatus=data.billStatus;
        if(data.billStatus=="B"){
        	var words = inWords(data.advanceAmount);
	    	$("#amountAndWords").html(data.advanceAmount+"("+words+")");
	    	$("#amountINR").html(data.advanceAmount);
        }
        if(data.billStatus=="FB"){
        	var words = inWords(data.pendingAmount);
	    	$("#amountAndWords").html(data.pendingAmount+"("+words+")");
	    	$("#amountINR").html(data.pendingAmount);
        }
	  	
    });

	$('#currentBookingTable tbody').on('click', '#delete', function () {
	var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
	swal({
		title: "Are you sure? ",
		text: "Are you sure to cancel the Conference Details!",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "Yes, cancel it!",
		cancelButtonText: "Close",
		closeOnConfirm: false
	},
		function() {
			$('#loading').show();
			$.ajax({
				url: './admin/cancelConfrenceDetails/' + data.bookingId + '.do',
				type: "GET",
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(result) {
					$('#loading').hide();
					swal(result.msg);
					getDataToGadRequestTable();
				},
				error: function(jqXHR, status, error) {
					$('#loading').hide();
					$("#vehicleModal").modal("hide");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
		});
	});
$(".paymentType").on('change',function(){
	if($("input[name='paymentType']:checked").val()=='POS'){
		$('#statement').prop("disabled", false);
	}else{
		$('#statement').prop("disabled", true);
	}
});

$("#savePayment").on('click',function(){
	var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
	console.log(data);
	params={
	reqId:paymentReqId,
	payAmount:$("#amountINR").html()
	}
		$.ajax({
				url: './admin/saveConfrenceDetails.do',
				type: "POST",
      		    contentType: "application/json",
       		    data: JSON.stringify(params),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(result) {
					$('#loading').hide();
					swal(result.msg);
					getDataToGadRequestTable();
				},
				error: function(jqXHR, status, error) {
					$('#loading').hide();
					$("#vehicleModal").modal("hide");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
});

$('input[name="pay"]').on('change', function () {
        let total = $("#amountINR").html();
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
                $('#posId').prop('disabled', false);
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
        let total = parseFloat($("#amountINR").html());
        let cashAmount = parseFloat($('#cashAmount').val() || 0);
        let posAmount = parseFloat($('#posAmount').val() || 0);
        let onlineAmount = parseFloat($('#onlineAmount').val() || 0);
        let totalAmount = cashAmount + posAmount + onlineAmount;
        if (totalAmount > total) {
            swal('Amount should not be greater than total');
            $(this).val('');
        }
    });

$("#submitReceipt").on('click',function(){
	if($("#cityId").val()=="" || $("#gHouse").val()==""){
	swal("please provide city or guest house");
	return false;
	}
	var total="";

	var status1="P"
	if(paymentTimeBillStatus=="FB"){
		status1="F"
	}
	 
	 var transactionNo = $('#posId').val();
	 let mode = {};
        if ($('#cash').is(':checked')) {
            if ($('#cashAmount').val() === '' || $('#cashAmount').val() === '0') {
                swal('Cash amount should not be empty or zero if cash is selected');
                return false;
            }
         
            mode['cash'] = parseFloat($('#cashAmount').val());
            total= parseInt($('#cashAmount').val());
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
 
            mode['pos'] = parseFloat($('#posAmount').val());
            total=total+ parseInt($('#posAmount').val());
        }
        if ($('#online').is(':checked')) {
            if ($('#onlineAmount').val() === '' || $('#onlineAmount').val() === '0') {
                swal('Online amount should not be empty or zero if online is selected');
                return false;
            }
           
            mode['online'] = parseFloat($('#onlineAmount').val());
            total=total+ parseInt($('#onlineAmount').val());
        }
        if(total!=$("#amountINR").html()){
        	swal("Please provide requested amount");
        	return false;
        }
        
        
		params={
				billId:billId,
				paymentReqId:paymentReqId,
				transactionNo:transactionNo,
				amount:$("#amountINR").html(),
				ghId:$("#cityId").val(),
				status:status1,
				mode:mode
		}
		$.ajax({
				url: './admin/saveRecipetDetails.do',
				type: "POST",
      		    contentType: "application/json",
       		    data: JSON.stringify(params),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(result) {
					$('#loading').hide();
					$("#paymentDetails").modal("hide");
					swal(result.msg);
					getDataToGadRequestTable();
				},
				error: function(jqXHR, status, error) {
					$('#loading').hide();
					$("#vehicleModal").modal("hide");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
});
		
		var viewRecipetDetails=function (data){
			billIdForPdf= data.billId;
		$.ajax({
				url: './admin/getRecieptDetails/'+data.billId+'.do',
				type: "GET",
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success: function(result) {				
					$('#loading').hide();
					$('#printReceiptModal').modal('show');
					$('#printDate').text("Date:"+moment().format('DD-MM-YYYY'));
					$('#receiptDate').text(moment().format('DD-MM-YYYY'));
       				$("#pBillDate").text(moment().format('DD-MM-YYYY'));
	 				$("#pGuestNames").html(data.name+","+data.address);
	 				$("#guestHouseDetails").html(data.guestHouseName+","+data.cityName);
	 				$("#pBillNo").html(data.billId);
	  				var words = inWords(data.advanceAmount);
	  				$(".rgHouse").html(data.guestHouseName);
	  				$(".rcity").html(data.cityName);
	    			$("#pAmountAndWords").html(data.advanceAmount+"("+words+")");
	    			
	    			var wordsf = inWords(data.pendingAmount);
	    			$("#fAmountAndWords").html(data.pendingAmount+"("+wordsf+")");
	    			
	    			$("#cod").prop("disabled", false);
					$("#POS").prop("disabled", false);
					for(var i=0;i<result.data.length;i++){
           			if(result.data[i].recStatus=="A"){
           			$(".finalPaymentReceipt").hide();
           			$('#receiptDate').text(result.data[i].receiptDate);
       				$("#pBillDate").text(result.data[i].billDate);
           			$("#pRectId").text(result.data[i].recId);
            		if (result.data[i].modeOfpayment === 'cash') {
              		$('#cashShow').show();
                	$('#pCashAmount').val(result.data[i].amount);
            		}
            		if (result.data[i].modeOfpayment === 'pos') {
               		 $('#posShow').show();
               		 $('#pPostx').show();
                	 $('#pPosAmount').val(result.data[i].amount);
               		 $('#pPosId').val(result.data[i].pos_tr_num);
            		}
            		if (result.data[i].modeOfpayment === 'online') {
                	$('#onlineShow').show();
                	$('#pOnlineAmount').val(result.data[i].amount);
            		}
           			}
           			
           			if(result.data[i].recStatus=="F"){
           			
           			$(".finalPaymentReceipt").show();
           			$('#freceiptDate').text(result.data[i].receiptDate);
       				$("#fBillDate").text(result.data[i].billDate);
           			$("#fRectId").text(result.data[i].recId);
            		if (result.data[i].modeOfpayment === 'cash') {
              		$('#fcashShow').show();
                	$('#fCashAmount').val(result.data[i].amount);
            		}
            		if (result.data[i].modeOfpayment === 'pos') {
               		 $('#fposShow').show();
               		 $('#fPostx').show();
                	 $('#fPosAmount').val(result.data[i].amount);
               		 $('#fPosId').val(result.data[i].pos_tr_num);
            		}
            		if (result.data[i].modeOfpayment === 'online') {
                	$('#fonlineShow').show();
                	$('#fOnlineAmount').val(result.data[i].amount);
            		}
           			}
           			
       		 }
					
				},
				error: function(jqXHR, status, error) {
					$('#loading').hide();
					$("#vehicleModal").modal("hide");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
}


$('#currentBookingTable tbody').on('click', '#billView', function () {
        $('#billingDetailsFinal').modal('show');
        // var data=table.row( $(this).parents('tr') ).data();
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
		bookingIdForBillPdf=data.bookingId;
       $("#additionalFi").prop("disabled", true);
		$("#billFiName").html(data.name);
		$("#billFiId").html(data.bookingId);
		$("#billFiMobile").html(data.mobile);
		$("#billFiAddress").html(data.address);
		$("#billFiHall").html(data.confrenceDesc);	
		$("#billFiPeriod").html(data.confreqnceUseDesc);
		$("#billFiAmount").html(data.charge);
		$("#billFiAdvnc").html(data.advanceAmount);
		$("#additionalFi").html(data.additonalCharge);
		$("#billFiMode").html(data.modeOfPayment);
		$("#frmDate").html(data.fromDate);
		
		if(data.billStatus=="FB"||data.billStatus=="F"){
			$("#forViewAddi").show();
			$("#baseOnBill").html("Total Amount(Amount+Additional Charge):");
		}else{
			$("#forViewAddi").hide();
			$("#baseOnBill").html("Total Amount:");
		}
		
		
		if(data.billStatus=="P"||data.billStatus=="B"){
		$(".showInFinal").hide();
		if(data.billStatus=="B"){
		$("#needTextForView").text("Amount Need To Pay:");
		}
		if(data.billStatus=="P"){
		$("#needTextForView").html("Amount Paid:");
		}
		$(".header-data").html("Bill Details For Advance Payment");
		
		$("#billFiTotal").html(parseInt(data.pendingAmount)+parseInt(data.advanceAmount));
		$("#needFi").html(data.advanceAmount);
		}
		if(data.billStatus=="F" || data.billStatus=="FB"){
		if(data.additonalCharge=="0"){
			$("#ifNullAdd").hide();
		}else{
			$("#ifNullAdd").show();
		}
		$(".showInFinal").hide();
		$("#billFiTotal").html(parseInt(data.pendingAmount)+parseInt(data.advanceAmount));
		if( data.billStatus=="FB"){
			$("#needTextForView").text("Amount Need To Pay:");
			$("#needFi").html(data.pendingAmount);
		}
		if(data.billStatus=="F"){
			$(".showInFinal").show();
			$("#totalFi").html(parseInt(data.pendingAmount)+parseInt(data.advanceAmount));
			$("#needTextForView").text("Amount Paid:");
			$("#needFi").html(data.pendingAmount);
		}
		}
    });


$('#printReceipt').click(function(){    
	console.log("billIdForConfrence"+billIdForPdf);    
	window.open('./admin/getReceiptForConFrencePdf/' + billIdForPdf + '.do','_blank');
})
$('#printPdf').click(function(){    
	console.log("billIdForConfrence"+bookingIdForBillPdf);    
	window.open('./admin/getBillForConFrencePdf/' + bookingIdForBillPdf + '.do','_blank');
})

/*filter work*/
$('#filterSearch').click(function () {        
    	getDataToGadRequestTable();
    	$('#filter-popover').unbind();
    	setTimeout(function(){
    		$('#filter-popover').bind('click',preventClick);
    	}, 1000);
});
$('#resetFiterBtn').click(function () {
        $('#filterBookingId').val('');
        $('#filterBookingDate').val('');     
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        getDataToGadRequestTable();
});
$('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
});

	$('#payOnline').on('click', function () {
		let amount = $('#onlineAmount').val();
		if (amount == "" || amount == 0) {
			swal("Amount must be greater than 0");
			return false;
		}
		$('#payAmount').val(amount);
		$('#blockId').val(globalData.blockId);
		$('#confBillId').val(globalData.billId);
		$('#loading').show();
		$('#payOnlineForm').submit();
		$('#showStatus').html('');
		connect();
	});

});
function preventClick() {
       event.stopPropagation();
}
function onlyNumberKey(evt) {  
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
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
function forReset(){
	$('#pCashAmount').val("");
	$('#pOnlineAmount').val("");
	$('#pPosAmount').val("");
	$('#pPosId').val("");
	$('#fOnlineAmount').val("");
	$('#fPosAmount').val("");
	$('#fCashAmount').val("");
	$('#fPosId').val("");
	
	$('#cashAmount').val("");
	$('#onlineAmount').val("");
	$('#posAmount').val("");
	$('#posId').val("");
	 $('#postx').prop('hidden', true);
	$('#cashAmount').prop("disabled",true);
	$('#onlineAmount').prop("disabled",true);
	$('#posAmount').prop("disabled",true);
	$('#posId').prop("disabled",true);

	$('#payOnline').prop('hidden', true);
	$('#checkStatus').prop('hidden', true);
	$('#submitReceipt').prop('disabled', false);
	$('#showStatus').html('');
	
	$('input[name="pay"]').prop("checked",false);
}
function onlyNumberKey(evt) {
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
}