$(document).ready(function() {		 
	$("#chart-dropdown").hide();
	$("#chart-dropdown2").hide();
	$("#chart-dropdown3").hide();
	$("#blockDD").hide();
	$("#chat-area").hide();
	surveyCategory(null, null);
	map(null);
	$("#question_table").empty();
	getQuestionTable(null, null, null, null, null, "public.fact_questionagg");
	//weekStatusSurvey(null);
	weekStatusSurveyUtthan(null, null);

	$('#clear').on('click',function(){
		$("#map-area").show();
		$("#chat-area").hide();
		$("#chart-dropdown").hide();
		$("#blockDD").hide();
		$("#chart-dropdown2").hide();
		$("#chart-dropdown3").hide();
		$("#districtDropdown").val("ALL");
		$("#survey_category").val("ALL");
		$('#blockDropdown').children('option:not(:first)').remove();
		$('#clusterDropdown').children('option:not(:first)').remove();
		$('#schoolDropdown').children('option:not(:first)').remove();
		$("#textArea").show();
		$("#question_table").empty();
		getQuestionTable(null, null, null, null, null, "public.fact_questionagg");
		//weekStatusSurvey(null);
		weekStatusSurveyUtthan(null, null);
	})

	function getChartDropdown(distName){
		$.ajax({
			url:'./dashboard/getBlockName.do',
			type: 'POST',
			async: false,
			data:{value:distName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				setDataToDropDown(result.data, distName);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function setDataToDropDown(data, distName){
		var blockName = data;
		console.log("block name==================");
		console.log(blockName);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<blockName.length;i++) {
			option += '<option value="'+ blockName[i]+ '">' + blockName[i]+ '</option>';
		}
		$('#blockDropdown').html("");
		$('#blockDropdown').append(option);
		$("#blockDropdown").off().on('change', function(){
			var blockName = $(this).find(":selected").val();
			var category=$("#survey_category").val();
			if (blockName=='ALL') {
				$('#schoolDropdown').children('option:not(:first)').remove();
				$('#clusterDropdown').children('option:not(:first)').remove();
				//weekStatusSurvey(distName);
				weekStatusSurveyUtthan("dist_name",distName);
				tableData(distName);
				getChartDropdown(distName);
				$("#question_table").empty();
				getQuestionTable(distName,'dist_name', category, null, null, "public.fact_questiondistagg");
			} else {
				getClusterName(blockName, distName);
				getClusterData(blockName, distName);
				//getWeekSurveyStatusBlock(blockName);
				weekStatusSurveyUtthan("block_name",blockName);
				$("#question_table").empty();
				getQuestionTable(blockName,'block_name', category, null, null, "public.fact_questionblockagg");
				$("#blockDD").show();
			}
		})
	}

	function getWeekSurveyStatusBlock(blockName){
		$.ajax({
			url:'./dashboard/getWeekSurveyStatusBlock.do',
			type:'POST',
			async:false,
			data:{blockName:blockName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result) {	
				if (result.success) {
					console.log("=================week map===================");
					console.log(result.data);
					console.log("=================week map===================");
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.week_wise_survey,
							value:this.survey_conducted
						}); 
					})
				} else {
					swal(result.msg+" Error");
				}
				drawBarchartWeek(arr);
			},
			error:function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getWeekSurveyStatusSchool(schoolName){
		$.ajax({
			url:'./dashboard/getWeekSurveyStatusSchool.do',
			type:'POST',
			async:false,
			data:{schoolName:schoolName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result) {	
				if (result.success) {
					console.log("=================week map school===================");
					console.log(result.data);
					console.log("=================week map school===================");
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.week_wise_survey,
							value:this.survey_conducted
						}); 
					})
				} else {
					swal(result.msg+" Error");
				}
				drawBarchartWeek(arr);
			},
			error:function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getClusterName(blockName, distName){
		$.ajax({
			url:'./dashboard/getClusterName.do',
			type: 'POST',
			async: false,
			data:{distName:distName, blockName:blockName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				setDataToDropDownCluster(result.data, blockName);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getschoolName(clusterName){
		$.ajax({
			url:'./dashboard/getSchoolName.do',
			type: 'POST',
			async: false,
			data:{clusterName:clusterName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				setDataToSchoolDropDown(result.data, clusterName);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function setDataToSchoolDropDown(data, clusterName){
		var schoolName = data;
		console.log("========school name========");
		console.log(schoolName);
		console.log("========school name========");
		var option = '<option value="ALL" deactive>ALL</option>';
		for(var i = 0;i<schoolName.length;i++) {
			option += '<option value="'+ schoolName[i]+ '">' + schoolName[i]+ '</option>';
		}
		$('#schoolDropdown').html("");
		$('#schoolDropdown').append(option);
	}

	$("#schoolDropdown").on('change', function(){
		var category=$("#survey_category").find(":selected").val();
		var schoolName = $(this).find(":selected").val();
		var blockName=$("#blockDropdown").find(":selected").val();
		var distName=$("#districtDropdown").find(":selected").val();
		var  clusterName=$("#clusterDropdown").find(":selected").val();
		if (schoolName=='ALL') {
			//getWeekSurveyStatusCluster(clusterName);
			weekStatusSurveyUtthan("cluster_name", clusterName);
			$("#question_table").empty();
			getQuestionTable(clusterName, 'cluster_name', category, null, null, "public.fact_questionclusteragg");
		} else {
			//getWeekSurveyStatusSchool(schoolName);
			weekStatusSurveyUtthan("school_name", schoolName);
			$("#question_table").empty();
			getQuestionTable(schoolName, 'school_name', category, null, null, "public.fact_questionschoolagg");
		}

	})

	function setDataToDropDownCluster(data, blockName){
		var blockName = data;
		var option = '<option value="ALL" deactive>ALL</option>';
		for(var i = 0;i<blockName.length;i++) {
			option += '<option value="'+ blockName[i]+ '">' + blockName[i]+ '</option>';
		}
		$('#clusterDropdown').html("");
		$('#clusterDropdown').append(option);
	}

	$("#clusterDropdown").on('change', function(){
		var clusterName = $(this).find(":selected").val();
		var category=$("#survey_category").val();
		var blockName=$("#blockDropdown").find(":selected").val();
		var distName=$("#districtDropdown").find(":selected").val();
		if (clusterName=='ALL') {
			$('#schoolDropdown').children('option:not(:first)').remove();
			getClusterName(blockName, distName);
			getClusterData(blockName, distName);
			getSurveyStatusBlock(blockName);
			//getWeekSurveyStatusBlock(blockName);
			weekStatusSurveyUtthan("block_name",blockName);
			$("#question_table").empty();
			getQuestionTable(blockName,'block_name', category, null, null, "public.fact_questionblockagg");
		} else {
			getSchoolData(clusterName);
			//getWeekSurveyStatusCluster(clusterName);
			weekStatusSurveyUtthan("cluster_name",clusterName);
			getschoolName(clusterName);
			$("#question_table").empty();
			getQuestionTable(clusterName, 'cluster_name', category, null, null, "public.fact_questionclusteragg");
		}

	})

	function getWeekSurveyStatusCluster(clusterName){
		$.ajax({
			url:'./dashboard/getWeekSurveyStatusCluster.do',
			type:'POST',
			async:false,
			data:{clusterName:clusterName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result) {	
				if (result.success) {
					console.log("=================week map===================");
					console.log(result.data);
					console.log("=================week map===================");
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.week_wise_survey,
							value:this. survey_conducted
						}); 
					})
				} else {
					swal(result.msg+" Error");
				}
				drawBarchartWeek(arr);
			},
			error:function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getSchoolData(clusterName){
		$.ajax({
			url:'./dashboard/getSchoolData.do',
			type: 'POST',
			async: false,
			data:{clusterName:clusterName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				if (result.success) {
					console.log('==========================Success============')
					console.log(result);
					console.log('==========================success============')
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.school_name,
							value:this.score
						}); 
						var name="School";
						console.log('==========================school============')
						console.log(result.data);
						console.log('==========================school============')
						drawBarchartCluster(arr, name);
					})
				} else {
					swal(result.msg+" Error");
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getClusterData(clusterName,distName){
		$.ajax({
			url:'./dashboard/getClusterData.do',
			type: 'POST',
			async: false,
			data:{value:clusterName, distName:distName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log("=======cluster data=======");
				console.log(result.data);
				console.log("=======cluster data=======");
				if (result.success) {
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.cluster_name,
							value:this.score
						}); 
						var name='Cluster';
						console.log("=======cluster data arr=======");
						console.log(arr);
						console.log("=======cluster data arr=======");
						drawBarchartCluster(arr, name);

					})
				} else {
					swal(result.msg+" Error");
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}


	function map(data){
		$.ajax({
			url:'./dashboard/map.do',
			type:"POST",
			async:false,
			data:{value:data},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result) {
				console.log("=========result map=========");
				console.log(result);
				console.log(result.data);
				console.log("=========result map=========");
				if(result.success){
					var arr = [];
					var distName=[];
					$.each(result.data, function() {
						arr.push({
							id:this.code,
							value:this.overallscore
						});
						distName.push({
							name:this.district_name
						});
					})
					console.log(arr);
					setToMapDropDown(distName);
					drawMap(arr);
				}else{
					swal(result.msg+" Error");	
				}
			},
			error:function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function setToMapDropDown(data){
		var distName = data;
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<distName.length;i++) {
			option += '<option value="'+ distName[i].name+ '">' + distName[i].name+ '</option>';
		}
		$('#districtDropdown').html("");
		$('#districtDropdown').append(option);
	}

	$("#districtDropdown").off().on('change', function(){
		var distName = $(this).find(":selected").val();
		var category=$("#survey_category").val();
		if (distName=='ALL') {
			$("#map-area").show();
			$("#chat-area").hide();
			$("#chart-dropdown").hide();
			$("#blockDD").hide();
			$("#chart-dropdown2").hide();
			$("#chart-dropdown3").hide();
			$("#districtDropdown").val("ALL");
			$('#schoolDropdown').children('option:not(:first)').remove();
			$('#clusterDropdown').children('option:not(:first)').remove();
			$('#blockDropdown').children('option:not(:first)').remove();
			//weekStatusSurvey(null);
			weekStatusSurveyUtthan(null,null);
			$("#textArea").show();
			$("#question_table").empty();
			getQuestionTable(null, null, category, null, null, "public.fact_questionagg");
		} else {
			//weekStatusSurvey(distName);
			weekStatusSurveyUtthan("dist_name",distName);
			tableData(distName);
			$("#districtDropdown").val(distName);
			$("#blockDD").show();
			$("#chat-area").show();
			$("#chart-dropdown").show();
			$("#chart-dropdown2").show();
			$("#chart-dropdown3").show();
			$("#map-area").hide();
			getChartDropdown(distName);
			$("#question_table").empty();
			getQuestionTable(distName,'dist_name', category, null, null, "public.fact_questiondistagg");
			$("#textArea").hide();
		}
	})


	function weekStatusSurvey(data){
		$.ajax({
			url:'./dashboard/getWeekSurveyStatus.do',
			type:'POST',
			async:false,
			data:{value:data},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result) {	
				if (result.success) {
					console.log("=================week map===================");
					console.log(result.data);
					console.log("=================week map===================");
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.week_wise_survey,
							value:this. survey_conducted
						}); 
					})
				} else {
					swal(result.msg+" Error");
				}
				drawBarchartWeek(arr);
			},
			error:function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function weekStatusSurveyUtthan(type, name){
		$.ajax({
			url:'./dashboard/getWeekSurveyStatusUtthan.do',
			type:'POST',
			async:false,
			data:{type:type, name:name},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result) {	
				if (result.success) {
					console.log("=================week map===================");
					console.log(result.data);
					console.log("=================week map===================");
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.week_wise_survey,
							value:this. survey_conducted
						}); 
					})
				} else {
					swal(result.msg+" Error");
				}
				drawBarchartWeekUtthan(arr);
			},
			error:function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function tableData(data){
		$.ajax({
			url:'./dashboard/getBlockData.do',
			type: 'POST',
			data:{value:data},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success:function(result){
				console.log("=======block data=======");
				console.log(result.data);
				console.log("=======block data=======");
				if (result.success) {
					var arr=[];
					$.each(result.data, function() {
						arr.push({
							label:this.block_name,
							value:this.score
						}); 
					})
					console.log("=======block data arr=======");
					console.log(arr);
					console.log("=======block data arr=======");
					drawBarchartBlock(arr);
				} else {

				}
			}
		})
	}

	function drawMap(arr){
		FusionCharts.ready(function() {
			var mapOfIndia = new FusionCharts({
				type: "maps/odisha",
				renderAt: "map-area",
				height: "650",
				width: "100%",
				dataFormat: "json",
				dataSource: {
					"chart": {
						"animation": "1",
						"showbevel": "1",
						"usehovercolor": "1",
						"canvasbordercolor": "FFFFFF",
						"numberSuffix": "%",
						"bordercolor": "FFFFFF",
						"showlegend": "1",
						"showshadow": "1",
						"legendposition": "BOTTOM",
						"legendborderalpha": "0",
						"legendbordercolor": "ffffff",
						"legendallowdrag": "0",
						"legendshadow": "0",
						"connectorcolor": "000000",
						"fillalpha": "80",
						"hovercolor": "cccccc",
						"showBorder": "1",
						"bordercolor":"000000",
						"showLabels": "0",
						"showTooltip": "1"
					},
					"colorrange": {
						"minvalue": "0",
						"startlabel": "Low",
						"endlabel": "High",
						"code": "ff0000",
						"gradient": "1",
						"color": [{"minvalue": "0", "code": "ff0000"}, 
							{"maxvalue": "100", "code": "33cc00"}]
					},
					"data": arr,
				},

				"events": {
					"entityRollover": function(evt, data) {
						document.getElementById('textArea').value="District Name  : " + data.label + "\n" +
						"Overall Score  : " + data.value+"%";
					},
					"entityRollout": function(evt, data) {
						document.getElementById('textArea').value =
							"Overall Score district wise";
					},
					"entityClick": function(evt, data) {
						var category=$("#survey_category").val();
						document.getElementById('textArea').value="District Name  : " + data.label + "\n" +
						"Overall Score  : " + data.value+"%";
						var newData=data.label.toUpperCase();
						if(newData=='KENDUJHAR (KEONJHAR)'){
							newData='KEONJHAR';
						}if (newData=='SUNDARGARH') {
							newData='SUNDERGARH';
						}if (newData=='JAGATSINGHAPUR') {
							newData='JAGATSINGHPUR';
						}
						//weekStatusSurvey(newData);
						weekStatusSurveyUtthan("dist_name",newData);
						tableData(newData);
						$("#districtDropdown").val(newData);
						$("#blockDD").show();
						$("#chat-area").show();
						$("#chart-dropdown").show();
						$("#chart-dropdown2").show();
						$("#chart-dropdown3").show();
						$("#map-area").hide();
						$("#textArea").hide();
						getChartDropdown(newData);
						$("#question_table").empty();
						getQuestionTable(newData,'dist_name', category, null, null, "public.fact_questiondistagg");
					},
				}
			}).render("map-area"); 
		});
	}


	function drawBarchartWeek(data, id, cap){
		FusionCharts.ready(function () {
			var chartColumnWeek = new FusionCharts({
				type: 'column2d',
				renderAt: 'bar-chart-weekly-survey-ujjwal',
				width: '100%',
				height: '300',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						"caption": "WEEK WISE SURVEY STATUS",
						"xAxisName": "Week",
						"yAxisName": "Survey Conducted",
						"paletteColors": "#0075c2",
						"bgColor": "#ffffff",
						"borderAlpha": "20",
						"canvasBorderAlpha": "0",
						"usePlotGradientColor": "0",
						"plotBorderAlpha": "10",
						"placevaluesInside": "0",
						"rotatevalues": "0",
						"valueFontColor": "#000000",                
						"showXAxisLine": "1",
						"xAxisLineColor": "#999999",
						"divlineColor": "#999999",               
						"divLineIsDashed": "1",
						"showAlternateHGridColor": "0",
						"subcaptionFontBold": "0",
						"subcaptionFontSize": "14",
						"formatNumberScale": "0",
						"decimalSeparator": ",",
						"thousandSeparator": ","
					},            
					"data": data,
					"trendlines": [
						{
							"line": [
								{
									"color": "#1aaf5d",
									"valueOnRight": "1"
								}
								]
						}
						]
				}
			}).render('bar-chart-weekly-survey-ujjwal');
		});
	}

	function drawBarchartWeekUtthan(data, id, cap){
		FusionCharts.ready(function () {
			var chartColumnWeek = new FusionCharts({
				type: 'column2d',
				renderAt: 'bar-chart-weekly-survey-utthan',
				width: '100%',
				height: '300',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						"caption": "WEEK WISE SURVEY STATUS",
						"xAxisName": "Week",
						"yAxisName": "Survey Conducted",
						"paletteColors": "#0075c2",
						"bgColor": "#ffffff",
						"borderAlpha": "20",
						"canvasBorderAlpha": "0",
						"usePlotGradientColor": "0",
						"plotBorderAlpha": "10",
						"placevaluesInside": "0",
						"rotatevalues": "0",
						"valueFontColor": "#000000",                
						"showXAxisLine": "1",
						"xAxisLineColor": "#999999",
						"divlineColor": "#999999",               
						"divLineIsDashed": "1",
						"showAlternateHGridColor": "0",
						"subcaptionFontBold": "0",
						"subcaptionFontSize": "14",
						"formatNumberScale": "0",
						"decimalSeparator": ",",
						"thousandSeparator": ","
					},            
					"data": data,
					"trendlines": [
						{
							"line": [
								{
									"color": "#1aaf5d",
									"valueOnRight": "1"
								}
								]
						}
						]
				}
			}).render('bar-chart-weekly-survey-utthan');
		});
	}

	function drawBarchartBlock(data){
		FusionCharts.ready(function () {
			var chartBarBlock = new FusionCharts({
				type: 'bar2d',
				renderAt: 'chat-area',
				width: '585',
				height: '420',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						"caption": "OVERALL SCORE",
						"xAxisName": "Block",
						"yAxisName": "Overall Score",
						"yAxisMaxValue": "100",
						"numberSuffix": "%",
						"paletteColors": "#0075c2",
						"bgColor": "#ffffff",
						"borderAlpha": "20",
						"canvasBorderAlpha": "0",
						"usePlotGradientColor": "0",
						"plotBorderAlpha": "10",
						"placevaluesInside": "0",
						"rotatevalues": "0",
						"valueFontColor": "#000000",                
						"showXAxisLine": "1",
						"xAxisLineColor": "#999999",
						"showAlternateVGridColor": "0",
						"divlineColor": "#999999",               
						"divLineIsDashed": "1",
						"showAlternateHGridColor": "0",
						"subcaptionFontBold": "0",
						"subcaptionFontSize": "14",
					},            
					"data": data,
					"trendlines": [
						{
							"line": [
								{
									"color": "#1aaf5d",
									"valueOnRight": "1"
								}
								]
						}
						]
				},
				"events": {
					"entityRollover": function(evt, data) {
					},
					"entityRollout": function(evt, data) {
					},
					"chartClick": function(eventObj, dataObj) {
					}
				}
			}).render('chat-area');
		});
	}

	function drawBarchartCluster(data,name){
		FusionCharts.ready(function () {
			var chartBarCluster = new FusionCharts({
				type: 'bar2d',
				renderAt: 'chat-area',
				width: '585',
				height: '400',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						"caption": "OVERALL SCORE",
						"xAxisName": name,
						"yAxisName": "Overall Score",
						"yAxisMaxValue": "100",
						"paletteColors": "#0075c2",
						"bgColor": "#ffffff",
						"borderAlpha": "20",
						"canvasBorderAlpha": "0",
						"usePlotGradientColor": "0",
						"plotBorderAlpha": "10",
						"placevaluesInside": "0",
						"rotatevalues": "0",
						"valueFontColor": "#000000",                
						"showXAxisLine": "1",
						"xAxisLineColor": "#999999",
						"divlineColor": "#999999",               
						"divLineIsDashed": "1",
						"showAlternateVGridColor": "0",
						"showAlternateHGridColor": "0",
						"subcaptionFontBold": "0",
						"subcaptionFontSize": "14",
					},            
					"data": data,
					"trendlines": [
						{
							"line": [
								{
									"color": "#1aaf5d",
									"valueOnRight": "1"
								}
								]
						}
						]
				},
				"events": {
					"entityRollover": function(evt, data) {
					},
					"entityRollout": function(evt, data) {
					},
					"chartClick": function(eventObj, dataObj) {
					}
				}
			}).render('chat-area');
		});
	}



	/*----------------------------------------------for table--------------------------*/	

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
			var distName=$("#districtDropdown").val();
			var blockName=$("#blockDropdown").val();
			var clusterName=$("#clusterDropdown").val();
			var schoolName=$("#schoolDropdown").val();
			var name1=null;
			var type1=null;
			var tableName="public.fact_questionagg";
			if (schoolName==null||schoolName=='ALL') {
				console.log("in school if");
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
			}else {
				name1=schoolName;
				type1="school_name";
				tableName="public.fact_questionschoolagg";
			}
			console.log("Name1=        "+name1);
			console.log("type1=        "+type1);
			console.log("Name=        "+name);
			console.log("type=        "+type);
			console.log("===================names====================");
			if (category=="ALL") {
				getQuestionTable( null, null, null, name1, type1, tableName);
			} else {
				getQuestionTable( null, null, category, name1, type1, tableName);
			}
		})
	}

	function getQuestionTable(name, type, category, name1, type1, tableName){
		console.log('Data Table');
		console.log('Name= '+name);
		console.log('Type= '+type);
		console.log('Category= '+category);
		console.log('Type1= '+type1);
		console.log('Name1= '+name1);
		console.log('Data Table');
		$.ajax({
			url:'./dashboard/getQuestionTable.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type, category:category, name1:name1, type1:type1, tableName:tableName},
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			success: function(result){
				console.log(result);
				var data=result.data;
				if(result.success){
					var table=$('#question_table').DataTable( {
						data: data,
								"scrollY": true,
								"scrollY":"475px",
								"scrollCollapse": true,
								"paging":false,
								"destroy": true,
								"fixedHeader": true,
							columns: [
								{ "data": "questions" },
								{ "data": "category" },
								{ "data": "% Schools Which Satisfy" },
								{ "data": "Performance" }
								],
								"createdRow": function( row, data, dataIndex ) {
									if ( data.Performance == "Bad" ) {;
										$(row).find('td:eq(3)').css( "background-color", "Orange" );
									}else if (data.Performance == "Average") {
										$(row).find('td:eq(3)').css( "background-color", "rgb(140, 217, 140)" );
									}else if (data.Performance == "Poor") {
										$(row).find('td:eq(3)').css( "background-color", "Red" );
									}else if (data.Performance == "Good") {
										$(row).find('td:eq(3)').css( "background-color", "rgb(0, 255, 0)" );
									}
								},
					} );
//				
				}else{
					swal('error');
				}
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
})