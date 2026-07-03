function disable_menu()	{document.getElementById('header-box').disabled=true; }
function enable_menu()	{document.getElementById('header-box').disabled=false;}

function logout()
{
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["logout",document.getElementById("process_id").value];
		get_to_post_reload("logout",arr_fieldname, arr_fieldvalue);
}


// Returns the process id in all the pages. the form is hidden and is included in the Template->DOC
function getprocessid()
{
	return document.getElementById('process_id').value;
}

function reloadpagepost(pservelt,ptask,pprocessid)
{
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=[ptask,pprocessid];
		get_to_post_reload(pservelt,arr_fieldname, arr_fieldvalue);
}

function goHomeIndexPage(p_form){
	try {
		if(document.getElementById("previewTask")){
        var element = document.getElementById("previewTask");
        element.parentNode.removeChild(element);
	}
	} catch (e) {
	}
	ins_form = document.getElementById(p_form);
		arr_fieldname = [ "task", "process_id" ];
		arr_fieldvalue = [ "goHome",document.getElementById("process_id").value ];
		ajax_post(ins_form,"index",response_handler_hgoHomeIndexPage,arr_fieldname,arr_fieldvalue);
}
function response_handler_hgoHomeIndexPage() {
	if (http.readyState == 4) {
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);
		if(results.substr(0,7)!= "Message")
			{
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],n[2]];
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);
			}
		else
		{	
			alert(results.substr(8));
		}
	}
}
function response_handler_save() {
	if (http.readyState == 4) {
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);
		
		if(results.substr(0,7)!= "Message")
			{
			
			
		
			}
		else
		{	
			alert(results.substr(8));
			
		}
	}
}

function handle_checkspecbody() {
	if (http.readyState == 4) {
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);
		// //commented for updatenew
		// document.getElementById('popup').style.display = 'none';
		if (results.substr(0, 7) != "Message") {
			document.getElementById(resultDiv).innerHTML = results;

		} else {
			alert(results.substr(8));

		}
	}
}
function handle_unit_new()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);
		////commented for updatenew document.getElementById('popup').style.display = 'none';
		if(results.substr(0,7)!= "Message")
			{
			document.getElementById(resultDiv).innerHTML=results;
		
			
			}
		else
		{
			alert(results.substr(8));
			
		}
	}
}function handle_unit()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
   		results = results.replace("$"+prid+"^)", "");
   		document.getElementById('popup').style.display = '';
   		createInputProcess_id(prid);
		////commented for updatenew document.getElementById('popup').style.display = 'none';
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

function edit_alert()
{
	var objchb =document.getElementsByName('genum');

	var genum="";
	for(var i=0; i < objchb.length; i++ ) 
	{ 
		if(objchb[i].checked == true)
			genum=objchb[i].value;
	}
	
	if(genum=="")
	{
		alert("Please make a Selection From the List");	
	}
	else if(!VAL_ANSP(genum,"/"))
	{
		alert("Invalid GE NUmber");
	}
	else
	{
			var event_id=document.getElementById(genum).value;
			var url=domain_servlet_shortcode+'_ger_alert?task=editger&flag=d&event_id='+escape(event_id)+'&genumber='+escape(genum);
			ajax_get(url,handle_editalert);
	}
	
}

function handle_editalert()
{	
	if(http.readyState == 4)
	{
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
   		results = results.replace("$"+prid+"^)", "");
   		document.getElementById('popup').style.display = '';
   		createInputProcess_id(prid);
		if(results.substr(0,7)!= "Message")
			{
				window.location.href=results;			
			}
		else
		{
			alert(results.substr(8));
		}
	}
}




