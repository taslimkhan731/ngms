$(document).ready(function(){

	//$( "#district_name" ).selectmenu();

	//ation_point_bar

	
	
	var formatter = new Intl.NumberFormat('en-IN', {
		currency: 'INR',
		minimumFractionDigits: 0,
	})
	
	surveyCategory(null, null);

	districtButtonClicked(null,null);
	blockButtonClicked(null,null);
	clusterButtonClicked(null,null)

	ticketAssigned(null, null);
	ticketsClosed(null, null);
	ticketsClosedWithinDueDate(null, null);
	closed7DaysFromDueDate(null, null);
	closedBtn7_14(null, null);
	closedMoreThan14(null, null);
	avgTime(null, null);
	ticketsClosedByDistrictLevelOfficer(null, null);
	ticketsClosedByBlockLevelOfficer(null, null);
	ticketsClosedByClusterLevelOfficer(null, null);

	actionPointBar(null, null, null, null, null, "dist_name", 'ACTION POINT STATUS DISTRICT WISE', 'ation_point_bar', "District");

	console.log('details of action point');
	console.log($('#userId').val());
	getDistrictName();
	
	//Modal
	actionPointClosedModal(null, null, 'actionPointClosedModal');
	actionPointClosedWithInDueDateModal(null, null, 'actionPointClosedWithInDueDateModal');
	actionPointClosed7Modal(null, null, 'actionPointClosed7Modal');
	actionPointClosed7_14Modal(null, null, 'actionPointClosed7_14Modal');
	actionPointClosed14pModal(null, null, 'actionPointClosed14pModal');
	
	/*getSchoolMonitoredBarUjjwalSC(null, null, null, "District Wise Schools Monitored and Not Monitored", "cov_n_cov", "District");
	schoolCoveredByDistrictOfficer(null, null);
	schoolCoveredByBlockOfficer(null, null);
	schoolCoveredByClusterOfficer(null, null);
	districtButtonClicked(null,null);
	blockButtonClicked(null,null);
	clusterButtonClicked(null,null);*/

	$("#dist").hide();
	$("#block").hide();
	$("#cluster").hide();

	//var date=new Date($.now());
	
	$("#search_date").on("click", function(){
		//swal("clicked");
		//date_to
		//date_from
		var to=$("#date_to").val();
		
		var from=$("#date_from").val();
		alert(to);
		alert(from);
		if (to==""&&from=="") {
			swal("Error", "please choose start and end date", "error");
		} /*else {
			var date=new Date($.now()).toLocaleDateString().split("/");
			console.log(date);
			var newDate=date[2]+"-"+date[1]+"-"+date[0];
			alert(newDate);
		}*/
		
	})
	
	
	function ticketAssigned(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/ticketAssigned.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========ticketAssigned==============");
				console.log(result);
				console.log("==========ticketAssigned==============");
				$("#tickets_assigned").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function ticketsClosed(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/ticketsClosed.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========ticketsClosed==============");
				console.log(result);
				console.log("==========ticketsClosed==============");
				$("#tickets_closed").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function ticketsClosedWithinDueDate(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/ticketsClosedWithinDueDate.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========ticketsClosedWithinDueDate==============");
				console.log(result);
				console.log("==========ticketsClosedWithinDueDate==============");
				$("#tickets_closed_within_due_date").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function closed7DaysFromDueDate(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/closed7DaysFromDueDate.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========closed7DaysFromDueDate==============");
				console.log(result);
				console.log("==========closed7DaysFromDueDate==============");
				$("#less_than_7days").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function closedBtn7_14(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/closedBtn7_14.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========closedBtn7_14==============");
				console.log(result);
				console.log("==========closedBtn7_14==============");
				$("#7_to_14days").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function closedMoreThan14(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/closedMoreThan14.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========closedMoreThan14==============");
				console.log(result);
				console.log("==========closedMoreThan14==============");
				$("#14_p_days").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function avgTime(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/avgTime.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log("==========avgTime==============");
				console.log(result);
				console.log("==========avgTime==============");
				var time=result.data[0] ;
				if(time==null){
					time=0;
				}
				$("#avg_time").text(time +'days');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function districtButtonClicked(type,name){
		//$("#dist" ).empty();
		$("#district_button").on("click", function(){
			console.log("======from dist button click======");
			console.log(type,name);
			console.log();
			districtTableSC(type,name);
			$("#dist").show();
			$("#block").hide();
			$("#cluster").hide();
			console.log("======from dist button click======");
		})
	}

	function blockButtonClicked(type,name){
		//$("#block" ).empty();
		$("#block_button").on("click", function(){
			console.log("======from block button click======");
			console.log(type,name);
			console.log();
			blockTableSC(type,name);
			$("#block").show();
			$("#dist").hide();
			$("#cluster").hide();
			console.log("======from block button click======");
		})
	}

	function clusterButtonClicked(type,name){
		//$("#block" ).empty();
		$("#cluster_button").on("click", function(){
			console.log("======from cluster button click======");
			console.log(type,name);
			console.log();
			clusterTableSC(type,name);
			$("#cluster").show();
			$("#dist").hide();
			$("#block").hide();
			console.log("======from cluster button click======");
		})
	}

	function clusterTableSC(name,type){
		$.ajax({
			url:'./detailsOfActionPoint/detailsOfClusterOfficer.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				var data=result.data;
				console.log("==========clusterTableSC==============");
				console.log(result.data);
				console.log("==========clusterTableSC==============");
				if(result.success){
					if (name==null&&type==null) {
						name="";
						type="";
					}
					if (type=='dist_name') {
						type='District';
					}else if (type=='block_name') {
						type='Block';
					}else if (type=='cluster_name') {
						type='Cluster';
					}
					var table=$('#cluster_table').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
							
							{
				            	text:'<i class="glyphicon glyphicon-remove-circle" style="height:1%; width:72%; font-size:3em; color:grey"></i>',
				            	titleAttr: 'Close',
				            	//className:"BUTTON_DPX allign",
				            	action: function ( e, dt, node, config ) {
				            		//alert( 'Button activated' );
				                $('#cluster').hide();
			                },	
				            },
							
							{
								extend: 'excelHtml5',
								className:"BUTTON_DPX",
								title: 'School Covered By Cluster Officer'+' '+name+' '+type
							},
							{
								extend: 'print',
								className:"BUTTON_DPX",
								title: 'School Covered By Cluster Officer'+' '+name+' '+type
							},
							//'print'/*, 'excel'*/
							],
							//"searching": false,
							//"scrollY": true,
							//"scrollY":"200px",
							"scrollCollapse": true,
							//"searching": false,
							//"sorting":false,
							"paging":true,
							"destroy": true,
							columns: [
								{ "data": "officer_designation" },
								{ "data": "dist_name" },
								{ "data": "block_name" },
								{ "data": "cluster_name" },
								{ "data": "action_point_assigned" },
								{ "data": "action_point_resolved" },
								{ "data": "<7" },
								{ "data": "7-14" },
								{ "data": "14+" },
								{ "data": "officer_moblieno" },
								{ "data": "officer_email" }
								],
								"columnDefs": [
									{
										"targets": [ 9, 10 ],
										"visible": false
									}
									],
									/*"createdRow": function( row, data, dataIndex ) {
								if ( data.percentage<30 ) {
									$(row).find('td:eq(5)').css( "background-color", "Red" );
								}else if (data.percentage>30 && data.percentage<=60) {
									$(row).find('td:eq(5)').css( "background-color", "Orange" );
								}else if (data.percentage>60 && data.percentage<=80) {
									$(row).find('td:eq(5)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.percentage> 80) {
									$(row).find('td:eq(5)').css( "background-color", "rgb(0, 255, 0)" );
								}
							}*/
					} );
					
					table.fnAdjustColumnSizing();
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function blockTableSC(name,type){
		$.ajax({
			url:'./detailsOfActionPoint/detailsOfBlockOfficer.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				var data=result.data;
				console.log("==========blockTableSC==============");
				console.log(result.data);
				console.log("==========blockTableSC==============");
				if(result.success){
					if (name==null&&type==null) {
						name="";
						type="";
					}
					if (type=='dist_name') {
						type='District';
					}else if (type=='block_name') {
						type='Block';
					}else if (type=='cluster_name') {
						type='Cluster';
					}
					var table=$('#block_table').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
							
							{
				            	text:'<i class="glyphicon glyphicon-remove-circle" style="height:1%; width:72%; font-size:3em; color:grey"></i>',
				            	titleAttr: 'Close',
				            	//className:"BUTTON_DPX allign",
				            	action: function ( e, dt, node, config ) {
				            		//alert( 'Button activated' );
				                $('#block').hide();
			                },	
				            },
							
							{
								extend: 'excelHtml5',
								className:"BUTTON_DPX",
								title: 'School Covered By Block Officer'+' '+name+' '+type
							},
							{
								extend: 'print',
								className:"BUTTON_DPX",
								title: 'School Covered By Block Officer'+' '+name+' '+type
							},
							//'print'/*, 'excel'*/
							],
							//"searching": false,
							//"scrollY": true,
							//"scrollY":"200px",
							"scrollCollapse": true,
							//"searching": false,
							//"sorting":false,
							"paging":true,
							"destroy": true,
							columns: [
								{ "data": "officer_designation" },
								{ "data": "dist_name" },
								{ "data": "block_name" },
								{ "data": "action_point_assigned" },
								{ "data": "action_point_resolved" },
								{ "data": "<7" },
								{ "data": "7-14" },
								{ "data": "14+" }
								],
								/*"columnDefs": [
					            {
					                "targets": [ 5 ],
					                "visible": false
					            }
					        	],*/
								/* "createdRow": function( row, data, dataIndex ) {
								if ( data.percentage<30 ) {
									$(row).find('td:eq(4)').css( "background-color", "Red" );
								}else if (data.percentage>30 && data.percentage<=60) {
									$(row).find('td:eq(4)').css( "background-color", "Orange" );
								}else if (data.percentage>60 && data.percentage<=80) {
									$(row).find('td:eq(4)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.percentage> 80) {
									$(row).find('td:eq(4)').css( "background-color", "rgb(0, 255, 0)" );
								}
							}*/
					} );
					table.fnAdjustColumnSizing();
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	setTimeout(function () {
	     $($.fn.dataTable.tables( true ) ).DataTable().columns.adjust().draw();
	},200);
	
	function districtTableSC(name,type){
		$.ajax({
			url:'./detailsOfActionPoint/detailsOfDistrictOfficer.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				var data=result.data;
				console.log("==========districtTableSC==============");
				console.log(result.data);
				console.log("==========districtTableSC==============");
				if(result.success){
					if (name==null&&type==null) {
						name="";
						type="";
					}
					if (type=='dist_name') {
						type='District';
					}else if (type=='block_name') {
						type='Block';
					}else if (type=='cluster_name') {
						type='Cluster';
					}
					var table=$('#district_table').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
							
							{
				            	text:'<i class="glyphicon glyphicon-remove-circle" style="height:1%; width:72%; font-size:3em; color:grey"></i>',
				            	titleAttr: 'Close',
				            	//className:"BUTTON_DPX allign",
				            	action: function ( e, dt, node, config ) {
				            		//alert( 'Button activated' );
				                $('#dist').hide();
			                },	
				            },
							
							{
								extend: 'excelHtml5',
								className:"BUTTON_DPX",
								title: 'School Covered By district Officer'+' '+name+' '+type
							},
							{
								extend: 'print',
								className:"BUTTON_DPX",
								title: 'School Covered By district Officer'+' '+name+' '+type
							},
							//'print'/*, 'excel'*/
							],
							//"searching": false,
							//"scrollY": true,
							//"scrollY":"200px",
							"scrollCollapse": true,
							//"searching": false,
							//"sorting":false,
							"paging":true,
							"destroy": true,
							"fixedHeader": true,
							columns: [
								{ "data": "officer_designation" },
								{ "data": "dist_name" },
								{ "data": "action_point_assigned" },
								{ "data": "action_point_resolved" },
								{ "data": "<7" },
								{ "data": "7-14" },
								{ "data": "14+" }
								],
								/*"columnDefs": [
					            {
					                "targets": [ 4 ],
					                "visible": false
					            }
					        ],*/
								/*"createdRow": function( row, data, dataIndex ) {
								if ( data.percentage<30 ) {
									$(row).find('td:eq(3)').css( "background-color", "Red" );
								}else if (data.percentage>30 && data.percentage<=60) {
									$(row).find('td:eq(3)').css( "background-color", "Orange" );
								}else if (data.percentage>60 && data.percentage<=80) {
									$(row).find('td:eq(3)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.percentage> 80) {
									$(row).find('td:eq(3)').css( "background-color", "rgb(0, 255, 0)" );
								}
							}*/
					} );
					table.fnAdjustColumnSizing();
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function ticketsClosedByDistrictLevelOfficer(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/ticketsClosedByDistrictLevelOfficer.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console
				if(result.success)
					var value=result.data[0];
				$("#fillgaugeDistrict" ).empty();
				liquidfillgaugeDraw("fillgaugeDistrict",value,"%",100);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function ticketsClosedByBlockLevelOfficer(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/ticketsClosedByBlockLevelOfficer.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				if(result.success)
					var value=result.data[0];
				$("#fillgaugeBLock" ).empty();
				liquidfillgaugeDraw("fillgaugeBLock",value,"%",100);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function ticketsClosedByClusterLevelOfficer(name, type){
		$.ajax({
			url:'./detailsOfActionPoint/ticketsClosedByClusterLevelOfficer.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				if(result.success)
					var value=result.data[0];
				$("#fillgaugeCluster" ).empty();
				liquidfillgaugeDraw("fillgaugeCluster",value,"%",100);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}


	//actionPointBar( null, null, null, name1, type1, category1);
	function actionPointBar(name, type, category, name1, type1, category1, caption, area, x){
		/*console.log("===================names1====================");
		console.log("Name1=        "+name1);
		console.log("type1=        "+type1);
		console.log("Name=        "+name);
		console.log("type=        "+type);
		console.log("category1=        "+category1);
		console.log("category=        "+category);
		console.log("===================names1====================");*/
		$.ajax({
			url:'./detailsOfActionPoint/actionPointBar.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type, category:category, name1:name1, type1:type1, category1:category1},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result.data);
				var label=[];
				var closed=[];
				var notclosed=[];
				var toolclosed=[];
				var toolnotclosed=[];
				var i=0;
				console.log("==========action point bar===========");
				console.log(result);
				console.log(result.data);
				if (result.success) {
					$.each(result.data, function() {
						label.push({
							label:result.data[i].label+" \n"+"("+result.data[i].tickets_assigned+")"
						});
						closed.push({
							value:result.data[i].resolved,
							tooltext: "Action Points Resolved :"+result.data[i].tickets_closed
						});
						notclosed.push({
							value:result.data[i].not_resolved,
							tooltext: "Action Points Open :"+result.data[i].tickets_open
						});
						i++;
					});
					console.log("===resolved===");
					console.log(label);
					console.log(notclosed);
					console.log("===resolved===");
					drawStacked(label, closed, notclosed, caption, area, x);
				}
				console.log("==========action point bar===========");
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}


	function getDistrictName(){
		$.ajax({
			url:'./dashboard/getDistrictName.do',
			type: 'POST',
			async: false,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){

				console.log(result);
				setDataToDropDown(result.data, '#district_name');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getBlockName(distName){
		$.ajax({
			url:'./dashboard/getBlockName.do',
			type: 'POST',
			async: false,
			data:{value:distName},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				setDataToDropDownBlock(result.data, '#block_name', distName);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getClusterName(blockName,data){
		$.ajax({
			url:'./dashboard/getClusterName.do',
			type: 'POST',
			async: false,
			data:{distName:data,blockName:blockName},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				setDataToDropDownCluster(result.data, '#cluster_name', blockName);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function setDataToDropDown(data, id){
		//console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i]+ '">' + data[i]+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			var distName = $(this).find(":selected").val();
			var category=$("#survey_category").val();
			$("#dist").hide();
			$("#block").hide();
			$("#cluster").hide();
			if (distName=='ALL') {
				$('#block_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				districtButtonClicked(null,null);
				blockButtonClicked(null,null);
				clusterButtonClicked(null,null);

				ticketAssigned(null, null);
				ticketsClosed(null, null);
				ticketsClosedWithinDueDate(null, null);
				closed7DaysFromDueDate(null, null);
				closedBtn7_14(null, null);
				closedMoreThan14(null, null);
				avgTime(null, null);
				ticketsClosedByDistrictLevelOfficer(null, null);
				ticketsClosedByBlockLevelOfficer(null, null);
				ticketsClosedByClusterLevelOfficer(null, null);
				
				actionPointBar(null, null, null, null, null, "dist_name", 'ACTION POINT STATUS DISTRICT WISE', 'ation_point_bar', "DISTRICT");

				//actionPointBar(null, null, null, 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "District");
			} else {
				districtButtonClicked(distName,'dist_name');
				blockButtonClicked(distName,'dist_name');
				clusterButtonClicked(distName,'dist_name');

				getBlockName(distName);
				ticketAssigned(distName,'dist_name');
				ticketsClosed(distName,'dist_name');
				ticketsClosedWithinDueDate(distName,'dist_name');
				closed7DaysFromDueDate(distName,'dist_name');
				closedBtn7_14(distName,'dist_name');
				closedMoreThan14(distName,'dist_name');
				avgTime(distName,'dist_name');
				ticketsClosedByDistrictLevelOfficer(distName,'dist_name');
				ticketsClosedByBlockLevelOfficer(distName,'dist_name');
				ticketsClosedByClusterLevelOfficer(distName,'dist_name');

				actionPointBar(distName, "dist_name", category, null, null, "block_name", 'ACTION POINT STATUS BLOCK WISE', 'ation_point_bar', "BLOCK");
				
				//actionPointBar(distName, "dist_name", "block_name", 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "Block");
			}
		})
	}

	function setDataToDropDownBlock(data, id, distName){
		//console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i]+ '">' + data[i]+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			var blockName = $(this).find(":selected").val();
			var category=$("#survey_category").val();
			$("#dist").hide();
			$("#block").hide();
			$("#cluster").hide();
			if (blockName=='ALL') {
				$('#cluster_name').children('option:not(:first)').remove();


				districtButtonClicked(distName,'dist_name');
				blockButtonClicked(distName,'dist_name');
				clusterButtonClicked(distName,'dist_name');


				ticketAssigned(distName,'dist_name');
				ticketsClosed(distName,'dist_name');
				ticketsClosedWithinDueDate(distName,'dist_name');
				closed7DaysFromDueDate(distName,'dist_name');
				closedBtn7_14(distName,'dist_name');
				closedMoreThan14(distName,'dist_name');
				avgTime(distName,'dist_name');
				ticketsClosedByDistrictLevelOfficer(distName,'dist_name');
				ticketsClosedByBlockLevelOfficer(distName,'dist_name');
				ticketsClosedByClusterLevelOfficer(distName,'dist_name');

				actionPointBar(distName, "dist_name", category, null, null, "block_name", 'ACTION POINT STATUS BLOCK WISE', 'ation_point_bar', "BLOCK");
				
				//actionPointBar(distName, "dist_name", "block_name", 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "Block");
			} else {
				districtButtonClicked(blockName,'block_name');
				blockButtonClicked(blockName,'block_name');
				clusterButtonClicked(blockName,'block_name');


				getClusterName(blockName,distName);
				ticketAssigned(blockName,'block_name');
				ticketsClosed(blockName,'block_name');
				ticketsClosedWithinDueDate(blockName,'block_name');
				closed7DaysFromDueDate(blockName,'block_name');
				closedBtn7_14(blockName,'block_name');
				closedMoreThan14(blockName,'block_name');
				avgTime(blockName,'block_name');
				ticketsClosedByDistrictLevelOfficer(blockName,'block_name');
				ticketsClosedByBlockLevelOfficer(blockName,'block_name');
				ticketsClosedByClusterLevelOfficer(blockName,'block_name');

				actionPointBar(blockName,'block_name', category, null, null, "cluster_name", 'ACTION POINT STATUS CLUSTER WISE', 'ation_point_bar', "CLUSTER");
				
				//actionPointBar(blockName,'block_name', "cluster_name", 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "Cluster");
			}
		})
	}

	function setDataToDropDownCluster(data, id, blockName){
		console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i]+ '">' + data[i]+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			var clusterName = $(this).find(":selected").val();
			var category=$("#survey_category").val();
			$("#dist").hide();
			$("#block").hide();
			$("#cluster").hide();
			if (clusterName=='ALL') {



				districtButtonClicked(blockName,'block_name');
				blockButtonClicked(blockName,'block_name');
				clusterButtonClicked(blockName,'block_name');



				ticketAssigned(blockName,'block_name');
				ticketsClosed(blockName,'block_name');
				ticketsClosedWithinDueDate(blockName,'block_name');
				closed7DaysFromDueDate(blockName,'block_name');
				closedBtn7_14(blockName,'block_name');
				closedMoreThan14(blockName,'block_name');
				avgTime(blockName,'block_name');
				ticketsClosedByDistrictLevelOfficer(blockName,'block_name');
				ticketsClosedByBlockLevelOfficer(blockName,'block_name');
				ticketsClosedByClusterLevelOfficer(blockName,'block_name');

				actionPointBar(blockName,'block_name', category, null, null, "cluster_name", 'ACTION POINT STATUS CLUSTER WISE', 'ation_point_bar', "CLUSTER");
				
				//actionPointBar(blockName,'block_name', "cluster_name", 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "Cluster");
			} else {


				districtButtonClicked(clusterName, 'cluster_name');
				blockButtonClicked(clusterName, 'cluster_name');
				clusterButtonClicked(clusterName, 'cluster_name');



				ticketAssigned(clusterName, 'cluster_name');
				ticketsClosed(clusterName, 'cluster_name');
				ticketsClosedWithinDueDate(clusterName, 'cluster_name');
				closed7DaysFromDueDate(clusterName, 'cluster_name');
				closedBtn7_14(clusterName, 'cluster_name');
				closedMoreThan14(clusterName, 'cluster_name');
				avgTime(clusterName, 'cluster_name');
				ticketsClosedByDistrictLevelOfficer(clusterName, 'cluster_name');
				ticketsClosedByBlockLevelOfficer(clusterName, 'cluster_name');
				ticketsClosedByClusterLevelOfficer(clusterName, 'cluster_name');

				actionPointBar(clusterName, 'cluster_name', category, null, null, "school_name", 'ACTION POINT STATUS SCHOOL WISE', 'ation_point_bar', "SCHOOL");
				
				//actionPointBar(clusterName, 'cluster_name', "school_name", 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "School");
			}
		})
	}

	function surveyCategory(type, name){
		$.ajax({
			url:'./dashboard/surveyCategory.do',
			type: 'POST',
			async: false,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				setDataToSurveyCategory(result.data, type, name);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function setDataToSurveyCategory(data, type, name){
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i]+ '">' + data[i]+ '</option>';
		}
		$('#survey_category').html("");
		$('#survey_category').append(option);
		$('#survey_category').off().on('change', function(){
			var category = $(this).find(":selected").val();
			var category1='dist_name';
			var distName=$("#district_name").val();
			var blockName=$("#block_name").val();
			var clusterName=$("#cluster_name").val();
			var name1=null;
			var type1=null;
			var tableName="public.fact_questionagg";
			if (clusterName==null||clusterName=='ALL') {
				console.log("in cluster if");
				if (blockName==null||blockName=='ALL') {
					console.log("in block if");
					if (distName==null||distName=='ALL') {
						console.log("in dist if");
					} else {
						name1=distName;
						type1="dist_name";
						tableName="public.fact_questiondistagg";
					}
				} else {
					name1=blockName;
					type1="block_name";
					tableName="public.fact_questionblockagg";
				}
			} else {
				name1=clusterName;
				type1="cluster_name";
				tableName="public.fact_questionclusteragg";
			}
			var caption='DISTRICT';
			if (type1=='dist_name') {
				caption='BLOCK';
				category1='block_name';
			} else if (type1=='block_name') {
				caption='CLUSTER';
				category1='cluster_name';
			} else if (type1=='cluster_name') {
				caption='SCHOOL';
				category1='school_name';
			}{

			}{

			}
			/*console.log("===================names====================");
			console.log("Name1=        "+name1);
			console.log("type1=        "+type1);
			console.log("Name=        "+name);
			console.log("type=        "+type);
			console.log("category1=        "+category1);
			console.log("category=        "+category);
			console.log("===================names====================");*/
			if (category=="ALL") {
				actionPointBar( null, null, null, name1, type1, category1, 'ACTION POINT STATUS '+caption+ ' WISE', 'ation_point_bar',  caption);
			} else {
				actionPointBar( null, null, category, name1, type1, category1, 'ACTION POINT STATUS '+caption+ ' WISE', 'ation_point_bar',  caption);
			}
		})
	}
	
	//actionPointBar(null, null, null, 'ACTION POINT STATUS DISTRIT WISE', 'ation_point_bar', "District");
	//function actionPointBar(name, type, category, caption, area, x)

	function drawStacked(label, closed, notclosed, caption, area, x){
		FusionCharts.ready(function(){
			var chartScrollColumn = new FusionCharts({
				type: 'scrollstackedcolumn2d',
				dataFormat: 'json',
				renderAt: area,
				width: '100%',
				height: '350',
				dataSource: {
					"chart": {
//						"exportEnabled": "1",
//						"exportFileName": caption,
						"caption": caption,
						"captionFontSize": "14",
						"subcaptionFontSize": "14",
						"subcaptionFontBold": "0",
						"xaxisname": x,
						"yaxisname": "Survey Status",
						"yAxisMaxValue": "100",
						"showvalues": "1",
						"showlabel": "1",
						"valueFontColor":"#ffffff",
						"numberSuffix": "%",
						"legendBgAlpha": "0",
						"legendBorderAlpha": "0",
						"legendShadow": "0",
						"showborder": "0",
						"bgcolor": "#ffffff",
						"showalternatehgridcolor": "0",
						"showplotborder": "0",
						"showcanvasborder": "0",
						"legendshadow": "0",
						"plotgradientcolor": "",
						"showCanvasBorder": "0",
						"showAxisLines": "1",
						"showAlternateHGridColor": "0",
						"divlineAlpha": "100",
						"divlineThickness": "1",
						"divLineIsDashed": "1",
						"divLineDashLen": "1",
						"divLineGapLen": "1",
						"lineThickness": "3",
						"flatScrollBars": "1",
						"scrollheight": "10",
						"numVisiblePlot": "15",
						"showHoverEffect":"1"
					},
					"categories": [
						{
							"category": label
						}
						],
						"dataset": [
							{
								"seriesname": "Resolved",
								"color": "26b262",
								"data": closed
							},{
								"seriesname": "Open",
								"color": "ff3333",
								"data": notclosed
							}
							]
				}
			}).render(area);
		});
	}
	
	//Modal
	function actionPointClosedModal(name, type, url){		
		var title=$("#apClosed_title").text();
		$("#apClosed_modal").on("click", function(){
			actionPointClosedModalTable(name, type, url,title);
			$('#myModal').modal('toggle');
			$('#modal_heading').text(title);
		})
	}
	
	function actionPointClosedWithInDueDateModal(name, type, url){		
		var title=$("#actionPointClosedWithInDueDateModal_title").text();
		$("#actionPointClosedWithInDueDate_Modal").on("click", function(){
			actionPointClosedModalTable(name, type, url,title);
			$('#myModal').modal('toggle');
			$('#modal_heading').text(title);
		})
	}
	
	function actionPointClosed7Modal(name, type, url){
		var title=$("#actionPointClosed7_Modal_title").text();
		$("#actionPointClosed7_Modal").on("click", function(){
			actionPointClosedModalTable(name, type, url,title);
			$('#myModal').modal('toggle');
			$('#modal_heading').text(title);
		})
	}
	
	function actionPointClosed7_14Modal(name, type, url){
		var title=$("#actionPointClosed7_14Modal_title").text();
		$("#actionPointClosed7_14Modal").on("click", function(){
			actionPointClosedModalTable(name, type, url,title);
			$('#myModal').modal('toggle');
			$('#modal_heading').text(title);
		})
	}
	
	function actionPointClosed14pModal(name, type, url){
		var title=$("#actionPointClosed14pModal_title").text();
		$("#actionPointClosed14p_Modal").on("click", function(){
			actionPointClosedModalTable(name, type, url,title);
			$('#myModal').modal('toggle');
			$('#modal_heading').text(title);
		})
	}
	
	function actionPointClosedModalTable(name, type, url, title){
		$.ajax({
			url:'./modal/'+url+'.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				var data=result.data;
				console.log("==========actionPointClosedModal==============");
				console.log(result.data);
				console.log("==========actionPointClosedModal==============");
				if(result.success){
					var table=$('#apclosed_modal_table').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [							
							{
								extend: 'excelHtml5',
								className:"BUTTON_DPX",
								title: title
							},
							{
								extend: 'print',
								className:"BUTTON_DPX",
								title: title
							},
							],
							"scrollCollapse": true,
							"paging":true,
							"destroy": true,
							columns: [
								{ "data": "location_id" },
								{ "data": "dist_name" },
								{ "data": "block_name" },
								{ "data": "cluster_name" },
								{ "data": "school_name" },
								{ "data": "raised_on" },
								{ "data": "ticket_period_end_time" },
								{ "data": "question_group" },
								/*{ "data": "officer_id" },*/
								{ "data": "officer_name" },
								{ "data": "officer_designation" }
								]
					} );
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
})