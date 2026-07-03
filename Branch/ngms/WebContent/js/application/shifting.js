         

 var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
 var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];


 function inWords(num) {

     if ((num = num.toString()).length > 9) return 'overflow';
     n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
     if (!n) return; var str = '';
     str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
     str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
     str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
     str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
     str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only ' : '';
   
    return str;
 	
 }
 
 function getBlocks(){
		ins_form= document.getElementById('form-general1');
		var gh_id = document.getElementById('sel_id').value;
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =["getblock",getprocessid(),gh_id];
		document.getElementById("header_div").innerHTML = "";
		ajax_post_form_field_divH(ins_form, "s_shifting", arr_fieldname, arr_fieldvalue, 'header_div');
	}
 
///search_shift_report_TC
 function search_shift_report_TC(page_no){

		var shift_id=  document.getElementById('shift_id').value;
		var name =  document.getElementById('name').value;
		var gh_id = document.getElementById('gh_id').value;
		var date=document.getElementById('date').value;
		if(shift_id==""&&name==""&&date==""){
			nic_alert("Please Enter data for searching","N");
		}else{
			if(val_search_shift_report_TC(shift_id,name,date)){
				
			arr_fieldname=["task","process_id","gh_id","shift_id","name","date","page_no"];
			arr_fieldvalue=["searchTC",getprocessid(),gh_id,shift_id,name,date,page_no];
			ajax_post_field_divH("s_shifting",arr_fieldname,arr_fieldvalue,'tc-all');	
		}}
	}
 

 function val_search_shift_report_TC(shift_id,name,date){

 	if(!shift_id==""){
 		if(!isLEN(shift_id,1,25)){
 			 
 			nic_alert("Invalid Length for Shift ID.","V");
 			return false;
 		}
 		else if(!VAL_N(shift_id)){
 			 
 			nic_alert("Invalid Shift ID.","V");
 			return false;
 		}
 	}
 	if(name!=""){
 	if(!isLEN(name,1,50)){
 		 
 		nic_alert("Invalid Length for Name.","V");
 		return false;
 	}
 	else if(!VAL_AN(name)){
 		 
 		nic_alert("Invalid Name.","V");
 		return false;
 	}
 	}
 	if(date!=""){
 	if(!isLEN(date,1,25)){
 		 
 		nic_alert("Invalid Length for Date.","V");
 		return false;
 	}
 	else if(!VAL_NC(date)){ 
 		 
 		nic_alert("Invalid Date.","V");
 		return false;
 	}
 	}
 	
 	return true;
 }
function shift_report_TC(page_no)
{
	 
	var gh_id  = document.getElementById('sel_id').value;
	 
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","page_no","sel_block"];
		arr_fieldvalue =['reportlist',getprocessid(),gh_id,page_no,sel_block];	
		ajax_post_field_div("s_shifting",handleshift, arr_fieldname,arr_fieldvalue,'dataEntry');
	}
		 
	}

function addshift()
{
	 
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['showform',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_shifting",handleshift, arr_fieldname,arr_fieldvalue,'dataEntry');
	}
 		 
	}
function changeshift()
{
	 
	var gh_id  = document.getElementById('sel_id').value;
	 
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['changeshift',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_shifting",handleshift, arr_fieldname,arr_fieldvalue,'dataEntry');
	}
 		 
	}
function closeshift()
{
	 
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['closeshift',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_shifting",handleshift, arr_fieldname,arr_fieldvalue,'dataEntry');
	}
 		 
	}
	
