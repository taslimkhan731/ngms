//----------------------------------------------------------Common Ajax Functions--------------------------------------------------------//
var xmlReq = null;
var resultDiv=null;



// Required - General
function add_fieldname(str,arr_fieldname,arr_fieldvalue)
{
 if(arr_fieldname == null) return str;
 
 	for(var i=0; i< arr_fieldname.length; ++i)
		{
		str=str+"&"+arr_fieldname[i]+"="+escape(arr_fieldvalue[i]);
		
		}
	return str; 
}

//Required - General
function getfieldobj(fieldname, fieldvalue)
{
var hiddenField=document.createElement("input");  
hiddenField.type = 'hidden';
hiddenField.setAttribute("name", fieldname);
hiddenField.setAttribute("value", fieldvalue);
return hiddenField;
}

//Required // Required - General

function ajax_post_form_field(p_form, p_servlet,p_handelFN,arr_fieldname,arr_fieldvalue) 
{ 
	var str=null;
	str = callform_values(p_form);
	str = add_fieldname(str,arr_fieldname,arr_fieldvalue);
    xmlReq = getXML(p_servlet,str,p_handelFN);    
}
//Required // Required - General
function ajax_post_form(p_form, p_servlet,p_handelFN) 
{ 
	var str=null;
	str = callform_values(p_form);
    xmlReq = getXML(p_servlet,str,p_handelFN);    
}

//Required // Required - General
function ajax_post_field(p_servlet,p_handelFN,arr_fieldname,arr_fieldvalue) 
{ 
	var str;
	str = add_fieldname(str,arr_fieldname,arr_fieldvalue);
    xmlReq = getXML(p_servlet,str,p_handelFN);    
}

function ajax_post_form_field_div(p_form, p_servlet,p_handelFN,arr_fieldname,arr_fieldvalue,p_divid) 
{ 
	resultDiv 	= p_divid;
	ajax_post_form_field(p_form, p_servlet,p_handelFN,arr_fieldname,arr_fieldvalue);
	return;
}

function ajax_post_form_div(p_form, p_servlet,p_handelFN,p_divid) 
{ 
	resultDiv 	= p_divid;
	ajax_post_form(p_form, p_servlet,p_handelFN);
	return;
}

function ajax_post_field_div(path,p_handelFN, arr_fieldname,arr_fieldvalue,p_divid)
{
	resultDiv 	= p_divid;
	ajax_post_field(path, p_handelFN,arr_fieldname,arr_fieldvalue);
}


function ajax_post_form_field_divH(p_form, p_servlet,arr_fieldname,arr_fieldvalue,p_divid) 
{ 
	$('#popup').show();
	resultDiv 	= p_divid;
	ajax_post_form_field(p_form, p_servlet,handleOutput,arr_fieldname,arr_fieldvalue);
	return;
}

function ajax_post_form_divH(p_form, p_servlet,p_handelFN,p_divid) 
{ 
	$('#popup').show();
	resultDiv 	= p_divid;
	ajax_post_form(p_form, p_servlet,handleOutput);
	return;
}

function ajax_post_field_divH(path, arr_fieldname,arr_fieldvalue,p_divid)
{
	$('#popup').show();
	resultDiv 	= p_divid;
	ajax_post_field(path, handleOutput,arr_fieldname,arr_fieldvalue);
	return;
}


function ajax_post_form_field_urlH(p_form, p_servlet,arr_fieldname,arr_fieldvalue,p_divid) 
{ 
	
	ajax_post_form_field(p_form, p_servlet,'handleURL',arr_fieldname,arr_fieldvalue);
	return;
}

function ajax_post_form_urlH(p_form, p_servlet,p_handelFN,p_divid) 
{ 
	
	ajax_post_form(p_form, p_servlet,'handleURL');
	return;
}

function ajax_post_field_urlH(path, arr_fieldname,arr_fieldvalue,p_divid)
{
	
	ajax_post_field(path, 'handleURL',arr_fieldname,arr_fieldvalue);
	return;
}

function getpath(str)
{
	//str="path=s_modearaotr;task=abc;processid=4534543;pageno=122";
	
	arr_data=str.split(";");
	var arr_fieldname=[];
	var retvalue='';
	var i=0;
	for(i=0;i<arr_data.length;i++)
		{
	temp =	arr_data[i].split("=");
	arr_fieldname[i]=temp[0];
	if(temp[0]=='path') retvalue = temp[1];
		
		}
	
	return retvalue;
	
	}
function getarray_fieldname(str)
{
	//str="path=s_modearaotr;task=abc;processid=4534543;pageno=122";
	
	arr_data=str.split(";");
	var arr_fieldname=[];
	var i=0;
	for(i=0;i<arr_data.length;i++)
		{
	temp =	arr_data[i].split("=");
	arr_fieldname[i]=temp[0];
		
		}
	
	
	return arr_fieldname;
	
	}
function getarary_fiedlvalue(str)
{
	//str="path=s_modearaotr;task=abc;processid=4534543;pageno=122";
	//arr_fieldvalue =["moderator","abc","454545","122"];
	
	arr_data=str.split(";");
	var arr_fieldvalue=[];
	var i=0;
	for(i=0;i<arr_data.length;i++)
		{
	temp =	arr_data[i].split("=");
	arr_fieldvalue[i]=temp[1];
		
		}
	
	
	return arr_fieldvalue;
	
	}

