$(document).ready(function(){
	console.log($('#userId').val());
	var userId=$('#userId').val();
	getDistrictName();
	overallScoreOR(null, null);
	getUjjwalValue(null, null);
	getAcademicsValue(null, null);
	getUtthanValue(null, null);
	getSchoolOperationValue(null, null);
	getAttendanceValue(null, null);
	getInfrastructureValue(null, null);
	scoreTable(null, null, null);
	drawChart();
	weekWiseTradeSP(null, null);
	
	function weekWiseTradeSP(name, type){
		$.ajax({
			url:'./dashboard/weekWiseTradeSP.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				var arr=[];
				if(result.success){
					$.each(result.data, function() {
						arr.push({
							label:this.week_number,
							value:this.score
						});
					});
					drawChart(arr);
				}
				console.log('=================weekWiseTradeSP');
				console.log(result);
				console.log(arr);
				console.log('=================weekWiseTradeSP');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function scoreTable(name, type, category){
		console.log('Data Table');
		$.ajax({
			url:'./dashboard/scoreTable.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type, category:category},
			success: function(result){
				console.log('=================scoreTable==========');
				console.log(result);
				console.log('=================scoreTable==========');
				var data=result.data;
				if(result.success){
					var table=$('#score_table').DataTable( {
				        data: data,
				        "searching": false,
				        "scrollY": true,
				        "scrollY":"200px",
				        "scrollCollapse": true,
				        "searching": false,
				        "sorting":false,
				        "paging":false,
				        "destroy": true,
				        columns: [
				            { "data": "Name" },
				            { "data": "Average of Ujjwal" },
				            { "data": "Average of Utthan" },
				            { "data": "Average of Academics" },
				            { "data": "Average of Infrastructure" },
				            { "data": "Average of School Operations" },
				            { "data": "Average of Attendance" }
				        ],
				        "createdRow": function( row, data, dataIndex ) {
				        	 //$( row ).css( "background-color", "Orange" );
				        	console.log(dataIndex[2]);
			                if ( data["2"] == "Bad" ) {
			                    $( row ).css( "background-color", "Orange" );
			                    $( row ).addClass( "warning" );
			                }
			            },
			            "columnDefs": [
			                {"className": "dt-center", "targets": "_all"}
			              ],
				    } );
					table.clear();
				}else{
					swal('error');
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function getUtthanValue(name, type){
		$.ajax({
			url:'./dashboard/getUtthanValue.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				if(result.success){
					var data=result.data[0];
					$("#utthanGauge" ).empty();
					liquidfillgaugeDraw("utthanGauge",data,"%",100);
				}
				console.log('=================getUtthanValue');
				console.log(result);
				console.log('=================getUtthanValue');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function getAcademicsValue(name, type){
		$.ajax({
			url:'./dashboard/getAcademicsValue.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				if(result.success){
					var data=result.data[0];
					$("#academicsGauge" ).empty();
					liquidfillgaugeDraw("academicsGauge",data,"%",100);
				}
				console.log('=================getAcademicsValue');
				console.log(result);
				console.log('=================getAcademicsValue');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function getUjjwalValue(name, type){
		$.ajax({
			url:'./dashboard/getUjjwalValue.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				if(result.success){
					var data=result.data[0];
					$("#ujjwalGauge" ).empty();
					liquidfillgaugeDraw("ujjwalGauge",data,"%",100);
				}
				console.log('=================remediation');
				console.log(result);
				console.log('=================remediation');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function getSchoolOperationValue(name, type){
		$.ajax({
			url:'./dashboard/getSchoolOperationValue.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				if(result.success){
					var data=result.data[0];
					$("#schoolOperationGauge" ).empty();
					liquidfillgaugeDraw("schoolOperationGauge",data,"%",100);
				}
				console.log('=================getSchoolOperationValue===============');
				console.log(result);
				console.log('=================getSchoolOperationValue===============');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function getAttendanceValue(name, type){
		$.ajax({
			url:'./dashboard/getAttendanceValue.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				if(result.success){
					var data=result.data[0];
					$("#attendanceGauge" ).empty();
					liquidfillgaugeDraw("attendanceGauge",data,"%",100);
				}
				console.log('=================getAttendanceValue================');
				console.log(result);
				console.log('=================getAttendanceValue================');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function getInfrastructureValue(name, type){
		$.ajax({
			url:'./dashboard/getInfrastructureValue.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				if(result.success){
					var data=result.data[0];
					$("#infrastructureGauge" ).empty();
					liquidfillgaugeDraw("infrastructureGauge",data,"%",100);
				}
				console.log('=================getInfrastructureValue==================');
				console.log(result);
				console.log('=================getInfrastructureValue==================');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function overallScoreOR(name,type){
		$.ajax({
			url:'./dashboard/overallScoreOR.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				$("#overall_score").text(result.data[0]+"%");
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
				setDataToDropDown(result.data, '#dist_name');
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
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{value:distName},
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
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{distName:data,blockName:blockName},
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
			if (distName=='ALL') {
				$('#block_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				overallScoreOR(null, null);
				scoreTable(null, null, null);
				getUjjwalValue(null, null);
				getAcademicsValue(null, null);
				getUtthanValue(null, null);
				getSchoolOperationValue(null, null);
				getAttendanceValue(null, null);
				getInfrastructureValue(null, null);
				weekWiseTradeSP(null, null);
			} else {
				getBlockName(distName);
				scoreTable(distName, "dist_name", "block_name");
				overallScoreOR(distName, "dist_name");
				getUjjwalValue(distName, "dist_name");
				getAcademicsValue(distName, "dist_name");
				getUtthanValue(distName, "dist_name");
				getSchoolOperationValue(distName, "dist_name");
				getAttendanceValue(distName, "dist_name");
				getInfrastructureValue(distName, "dist_name");
				weekWiseTradeSP(distName, "dist_name");
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
			if (blockName=='ALL') {
				$('#cluster_name').children('option:not(:first)').remove();
				scoreTable(distName, "dist_name", "block_name");
				overallScoreOR(distName, "dist_name");
				getUjjwalValue(distName, "dist_name");
				getAcademicsValue(distName, "dist_name");
				getUtthanValue(distName, "dist_name");
				getSchoolOperationValue(distName, "dist_name");
				getAttendanceValue(distName, "dist_name");
				getInfrastructureValue(distName, "dist_name");
				weekWiseTradeSP(distName, "dist_name");
			} else {
				scoreTable(blockName, "block_name", "cluster_name");
				overallScoreOR(blockName, "block_name");
				getUjjwalValue(blockName, "block_name");
				getAcademicsValue(blockName, "block_name");
				getUtthanValue(blockName, "block_name");
				getSchoolOperationValue(blockName, "block_name");
				getAttendanceValue(blockName, "block_name");
				getInfrastructureValue(blockName, "block_name");
				getClusterName(blockName, "null");
				weekWiseTradeSP(blockName, "block_name");
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
			scoreTable(blockName, "block_name", "cluster_name");
			overallScoreOR(blockName, "block_name");
			getUjjwalValue(blockName, "block_name");
			getAcademicsValue(blockName, "block_name");
			getUtthanValue(blockName, "block_name");
			getSchoolOperationValue(blockName, "block_name");
			getAttendanceValue(blockName, "block_name");
			getInfrastructureValue(blockName, "block_name");
			weekWiseTradeSP(blockName, "block_name");
			if (clusterName=='ALL') {
			} else {
				scoreTable(clusterName, "cluster_name", "school_name");
				overallScoreOR(clusterName, "cluster_name");
				getUjjwalValue(clusterName, "cluster_name");
				getAcademicsValue(clusterName, "cluster_name");
				getUtthanValue(clusterName, "cluster_name");
				getSchoolOperationValue(clusterName, "cluster_name");
				getAttendanceValue(clusterName, "cluster_name");
				getInfrastructureValue(clusterName, "cluster_name");
				weekWiseTradeSP(clusterName, "cluster_name");
			}
		})
	}
	
	function drawChart(arr){
		FusionCharts.ready(function () {
		    var salesChart = new FusionCharts({
		        type: 'area2d',
		        renderAt: 'weekwisetrend',
		        width: '100%',
		        height: '300',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		            	"showBorder" : "0",
		                "caption": "Week Wise Trend",
		                "xAxisName": "Week",
		                "yAxisName": "Score(%)",
		                "yAxisMaxValue": "100",
		                "plotGradientColor": "#00cccc,#00b300"
		            },            
		            "data": arr
		        }
		    }).render('weekwisetrend');
		});
	}
})