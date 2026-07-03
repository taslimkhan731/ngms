$('document').ready(function () {
    /* Validation ends */
    var blockId = 0;
    var catgDoc = null;
    var gDoc = null;
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
        setTimeout(function () {
            $('#city').trigger('change');
        }, 1000);
    }

    $("#filterBookingDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
        // format: 'YYYY-MM-DD'
    });
    $("#arrDt, #depDt").datetimepicker({
        useCurrent: false,
        minDate: moment().millisecond(0).second(0).minute(0).hour(0),
        format: 'DD-MM-YYYY'
    });
    $("#arrDt").on("dp.change", function (e) {
//        $('#existingBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
//        $("#depDt").data("DateTimePicker").minDate(e.date);
    });
    $("#depDt").on("dp.change", function (e) {
//        $('#existingBookingApplication').bootstrapValidator('revalidateField', 'depDt');
//        $("#arrDt").data("DateTimePicker").maxDate(e.date);
    });

 //validation of form dates and todate
	 $("#frmDt").on("dp.change", function (e) {
	 	$("#toDt").val("");
	 	 $("#toDt").prop('disabled',false);
	     $("#toDt").data("DateTimePicker").minDate(e.date.millisecond(0).second(0).minute(0).hour(0));
	  });
	 
	 $("#toDt").on("dp.change", function (e) {
	     $("#frmDt").data("DateTimePicker").maxDate(e.date);
	  }); 
 
	    $("#frmDtCon").on("dp.change", function (e) {
	    	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	        $("#formSubmissionBtnForConfrence").hide();
	        forDiabledProperty();
	    });
	    $("#frmDtCon, #hallType, #timePeriod,#startTime").on("change", function () {
	  		 forDiabledProperty();
	    });
	     
	    $("#toDt").on("dp.change", function (e) {
	        $('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'toDt');
	        $("#frmDt").data("DateTimePicker").maxDate(e.date);
	        if ($('#frmDt').val() != '') {
	            var date1 = convertStringToDatetime($('#frmDt').val());
	            var date2 = convertStringToDatetime($('#toDt').val());
	            var days = daysBetween(date1, date2);
	            $('#noOfDays').val(days);
	        }
	    });

    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
    $("#newAppList").show();
    $("#moderationTable").hide();
    
   	
   
	

    $("#process").on("click", function () {
        if (confirm("Do you want to process all current requests?")) {
            alert("SUCCESSFULLY PROCESSED ALL REQUESTS");
        } else {
            txt = "You pressed Cancel!";
        }
    })
    $('#createDE').on('click', function () {
    	  var today = moment().format("DD-MM-YYYY");
		  var fromDateValue = today + " 09:00";
		  $("#frmDt").val(fromDateValue);
		  var tomorrow = moment().add(1, "day").format("DD-MM-YYYY");
		  var toDateValue = tomorrow + " 08:00";
		  $("#toDt").val(toDateValue);
		  $("#noOfDays").val(1);
    	$("#formSubmissionBtn").show();
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        checkForShift(cityId, blockId);
        setTimeout(function () {
            let status = $('#shiftStatus').val();
            if (status == "true") {
                $('.cat_1').prop('disabled', true);
                $('.cat_234').prop('disabled', true);
                $('#editDetails').modal('show');
                $('#addGuestModalTitle').html("Add Guest Details");
                $('#onlyRoom').click();
                $('#dormitoryWrapper').hide();
                $('#guestTableCat4').hide();
                let bookingFor = $('#bookingFor').html('');
                bookingFor.append('<option value="others">Others</option>')
                bookingFor.prop('disabled', true);
                $('#mobileNumber').hide();
                $('#formSubmissionBtn').html('Submit');
                $('#formSubmissionBtnForConfrence').html('Submit');
                $('#category_supporting').hide();
                $('#supporting').hide();
                $('#guestTableCat123').show();
                $('#duringStayDiv').addClass("disableElement");
                $('#arrivalRow').addClass('disableElement');
                $('#departureRow').addClass('disableElement');
                $('#suppDocDtlsBox').hide();
                $('#desgDocDtlsBox').hide();
                $('#gDocDtlsBox').hide();
            } else if (status == "false") {
                let msg = $('#shiftMsg').val();
                swal(msg);
                return false;
            } else {
                swal("Please open shift");
                return false;
            }
        }, 1000);
    });

    $('#moderation').on('click', function () {
        if (!isCityAndGHSelected())
            return false;
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        checkForShift(cityId, blockId);
        setTimeout(function () {
            let status = $('#shiftStatus').val();
            if (status == "true") {
                $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
                $('#addOrModifyModModalView').modal('show');
            } else if (status == "false") {
                let msg = $('#shiftMsg').val();
                swal(msg);
                return false;
            } else {
                swal("Please open shift");
                return false;
            }
        }, 1000);
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

    var table2 = $("#currentBookingTable").DataTable({
        "paging": true,
		"searching": false,
		"scrollX": true,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"bLengthChange": false,
		"fixedHeader": {
			header: true,
			footer: true
    	},
        "scrollCollapse": true,
    	"serverSide": true,
		"autoWidth": true, 
    	"ajax": {
        	"url": "./admin/getAllotmentLstDtlsAccToReq.do",
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
            	d.bookingDate = $('#filterBookingDate').val();
            	d.purpose = $('#filterPurpose').val();
            	d.mobNo = $('#filterMobNum').val();
            	d.name = $('#filterGuestName').val();
            	d.category = $('#filterCategory').val();
				d.status = $('#filterstatus').val();
            	d.type = "GH";
        	}, "error": function (jqXHR, textStatus, errorThrown) {
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
                "className": "text-center text-nowrap",
                "targets": [0,1,3,4,5,6,7,8,9,10,11]
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
            {"width": "5%", "targets": 1, "data": "bookingId"},
            {"width": "10%", "targets": 2, "data": "name"},
            {"width": "10%", "targets": 3, "data": "bookingDt"},
            {"width": "10%", "targets": 4, "data": "fromDate"},
            {"width": "10%", "targets": 5, "data": "toDate"},
            {"width": "10%", "targets": 6, "data": "catgId"},
            {"width": "10%", "targets": 7, "data": "purpose"},
            {"width": "10%", "targets": 8, "data": "mobile"},
            {"width": "10%", "targets": 9, "data": "typeOfRoom"},
            {"width": "10%", "targets": 10, "data": "statusDesc"},
            {
                "width": "10%", "targets": 11, "data": null,
                render: function (data, type, row, meta) {
                    let str = '<button type="button" id="view" title="View Allotment Details" class="btn btn-view btn-sm mr-1"><i class="fa fa-eye" aria-hidden="true"></i></button> '

                    if (data.status == "REQ") {
                        str += '<button type="button" id="edit" title="Edit Allotment Details" class="btn btn-edit btn-sm mr-1"><i class="fa fa-edit" aria-hidden="true"></i></button> ' +
                            '<button type="button" id="delete" title="Delete" class="btn btn-dlt btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>'
                    }
                    return str;
                }
            }
        ],
    });

    //Edit button click function
    $('#currentBookingTable tbody').on('click', '#edit', function () {
    	$("#toDt").prop('disabled',false);
    	$("#formSubmissionBtn").show();
        $('#addGuestModalTitle').html("Edit Guest Details");
        $('#editDetails').modal('show');
        // var data=table.row( $(this).parents('tr') ).data();
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
        blockId = data.ghId;
        console.log(data);
        guestCount = 0;
        console.log(data);
        console.log(data.bookingId);
        $('#guestDetailsWrapper').html("");
        $('#guestDetailsWrapper12').html('');
        $('#guestDetailsWrapper1').html('');
        $('#formSubmissionBtn').html('Update Details');
        $('#formSubmissionBtnForConfrence').html('Update Details');
        localStorage.setItem('bI', data.bookingId);
        localStorage.setItem('subType', 'UPDATE');
        /*$('#applicationHeader').html('Application Form Update');*/
        setGuestDetails(data.bookingId, false);
        $('#arrivalRow').addClass('disableElement');
        $('#departureRow').addClass('disableElement');

    });

    $('#currentBookingTable tbody').on('click', '#view', function () {
    	 $('#addGuestModalTitle').html("View Guest Details");
    	$("#toDt").prop('disabled',false);
    	$("#formSubmissionBtn").hide();
        console.log("clicked----");
        $('#editDetails').modal('show');
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
        blockId = data.ghId;
        console.log("BlockId-----" + blockId);
        // getAndSetGuestRoomDetails(data.bookingId);
        setGuestDetails(data.bookingId, true)
    });

    $('#currentBookingTable tbody').on('click', '#delete', function () {
        var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
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
                    url: './admin/cancelRequest/' + data.bookingId + '/' + data.mobile + '.do',
                    type: "GET",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (data) {
                        $('#loading').hide();
                        if (data.success) {
                            swal(data.msg);
                            //getDataToGadRequestTable();
							table2.ajax.reload();
							moderationViewTbl.ajax.reload();
                        } else {
                            swal(data.msg);
                        }
                    }
                });
            });
    });

    var moderationViewTbl = $("#currentBookingTable1").DataTable({
        "paging": true,
        "searching": false,
        "scrollX": false,
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
        "serverSide": true,
		"autoWidth": true, 
    	"ajax": {
        	"url": "./admin/getAllotmentLstDtlsAccToReq.do",
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
            	d.bookingDate = $('#filterBookingDate').val();
            	d.purpose = $('#filterPurpose').val();
            	d.mobNo = $('#filterMobNum').val();
            	d.name = $('#filterGuestName').val();
            	d.category = $('#filterCategory').val();
				d.status = $('#filterstatus').val();
            	d.type = "GH";
        	} ,"error": function (jqXHR, textStatus, errorThrown) {
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
                "className": "text-center text-nowrap",
                "targets": [0,1,3,4,5,6,7,8,9,10]
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
            {"width": "5%", "targets": 1, "data": "bookingId"},
            {"width": "5%", "targets": 2, "data": "name"},
            {"width": "5%", "targets": 3, "data": "bookingDt"},
            {"width": "10%", "targets": 4, "data": "fromDate"},
            {"width": "10%", "targets": 5, "data": "toDate"},
            {"width": "10%", "targets": 6, "data": "catgId"},
            {"width": "10%", "targets": 7, "data": "purpose"},
            {"width": "10%", "targets": 8, "data": "mobile"},
            {"width": "10%", "targets": 9, "data": "typeOfRoom"},
            {
                "width": "10%", "targets": 10, "data": null,
                render: function (data, type, row, meta) {
                    return '<button id="ModBtn" class="custom-btn custom-btn-active" type="button"><i class="fa fa-edit"></i> Moderator</button> ';

                }
            }
        ],
    });

    //moderation
    $('#currentBookingTable1 tbody').on('click', '#ModBtn', function () {
        /*$('#addOrModifyModModal').modal('show');
        $('#saveDtls').show();
        $('#modifyDtls').hide();
        var data = moderationViewTbl.row($(this).closest('tr')).data() != undefined ? moderationViewTbl.row($(this).closest('tr')).data() : moderationViewTbl.row($(this)).data();
        console.log("data of the moderationViewTbl" + data)
        getAndSetGuestRoomDetails(data.bookingId);*/
        
        
        var data = moderationViewTbl.row($(this).closest('tr')).data() != undefined ? moderationViewTbl.row($(this).closest('tr')).data() : moderationViewTbl.row($(this)).data();
        console.log("data of the moderationViewTbl" + data)
        typeOfRoom = data.typeOfRoom;
        if(typeOfRoom == 'Conveyance'){        	
        	$('#convAddOrModifyModModal').modal('show');
        	$('#convSaveDtls').show();
            $('#convModifyDtls').hide();
            getAndSetGuestConveyanceDetails(data.bookingId);
        }else{
        	$('#addOrModifyModModal').modal('show');
        	$('#saveDtls').show();
            $('#modifyDtls').hide();
            getAndSetGuestRoomDetails(data.bookingId);
        }
    });
    
    
 // Coveyance Details
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
                       $('#convModYesDuringStay').show();
                        $('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                    } else {
                        $('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                        $('#convModYesDuringStay').hide();
                    }
                    var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
                    if (convay == 1) {
                    	$('#convModNoconve').hide();
                    	$('#convModYesconve').show();
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
                    	$("#convSuppDocDtls").html('');
                    }
                    if (catgdocname != null) {
                        $("#convCatDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	 $("#convCatDocDtls").html('');
                    }
                    if (gdocname != null) {
                        $("#convGuestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#convGuestDocDtls").html('');
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
    
    $('#purp').on('change', function () {
        var purpose = $('#purp').val();
        if (purpose == '1') {
            $('#supporting').show();
            $('#purposeDocLabel').html('Official Document Upload');
        } else if (purpose == '3' || purpose == '4') {
            $('#supporting').show();
            if (purpose == '3') {
                $('#purposeDocLabel').html('Patient Document Upload');
            } else {
                $('#purposeDocLabel').html('Student Document Upload');
            }
        } else {
            $('#supporting').hide();
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
    

    var getAndSetGuestRoomDetails = function (bookingId) {
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
                    	$('#Yesconve').show();
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
                        $("#purpDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }else{
                    	$("#purpDocDtls").html('');
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
                    r.data.ghList.forEach((item, index) => {
                        $('#count').val(r.data.ghList.length);
                        $('#roomDetails').append(
                            `<tr class="text-center text-nowrap resp">
								<input type="hidden" id="blockId${index + 1}"  name="blockId${index + 1}" value="${item.id}">
								<input type="hidden" id="blockId"  name="blockId" value="${r.data.blockId}">
								<input type="hidden" id="ghId"  name="cityId" value="${r.data.cityId}">
								<input type="hidden" id="reqId"  name="bookingId" value="${r.data.bookingId}">
								<td scope="col">${item.name}</td>
								<td scope="col"><input type="text" min="0" placeholder="0" id="noOfRooms${index + 1}"  name="noOfRooms${index + 1}" class="test" value="" autocomplete="off" onkeyup="return restrictInput(${index + 1});"></td>
								<td scope="col"><input type="text" min="0" placeholder="0" id="noOfBedsDormitory${index + 1}" name="noOfBedsDormitory${index + 1}" class="test" autocomplete="off" value="" onkeyup="return restrictInput(${index + 1});"></td>								
							</tr>`                        		                        		
                        );
                    });                    
                                       
                    if (r.data.catg == "C4") {
                        $('#guestDetailsWrapperr').html('');
                        //$('#guestDetailsWrapper12').html('');
                        //$('#guestDetailsWrapper1').html('');
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
                        //$('#guestDetailsWrapper12').html('');
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
    $('#addOrModifyModModal').on('hidden.bs.modal', function () {
        $('#roomDetails').html('');
    });   

    $('#office').hide();
    $('#supporting').hide();
    $('#convenceFrom').hide();
    $('#convenceTo').hide();
    $('#desgHidden').hide();
    $('#noOfRooms').val('');
    $("#noOfBeds").addClass('disableElement');


	  $("#frmDtCon").datetimepicker({
    	useCurrent: false,
    	minDate: moment().add(1, 'day').millisecond(0).second(0).minute(0).hour(0),
    	format: 'DD-MM-YYYY'
	});

    $("#toDtCon").datetimepicker({
        useCurrent: false,
        minDate: moment(),
        format: 'DD-MM-YYYY 08:00',

    });
	
	$("#startTime").datetimepicker({
      	format: 'HH:mm',
        minDate: moment({ hour: 8, minute: 0 }),
    	maxDate: moment({ hour: 20, minute: 0 })
    });
	
	
    $("#depTm, #arrTm").datetimepicker({
        format: 'hh:mm A'
    });
	
 
	
   

    $("#arrDt").on("dp.change", function (e) {
//        $('#existingBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
//        $("#depDt").data("DateTimePicker").minDate(e.date);
    });
    $("#depDt").on("dp.change", function (e) {
//        $('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'depDt');
//        $("#arrDt").data("DateTimePicker").maxDate(e.date);
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

    // show and hide according to btn
    $('#onlyRoom').on('click', function () {
        $('#onlyRoom').addClass('segment-active');
        $('#roomCon').removeClass('segment-active');
        $('#conv').removeClass('segment-active');
        $('#conference').removeClass('segment-active');
		$('.bedRoomDetails').show();
        $('#roomBed').prop('checked', true);
		$(".hideAtConf").show();	
		$('.bed-header').show();
        $('#otherGuestDetails').show();	
        $('.gst-header').show();
       	$('.gstTable').show();	
		$('#viewCate').show();
        $('#roomTyp').val($(this).val()); // onlyRoom
        console.log($('#roomTyp').val());
        $('#conveyance').hide();
        $('#GuestDetails').show();
        $('#btnSub').show();
        $('.roomBook').show();
        $('#conferenceTerrace').hide();
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
		$(".hideAtConf").show();	
        $('#roomBed').prop('checked', true);
		$('.bedRoomDetails').show();
		$('.bed-header').show();
        $('#otherGuestDetails').show();	
    	$('#viewCate').show();
        $('.gst-header').show();
        $('#roomTyp').val($(this).val()); // roomCon
        console.log($('#roomTyp').val());
        $('#conveyance').show();
        $('#GuestDetails').show();
        $('#btnSub').show();
        $('.roomBook').show();
        $('#conferenceTerrace').hide();
        $('#roomWrapper').show();
        $('#dormintoryWrapper').hide();
    });

    $('#conv').on('click', function () {
        $('#conv').addClass('segment-active');
        $('#onlyRoom').removeClass('segment-active');
        $('#roomCon').removeClass('segment-active');
        $('#conference').removeClass('segment-active');
		$('#viewCate').show();
        $('#roomTyp').val($(this).val()); // conv
        console.log($('#roomTyp').val());
        $('#conveyance').show();
        $('#GuestDetails').show();
        $('#otherGuestDetails').show();
        $(".hideAtConf").show();		
        $('.bedRoomDetails').show();
        $('.bed-header').show();
        $('#otherGuestDetails').show();	
        $('.gst-header').show();
     	
        $('.gstTable').show();		
        $('#btnSub').show();
        $('.roomBook').hide();
        $('#roomWrapper').hide();
        $('#dormitoryWrapper').hide();
        $('#conferenceTerrace').hide();
    });

    $('#conference').on('click', function () {
        $('#conference').addClass('segment-active');
        $('#onlyRoom').removeClass('segment-active');
        $('#roomCon').removeClass('segment-active');
        $('#conv').removeClass('segment-active');
		getConfrenceHalltype();
        $('#roomTyp').val($(this).val()); // onlyRoom
        console.log($('#roomTyp').val());	
        $('#conferenceTerrace').show();
        $('.hideAtConf').hide();
       	$('#viewCate').hide();
       	if($("#city").val()!="04"){
    		$("#inclusivexclusive").html("The tariff will be inclusive of Electricity and Service charges.");
    	}else{
    		$("#inclusivexclusive").html("The tariff will be exclusive of Electricity, AC and Service charges.");
    	}
    	if($("#city").val()=="02"){
    		$(".mumbaiPoint").show();
    	}else{
    	 $(".mumbaiPoint").hide();
    	}
    });

    /*
     * $("input[name='bookingCond']").click(function() { if($(this).val() ==
     * "roomCon"){ $('#roomTyp').val($(this).val()); $('#reqDetails').show();
     * $('#convenceFrom').show(); $('#convenceTo').show();
     * $('#GuestDetails').show(); $('#btnSub').show(); }else if($(this).val() ==
     * "onlyRoom"){ $('#roomTyp').val($(this).val()); $('#reqDetails').show();
     * $('#convenceFrom').hide(); $('#convenceTo').hide();
     * $('#GuestDetails').show(); $('#btnSub').show(); } else {
     * $('#roomTyp').val($(this).val()); $('#reqDetails').hide();
     * $('#convenceFrom').show(); $('#convenceTo').show();
     * $('#GuestDetails').hide(); $('#btnSub').show(); } });
     */

    var guestParameters = 7;
    var guestCount = 0;
    // blockId = getUrlParameter('id');
    var suppDoc = null;
    var bookingReqId = null;
    var officAddress = '';

    $('#roomWrapper').show();
    $('#dormintoryWrapper').hide();

    $("input[name='bookingType']").click(function () {
        if ($(this).val() == "room") {
            $('#roomWrapper').show();
            $('#dormintoryWrapper').hide();
            $('#noOfRooms').val('1');
            $('#noOfBeds').val('');
            $('#roomWrapper').show();
            $('#dormitoryWrapper').hide();
        } else {
        	console.log("dormatory show");
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

	$("#hallType").change(function () {
         let cityId = $('#city').val();
     	 let blockId = $('#gHouse').val();
     	 let conCatId=$('#hallType').val();
     	 $("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
     	 $("#formSubmissionBtnForConfrence").hide();
      	params={
     	 cId:cityId,
      	 gId:blockId,
     	 conCatId:conCatId
      	};
      	
 		$.ajax({
		url: './admin/getConfrenceTimePeriodDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(params),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
				$("#amount").val("0");
				$("#capacity").val("0");
                $('#loading').hide();
               	console.log(result);
               	$("#timePeriod").html("");
               	$("#timePeriod").append(
               	 "<option value='' selected>---select---</option>"
               	);
               	for(var i=0;i<result.data.length;i++){
               	$("#timePeriod").append(
               		"<option value="+result.data[i].confUseId+">"+result.data[i].confUseName+"</option>"
               	);
               	}
            }, error: function (jqXHR, status, error) {
                swal(error);
            }
        }); 
    });

	$("#timePeriod").change(function () {
         let cityId = $('#city').val();
     	 let blockId = $('#gHouse').val();
     	 let conCatId=$('#hallType').val();
     	 let conUseId=$("#timePeriod").val();
     	 if(conUseId=="4"){
     	 	$(".forTwoHrs").show();
     	 }else{
     	 	$(".forTwoHrs").hide();
     	 }
      params={
     	 cId:cityId,
      	 gId:blockId,
     	 conCatId:conCatId,
     	 conUseId:conUseId,
     	 formDate:$("#frmDtCon").val(),
     	 startTime:$("#startTime").val(),
     	 endTime:$("#endTime").val()
      	};
      	$("#formSubmissionBtnForConfrence").hide();
 		$.ajax({
		url: './admin/getConfrenceAmount.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(params),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
                $('#loading').hide();
                var amountWithCapacity=result.data.split(",");
                $("#amount").val(amountWithCapacity[0]);
             	$("#capacity").val(amountWithCapacity[1]);  	
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
    
    $('#filterstatus').append(
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
    	//getDataToGadRequestTable();
		table2.ajax.reload();
		moderationViewTbl.ajax.reload();
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
        $('#filterPurpose').val('');
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        $('#filterstatus').val('');
        //getDataToGadRequestTable();
		table2.ajax.reload();
		moderationViewTbl.ajax.reload();
    });
// *******************************
    /*var getDataToGadRequestTable = function () {       
        
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let bookingDate = $('#filterBookingDate').val();
        let purpose = $('#filterPurpose').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
        let status = $('#filterstatus').val();
        let category = $('#filterCategory').val();

        let postData = { 
    		cId: city,
            gId: gHouse,	
            bookingId: bookingId,
            bookingDate: bookingDate,
            category:category,
            purpose:purpose,
            mobNo:mobNo,
            name:name,
            reqType: 'GH',
            status: status
        };
        $.ajax({
            url: './admin/getAllotmentLstDtlsAccToReq.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result.data);
                table2.clear();
                moderationViewTbl.clear();
                table2.rows.add(result.data).draw();
                moderationViewTbl.rows.add(result.data).draw()
            },
            error: function (jqXHR, status, error) {
             
            }
        });
    }*/

    //getDataToGadRequestTable();
	table2.ajax.reload();
	moderationViewTbl.ajax.reload();
    $("#city").change(function () {
    	var id = $('#city').val();
        //getDataToGadRequestTable();
		table2.ajax.reload();
		moderationViewTbl.ajax.reload();
        /*getDataToGadRequestTable1();*/
    });
    $("#gHouse").change(function () {
    	blockId = $(this).val();
        //getDataToGadRequestTable();
		table2.ajax.reload();
        /*getDataToGadRequestTable1();*/
    });

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

    $('#catg').on('change', function () {
        var id = $('#catg').val();
        console.log(id);
        getDesignationListDtls(id, '');
    });

    $('#city').on('change', function () {
        var id = $('#city').val();
        getGuestHouseListDtls(id);
    });
    $('#city1').on('change', function () {
        var id = $('#city1').val();
        console.log(id);
        getGuestHouseListDtls(id);
    });

    $('#catg').on('change', function () {
        var id = $('#catg').val();
        console.log("id is _" + id);
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
                $("#desg1").html("");
                $("#desg").append('<option value="">---Select---</option>');
                $("#desg1").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    if (option.id == value) {
                        $("#desg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                        $("#desg1").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                    } else {
                        $("#desg").append('<option value="' + option.id + '">' + option.name + '</option>');
                        $("#desg1").append('<option value="' + option.id + '">' + option.name + '</option>');
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

    var getCatgoryListDtls = function (type) {
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
                    if (('C' + type) == option.id) {
                        $("#catg").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                    } else {
                        $("#catg").append('<option value="' + option.id + '">' + option.name + '</option>');
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

    var desAccToCatTable = $("#guestCatDataTable").removeAttr('width').DataTable({
        "paging": true,
        "searching": true,
        "responsive": true,
        "lengthMenu": [[100, 200, 300, -1], [100, 200, 300, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
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

    $('#guestCatDataTable').on('click', 'tbody #cateType', function () {
        $('#myModalViewForCategory').modal('hide');
        var data_row = desAccToCatTable.row($(this).closest('tr')).data();
        /*getCatgoryListDtls(data_row.desc);
        getDesignationListDtls(('C' + data_row.desc), data_row.id);
        getPurposeListDtls(('C' + data_row.desc));
        onCatgChange('C' + data_row.desc);*/
        getDesgAccToCat(data_row.desc);
        getDesignationListDtls(('C' + data_row.desc), data_row.id);
        getPurposeListDtls(('C' + data_row.desc), '');
        onCatgChange('C' + data_row.desc);
// getGuestDesignationDetails(data_row.id);
    });

    var viewDesg;
    $('#viewCate').click(function () {
        viewDesg = "All";
        var cat = $('#catg').val();
        console.log(cat);
        if (cat != "") {
            console.log("value of catg" + cat)
            viewDesg = cat;
        }
        getDesgAccToCat(viewDesg);
        $('#myModalViewForCategory').modal('show');

    });

    var getDesgAccToCat = function (type) {
        $.ajax({
            url: "./guest/getDesgList/" + type + ".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                desAccToCatTable.clear().draw();
                desAccToCatTable.rows.add(result).draw();
            }
        });
    };


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
	var forDiabledProperty=function(){

		if($("#frmDtCon").val()!="" && $("#hallType").val()!="" && $("#timePeriod").val()!=""){
				$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",false);  
	         }else{
	         	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",true);
	         	$("formSubmissionBtnForConfrence").hide();
		}
		if($('#timePeriod').val()=="4"){
			if($("#frmDtCon").val()!="" && $("#hallType").val()!="" && $("#timePeriod").val()!="" && $("#startTime").val()){
				$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",false);  
	         }else{
	         	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",true);
	         	$("formSubmissionBtnForConfrence").hide();
			}
		}
	}
	 $('#timePeriod').on('change', function () {
	 	if($('#timePeriod').val()=="4"){
	 		if($("#frmDtCon").val()!="" && $("#hallType").val()!="" && $("#timePeriod").val()!="" && $("#startTime").val()){
				$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",false);  
	         }else{
	         	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	         	$("#checkAvb").prop("disabled",true);
	         	$("formSubmissionBtnForConfrence").hide();
			}
	 	}
	 });
    $('#purp').on('change', function () {
        var purpose = $('#purp').val();
        if (purpose == '1') {
            $('#supporting').show();
            $('#purposeDocLabel').html('Upload Official Document (jpg/png/pdf)<small class="small">*</small>');
        } else if (purpose == '3' || purpose == '4') {
            $('#supporting').show();
            if (purpose == '3') {
                $('#purposeDocLabel').html('Upload Patient Document (jpg/png/pdf)<small class="small">*</small>');
            } else {
                $('#purposeDocLabel').html('Upload Student Document (jpg/png/pdf)<small class="small">*</small>');
            }
        } else {
            $('#supporting').hide();
        }
    });

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
                $("#purp1").html("");
                $("#purp").append('<option value="">---Select---</option>');
                $("#purp1").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    if (option.id == value) {
                        $("#purp").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                        $("#purp1").append('<option value="' + option.id + '" selected="selected">' + option.name + '</option>');
                        // $("#desg").append('<option value="'+option.id+'"
                        // selected="selected">'+option.name+'</option>');
                    } else {
                        $("#purp").append('<option value="' + option.id + '">' + option.name + '</option>');
                        $("#purp1").append('<option value="' + option.id + '">' + option.name + '</option>');
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

    getCatgoryListDtls();

    var convertingGuesetDetailsObjectToArrayOfObjects = function (data) {
        var array = [];
        var count = data.length / guestParameters;
        console.log("Data lenght in converting guest details to array obj" + data.length);
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
            $('#guestDetailsWrapper12').append(
                '<tr id=tr' + count + '> ' +
                '<td class="text-center text-nowrap" id=guestCo' + count + '>' + count + '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="hidden" name="guestId" id="guestId' + count + '">' +
                '<input type="hidden" name="guestSlNo" value="' + count + '">' +
                '<input type="text" placeholder="Name" autocomplete="off" id="guestName' + count + '" name="guestName" onkeypress="toCapitaLiaze(event)">' +
                '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="number" placeholder="Age in years" autocomplete="off" id="guestAge' + count + '" name="guestAge">' +
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
                '<input type="text" placeholder="Designation" autocomplete="off" name="guestDesignation" id="guestDesignation' + count + '">' +
                '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="text" placeholder="Department" autocomplete="off" name="guestDepartment" id="guestDepartment' + count + '">' +
                '</td> ' +              
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
                '<input type="text" placeholder="Name" autocomplete="off" id="guestName' + count + '" name="guestName" onkeypress="toCapitaLiaze(event)">' +
                '</td> ' +
                '<td class="text-center text-nowrap">' +
                '<input type="number" placeholder="Age in years" autocomplete="off" id="guestAge' + count + '" name="guestAge">' +
                '</td> ' +
                '<td class="text-center text-nowrap"> ' +
                '<select id="guestGender' + count + '" name="guestGender"> ' +
                '<option>---Select---</option> ' +
                '<option value="M">Male</option> ' +
                '<option value="F">Female</option> ' +
                '<option value="O">Other</option> ' +
                '</select> ' +
                '<input type="hidden" id="guestDesignation' + count + '" name="guestDesignation" value="">' +
                '<input type="hidden" id="guestDepartment' + count + '" name="guestDepartment" value="">' +'</td> ' +
                '</td> ' +                  
                '</tr> '
            );
        }
        if (data != undefined) {
            $("#guestId" + count).val(data.guestId);
            $("#guestName" + count).val(data.guestName);
            $("#guestAge" + count).val(data.guestAge);
            $("#guestGender" + count).val(data.guestGender);
            /*$("#guestAddr" + count).val(data.guestAddr);*/
            $("#guestDesignation" + count).val(data.guestDesignation);
            $("#guestDepartment" + count).val(data.guestDepartment);
        }
        if (readonly != undefined ? readonly : false) {
            $("#guestId" + count).addClass('disableElement');
            $("#guestName" + count).addClass('disableElement');
            $("#guestAge" + count).addClass('disableElement');
            $("#guestGender" + count).addClass('disableElement');
            /* $("#guestAddr" + count).addClass('disableElement');*/
            $("#guestDesignation" + count).addClass('disableElement');
            $("#guestDepartment" + count).addClass('disableElement');
            $('#checkRoomWith').addClass('disableElement');
        }
    }

    $('#noOfAdults, #noOfChildren').on('click', function () {
        if ($('#catg').val() == "") {
            swal("Please select category");
            $('#noOfAdults').val('');
            $('#noOfChildren').val('');
        }
    });

    $('#noOfAdults, #noOfChildren').on('blur', function () {
        let noOfAdults = $('#noOfAdults').val() == '' ? 0 : $('#noOfAdults').val();
        let noOfChildren = $('#noOfChildren').val() == '' ? 0 : $('#noOfChildren').val();
        console.log(noOfAdults + "----count of the adult-----" + noOfChildren + "count of the childer");
        let totalGuests = parseInt(noOfAdults) + parseInt(noOfChildren);
        $('#guestDetailsWrapper12').html('');
        $('#guestDetailsWrapper1').html('');
        $('#guestDetailsWrapper').html('');

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

    $('#bookingFor').change(function () {
        var bookingFor = $('#bookingFor').val();
        if (bookingFor == 'others') {
            $('#otherGuestDetails').show();
        } else {
            $('#otherGuestDetails').hide();
        }
        $('#guestDetailsWrapper').html('');
        $('#guestDetailsWrapper1').html('');
        $('#guestDetailsWrapper12').html('');
        $('#noOfAdults').val('');
        $('#noOfChildren').val('');
        if (bookingFor == "self") {
            $('#noOfAdults').val(1);
            $('#noOfAdults').trigger('blur');
        }
    });

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
                    },
                    date: {
                        format: 'DD-MM-YYYY HH:MM'
                    }
                }
            }, toDt: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    },
                    date: {
                        format: 'DD-MM-YYYY HH:MM'
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
            }, emailId: {
                validators: {
                    // notEmpty: {
                    //     message: 'field can\'t be empty'
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
                        message: 'select a valid optionn'
                    }
                }
            }, offAddr: {
                validators: {
                    notEmpty: {
						message: 'Field can\'t be empty'
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
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         }
            //     }
            // }, flNoFr: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         },
            //         regexp: {
            //             regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
            //             message: 'field is not valid'
            //         }
            //     }
            // }, arrDt: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         }
            //     }
            // }, arrPla: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         },
            //         regexp: {
            //             regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
            //             message: 'field is not valid'
            //         }
            //     }
            // }, travelModeTo: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         }
            //     }
            // }, flNoTo: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         },
            //         regexp: {
            //             regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
            //             message: 'field is not valid'
            //         }
            //     }
            // }, depDt: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         }
            //     }
            // }, depPla: {
            //     validators: {
            //         notEmpty: {
            //             message: 'field can\'t be empty'
            //         },
            //         regexp: {
            //             regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
            //             message: 'field is not valid'
            //         }
            //     }
            }
        }
    }).on('error.field.bv', function(e, data) {
        if (data.bv.getSubmitButton()) {
            data.bv.disableSubmitButtons(false);
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var type = $('#roomTyp').val();
        if (type == 'conv') {
            if (!checkTravel())
                return false;
            submitBookingDetails('C');
        } else if (type == 'onlyRoom') {
            submitBookingDetails('R');
        } else {
            if (!checkTravel())
                return false;
            submitBookingDetails('RC');
        }
    });
	
	$('#submitBookingDetailsConfrence').bootstrapValidator({
        btnSubmit: 'button[type="submit"]',
        fields: {
            confrenceGuestName: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }
                }
            }, hallType: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    },
                   
                }
            }, address: {
                validators: {
                    notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
			            regexp: /^[a-zA-Z0-9\s,.-]+ [1-9][0-9]{2}-?[0-9]{3}$/,
			            message: 'Please enter complete address followed by a 6-digit pincode, separated by a space'
			        }
                }
            },mobileNo: {
                validators: {
                    notEmpty: {
                        message: 'field can\'t be empty'
                    }, regexp: {
                        regexp: /^[9 8 7 6]\d{9}$/,
                        message: 'only 10 digits are allowed'
                    }
                }
            },
            timePeriod: {
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
            termsCondition: {
            validators: {
              notEmpty: {
                message: 'Please accept the terms and conditions'
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
        /*if (data.bv.getSubmitButton()) {
            data.bv.disableSubmitButtons(false);
        }*/
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var type = $('#roomTyp').val();
        if (type == 'conv') {
            if (!checkTravel())
                return false;
            submitBookingDetailsConfrence('C');
        } else if (type == 'onlyRoom') {
            submitBookingDetailsConfrence('R');
        } else {
          /*  if (!checkTravel())
                return false;*/
            submitBookingDetailsConfrence('RC');
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
	$("#advncAamount").on("change",function(){
    	console.log($("#amount").val());
    	console.log($("#advncAamount").val());
    	if(parseInt($("#amount").val())<parseInt($("#advncAamount").val())){
    	swal("Advance Amount Cann't be greater than Amount");
    	$("#advncAamount").val("");
    	return false;
    	}
    });
	 $("#startTime").on("dp.change", function (e) {
	 	const timeString =  $("#startTime").val();
		
		// Parse the time string into a Date object
		const time = new Date(`2000-01-01T${timeString}:00`);

		// Add two hours to the time using setHours()
		time.setHours(time.getHours() + 2);

		// Format the resulting date object back into HH:mm format
		const newTimeString = time.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", hour12: false });
		$("#endTime").val(newTimeString);
		forDiabledProperty();
	 });
	
	
	
    $("#editDetails").on("hide.bs.modal", function () {
        //$(this).find('form').trigger('reset');
        $('#existingBookingApplication').bootstrapValidator('resetForm', true);
        $('#existingBookingApplication')[0].reset();
        //$('#editDetails').find('*').removeClass("disableElement");
        $('#guestDetailsWrapper').html('');
        $('#guestDetailsWrapper1').html('');
        $('#guestDetailsWrapper12').html('');
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
        guestCount = 0;
        $("#splitCityGH").hide();
    });


    var setGuestDetails = function (bookingId, isReadonly) {
        console.log("Booking Id------", bookingId);
        let bookingFor = $('#bookingFor').html('');
        bookingFor.append('<option value="">--Select--</option>')
        bookingFor.append('<option value="self">Self</option>')
        bookingFor.append('<option value="others">Others</option>')
        bookingFor.prop('disabled', false);
        $('#mobileNumber').show();

        var formData = $('#existingBookingApplication').serializeArray();
        var otherDetails = formData.filter(d => !d.name.match(/guest/g));

        console.log(otherDetails);
        $.ajax({
            url: "./admin/getBookingDetailsByBookingId/" + bookingId + ".do",
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
                    editOrViewGuestDetails(r, otherDetails, isReadonly);
                } else {
                    swal(r.msg);
                }
            }, error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }

    function editOrViewGuestDetails(r, otherDetails, isReadonly) {
    	blockId = r.data.blockId;
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
            //$('#addressDetails').show();
            $('#convFrDtls').show();
            $('#convToDtls').show();
            $('#GuestDetails').show();
            if (isReadonly) {
                $('.cat_all').prop('disabled', true);
                $('#roomCon').prop('disabled', false);
                $('#roomCon').addClass('disableElement');
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
                $('#onlyRoom').addClass('disableElement');
            }
        } else if (type == 'C') {
            $('#conv').click();
            $('#roomWrapper').hide();
            $('#dormintoryWrapper').hide();
            if (isReadonly) {
                $('.cat_all').prop('disabled', true);
                $('#conv').prop('disabled', false);
                $('#conv').addClass('disableElement');
            }
        }
        // $('input[name="bookingCond"][type="radio"][value="' + valu +'"]').prop('checked', true);
        if (r.data.bookingType != null) {
            if (r.data.bookingType == "") {
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
                $('select[name="' + item.name + '"]').removeClass('disableElement');
                $('textarea[name="' + item.name + '"]').removeClass('disableElement');
                $('.viewOnly').removeClass('disableElement');
            }
        });
        if (bookingReqId.charAt(0) === 'H') {
            $("#bookingFor").addClass("disableElement");
            $('#mobileNumber').hide();
        } else {
            $("#bookingFor").removeClass("disableElement");
            $('#mobileNumber').show();
        }
        for (var i = 0; i < r.data.list.length; i++) {
            guestCount++;
            addGuestDetails(guestCount, r.data.list[i], isReadonly);
        }
        var suppdocname = null;
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
        if (r.data.bookingType == "room") {
            $('#roomWrapper').show();
            $('#dormitoryWrapper').hide();
        } else if (r.data.bookingType == "") {
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
            $("#bookingTypeDtls, #guestMangCtrl, #formSubmissionBtn, #suppDocEdit,#desgDocEdit, #gDocEdit, #formSubmissionBtnForConfrence").hide();
            $("#bookingTypeShowDtls").html(r.data.bookingType == 'room' ? 'Book Room' : 'Book Bed');
            if (!r.data.isArrivalConv) {
                $("#convFrDtls").hide()
            }
            if (!r.data.isDepartueConv) {
                $("#convToDtls").hide()
            }
            if (suppdocname != null) {
                $("#suppDocDtlsBox").show();
                $("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + suppdocname + '</a>');
            }
            if (catgdocname != null) {
                $("#desgDocDtlsBox").show();
                $("#desgDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + catgdocname + '</a>');
            }
            if (gdocname != null) {
                $("#gDocDtlsBox").show();
                $("#gDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>' + gdocname + '</a>');
            }
            if (r.data.arrivalConv === true) {
                $('#arrivalRow').removeClass("disableElement");
                $('#arrTravel').prop('checked', true);
            }
            if (r.data.departueConv=== true) {
                $('#departureRow').removeClass("disableElement");
                $('#deptTravel').prop('checked', true);
            }
        } else {
            $("#bookingTypeDtls,#guestMangCtrl,#formSubmissionBtn,#suppDocEdit,#desgDocEdit,#gDocEdit,#formSubmissionBtnForConfrence").show();
            $("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls,#gDocDtls").hide();
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
    }

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


    var bookingDay = function () {
        var date1 = convertStringToDatetime($('#frmDt').val());
        var date2 = convertStringToDatetime($('#toDt').val());
        var day = daysBetween(date1, date2);
        if (day > 1) {
            swal('The booking period must be one day');
            return false;
        }
        return true;
    }       
    
    $('#cancelDtls').on('click', function () {        
        $('#cancelGuestBooking').modal('show');           
    });
        
    $('#convCancelDtls').on('click', function () {        
        $('#cancelConvBooking').modal('show');           
    });
        
    $('#rejectConvBooking').on('click',function(){
    	var reqId = $('#reqId').val();
        var ghId = $('#ghId').val();
        var blockId = $('#blockId').val();
        var remarksReason = $('#convRemarksReason').val();
    	let postData = {
			bookingId: reqId,
			cityId: ghId,
			ghId: blockId,
			remarks:remarksReason
		}
		$.ajax({
			url: "./admin/saveCancelModeration.do",
			type: "POST",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType: "application/json",
			dataType: "JSON",
			success: function (result) {
				if (result.success) {					
					swal(result.msg);
					$('#cancelConvBooking').modal('hide');
					$('#convAddOrModifyModModal').modal('hide');
					//getDataToGadRequestTable();
					table2.ajax.reload();
					moderationViewTbl.ajax.reload();
				} else {
					swal(result.msg);
				}
			}
		});
    });
    
    $('#rejectBooking').on('click',function(){
    	var reqId = $('#reqId').val();
        var ghId = $('#ghId').val();
        var blockId = $('#blockId').val();
        var remarksReason = $('#remarksReason').val();
    	let postData = {
			bookingId: reqId,
			cityId: ghId,
			ghId: blockId,
			remarks:remarksReason
		}
		$.ajax({
			url: "./admin/saveCancelModeration.do",
			type: "POST",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType: "application/json",
			dataType: "JSON",
			success: function (result) {
				if (result.success) {					
					swal(result.msg);
					$('#cancelGuestBooking').modal('hide');
					$('#addOrModifyModModal').modal('hide');
					//getDataToGadRequestTable();
					table2.ajax.reload();
					moderationViewTbl.ajax.reload();
				} else {
					swal(result.msg);
				}
			}
		});
    });
   
	
	
    $("#saveDtls").on('click', function () {
        submitModeratorDetails(appData, 'ADD');
    });
    $("#modifyDtls").on('click', function () {
        submitModeratorDetails(appData, 'UPDATE');
    });        
    $("#convSaveDtls").on('click', function () {
    	submitConvModeratorDetails(appData, 'ADD');
    });   
    $("#convModifyDtls").on('click', function () {
    	submitConvModeratorDetails(appData, 'UPDATE');
    });
    
    var submitConvModeratorDetails = function (data, type) {
        var jsonData = {
            "type": type,
            "cityId": data.cityId,
            "blockId": data.blockId,
            "bookingId": data.bookingId,
            "catg": data.catg,
            "desg": data.desg,
            "duringstay": $('input[name="convduringstay"][type="radio"]:checked').val(),
            "convayance": $('input[name="convModconveyance"][type="radio"]:checked').val(),
            "roomType": data.roomType,
            "isArrivalConv": data.isArrivalConv,            
            "isDepartueConv": data.isDepartueConv,
            "ext":'0'
        };
        console.log(jsonData);
        $.ajax({
            url: "./admin/addOrModifyModeratorDetails.do",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                $('#loading').hide();
                console.log(data);
                $("#convModModalCloseBtn").click();
                //$("#modModalCloseBtn1").click();
                swal('Successfuly submitted Moderation data');
                //getDataToGadRequestTable();   
				table2.ajax.reload();   
				moderationViewTbl.ajax.reload();          
            }, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                $('#loading').hide();
            }
        });
    }

    var submitModeratorDetails = function (data, type) {
    	var room = data.noOfRooms; 
    	var bed = data.noOfBedsDormitory;
    	if(parseInt(noOfRooms) < 1 && parseInt(noOfBedsDormitory) < 1){
        	swal('You have to add atleast one room or bed');
        	return false;
        }
        /*if(parseInt(room) < parseInt(noOfRooms)){
    		swal('You have entered maximum room than applied room');
    		return false;
    	}
        if(parseInt(bed) < parseInt(noOfBedsDormitory)){
    		swal('You have entered maximum bed than applied bed');
    		return false;
    	}*/
        var jsonData = {
            "type": type,
            "cityId": data.cityId,
            "blockId": blockIdd,
            "bookingId": data.bookingId,
            "noOfRooms": noOfRooms,
            "noOfBedsDormitory": noOfBedsDormitory,
            "catg": data.catg,
            "desg": data.desg,
            "duringstay": $('input[name="duringstay"][type="radio"]:checked').val(),
            "convayance": $('input[name="conveyance"][type="radio"]:checked').val(),
            "roomType": data.roomType,
            "isArrivalConv": data.isArrivalConv,
            "isDepartueConv": data.isDepartueConv,
            "ext":'0'
        };
        $.ajax({
            url: "./admin/addOrModifyModeratorDetails.do",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                $('#loading').hide();
                console.log(data);
                $("#modModalCloseBtn").click();
                $("#modModalCloseBtn1").click();
                swal('Successfuly submitted Moderation data');
                //getDataToGadRequestTable();
				table2.ajax.reload();
				moderationViewTbl.ajax.reload();
            }, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                $('#loading').hide();
            }
        });
    }
    
    /*
       * -------------------------- Copied from createdataentry.js
       * ------------------------------
       */
    var submitBookingDetails = function (type) {
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
        console.log('form valid');
        if (!bookingDay())
            return false;
        var formData = $('#existingBookingApplication').serializeArray();
        var rawGuestDetails = formData.filter(d => d.name.match(/guest/g));
        var otherDetails = formData.filter(d => !d.name.match(/guest/g));
        var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
        console.log("guest details-------" + guestDtls);
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
        console.log("Other details-------" + otherDetails);
        var fd = new FormData();
        fd = convertSerializeArrayToFd(otherDetails);
        fd.append("guestDtlsStr", JSON.stringify(guestDtls));
        fd.append("blockId", $('#gHouse').val());
        fd.append("bookingId", bookingReqId);
        fd.append("noOfDays", $('#noOfDays').val());

        if ($('#mob').val() == "") {
            fd.delete('mob');
            fd.append("mob", $('#gMobile').val());
        }

        fd.delete('bookingFor');
        fd.append("bookingFor", $('#bookingFor').val());

        if (suppDoc != null) fd.append("suppDoc", suppDoc, suppDoc.name);
        if (catgDoc != null) {
            fd.append("catgDoc", catgDoc, catgDoc.name)
        }
        ;
        if (gDoc != null) fd.append("gDoc", gDoc, gDoc.name)

        let adultCount = $('#noOfAdults').val() == "" ? 0 : $('#noOfAdults').val();
        let childCount = $('#noOfChildren').val() == "" ? 0 : $('#noOfChildren').val();
        console.log("Number of adult" + adultCount);
        console.log("Number of child" + childCount);
        console.log("Number of guest details====" + guestDtls)
        if (!validateChildrenAndAdultCount(adultCount, childCount, guestDtls))
            return false;

        let url = addOrUpdateUrl(type);

        console.log(fd);
        $.ajax({
            url: url,
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
                console.log("data of booking details-------" + data);
                $('#loading').hide();
                if (data.status) {
                    $('#editDetails').modal('hide');
                    swal(data.msg);
                    $(this).find('form').trigger('reset');
                    $('#existingBookingApplication').bootstrapValidator('resetForm', true);
                    guestCount = 0;
                    // $('#addGuestDetailsBtn').click();
                    //getDataToGadRequestTable();
					table2.ajax.reload();
					moderationViewTbl.ajax.reload();
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
$('input[name="pay"]').on('change', function () {
		let total = $('#advncAamount').val();
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
            } else {
                $('#onlineAmount').prop('disabled', true);
                $('#onlineAmount').val('');
            }
        }
    });
    
    $("#editDetails").on("hide.bs.modal", function () {
        $(this).find('form').trigger('reset');
        $('#existingBookingApplication').bootstrapValidator('resetForm', true);
        $('#editDetails').find('*').removeClass("disableElement");
        $('#guestDetailsWrapper').html('');
        $('#guestDetailsWrapper1').html('');
        guestCount = 0;
        $("#splitCityGH").hide();
        $("#extSubmitBtn").hide();
        $('#suppDocDtlsBox').hide();
        $('#catgDocDtlsBox').hide();
        $('#gDocDtlsBox').hide();
	   	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	   	$("#formSubmissionBtnForConfrence").hide();
   }); 
    $("#editDetails").on("show.bs.modal",function(){
	   	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
	   	$("#formSubmissionBtnForConfrence").hide();
   }); 
   
});//end of document.ready()

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
    return Math.round(difference_ms / ONE_DAY);
}

function addOrUpdateUrl(type) {
    if ($('#formSubmissionBtn').html() == 'Update Details') {
        return "./admin/receptionistaddBookingDetails/edit/" + type + ".do";
    } else {
        return "./admin/receptionistaddBookingDetails/add/" + type + ".do";
    }
}

function addOrUpdateUrlForConfrence(type) {
    if ($('#formSubmissionBtnForConfrence').html() == 'Update Details') {
        return "./admin/receptionistaddBookingDetailsForConfrence/edit/" + type + ".do";
    } else {
        return "./admin/receptionistaddBookingDetailsForConfrence/add/" + type + ".do";
    }
}
/*var blockIdd = null;

function restrictInput(id) {
    blockIdd = $('#blockId' + id).val();
    noOfRooms = $('#noOfRooms' + id).val();
    noOfBedsDormitory = $('#noOfBedsDormitory' + id).val();
    if(noOfBedsDormitory != '') {
    	$('#noOfRooms' + id).val('');
    	noOfRooms = 0;	
    }
    if(noOfRooms != '') {
    	$('#noOfBedsDormitory' + id).val('');
    	noOfBedsDormitory = 0;
    }
    console.log(blockIdd);
    console.log(noOfRooms);
    console.log(noOfBedsDormitory);
}*/

$('#roomDetails').on('keyup', '.test', function () {
    let value = $(this).val();
    $('.test').val('');
    $(this).val(value);
});

var blockIdd = null;
let noOfRooms = 0;
let noOfBedsDormitory = 0;

function restrictInput(id) {	
	blockIdd = $('#blockId' + id).val();
    noOfRooms = $('#noOfRooms' + id).val();
    noOfBedsDormitory = $('#noOfBedsDormitory' + id).val();   
    console.log('id'+id)
    if(id==1){0
    	console.log('inside 1')    	
    	$('#noOfRooms2').val('')
    	$('#noOfBedsDormitory2').val('');
    	$('#noOfRooms3').val('')
    	$('#noOfBedsDormitory2').val('');
    }  if(id==2){
    	console.log('inside 2')
    	$('#noOfRooms3').val('')
    	$('#noOfBedsDormitory3').val('');
    	$('#noOfRooms1').val('')
    	$('#noOfBedsDormitory1').val('');
    }
     if(id==3){
    	console.log('inside 3')
    	$('#noOfRooms2').val('')
    	$('#noOfBedsDormitory2').val('');
    	$('#noOfRooms1').val('')
    	$('#noOfBedsDormitory1').val('');
    }   
    console.log(blockIdd);
    console.log(noOfRooms);
    console.log(noOfBedsDormitory);
}

var submitBookingDetailsConfrence = function (type) {
	if($("#startTime").val()==""&&$("#timePeriod").val()=="4"){
		swal("Please provide start time");
		 $('#formSubmissionBtnForConfrence').prop("disabled", false);
		return false;
	}
	if($("#frmDtCon").val()==""){
		swal("Please provide from date");
		 $('#formSubmissionBtnForConfrence').prop("disabled", false);
		return false;
		
	}
	if($("#advncAamount").val()==""||$("#advncAamount").val()==0){
		 swal("Please provide Advance Amount");
		 $('#formSubmissionBtnForConfrence').prop("disabled", false);
		return false;
	}
	var fd={
		cityId:$("#city").val(),
		blockId:$("#gHouse").val(),
		name:$("#confrenceGuestName").val(),
		mobileNo:$("#mobileNo").val(),
		address:$("#address").val(),
		fromDate:$("#frmDtCon").val(),
		toDate:$("#toDtCon").val(),
		capacity:$("#capacity").val(),
		categoryType:$("#catg").val(),
		confCatId:$("#hallType").val(),
		confUseId:$("#timePeriod").val(),
		amount:$("#amount").val(),
		strtTime:$("#startTime").val(),
		endTime:$("#endTime").val(),
		advanceAmount:$("#advncAamount").val()
	};
	let url = addOrUpdateUrlForConfrence(type);
        console.log(fd);
       $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(fd),
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
            	$("#frmDtCon").val("");
                console.log("data of booking details-------" + data);
                $('#loading').hide();
          
                if (data.status) {
                    $('#editDetails').modal('hide');
                    swal(data.msg);
                    $(this).find('form').trigger('reset');
                    $('#submitBookingDetailsConfrence').bootstrapValidator('resetForm', true);
                   	$("#startTime").val("");
                    guestCount = 0;
                } else {
                    swal(data.msg);
                }
                $("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
                $("formSubmissionBtnForConfrence").hide();
				$('#editDetails').modal('hide');
            	$('#loading').hide();
            },
            error:function(error,textStatus, jqXHR){
            	$("#forIconChange").html('<input type="checkbox" id="checkAvb" name="checkAvb" onchange="checkAvailbility()" disabled>');
                $("formSubmissionBtnForConfrence").hide();
				$('#editDetails').modal('hide');
            	$('#loading').hide();
            	  swal("Something went wrong try after some time");
            }
        });
}
function getConfrenceHalltype(){
	 let cityId = $('#city').val();
     let blockId = $('#gHouse').val();
      params={
      cId:cityId,
      gId:blockId
      };
 		$.ajax({
		url: './admin/getConfrenceTypeDetails.do',
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(params),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
                $('#loading').hide();
               	console.log(result);
               	$("#hallType").html("");
               	$("#hallType").append(
               	 "<option value=''>---select---</option>"
               	);
               	for(var i=0;i<result.data.length;i++){
               	$("#hallType").append(
               		"<option value="+result.data[i].confCatId+">"+result.data[i].confCatName+"</option>"
               	);
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

function toCapitaLiaze(event){
var str=event.target.value;
const arr = str.split(" ");
for (var i = 0; i < arr.length; i++) {
    arr[i] = arr[i].charAt(0).toUpperCase() + arr[i].slice(1);
}
const str2 = arr.join(" ");
document.getElementById(event.target.id).value=str2;
}
 function checkAvailbility(){
	     var postData={
	     	conUseId:$("#timePeriod").val(),
	     	conCatId:$("#hallType").val(),
	     	formDate:$("#frmDtCon").val(),
	     	startTime:$("#startTime").val(),
	     	endTime:$("#endTime").val(),
	     	gId:$("#gHouse").val()
	     };
	   $("#checkAvb").hide();
	   $("#forIconChange").html('<i class="fa fa-circle-o-notch fa-spin" style="font-size:20px;margin-top:10px;"></i>');
       $.ajax({
            url: "./admin/checkAvailability.do",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
            	console.log(data);
            	if(data=="0"){
            		$("#forIconChange").html('<i class="fa fa-check" style="font-size:20px;margin-top:10px;color:green;"></i>');
            		$("#aviabCheck").html("Conference Hall Available this time");
            		$("#formSubmissionBtnForConfrence").show();
            	}else{
            		$("#forIconChange").html('<i class="fa fa-close" style="font-size:20px;margin-top:10px;color:red;"></i>');
            		$("#aviabCheck").html("Conference Hall Not Available this time");
            		$("#formSubmissionBtnForConfrence").hide();
            	}
            },
            error:function(error, textStatus, jqXHR){
            
            }
         })
}
function onlyNumberKey(evt) {
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
}