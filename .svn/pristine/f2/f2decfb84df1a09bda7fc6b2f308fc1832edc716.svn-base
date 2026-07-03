function replace_all_in_string(stringtoreplace,find,replace){
      while( results.indexOf(find) > -1)
      {
    	  results = results.replace(find, replace);
      }
   	alert(results);
	}
//------------------------------------------------Special Ajax Functions---------------------------------------------------------//
function RotateLeft(lValue, iShiftBits) {
    return (lValue<<iShiftBits) | (lValue>>>(32-iShiftBits));
}
function p_handelFN()
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


var http = getHTTPObject();


function getHTTPObject() { 
	var xmlhttp=false; 
	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') { 
		try { xmlhttp = new XMLHttpRequest(); } 
		catch(e){ xmlhttp = false; } 
	} 
	return xmlhttp; 
}

/****************************************************Common Application Functions***************************************************************************************************/
/**
 * function to redirec to spcific page
 * @param to_pg name of servlet
 * @param p_process_id
 * @param task task in the servlet
 */
function redirect_link(to_pg,p_process_id,task){
	document.getElementById('popup').style.display = 'block';
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="process_id";
	var array_value=new Array();
	array_value[0]=task;
	array_value[1]=p_process_id;
	get_to_post_reload(to_pg, array_name, array_value);
}

function cancel(to_pg,p_process_id){
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="process_id";
	var array_value=new Array();
	array_value[0]="abc";
	array_value[1]=p_process_id;
	get_to_post_reload(to_pg, array_name, array_value);
}
/**
 * fn to toggle tab.
 * @param p_divId
 */
function process_toggle(p_divId)
{
	var act_div=document.getElementById('active_div').value;
	document.getElementById("page-"+act_div).className="hide";
	document.getElementById("page-"+p_divId).className="visible";
	document.getElementById('active_div').value=p_divId;
	document.getElementById(p_divId).className="active";
	document.getElementById(act_div).className="inactive";
}
function process_toggle_inner(p_divId ,activediv)
{
	var act_div=document.getElementById(activediv).value;
	document.getElementById("page-"+act_div).className="hide";
	document.getElementById("page-"+p_divId).className="visible";
	document.getElementById(activediv).value=p_divId;
	document.getElementById(p_divId).className="active";
	document.getElementById(act_div).className="inactive";
}



function view_uploaded(baseUrl,to_pg,p_process_id,task,appid){
	alert(pdf);
	to_pg= baseUrl + to_pg;
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="process_id";
	array_name[2]="appId";
	var array_value=new Array();
	array_value[0]=task;
	array_value[1]=p_process_id;
	array_value[2]=appid;
	get_to_post_reloadnewpage(to_pg, array_name, array_value);// since we need to see pdf in new page
}
function view_uploaded(baseUrl,to_pg,p_process_id,task,appid,userid){
	
	to_pg= baseUrl + to_pg;
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="process_id";
	array_name[2]="appId";
	array_name[3]="userid";
	var array_value=new Array();
	array_value[0]=task;
	array_value[1]=p_process_id;
	array_value[2]=appid;
	array_value[3]=userid;
	get_to_post_reloadnewpage(to_pg, array_name, array_value);// since we need to see pdf in new page
}
function get_to_post_reloadnewpage(path,arr_fieldname,arr_fieldvalue) {
	 method = "post"; 
	 var i;
	 var form = document.createElement("form");
	 form.setAttribute("method", method);
	 form.setAttribute("action", path);
	// form.setAttribute("target", "_blank");
	 for(i=0; i< arr_fieldname.length;i++)
	 {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		  var hiddenfield= getfieldobj(name, value);
	 form.appendChild(hiddenfield);
	  }
	 document.body.appendChild(form);
	 form.submit();
	}


/*function view_uploaded(baseUrl,to_pg,p_process_id,task,appid){
	to_pg= baseUrl + to_pg;
	//var appid = document.getElementById('fetch_app_id').value;
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="process_id";
	array_name[2]="appId";
	var array_value=new Array();
	array_value[0]=task;
	array_value[1]=p_process_id;
	array_value[2]=appid;
	// ajax_post(null, to_pg,handle,array_name,array_value);
	get_to_post_reloadnewpage(to_pg, array_name, array_value);
}
function get_to_post_reloadnewpage(path,arr_fieldname,arr_fieldvalue) {
	 method = "post"; 
	 var i;
	 var form = document.createElement("form");
	 form.setAttribute("method", method);
	 form.setAttribute("action", path);
	 form.setAttribute("target", "_blank");
	 for(i=0; i< arr_fieldname.length;i++)
	 {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		  var hiddenfield= getfieldobj(name, value);
	 form.appendChild(hiddenfield);
	  }
	 document.body.appendChild(form);
	 form.submit();
	}*/
