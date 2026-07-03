
var cvalue="please select something";

 $(document).on('change', '#chck', function() {

   if($(this).is(":checked")){
          
            cvalue="ok";
            
   }else{
		   
   }
});
 
 
 
var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
var b = ['', '', 'Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety'];


function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_billing", arr_fieldname, arr_fieldvalue, 'header_div');
}


function inWords(num) {
 
    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'Only ' : '';
  
   return str;
	
}

$(document).on('keyup', '.rowDataSd input', function () {

	  var tots = 0;
 var totals=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
 
    var $dataRows=$("#sum_table tr:not('.totalColumn input, .titlerow input')");
    
    $dataRows.each(function() {
        $(this).find('.rowDataSd input').each(function(i){        
            totals[i]+=parseInt( $(this).val());
        });
    });
	
    $("#sumtable1 td.totalCol1").each(function(i){  
       
    	 $(this).html("<i class='fa fa-rupee'></i> "+ totals[i]);
       /* if (!isNaN(totals[i])) {
        	 $(this).val(totals[i]);
        }
        else
        	{
        	// $(this).val("0");
        	// $(this).html(0);
        	 //totals[i]+=parseInt( $(this).val());
        	}*/
       
	});
    
   
  var adv=$('#adv').val();
    $("#sum_table td.totalCol input").each(function(i){  
    	
    	var cccc=totals[i];
    	//var ccc=$('#tot-value').val(0);
        $(this).val(+totals[i]);
        $(this).attr('value',+totals[i]);
        
        
        
        $('#tot-value').html($('#tot-value').val()+cccc);
        ccc=i;
        to= 0;
        to=to+ccc;
    	var temp = parseInt($(this).val());
    	tots+= temp;
    	 $('#tot-value').html("<i class='fa fa-rupee'></i> "+ tots);
    //	 $('#deduct').html("<i class='fa fa-rupee'></i> "+ tots-adv);
    	 var dd= parseInt(tots-adv);
    	 $('#deduct').html("<i class='fa fa-rupee'></i> "+ dd);
    	 $('#towords').html(inWords(dd));
        
	});
    
});

var digitsOnly = /[1234567890]/g;

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

$(document).ready(function(){
	
	
});


function return_values(amnt)
{
	var vals= document.getElementById(amnt).value;
	
	return vals;
	}

function calc_rent()
{

	var a=$('#days').val();
	var b=$('#rent').val();
	
	$('#total_rent').val(a*b);
	
	
	}

function add_bill_lists()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['addbilling',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_billing",handle_add_bill_list,arr_fieldname,arr_fieldvalue,'billing');
		}
	
}

function showRentBill(vc_id,purpose,from,to,adults,child,guest_id){
	var gh_id = document.getElementById('gh_id').value;
	var basic_pay = document.getElementById('basic_pay').value;
	if(basic_pay==0){
		nic_alert("Please enter the basic pay","N");
	}else{
			arr_fieldname=["task","process_id","gh_id","vc_id","purpose","from","to","adults","child","guest_id","basic_pay"];
			arr_fieldvalue=["showRentBillAuto",getprocessid(),gh_id,vc_id,purpose,from,to,adults,child,guest_id,basic_pay];
			ajax_post_field_div("s_billing",handle_showRentBill,arr_fieldname,arr_fieldvalue,'billing');
	}
}

function handle_showRentBill()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
			//document.getElementById('basic_pay').value=0;
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}




//function showRentBill(vc_id,purpose,from,to,adults,child,guest_id){
//	var gh_id = document.getElementById('gh_id').value;
//	
//	arr_fieldname=["task","process_id","gh_id","vc_id","purpose","from","to","adults","child","guest_id"];
//	arr_fieldvalue=["showRentBillAuto",getprocessid(),gh_id,vc_id,purpose,from,to,adults,child,guest_id];
//	ajax_post_field_div("s_billing",handle_showRentBill,arr_fieldname,arr_fieldvalue,'billing');
//}
//
//function handle_showRentBill()
//{
//	if(http.readyState == 4)
//	{
//		results = http.responseText;
//		if(results.substr(0,7)!= "Message")
//		{
//			//alert(results);
//			show_nicpopup(results);
//		}
//		else
//		{
//			nic_alert(results.substr(8),"N");
//		}
//	}
//}

