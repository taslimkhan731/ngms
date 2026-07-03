$('document')
		.ready(
				function() {
					isEdit = false;
					var x = [ {
						"item" : "Rice",
						"price" : "20",
					}, {
						"item" : "Fish curry",
						"price" : "30",
					}, {
						"item" : "Chiken Curry",
						"price" : "70",
					}, {
						"item" : "Veg Curry",
						"price" : "50",
					}, {
						"item" : "Snacks",
						"price" : "30",
					}, {
						"item" : "veg manchurian",
						"price" : "60",
					} ]

					$("#itemList").show();
					$("#editList").hide();
					$("#deleteList").hide();
					
					$('#go').on('click', function() {
						$('#foodDetailModal').modal('show');

					});
					
					$('#add').on('click', function() {
						document.getElementById("modalTitle").innerHTML = "Add Food Details";
						document.getElementById("modalBtn").innerHTML = "Add";
						$("#item").val('');
						$("#rate").val('');
						$('#addModal').modal('show');

					});
					$('#editDataTable tbody').on('click', '#edit', function() {
						document.getElementById("modalTitle").innerHTML = "Edit Food Details";
						document.getElementById("modalBtn").innerHTML = "Update";
						var data = table.row($(this).parents('tr')).data();
						$("#item").val(data.item);
						$("#rate").val(data.price);
						console.log("***************", data);
						$('#addModal').modal('show');
					});
					var contable = null;
					contable = $("#foodMasterDataTable")
							.DataTable(
									{
										"paging" : true,
										"searching" : true,
										"scrollX":true,
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
												}, {
													"targets" : 1,
													"data" : "item"
												}, {
													"targets" : 2,
													"data" : "price"
												} ],
									});
					contable.clear();
					contable.rows.add(x).draw();

					$('#view').on('click', function() {
						$("#editList").hide();
						$("#deleteList").hide();
						$("#itemList").show();
					})

					$('#edit').on('click', function() {
						$("#editList").show();
						$("#deleteList").hide();
						$("#itemList").hide();

					});
					var table = $("#editDataTable")
							.DataTable(
									{
										"paging" : true,
										"searching" : true,
//										"scrollX":true,
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
													"data" : "item"
												},
												{
													"targets" : 2,
													"data" : "price"
												},
												{
													"targets" : 3,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<button type="button" id="edit" action="View" title="Edit" class="btn btn-secondary btn-sm btn-view text-center"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button> '

													}

												} ],
									});
					mod(table)
					function mod(table) {
						table.clear();
						table.rows.add(x).draw();
					}

					$('#delete').on('click', function() {
						$("#editList").hide();
						$("#itemList").hide();
						$("#deleteList").show();
						$("#deleteDataTable").show();

					});
					var table2 = null;
					var table2 = $("#deleteDataTable")
							.DataTable(
									{
										"paging" : true,
										"searching" : true,
//										"scrollX":true,
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
													"data" : "item"
												},
												{
													"targets" : 2,
													"data" : "price"
												},
												{
													"targets" : 3,
													"data" : null,
													render : function(data,
															type, row, meta) {
														// if(data.status ==
														// 'REQ'){
														return '<button type="button" id="delete" action="delete" title="delete" class="btn btn-secondary btn-sm btn-view text-center"><i class="fa fa-remove" aria-hidden="true"></i> Delete</button> '

													}

												} ],
									});
					table2.clear();
					table2.rows.add(x).draw();
				});