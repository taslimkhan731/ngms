
	
	// Function for Minimise and maximise the DIV contents
		$(document).on('click', '[data-widget="collapse"]', function () {

	        //Find the box parent        
	        var box = $(this).parents(".panel").first();
	        //Find the body and the footer
	        var bf = box.find(".panel-body, .panel-footer");
	        if (!box.hasClass("collapsed-box")) {
	            box.addClass("collapsed-box");
	            //Convert minus into plus
	            $(this).children(".fa-minus").removeClass("fa-minus").addClass("fa-plus");
	            bf.slideUp();
	        } else {
	            box.removeClass("collapsed-box");
	            //Convert plus into minus
	            $(this).children(".fa-plus").removeClass("fa-plus").addClass("fa-minus");
	            bf.slideDown();
	        }
	    });


//search view

function view()
{
	var gh_id = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select the guest house","N");
	}else{
		
			arr_fieldname =["task","process_id","gh_id"];
			arr_fieldvalue =["searchview",getprocessid(),gh_id];
			ajax_post_field_div("s_view",handle_searchview,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_searchview()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function viewConfirmed()
{
	var gh_id = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select the guest house","N");
	}else{
		
			arr_fieldname =["task","process_id","gh_id"];
			arr_fieldvalue =["searchviewConfirmed",getprocessid(),gh_id];
			ajax_post_field_div("s_view",handle_searchview,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function search_process_confirmed(page_no){

	
	var req_id=  document.getElementById('req_id').value;
	
	var designation =  document.getElementById('designation').value;
	
	var gh_id = document.getElementById('sel_id').value;

	var name =  document.getElementById('name').value;
	
	var date = document.getElementById('date').value;
	
	if(req_id==""&&designation==""&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_process(req_id,name,date)){			
		
		arr_fieldname=["task","process_id","req_id","designation","gh_id","name","date","page_no"];
		arr_fieldvalue=["getsearchconfirmed",getprocessid(),req_id,designation,gh_id,name,date,page_no];
		
		ajax_post_field_divH("s_view", arr_fieldname,arr_fieldvalue,'search');
	}}

}
function search_process(page_no){
	
	var req_id=  document.getElementById('req_id').value;
	
	var designation =  document.getElementById('designation').value;
	
	var gh_id = document.getElementById('sel_id').value;

	var name =  document.getElementById('name').value;
	
	var date = document.getElementById('date').value;
	
	if(req_id==""&&designation==""&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_process(req_id,name,date)){
			
			//--------ATHIRA EDITED------
			
//			if(!rno==""){
//				if(!isLEN(rno,1,5)){
//					 
//					nic_alert("Invalid Length for Room Number.","V");
//					return false;
//				}
//				else if(!VAL_N(rno)){
//					 
//					nic_alert("Invalid Room Number.","V");
//					return false;
//				}
//			}
			
		arr_fieldname=["task","process_id","req_id","designation","gh_id","name","date","page_no"];
		arr_fieldvalue=["getsearch",getprocessid(),req_id,designation,gh_id,name,date,page_no];
		
		ajax_post_field_divH("s_view", arr_fieldname,arr_fieldvalue,'search');
	}}
}


function val_search_process(req_id,name,date){

	if(!req_id==""){
		if(!isLEN(req_id,1,25)){
			 
			nic_alert("Invalid Length for Request ID.","V");
			return false;
		}
		else if(!VAL_ANHYP(req_id)){
			 
			nic_alert("Invalid Request ID.","V");
			return false;
		}
	}
	if(name!=""){
	if(!isLEN(name,1,50)){
		 
		nic_alert("Invalid Length for Name.","V");
		return false;
	}
	else if(!VAL_NAME(name)){
		 
		nic_alert("Invalid Name.","V");
		return false;
	}
	}
	if(date!=""){
	if(!isLEN(date,1,25)){
		 
		nic_alert("Invalid Length for Date.","V");
		return false;
	}
	else if(!VAL_NC(date)){ 
		 
		nic_alert("Invalid Date.","V");
		return false;
	}
	}
	
	return true;
}
/*
function view_all_requestid(req_id,gh_id){
	arr_fieldname=["task","process_id","req_id","gh_id"];
	arr_fieldvalue=["generalViewReqGh",getprocessid(),req_id,gh_id];
	//ajax_post_field_divH("s_general_view", arr_fieldname,arr_fieldvalue,'search');
	ajax_post_field("s_general_view",handle_view_all_requestid,arr_fieldname,arr_fieldvalue);	
}

function handle_view_all_requestid()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}*/
