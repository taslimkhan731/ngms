$('document').ready(function() {
	var x = [ {
		"billNo" : "1",
		"rollNo" : "1",
		"name" : "Pantry Collection",
		"dcbType" : "123",
		"amount" : "1000",
		"status" : "Bill",
		"date" : "22-04-2020"
	} ]
	$('#miscellaneous').on('click', function() {
		$("#miscellaneousModal").show();
	});
var table = null;
	table = $("#misscellaneousDataTable")
	.DataTable(
			{
				"paging" : true,
				"searching" : true,
				"scrollX": true,
				"lengthMenu" : [ [ 10, 20, 30, -1 ],
					[ 10, 20, 30, "All" ] ],
					"processing" : false,
					"ordering" : true,
					"info" : false,
					"bFilter" : false,
					"bLengthChange": false,
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
							"data" : "billNo"
						}, {
							"targets" : 2,
							"data" : "rollNo"
						},{
							"targets" : 3,
							"data" : "name"
						}, {
							"targets" : 4,
							"data" : "dcbType"
						}, {
							"targets" : 5,
							"data" : "amount"
						},{
							"targets" : 6,
							"data" : "status"
						}, {
							"targets" : 7,
							"data" : "date"
						},{
							"targets" : 8,
							"data" : null,
							render : function(data,
									type, row, meta) {
								// if(data.status ==
								// 'REQ'){
								return '<button type="button" id="bill" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-check-square-o" aria-hidden="true"></i>Bill</button> '
								+ '<button type="button" id="payment" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-check-square-o" aria-hidden="true"></i>Payment</button> '+
								'<button type="button" id="cancel" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-check-square-o" aria-hidden="true"></i>Cancel</button> '

							}

						}],
			});
	table.clear();
	table.rows.add(x).draw();
	
	$('#misscellaneousDataTable tbody').on('click', '#bill', function () {
        var data = table.row( $(this).parents('tr') ).data();
         $('#billModal').modal('show');
    });
	
	$('#misscellaneousDataTable tbody').on('click', '#payment', function () {
        var data = table.row( $(this).parents('tr') ).data();
         $('#paymentModal').modal('show');
    });
	
	$('#misscellaneousDataTable tbody').on('click', '#cancel', function () {
        var data = table.row( $(this).parents('tr') ).data();
        confirm("Do you want to cancel?");
    });
});