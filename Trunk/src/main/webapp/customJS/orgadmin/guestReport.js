$('document').ready(function() {
	var requestID=null;
	$('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
	
	$("#cancelReportSection").hide();
	
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
    
    $('#city').on('change', function () {
	     $('#guestReportBtn').removeClass("custom-btn-active");
        var id = $('#city').val();
        getGuestHouseListDtls(id);
    });
     
    $('#guestReportBtn').on('click', function () {   
        if(!isCityAndGHSelected())
            return false;
        
        $('#guestReportBtn').addClass("custom-btn-active");
        $('#cancelReportBtn').removeClass("custom-btn-active");
        document.getElementById("headerName").innerHTML = "Guest Report";
        $("#guestReportSection").show();
        $("#cancelReportSection").hide();
       /* $('#resetFiterBtn').trigger('click');*/
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
   				table.ajax.reload();
        }
        
    });

    $('#cancelReportBtn').on('click', function () {    
    	
       if(!isCityAndGHSelected())
            return false;
      /* $('#resetFiterBtn').trigger('click');*/
        $('#guestReportBtn').removeClass("custom-btn-active");
        $('#cancelReportBtn').addClass("custom-btn-active");   
        document.getElementById("headerName").innerHTML = "Cancel Report";
        $("#guestReportSection").hide();
        $("#cancelReportSection").show();
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
        	getCancelGuestReport();
        }
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
    	/*getGuestReport();*/
    	table.ajax.reload();
    	getCancelGuestReport();
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
        table.ajax.reload();
        getCancelGuestReport();
    });
	
	
	$('#miscellaneous').on('click', function() {
		$("#miscellaneousModal").show();
	});
	
	
var table = null;
	table = $("#guestReportDataTable")
	.DataTable(
			{
				
				"paging" : true,
				"searching" : false,
				"scrollX": true,
				"lengthMenu" : [ [ 10, 20, 30, -1 ],
					[ 10, 20, 30, "All" ] ],
					"processing" : false,
					"ordering" : true,
					"info" : false,
					"bFilter" : false,
					"bLengthChange": false,
					"fixedHeader" : {
						header : true,
						footer : true
					},
					"scrollCollapse" : true,
				    "serverSide": true,
				    
				   "ajax": {
				        "url": "./admin/example.do",
				        "type": "POST",
				        "beforeSend": function (xhr) {
				            $('#loading').show();
				            xhr.setRequestHeader(header, token); // Set custom header if needed
				        },
				        "complete": function (jqXHR, status) {
				            // Hide loading indicator when request is complete
				            $('#loading').hide();
				        },
				        "data": function (d) {
				            // Pass additional data to the server
				            d.city = $('#city').val();
				            d.gHouse = $('#gHouse').val();
				            d.bookingId = $('#filterBookingId').val();
				            d.bookingDate = $('#filterBookingDate').val();
				            d.purpose = $('#filterPurpose').val();
				            d.mobNo = $('#filterMobNum').val();
				            d.name = $('#filterGuestName').val();
				            d.category = $('#filterCategory').val();
				            d.type = "all";
				        },
				        // Custom error handling
				        "error": function (jqXHR, textStatus, errorThrown) {
				            // Hide the loading indicator
				            $('#loading').hide();
				            if (jqXHR.status === 400) {
				                // If status 400 (Bad Request), show custom Swal error
				                swal("Invalid Request");
				            } 
				        }
				    },
					"columnDefs" : [
						{
							"targets" : 0,
							"data" : null,
							render : function(data,
									type, row, meta) {
								return meta.row
								+ meta.settings._iDisplayStart
								+ 1;
							}
						}, {
							"targets" : 1,
							"data" : "bookingId"
						}, {
							"targets" : 2,
							"data" : "fromDate",
							
						},{
							"targets" : 3,
							"data" : "toDate"
						}, {
							"targets" : 4,
							"data" : "name"
						},{
							"targets" : 5,
							"data" : "mobile"
						}, {
							"targets" : 6,
							"data" : "typeOfRoom"
						}, {
							"targets" : 7,
							"data" : "catgId"
						}, {
							"targets" : 8,
							"data" : "purpose"
						}, {
							"targets" : 9,
							"data" : "bookingDt"
						},{
							"targets" : 10,
							"data" : "statusDesc"
						},{
							"targets" : 11,
							"data" : null,
							render : function(data,
									type, row, meta) {
								// if(data.status ==
								// 'REQ'){
								return '<button type="button" id="view" action="View" title="view" class="btn btn-sm btn-view mr-1"><i class="fa fa-eye" aria-hidden="true"></i></button> '
								/*
								 * + '<button type="button" id="payment"
								 * action="View" title="payment" class="btn
								 * btn-sm btn-edit mr-1"><i class="fa fa-inr"
								 * aria-hidden="true"></i></button> '+ '<button
								 * type="button" id="cancel" action="View"
								 * title="cancel" class="btn btn-sm btn-dlt
								 * mr-1"><i class="fa fa-times"
								 * aria-hidden="true"></i></button> '
								 */

							}

						}],
			});
	var table2 = null;
	table2 = $("#cancelGuestReportDataTable")
	.DataTable(
			{
				"paging" : true,
				"searching" : true,
				"scrollX": true,
				
				"lengthMenu" : [ [ 10, 20, 30, -1 ],
					[ 10, 20, 30, "All" ] ],
					"processing" : false,
					"ordering" : true,
					"info" : false,
					"bFilter" : false,
					"bLengthChange": false,
					"fixedHeader" : {
						header : true,
						footer : true
					},
					"scrollCollapse" : true,
					"columnDefs" : [
						{
							"targets" : 0,
							"data" : null,
							render : function(data,
									type, row, meta) {
								return meta.row
								+ meta.settings._iDisplayStart
								+ 1;
							}
						}, {
							"targets" : 1,
							"data" : "bookingId"
						}, {
							"targets" : 2,
							"data" : "fromDate",
							
						},{
							"targets" : 3,
							"data" : "toDate"
						}, {
							"targets" : 4,
							"data" : "name"
						},{
							"targets" : 5,
							"data" : "mobile"
						}, {
							"targets" : 6,
							"data" : "typeOfRoom"
						},  {
							"targets" : 7,
							"data" : "modeOfCancelDesc"
						},{
							"targets" : 8,
							"data" : "category"
						}, {
							"targets" : 9,
							"data" : "purpose"
						}, {
							"targets" : 10,
							"data" : "bookingDt"
						},{
							"targets" : 11,
							"data" : "statusDesc"
						},{
							"targets" : 12,
							"data" : null,
							render : function(data,
									type, row, meta) {
								
								return '<button type="button" id="view" action="View" title="view" class="btn btn-sm btn-view mr-1"><i class="fa fa-eye" aria-hidden="true"></i></button> '
								/*
								 * + '<button type="button" id="payment"
								 * action="View" title="payment" class="btn
								 * btn-sm btn-edit mr-1"><i class="fa fa-inr"
								 * aria-hidden="true"></i></button> '+ '<button
								 * type="button" id="cancel" action="View"
								 * title="cancel" class="btn btn-sm btn-dlt
								 * mr-1"><i class="fa fa-times"
								 * aria-hidden="true"></i></button> '
								 */

							}

						}],
			});
	
	$('#guestReportDataTable tbody').on('click', '#view', function () {
		$('#allotmentBlockForFullCan').hide();
		$('#remarkSection').hide();
		 var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		 console.log(data)
		 typeOfRoom = data.typeOfRoom;
	        if(typeOfRoom == 'Conveyance'){        	
	        	$('#convAddOrModifyModModal').modal('show');
	            getAndSetGuestConveyanceDetails(data.bookingId);
	        }else{
	        	if(typeOfRoom == 'Room with Conveyance'){
	        		$("#conveyanceBlock").show();
	        	} else {
	        		$("#conveyanceBlock").hide();
	        	}
	        	$('#addOrModifyModModal').modal('show');
	            getAndSetGuestRoomDetails(data.bookingId,'ADD');
	        }       
    });
	$('#gHouse').on('change', function () {
     	 table.clear();
         table.rows.add("").draw();
	     $('#guestReportBtn').removeClass("custom-btn-active");
    });
	$('#cancelGuestReportDataTable tbody').on('click', '#view', function () {
		$('#allotmentBlock').hide();
		 var data = table2.row($(this).closest('tr')).data() != undefined ? table2.row($(this).closest('tr')).data() : table2.row($(this)).data();
		 console.log(data)
		 var modeOfCancel = data.modeOfCancel;
		 var typeOfRoom = data.typeOfRoom;
		
		 if(typeOfRoom == 'Room with Conveyance' || typeOfRoom == 'Conveyance'){
     		$("#conveyanceBlock").show();
     	 } else {
     		$("#conveyanceBlock").hide();
     	 }
	        if(modeOfCancel == 'MODCAN'){        	
	        	/* $('#allotmentBlock').hide(); */
	        	$('#allotmentBlockForFullCan').hide();
	        	$('#remarkSection').show();
	        	$('#remark').val(data.remarks);  
	        	getAndSetGuestRoomStatusDetails(data.bookingId,'ADD');
	        }
	        if(modeOfCancel == 'FULL'){        	
	        	/* $('#allotmentBlock').hide(); */
	        	$('#allotmentBlockForFullCan').show();
	        	$('#remarkSection').hide();  	
	      	
	        	getAndSetGuestRoomStatusDetails(data.bookingId,'ADD');
	        }
	        
	        if(modeOfCancel == 'PARTIAL'){        	
	        	/* $('#allotmentBlock').hide(); */
	        	$('#allotmentBlockForFullCan').show();
	        	$('#remarkSection').hide();  	
	        
	        	getAndSetGuestRoomStatusDetails(data.bookingId,'ADD');
	        }
	        
	        $("#addOrModifyModModal").modal('show');
	        /*
			 * else{ if(typeOfRoom == 'Room with Conveyance'){
			 * $("#conveyanceBlock").show(); } else {
			 * $("#conveyanceBlock").hide(); }
			 * $('#addOrModifyModModal').modal('show');
			 * getAndSetGuestRoomDetails(data.bookingId,'ADD'); }
			 */
   });
	
    var getAndSetGuestRoomStatusDetails = function (bookingId,type) {
    	
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
                    $('#modNob').text(r.data.noOfBedsDormitory);
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
                    r.data.ghList.forEach((item, index) => {
                        $('#count').val(r.data.ghList.length);
                        $('#roomDetails').append(
                            `<tr class="text-center text-nowrap resp">
								<input type="hidden" id="blockId${index + 1}"  name="blockId${index + 1}" value="${item.id}">
								<input type="hidden" id="blockId"  name="blockId" value="${r.data.blockId}">
								<input type="hidden" id="ghId"  name="cityId" value="${r.data.cityId}">
								<input type="hidden" id="reqId"  name="bookingId" value="${r.data.bookingId}">
								<td scope="col">${item.name}</td>
								<td scope="col"><input type="number" min="0" placeholder="0" id="noOfRooms${index + 1}"  name="noOfRooms${index + 1}" class="test" value="" autocomplete="off" onkeyup="return restrictInput(${index + 1});"></td>
								<td scope="col"><input type="number" min="0" placeholder="0" id="noOfBedsDormitory${index + 1}" name="noOfBedsDormitory${index + 1}" class="test" autocomplete="off" value="" onkeyup="return restrictInput(${index + 1});"></td>								
							</tr>`
                        );
                    });
                    if(type == 'UPDATE') {
                    	if(r.data.cityId == '01'){
                        	if(r.data.blockId == '01'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '01'){
                        	if(r.data.blockId == '02'){
                        		$('#roomDetails #noOfBedsDormitory2').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms2').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '01'){
                        	if(r.data.blockId == '03'){
                        		$('#roomDetails #noOfBedsDormitory3').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms3').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '02'){
                        	if(r.data.blockId == '04'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '03'){
                        	if(r.data.blockId == '05'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '04'){
                        	if(r.data.blockId == '06'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
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
                    if (r.data.status == "PRO" || r.data.status == "CIN" || r.data.status == "COUT" || r.data.status == "BILL" || r.data.status == "RECT" || r.data.status == "CAN") {
                      /* $('#allotmentBlockForFullCan').show(); */
                      $('#roomBedStatusAllotment tbody').empty();
                      let bedStr = '';
                      r.data.partialCancelRoomDetails.forEach(function (drm) {
                    	  
                    	   bedStr += '<tr>' +
                           '<td class="roomDormfont text-truncate text-center">' + drm.fromDate + '</td>' +
                           '<td class="text-truncate text-center">';
                       if(drm.roomNo == null || drm.roomNo == '') {
                           bedStr += '<input type="text" class="text-center" placeholder="Bed No" name="bedNo" value="' + drm.bedNo + '" disabled>';
                       } else {
                           bedStr += '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>';
                       }
                       bedStr += '</td> ' +
                           '<td class="text-truncate text-center">' +
                           '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' ;
                       if(drm.type == 'CIN' ) {
                    	   bedStr += '</td> ' +
                    	   '<td class="text-truncate text-center"> <input type="text" class="text-center" placeholder="Rent" name="status" value = "CHECK-IN" disabled>'+
                           '</td> ' +
                           '</tr>';        
                       } else if (drm.type == 'CAN'){
                    	   bedStr += '</td> ' +
                    	   '<td class="text-truncate text-center"> <input type="text" class="text-center" placeholder="Rent" name="status" value = "CANCELLED" disabled>'+
                           '</td> ' +
                           '</tr>';        
                       } else {
                    	   bedStr += '</td> ' +
                    	   '<td class="text-truncate text-center"> <input type="text" class="text-center" placeholder="Rent" name="status" value = "' + drm.type + '" disabled>'+
                           '</td> ' +
                           '</tr>';    
                       }
                                                            
                      });
                      $('#roomBedStatusAllotment tbody').append(bedStr);
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
	
	$('#guestReportDataTable tbody').on('click', '#bill', function () {
        var data = table.row( $(this).parents('tr') ).data();
         $('#billModal').modal('show');
    });
	
	$('#guestReportDataTable tbody').on('click', '#payment', function () {
        var data = table.row( $(this).parents('tr') ).data();
         $('#paymentModal').modal('show');
    });
	
	$('#guestReportDataTable tbody').on('click', '#cancel', function () {
        var data = table.row( $(this).parents('tr') ).data();
        confirm("Do you want to cancel?");
    });
	
    var getGuestHouseListDtls = function (id) {
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
            }, error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }
    
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
                        $('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                    } else {
                        $('input[name="convduringstay"][type="radio"][value="' + r.data.duringstay + '"]').prop('checked', true);
                        $('#convModYesDuringStay').hide();
                    }
                    var convay = String((r.data.isArrivalConv || r.data.isDepartueConv) ? 1 : 0);
                    if (convay == 1) {                    	
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
                        $('input[name="convModconveyance"][type="radio"][value="' + convay + '"]').prop('checked', true);
                    } else {
                        $('#convModArrivalDetails').html("No Coveyance Requested");
                        $('#convModDepartureDetails').html("No Coveyance Requested");
                        $('input[name="convModconveyance"][type="radio"][value="' + convay + '"]').prop('checked', true);
                        $('#convModYesconve').hide();
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
                    }
                   
                    if (suppdocname != null) {
                        $("#convSuppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    };
                    if (catgdocname != null) {
                        $("#convCatDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    };
                    if (gdocname != null) {
                        $("#convGuestDocDtls").html('<a href="./imagedownload/guestDoc_' + bookingReqId + '.do" target="_blank" class="download-btn">View</a>');
                    }
                    else{
                        $('#allotmentBlock').hide();
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
    
    var getAndSetGuestRoomDetails = function (bookingId,type) {
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
                    $('#modNob').text(r.data.noOfBedsDormitory);
                    $('#modPurpose').text(r.data.purpName);
                    $('#modOffAddr').text(r.data.offAddr);
                    $('#modDepPlace').text(r.data.depPla);  
                    if(r.data.bookingId.startsWith("P")){
                    	$("#profileDoc").html('<a href="./imagedownload/user_' + r.data.userId + '.do" target="_blank" class="download-btn">View</a>');
                    }
/*
 * $("#profileDoc").html('<a href="./imagedownload/user_' + r.data.userId +
 * '.do" target="_blank" class="download-btn">Download</a>');
 */
                  
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
                    r.data.ghList.forEach((item, index) => {
                        $('#count').val(r.data.ghList.length);
                        $('#roomDetails').append(
                            `<tr class="text-center text-nowrap resp">
								<input type="hidden" id="blockId${index + 1}"  name="blockId${index + 1}" value="${item.id}">
								<input type="hidden" id="blockId"  name="blockId" value="${r.data.blockId}">
								<input type="hidden" id="ghId"  name="cityId" value="${r.data.cityId}">
								<input type="hidden" id="reqId"  name="bookingId" value="${r.data.bookingId}">
								<td scope="col">${item.name}</td>
								<td scope="col"><input type="number" min="0" placeholder="0" id="noOfRooms${index + 1}"  name="noOfRooms${index + 1}" class="test" value="" autocomplete="off" onkeyup="return restrictInput(${index + 1});"></td>
								<td scope="col"><input type="number" min="0" placeholder="0" id="noOfBedsDormitory${index + 1}" name="noOfBedsDormitory${index + 1}" class="test" autocomplete="off" value="" onkeyup="return restrictInput(${index + 1});"></td>								
							</tr>`
                        );
                    });
                    if(type == 'UPDATE') {
                    	if(r.data.cityId == '01'){
                        	if(r.data.blockId == '01'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '01'){
                        	if(r.data.blockId == '02'){
                        		$('#roomDetails #noOfBedsDormitory2').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms2').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '01'){
                        	if(r.data.blockId == '03'){
                        		$('#roomDetails #noOfBedsDormitory3').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms3').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '02'){
                        	if(r.data.blockId == '04'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '03'){
                        	if(r.data.blockId == '05'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
                        if(r.data.cityId == '04'){
                        	if(r.data.blockId == '06'){
                        		$('#roomDetails #noOfBedsDormitory1').val(r.data.noOfBedsDormitory);
                        		$('#roomDetails #noOfRooms1').val(r.data.noOfRooms);
                        	}
                        }
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
                    if (r.data.status == "PRO" || r.data.status == "CIN" || r.data.status == "COUT" ||
						r.data.status == "BILL" || r.data.status == "RECT" || r.data.status == "ALO" ||
						r.data.status == "PEXT" || r.data.status == "EXT") {
                      $('#allotmentBlock').show();
                      $('#roomBedAllotment tbody').empty();
                      let bedStr = '';
                      r.data.roomDetails.forEach(function (drm) {
                          bedStr += '<tr>' +
                              '<td class="roomDormfont text-truncate text-center">' + drm.fromDate + '</td>' +
                              '<td class="text-truncate text-center">';
                          if(drm.roomNo == null || drm.roomNo == '') {
                              bedStr += '<input type="text" class="text-center" placeholder="Bed No" name="bedNo" value="' + drm.bedNo + '" disabled>';
                          } else {
                              bedStr += '<input type="text" class="text-center" placeholder="Room No" name="roomNo" value="' + drm.roomNo + '" disabled>';
                          }
                          bedStr += '</td> ' +
                              '<td class="text-truncate text-center">' +
                              '<input type="text" class="text-center" placeholder="Rent" name="rent" value = "' + drm.roomRent + '" disabled>' +
                              '</td> ' +
                              '</tr>';
                           
                      });
                      $('#roomBedAllotment tbody').append(bedStr);
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
   
    
  /*  var getGuestReport = function () {
    	var city = $('#city').val();
        var gHouse = $('#gHouse').val();
    	let bookingId = $('#filterBookingId').val();
        let bookingDate = $('#filterBookingDate').val();
        let purpose = $('#filterPurpose').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
        let category = $('#filterCategory').val();
        let postData = {  
        	gId:gHouse,
        	cId:city,
            bookingId: bookingId,
            bookingDate: bookingDate,
            category:category,
            purpose:purpose,
            mobNo:mobNo,
            name:name,
            reqType: 'AL'
        };

        $.ajax({
            url: './admin/getAllGuestDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
            	 $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
            	 $('#loading').hide();
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
                $('#loading').hide();
            }
        });
    }
    getGuestReport();*/
    
    var getCancelGuestReport = function () {
    	var city = $('#city').val();
        var gHouse = $('#gHouse').val();
    	let bookingId = $('#filterBookingId').val();
        let bookingDate = $('#filterBookingDate').val();
        let purpose = $('#filterPurpose').val();
        let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
        let category = $('#filterCategory').val();
        let postData = {  
        	gId:gHouse,
        	cId:city,
            bookingId: bookingId,
            bookingDate: bookingDate,
            category:category,
            purpose:purpose,
            mobNo:mobNo,
            name:name,
            reqType: 'AL'
        };

        $.ajax({
            url: './admin/getAllGuestCancelReport.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
            	 $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
            	 $('#loading').hide();
                table2.clear();
                table2.rows.add(result.data).draw();
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
    $('#printPDF,#printPDFForCon').click(function(){
		console.log("Inside print btn click");
		console.log(bookingReqId+" Booking ID  ");
		window.open('./admin/downloadModListInPDF/'+bookingReqId+'.html','_blank');
	})
});