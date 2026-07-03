function arrivalList(){
	var gh_id  = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select from the list","N");
		return false;
	} 
	arr_fieldname=["task","process_id","gh_id"];
	arr_fieldvalue=["LIST",getprocessid(),gh_id];
	//alert(arr_fieldvalue);
	ajax_post_field_div("s_arrival",handlearrival, arr_fieldname,arr_fieldvalue,'list');

}

function handlearrival(){
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

function goarrivalfulllist(){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	if(VAL_submit())
	{
		arr_fieldname=["task","process_id","from","gh_id"];
		arr_fieldvalue=["doprocess",getprocessid(),from,gh_id];//allotprocessid
		ajax_post_field_div("s_arrival",handlegoarrivalfulllist, arr_fieldname,arr_fieldvalue,'listlist');
	}
}
function handlegoarrivalfulllist(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}

function VAL_submit(){
	 
	var from_date  = document.getElementById('date').value;
	if(val_fromdate(from_date))
		return true;
}