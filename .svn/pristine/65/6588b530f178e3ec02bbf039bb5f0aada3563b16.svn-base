window.onload = function() {
  document.getElementById("login-username").focus();
};

history.pushState({ page: 1 }, "title 1", "#login");
window.history.forward();
window.onhashchange = function (event) {
    window.location.hash = "login";

};

function gologin()
{
	var ins_form	= document.getElementById('form-login');
	var username	= document.getElementById('login-username').value;
	var password	= document.getElementById('login-password').value;
	var imgtext 	= document.getElementById('login-captcha').value;
	var status		= validate(username,password,imgtext);
	if(status)
	{
		console.log('in side login page -> gologin()')
		document.getElementById('popup').style.display = 'block';
		var retpasswd= SHA256(SHA256(password)+imgtext);
		document.getElementById('login-password').value = retpasswd;
		
		arr_fieldname =["process_id","task"];
		arr_fieldvalue =[process_id,"abc"];
		ajax_post_form_field(ins_form,'login',handle_gologin,arr_fieldname,arr_fieldvalue);
				
	}
} 

function handle_gologin()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],n[2]];
			post_field(n[0],arr_fieldname,arr_fieldvalue,false);
			document.getElementById('popup').style.display = 'none';
		}
		else
		{
			document.getElementById('popup').style.display = 'none';
		//	nic_alert(results.substr(8));
			results=results.substr(8);
			results=results.replace("Message:","");
			nic_alert(results,"N");
			document.getElementById('form-login').reset();
			//window.location.reload(true);
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
	if (!VAL_AN(p_username))
	{
		nic_alert('Check Your User Id!\nSpecial Characters are Not Allowed.',"V");
		return false;
	}
	else if(!isLEN(p_username,3,25)){
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

function getCaptchaCode() {
	$('#captchaLabelId').attr('src',
			'captcha' + '?' + Math.random());
}

function captcha_refresh()
{

	getCaptchaCode();
	
//	var ins_form	= document.getElementById('form-login');
//	arr_fieldname =["task","id","process_id"];
//	arr_fieldvalue =["refresh","a",getprocessid()];
//	//ajax_post_form_div(p_form, p_servlet, p_handelFN, p_divid)
//	ajax_post_form_field_divH(ins_form, "captchanew",arr_fieldname,arr_fieldvalue,'captcha_div_login');
//	//ajax_post_form_field_div(p_form, p_servlet, p_handelFN, arr_fieldname, arr_fieldvalue, p_divid)
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
