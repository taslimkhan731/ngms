$('document')
.ready(
		function() {
			var x = [ {
				"proceeId" : "123",
				"createdDate" : "22-10-2019",
				"statusDesc" : "Active"
			} ]
		

			var x3 = [ {
				"processId" : "123",
				"createdBy" : "1",
				"createdDate" : "22-10-2019"
			} ]
			
			$("#avail_list").hide();
			$("#changesList").hide();
			$("#cancelList").hide();
			$("#additinalList").hide();
			$("#allot").on("hidden.bs.modal", function() {
				$("#formPassword")[0].reset();
				var validator = $("#formPassword").validate();
				validator.resetForm();
			});
			$('#avail').on('click', function() {
				$("#avail_list").show();

			});
			$('#allotmentListDataTable tbody').on('click', '#allot', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#allotmentModal').modal('show');
		    });
			$('#changeListDataTable tbody').on('click', '#allot', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#allotmentModal').modal('show');
		    });
			$('#cancelListDataTable tbody').on('click', '#allot', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#allotmentModal').modal('show');
		    });
			$('#addListDataTable tbody').on('click', '#allot', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#allotmentModal').modal('show');
		    });
					
		
//			***************************
			
			$('#city').on('change', function() {
				var id = $('#city').val();
				console.log(id);
				getGuestHouseListDtls(id);
			});

			var getGuestHouseListDtls = function(id){
				$.ajax({
					url: "./admin/getGuestHouseListDetails/" + id + ".do",
					type: "GET",
					contentType:"application/json",
					beforeSend: function(xhr) {
						$('#loading').show();
						xhr.setRequestHeader(header, token);
					},
					success: function(data, textStatus, jqXHR) {
						console.log(data);
						$('#loading').hide();
						$("#gHouse").html("");
						
					 $("#gHouse").append('<option value="">---Select guest house---</option>');
						data.forEach(option=>{
							$("#gHouse").append('<option value="'+option.id+'">'+option.name+'</option>');
						})
					}, error:function(jqXHR, status, error){
						swal(error);
					}
				});
			}
			var getCityListDtls = function(){
				$.ajax({
					url: "./admin/getCityDetails.do",
					type: "POST",
					contentType:"application/json",
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token);
					},
					success: function(data, textStatus, jqXHR) {
						console.log(data);
						$("#city").html("");
						$("#city").append('<option value="">Select City</option>');
						data.forEach(option=>{
							$("#city").append('<option value="'+option.id+'">'+option.name+'</option>');
						})
					}, error:function(jqXHR, status, error){
						swal(error);
					}
				});
			}
			getCityListDtls ();
			

			var contable = null;
			contable = $("#changeListDataTable")
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
								}, {
									"targets" : 1,
									"data" : "reqId"
								}, {
									"targets" : 2,
									"data" : "name"
								},{
									"targets" : 3,
									"data" : "designation"
								}, {
									"targets" : 4,
									"data" : "dept"
								}, {
									"targets" : 5,
									"data" : "fromDate"
								},{
									"targets" : 6,
									"data": "toDate"
								},{
									"targets" : 7,
									"data": "purpose"
								},{
									"targets" : 8,
									"data": "type"
								},{
									"targets" : 9,
									"data": "status"
								},{
									"targets" : 10,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="allot" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-map-marker" aria-hidden="true"></i>Make Allotment</button> '
										

									}

								}],
					});

			$('#change').on('click', function() {
				$("#avail_list").hide();
				$("#allotmentList").hide();
				$("#cancelList").hide();
				$("#additinalList").hide();
				$("#changesList").show();
				$("#changeListDataTable").show();
				contable.clear();
				contable.rows.add(x2).draw();
			})

			$('#allotment').on('click', function() {
				$("#avail_list").hide();
				$("#allotmentList").show();
				$("#cancelList").hide();
				$("#additinalList").hide();
				$("#changesList").hide();

			});
			
			
			var table = $("#allotmentListDataTable")
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
								}, {
									"targets" : 1,
									"data" : "reqId"
								}, {
									"targets" : 2,
									"data" : "name"
								},{
									"targets" : 3,
									"data" : "designation"
								}, {
									"targets" : 4,
									"data" : "dept"
								}, {
									"targets" : 5,
									"data" : "fromDate"
								},{
									"targets" : 6,
									"data": "toDate"
								},{
									"targets" : 7,
									"data": "purpose"
								},{
									"targets" : 8,
									"data": "type"
								},{
									"targets" : 9,
									"data": "status"
								},{
									"targets" : 10,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="allot" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-map-marker" aria-hidden="true"></i>Make Allotment</button> '
										

									}

								}],
					});
			mod(table)
			function mod(table){
			}

			$('#cancel').on('click', function() {
				$("#avail_list").hide();
				$("#additinalList").hide()
				$("#allotmentList").hide();
				$("#additinalList").hide();
				$("#changesList").hide()
				$("#cancelList").show();;
				$("#cancelListDataTable").show();

			});
			var table2 = null;
			var table2 = $("#cancelListDataTable")
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
								}, {
									"targets" : 1,
									"data" : "reqId"
								}, {
									"targets" : 2,
									"data" : "name"
								},{
									"targets" : 3,
									"data" : "designation"
								}, {
									"targets" : 4,
									"data" : "dept"
								}, {
									"targets" : 5,
									"data" : "fromDate"
								},{
									"targets" : 6,
									"data": "toDate"
								},{
									"targets" : 7,
									"data": "purpose"
								},{
									"targets" : 8,
									"data": "type"
								},{
									"targets" : 9,
									"data": "status"
								},{
									"targets" : 10,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="allot" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-map-marker" aria-hidden="true"></i>Make Allotment</button> '
										

									}

								}],
					});
			table2.clear();
			table2.rows.add(x2).draw();
			
			$('#addBed').on('click', function() {
				$("#avail_list").hide();
				$("#allotmentList").hide();
				$("#cancelList").hide();
				$("#additinalList").show();
				$("#changesList").hide();

			});
			var table = $("#addListDataTable")
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
								}, {
									"targets" : 1,
									"data" : "reqId"
								}, {
									"targets" : 2,
									"data" : "name"
								},{
									"targets" : 3,
									"data" : "designation"
								}, {
									"targets" : 4,
									"data" : "dept"
								}, {
									"targets" : 5,
									"data" : "fromDate"
								},{
									"targets" : 6,
									"data": "toDate"
								},{
									"targets" : 7,
									"data": "purpose"
								},{
									"targets" : 8,
									"data": "type"
								},{
									"targets" : 9,
									"data": "status"
								},{
									"targets" : 10,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="allot" action="View" title="Allotment" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-map-marker" aria-hidden="true"></i>Make Allotment</button> '
										

									}

								}],
					});
			mod(table)
			function mod(table){
				table.clear();
				table.rows.add(x2).draw();
			}


		});