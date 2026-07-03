$('document').ready(
		function() {
			var processId;
			var table = null;
			 $('#filter-popover').on('click', function () {
		         event.stopPropagation();
		     });
		
			$("#dueList").show();
			$('#dueDataTable tbody').on('click', '#payment', function () {
		        var data = table.row( $(this).parents('tr') ).data();
		         $('#makeReceiptModal').modal('show');
		    });
			  $("#filterBookingDate").datetimepicker({
			        useCurrent: false,
			        format: 'DD-MM-YYYY'
			        // format: 'YYYY-MM-DD'
			    });
			 var cityData = $('#cityUserlocssd').val().trim();
			    var cityDetails = JSON.parse(cityData);
			    $("#city").html("");
			    $("#city").append('<option value="">--Select City--</option>');
			    cityDetails.forEach(option => {
			        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
			    });

			let size = Object.keys(cityDetails).length;
			if (size == 1) {
				$('#city').val(cityDetails[0].gh_id);
				setTimeout(function () {
					$('#city').trigger('change');
				}, 1000);
			}

			$('#confirmedList').on('click', function () {
		        /*getAllConfirmList();*/
				table.ajax.reload();
		    });
			
			var table = $("#confirmListDataTable").DataTable({
		        "paging": true,
				"searching": false,
				"scrollX": true,
				"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
				"processing": false,
				"ordering": true,
				"info": false,
				"bFilter": false,
				"autoWidth": true,
				"bLengthChange": false,
				"fixedHeader": {
					header: true,
					footer: true
				},
				"scrollCollapse": true,
				"serverSide": true,
				"ajax": {
					"url": "./admin/getconfirmDraftlist.do",
					"type": "POST",
					"beforeSend": function(xhr) {
						$('#loading').show();
						xhr.setRequestHeader(header, token);
					},
					"complete": function() {
						$('#loading').hide();
					},
					"data": function(d) {
						d.city = $('#city').val();
						d.gHouse = $('#gHouse').val();
					}
				},
		        "columnDefs": [{
		            "targets": 0, "data": null, render: function (data, type, row, meta) {
		                return meta.row + meta.settings._iDisplayStart + 1;
		            }
		        },  {
		            "targets": 1, "data": "cityName"
		        },{
		            "targets": 2, "data": "blockName"
		        },{
		            "targets": 3, "data": "goSign"
		        }, {
		            "targets": 4, "data": "createdBy"
		        }, {
		            "targets": 5, "data": "createdDate"
		        }, {
		            "targets": 6, "data": null, render: function (data, type, row, meta) {
		                return `<input type="hidden" value="${data.processId}"><button type="button" id="view" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> `
		            }
		        }],
		    });
			   table.ajax.reload();
			   /*getAllConfirmList();*/
			   
			   $('#confirmListDataTable tbody').on('click', '#view', function () {
			        var data = table.row($(this).parents('tr')).data();
			        processId = data.processId;
			        console.log(data);
			       
			        getAllDetailsByProcessid(processId, "confirm");
			        $('#viewModal').modal('show');
			        $('#loading').show();
			    });
			   
			    var table2 = null;
			    table2 = $("#viewConfirmListDataTable").DataTable({
			        "paging": true,
			        "bAutoWidth": false,
			        "searching": true,
			        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			        "processing": false,
			        "ordering": true,
			        "info": false,
			        "bFilter": false,
			        "fixedHeader": {
			            header: true, footer: true
			        },
			        "scrollCollapse": true,
			        "columnDefs": [{"width":"5%",
			            "targets": 0, "data": null, render: function (data, type, row, meta) {
			                return meta.row + meta.settings._iDisplayStart + 1;
			            }
			        },{"width":"20%", "targets": 1, "data": "guestName"}, 
			        {"width":"20%", "targets": 2, "data": "periodOfStay"}, 
			        {"width":"15%", "targets": 3, "data": "roomNo"},
			        {"width":"5%","targets": 4, "data": "duringStayConveynce"}, 
			        {"width":"10%","targets": 5, "data": "conveynceFromDatails"},
			        {"width":"10%","targets": 6, "data": "conveynceToDetails"},
			        {"width":"15%","targets": 7, "data": "rent",
			        }],
			    });

			    var table3 = null;
			    table3 = $("#viewCancelListDataTable").DataTable({
			        "paging": true,
			        "searching": true,
			        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			        "processing": false,
			        "ordering": true,
			        "info": false,
			        "bFilter": false,
			        "fixedHeader": {
			            header: true, footer: true
			        },
			        "scrollCollapse": true,
			        "columnDefs": [{
			            "targets": 0, "data": null, render: function (data, type, row, meta) {
			                return meta.row + meta.settings._iDisplayStart + 1;
			            }
			        },{
			            "targets": 1, "data": "name"
			        }, {
			            "targets": 2, "data": "periodOfStay"
			        }, {
			            "targets": 3, "data": "roomNo"
			        }, {
			            "targets": 4, "data": "modeOfCancel"
			        }],
			    });
			    
			    var table5 = $("#viewModifiedListDataTable").DataTable({
			        "paging": true,
			        "searching": true,
			        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			        "processing": false,
			        "ordering": true,
			        "info": false,
			        "bFilter": false,
			        "fixedHeader": {
			            header: true, footer: true
			        },
			        "scrollCollapse": true,
			        "columnDefs": [{
			            "targets": 0, "data": null, render: function (data, type, row, meta) {
			                return meta.row + meta.settings._iDisplayStart + 1;
			            }
			        },{
			            "targets": 1, "data": "name"
			        }, {
			            "targets": 2, "data": "periodOfStay"
			        }, {
			            "targets": 3, "data": "prevOrderNo"
			        },
			        {
			            "targets": 4, "data": "prevOrderDate"
			        },
			        {
			            "targets": 5, "data": "reason"
			        }, {
			            "targets": 6, "data": "modifiedRoomNo",
			        },{
			            "targets": 7, "data": "modifiedRoomRent",
			        },{
			            "targets": 8, "data": "roomNo",
			        },{
			            "targets": 9, "data": "roomRent",
			        }],
			    });
			    var conTable = $("#viewConveyanceList").DataTable({
			        "paging": true,
			        "searching": true,
			        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
			        "processing": false,
			        "ordering": true,
			        "info": false,
			        "bFilter": false,
			        "fixedHeader": {
			            header: true, footer: true
			        },
			        "scrollCollapse": true,
			        "columnDefs": [{
			            "targets": 0, "data": null, render: function (data, type, row, meta) {
			                return meta.row + meta.settings._iDisplayStart + 1;
			            }
			        },{
			            "targets": 1, "data": "guestName"
			        }, 
			        {
			            "targets": 2, "data": "duringStayConveynce"
			        },{
			            "targets": 3, "data": "conveynceFromDatails"
			        }, {
			            "targets":4, "data": "conveynceToDetails"
			        }],
			    });
			    var conferenceTbl = $("#viewConferenceListTable").DataTable({
			        "paging": true,
			        "searching": true,
			        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
			        "processing": false,
			        "ordering": true,
			        "info": false,
			        "bFilter": false,
			        "fixedHeader": {
			            header: true, footer: true
			        },
			        "scrollCollapse": true,
			        "columnDefs": [{
			            "targets": 0, "data": null, render: function (data, type, row, meta) {
			                return meta.row + meta.settings._iDisplayStart + 1;
			            }
			        }, {
			            "targets": 1, "data": "name"
			        }, {
			            "targets": 2, "data": "fromDate"
			        }, {
			            "targets": 3, "data": "toDate"
			        }, {
			            "targets": 4, "data": "confUseDesc",
			        },{
			            "targets": 5, "data": "confCatDesc",
			        },{
			            "targets": 6, "data": "capacity",
			        },{
			        	"targets":7,"data":"totalAmt"
			        }],
			    });
			 var getAllDetailsByProcessid = function (id, type) {
				 
			        $.ajax({
			            url: "./admin/getAllDetailsByProcessidforOfficeOrder/" + id + "/" + type +".do",
			            type: "GET",       
			            success: function (data) {
			                console.log(data);
			                $('#loading').hide();
			                var countForConfirm = data.data.guestDetails.length;
			                var countForCancel = data.data.canList.length;
			                var countForMod = data.data.modList.length;
			                var countForConveyance = data.data.conveyanceList.length;
			                var countForConference = data.data.conferenceList.length;
			                if ( countForConfirm == 0 ) {
						    	$('#confirm_list_div').hide();
						    }else{
						    	$('#confirm_list_div').show();
						    	table2.clear();
				                table2.rows.add(data.data.guestDetails).draw();
						    }
						    if ( countForCancel == 0 ) {
						    	$('#cancel_list_div').hide();
						    }else{
						    	$('#cancel_list_div').show();
						    	table3.clear();
				                table3.rows.add(data.data.canList).draw();
						    }
						    if ( countForMod == 0 ) {
						    	$('#modify_list_div').hide();
						    }else{
						    	$('#modify_list_div').show();
						    	 table5.clear();
					             table5.rows.add(data.data.modList).draw();
						    }
						    if ( countForConveyance == 0 ) {
						    	$('#conveyance_list_div').hide();
						    }else{
						    	$('#conveyance_list_div').show();
						    	conTable.clear();
						    	conTable.rows.add(data.data.conveyanceList).draw();
						    }
						    if ( countForConference === 0 ) {
						    	$('#conferenceListDiv').hide();
						    }else{
						    	$('#conferenceListDiv').show();
						    	conferenceTbl.clear();
						    	conferenceTbl.rows.add(data.data.conferenceList).draw();
						    }
			            },
			            error: function (jqXHR, status, error) {
		                if (jqXHR.status == 400) {
		                    swal(jqXHR.responseJSON.msg);
		                } else if (jqXHR.status == 404) {
		                    swal('Requested page not found. [404]');
		                }
		                console.log(jqXHR);
		                /*swal(jqXHR.responseText);*/
		                $('#loading').hide();
           				 }
			        });
			    }

			$('#city').on('change', function() {
				var id = $('#city').val();
				getGuestHouseListDtls(id);
				/*getAllConfirmList();*/
				table.ajax.reload();
			});
			
			$('#gHouse').on('change', function() {
				/*getAllConfirmList();*/
				table.ajax.reload();
			});

			var getGuestHouseListDtls = function (id) {
				blockId = id;
				//$('#loading').show();
		        $.ajax({
		            url: "./admin/getGuestHouseListDetails/" + id + ".do",
		            type: "GET",
		            contentType: "application/json",
		            beforeSend: function () {
		            	$('#loading').show();
		            },
		            success: function (data, textStatus, jqXHR) {
		                console.log(data);
		                $('#loading').hide();
		                $("#gHouse").html("");
		                $("#gHouse").append('<option value="">---Select---</option>');
		                data.forEach(option => {
		                    $("#gHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
		                })
		                let size = Object.keys(data).length;
		                if (size == 1) {
		                    $('#gHouse').val(data[0].id);
		                }
		                $('#gHouse').trigger('change');
		            }, error: function (jqXHR, status, error) {
			                if (jqXHR.status == 400) {
			                    swal(jqXHR.responseJSON.msg);
			                } else if (jqXHR.status == 404) {
			                    swal('Requested page not found. [404]');
			                }
			                console.log(jqXHR);
			                /*swal(jqXHR.responseText);*/
			                $('#loading').hide();
			            }
		        });
		    }
			
			
			$('#allOfficeOrderList').click(function(){
				let text = $('#copyToText').val();
				// replace & with %26
				text = text.replace(/&/g, "%26");
				console.log(text);
				let type = "print";
				console.log(processId+" and "+type);
					window.open('./admin/downloadOfficeOrderListInPDF/'+processId+ '/' + type +'.html?msg=' + text,'_blank');
			});

			$('#allOfficeOrderListInWord').click(function(){
				let text = $('#copyToText').val();
				// replace & with %26
				text = text.replace(/&/g, "%26");
				let type = "print";
				window.location.href = './admin/generateOfficeOrderWord/'+processId+ '/' + type +'.html?msg=' + text;
			});		
		});