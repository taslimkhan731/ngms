/*function showEditGuestDetails(page_no,div)
{
		val_ghid(gh_id);
		var gh_id = document.getElementById('sel_id').value;
		if(gh_id==0){
			nic_alert("Please Select the guest house","V");
		}else{
			$('#popup').show();
			arr_fieldname =["task","gh_id","process_id","page_no"];
			arr_fieldvalue =['listeditguestdetails',gh_id,getprocessid(),page_no];	
			ajax_post_field_div("s_dataentry",handle_showEditGuestDetails,arr_fieldname,arr_fieldvalue,div);//'listdiv' 'home_value'
		}
}

function handle_showEditGuestDetails()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

function editGuestDetails(req_id){
	gh_id  = document.getElementById('sel_id').value;
	val_ghid(gh_id);
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id","page_no"];
		arr_fieldvalue =["editguestdetails",getprocessid(),gh_id,req_id,"1"];
		ajax_post_field_div( "s_dataentry",handle_editGuestDetails,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}

function handle_editGuestDetails()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function doeditGuestDetails()
{
		var ins_form= document.getElementById('formmod');
		req_id1  = document.getElementById('reqid').value;
		count=document.getElementById('rowCnt').value;
		var checkedval;
		var extval;
		if($("#chk").is(":checked")){
			if(VAL_savevehicle())return true;
			checkedval = 1;
			}
		else{
			checkedval = 0;
			}
		
		if($("#ext").is(":checked")){
			extval = 1;
			}
		else{
			extval = 0;
			}	
		if($("#family").is(":checked")){
			familyval = 1;
			}
		else{
			familyval = 0;
			}
		if(VAL_savedataentry()){
			arr_fieldname=["task","process_id","checkedval","extval","req_id","familyval"];
			arr_fieldvalue=["editformdata",getprocessid(),checkedval,extval,req_id1,familyval];
			ajax_post_form_field(ins_form,"s_dataentry",handle_doeditGuestDetails,arr_fieldname,arr_fieldvalue);
		}
}

function handle_doeditGuestDetails(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			 
			nic_alert("Guest Details updated Successfully.","N");
			
		close_nicpopup();
		showEditGuestDetails('1');
						
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function VAL_savevehicle(){
	var mode_of_arrival  = document.getElementById('mode').value;
	var arrival_time  = document.getElementById('arrival').value;
	var arrival_place  = document.getElementById('place').value;
	var no_seats_req  = document.getElementById('seat').value;
	var ftno = document.getElementById('ftno').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_no_seats_req(no_seats_req)) && (val_arrival_time(arrival_time)) && (val_arrival_place(arrival_place)) ) return false;
	return true;

}

function val_mode_of_arrival(p_val){
	if(p_val=='s') 
	{
		nic_alert("Please select the mode of arrival","V");
		return false;
	} else{
	return true;
	}
}

//validate arrival_place
function val_arrival_place(p_val){
	if(p_val=="") 
	{
		nic_alert("Please enter the arrival place","V");
		return false;
	}
	else if(!isLEN(p_val,1,100))
	{
		nic_alert("Invalid Length for arrival place.","V");
		return false;
	}
	if(!isvalidaddress(p_val))
	{
		nic_alert("Invalid arrival place, only allow AlphaNumeric.","V");
		return false;
	}
	return true;
}


//validate arrival_time
function val_arrival_time(p_val){
	
	var from_date  = document.getElementById('from').value;
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for arraival time.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			nic_alert("Invalid arraival time.","V");
			return false;
		}
		else if(dateCompTwo(from_date,p_val))
		{
		nic_alert("invalid Arrival Time,Only allowed Arrival date less than from date","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the arraival time","V");
		return false;
	}
	return true;
}

//validate no_seats_req
function val_no_seats_req(p_val){
	if(p_val=="") 
	{
		nic_alert("Please enter the no of seats req","V");
		return false;
	}
	else if(!isLEN(p_val,1,10))
	{
		nic_alert("Invalid Length for no of seats req.","V");
		return false;
	}
	if(!VAL_N(p_val))
	{
		nic_alert("Invalid no of seats req, only allow Numeric.","V");
		return false;
	}
	return true;
}


//validate flight number
function val_ftno(p_val){
	if(p_val=="") 
	{
		nic_alert("Please enter Flight/Train no","V");
		return false;
	}
	else if(!isLEN(p_val,1,50))
	{
		nic_alert("Invalid Length for Flight/Train no.","V");
		return false;
	}
	if(!isANS(p_val))
	{
		nic_alert("Invalid Flight/Train no, only allow Numeric.","V");
		return false;
	}
	return true;
}





//validate vc_id
function val_vcid(p_val){
 	if(p_val=='z') 
	{	
		nic_alert("Please select the designation category","V");
		return false;
	}  
	return true; 
}

//validate quota
function val_type(p_val){
	 
	if(p_val==0) 
	{
		nic_alert("Please select the quota type","V");
		return false;
	} else{
	return true;
	}
}



//validate block_id
function val_blockid(p_val){
		if(p_val==null) 
		{
			nic_alert("block_id is Null","N");
			return false;
		}
		else if(p_val.match(/^[\/ &a-zA-Z0-9]+$/))
		{ 
			if(!isLEN(p_val,1,10))
			{
				nic_alert("Invalid Length for block_id. Min(1) and Max(10).","N");
				return false;
			}
			return true;
		}
		else
		{
			nic_alert("Invalid block_id. Allowed Characters are a-z A-Z 0-9 / space","N");
			return false;
		} 
}

function val_userid(p_val)
{
	if(p_val=="") 
	{
		nic_alert("Please enter the user id","V");
		return false;
	}
	else if(p_val.match(/^[-\/ &a-zA-Z0-9]+$/)) //(/^[\/ &a-zA-Z0-9]+$/)) 
	{ 
		if(!isLEN(p_val,1,25))
		{
			nic_alert("Invalid Length for userid. Min(1) and Max(25).","V");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Invalid userid. Allowed Characters are a-z A-Z 0-9","V");
		return false;
	} 
}

function VAL_savedataentry(){
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var email_id= document.getElementById('email').value;
	if((val_fromdate(from_date)) && (val_todate(to_date)) && (val_purpose(purpose)) &&(val_mobileno(mobile_no)) && (val_emailid(email_id)) && (VAL_roommf()) && (val_address(address1)) && (VAL_guest()))
		if($("#chk").is(":checked")){
		var arrival_time  = document.getElementById('arrival').value;
	 	var temp_from = from_date.split(" ")[0]; 
	 	var temp_arrival = arrival_time.split(" ")[0];
		if(temp_arrival!=temp_from){
			if(confirm("From date and arrived date are different, Is it OK?")){
				return true;
			}
			return false;
		}else{
			return true;
		}
		}else{
			return true;
		}
	}

function VAL_roommf(){
	var nor_req  = document.getElementById('rooms').value;
	var nob_g_req  = document.getElementById('nbg').value;
	var nob_l_req  = document.getElementById('nbl').value;
	var nod_g_req  = document.getElementById('ndg').value;
	var nod_l_req  = document.getElementById('ndl').value;
	
	if((nor_req==0) && (nob_g_req==0) && (nob_l_req==0) && (nod_g_req==0) && (nod_l_req==0))
	//if(((val_rooms(nor_req)) || (val_nbg(nob_g_req)) || (val_nbl(nob_l_req)) || (val_ndg(nod_g_req)) || (val_ndl(nod_l_req))))  
		{
		nic_alert("Please enter the any one data(room,male beds,female beds,male dormitory,female dormitory","V");
		return false;
		}
	else{
		if(nor_req>0){ 
			val_rooms(nor_req);
			
		}
		else if(nob_g_req>0){ 
			val_rooms(nor_req);
			
		}
		else if(nob_l_req>0){ 
			val_rooms(nor_req);
			
		}
		else if(nod_g_req>0){ 
			val_rooms(nor_req);
			
		}
		else if(nod_l_req>0){ 
			val_rooms(nor_req);
			
		}
	}
		return true;
}

function VAL_guest(){
	count=document.getElementById('rowCnt').value;
	var a=0;
	
	for(var i=0;i<count;i++)
	{
		var name  = document.getElementById('name'+i).value;
		var age	=document.getElementById('age'+i).value;
		var gender=document.getElementById('gender'+i).value;
		var desg  = document.getElementById('desg'+i).value;
		var dept	=document.getElementById('dept'+i).value;
		var address=document.getElementById('address'+i).value;
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) && (val_designation(desg)) && (val_department(dept)) && (val_address1(address)))
		{
			a=1;
		}
		else
			{
			a=0;
			}
		
	}	
	if(a==1)
		return true;
}
*/