function handleshift()
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
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function getshift(){
	
	var parent_id = document.getElementById("sel_parent").value;
	arr_fieldname =["task","process_id","parent_id"];
	arr_fieldvalue =['showdata',getprocessid(),parent_id];	
	ajax_post_field_divH("s_shifting", arr_fieldname,arr_fieldvalue,'search');
	
}
function getreport(shift_id){
	var gh_id  = document.getElementById('sel_id').value;
 	arr_fieldname =["task","process_id","shift_id","gh_id"];
	arr_fieldvalue =['showreport',getprocessid(),shift_id,gh_id];	
	ajax_post_field_div("s_shifting",handlereport, arr_fieldname,arr_fieldvalue,'search');

	//ajax_post_field_divH("s_shifting", arr_fieldname,arr_fieldvalue,'search');
	
}
function handlereport(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			 
			 
			 show_nicpopup(results);
			//document.getElementById(results).innerHTML="";
			
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function submitdata(){
	ins_form = document.getElementById("shift-entry");
	if (validateshift()){
		
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=["submitdata",getprocessid()];
	ajax_post_form_field(ins_form,"s_shifting",handle_saveform_data,arr_fieldname,arr_fieldvalue);
}
}

function updatedata(){
	ins_form = document.getElementById("shift-entry");
	if(validateshift()){
	arr_fieldname=["task","process_id"];
	arr_fieldvalue=["updatedata",getprocessid()];
	ajax_post_form_field(ins_form,"s_shifting",handle_saveform_data,arr_fieldname,arr_fieldvalue);
}
}

function closedata(){
	var shift_id = document.getElementById("shift_id").value;
	var gh_id = document.getElementById("gh_id").value;
	var sel_block = document.getElementById("sel_block").value;
	arr_fieldname=["task","process_id","gh_id","shift_id","sel_block"];
	arr_fieldvalue=["closedata",getprocessid(),gh_id,shift_id,sel_block];
	ajax_post_field("s_shifting",handle_saveform_data,arr_fieldname,arr_fieldvalue);
}
function handle_saveform_data(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			 
			 
			var n=results.split("-");
			nic_alert(n[0],"N");
		 
			
			//alert(n[3]);
			
		/*	
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[2],getprocessid()];
			post_field(n[1],arr_fieldname,arr_fieldvalue,false);
			//document.getElementById(results).innerHTML="";
			$("#sel_id option:selected").text=n[3]; DOUBT OK*/
			
			shift_report_TC('1');
			
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}


function restrictInput(myfield, e, restrictionType, checkdot) {
	/*if (!e)
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
		    }*/
	if (event.keyCode == 8) {
		// let it happen, don't do anything
	}
	else {
		// Ensure that it is a number and stop the keypress
		if (event.keyCode < 48 || event.keyCode > 57) {
			nic_alert("Character Not Permitted in this field. Enter Number.","V")
			return false;
		}
	}
	/*if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && 
					code != 38 &&(code != 39 || (code == 39 && character == "'"))
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
			}*/

}

function replacezero(textboxid)
{
	
	if(document.getElementById(textboxid).value==0 || document.getElementById(textboxid).value==0.0 || document.getElementById(textboxid).value==0.00)
	document.getElementById(textboxid).value="";

	}
function placezero(textboxid)
{
	
	if(document.getElementById(textboxid).value=="")
	document.getElementById(textboxid).value=0;
	
	}
function settotal_opb(){
	 
var tot_bal = 0;
	 
	var total_recp =0;	
	var tot_reg=0;
	 var tot_adv = 0;
	 var tot_reg =0;
	 var over_total=0;
	 var over_total_adv=0;
	  var over_total_reg=0;
	  var close_total =0;
		var  opn_adv =   document.getElementById('bal_adv').value;
		var  opn_reg =   document.getElementById('bal_reg').value;
		var  rcp_adv=   document.getElementById('rcp_adv').value;
		var  rcp_reg =   document.getElementById('rcp_reg').value;
		var bal_tot= document.getElementById('bal_tot').value;

		var rcp_tot = document.getElementById('rcp_tot').value;
		var rem_adv = document.getElementById('rem_adv').value;
		var rem_reg = document.getElementById('rem_reg').value;
		var rem_tot = document.getElementById('rem_tot').value;
		
		tot_bal = parseInt(opn_adv) +  parseInt(opn_reg);
		
			
		total_recp = parseInt(rcp_adv) +  parseInt(rcp_reg);
		
		tot_adv = parseInt(opn_adv) +  parseInt(rcp_adv);
		
		tot_reg = parseInt(opn_reg) +  parseInt(rcp_reg);
		over_total = tot_bal +  total_recp;
			
		over_total_adv = tot_adv + parseInt(rem_adv);
		
		over_total_reg =  tot_reg + parseInt(rem_reg);
		
		close_total =  over_total +  parseInt(rem_tot);
		
	if(tot_bal>0){document.getElementById('bal_tot').value = tot_bal;}else{document.getElementById('bal_tot').value =0;}
		
	if(total_recp>0){document.getElementById('rcp_tot').value = total_recp;}else{document.getElementById('rcp_tot').value =0;}
	if(tot_adv>0){document.getElementById('tot_adv').value = tot_adv;}else{document.getElementById('tot_adv').value =0;}
	if(tot_reg>0){document.getElementById('tot_reg').value = tot_reg;}else{document.getElementById('tot_reg').value =0;}
	if(over_total>0){document.getElementById('tot_tot').value = over_total;}else{document.getElementById('tot_tot').value =0;}

	 if(over_total_adv>0){document.getElementById('clo_adv').value = over_total_adv;}else{document.getElementById('clo_adv').value =0;}
	if(over_total_reg>0){document.getElementById('clo_reg').value = over_total_reg;}else{document.getElementById('clo_reg').value =0;}
	if(close_total>0){document.getElementById('clo_tot').value = close_total;}else{document.getElementById('clo_tot').value =0;}

}
 
function comparetime(extractedStartHour,extractedStartMinute){
	
	var startHour = extractedStartHour;
	 var startMinute = extractedStartMinute;
	 var startSecond = extractedStartSecond;

	 var endHour = extractedEndHour;
	 var endMinute = extractedEndMinute;
	 var endSecond = extractedEndSecond;

	 //Create date object and set the time to that
	 var startTimeObject = new Date();
	 startTimeObject.setHours(startHour, startMinute, startSecond);

	 //Create date object and set the time to that
	 var endTimeObject = new Date(startTimeObject);
	 endTimeObject.setHours(endHour, endMinute, endSecond);

	 //Now we are ready to compare both the dates
	 if(startTimeObject > endTimeObject)
	 {
	 nic_alert('End time should be after start time.',"V");
	 return false;
	 }
	 
 
	
	 
} 
function printshiftreport(shift_id,gh_id){
	 arr_fieldname=["task","process_id","shift_id","gh_id"];
		arr_fieldvalue=["shiftreport",getprocessid(),shift_id,gh_id];
		post_field("doc_handler",arr_fieldname,arr_fieldvalue,true); 
	 
	 
}


function validateshift(){
	
	 var bal_rfd = document.getElementById('bal_rfd').value;
	 var bal_reg = document.getElementById('bal_reg').value;
	 var bal_tot = document.getElementById('bal_tot').value;
	 var opn_time = document.getElementById('opn_time').value;
	 var cls_time = document.getElementById('cls_time').value;
	 //var parent_id = document.getElementById('sel_parent').value;
	 if(opn_time==""){
			nic_alert("Please Enter From time","V") ;
			return false;
		 }
	 if(cls_time==""){
			nic_alert("Please Enter to time","V") ;
			return false;
		 }
	 if(bal_rfd==""){
		nic_alert("Please Enter Opening balance refund","V") ;
		return false;
	 }
	 if(bal_reg==""){
			nic_alert("Please Enter Opening balance regular","V") ;
			return false;
		 }
	 if(bal_tot==""){
			nic_alert("Please Enter opening balance ","V") ;
			return false;
		 }
	
	 if(!isdateandtime_GT(opn_time,cls_time)){//cmptime
		 nic_alert("From time must be before To time","V");
		 return false;
	 }
	/* if(parent_id=="z"){
		nic_alert("You didnt select Parent id","V") ;
		return false;
	}*/
	 
	return true;
}
function total_cash(){
	 var bal_rfd = document.getElementById('bal_rfd').value;
	 var bal_reg = document.getElementById('bal_reg').value;
		tot_bal = parseInt(bal_rfd) +  parseInt(bal_reg);
		if(tot_bal>0){document.getElementById('bal_tot').value = tot_bal;}else{document.getElementById('bal_tot').value =0;}

}
function pagination_data(page_no,gh_id){
	
	VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id"];
	arr_fieldvalue =[getprocessid(),"reportlist",page_no,gh_id];
	ajax_post_field_divH("s_shifting", arr_fieldname,arr_fieldvalue,'home_value');
}
	
 