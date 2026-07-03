$('document').ready(function() {
	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});
	isEdit = false;
	var x = [{
		"roomNo": "101",
		"name": "Amrita",
		"purpose": "xyz"
	}, {
		"roomNo": "102",
		"name": "Amrita",
		"purpose": "xyz"
	}, {
		"roomNo": "103",
		"name": "Amrita",
		"purpose": "xyz"
	}, {
		"roomNo": "104",
		"name": "Amrita",
		"purpose": "xyz"
	}, {
		"roomNo": "105",
		"name": "Amrita",
		"purpose": "xyz"
	}, {
		"roomNo": "106",
		"name": "Amrita",
		"purpose": "xyz"
	},]

	$("#occupancyList").show();

	$("#filterBookingDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
var table = $("#occupancyDataTable").DataTable({
		"paging": true,
		"searching": true,
		"scrollX": true,
		"lengthMenu": [[10, 20, 30, -1],
		[10, 20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs": [
			{
				"targets": 0,
				//"data": "roomNo"
				"data": function(row, type, val, meta) {
					return [row.roomNo, row.roomType, row.maxNoBeds];
				},
				"render": function(data, type, full, meta) {
					if (data[0] === null) {
						return "";
					} else{
						var roomNo = data[0];
						var roomType = data[1];
						var maxNoBeds = data[2];
						
						if(roomType == "D"){
							return roomNo +" ("+ maxNoBeds +" Beds)";
						} else{
							return roomNo;
						}
					}
				}
			},
			{
				"targets": 1,
				//"data": "name"
				"data": function(row, type, val, meta) {
					return [row.name, row.norReq, row.type,row.nodGreq];
				},
				"render": function(data, type, full, meta) {
					if (data[0] === null) {
						return "";
					} else {
						var name = data[0];
						var norReq = data[1];
						var type = data[2];
						var nodGreq = data[3];
						
						if(type=="R"){
							if(norReq=="1"){
								return name +" ("+norReq+"  Room)";
							}else{
								return name +" ("+norReq+"  Rooms)";
							}
							
						}else if(type=="MBG"){
							return name;
						}else{
							if(nodGreq=="1"){
								return name +" ("+nodGreq+"  Bed)";
							}else{
								return name +" ("+nodGreq+"  Beds)";
							}
						}
					
					}
				}
				
			},
			{
				"targets": 2,
				"data": function(row, type, val, meta) {
					return [row.checkInDate, row.toDate,row.type];
				},
				"render": function(data, type, full, meta) {
					if(data[2]=="MBG"){
						return data[0];
					}
					if (data[0] === null || data[1] === null) {
						return "";
					} else {
						var checInDate = new Date(data[0]);
						var toDate = new Date(data[1]);

						

						return data[0] + " - " + data[1];
					}
				}
			},
			{
				"targets": 3,
				"data": function(row, type, val, meta) {
					return [row.exName, row.exfromDate, row.expectType, row.exNob, row.exRoom, row.exToDate,row.reason,row.roomType];
				},
				"render": function(data, type, full, meta) {
				
					if(data[6]!=""&&data[6]!=null){
						return data[6];
					}else{
						return data[0];
					}
					/*if (data[0] === null || data[1] === null) {
						return "";
					} else {
						var exName = data[0];
						var expectType = data[2];
						var exNob = data[3];
						var exRoom = data[4];	
						var roomType=data[7];			
						if(roomType=="R" && expectType=="BG"){
							return exName + " (" + data[1] + " - "+ data[5] +")" + " (1 Bed)";
						}else if(expectType=="R"){
						 if(exRoom=="1"){
								return exName + " (" + data[1] + " - "+ data[5] +")" + " ("+exRoom+" Room)";
						}else{
								return exName + " (" + data[1] + " - "+ data[5] +")" + " ("+exRoom+" Rooms)";
						}
						}else{
							if(exNob=="1"){
								return exName + " (" + data[1] + " - "+ data[5] +")" + " ("+exNob+"  Bed)";
							}else{
								return exName + " (" + data[1] + " - "+ data[5] +")" + " ("+exNob+"  Beds)";
							}
						}
						return exName;
					}*/
				}
			}
		],
	});





	////////// Getting Cities and Guest House///////
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
		setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}

	$('#city').on('change', function() {
		var id = $('#city').val();
		console.log("City ID = " + id);
		getGuestHouseListDtls(id);
	});
	$('#gHouse').on('change',function(){
		$("#add").removeClass("custom-btn-active");
		$("#occupancy-list").html("");
	});
	var getGuestHouseListDtls = function(id) {
		$.ajax({
			url: "./admin/getGuestHouseListDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$('#loading').hide();
				$("#gHouse").html("");

				$("#gHouse").append('<option value="">--Select Guest House--</option>');
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

	$('#add').on('click', function() {
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		if (city == "") {
			swal("Please Select City");
			$("#add").removeClass("custom-btn-active");
			$('#occupancy-list').hide();
			return false;
		}
		else if (gHouse == "") {
			swal("Please Select Guest House");
			$("#add").removeClass("custom-btn-active");
			$('#occupancy-list').hide();
			return false;
		}
		else{
			$("#add").addClass("custom-btn-active");
			$('#occupancy-list').show();
		}

		var postData = {
			cId: city,
			gId: gHouse

		}
		$.ajax({
			url: './admin/getOccupancy.do',
			type: "POST",
			contentType: "application/json",
			dataType: "JSON",
			data: JSON.stringify(postData),
			beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result);
				table.clear();
				table.rows.add(result.data).draw();
				$('#loading').hide();
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
		return true;
	});


	/// Generate Occupancy PDF
	$('#printOcc').click(function() {
		let city = $('#city').val();
		let gHouse = $('#gHouse').val();
		if (city == "") {
			swal("Please Select City");
			return false;
		}
		if (gHouse == "") {
			swal("Please Select Guest House");
			return false;
		}
		window.open('./admin/getPdfPrintView/' + city + "/" + gHouse + '.do');
	})

});