function handleURL()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		

		if(results.substr(0,7)!= "Message")
			{
		path = getpath(results);
		arr_fieldname = getarray_fieldname(results);
		arr_fieldvalue = getarray_fieldvalue(results);
		post_field(path,arr_fieldname,arr_fieldvalue,false);
			
		
			
			}
		else
		{
			alert(results.substr(8));
			
		}
	}

}


function handleOutput()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		
		////commented for updatenew document.getElementById('popup').style.display = 'none';
		if(results.substr(0,7)!= "Message")
			{
			$('#popup').hide();
			//console.log(results)
			document.getElementById(resultDiv).innerHTML=results;
		
			
			}
		else
		{
			alert(results.substr(8));
			$('#popup').hide();
		}
	}

}






function show_loading()
{
	document.getElementById('loading').style.display = 'block';
}

function post_field(path,arr_fieldname,arr_fieldvalue,is_new_win) {
//reloading page after submitting
 method = "post"; 
 var i;
 var form = document.createElement("form");
 form.setAttribute("method", method);
 form.setAttribute("action", path);
 if(is_new_win) 
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
 //show_loading();
}



function post_form_field(form,path,arr_fieldname,arr_fieldvalue,is_new_win)
{
	 method = "post"; 
	 var i;
	 
	// var form = document.createElement("form");
	 form.setAttribute("method", method);
	 form.setAttribute("action", path);
	 if(is_new_win)
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
	 
	// document.getElementById('popup').style.display = 'none';
	}



function getXML(file,str, ins_handleFN) 
{ 
	
   http.open("POST", file, true); 
   http.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"); 
   http.onreadystatechange = function(){ ins_handleFN();};// if(http.readyState == 4) document.getElementById('popup').style.display = 'none';};
   http.send(str);
   return http;
}

function getFormValues(fobj) 
{ 
	
   var str = ""; 
   for(var i = 0;i < fobj.elements.length;i++) 
   { 
      
	   switch(fobj.elements[i].type) 
       { 
		case "text": 
//			if(validate_elements_text(fobj.elements[i].value,fobj.elements[i].name)){
			
		   str += fobj.elements[i].name +"="+encodeURIComponent(fobj.elements[i].value) + "&"; 
//			}
		break; 
		
		case "password": 
//			if(validate_elements_password(fobj.elements[i].value,fobj.elements[i].name)){
			str += fobj.elements[i].name +"="+ encodeURIComponent(fobj.elements[i].value) + "&"; 
//			}
		break; 
		
		case "textarea": 
//			if(validate_elements_textarea(fobj.elements[i].value,fobj.elements[i].name)){
			str += fobj.elements[i].name +"="+ encodeURIComponent(fobj.elements[i].value) + "&"; 
//			}
		break; 
		
		case "radio": 
			if(fobj.elements[i].checked)
				 str += fobj.elements[i].name +"="+ encodeURIComponent(fobj.elements[i].value)+ "&";
		break; 
		
		case "checkbox": 
			if(fobj.elements[i].checked)
				str += fobj.elements[i].name +"="+ encodeURIComponent(fobj.elements[i].value)+ "&"; 
		break; 
		
		case "hidden": 
//			if(validate_elements_hidden(fobj.elements[i].value,fobj.elements[i].name)){
			str += fobj.elements[i].name + "=" + encodeURIComponent(fobj.elements[i].value) + "&";
//			}
		break; 
		
		case "select-one":
			str += fobj.elements[i].name +"=" + encodeURIComponent(fobj.elements[i].options[fobj.elements[i].selectedIndex].value) + "&"; 
		break;
       } 
   } 
   str = str.substr(0,(str.length - 1)); 
   return str;
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




function callform_values(ins_form) 
{ 
   var str = getFormValues(ins_form); 
  
   return str;
}



//////for pdf/////////////

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
	}



function view_uploaded1(baseUrl,to_pg,p_process_id,task,appid){
	 
	to_pg= baseUrl + to_pg;
	var array_name=new Array();
	array_name[0]="task";
	array_name[2]="process_id";
	array_name[3]="appId";
	var array_value=new Array();
	array_value[0]=task;
	array_value[2]=p_process_id;
	array_value[3]=appid;


	get_to_post_reloadnewpage(to_pg, array_name, array_value);// since we need to see pdf in new page
}

function view_uploaded11(baseUrl,to_pg,p_process_id,task,appid,reqid){
	//alert("jaxxxxxx    "+reqid); 
	to_pg= baseUrl + to_pg;
	var array_name=new Array();
	array_name[0]="task";
	array_name[2]="process_id";
	array_name[3]="appId";
	array_name[4]="reqid";
	var array_value=new Array();
	array_value[0]=task;
	array_value[2]=p_process_id;
	array_value[3]=appid;
	array_value[4]=reqid;


	get_to_post_reloadnewpage(to_pg, array_name, array_value);// since we need to see pdf in new page
}

function vieww()
{
	//alert("dfsdhfhsdjf");
}




