$('document').ready(
		function() {

		
          // $("#applicationList").hide();
             $("#editModeratorList").hide();

		
          // $("#applicationList").hide();
			$("#editModerationList").hide();

		var id1;
		var id2;
		var dataget;
		var datamod;
		var count=0;
		$("#editmoderationDataTable").hide();
		$("#editModeratorList").hide();

			
			$("#moderationn").on("hidden.bs.modal", function() {
				alert("test")
				$("#formPassword")[0].reset();
				var validator = $("#formPassword").validate();
				validator.resetForm();
			});

			$('#editList').on('click', function() {
				$("#editModeratorList").show();
		
        	});

			
			

			$('#applicationDataTable tbody').on('click', '#moderationn', function () {
				
		        var data = table.row( $(this).parents('tr') ).data();
		        console.log(data);
		        console.log("-------moderation------------", data.reqId);
		        id1=data.reqId;
		        getGuestDetailsByReqid(data.reqId); 
		        getroomCategory();

		        $('#loading').hide();
		        $('#moderationModal').modal('show');
		        $('#submit').show();
			    $('#update').hide();
			    getDataEditModeration();

		    });
			
              
			var table = $("#applicationDataTable")
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
								},{
									"targets" : 1,
									"data" : "reqId"
								},
								{
									"targets" : 2,
									"data" : "guestName"
								},{
									"targets" : 3,
									"data" : "gender"
								},
								{
									"targets" : 4,
									"data" : "fromDate"
								},{
									"targets" : 5,
									"data" : "toDate"
								},
								{
									"targets" : 6,
									"data" : "req_date"
								},
								{
									"targets" : 7,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="moderationn" action="Add" title="Moderationn" class="btn btn-success btn-sm btn-mod"><i class="fa fa-check-square-o" aria-hidden="true"></i> Moderation</button> '
										

									}
								},
								{
									"targets" : 8,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
                                       
										return  '<button type="button" action="cancel" id="cancel" title="Cancel" class="btn btn-sm btn-delete"><i class="fa fa-times" aria-hidden="true"></i></button> ' 

									}
								}
								],
					});
			
			$('#applicationDataTable tbody').on('click', '#cancel', function () {
				var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
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
						url: './admin/cancelRequest/'+data.reqId + ".do",
						type: "GET",	
						headers : {"X-CSRF-TOKEN" : token},		 
						success:function(result) {
							$('#loading').hide();
							if(result.success){
								swal(result.msg);
								location.reload();
							   
							}else {
								swal(result.msg);
								
							}
						}
					});
				});
			})
			
				
			$('#editDataTable tbody').on('click', '#onlyview', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		        console.log("-------monly view this page------------", data.reqId);
		        getGuestDetailsByReqid(data.reqId); 
		        getroomCategory();
		        $('#loading').hide();
		        $('#moderationModal').modal('show');
		        $("#modalButton").hide();
		        $("#cardBody :input").prop("disabled", true);
		        });
			
              $('#editDataTable tbody').on('click', '#edit', function () {
				
		        var data = table.row( $(this).parents('tr') ).data();
		        console.log("-------moderation------------", data.reqId);
		        getGuestDetailsByReqid(data.reqId); 
		        getroomCategory();
		        $('#loading').hide();
		        $('#moderationModal').modal('show');
		        
		       
		    });

			var contable = null;
			contable = $("#editDataTable")
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
									"data" : "guestName"
								},{
									"targets" : 3,
									"data" : "gender"
								},
								{
									"targets" : 4,
									"data" : "fromDate"
								},{
									"targets" : 5,
									"data" : "toDate"
								},
								{
									"targets" : 6,
									"data" : "req_date"
								},
								{
									"targets" : 7,
									"data" : null,
									render : function(data,
											type, row, meta) {
										// if(data.status ==
										// 'REQ'){
										return '<button type="button" id="onlyview" action="View" title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
										+'<button type="button" id="edit" action="Edit" title="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-edit" aria-hidden="true"></i></button> '


									}
								}
								],
					});
			$('#editList').on('click', function() {
				$("#applicationList").hide();
				$("#editModeratorList").show();
				$("#editDataTable").show();
				contable.clear();
			})

			function refreshPage(){
					location.reload();
				}				
			
