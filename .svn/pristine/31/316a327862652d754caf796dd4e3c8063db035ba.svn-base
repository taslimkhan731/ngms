$('document').ready(function(){
	/*Validation ends*/

	$('#createDE').on('click', function(){
		window.location="admin/createDataEntry.html";
	});
	
	$('#updateDE').on('click', function(){
		window.location="admin/createDataEntry.html";
	});
	$('#dataEntryDataTable tbody').on('click', '#edit', function () {
		$('#applicationFormModal1').modal('show');
		//var data=table.row( $(this).parents('tr') ).data();
	    var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		console.log(data);
		guestCount=0;
		console.log(data.bookingId);
		$('#guestDetailsWrapper').html("");
		$('#applicationHeader').html('Application Form Update');
     	setGuestDetails(data.bookingId, false);	
		
		
	});
	var table = $("#dataEntryDataTable").DataTable({  
		"paging":  true,
		"searching": true,
		"scrollX": true,
		"lengthMenu": [[10, 20,30, -1], [10,20,30,"All"]],
		"processing": false,
		"ordering": true,
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
				"className" : "dt-center",
				"targets" : "_all"
			},
			{"targets":0,"data": null, 
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{"targets":1,"data":"bookingId"},
			{"targets":2,"data":"fromDate"},
			{"targets":3,"data":"toDate"},
			{"targets":4,"data":"purpose"},
			{"targets":5,"data":"category"},
			{"targets":6,"data":"mobile"},
			{"targets":7,"data":"email"},
			{"targets":8,"data":"name"},		
			{"targets":9,"data": null, 
				render: function (data, type, row, meta) {
					
						return '<button type="button" id="edit" title="Edit" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> ' 
//						'<button type="button" id="receipt" action="Receipt" title="Receipt" class="btn btn-secondary btn-sm btn-view" ><i class="fa fa-print" aria-hidden="true"></i>recipt</button>'
					
				}
			}
			], 	  
	});	


	$('#office').hide();
	$('#supporting').hide();
	$('#convenceFrom').hide();
	$('#convenceTo').hide();
	$('#desgHidden').hide();
	$('#noOfRooms').val('0');
	$("#noOfBeds").addClass('disableElement');

	$("#frmDt, #toDt, #arrDt, #depDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY'
	});

	$("#depTm, #arrTm").datetimepicker({
		format: 'hh:mm A'
	});
	$("#frmDt").on("dp.change", function (e) {
		$('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'frmDt');
		$("#toDt").data("DateTimePicker").minDate(e.date);
	});
	$("#toDt").on("dp.change", function (e) {
		$('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'toDt');
		$("#frmDt").data("DateTimePicker").maxDate(e.date);
	});

	$("#arrDt").on("dp.change", function (e) {
		$('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'arrDt');
		$("#depDt").data("DateTimePicker").minDate(e.date);
	});
	$("#depDt").on("dp.change", function (e) {
		$('#newDataEntrygApplication').bootstrapValidator('revalidateField', 'depDt');
		$("#arrDt").data("DateTimePicker").maxDate(e.date);
	});
	
	$("#noOfRooms").bind('keyup keypress mouseup', function () {
	    var room = $("#noOfRooms").val();
	    if(room == ''){
	    	$("#noOfRooms").removeClass('disableElement');
	    	$("#noOfBeds").removeClass('disableElement');
	    }else{
	    	$("#noOfBeds").addClass('disableElement');
	    }
	});
	
	$("#noOfBeds").bind('keyup keypress mouseup', function () {
		 var room = $("#noOfBeds").val();
		    if(room == ''){
		    	$("#noOfRooms").removeClass('disableElement');
		    	$("#noOfBeds").removeClass('disableElement');
		    }else{
		    	$("#noOfRooms").addClass('disableElement');
		    }           
	});
	

	$("input[name='bookingCond']").click(function() {
		if($(this).val() == "roomCon"){
			$('#roomTyp').val($(this).val()); //Room and Conveyance
			$('#reqDetails').show();
			$('#convenceFrom').show();
			$('#convenceTo').show();
			$('#GuestDetails').show();
			$('#btnSub').show();
		}else if($(this).val() == "onlyRoom"){
			$('#roomTyp').val($(this).val());//only room
			$('#reqDetails').show();
			$('#convenceFrom').hide();
			$('#convenceTo').hide();
			$('#GuestDetails').show();
			$('#btnSub').show();
		} else {
			$('#roomTyp').val($(this).val());
			$('#reqDetails').hide();
			$('#convenceFrom').show();
			$('#convenceTo').show();
			$('#GuestDetails').hide();
			$('#btnSub').show();
		}
	});

	var guestParameters = 5;
	var guestCount = 0;
	var blockId = getUrlParameter('id');
	var suppDoc = null;
	var bookingReqId=null;
	var officAddress = '';
	
	$('#roomWrapper').show();
	$('#dormintoryWrapper').hide();

	$("input[name='bookingType']").click(function() {
		if($(this).val() == "room"){
			$('#noOfRooms').val('1');
			$('#noOfBedsDormitory').val('0');
			$('#noOfBeds').val('');
			$('#roomWrapper').show();
			$('#dormintoryWrapper').hide();
		} else {
			$('#noOfRooms').val('0');
			$('#noOfBedsDormitory').val('1');
			$('#noOfBeds').val('');
			$('#roomWrapper').hide();
			$('#dormintoryWrapper').show();
		}
	});
	$('#supportDoc').change(function(e){
		var file = e.target.files[0];
        if(file!=undefined){
        	var size = Math.round((file.size / (1024 * 1024)));
        	if(size > 2){
        		swal('Supporting document should not be greater than 2 mb');
        		$('#supportDocName').html('Choose file');
        		$('#supportDoc').val('');
        	}else{
        		suppDoc = file;
            	$('#supportDocName').html(suppDoc.name);
        	}
        } else {
        	$('#supportDocName').html('Choose file');
        }
	});
//	**************************************************
	
	var getDataToGadRequestTable = function() {
		 var city=$('#city').val();
		 var gHouse = $('#gHouse').val();
		  console.log(city);
		  console.log(gHouse);
		$.ajax({
			url : './admin/allDataList.do',
			type : "GET",
			contentType : "application/json",
			dataType : "JSON",
			data:{
		        cityValue:city,
		        guesthouseValue:gHouse
		    },
		
			success : function(result) {
				console.log(result);
				table.clear();
				table.rows.add(result.data).draw();
			},
			error : function(jqXHR, status, error) {
				swal(error);
			}
		});

	}

	getDataToGadRequestTable ();
	
	$("#city").change(function(){
		getDataToGadRequestTable();		
	});
	$("#gHouse").change(function(){
		getDataToGadRequestTable();		
	});
	
	$('#city').on('change', function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
	});
	$('#city1').on('change', function() {
		var id = $('#city1').val();
		console.log(id);
		getGuestHouseListDtls(id);
	});
	
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
	getCityListDtls ();
	

	$('#catg').on('change', function() {
		var id = $('#catg').val();
		console.log(id);
		getDesignationListDtls(id,'');
	});

	var getDesignationListDtls = function(id,value){
		$.ajax({
			url: "./guest/getDesignationDetails/" + id + ".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				$("#desg").html("");
		//		$("#desg").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					$("#desg").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
	var getCatgoryListDtls = function(){
		$.ajax({
			url: "./guest/getCategoryDetails.do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$("#catg").html("");
				$("#catg").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					$("#catg").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
//	**************************
	var getFlightTrainList = function(type,cond,sel){
		
		$.ajax({		
			url: "./guest/getFlightTrainList/" + $('#gHouse1').val() + "/" + type + ".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();		
				if(cond == 'arr'){
					$("#arrPla").html("");
					$("#arrPla").append('<option value="">---Select---</option>');
					data.forEach(option=>{
						if(option.name == sel){
							$("#arrPla").append('<option value="'+option.name+'" selected="selected">'+option.name+'</option>');
						}else{
						$("#arrPla").append('<option value="'+option.name+'">'+option.name+'</option>');
						}
					})
				}else if(cond == 'dep'){
					$("#depPla").html("");
					$("#depPla").append('<option value="">---Select---</option>');
					data.forEach(option=>{
						if(option.name == sel){
							$("#depPla").append('<option value="'+option.name+'" selected="selected">'+option.name+'</option>');
						}else{
						$("#depPla").append('<option value="'+option.name+'">'+option.name+'</option>');
						}
					})
				}
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
	
	$('#purp').on('change', function() {
		var purpose = $('#purp').val();
		if(purpose == '1'){
			$('#office').show();
			$('#offAddr').val(officAddress);
			$('#supporting').hide();
		}else if(purpose == '3' || purpose == '4'){
			$('#office').hide();
			$('#offAddr').val('');
			$('#supporting').show();
		}else{
			$('#office').hide();
			$('#offAddr').val('');
			$('#supporting').hide();
		}

	});
	
	$('#travelModeFr').on('change', function() {
		var mode = $('#travelModeFr').val();
		if(mode == 'T'){
			getFlightTrainList('t','arr','')
		}else if(mode == 'F'){
			getFlightTrainList('f','arr','')
		}
	});
	
	$('#travelModeTo').on('change', function() {
		var mode = $('#travelModeTo').val();
		if(mode == 'T'){
			getFlightTrainList('t','dep','')
		}else if(mode == 'F'){
			getFlightTrainList('f','dep','')
		}
	});


	var getPurposeListDtls = function(){
		$.ajax({
			url: "./guest/getPurposeDetails.do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#purp").html("");
				$("#purp").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					$("#purp").append('<option value="'+option.id+'">'+option.name+'</option>');
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}



	getCatgoryListDtls();
	getPurposeListDtls();
	
	
	var convertingGuesetDetailsObjectToArrayOfObjects=function(data){
		var array=[]; var count = data.length/guestParameters;
		console.log(data.length);
		for (var i=0; i<count; i++){
			array.push(convertSerializeArrayToObject(data.slice((i*guestParameters), ((i+1)*guestParameters))));
		}
		return array;
	}
	var addGuestDetails = function(data, readonly){
		$('#guestDetailsWrapper').append(
				'<tr> '+
				'<th scope="row"><span class="mt-2">'+guestCount+'</span></th> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="hidden" name="guestId" id="guestId'+guestCount+'"> '+
				'<input type="text" name="guestName" id="guestName'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter name" autocomplete="off"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestAge" id="guestAge'+guestCount+'"class="form-control col-12" '+
				'placeholder="Enter age" autocomplete="off"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<select class="form-control col-12" name="guestGender" id="guestGender'+guestCount+'">'+
				'<option value=""> --- Select --- </option> '+
				'<option value="M">Male</option> '+
				'<option value="F">Female</option> '+
				'<option value="O">Others</option> '+
				'</select> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestAddr" id="guestAddr'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter address" autocomplete="off"> '+
				'</div> '+
				'</td> '+
				'</tr>' 
		);
		if(data!=undefined){
			$("#guestId"+guestCount).val(data.guestId);
			$("#guestName"+guestCount).val(data.guestName);
			$("#guestAge"+guestCount).val(data.guestAge);
			$("#guestGender"+guestCount).val(data.guestGender);
			$("#guestAddr"+guestCount).val(data.guestAddr);	
		}
		if(readonly!=undefined?readonly:false){	
			$("#guestId"+guestCount).addClass('disableElement');
			$("#guestName"+guestCount).addClass('disableElement');
			$("#guestAge"+guestCount).addClass('disableElement');
			$("#guestGender"+guestCount).addClass('disableElement');
			$("#guestAddr"+guestCount).addClass('disableElement');
			 
		}
	}
	
	$('#addGuestDetailsBtn').on('click', function(){
		guestCount++;
		addGuestDetails();
	});
	
	$('#deleteGuestDtlsBtn').on('click', function(){
		if(guestCount>1){
			guestCount--;
			$('#guestDetailsWrapper').children().last().remove();
		}
	});

	$('#newDataEntrygApplication').bootstrapValidator({
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
					}
				}
			}, toDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, mob: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp: /^[9 8 7]\d{9}$/,
						message: 'only 10 digits are allowed'
					}
				}
			}, emailId: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, emailAddress: {
						message: 'field is not valid'
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
						message: 'select a valid option'
					}
				}
			}, offAddr: {
				validators: {
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}, travelModeFr: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, flNoFr: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
						message: 'field is not valid'
					}
				}
			}, arrDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, arrPla: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}, travelModeTo: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			},flNoTo: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_\n/ ]*$/,
						message: 'field is not valid'
					}
				}
			},depDt: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			}, depPla: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			}
		}
	}).on('success.form.bv', function (e) {
		e.preventDefault();
		var type = $('#roomTyp').val();
		if(type == 'conv'){
			submitBookingDetails('C');
		}else if(type == 'onlyRoom'){
			submitBookingDetails('R');
		}else{
			submitBookingDetails('RC');
		}
	});
	var setGuestDetails = function(bookingId, isReadonly){
		var formData = $('#newDataEntrygApplication').serializeArray();	
		var otherDetails = formData.filter(d=>!d.name.match(/guest/g));	
		$.ajax({
			url: "./admin/getBookingDetailsByBookingId/"+bookingId+".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
//				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {				
				console.log(r);
				if (r.status) {
					$('#city1').val(r.data.cityId).change();
					setTimeout(function(){  
					$('#gHouse1').val(r.data.blockId).change();}, 3000);					
					bookingReqId = r.data.bookingId;
					var id = r.data.catg;
					getDesignationListDtls(id,r.data.desg);
					var type = r.data.roomType;
					$('#roomTyp').val(type);
					var valu = '';
					if(type == 'RC'){
						valu = 'roomCon';
						$('#reqDetails').show();
						$('#convFrDtls').show();
						$('#convToDtls').show();
						$('#GuestDetails').show();
					}else if(type == 'R'){
						valu = 'onlyRoom';
						$('#reqDetails').show();
						$('#convFrDtls').hide();
						$('#convToDtls').hide();
						$('#GuestDetails').show();
					}
					$('input[name="bookingCond"][type="radio"][value="' + valu +'"]').prop('checked', true);
					if(r.data.bookingType != null){
						$('input[name="bookingType"][type="radio"][value="' + r.data.bookingType +'"]').prop('checked', true);
					}
					otherDetails.forEach(item => {
						$('input[name="'+item.name+'"][type!="radio"]').val(r.data[item.name]);
						$('input[name="'+item.name+'"][type="radio"][value="'+r.data[item.name]+'"]').prop('checked', true);
						$('select[name="'+item.name+'"]').val(r.data[item.name]);
						if(item.name == 'purp'){
							var purpose = $('#purp').val();
							if(purpose == '1'){
								$('#office').show();
								$('#supporting').hide();
							}else if(purpose == '3' || purpose == '4'){
								$('#office').hide();
								$('#supporting').show();
							}else{
								$('#office').hide();
								$('#supporting').hide();
							}
						}
						
						if(item.name == 'travelModeFr'){
							var purpose = $('#travelModeFr').val();
							console.log(purpose);
							var valu = r.data.arrPla;
							if(purpose == 'F'){	
									setTimeout(function(){ 
									getFlightTrainList('f','arr',valu);},3000);
							}else if(purpose == 'T'){
								setTimeout(function(){ 
									getFlightTrainList('t','arr',valu);}, 3000);									
							}
						}
						
						if(item.name == 'travelModeTo'){
							var purpose = $('#travelModeTo').val();
							var valu = r.data.depPla;
							if(purpose == 'F'){
								setTimeout(function(){  
								getFlightTrainList('f','dep',valu);}, 3000);
								
							}else if(purpose == 'T'){
								setTimeout(function(){ 
								getFlightTrainList('t','dep',valu);}, 3000);
								
							}
						}
						
						$('textarea[name="'+item.name+'"]').val(r.data[item.name]);
						if(isReadonly){
							$("#desg").addClass('disableElement');
							$("#travelModeFr").addClass('disableElement');
							$("#travelModeTo").addClass('disableElement');
							$("#arrPla").addClass('disableElement');
							$("#depPla").addClass('disableElement');
							$('input[name="'+item.name+'"]').addClass('disableElement');
							$('select[name="'+item.name+'"]').addClass('disableElement');
							$('textarea[name="'+item.name+'"]').addClass('disableElement');
						} else {
							$("#desg").removeClass('disableElement');
							$("#travelModeFr").removeClass('disableElement');
							$("#travelModeTo").removeClass('disableElement');
							$("#arrPla").removeClass('disableElement');
							$("#depPla").removeClass('disableElement');
							$('#checkRoomWith').removeClass('disableElement');
							$('input[name="'+item.name+'"]').removeClass('disableElement');
							$('select[name="'+item.name+'"]').removeClass('disableElement');
							$('textarea[name="'+item.name+'"]').removeClass('disableElement');
						}
					});
					for(var i=0;i<r.data.list.length; i++){
						guestCount++;
						
						addGuestDetails(r.data.list[i], isReadonly);
					}
					var suppdocname = null;
					if(r.data.suppDocName!=null){
						var fileArr = r.data.suppDocName.split(/(\\|\/)/);
						suppdocname = fileArr[fileArr.length -1];
					}
					if(r.data.bookingType == "room"){
						$('#roomWrapper').show();
						$('#dormintoryWrapper').hide();
					} else {
						$('#roomWrapper').hide();
						$('#dormintoryWrapper').show();
					}
					if(isReadonly){
						$("#bookingTypeShowDtls, #suppDocDtls").show();
						$("#bookingTypeDtls, #guestMangCtrl, #formSubmissionBtn, #suppDocEdit").hide();
						$("#bookingTypeShowDtls").html(r.data.bookingType=='room'?'Book Room/Bed':'Book Dormitory');
						if(!r.data.isArrivalConv){$("#convFrDtls").hide()};
						if(!r.data.isDepartueConv){$("#convToDtls").hide()};
						if(suppdocname!=null){$("#suppDocDtls").html('<a href="./imagedownload/application_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>'+suppdocname+'</a>');};
					} else {
						$("#bookingTypeDtls,#guestMangCtrl,#formSubmissionBtn,#suppDocEdit").show();
						$("#bookingTypeShowDtls, #suppDocDtls").hide();
						$("#bookingTypeShowDtls,#suppDocDtls").html("");
						if(suppdocname!=null){$("#supportDocName").html(suppdocname);}
					}
				}
			},error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
		    var submitBookingDetails = function(type){
	        console.log('form valid');       
	        var formData = $('#newDataEntrygApplication').serializeArray();
	        console.log(formData);
	        console.log($('#gHouse1').val());
	        var rawGuestDetails = formData.filter(d=>d.name.match(/guest/g));
	        var otherDetails = formData.filter(d=>!d.name.match(/guest/g));
	        var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
	        console.log(guestDtls);
	        var fd = new FormData();
	        fd = convertSerializeArrayToFd(otherDetails);
	        fd.append("guestDtlsStr", JSON.stringify(guestDtls));
	        fd.append("bookingId",bookingReqId);
//	        fd.append("cityID",$('#city1').val());
//	        fd.append("blockId", $('#gHouse1').val());
	        if(suppDoc!=null){fd.append("suppDoc", suppDoc, suppDoc.name)};
	        console.log(fd);
	        $.ajax({
	            url: "./admin/adminsaveBookingDetails/update/" + type + ".do",
	            type : "POST",
	            enctype: 'multipart/form-data',
	            processData: false,  // Important!
	            contentType: false,
	            data: fd,
	            beforeSend: function(xhr) {
	                $('#loading').show();
	                xhr.setRequestHeader(header, token);
	            },
	            success: function(data, textStatus, jqXHR) {
	                $('#loading').hide();
	                if (data.status) {
	                    $(this).find('#applicationFormModal1').trigger('reset');
	                    $('#newDataEntrygApplication').bootstrapValidator('resetForm', true);
	                    resetForm();
	                    guestCount = 0;
	                    $('#addGuestDetailsBtn').click();
	                    $('#applicationFormModal1').modal('hide');
	                    swal(data.msg);
	                    getDataToGadRequestTable ();
	                } else {
	                    swal(data.msg);
	                }
	            },error:function(jqXHR, status, error){
	                swal(error);
	            }
	        });
	    }	
	
});            //end document function

$('#guestDataTable').on('click', 'tbody #edit', function () {
	$('#applicationFormModal').modal('show');
	var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
	blockId = data.cityName; 
	guestCount=0; $('#guestDetailsWrapper').html("");
	$('#applicationHeader').html('Application Form Update');
	setGuestDetails(data.bookingId, false);
});


function resetForm(){
	$('#guestDetailsWrapper').html('');
	$('#noOfBedsDormitory').val('');
	$('#depTm').val('');
	$('#arrTm').val('');
}