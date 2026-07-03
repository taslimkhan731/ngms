
function show_form_publicrequest(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["list",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_divH("s_accept_pubrequest", arr_fieldname,arr_fieldvalue,'listdiv');
	}
	 
}
function acceptdata(id){
	if(confirm("Do you want to accept?")){
	arr_fieldname=["task","process_id","req_id"];
	arr_fieldvalue=["acceptdata",getprocessid(),id];
	$('#popup').show();
	ajax_post_field("s_accept_pubrequest",handle_updateaccept,arr_fieldname,arr_fieldvalue);	
	}
}
function handle_view_form_dataentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
	 
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function viewdata(req_id){
	 
	//VAL_showform(gh_id,pid);
	 var gh_id= document.getElementById("gh_id").value;
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showdata",getprocessid(),gh_id,req_id];
		$('#popup').show();
		ajax_post_field_div("s_accept_pubrequest",handle_view_form_dataentry,arr_fieldname,arr_fieldvalue,'viewdata');	
	 
	 
}
function handle_updateaccept(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			
		 
		document.getElementById(results).innerHTML="";  
		$('#popup').hide();
			 
		}else{
			nic_alert(results.substr(8),"N");
			show_form_publicrequest('1');
			$('#popup').hide();
		}
	}
}

function searchdataa(){

	var   req_id=  document.getElementById('req_id').value;
	var userid =  document.getElementById('userid').value;
	var gh_id = document.getElementById('gh_id').value;
	if(req_id=="" && userid==""){
		nic_alert("Please Enter data for searching","N");
	}else{
	arr_fieldname=["task","process_id","gh_id","req_id","userid"];
	arr_fieldvalue=["search",getprocessid(),gh_id,req_id,userid];
	$('#popup').show();
	ajax_post_field_divH("s_accept_pubrequest",arr_fieldname,arr_fieldvalue,'search');	
	}
}