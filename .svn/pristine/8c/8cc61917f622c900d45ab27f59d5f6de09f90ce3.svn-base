$('document')
		.ready(
				function() {
					isEdit = false;
					var x = [ {
						"reqIdGuestId" : "G38",
						"rollNo" : "123",
						"guest" : "Amrita ,xyz ,pqr ,bbsr",
						"fromDt" : "30-04-2020 ",
						"toDt" : "31-04-2020 ",
						"catg" : "cat1",
						"purpose" : "Official",
						"adults" : "1",
						"children" : "0",
						"advance" : "1000"
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
					$("#selectBill").on("click", function() {
						$('#billModal').modal('show');
					});
					$("#createBill").on("click", function() {
						$('#billModal').modal('hide');
						$('#createBillModal').modal('show');
					});
					$("#billingList").show();

					var table = $("#billingDataTable")
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
													"data" : "reqIdGuestId"
												},
												{
													"targets" : 1,
													"data" : "rollNo"
												},
												{
													"targets" : 2,
													"data" : "guest"
												},
												{
													"targets" : 3,
													"data" : "fromDt"
												},
												{
													"targets" : 4,
													"data" : "toDt"
												},
												{
													"targets" : 5,
													"data" : "catg"
												},
												{
													"targets" : 6,
													"data" : "purpose"
												},
												{
													"targets" : 7,
													"data" : "adults"
												},
												{
													"targets" : 8,
													"data" : "children"
												},
												{
													"targets" : 9,
													"data" : "advance"
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
												{
													"targets" : 11,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<button type="button" id="cancel" title="Cancel" action="Cancel" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-close" aria-hidden="true"></i></button>'
													}
												} ],
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