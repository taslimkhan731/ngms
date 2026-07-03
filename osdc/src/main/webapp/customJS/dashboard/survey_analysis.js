$(document).ready(function(){
	//alert("Entered");
	getDistrictName();
	surveyCategory();
	noOfSchool(null, null);
	overallScoreSA(null, null);
	getQuestionTable(null, null, null, null, null);
	getTotalSchoolCovered(null,null);
	
	function getTotalSchoolCovered(name,type){
		$.ajax({
			url:'./dashboard/getTotalSchoolCovered.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				$("#totalSchoolInspected").text(result.data[0]);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}
	
	function noOfSchool(name,type){
		$.ajax({
			url:'./dashboard/noOfSchool.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				$("#no_of_school").text(result.data[0]);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function overallScoreSA(name,type){
		$.ajax({
			url:'./dashboard/overallScoreSA.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type},
			success: function(result){
				$("#overal_score").text(result.data[0]+" %");
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
			var distName=$("#district_name").val();
			var blockName=$("#block_name").val();
			var clusterName=$("#cluster_name").val();
			var name1=null;
			var type1=null;
			if (clusterName==null||clusterName=='ALL') {
				if (blockName==null||blockName=='ALL') {
					if (distName==null||distName=='ALL') {

					} else {
						name1=distName;
						type1="dist_name";
					}
				} else {
					name1=blockName;
					type1="block_name";
				}
			} else {
				name1=clusterName;
				type1="cluster_name";
			}
			console.log("Name=        "+name1);
			console.log("type=        "+type1);
			console.log("===================names====================");
			if (category=="ALL") {
				getQuestionTable(name1, type1, null, null, null);
			} else {
				getQuestionTable(name1, type1, category, name1, type1);
			}
		})
	}

	function setDataToDropDown(data, id){
		//console.log(data);
		var option = '<option value="ALL" >ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i]+ '">' + data[i]+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			var distName = $(this).find(":selected").val();
			var category=$("#survey_category").val();
			if (category=="ALL") {
				category=null;
			}
			if (distName=='ALL') {
				$('#block_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				noOfSchool(null, null);
				overallScoreSA(null, null);
				getTotalSchoolCovered(null,null);
				getQuestionTable(null, null, null, null, category, null, null);
			} else {
				getBlockName(distName);
				noOfSchool(distName,'dist_name');
				overallScoreSA(distName,'dist_name');
				getQuestionTable(distName,'dist_name', category, null, null);
				getTotalSchoolCovered(distName,'dist_name');
				//surveyCategory(distName,'dist_name');
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
			if (category=="ALL") {
				category=null;
			}
			if (blockName=='ALL') {
				$('#cluster_name').children('option:not(:first)').remove();
				getBlockName(distName);
				noOfSchool(distName,'dist_name');
				overallScoreSA(distName,'dist_name');
				getQuestionTable(distName,'dist_name', category, null, null);
				getTotalSchoolCovered(distName,'dist_name');
			} else {
				getClusterName(blockName, "null");
				noOfSchool(blockName,'block_name');
				overallScoreSA(blockName,'block_name');
				getQuestionTable(blockName,'block_name', category, null, null);
				getTotalSchoolCovered(blockName,'block_name');
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
			if (category=="ALL") {
				category=null;
			}
			if (clusterName=='ALL') {
				getClusterName(blockName, "null");
				noOfSchool(blockName,'block_name');
				overallScoreSA(blockName,'block_name');
				getQuestionTable(blockName,'block_name', category, null, null);
				getTotalSchoolCovered(diblockName,'block_name');
			} else {
				noOfSchool(clusterName, 'cluster_name');
				overallScoreSA(clusterName, 'cluster_name');
				getQuestionTable(clusterName, 'cluster_name', category, null, null);
				getTotalSchoolCovered(clusterName, 'cluster_name');
			}
		})
	}

	function getQuestionTable(name, type, category, name1, type1){
		console.log('Data Table');
		$.ajax({
			url:'./dashboard/getQuestionTable.do',
			type: 'POST',
			async: false,
			 beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 },
			data:{name:name, type:type, category:category,name1:name1, type1:type1},
			success: function(result){
				console.log(result);
				var data=result.data;
				if(result.success){
					var table=$('#question_table').DataTable( {
						data: data,
						/* "searching": false,*/
						"scrollY": true,
						"scrollY":"50vh",
						"scrollCollapse": true,
						"searching": false,
						"sorting":false,
						"paging":false,
						"destroy": true,
						columns: [
							{ "data": "Questions" },
							{ "data": "Category" },
							{ "data": "% Schools Which Satisfy" },
							{ "data": "Performance" }
							],
							"createdRow": function( row, data, dataIndex ) {
								//$( row ).css( "background-color", "Orange" );
								console.log("==========dataIndex==========");
								console.log(dataIndex);
								console.log(data.Performance);
								console.log(row);
								console.log("==========dataIndex==========");
								if ( data.Performance == "Bad" ) {
									//$( row ).css( "background-color", "Red" );
									$(row).find('td:eq(3)').css( "background-color", "Orange" );
									//$( row ).addClass( "warning" );
								}else if (data.Performance == "Average") {
									$(row).find('td:eq(3)').css( "background-color", "rgb(140, 217, 140)" );
								}else if (data.Performance == "Poor") {
									$(row).find('td:eq(3)').css( "background-color", "Red" );
								}else if (data.Performance == "Good") {
									$(row).find('td:eq(3)').css( "background-color", "rgb(0, 255, 0)" );
								}
							},
							"columnDefs": [
							    { "width": "50%", "targets": 0 }
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
})