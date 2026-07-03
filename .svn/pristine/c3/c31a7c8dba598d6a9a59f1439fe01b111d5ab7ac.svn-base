
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_dailyroomstatus", arr_fieldname, arr_fieldvalue, 'header_div');
}
function dailyroomstatus(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["blocksearch",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_dailyroomstatus",handledailyroomstatus, arr_fieldname,arr_fieldvalue,'list');
	}
}
function handledailyroomstatus(){
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
	var block =  document.getElementById('sel_block').value;
	
	if(VAL_submit())
	{
		arr_fieldname=["task","process_id","from","gh_id","block","sel_block"];
		arr_fieldvalue=["doprocess",getprocessid(),from,gh_id,block,sel_block];//allotprocessid
		ajax_post_field_div("s_dailyroomstatus",handlegoblockstatus, arr_fieldname,arr_fieldvalue,'listlist');
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

function pdfdata(date,gh_id,block_id){
	
	arr_fieldname =["process_id","task","date","gh_id","block_id"];
	arr_fieldvalue =[getprocessid(),"dailyroomstatuspdf",date,gh_id,block_id];
 	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}
function VAL_submit(){
	 
	var from_date  = document.getElementById('date').value;
	if(val_fromdate(from_date))
		return true;
}
