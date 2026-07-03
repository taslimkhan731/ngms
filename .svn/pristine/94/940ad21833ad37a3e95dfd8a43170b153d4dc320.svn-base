$('document').ready(function() {
	$('#vehicleDataTable tbody').on('click', '#openVehicleModal', function() {
         $('#vehicleModal').modal('show');
	})

	$("#timeReport, #timeReport2, #startTime, #endTime, #startTime2, #endTime2").datetimepicker({
		/*minDate : new Date(),
		format : 'DD-MM-YYYY hh:mm A'*/
		format : 'hh:mm A'
	})
	var x = [ {
		"proName" : "Test",
		"proNo": "9337104758",
		"vNo": "OD-02-AA-2789",
		"vDes": "INNOVA"
	}]
	var table = null;
	table = $("#requestListDataTable")
	.DataTable(
			{
				
				"lengthMenu" : [ [ 10, 20, 30, -1 ],
					[ 10, 20, 30, "All" ] ],
					"processing" : false,
					"ordering" : false,
					"info" : false,
					"bFilter" : false,
//					"scrollX": true,
					"paging" : true,
					"searching" : false,
					"fixedHeader" : {
						header : true,
						footer : true
					},
					"scrollCollapse" : true,
					"columnDefs" : [
						{
							"targets" : 0,
							"data" : null,
							render : function(data,
									type, row, meta) {
								return meta.row
								+ meta.settings._iDisplayStart
								+ 1;
							}
						}, {
							"targets" : 1,
							"data" : "proName"
						}, {
							"targets" : 2,
							"data" : "proNo"
						},{
							"targets" : 3,
							"data" : "vNo"
						}, {
							"targets" : 4,
							"data" : "vDes"
						}],
			});
	table.clear();
	table.rows.add(x).draw();
})