// ******************
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
			

			var getDataToGadRequestTable = function() {
				 var city=$('#city').val();
				 var gHouse = $('#gHouse').val();
				  console.log(city);
				  console.log(gHouse);
				$.ajax({
					url : './admin/allApplicationDataList.do',
					type : "GET",
					
					contentType : "application/json",
					dataType : "JSON",
					data:{
				        cityName:city,
				        guesthouseName:gHouse
				    },
					success : function(result) {
						console.log(result);
						table.clear();
						table.rows.add(result.data).draw();
						
					},
					error : function(jqXHR, status, error) {
						swal(error);
					}
				});

			}
			getDataToGadRequestTable ();
			
			$("#city").change(function(){
				
				getDataEditModeration ();
				getDataToGadRequestTable();
			});
			$("#gHouse").change(function(){
				
				getDataEditModeration ();
				getDataToGadRequestTable();
				
			});
			
// ****************************************
			$('#categ').on('change', function() {
				getroomStatus(dataget);
				
				});
			var getroomStatus = function(data) {		  
				 var ctype = $("#categ").val();
				 var gh_id= data.data[0].cityId;
				 var from_date=data.data[0].frmDt;
				 var to_date=data.data[0].toDt;
				 var reqId=data.data[0].bookingId;
				  console.log(gh_id);
				  console.log(ctype);
				  console.log(reqId);
				$.ajax({
					url : './admin/getroomstatus.do',
					type : "GET",	
					contentType : "application/json",
					dataType : "JSON",
					data:{
						gh_id:gh_id,
						ctype:ctype,
						fromdate:from_date,
						todate:to_date,
						reqId:reqId
				    },
					success : function(result) {
						console.log(result);
						$("#modalstatus").html("");
						result.data.forEach(option=>{
							console.log(option);
							count++;
							$("#modalstatus").append(
									'<tr>'+
									'<td >'+
									'<option value="">'+option.bock_name +
									'</td>'+
									'<input type="hidden" name="block_id" id="block_id'+count+'" class="form-control col-12" '+
									'value="'+option.block_id+'" autocomplete="off"> '+
									'<td>'+
									'<div class="row"> '+
									'<input type="text" name="rooms" id="rooms'+count+'" value="'+option.noofrooms+'" class="form-control col-12" '+
									'placeholder="" autocomplete="off"> '+
									'</div> '+'</td>'+
									'<td>'+
									'<div class="row"> '+
									'<input type="text" name="bg" id="bedg'+count+'" value="'+option.noofbg+'" class="form-control col-12" '+
									'placeholder="" autocomplete="off"> '+
									'</div> '+'</td>'+
									'<td>'+
									'<div class="row"> '+
									'<input type="text" name="bl" id="bedl'+count+'" value="'+option.noofbl+'" class="form-control col-12" '+
									'placeholder="" autocomplete="off"> '+
									'</div> '+'</td>'+
									'<td>'+
									'<div class="row"> '+
									'<input type="text" name="dg" id="dorg'+count+'" value="'+option.noofdg+'" class="form-control col-12" '+
									'placeholder="" autocomplete="off"> '+
									'</div> '+'</td>'+
									'<td>'+
									'<div class="row"> '+
									'<input type="text" name="dl" id="dorl'+count+'" value="'+option.noofdl+'" class="form-control col-12" '+
									'placeholder="" autocomplete="off"> '+
									'</div> '+'</td>'+
									'<td value="">'+option.status  +'</td>'+'</tr>'			
  							);
						})
					},
					error : function(jqXHR, status, error) {
						swal(error);
					}
				});

			}
				var getroomCategory = function() {
				$.ajax({
				url: "./admin/getroomCategory.do",
				type: "GET",
				contentType: "application/json",
				beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
				},
				success: function(data, textStatus, jqXHR) {
				$('#loading').hide();
				console.log("room data",data.data);
				$("#categ").html("");
				data.data.forEach(option=>{
				$("#categ").append('<option value="'+option.quota_type+'">'+option.quota_desc +'</option>'); // .val(value.CountryId).html(value.CountryName));
				})
				},
				error: function(jqXHR, status, error) {
				swal(error);
				}
				});
				}
				
				var moderationtable = $("#moderationTable").DataTable(
					{
						"paging" : false,
						"searching" : false,
						"scrollX": false,
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
							"targets": 1,
							"data": "single.guestId"
							},
							{
							"targets": 2,
							"data": "single.guestName"
							},
							{
							"targets": 3,
							"data": "purpName"
							},
							{
							"targets": 4,
							"data": "single.guestAge"
							},
							{
							"targets": 5,
							"data": "noOfRooms"
							},
							{
							"targets": 6,
							"data": "noOfBeds"
							},
							{
							"targets": 7,
							"data": "noOfBedsDormitory"
							},
							{
							"targets": 8,
							"data": "responseSupDoc"
							},
							{
							"targets": 9,
							"data": "suppDocName"
							},
							{
							"targets": 10,
							"data": "status"
							}
							],
							});
				