function get_paymax(p_lblmin,p_lblmax,p_val,p_div,p_label)
{

	if(!VAL_ANSP(p_lblmin,"_") || !VAL_ANSP(p_lblmax,"_") ||  !VAL_ANSP(p_div,"_"))
	{
		alert("Invalid Data");
	}
	else if(!VAL_N(p_val))
	{
		alert("Invalid Selection");
	}
	else if(!VAL_AS(p_label))
	{
		alert("Invalid Selection");
	}
	else
	{
		if(document.getElementById('hid_dept_code')!=null){
			var url=domain_servlet_shortcode+'_common?task=getpaymax&dept_code='+escape(document.getElementById

('hid_dept_code').value)+'&lblpaymin='+escape(p_lblmin)+'&lblpaymax='+escape(p_lblmax)+'&selvalue='+escape(p_val)+'&divid='+escape

(p_div)+'&label='+escape(p_label);
			ajax_getDiv(url,p_div);
		}
		else{
		var url=domain_servlet_shortcode+'_common?task=getpaymax&lblpaymin='+escape(p_lblmin)+'&lblpaymax='+escape

(p_lblmax)+'&selvalue='+escape(p_val)+'&divid='+escape(p_div)+'&label='+escape(p_label);
		ajax_getDiv(url,p_div);
		}
	}
}


function searchFun(pservlet)
{
	//alert("insdie search" );
	
	var p_val=document.getElementById('filter_search').value;
	p_val=p_val.trim();
	if(!VAL_ANSP(p_val," /."))
	{
		alert("Invalid Search Field");
	}
	else
	{
		var url=pservlet+'?task=search&parm='+escape(p_val);
		ajax_getDiv(url,'pagination_div');
	}
}



function pagination(p_index,p_limit,p_servlet,p_task,p_div)
{
	var url = p_servlet+"?task="+p_task+"&index="+p_index+"&limit="+p_limit+"&process_id="+document.getElementById("process_id").value;
	ajax_getDiv(url, p_div);
}
function getRadio(btn_obj)
{
	for(var i=0; i < btn_obj.length; i++ ) 
	{ 
		if(btn_obj[i].checked == true)
			return btn_obj[i].value;
	}	
}
function setVisible()
{
	obj = document.getElementById("ani_processing");
	obj.style.visibility = 'visible';
}
function setHide()
{
	obj = document.getElementById("ani_processing");
	obj.style.visibility = 'hidden';
}
function placeIt()
{
	obj = document.getElementById("ani_processing");
	        x = self.screen.width/2;
			y = self.screen.height/2;
			x=400;y=250;
			obj.style.left = x + 'px' ;
		obj.style.top = y + 'px'; 
	setVisible();
}

function openwin(val)
{
	val = getHiddentoken(val);
	window.open(val,'_blank','resizable=yes,scrollbars=yes');
}



function openwin_tab(val)
{
	val = getHiddentoken(val);
	//window.open(val,'_blank','resizable=yes,scrollbars=yes');
	//window.open(url, '_blank');
	// window.focus();
	  var newtab = window.open();
	  newtab.location = val;
}


function validate_document()
{
	var chk_obj=document.getElementsByName('chk[]');
	for (var i=0; i < chk_obj.length;i++){ 
		if (chk_obj[i].checked){
		   if(!VAL_AN(chk_obj[i].value))
		   {
			   alert("Invalid document");
			   check=false;
			   break;
		   }
		} 	
	}
	var pending_status		= getRadio(document.getElementsByName('rdo_pend'));
	 if(!VAL_CE(pending_status,"Y","N"))
	   {
		   alert("Invalid Pending Status");
		   return false;
	   }
	return true;
}


