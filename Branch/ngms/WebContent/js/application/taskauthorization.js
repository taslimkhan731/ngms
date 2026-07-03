
function   update_authorizationdetails(serv_name,task_id)
{
	
	if(VAL_addauthorizations()){
	
	
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","serv_name","task_id"];
			arr_fieldvalue=["updatedetailsentry",getprocessid(),serv_name,task_id];
		  ajax_post_form_field_div(ins_form,"s_taskauthorization",handle_update_detailsentry,arr_fieldname,arr_fieldvalue,'add_auth');
	}
}
function handle_update_detailsentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			//edit_authorization('1');
		    document.getElementById(resultDiv).innerHTML="";
			edit_authorization('1');
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}




function add_authorization()
{
	
    arr_fieldname =["task","process_id"];
    arr_fieldvalue =["addauthorization",getprocessid()];
	ajax_post_field_div("s_taskauthorization",handle_addauthorization,arr_fieldname,arr_fieldvalue,'add_auth');	
	
}
function handle_addauthorization()
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


function edit_authorization(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editauthorization",getprocessid(),page_no];
	ajax_post_field_div("s_taskauthorization",handle_editauthorization,arr_fieldname,arr_fieldvalue,'add_auth');	
}
function handle_editauthorization()
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


function delete_authorization(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteauthorization",getprocessid(),page_no];
	ajax_post_field_div("s_taskauthorization",handle_deleteauthorization,arr_fieldname,arr_fieldvalue,'add_auth');	
}
function handle_deleteauthorization()
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


function edit_authorization1(serv_name,task_id,role_code)
{ 

			arr_fieldname=["task","process_id","serv_name","task_id","role_code"];
			
			arr_fieldvalue=["editauthorizationnext",getprocessid(),serv_name,task_id,role_code];
			
			ajax_post_field_div("s_taskauthorization",handle_editauthorization,arr_fieldname,arr_fieldvalue,'add_auth');
	
}
function handle_editauthorization()
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




/*function delete_authorization1(serv_name,task_id)
{  


			arr_fieldname=["task","process_id","serv_name","task_id"];
			
			arr_fieldvalue=["deleteauthorizationnext",getprocessid(),serv_name,task_id];
			
			ajax_post_field_div("s_taskauthorization",handle_deleteauthorization,arr_fieldname,arr_fieldvalue,'add_auth');
	
}

function handle_deleteauthorization()

{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
			delete_authorization('1');
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}



*/

/*
function delete_authorizationsentry(slno)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","slno"];
		arr_fieldvalue=["deleteauthorizationlist",getprocessid(),slno];
		ajax_post_field("s_taskauthorization",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleteding","V");
			deleteuserdetails();
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}*/



function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_taskauthorization",arr_fieldname,arr_fieldvalue,false);
}

function delete_authorization(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteauthorization",getprocessid(),page_no];
	ajax_post_field_div("s_taskauthorization",handle_deleteauthorization,arr_fieldname,arr_fieldvalue,'add_auth');	
}
function handle_deleteauthorization()
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

