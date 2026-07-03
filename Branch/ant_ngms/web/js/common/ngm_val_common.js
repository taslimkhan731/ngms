//validate pageno

function val_pageno(p_val)
{
	if(p_val==null) 
	{
		nic_alert("pageno is Null","N");
		return false;
	}
	else if(p_val.match(/^[\/ &a-zA-Z0-9]+$/))
	{ 
		if(!isLEN(p_val,1,10))
		{
			nic_alert("Invalid Length for pageno. Min(1) and Max(10).","N");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Invalid pageno. Allowed Characters are a-z A-Z 0-9 / space","N");
		return false;
	} 
}


//validate gh_id
function val_ghid(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the guesthouse","V");
		return false;
	}
	return true;
}

function val_file(p_val){
	if(p_val.length >= 250) 
	{
		nic_alert("File length should not be greater than 250","V");
		return false;
	}
	return true;
}

function val_file_purpose(p_val,purpose){
	if(p_val == "" && purpose == 'Patient') 
	{
		nic_alert("File should not be blank","V");
		return false;
	}else if(p_val.length >= 250) 
	{
		nic_alert("File length should not be greater than 250","V");
		return false;
	}
	return true;
}

//validate from_date
function val_fromdate(p_val){
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

function val_fromdate_public(p_val){
	var today = new Date();
	var from_date = (today.getDate() < 10 ? '0' : '') + today.getDate()+'-'+((today.getMonth() + 1) < 10 ? '0' : '') + (today.getMonth() + 1)+'-'+today.getFullYear();
	console.log("Date ")
	console.log(p_val)
	console.log(from_date)
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){

			nic_alert("Invalid Length for from date.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){

			nic_alert("Invalid Date.","V");
			return false;
		}else if(dateCompTwo(p_val,from_date))
		{
			nic_alert("invalid from date,Date should not be less than current date","V");
			return false;
		}else if(datediff_public(p_val,from_date))
		{
			nic_alert("Only 7 days booking possible from current date","V");
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

//validate from_date it start from only today, not take previous date

function val_fromdatenottakeprevusdate(p_val){
	if(p_val!="")
	{
		var today = new Date();
		var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();

		var time = today.getHours() + ":" + today.getMinutes();
		var dateTime = date+' '+time;
		var firstValue=date.split("-");
		var firstDate=new Date();
		firstDate.setFullYear(firstValue[2],(firstValue[1] - 1 ),firstValue[0]);
		var secondValue =p_val.split("-");
		var secondDate=new Date();
		secondDate.setFullYear(secondValue[2],(secondValue[1] - 1 ),secondValue[0]); 
		if (firstDate > secondDate)
		{
			nic_alert("Invalid Date.","V");
			return false;
		}
		else
		{
			return true;
//			alert("Second Date  is greater than First Date");
		}


//		if(!(+p_val >= +dateTime)){


//		}
	}else
		return true;

}




//val_guest_id
function val_guest_id(p_val){

	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to guest ID.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid guest ID.","V");
			return false;
		}
	}
	else
	{
		nic_alert("No guest ID","V");
		return false;
	}
	return true;
}
//val_charge
function val_charge(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for amount.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid amount.","V");
			return false;
		}

	}
	else
	{
		nic_alert("No amount for particulars","V");
		return false;
	}
	return true;
}
///val_gh_id
function val_gh_id(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for Guest House ID.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid Guest House ID.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please select the Guest House ID","V");
		return false;
	}
	return true;
}
//val_req_id
function val_req_id(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for Request ID.","V");
			return false;
		}
		/*else if(!VAL_ANH(p_val)){
			nic_alert("Invalid Request ID.","V");
			return false;
		}*/
	}
	else
	{
		nic_alert("No Guest House ID","V");
		return false;
	}
	return true;
}

