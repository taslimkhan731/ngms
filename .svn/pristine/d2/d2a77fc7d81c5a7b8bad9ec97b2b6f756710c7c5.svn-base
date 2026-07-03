var tr_uin_id="";
var tr_up_id="";
var tablename="";
 jsonobj = "";
//document.getElementById('tr_uin_id').value=tr_uin_id;
//document.getElementById('tr_up_id').value=tr_uin_id;
//document.getElementById('tablename').value=tablename;
function listupload()
{
		 
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["list",getprocessid()];
		ajax_post_field_divH("s_uploadtable", arr_fieldname,arr_fieldvalue,'list');
 
	
}
function getpop(tr_uin_id){
	 
	arr_fieldname=["task","process_id","tr_uin_id"];
	arr_fieldvalue=["showlist",getprocessid(),tr_uin_id];
	ajax_post_field_div("s_uploadtable", hndlepop,arr_fieldname,arr_fieldvalue,'ppop');
}
function hndlepop(){
	if(http.readyState == 4){
		results = http.responseText;
	 
		if(results.substr(0,7)!= "Message"){
 		 
		//document.getElementById(results).innerHTML=results; 
			show_nicpopup(results);
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function cancel()
{
		 
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["cancel",getprocessid()];
	 
		ajax_post_field_div("s_uploadtable",handle_cancel,arr_fieldname,arr_fieldvalue,'list');	 
		//ajax_post_field("s_uploadtable",handle_updateform_data,arr_fieldname,arr_fieldvalue);	
	
}
function handle_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
			 
		if(results.substr(0,7)!= "Message"){
		
			document.getElementById(results).innerHTML=results;
			nic_alert("task cancelled","N"); 
			
		}else{
			nic_alert(results.substr(8),"N"); 
			 
		}
	}
}
function updateid(id)
{
		 
		arr_fieldname=["task","process_id","m_id"];
		arr_fieldvalue=["list",getprocessid(),id];
		ajax_post_field("s_uploadtable",handle_updateform_data,arr_fieldname,arr_fieldvalue);	 
	
}
function create_new()
{
		 
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["createnew",getprocessid()];
		ajax_post_field_divH("s_uploadtable",arr_fieldname,arr_fieldvalue,'list');	 
	
}
function handle_saveform_data(){
	if(http.readyState == 4){
		results = http.responseText;
		 
		if(results.substr(0,7)!= "Message"){
 		 
		document.getElementById(results).innerHTML=""; 
			
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function handle_updateform_data(){
	if(http.readyState == 4){
		results = http.responseText;
		 
		 
		if(results.substr(0,7)!= "Message"){
		 
			document.getElementById(results).innerHTML="";
			
		}else{
			 
			nic_alert("updated successfully","N");
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["list",getprocessid()];
			ajax_post_field_divH("s_uploadtable", arr_fieldname,arr_fieldvalue,'list');
		}
	}
}

function save_serv(id,up_id,table)
{
	  tr_uin_id= id;
	  tr_up_id=up_id;
     
		arr_fieldname=["task","tablename","process_id"];
		arr_fieldvalue=["update",table,getprocessid()];
		ajax_post_field("s_uploadtable",handle_updateindex,arr_fieldname,arr_fieldvalue);	 
	
}
function handle_updateindex(){
	if(http.readyState == 4){
		results = http.responseText;
		 jsonobj = results; 	 
	  
		if(results.substr(0,7)!= "Message"){
			arr_fieldname=["task"];
			arr_fieldvalue=["login"];
			ajax_post_field_div("s_uploadlogin",handle_updatelogin,arr_fieldname,arr_fieldvalue,'getusr');	 
						 
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function handle_updatelogin(){
	if(http.readyState == 4){
		results = http.responseText;
	  
	 
		if(results.substr(0,7)!= "Message"){
			
		show_nicpopup(results); 
			//document.getElementById(results).innerHTML=results;  
			//alert(tr_uin_id);
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function getusrname()
{
	 
	var ins_form	= document.getElementById('usrform');
	var username	= document.getElementById('login-username').value;
	var password	= document.getElementById('login-password').value;
	var imgtext 	= document.getElementById('login-captcha').value;
	var processid 	= document.getElementById('process_id').value;
//	var jobj = document.getElementById('jobj').value;
	 
	 
	//jobj= [$('#jobj').val()];
	var status		= validatelogoin(username,password,imgtext);
	 
	if(status)
	{
		close_nicpopup(); 
		var retpasswd= SHA256(SHA256(password)+imgtext);
		document.getElementById('login-password').value = retpasswd;
	 	arr_fieldname=["task","jobj"];
		arr_fieldvalue=["updatelist",jsonobj];
		//ajax_post_field("s_uploadtable2",handlesuccess,arr_fieldname,arr_fieldvalue);
		ajax_post_form_field(ins_form,"s_uploadtable2",handlesuccess,arr_fieldname,arr_fieldvalue);		
	}
} 
function handlesuccess(){
	if(http.readyState == 4){
		results = http.responseText;
		//nic_alert("updated successfully","N");
		 
		 update = results.split("-");
		 var id = update[1];
		 if (id==1){
			nic_alert(update[0].substr(8),"N");
			 
			arr_fieldname=["task","process_id","tr_uin_id","tr_up_id"];
			arr_fieldvalue=["updateindex",getprocessid(),tr_uin_id,tr_up_id];
			ajax_post_field_divH("s_uploadtable", arr_fieldname,arr_fieldvalue,'list');	
				 
		}else{
		
				
	nic_alert(results.substr(8),"N");
			
		}
		
	}
}

function validatelogoin(p_username,p_pwd,p_captcha)
{
	if(p_username == "" || p_pwd == "" || p_captcha == "")
	{
		nic_alert('Check Your Credentials',"V");
		return false;
	}
	if (!VAL_AN(p_username))
	{
		nic_alert('Check Your User Id!\nSpecial Characters are Not Allowed.',"V");
		return false;
	}
	else if(!isLEN(p_username,3,15)){
		nic_alert('Check Your User Id!',"V");
		return false;
	}
	else if(!VAL_ispwdok(p_pwd)){
		nic_alert("Check Your Password!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
		return false;
	}
	return true;
}