function showFoodBill(req_id,guest_id){
var gh_id = document.getElementById('gh_id').value;
	
	arr_fieldname=["task","process_id","gh_id","req_id","guest_id"];
	arr_fieldvalue=["showFoodBillAuto",getprocessid(),gh_id,req_id,guest_id];
	ajax_post_field_div("s_billing",handle_showFoodBill,arr_fieldname,arr_fieldvalue,'billing');
}

function handle_showFoodBill()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}
//billing_setFoodBill(
function billing_setFoodBill(guest_id,total){
	//alert("guest_id,total "+guest_id+" "+total);
	close_nicpopup();
	document.getElementById('amount'+guest_id+'2').value = total;
}

function billing_setRentBill(guest_id,total){
	//alert("guest_id,total "+guest_id+" "+total);
	document.getElementById('amount'+guest_id+'1').value = total;
	close_nicpopup();
	if(total.equals('No Data in Table')){
		total = '0';
	}
	
	
}

function search_cancel_bill(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var bill_no = document.getElementById('bill_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&bill_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_cout_guest(req_id,name,date)){
			if(!bill_no==""){
				if(!isLEN(bill_no,1,25)){
					 
					nic_alert("Invalid Length for Bill No.","V");
					return false;
				}
				else if(!VAL_N(bill_no)){
					 
					nic_alert("Invalid Bill No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","bill_no","page_no"];
		arr_fieldvalue=["searchCancel",getprocessid(),gh_id,req_id,name,date,bill_no,page_no];
		ajax_post_field_divH("s_billing",arr_fieldname,arr_fieldvalue,'BillCancelList');	
	}}
}

function search_show_bill(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var bill_no = document.getElementById('bill_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&bill_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_cout_guest(req_id,name,date)){
			if(!bill_no==""){
				if(!isLEN(bill_no,1,25)){
					 
					nic_alert("Invalid Length for Bill No.","V");
					return false;
				}
				else if(!VAL_N(bill_no)){
					 
					nic_alert("Invalid Bill No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","bill_no","page_no"];
		arr_fieldvalue=["searchShow",getprocessid(),gh_id,req_id,name,date,bill_no,page_no];
		ajax_post_field_divH("s_billing",arr_fieldname,arr_fieldvalue,'BillShowList');	
	}}
}
 ///search_cout_guest


function search_cout_guest(){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_cout_guest(req_id,name,date)){
		arr_fieldname=["task","process_id","gh_id","req_id","name","date"];
		arr_fieldvalue=["search",getprocessid(),gh_id,req_id,name,date];
		ajax_post_field_divH("s_billing",arr_fieldname,arr_fieldvalue,'searchguest');	
	}}
}

function val_search_cout_guest(req_id,name,date){

	if(!req_id==""){
		if(!isLEN(req_id,1,25)){
			 
			nic_alert("Invalid Length for Request ID.","V");
			return false;
		}
		else if(!VAL_ANHYP(req_id)){
			 
			nic_alert("Invalid Request ID.","V");
			return false;
		}
	}
	if(name!=""){
	if(!isLEN(name,1,50)){
		 
		nic_alert("Invalid Length for Name.","V");
		return false;
	}
	else if(!VAL_NAME(name)){
		 
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

function handle_add_bill_list()
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

//bill_list
function bill_list(page_no)
{
		
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =['showbills',getprocessid(),gh_id,sel_block,page_no];	
		ajax_post_field_div("s_billing",handle_bill_list,arr_fieldname,arr_fieldvalue,'billing');
	}
	
}

function handle_bill_list()
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
///show_billforpdf(
function show_billforpdf(gh_id,bill_id)
{
		arr_fieldname =["task","process_id","gh_id","bill_id"];
		arr_fieldvalue =['showbillforpdf',getprocessid(),gh_id,bill_id];
		$('#popup').show();
		ajax_post_field_div("s_billing",handle_show_billforpdf,arr_fieldname,arr_fieldvalue,'billing');
}
function handle_show_billforpdf()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

//cancel_bill_list
function cancel_bill_list(page_no)
{
		
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =['cancelbilling',getprocessid(),gh_id,sel_block,page_no];	
		ajax_post_field_div("s_billing",handle_cancel_bill_list,arr_fieldname,arr_fieldvalue,'billing');
	}
	
}

function handle_cancel_bill_list()
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
///cancel_bill
function cancel_bill(gh_id,bill_id,page_no)
{
		arr_fieldname =["task","process_id","gh_id","bill_id","page_no"];
		arr_fieldvalue =['cancelguestbilling',getprocessid(),gh_id,bill_id,page_no];	
		ajax_post_field_div("s_billing",handle_cancel_bill,arr_fieldname,arr_fieldvalue,'billing');
}
function handle_cancel_bill()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}
///cancel_bill_confirm
function cancel_bill_confirm(gh_id,bill_id,page_no)
{
	var ins_form= document.getElementById('form-cancel-bill');
	if(confirm("Do you want to cancel the bill?")){
		arr_fieldname =["task","process_id","gh_id","bill_id","page_no"];
		arr_fieldvalue =['cancelbill',getprocessid(),gh_id,bill_id,page_no];	
		ajax_post_form_field(ins_form,"s_billing",handle_cancel_bill_confirm,arr_fieldname,arr_fieldvalue);
	}
}