function uni2ent(srcTxt) {
  var entTxt = '';
  var c, hi, lo;
  var len = 0;
  for (var i=0, code; code=srcTxt.charCodeAt(i); i++) {
    var rawChar = srcTxt.charAt(i);
    // needs to be an HTML entity
    if (code > 255) {
      // normally we encounter the High surrogate first
      if (0xD800 <= code && code <= 0xDBFF) {
        hi  = code;
        lo = srcTxt.charCodeAt(i+1);
        // the next line will bend your mind a bit
        code = ((hi - 0xD800) * 0x400) + (lo - 0xDC00) + 0x10000;
        i++; // we already got low surrogate, so don't grab it again
      }
      // what happens if we get the low surrogate first?
      else if (0xDC00 <= code && code <= 0xDFFF) {
        hi  = srcTxt.charCodeAt(i-1);
        lo = code;
        code = ((hi - 0xD800) * 0x400) + (lo - 0xDC00) + 0x10000;
      }
      // wrap it up as Hex entity
      c = "" + code.toString(16).toUpperCase() + ";";
    }
    else {
      c = rawChar;
    }
    entTxt += c;
    len++;
  }
  return entTxt;
}

String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
};


function phonetrim(inputString)
{
        if (typeof inputString != "string") { return inputString; }

        var temp_str = '';
        temp_str = inputString.replace(/[\s]+/g,"");
        if(temp_str == '')
                return "";

        var retValue = inputString;
        var ch = retValue.substring(0, 1);
        while (ch == " ")
        {
                retValue = retValue.substring(1, retValue.length);
                ch = retValue.substring(0, 1);
        }
        ch = retValue.substring(retValue.length-1, retValue.length);
        while (ch == " ")
        {
                retValue = retValue.substring(0, retValue.length-1);
                ch = retValue.substring(retValue.length-1, retValue.length);
	}
        while (retValue.indexOf("  ") != -1)
        {
              retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length);
        }
        return retValue;
}

function validate_alert()
{
	var eventCode		= document.getElementById('txt_eventCode').value;
	var alert_date		= document.getElementById('txt_alertdate').value;

	if(eventCode!=""){
		if (!VAL_AN(eventCode)){
			alert("Please Enter a Valid Event");
			return false;
		}
	}	
	if(alert_date!=""){
		if (!VAL_D(alert_date)){
			alert("Please Enter a Valid Alert date");
			return false;
		}
	}
	return true;
}

function ascii_value(c)
{
	// restrict input to a single character
	c = c . charAt (0);
	// loop through all possible ASCII values
	var i;
	for (i = 0; i < 256; ++ i)
	{
		// convert i into a 2-digit hex string
		var h = i . toString (16);
		if (h . length == 1)
			h = "0" + h;
		h = "%" + h;
		// determine the character represented by the encodeURIComponent code
		h = encodeURIComponent (h);

		// if the characters match, we've found the ASCII value
		if (h == c)
			break;
	}
	return i;
}




function setUpperCase(p_textname)
{
	var x=document.getElementById(p_textname);
	x.value=x.value.toUpperCase();
}

function setSame(p_textname,p_textname2)
{
	document.getElementById(p_textname2).value=document.getElementById(p_textname).value;
}


function ajax_getDiv_fast(p_url,p_divid)
{
	//document.getElementById('popup').className = 'hide';
	resultDiv 	= p_divid;
	url=getHiddentoken(p_url);
	http.open("GET",url,true);
	http.onreadystatechange = handleOutput_fast;
	http.send(null);
}





function handleOutput_fast()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
   		results = results.replace("$"+prid+"^)", "");
   		document.getElementById('popup').style.display = '';
   		createInputProcess_id(prid);
		//document.getElementById('popup').className = 'hide';
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



function view_orders(){
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['abc',document.getElementById("process_id").value];
	
	get_to_post_reload_newpage('s_view_orders', arr_fieldname, arr_fieldvalue);
	
}




//==============new search post function


function searchFunpost(pservlet,form)
{
	//alert("insdie search" );
	
	var ins_form=document.getElementById(form);
	
	var p_val=document.getElementById('filter_search').value;
	p_val=p_val.trim();
	if(!VAL_ANSP(p_val," /."))
	{
		alert("Invalid Search Field");
	}
	else
	{
		/*var url=pservlet+'?task=search&parm='+escape(p_val);
		ajax_getDiv(url,'pagination_div');*/
		
		
		arr_fieldname=["task","process_id","parm"];
		
		arr_fieldvalue=["search",document.getElementById("process_id").value,p_val];
		
		ajax_post_div(ins_form, pservlet, handle_estimate, arr_fieldname, arr_fieldvalue, 'pagination_div');
	}
}



