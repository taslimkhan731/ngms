$(document).ready(function() {
	
	$("#Loginmodal").modal(); // login Modal Open
	$(window).on("load", function() {
		$(".modalView").hide();
	});
	var otpScreen = function() {
		$('#reqType').val('otpbased');
		$("#getOtpBtn").show();
		$("#otpEntry").hide();
		$("#passwordEntry").hide();
		$("#captchaScreen").hide();
		$("#btnCheck").hide();
		$("#fileData").show();
		$("#fileData1").show();
	}
	var passwordScreen = function() {
		$('#reqType').val('register');
		$("#getOtpBtn").hide();
		$("#otpEntry").hide();
		$("#passwordEntry").show();
		$("#captchaScreen").show();
		$("#btnCheck").show();
		$("#fileData").hide();
		$("#fileData1").hide();
	}
	var suppDoc = null;
	$('#customFile').change(function(e){
		var file = e.target.files[0];
        if(file!=undefined){
        	var size = Math.round((file.size / (1024 * 1024)));
        	if(size > 2){
        		alert('Identity document should not be greater than 2 mb');
        		$('#supportDocName').html('Upload proof (image/pdf)');
        		$('#supportDoc').val('');
        	}else{
        		suppDoc = file;
            	$('#supportDocName').html(suppDoc.name);
        	}
        } else {
        	$('#supportDocName').html('Upload proof (jpg/pdf)');
        }
	});
	
	$('input[type=radio][id=loginType1]').prop('checked',true);
	otpScreen();
	/* Form validation */
	$('#frmLogin').bootstrapValidator({
		framework : 'bootstrap',
		row : {
			valid : 'field-success',
			invalid : 'field-error'
		},
		fields : {
			password : {
				validators : {
					notEmpty : {
						message : 'The password can\'t be Empty'
					},
				}
			},
			captcha : {
				validators : {
					notEmpty : {
						message : 'The Captcha can\'t be Empty'
					},
				}
			},
			otp : {
				validators : {
					notEmpty : {
						message : 'otp can\'t be Empty'
					},
				}
			},
			j_username : {
				validators : {
					notEmpty : {
						message : 'User Id is required and cannot be Empty.'
					},

					regexp : {
						regexp : /^[a-zA-Z0-9_@.-]+$/,
						message : 'Username must contain only letters, numbers and special characters like (@,_,.,-)'
					}

				}
			},
			docType : {
				validators : {
					notEmpty : {
						message : 'Document Type is required and cannot be Empty.'
					},

				}
			},
			customFile : {
				validators : {
					notEmpty : {
						message : 'file  is required and cannot be Empty.'
					},

				}
			},
		}
	})

	$('input[type=radio][name=radio]').change(function() {
		if (this.value == '2') {
			otpScreen();
		} else if (this.value == '1') {
			passwordScreen();
		}
	});

	$("#getOtpBtn").click(function() {
		var userId = $("#j_username").val();
		/*var customFile=$("#customFile").val();*/
		if(userId == ''){
			alert('mobile number should not be blank');
			return false;
		}
		/*if(customFile == ''){
			alert('file should not be blank');
			return false;
		}*/
		var form = $('#frmLogin')[0];
        var podata = new FormData(form);
        //if(suppDoc!=null){podata.append("suppDoc", suppDoc, suppDoc.name)};
        console.log(podata);
		
		$.ajax({
			url : "./otpForLogin.do",
			type : "POST",
			//enctype: 'multipart/form-data',
	       processData: false,  // Important!
	        contentType: false,
			beforeSend: function(xhr) {
				$(".modalView").show();
				xhr.setRequestHeader(header, token);
			},
			data: podata,
			success: function(data) {
				$(".modalView").hide();
				if (data.status) {
					swal(data.message);
					$('#getOtpBtn').hide();
					$('#otpEntry').show();
					$("#btnCheck").show();
					
				}else{
					
					swal(data.message);
					
				}
			},
			error:function(jqXHR, status, error){
				console.log(error);
				swal(error);
			}
		});
	})
	/* Captcha validation */

	$('#captchaImgDtls').mousedown(function(e) {
		e.preventDefault();
	});
	var getCaptchaCode= function() {
		$('#captchaImgDtls').attr('src','./captcha/getCaptcha.html?'+Math.random());
	}
	$("#captchaBtnReload").click(function(){
//		alert(10);
		getCaptchaCode();
	})

	$("#captcha").bind("paste", function(e) {
		e.preventDefault();
	});
	
	var table = $("#guestHouseViewForOrder").DataTable({  
		"responsive": true,
		"processing": false,
		"ordering": true,
		"info": false,
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
			{"targets":1,"data":"ghName"},
			{"targets":2,"data": null, 
				render: function (data, type, row, meta) {
					return '<button type="button" id="select" title="Select" action="Select" class="btn btn-sm btn-edit btn-danger">Select</button>' 
				}
			}
			], 	  
	});	
	/*getGHRequestList(table);*/
	
	var tableOrder = $("#OrderCopy").DataTable({  
		"responsive": true,
		"processing": false,
		"ordering": true,
		"info": false,
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
			{"targets":1,"data":"userId"},
			{"targets":2,"data":"ghName"},
			{"targets":3,"data":"cityName"},
			{"targets":4,"data": null, 
				render: function (data, type, row, meta) {
					return '<a href="./guest/printOrderCopy.do?ghId=' + data.ghId + '&cityId=' + data.cityId + '&processId=' + data.userId + '"' + ' class="btn btn-danger" target="_blank" style="margin-left: 3rem;"><i class="fa fa-eye" aria-hidden="true"></i></a>' 
				}
			}
			], 	  
	});	
	
	$('#guestHouseViewForOrder').on('click', 'tbody #select', function () {
		$(".modalView").show();
		console.log("clicked");
		$('#myModalView').modal('show');
		var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		getProcessOrderList(tableOrder,data.cityId,data.ghId, data.ghName)
	})
	
})	

