$('document').ready(function() {
	var x = [ {
		"allotee" : "Amrita",
		"suitNo" : "1001",
		"period": "2",
		"roomRent": "2000"
	},{
		"allotee" : "Rahul",
		"suitNo" : "1002",
		"period": "3",
		"roomRent": "2000"
	},{
		"allotee" : "Riya",
		"suitNo" : "1003",
		"period": "4",
		"roomRent": "2000"
	} ]
	$("#fromDt, #toDt").datetimepicker({
		minDate : new Date(),
		format : 'DD-MM-YYYY'
	});
	$("#allotmentList").hide();
	$("#go").on("click", function(){
		var date = document.getElementById("fromDt").value;
		var list = document.getElementById("list").value;
		if(date == "" && list == "0") {
			alert("Please select date and type");
		} else {
			$("#allotmentList").show();			
		}
	});
	
	var table = null;
	table = $("#allotmentDataTable")
	.DataTable(
			{
				
				"lengthMenu" : [ [ 10, 20, 30, -1 ],
					[ 10, 20, 30, "All" ] ],
					"processing" : false,
					"ordering" : true,
					"info" : false,
					"bFilter" : true,
//					"scrollX": true,
					"paging" : true,
					"searching" : true,
					"fixedHeader" : {
						header : true,
						footer : true
					},
					"scrollCollapse" : true,
					"columnDefs" : [
						{
							"targets" : 0,
							"data" : null,
							render : function(data,
									type, row, meta) {
								return meta.row
								+ meta.settings._iDisplayStart
								+ 1;
							}
						}, {
							"targets" : 1,
							"data" : "allotee"
						}, {
							"targets" : 2,
							"data" : "suitNo"
						},{
							"targets" : 3,
							"data" : "period"
						}, {
							"targets" : 4,
							"data" : "roomRent"
						}],
			});
	table.clear();
	table.rows.add(x).draw();
})