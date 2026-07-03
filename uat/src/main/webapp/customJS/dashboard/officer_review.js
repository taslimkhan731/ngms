$(document).ready(function(){
	console.log($('#userId').val());
	getDistrictName();
	getSurveyStatusOR(null,null);
	getActionPointStatusOR(null,null);
	overallScoreOR(null, null);
	avgTime(null, null);
	districtWiseSurveyStatus(null, null, null, null, 'District Name');
	districtWiseTicketStatus(null, null, null, null, 'District Name');
	getOfficerPerformance(null, null);



	function getOfficerPerformance(name, type){
		console.log('Data Table');
		$.ajax({
			url:'./dashboard/getOfficerPerformance.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				console.log(result);
				var data=result.data;
				if(result.success){
					var table=$('#officer_table').DataTable( {
						dom: 'Bfrtip',
						buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
						data: data,
						"searching": true,
						"scrollY": true,
						"scrollY":"200px",
						"scrollCollapse": true,
						"paging":false,
						"sorting":true,
						"destroy": true,
						columns: [
							{ "data": "Officer Name" },
							{ "data": "Officer Designation" },
							{ "data": "Assigned Inspection" },
							{ "data": "Pending Inspection" },
							{ "data": "Pending Action Point" }
							],
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

	function getSurveyStatusOR(name, type){
		$.ajax({
			url:'./dashboard/getSurveyStatusOR.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				var arr=[];
				var i=0;
				if (result.success) {
					$.each(result.data, function() {
						arr.push({
							label:result.data[i].label,
							value:result.data[i].value
						});
						i++;
					});
					console.log("====================getSurveyStatusOR==========");
					console.log(arr);
					console.log("====================getSurveyStatusOR==========");
					drawChart(arr, 'Survey Status', 'district_donut');
				} else {
					swal('error');
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function districtWiseTicketStatus(name, type, up, label, x){
		$.ajax({
			url:'./dashboard/getTicketStatusOR.do',
			type: 'GET',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type, up:up},
			success: function(result){
				console.log(result.data);
				var label=[];
				var pending=[];
				var resolved=[];
				var i=0;
				if (result.success) {
					$.each(result.data, function() {
						label.push({
							label:result.data[i].label
						});
						pending.push({
							value:result.data[i].pending
						});
						resolved.push({
							value:result.data[i].resolved
						});
						i++;
					});
					drawStacked(label, pending, resolved, 'Ticket Status', 'ticket_status', x);
				} else {
					swal('error');
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	} 

	function getActionPointStatusOR(name, type){
		$.ajax({
			url:'./dashboard/getActionPointStatusOR.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				var arr=[];
				var i=0;
				if (result.success) {
					$.each(result.data, function() {
						arr.push({
							label:result.data[i].label,
							value:result.data[i].value
						});
						i++;
					});
					console.log("====================getActionPointStatusOR==========");
					console.log(arr);
					console.log("====================getActionPointStatusOR==========");
					drawChart2(arr, 'Action Point Status', 'block_donut');
				} else {
					swal('error');
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function districtWiseSurveyStatus(name, type, up, label, x){
		$.ajax({
			url:'./dashboard/districtWiseSurveyStatus.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type, up:up},
			success: function(result){
				var category=[];
				var data=[];
				if (result.success) {
					console.log(result.data);
					$.each(result.data, function() {
						category.push({
							label:this.label
						});
						data.push({
							value:this.round
						});
					});
					//drawBar();
					console.log(category);
					console.log(data);
					drawBar(category,data, 'District Wise Survey Status', 'survey_status', 'scrollColumn2d', x);
				} else {
					swal('error');
				}
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

	function avgTime(name,type){
		$.ajax({
			url:'./dashboard/avgTime.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				var time=result.data[0];
				console.log("time===========");
				console.log(time);
				console.log("time===========");
				$("#fillgauge3").empty();
				liquidfillgaugeDrawAvgTime("fillgauge3",time," min",20);
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
				getSurveyStatusOR(null,null);
				getActionPointStatusOR(null,null);
				districtWiseSurveyStatus(null, null, null, null, 'District Name');
				districtWiseTicketStatus(null, null, null, null, 'District Name');
				getOfficerPerformance(null, null);
				overallScoreOR(null, null);
				avgTime(null, null);
			} else {
				getBlockName(distName);
				getSurveyStatusOR(distName, 'dist_name');
				getActionPointStatusOR(distName, 'dist_name');
				districtWiseSurveyStatus(distName, 'dist_name', 'block_name', 'dist_name', 'Block Name');
				districtWiseTicketStatus(distName, 'dist_name', 'block_name', 'dist_name', 'Block Name');
				getOfficerPerformance(distName,'dist_name');
				overallScoreOR(distName,'dist_name');
				avgTime(distName,'dist_name');
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
				getBlockName(distName);
				getSurveyStatusOR(distName, 'dist_name');
				getActionPointStatusOR(distName, 'district_name');
				districtWiseSurveyStatus(distName, 'dist_name', 'block_name', 'dist_name', 'Block Name');
				districtWiseTicketStatus(distName, 'dist_name', 'block_name', 'dist_name', 'Block Name');
				getOfficerPerformance(distName,'dist_name');
				overallScoreOR(distName,'dist_name');
				avgTime(distName,'dist_name');
			} else {
				getClusterName(blockName, "null");
				getSurveyStatusOR(blockName, 'block_name');
				getActionPointStatusOR(blockName, 'block_name');
				districtWiseSurveyStatus(blockName, 'block_name', 'cluster_name','block_name', 'Cluster Name');
				districtWiseTicketStatus(blockName, 'block_name', 'cluster_name','block_name', 'Cluster Name');
				getOfficerPerformance(blockName,'block_name');
				overallScoreOR(blockName,'block_name');
				avgTime(blockName,'block_name');
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
			if (clusterName=='ALL') {
				getClusterName(blockName, "null");
				getSurveyStatusOR(blockName, 'block_name');
				getActionPointStatusOR(blockName, 'block_name');
				districtWiseSurveyStatus(blockName, 'block_name', 'cluster_name','block_name', 'Cluster Name');
				districtWiseTicketStatus(blockName, 'block_name', 'cluster_name','block_name', 'Cluster Name');
				getOfficerPerformance(blockName,'block_name');
				overallScoreOR(blockName,'block_name');
				avgTime(blockName,'block_name');
			} else {
				getSurveyStatusOR(clusterName, 'cluster_name');
				getActionPointStatusOR(clusterName, 'cluster_name');
				districtWiseSurveyStatus(clusterName, 'cluster_name', 'school_name','cluste_name', 'School Name');
				districtWiseTicketStatus(clusterName, 'cluster_name', 'school_name','cluste_name', 'School Name');
				getOfficerPerformance(clusterName,'cluster_name');
				overallScoreOR(clusterName, 'cluster_name');
				avgTime(clusterName, 'cluster_name');
			}
		})
	}

	function drawChart(arr, caption, area){
		FusionCharts.ready(function () {
			var datachart = new FusionCharts({
				type: 'doughnut2d',
				renderAt: area,
				width: '100%',
				height: '300',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						/*"exportEnabled": "1",
						"exportFileName": caption,*/
						"formatNumberScale": "0",
						"caption": caption,
						'paletteColors' :'006442,CF000F,F2C80F',
						"showPercentValues": "1",
						"showPercentInTooltip": "1",
						"startingAngle": "0",
						"decimals": "0",  
						"showLabels": "0",
						"showLegend": "1",
						"showValues": "1",
						"centerLabel": "$value",
						"pieRadius": "80",
						"enableSmartLabels": "1",
						"labelDistance": "1",
						"smartLabelClearance": "5",
						"theme": "fint" ,
						"use3DLighting": "1",
						"radius3D": "5"
					},
					"data": arr
				}
			}).render(area);
		});
	}

	function drawChart2(arr, caption, area){
		FusionCharts.ready(function () {
			var datachart = new FusionCharts({
				type: 'doughnut2d',
				renderAt: area,
				width: '100%',
				height: '300',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						/*"exportEnabled": "1",
						"exportFileName": caption,*/
						"formatNumberScale": "0",
						"caption": caption,
						'paletteColors' :'006442,CF000F,F2C80F',
						"showPercentValues": "1",
						"showPercentInTooltip": "1",
						"startingAngle": "0",
						"decimals": "0",  
						"showLabels": "0",
						"showLegend": "1",
						"showValues": "1",
						"centerLabel": "$value",
						"pieRadius": "80",
						"enableSmartLabels": "1",
						"labelDistance": "1",
						"smartLabelClearance": "5",
						"theme": "fint" ,
						"use3DLighting": "1",
						"radius3D": "5"
					},
					"data": arr
				}
			}).render(area);
		});
	}

	function drawBar(category,data, caption, area, type, x){
		FusionCharts.ready(function () {
			var datachart = new FusionCharts({
				type: type,
				renderAt: area,
				width: '100%',
				height: '350',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						/*"exportEnabled": "1",
						"exportFileName": caption,*/
						"caption": caption,
						"xaxisname": x,
						"yaxisname": 'Survey Status',
						"yAxisMaxValue": "100",
						"showvalues": "1",
						"placeValuesInside" : "1",
						"rotateValues": "0",
						"valueFontColor" : "#ffffff",
						"numberSuffix": "%",

						//Cosmetics
						"baseFontColor" : "#333333",
						"showborder": "0",
						"paletteColors" : "#0075c2",
						"bgcolor": "#FFFFFF",
						"showalternatehgridcolor": "0",
						"showplotborder": "0",
						"labeldisplay": "WRAP",
						"divlinecolor": "#CCCCCC",
						"showcanvasborder": "0",
						"linethickness": "3",
						"plotfillalpha": "100",
						"plotgradientcolor": "",
						"numVisiblePlot" : "5",
						"divlineAlpha" : "100",
						"divlineColor" : "#999999",
						"divlineThickness" : "1",
						"divLineIsDashed" : "1",
						"divLineDashLen" : "1",
						"divLineGapLen" : "1",
						"scrollheight" : "10",
						"flatScrollBars" : "1",
						"scrollShowButtons" : "0",
						"scrollColor" : "#cccccc",
						"showHoverEffect" : "1",
					},
					"categories": [
						{
							"category": category
						}
						],
						"dataset": [
							{
								"data": data
							}
							]
				}
			}).render(area);
		});
	}

	function drawStacked(label, pending, resolved, caption, area, x){
		FusionCharts.ready(function(){
			var datachart = new FusionCharts({
				type: 'scrollstackedcolumn2d',
				dataFormat: 'json',
				renderAt: area,
				width: '100%',
				height: '350',
				dataSource: {
					"chart": {
						/*"exportEnabled": "1",
						"exportFileName": caption,*/
						"caption": caption,
						"captionFontSize": "14",
						"subcaptionFontSize": "14",
						"subcaptionFontBold": "0",
						"xaxisname": x,
						"yaxisname": "Ticket Status",
						"showvalues": "1",
						"valueFontColor": "#ffffff",
						//"numberSuffix": "%",
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
						"numVisiblePlot": "5",
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
								"color": "006442",
								"data": resolved
							},
							{
								"seriesname": "Pending",
								"color": "CF000F",
								"data": pending
							}
							],
							"events": {
								"dataLabelClick": function (eventObj, dataObj) {
									alert(dataObj.text);
								}
							}
				}
			}).render(area);
		});
	}

})