function delete_authorizationentry(user_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","user_id"];
		arr_fieldvalue=["deleteauthorizationlist",getprocessid(),user_id];
		ajax_post_field("s_taskauthorization ",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleteuserdetails();
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}





function add_authorizationentry()
{
	if(VAL_addauthorization()){
	
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["saveauthorizationentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_taskauthorization",handle_save_authorizationentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_authorizationentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			$('#form-general')[0].reset();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}



function searcheditgo(page_no){
	
	var servlet =  document.getElementById('servlet').value;
    var taskid =  document.getElementById('taskid').value;
	if(servlet=="" && taskid=="" ){
		
		nic_alert("Please Enter data for searching","N");}
		
	else{
		if(VAL_searcheditauthorization())
		{
		   arr_fieldname=["task","process_id","servlet","taskid","page_no"];
			arr_fieldvalue=["searcheditgo",getprocessid(),servlet,taskid,page_no];
			ajax_post_field_divH("s_taskauthorization",arr_fieldname,arr_fieldvalue,'home_valu');	
		}
	}
}

function searchdeletego(page_no){
	
	var servlet =  document.getElementById('servlet').value;
	var taskid =  document.getElementById('taskid').value;
		if(servlet=="" && taskid=="" ){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(VAL_searchdeleteauthorization()){
		arr_fieldname=["task","process_id","servlet","taskid","page_no"];
		arr_fieldvalue=["searchdeletego",getprocessid(),servlet,taskid,page_no];
	    ajax_post_field_divH("s_taskauthorization",arr_fieldname,arr_fieldvalue,'home_valu');	
		}
	}
}



function delete_authorization1(serv_name,task_id)
{
	if(confirm("Do you want to delete?")){
		
		arr_fieldname=["task","process_id","serv_name","task_id"];
		
		arr_fieldvalue=["deleteuserdetailslist",getprocessid(),serv_name,task_id];

		ajax_post_field("s_taskauthorization",handledeletelists,arr_fieldname,arr_fieldvalue);
	}

}


function handledeletelists(){
	if(http.readyState == 4)
	{
	results = http.responseText;
	if(results.substr(0,7)!= "Message")	{
		nic_alert("Deleted","V");
		//edit_authorization('1');
	    document.getElementById(resultDiv).innerHTML="";
		delete_authorization('1');
	/*
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			*/
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}


function edit_detailsentry(slno)
{
			var ins_form= document.getElementById('form-general');
		
			arr_fieldname=["task","process_id","slno"];
			arr_fieldvalue=["editdetailsentry",getprocessid(),slno];
			ajax_post_form_field_div(ins_form,"s_taskauthorization",handle_edituserdetails,arr_fieldname,arr_fieldvalue,'add_auth');
}
function handle_edituserdetails()
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
function VAL_addauthorization()
{
	
if($("#checkbox_group input#rolecode").is(':checked'))
		   alert('checked');
	 else{
		 alert(' not checked');
			 return false;}
	
	var servletname=  document.getElementById('servletname').value;
	var task_id =  document.getElementById('task_id').value;
	var rolecode =  document.getElementById('rolecode').value;
	
	
	if((val_servlet(servletname)) && (val_task_id(task_id)))
		
			return true;
	}






function VAL_addauthorizations()
{
	if($("#checkbox_group input#rolecode").is(':checked'))
		   alert('checked');
	 else{
		 alert(' not checked');
			 return false;}
	
	
	var servletname=  document.getElementById('serv_name1').value;
	var task_id =  document.getElementById('task_id1').value;
	var rolecode =  document.getElementById('rolecode').value;
	if((val_servlet(servletname)) && (val_task_id(task_id)))
		
			return true;
	}



function VAL_searcheditauthorization()
{
	var servletname=  document.getElementById('servlet').value;
	var task_id =  document.getElementById('taskid').value;
	if(servletname!=="")
	{
		if((val_servlet(servletname))){
		return true;
		}
	}
	if(task_id!=="")
	{
		if((val_task_id(task_id))){
			return true;
		}
	}
	}
		
function VAL_searchdeleteauthorization()
{
	
	var servletname=  document.getElementById('servlet').value;
	var task_id =  document.getElementById('taskid').value;
	if(servletname!=="")
	{
		if((val_servlet(servletname)))
		{
			return true;
		}
	}
	if(task_id!=="")
	{
		if((val_task_id(task_id)))
		{
			return true;
		}					
	}
}

function VAL_details()
{
	var servletname=  document.getElementById('servlet').value;
	var task_id =  document.getElementById('taskid').value;
	var rolecode =  document.getElementById('Role_code').value;
		if((servletname=="") && (task_id=="") && (rolecode==""))
			{
			nic_alert("Please enter anyone for search","V");
			return false;
			}
		else{
			if(!servletname==""){ 
				val_servlet(servletname);
				
			}
			else if(!task_id==""){ 
				val_task_id(task_id);
				
			}
			else if(!rolecode==""){ 
				val_rolecode(rolecode);
				
			}
		}
			return true;
	}
