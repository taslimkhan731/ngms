$('document')
		.ready(
				function() {
					$("#closeShift").hide();
					$("#addShift").show();
					$("#changeShift").hide();

					$("#fromDt, #toDt").datetimepicker({
						minDate : new Date(),
						format : 'DD-MM-YYYY'
					});

					$("#fromTm, #toTm").datetimepicker({
						format : 'hh:mm A'
					});

					var x2 = [ {
						"name" : "orgadmin"
					}, {
						"name" : "delbhashift1"
					}, {
						"name" : "delbhashift2"
					} /*
						 * ,{ "name" : "delbhashift3" },{ "name" :
						 * "delbhashift4" },{ "name" : "delbhashift5" },{ "name" :
						 * "delbhashift6" },{ "name" : "delbhashift7" },{ "name" :
						 * "delbhashift8" },{ "name" : "delbhashift9" },{ "name" :
						 * "delbhashift10" },{ "name" : "delbhashift11" },{
						 * "name" : "delbhashift12" },{ "name" : "delbhashift13" }
						 */
					]

					
					$('#change').on('click', function() {
						$("#closeShift").hide();
						$("#addShift").hide();
						$("#changeShift").show();
					})
					$('#close').on('click', function() {
						$("#addShift").hide();
						$("#changeShift").hide();
						$("#closeShift").show();
					})
					$('#add').on('click', function() {
						$("#addShift").show();
						$("#changeShift").hide();
						$("#closeShift").hide();
					})
				});