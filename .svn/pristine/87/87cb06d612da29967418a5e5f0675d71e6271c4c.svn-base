$(document).ready(function(){
	var blockId = 0;
	//$('#docTypediv').hide();
	$('#conReq').hide();
	$('#chec').show();
	$("#frmDt, #toDt, #arrDt, #depDt").datetimepicker({
		useCurrent: false,
		minDate: moment(),
		format: 'DD-MM-YYYY'
	});

	$("#depTm, #arrTm").datetimepicker({
		format: 'hh:mm A'
	});

	$("#frmDt").on("dp.change", function (e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'frmDt');
		$("#toDt").data("DateTimePicker").minDate(e.date);
	});
	$("#toDt").on("dp.change", function (e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'toDt');
		$("#frmDt").data("DateTimePicker").maxDate(e.date);
	});

	$("#arrDt").on("dp.change", function (e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'arrDt');
		$("#depDt").data("DateTimePicker").minDate(e.date);
	});
	$("#depDt").on("dp.change", function (e) {
		$('#existingBookingApplication').bootstrapValidator('revalidateField', 'depDt');
		$("#arrDt").data("DateTimePicker").maxDate(e.date);
	});

	$("#noOfRooms").bind('keyup keypress mouseup', function () {
		var room = $("#noOfRooms").val();
		if(room == ''){
			$("#noOfRooms").removeClass('disableElement');
			$("#noOfBeds").removeClass('disableElement');
		}else{
			$("#noOfBeds").addClass('disableElement');
			$("#noOfBeds").val('')
		}
	});

	$("#noOfBeds").bind('keyup keypress mouseup', function () {
		var room = $("#noOfBeds").val();
		if(room == ''){
			$("#noOfRooms").removeClass('disableElement');
			$("#noOfBeds").removeClass('disableElement');
		}else{
			$("#noOfRooms").addClass('disableElement');
			$("#noOfRooms").val('')
		}           
	});

	$("input[name='bookingCond']").click(function() {
		if($(this).val() == "roomCon"){
			$('#roomTyp').val($(this).val());
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convFrDtls').show();
			$('#convToDtls').show();
			$('#GuestDetails').show();
			$('#addressLine').hide();
		}else if($(this).val() == "onlyRoom"){
			$('#roomTyp').val($(this).val());
			$('#reqDetails').show();
			$('#addressDetails').show();
			$('#convFrDtls').hide();
			$('#convToDtls').hide();
			$('#GuestDetails').show();
			$('#addressLine').hide();
		} 
	});

	var guestParameters = 7;
	var bookingReqId = null;
	var guestCount = 0;
	var suppDoc = null;
	var catgDoc = null;

	var token = $("meta[name='_csrf']").attr("content");
	$("input[name='bookingType']").change(function() {
		if($(this).val() == "room"){
			$('#roomWrapper').show();
			$('#dormintoryWrapper').hide();
		} else {
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
				$('#guestDesignation1').val(data == null ? '' : data);
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}

	var getDesignationListDtls = function(id,value){
		$.ajax({
			url: "./guest/getDesignationDetails/" + id + ".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				$("#desg").html("");
				$("#desg").append('<option value="">---Select---</option>');
				data.forEach(option=>{
					if(option.id == value){
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
	
	var getFlightTrainList = function(type,cond,sel){
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

	
	$('#purp').on('change', function() {
		var purpose = $('#purp').val();
		if(purpose == '1'){
			$('#office').show();
			$('#supporting').show();
		}else if(purpose == '3' || purpose == '4'){
			$('#office').show();
			$('#supporting').show();
		}else{
			$('#office').show();
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

	getCatgoryListDtls('');
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
				'<tr id=tr' + guestCount + '> '+
				'<th scope="row"><span class="mt-2" id=guestCo' + guestCount +'>'+guestCount+'</span></th> '+
				'<th scope="row"><span class="mt-2">'+
				'<input class="form-check-input" type="checkbox" value=' + guestCount + ' id=check' + guestCount + '>'+ 
				'</span></th> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="hidden" name="guestId" id="guestId'+guestCount+'"> '+
				'<input type="text" name="guestName" id="guestName'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter name"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="number" name="guestAge" id="guestAge'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter age" autocomplete="off" min="1"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<select class="form-control col-12" id="guestGender'+guestCount+'" name="guestGender"> '+
				'<option value=""> --- Select --- </option> '+
				'<option value="M">Male</option> '+
				'<option value="F">Female</option> '+
				'<option value="O">Others</option> '+
				'</select> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestDesignation" id="guestDesignation'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter Designation"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestDepartment" id="guestDepartment'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter Department"> '+
				'</div> '+
				'</td> '+
				'<td> '+
				'<div class="row"> '+
				'<input type="text" name="guestAddr" id="guestAddr'+guestCount+'" class="form-control col-12" '+
				'placeholder="Enter address"> '+
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
			$("#guestDesignation"+guestCount).val(data.guestDesignation);
			$("#guestDepartment"+guestCount).val(data.guestDepartment);
		}
		if(readonly!=undefined?readonly:false){
			$("#guestId"+guestCount).addClass('disableElement');
			$("#guestName"+guestCount).addClass('disableElement');
			$("#guestAge"+guestCount).addClass('disableElement');
			$("#guestGender"+guestCount).addClass('disableElement');
			$("#guestAddr"+guestCount).addClass('disableElement');
			$("#guestDesignation"+guestCount).addClass('disableElement');
			$("#guestDepartment"+guestCount).addClass('disableElement');
			$('#checkRoomWith').addClass('disableElement');
		}
	}

	$('#addGuestDetailsBtn').on('click', function(){
		guestCount++;
		addGuestDetails();
	});

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

	var setGuestDetails = function(bookingId, isReadonly){
		console.log(bookingId);
		var formData = $('#existingBookingApplication').serializeArray();
		var otherDetails = formData.filter(d=>!d.name.match(/guest/g));
		
		console.log(otherDetails);
		$.ajax({
			url: "./guest/getBookingDetailsByBookingId/"+bookingId+".do",
			type: "GET",
			contentType:"application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(r, textStatus, jqXHR) {
				console.log(r);
				$('#loading').hide();
				console.log(r);
				if (r.status) {
					bookingReqId = r.data.bookingId;
					var id = r.data.catg;
					getDesignationListDtls(id,r.data.desg);
					var type = r.data.roomType;
					$('#roomTyp').val(type);
					var valu = '';
					if(type == 'RC'){
						valu = 'roomCon';
						$('#reqDetails').show();
						$('#addressDetails').show();
						$('#convFrDtls').show();
						$('#convToDtls').show();
						$('#GuestDetails').show();
					}else if(type == 'R'){
						valu = 'onlyRoom';
						$('#reqDetails').show();
						$('#addressDetails').show();
						$('#convFrDtls').hide();
						$('#convToDtls').hide();
						$('#GuestDetails').show();
					}
					$('input[name="bookingCond"][type="radio"][value="' + valu +'"]').prop('checked', true);
					if(r.data.bookingType != null){
						$('input[name="bookingType"][type="radio"][value="' + r.data.bookingType +'"]').prop('checked', true);
					}
					if(r.data.duringstay!=null){
						$('input[name="duringstay"][type="radio"][value="'+ r.data.duringstay+'"]').prop('checked',true);
					}
					otherDetails.forEach(item => {
						console.log(item.name);
						$('input[name="'+item.name+'"][type!="radio"]').val(r.data[item.name]);
						$('input[name="'+item.name+'"][type="radio"][value="'+r.data[item.name]+'"]').prop('checked', true);
						$('input[name="'+item.name+'"][type="checkbox"][value="'+ r.data[item.name]+'"]').prop('checked',true);
						$('select[name="'+item.name+'"]').val(r.data[item.name]);
						if(item.name == 'purp'){
							var purpose = $('#purp').val();
							if(purpose == '1'){
								$('#office').show();
								$('#supporting').show();
							}else if(purpose == '3' || purpose == '4'){
								$('#office').hide();
								$('#supporting').show();
							}else{
								$('#office').show();
								$('#supporting').hide();
							}
						}
						if(item.name == 'catg'){
							var category = $('#catg').val();
							if(category == 'C1'||category == 'C2'||category == 'C3'){
								$('#docTypediv').show();
								$('#category_supporting').show();
							}else{
								$('#docTypediv').hide();
								$('#category_supporting').hide();
							}
							
						}
		
						if(item.name == 'travelModeFr'){
							var purpose = $('#travelModeFr').val();
							var valu = r.data.arrPla;
							if(purpose == 'F'){
								getFlightTrainList('f','arr',valu)
							}else if(purpose == 'T'){
								getFlightTrainList('t','arr',valu)
							}
						}
						
						if(item.name == 'travelModeTo'){
							var purpose = $('#travelModeTo').val();
							var valu = r.data.depPla;
							if(purpose == 'F'){
								getFlightTrainList('f','dep',valu)
							}else if(purpose == 'T'){
								getFlightTrainList('t','dep',valu)
							}
						}
						
						$('textarea[name="'+item.name+'"]').val(r.data[item.name]);
						if(isReadonly){
							$("#desg").addClass('disableElement');
							$("#viewCate").addClass('disableElement');
							$("#travelModeFr").addClass('disableElement');
							$("#travelModeTo").addClass('disableElement');
							$("#arrPla").addClass('disableElement');
							$("#duringstay").addClass('disableElement');
							$("#depPla").addClass('disableElement');
							$('input[name="'+item.name+'"]').addClass('disableElement');
							$('select[name="'+item.name+'"]').addClass('disableElement');
							$('textarea[name="'+item.name+'"]').addClass('disableElement');
						} else {
							$("#desg").removeClass('disableElement');
							$("#viewCate").removeClass('disableElement');
							$("#travelModeFr").removeClass('disableElement');
							$("#travelModeTo").removeClass('disableElement');
							$("#duringstay").removeClass('disableElement');
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
					var catgdocname = null;
					if(r.data.catgDocName!=null){
						var fileArr = r.data.catgDocName.split(/(\\|\/)/);
						catgdocname = fileArr[fileArr.length -1];
					}
					if(r.data.bookingType == "room"){
						$('#roomWrapper').show();
						$('#dormintoryWrapper').hide();
					} else {
						$('#roomWrapper').hide();
						$('#dormintoryWrapper').show();
					}
					if(isReadonly){
						$("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls").show();
						$("#bookingTypeDtls, #guestMangCtrl, #formSubmissionBtn, #suppDocEdit,#desgDocEdit").hide();
						$("#bookingTypeShowDtls").html(r.data.bookingType=='room'?'Book Room/Bed':'Book Dormitory');
						if(!r.data.isArrivalConv){$("#convFrDtls").hide()};
						if(!r.data.isDepartueConv){$("#convToDtls").hide()};
						if(suppdocname!=null){$("#suppDocDtls").html('<a href="./imagedownload/applicationSup_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>'+suppdocname+'</a>');};
						if(catgdocname!=null){$("#desgDocDtls").html('<a href="./imagedownload/applicationCat_' + bookingReqId + '.do" target="_blank"><i class="fa fa-download pr-2"></i>'+catgdocname+'</a>');};
					} else {
						$("#bookingTypeDtls,#guestMangCtrl,#formSubmissionBtn,#suppDocEdit,#desgDocEdit").show();
						$("#bookingTypeShowDtls, #suppDocDtls,#desgDocDtls").hide();
						$("#bookingTypeShowDtls,#suppDocDtls,#desgDocDtls").html("");
						if(suppdocname!=null){$("#supportDocName").html(suppdocname);}
						if(catgdocname!=null){$("#categoryDocName").html(catgdocname);}
					}
					
					
				}else{
					swal(r.msg);
				}
			},error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}

	var table = $("#guestDataTable").DataTable({  
		"paging":  true,
		"searching": true,
		"responsive": true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs":[		
			{ className: "all", "targets": [ 0, 1,2 ] },
			{"targets":0,"data": null, 
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{"targets":1,"data":"bookingId"},
			{"targets":2,"data":"ghName"},
			{"targets":3,"data":"bookingDt"},
			{"targets":4,"data":"purpose"},
			{"targets":5,"data":"conveyance"},
			{"targets":6,"data":"bookingType"},
			{"targets":7,"data":"statusDesc"},
			{"targets":8,"data": null, 
				render: function (data, type, row, meta) {
					if(data.status == 'REQ'){
						return '<button type="button" id="edit" title="Edit" action="Edit" class="btn btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> ' + 
						'<button type="button" action="cancel" id="cancel" title="Cancel" class="btn btn-sm btn-delete"><i class="fa fa-times" aria-hidden="true"></i></button> ' +
						'<button type="button" id="view" action="View" title="View" class="btn btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> ' + 
						'<button type="button" id="receipt" action="Receipt" title="Receipt" class="btn btn-sm btn-recipt" ><i class="fa fa-print" aria-hidden="true"></i></button>'
					}else if(data.status == 'COUT'){
						return '<button type="button" id="view" action="View" title="View" class="btn btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> ' +
						'<button type="button" id="receipt" action="Receipt" title="Receipt" class="btn btn-sm btn-recipt" style="margin-right: 0.28rem;"><i class="fa fa-print" aria-hidden="true"></i></button>' + 
						'<button type="button" id="paymentReceipt" action="PaymentReceipt" title="Payment Receipt" class="btn btn-sm btn-payment"><i class="fa fa-credit-card " aria-hidden="true"></i></button>'
					}else{
						return '<button type="button" id="view" action="View" title="View" class="btn btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> ' +
						'<button type="button" id="receipt" action="Receipt" title="Receipt" class="btn btn-sm btn-recipt"><i class="fa fa-print" aria-hidden="true"></i></button>'
					}
				}
			}
			], 	  
	});	

	var contable = $("#conDataTable").DataTable({  
		"paging":  true,
		"searching": true,
		"responsive": true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs":[		
			{ className: "all", "targets": [ 0, 1,2 ] },
			{"targets":0,"data": null, 
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{"targets":1,"data":"bookingId"},
			{"targets":2,"data":"ghName"},
			{"targets":3,"data":"fromDate"},
			{"targets":4,"data":"toDate"},
			{"targets":5,"data":"conveyance"},
			{"targets":6,"data":"statusDesc"},
			{"targets":7,"data": null, 
				render: function (data, type, row, meta) {
					if(data.status == 'REQ'){
						return '<button type="button" id="edit" title="Edit" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> ' + 
						'<button type="button" action="cancel" id="cancel" title="Cancel" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-times" aria-hidden="true"></i></button> ' +
						'<button type="button" id="view" action="View" title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
					}else{
						return '<button type="button" id="view" action="View" title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
					}
				}
			}
			], 	  
	});	
    
	getNomineeRequestList(table,contable);
	$('#guestDataTable').on('click', 'tbody #view', function () {
		$('#applicationFormModal').modal('show');
		$('#applicationHeader').html('View Form')
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		blockId = data.cityName; 
		guestCount=0;
		$('#guestDetailsWrapper').html("");
		setGuestDetails(data.bookingId, true);
	});
	
	$('#guestDataTable').on('click', 'tbody #edit', function () {
		$('#applicationFormModal').modal('show');
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		blockId = data.cityName; 
		guestCount=0; 
		$('#guestDetailsWrapper').html("");
		$('#applicationHeader').html('Application Form Update');
		setGuestDetails(data.bookingId, false);
		
	});
//	$('#applicationFormModal').on('hidden.bs.modal', function () { 
//	    location.reload();
//	});

	$('#guestDataTable').on('click', 'tbody #cancel', function () {
		var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		swal({
			title: "Are you sure? ",
			text: "Are you sure to cancel!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, cancel it!",
			closeOnConfirm: false
		},
		function(){
			$('#loading').show();
			$.ajax({
				url: './guest/cancelRequest/'+data_row.bookingId + ".do",
				type: "GET",	
				headers : {"X-CSRF-TOKEN" : token},		 
				success:function(data) {
					$('#loading').hide();
					console.log(data);
					
					if(data.status && data.isThirdParty == false){
						swal(data.msg);
						getNomineeRequestList(table,contable);
					}else if(data.status && data.isThirdParty){
						console.log(data.cancelEncData);
						alert(data.cancelEncData);
						$("#encDataSuccessData").val(data.cancelEncData);
						$('#successBacktoOdishaOne').attr('action', data.successUrl).submit();
					}else {
						swal(data.msg);
					}
				}
			});
		});
	})

	$('#guestDataTable').on('click', 'tbody #receipt', function () {
		$('#myModalView').modal('show');
		var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		$('#loading').show();
		$.ajax({
			url: './guest/getRequest/'+data_row.bookingId + ".do",
			type: "GET",	
			headers : {"X-CSRF-TOKEN" : token},		 
			success:function(result) {
				$('#loading').hide();
				if(result.success){
					if(result.data != null){
						var date = '';
						if(result.data.bookingDt != null){
							date = result.data.bookingDt.split(".");
						}
						$('#bookingId').html(result.data.bookingId);
						$('#bookingDt').html(date[0]);
						$('#ghName').html(result.data.cityName);
						$('#blockName').html(result.data.ghName);
						$('#fromDt').html(result.data.fromDate);
						$('#toDate').html(result.data.toDate);
						$('#noRooms').html(result.data.noRooms);
						$('#noBeds').html(result.data.noBeds);
						$('#mobile').html(result.data.mobile);
						$('#purpose').html(data_row.purpose);
						$('#category').html(result.data.category);
						$('#catgType').html(result.data.catgType);
						$('#desgignation').html(result.data.desg);
						$('#officeAddress').html(result.data.office);
						$('#convence').html(result.data.conveyance);
						$("#pdfPrintBtn").attr("href", './guest/getPdfPrintView/' + result.data.bookingId + '.do');
					}else{
						$('#printForm').html('');
					}
				}else{
					swal(result.msg);
				}
			}
		});
	})
	
	$('#guestDataTable').on('click', 'tbody #paymentReceipt', function () {
		$('#myModalViewForPayment').modal('show');
		let current_datetime = new Date()
		let formatted_date = current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear()
		var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		$('#paymentReceiptDetails').html(data_row.ghName + "Receipt");
		$('#loading').show();
		$.ajax({
			url: './guest/getPaymentReceipt/'+data_row.bookingId + ".do",
			type: "GET",	
			headers : {"X-CSRF-TOKEN" : token},		 
			success:function(result) {
				$('#loading').hide();
				if(result.success){
					if(result.data != null){
						if(result.data.receiptId != null){
							$('#receiptNo').html('Receipt No ' + result.data.receiptId)
						}
						$('#dateFormat').html('Date ' + (result.data == null ? formatted_date : result.data.recDate));
						$('#guestName').html(result.data.guestName == null ? '' : result.data.guestName);
						$('#payRuppess').html(result.data.amount == null ? '' : result.data.amount);
						var textRuppess = inWords(result.data.amount == null ? '' : result.data.amount);
						$('#inrupees').html(' (Rupees ' + (textRuppess == null ? '' : textRuppess) + ')');
						$('#mode').html(result.data.mode == null ? '' : result.data.mode);
						$('#inrPrint').html('INR' + (result.data.amount == null ? '' : result.data.amount));
						$("#pdfPrintBtnForPayment").attr("href", './guest/getPdfPaymentReceipt/' + data_row.bookingId + '.do');
					}else{
						$('#printFormPayment').html('');
					}
				}else{
					swal(result.msg);
				}
			}
		});
	})

	var submitBookingDetails = function(type){
		var formData = $('#existingBookingApplication').serializeArray();
		var rawGuestDetails = formData.filter(d=>d.name.match(/guest/g));
		var otherDetails = formData.filter(d=>!d.name.match(/guest/g));
		var guestDtls = convertingGuesetDetailsObjectToArrayOfObjects(rawGuestDetails);
		console.log(guestDtls);
		var fd = new FormData();
		fd = convertSerializeArrayToFd(otherDetails);
		fd.append("guestDtlsStr", JSON.stringify(guestDtls));
		fd.append("bookingId", bookingReqId);
		if(suppDoc!=null){fd.append("suppDoc", suppDoc, suppDoc.name)};
		if(catgDoc!=null){fd.append("catgDoc", catgDoc, catgDoc.name)};
		$.ajax({
			url: "./guest/saveBookingDetails/update/" + type + ".do",
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
					$('#applicationFormModal').modal('hide');
					swal(data.msg);
					getNomineeRequestList(table);
				} else {
					swal(data.msg);
				}
			},error:function(jqXHR, status, error){
				swal(error);
			}
		});
	};

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
					notEmpty: {
						message: 'select a valid option'
					},
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
	}).on('status.field.bv', function(e, data) {
		if (data.bv.getSubmitButton()) {
			data.bv.disableSubmitButtons(false);
		}
	}).on('success.form.bv', function (e) {
		e.preventDefault();
		var status = guestValidation(guestCount)
		if(status){
			var type = $('#roomTyp').val();
			if(type == 'onlyRoom' || type == 'R'){
				submitBookingDetails('R');
			}else if(type == 'C'){
				submitBookingDetails('C');
			}else{
				submitBookingDetails('RC');
			}
		}else{
			console.log('Test')
			$('#applicationUpdateBtn').removeAttr("disabled");
		}
	});

	$('#conDataTable').on('click', 'tbody #view', function () {
		$('#applicationFormModal').modal('show');
		$('#applicationHeader').html('View Form')
		$('#chec').hide();
		$('#convFrDtls').show();
		$('#convToDtls').show();
		$('#reqDetails').hide();
		$('#GuestDetails').show();
		var data = contable.row($(this).closest('tr')).data() != undefined ? contable.row($(this).closest('tr')).data() : contable.row($(this)).data();
		guestCount=0; $('#guestDetailsWrapper').html("");
		blockId = data.cityName; 
		setGuestDetails(data.bookingId, true);
	});

	$('#conDataTable').on('click', 'tbody #edit', function () {
		$('#applicationFormModal').modal('show');
		$('#chec').hide();
		$('#convFrDtls').show();
		$('#convToDtls').show();
		$('#reqDetails').hide();
		$('#GuestDetails').show();
		var data = contable.row($(this).closest('tr')).data() != undefined ? contable.row($(this).closest('tr')).data() : contable.row($(this)).data();
		blockId = data.cityName; 
		guestCount=0; $('#guestDetailsWrapper').html("");
		setGuestDetails(data.bookingId, false);
	});

	$('#conDataTable').on('click', 'tbody #cancel', function () {
		var data_row = contable.row($(this).closest('tr')).data() != undefined ? contable.row($(this).closest('tr')).data() : contable.row($(this)).data();
		console.log(data_row);
		swal({
			title: "Are you sure? ",
			text: "Are you sure to cancel!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, cancel it!",
			closeOnConfirm: false
		},
		function(){
			$('#loading').show();
			$.ajax({
				url: './guest/cancelRequest/'+data_row.bookingId + ".do",
				type: "GET",	
				headers : {"X-CSRF-TOKEN" : token},		 
				success:function(result) {
					$('#loading').hide();
					if(result.success){
						swal(result.msg);
						getNomineeRequestList(table,contable);
					}else {
						swal(result.msg);
					}
				}
			});
		});
	})

	$("#applicationFormModal").on("hidden.bs.modal", function(){
		$(this).find('form').trigger('reset');
		$('#existingBookingApplication').bootstrapValidator('resetForm', true); 
	});
	
	var tableHelpCategory = $("#guestCatDataTable").removeAttr('width').DataTable({  
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
	
	$("#myModalViewForCategory").on("hidden.bs.modal", function(){
		$(this).find('form').trigger('reset');
	});
	
	$("#myModalView").on("hidden.bs.modal", function(){
		$(this).find('form').trigger('reset');
	});
	
	$('#guestCatDataTable').on('click', 'tbody #cateType', function () {
		$('#myModalViewForCategory').modal('hide');
		var data_row = tableHelpCategory.row($(this).closest('tr')).data();
		getCatgoryListDtls(data_row.desc);
		getDesignationListDtls(('C' + data_row.desc),data_row.id);
//		getGuestDesignationDetails(data_row.id);
	});
	
	$('#view').click(function(){
		$('#myModalViewForCategory').modal('show');
		$.ajax({
			url: './guest/getDesgList.do',
			type: "GET",	
			headers : {"X-CSRF-TOKEN" : token},		 
			success:function(result) {
				tableHelpCategory.clear().draw();
				tableHelpCategory.rows.add(result).draw();
				tableHelpCategory.columns.adjust().responsive.recalc();
			}
		});
	});
})

/**************************************************
 *   Get The PEDNING REQUESTS FROM ADDRESS ERROR MARK
 ***************************************************/
function getNomineeRequestList(table,contable){
	var id = $('#blockId').val();
	console.log(id);
	var reqType = $('#reqT').val();
	console.log('Value ' + reqType);
	if(id == ''){
		if(reqType != ''){
			id = reqType;
		}
	}
	if(reqType != ''){
		if(reqType == 'con'){
			table = contable;
			$('#conReq').show();
			$('#vehReq').hide();
		}else{
			$('#conReq').hide();
			$('#vehReq').show();
		}
	}
	$.ajax({
		url: './guest/getUserRequestList/' + id + ".do",
		type: "GET",	
		headers : {"X-CSRF-TOKEN" : token},		 
		success:function(result) {
			table.clear().draw();
			table.rows.add(result.data).draw();
			table.columns.adjust().responsive.recalc();
		}
	});
}

function guestValidation(guestCount){
	console.log('guestCount' + guestCount);
	if(guestCount>=1){
		for (i=1; i <= guestCount; i++) {
			console.log('Guest1' + $('#guestName' + i).val())
			if($('#guestName' + i).val() == ''){
				swal("Please Enter Guest Name!");
				return false;
			}else if($('#guestAge' + i).val() == ''){
				swal("Please Enter Guest Age!");
				return false;
			}else if($('#guestGender' + i).val() == ''){
				swal("Please Enter Guest Gender!");
				return false;
			}else if($('#guestAddr' + i).val() == ''){
				swal("Please Enter Guest Address!");
				return false;
			}
		}
	}
	return true;
}

var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];

function inWords(num) {
    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'Only ' : '';
  
   return str;
	
}
