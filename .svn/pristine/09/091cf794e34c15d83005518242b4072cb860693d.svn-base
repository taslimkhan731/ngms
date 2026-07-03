$('document')
		.ready(
				function() {
					isEdit = false;
					var x = [ {
						"requestId" : "101",
						"fromDt" : "21-03-2020",
						"toDt": "30-03-2020",
						"guestDt": "Amrita"
					},{
						"requestId" : "102",
						"fromDt" : "21-03-2020",
						"toDt": "30-03-2020",
						"guestDt": "Amrita"
					},{
						"requestId" : "103",
						"fromDt" : "21-03-2020",
						"toDt": "30-03-2020",
						"guestDt": "Amrita"
					},{
						"requestId" : "104",
						"fromDt" : "21-03-2020",
						"toDt": "30-03-2020",
						"guestDt": "Amrita"
					} ]
					
					$("#fromDt, #toDt").datetimepicker({
						minDate : new Date(),
						format : 'DD-MM-YYYY'
					});

					$("#fromTm, #toTm").datetimepicker({
						format : 'hh:mm A'
					});

					$("#extensionList").show();
					$('#extensionDataTable tbody').on('click', '#extension', function () {
				        var data = table.row( $(this).parents('tr') ).data();
				         $('#extensionModal').modal('show');
				    });
					var table = $("#extensionDataTable")
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
													"data" : "requestId"
												},
												{
													"targets" : 2,
													"data" : "fromDt"
												},
												{
													"targets": 3,
													"data": "toDt"
												},
												{
													"targets": 4,
													"data": "guestDt"
												},
												{
													"targets" : 5,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<button type="button" id="extension" action="View" title="Extension Checkin" class="btn btn-secondary btn-sm btn-view text-center"><i class="fa fa-pencil" aria-hidden="true"></i> Extension</button> '

													}

												}
												],
									});
					table.clear();
					table.rows.add(x).draw();
				});