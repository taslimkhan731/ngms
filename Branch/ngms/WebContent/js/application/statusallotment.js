
function statusallotment(){
	//alert("hhhhhh");
	var gh_id  = document.getElementById('sel_id').value;
	 
	if(gh_id==0){
		nic_alert("Please Select from the list","N");
		return false;
	} 
	arr_fieldname=["task","process_id","gh_id"];
	arr_fieldvalue=["search",getprocessid(),gh_id];
	//alert(arr_fieldvalue);
	ajax_post_field_div("s_statusallotment",handleallot, arr_fieldname,arr_fieldvalue,'list');


}


function handleallot(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			// document.getElementById('popup').style.display = 'none';
		}else{
			nic_alert(results.substr(8),"N");
			 //document.getElementById('popup').style.display = 'none';
		}
	}
}
function goostatusallotment(){
	//alert(type);
	var from  = document.getElementById('fromdate').value;
	var to  = document.getElementById('todate').value;
	var gh_id  = document.getElementById('sel_id').value;
	if(VAL_submit())
	{
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id"];
	arr_fieldvalue=["doprocessavailabilty",getprocessid(),"","","","",from,to,gh_id];//allotprocessid
	//document.getElementById('popup').style.display = 'block';
	ajax_post_field_div("s_statusallotment",handlegetavailability, arr_fieldname,arr_fieldvalue,'listlist');
	}
}
function handlegetavailability(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		/*show_nicpopup(results); 
		$('.disable_room').hide();
		 $('.enable_room').show();*/
		
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}

function VAL_submit(){
	 
	var from  = document.getElementById('fromdate').value;
	var to  = document.getElementById('todate').value;
	if((val_fromdate(from)) && (val_to_date(to)))
		return true;
}
//validate to_date
function val_to_date(p_val){
	var from  = document.getElementById('fromdate').value;
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to date.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			nic_alert("Invalid Date.","V");
			return false;
		}
		else if(dateCompTwo(p_val,from))
		{
		nic_alert("invalid from date,Only allowed from date less than to date","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the to date","V");
		return false;
	}
	return true;
}