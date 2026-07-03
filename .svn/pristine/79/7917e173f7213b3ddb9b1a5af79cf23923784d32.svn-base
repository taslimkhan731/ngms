function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_extension_checkin", arr_fieldname, arr_fieldvalue, 'header_div');
}


function extension_checkin(page_no)
{
	//alert("extension_checkin");
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
			arr_fieldvalue =["extcheckinlist",getprocessid(),gh_id,sel_block,page_no];
			ajax_post_field_div("s_extension_checkin",handle_ext_checkinlistc,arr_fieldname,arr_fieldvalue,'ext_chk_home');	
	}
}
function handle_ext_checkinlistc()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function extension_cancel()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname =["task","process_id","gh_id"];
			arr_fieldvalue =["extcheckincancel",getprocessid(),gh_id];
			ajax_post_field_div("s_extension_checkin",handle_ext_checkincancel,arr_fieldname,arr_fieldvalue,'ext_chk_home');	
	}
}
function handle_ext_checkincancel()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function searchdata_ext(page_no){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	if(VAL_savecheckin()){
		arr_fieldname=["task","process_id","gh_id","req_id","name","pro_id","date","page_no"];
		arr_fieldvalue=["searchext",getprocessid(),gh_id,req_id,name,pro_id,date,page_no];
		ajax_post_field_divH("s_extension_checkin",arr_fieldname,arr_fieldvalue,'search_ext');	
	}
}


function extension_viewdata(preq_id,pguest_id,page_no,existing_req_id)
{
	//alert('existing_req_id '+existing_req_id);
	var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id","guest_id","page_no","hidvalue","existing_req_id"];
		arr_fieldvalue =["selectfromcheckin",getprocessid(),gh_id,sel_block,preq_id,pguest_id,page_no,hidvalue,existing_req_id];//viewlistext
		ajax_post_field_div("s_extension_checkin",handle_viewdata_ext,arr_fieldname,arr_fieldvalue,'viewdata_ext');	
	}
}
function handle_viewdata_ext()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

//select_checkinforext
function select_checkinforext(prev_reqid,newreq_id,page_no)
{
	var hidvalue = document.getElementById('hidvalue').value;	
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
//	alert(prev_reqid);
//	alert(prev_guestid);
//	alert(newreq_id);
//	alert(newguest_id);
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","gh_id","sel_block","newreq_id","prev_reqid","hidvalue","page_no"];
		arr_fieldvalue =["viewlistext",getprocessid(),gh_id,sel_block,newreq_id,prev_reqid,hidvalue,page_no];//
		ajax_post_field_div("s_extension_checkin",handle_select_checkinforext,arr_fieldname,arr_fieldvalue,'selcted_prevdata_ext');	
	}
}
function handle_select_checkinforext()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


function save_checkin_ext(prev_reqid,newreq_id,page_no)
{
	var ins_form= document.getElementById('form-general-ext');
	var hidvalue = document.getElementById('hidvalue').value;
	if(VAL_savedata()){
	    arr_fieldname=["task","process_id","newreq_id","prev_reqid","page_no","hidvalue"];
		arr_fieldvalue=["savecheckinext",getprocessid(),newreq_id,prev_reqid,page_no,hidvalue];
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_extension_checkin",handle_savecheckin_ext,arr_fieldname,arr_fieldvalue);
	}
}
function handle_savecheckin_ext(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
			close_nicpopup(n[3]);
			nic_alert("Created Successfully.","N");
			$('#popup').hide();
			extension_checkin(n[3]);
			$('#form-general-ext')[0].reset();
			document.getElementById(results).innerHTML="";
			
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

///////////////////////////////////

 $(document).on('change', '#chck', function() {

    if($(this).is(":checked")){
    	 $(this).closest('tr').addClass("success"); 
    }else{
    	 $(this).closest('tr').removeClass("success");  
    }
});


function restrictInput(myfield, e, restrictionType, checkdot) {
	if (!e)
		var e = window.event
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	// if user pressed esc... remove focus from field...
	if (code == 27) {
		this.blur();
		return false;
	}
	if ((code == 46)) {
        var strvalue = myfield.value;
        if (strvalue.indexOf(".") > 0){
            return false;}
    }	
	if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && code != 39 
			&& code != 38 && code != 39 &&(code != 39 || (code == 39 && character == "'"))
			&& code != 40 && code != 46) {
		if (character.match(restrictionType)) {
			if (checkdot == "checkdot") {
				return !isNaN(myfield.value.toString() + character);
			} else {
				return true;
			}
		} else {nic_alert("Character Not Permitted in this field. Enter Number.","V");
			return false;
		}
	}

}



