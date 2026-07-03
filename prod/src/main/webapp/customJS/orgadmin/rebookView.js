$('document').ready(function() {

	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		$($.fn.dataTable.tables(true)).DataTable().columns.adjust()
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

	var appData;
	var blockId = 0;

	var guestCount = 0;
	var guestDelIds = [];
	var guestParameters = 7;
	var bookingReqId = null;
	var guestList;
	var suppDoc = null;
	var catgDoc = null;
	var gDoc = null;
	var typeOfRoom = null;

	var convertingGuesetDetailsObjectToArrayOfObjects = function(data) {
		console.log(data);
		var array = [];
		var count = data.length / guestParameters;
		console.log(data.length);
		for (var i = 0; i < count; i++) {
			array.push(convertSerializeArrayToObject(data.slice((i * guestParameters), ((i + 1) * guestParameters))));
		}
		return array;
	}
	$("#filterBookingDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
	$("#filterFromDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
		// format: 'YYYY-MM-DD'
	});
	
	
	var table = $("#reBookDataTable").DataTable({
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
			"url": "./admin/getRebookingList.do",
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
				d.bookingDate = $('#filterBookingDate').val();
				d.frmDate = $('#filterFromDate').val();
				d.purpose = $('#filterPurpose').val();
				d.mobNo = $('#filterMobNum').val();
				d.name = $('#filterGuestName').val();
				d.category = $('#filterCategory').val();
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
				"targets": 0,
				"data": null,
				"width": "5%",
				"className": "text-center text-nowrap",
				render: function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{ "width": "5%", "targets": 1, "data": "bookingId", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 2, "data": "name", "className": "text-left text-nowrap" },
			{ "width": "10%", "targets": 3, "data": "fromDate", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 4, "data": "toDate", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 5, "data": "category", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 6, "data": "purpose", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 7, "data": "mobile", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 8, "data": "typeOfRoom", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 9, "data": "reqDate", "className": "text-center text-nowrap" },
			{ "width": "10%", "targets": 10, "data": "modeOfCancelDesc", "className": "text-center text-nowrap" },
			{
				"width": "10%",
				"targets": 11,
				"data": null,
				"className": "text-center text-nowrap",
				render: function(data, type, row, meta) {
					return '<button type="button" id="saveRebook" title="Revert Cancellation" action="saveRebook" class="btn btn-sm btn-edit btn-save-mod mr-1"><i class="fa fa-undo" aria-hidden="true"></i> </button>';
				}
			}
		]
	});
	
	/*table.ajax.reload();*/
	
	$('#reBookDataTable').on('click', 'tbody #saveRebook', function () {
        var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
        swal({
                title: "Are you sure? ",
                text: "Do you want to revert the booking cancellation?",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, revert it!",
                closeOnConfirm: false
            },
            function () {
                $('#loading').show();
                $.ajax({
                    url: './admin/revertCancellationByBookingId/' + data_row.bookingId + ".do",
                    type: "POST",
                    headers: {"X-CSRF-TOKEN": token},
                    success: function (data) {
                        $('#loading').hide();
                        console.log(data);
						swal(data.msg);
                    }
                });
				table.ajax.reload();
            });
    });

	var getGuestHouseListDtls = function(id) {
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
	
	$('#filterCategory').append(
		"<option value='C1'>Category1</option>" +
		"<option value='C2'>Category2</option>" +
		"<option value='C3'>Category3</option>" +
		"<option value='C4'>Category4</option>"
	);

	$('#filterPurpose').append(
		"<option value='1'>Official</option>" +
		"<option value='2'>Private</option>" +
		"<option value='3'>Patient</option>" +
		"<option value='4'>Student</option>"
	);

	$('#filterBookingId').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});
	
	$('#filterSearch').click(function() {
		table.ajax.reload();
		$('#filter-popover').unbind();
		setTimeout(function() {
			$('#filter-popover').bind('click', preventClick);
		}, 1000);
	});

	function preventClick() {
		event.stopPropagation();
	}

	$('#resetFiterBtn').click(function() {
		$('#filterBookingId').val('');
		$('#filterBookingDate').val('');
		$('#filterFromDate').val('');
		$('#filterPurpose').val('');
		$('#filterMobNum').val('');
		$('#filterRoomType').val('');
		$('#filterGuestName').val('');
		$('#filterCategory').val('');
		table.ajax.reload();
	});
	
	$("#city").change(function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
		table.ajax.reload();
	});
	$("#gHouse").change(function() {
		table.ajax.reload();
	});
	
});