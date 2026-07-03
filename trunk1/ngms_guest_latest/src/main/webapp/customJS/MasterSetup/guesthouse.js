$(document).ready(function(){
	//swal("44444")
	isEdit = false;
	var table = $("#UserGHDetails").DataTable({   	
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
			{"targets":0,"data":"ghId"},
			{"targets":1,"data":"ghName"},
			{"targets":2,"data":"cityName"}, 
			{"targets":3,"data":"totalRooms"},
			{"targets":4,"data":"totalFloor"},
			{"targets":5,"data": null, 
				"defaultContent": '<button type="button" action="deleteGH" id="deleteGH" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			'<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button></span></button>'}            

			],

	});
	getGHTableData(table);
	getAllCity();

	/* *******************************************
	 *    Form Data to the Create Group table
	 ***********************************************/

	$('#addGuestHouse').click(function(){
		var type = "";

		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}

		var cityid = $("#city_name").val();
		var ghname = $("#gh_name").val();
		var totfloor= $("#total_floor").val();
		var id= $("#blockId").val();
		var totrooms= $("#total_room").val();
		var Data =
		{
			cityId:cityid,
			ghName:ghname,
			totalFloor:totfloor,
			type:type,
			ghId:id,
			totalRooms: totrooms
		};

		if(cityid==''){
			swal("Please Select City Name!");
			$("#city_name").focus();
		}else if(ghname==''){
			swal("Please Guest House Name!");
			$("#gh_name").focus();
		}else if(totfloor==''){
			swal("Please Enter Number Of Floors!");
			$("#total_floor").focus();
		}else if(totrooms==''){
			swal("Please Enter Number Of Floors!");
			$("#total_room").focus();
		}else{
			$.ajax({
				url: "./mdm/saveUserGHTable.do",
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
						resetFormCreateGH();
						getGHTableData(table);
					}else{
						swal(result.msg,"","error");
						resetFormCreateGH();
						getGHTableData(table);	
					}
				},
				error:function(jqXHR, status, error){
					swal(error);
				}
			});
		}
	}) ; 

	/*********************************************
	 * Edit button click function
	 *********************************************/
	$('#UserGHDetails tbody').on( 'click', 'button[action=Edit]', function () {
		//swal('hii')
		var data = table.row( $(this).parents('tr') ).data();
		isEdit = true;
		$("#city_name").val(data.cityId); 
		$("#gh_name").val(data.ghName);
		$("#total_floor").val(data.totalFloor);
		$("#total_room").val(data.totalRooms);
		$("#blockId").val(data.ghId);
		$('#addGuestHouse').html('Update'); 
	});   

	/*********************************************
	 * Delete button click function
	 *********************************************/

	$('#UserGHDetails tbody').on( 'click', 'button[action=deleteGH]', function () {
		var data = table.row( $(this).parents('tr') ).data();
		var Data =
		{
				ghId: data.ghId
		};
		
		$.ajax({
			url: "./mdm/deleteCreateUserGH.do",
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
					getGHTableData(table);
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
})

function getGHTableData(table){
	$.ajax({
		url: "./mdm/getDataGHTable.do",
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

//get all city details
var getAllCity = function getAllCity() {	
	$.ajax({
		url: "./setting/getAllDistrict.do",
		type: "GET",	
		beforeSend: function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success:function(result) {
			$('#loading').hide();
			var cityList = result.data;
			var option = '<option value="">---SELECT----</option>';
			for(var i = 0;i<cityList.length;i++) {
				option += '<option value="'+ cityList[i].cityId + '">' + cityList[i].cityName + '</option>';
			}
			$('#city_name').html("");
			$('#city_name').empty();
			$('#city_name').append(option);
		},
		error:function(jqXHR, status, error){
			(error);
		}
	});
}

var resetFormCreateGH = function(){
	isEdit = false;
	$("#city_name").val(''); 
	$("#gh_name").val('');
	$("#total_floor").val('');
	$("#total_room").val('');
	$("#blockId").val('');
	$('#addGuestHouse').html('Add'); //default value
}