function resetFunpost(pservlet,form)
{
	
	var ins_form=document.getElementById(form);
		
	
		arr_fieldname=["task","process_id"];
		
		arr_fieldvalue=["abc",document.getElementById("process_id").value];
		
		get_to_post_reload(pservlet,arr_fieldname, arr_fieldvalue);
}




function paginationpost(p_index,p_limit,p_processId,p_servlet,p_task,p_div,form)
{
	
	
	
	var ins_form=document.getElementById(form);
	arr_fieldname=["task","process_id","index","limit",];
	
	arr_fieldvalue=[p_task,document.getElementById("process_id").value,p_index,p_limit];
	
	ajax_post_div(ins_form, p_servlet, handle_estimate, arr_fieldname, arr_fieldvalue, 'pagination_div');
	
}



function paginationpostpage(ref,p_index,p_limit,p_processId,p_servlet,p_task,p_div,form)
{
	
	
	
	var ins_form=document.getElementById(form);

	arr_fieldname=["task","process_id","index","limit"];
	
	arr_fieldvalue=[p_task,document.getElementById("process_id").value,p_index,p_limit];
	
	ajax_post_div(ins_form, p_servlet, handle_estimatepage, arr_fieldname, arr_fieldvalue, 'pagination_div');
}

function menuPost_copy(url)
{
	if(url=="#"){
		return false;
	}
	
	var array_name=new Array();
	var array_name_new=new Array();
	var array_name_two=new Array();
	array_name=url.split("?");
	array_name_new=url.split("=");

		array_name_two=array_name_new[1].split("&");
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=[array_name_two[0],document.getElementById("process_id").value];
		get_to_post_reload(array_name[0], arr_fieldname, arr_fieldvalue);
}

function menuPost(url)
{
	if(url=="#"){
		return false;
	}
	var a="",b="";
	
	var array_name=new Array();
	
	var array_name_two=new Array();

	array_name=url.split("?");
	
	
	
		array_name_two=array_name[1].split("&");
	for(var i=0;i<array_name_two.length;i++){
		array_name_three = array_name_two[i].split("=");
		if(i==0){
			a=array_name_three[0];
			b=array_name_three[1];
		}else{
		a+=","+array_name_three[0];
		b+=","+array_name_three[1];
		}
	}
	arr_fieldname=a.split(",");
	arr_fieldvalue=b.split(",");
if(arr_fieldname.length==2){
	get_to_post_reload(array_name[0], arr_fieldname, arr_fieldvalue);
	alert(arr_fieldname);
	arr_fieldname(arr_fieldvalue);
}

else if(arr_fieldname.length==3){
	get_to_post_reload(array_name[0], arr_fieldname, arr_fieldvalue);
}
else{
	ajax_post_newtype(array_name[0],handleajaxpostnew, arr_fieldname, arr_fieldvalue);
}
}


