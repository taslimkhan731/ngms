$('document')
		.ready(
				function() {
					isEdit = false;
					var x = [ {
						"roomNo" : "101",
						"name" : "Amrita",
						"purpose": "xyz"
					},{
						"roomNo" : "102",
						"name" : "Amrita",
						"purpose": "xyz"
					},{
						"roomNo" : "103",
						"name" : "Amrita",
						"purpose": "xyz"
					},{
						"roomNo" : "104",
						"name" : "Amrita",
						"purpose": "xyz"
					},{
						"roomNo" : "105",
						"name" : "Amrita",
						"purpose": "xyz"
					},{
						"roomNo" : "106",
						"name" : "Amrita",
						"purpose": "xyz"
					}, ]

					$("#occupancyList").show();

					var table = $("#occupancyDataTable")
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
													"data" : "roomNo"
												},
												{
													"targets" : 2,
													"data" : "name"
												},
												{
													"targets": 3,
													"data": "purpose"
												}
												],
									});
					table.clear();
					table.rows.add(x).draw();
				});