function val_rollnumber(p_val){
	if(p_val != "")
	{
		if(!isLEN(p_val,1,10))
		{
			nic_alert("Allowed Length of Roll No. is in between 1 and 100","N");
			return false;
		}
		else if(!VAL_N(p_val))
		{
			nic_alert("Please Enter Valid Roll No. Allowed Numeric Only.","N");
			return false;
		}
	}
	else{
		nic_alert("Please Enter the Roll No.","V");
		return false;
	}
	return true;
}
//validate to_date
function val_todate(p_val){
	var from_date  = document.getElementById('from').value;
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
		else if(datediff(p_val,from_date))
		{
			nic_alert("Only 5 days booking possible","V");
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
function val_todateone(p_val){
	var from_date  = document.getElementById('from').value;
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
		else if(datediff_one(p_val,from_date))
		{
			nic_alert("Only 1 days booking possible","V");
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
//validate to_date
function val_todate_purpose(p_val,purpose){
	var from_date  = document.getElementById('from').value;
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
		}else if(purpose == '3'){
			if(datediff_purpose(p_val,from_date))
			{
				nic_alert("Only 7 days booking possible","V");
				return false;
			}
		}else if(purpose == '1'){
			return true;
		}else if(datediff(p_val,from_date))
		{
			nic_alert("Only 5 days booking possible","V");
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

function val_todate_notice(p_val,from_date){
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

function val_todateonduty(p_val){
	var from_date  = document.getElementById('to').value;
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
			nic_alert("invalid to date,Only allowed to date less than to date","V");
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

//validate number of rooms
function val_rooms(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,4)){
			nic_alert("Invalid Length for number of" +p_val,"V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of "+p_val, "only allow numeric.","V");
			return false;
		}

	}
	else
	{
		//	nic_alert("Please enter the number of "+p_val +"","V");
		return false;
	}
	return true;
}

//validate search
function val_search(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for number of" +p_val,"V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid number of "+p_val, "only allow numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the number of "+p_val +"","V");
		return false;
	}
	return true;
}

//validate number of  beds gends
function val_nbg(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,4)){
			nic_alert("Invalid Length for number of rooms in gents.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of rooms in gents, only allow numeric.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the number of rooms in gents","V");
		return false;
	}
	return true;
}

//validate number of  beds gends
function val_nbl(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,4)){
			nic_alert("Invalid Length for number of rooms in ladies.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of rooms in ladies, only allow numeric.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the number of rooms in ladies","V");
		return false;
	}
	return true;
}



//validate number of  beds gends(dormitory)
function val_ndg(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,4)){
			nic_alert("Invalid Length for number of rooms in gents(Dormitory).","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of rooms in gents, only allow numeric(Dormitory).","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the number of rooms in gents(Dormitory)","V");
		return false;
	}
	return true;
}

//validate number of  beds gends(Dormitory)
function val_ndl(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,4)){
			nic_alert("Invalid Length for number of rooms in ladies.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of rooms in ladies, only allow numeric.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the number of rooms in ladies","V");
		return false;
	}
	return true;
}


//validate address
function val_address1(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,200)){
			nic_alert("Invalid Length for address.","V");
			return false;
		}
		else if(!isvalidaddress(p_val)){
			nic_alert("Invalid address, only allow alpha numeric.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the address","V");
		return false;
	}

	return true;
}

//validate resi address
function val_address(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,200)){
			nic_alert("Invalid Length for Official address.","V");
			return false;
		}
		else if(!isvalidaddress(p_val)){
			nic_alert("Invalid Official address, only allow alpha numeric.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the Office address","V");
		return false;
	}
	return true;

}

//validate mobile_no
function val_mobileno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,15)){
			nic_alert("Invalid Length for mobile number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid mobileno, only allow numeric.","V");
			return false;
		}
		else if(!VAL_MOB(p_val)){
			nic_alert("Invalid mobileno.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the mobile no","V");
		return false;
	}
	return true;
}
//validate email id
function val_emailid(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for email id.","V");
			return false;
		}
		else if(!VAL_E(p_val)){
			nic_alert("Invalid email id.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the email id","V");
		return false;
	}
	return true;
}
//public
//validate mobile_no
function val_pmobileno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,15)){
			nic_alert("Invalid Length for mobile number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid mobileno, only allow numeric.","V");
			return false;
		}
		else if(!VAL_MOB(p_val)){
			nic_alert("Invalid mobileno.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the mobile no","V");
		return false;
	}
	return true;
}
//validate email id
function val_pemailid(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for email id.","V");
			return false;
		}
		else if(!VAL_E(p_val)){
			nic_alert("Invalid email id.","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please enter the email id","V");
		return false;
	}
	return true;
}