function process_toggleindex(p_divId)
{
	var act_div=document.getElementById('active_div').value;
	document.getElementById("page-"+act_div).className="hide";
	document.getElementById("page-"+p_divId).className="visibleforindex";
	document.getElementById('active_div').value=p_divId;
	document.getElementById(p_divId).className="active";
	document.getElementById(act_div).className="inactive";
}

function getHiddentoken(p_url)
{
	var processid=document.getElementById('process_id').value;
	return p_url +="&process_id="+processid;
}

function get_to_post_reload_new_page(path,arr_fieldname,arr_fieldvalue) {

	 method = "post"; 
	 var i;
	 var form = document.createElement("form");
	 form.setAttribute("method", method);
	 form.setAttribute("target", "_blank");

	 form.setAttribute("action", path);
	 for(i=0; i< arr_fieldname.length;i++)
	 {
		  
		  

		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		 
		  var hiddenfield= getfieldobj(name, value);
		

	 form.appendChild(hiddenfield);
	  }
	 document.body.appendChild(form);

	 form.submit();
	}




/****************************************************Application Functions end***************************************************************************************************/
//****************************************************** *FromHere *****************************************************************************//













/*
 * 
 * 
  function getHiddentoken_post(p_url)
{
	var processid=document.getElementById('process_id').value;
	var um="POST";
	return p_url +="&process_id="+processid+"&um="+um;
}

function ajax_post_new(p_form, p_servlet,p_handelFN) 
{ 
	//document.getElementById('popup').style.display = 'block';
	var str = getFormValues(p_form);
	str = getHiddentoken_post(str);
    xmlReq = getXML(p_servlet,str,p_handelFN);   
   
}

function change_your_name(p_process_id){
	var path="change_your_name";
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="um";
	array_name[2]="process_id";
	var array_value=new Array();
	array_value[0]="abc";
	array_value[1]="GET";
	array_value[2]=p_process_id;
	//post_to_url(path, "POST");
	get_to_post_reload(path, array_name, array_value);
}


function getHiddentoken_get(p_url)
{
	var processid=document.getElementById('process_id').value;
	var um="GET";
	return p_url +="&process_id="+processid+"&um="+um+"";
}
function ajax_getDiv_new(p_url,p_divid)
{
	resultDiv 	= p_divid;
	url=getHiddentoken_get(p_url);
	http.open("POST",url,true);
	http.onreadystatechange = handleOutput;
	http.send(null);
}
function ajax_postDiv1(p_form, p_servlet, p_divid)
{ 
	p_servlet 	= getHiddentoken_post(p_servlet);
	resultDiv 	= p_divid;
	var str 	= getFormValues(p_form);
	xmlReq 		= getXML(p_servlet,str,handleOutput);    
}

function ajax_getDiv(p_url,p_divid)
{
	resultDiv 	= p_divid;
	url=getHiddentoken(p_url);
	http.open("GET",url,true);
	http.onreadystatechange = handleOutput;
	http.send(null);
}




function ajax_post_new_post(p_form, path,arr_fieldname,arr_fieldvalue,p_handelFN) 
{ 
	var form = document.getElementById(p_form);
	 for(var i=0; i< arr_fieldname.length;i++)
	  {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		  var hiddenfield= getfieldobj(name, value);
	  form.appendChild(hiddenfield);
	   }
	 form = getHiddentoken_post(form);
    xmlReq = getXML(path,form,p_handelFN);  
}



function post_to_url(path, method) {

    method = method || "post"; // Set method to post by default, if not specified.
    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    document.body.appendChild(form);
    form.submit();
}
function post_to_url_get(path, method) {
	//alert("sdf");
    method = method || "get"; // Set method to post by default, if not specified.
    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    document.body.appendChild(form);
    form.submit();
}

//-------------------------------------------------Common Handle Function--------------------------------------------------------------//



function handleHref()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
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



function validate_elements_text(value,name)
{
	
	
	if(value==""){
		alert("please enter valid "+name+" ");
		return false;	
	}
	else if (!isLEN(value,1,150)){
		alert("Please Enter Valid "+name+"");
		return false;
	}
		return true;
	
}

function validate_elements_password(value,name)
{
	
	
	if(value==""){
		alert("please enter valid "+name+" ");
		return false;	
	}
	else if (!isLEN(value,1,250)){
		alert("Please Enter Valid "+name+"");
		return false;
	}
		return true;
	
}

function validate_elements_textarea(value,name)
{
	
	
	if(value==""){
		alert("please enter valid "+name+" ");
		return false;	
	}
	else if (!isLEN(value,1,1500)){
		alert("Please Enter Valid "+name+"");
		return false;
	}
		return true;
	
}

function validate_elements_hidden(value,name)
{
	
	
	if(value==""){
		alert("please enter valid "+name+" ");
		return false;	
	}
	else if (!isLEN(value,1,500)){
		alert("Please Enter Valid "+name+"");
		return false;
	}
		return true;
	
}



function getHiddentoken(p_url)
{
	var processid=document.getElementById('process_id').value;
	return p_url +="&process_id="+processid;
}


function post_to_url_post_new(path, method,ins_form,arr_fieldname,arr_fieldvalue) {
	alert(path);
	  method = method || "post"; 
	  var i;
	  var form = document.getElementById('form-register');
	  form.setAttribute("method", method);
	  form.setAttribute("action", path);
	  for(i=0; i< arr_fieldname.length;i++)
	  {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		  alert(name+"="+value);
		  var hiddenfield= getfieldobj(name, value);
		  form.appendChild(hiddenfield);
	   }
	  document.body.appendChild(form);
	  
	  form.submit();
	}

*/
function logout(p_process_id){
	//var path='logout?task=abc&um=GET&process_id='+escape(p_process_id);
	
	var path="logout";
	var array_name=new Array();
	array_name[0]="task";
	array_name[1]="um";
	array_name[2]="process_id";
	var array_value=new Array();
	array_value[0]="abc";
	array_value[1]="GET";
	array_value[2]=p_process_id;
	//post_to_url(path, "POST");
	get_to_post_reload(path, array_name, array_value);
}


