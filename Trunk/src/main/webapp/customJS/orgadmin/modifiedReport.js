$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var blockId = 0;
	var catgDoc = null;
	var gDoc = null;
	var overAll = null;
	var blockName = null;
	var mobile = null;

	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});		

	var cityData = $('#cityUserlocssd').val().trim();
	var cityDetails = JSON.parse(cityData);
	$("#city").html("");
	$("#city").append('<option value="">--Select City--</option>');
	cityDetails.forEach(option => {
		$("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
	});

	let size = Object.keys(cityDetails).length;
	if (size == 1) {
		$('#city').val(cityDetails[0].gh_id);
		setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}
	$('#city').on('change', function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
		/*getModifiedAllotmentList();*/
		modifiedTable.ajax.reload();
	});
	$('#gHouse').on('change', function() {
		var id = $('#city').val();
		/*getModifiedAllotmentList();*/
		modifiedTable.ajax.reload();
	});
	var getGuestHouseListDtls = function(id) {
		blockId = id;
		$.ajax({
			url: "./admin/getGuestHouseListDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function() {
				$('#loading').show();
			},
			success: function(data, textStatus, jqXHR) {
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
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}


	var modifiedTable = $("#modifiedReportDataTable").DataTable({
		"paging": true,
		"searching": false,
		"scrollX": true,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": true,
		"info": false,
		"bFilter": false,
		"autoWidth": true,
		"bLengthChange": false,
		"fixedHeader": {
			header: true,
			footer: true
			},
		"scrollCollapse": true,
		"serverSide": true,
		"ajax": {
					"url": "./admin/getModifiedAllotmentList.do",
					"type": "POST",
					"beforeSend": function(xhr) {
						$('#loading').show();
						xhr.setRequestHeader(header, token);
					},
					"complete": function() {
						$('#loading').hide();
					},
					"data": function(d) {
						d.city = $('#city').val();
						d.gHouse = $('#gHouse').val();
						d.bookingId = $('#filterBookingId').val();
						d.mobNo = $('#filterMobNum').val();
        				d.name = $('#filterGuestName').val();
					},
					"error": function (jqXHR, textStatus, errorThrown) {
				            // Hide the loading indicator
				            $('#loading').hide();
				            if (jqXHR.status === 400) {
				                // If status 400 (Bad Request), show custom Swal error
				                swal("Invalid Request");
				            } 
				    }
				},
		"columnDefs": [
			{
	        "targets": 0, "data": null, render: function (data, type, row, meta) {
	            return meta.row + meta.settings._iDisplayStart + 1;
	        },
	        },{
	            "targets": 1, "data": "bookingId"
	        }, {
	            "targets": 2, "data": "name"
	        }, {
	            "targets": 3, "data": "fromDate"
	        }, {
	            "targets": 4, "data": "toDate"
	        }, {
	            "targets": 5, "data": "prevOrderNo"
	        },{
	            "targets": 6, "data": "roomNo"
	        },{
	            "targets": 7, "data": "roomRent"
	        },{
	            "targets": 8, "data": "modifiedRoomNo"
	        },{
	            "targets": 9, "data": "modifiedRoomRent"
	        },{
	            "targets": 10, "data": "reason"
	        }	        
        ],
	});
	
	$('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });    
    
    $('#filterSearch').click(function () {        
    	/*getModifiedAllotmentList();*/
		modifiedTable.ajax.reload();
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
        $('#filterMobNum').val('');
        $('#filterGuestName').val('');
        $('#city').val('');
        $('#gHouse').val('');
        /*getModifiedAllotmentList();*/
		modifiedTable.ajax.reload();
    });
	
	/*var getModifiedAllotmentList = function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		let bookingId = $('#filterBookingId').val();
		let mobNo = $('#filterMobNum').val();
        let name = $('#filterGuestName').val();
		$.ajax({
			url: "./admin/getModifiedAllotmentList.do",
			type: "GET",
			contentType: "application/json",
			dataType: "JSON",
			data: {
				cId: city,
				gId: gHouse,
				bookingId: bookingId,
				mobNo:mobNo,
	            name:name
			},
			success: function(result) {
				console.log(result.data);
				modifiedTable.clear().draw();
				modifiedTable.rows.add(result.data).draw();

			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});

	}*/
	/*getModifiedAllotmentList();*/
	modifiedTable.ajax.reload();
	
	
});