$(document).on('click', '#chck', function () {
	var cx=	$('#chck:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});



 $(function () {
      $('.btn btn-success').click(function () { 
        if ($('.chck:checked').length) {
          var chkId ;
          $('.chck:checked').each(function () {
            chkId += $(this).val();
          });
          chkId = chkId.slice(0, -1);
        
        }
        else {
         // alert('Nothing Selected');
        }
      });
 });
    



var checkboxes = { 
  red: getElementByID('chck'),
};

var resultMessage = "";
for (var name in checkboxes) {
  resultMessage += name + ": " + checkboxes[name].selected + "\n";
};

//alert(resultMessage);





function checkout()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname =["task","process_id","gh_id"];
			arr_fieldvalue =["checkoutlist",getprocessid(),gh_id];
			ajax_post_field_div("s_checkin",handle_checkoutlistc,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_checkoutlistc()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}



function viewdataout(req_id)
{
	var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","gh_id","req_id","hidvalue"];
		arr_fieldvalue =["viewlistout",getprocessid(),gh_id,req_id,hidvalue];
		ajax_post_field("s_checkin",handle_viewdataout,arr_fieldname,arr_fieldvalue);	
	}
}
function handle_viewdataout()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function save_checkout(req_id,page_no)
{
	//if(VAL_savedata()){
		var hidvalue = document.getElementById('hidvalue').value;
	    var ins_form= document.getElementById('form-general');
	    arr_fieldname=["task","process_id","req_id","hidvalue","page_no"];
		arr_fieldvalue=["savecheckout",getprocessid(),req_id,hidvalue,page_no];
		
		ajax_post_form_field(ins_form,"s_checkin",handle_savecheckout,arr_fieldname,arr_fieldvalue);
	//}
}
function handle_savecheckout(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			close_nicpopup();
			nic_alert("Created Successfully.","N");
			checkout();
			$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}



function canceldata(req_id)
{
	var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","gh_id","req_id","hidvalue"];
		arr_fieldvalue =["cancellist",getprocessid(),gh_id,req_id,hidvalue];
		ajax_post_field("s_checkin",handle_canceldata,arr_fieldname,arr_fieldvalue);	
	}
}
function handle_canceldata()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;
			
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


function cancel_checkin(req_id,page_no)
{
	//if(VAL_savedata()){
	var hidvalue = document.getElementById('hidvalue').value;
	    var ins_form= document.getElementById('form-general');
	    arr_fieldname=["task","process_id","req_id","hidvalue","page_no"];
		arr_fieldvalue=["cancelcheckin",getprocessid(),req_id,hidvalue,page_no];
		ajax_post_form_field(ins_form,"s_checkin",handle_cancelcheckin,arr_fieldname,arr_fieldvalue);
	//}
}
function handle_cancelcheckin(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			close_nicpopup();
			nic_alert("Cancel Successfully.","N");
			cancel();
			$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}


//-------------------------------------------------------------------------------------------validation-----------------------------------------------------------------

function VAL_savecheckin(){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
		if((req_id=="") && (name=="") && (pro_id=="") && (date==""))
			{
			nic_alert("Please Enter data for searching","N");
			return false;
			}
		else{
			if(!req_id==""){ 
				val_search(req_id);
				
			}
			else if(!name==""){ 
				val_search(name);
				
			}
			
			else if(!pro_id==""){ 
				val_search(pro_id);
				
			}
			else if(!date==""){ 
				val_search(date);
				
			}
		}
			return true;
	}

function VAL_savedata(){
	//var vc_id= document.getElementById('sel_vcid').value;
	//var quota=document.getElementById('sel_q').value;
	//if(val_vcid(vc_id))
	//if(val_type(quota))
	
	count= document.getElementById('noofrows').value;
	var i;
	for(i=1;i<=count;i++){
		    var b = 'roll'+i;
		var bb 	  = document.getElementById(b).value;
			var c='time'+i;
		var cc 	  = document.getElementById(c).value;
			var d='amount'+i;
		var dd 	  = document.getElementById(d).value;
			var e='sel_room_allotid'+i;
		var ee 	  = document.getElementById(e).value;
			var f='adults'+i;
		var ff 	  = document.getElementById(f).value;	
		var g='children'+i;
		var gg 	  = document.getElementById(g).value;	
		if( bb =="" || cc =="" || dd =="" || gg==""|| ff==""|| ee ==0)
		{
			
		if(bb != "")
			{
				if(!isLEN(bb,1,10))
				{
					nic_alert("Allowed Length of Roll No. is in between 1 and 100","N");
					return false;
				}
				else if(!VAL_N(bb))
				{
					nic_alert("Please Enter Valid Roll No. Allowed Numeric Only.","N");
					return false;
				}
			}
		   	else{
		   		nic_alert("Please Enter the Roll No.","V");
		   		return false;
			}
			if(cc== ""){
				nic_alert("Please Enter the Time","V");
		   		return false;
			}
			if(dd != ""){
				if(!isLEN(dd,1,10))
				{
					nic_alert("Allowed Length of Advance Amount is in between 1 and 10","N");
					return false;
				}
				else if(!VAL_N(dd))
				{
					nic_alert("Please Enter Valid Advance Amount. Allowed Numeric only.","N");
					return false;
				}
			}
//			else{
//		   		nic_alert("Please Enter the advance amount","V");
//		   		return false;
//			}
			if(gg != ""){
				if(!VAL_N(gg))
				{
					nic_alert("Please Enter Valid No. of Children. Allowed Numeric Only.","N");
					return false;
				}
			}
			if(ff != ""){
				if(!VAL_N(ff))
				{
					nic_alert("Please Enter Valid No. of Adults. Allowed Numeric Only.","N");
					return false;
				}
			}
			/*else{
				if(dd==""){
					nic_alert("Please Enter the advance amount","V");
					return false;
				}
			}*/
				
			
			if(ee== 0){
				nic_alert("Please Select the Room","V");
		   		return false;
			}
		}
			return true;
		}
		}
