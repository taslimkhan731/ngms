function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_vehiclerequiedreport", arr_fieldname, arr_fieldvalue, 'header_div');
}
function vehiclereport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["vehiclesearch",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_vehiclerequiedreport",handleallot, arr_fieldname,arr_fieldvalue,'list');
	}
}
function handleallot(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			}else{
			nic_alert(results.substr(8),"N");
		}
	}
}
function govehiclestatus(page_no){
	var from  = document.getElementById('date').value;
	var status  = document.getElementById('hidvalue').value;
	var sel_block  = document.getElementById('sel_block').value;
	var gh_id  = document.getElementById('sel_id').value;
	console.log(sel_block)
	console.log(gh_id)
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else if(from==""){
		nic_alert("Please Select the date","N");
		return false;
	} else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	} else{
		arr_fieldname=["task","process_id","from","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["doday",getprocessid(),from,gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handlegetavailability, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handlegetavailability(){
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


function todays(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var status  = document.getElementById('hidvalue').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	}else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["dotoday",getprocessid(),gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handletoday, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handletoday(){
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


function yesterdays(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var status  = document.getElementById('hidvalue').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	}else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["doyesterday",getprocessid(),gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handleyesterday, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handleyesterday(){
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


function tomorrows(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var status  = document.getElementById('hidvalue').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	}else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["dotomorrow",getprocessid(),gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handletomorrow, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handletomorrow(){
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

function pdfdata(date,gh_id,sel_block){
	var status  = document.getElementById('hidvalue').value;
	arr_fieldname =["process_id","task","date","status","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"vehiclepdf",date,status,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}


function VAL_submit(){
	 
	var from  = document.getElementById('date').value;
	if((val_from(from)))
		return true;
}

function val_from(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for year.","V");
			return false;
		}
		
	}
	else
	{
		nic_alert("Please enter the Year","V");
		return false;
	}
	return true;
}