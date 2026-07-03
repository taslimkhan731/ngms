function showv(pro_id){
/*	alert(pro_id);*/
	 
	if(pro_id!=0){
		arr_fieldname =["task","process_id","pro_id"];
		arr_fieldvalue =["showformview",getprocessid(),pro_id];
		alert(arr_fieldvalue);
		ajax_post_field("s_report",handle_show_formd,arr_fieldname,arr_fieldvalue);	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}
function handle_show_formd()
{/*alert("handle");*/
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


	