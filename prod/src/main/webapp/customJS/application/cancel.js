/**
 * 
 */
$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	
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
					if(data.status == 'ALO'){
						return '<button type="button" action="cancel" id="cancel" title="Cancel" class="btn btn-sm btn-delete"><i class="fa fa-times" aria-hidden="true"></i></button>' 
					}
				}
			}
			], 	  
	});	
	getNomineeRequestList(table);
	$('#guestDataTable').on('click', 'tbody #cancel', function () {
		var data_row = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
		swal({
			title: "Are you sure? ",
			text: "Are you sure to cancel!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, cancel it!",
			closeOnConfirm: false
		},
		function(){
			$('#loading').show();
			$.ajax({
				url: './guest/cancelRequest1/'+data_row.bookingId + ".do",
				type: "GET",	
				headers : {"X-CSRF-TOKEN" : token},		 
				success:function(result) {
					$('#loading').hide();
					if(result.success){
						swal(result.msg);
						getNomineeRequestList(table);
					}else {
						swal(result.msg);
					}
				}
			});
		});
	})


})

/**************************************************
 *   Get The PEDNING REQUESTS FROM ADDRESS ERROR MARK
 ***************************************************/
function getNomineeRequestList(table){
	$.ajax({
		url: './guest/getUserCancel.do',
		type: "GET",	
		headers : {"X-CSRF-TOKEN" : token},		 
		success:function(result) {
			table.clear().draw();
			table.rows.add(result.data).draw();
			table.columns.adjust().responsive.recalc();
		}
	});
}