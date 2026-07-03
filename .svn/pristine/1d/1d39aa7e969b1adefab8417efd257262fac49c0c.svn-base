$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var blockId = 0;
    var catgDoc = null;
    var gDoc = null;
    var overAll =  null;
    var blockName = null;
    var mobile = null;
    var isFeedbackReply = null;
    var isToDateEmpty  = null;
	  var isFromDateEmpty =null;
	  var isYearEmpty = null;
	  var isMonthEmpty = null;
    $('#guestFeedbackReportTable').hide();
    
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
    
    $('#filter-popover-for-feedbackModal').on('click', function () {
        event.stopPropagation();
    });
    
    $('#filter-popover-for-guestFeedback').on('click', function () {
        event.stopPropagation();
    });
    
    $('#filter-popover-for-totalFeedback').on('click', function () {
        event.stopPropagation();
    });
    
    $('#filter-popover-commentreport').on('click', function () {
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
    
       $("#extfromDateforTotalFeedback").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
    $("#extToDateforTotalFeedback").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    $("#textfromDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
    $("#textToDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
    $("#filterGuestFeedbackFrmDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
    $("#filterGuestFeedbackToDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
     $("#extfromDateforComment").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
    
     $("#extToDateforComment").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
 	//validation of form dates and todate
	 $("#extfromDate").on("dp.change", function (e) {
	     $("#extToDate").data("DateTimePicker").minDate(e.date);
	  });
	 
	 $("#extToDate").on("dp.change", function (e) {
	     $("#extfromDate").data("DateTimePicker").maxDate(e.date);
	  }); 
	 
	 
	 	//validation of form dates and todate
	 $("#extfromDateforTotalFeedback").on("dp.change", function (e) {
	     $("#extToDateforTotalFeedback").data("DateTimePicker").minDate(e.date);
	  });
	 
	 $("#extToDateforTotalFeedback").on("dp.change", function (e) {
	     $("#extfromDateforTotalFeedback").data("DateTimePicker").maxDate(e.date);
	  }); 
	 
	 
	 	//validation of form dates and todate
	 $("#filterGuestFeedbackFrmDate").on("dp.change", function (e) {
	     $("#filterGuestFeedbackToDate").data("DateTimePicker").minDate(e.date);
	  });
	 
	 $("#filterGuestFeedbackToDate").on("dp.change", function (e) {
	     $("#filterGuestFeedbackFrmDate").data("DateTimePicker").maxDate(e.date);
	  }); 
	 $('#bookingIdfilter').on('keyup', function() {
		this.value = this.value.toUpperCase();
	});
	 
	 	//validation of form dates and todate
	 $("#extfromDateforComment").on("dp.change", function (e) {
	     $("#extToDateforComment").data("DateTimePicker").minDate(e.date);
	  });
	 
	 $("#extToDateforComment").on("dp.change", function (e) {
	     $("#extfromDateforComment").data("DateTimePicker").maxDate(e.date);
	  }); 
	 
    //adding year to dropdown for Total feedback report
	var ddlYears = $("#yearForTotalFeedback");

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
	$('#monthForTotalFeedback').append(

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
    
     //adding year to dropdown for feedback report
	var ddlYears = $("#yearForFeedback");

	//Determine the Current Year.
	var currentYear = (new Date()).getFullYear();
	//Loop and add the Year values to DropDownList.
	for (var i = currentYear; i >= 1950; i--) {
		var option = $("<option />");
		option.html(i);
		option.val(i);
		ddlYears.append(option);
	}
	//adding months to the dropdown for feedback report
	$('#monthForFeedback').append(

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
    
     //adding year to dropdown for Comment report
	var ddlYears1 = $("#yearForCommentFeedback");

	//Determine the Current Year.
	var currentYearr = (new Date()).getFullYear();
	//Loop and add the Year values to DropDownList.
	for (var i = currentYearr; i >= 1950; i--) {
		var option = $("<option />");
		option.html(i);
		option.val(i);
		ddlYears1.append(option);
	}
	//adding months to the dropdown for Comment report
	$('#monthForCommentFeedback').append(

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
    
    $(".selectBetweenDates").hide();
    $(".selectYearAndMonth").hide();
    
    
    $("input[name='radioBox']").click(function () {
        if ($(this).val() === "date") {
            $('#yearForCommentFeedback').val('');
            $('#monthForCommentFeedback').val('');
            $('.selectYearAndMonth').hide();
            $('.selectBetweenDates').show();
        } else if ($(this).val() === "year") {
            $('#extfromDateforComment').val('');
            $('#extToDateforComment').val('');
            $('.selectYearAndMonth').show();
            $('.selectBetweenDates').hide();
        }
    });

    $("#extfromDate").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
    
    $("#extToDate").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
    
    $("#textfromDate").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
    
    $("#textToDate").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
    
    $("#filterGuestFeedbackFrmDate").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
    
    $("#filterGuestFeedbackToDate").datetimepicker({
        useCurrent: false,
        // minDate: moment(),
        format: 'DD-MM-YYYY'
    });
  var requestIdd = null;
    var feedbackIdd = null;
    $("#replyDetails").hide();
    $("#reply").hide();	
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
    
	var getGuestHouseListDtls = function (id) {
		blockId = id;
        $.ajax({
            url: "./admin/getGuestHouseListDetails/" + id + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function () {
                $('#loading').show();
            },
            success: function (data, textStatus, jqXHR) {
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
            }, error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }
	
	   $('#city').on('change', function() {
			var id = $('#city').val();
			getGuestHouseListDtls(id);
			getAdminFeedbackDetails();
		});
	    $("#fromDate").datetimepicker({
	        useCurrent: false,
	        // minDate: moment(),
	        format: 'DD-MM-YYYY'
	    });
	   
	   
	    $("#toDate").datetimepicker({
	        useCurrent: false,
	        // minDate: moment(),
	        format: 'DD-MM-YYYY'
	    });
	   
	   $('#guestFeedbackDetailsFilter').on('click', function () {
	        if ($('#filterGuestFeedbackFrmDate').val() == "" || $('#filterGuestFeedbackToDate').val() == "" 
	        	|| $('#filtercategory').val() == "" 
	        	|| $('#filterfeedback').val() == "") {
	            swal("Please provide all The informations");
	            return false;
	        }
	    	getGuestFeedbackReportDetails();

	    	//$('#guestFeedbackReportTable').show();
	    });
	   
	 
	var feedbackadminTable = $("#feedbackDetailsTbl").DataTable({ 
		"paging":  true,
		"searching": true,
		"responsive": true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs":[		
			{ className: "all", "targets": [ 0, 1,2 ] },
			{"targets":0,"data": "bookingId"},
			{"targets":1,"data":"name"},
			{"targets":2,"data":"mobile"},
			{"targets":3,"data":"fromDate"},
			{"targets":4,"data":"toDate"},
			{"targets":5,"data":"submittedOn"},
			{"targets":6,"data":"reqDate"},
			{"targets":7,"data":"statusDesc"},
			{"targets":8,"data": null, 
					"defaultContent": '<button type="button" id="view" action="View"  title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button>' }					
			
			], 	  
	});
	
var guestFeedbackReportTbl = $("#guestFeedbackReportTable").DataTable({ 
		"paging":  false,
		"searching": false,
		"responsive": true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
		"columnDefs":[		
			{ className: "all", "targets": [ 0, 1,2 ] },
			{"targets":0,"data": "requestId"},
			{"targets":1,"data":"name"},
			{"targets":2,"data":"mobile"},
			{"targets":3,"data":"submittedOn"},
			{"targets":4,"data":"repliedOn"},
			{"targets":5,"data":"feedback"},
			{"targets":6,"data":"status"},			
			], 	  
	});

var getCommentfeedbackReportDetails = function(){
	var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var fDate = $('#extfromDateforComment').val();
    var tDate = $('#extToDateforComment').val();
    var yearForCommentFeedbackk =  $('#yearForCommentFeedback').val();
    var monthForCommentFeedbackk =  $('#monthForCommentFeedback').val();
	$.ajax({
		url: "./admin/getCommentFeedbackReport.do",
		type: "GET",
		contentType : "application/json",
		dataType : "JSON",
		data:{
			cId:city,
	        gId:gHouse,
	        fromDate : fDate,
	        toDate : tDate,
	        year:yearForCommentFeedbackk,
	        month:monthForCommentFeedbackk
	    },
	    beforeSend: function () {
            $('#loading').show();
        },
		success: function(data) {
			$('#loading').hide();
			let str = '';
			data.lstFeedbackDetails.forEach(function (item, i) {
				  if (item.list.length == 0) {
					  str += '<tr > ' +
		              '<td class="text-center sticky-div" width="15%">' + item.block_name +', ' +item.gh_name+ '</td>'
		              + '<td></td>' + '<td></td>'+'<td></td>'+'</tr>'
				  } else {
					  str += '<tr> ' +
		              '<td class="text-center" width="15%" rowspan="' + item.list.length + '">' + item.block_name +', ' +item.gh_name+'</td>'
		              item.list.forEach(function (feedback, i) {
		            	  if (i != 0) {
		            		  str += '<tr>'
		            	  }
		            	  if(feedback.repliedSms==null){
							  feedback.repliedSms="";
							  isFeedbackReply = "NO"
						  }
		            	  else{
		            		  isFeedbackReply = "YES"
		            	  }
		            	  str += '<td class="text-left" width="35%"><b>' + (i + 1) +') '+ feedback.submittedOn+'-</b> '+feedback.feedbacksms  + '</td> ' +
		                  '<td  class="text-left"  width="50%">'+ feedback.repliedSms+'</td>'+
		                  '<td  class="text-centre"  width="50%">'+ isFeedbackReply+'</td></tr>';
		              });
				  }
			});
			 $("#commentTbl").append(str);
		}, error:function(jqXHR, status, error){
			swal(error);
		}
		
	});
	
}
	
	var getGuestFeedbackReportDetails = function(){
		 var city = $('#city').val();
	     var gHouse = $('#gHouse').val();
	     var catgForfeedback = $('#filtercategory').val();
	     var feedbackk = $('#filterfeedback').val();
	     var fromDate = $('#filterGuestFeedbackFrmDate').val();
	     var toDate = $('#filterGuestFeedbackToDate').val();
	     
		$.ajax({
			url: "./admin/guestFeedbackReportDetails.do",
			type: "GET",
			contentType : "application/json",
			dataType : "JSON",
			data:{
				cId:city,
		        gId:gHouse,
		        formDate: fromDate,
		        todate: toDate,
		        category: catgForfeedback,
		        feedback: feedbackk
		    },
			success: function(result) {

				if(result.data && result.data.length > 0 && typeof result.data[0].category !== 'undefined' && result.data[0].category !== null){
					var categoryforHeader =result.data[0].category;

					if(categoryforHeader=="checkin_out") {
						$('#catgAccToSelect').text('Ease Of Check in & Check Out');
					}else if(categoryforHeader=="keep_room") {
					$('#catgAccToSelect').text('Up Keep of the Room');
					}else if(categoryforHeader=="cleanliness_hygiene") {
					$('#catgAccToSelect').text('Cleanliness and Hygiene');
					}else if(categoryforHeader=="quality_food") {
					 $('#catgAccToSelect').text('Quality Of Food');
					}else if(categoryforHeader=="conduct_of_staff"){
					$('#catgAccToSelect').text('Conduct Of Staff');
					}else{ 
					$('#catgAccToSelect').text('Overall Feedback');
					} 
				}else{

					$('#catgAccToSelect').text('Category');
				}
		    	$('#guestFeedbackReportTable').show();
				guestFeedbackReportTbl.clear().draw();
				guestFeedbackReportTbl.rows.add(result.data).draw();

				if(result.data && result.data.length == 0){
					$('#guestFeedbackReportPDF').prop('disabled', true).css('opacity',0.7);
				}else{
					$('#guestFeedbackReportPDF').prop('disabled', false).css('opacity',1);
				}
			}, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
	});
}

var verifyfeedback = $("#ResponseFeedback").DataTable({ 
	"responsive": true,
	"bPaginate": false,
	"processing": false,
	"info": false,
	"bFilter": false,
	"scrollCollapse": true,
	"columnDefs":[		
		{ className: "all", "targets": [ 0, 1,2 ] },
		{"targets":0,"data": null, 
			render: function (data, type, row, meta) {
				return meta.row + meta.settings._iDisplayStart + 1;
			}
		},
		{"targets":1,"data":"requestId"},
		{"targets":2,"data":"repliedSms"},
		{"targets":3,"data":null,
			 render: function (data, type, row) {
                var repliedByDetails = data.repliedByRole + " (" + data.repliedByUserId+")" ;
                return repliedByDetails;
            }
		},
		{"targets":4,"data":"repliedOn"},	
		], 	  
});	


var feedbackReportTbl = $("#feedbackReportTable").DataTable({ 
	    "paging":  false,
		"searching": false,
		"responsive": true,
		"scrollX": false,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
	"columnDefs":[		
		{ className: "all", "targets": [ 0, 1,2 ] },
		{ "targets": 0, "data": null,
			 render: function (data, type, row, meta) {
				 var guestHouseAndCity =''+data.guestHouse+", "+data.cityName;
	              return guestHouseAndCity;
			 }
		},
		{"targets":1,"data":null,
			 render: function (data, type, row) {
                 var checkInExcDetails ="<b>("+ data.checkInExc + " Users) </b><br>" + data.checkInExcPercent+"%" ;
                 return checkInExcDetails;
             }
		},
		{"targets":2,"data":null,
			render: function (data, type, row) {
                var checkInGoodDetails ="<b>("+ data.checkInGood + " Users) </b><br>" + data.checkInGoodPercent+"%" ;
                return checkInGoodDetails;
            }
		},
		{"targets":3,"data":null,
			render: function (data, type, row) {
                var checkInAvgDetails ="<b>("+ data.checkInAvg + " Users) </b><br>" + data.checkInAvgPercent+"%" ;
                return checkInAvgDetails;
            }
		},
		{"targets":4,"data": null, 
			render: function (data, type, row) {
                var checkInPoorDetails ="<b>("+ data.checkInPoor + " Users) </b><br>" + data.checkInPoorPercent+"%" ;
                return checkInPoorDetails;
            }
		},
		{"targets":5,"data":null,
			render: function (data, type, row) {
                var checkInVpoorDetails ="<b>("+ data.checkInVpoor + " Users) </b><br>" + data.checkInVpoorPercent+"%" ;
                return checkInVpoorDetails;
            }
		},
		{"targets":6,"data":null,
			render: function (data, type, row) {
                var upKeepExcDetails ="<b>("+ data.upKeepExc + " Users) </b><br>" + data.upKeepExcPercent+"%" ;
                return upKeepExcDetails;
            }
		},
		{"targets":7,"data":null,
			render: function (data, type, row) {
                var upkeepGoodDetails ="<b>("+ data.upkeepGood + " Users) </b><br>" + data.upkeepGoodPercent+"%" ;
                return upkeepGoodDetails;
            }
		},
		{"targets":8,"data": null,
			render: function (data, type, row) {
                var upKeepAvgDetails ="<b>("+ data.upKeepAvg + " Users) </b><br>" + data.upKeepAvgPercent+"%" ;
                return upKeepAvgDetails;
            }
		},
		{"targets":9,"data":null,
			render: function (data, type, row) {
                var upKeepPoorDetails ="<b>("+ data.upKeepPoor + " Users) </b><br>" + data.upKeepPoorPercent+"%" ;
                return upKeepPoorDetails;
            }
		},
		{"targets":10,"data":null,
			render: function (data, type, row) {
                var upKeepVpoorDetails ="<b>("+ data.upKeepVpoor + " Users) </b><br>" + data.upKeepVpoorPercent+"%" ;
                return upKeepVpoorDetails;
            }
			
		},
		{"targets":11,"data":null,
			render: function (data, type, row) {
                var cleanExcDetails ="<b>("+ data.cleanExc + " Users) </b><br>" + data.cleanExcPercent+"%" ;
                return cleanExcDetails;
            }
			
		},
		{"targets":12,"data": null,
			render: function (data, type, row) {
                var cleanGoodDetails ="<b>("+ data.cleanGood + " Users) </b><br>" + data.cleanGoodPercent+"%" ;
                return cleanGoodDetails;
            } 
			
		},
		{"targets":13,"data":null,
			render: function (data, type, row) {
                var cleanAvgDetails ="<b>("+ data.cleanAvg + " Users) </b><br>" + data.cleanAvgPercent+"%" ;
                return cleanAvgDetails;
            }
			
		},
		{"targets":14,"data":null,
			render: function (data, type, row) {
                var cleanPoorDetails ="<b>("+ data.cleanPoor + " Users) </b><br>" + data.cleanPoorPercent+"%" ;
                return cleanPoorDetails;
            }
			
		},
		{"targets":15,"data":null,
			render: function (data, type, row) {
                var cleanVpoorDetails ="<b>("+ data.cleanVpoor + " Users) </b><br>" + data.cleanVpoorPercent+"%" ;
                return cleanVpoorDetails;
            }
			
		},
		{"targets":16,"data": null,
			render: function (data, type, row) {
                var qualityExcDetails ="<b>("+ data.qualityExc + " Users) </b><br>" + data.qualityExcPercent+"%" ;
                return qualityExcDetails;
            } 
			
		},
		{"targets":17,"data":null,
			render: function (data, type, row) {
                var qualityGoodDetails ="<b>("+ data.qualityGood + " Users) </b><br>" + data.qualityGoodPercent+"%" ;
                return qualityGoodDetails;
            }
			
		},
		{"targets":18,"data":null,
			render: function (data, type, row) {
                var qualityAvgDetails ="<b>("+ data.qualityAvg + " Users) </b><br>" + data.qualityAvgPercent+"%" ;
                return qualityAvgDetails;
            }
			
		},
		{"targets":19,"data":null,
			render: function (data, type, row) {
                var qualityPoorDetails ="<b>("+ data.qualityPoor + " Users) </b><br>" + data.qualityPoorPercent+"%" ;
                return qualityPoorDetails;
            }
			
		},
		{"targets":20,"data":null,
			render: function (data, type, row) {
                var qualityVpoorDetails ="<b>("+ data.qualityVpoor + " Users) </b><br>" + data.qualityVpoorPercent+"%" ;
                return qualityVpoorDetails;
            }
			
		},
		{"targets":21,"data":null,
			render: function (data, type, row) {
                var conductExcDetails ="<b>("+ data.conductExc + " Users) </b><br>" + data.conductExcPercent+"%" ;
                return conductExcDetails;
            }
			
		},
		{"targets":22,"data":null,
			render: function (data, type, row) {
                var conductGoodDetails ="<b>("+ data.conductGood + " Users) </b><br>" + data.conductGoodPercent+"%" ;
                return conductGoodDetails;
            }
			
		},
		{"width":"25%","targets":23,"data":null,
			render: function (data, type, row) {
                var conductAvgDetails ="<b>("+ data.conductAvg + " Users) </b><br>" + data.conductAvgPercent+"%" ;
                return conductAvgDetails;
            }
			
		},
		{"targets":24,"data":null,
			render: function (data, type, row) {
                var conductPoorDetails ="<b>("+ data.conductPoor + " Users) </b><br>" + data.conductPoorPercent+"%" ;
                return conductPoorDetails;
            }
			
		},
		{"targets":25,"data":null,
			render: function (data, type, row) {
                var conductVpoorDetails ="<b>("+ data.conductVpoor + " Users) </b><br>" + data.conductVpoorPercent+"%" ;
                return conductVpoorDetails;
            }
			
		},
		{"targets":26,"data": null,
			render: function (data, type, row) {
                var overallExcDetails ="<b>("+ data.overallExc + " Users) </b><br>" + data.overallExcPercent+"%" ;
                return overallExcDetails;
            } 
			
		},
		{"targets":27,"data":null,
			render: function (data, type, row) {
                var overallGoodDetails ="<b>("+ data.overallGood + " Users) </b><br>" + data.overallGoodPercent+"%" ;
                return overallGoodDetails;
            }
			
		},
		{"targets":28,"data":null,
			render: function (data, type, row) {
                var overallAvgDetails ="<b>("+ data.overallAvg + " Users) </b><br>" + data.overallAvgPercent+"%" ;
                return overallAvgDetails;
            }
			
		},
		{"targets":29,"data":null,
			render: function (data, type, row) {
                var overallPoorDetails ="<b>("+ data.overallPoor + " Users) </b><br>" + data.overallPoorPercent+"%" ;
                return overallPoorDetails;
            }
			
		},
		{"targets":30,"data":null,
			render: function (data, type, row) {
                var overallVpoorDetails ="<b>("+ data.overallVpoor + " Users) </b><br>" + data.overallVpoorPercent+"%" ;
                return overallVpoorDetails;
            }
			
		}
		], 	  
});	


var totalFeedbackReportTbl = $("#totalFeedbackTable").DataTable({ 
	    "paging":  false,
		"searching": false,
		"responsive": true,
		"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		"processing": false,
		"ordering": false,
		"info": false,
		"bFilter": false,
		"fixedHeader": {
			header: true,
			footer: true
		},
		"scrollCollapse": true,
	"columnDefs":[		
		{ className: "all", "targets": [ 0, 1,2 ] },
		{ "targets": 0, "data": null,
			 render: function (data, type, row, meta) {
				 if(data.cityName!==null){
					 var guestHouseAndCity =''+data.guestHouse+", "+data.cityName;
				 }if(data.cityName==null || data.cityName==""){
					 var guestHouseAndCity ='<b>'+data.guestHouse+'</b>';
				 }
	              return guestHouseAndCity;
			 }
		},
		{"targets":1,"data":"totalRow"},
		{"targets":2,"data":"totalNumFeedback"},
		{"targets":3,"data":"checkInExc"},
		{"targets":4,"data":"checkInGood"},
		{"targets":5,"data":"checkInAvg"},
		{"targets":6,"data":"checkInPoor"},
		{"targets":7,"data":"checkInVpoor"},
		{"targets":8,"data":"upKeepExc"},
		{"targets":9,"data":"upkeepGood"},
		{"targets":10,"data":"upKeepAvg"},
		{"targets":11,"data":"upKeepPoor"},
		{"targets":12,"data":"upKeepVpoor"},
		{"targets":13,"data":"cleanExc"},
		{"targets":14,"data":"cleanGood"},
		{"targets":15,"data":"cleanAvg"},
		{"targets":16,"data":"cleanPoor"},
		{"targets":17,"data":"cleanVpoor"},
		{"targets":18,"data":"qualityExc"},
		{"targets":19,"data":"qualityGood"},
		{"targets":20,"data":"qualityAvg"},
		{"targets":21,"data":"qualityPoor"},
		{"targets":22,"data":"qualityVpoor"},
		{"targets":23,"data":"conductExc"},
		{"targets":24,"data":"conductGood"},
		{"targets":25,"data":"conductAvg"},
		{"targets":26,"data":"conductPoor"},
		{"targets":27,"data":"conductVpoor"},
		{"targets":28,"data":"overallExc"},
		{"targets":29,"data":"overallGood"},
		{"targets":30,"data":"overallAvg"},
		{"targets":31,"data":"overallPoor"},
		{"targets":32,"data":"overallVpoor"}
		], 	  
});	

	var getAdminFeedbackDetails = function(){
		 var city = $('#city').val();
	     var gHouse = $('#gHouse').val();
	     var nameforFeedbackDeatils = $('#userNameFilter').val();
	     var reqIdforFeedbackDeatils = $('#bookingIdfilter').val();
		$.ajax({
			url: "./admin/getAdminFeedbackDetailsList.do",
			type: "GET",
			contentType : "application/json",
			dataType : "JSON",
			data:{
				cId:city,
		        gId:gHouse,
		        reqType: 'AL',
		        name : nameforFeedbackDeatils,
		        bookingId:reqIdforFeedbackDeatils,
		    },
			success: function(result) {
				feedbackadminTable.clear().draw();
				feedbackadminTable.rows.add(result.data).draw();
				
			},error: function (jqXHR, status, error) {
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
	 
    $('#filterSearchForFeedbakDetails').click(function () {
    	getAdminFeedbackDetails();
    });
	
	var getTotalfeedbackCount = function() {
		 var city = $('#city').val();
	     var gHouse = $('#gHouse').val();
		$.ajax({
			url: "./admin/getTotalfeedbackCount.do",
			type: "GET",
			contentType : "application/json",
			dataType : "JSON",
			data:{
				cId:city,
		        gId:gHouse
		    },		
			success:function(result) {
				$('#loading').hide();
				$('#totalNoOfFeedback').html(result.data.count);
				$('#noOffeedbackView').html(result.data.viewFeedbackCount);
				$('#noOfFeedbackClosed').html(result.data.closedFeedbackCount);
				$('#noOfFeedbackOpened').html(result.data.openFeedbackCount);
			},
			error:function(jqXHR, status, error){
				swal(error,"","error");
			}
		});
	}
	getTotalfeedbackCount();
	getAdminFeedbackDetails();
	$("#gHouse").change(function(){
		getAdminFeedbackDetails();
		getTotalfeedbackCount();
	});
	
	$("#reply").click(function(){
	
		$('#feedbackCheckBox').prop('checked', false);
		$('#myModal').modal('show');	
		$('#textAreaForXtraFeedback').val('');
		feedbackDetailsLenght();
		 $('#textAreaForXtraFeedback').hide()
		if(overAll == 5 || overAll == 4) {
			$('#feedbackDefaultReply').text('We are happy that you had a pleasant experience at '+blockName+'. We are committed to provide quality services to our Guests. We are encouraged by your feedback and will strive harder to provide even better services in future.');
			$('#templateid').val('1107164967721333473');
		}else if(overAll == 3) {
			$('#feedbackDefaultReply').text('We are disappointed that you did not have a very pleasant experience at '+blockName+'. We are committed to provide quality services to our Guests. Your feedback is being reviewed and we shall undertake necessary corrective actions to prevent any recurrence.');
			$('#templateid').val('1107164967740329102');
		}else{
			$('#feedbackDefaultReply').text('Please accept our sincere apologies for an unsatisfactory experience during your recent visit to '+blockName+'. We are committed to provide the best service to our guests and shall make necessary improvements to prevent any recurrence henceforth.');
			$('#templateid').val('1107164967750152268');
		}
		$('#myModalViewoffeedback').modal('hide');
		
	});
	$("#feedbackCheckBox").change(function(){
		checkval();
	});
	function checkval() {
	    if ($('#feedbackCheckBox').is(':checked')) {
	    	$('#textAreaForXtraFeedback').val('');
	        $('#textAreaForXtraFeedback').show();
	    } else {
	        $('#textAreaForXtraFeedback').hide();
	    }
	}
	$("#textAreaForXtraFeedback").keyup(function(){
		feedbackDetailsLenght();
	});
	var max_length = 100;
	function feedbackDetailsLenght() {
		var len = max_length - $("#textAreaForXtraFeedback").val().length;
		$('.feedbackLength').text(len);

	};
	
$('#feedbackDetailsTbl').on('click', 'tbody #view', function () {
	var data = feedbackadminTable.row($(this).closest('tr')).data() != undefined ? feedbackadminTable.row($(this).closest('tr')).data() : feedbackadminTable.row($(this)).data();
	$('#myModalViewoffeedback').modal('show');
	getFeedbackRepliedDetails(data.feedbackId,data.bookingId);
	$('#name2').html(data.name);
	getTotalfeedbackCount();
	getAdminFeedbackDetails();
});

$("#myModalViewoffeedback").on("hide.bs.modal", function(){
	$(this).find('form').trigger('reset');
});

$("#totalFeedbackReport").on("hide.bs.modal", function () {
    $(this).find('form').trigger('reset');
});

$('#guestfeedbackModal,#feedbackReportModal,#totalFeedbackReportModal,#commentReportModal').on("hide.bs.modal", function () {
   $('#filterGuestFeedbackFrmDate,#extfromDate,#extfromDateforTotalFeedback,#extfromDateforComment').val('');
   $('#filterGuestFeedbackToDate,#extToDate,#extToDateforTotalFeedback,#extToDateforComment').val('');
   $('#filtercategory,#yearForFeedback,#yearForTotalFeedback,#yearForCommentFeedback').val('');
   $('#filterfeedback,#monthForFeedback,#monthForTotalFeedback,#monthForCommentFeedback').val('');
});

var getFeedbackRepliedDetails = function(feedbackId,requestId){
	$.ajax({
		url: "./guest/getFeedbackRepliedDetails/"+feedbackId+"/"+requestId+".do",
		type: "GET",			
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			verifyfeedback.clear().draw();
			$('#requestId2').html(result.data[0].requestId);
			$('#feedbackid').html(result.data[0].feedbackId);
			$('#submitted').html(result.data[0].submittedOn);
			$('input[name="foodradio"][type="radio"][value="' + result.data[0].food +'"]').prop('checked', true);
			$('input[name="housekeeping"][type="radio"][value="' + result.data[0].housekeeping +'"]').prop('checked', true);
			$('input[name="checkincheckout"][type="radio"][value="' + result.data[0].checkincheckout +'"]').prop('checked', true);
			$('input[name="conduct"][type="radio"][value="' + result.data[0].conduct +'"]').prop('checked', true);
			$('input[name="clean"][type="radio"][value="' + result.data[0].clean +'"]').prop('checked', true);
			$('#description2').val(result.data[0].feedbacksms);
			mobile =  result.data[0].mobile;
			feedbackIdd = result.data[0].feedbackId ;
			requestIdd =  result.data[0].requestId;
			overAll = result.data[0].overall;
			blockName = result.data[0].block_name;
			if(result.data[0].status=='Closed'){
				/*verifyfeedback.clear().draw();*/
				$("#replyDetails").show();
				$("#reply").hide();
				verifyfeedback.rows.add(result.data).draw();
			}else{
				$("#replyDetails").hide();
				$("#reply").show();
	
			}

		}, error:function(jqXHR, status, error){
			swal(error);
		}
	});
	$.ajax({
			url: "./admin/openReplyFeedback/"+feedbackId+".do",
			type: "GET",			
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {

				getTotalfeedbackCount();
				getAdminFeedbackDetails();
				},	
			 error:function(jqXHR, status, error){
				swal(error);
			}
		});
}
  $('#filterSearch').click(function () {

	  isToDateEmpty  = $('#extToDate').val();
	  isFromDateEmpty = $('#extfromDate').val();
	  isYearEmpty = $('#yearForFeedback').val();
	  isMonthEmpty = $('#monthForFeedback').val();


	  if ($('input[name=radioBox]:checked').val() == "date") {
			if (isFromDateEmpty != "" &&  isToDateEmpty== "") {
				swal("Please provide to date");
				return false;
			}
			if (isFromDateEmpty == "" && isToDateEmpty != "") {
				swal("Please provide From Date");
				return false;
			}
			if(isFromDateEmpty == "" && isToDateEmpty == ""){
				swal("Please provide dates the information");
			}
		}
		if ($('input[name=radioBox]:checked').val() == "year") {
			if (isYearEmpty != "" && isMonthEmpty == "") {
				swal("Please provide Month");
				return false;
			}
			if (isYearEmpty == "" && isMonthEmpty != "") {
				swal("Please provide year");
				return false;
			}
			if(isYearEmpty == "" && isMonthEmpty == ""){
				swal("Please provide Month and year");
			}
		}
		 if (!$('input[name=radioBox]:checked').val()) {
		       swal("Please select search by dates or year");
		        return;
		 }
       getfeedbackReportDetails();
       $('#filter-popover-for-feedbackModal').unbind();
   		setTimeout(function(){
   			$('#filter-popover-for-feedbackModal').bind('click',preventClick);
   	}, 1000);
    });

	$('#filterSearchforTotalFeedback').click(function() {
		 isToDateEmpty  = $('#extfromDateforTotalFeedback').val();
		 isFromDateEmpty = $('#extToDateforTotalFeedback').val();
		 isYearEmpty = $('#yearForTotalFeedback').val();
		 isMonthEmpty = $('#monthForTotalFeedback').val();
		 
		  if ($('input[name=radioBox]:checked').val() == "date") {
				if (isFromDateEmpty != "" &&  isToDateEmpty== "") {
					swal("Please provide to date");
					return false;
				}
				if (isFromDateEmpty == "" && isToDateEmpty != "") {
					swal("Please provide From Date");
					return false;
				}
				if(isFromDateEmpty == "" && isToDateEmpty == ""){
					swal("Please provide dates the information");
				}
			}
			if ($('input[name=radioBox]:checked').val() == "year") {
				if (isYearEmpty != "" && isMonthEmpty == "") {
					swal("Please provide Month");
					return false;
				}
				if (isYearEmpty == "" && isMonthEmpty != "") {
					swal("Please provide year");
					return false;
				}
				if(isYearEmpty == "" && isMonthEmpty == ""){
					swal("Please provide Month and year");
				}
			}
			 if (!$('input[name=radioBox]:checked').val()) {
			       swal("Please select search by dates or year");
			        return;
			 }
		getTotalfeedbackReportDetails();
		$('#filter-popover-for-totalFeedback').unbind();
    	setTimeout(function(){
    		$('#filter-popover-for-totalFeedback').bind('click',preventClick);
    	}, 1000);
	});
	$('#filterSearchforComment').click(function() {
		isToDateEmpty  = $('#extfromDateforComment').val();
		isFromDateEmpty = $('#extToDateforComment').val();
		isYearEmpty = $('#yearForCommentFeedback').val();
		isMonthEmpty = $('#monthForCommentFeedback').val();
		 
		  if ($('input[name=radioBox]:checked').val() == "date") {
				if (isFromDateEmpty != "" &&  isToDateEmpty== "") {
					swal("Please provide to date");
					return false;
				}
				if (isFromDateEmpty == "" && isToDateEmpty != "") {
					swal("Please provide From Date");
					return false;
				}
				if(isFromDateEmpty == "" && isToDateEmpty == ""){
					swal("Please provide dates the information");
				}
			}
			if ($('input[name=radioBox]:checked').val() == "year") {
				if (isYearEmpty != "" && isMonthEmpty == "") {
					swal("Please provide Month");
					return false;
				}
				if (isYearEmpty == "" && isMonthEmpty != "") {
					swal("Please provide year");
					return false;
				}
				if(isYearEmpty == "" && isMonthEmpty == ""){
					swal("Please provide Month and year");
				}
			}
			 if (!$('input[name=radioBox]:checked').val()) {
			       swal("Please select search by dates or year");
			        return;
			 }
		$("#commentTbl").html("");
		getCommentfeedbackReportDetails();
		$('#filter-popover-commentreport').unbind();
    	setTimeout(function(){
    		$('#filter-popover-commentreport').bind('click',preventClick);
    	}, 1000);
		
	});
	function preventClick() {
	       event.stopPropagation();
	  }

	
	
	
	
	
	
	
	
	
	
  $('#filtercategory').append(
          "<option value='checkin_out'>Ease Of Check in & Check Out</option>" +
          "<option value='keep_room'>Up Keep of the Room</option>" +
          "<option value='cleanliness_hygiene'>Cleanliness and Hygiene</option>" +
          "<option value='quality_food'>Quality Of Food</option>" +
          "<option value='conduct_of_staff'>Conduct Of Staff</option>" +
          "<option value='overall'>Overall Feedback</option>" 
      );
  $('#filterfeedback').append(
			"<option value='5'>Excellent</option>"+
			"<option value='4'>Good</option>"+
			"<option value='3'>Average</option>"+
			"<option value='2'>Poor</option>"+
			"<option value='1'>Very Poor</option>" 
      );
  
var getfeedbackReportDetails = function(){
	var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var fDate = $('#extfromDate').val();
    var tDate = $('#extToDate').val();
    var yearr =  $('#yearForFeedback').val();
    var monthh =  $('#monthForFeedback').val();
	$.ajax({
		url: "./admin/getFeedbackReport.do",
		type: "GET",
		contentType : "application/json",
		dataType : "JSON",
		data:{
			cId:city,
	        gId:gHouse,
	        fromDate : fDate,
	        toDate : tDate,
	        year : yearr,
	        month : monthh
	    },
		success: function(result) {
			feedbackReportTbl.clear().draw();
			feedbackReportTbl.rows.add(result.data).draw();
			
		}, error:function(jqXHR, status, error){
			swal(error);
		}
	});
	
}

var getTotalfeedbackReportDetails = function(){
	var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var fDate = $('#extfromDateforTotalFeedback').val();
    var tDate = $('#extToDateforTotalFeedback').val();
    var yearForTotalFeedback =  $('#yearForTotalFeedback').val();
    var monthForTotalFeedback =  $('#monthForTotalFeedback').val();
	$.ajax({
		url: "./admin/getTotalFeedbackReport.do",
		type: "GET",
		contentType : "application/json",
		dataType : "JSON",
		data:{
			cId:city,
	        gId:gHouse,
	        fromDate : fDate,
	        toDate : tDate,
	        year : yearForTotalFeedback,
	        month : monthForTotalFeedback
	    },
		success: function(result) {
			totalFeedbackReportTbl.clear().draw();
			totalFeedbackReportTbl.rows.add(result.data).draw();
			
		}, error:function(jqXHR, status, error){
			swal(error);
		}
	});
	
}


$("#submitButton").click(function(){
	$('#myModal').modal('hide');
	saveFeedbackRepliedDetails();
});
var saveFeedbackRepliedDetails = function(feedbackId,requestId){
	postData = {
		   
	        "feedbackId":feedbackIdd,
	        "requestId" : requestIdd,
	        "mobile" : mobile,
	        "repliedSms":  $('#feedbackDefaultReply').val(),
	        "repliedCustomSms": $('#textAreaForXtraFeedback').val(),
	        "templateId" : $('#templateid').val()
	}
	$.ajax({
		url: "./admin/saveFeedbackRepliedDetails.do",
		type: "POST",
		processData: false,  // Important!
		contentType: "application/json",
        data: JSON.stringify(postData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			swal(result.message);
			getAdminFeedbackDetails();
			getTotalfeedbackCount();
			
		}, error:function(jqXHR, status, error){
			swal(error);
		}
	});
}

let isCityAndGHSelected = function () {
    let city = $('#city').val();
    let gHouse = $('#gHouse').val();
    if (city == "") {
        swal("Select City");
        return false;
    }
    if (gHouse == "") {
        swal("Select Guest House");
        return false;
    }
    return true;
}

$('#feedbackReport').on('click', function(){
	   getfeedbackReportDetails();
		$('#feedbackReportModal').modal('show');
	}); 
$("#guestFeedbackReport").click(function(){
    if (!isCityAndGHSelected())
        return false;
	$('#guestfeedbackModal').modal('show');
	   
});
$("#totalFeedbackReportBtn").click(function(){
	getTotalfeedbackReportDetails();
	$('#totalFeedbackReportModal').modal('show');
	   
});
$("#commentReportBtn").click(function(){
	getCommentfeedbackReportDetails();
	$('#commentReportModal').modal('show');
	   
});

$("#commentReportModal").on("hidden.bs.modal", function () {
        $("#commentTbl").html("");
});

$('#printBtn').click(function(){
	var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var frDt = $('#extfromDate').val();
    var toDt = $('#extToDate').val();
    var year =  $('#yearForFeedback').val();
    var month =  $('#monthForFeedback').val();
    		if( city == '' ){
				city = 'NA';
			} 
			if( gHouse == '' ){
				gHouse = 'NA';
			}
    		if (frDt == '' && toDt == '') {
            frDt = 'NA';
            toDt = 'NA';
        }
        if (year == '' && month == '') {
            year = 'NA';
            month = 'NA';
        }
        
		window.open('./admin/downloadFeedbackReportInPDF/'+city+'/'+ gHouse +'/'+frDt+'/'+toDt+'/'+year+'/'+month+'.html','_blank');
	})
$('#totalFeedbackPrintBtn').click(function(){
	var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var frDt = $('#extfromDateforTotalFeedback').val();
    var toDt = $('#extToDateforTotalFeedback').val();
    var yearForTotalFeedback =  $('#yearForTotalFeedback').val();
    var monthForTotalFeedback =  $('#monthForTotalFeedback').val();

    		if( city == '' ){
				city = 'NA';
			} 
			if( gHouse == '' ){
				gHouse = 'NA';
			}
    		if (frDt == '' || toDt == '') {
            frDt = 'NA';
            toDt = 'NA';
        }
        if (yearForTotalFeedback == '' && monthForTotalFeedback == '') {
            yearForTotalFeedback = 'NA';
            monthForTotalFeedback = 'NA';
        }
		window.open('./admin/downloadTotalFeedbackReportInPDF/'+city+'/'+ gHouse +'/'+frDt+'/'+toDt+'/'+yearForTotalFeedback+'/'+monthForTotalFeedback+'.html','_blank');
	})
	$('#guestFeedbackReportPDF').click(function(){
	var city = $('#city').val();
    var gHouse = $('#gHouse').val();
    var frDt = $('#filterGuestFeedbackFrmDate').val();
    var toDt = $('#filterGuestFeedbackToDate').val();
    var catgForfeedback = $('#filtercategory').val();
    var feedbackk = $('#filterfeedback').val();

    		if( city == '' ){
				city = 'NA';
			} 
			if( gHouse == '' ){
				gHouse = 'NA';
			}
    		if (frDt == '' || toDt == '') {
            frDt = 'NA';
            toDt = 'NA';
        }
		window.open('./admin/downloadGuestFeedbackReportInPDF/'+city+'/'+ gHouse +'/'+frDt+'/'+toDt+'/'+catgForfeedback+'/'+feedbackk+'.html','_blank');
	})
	$('#commentFeedbackReportPDF').click(function(){
		var city = $('#city').val();
	    var gHouse = $('#gHouse').val();
	    var frDt = $('#extfromDateforComment').val();
	    var toDt = $('#extToDateforComment').val();
	    var yearForCommentFeedback =  $('#yearForCommentFeedback').val();
	    var monthForCommentFeedback =  $('#monthForCommentFeedback').val();

	    		if( city == '' ){
					city = 'NA';
				} 
				if( gHouse == '' ){
					gHouse = 'NA';
				}
	    		if (frDt == '' && toDt == '') {
	            frDt = 'NA';
	            toDt = 'NA';
	        }
	        if (yearForCommentFeedback == '' && monthForCommentFeedback == '') {
	        	yearForCommentFeedback = 'NA';
	        	monthForCommentFeedback = 'NA';
	        }
	        window.open('./admin/downloadCommentFeedbackReportInPDF/'+city+'/'+ gHouse +'/'+frDt+'/'+toDt+'/'+yearForCommentFeedback+'/'+monthForCommentFeedback+'.html','_blank');
	})
	
	// reset btn for feedback list
	 $('#resetFiterBtn').click(function () {
		 $('#city').val('');
	        $('#gHouse').val('');
	        $('#bookingIdfilter').val('');
	        $('#userNameFilter').val('');
	        getAdminFeedbackDetails();
	    });
	
	// reset btn for feedback report
	$('#feedbackReportResetBtn').click(function () {
		$('#city').val('');
        $('#gHouse').val('');
		$('#extfromDate').val('');
        $('#extToDate').val('');
        $('#yearForFeedback').val('');
        $('#monthForFeedback').val('');
        getfeedbackReportDetails();
	});

	//reset btn for total feedback report
	 $('#totalFeedbackReportResetBtn').click(function () {
		 $('#city').val('');
	        $('#gHouse').val('');
		 $('#extfromDateforTotalFeedback').val('');
	        $('#extToDateforTotalFeedback').val('');
	        $('#yearForTotalFeedback').val('');
	        $('#monthForTotalFeedback').val('');
	        getTotalfeedbackReportDetails();
	  });
	 
	//reset btn for comment report
	 $('#commentFeedbackReportResetBtn').click(function () {
		 $("#commentTbl").html("");
		 $('#city').val('');
	        $('#gHouse').val('');
	        $('#extfromDateforComment').val('');
	        $('#extToDateforComment').val('');
	        $('#yearForCommentFeedback').val('');
	        $('#monthForCommentFeedback').val('');
	        getCommentfeedbackReportDetails();
	    });
	 
	 //reset btn for guest report
	 $('#guestFeedbackReset').click(function () {
	        $('#filterGuestFeedbackFrmDate').val('');
	        $('#filterGuestFeedbackToDate').val('');
	        $('#filtercategory').val('');
	        $('#filterfeedback').val('');
	        $('#guestFeedbackReportTable').hide();
	    });
	 
})
