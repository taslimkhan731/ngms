


$(function() {

	$(document).ready(function() {

		$('.showTooltip').tooltip();

		/* $('.date').datetimepicker(); */

		$(document).on('mousemove', '.addCalendar', function() {
			$('.calendar').datetimepicker({

				// For start Date is today
				/*
				 * formatTime:'h:mm a', formatDate:'d/m/Y',
				 */
				
				step : 5,
				format : 'd-m-Y H:i'
			/*
			 * allowTimes:[ '12:00', '13:00', '15:00', '17:00', '17:05',
			 * '17:20', '19:00', '20:00' ]
			 */

			});

			$('.content').on('click', 'input:checkbox', function() {
				$(this).parent().toggleClass('text-black', this.checked);

			});

		});
		
		
		$(document).on('mousemove', '.addBookingCalendar', function() {
			$('.calendars').datetimepicker({

				// For start Date is today
				/*
				 * formatTime:'h:mm a', formatDate:'d/m/Y',
				 */
				minDate:0,
				step : 5,
				format : 'd-m-Y H:i'
			/*
			 * allowTimes:[ '12:00', '13:00', '15:00', '17:00', '17:05',
			 * '17:20', '19:00', '20:00' ]
			 */

			});
		});
		
		
		$(document).on('mousemove', '.needCalendar', function() {
			$('.calendarOnly').datetimepicker({			
				timepicker:false,
				format:'d-m-Y'
				  
					  
				 
	        	  // For start Date is today 
	        	  /*formatTime:'h:mm a',
	        	  formatDate:'d/m/Y',*/
	        	  
			});
		});
		
		
		 $(document).on('mousemove', '.addTime', function () {
	          $('.time').datetimepicker({
	        	  
	        	  minDate:0,
	        	  maxDate:0,
	        	  // For start Date is today 
	        	  /*formatTime:'h:mm a',
	        	  formatDate:'d/m/Y',*/
	        	  step:5,
	        	  format:'d-m-Y H:i'
	        	/*  allowTimes:[
	        	              '12:00', '13:00', '15:00', 
	        	              '17:00', '17:05', '17:20', '19:00', '20:00'
	        	             ]*/
	        	  
	        	  
	        	  
	          });
		 }); 

		$(document).on('mousemove', '.table', function() {
			$('.showTooltip').tooltip();

		});

		$(document).on('mousemove', '.box', function() {
			$('.showTooltip').tooltip();

		});
		
		
		
	

	});
	
	 

});

function handlecreatefile() {
	if (http.readyState == 4) {
		results = http.responseText;
		if (results.substr(0, 7) != "Message") {
			// alert(results);
			var n = results.split("-");
			alert(n[0]);
			alert(n[1]);
			alert(n[2]);
			arr_fieldname = [ "task", "process_id", "fileid" ];
			arr_fieldvalue = [ "abc", n[2], n[1] ];
			get_to_post_reload("s_filemanager", arr_fieldname, arr_fieldvalue);

		} else {
			nic_alert(results.substr(8), "N");
		}
	}
}

function handledeptofc() {
	if (http.readyState == 4) {
		results = http.responseText;
		if (results.substr(0, 7) != "Message") {

			document.getElementById(resultDiv).innerHTML = results;

		} else {
			nic_alert(results.substr(8), "N");
		}
	}
}

function goto_form(process_id, pageurl) {
	alert("hai");
	var ar_url = pageurl.split("?");
	var urlpath = ar_url[0];
	var ar_task = ar_url[1];
	var array_name = ar_task.split("=");
	task_val = array_name[1];
	alert(urlpath);
	arr_fieldname = [ "task", "process_id" ];// file_id
	arr_fieldvalue = [ task_val, process_id ];
	alert(urlpath);
	get_to_post_reload(urlpath, arr_fieldname, arr_fieldvalue);
}

function logout(p_process_id) {
	// var path='logout?task=abc&um=GET&process_id='+escape(p_process_id);

	var path = "logout";
	var array_name = new Array();
	array_name[0] = "task";
	array_name[1] = "um";
	array_name[2] = "process_id";
	var array_value = new Array();
	array_value[0] = "abc";
	array_value[1] = "GET";
	array_value[2] = p_process_id;
	// post_to_url(path, "POST");
	post_field(path, array_name, array_value);
}

function test_pup_call(menu_id, process_id, file_id) {

	arr_fieldname = [ "process_id", "menu_id", "task", "file_id" ];
	arr_fieldvalue = [ process_id, menu_id, "getsubmenu", file_id ];
	get_to_post("s_menu", arr_fieldname, arr_fieldvalue,
			handle_test_menu_pup_call);
}

function handle_test_menu_pup_call() {
	if (http.readyState == 4) {

		results = http.responseText;
		if (results.substr(0, 7) != "Message") {
			document.getElementById("pbmenu").innerHTML = results;
			$('.small_menu').modal('show');
		} else {
			alert(results.substr(8));
		}
	}
}