
$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var blockId = 0;
	var catgDoc = null;
	var gDoc = null;
	var overAll = null;
	var blockName = null;
	var mobile = null;
	var totalRoomsWithBloCkName=null;
	$('#filter-popover').on('click', function() {
		event.stopPropagation();
	});

	$('#filter-popover-for-feedbackModal').on('click', function() {
		event.stopPropagation();
	});

	$('#filter-popover-for-guestFeedback').on('click', function() {
		event.stopPropagation();
	});

	$('#filter-popover-for-totalFeedback').on('click', function() {
		event.stopPropagation();
	});

	$('#filter-popover-commentreport').on('click', function() {
		event.stopPropagation();
	});

	$("#extfromDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
	});

	$("#extToDate").datetimepicker({
		useCurrent: false,
		format: 'DD-MM-YYYY'
	});

	//validation of form dates and todate
	 $("#extfromDate").on("dp.change", function (e) {
		 console.log("Hyyy");
	     $("#extToDate").data("DateTimePicker").minDate(e.date);
	  });
	 
	 $("#extToDate").on("dp.change", function (e) {
		 console.log("Hyyy");
	     $("#extfromDate").data("DateTimePicker").maxDate(e.date);
	  }); 
	 
	$(".selectBetweenDates").hide();
	$(".selectYearAndMonth").hide();

	$("input[name=radioBox]:radio").click(function() {
		if ($('input[name=radioBox]:checked').val() == "date") {
			$('#monthForAllot').val('');
			$('#yearForAllot').val('');
			$('.selectYearAndMonth').hide();
			$('.selectBetweenDates').show();

		} else if ($('input[name=radioBox]:checked').val() == "year") {
			$('#extfromDate').val('');
			$('#extToDate').val('');
			$('.selectYearAndMonth').show();
			$('.selectBetweenDates').hide();
		}
	});
	var ddlYears = $("#yearForAllot");

	//Determine the Current Year.
	var currentYear = (new Date()).getFullYear();
	//Loop and add the Year values to DropDownList.
	for (var i = currentYear; i >= 1950; i--) {
		var option = $("<option />");
		option.html(i);
		option.val(i);
		ddlYears.append(option);
	}
	//adding months to the dropdown for Total feedback report
	$('#monthForAllot').append(

		"<option value='01'>January</option>" +
		"<option value='02'>February</option>" +
		"<option value='03''>March</option>" +
		"<option value='04'>April</option>" +
		"<option value='05''>May</option>" +
		"<option value='06'>June</option>" +
		"<option value='07'>July</option>" +
		"<option value='08'>August</option>" +
		"<option value='09'>September</option>" +
		"<option value='10'>October</option>" +
		"<option value='11'>November</option>" +
		"<option value='12'>December</option>"
    );
    

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
		$('#city').trigger('change');
	}
	$('#city').on('change', function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
		getTotalAllotmentList();
	});
	$('#gHouse').on('change', function() {
		var id = $('#city').val();
		getTotalAllotmentList();
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


	var allotmentTabledfdfdf = $("#allotmentTbl").DataTable({
		"paging": false,
		"searching": false,
		"responsive": true,
		"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs": [
			{ className: "all", "targets": [0, 1, 2] },
			{ "targets": 0, "data": null,
				 render: function (data, type, row, meta) {
					 if(data.gh_name!==null){
		                 totalRoomsWithBloCkName = ""+data.block_name + ", " + data.gh_name;
					 }if(data.gh_name==null || data.gh_name==""){
						 totalRoomsWithBloCkName ='<b>'+data.block_name+'</b>';
					 }
		              return totalRoomsWithBloCkName;
				 }
			},
			{ "targets": 1, "data": "totalApplicationOnline" },
			{ "targets": 2, "data": "totalApplicationOffline" },
			{ "targets": 3, "data": "totalAllotOfficial" },
			{ "targets": 4, "data": "totalAllotNonOfficial" },
			{ "targets": 5, "data": "totalAllotStudent" },
			{ "targets": 6, "data": "totalAllotPatient" },
			{ "targets": 7, "data": "actualOccupancy" },
			{ "targets": 8, "data": "occupancyPercentage" }
		],
	});
	var getTotalAllotmentList = function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var fDate = $('#extfromDate').val();
		var tDate = $('#extToDate').val();
		var yearr = $('#yearForAllot').val();
		var monthh = $('#monthForAllot').val();
		console.log("fDate    " + fDate + "    tDate   " + tDate);
		console.log("city" + city + "gHouse" + gHouse);
		$.ajax({
			url: "./admin/getTotalAllotmentList.do",
			type: "GET",
			contentType: "application/json",
			dataType: "JSON",
			data: {
				cId: city,
				gId: gHouse,
				fromDate: fDate,
				toDate: tDate,
				year: yearr,
				month: monthh
			},beforeSend: function (xhr) {
                $('#loading').show();
            },
			success: function(result) {
				$('#loading').hide();
				console.log(result.data);
				allotmentTabledfdfdf.clear().draw();
				allotmentTabledfdfdf.rows.add(result.data).draw();

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
	getTotalAllotmentList();
	$('#filterSearch').on('click', function() {
		console.log("inside feedback");
		var fromDate = $('#extfromDate').val();
		var toDate = $('#extToDate').val();
		var yearr = $('#yearForAllot').val();
		var monthhh = $('#monthForAllot').val();
		if(fromDate != "" && toDate != ""){
			$('#fiterInfo').html(" From "+fromDate+" to "+toDate);
		}
		else if(yearr != "" && monthhh != ""){
			function getMonthName(monthhh) {
				  const date = new Date();
				  date.setMonth(monthhh - 1);

				  return date.toLocaleString('en-US', { month: 'long' });
			}
			$('#fiterInfo').html(" on "+getMonthName(monthhh)+"-"+yearr);
		}
		if ($('input[name=radioBox]:checked').val() == "date") {
			if (fromDate != "" && toDate == "") {
				swal("Please provide to date");
				return false;
			}
			if (fromDate == "" && toDate != "") {
				swal("Please provide From Date");
				return false;
			}
			if(fromDate == "" && toDate == ""){
				swal("Please provide dates the information");
			}
		}
		if ($('input[name=radioBox]:checked').val() == "year") {
			if (yearr != "" && monthhh == "") {
				swal("Please provide Month");
				return false;
			}
			if (yearr == "" && monthhh != "") {
				swal("Please provide year");
				return false;
			}
			if(yearr == "" && monthhh == ""){
				swal("Please provide Month and year");
			}
		}
		console.log($('input[name=radioBox]:checked').val());
		 if (!$('input[name=radioBox]:checked').val()) {
		       swal("Please select search by dates or year");
		        return;
		 }
		getTotalAllotmentList();
		$('#filter-popover-for-feedbackModal').unbind();
    	setTimeout(function(){
    		$('#filter-popover-for-feedbackModal').bind('click',preventClick);
    	}, 1000);
	});
	
	function preventClick() {
	   event.stopPropagation();
	}
   
	$('#printBtn').click(function() {
		var city = $('#city').val();
		var gHouse = $('#gHouse').val();
		var frDt = $('#extfromDate').val();
		var toDt = $('#extToDate').val();
		var yearForAllotPDF = $('#yearForAllot').val();
		var monthForAllotPDF = $('#monthForAllot').val();
	
		console.log("city   " + city + "    ghouse     " + gHouse);
		console.log("frDt   " + frDt + "    toDt     " + toDt);
		if (city == '') {
			city = 'NA';
		}
		if (gHouse == '') {
			gHouse = 'NA';
		}
		if (frDt == '' || toDt == '') {
			frDt = 'NA';
			toDt = 'NA';
		}
		if (yearForAllotPDF == '' && monthForAllotPDF == '') {
			yearForAllotPDF = 'NA';
			monthForAllotPDF = 'NA';
		}
		window.open('./admin/downloadAllotmentReportInPDF/' + city + '/' + gHouse + '/' + frDt + '/' + toDt + '/' + yearForAllotPDF + '/' + monthForAllotPDF + '.html', '_blank');
	})
	
	 $('#allotmentResetBtn').click(function () {
		 $('#city').val('');
	        $('#gHouse').val('');
	        $('#extfromDate').val('');
	        $('#extToDate').val('');
	        $('#yearForAllot').val('');
	        $('#monthForAllot').val('');
	        getTotalAllotmentList();
	 });
	//to get the current date
	var currentDate = new Date();
    var day = String(currentDate.getDate()).padStart(2, '0');
    var month = String(currentDate.getMonth() + 1).padStart(2, '0');
    var year = currentDate.getFullYear();
    var formattedDate = day + '-' + month + '-' + year;
	$('#fiterInfo').html(" From 06-01-2020 to "+formattedDate);
});





