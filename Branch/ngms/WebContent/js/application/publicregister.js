$(document).ready(function()
{
	$('.multiradio input[type=radio]').change(function() {
		$('.multiradio input[type=radio]:checked').not(this).prop('checked', false);
	});
});

$(document).ready(function()
		{
	
	 $('.register_page input[type=radio]').change(function() {
	        // When any radio button on the page is selected,
	        // then deselect all other radio buttons.
	        $('input[type=radio]:checked').not(this).prop('checked', false);
	    });
});

var ins_form= null;
function loadUrl(location)
{
	/*alert("");*/
this.document.location.href = location;
}
function checkuser(){
	ins_form= document.getElementById('form-login');
	var userid = document.getElementById('username').value;
	alert(userid);
	if(userid==""){
		alert("Please Enter Your Preffred User Name ");
	}
	else{
	validateuserID();
	//ins_form= document.getElementById('form_general');
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["checkuseravailability",getprocessid()];
	ajax_post_form_field(ins_form, "s_publicregister",handlecheckuser,arr_fieldname,arr_fieldvalue,'container');
   }
}
function handlecheckuser()
{
if(http.readyState == 4)
{
	results = http.responseText;
	if(results.substr(0,7)!= "Message")
		{
		document.getElementById(resultDiv).innerHTML=results;
		}
	else
	{
		alert(results.substr(8));
	}
}

}

function disp_div(p_val)
{
	if(p_val=="yes")
	{
	document.getElementById('txt_t1').className 	= 'visible';
	document.getElementById('txt_t2').className 	= 'hide';
	}
	else if(p_val=="no")
	{
	document.getElementById('txt_t2').className 	= 'visible';
	}
}


function save_registration(){
	var ins_form= document.getElementById('form-login');
	var x = null;
	if(validate_registration()){
		var password = document.getElementById('txt_password').value;
		var retpasswd= SHA256(password);
		var area = document.getElementById('txt_eaddress').value;
//		var username = document.getElementById('username').value;
		
//		var fields = username.split('@');
//		x = fields[0] + 'atdot' + fields[1];
//		var fields = x.split(".");
//		x = fields[0]
//		for(var i = 1; i < fields.length; i++){
//			x = x + 'attdot' + fields[i];
//		}
//		document.getElementById('username').value = x;
		if(area != ""){
			var fields = area.split('@');
			x = fields[0] + 'atdot' + fields[1];
			var fields = x.split(".");
			x = fields[0]
			for(var i = 1; i < fields.length; i++){
				x = x + 'attdot' + fields[i];
			}
			document.getElementById('txt_eaddress').value = x
		}
		document.getElementById('txt_password').value = retpasswd;
		document.getElementById('txt_repassword').value = retpasswd;
		arr_fieldname =["task"];
		arr_fieldvalue =["saveregistration"];
//		document.getElementById('popup').style.display = 'block';
		ajax_post_form_field(ins_form, "s_publicregister",handle_saveregistration,arr_fieldname,arr_fieldvalue);
		
	}	
}
var tname23=null;

/*function handle_saveregistration()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{document.getElementById('popup').style.display = 'none';
			alert("Account Created Successfully");
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],n[2]];
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);
		}
		else
		{
			document.getElementById('create').disabled = false;
			document.getElementById('popup').style.display = 'none';
			alert(results.substr(8));
		}
	}
}
*/
function handle_saveregistration()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			alert("Verification Code is sent");
			document.getElementById('form-change').innerHTML= results;
			$('.registerpage .box-body input').attr('disabled','disabled');
			$('.registerpage').addClass("registerpage1");
//			document.getElementById('popup').style.display = 'none';
		}
		else
		{
			
			if(results.substr(0,7)!= "Message"){
				/*alert(results.substr(9));*/
				/*arr_fieldname=['task'];
				arr_fieldvalue=["forlogin"];
				post_field("s_publicregister",arr_fieldname,arr_fieldvalue,false);*/
				window.location.href = "public_login";
				
//				document.getElementById('popup').style.display = 'none';
				
			}else{
				alert(results.substr(9));
//				document.getElementById('popup').style.display = 'none';
			}
			
		}
	}
} 

function cancel(){
	ins_form= document.getElementById('form_general');
	window.location.href="login";return;
}


function gopublichome()
{
	window.location.href = "public_login";
}

function gohome()
{
	window.location.href = "./";
}
function isDetailsValid(){
	ins_form= document.getElementById('form-login');
	//if(validate_savedetails()){
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["savedetails",getprocessid()];
		
		ajax_post_form_field(ins_form,"s_publicregister",handle_isDetailsValid,arr_fieldname,arr_fieldvalue);
	//}
		
	
}

function handle_isDetailsValid()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			alert("Registration Successfully Completed.","N");
			var n=results.split("-");
			/*arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],getprocessid()];
			post_field(n[0],arr_fieldname,arr_fieldvalue,false);
			*/
			arr_fieldname=['task'];
			arr_fieldvalue=["forlogin"]; //forlogin
			
			post_field("s_publicregister",arr_fieldname,arr_fieldvalue,false);
		} 
		else
		{
			nic_alert(results.substr(8),"V");
			
		}
	}
}

function getAnotherCode(){
	ins_form= document.getElementById('form-login');
	//if(validate_personaldetails()){
		arr_fieldname=['task','process_id'];
		arr_fieldvalue=["saveregGetcode",getprocessid()];
		ajax_post_form_field(ins_form,"s_publicregister",handle_getAnotherCode,arr_fieldname,arr_fieldvalue);
		
	//}	
}
function handle_getAnotherCode()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			nic_alert("Verification Code is sent","N");
			document.getElementById('form-change').innerHTML= results;
			$('.hide_div input').attr('disabled','');
		}
		else
		{
			
			if(results.substr(0,8)!= "Message1"){
				/*alert(results.substr(9));*/
				arr_fieldname=['task'];
				arr_fieldvalue=["forlogin"];
				post_field("s_publicregister",arr_fieldname,arr_fieldvalue,false);
			}else{
				nic_alert(results.substr(9),"V");
			}
			
		}
	}
}