function handle_cancel_bill_confirm()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			var n=results.split("-");
			/*arr_fieldname =["process_id","task"];
			arr_fieldvalue =[getprocessid(),"abc"];
			post_field("s_billing", arr_fieldname,arr_fieldvalue,false);*/
			nic_alert("Bill Cancelled","N");
			close_nicpopup();
			cancel_bill_list(n[3]);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

//add_guest_billins(
function add_guest_billins(gh_id)
{
		var sel_block = document.getElementById('sel_block').value;
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['addguestbilling',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_billing",handle_add_guest_billins,arr_fieldname,arr_fieldvalue,'billing');
}

function handle_add_guest_billins()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}
//make_bill_guestlist(
function make_bill_guestlist(gh_id,req_id,sel_block)
{
	var ins_form= document.getElementById('form-guest-selection');
	$("#search56 #chck").each(function()
	{
		var ch=$(this);
		if($("#search56 #chck").is(':checked'))
			{
			arr_fieldname =["task","process_id","gh_id","sel_block"];
			arr_fieldvalue =['makeguestbilling',getprocessid(),gh_id,sel_block];	
			ajax_post_form_field_div(ins_form,"s_billing",handle_make_bill_guestlist,arr_fieldname,arr_fieldvalue,'billing');
			}
		else
		{
			nic_alert("Please Select a Bill","N");
		 }
	});
	
}

function handle_make_bill_guestlist()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
			close_nicpopup();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

//create_bill
function create_bill()//gh_id,req_id,guest_values
{
	
	$('#bill').removeClass('hide');
	window.location = $("a").attr("href");
	
}

function handle_create_bill()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			document.getElementById(resultDiv).innerHTML=results;
			//close_nicpopup();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}
//submit_bill(
function submit_bill(gh_id,sel_block,charge_count,guest_count,div)//gh_id,req_id,guest_values
{
	var ins_form= document.getElementById('form-gen_bill');
	this.div = div;
	if(VAL_submit_bill(gh_id,charge_count,guest_count)){
		arr_fieldname =["task","process_id","gh_id","sel_block","charge_count","guest_count"];
		arr_fieldvalue =['submitbill',getprocessid(),gh_id,sel_block,charge_count,guest_count];	
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_billing",handle_submit_bill,arr_fieldname,arr_fieldvalue);
	}
}
function handle_submit_bill()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			/*nic_alert("Bill Submitted Successfully","N");
			arr_fieldname =["process_id","task"];
			arr_fieldvalue =[getprocessid(),"abc"];
			post_field("s_billing", arr_fieldname,arr_fieldvalue,false);
			//add_bill_lists();
			bill_list();*/
			
			var arr_result = results.split("-");
			var bill_id = arr_result[4];
			var gh_id = arr_result[3];
			//var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","bill_id","gh_id","div"];
			arr_fieldvalue=["billprint",getprocessid(),bill_id,gh_id,this.div];
			//$('#popup').hide();
			ajax_post_field_divH("s_billing",arr_fieldname,arr_fieldvalue,this.div); //billing Gopika 'dataEntry'
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

