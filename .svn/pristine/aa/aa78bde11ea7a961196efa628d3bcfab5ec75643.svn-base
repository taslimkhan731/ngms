$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	
	var current_fs, next_fs, previous_fs; //fieldsets
	var opacity;
	var current = 1;
	var steps = $("fieldset").length;
	var data;
	setProgressBar(current);
	

	 $("input[name='foodradio']").click(function () {
		 /* emoji color */
		$(this).parent().parent().children('label').each(function (index) {
			i=parseInt(index+1);
			$(this).removeClass('label-radio'+i).addClass('lbl-grey');
			if(index==0){
				$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
			}else if(index==1){
				$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
			}else if(index==2){
				$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
			}else if(index==3){
				$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
			}else if(index==4){
				$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
			}
		});
		
			if($('input[name="foodradio"]:checked').val()==1){
			
			$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
			$(this).prev().addClass('fifthicon-bold');
		}else if($('input[name="foodradio"]:checked').val()==2){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
		 	$(this).prev().addClass('fouricon-bold');
		}else if($('input[name="foodradio"]:checked').val()==3){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
		 	$(this).prev().addClass('thirdicon-bold');
		}else if($('input[name="foodradio"]:checked').val()==4){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
			$(this).prev().addClass('secondicon-bold');
		}else if($('input[name="foodradio"]:checked').val()==5){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
		 	$(this).prev().addClass('firsticon-bold');
		}
		 
	 });



	 $("input[name='housekeeping']").click(function () {
		 /* emoji color */
		$(this).parent().parent().children('label').each(function (index) {
			i=parseInt(index+1);
			$(this).removeClass('label-radio'+i).addClass('lbl-grey');
			console.log(i);
			console.log($(this).attr('class'));
			if(index==0){
				$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
			}else if(index==1){
				$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
			}else if(index==2){
				$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
			}else if(index==3){
				$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
			}else if(index==4){
				$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
			}
		});
		
if($('input[name="housekeeping"]:checked').val()==1){
			
			$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
			$(this).prev().addClass('fifthicon-bold');
		}else if($('input[name="housekeeping"]:checked').val()==2){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
		 	$(this).prev().addClass('fouricon-bold');
		}else if($('input[name="housekeeping"]:checked').val()==3){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
		 	$(this).prev().addClass('thirdicon-bold');
		}else if($('input[name="housekeeping"]:checked').val()==4){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
			$(this).prev().addClass('secondicon-bold');
		}else if($('input[name="housekeeping"]:checked').val()==5){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
		 	$(this).prev().addClass('firsticon-bold');
		}
		 
	 });
	 


	 $("input[name='checkincheckout']").click(function () {
		 /* emoji color */
		$(this).parent().parent().children('label').each(function (index) {
			i=parseInt(index+1);
			$(this).removeClass('label-radio'+i).addClass('lbl-grey');
			console.log(i);
			console.log($(this).attr('class'));
			if(index==0){
				$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
			}else if(index==1){
				$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
			}else if(index==2){
				$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
			}else if(index==3){
				$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
			}else if(index==4){
				$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
			}
		});
		
if($('input[name="checkincheckout"]:checked').val()==1){
			
			$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
			$(this).prev().addClass('fifthicon-bold');
		}else if($('input[name="checkincheckout"]:checked').val()==2){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
		 	$(this).prev().addClass('fouricon-bold');
		}else if($('input[name="checkincheckout"]:checked').val()==3){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
		 	$(this).prev().addClass('thirdicon-bold');
		}else if($('input[name="checkincheckout"]:checked').val()==4){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
			$(this).prev().addClass('secondicon-bold');
		}else if($('input[name="checkincheckout"]:checked').val()==5){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
		 	$(this).prev().addClass('firsticon-bold');
		}
		 
	 });


	 $("input[name='conduct']").click(function () {
		 /* emoji color */
		$(this).parent().parent().children('label').each(function (index) {
			i=parseInt(index+1);
			$(this).removeClass('label-radio'+i).addClass('lbl-grey');
			console.log(i);
			console.log($(this).attr('class'));
			if(index==0){
				$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
			}else if(index==1){
				$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
			}else if(index==2){
				$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
			}else if(index==3){
				$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
			}else if(index==4){
				$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
			}
		});
		
if($('input[name="conduct"]:checked').val()==1){
			
			$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
			$(this).prev().addClass('fifthicon-bold');
		}else if($('input[name="conduct"]:checked').val()==2){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
		 	$(this).prev().addClass('fouricon-bold');
		}else if($('input[name="conduct"]:checked').val()==3){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
		 	$(this).prev().addClass('thirdicon-bold');
		}else if($('input[name="conduct"]:checked').val()==4){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
			$(this).prev().addClass('secondicon-bold');
		}else if($('input[name="conduct"]:checked').val()==5){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
		 	$(this).prev().addClass('firsticon-bold');
		}			 
	 });


	 $("input[name='clean']").click(function () {
		 /* emoji color */
		$(this).parent().parent().children('label').each(function (index) {
			i=parseInt(index+1);
			$(this).removeClass('label-radio'+i).addClass('lbl-grey');
			console.log(i);
			console.log($(this).attr('class'));
			if(index==0){
				$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
			}else if(index==1){
				$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
			}else if(index==2){
				$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
			}else if(index==3){
				$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
			}else if(index==4){
				$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
			}
		});
		
if($('input[name="clean"]:checked').val()==1){
			
			$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
			$(this).prev().addClass('fifthicon-bold');
		}else if($('input[name="clean"]:checked').val()==2){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
		 	$(this).prev().addClass('fouricon-bold');
		}else if($('input[name="clean"]:checked').val()==3){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
		 	$(this).prev().addClass('thirdicon-bold');
		}else if($('input[name="clean"]:checked').val()==4){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
			$(this).prev().addClass('secondicon-bold');
		}else if($('input[name="clean"]:checked').val()==5){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
		 	$(this).prev().addClass('firsticon-bold');
		}
		 
	 });
	 $("input[name='overall']").click(function () {
		 /* emoji color */
		 var i=0;
		 $(this).parent().parent().children('label').each(function (index) {
				i=parseInt(index+1);
				$(this).removeClass('label-radio'+i).addClass('lbl-grey');
				console.log(i);
				console.log($(this).attr('class'));
				if(index==0){
					$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
				}else if(index==1){
					$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
				}else if(index==2){
					$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
				}else if(index==3){
					$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
				}else if(index==4){
					$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
				}
		});
		
if($('input[name="overall"]:checked').val()==1){
			
			$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
			$(this).prev().addClass('fifthicon-bold');
		}else if($('input[name="overall"]:checked').val()==2){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
		 	$(this).prev().addClass('fouricon-bold');
		}else if($('input[name="overall"]:checked').val()==3){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
		 	$(this).prev().addClass('thirdicon-bold');
		}else if($('input[name="overall"]:checked').val()==4){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
			$(this).prev().addClass('secondicon-bold');
		}else if($('input[name="overall"]:checked').val()==5){
			$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
		 	$(this).prev().addClass('firsticon-bold');
		}
		 
	 });
		$("#select").click(function() {
				
				console.log("clicked");
				$('#myModalVieww').modal('show');
				$('#name1').val(data.name);
				 $('#mobile1').val(data.mobile);
				 $('#requestId1').val(data.bookingId);
				 $('#desg').val(data.desg);
				var foodvalueval=$("input[name='foodradio']:checked").val();
				var housekeepingvalue=$("input[name='housekeeping']:checked").val();
				var checkincheckoutvalue=$("input[name='checkincheckout']:checked").val();
				var conductvalue=$("input[name='conduct']:checked").val();
				var cleanvalue=$("input[name='clean']:checked").val();
				var overall=$("input[name='overall']:checked").val();
				var sms=$('#description').val();
				$('input[name="foodradio"][type="radio"][value="' + foodvalueval +'"]').prop('checked', true);
				$('input[name="housekeeping"][type="radio"][value="' + housekeepingvalue +'"]').prop('checked', true);
				$('input[name="checkincheckout"][type="radio"][value="' + checkincheckoutvalue +'"]').prop('checked', true);
				$('input[name="conduct"][type="radio"][value="' + conductvalue +'"]').prop('checked', true);
				$('input[name="clean"][type="radio"][value="' + cleanvalue +'"]').prop('checked', true);
				$('input[name="overall"][type="radio"][value="' + overall +'"]').prop('checked', true);
				$('#description1').val(sms);
			})
			$(function() {
				$('input[name="checkincheckout"]').change(function() {
					$("#checkinbtn").prop('disabled', false);
				});
				$('input[name="housekeeping"]').change(function() {
					$("#housekeepingbtn").prop('disabled', false);
				});
				$('input[name="clean"]').change(function() {
					$("#cleanbtn").prop('disabled', false);
				});
				$('input[name="foodradio"]').change(function() {
					$("#foodbtn").prop('disabled', false);
				});					
				$('input[name="conduct"]').change(function() {
					$("#conductbtn").prop('disabled', false);
				});
				$('input[name="overall"]').change(function() {
					$("#overallbtn").prop('disabled', false);
				});
				
			});
			
						$(".next").click(
								function() {

									current_fs = $(this).parent();
									next_fs = $(this).parent().next();
									//Add Class Active
									$("#progressbar li").eq(
											$("fieldset")
													.index(next_fs))
											.addClass("active");
									$("#progressbar li strong").eq(
											$("fieldset")
													.index(next_fs))
											.addClass("text-dark");
									//show the next fieldset
								
									
										
									
									next_fs.show();
									//hide the current fieldset with style
									current_fs.animate({
										opacity : 0
									}, {
										step : function(now) {
											// for making fielset appear animation
											opacity = 1 - now;

											current_fs.css({
												'display' : 'none',
												'position' : 'relative'
											});
											next_fs.css({
												'opacity' : opacity
											});
										},
										duration : 500
									});
									setProgressBar(++current);
								});

						$(".previous").click(
								function() {

									current_fs = $(this).parent();
									previous_fs = $(this).parent()
											.prev();

									$("#progressbar li").eq(
											$("fieldset").index(
													current_fs))
											.removeClass("active");
									$("#progressbar li strong").eq(
											$("fieldset").index(
													current_fs))
											.removeClass("text-dark");

									previous_fs.show();

									current_fs.animate({
										opacity : 0
									}, {
										step : function(now) {
											opacity = 1 - now;
											current_fs.css({
												'display' : 'none',
												'position' : 'relative'
											});
											previous_fs.css({
												'opacity' : opacity
											});
										},
										duration : 500
									});
									setProgressBar(--current);
								});

						function setProgressBar(curStep) {
							var percent = parseFloat(100 / steps)
									* curStep;
							percent = percent.toFixed();
							$(".progress-bar").css("width",
									percent + "%")
						}

