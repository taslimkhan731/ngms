$(document).ready(function(){

	
	
	getDistrictName();
	
	drawTable(null, null);
	
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
			if (distName=='ALL') {
				$('#block_name').children('option:not(:first)').remove();
				$('#cluster_name').children('option:not(:first)').remove();
				drawTable(null, null);
			} else {
				drawTable(distName, "district_name");
				getBlockName(distName);
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
				drawTable(distName, "district_name");
			} else {
				drawTable(blockName, "block_name");
				getClusterName(blockName,distName);
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
				drawTable(blockName, "block_name");
			} else {
				drawTable(clusterName, "cluster_name");
			}
		})
	}
	function drawTable(name, type){
		//swal("called");
		$.ajax({
			url:'./dashboard/detailsAcrossQuestion.do',
			type: 'POST',
			async: false,
			data:{name:name, type:type},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				var data=result.data;
				console.log("==========questionCrossTab==============");
				console.log(result.data);
				console.log("==========questionCrossTab==============");
				if(result.success){
					if (name==null&&type==null) {
					name="ANGUL";
					type="District";
				}
				if (type=='district_name') {
					type='District';
				}else if (type=='block_name') {
					type='Block';
				}else if (type=='cluster_name') {
					type='Cluster';
				}
					console.log(result);
					/*table.clear().draw();
					table.rows.add(data); // Add new data
				    table.columns.adjust().draw(); */// Redraw the DataTable
//					table.clear();
//					table.rows.add(data).draw();
					var table=$('#questionCrossTab').DataTable( {
						data: data,
						dom: 'Bfrtip',
						buttons: [
							{
								extend: 'excelHtml5',
								className:"BUTTON_DPX",
								customize: function (xlsx) {
								    var sheet = xlsx.xl.worksheets['sheet1.xml'];
								    var col = $('col', sheet);
								    col.each(function () {
								          $(this).attr('width', 100);
								   });
								},
								title: 'Details Accross Question of'+' '+name+' '+type
							},
							{
								extend: 'print',
								className:"BUTTON_DPX",
								title: 'Details Accross Question of'+' '+name+' '+type
							},
							],
							//"scrollY": true,
							//"scrollY":"800px",
							"scrollX":true,
							"paging":true,
							"bFilter": true,
				            "bAutoWidth": false,
				            "bProcessing" : false, 
				            "destroy":true,
							columns: [
								{ "data": "district_name" },
								{ "data": "block_name" },
								{ "data": "cluster_name" },
								{ "data": "school_name" },
								{ "data": "inspector_name" },
								{ "data": "group_name" },
								{ "data": "group_type" },
								{ "data": "inspection_allocated_on" },
								{ "data": "inspection_dueby" },
								{ "data": "inspection_started_on" },
								{ "data": "inspection_ended_on" },
								{ "data": "q1" },
								{ "data": "q2" },
								{ "data": "q3" },
								{ "data": "q4" },
								{ "data": "q5" },
								{ "data": "q6" },
								{ "data": "q7" },
								{ "data": "q8" },
								{ "data": "q9" },
								{ "data": "q10" },
								{ "data": "q11" },
								{ "data": "q12" },
								{ "data": "q13" },
								{ "data": "q14" },
								{ "data": "q15" },
								{ "data": "q16" }
								], 
								"columnDefs": [
								    { "width": "100px", "targets": 11 },
								    { "width": "20%", "targets": 4 },
								    { "width": "20%", "targets": 5 }
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