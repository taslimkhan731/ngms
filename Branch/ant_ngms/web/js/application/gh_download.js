var jobj = "";
var time = "";
var serv1="";
function gh_download(serv){
	 
	serv1 = serv;
	var time1	= document.getElementById('time2').value;
	time =time1;
	
	arr_fieldname=["task","clientip"];
	arr_fieldvalue=["login",serv1];
	ajax_post_field_div(serv1+"s_ghdownload_login",handle_updatelogin,arr_fieldname,arr_fieldvalue,'dataEntry');	
	
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
function  download_ghtable()
{
	 
	var ins_form	= document.getElementById('usrform');
	var username	= document.getElementById('login-username').value;
	var password	= document.getElementById('login-password').value;
	var imgtext 	= document.getElementById('login-captcha').value;
	var processid 	= document.getElementById('processid').value;//process_id
	 
	 
	 
	//jobj= [$('#jobj').val()];
	var status		= validatelogoin(username,password,imgtext);
	 
	if(status)
	{
		close_nicpopup(); 
		var retpasswd= SHA256(SHA256(password)+imgtext);
		document.getElementById('login-password').value = retpasswd;
	 
		arr_fieldname=["task","time"];
		arr_fieldvalue=["downloaddata",time];
		 
		ajax_post_form_field(ins_form,serv1+"s_ghdownload",handlesuccess,arr_fieldname,arr_fieldvalue);		
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

function handlesuccess(){
	if(http.readyState == 4){
		results = http.responseText;
		
		if(results.substr(0,7)!= "Message"){
			 //alert(results);
			arr_fieldname=["task","jobj","process_id"];
			arr_fieldvalue=["updatelist",results,getprocessid()];
			//ajax_post_field("s_uploadtable2",handlesuccess,arr_fieldname,arr_fieldvalue);
			ajax_post_field("s_receive_ghdownload",handlepostdata,arr_fieldname,arr_fieldvalue);	
		 
	 		 
		}else{
			 
			
		nic_alert(results.substr(8),"N");
			
		}
	
	}
}

function handlepostdata(){
	if(http.readyState == 4){
		results = http.responseText;
		 
		if(results.substr(0,7)!= "Message"){
 
				 
		}else{
					
		nic_alert(results.substr(8),"N");
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["abc",getprocessid()];
		//ajax_post_field("s_uploadtable2",handlesuccess,arr_fieldname,arr_fieldvalue);
		post_field("s_ghupload",arr_fieldname,arr_fieldvalue,false);
			
		}
	}
}