function menuPost_ajax(url)
{
	
//	if(url=="#"){
//		return false;
//	}

	var a="",b="";
	var array_name=new Array();
	var array_name_two=new Array();
	array_name=url.split("?");
	array_name_two=array_name[1].split("&");
	for(var i=0;i<array_name_two.length;i++){
		array_name_three = array_name_two[i].split("=");
		if(i==0){
			a=array_name_three[0];
			b=array_name_three[1];
		}else{
		a+=","+array_name_three[0];
		b+=","+array_name_three[1];
		}
	}
	arr_fieldname=a.split(",");
	arr_fieldvalue=b.split(",");
	if(arr_fieldname.length==4){
		
	 if(arr_fieldvalue[(arr_fieldvalue.length-1)]=="Staff Details"||arr_fieldvalue[(arr_fieldvalue.length-1)]=="Travel expense"){
		 $('.small_menu').modal('show');
		 
	 document.getElementById(arr_fieldvalue[(arr_fieldvalue.length-1)]).style="block";
	 }
	 else{
		 get_to_post_reloadnewpage(array_name[0], arr_fieldname, arr_fieldvalue);
		 
	 }
		
	}else{
		 if(arr_fieldvalue[(arr_fieldvalue.length-1)]=="Staff Details"||arr_fieldvalue[(arr_fieldvalue.length-1)]=="Travel expense"){
			 $('.small_menu').modal('show');
			 
		 document.getElementById(arr_fieldvalue[(arr_fieldvalue.length-1)]).style="block";
		 }
		 else{
			 get_to_post_reloadnewpage(array_name[0], arr_fieldname, arr_fieldvalue);
			 
		 }
	}
	
	
//	document.getElementById("modal-dialog modal-sm").innerHTML="test";
//	$(".small_model").Attr('data-toggle="modal" data-target=".small_menu"');
//	document.getElementById('.small_model').setAttribute('data-toggle="modal" data-target=".small_menu"');
//
//}
}


//jQuery(document).ready(function ($) {
//	
//	
//	$('#one1').click(function(){
//		$('.small_menu').modal('show');
//
//		var a="",b="";
//		var array_name=new Array();
//		var array_name_two=new Array();
//		
//		array_name=url.split("?");
//		array_name_two=array_name[1].split("&");
//		for(var i=0;i<array_name_two.length;i++){
//			array_name_three = array_name_two[i].split("=");
//			if(i==0){
//				a=array_name_three[0];
//				b=array_name_three[1];
//			}else{
//			a+=","+array_name_three[0];
//			b+=","+array_name_three[1];
//			}
//		}
//		arr_fieldname=a.split(",");
//		arr_fieldvalue=b.split(",");
//		
//		
//	  
//	  return false;
//	 
//	})
//
//	});







function handleajaxpostdivnew()
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





function handleajaxpostnew()
{
	if(http.readyState == 4)
	{//document.getElementById('popup').style.display = 'none';
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			var n=results.split("-"); 
			alert(n);
			if(n.length==2){
				arr_fieldname=['task','process_id'];
				arr_fieldvalue=[n[1],n[2]];
			}else{
				arr_fieldname=['task','formid','status','formtype','process_id'];
				arr_fieldvalue=[n[1],n[2],n[3],n[4],n[5]];
			}
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);
		}
		else
		{
		//	alert(results.substr(8));
			results=results.substr(8);
			results=results.replace("Message:","");
			alert(results);
			document.getElementById('form-login').reset();
			window.location.reload(true);
		}
	}
}

function goHomecommon(servlet,form)
{
	if(form==null){
		ins_form=null;
	}
	else{
		ins_form=document.getElementById(form);	
	}
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['goHome',document.getElementById("process_id").value];
	ajax_postbin(ins_form,servlet,response_handler,arr_fieldname,arr_fieldvalue);
}
function goHome(p_form) {
	ins_form = document.getElementById(p_form);
	arr_fieldname = [ "task", "process_id" ];
	arr_fieldvalue = [ "goAdminHome", document.getElementById("process_id").value ];
	ajax_postbin(ins_form,"user_manager",response_handler,arr_fieldname,arr_fieldvalue);
}
function response_handler()
{
	if(http.readyState == 4)
	{  
		alert("resp");
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);
		if(results.substr(0,7)!= "Message")
			{
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			
			arr_fieldvalue=[n[1],n[2]];
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);
			}
		else
		{	
			alert(results.substr(8));
		}
	}
}

function handle_estimate()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);

		////commented for updatenew document.getElementById('popup').style.display = 'none';
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




function Change_main_pass(){
	//alert("inside change password main");
	
	
	ins_form=null;
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=["changepasswordMain",document.getElementById("process_id").value];
	//alert("asdasdasdasdas");
	ajax_post_div(ins_form, "index", handle_estimatepage, arr_fieldname, arr_fieldvalue, 'container');
	
}

