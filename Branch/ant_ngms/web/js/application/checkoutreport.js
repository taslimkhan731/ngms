
function checkoutreport(){
	var gh_id  = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select from the list","N");
		return false;
	} 
	arr_fieldname=["task","process_id","gh_id"];
	arr_fieldvalue=["checkoutsearch",getprocessid(),gh_id];
	ajax_post_field_div("s_checkoutreport",handleallot, arr_fieldname,arr_fieldvalue,'list');
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
function go(){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	if(from==0){
		nic_alert("Please Select from date","N");
		return false;
	} 
	arr_fieldname=["task","process_id","from","gh_id"];
	arr_fieldvalue=["dostatus",getprocessid(),from,gh_id];//allotprocessid
	ajax_post_field_div("s_checkoutreport",handlegetavailability, arr_fieldname,arr_fieldvalue,'listlist');
		//}
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


function view_pdf(date){
	arr_fieldname =["process_id","task","date"];
	arr_fieldvalue =[getprocessid(),"checkoutpdf",date];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}
//
//function VAL_submit(){
//	 
//	var from  = document.getElementById('date').value;
//	if((val_from(from)))
//		return true;
//}
//
//function val_from(p_val){
//	if(p_val!="")
//	{
//		if(!isLEN(p_val,1,4)){
//			nic_alert("Invalid Length for year.","V");
//			return false;
//		}
//		else if(!VAL_N(p_val)){
//			nic_alert("Invalid Year, only allow Numeric.","V");
//			return false;
//		}
//	}
//	else
//	{
//		nic_alert("Please enter the Year","V");
//		return false;
//	}
//	return true;
//}