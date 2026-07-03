$('document')
.ready(
		function() {
			var x = [ {
				"reqId" : "G39",
				"guestId" : "59",
				"name" : "Amrita",
				"desig":"xyz",
				"dept":"pqr",
				"receiptId": "1",
				"dueDt": "08-05-2020 13:28",
				"balanceAmnt": "412.0"				
			} ]
			

			$("#dueList").show();
			$('#dueDataTable tbody').on('click', '#payment', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#makeReceiptModal').modal('show');
		    });

			var table = null;
			table = $("#dueDataTable")
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
								},
								{
									"targets" : 1,
									"data" : "reqId"
								},
								{
									"targets" : 2,
									"data" : "guestId"
								},
								{
									"targets" : 3,
									"data" : "name"
								},
								{
									"targets" : 4,
									"data" : "desig"
								},
								{
									"targets" : 5,
									"data" : "dept"
								},
								{
									"targets" : 6,
									"data" : "receiptId"
								},
								{
									"targets" : 7,
									"data" : "dueDt"
								},
								{
									"targets" : 8,
									"data" : "balanceAmnt"
								},
								{
									"targets" : 9,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return  '<button type="button" id="payment" title="Payment" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-wpexplorer" aria-hidden="true"></i></button> '
										
									}
								} ],
					});

			table.clear();
			table.rows.add(x).draw();
		});