function handle_estimatepage()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);

		////commented for updatenew document.getElementById('popup').style.display = 'none';
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

function input_pass(){
	var pass_reset		= getRadio(document.getElementsByName('pass_reset'));
	if(pass_reset=="R"){
		document.getElementById('hide_onclick_new').className = 'hide';
		document.getElementById('hide_onclick_conf').className = 'hide';
	}else{
		document.getElementById('hide_onclick_new').className = 'visible';
		document.getElementById('hide_onclick_conf').className = 'visible';
	}
}
function change_password_admin_confirm(){
	
	ins_form=null;
	var newpass = document.getElementById('new_pass').value;
	var confirmpass = document.getElementById('confirm_pass').value;
	var selected_user = document.getElementById('user_id').value;
	var pass_reset		= getRadio(document.getElementsByName('pass_reset'));
	var msg="";
	if(pass_reset=="N") msg="Do want to Change Password"; else msg="Do want to Reset Password ?";
	if(pass_validator_admin()){
		if(confirm(msg)){
		arr_fieldname=["task","process_id","newp","confirm","selected_user","pass_reset"];
		arr_fieldvalue=['changepasswordconfirmmainAdmin',document.getElementById("process_id").value,newpass,confirmpass,selected_user,pass_reset];
		ajax_post(ins_form,'index',response_handler_pass,arr_fieldname,arr_fieldvalue);
		}
	}
}
function change_password_admin(){
	ins_form=null;
	var selected_user = '';
	var radios = document.getElementsByName("genum");

	for ( var i = 0; i < radios.length; i++) {
		if (radios[i].checked) {
			selected_user = radios[i].value;
			break;
		}
	}
	if (selected_user == '') {
		alert("Please Select a User to Change / Reset Password !");
	} 
	else {
	arr_fieldname=["task","process_id","selected_user"];
	arr_fieldvalue=["changepasswordAdmin",document.getElementById("process_id").value,selected_user];
	ajax_post_div(ins_form, "index", handle_estimatepage, arr_fieldname, arr_fieldvalue, 'container');
	}
}

function change_pwd_MAIN(captcha){
	
	
	ins_form=null;
	var oldpass = document.getElementById('old_pass').value;
	var newpass = document.getElementById('new_pass').value;
	var confirmpass = document.getElementById('confirm_pass').value;

	
	if(pass_validator_nic()){
		
		
		var retpasswd= SHA256(SHA256(oldpass)+captcha);
		
		arr_fieldname=["task","process_id","old","newp","confirm"];
		arr_fieldvalue=['changepasswordconfirmmain',document.getElementById('process_id').value,retpasswd,newpass,confirmpass];
		ajax_post(ins_form,'index',response_handler_pass,arr_fieldname,arr_fieldvalue);
	}
}

function response_handler_pass()
{
	if(http.readyState == 4)
	{   
		results = http.responseText;
								var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);

		if(results.substr(0,7)!= "Message")
			{
			alert("Successfully Updated Password");
			
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			
			arr_fieldvalue=[n[1],n[2]];
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);
			}
		else
		{	
			alert(results.substr(8));
		}
	}
}

function pass_validator_admin() {
	var pass_reset		= getRadio(document.getElementsByName('pass_reset'));
	if(pass_reset=="N"){
	var newpass = document.getElementById('new_pass').value;
	var confirmpass = document.getElementById('confirm_pass').value;
	
	if(newpass == null || newpass == ""||confirmpass == null || confirmpass == ""){
		alert("Please fill the fields ");
		return false;
	}

	if(newpass!=confirmpass){
		alert("password not matching");
		return false;
	}
	
	 if(!isLEN(confirmpass,8,100)){
		alert("min length of password is 8 characters");
		return false;
	}
	
	 if(!VAL_ispwdok(confirmpass)){
		 alert("Password should consist at least one lowercase letter, one uppercase letter, one numeric digit, and one special character");
		 return false;
	 }
	 if(!VAL_ispwdok(newpass)){
		 alert("Password should consist at least one lowercase letter, one uppercase letter, one numeric digit, and one special character");
		 return false;
	 }
	}
	return true;
}

