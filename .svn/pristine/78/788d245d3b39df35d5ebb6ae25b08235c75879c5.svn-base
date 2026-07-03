//Room Rent Details

$(document).on('click', '#family', function () {
	var cx=	$('#family:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});

$(document).on('click', '#mode_payment', function () {
	var selected_val=	$('#mode_payment:checked').map(function() {return this.value;}).get().join("','");
		if(selected_val=="D")
		{
		$('.hide-others').removeClass('hide');
		$('.hide-others').addClass('show');
		}
	else 
		{
	
		
		$('.hide-others').addClass('hide');
		
		//$('.hide-others').attr('class','hide-others f hide');
		
		
		
		};
 });

//pagination list
function pagination_list(page_no){
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_rent", arr_fieldname,arr_fieldvalue,'add');
}

//click Add rent submenu
function addrent()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addrent",getprocessid()];
	ajax_post_field_div("s_rent",handle_addrent,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addrent()
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

//click edit rent submenu
function editrent(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editrent",getprocessid(),page_no];
	ajax_post_field_div("s_rent",handle_editrent,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editrent()
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

//click Delete rent submenu
function deleterent(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleterent",getprocessid(),page_no];
	ajax_post_field_div("s_rent",handle_deleterent,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleterent()
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



//click save rent

function add_rententry()
{
	if(VAL_saverent())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["saverententry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_rent",handle_save_rententry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_rententry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addrent();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit rent

function edit_rententry(rent_id,vc_id,type,family)
{
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id","rent_id","vc_id","type","family"];
		arr_fieldvalue=["editrententry",getprocessid(),rent_id,vc_id,type,family];
		ajax_post_form_field_div(ins_form,"s_rent",handle_editrent,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editrent()
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

//click edit rent

function update_rent(rent_id)
{
	if(VAL_updaterent())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","rent_id"];
			arr_fieldvalue=["updaterententry",getprocessid(),rent_id];
			ajax_post_form_field_div(ins_form,"s_rent",handle_update_rententry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_rententry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editrent('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_rententry(rent_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","rent_id"];
		arr_fieldvalue=["deleterentlist",getprocessid(),rent_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_rent",handledeletelist,arr_fieldname,arr_fieldvalue);
}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleterent('1');
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
	post_field("s_rent",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

$(document).on('click', '#family', function () {
	var cx=	$('#family:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});

$(document).on('click', '#mode_payment', function () {
	var cx=	$('#mode_payment:checked').map(function() {return this.value;}).get().join("','");
	$('#hid').val(cx);
});

function VAL_saverent(){
	var from_date  = document.getElementById('f_date').value;
	var to_date  = document.getElementById('t_date').value;
	var vc_id  = document.getElementById('sel_vc').value;
	var type  = document.getElementById('type').value;
	var family  = document.getElementById('hidvalue').value;
	var rent  = document.getElementById('rent').value;
	var mode  = document.getElementById('hid').value;		
	if(((valvcid(vc_id)) && (val_purpose(type)) && (val_family(family)) && (val_mode_pay(mode)) && val_fromdate(from_date)) && (val_to_date(to_date)) && (val_rent(rent)))
		return true;
	}

function VAL_updaterent(){
	var from_date  = document.getElementById('f_date').value;
	var to_date  = document.getElementById('t_date').value;
	var rent  = document.getElementById('rent').value;
			
	if((val_fromdate(from_date)) && (val_to_date(to_date)) && (val_rent(rent)))
		return true;
	}
//validate purpose
function val_family(p_val){
	if(p_val==""){
		nic_alert("Please Select the with family or without family","V");
		return false;
	} 
	return true;
}

//validate mode of payment
function val_mode_pay(p_val){
	if(p_val==""){
		nic_alert("Please Select the mode of payment","V");
		return false;
	} 
	return true;
}


function val_rent(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to rent.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid rent,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the rent","V");
		return false;
	}
	return true;
}
//validate vc_id
function valvcid(p_val){
 	if(p_val==0) 
	{	
		nic_alert("Please select the designation category","V");
		return false;
	}  
	return true; 
}


function val_to_date(p_val){
	var from_date  = document.getElementById('f_date').value;
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
		else if(dateCompTwo(p_val,from_date))
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


