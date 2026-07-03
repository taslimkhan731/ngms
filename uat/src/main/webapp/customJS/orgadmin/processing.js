$('document').ready(function () {
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });

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
    $("#draftAndConfirmedSection").hide();
    $("#btnSection").hide();

    $("#draftListModal").on("hidden.bs.modal", function () {
        $("#formPassword")[0].reset();
        var validator = $("#formPassword").validate();
        validator.resetForm();
    });

    let emptyList = true;

    $("#filterBookingDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
        // format: 'YYYY-MM-DD'
    });
    $("#viewApproveModal").on("hidden.bs.modal",function(){
   		$("#yesDuringStay").show();
   });
    $('#approvedList').on('click', function () {
        if(!isCityAndGHSelected())
            return false;
        document.getElementById("headerName").innerHTML = "Approve List";
        $('#approvedList').addClass('custom-btn-active');
        $('#draftList').removeClass('custom-btn-active');
        $('#confirmedList').removeClass('custom-btn-active');
        $("#draftAndConfirmedSection").hide();
        $("#confirmDetailsList").hide();
        $("#draftDetailsList").hide();
        $("#ApproveSection").show();
        $("#btnSection").show();
        getallProcesslist();
        getAllCancelList();
        getAllModifiedList();
        getAllCoveyanceList();
        getAllConferenceList();
    });

    $('#draftList').on('click', function () {
    	if(!isCityAndGHSelected())
            return false;
        document.getElementById("headerName").innerHTML = "Draft List";
        $('#draftList').addClass('custom-btn-active');
        $('#approvedList').removeClass('custom-btn-active');
        $('#confirmedList').removeClass('custom-btn-active');
        $("#draftAndConfirmedSection").show();
        $("#confirmDetailsList").hide();
        $("#confirmListDataTable").hide();
        $("#ApproveSection").hide();
        $("#draftDetailsList").show();
        $("#draftListDataTable").show();

        getallDraftlist();
    });

    $('#confirmedList').on('click', function () {
        document.getElementById("headerName").innerHTML = "Allotment List";
        $('#confirmedList').addClass('custom-btn-active');
        $('#approvedList').removeClass('custom-btn-active');
        $('#draftList').removeClass('custom-btn-active');
        $("#draftAndConfirmedSection").show();
        $("#ApproveSection").hide();
        $("#draftDetailsList").hide();
        $("#draftListDataTable").hide();

        $("#confirmDetailsList").show();
        $("#confirmListDataTable").show();

        getAllConfirmList();

    });

    $('#draftListDataTable tbody').on('click', '#view', function () {
        var data = table1.row($(this).parents('tr')).data();
        processId = data.processId;
        console.log(data);
        getAllDetailsByProcessid(processId, "draft");
        $('#viewModal').modal('show');
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
        	"width":"10%","targets": 1, "data": "guestName"
        }, {
        	"width":"10%","targets": 2, "data": "periodOfStay"
        }, {
        	"width":"10%", "targets": 3, "data": "roomNo"
        }, {
        	"width":"10%","targets": 4, "data": "duringStayConveynce"
        }, {
        	"width":"10%", "targets": 5, "data": "conveynceFromDatails",
        },{
        	"width":"10%","targets": 6, "data": "conveynceToDetails",
        },{
        	"width":"10%","targets": 7, "data": "rent",
        }],
    });
    
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
            "targets": 1, "data": "name"
        }, {
            "targets": 2, "data": "periodOfStay"
        }, {
            "targets": 3, "data": "roomNo"
        }, {
            "targets": 4, "data": "modeOfCancel"
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
            "targets": 2, "data": "periodOfStay"
        }, {
            "targets": 3, "data": "prevOrderNo"
        },
        {
            "targets": 4, "data": "prevOrderDate"
        },{
            "targets": 5, "data": "reason"
        }, {
            "targets": 6, "data": "modifiedRoomNo",
        },{
            "targets": 7, "data": "modifiedRoomRent",
        },{
            "targets": 8, "data": "roomNo",
        },{
            "targets": 9, "data": "roomRent",
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
            "targets": 1, "data": "guestName"
        }, {
            "targets": 2, "data": "duringStayConveynce"
        },
        {
            "targets": 3, "data": "conveynceFromDatails"
        },
         {
            "targets": 4, "data": "conveynceToDetails"
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
        	"targets":7,"data":"totalAmt"
        }],
    });
    var getAllDetailsByProcessid = function (id, type) {
    	console.log("gtttt");
        $.ajax({
            url: "./admin/getAllDetailsByProcessidforOfficeOrder/" + id + "/" + type +".do",
            type: "GET",       
            success: function (data) {
                console.log(data);
                var countForConfirm = data.data.guestDetails.length;
                var countForCancel = data.data.canList.length;
                var countForMod = data.data.modList.length;
                var countForConveyance = data.data.conveyanceList.length;
                var countForConference = data.data.conferenceList.length;
                $('#conferenceListDiv').hide();
                if ( countForConfirm == 0 ) {
			    	$('#overflow_div').hide();
			    }else{
			    	$('#overflow_div').show();
			    	table3.clear();
			    	table3.rows.add(data.data.guestDetails).draw();
			    }
			    if ( countForCancel == 0 ) {
			    	$('#cancelLstDiv').hide();
			    }else{
			    	$('#cancelLstDiv').show();
			    	table4.clear();
			    	table4.rows.add(data.data.canList).draw();
			    }
			    if ( countForMod == 0 ) {
			    	$('#modifiedLstDiv').hide();
			    }else{
			    	$('#modifiedLstDiv').show();
			    	 table5.clear();
		             table5.rows.add(data.data.modList).draw();
			    }
			    if ( countForConveyance == 0 ) {
			    	$('#conveyance_list_div').hide();
			    }else{
			    	$('#conveyance_list_div').show();
			    	conTable.clear();
			    	conTable.rows.add(data.data.conveyanceList).draw();
			    }
			    
			    if ( countForConference === 0 ) {
			    	console.log("countForConference "+countForConference);
			    	$('#conferenceListDiv').hide();
			    }else{
			    	$('#conferenceListDiv').show();
			    	conferenceTbl.clear();
			    	conferenceTbl.rows.add(data.data.conferenceList).draw();
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
    
    $('#modifiedListDatatable tbody').on('click', '#viewModifiedListData', function () {
        var data = table7.row($(this).parents('tr')).data();       
        $('#viewApproveModal').modal('show');
        $("#allotmentBlock").hide();
        $("#fullCanBlock").hide();
        getAndSetGuestViewDetails(data.bookingId);
    });
    
    $('#cancelListDataTable tbody').on('click', '#viewCancelListData', function () {
        var data = table8.row($(this).parents('tr')).data();
        $('#viewApproveModal').modal('show');
        $("#modifiedBlock").hide();
        $("#allotmentBlock").hide();
        $("#fullCanBlock").show();
        getAndSetGuestViewDetails(data.bookingId);
    });
    
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
                swal(error);
                $('#loading').hide();
            }
        });
    }
    
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
	                    $('#roomBedAllotment tbody').empty();
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
	                    $('#roomBedAllotment tbody').append(bedStr);
                    }
                    if ((r.data.status == "PRO" || r.data.status == "CIN") && r.data.modifiedDetails!=0) {
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
                    }else{
                    	$('#modifiedBlock').hide();
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
        "columnDefs": [{
        	"width":"5%","targets": 0, "data": null, 'render': function (data, type, full, meta) {
                return `<input type="checkbox" class="checkBoxMod" value='${data.bookingId}'>`;
            }
        }, {
            "width":"5%","targets": 1, "data": "bookingId"
        },{
            "width":"5%","targets": 2, "data": "name"
        }, {
        	"width":"10%", "targets": 3, "data": "reqDate"
        }, {
        	"width":"30%","targets": 4, "data": "frmDt"
        },{
        	"width":"30%","targets": 5, "data": "toDt"
        },{
        	"width":"10%","targets": 6, "data": "quotatype"
        }, {
        	"width":"10%", "targets": 7, "data": null, render: function (data, type, row, meta) {
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
        }, {
            "width": "5%", "targets": 2, "data": "name"
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
        }, {
            "width": "10%", "targets": 2, "data": "name"
        }, {
            "width": "10%", "targets": 3, "data": "bookingDt"
        }, {
            "width": "30%", "targets": 4, "data": "fromDate"
        }, {
            "width": "30%", "targets": 5, "data": "toDate"
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
        }, {
            "width": "5%", "targets": 2, "data": "name"
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
            "width": "5%", "targets": 2, "data": "noOfChildren"
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
                console.log(data);
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

    var getAllCoveyanceList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/getAllConveyanceDataForApprove.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
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
            url: './admin/getAllCancelDataForApprove.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
                if(result.data.length > 0) {
                    emptyList = false;
                }
                table8.clear();
                table8.rows.add(result.data).draw();
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

    var getAllModifiedList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        var postData = {
            cId: city, gId: gHouse
        }
        $.ajax({
            url: './admin/getAllModifiedDataForApprove.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
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

    var table2 = null;
    var table2 = $("#confirmListDataTable").DataTable({
        "paging": true,
        "searching": true, // "scrollX": true,
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
            "targets": 1, "data": "processId"
        }, {
            "targets": 2, "data": "createdBy"
        }, {
            "targets": 3, "data": "createdDate"
        }, {
            "targets": 4, "data": null, render: function (data, type, row, meta) {
                // if(data.status ==
                // 'REQ'){
                return '<button type="button" id="view" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> '
                // }else{
            }
        }
        ],
    });

    $('#confirmListDataTable tbody').on('click', '#view', function () {
        var data = table2.row($(this).parents('tr')).data();
        processId = data.processId;
        console.log(data);
        getAllDetailsByProcessid(processId, "confirm");
        $('#viewModal').modal('show');
    });

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
    // getCityListDtls ();

    var getallProcesslist = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/allProcessApplicationList.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
                console.log(data);
                if(data.data.length > 0) {
                    emptyList = false;
                }
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

    $('#process').on('click', function () {
        if (!emptyList) {
            saveallProcesslist();
        } else {
            swal("No data to Approve");
            return false;
        }
    });

    let selected = "";

    $("#checkAllModList").click(function () {
        $(".checkBoxMod").prop('checked', $(this).prop('checked'));
        if ($('#checkAllModList').is(':checked')) {
            selected = "ALL";
        } else selected = "";
        console.log(selected);
    });

    $("#modListDataTable tbody").on("change", ".checkBoxMod", function () {
        selected = $(".checkBoxMod:checked").map(function () {
            return this.value;
        }).get().join("', '");
        console.log(selected);
    });

    var saveallProcesslist = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        var sign = $('input[name=gosignature]:checked').val();
        
        var table = $('#modListDataTable').DataTable();
    	if (table.data().any()) {
    	    if (selected.trim() == "") {
                swal("Please approve atlease one item");
                return false;
            }
    	}
        
        console.log(sign);
        console.log(city);
        console.log(gHouse);
        $('#loading').show();
        $.ajax({
            url: './admin/saveProcessApplicationList.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityId: city, guesthouseId: gHouse, sign: sign, selected: selected
            },
            success: function (data) {
                $('#loading').hide();
                console.log(data);
                swal(data.msg);
                emptyList = true;
                getallProcesslist();
                getAllCancelList();
                getAllModifiedList();
                getAllCoveyanceList();
                getAllConferenceList();
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

    var getallDraftlist = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/allDraftApplicationList.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
                console.log(data);
                table1.clear();
                table1.rows.add(data.data).draw();
            },
            error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
        });

    }

    var getAllConfirmList = function () {
        var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        console.log(city);
        console.log(gHouse);
        $.ajax({
            url: './admin/getconfirmDraftlist.do',
            type: "GET",
            contentType: "application/json",
            dataType: "JSON",
            data: {
                cityName: city, guesthouseName: gHouse
            },
            success: function (data) {
                console.log(data);
                table2.clear();
                table2.rows.add(data.data).draw();
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

    $("#city").change(function () {
        var id = $('#city').val();
        getGuestHouseListDtls(id);
    });
    $("#gHouse").change(function () {
        $("#btnSection").hide();
        table1.clear().draw();
        table2.clear().draw();
        table6.clear().draw();
        table7.clear().draw();
        table8.clear().draw();
        table9.clear().draw();
    });

    let processId = "";
    $('#draftListDataTable tbody').on('click', '#accept', function () {
        var data = table1.row($(this).parents('tr')).data();
        processId = data.processId;
        console.log(data);
        getGuestDetailsByProcessid(processId);
        $('#acceptModal').modal('show');
    });

    var getGuestDetailsByProcessid = function (id) {
        $.ajax({
            url: "./admin/getDetailsByprocessId/" + id + ".do", type: "GET",

            success: function (result) {
                console.log(result);
                $("#card-body").html("");
                $("#card-body").append('<tr>' + '<th> ProcessId :</th>' + '<td >' + result.data.processId + '</td>' + '</tr>' +
                    '<tr>' + '<th> CreateBy :</th>' + '<td >' + result.data.createdBy + '</td>' + '</tr>' +
                    '<tr>' + '<th> CreatedDate :</th>' + '<td >' + result.data.createdDate + '</td>' + '</tr>');
            },
            error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }

    $('#draftListDataTable tbody').on('click', '#cancel', function () {
        var data = table1.row($(this).closest('tr')).data() != undefined ? table1.row($(this).closest('tr')).data() : table1.row($(this)).data();
        swal({
            title: "Are you sure? ",
            text: "Are you sure to cancel!",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Yes, cancel it!",
            closeOnConfirm: false
        }, function () {
            $('#loading').show();
            $.ajax({
                url: './admin/cancelProcessRequest/' + data.processId + ".do",
                type: "GET",
                headers: {"X-CSRF-TOKEN": token},
                success: function (result) {
                    $('#loading').hide();
                    if (result.success) {
                        swal(result.msg);
                        getallDraftlist();
                    } else {
                        swal(result.msg);
                    }
                }
            });
        });
    })

    $('#confirm').on('click', function () {
        $('#loading').show();
        $.ajax({
            url: './admin/confirmProcess/' + processId + ".do",
            type: "GET",
            headers: {"X-CSRF-TOKEN": token},
            success: function (result) {
                $('#loading').hide();
                if (result.status) {
                    swal(result.msg);
                    $('#acceptModal').modal('hide');
                    getallDraftlist();
                } else {
                    swal(result.msg);
                }
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
    
	$('#printPDF').click(function(){
		type = "draft";
		console.log(processId+" and "+type);
			window.open('./admin/downloadApproverOrderListInPDF/'+processId+ '/' + type +'.html','_blank');
		})
		
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
});