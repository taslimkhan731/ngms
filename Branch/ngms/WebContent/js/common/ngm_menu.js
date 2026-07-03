
function menu_pup_call(menu_id,process_id){
	
	arr_fieldname =["process_id","menu_id","task"];
	arr_fieldvalue =[process_id,menu_id,"getsubmenu"];
	get_to_post("s_menu", arr_fieldname,arr_fieldvalue,handle_menu_pup_call);
}

function handle_menu_pup_call()
{
	if(http.readyState == 4)
	{
		
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
		document.getElementById("pbmenu").innerHTML=results;
		$('.small_menu').modal('show');
		}
		else
		{
			alert(results.substr(8));
		}
	}
}

function menu_url_call(url,process_id){

	var ar_url = url.split("?");
	var urlpath =ar_url[0];
	var ar_task = ar_url[1];
	var array_name=ar_task.split("=");
	task_val = array_name[1];
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =[task_val,process_id];
	post_field(urlpath, arr_fieldname, arr_fieldvalue);
}

function menu_url_call2(url,process_id,btn){ 
	

	var ar_url = url.split("?");
	var urlpath =ar_url[0];
	var ar_task = ar_url[1];
	var array_name=ar_task.split("=");
	task_val = array_name[1];
	if(btn == 1)
		var office =document.getElementById('sel_ofc').value;
	else
		var office =document.getElementById('sel_ofc2').value;
	arr_fieldname =["task","process_id","office"];
	arr_fieldvalue =[task_val,process_id,office];
	//alert(arr_fieldvalue);
	get_to_post_reload(urlpath, arr_fieldname, arr_fieldvalue);
}


