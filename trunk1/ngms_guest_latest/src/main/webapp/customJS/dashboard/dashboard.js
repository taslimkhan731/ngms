$(document).ready(function(){

	var userDetails;
	var table1;

	var year;
	var name2nd;
	var id2nd;

	getDistrictName();
	getFinancialYear();
	getScheme();

	sectionByCatagoryBar(null, 0, null, 0, 0);

	table(null, 0, null, 0, 0);
	ticket(null, 0, null, 0, 0);
	noOfWorksSanctioned(null, 0, null, 0, 0);
	amountSanctionedPie(null, 0, null, 0, 0);

	function noOfWorksSanctioned(name, id, name1, id1, yearId){
		$.ajax({
			url:'./dashboard/noOfWorksSanctioned.do',
			type: 'POST',
			async: false,
			data:{name:name, id:id, name1:name1, id1:id1, yearId:yearId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				$('#work_no').text(result.data[0]);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function amountSanctionedPie(name, id, name1, id1, yearId){
		$.ajax({
			url:'./dashboard/amountSanctionedPie.do',
			type: 'POST',
			async: false,
			data:{name:name, id:id, name1:name1, id1:id1, yearId:yearId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				if (result.data.length<2) {
					if(result.data.length==0){
						var nbuilding=[{'value':0},{'value':0}];
						var building=[{'value':0},{'value':0}];
					}else if (result.data[0].catg_desc=='Building') {
						console.log(result.data[0].catg_desc);
						var nbuilding=[{'value':0},{'value':0}];
						var building=[{'value':result.data[0].sanc_amt},{'value':result.data[0].amount_released}];
					}else if (result.data[0].catg_desc=='Non Building') {
						var building=[{'value':0},{'value':0}];
						var nbuilding=[{'value':result.data[0].sanc_amt},{'value':result.data[0].amount_released}];
					}
				}else if(result.data.length==0){
					var nbuilding=[{'value':0},{'value':0}];
					var building=[{'value':0},{'value':0}];
				} else {
					var building=[{'value':result.data[0].sanc_amt},{'value':result.data[0].amount_released}];
					var nbuilding=[{'value':result.data[1].sanc_amt},{'value':result.data[1].amount_released}];
				}
				console.log("==================amountSanctionedPie==================");
				console.log(result);
				drawBarchart(building, nbuilding, "bar_area_2nd", "Amount By Category", 'Amount', 'Amount Sanctioned', 'Amount Approved', "In Lakhs");
				console.log(building);
				console.log(nbuilding);
				console.log("==================amountSanctionedPie==================");
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function ticket(name, id, name1, id1, yearId){
		$.ajax({
			url:'./dashboard/ticket.do',
			type: 'POST',
			async: false,
			data:{name:name, id:id, name1:name1, id1:id1, yearId:yearId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				$('#sanc_amount').text(result.data[0]);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function table(name, id, name1, id1, yearId){
		var i=0;
		if(name==null||name=='ALL'){
			name2nd=name1;
			id2nd=id1;
		}
		if(name1==null||name1=='ALL'){
			name2nd=name;
			id2nd=id;
		}
		year=yearId;
		$.ajax({
			url:'./dashboard/table.do',
			type: 'POST',
			async: false,
			data:{name:name, id:id, name1:name1, id1:id1, yearId:yearId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				table1=$('#table1').DataTable( {
					data: result.data,
					dom: 'Bfrtip',
					buttons: [						
						{
							extend: 'excelHtml5',
							className:"BUTTON_DPX",
							title: 'Overall Status Of Scholls',
							action: function ( e, dt, node, config ) {
								i=0;
								$.fn.dataTable.ext.buttons.excelHtml5.action.call(this, e, dt, node, config);
							},
							 footer: true,
						},
						{
							extend: 'pdfHtml5',
							className:"BUTTON_DPX",
							title: 'Overall Status Of Scholls',
							action: function ( e, dt, node, config ) {
								i=0;
								$.fn.dataTable.ext.buttons.pdfHtml5.action.call(this, e, dt, node, config);
							},
							 footer: true,
						},
						],
						"scrollCollapse": true,
						"paging":true,
						"destroy": true,
						"scrollX":true,
						columns: [
							{ "data": null ,
								"render": function ( data, type, row ) {
									return ++i;
								} },
								{ "data": "catg_desc" },
								{ "data": "work_desc" },
								{ "data": "school_no" },
								{ "data": "no_wrksanc" },
								{ "data": "allocated_units"},
								{ "data": "sanc_amt" },
								{ "data": "sanc_amt_b"}
								],
								"columnDefs":[
									{"className": "dt-center", "targets": [0,3,6,7]},
									{"className": "r-click", "targets": [4,5]},
									],
									"footerCallback": function ( row, data, start, end, display ) {
							            var api = this.api(), data;
							 
							            // Remove the formatting to get integer data for summation
							            var intVal = function ( i ) {
							                return typeof i === 'string' ?
							                    i.replace(/[\$,]/g, '')*1 :
							                    typeof i === 'number' ?
							                        i : 0;
							            };
							 
							            // Total over all pages
							            total3 = api
							                .column( 3 )
							                .data()
							                .reduce( function (a, b) {
							                    return intVal(a) + intVal(b);
							                }, 0 );
							            
							            total4 = api
						                .column(4)
						                .data()
						                .reduce( function (a, b) {
						                    return intVal(a) + intVal(b);
						                }, 0 );
							            
							            total5 = api
						                .column( 5 )
						                .data()
						                .reduce( function (a, b) {
						                    return intVal(a) + intVal(b);
						                }, 0 );
							            
							            total6 = api
						                .column( 6 )
						                .data()
						                .reduce( function (a, b) {
						                    return intVal(a) + intVal(b);
						                }, 0 );
							            
							            total7 = api
						                .column( 7 )
						                .data()
						                .reduce( function (a, b) {
						                    return intVal(a) + intVal(b);
						                }, 0 );
							            
							            console.log(api.column(4).data());
							            
							           /* // Total over this page
							            pageTotal = api
							                .column( 4, { page: 'current'} )
							                .data()
							                .reduce( function (a, b) {
							                    return intVal(a) + intVal(b);
							                }, 0 );*/
							            /*$( api.column( 0 ).footer() ).html(
							            		'<th colspan="3" >Total:</th>'							            		
							                );*/
							            
							            // Update footer
							            $( api.column( 3 ).footer() ).html(
							                    //'$'+pageTotal +' ( $'+ total +' total)'
							            		total3
							                );
							            $( api.column( 4 ).footer() ).html(
							                    //'$'+pageTotal +' ( $'+ total +' total)'
							            		total4
							                );
							            $( api.column(5 ).footer() ).html(
							                    //'$'+pageTotal +' ( $'+ total +' total)'
							            		total5
							                );
							            $( api.column(6 ).footer() ).html(
							                    //'$'+pageTotal +' ( $'+ total +' total)'
							            		total6.toFixed(2)
							                );
							            $( api.column( 7 ).footer() ).html(
							                    //'$'+pageTotal +' ( $'+ total +' total)'
							            		total7.toFixed(2)
							                );
							        },
				} );
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function sectionByCatagoryBar(name, id, name1, id1, yearId){
		console.log(name1);
		console.log(id1);
		$.ajax({
			url:'./dashboard/sectionByCatagoryBar.do',
			type: 'POST',
			async: false,
			data:{name:name, id:id, name1:name1, id1:id1, yearId:yearId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){

				console.log(result);
				if (result.data.length<2) {
					if(result.data.length==0){
						var nbuilding=[{'value':0},{'value':0}];
						var building=[{'value':0},{'value':0}];
					}else
						if (result.data[0].catg_desc=='Building') {
							console.log(result.data[0].catg_desc);
							var nbuilding=[{'value':0},{'value':0}];
							var building=[{'value':result.data[0].no_wrksanc},{'value':result.data[0].allocated_units}];
						}else if (result.data[0].catg_desc=='Non Building') {
							var building=[{'value':0},{'value':0}];
							var nbuilding=[{'value':result.data[0].no_wrksanc},{'value':result.data[0].allocated_units}];
						}
				}  else {
					var building=[{'value':result.data[0].no_wrksanc},{'value':result.data[0].allocated_units}];
					var nbuilding=[{'value':result.data[1].no_wrksanc},{'value':result.data[1].allocated_units}];
				}
				console.log('================sectionByCatagoryBar================');
				console.log(result);
				console.log(building);
				console.log(nbuilding);
				console.log('================sectionByCatagoryBar================');
				drawBarchart(building, nbuilding, "bar_area", "No. Of Work By Category", 'No of Work', 'Work Sanctioned', 'Work Approved',"");
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getFinancialYear(){
		$.ajax({
			url:'./dashboard/getFinancialYear.do',
			type: 'POST',
			async: false,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){

				console.log(result);
				getFinancialYearDropDown(result.data, null, 0);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getScheme(){
		$.ajax({
			url:'./dashboard/getScheme.do',
			type: 'POST',
			async: false,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){

				console.log(result);
				getSchemeDropDown(result.data);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getSchemeDropDown(data){
		//console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i].scheme_id+ '">' + data[i].scheme_desc+ '</option>';
		}
		$('#scheme_dropdown').html("");
		$('#scheme_dropdown').append(option);
		$('#scheme_dropdown').off().on('change', function(){
			$('#loading').show();
			var scheme = $(this).find(":selected").val();
			var text = $(this).find(":selected").text();
			if (scheme=='ALL') {
				$("#scheme_text").html("ALL");
			} else {
				$("#scheme_text").html(text);
			}
			$('#loading').fadeOut(2000);
		})
	}

	/*function getFinancialYearDropDown(data){
		//console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i].year_id+ '">' + data[i].year_desc+ '</option>';
		}
		$('#appr_year').html("");
		$('#appr_year').append(option);
		$('#appr_year').off().on('change', function(){
			var year = $(this).find(":selected").val();
			if (year=='ALL') {
			} else {
			}
		})
	}*/


	function getFinancialYearDropDown(data, name, id){
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i].year_id+ '">' + data[i].year_desc+ '</option>';
		}
		$('#appr_year').html("");
		$('#appr_year').append(option);
		$('#appr_year').off().on('change', function(){
			$('#loading').show();
			var year = $(this).find(":selected").val();
			var yearText = $(this).find(":selected").text();
			var distId=$("#district_name").val();
			var blockId=$("#block_name").val();
			if (distId=='ALL') {
				distId=0;
			}
			if (blockId=='ALL') {
				blockId=0;		
			}
			var name1=null;
			var id1=0;
			console.log(id1);
			if (blockId==0||blockId=='ALL') {
				console.log("in block if");
				if (distId==0||distId=='ALL') {
					console.log("in dist if");
				} else {
					id1=distId;
					name1="district_id";
				}
			} else {
				id1=blockId;
				name1="block_id";
			}
			console.log("===================names====================");
			console.log(name1);
			console.log(id1);
			if(id1==null&&name1!=null){
				id1=0;
				name1=null;
			}
			if (year=="ALL") {
				sectionByCatagoryBar( null, 0, name1, id1, 0);
				table(null, 0, name1, id1, 0);
				ticket(null, 0, name1, id1, 0);
				noOfWorksSanctioned(null, 0, name1, id1, 0);
				amountSanctionedPie(null, 0, name1, id1, 0);
				
				$('#year_text').html('All');
			} else {
				sectionByCatagoryBar( null, 0, name1, id1, year);
				table(null, 0, name1, id1, year);
				ticket(null, 0, name1, id1, year);
				noOfWorksSanctioned(null, 0, name1, id1, year);
				amountSanctionedPie(null, 0, name1, id1, year);
				
				$('#year_text').html(yearText);
			}
			$('#loading').fadeOut(2000);
		})
	}


	function getDistrictName(){
		$.ajax({
			url:'./dashboard/getDistrictNameId.do',
			type: 'POST',
			async: false,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				userDetails=result.user_detais;
				console.log('=====================getDistrictName=====================');
				console.log(result);
				console.log('=====================getDistrictName=====================');
				setDataToDropDown(result.data, '#district_name');
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function getBlockName(districtId){
		$.ajax({
			url:'./dashboard/getBlockNameId.do',
			type: 'POST',
			async: false,
			data:{districtId:districtId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				setDataToDropDownBlock(result.data, '#block_name', districtId);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	function schoolName(blockId){
		$.ajax({
			url:'./dashboard/getSchoolNameId.do',
			type: 'POST',
			async: false,
			data:{blockId:blockId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				setDataToDropDownSchool(result.data, '#school_name', blockId);
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	/*function getClusterName(blockName,data){
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
	}*/

	function setDataToDropDown(data, id){
		//console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i].district_id+ '">' + data[i].district_name+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			$('#loading').show();
			var distId = $(this).find(":selected").val();
			var distText = $(this).find(":selected").text();
			console.log("district is "+distText);
			//alert(distId);
			var year=$('#appr_year').val();
			if (year=='ALL') {
				year=0;
			}
			$('#block_name').children('option:not(:first)').remove();
			$('#school_name').children('option:not(:first)').remove();
			console.log(year);
			if (distId=='ALL') {
				sectionByCatagoryBar(null, 0, null, 0, 0);
				table(null, 0, null, 0, 0);
				ticket(null, 0, null, 0, 0);
				noOfWorksSanctioned(null, 0, null, 0, 0);
				amountSanctionedPie(null, 0, null, 0, 0);
				
				$('#district_text').html('All');
				$('#block_text').html('All');
				$('#school_text').html('All');
			}else {				
				sectionByCatagoryBar("district_id", distId, null, 0, year);
				table("district_id", distId, null, 0, year);
				ticket("district_id", distId, null, 0, year);
				noOfWorksSanctioned("district_id", distId, null, 0, year);
				amountSanctionedPie("district_id", distId, null, 0, year);
				getBlockName(distId);
				
				$('#district_text').html(distText);
				$('#block_text').html('All');
				$('#school_text').html('All');
			}
			$('#loading').fadeOut(2000);
		})
	}

	function setDataToDropDownBlock(data, id, districtId){
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i].block_id+ '">' + data[i].block_name+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			
			$('#loading').show();

			$('#school_name').children('option:not(:first)').remove();
			
			var blockId = $(this).find(":selected").val();
			var blockText = $(this).find(":selected").text();
			
			var year=$('#appr_year').val();
			if (year=='ALL') {
				year=0;
			}
			if (blockId=='ALL') {
				sectionByCatagoryBar("district_id", districtId, null, 0, year);
				table("district_id", districtId, null, 0, year);
				ticket("district_id", districtId, null, 0, year);
				noOfWorksSanctioned("district_id", districtId, null, 0, year);
				amountSanctionedPie("district_id", districtId, null, 0, year);
				
				$('#block_text').html('All');
				$('#school_text').html('All');
			} else {
				sectionByCatagoryBar("block_id", blockId, null, 0, year);
				table("block_id", blockId, null, 0, year);
				ticket("block_id", blockId, null, 0, year);
				noOfWorksSanctioned("block_id", blockId, null, 0, year);
				amountSanctionedPie("block_id", blockId, null, 0, year);
				schoolName(blockId);
				
				$('#block_text').html(blockText);
				$('#school_text').html('All');
			}
			$('#loading').fadeOut(2000);
		})
	}

	function setDataToDropDownSchool(data, id, blockId){
		//console.log(data);
		var option = '<option value="ALL">ALL</option>';
		for(var i = 0;i<data.length;i++) {
			option += '<option value="'+ data[i].school_id+ '">' + data[i].school_name+ '</option>';
		}
		$(id).html("");
		$(id).append(option);
		$(id).off().on('change', function(){
			$('#loading').show();
			var schoolId = $(this).find(":selected").val();
			var schoolText = $(this).find(":selected").text();
			
			var year=$('#appr_year').val();
			if (year=='ALL') {
				year=0;
			}
			if (schoolId=='ALL') {
				sectionByCatagoryBar("block_id", blockId, null, 0, year);
				table("block_id", blockId, null, 0, year);
				ticket("block_id", blockId, null, 0, year);
				noOfWorksSanctioned("block_id", blockId, null, 0, year);
				amountSanctionedPie("block_id", blockId, null, 0, year);
				
				$('#school_text').html('All');
			} else {
				sectionByCatagoryBar("school_id", schoolId, null, 0, year);
				table("school_id", schoolId, null, 0, year);
				ticket("school_id", schoolId, null, 0, year);
				noOfWorksSanctioned("school_id", schoolId, null, 0, year);
				amountSanctionedPie("school_id", schoolId, null, 0, year);
				
				$('#school_text').html(schoolText);
			}
			$('#loading').fadeOut(2000);
		})
	}

	function drawBarchart(building, nbuilding, id, cap, x, s1, s2, y){
		FusionCharts.ready(function () {
			var mschart = new FusionCharts({
				type: 'mscolumn2d',
				renderAt: id,
				width: '100%',
				height: '360',
				dataFormat: 'json',
				dataSource: {
					"chart": {
						"caption": cap,
						"xAxisName": x,
						"yAxisName": y,
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
						'paletteColors' :'1ba1e2,a20025',
						"decimalSeparator": ".",
						"thousandSeparator": ","
					},
					"categories": [{
						"category": [{
							"label": s2
						}, {
							"label": s1
						}]
					}],
					"dataset": [{
						"seriesname": "Building",
						"data": building
					}, {
						"seriesname": "Non-Building",
						"data": nbuilding
					}]
				}
			});

			mschart.render(id);
		});
	}

	/*$('.dataTable').on('click', 'tbody td', function() {

		  //get textContent of the TD
		  console.log('TD cell textContent : ', this.textContent, this);

		  //get the value of the TD using the API 
		  //console.log('value by API : ', table.cell({ row: this.parentNode.rowIndex, column : this.cellIndex }).data());

		  alert('clicked');
		})*/


	function details(name, id, work_desc, catg_desc, yearId){
		var i=0;
		$.ajax({
			url:'./dashboard/details.do',
			type: 'POST',
			async: false,
			data:{name:name, id:id, work_desc:work_desc, catg_desc:catg_desc, yearId:yearId},
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){
				console.log(result);
				var table2=$('#details_table').DataTable( {
					data: result.data,
					dom: 'Bfrtip',
					buttons: [						
						{
							extend: 'excelHtml5',
							className:"BUTTON_DPX",
							title: 'Overall Status Of Scholls',
							action: function ( e, dt, node, config ) {
								i=0;
								$.fn.dataTable.ext.buttons.excelHtml5.action.call(this, e, dt, node, config);
							},
						},
						{
							extend: 'pdfHtml5',
							className:"BUTTON_DPX",
							title: 'Overall Status Of Scholls',
							action: function ( e, dt, node, config ) {
								i=0;
								$.fn.dataTable.ext.buttons.pdfHtml5.action.call(this, e, dt, node, config);
							},
						},
						],
						"scrollCollapse": true,
						"paging":true,
						"destroy": true,
						columns: [
							{ "data": null ,
								"render": function ( data, type, row ) {
									return ++i;
								} },
								{ "data": "catg_desc" },
								{ "data": "work_desc" },
								{ "data": "school_name" },
								{ "data": "no_wrksanc" },
								{ "data": "allocated_units"},
								{ "data": "sanc_amt" },
								{ "data": "sanc_amt_b"}
								],
								"columnDefs":[
									{"className": "dt-center", "targets": [0,4,5,6,7]},
									//{"className": "r-click", "targets": []},
									],
								
				} );
			},
			error: function(jqXHR, status, error){
				swal('error');
			}
		});
	}

	//$('.r-click').on('click', function() {
	//$('#table1 tbody').on( 'click', 'button[action=getReallocationReport]', function () {
	$('#table1 tbody').on( 'click', '.r-click', function () {
		//get textContent of the TD
		console.log('TD cell textContent : ', this.textContent, this);

		//get the value of the TD using the API 
		//console.log('value by API : ', table.cell({ row: this.parentNode.rowIndex, column : this.cellIndex }).data());
		console.log(table1.column().data());
		var data = table1.row( $(this).parents('tr') ).data();
		var row=table1.cell( this ).index().row;
		console.log("==============data==================");
		console.log(data);
		console.log(row);
		console.log(table1.cell(this).index());
		console.log("==============data==================");
		console.log(data.catg_desc);
		console.log(data.work_desc);
		var catg_desc=data.catg_desc;
		var work_desc=data.work_desc;
		$('#myModal').modal();
		console.log(name2nd);
		console.log(id2nd);
		console.log(year);
		details(name2nd, id2nd, work_desc, catg_desc, year);
		//alert('clicked');
	})

	console.log('=======================User Details=======================');
	console.log(userDetails);
	console.log('=======================User Details=======================');



	if(userDetails.group_name.toUpperCase()=="stc".toUpperCase()){
		$('#district_name').html("<option value="+userDetails.location_id+">"+userDetails.districtName+"</option>");
		$("#district_name").prop("disabled", true);
		getBlockName(userDetails.districtId);
	}

	var setAllToLabels = function(){
		$('#scheme_text').html('ALL');
		$('#year_text').html('ALL');
		$('#district_text').html($("#district_name").find(":selected").text());
		$('#block_text').html('ALL');
		$('#school_text').html('ALL');
	}
	
	setAllToLabels();
})