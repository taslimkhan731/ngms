//pagination list
function pagination_list(page_no){
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_basicpay", arr_fieldname,arr_fieldvalue,'add');
}

//click Add basicpay submenu
function addbasicpay()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addbasicpay",getprocessid()];
	ajax_post_field_div("s_basicpay",handle_addbasicpay,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addbasicpay()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//end

//click edit basicpay submenu
function editbasicpay(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editbasicpay",getprocessid(),page_no];
	ajax_post_field_div("s_basicpay",handle_editbasicpay,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editbasicpay()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//end

//click Delete basicpay submenu
function deletebasicpay(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletebasicpay",getprocessid(),page_no];
	ajax_post_field_div("s_basicpay",handle_deletebasicpay,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletebasicpay()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//end



//click save basicpay

function add_basicpayentry()
{
	//if(VAL_savebasicpay())
		//{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["savebasicpayentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_basicpay",handle_save_basicpayentry,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_save_basicpayentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addbasicpay();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit basicpay

function edit_basicpayentry(da_id)
{
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id","da_id"];
		arr_fieldvalue=["editbasicpayentry",getprocessid(),da_id];
		ajax_post_form_field_div(ins_form,"s_basicpay",handle_editbasicpay,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editbasicpay()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//
//click edit basicpay

function update_basicpay(da_id)
{
	//if(VAL_updatebasicpay())
		//{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","da_id"];
			arr_fieldvalue=["updatebasicpayentry",getprocessid(),da_id];
			ajax_post_form_field_div(ins_form,"s_basicpay",handle_update_basicpayentry,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_update_basicpayentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editbasicpay('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_basicpayentry(da_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","da_id"];
		arr_fieldvalue=["deletebasicpaylist",getprocessid(),da_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_basicpay",handledeletelist,arr_fieldname,arr_fieldvalue);
}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletebasicpay('1');
			//menu_url_call('s_publicdataentry?task=abc',getprocessid());
			//document.getElementById(resultDiv).innerHTML=results;
			 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_basicpay",arr_fieldname,arr_fieldvalue,false);
}
//
//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

$(document).on('click', '#family', function () {
	var cx=	$('#family:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});


function VAL_savebasicpay(){
	alert("A");
	var grade_title  = document.getElementById('grade_title').value;
	alert("C");
	var effect_date_from  = document.getElementById('effect_date_from').value;
	alert("D");
	var effect_date_to  = document.getElementById('effect_date_to').value;
	alert("E");
	var basis_pay_from  = document.getElementById('bpay_from').value;
	alert("F");
	var basis_pay_to  = document.getElementById('bpay_to').value;
	alert("G");
	var da_value  = document.getElementById('da_value').value;
			
	if((val_grade_title(grade_title)) && (val_effect_date_from(effect_date_from)) && (val_effect_date_to(effect_date_to)) && (val_basis_pay_from(basis_pay_from)) && (val_basis_pay_to(basis_pay_to)) && (val_da_value(da_value)))
		return true;
	}



function val_basis_pay_from(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for to basic pay from.","V");
			return false;
		}
		else if(!VAL_ND(p_val)){
			nic_alert("Invalid basic pay from,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the basic pay from","V");
		return false;
	}
	return true;
}

function val_basis_pay_to(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for to basic pay to.","V");
			return false;
		}
		else if(!VAL_ND(p_val)){
			nic_alert("Invalid basic pay to,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the basic pay to","V");
		return false;
	}
	return true;
}

function val_da_value(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for da vlue.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid da value,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the da value","V");
		return false;
	}
	return true;
}


function val_da_id(p_val){
	alert(p_val);
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to basicpay id.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid basicpay id,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the basicpay id","V");
		return false;
	}
	return true;
}

function val_grade_title(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for to grade title.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid grade title,only allowed alpha numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the grade title","V");
		return false;
	}
	return true;
}

//validate from_date
function val_effect_date_from(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			 
			nic_alert("Invalid Length for from date.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			 
			nic_alert("Invalid Date.","V");
			return false;
		}
		
	}
	else
	{
		nic_alert("Please enter the from date","V");
		return false;
	}
	return true;
}

function val_effect_date_to(p_val){
	var effect_date_from  = document.getElementById('effect_date_from').value;
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
		else if(dateCompTwo(p_val,effect_date_from))
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
//
//
