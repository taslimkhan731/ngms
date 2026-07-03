$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	$("#toDt").datetimepicker({
		minDate: new Date(),
		format: 'DD-MM-YYYY'
	});
	
	var table = $("#guestDataTable").DataTable({  
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
			{"targets":0,"data": null, 
				render: function (data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{"targets":1,"data":"bookingId"},
			{"targets":2,"data":"ghName"},
			{"targets":3,"data":"fromDate"},
			{"targets":4,"data":"toDate"},
			{"targets":5,"data":"purpose"},
			{"targets":6,"data":"conveyance"},
			{"targets":7,"data":"statusDesc"},
			{"targets":8,"data": null, 
				render: function (data, type, row, meta) {
					if(data.status == 'CIN'){
						return '<button type="button" id="extention" title="Extention" action="extention" class="btn btn-sm btn-edit" data-toggle="modal" data-target="#myModalView"><i class="fa fa-pencil" aria-hidden="true"></i></button>' 
					}
				}
			}
			], 	  
	});	
	getNomineeRequestList(table);

	$('#guestDataTable').on('click', 'tbody #extention', function () {
		var data_row = table.row($(this).closest('tr')).data();
		$('#bookingId').html(data_row.bookingId);
		var date = '';
		var date1 = '';
		if(data_row.toDate != null){
			date = data_row.toDate.split('-');
			if(date[0].length == 4){
				var dt = '';
				if(date[2].length > 2){
					dt = date[2].split(' ');
				}
				date1 = date[0] + '-' + date[1] + '-' + dt[0]
				date = dt[0] + '-' + date[1] + '-' + date[0] + ' ' + dt[1];
				$('#fromDate').val(date1);
			}else{
				var dt = '';
				date = data_row.toDate.split('-');
				if(date[2].length > 4){
					dt = date[2].split(' ');
				}
				date1 = dt[0] + '-' + date[1] + '-' + date[0]
				$('#fromDate').val(date1);
				date = data_row.toDate;
			}
		}
		$('#frmDt').val(date);
	})

	$("#myModalView").on("hidden.bs.modal", function(){
		$(this).find('form').trigger('reset');
	});

	//Button Click
	$("#extBtn").click(function() {
		var date = '';
		var bookingId = $('#bookingId').html();
		var frmDt = $('#fromDate').val();
		var toDt = $('#toDt').val();
		if(toDt == ''){
			swal('To Date can not be blank');
			return false;
		}
		date = toDt.split('-');
		toDt = date[2] + '-' + date[1] + '-' + date[0]
		var g1 = new Date(toDt); 
		var g2 = new Date(frmDt); 
		if (g1.getTime() <= g2.getTime()) {
			swal('To Date must be grater than from date.');
			return false;
		}
		postData = {
				reqId : bookingId,
				toDate: toDt
		}
		$.ajax({
			url : "./guest/updateBookingExtension.do",
			type : "POST",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(postData),
			contentType:"application/json",
			dataType:"JSON",		 
			success:function(result) {
				if(result.success){
					 $('#myModalView').modal('hide');
					swal(result.msg);
					getNomineeRequestList(table);
				}else{
					swal(result.msg);
				}
			}
		});
	});
})

/**************************************************
 *   Get The PEDNING REQUESTS FROM ADDRESS ERROR MARK
 ***************************************************/
function getNomineeRequestList(table){
	$.ajax({
		url: './guest/getUserExtension.do',
		type: "GET",	
		headers : {"X-CSRF-TOKEN" : token},		 
		success:function(result) {
			table.clear().draw();
			table.rows.add(result.data).draw();
			//table.columns.adjust().responsive.recalc();
		}
	});
}