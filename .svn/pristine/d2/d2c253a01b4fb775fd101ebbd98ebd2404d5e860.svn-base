window.onload = function() {
  document.getElementById("login-username").focus();
};

history.pushState({ page: 1 }, "title 1", "#login");
window.history.forward();
window.onhashchange = function (event) {
    window.location.hash = "login";

};

function gopublogin()
{
	var ins_form	= document.getElementById('form-login');
	var username	= document.getElementById('login-username').value;
	var password	= document.getElementById('login-password').value;
	var imgtext 	= document.getElementById('login-captcha').value;
	var status		= validate(username,password,imgtext);
//	var fields = username.split("@");
//	username = fields[0] + 'atdot' + fields[1];
//	var fields = username.split(".");
//	username = fields[0]
//	for(var i = 1; i < fields.length; i++){
//		username = username + 'attdot' + fields[i];
//	}
	if(status)
	{
		document.getElementById('popup').style.display = 'block';
		var retpasswd= SHA256(SHA256(password)+imgtext);
		document.getElementById('login-password').value = retpasswd;
		document.getElementById('login-username').value = username;
		arr_fieldname =["process_id","task"];
		arr_fieldvalue =[getprocessid(),"abc"];
		ajax_post_form_field(ins_form,'public_login',handle_gologin,arr_fieldname,arr_fieldvalue);
	}
} 

function handle_gologin()
{
	if(http.readyState == 4)
	{//document.getElementById('popup').style.display = 'none';
		results = http.responseText;
	 //alert(results);
	 if(results.substr(0,7)!= "Message")
		{
			//alert("message     "+results);
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],n[2]];
			post_field(n[0],arr_fieldname,arr_fieldvalue,false);
			document.getElementById('popup').style.display = 'none';
		}
		else if(results.substr(0,8)!= "Messages")
		{
			//alert("message1 "+results);
			document.getElementById('popup').style.display = 'none';
			results=results.replace("Message1: ","");
			//document.getElementById('login-box-body').innerHTML= results;
			document.getElementById('form-login').reset();
			nic_alert(results,"N");
			
		}
		else {
			//alert("Message:    "+results);
			document.getElementById('popup').style.display = 'none';
			results=results.substr(8);
			
			results=results.replace(":","");
			/////nic_alert(results,"N");
			document.getElementById('login-box-body').innerHTML= results;
			/////document.getElementById('form-login').reset();
		}
	}
} 
function validate(p_username,p_pwd,p_captcha)
{
	if(p_username == "")
	{
		nic_alert('Please Enter User Id',"V");
		return false;
	}
	if( p_pwd == "" )
	{
		nic_alert('Please Enter Password',"V");
		return false;
	}
	if( p_captcha == "")
	{
		nic_alert('Please Enter The Captcha',"V");
		return false;
	}
	/*if (!VAL_E(p_username))
	{
		nic_alert('Check Your User Id! (Mail ID)',"V");
		return false;
	}
	else*/ if(!isLEN(p_username,3,50)){
		nic_alert('Check Your User Id!',"V");
		return false;
	}
	else if(!VAL_ispwdok(p_pwd)){
		nic_alert("Check Your Password!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
		return false;
	}
	return true;
}


function sendAlertBack(process_id){
	//alert("sendAlertBackjs");
$('.viewall_page').modal('show');
}

function go_register(){
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[getprocessid(),"abc"];
	post_field("s_publicregister", arr_fieldname, arr_fieldvalue,false);
}

function go_login(){
	window.location.href = "login";
}

function go_forgot(){
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[getprocessid(),"abc"];
	post_field("s_forgotpassword", arr_fieldname, arr_fieldvalue,false);
}


function disclaimer(){
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[getprocessid(),"disclaimer"];
	get_to_post_reloadnewpage("s_forgotpassword", arr_fieldname, arr_fieldvalue);
}

function help_document(process_id){
	//input.setAttribute("val", val);
	/*arr_fieldname =["task"];
	arr_fieldvalue =["abc"];
	get_to_post_reload("s_help_document",arr_fieldname,arr_fieldvalue);	*/
	var url="s_help_document?task=abc&process_id="+process_id;
	//url=getHiddentoken(url);
	window.open(url);
}

function getCaptchaCode() {
	$('#captchaLabelId').attr('src',
			'captcha' + '?' + Math.random());
}

function captcha_refresh_pub()
{
	getCaptchaCode();
//	var ins_form	= document.getElementById('form-login');
//	arr_fieldname =["task","id","process_id"];
//	arr_fieldvalue =["refresh","a",getprocessid()];
//	ajax_post_form_field_divH(ins_form, "captchanewpub",arr_fieldname,arr_fieldvalue,'captcha_div');
	document.getElementById("login-captcha").value="";
}	

function handlenewcaptcha()
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
		//alert(results.substr(8));
	}
}
}