/////////////////////correct upto this
function bill_dataentry(req_id,charge_id,guest_id){
	arr_fieldname =["task","process_id","req_id","guest_id","charge_id"];
	arr_fieldvalue =['billdataentry',getprocessid(),req_id,guest_id,charge_id];	
	ajax_post_field_div("s_billing",handle_bill_dataentry,arr_fieldname,arr_fieldvalue,'billing');
}

function handle_bill_dataentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function rent_calculation(req_id,guest_id,charge_id) {
	var ins_form= document.getElementById('form_rent_calc');
	arr_fieldname =["task","process_id","guest_id","charge_id","req_id"];
	arr_fieldvalue =['rentcalc',getprocessid(),guest_id,charge_id,req_id];	
	ajax_post_form_field(ins_form,"s_billing",handle_rent_calc,arr_fieldname,arr_fieldvalue);
}

function handle_rent_calc()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			close_nicpopup();
			add_bill_lists();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}



//pdf creation

function pdf_bill(gh_id,bill_id,block)
{
	arr_fieldname =["task","process_id","bill_id","gh_id","block"];
	arr_fieldvalue =['billprintpdf',getprocessid(),bill_id,gh_id,block];	
	post_field("doc_handler", arr_fieldname,arr_fieldvalue,true);

}



//--------------------------------------------------------validation---------------------------------------------------------------
function close_and_reload()

{
	close_nicpopup();
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[getprocessid(),"abc"];
	post_field("s_billing", arr_fieldname,arr_fieldvalue,false);
	
}



function validateCheck(){
	
	var cc=$( "#sel_id option:selected" ).text();
	
	if(cc=="--select--"){
		nic_alert("Please select a Guest House","V");
		return false;
	}
	return true;
}

function VAL_save(){
	
	val_fromdate(from_date);
	//val_todate(to_date);
	return true;
}











//--------------------------------validation---------------------------------------------------------------------//
function VAL_submit_bill(gh_id,charge_count,guest_count){
	//if((val_gh_id(gh_id)) && (val_charge_count(charge_count)) &&(val_guest_count(guest_count)) && (VAL_guest(guest_count)) && (VAL_charge(charge_count)))
	if(VAL_charge(guest_count,charge_count))
		return true; //val_req_id doublt  // VAL_charge doubt //val_charge_count
	
}

function VAL_guest(guest_count){
	for(var i=1;i<=guest_count;i++)
	{
		var guest_id  = document.getElementById('gue_id'+i).value;
		if(!val_guest_id(guest_id))
		{
			return false;
		}
	}
		return true;
}
function VAL_charge(guest_count,charge_count){	

	for(var i=1;i<=guest_count;i++)
	{
		for(var j=1;j<=charge_count;j++)
		{
			var guest_id  = document.getElementById('gue_id'+i).value;
			var field = document.getElementById('amount'+guest_id+j).value;
			if(!val_charge(field))
			{
				return false;
			}
		}
	}
		return true;
}
function val_charge_count(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for charge count.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid charge count.","V");
			return false;
		}
	}
	else
	{
		nic_alert("No charge count","V");
		return false;
	}
	return true;
}
function val_guest_count(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for guest count.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid guest count.","V");
			return false;
		}
	}
	else
	{
		nic_alert("No guest count","V");
		return false;
	}
	return true;
}
/////old

function VAL_savedataentry(){
	
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var email_id= document.getElementById('email').value;
	
	if((val_fromdate(from_date)) && (val_todate(to_date)) && (val_purpose(purpose)) &&(val_mobileno(mobile_no)) && (val_emailid(email_id)) && (VAL_roommf()) && (val_address(address1)) && (VAL_guest()))
		return true;
	}

//validate gh_id
function val_ghid(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the City","V");
		return false;
	}
	return true;
}
//validate pagination
function VAL_pagination_data(page_no,gh_id){
	val_pageno(page_no);
	val_ghid(gh_id);
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


/////old end