//end


//validate purpose
function val_purpose(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the purpose","V");
		return false;
	} else{
		return true;
	}
}


//validate Category
function val_category(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the category","V");
		return false;
	} else{
		return true;
	}
}

//validate roomtype
function val_roomtype(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the room type","V");
		return false;
	} else{
		return true;
	}
}

//validate name
function val_name(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length guest name.","V");
			return false;
		}
		else if(!VAL_NAME(p_val)){
			nic_alert("Invalid guest name, only allow alphabetic.","V");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Please enter the guest name","V");
		return false;
	}
	return false;
}

//public
//validate age
function val_page(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,2)){
			nic_alert("Invalid Length for guest age.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of guest age, only allow numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the guest age","V");
		return false;
	}
	return true;
}

function val_age(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,2)){
			nic_alert("Invalid Length for guest age.","V");
			return false;
		}
		else if(!VAL_N1(p_val)){
			nic_alert("Invalid number of guest age, only allow numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the guest age","V");
		return false;
	}
	return true;
}


//end
//validate gender
function val_gender(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the gender","V");
		return false;
	} else{
		return true;
	}
}

//validate designation
function val_designation(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for designation.","V");
			return false;
		}
		else if(!VAL_SPCE(p_val)){
			nic_alert("Invalid Designation, only allow Character.","V");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Please enter the designation","V");
		return false;
	}
	return true;
}
//validate designation
function val_department(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for department.","V");
			return false;
		}
		else if(!VAL_SPCE(p_val)){
			nic_alert("Invalid department, only allow Character.","V");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Please enter the department","V");
		return false;
	}
	return true;
}
//validate mode_of_arrival
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

//validate arrival_time
function val_arrival_time1(p_val){

	var to_date  = document.getElementById('to').value;

	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for departure time.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			nic_alert("Invalid departure time.","V");
			return false;
		}
		else if(dateCompTwo(to_date,p_val))
		{
			nic_alert("invalid Arrival Time,Only allowed departure date less than to date","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the departure time","V");
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

function val_servlet(p_val){

	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for servlet name.","V");
			return false;
		}
		else if(!VAL_AHA(p_val)){
			nic_alert("Invalid servlet name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the servlet name","V");
		return false;
	}
	return true;
}


function val_task_id(p_val){

	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for task_id.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid Task_id.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the task_id","V");
		return false;
	}
	return true;
}

function val_cat_id(p_val){
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for Food Category ID.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid Food Category.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please select the Food category","V");
		return false;
	}
	return true;
}

function val_fooditem(p_val){
	if(p_val=="") 
	{
		nic_alert("Please Enter Food Item","V");
		return false;
	}
	else if(!isLEN(p_val,1,150))
	{
		nic_alert("Invalid Length for Food Item","V");
		return false;
	}
	if(!VAL_ANS(p_val))
	{
		nic_alert("Invalid Food Item","V");
		return false;
	}
	return true;
}

function val_foodrate(p_val,p_type){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,12)){
			nic_alert("Invalid Length for "+p_type+" rate","V");
			return false;
		}
		else if(!VAL_ND(p_val)){
			nic_alert("Invalid "+p_type+" rate","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please Enter "+p_type+" rate","V");
		return false;
	}
	return true;
}
function val_foodrate(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,12)){
			nic_alert("Invalid Length for rate","V");
			return false;
		}
		else if(!VAL_ND(p_val)){
			nic_alert("Invalid rate","V");
			return false;
		}

	}
	else
	{
		nic_alert("Please Enter rate","V");
		return false;
	}
	return true;
}

function val_NULL(p_val)
{
	if(p_val=="0") 
	{
		nic_alert("Please enter data","V");
		return false;
	}else{
		return true;
	}
}