// ***************************
				var guestRoom = $("#guestRoom").DataTable(
						{
							"paging" : false,
							"searching" : false,
							"scrollX": false,
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
										"data" :" block_name",
					                 },
								{
								"targets": 1,
								"data": "0"
								},
								{
								"targets": 2,
								"data": "b_gents"
								},
								{
								"targets": 3,
								"data": "b_ladies"
								},
								{
								"targets": 4,
								"data": "d_gents"
								},
								{
								"targets": 5,
								"data": "d_ladies"
								},
								{
								"targets": 6,
								"data": "room_status"
								},
							
								],
								});
					mod(table)
					function mod(table){
						table.clear();
					}
						
// ************************************
				var getGuestDetailsByReqid = function(id) {
				$.ajax({
				url: "./admin/getGuestDetailsByReqid/" + id + ".do",
				type: "GET",
				contentType: "application/json",
				beforeSend: function(xhr) {
				$('#loading').show();
				xhr.setRequestHeader(header, token);
				},
				success: function(data, textStatus, jqXHR) {
				dataget=data;
				console.log(data);
				console.log(data);
				moderationtable.clear();
				moderationtable.rows.add(data.data).draw();
				setConvdtls(data);
				getroomStatus(data);
				},
				error: function(jqXHR, status, error) {
				swal(error);
				}
				});
				}

				$('#conVnotNeeded').on('change', function() {
				console.log("not allowing conveyce for--");
				$("#conVNeeded").prop("checked", false);
				$("#conVnotNeeded").prop("checked", true);
				$("#fromConvyence").hide();
				$("#toConvyence").hide();
				});

				$('#conVNeeded').on('change', function() {
				console.log("allowing conveyce for--");
				$("#conVNeeded").prop("checked", true);
				$("#conVnotNeeded").prop("checked", false);
				$("#fromConvyence").show();
				$("#toConvyence").show();
				});

				var setConvdtls = function(data) {
				console.log("inside setConvdtls ")
				$("#conVNeeded").prop("checked", false);
				$("#conVnotNeeded").prop("checked", false);
				$("#fromConvyence").html("");
				$("#toConvyence").html("");
				$("#desig").html("");
				if (data.data[0].isArrivalConv) 
				{
			        $("#conVNeeded").prop("checked", true);
				    $("#conVnotNeeded").prop("checked", false);
				    if (data.data[0].travelModeFr != null && data.data[0].flNoFr != null && data.data[0].arrDt != null && data.data[0].arrTm != null && data.data[0].arrPla)
				    {
				          $("#fromConvyence").html(data.data[0].travelModeFr + " : " + data.data[0].flNoFr + " at " + data.data[0].arrDt + " " + data.data[0].arrTm + " in " + data.data[0].arrPla);
				    } else  {
				          $("#fromConvyence").html("no data");
				          }
				     if (data.data[0].travelModeTo != null && data.data[0].flNoTo != null && data.data[0].depDt != null && data.data[0].depTm != null && data.data[0].depPla) 
				     {
				           $("#toConvyence").html(data.data[0].travelModeTo + " : " + data.data[0].flNoTo + " at " + data.data[0].depDt + " " + data.data[0].depTm + " in " + data.data[0].depPla);
				      } else {
				         $("#toConvyence").html("no data");
				      }
				} 
				else {
					    $("#conVNeeded").prop("checked", false);
				        $("#conVnotNeeded").prop("checked", true);
				        console.log("no convence needed for--",data.data[0].bookingId);
				        $("#fromConvyence").html("No conveyance requested.");
				        $("#toConvyence").html("No conveyance requested.");
				        $("#conVNeeded").prop("checked", false);
				        $("#conVnotNeeded").prop("checked", true );
				     }
				if (data.data[0].isDepartueConv) 
				{
			        $("#conVNeeded").prop("checked", true);
				    $("#conVnotNeeded").prop("checked", false);
				    if (data.data[0].travelModeFr != null && data.data[0].flNoFr != null && data.data[0].arrDt != null && data.data[0].arrTm != null && data.data[0].arrPla)
				    {
				          $("#fromConvyence").html(data.data[0].travelModeFr + " : " + data.data[0].flNoFr + " at " + data.data[0].arrDt + " " + data.data[0].arrTm + " in " + data.data[0].arrPla);
				    } else  {
				          $("#fromConvyence").html("no data");
				          }
				     if (data.data[0].travelModeTo != null && data.data[0].flNoTo != null && data.data[0].depDt != null && data.data[0].depTm != null && data.data[0].depPla) 
				     {
				           $("#toConvyence").html(data.data[0].travelModeTo + " : " + data.data[0].flNoTo + " at " + data.data[0].depDt + " " + data.data[0].depTm + " in " + data.data[0].depPla);
				      } else {
				         $("#toConvyence").html("no data");
				      }
				} 
				else {
					    $("#conVNeeded").prop("checked", false);
				        $("#conVnotNeeded").prop("checked", true);
				        console.log("no convence needed for--",data.data[0].bookingId);
				        $("#fromConvyence").html("No conveyance requested.");
				        $("#toConvyence").html("No conveyance requested.");
				        $("#conVNeeded").prop("checked", false);
				        $("#conVnotNeeded").prop("checked", true );
				     }
				$("#desig").append('<option value="'+data.data[0].desg+'">' + data.data[0].desgName + '</option>');
				   }
					
				function objectifyForm(formArray) {// serialize data function

					   var returnArray = {};
					   for (var i = 0; i < formArray.length; i++){
					     returnArray[formArray[i]['name']] = formArray[i]['value'];
					   }
					   return returnArray;
					 }
				
				
				$("#submit").click(function(){
					submitModerationDetails(id1);
					$('#moderationModal').modal('hide');
				})
					
				 var submitModerationDetails = function (id){
				        console.log('form valid1');       
				        var formData = $('#moderationform').serializeArray();
				        console.log(formData);
				        $.ajax({
				            url: "./admin/adminmoderationDetails/add/" + id + ".do",
				            type : "POST",   
				            data: formData,
				            beforeSend: function(xhr) {
				                xhr.setRequestHeader(header, token);
				            },
				            success: function(data, textStatus, jqXHR) {
				            	console.log(data);
				            	 if (data.status) {
				                     swal(data.msg);
				                     getDataToGadRequestTable();
				                 } else {
				                     swal(data.msg);
				                 }
				            },error:function(jqXHR, status, error){
				                swal(error);
				            }
				        });
				    }	
				
				var getDataEditModeration  = function() {
					 var city=$('#city').val();
					 var gHouse = $('#gHouse').val();
					  console.log(city);
					  console.log(gHouse);
					$.ajax({
						url : './admin/allmoderationApplicationList.do',
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
				
				
				$('#editList').on('click', function() {
					$("#applicationList").hide();
					$("#editmoderationDataTable").show();
					$("#editModeratorList").show();
					getDataEditModeration ();
					
				})

				var table1 = $("#editmoderationDataTable")
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
									{"targets":1,"data":"reqId"},
									{"targets":2,"data":"guestName"},
									{"targets":3,"data":"gender"},
									{"targets":4,"data":"fromDate"},
									{"targets":5,"data":"toDate"},
									{"targets":6,"data":"req_date"},		
									{"targets":7,"data": null, 
										render: function (data, type, row, meta) {
											
												return '<button type="button" id="edit" title="Edit" action="Edit" class="btn btn-secondary btn-sm btn-edit"><i class="fa fa-pencil" aria-hidden="true"></i></button> ' 
												+'<button type="button" id="onlyview" action="View" title="View" class="btn btn-secondary btn-sm btn-view"><i class="fa fa-eye" aria-hidden="true"></i></button> '
										}
									}

									],
						});
				
				
				$('#editmoderationDataTable tbody').on('click', '#edit', function () {
					
			        var data1 = table1.row( $(this).closest('tr') ).data();
			        console.log(data1.reqId);
			        id2=data1.reqId;
			        getmoderationDetailsByReqid(data1.reqId); 
			        getroomCategory();
			       $('#submit').hide();
			       $('#update').show();
			    });
				
                $('#editmoderationDataTable tbody').on('click', '#onlyview', function () {
					
			        var data1 = table1.row( $(this).closest('tr') ).data();
			        console.log(data1.reqId);
			        id2=data1.reqId;
			        getmoderationDetailsByReqid(data1.reqId); 
			        getroomCategory();
			       $("#modalButton").hide();
			       // $("#card-body :input").prop("disabled", true);
			        $("#card-body").addClass('disableElement');
			        $("#modalstatus").addClass('disableElement');
			        
			    });
				
				
				var getmoderationDetailsByReqid = function(id) {
					$.ajax({
						      url: "./admin/getmoderattionDetailsByReqid/" + id + ".do",
						      type: "GET",
						      contentType: "application/json",
						      beforeSend: function(xhr) {
						      $('#loading').show();
							  xhr.setRequestHeader(header, token);
					      },
							success: function(data, textStatus, jqXHR) 
							{
								dataget=data;
								console.log(data);
								moderationtable.clear();
								moderationtable.rows.add(data.data).draw();
								setConvdtls(data);
								getroomStatus(data); 
								var value=data.data[0].quotatype;
								var desc=data.data[0].quotadesc;
								console.log(value);
								console.log(desc);
								$("#categ").val("");
								$("#categ").val(value);
								$('#moderationModal').modal('show');
					        },
							error: function(jqXHR, status, error) {
							swal(error);
					        }
					   });
				}
				
				$("#update").click(function(){
					updateModerationDetails(id2);
					console.log(id2);
					$('#moderationModal').modal('hide');
				})
					
				 var updateModerationDetails = function (id){
				        console.log('form valid1');       
				        var formData = $('#moderationform').serializeArray();
				        console.log(formData);
				        $.ajax({
				            url: "./admin/adminmoderationDetails/update/" + id + ".do",
				            type : "POST",   
				            data: formData,
				            beforeSend: function(xhr) {
				                xhr.setRequestHeader(header, token);
				            },
				            success: function(data, textStatus, jqXHR) {
				            	console.log(data);
				            	 if (data.status) {
				                     swal(data.msg);
				                   
				                 } else {
				                     swal(data.msg);
				                 }
				            },error:function(jqXHR, status, error){
				                swal(error);
				            }
				        });
				    }	
				
				
				
				
				
				
				
				
				
				// ********************************
