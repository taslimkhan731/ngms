$('document')
		.ready(
				function() {
					var x = [ {
						"reqId" : "G38",
						"fromDt" : "29-04-2020",
						"toDt" : "30-04-2020",
						"room" : "104-B1",
						"guest" : "Amrita ,xyz ,pqr ,bbsr"
					}, {
						"reqId" : "G39",
						"fromDt" : "29-04-2020",
						"toDt" : "30-04-2020",
						"room" : "104-B2",
						"guest" : "Amrita ,xyz ,pqr ,bbsr"
					}, {
						"reqId" : "G40",
						"fromDt" : "29-04-2020",
						"toDt" : "30-04-2020",
						"room" : "104-B3",
						"guest" : "Amrita ,xyz ,pqr ,bbsr"
					} ]
					var y = [ {
						"reqId" : "G38",
						"guestId" : "G1",
						"guest" : "Amrita ,xyz ,pqr ,bbsr",
						"rollNo" : "123",
						"time" : "4:00",
						"advAmount" : "2000",
						"adults" : "1",
						"children" : "0",
						"room" : "1"
					} ]
					var z = [ {
						"reqId" : "G38",
						"guestId" : "G1",
						"guest" : "Amrita ,xyz ,pqr ,bbsr"
					} ]
					var z1 = [ {
						"reqId" : "G38",
						"guestId" : "G1",
						"guest" : "Amrita ,xyz ,pqr ,bbsr",
						"rollNo" : "123",
						"time" : "4:00",
						"advAmount" : "2000",
						"adults" : "1",
						"children" : "0",
						"room" : "1"
					} ]
					$('#currentDataTable2 tbody').on('click', '.checkout', function () {
				        var data = table.row( $(this).parents('tr') ).data();
						$("#checkouttime").datetimepicker({
							format : 'hh:mm A'
						});
				    });
					table = $("#checkInOutDataTable")
							.DataTable(
									{

										"lengthMenu" : [ [ 10, 20, 30, -1 ],
												[ 10, 20, 30, "All" ] ],
										"processing" : false,
										"ordering" : true,
										"info" : false,
										"bFilter" : true,
										"scrollX" : true,
										"paging" : true,
										"searching" : true,
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
													"data" : "fromDt"
												},
												{
													"targets" : 3,
													"data" : "toDt"
												},
												{
													"targets" : 4,
													"data" : "room"
												},
												{
													"targets" : 5,
													"data" : "guest"
												},
												{
													"targets" : 6,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<input type="checkbox" id="check" action="Check" class=""/>'
													}
												},
												{
													"targets" : 7,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<button type="button" id="checkIn" action="CheckIn" title="CheckIn" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-check" aria-hidden="true"></i></button> '
																+ '<button type="button" action="CheckOut" id="checkOut" title="CheckOut" class="btn btn-secondary btn-sm "><i class="fa fa-calendar-check-o" aria-hidden="true"></i></button> '
																+ '<button type="button" id="cancel" title="Cancel" action="Cancel" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-close" aria-hidden="true"></i></button> '

													}
												} ],
									});
					table.clear();
					table.rows.add(x).draw();

					$('#checkInOutDataTable tbody').on(
							'click',
							'#checkIn',
							function() {
								var data = table.row($(this).parents('tr'))
										.data();
								$('#checkInModal').modal('show');
							});

					$('#checkInOutDataTable tbody').on(
							'click',
							'#checkOut',
							function() {
								var data = table.row($(this).parents('tr'))
										.data();
								$('#checkOutModal').modal('show');
							});

					var table2 = null;
					table2 = $("#otherDataTable").DataTable({
						"bFilter" : false,
						// "scrollX": true,
						"paging" : false,
						"searching" : false,
						// "fixedHeader" : {
						// header : true,
						// footer : true
						// },
						// "scrollCollapse" : true,
						"columnDefs" : [ {
							"targets" : 0,
							"data" : "reqId"
						}, {
							"targets" : 1,
							"data" : "guestId"
						}, {
							"targets" : 2,
							"data" : "guest"
						}, {
							"targets" : 3,
							"data" : "rollNo"
						}, {
							"targets" : 4,
							"data" : "time"
						}, {
							"targets" : 5,
							"data" : "advAmount"
						}, {
							"targets" : 6,
							"data" : "adults"
						}, {
							"targets" : 7,
							"data" : "children"
						}, {
							"targets" : 8,
							"data" : "room"
						}

						],
					});
					/*
					 * table2.clear(); table2.rows.add(y).draw();
					 */
					mod1(table2)
					function mod1(table2) {
						table2.clear();
						table2.rows.add(y).draw();
					}

					var table3 = null;
					table3 = $("#currentDataTable")
							.DataTable(
									{
										"bFilter" : false,
										// "scrollX": true,
										"paging" : false,
										"searching" : false,
										// "fixedHeader" : {
										// header : true,
										// footer : true
										// },
										// "scrollCollapse" : true,
										"columnDefs" : [
												{
													"targets" : 0,
													"data" : "reqId"
												},
												{
													"targets" : 1,
													"data" : "guestId"
												},
												{
													"targets" : 2,
													"data" : "guest"
												},
												{
													"targets" : 3,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<input type="text" id="rollNo" class=""/>'

													}
												},
												{
													"targets" : 4,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<input type="text" id="time" class=""/>'

													}
												},
												{
													"targets" : 5,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<input type="text" id="advAmt" class="" placeholder="0"/>'

													}
												},
												{
													"targets" : 6,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<input type="text" id="adults" class="" placeholder="1"/>'

													}
												},
												{
													"targets" : 7,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<input type="text" id="children" class="" placeholder="0"/>'

													}
												},
												{
													"targets" : 8,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<select  id="category"><option value="1">Cat1</option><option value="2">Cat2</option></select>'

													}
												},
												{
													"targets" : 9,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<select  id="purpose"><option value="1">Official</option><option value="2">No-Official</option><option value="3">Patient</option><option value="4">Student</option></select>'

													}
												},
												{
													"targets" : 10,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<select  id="room"><option value="1">BL</option></select>'

													}
												} ],
									});
					/*
					 * table3.clear(); table3.rows.add(z).draw();
					 */
					mod(table3)
					function mod(table3) {
						table3.clear();
						table3.rows.add(z).draw();
					}

					var table4 = null;
					table4 = $("#otherDataTable2").DataTable({
						"bFilter" : false,
						// "scrollX": true,
						"paging" : false,
						"searching" : false,
						// "fixedHeader" : {
						// header : true,
						// footer : true
						// },
						// "scrollCollapse" : true,
						"columnDefs" : [ {
							"targets" : 0,
							"data" : "reqId"
						}, {
							"targets" : 1,
							"data" : "guestId"
						}, {
							"targets" : 2,
							"data" : "guest"
						}, {
							"targets" : 3,
							"data" : "rollNo"
						}, {
							"targets" : 4,
							"data" : "time"
						}, {
							"targets" : 5,
							"data" : "advAmount"
						}, {
							"targets" : 6,
							"data" : "adults"
						}, {
							"targets" : 7,
							"data" : "children"
						}, {
							"targets" : 8,
							"data" : "room"
						}

						],
					});
					/*
					 * table2.clear(); table2.rows.add(y).draw();
					 */
					mod1(table4)
					function mod1(table4) {
						table4.clear();
						table4.rows.add(y).draw();
					}

					var table5 = null;
					table5 = $("#currentDataTable2")
							.DataTable(
									{
										"bFilter" : false,
										// "scrollX": true,
										"paging" : false,
										"searching" : false,
										// "fixedHeader" : {
										// header : true,
										// footer : true
										// },
										// "scrollCollapse" : true,
										"columnDefs" : [
												{
													"targets" : 0,
													"data" : "reqId"
												},
												{
													"targets" : 1,
													"data" : "guestId"
												},
												{
													"targets" : 2,
													"data" : "guest"
												},
												{
													"targets" : 3,
													"data" : "rollNo"
												},
												{
													"targets" : 4,
													"data" : "time"
												},
												{
													"targets" : 5,
													"data" : "advAmount"
												},
												{
													"targets" : 6,
													"data" : "adults"
												},
												{
													"targets" : 7,
													"data" : "children"
												},
												{
													"targets" : 8,
													"data" : "room"
												},
												{
													"targets" : 9,
													"data" : null,
													render : function(data,
															type, row, meta) {
														return '<input type="text" placeholder="time" id="checkouttime" name="checkouttime" class="form-control checkout">'

													}
												} ],
									});
					/*
					 * table3.clear(); table3.rows.add(z).draw();
					 */
					mod(table5)
					function mod(table5) {
						table5.clear();
						table5.rows.add(z1).draw();
					}
				})