
var ins_form= null;

function cancel(){
	arr_fieldname=['task','process_id'];
	arr_fieldvalue=["forlogin",getprocessid()];
	post_field("s_forgotpassword",arr_fieldname,arr_fieldvalue,false);
}

function forgotpassword(){
	ins_form= document.getElementById('form-forgot');
	if(validate_forgotpassword()){
		arr_fieldname=['task','process_id'];
		arr_fieldvalue=["forgotpassword",getprocessid()];
		ajax_post_form_field(ins_form,"s_forgotpassword",handle_forgotpassword,arr_fieldname,arr_fieldvalue);
		
	}	
}
function handle_forgotpassword()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			nic_alert("Verification Code is sent to your Mobile/Email.","N");
			setTimeout(
					  function() 
					  {
					    //do something special
					  }, 5000);
			document.getElementById('form-group').innerHTML= results;
			
			$(".box-body input").attr("disabled","disabled");
			$('#popup').hide();
		//	$('.hide_div input').attr('disabled','');
			//show_nicpopup(results);
		}
		else
		{
			
			if(results.substr(0,8)!= "Message1"){
				/*alert(results.substr(9));*/
				arr_fieldname=['task'];
				arr_fieldvalue=["forlogin"];
				post_field("s_forgotpassword",arr_fieldname,arr_fieldvalue,false);
				$('#popup').hide();
			}else{
				nic_alert(results.substr(9),"V");
				$('#popup').hide();
			}
			
		}
	}
}
function validate_forgotpassword() {
	var username = document.getElementById('fuserid').value;
//	var mobile = document.getElementById('fmob').value;
//	var emailid = document.getElementById('femailid').value;
	//var dob = document.getElementById('fdob').value;
	var txt_dd = document.getElementById('txt_dd').value;
	var txt_mm = document.getElementById('txt_mm').value;
	var txt_yy = document.getElementById('txt_yy').value;

		if(username==""){
			nic_alert("Please Enter Your Login ID ","V");
			return false;
		}
//		if(mobile==""){
//			nic_alert("Please Enter Your Mobile Number ","V");
//			return false;
//		}
//		else if (!VAL_N(mobile)){
//			nic_alert("Please Enter Valid Mobile No.Enter numeric value only","V");
//			return false;
//		}
//		else if (!isLEN(mobile,10,20)){
//			nic_alert("Please Enter Valid Mobile No. Minimum Length is 10 and Maximum Length is 20","V");
//			return false;
//		}
//		if(emailid==""){
//			nic_alert("Please Enter Your Email Id ","V");
//			return false;
//		}
//		else if (!isValidEmail(emailid)){
//			nic_alert("Please Enter Valid  Email Id.","V");
//			return false;
//		}
//		else if (!isLEN(emailid,10,50)){
//			nic_alert("Please Enter Valid  Email Id.","V");
//			return false;
//		}
		/*if(dob==""){
			nic_alert("Please Enter Date of Birth","V");
			return false;
		}else if(!isLEN(dob,3,15)){
			nic_alert("Please check your Date of Birth","V");
			return false;
		}else if(!VAL_D_new(dob)){
			nic_alert("Please Enter  Valid Date of Birth(yyyy-mm-dd)","V");
			return false;
		}*/
		if(txt_dd==""){
			nic_alert("Please Enter Date of Birth","V");
			return false;
		}else if(!isLEN(txt_dd,2,2)){
			nic_alert("Please check your Date of Birth","V");
			return false;
		}else if(!VAL_N(txt_dd)){
			nic_alert("Please Enter  Valid Date of Birth(dd)","V");
			return false;
		}
		if(txt_mm==""){
			nic_alert("Please Enter Month of Birth","V");
			return false;
		}else if(!isLEN(txt_mm,2,2)){
			nic_alert("Please check your Month of Birth","V");
			return false;
		}else if(!VAL_N(txt_mm)){
			nic_alert("Please Enter  Valid Month of Birth(mm)","V");
			return false;
		}
		if(txt_yy==""){
			nic_alert("Please Enter Year of Birth","V");
			return false;
		}else if(!isLEN(txt_yy,4,4)){
			nic_alert("Please check your Year of Birth","V");
			return false;
		}else if(!VAL_N(txt_yy)){
			nic_alert("Please Enter  Valid Year of Birth(yyyy )","V");
			return false;
		}
		return true;
}

function resetpassword(){
	ins_form= document.getElementById('form-forgot');
	if(validate_resetpassword()){
		var fpswd = document.getElementById('fpswd').value;
		var retpasswd= SHA256(fpswd);
		document.getElementById('fcpswd').value = retpasswd;
		document.getElementById('fpswd').value = retpasswd;
		arr_fieldname=['task','process_id'];
		arr_fieldvalue=["changepassword",getprocessid()];
		ajax_post_form_field(ins_form,"s_forgotpassword",handle_changepassword,arr_fieldname,arr_fieldvalue);
	}
		
	
}
function validate_resetpassword() {
	var username = document.getElementById('fuserid').value;
	var fpswd = document.getElementById('fpswd').value;
	var fcpswd = document.getElementById('fcpswd').value;
	var fverfcode = document.getElementById('fverfcode').value;
		if(username==""){
			nic_alert("Please Enter Your Login ID ","V");
			return false;
		}
		if(fpswd==""){
			nic_alert("Please Enter Password","V");
			return false;
		}
		if(fcpswd==""){
			nic_alert("Please Confirm Password","V");
			return false;
		}
		/*else if(fcpswd.match(' ')){
			nic_alert("Password should not contain space","V");
			return false;		
		}*/
		else if (fcpswd!=fpswd){
			nic_alert("Password re-entered does not match the given password ","V");
			return false;
		}else if(!VAL_ispwdok(fcpswd)){
			nic_alert(" Password is not proper!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
			return false;
		}
		if(fverfcode==""){
			nic_alert(" Please Enter Verification Code","V");
			return false;
		}
		else if(!VAL_N(fverfcode)){
			nic_alert(" Please check the verification code you entered","V");
			return false;
        }
		return true;
}
function handle_changepassword()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			
			nic_alert("Password Changed Successfully.","V");
			setTimeout(
					  function() 
					  {
					    //do something special
					  }, 100000000);
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],n[2]];
			post_field(n[0],arr_fieldname,arr_fieldvalue,false);
			
		} 
		else
		{
			alert(results.substr(8));
		}
	}
}