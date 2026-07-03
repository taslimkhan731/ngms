
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_finalreport", arr_fieldname, arr_fieldvalue, 'header_div');
}
function report_list(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["list",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_finalreport",handle_reportList,arr_fieldname,arr_fieldvalue,'report');	
	}
}

function handle_reportList()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function VAL_submit(){
	 
	var from  = document.getElementById('date').value;
	if((val_fromdate(from)))
		return true;
}
function goreportstatus(){
	var from  = document.getElementById('date').value;
	var purpose  = document.getElementById('txt_pur').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(VAL_submit())
	{
		arr_fieldname=["task","process_id","from","gh_id","sel_block","purpose"];
		arr_fieldvalue=["doprocessavailabilty",getprocessid(),from,gh_id,sel_block,purpose];
		ajax_post_field_div("s_finalreport",handlegetavailability, arr_fieldname,arr_fieldvalue,'reportlist');
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

function reportpdf(gh_id,from,block,purpose)
{
	arr_fieldname =["task","process_id","gh_id","from","block","purpose"];
	arr_fieldvalue =["reportpdf",getprocessid(),gh_id,from,block,purpose];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}
