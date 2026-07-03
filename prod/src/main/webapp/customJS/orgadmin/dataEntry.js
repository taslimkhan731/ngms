$('document').ready(function(){
	/*$('[data-toggle="popover"]').popover();
	$('#PopoverContent').hide();
	$('#filter-popover').on('click', function(){
		$('#PopoverContent').toggle();
	});*/
	
	 $('#filter-popover').on('click', function () {
         event.stopPropagation();
     });
	var convertingGuesetDetailsObjectToArrayOfObjects=function(data){
		var array=[]; var count = data.length/guestParameters;
		console.log(data.length);
		for (var i=0; i<count; i++){
			array.push(convertSerializeArrayToObject(data.slice((i*guestParameters), ((i+1)*guestParameters))));
		}
		return array;
	}
	
	$('#createDE').on('click', function(){
		window.location="admin/createDataEntry.html";
	});
	
	$('#updateDE').on('click', function(){
		window.location="admin/createDataEntry.html";
	});
	
	var table = $("#dataEntryDataTable").DataTable({  
		"paging":  true,
		"searching": false,
		"scrollX": false,
		"lengthMenu": [[10, 20,30, -1], [10,20,30,"All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": true,
		"bLengthChange" : true,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs":[	
			{
				"className" : "text-center text-nowrap",
				"targets" : "_all"
			},
			{"width": "5%","targets":0,"data": null, 
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{"width": "5%","targets":1,"data":"bookingId"},
			{"width": "10%","targets":2,"data":"fromDate"},
			{"width": "10%","targets":3,"data":"toDate"},
			{"width": "10%","targets":4,"data":"purpose"},
			{"width": "10%","targets":5,"data":"mobile"},
			{"width": "10%","targets":6,"data":"email"},
			{"width": "10%","targets":7,"data":"name"},		
			{"width": "10%","targets":8,"data": null, 		
				render: function (data, type, row, meta) {
						return '<button type="button" id="allot-edit" title="Edit Allotment Details" class="btn btn-edit btn-sm mr-1"><i class="fa fa-edit" aria-hidden="true"></i></button> '+ 
						'<button type="button" id="allot-view" title="View Allotment Details" class="btn btn-view btn-sm mr-1"><i class="fa fa-eye" aria-hidden="true"></i></button> '+ 
						'<button type="button" id="edit-guest" title="Edit Guest Details" class="btn btn-dlt btn-sm"><i class="fa fa-times" aria-hidden="true"></i></button>'
				}
			}
			], 	  
	});	
	
	$('#dataEntryDataTable tbody').on('click', '#allot-edit', function () {
		$('#editDetails').modal('show');
	    var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		console.log(data);
		/*localStorage.setItem('bI', data.bookingId);
		localStorage.setItem('subType', 'UPDATE');
		window.location="admin/createDataEntry.html";*/
	});
	
	$('#dataEntryDataTable tbody').on('click', '#allot-view', function () {
		$('#addOrModifyModModal').modal('show');
		$('#saveDtls').show();
		$('#modifyDtls').hide();
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		getAndSetGuestRoomDetails(data.bookingId);
	});
	
	$('#dataEntryDataTable tbody').on('click', '#edit-guest', function () {
		$('#modifyGuestDtlsModal').modal('show');
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		getAndSetGuestLstDetails(data.bookingId);
	});
	
	var addGuestDetails = function(data){
		console.log(guestCount);
		$('#guestLstDtlsWrapper').append(
			'<tr id=tr' + guestCount + '> '+
			'<th scope="row"><span class="mt-2" id=guestCo' + guestCount +'>'+guestCount+'</span></th> '+
			'<td> '+
			'<div class="row"> '+
			'<input type="hidden" name="guestId" id="guestId' + guestCount + '"> '+
			'<input type="text" name="guestName" id="guestName' + guestCount + '" class="form-control col-12" '+
			'placeholder="Enter name"> '+
			'</div> '+
			'</td> '+
			'<td> '+
			'<div class="row"> '+
			'<input type="number" name="guestAge" id="guestAge' + guestCount + '" class="form-control col-12" '+
			'placeholder="Enter age" autocomplete="off" min="1"> '+
			'</div> '+
			'</td> '+
			'<td> '+
			'<div class="row"> '+
			'<select class="form-control col-12" name="guestGender" id="guestGender' + guestCount + '"> '+
			'<option value=""> --- Select --- </option> '+
			'<option value="M">Male</option> '+
			'<option value="F">Female</option> '+
			'<option value="O">Others</option> '+
			'</select> '+
			'</div> '+
			'</td> '+
			'<td> '+
			'<div class="row"> '+
			'<input type="text" name="guestDesignation" class="form-control col-12" id="guestDesignation' + guestCount + '"'+
			'placeholder="Enter Designation"> '+
			'</div> '+
			'</td> '+
			'<td> '+
			'<div class="row"> '+
			'<input type="text" name="guestDepartment" id="guestDepartment' + guestCount + '" class="form-control col-12" '+
			'placeholder="Enter Department"> '+
			'</div> '+
			'</td> '+
			'<td> '+
			'<div class="row"> '+
			'<input type="text" name="guestAddr" id="guestAddr' + guestCount + '" class="form-control col-12" '+
			'placeholder="Enter address"> '+
			'</div> '+
			'</td> '+
			'</tr>' 
		);
		if(data!=null){
			$("#guestId"+guestCount).val(data.guestId);
			$("#guestName"+guestCount).val(data.guestName);
			$("#guestAge"+guestCount).val(data.guestAge);
			$("#guestGender"+guestCount).val(data.guestGender);
			$("#guestDesignation"+guestCount).val(data.guestDesignation);	
			$("#guestDepartment"+guestCount).val(data.guestDepartment);	
			$("#guestAddr"+guestCount).val(data.guestAddr);	
		}
	};

	$('#addGuestDetailsBtn').on('click', function(){
		guestCount++;
		addGuestDetails(null);
	});
	
	$('#deleteGuestDtlsBtn').on('click', function(){
		if(guestCount>1){
			var gusId = $('#guestLstDtlsWrapper').children().last().children().eq(1).children().children().eq(0).val();
			if(gusId!=''){
				guestDelIds.push(gusId);
			}
			guestCount--;
			$('#guestLstDtlsWrapper').children().last().remove();
		}
	});
	
	var getDataToGadRequestTable = function() {
		 var city=$('#city').val();
		 var gHouse = $('#gHouse').val();
		$.ajax({
			url : './regusr/getAllotmentLstDtlsAccToReq.do',
			type : "GET",
			contentType : "application/json",
			dataType : "JSON",
			data:{
				cId:city,
		        gId:gHouse,
		        reqType: 'AL'
		    },
			success : function(result) {
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
	
	var getCityListDtls = function(){
		$.ajax({
			url: "./admin/getCityDetails.do",
			type: "POST",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$("#city,#city1").html("");
				$("#city,#city1").append('<option value="">Select City</option>');
				data.forEach(option=>{
					$("#city,#city1").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
	
	var getGuestHouseListDtls = function(id){
		$.ajax({
			url: "./admin/getGuestHouseListDetails/" + id + ".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function() {
				$('#loading').show();
			},
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$('#loading').hide();
				$("#gHouse,#gHouse1").html("");
				$("#gHouse").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					$("#gHouse,#gHouse1").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
	
	var getAndSetGuestRoomDetails = function(bookingId){
		$('#loading').show();
		$.ajax({
			url: "./guest/getBookingDetailsByBookingId/"+bookingId+".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {				
				console.log(r);
				$('.clearIdValClass').text('');
				$('.clearIdValClass').val('');
				appData = r.data;
				$('#modBookId').text(r.data.bookingId);
				$('#modRoomType').text(r.data.roomType=='R'?'Room Only':r.data.roomType=='RC'?'Room and Convayance':'');
				$('#modCity').text(r.data.cityName);
				$('#modGH').text(r.data.blockName);
				$('#modFrDt').text(r.data.frmDt);
				$('#modToDt').text(r.data.toDt);
				$('#modMobNo').text(r.data.mob);
				$('#modEmailId').text(r.data.emailId);
				$('#modNor').text(r.data.noOfRooms);
				$('#modNob').text(r.data.noOfBeds);
				$('#modNod').text(r.data.noOfBedsDormitory);
				
				$('#modCatg').text(r.data.bookingId);
				$('#modDesg').text(r.data.bookingId);
				$('#modDurStConv').text(r.data.bookingId);
				$('#modConv').text(r.data.bookingId);
				
				$('#modPurpose').text(r.data.purpName);
				$('#modOffAddr').text(r.data.offAddr);
				$('#modArrModOfTravel').text(r.data.travelModeFr=='t'?'Train':r.data.travelModeFr=='f'?'Flight':'');
				$('#modArrTravelNum').text(r.data.flNoFr);
				$('#modArrDt').text(r.data.arrDt);
				$('#modArrTime').text(r.data.arrTm);
				$('#modArrPlace').text(r.data.arrPla);
				$('#modDepModOfTravel').text(r.data.travelModeTo=='t'?'Train':r.data.travelModeTo=='f'?'Flight':'');
				$('#modDepTravelNum').text(r.data.flNoTo);
				$('#modDepDt').text(r.data.depDt);
				$('#modDepTime').text(r.data.depTm);
				$('#modDepPlace').text(r.data.depPla);
				$('#guestDetailsWrapper').html('');
				r.data.list.forEach((item, index)=>{
					$('#guestDetailsWrapper').append(
						`<tr class="text-center text-nowrap text-font-var resp">
							<td scope="col">${index+1}</td>
							<td scope="col">${item.guestName}</td>
							<td scope="col">${item.guestAge}</td>
							<td scope="col">${item.guestGender=='M'?'Male':'Female'}</td>
							<td scope="col">${item.guestDesignation}</td>
							<td scope="col">${item.guestDepartment}</td>
							<td scope="col">${item.guestAddr}</td>
						</tr>`
					);
				});
				$('#loading').hide();
			},error:function(jqXHR, status, error){
				swal(error);
				$('#loading').hide();
			}
		});
	}
	
	var getAndSetGuestLstDetails = function(bookingId){
		$('#loading').show();
		$.ajax({
			url: "./guest/getBookingDetailsByBookingId/"+bookingId+".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {				
				console.log(r);
				appData = r.data;
				r.data.list.forEach((item, index)=>{
					guestCount++;
					addGuestDetails(item);
				});
				$('#loading').hide();
			},error:function(jqXHR, status, error){
				swal(error);
				$('#loading').hide();
			}
		});
	}
	
	getCityListDtls();
	getDataToGadRequestTable ();
	
	$('#city').on('change', function() {
		var id = $('#city').val();
		getDataToGadRequestTable();
		getGuestHouseListDtls(id);
	});
	
	$("#gHouse").change(function(){
		getDataToGadRequestTable();		
	});
	
	$('#catg').on('change', function() {
		var id = $('#catg').val();
		getDesignationListDtls(id,'');
	});

}); 