// var getGuestDetailsByReqid = function(id) {
// $.ajax({
// url: "./admin/getUpdateDetailsByReqid/" + id + ".do",
// type: "GET",
// contentType: "application/json",
// beforeSend: function(xhr) {
// $('#loading').show();
// xhr.setRequestHeader(header, token);
// },
// success: function(data, textStatus, jqXHR) {
// $('#loading').hide();
// console.log(data);
// moderationtable.clear();
// moderationtable.rows.add(data.data).draw();
// setConvdtls(data);
// },
// error: function(jqXHR, status, error) {
// swal(error);
// }
// });
// }
//
// $('#conVnotNeeded').on('change', function() {
// console.log("not allowing conveyce for--");
// $("#conVNeeded").prop("checked", false);
// $("#conVnotNeeded").prop("checked", true);
// $("#fromConvyence").hide();
// $("#toConvyence").hide();
// });
//
// $('#conVNeeded').on('change', function() {
// console.log("allowing conveyce for--");
// $("#conVNeeded").prop("checked", true);
// $("#conVnotNeeded").prop("checked", false);
// $("#fromConvyence").show();
// $("#toConvyence").show();
// });
//
// var setConvdtls = function(data) {
// console.log("inside setConvdtls ")
// $("#conVNeeded").prop("checked", false);
// $("#conVnotNeeded").prop("checked", false);
// $("#fromConvyence").html("");
// $("#toConvyence").html("");
// $("#desig").html("");
// if (data.data[0].isArrivalConv) {
// console.log("yes convence needed for--", data.data[0].bookingId);
// $("#conVNeeded").prop("checked", true);
// $("#conVnotNeeded").prop("checked", false);
// if (data.data[0].travelModeFr != null && data.data[0].flNoFr != null &&
// data.data[0].arrDt != null && data.data[0].arrTm != null &&
// data.data[0].arrPla) {
// $("#fromConvyence").html(data.data[0].travelModeFr + " : " +
// data.data[0].flNoFr + " at " + data.data[0].arrDt + " " + data.data[0].arrTm
// + " in " + data.data[0].arrPla);
// }
// else {
// $("#fromConvyence").html("no data");
// }
// if (data.data[0].travelModeTo != null && data.data[0].flNoTo != null &&
// data.data[0].depDt != null && data.data[0].depTm != null &&
// data.data[0].depPla) {
// $("#toConvyence").html(data.data[0].travelModeTo + " : " +
// data.data[0].flNoTo + " at " + data.data[0].depDt + " " + data.data[0].depTm
// + " in " + data.data[0].depPla);
// }
// else {
// $("#toConvyence").html("no data");
// }
// } else {
// console.log("no convence needed for--", data.data[0].bookingId);
// $("#fromConvyence").html("No conveyance requested.");
// $("#toConvyence").html("No conveyance requested.");
// $("#conVNeeded").prop("checked", false);
// $("#conVnotNeeded").prop("checked", true);
// }
// $("#desig").html('<option>' + data.data[0].desgName + '</option>');
// }
//					
// ******************************

				$('#applicationDataTable tbody').on('click', '#cancel', function () {
					var data = table.row($(this).closest('tr')).data() != undefined ? table.row($(this).closest('tr')).data() : table.row($(this)).data();
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
							url: './admin/cancelRequest/'+data.reqId + ".do",
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

