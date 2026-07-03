function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_billdetails", arr_fieldname, arr_fieldvalue, 'header_div');
}

//pagination list
function pagination_list(page_no,gh_id){
	//VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id"];
	arr_fieldvalue =[getprocessid(),"search",page_no,gh_id];
	ajax_post_field_divH("s_billdetails", arr_fieldname,arr_fieldvalue,'list');
}


//pdf
function viewpdf(rec_id)
{
	//var ins_form= document.getElementById('form-general');
	var gh_id  = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","rec_id","gh_id"];
	arr_fieldvalue =['printpdf',getprocessid(),rec_id,gh_id];	
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
	
}

function billdetails(){
	//alert("hhhhhh");
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
	arr_fieldvalue=["search",getprocessid(),gh_id,'1',sel_block];
	//alert(arr_fieldvalue);
	ajax_post_field_div("s_billdetails",handleallot, arr_fieldname,arr_fieldvalue,'list');
	}
}


function handleallot(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			// document.getElementById('popup').style.display = 'none';
		}else{
			nic_alert(results.substr(8),"N");
			 //document.getElementById('popup').style.display = 'none';
		}
	}
}
function searchdataa(){
	var billno  = document.getElementById('billno').value;
	var gh_id  = document.getElementById('sel_id').value;
	var name  = document.getElementById('name').value;
	//if(VAL_submit())
	//{
	if(billno==""&&name==""){
		nic_alert("Please Enter data for searching","N");
	}else{
	arr_fieldname=["task","process_id","billno","gh_id","name"];
	arr_fieldvalue=["doprocessavailabilty",getprocessid(),billno,gh_id,name];
	ajax_post_field_div("s_billdetails",handlebill, arr_fieldname,arr_fieldvalue,'changers');
	}
	//}
}
function handlebill(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		/*show_nicpopup(results); 
		$('.disable_room').hide();
		 $('.enable_room').show();*/
		
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}

function VAL_submit(){
	 
	var from  = document.getElementById('fromdate').value;
	var to  = document.getElementById('todate').value;
	if((val_fromdate(from)) && (val_to_date(to)))
		return true;
}
//validate to_date
function val_to_date(p_val){
	var from  = document.getElementById('fromdate').value;
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to date.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			nic_alert("Invalid Date.","V");
			return false;
		}
		else if(dateCompTwo(p_val,from))
		{
		nic_alert("invalid from date,Only allowed from date less than to date","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the to date","V");
		return false;
	}
	return true;
}