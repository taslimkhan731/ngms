function change_passworddd(process_id){
	if(validate()){
		var ins_form	= document.getElementById('form-changepassword');
		var password			= document.getElementById('newpassword').value;
		var curpassword			= document.getElementById('currentpassword').value;
		var retpasswd= SHA256(password);
		var curpasswd= SHA256(curpassword);
		document.getElementById('newpassword').value = retpasswd;
		document.getElementById('confirmpassword').value = retpasswd;
		document.getElementById('currentpassword').value = curpasswd;
	    arr_fieldname=['task','process_id'];
		arr_fieldvalue=["change",getprocessid()];
		ajax_post_form_field(ins_form,"s_change_password_admin",handle_change_passworddd,arr_fieldname,arr_fieldvalue);		
		
	}
}


function handle_change_passworddd(){	
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Password Changed Successfully","N");
			clear();
			/*var n=results.split("-");
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],n[2]];
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);*/
		}
		else{
			nic_alert(results.substr(8),"N");						
		}
	}
}

function validate() {
	var cur_password		= document.getElementById('currentpassword').value;
	var password			= document.getElementById('newpassword').value;
	var cpassword			= document.getElementById('confirmpassword').value;
	
	if(cur_password==""){
		nic_alert("Please Enter Current Password","V");
		return false;
	}
	if(!VAL_ispwdok(cur_password)){
		nic_alert("Your Current password is not proper!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
		clear();
		return false;
	}
	if(password==""){
		nic_alert("Please Enter New Password","V");
		return false;
	}
	if(!VAL_ispwdok(password)){
		nic_alert("Your New password is not proper!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
		document.getElementById('newpassword').value="";
		return false;
	}
	if(cpassword==""){
		nic_alert("Please Enter Confirm Password","V");
		return false;  
	}
	if(!VAL_ispwdok(cpassword)){
		nic_alert("Confirm password is not proper!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
		document.getElementById('confirmpassword').value="";
		return false;
	}
	if(password != cpassword){
		nic_alert("Passwords are not matching","V"); 
		document.getElementById('newpassword').value="";
		document.getElementById('confirmpassword').value="";
		return false;
	}
	return true;
}

function clear(){
	
	document.getElementById('currentpassword').value="";
	document.getElementById('newpassword').value="";
	document.getElementById('confirmpassword').value="";
}