function changeform() {
	var condition = $("#reqType").val();
	var username = $("input[name=j_username]").val();
	if(condition == 'otpbased'){
		var password = $("input[name=otp]").val();
		var saltKey = $("input[name=saltKey]").val();
		var encSaltSHAPass = sha512LoginHash(password, saltKey);
		console.log(encSaltSHAPass)
		$("input[name=password]").val(encSaltSHAPass);// changed
		$("input[name=j_password]").val(encSaltSHAPass);// changed
	}else{
		var password = $("input[name=password]").val();
		var saltKey = $("input[name=saltKey]").val();
		// var encSaltPass = md5LoginHash(password,saltKey);
		var encSaltSHAPass = sha512LoginHash(password, saltKey);
		console.log(encSaltSHAPass)
		var password=$("input[name=password]").val(encSaltSHAPass);// changed
		var original=$("input[name=j_password]").val(encSaltSHAPass);// changed
		console.log(password);
		console.log(original);
		console.log(encSaltSHAPass);
	}
	return true;
}

/*function getGHRequestList(table){
	$.ajax({
		url: './getAllLocation.do',
		type: "GET",
		contentType:"application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},	 
		success:function(result,textStatus, jqXHR) {
			table.clear().draw();
			table.rows.add(result.data).draw();
		},error:function(jqXHR, status, error){
			swal(error);
		}
	});
}
*/
function getProcessOrderList(table,cityIdd,ghIdd, ghNamee){
	postData = {
			ghId : ghIdd,
			cityId: cityIdd,
			ghName: ghNamee
	}
	$.ajax({
		url: './getAllProcess.do',
		type: "POST",	
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: JSON.stringify(postData),
		contentType:"application/json",
		dataType:"JSON",	 
		success:function(result) {
			$(".modalView").hide();
			table.clear().draw();
			table.rows.add(result.data).draw();
		}
	});
	
}