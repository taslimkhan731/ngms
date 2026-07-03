$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	
	var feedbackuserTable = $("#feedbackusertable").DataTable({ 
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
			{"targets":0,"data": "feedbackId"},
			{"targets":1,"data":"bookingId"},
			{"targets":2,"data":"name"},
			{"targets":3,"data":"fromDate"},
			{"targets":4,"data":"toDate"},
			{"targets":5,"data":"ghName"},
			{"targets":6,"data":"statusDesc"},
			{"targets":7,"data": null, 
					"defaultContent": '<button type="button" id="view" action="View"  title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button>' }					
				
			
			], 	  
	});	

var verifyfeedback = $("#ResponseFeedback").DataTable({ 
	/*"paging":  true,
	"searching": true,*/
	"responsive": true,
	"bPaginate": false,
	/*"lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],*/
	"processing": false,
	/*"ordering": true,*/
	"info": false,
	"bFilter": false,
	/*"fixedHeader": {
		header: true,
		footer: true
	},*/
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
	var getFeedbackDetails = function(){
		$.ajax({
			url: "./guest/getFeedbackDetailsList.do",
			type: "GET",			
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result) {
				console.log(result);
					feedbackuserTable.clear().draw();
					feedbackuserTable.rows.add(result.data).draw();
				
			}, error:function(jqXHR, status, error){
				swal(error);
			}
		});
	}	



getFeedbackDetails();

$('#feedbackusertable').on('click', 'tbody #view', function () {
	var data = feedbackuserTable.row($(this).closest('tr')).data() != undefined ? feedbackuserTable.row($(this).closest('tr')).data() : feedbackuserTable.row($(this)).data();
	$('#myModalViewoffeedback').modal('show');
	getFeedbackRepliedDetails(data.feedbackId,data.bookingId);
	$('#name2').html(data.name);
	
});

$("#myModalView").on("hidden.bs.modal", function(){
	$(this).find('form').trigger('reset');
});

var getFeedbackRepliedDetails = function(feedbackId,requestId){
	$.ajax({
		url: "./guest/getFeedbackRepliedDetails/"+feedbackId+"/"+requestId+".do",
		type: "GET",			
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(result) {
			console.log(result);
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
			if(result.data[0].status=='Closed'){
				/*verifyfeedback.clear().draw();*/
				verifyfeedback.rows.add(result.data).draw();
			}
				
			
		}, error:function(jqXHR, status, error){
			swal(error);
		}
	});
}	




})