function post_to_url(path, method) {
	
    method = method || "post"; // Set method to post by default, if not specified.
    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    document.body.appendChild(form);
    form.submit();
}
function callAjax_str(Pstring, Pfilename,PhandleFN) 
{ 
   var file = Pfilename ;
   var str = Pstring; 
   xmlReq = getXML(file,str,PhandleFN);  
}

function callAjax_special(ins_form, Pstring, Pfilename,PhandleFN)
{
	var file = Pfilename ;
	var str1 = Pstring;
	var str2 = getFormValues(ins_form);
	var str =str1+"&"+str2;
	xmlReq = getXML(file,str,PhandleFN);  	
}
function get_to_post(path, arr_fieldname,arr_fieldvalue,handlefunction) {
	
	
	  method = "post"; 
	  var i;
	  var form = document.createElement("form");
	  form.setAttribute("method", method);
	  form.setAttribute("action", path);
	  for(i=0; i< arr_fieldname.length;i++)
	  {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		
		  var hiddenfield= getfieldobj(name, value);
	  form.appendChild(hiddenfield);
	   }
	  document.body.appendChild(form);
	  ajax_post(form, path,handlefunction,null,null);
	}
function get_to_post_div(path, arr_fieldname,arr_fieldvalue,p_divid) {
	resultDiv 	= p_divid;
	  method = "post"; 
	  var i;
	  var form = document.createElement("form");
	  form.setAttribute("method", method);
	  form.setAttribute("action", path);
	  for(i=0; i< arr_fieldname.length;i++)
	  {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		
		  var hiddenfield= getfieldobj(name, value);
	  form.appendChild(hiddenfield);
	   }
	  document.body.appendChild(form);
	  ajax_post(form, path,handleOutput,null,null);
	}

function ajax_post1(p_form, p_servlet,arr_fieldname,arr_fieldvalue) 
{ 
	var str=null;
	str = callform_values(p_form);
	str = add_fieldname(str,arr_fieldname,arr_fieldvalue);
    xmlReq = getXML(p_servlet,str);    
}

function get_to_post_reload1(form,path,arr_fieldname,arr_fieldvalue) {
	 method = "post"; 
	 var i;
	 
	// var form = document.createElement("form");
	 form.setAttribute("method", method);
	 form.setAttribute("action", path);
	 for(i=0; i< arr_fieldname.length;i++)
	 {
		  var name=arr_fieldname[i];
		  var value=arr_fieldvalue[i];
		  var hiddenfield= getfieldobj(name, value);
	 form.appendChild(hiddenfield);
	  }
	 document.body.appendChild(form);
	 form.submit();
	 ajax_post1(form, path,null,null);
	// document.getElementById('popup').style.display = 'none';
	}

//ajax_post_form <- ajax_post
