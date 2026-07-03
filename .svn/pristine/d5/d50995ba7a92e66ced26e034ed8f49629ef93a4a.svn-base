$('document')
		.ready(
				function() {
					isEdit = false;
					var x = [ {
						"billId" : "1",
						"billNo" : "1",
						"createdBy" : "orgadmin",
						"date" : "30-04-2020 ",
						"reqId" : "G38",
						"guestDt" : "Amrita,xyz,pqr",
						"sFrom" : "30-04-2020 12:34",
						"sTo" : "0-04-2020 15:35",
						"room" : "ODBHAWAN - 104-B1",
						"amount" : "1572"
					} ]
					var y = [ {
						"particulars" : "Room Rent",
						"rupees" : ""
					}, {
						"particulars" : "Food",
						"rupees" : ""
					}, {
						"particulars" : "News paper",
						"rupees" : ""
					}, {
						"particulars" : "Travelling",
						"rupees" : ""
					}, {
						"particulars" : "Telephone",
						"rupees" : ""
					}, {
						"particulars" : "TV Charges",
						"rupees" : ""
					}, {
						"particulars" : "Others",
						"rupees" : ""
					} ]
					$("#selectReceipt").on("click", function() {
						$('#receiptModal').modal('show');
					});
					$("#makeReceipt").on("click", function() {
						$('#receiptModal').modal('hide');
						$('#makeReceiptModal').modal('show');
					});
					$("#billingList").show();

					var table = $("#receiptDataTable")
							.DataTable(
									{
										"paging" : true,
										"searching" : true,
										"scrollX" : true,
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
													"data" : "billId"
												},
												{
													"targets" : 1,
													"data" : "billNo"
												},
												{
													"targets" : 2,
													"data" : "createdBy"
												},
												{
													"targets" : 3,
													"data" : "date"
												},
												{
													"targets" : 4,
													"data" : "reqId"
												},
												{
													"targets" : 5,
													"data" : "guestDt"
												},
												{
													"targets" : 6,
													"data" : "sFrom"
												},
												{
													"targets" : 7,
													"data" : "sTo"
												},
												{
													"targets" : 8,
													"data" : "room"
												},
												{
													"targets" : 9,
													"data" : "amount"
												},
												{
													"targets" : 10,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<input type="checkbox" id="check" action="Check" class=""/>'
													}
												},
												/*{
													"targets" : 11,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<button type="button" id="cancel" title="Cancel" action="Cancel" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-close" aria-hidden="true"></i></button>'
													}
												} */],
									});
					table.clear();
					table.rows.add(x).draw();

					var table2 = $("#listDataTable").DataTable(
							{
								"paging" : false,
								"searching" : false,
								"lengthMenu" : [ [ 10, 20, 30, -1 ],
										[ 10, 20, 30, "All" ] ],
								"processing" : false,
								"ordering" : false,
								"info" : false,
								"bFilter" : false,
								"fixedHeader" : {
									header : true,
									footer : true
								},
								"scrollCollapse" : true,
								"columnDefs" : [ {
									"targets" : 0,
									"data" : "particulars"
								}, {
									"targets" : 1,
									"data" : "rupees"
								} ],
							});
					table2.clear();
					table2.rows.add(y).draw();
				});