function pass_validator_nic() {
	
	var oldpass = document.getElementById('old_pass').value;
	
	var newpass = document.getElementById('new_pass').value;
	var confirmpass = document.getElementById('confirm_pass').value;
	
	
	if(oldpass == null || oldpass == ""||newpass == null || newpass == ""||confirmpass == null || confirmpass == ""){
		alert("Please fill the fields ");
		return false;
	}

	if(newpass!=confirmpass){
		alert("password not matching");
		return false;
	}
	
	 if(!isLEN(confirmpass,8,100)){
		alert("min length of password is 8 characters");
		return false;
	}
	
	 if(!VAL_ispwdok(confirmpass)){
		 alert("Password should consist at least one lowercase letter, one uppercase letter, one numeric digit, and one special character");
		 return false;
	 }
	 if(!VAL_ispwdok(newpass)){
		 alert("Password should consist at least one lowercase letter, one uppercase letter, one numeric digit, and one special character");
		 return false;
	 }
	
	return true;
}
function PrintElem(elem)
{
	
    Popup($(elem).html());
}

function Popup1(data) 
{
    var mywindow = window.open('', '_blank');
    mywindow.document.write('<input type=\"button\" value=\"Print Div\" onclick=\"window.print()\" />');
    mywindow.document.write('<html><head><title>Data Analysis</title>');
    /*optional stylesheet*/
   mywindow.document.write('<link rel="stylesheet" href="css/price.css" type="text/css" />');
  //  mywindow.document.write('<link rel="stylesheet" href="css/menu/menustyle.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');
    //mywindow.close();
}

function PrintElem1(elem)
{
	
    Popup1($(elem).html());
}

function Popup(data) 
{
    var mywindow = window.open('', '_blank');
    mywindow.document.write('<html><head><title>my div</title>');
    /*optional stylesheet*/
  // mywindow.document.write('<link rel="stylesheet" href="css/price.css" type="text/css" />');
  //  mywindow.document.write('<link rel="stylesheet" href="css/menu/menustyle.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');

    mywindow.print();
    mywindow.close();

    return true;
}

function gotoOfficeManagement()
{
	ins_form=document.getElementById('form_index_admin');
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['goOfficeManagement',document.getElementById("process_id").value];
	ajax_post(ins_form, "index_admin", response_handler, arr_fieldname, arr_fieldvalue);
}
function gotoUserManagement()
{
	ins_form=document.getElementById('form_index_admin');
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['goUserManagement',document.getElementById("process_id").value];
	ajax_post(ins_form, "index_admin", response_handler, arr_fieldname, arr_fieldvalue);
}
function gotonewsManagement()
{
	ins_form=document.getElementById('form_index_admin');
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['gonewsManagement',document.getElementById("process_id").value];
	ajax_post(ins_form, "index_admin", response_handler, arr_fieldname, arr_fieldvalue);
}

function gotoWorkManagement()
{
	ins_form=document.getElementById('form_index_admin');
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['goWorkManagement',document.getElementById("process_id").value];
	ajax_post(ins_form, "index_admin", response_handler, arr_fieldname, arr_fieldvalue);
}
function gotohelpUserAdmin()
{
	ins_form=document.getElementById('form_index_admin');
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['goHelpUser',document.getElementById("process_id").value];
	ajax_post(ins_form, "index_admin", response_handler, arr_fieldname, arr_fieldvalue);
}
function gotohelpUser()
{
	ins_form=document.getElementById('form_index_admin');
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=['goHelpUser',document.getElementById("process_id").value];
	ajax_post(ins_form, "index", response_handler_gotohelpUser, arr_fieldname, arr_fieldvalue);
}
function response_handler_gotohelpUser()
{
	if(http.readyState == 4)
	{   
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
		results = results.replace("$"+prid+"^)", "");
		document.getElementById('popup').style.display = '';
		createInputProcess_id(prid);
		if(results.substr(0,7)!= "Message")
			{
			var n=results.split("-");
			arr_fieldname=['task','process_id'];
			
			arr_fieldvalue=[n[1],n[2]];
			get_to_post_reload(n[0],arr_fieldname,arr_fieldvalue);
			}
		else
		{	
			alert(results.substr(8));
		}
	}
}


