$(document).ready(function(){
	
	$("#startDt, #endDt").datetimepicker({
		minDate: new Date(),
		format: 'DD-MM-YYYY'
	});
	
	isEdit = false;
	var table = $("#UserNoticeDetails").DataTable({   	
		"paging":  true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": true,
		"bFilter": true,
		"searching": true,
		"scrollCollapse": true,
		"columnDefs":[
			{"className": "dt-center", "targets": "_all"}, 
			{"targets":0,"data":"id"},
			{"targets":1,"data":"subject"},
			{"targets":2,"data":"desc"}, 
			{"targets":3,"data":"startDt"},
			{"targets":4,"data":"endDt"},
			{"targets":5,"data":"status"},
			{"targets":6,"data": null, 
				"defaultContent": '<button type="button" action="deleteNotice" id="deleteNotice" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],

	});
	getNoticeTableData(table);
	getAllCity();

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addNotice').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var cityname = $("#city_name").val();
		var ghid = $("#gh_name").val();
		var noticesub= $("#notice_subject").val();
		var id= $("#noticeId").val();
		var noticedesc= $("#notice_desc").val();
		var startDt= $("#startDt").val();
		var endDt= $("#endDt").val();
		
		var Data =
		{
				subject: noticesub,
				desc: noticedesc,
				startDt: startDt,
				type:type,
				id:id,
				ghId: cityname,
				blockId: ghid,
				endDt: endDt
		};

		if(noticesub==''){
			swal("Please Enter Notice Subject!");
			$("#notice_subject").focus();
		}else if(noticedesc==''){
			swal("Please Enter Notice Description!");
			$("#notice_desc").focus();
		}else if(startDt==''){
			swal("Please Select Start Date!");
			$("#startDt").focus();
		}else if(endDt==''){
			swal("Please Select End Date!");
			$("#endDt").focus();
		}else{
			$.ajax({
				url: "./mdm/saveNotice.do",
				type: "POST",
				contentType : 'application/json',
				dataType: "json",
				data: JSON.stringify(Data),
				beforeSend: function(xhr) {
					$('#loading').show();
					xhr.setRequestHeader(header, token);
				},
				success:function(result) {
					$('#loading').hide();
					if(result.success){
						swal(result.msg,"","success");
						resetFormCreateNotice();
						getNoticeTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateNotice();
						getNoticeTableData(table);	
					}
				},
				error:function(jqXHR, status, error){
					$('#loading').hide();
					swal(error);
				}
			});
		}
	}) ; 

	/*********************************************
	 * Edit button click function
	 *********************************************/
	$('#UserNoticeDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#notice_subject").val(data.subject);
		$("#notice_desc").val(data.desc);
		$("#startDt").val(data.startDt);
		$("#endDt").val(data.endDt);
		$("#noticeId").val(data.id);
		$('#addNotice').html('Update');
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserNoticeDetails tbody').on( 'click', 'button[action=deleteNotice]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				id: data.id,
				type: data.status
		};
		
		$.ajax({
			url: "./mdm/deleteNotice.do",
			type: "POST",		
			contentType : 'application/json',
			dataType: "json",
			data: JSON.stringify(Data),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success:function(result) {
				$('#loading').hide();
				if(result.success){
					getNoticeTableData(table);
					swal(result.msg,"","success");
				}else{
					swal(result.msg,"","error");	
				}
			},
			error:function(jqXHR, status, error){
				swal(error);
			}
		});
	});
	
	$('#city_name').on('change', function() {
		var id = $('#city_name').val();
		getAllBlock(id,'');
	});
})

function getNoticeTableData(table){
	$.ajax({
		url: "./mdm/getNoticeDetails.do",
		type: "GET",		
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			table.clear().draw();
			table.rows.add(result.data).draw();
		}
	});
}

//get all district details
var getAllCity = function getAllCity() {	
	$.ajax({
		url: "./mdm/getCityDropDown.do",
		type: "GET",	
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			var stateList = result.data;
			$('#city_name').html("");
			$('#city_name').empty();
			if(stateList.length == 1){
				stateList.forEach(option=>{
					$("#city_name").append('<option value="'+option.ghId+'">'+option.ghName+'</option>').addClass('disableElement');
				})
			}else{
				$("#city_name").append('<option value="">---Select---</option>');
				stateList.forEach(option=>{
					$("#city_name").append('<option value="'+option.ghId+'">'+option.ghName+'</option>');
				})
			}
			
			if(stateList.length == 1){
				if(result.gh != null){
					var ghList = result.gh;
					$("#gh_name").html("");
					if(ghList.length == 1){
						ghList.forEach(option=>{
							$("#gh_name").append('<option value="'+option.cityId+'">'+option.cityName+'</option>').addClass('disableElement');;
						})
					}
				}else{
					var id = $("#city_name").val();
					getAllBlock(id,'');
				}
			}else{
				$("#gh_name").html("");
				$("#gh_name").append('<option value="">---Select---</option>');
			}
		},
		error:function(jqXHR, status, error){
			(error);
		}
	});
}

var getAllBlock = function getAllBlock(id,value) {	
	console.log("Id " + id + " Value " + value);
	$.ajax({
		url: "./mdm/" + id +"/getGhDropDown.do",
		type: "GET",		
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			$("#gh_name").html("");
			$("#gh_name").append('<option value="">---Select---</option>');
			result.data.forEach(option=>{
				if(option.cityId == value){
					$("#gh_name").append('<option value="'+option.cityId+'" selected="selected">'+option.cityName+'</option>');
				}else{
					$("#gh_name").append('<option value="'+option.cityId+'">'+option.cityName+'</option>')
				}
			})
		}
	});
}

var resetFormCreateNotice = function(){
	isEdit = false;
	$("#city_name").val(''); 
	$("#gh_name").val('');
	$("#notice_subject").val('');
	$("#notice_desc").val('');
	$("#startDt").val('');
	$("#endDt").val('');
	$("#noticeId").val('');
	$('#addNotice').html('Add'); //default value
}


