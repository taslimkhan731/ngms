$('document')
.ready(
		function() {
			var x = [ {
				"name" : "Amrita",
				"address" : "bbsr",
				"categ" : "State Government",
				"fromDt":"08-05-2020 15:06",
				"toDt":"08-05-2020 15:06",
				"type": "Test",
				"status": "A",
				"reqDt": "08-05-2020 15:12"				
			} ]
			

			/* Validation ends */
			// $("#moderatorList").hide();
			$('#openBillModal').on('click', function () {
		         $('#closeModal').modal('hide');
		         $('#billModal').modal('show');
		    });
			$('#openAddModal').on('click', function () {
		         $('#addConfModal').modal('show');
		    });
			
			$("#hallList").show();
			$('#hallListDataTable tbody').on('click', '#payment', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#makeReceiptModal').modal('show');
		    });
			$('#hallListDataTable tbody').on('click', '#receipt', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#receiptModal').modal('show');
		    });
			$('#hallListDataTable tbody').on('click', '#close', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		        console.log("Data", data);
		        $("#catg").val(data.categ);
				$("#fromDt").val(data.fromDt);
		        $("#toDt").val(data.toDt);
				$("#type").val(data.type);
		        $("#addThings").val("xyz");
				$("#name").val(data.name);
				$("#charge").val("500");
		        $("#addr").val(data.address);
				$("#addCharge").val("750");
		         $('#closeModal').modal('show');
		    });

			var table = null;
			table = $("#hallListDataTable")
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
									"data" : "name"
								},
								{
									"targets" : 2,
									"data" : "address"
								},
								{
									"targets" : 3,
									"data" : "categ"
								},
								{
									"targets" : 4,
									"data" : "fromDt"
								},
								{
									"targets" : 5,
									"data" : "toDt"
								},
								{
									"targets" : 6,
									"data" : "type"
								},
								{
									"targets" : 7,
									"data" : "status"
								},
								{
									"targets" : 8,
									"data" : "reqDt"
								},
								{
									"targets" : 9,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="close" action="Close" title="Close" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-check" aria-hidden="true"></i></button> '
										+ '<button type="button" action="cancel" id="cancel" title="Cancel" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-close" aria-hidden="true"></i></button> '
										+ '<button type="button" id="payment" title="Payment" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-wpexplorer" aria-hidden="true"></i></button> '+
										'<button type="button" id="receipt" title="Receipt" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-superpowers" aria-hidden="true"></i></button> '
									}
								} ],
					});

			table.clear();
			table.rows.add(x).draw();
		});