function get_section(p_val,p_ce_code,is_sect,cir,div) {
	if(is_sect!="N"){
	document.getElementById('popup').style.display = 'block';
	ins_form = document.getElementById('form_user_management');
	arr_fieldname = [ "task","process_id","assembly","office","cir","div"];
	arr_fieldvalue = [ "getSection",document.getElementById("process_id").value,p_val,p_ce_code,cir,div];
	ajax_post_div(ins_form, "user_manager", handle_get_cir,
			arr_fieldname, arr_fieldvalue, 'section');	
	}
}
function createInputProcess_id(results){
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "process_id");
	input.setAttribute("id", "process_id");
	input.setAttribute("value", results);
	alert("process_id="+results);
	if(document.getElementById("minwidth-body")){
		alert("div present");
	}
	document.getElementById("minwidth-body").appendChild(input);
}
function handle_get_cir() {
	document.getElementById('popup').style.display = '';
	if (http.readyState == 4) {
		results = http.responseText;
		var prid=results.substring(results.lastIndexOf("$")+1,results.lastIndexOf("^)"));
   		results = results.replace("$"+prid+"^)", "");
   		document.getElementById('popup').style.display = '';
   		createInputProcess_id(prid);
		document.getElementById('popup').style.display = '';
		if (results.substr(0, 7) != "Message") {
			document.getElementById(resultDiv).innerHTML = results;

		} else {
			alert(results.substr(8));

		}
	}

}

function get_related_field_ofc(p_val,p_id,p_task,p_resultdiv,process_id)
{
	if(!VAL_N(p_val))
	{
		alert("Invalid Department Code");
		return false;
	}
	else if(!VAL_ANSP(p_id,"_"))
	{
		alert("Invalid Input Name/Id");
		return false;
	}
	else if(!VAL_ANSP(p_resultdiv,"_"))
	{
		alert("Invalid Replace Field Name/Id");
		return false;
	}
	else
	{
		arr_fieldname =["task","dept_code","input_name","process_id"];
		arr_fieldvalue =[p_task,p_val,p_id,process_id];
		ajax_post_div(ins_form, "s_usermanagement",handleofc,arr_fieldname,arr_fieldvalue,p_resultdiv);	
	}
}

function handleofc()
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

function help_doc(){
	alert("help_doc");
	//input.setAttribute("val", val);
	/*arr_fieldname =["task"];
	arr_fieldvalue =["abc"];
	get_to_post_reload("s_help_document",arr_fieldname,arr_fieldvalue);	*/
	var url="s_help_document?task=abc";
	url=getHiddentoken(url);
	window.open(url);
}

function menu_url_call(url,process_id){

	var ar_url = url.split("?");
	var urlpath =ar_url[0];
	var ar_task = ar_url[1];
	var array_name=ar_task.split("=");
	task_val = array_name[1];
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =[task_val,process_id];
	get_to_post_reload(urlpath, arr_fieldname, arr_fieldvalue);

}
/* Display Popup - Popup code is created inside Template->Doc */

function show_popup(process_id){
	Sytem.out.println("SSSSSSSSSSSSSDDDDDDDDDDDDDDDDD");
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[process_id,"viewallpending"];
	get_to_post("index", arr_fieldname,arr_fieldvalue,handle_viewall_pending);
}

function handle_viewall_pending()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			document.getElementById("viewall_window").innerHTML=results;
		$('.viewall_page').modal('show');
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}