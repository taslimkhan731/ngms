$(document).ready(function(){
	
	//$( "#district_name" ).selectmenu();
	
	var formatter = new Intl.NumberFormat('en-IN', {
		  currency: 'INR',
		  minimumFractionDigits: 0,
		})
	var count=1;
	console.log('school coverage');
	console.log($('#userId').val());
	getDistrictName();
	totalNoOfSchoolUjjwalSC(null, null);
	surveyAssigned(null, null);
	//surveyPercentage(null,null);
	//surveyCompleted(null, null);
	getSchoolMonitoredBarUjjwalSC(null, null, null, "District Wise Schools Monitored and Not Monitored", "cov_n_cov", "District");
	//getSchoolMonitoredBarUtthanSC(null, null, null, "UTTHAN-District Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "District");
	schoolMonitoredUjjwalPercentageSC(null, null);
	schoolCoveredByDistrictOfficer(null, null);
	schoolCoveredByBlockOfficer(null, null);
	schoolCoveredByClusterOfficer(null, null);
	schoolMonitoredUjjwalSC(null,null);
	districtButtonClicked(null,null);
	blockButtonClicked(null,null);
	clusterButtonClicked(null,null);
	
	schoolNotVisited(null, null);
	
	$("#dist").hide();
	$("#block").hide();
	$("#cluster").hide();

	/*$("#download").click(function() {
		alert('clicked');
	    window.location = 'file-download.pdf';
	});*/

	
	function schoolNotVisited(name,type){
		$.ajax({
			url:'./dashboard/schoolNotVisited.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				var data=result.data;
				console.log("==========schoolnotvisited==============");
				console.log(result.data);
				console.log("==========schoolnotvisited==============");
				if(result.success){
					var table=$('#schoolnotvisited').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
				           /* {
				            	text:'<i class="glyphicon glyphicon-remove-circle" style="height:1%; width:72%; font-size:3em; color:grey"></i>',
				            	action: function ( e, dt, node, config ) {
				                $('#div_school').hide();
				            	},	
				            },*/
							{
				                extend: 'excelHtml5',
				                className:"BUTTON_DPX",
				               // title: 'School Covered By Cluster Officer'+' '+name+' '+type
				            },
				            {
				                extend: 'print',
				                className:"BUTTON_DPX",
				               // title: 'School Covered By Cluster Officer'+' '+name+' '+type
				            },
							],
							data: data,
							"scrollY": true,
							"scrollY":"300px",
							"scrollCollapse": true,
							"paging":true,
							"destroy": true,
							columns: [
								{ "data": "dist_name" },
								{ "data": "block_name" },
								{ "data": "cluster_name" },
								{ "data": "school_name" },
								{ "data": "location_id" },
								{ "data": "SURVEY_ASSIGNED" },
								{ "data": "SURVEY_NOT_COMPLETED" }
								],
					});
				}
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
			/*if(count==1){
			districtTableSC(type,name);
			count=0;
			}else {
				$("#dist").show();
			}*/
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
			url:'./dashboard/clusterTableSC.do',
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
						//alert(type);
						type='District';
						//alert(type);
					}else if (type=='block_name') {
						//alert(type);
						type='Block';
						//alert(type);
					}else if (type=='cluster_name') {
						//alert(type);
						type='Cluster';
						//alert(type);
					}
					var table=$('#cluster_table').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
				            {
				            	text:'<i class="glyphicon glyphicon-remove-circle" style="height:1%; width:72%; font-size:3em; color:grey"></i>',
				            	//className:"BUTTON_DPX",
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
							data: data,
							//"scrollX": true,
							//"scrollX":"100px",
							//"searching": false,
							//"searching": false,
							//"sorting":false,
							"scrollY": true,
							"scrollY":"300px",
							"scrollCollapse": true,
							"paging":true,
							"destroy": true,
							"fixedColumn":false,
							columns: [
								{ "data": "dist_name" },
								{ "data": "block_name" },
								{ "data": "cluster_name" },
								{ "data": "avg" },
								{ "data": "officer_name" },
								{ "data": "officer_moblieno" },
								{ "data": "officer_email" },
								{ "data": "status" },
								{ "data": "case" },
								{ "data": "target" },
								{ "data": "completed" },
								{ "data": "percentage" }
								],
							"columnDefs": [
					            {
					                "targets": [  8, 9 ],
					                "visible": false
					            }
					        ],
					        "createdRow": function( row, data, dataIndex ) {
								if ( data.percentage<=30 ) {
									$(row).find('td:eq(11)').css( "background-color", "Red" );
									$(row).find('td:eq(11)').css( "color", "white" );
								}else if (data.percentage>=30 && data.percentage<=60) {
									$(row).find('td:eq(11)').css( "background-color", "Orange" );
								}else if (data.percentage>60 && data.percentage<=80) {
									$(row).find('td:eq(11)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.percentage> 80) {
									$(row).find('td:eq(11)').css( "background-color", "rgb(0, 255, 0)" );
								}
							}
					});
					table.columns.adjust();
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function blockTableSC(name,type){
		$.ajax({
			url:'./dashboard/blockTableSC.do',
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
						//alert(type);
						type='District';
						//alert(type);
					}else if (type=='block_name') {
						//alert(type);
						type='Block';
						//alert(type);
					}else if (type=='cluster_name') {
						//alert(type);
						type='Cluster';
						//alert(type);
					}
					var table=$('#block_table').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
				            {
				            	text:'<i class="glyphicon glyphicon-remove-circle" style="height:1%; width:72%; font-size:3em; color:grey"></i>',
				            	//className:"BUTTON_DPX",
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
								{ "data": "avg" },
								{ "data": "officer_name" },
								{ "data": "officer_moblieno" },
								{ "data": "officer_email" },
								{ "data": "status" },
								{ "data": "target" },
								{ "data": "completed" },
								{ "data": "percentage" }
								],
							"columnDefs": [
					            {
					                "targets": [ 10 ],
					                "visible": false
					            }
					        ],
					        "createdRow": function( row, data, dataIndex ) {
								if ( data.percentage<=30 ) {
									$(row).find('td:eq(9)').css( "background-color", "Red" );
									$(row).find('td:eq(9)').css( "color", "white" );
								}else if (data.percentage>=30 && data.percentage<=60) {
									$(row).find('td:eq(9)').css( "background-color", "Orange" );
								}else if (data.percentage>60 && data.percentage<=80) {
									$(row).find('td:eq(9)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.percentage> 80) {
									$(row).find('td:eq(9)').css( "background-color", "rgb(0, 255, 0)" );
								}
							}
					} );
					table.columns.adjust();
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function districtTableSC(name,type){
		$.ajax({
			url:'./dashboard/districtTableSC.do',
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
						//alert(type);
						type='District';
						//alert(type);
					}else if (type=='block_name') {
						//alert(type);
						type='Block';
						//alert(type);
					}else if (type=='cluster_name') {
						//alert(type);
						type='Cluster';
						//alert(type);
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
							columns: [
								{ "data": "officer_designation" },
								{ "data": "dist_name" },
								{ "data": "avg" },
								{ "data": "officer_name" },
								{ "data": "officer_moblieno" },
								{ "data": "officer_email" },
								{ "data": "status" },
								{ "data": "target" },
								{ "data": "completed" },
								{ "data": "percentage" }
								],
							"columnDefs": [
					            {
					                "targets": [ 9 ],
					                "visible": false
					            }
					        ],
					        "createdRow": function( row, data, dataIndex ) {
								if ( data.percentage<=30 ) {
									$(row).find('td:eq(8)').css( "background-color", "Red" );
									$(row).find('td:eq(8)').css( "color", "white" );
								}else if (data.percentage>=30 && data.percentage<=60) {
									$(row).find('td:eq(8)').css( "background-color", "Orange" );
								}else if (data.percentage>60 && data.percentage<=80) {
									$(row).find('td:eq(8)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.percentage> 80) {
									$(row).find('td:eq(8)').css( "background-color", "rgb(0, 255, 0)" );
								}
							}
					} );
					
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function schoolMonitoredUjjwalSC(name,type){
		$.ajax({
			url:'./dashboard/schoolMonitoredUjjwalSC.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log("==========school_monitored ujjwal==============");
				console.log(result.data);
				console.log("==========school_monitored ujjwal==============");
				$("#school_monitored_ujjwal").text(formatter.format(result.data[0]));
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function surveyPercentage(name,type){
		$.ajax({
			url:'./dashboard/surveyPercentage.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log("==========school_monitored utthan==============");
				console.log(result.data);
				console.log("==========school_monitored utthan==============");
				$("#school_monitored_utthan").text(result.data[0]);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function surveyAssigned(name,type){
		$.ajax({
			url:'./dashboard/surveyAssigned.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log("==============surveyAssigned==============");
				console.log(result);
								
				console.log(formatter.format(result.data[0].monitored_percentage));
				console.log("==============surveyAssigned==============");
				$("#assigned_survey").text(formatter.format(result.data[0].assigned));
				$("#survey_completed").text(formatter.format(result.data[0].monitored));
				$("#survey_percentage").text(result.data[0].monitored_percentage+"%");
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function schoolCoveredByDistrictOfficer(name, type){
		$.ajax({
			url:'./dashboard/schoolCoveredByDistrictOfficer.do',
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

	function schoolCoveredByBlockOfficer(name, type){
		$.ajax({
			url:'./dashboard/schoolCoveredByBlockOfficer.do',
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

	function schoolCoveredByClusterOfficer(name, type){
		$.ajax({
			url:'./dashboard/schoolCoveredByClusterOfficer.do',
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

	function schoolMonitoredUjjwalPercentageSC(name, type){
		$.ajax({
			url:'./dashboard/schoolMonitoredUjjwalPercentageSC.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				if(result.success){
				console.log(result);
				console.log("==========school_monitored_percentage ujjwal==============");
				console.log(result.data);
				console.log("==========school_monitored_percentage ujjwal==============");
				$("#no_school_covered_percentage_ujjwal").text(result.data[0]+"%");
				}else{
					$("#no_school_covered_percentage_ujjwal").text("0%");
				}
			},
			error: function(jqXHR, status, error){
				$("#no_school_covered_percentage_ujjwal").text("0%");
				swal('error');
			}
		});
	}

	function surveyCompleted(name, type){
		$.ajax({
			url:'./dashboard/surveyCompleted.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log(result);
				console.log("==========school_monitored_percentage utthan==============");
				console.log(result.data);
				console.log("==========school_monitored_percentage utthan==============");
				$("#no_school_covered_percentage_utthan").text(result.data[0]);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getSchoolMonitoredBarUjjwalSC(name, type, category, caption, area, x){
		$.ajax({
			url:'./dashboard/getSchoolMonitoredBarUjjwalSC.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type, category:category},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log(result.data);
				var label=[];
				var covered=[];
				var noCovered=[];
				var i=0;
				console.log("==========school covered");
				console.log(result);
				console.log(result.data);
				if (result.success) {
					$.each(result.data, function() {
						label.push({
							label:result.data[i].label+"\n"+"("+result.data[i].assigned+")"
						});
						covered.push({
							value:result.data[i].monitored
						});
						noCovered.push({
							value:result.data[i].notmonitored
						});
						i++;
					});
					console.log("===covered===");
					console.log(label);
					console.log(covered);
					console.log(noCovered);
					console.log("===covered===");
					drawStacked(label, covered, noCovered, caption, area, x);
				}
				console.log("==========school covered");
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getSchoolMonitoredBarUtthanSC(name, type, category, caption, area, x){
		$.ajax({
			url:'./dashboard/getSchoolMonitoredBarUtthanSC.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type, category:category},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log(result.data);
				var label=[];
				var covered=[];
				var noCovered=[];
				var i=0;
				console.log("==========school covered");
				console.log(result);
				console.log(result.data);
				if (result.success) {
					$.each(result.data, function() {
						label.push({
							label:result.data[i].label+"\n"+"("+result.data[i].assigned+")"
						});
						covered.push({
							value:result.data[i].monitored
						});
						noCovered.push({
							value:result.data[i].notmonitored
						});
						i++;
					});
					console.log("===covered===");
					console.log(label);
					console.log(covered);
					console.log(noCovered);
					console.log("===covered===");
					drawStacked(label, covered, noCovered, caption, area, x);
				}
				console.log("==========school covered");
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function totalNoOfSchoolUjjwalSC(name,type){
		$.ajax({
			url:'./dashboard/totalNoOfSchoolUjjwalSC.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				$("#total_school").text(formatter.format(result.data[0]));
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
			$("#dist").hide();
			$("#block").hide();
			$("#cluster").hide();
			if (distName=='ALL') {
				$('#block_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				totalNoOfSchoolUjjwalSC(null, null);
				surveyAssigned(null, null);
				getSchoolMonitoredBarUjjwalSC(null, null, null, "District Wise Schools Monitored and Not Monitored", "cov_n_cov", "District");
				//getSchoolMonitoredBarUtthanSC(null, null, null, "UTTHAN-District Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "District");
				schoolMonitoredUjjwalPercentageSC(null, null);
				//surveyCompleted(null, null);
				schoolCoveredByDistrictOfficer(null, null);
				schoolCoveredByBlockOfficer(null, null);
				schoolCoveredByClusterOfficer(null, null);
				schoolMonitoredUjjwalSC(null,null);
				//surveyPercentage(null,null);
				districtButtonClicked(null,null);
				blockButtonClicked(null,null);
				clusterButtonClicked(null,null);
				
				schoolNotVisited(nill, null);
				count =1;
			} else {
				totalNoOfSchoolUjjwalSC(distName,'dist_name');
				surveyAssigned(distName,'dist_name');
				getSchoolMonitoredBarUjjwalSC(distName, "dist_name", "block_name", "Block Wise Schools Monitored and Not Monitored", "cov_n_cov", "Block");
				//getSchoolMonitoredBarUtthanSC(distName, "dist_name", "block_name", "UTTHAN-Block Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "Block");
				schoolMonitoredUjjwalPercentageSC(distName,'dist_name');
				//surveyCompleted(distName,'dist_name');
				schoolCoveredByDistrictOfficer(distName,'dist_name');
				schoolCoveredByBlockOfficer(distName,'dist_name');
				schoolCoveredByClusterOfficer(distName,'dist_name');
				schoolMonitoredUjjwalSC(distName,'dist_name');
				//surveyPercentage(distName,'dist_name');
				getBlockName(distName);
				districtButtonClicked(distName,'dist_name');
				blockButtonClicked(distName,'dist_name');
				clusterButtonClicked(distName,'dist_name');
				
				schoolNotVisited(distName,'dist_name');
				count =1;
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
			$("#dist").hide();
			$("#block").hide();
			$("#cluster").hide();
			if (blockName=='ALL') {
				$('#cluster_name').children('option:not(:first)').remove();
				totalNoOfSchoolUjjwalSC(distName,'dist_name');
				surveyAssigned(distName,'dist_name');
				getSchoolMonitoredBarUjjwalSC(distName, "dist_name", "block_name", "Block Wise Schools Monitored and Not Monitored", "cov_n_cov", "Block");
				//getSchoolMonitoredBarUtthanSC(distName, "dist_name", "block_name", "UTTHAN-Block Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "Block");
				schoolMonitoredUjjwalPercentageSC(distName,'dist_name');
				//surveyCompleted(distName,'dist_name');
				schoolCoveredByDistrictOfficer(distName,'dist_name');
				schoolCoveredByBlockOfficer(distName,'dist_name');
				schoolCoveredByClusterOfficer(distName,'dist_name');
				schoolMonitoredUjjwalSC(distName,'dist_name');
				//surveyPercentage(distName,'dist_name');
				districtButtonClicked(distName,'dist_name');
				blockButtonClicked(distName,'dist_name');
				clusterButtonClicked(distName,'dist_name');
				
				schoolNotVisited(distName,'dist_name');
				count =1;
			} else {
				totalNoOfSchoolUjjwalSC(blockName,'block_name');
				surveyAssigned(blockName,'block_name');
				getSchoolMonitoredBarUjjwalSC(blockName, "block_name", "cluster_name", "Cluster Wise Schools Monitored and Not Monitored", "cov_n_cov", "Cluster");
				//getSchoolMonitoredBarUtthanSC(blockName, "block_name", "cluster_name", "UTTHAN-Cluster Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "Cluster");
				schoolMonitoredUjjwalPercentageSC(blockName,'block_name');
				//surveyCompleted(blockName,'block_name');
				schoolCoveredByDistrictOfficer(blockName,'block_name');
				schoolCoveredByBlockOfficer(blockName,'block_name');
				schoolCoveredByClusterOfficer(blockName,'block_name');
				schoolMonitoredUjjwalSC(blockName,'block_name');
				//surveyPercentage(blockName,'block_name');
				getClusterName(blockName,distName);
				districtButtonClicked(blockName,'block_name');
				blockButtonClicked(blockName,'block_name');
				clusterButtonClicked(blockName,'block_name');
				
				schoolNotVisited(blockName,'block_name');
				count =1;
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
			$("#dist").hide();
			$("#block").hide();
			$("#cluster").hide();
			if (clusterName=='ALL') {
				totalNoOfSchoolUjjwalSC(blockName,'block_name');
				surveyAssigned(blockName,'block_name');
				getSchoolMonitoredBarUjjwalSC(blockName, "block_name", "cluster_name", "Cluster Wise Schools Monitored and Not Monitored", "cov_n_cov", "Cluster");
				//getSchoolMonitoredBarUtthanSC(blockName, "block_name", "cluster_name", "UTTHAN-Cluster Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "Cluster");
				schoolMonitoredUjjwalPercentageSC(blockName,'block_name');
				//surveyCompleted(blockName,'block_name');
				schoolCoveredByDistrictOfficer(blockName,'block_name');
				schoolCoveredByBlockOfficer(blockName,'block_name');
				schoolCoveredByClusterOfficer(blockName,'block_name');
				schoolMonitoredUjjwalSC(blockName,'block_name');
				//surveyPercentage(blockName,'block_name');
				districtButtonClicked(blockName,'block_name');
				blockButtonClicked(blockName,'block_name');
				clusterButtonClicked(blockName,'block_name');
				
				schoolNotVisited(blockName,'block_name');
				count =1;
			} else {
				totalNoOfSchoolUjjwalSC(clusterName, 'cluster_name');
				surveyAssigned(clusterName, 'cluster_name');
				getSchoolMonitoredBarUjjwalSC(clusterName, "cluster_name", "public.master_location.school_name", "School Wise Schools Monitored and Not Monitored", "cov_n_cov", "School")
				//getSchoolMonitoredBarUtthanSC(clusterName, "cluster_name", "public.master_location.school_name", "UTTHAN-School Wise Schools Monitored and Not Monitored", "cov_n_cov-utthan", "School")
				schoolMonitoredUjjwalPercentageSC(clusterName, 'cluster_name');
				//surveyCompleted(clusterName, 'cluster_name');
				schoolCoveredByDistrictOfficer(clusterName, 'cluster_name');
				schoolCoveredByBlockOfficer(clusterName, 'cluster_name');
				schoolCoveredByClusterOfficer(clusterName, 'cluster_name');
				schoolMonitoredUjjwalSC(clusterName, 'cluster_name');
				//surveyPercentage(clusterName, 'cluster_name');
				districtButtonClicked(clusterName, 'cluster_name');
				blockButtonClicked(clusterName, 'cluster_name');
				clusterButtonClicked(clusterName, 'cluster_name');
				
				schoolNotVisited(clusterName, 'cluster_name');
				count =1;
			}
		})
	}

	function drawStacked(label, pending, covered, caption, area, x){
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
								"seriesname": "Monitored",
								"color": "26b262",
								"data": pending
							},{
								"seriesname": "Not Monitored",
								"color": "ff3333",
								"data": covered
							}
							]
				}
			}).render(area);
		});
	}

})