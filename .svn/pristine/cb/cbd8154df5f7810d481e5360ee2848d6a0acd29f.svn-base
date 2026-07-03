
$('document').ready(function() {
// var x = [ {
// "proceeId" : "123",
// "createdDate" : "22-10-2019",
// "statusDesc" : "Active"
// } ]
// var x2 = [ {
// "reqId" : "123",
// "blockId" : "1",
// "reqDate" : "22-10-2019",
// "fromDate" : "22-10-2019",
// "vlcId" : "23",
// "quataType" : "xyz"
// } ]

			var x3 = [ {
				"processId" : "123",
				"createdBy" : "1",
				"createdDate" : "22-10-2019"
			} ]

			/* Validation ends */
			// $("#moderatorList").hide();
			$("#draftDetailsList").hide();
			$("#confirmDetailsList").hide();
			$("#draftListModal").on("hidden.bs.modal", function() {
				$("#formPassword")[0].reset();
				var validator = $("#formPassword").validate();
				validator.resetForm();
			});

			 $('#draftList').on('click', function() {
					$("#moderatorList").hide();
					$("#confirmDetailsList").hide();
					$("#draftDetailsList").show();
					$("#draftListDataTable").show();
					
				});
			 
			$('#draftListDataTable tbody').on('click', '#view', function () {
		        var data = table.row( $(this).parents('tr') ).data();
// $('#loading').show();
		        $('#viewModal').modal('show');
		        $("#cardBody :input").prop("disabled", true);
		        });

			

			var table1 = null;
			table1 = $("#draftListDataTable")
			.DataTable(
					{
						"paging" : true,
						"searching" : true,
// "scrollX": true,
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
									"data" : "processId"
								},
								{
									"targets" : 2,
									"data" : "createdDate"
								},
								{
									"targets" : 3,
									"data" : "status"
								},
								{
									"targets" : 4,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="view" action="View" title="View" class="btn btn-secondary btn-sm "><i class="fa fa-eye" aria-hidden="true"></i></button> '
										+ '<button type="button" id="accept" action="accept"  title="Accept" class="btn btn-info btn-sm "><i class="fa fa-check" aria-hidden="true"></i></button> '
										+ '<button type="button" id="cancel" action="cancel" title="Reject"  class="btn btn-danger btn-sm "><i class="fa fa-close" aria-hidden="true"></i></button> '

									}
								} ],
					});
			
			

       

			$('#modeList').on('click', function() {
				$("#moderatorList").show();
				// $("#modListDataTable").show()
				$("#draftDetailsList").hide();
				$("#confirmDetailsList").hide();

			});
			var table = $("#modListDataTable")
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
									"data" : "bookingId"
								}, {
									"targets" : 2,
									"data" : "blockId"
								},{
									"targets" : 3,
									"data" : "reqDate"
								}, {
									"targets" : 4,
									"data" : "frmDt"
								}, {
									"targets" : 5,
									"data" : "vcId"
								},{
									"targets" : 6,
									"data": "quotatype"
								}

								],
					});
			
		
			$('#confirmedList').on('click', function() {
				$("#draftDetailsList").hide();
				$("#moderatorList").hide();
				$("#confirmDetailsList").show();
				$("#confirmListDataTable").show();
				
				table2.clear();
				table2.rows.add(x3).draw();

			});
			
			var table2 = null;
			var table2 = $("#confirmListDataTable")
			.DataTable(
					{
						"paging" : true,
						"searching" : true,
// "scrollX": true,
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
									"data" : "processId"
								}, {
									"targets" : 2,
									"data" : "createdBy"
								}, {
									"targets" : 3,
									"data" : "createdDate"
								},{
									"targets" : 4,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="view" action="View" title="Print" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-print" aria-hidden="true"></i></button> '
										

										// }else{
								}
								}

								],
					});
			
			$('#confirmListDataTable tbody').on('click', '#view', function () {
		        var data = table.row( $(this).parents('tr') ).data();
// $('#loading').show();
		        $('#viewModal').modal('show');
		        });
			

			
			
			$('#city').on('change', function() {
				var id = $('#city').val();
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
// $("#gHouse").val("");
						$("#gHouse").append('<option value="">---Select---</option>');
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
			
			var getallProcesslist  = function() {
				 var city=$('#city').val();
				 var gHouse = $('#gHouse').val();
				  console.log(city);
				  console.log(gHouse);
				$.ajax({
					url : './admin/allProcessApplicationList.do',
					type : "GET",			
					contentType : "application/json",
					dataType : "JSON",
					data:{
				        cityName:city,
				        guesthouseName:gHouse
				    },
					success : function(data) {
						console.log(data);
						table.clear();
						table.rows.add(data.data).draw();				
					},
					error : function(jqXHR, status, error) {
						swal(error);
					}
				});

			}
			$("#city").change(function(){
				getallProcesslist();		
			});
			$("#gHouse").change(function(){
				getallProcesslist();		
			});
			getallProcesslist();
			
			$('#process').on('click', function(){
				saveallProcesslist();
			});
			
			
			var saveallProcesslist  = function() {
				 var city=$('#city').val();
				 var gHouse = $('#gHouse').val();
				 var sign = $('input[name=gosignature]:checked').val();
				 console.log(sign);
				  console.log(city);
				  console.log(gHouse);
				$.ajax({
					url : './admin/saveProcessApplicationList.do',
					type : "GET",			
					contentType : "application/json",
					dataType : "JSON",
					data:{
				        cityId:city,
				        guesthouseId:gHouse,
				        sign:sign
				    },
					success : function(data) {
						console.log(data);
						getallProcesslist();
						swal(data.msg);
						table.clear();
					},
					error : function(jqXHR, status, error) {
						swal(error);
					}
				});

			}
			
			
			var getallDraftlist  = function() {
				 var city=$('#city').val();
				 var gHouse = $('#gHouse').val();
				  console.log(city);
				  console.log(gHouse);
				$.ajax({
					url : './admin/allDraftApplicationList.do',
					type : "GET",			
					contentType : "application/json",
					dataType : "JSON",
					data:{
				        cityName:city,
				        guesthouseName:gHouse
				    },
					success : function(data) {
						console.log(data);
						table1.clear();
						table1.rows.add(data.data).draw();				
					},
					error : function(jqXHR, status, error) {
						swal(error);
					}
				});

			}
			
			
			$("#city").change(function(){
				getallDraftlist();		
			});
			$("#gHouse").change(function(){
				getallDraftlist();		
			});
			getallDraftlist();
			

	           
			$('#draftListDataTable tbody').on('click', '#accept', function () {
					
			        var data = table1.row( $(this).parents('tr') ).data();
			        console.log(data);
			        getGuestDetailsByProcessid(data.processId); 
			        $('#acceptModal').modal('show');
			        
			       
			    });
				
			var getGuestDetailsByProcessid = function(id) {
						$.ajax({
							url : "./admin/getDetailsByprocessId/" +id+ ".do",
							type : "GET",
							
							success : function(result) {
								console.log(result);
								$("#card-body").html("");						
								$("#card-body").append(
										'<tr>'+
										'<th> ProcessId :</th>'+
										'<td >'+  result.data.processId +  '</td>'+
										'</tr>'+
										
										'<tr>'+
										'<th> CreateBy :</th>'+
										'<td >'+  result.data.createdBy +  '</td>'+
										'</tr>'+
										
										'<tr>'+
										'<th> CreatedDate :</th>'+
										'<td >'+  result.data.createdDate +  '</td>'+
										'</tr>'									
	  							);
								
							
							},

							error : function(jqXHR, status, error) {
								swal(error);
							}

						});
					}
				
		
			
			$('#draftListDataTable tbody').on('click', '#cancel', function () {
			var data = table.row($(this).closest('tr')).data() != undefined ?
					table.row($(this).closest('tr')).data() : table.row($(this)).data();
					
			swal({
				title: "Are you sure? ",
				text: "Are you sure to cancel!",
				type: "warning",
				showCancelButton: true,
				confirmButtonClass: "btn-danger",
				confirmButtonText: "Yes, cancel it!",
				closeOnConfirm: false
			},
			
			function(){
				$('#loading').show();
				$.ajax({
					url: './admin/cancelProcessRequest/'+data.processId + ".do",
					type: "GET",	
					headers : {"X-CSRF-TOKEN" : token},		 
					success:function(result) {
						$('#loading').hide();
						if(result.success){
							swal(result.msg);
							
						   
						}else {
							swal(result.msg);
							
						}
					}
				});
			});
		})
		

			
			
			});
			
			
			