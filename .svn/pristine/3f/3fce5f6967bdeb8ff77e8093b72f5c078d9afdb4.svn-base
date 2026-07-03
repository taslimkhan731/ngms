
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_blockstatus", arr_fieldname, arr_fieldvalue, 'header_div');
}
function blocklist(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["roomsearch",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_blockstatus",handleblocklist, arr_fieldname,arr_fieldvalue,'list');
	}
}
function handleblocklist(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			}else{
			nic_alert(results.substr(8),"N");
		}
	}
}
function goblockstatus(){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(VAL_submit())
	{
		arr_fieldname=["task","process_id","from","gh_id","sel_block"];
		arr_fieldvalue=["doprocess",getprocessid(),from,gh_id,sel_block];//allotprocessid
		ajax_post_field_div("s_blockstatus",handlegoblockstatus, arr_fieldname,arr_fieldvalue,'listlist');
	}
}
function handlegoblockstatus(){
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