$("#submitbtn").click(function() {
 
 postData = {
		    "name" : $('#name').val(),
		    "mobile":$('#mobile').val(),
		    "requestId": $('#requestId').val(),
			"food":$("input[name='foodradio']:checked").val(),
	        "housekeeping":$("input[name='housekeeping']:checked").val(),
	        "checkincheckout":$("input[name='checkincheckout']:checked").val(),
	        "conduct":$("input[name='conduct']:checked").val(),
	        "clean":$("input[name='clean']:checked").val(),
	        "overall":$("input[name='overall']:checked").val(),
	        "feedbacksms":$('#description').val()
	}
	$.ajax({
		url: './guest/savefeedbackDetails.do',
		type: "POST",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: JSON.stringify(postData),
		contentType:"application/json",
		dataType:"JSON",					
		success:function(result) {
			console.log(result);
			swal(result.message);
			$('#myModalView').modal('hide');
			
			getFeedbackDetails();
		}
	}); 

})						

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
				render: function (data, type, row, meta) {
					if(data.feedbackId != "NA" || data.feedbackstatus =='Opened'|| data.feedbackstatus =='Closed' ){
						return '<button type="button" id="view" action="View"  title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
					}else if(data.status == 'COUT' ){
						return '<button type="button" id="add" action="add" title="Add" class="btn btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> '					
					}else{
						return '<button type="button" id="view" action="View" disabled title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
						
					}
				}
			}
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
		{"targets":3,"data":"repliedOn"},	
		], 	  
});	

	var getFeedbackDetails = function(){
		$.ajax({
			url: "./guest/getFeedbackDetails.do",
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
$('#feedbackusertable').on('click', 'tbody #add', function () {
	$('#myModalView').modal('show');
	 data = feedbackuserTable.row($(this).closest('tr')).data() != undefined ? feedbackuserTable.row($(this).closest('tr')).data() : feedbackuserTable.row($(this)).data();
	 $('#name').val(data.name);
	 $('#mobile').val(data.mobile);
	 $('#requestId').val(data.bookingId);
});

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