function validateuserID() {
	var userid = document.getElementById('username').value;
	//var userln=document.getElementById('txt_userln').value;
	if (!isValidEmail(userid)){
		alert("Please Enter Valid Login ID.");
		return false;
	}
	
	else if (!isLEN(userid,10,50)){
		alert("Please Enter  Valid User ID. Minimum Length is 10 maximum 50 characters");
		return false;
	}
}
function validate_registration() {

	//ins_form			= document.getElementById('form_general');
	var name = document.getElementById('txt_name').value;//
	var yy = document.getElementById('txt_yy').value;//
	var mm = document.getElementById('txt_mm').value;//
	var dd = document.getElementById('txt_dd').value;//
	var state = document.getElementById('txt_state').value;//
	var eaddress = document.getElementById('txt_eaddress').value;//
	var mobile = document.getElementById('txt_mobile').value;//
//	var userid = document.getElementById('username').value;//
	var password = document.getElementById('txt_password').value;//
//	var chk_mobile = document.getElementById('chk_mobile').value;//
	var repassword = document.getElementById('txt_repassword').value;//
//	var chk_mail=document.getElementById('chk_mail').value;//
	var agree=document.getElementById('agree').checked;//

	
	//-----------------------------prefered userid
//	if(userid==""){
//		nic_alert("Please Enter Your Preffred User ID ","V");
//		return false;
//	}
//	else if (!VAL_E(userid)){
//		nic_alert("Please Enter Valid User ID(mail ID).","V");
//		return false;
//	}
//	
//	else if (!isLEN(userid,10,50)){
//		nic_alert("Please Enter  Valid User ID.","V");
//		return false;
//	}
//----------------------------------------------------name
		if(name==""){
			alert("Please Enter Your Name ");
			return false;
		}
		else if (!VAL_NAME(name)){
			alert("Please Enter Valid Name. Allowed Special Charector is Space and dot");
			return false;
		}
		else if (!isLEN(name,3,50)){
			alert("Please Enter  Valid Name. Minimum Length is 3 and Maximum Length is 50");
			return false;
		}
		
//---------------------------------------------------date
 
		if(dd!=""){
			
		 if (!VAL_N(dd)){
			alert("Please Enter Valid dd");
			return false;
		}
		else if (!isLEN(dd,2,2)){
			alert("Please Enter  Valid dd. Length is 2");
			return false;
		}
		 
		}else{
			alert("Please Enter Your dd ");
			return false;
		
		}
	// alert(mm);
		if(mm!=""){
			
		 if (!VAL_N(mm)){
			alert("Please Enter Valid mm");
			return false;
		}
		else if (!isLEN(mm,2,2)){
			alert("Please Enter  Valid mm. Length is 2");
			return false;
		}
		}
		else{
			alert("Please Enter Your mm ");
			return false;
		
		}
		if(yy!=""){
			
		if (!VAL_N(yy)){
			alert("Please Enter Valid yy");
			return false;
		}
		else if (!isLEN(yy,4,4)){
			alert("Please Enter  Valid Address. Length is 4");
			return false;
		}
		}else{

			alert("Please Enter Your yy ");
			return false;
		}



//----------------------------------------mobile
		if(mobile==""){
				alert("Please Enter Mobile no ");
				return false;
			}
			
		else if (!VAL_N(mobile)){
			alert("Please Enter Valid Mobile No.Enter numeric value only");
			return false;
		}
		else if (!isLEN(mobile,10,10)){
			alert("Please Enter Valid Mobile Number.Should contain only 10 numbers.");
			return false;
		}
		
//---------------------------------------------------email
		if(eaddress!=""){
			if (!VAL_E(eaddress)){
				alert("Please Enter Valid Email. It should be of mail id format ");
				return false;
			}
		}

		/*if ((chkemail==true) && (email=="")){
			nic_alert ('Enter Email Address to send Message',"V");
			return false;
		}	*/	

		//-------------------------------------------------state
		if(state==""){
			alert("Please Enter Your state Name ");
			return false;
		}
		else if (!VAL_AS(state)){
			alert("Please Enter Valid State Name.Numbers,Special characters and Space is not allowed ");
			return false;
		}
		else if (!isLEN(state,3,50)){
			alert("Please Enter  Valid state Name. Minimum Length is 3 and Maximum Length is 50");
			return false;
		}
		

//---------------------------------password---------------
		
		if(password==""){
			alert("Please Enter Password ");
			return false;
		}
		
		/*else if(password.match(' ')){
			nic_alert("Password should not contain space","V");
			return false;		
			
		}*/
		else if(!VAL_ispwdok(password)){
			alert("Your Current password is not proper!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit");
			document.getElementById('txt_password').value="";
			return false;
		}
//-------------------------------------repassword----------------------
	
		
		if(repassword==""){
			alert("Please Re-enter Password ");
			return false;
		}	
		else if(repassword.match(' ')){
			alert("Password should not contain space");
			return false;		
			
		}
		else if (repassword!=password){
			alert("Password re-entered does not match the given password ");
			return false;
		}
		
	
		 if (agree==true){
		   return true;
		  }else{
			  alert("Please check the terms and conditions ");
			  return false;
		  }
		  
		  
		
//-------------------------------------captcha
	/*	if(captcha==""){
			nic_alert("Please type the value of captcha image","V");
		}*/
//----------------------------		
		return true;
}