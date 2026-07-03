/**
 * 
 */
$(document).ready(function() {
	
/************************************************************************
 * Some demos for your reference 
 * @author 	: Chandra Vamsi
 * @date 	: 10th March 2018
 
------------This is for ajax request demo-----------

	$.ajax({
		url: "",
		type: "POST",
		data:{key:value},
		async:false,
		success:function(result) {
			
		},
		error:function(jqXHR, status, error){
			
		}
	});

------------This is for Highchart demo-----------	 

	Highcharts.chart('bar', {
		colors:['#3f51b5', '#1eb6fb', '#258039', '#f25c00', '#1e1f26'],
	    chart: {
	        type: 'column',
	    },
	    title: {
	        text: 'Building Condition',
	    },
	    subtitle: {
	        text: null
	    },
	    credits:{
		    enabled:false
		  },
		exporting: { 
		    enabled: true 
		},
	    xAxis: {
	        categories: cnd_group,
	        crosshair: false,
	    },
	    yAxis: {
	        title: {
	            text: 'Number of School Buildings',
	        }
	    },
	    legend: {
		    enabled: false
		  },
	    series: [{
	        name: 'Building',
	        data: []
	    }]
	});
	
---------------- This is the one way to use data table -----------------

	//for declaring the data tables
	
	var table = $("divId").DataTable({
		 "paging": true,
		 "pageLength" : 8,
		 "processing": true,
	     "bFilter": true,
	     "bLengthChange": false,
		 "scrollX": true,
	     "retrieve": true,
         "columnDefs":[
               {"targets":0,"data":"key_value"},
               {"targets":1,"data":"key_value"},
               {"targets":2,"data":"key_value"},
               {"targets":3,"data":"key_value"},
               {"targets":4,"data":"key_value"},
               {"targets":5,"data":"key_value"},
               {"targets":6,"data":"key_value"}
           ]
		})
	// for rendering the data into datatables
		table.clear().draw();
		table.rows.add(result).draw();
	
*******************************************************************************/
});	



