var jsonobj="";
var serv1 ="";
function gh_upload(serv)
{  serv1 = serv;
	var time	= document.getElementById('time').value;
	 
     
		arr_fieldname=["task","time"];
		arr_fieldvalue=["update",time];
		ajax_post_field("s_ghupload",handle_updateindex,arr_fieldname,arr_fieldvalue);	 
	
}
function handle_updateindex(){
	if(http.readyState == 4){
		results = http.responseText;
		jsonobj = results; 	 
	 		  
		if(results.substr(0,7)!= "Message"){
			arr_fieldname=["task","clientip"];
			arr_fieldvalue=["login",serv1];
			ajax_post_field_div(serv1+"s_ghupload_login",handle_updatelogin,arr_fieldname,arr_fieldvalue,'getusr');	 
						 
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

function update_ghtable()
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
		 alert(jsonobj);
	 	arr_fieldname=["task","jobj"];
		arr_fieldvalue=["updatelist",jsonobj];
		//ajax_post_field("s_uploadtable2",handlesuccess,arr_fieldname,arr_fieldvalue);
		ajax_post_form_field(ins_form,serv1+"s_receive_ghupload",handlesuccess1,arr_fieldname,arr_fieldvalue);		
	}
} 
function handlesuccess1(){
	if(http.readyState == 4){
		 
		results = http.responseText;
		//nic_alert("updated successfully","N");
		 
		 update = results.split("-");
		 
		 var id = update[1];
		 if (id==1){
			 
			nic_alert(update[0].substr(8),"N");
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateindex",getprocessid()];
			ajax_post_field_divH("s_ghupload", arr_fieldname,arr_fieldvalue,'list');	
				 
		}else{
			nic_alert(results.substr(8),"N");
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateindexfailed",getprocessid()];
			ajax_post_field_divH("s_ghupload", arr_fieldname,arr_fieldvalue,'list');	
				
	
			
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