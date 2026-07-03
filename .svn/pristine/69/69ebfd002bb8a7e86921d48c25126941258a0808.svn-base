   $(document).ready(function()
     {
	//$('#office').hide();
	$('#supporting').hide();
	$('#docTypediv').hide();
	$('#category_supporting').hide();
	$('#convenceFrom').hide();
	$('#convenceTo').hide();
	$('#desgHidden').hide();
	$('#noOfRooms').val('1');
	$("#noOfBeds").addClass('disableElement');
	//$('#addressLine').hide();

	$("#frmDt, #toDt, #arrDt, #depDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY'
	});

	$("#depTm, #arrTm").datetimepicker({
		format: 'hh:mm A'
	});

	$("#frmDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
		$("#toDt").data("DateTimePicker").minDate(e.date);
	});
	$("#toDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'toDt');
		$("#frmDt").data("DateTimePicker").maxDate(e.date);
	});

	$("#arrDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
		$("#depDt").data("DateTimePicker").minDate(e.date);
	});
	$("#depDt").on("dp.change", function (e) {
		$('#newRoomBookingApplication').bootstrapValidator('revalidateField', 'depDt');
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
			$('#roomTyp').val($(this).val()); // roomCon
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convenceFrom').show();
			$('#convenceTo').show();
			$('#GuestDetails').show();
			$('#btnSub').show();
			$('#addressLine').hide();
		}else if($(this).val() == "onlyRoom"){
			$('#roomTyp').val($(this).val()); // onlyRoom
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convenceFrom').hide();
			$('#convenceTo').hide();
			$('#GuestDetails').show();
			$('#btnSub').show();
			$('#addressLine').hide();
		} else {
			$('#roomTyp').val($(this).val()); // conv
			$('#reqDetails').hide();
			$('#addressLine').show();
			$('#addressDetails').show();
			$('#convenceFrom').show();
			$('#convenceTo').show();
			$('#GuestDetails').show();
			$('#btnSub').show();
		}
	});

	var guestParameters = 7;
	var guestCount = 0;
	var blockId = getUrlParameter('id');
	var suppDoc = null;
	var catgDoc=null;
	var officAddress = '';

	//$('#roomWrapper').show();
	$('#dormintoryWrapper').hide();

	$("input[name='bookingType']").click(function() {
		if($(this).val() == "room"){
			$('#noOfRooms').val('1');
			$('#noOfBedsDormitory').val('0');
			$('#noOfBeds').val('');
			$('#roomWrapper').show();
			$('#dormintoryWrapper').hide();
		} else {
			$('#noOfRooms').val('1');
			$('#noOfBedsDormitory').val('0');
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

	$('#categoryDoc').change(function(e){
		var file = e.target.files[0];
		if(file!=undefined){
			var size = Math.round((file.size / (1024 * 1024)));
			if(size > 2){
				swal('Category document should not be greater than 2 mb');
				$('#categoryDocName').html('Choose file');
				$('#categoryDoc').val('');
			}else{
				catgDoc = file;
				$('#categoryDocName').html(catgDoc.name);
			}
		} else {
			$('#categoryDocName').html('Choose file');
		}
	});

	$('#catg').on('change', function() {
		var id = $('#catg').val();
		getDesignationListDtls(id,'');
	});
	
//	$('#desg').on('change', function() {
//		var id = $('#desg').val();
//		getGuestDesignationDetails(id);
//	});
	
	var getGuestDesignationDetails = function(id){
		$.ajax({
			url: "./guest/getGuestDesignationDetails/" + id + ".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				$('#guestDesignation').val(data == null ? '' : data);
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}

	var getDesignationListDtls = function(id,type){
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
				$("#desg").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					if(type == option.id){
						$("#desg").append('<option value="'+option.id+'" selected="selected">'+option.name+'</option>');
					}else{
						$("#desg").append('<option value="'+option.id+'">'+option.name+'</option>');
					}
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
	
	var getCatgoryListDtls = function(type){
		$.ajax({
			url: "./guest/getCategoryDetails.do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#catg").html("");
				$("#catg").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					if(('C' + type) == option.id){
						$("#catg").append('<option value="'+option.id+'" selected="selected">'+option.name+'</option>');
					}else{
						$("#catg").append('<option value="'+option.id+'">'+option.name+'</option>');
					}
				})
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}
	
	var getFlightTrainList = function(type,cond){
		$.ajax({
			url: "./guest/getFlightTrainList/" + blockId + "/" + type + ".do",
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
						$("#arrPla").append('<option value="'+option.name+'">'+option.name+'</option>');
					})
				}else if(cond == 'dep'){
					$("#depPla").html("");
					$("#depPla").append('<option value="">---Select---</option>');
					data.forEach(option=>{
						$("#depPla").append('<option value="'+option.name+'">'+option.name+'</option>');
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
			$('#supporting').show();
		}else if(purpose == '3' || purpose == '4'){
			$('#office').show();
			$('#offAddr').val(officAddress);
			$('#supporting').show();
		}else{
			$('#office').show();
			$('#offAddr').val(officAddress);
			$('#supporting').hide();
		}

	});
	
	$('#catg').on('change', function() {
		var category = $('#catg').val();
		if(category == 'C1'||category == 'C2'||category == 'C3'){
			$('#docTypediv').show();
			$('#category_supporting').show();
		}else{
			$('#docTypediv').hide();
			$('#category_supporting').hide();
		}

	});
	
	
	$('#travelModeFr').on('change', function() {
		var mode = $('#travelModeFr').val();
		if(mode == 'T'){
			getFlightTrainList('t','arr')
		}else if(mode == 'F'){
			getFlightTrainList('f','arr')
		}
	});
	
	$('#travelModeTo').on('change', function() {
		var mode = $('#travelModeTo').val();
		if(mode == 'T'){
			getFlightTrainList('t','dep')
		}else if(mode == 'F'){
			getFlightTrainList('f','dep')
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

	var getUserDtls = function(){
		$.ajax({
			url: "./userProfile.do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				if(result.success){
					$("#mob").val(result.data.contactNo);
					$("#catgType").val(result.data.catgType);
					if(result.data.email != null){
						$("#emailId").val(result.data.email);
					}
					if(result.data.firstName != null){
						$("#guestName").val(result.data.firstName);
					}
					officAddress = result.data.officeAddr == null ? '' : result.data.officeAddr;
					$('#guestAddr').val(result.data.resAddr == null ? '' : result.data.resAddr);
					$('#guestGender').val(result.data.guestGender == null ? '' : result.data.guestGender);
					$('#guestDepartment').val(result.data.dept == null ? '' : result.data.dept);
					if(result.data.dob != null){
						var year = dayDiff(new Date(),result.data.dob)
						$('#guestAge').val(year)
					}
				}else{
					swal(result.msg);
				}
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}

	getCatgoryListDtls('');
	getPurposeListDtls();
	getUserDtls();

	var convertingGuesetDetailsObjectToArrayOfObjects=function(data){
		var array=[]; var count = data.length/guestParameters;
		console.log(data.length);
		for (var i=0; i<count; i++){
			array.push(convertSerializeArrayToObject(data.slice((i*guestParameters), ((i+1)*guestParameters))));
		}
		return array;
	}

	$('#addGuestDetailsBtn').on('click', function(){
		guestCount++;
		$('#guestDetailsWrapper').append(
				'<tr id=tr' + guestCount + '> '+
				'<th scope="row"><span class="mt-2" id=guestCo' + guestCount +'>'+guestCount+'</span></th> '+
				'<th scope="row"><span class="mt-2">'+
				'<input class="form-check-input" type="checkbox" value=' + guestCount + ' id=check' + guestCount + '>'+ 
				'</span></th> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="hidden" name="guestId"> '+
				'<input type="text" name="guestName" id="guestName" class="form-control col-12" '+
				'placeholder="Enter name"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="number" name="guestAge" id="guestAge" class="form-control col-12" '+
				'placeholder="Enter age" autocomplete="off" min="1"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<select class="form-control col-12" name="guestGender" id="guestGender"> '+
				'<option value=""> --- Select --- </option> '+
				'<option value="M">Male</option> '+
				'<option value="F">Female</option> '+
				'<option value="O">Others</option> '+
				'</select> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestDesignation" class="form-control col-12" id="guestDesignation"'+
				'placeholder="Enter Designation"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestDepartment" id="guestDepartment" class="form-control col-12" '+
				'placeholder="Enter Department"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestAddr" id="guestAddr" class="form-control col-12" '+
				'placeholder="Enter address"> '+
				'</div> '+
				'</td> '+
				'</tr>' 
		);
	});

	$('#addGuestDetailsBtn').click();

	$('#deleteGuestDtlsBtn').on('click', function(){
		var i,j,len=0;
		if(guestCount>1){
			for (i=1; i <= guestCount + len; i++) {
				if($('#check'+(i-len)).prop("checked")){
					$('#tr'+(i-len)).remove();
					for (j=(i+1-len); j <= guestCount; j++) {
						$('#check'+j).val(j-1); 
						$('#check'+j).attr('id', 'check'+(j-1)); 
						$('#tr'+j).attr('id', 'tr'+(j-1)); 
						$('#guestCo'+j).html(j-1); 
						$('#guestCo'+j).attr('id', 'guestCo'+(j-1)); 
					}
					len = len + 1;
					guestCount--;
				}
			}
		}
	});


	var submitBookingDetails = function(type){
		console.log('form valid');
		var formData = $('#newRoomBookingApplication').serializeArray();
		var rawGuestDetails = formData.filter(d=>d.name.match(/guest/g));	
		var otherDetails = formData.filter(d=>!d.name.match(/guest/g));
		var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
		console.log(guestDtls);
		var fd = new FormData();
		fd = convertSerializeArrayToFd(otherDetails);
		fd.append("guestDtlsStr", JSON.stringify(guestDtls));
		fd.append("blockId", blockId);
		
		if(suppDoc!=null){fd.append("suppDoc", suppDoc, suppDoc.name)};
		if(catgDoc!=null){fd.append("catgDoc", catgDoc, catgDoc.name)};
		$.ajax({
			url: "./guest/saveBookingDetails/add/" + type + ".do",
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
				console.log("Successs : "+data);
				$('#loading').hide();
				if (data.status && data.isThirdParty == false) {
					$(this).find('form').trigger('reset');
					$('#newRoomBookingApplication').bootstrapValidator('resetForm', true); 
					resetForm();
					$('#supportDocName').html('Choose file');
					$('#categoryDocName').html('Choose file');
					guestCount = 0;
					$('#addGuestDetailsBtn').click();
					swal(data.msg);
				} else if(data.status && data.isThirdParty){
					console.log(data.successEncData);
					$("#encDataSuccessData").val(data.successEncData);
					$('#successBacktoOdishaOne').attr('action', data.successUrl).submit();
				}else {
					swal(data.msg);
				}
			},error:function(jqXHR, status, error){	
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
	$('#newRoomBookingApplication').bootstrapValidator({
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
						format: 'DD-MM-YYYY'
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
			},emailId: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}, regexp: {
						regexp:/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
						message: 'field is not valid'
					}
				}
			},catg: {
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
					notEmpty: {
						message: 'select a valid option'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			},duringstay: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			
//			},supportDoc : {
//				validators : {
//					notEmpty : {
//						message : 'Document Type is required and cannot be Empty.'
//					},
//
//				}
//			},
//			categoryDoc : {
//				validators : {
//					notEmpty : {
//						message : 'file  is required and cannot be Empty.'
//					},
//
//				}
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
			},guestName: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9-_()\n\t@,/.$%&:' ]*$/,
						message: 'field is not valid'
					}
				}
			},guestAge: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				},regexp: {
					regexp: /^[0-9]*$/,
					message: 'field is not valid'
				}
			},guestGender: {
				validators: {
					notEmpty: {
						message: 'field can\'t be empty'
					}
				}
			},guestAddr: {
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
	
	var table = $("#guestCatDataTable").removeAttr('width').DataTable({  
		"paging":  true,
		"searching": true,
		"responsive": true,
		"lengthMenu": [[100,200, 300, -1], [100,200, 300, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"columnDefs":[		
			{ className: "all", "targets": [ 0, 1,2 ] },
			{"targets":0,"data": null, 
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{"targets":1,"data":null,
				render: function (data, type, row, meta) {
						return "Cat" + data.desc
				}
			},
			{"targets":2,"data":"name"},
			{"targets":3,"data": null, 
				render: function (data, type, row, meta) {
						return '<button type="button" id="cateType" title="Select Category" action="cateType" class="btn btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button>' 
				}
			}
			], 	  
	});
	
	$("#myModalView").on("hidden.bs.modal", function(){
		$(this).find('form').trigger('reset');
	});
	
	$('#guestCatDataTable').on('click', 'tbody #cateType', function () {
		$('#myModalView').modal('hide');
		var data_row = table.row($(this).closest('tr')).data();
		getCatgoryListDtls(data_row.desc);
		getDesignationListDtls(('C' + data_row.desc),data_row.id);
//		getGuestDesignationDetails(data_row.id);
	});
	
	$('#view').click(function(){
		$('#myModalView').modal('show');
		$.ajax({
			url: './guest/getDesgList.do',
			type: "GET",	
			headers : {"X-CSRF-TOKEN" : token},		 
			success:function(result) {
				table.clear().draw();
				table.rows.add(result).draw();
				table.columns.adjust().responsive.recalc();
			}
		});
	});
})

function dayDiff(d1, d2)
{
  d2 = new Date(d2);
  return new Number((d1.getTime() - d2.getTime()) / 31536000000).toFixed(0);
}

function resetForm(){
	$('#guestDetailsWrapper').html('');
	$('#noOfBedsDormitory').val('');
	$('#depTm').val('');
	$('#arrTm').val('');
	$("#duringstay").val('');
	
	
}