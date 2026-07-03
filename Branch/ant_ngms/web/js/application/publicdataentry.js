$(document).ready(function() {
	
 $(document).on('change', '#roomtype', function() {
		 
		 var roomtype = $('#roomtype').find(":selected").val();
			
			if(roomtype=="D")
				{
				$('.roombox').hide();
				/*$('#rooms').attr('disabled','disabled');*/
				}
			else
				{
				$('.roombox').show();
				/*$('#rooms').removeAttr('disabled','disabled');*/
				}
	 });
	
    $(document).on('change', '#chk', function() {
        if (this.checked) 
        	{
        	$('.data_box input[type="text"],.data_box select').prop("disabled", false);
    		$('.data_box input[type="text"],.data_box select').prop('enabled',true);
        	}

		else
			{
			$('.data_box input[type="text"],.data_box select').attr('disabled','');
			}
    });
    $(document).on('change', '#chk1', function() {

        if (this.checked) 
        	{
        	$('.data_box1 input[type="text"],.data_box1 select').prop("disabled", false);
    		$('.data_box1 input[type="text"],.data_box1 select').prop('enabled',true);
        	}
		else
			{
			$('.data_box1 input[type="text"],.data_box1 select').attr('disabled','');
			}
    });
});



$(document).ready(function() {
	
 
    var inputs = 1; 
    $('#btnAdd').click(function() {
    $('.btnDel:disabled').removeAttr('disabled');
        var c = $('.clonedInput:first').clone(true);
            c.children(':text').attr('name','input'+ (++inputs) );
             $('.clonedInput:last').after(c);
             $(c).after(val(""));
             
         
});

$('.btnDel').click(function() {
        if (confirm('continue delete?')) {
            --inputs;
            $(this).closest('.clonedInput').remove();
            $('.btnDel').attr('disabled',($('.clonedInput').length  < 2));
        }
    });
}); 

function getExtensionPub(){
	if($("#ext").is(":checked")){
		$("#extdata").removeClass('hide');
	}
	else{
		$("#extdata").addClass('hide');
	}	
	
}

function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_publicdataentry", arr_fieldname, arr_fieldvalue, 'header_div');
}
function getExistingReqPub(){
		var gh_id = document.getElementById('sel_id').value;
	
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =['getExistingReqPub',getprocessid(),gh_id];	
		ajax_post_field_div("s_publicdataentry",handle_getExistingReq,arr_fieldname,arr_fieldvalue,'dataEntry');
}

function handle_getExistingReq()
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

function selectExistingPub(p_req_id){
	close_nicpopup();
	$('#exist_req_id').val(p_req_id);
}

function pagination_data(page_no,gh_id){
	var sel_block = document.getElementById('sel_block').value;
	VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"createlistdata",page_no,gh_id,sel_block];
	ajax_post_field_divH("s_publicdataentry", arr_fieldname,arr_fieldvalue,'dataEntry');
}
function pagination_status(page_no,gh_id){
	var sel_block = document.getElementById('sel_block').value;
	VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"createliststatus",page_no,gh_id,sel_block];
	ajax_post_field_divH("s_publicdataentry", arr_fieldname,arr_fieldvalue,'dataEntry');
}
function pagination_datad(page_no,gh_id){
	var sel_block = document.getElementById('sel_block').value;
	VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"printapplication",page_no,gh_id,sel_block];
	ajax_post_field_divH("s_publicdataentry", arr_fieldname,arr_fieldvalue,'dataEntry');
}


//1.status public report list sub menu

function printstatus(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["createliststatus",getprocessid(),"1",gh_id,sel_block];
		$('#popup').show();
		ajax_post_field_div("s_publicdataentry",handle_show_form_conformation,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}

function printconformstatus(pro_id,req_id){
	 arr_fieldname=["task","process_id","reqid","pro_id"];
		arr_fieldvalue=["generatepublicpdf",getprocessid(),req_id,pro_id];
		post_field("doc_handler",arr_fieldname,arr_fieldvalue,true); 
	
}

function handle_show_form_conformation()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			$('#popup').hide();
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//print status list
function showconf(pro_id,req_id){
	arr_fieldname=["task","process_id","pro_id","req_id"];
	arr_fieldvalue=["statusshow",getprocessid(),pro_id,req_id];
	ajax_post_field("s_publicdataentry",handle_conformation,arr_fieldname,arr_fieldvalue);	


}

function handle_conformation(){
if(http.readyState == 4){
	results = http.responseText;
	if(results.substr(0,7)!= "Message"){
		show_nicpopup(results);
		/*document.getElementById(resultDiv).innerHTML="";*/
		
		 
	}else{
		nic_alert(results.substr(8),"V");
	}
}
}




// Checkbox with check and uncheck

//Save data

function save_data()
{
		var ins_form= document.getElementById('form-general');
		gh_id  = document.getElementById('sel_id').value;
		sel_block  = document.getElementById('sel_block').value;
		count=document.getElementById('rowCnt').value;
		var from = document.getElementById('from').value
		
		if(from != ''){
			from = from.split(' ')[0]
		}
		
		var checkedval;
		var checkedval1;
		var extval;
		var familyval;
		console.log(ins_form)
		var date = new Date();
		var time = date.getHours();
		
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!

		var yyyy = today.getFullYear();
		if (dd < 10) {
		  dd = '0' + dd;
		} 
		if (mm < 10) {
		  mm = '0' + mm;
		} 
		var today = dd + '-' + mm + '-' + yyyy;
		if((from == today) && (time >= 14)){
			nic_alert("Reservation system is not allow after 2 pm of same date","V");
			return false;
		}
		
		if($("#chk").is(":checked")){
			if(VAL_savevehicle())return true;
			checkedval = 1;
			
			}
		else{
			checkedval = 0;
			}
		if($("#chk1").is(":checked")){
			if(VAL_savevehicle1())return true;
			checkedval1 = 1;
			
			}
		else{
			checkedval1 = 0;
			}
		if($("#ext").is(":checked")){
			extval = 1;
			exist_req_id  = document.getElementById('exist_req_id').value;
			if(exist_req_id==""){
				nic_alert("Please Select the existing request ID for extension","V");
				return false;
			}
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
			document.getElementById('gh_id').value = gh_id;
			document.getElementById('block_sel').value = sel_block
			document.getElementById('familyval').value = familyval;
			document.getElementById('checkedval').value = checkedval
			document.getElementById('extval').value = extval;
			document.getElementById('checkedval1').value = checkedval1
			$('#form-general').submit();
//			arr_fieldname=["task","process_id","gh_id","sel_block","checkedval","checkedval1","extval","familyval"];
//			arr_fieldvalue=["savedataform",getprocessid(),gh_id,sel_block,checkedval,checkedval1,extval,familyval];
//			$('#popup').show();
//			ajax_post_form_field(ins_form,"s_publicdataentry",handle_saveform_data,arr_fieldname,arr_fieldvalue);
		}
}

function handle_saveform_data(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			var arr_result = results.split("-");
			var req_id = arr_result[2];
			nic_alert("Created Successfully","N");
			/*$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";*/
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","req_id"];
			arr_fieldvalue=["successmessage",getprocessid(),req_id];
			$('#popup').hide();
			ajax_post_form_field_divH(ins_form,"s_publicdataentry",arr_fieldname,arr_fieldvalue,'dataEntry');
		
			
			//post_field('s_publicdataentry',"","",false);
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}


//edit

function show_form_new()
{
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id",'gh_id'];
		arr_fieldvalue =['newpublicdataentry',getprocessid(),gh_id];	
		$('#popup').show();
		ajax_post_field_div("s_publicdataentry",handle_show_form_new,arr_fieldname,arr_fieldvalue,'dataEntry');
	}
		
	
}

function handle_show_form_new()
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

function get_list_gh2()
{
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =['createlistdata',getprocessid(),'1',gh_id,sel_block];
		$('#popup').show();
		ajax_post_field_divH("s_publicdataentry",arr_fieldname,arr_fieldvalue,'dataEntry');
	}
			

}

function showview2(req_id){
	//VAL_showform(gh_id,pid);
	if(req_id!=0){
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformview",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_publicdataentry",handle_show_form_dataentry,arr_fieldname,arr_fieldvalue);	
		$('#popup').hide();
	}
	else{
		nic_alert("Please select the list","N");
		$('#popup').hide();
		return false;
	}
}
function printview(req_id){
	//VAL_showform(gh_id,pid);
		var process_id  = document.getElementById('process_id').value;
		arr_fieldname =["task","process_id","req_id"];
		arr_fieldvalue =["printview",process_id,req_id];
		$('#popup').show();
		ajax_post_field_div( "s_publicdataentry",handle_print,arr_fieldname,arr_fieldvalue);	
	 
		 
	 
}
function handle_show_form_dataentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function printapplication(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
	 
			arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
			arr_fieldvalue =['printapplication',getprocessid(),'1',gh_id,sel_block];	
			//ajax_post_field_divH("s_publicdataentry",arr_fieldname,arr_fieldvalue,'listdiv');	
			ajax_post_field_divH("s_publicdataentry",arr_fieldname,arr_fieldvalue,'dataEntry');
	}
	 
	 
}
function handle_print()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			$('#popup').hide();
			show_nicpopup(results);
			
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

/*function handle_show_update()
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
			nic_alert(results.substr(8),"N");
		}
	}
}*/





function showview1(req_id,gh_id){
	//gh_id  = document.getElementById('sel_id').value;
	//VAL_showform(gh_id,pid);
	if(req_id!=0){
	/*	Updated by Anzar - Removed GH_ID
	 * arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformview",getprocessid(),gh_id,req_id];*/
		arr_fieldname =["task","process_id","req_id","gh_id"];
		arr_fieldvalue =["showformview",getprocessid(),req_id,gh_id];
		$('#popup').show();
		ajax_post_field_div( "s_publicdataentry",handle_view_form_dataentry,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{
		nic_alert("Please select the list","N");
		$('#popup').hide();
		return false;
	}
}
function handle_view_form_dataentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			$('#popup').hide();
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function showedit1(req_id){
	
	gh_id  = document.getElementById('sel_id').value;
	val_ghid(gh_id);
	if(req_id!=0){
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformedit",getprocessid(),gh_id,req_id];
		/*alert(arr_fieldvalue);*/
		$('#popup').show();
		ajax_post_field_div( "s_publicdataentry",handle_show_form_dataentry,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{
		nic_alert("Please select the list","N");
		$('#popup').hide();
		return false;
	}
	
	//gh_id  = document.getElementById('sel_id').value;
	//VAL_showform(gh_id,pid);
	/*if(req_id!=0){
		
		Updated by Anzar - Removed GH_ID
		 * 
		 * arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformedit",getprocessid(),gh_id,req_id];
		
		
		arr_fieldname =["task","process_id","req_id"];
		arr_fieldvalue =["showformedit",getprocessid(),req_id];
		ajax_post_field_div( "s_publicdataentry",handle_show_form_dataentry,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}*/
}
function handle_show_form_dataentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			$('#popup').hide();
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}



function editform_data()
{
		var ins_form= document.getElementById('formmod');
		req_id1  = document.getElementById('reqid').value;
		gh_id  = document.getElementById('sel_id').value;
		sel_block  = document.getElementById('sel_block').value;
		count=document.getElementById('rowCnt').value;
		var checkedval;
		var checkedval1;
		var extval;
		if($("#chk").is(":checked")){
			if(VAL_savevehicle())return true;
			checkedval = 1;
			}
		else{
			checkedval = 0;
			}
		
		if($("#chk1").is(":checked")){
			if(VAL_savevehicle1())return true;
			checkedval1 = 1;
			
			}
		else{
			checkedval1 = 0;
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
		if(VAL_savedataentry_edit()){
			arr_fieldname=["task","process_id","gh_id","sel_block","checkedval","checkedval1","extval","req_id","familyval"];
			arr_fieldvalue=["editformdata",getprocessid(),gh_id,sel_block,checkedval,checkedval1,extval,req_id1,familyval];
			ajax_post_form_field(ins_form,"s_publicdataentry",handle_showeditdataentry,arr_fieldname,arr_fieldvalue);
		}
}
function handle_showeditdataentry(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			
			nic_alert("Update Successfully.","N");
			//document.getElementById(results).innerHTML="";
			
			//post_field('s_publicdataentry',"","",false);
		    //close_and_reload();
			close_nicpopup();
			get_list_gh2();
			/*arr_fieldname =["task","gh_id","process_id","page_no"];
			arr_fieldvalue =['createlistdata',gh_id,getprocessid(),'1'];	
			ajax_post_field_divH("s_publicdataentry",arr_fieldname,arr_fieldvalue,'listdiv');	*/
			
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function show_form_update()
{
	 
	/*if (validateCheck()){*/
		arr_fieldname =["task","process_id"];
		arr_fieldvalue =['createlistedit',getprocessid()];	
		ajax_post_field_div("s_publicdataentry",handle_show_form_update,arr_fieldname,arr_fieldvalue,'dataEntry');
	/*}*/
}

function handle_show_form_update()
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
			nic_alert(results.substr(8),"N");
		}
	}
}



function showcancel(reqid){
	
	//VAL_vcid(vc_id);
	//if(validate_creation("saveformmod")){
	if(confirm("Do you want to cancel?")){
		arr_fieldname=["task","process_id","reqid"];
		arr_fieldvalue=["cancelformdata",getprocessid(),reqid];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_publicdataentry",handle_cancel,arr_fieldname,arr_fieldvalue);
	}
	//}	
}
function showstatus(reqid){
	arr_fieldname =["task","process_id","req_id"];
	arr_fieldvalue =["reqidstatus",getprocessid(),reqid];
	$('#popup').show();
	ajax_post_field_div("s_publicdataentry",handle_showstatus,arr_fieldname,arr_fieldvalue,'home_value');	

}

function handle_showstatus()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			$('#popup').hide();
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function handle_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("cancelled","V");
			get_list_gh2();
			//menu_url_call('s_publicdataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function deletelist(reqid){
	
	//VAL_vcid(vc_id);
	//if(validate_creation("saveformmod")){
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","reqid"];
		arr_fieldvalue=["deletelist",getprocessid(),reqid];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_publicdataentry",handledeletelist,arr_fieldname,arr_fieldvalue);	
	}
	//}
	
}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			get_list_gh2();
			//menu_url_call('s_publicdataentry?task=abc',getprocessid());
			//document.getElementById(resultDiv).innerHTML=results;
			 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
//--------------------------------------------------------validation---------------------------------------------------------------
function close_and_reload()

{
	close_nicpopup();
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[getprocessid(),"abc"];
	post_field("s_publicdataentry", arr_fieldname,arr_fieldvalue,false);
	
	}



function validateCheck(){
	
	var cc=$( "#sel_id option:selected" ).text();
	
	if(cc=="--select--"){
		nic_alert("Please select an item","N");
		return false;
	}
	return true;
}

function VAL_save(){
	
	val_fromdate(from_date);
	//val_todate(to_date);
	return true;
}

// Please dont change this functions Start

function addcellcb(p_index,p_name,p_ptrRef,tableRow, arr_value, arr_text)
{
 var chkBoxCell = tableRow.insertCell(p_index);
  var chkBoxColElem = document.createElement('select');
  
  chkBoxColElem.name = p_name;
  chkBoxColElem.id = p_name + p_ptrRef;
  chkBoxColElem.name = p_name + p_ptrRef;
  chkBoxCell.appendChild(chkBoxColElem);

	  var i=0;
	  for(i=0;i<arr_value.length;++i)
	  {
		  var option = document.createElement("option");
		  option.text = arr_text[i];
		  option.value = arr_value[i];
	  		  try {
	
	       chkBoxColElem.add(option, null);
		  } 
		  
  		//Standard     
  		catch(error) 
  		{
  			chkBoxColElem.add(option); 
  			// IE only    
  			}  
  
  		}

}

function addcell_ta(p_index,p_name,p_ptrRef,tableRow)
{
 var chkBoxCell = tableRow.insertCell(p_index);
  var chkBoxColElem = document.createElement('textarea');
  chkBoxColElem.name = p_name;
  chkBoxColElem.id = p_name + p_ptrRef;
  chkBoxColElem.name = p_name + p_ptrRef;
 // chkBoxColElem.className = "addressarea";
  chkBoxCell.appendChild(chkBoxColElem);
/* $('#'+p_name + p_ptrRef +'.addressarea').val("Same As Above");*/
 if( $('#'+p_name + p_ptrRef).val(''))
 {
 $('#'+p_name + p_ptrRef).val("Same As Above");
 }
 /* $('#'+p_name + p_ptrRef +'.addressarea').blur(function() {
	  if( $('#'+p_name + p_ptrRef +'.addressarea').val(''))
		  {
		  $('#'+p_name + p_ptrRef +'.addressarea').val("Same As Aboove");
		  }
	 
	  });
  $('#'+p_name + p_ptrRef +'.addressarea').focus(function() {
	  $('#'+p_name + p_ptrRef +'.addressarea').val("");
  });*/
}


function addcell(p_index,p_type,p_name,p_size,p_ptrRef,tableRow)
{
 var chkBoxCell = tableRow.insertCell(p_index);
  var chkBoxColElem = document.createElement('input');
  chkBoxColElem.type = p_type;
  chkBoxColElem.name = p_name;
  chkBoxColElem.size = p_size;
  chkBoxColElem.id = p_name + p_ptrRef;
  chkBoxColElem.name = p_name + p_ptrRef;
  if(p_index == 1){
	  chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Name'); };
  }else if(p_index == 2){
	  chkBoxColElem.onkeypress = function(event) {  return restrictInput(this, event, digitsOnly); };
  }else if(p_index == 4){
	  chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Designation'); };
  }else if(p_index == 5){
	  chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Department'); };
  }
  chkBoxCell.appendChild(chkBoxColElem);
}
function AddRow()
{
  var tableId = document.getElementById('dynamicTable');
  var noOfRows = tableId.rows.length;
  var ptrRef = noOfRows-1;
  arr_value =["0","M","F"];
  arr_text =["--select--","Male","Female"];
  var tableRow = tableId.insertRow(noOfRows);	
  
  arr_fields=["name","age","gender","desg","dept","address"];
  
  addcell(0,'checkbox','chkbox',2,ptrRef,tableRow);
  addcell(1,'text',arr_fields[0],30,ptrRef,tableRow);
  addcell(2,'text',arr_fields[1],4,ptrRef,tableRow);
  addcellcb(3,arr_fields[2],ptrRef,tableRow,arr_value,arr_text);
  addcell(4,'text',arr_fields[3],25,ptrRef,tableRow);
  addcell(5,'text',arr_fields[4],25,ptrRef,tableRow);
  addcell_ta(6,arr_fields[5],ptrRef,tableRow);
  
  var count = parseInt(document.getElementById('rowCnt').value);
  count++;			
  document.getElementById('rowCnt').value = count;	
 		
 
}
function renameids()
{
  var i, cb, en, ed, len, tbl;

var count=0;
  tbl = document.getElementById('dynamicTable');
  len = tbl.rows.length;
  for (i = 0; i < len-1; i++)
  {

k=i;
   while(true )
	   
  {
	   cb = document.getElementById("chkbox" + k);
	   if(cb !=null) 
		{
	    txt_name = document.getElementById("name" + k);
	    txt_age = document.getElementById("age" + k);
	    cb_gender=document.getElementById("gender" + k);
	    txt_desg = document.getElementById("desg" + k);
	    cb_gender=document.getElementById("gender" + k);
	    txt_dept = document.getElementById("dept" + k);
	    txt_address = document.getElementById("address" + k);
	    
	    
	    cb.id = "chkbox" + i; //dont change
        cb.name="chkbox" + i;
        
	    txt_name.id = "name" + i;
	    txt_name.name = "name" + i;
	    
	    txt_age.id = "age" + i;
	    txt_age.name = "age" + i;
	    

	    cb_gender.id="gender" +i;
	    cb_gender.name="gender" +i;
	    

	    txt_desg.id="desg"+i;
	    txt_desg.name="desg"+i;
	    
	    txt_dept.id="dept"+i;
	    txt_dept.name="dept"+i;
	    
	    txt_address.id="address"+i;
	    txt_address.name="address"+i;
       
        
        break;
		}
	   
	k=k+1;
      
  }
        count=count+1;
	   
  }
  document.getElementById('rowCnt').value = count;	
}



function RemoveRow()
{
  var i, cb, len=2, tbl;
  tbl = document.getElementById('dynamicTable');
  len = tbl.rows.length;

  
  for (i=0; i < len-2; i++) {
    cb = document.getElementById("chkbox" + i);
      if (cb.checked) {   	 
    		  tbl.deleteRow(i+1);    		
    	        renameids();    	   
    	        RemoveRow();
      } 
      else
    	  {
    	 len++;
    	  } 
  }   
}



//Please dont change this functions End





//--------------------------------validation---------------------------------------------------------------------



function VAL_savedataentry(){
	
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var category= document.getElementById('category').value;
//	var email_id= document.getElementById('email').value;
	var fullPath = document.getElementById('fname').value;
	console.log(category);
	if((val_file_purpose(fullPath,purpose)) && (val_ghid(gh_id)) && (val_purpose(purpose)) && (val_category(category)) &&  (val_fromdate_public(from_date)) && (val_todate_purpose(to_date,purpose)) &&(val_pmobileno(mobile_no)) /*&& (val_pemailid(email_id))*/ && (VAL_roommf()) && (val_address(address1)) && (VAL_guest()))
		return true;
}

function VAL_savedataentry_edit(){
	
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var category= document.getElementById('category').value;
//	var email_id= document.getElementById('email').value;
	
	if((val_ghid(gh_id)) && (val_purpose(purpose)) && (val_category(category)) && (val_fromdate(from_date)) && (val_todate(to_date)) &&(val_pmobileno(mobile_no)) /*&& (val_pemailid(email_id))*/ && (VAL_roommf()) && (val_address(address1)) && (VAL_guest()))
		return true;
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

//validate pagination
function VAL_pagination_data(page_no,gh_id){
	val_pageno(page_no);
	val_ghid(gh_id);
}

function VAL_guest(){
	count=document.getElementById('rowCnt').value;
	var b=0;
	for(i=0;i<count;i++)
	{
		var name  = document.getElementById('name'+i).value;
		var age	=document.getElementById('age'+i).value;
		var gender=document.getElementById('gender'+i).value;
//		var desg  = document.getElementById('desg'+i).value;
//		var dept	=document.getElementById('dept'+i).value;
		var address=document.getElementById('address'+i).value;
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) /*&& (val_designation(desg)) && (val_department(dept))*/ && (val_address1(address)))
		{
			b=1;
		}
		else
			{
				b=0;
			}
	}	
	if(b==1)
		return true;
}


function VAL_savevehicle(){
	var mode_of_arrival  = document.getElementById('mode').value;
	var arrival_time  = document.getElementById('arrival').value;
	var arrival_place  = document.getElementById('place').value;
//	var no_seats_req  = document.getElementById('seat').value;
	var ftno = document.getElementById('ftno').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_arrival_time(arrival_time)) && (val_arrival_place(arrival_place)) ) return false;
	return true;

}

function VAL_savevehicle1(){
	var mode_of_arrival  = document.getElementById('mode1').value;
	var arrival_time  = document.getElementById('arrival1').value;
	var arrival_place  = document.getElementById('place1').value;
//	var no_seats_req  = document.getElementById('seat1').value;
	var ftno = document.getElementById('ftno1').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_arrival_time1(arrival_time)) && (val_arrival_place(arrival_place))) return false;
	return true;

}


function showconfreq(pro_id,req_id){
		arr_fieldname=["task","process_id","pro_id","req_id"];
		arr_fieldvalue=["statusshow",getprocessid(),pro_id,req_id];
		ajax_post_field("s_publicdataentry",handle_showconfreq,arr_fieldname,arr_fieldvalue);	

	
}
function handle_showconfreq(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			/*document.getElementById(resultDiv).innerHTML="";*/
					 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function pagination_home(page_no,gh_id){
	
	//VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id"];
	arr_fieldvalue =[getprocessid(),"createhomelist",page_no,gh_id];
	ajax_post_field_divH("s_publicdataentry", arr_fieldname,arr_fieldvalue,'dataEntry');
}

function print_pubapp(req_id){
	 arr_fieldname=["task","process_id","req_id"];
		arr_fieldvalue=["generateapplication",getprocessid(),req_id];
		post_field("doc_handler",arr_fieldname,arr_fieldvalue,true); 
	 
	 
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

function fileInfo(file,attachment) {
	var validateFile = checkfile(file);
	if (validateFile) {
		console.log(file.files[0].name);
   }else {
	   if(attachment=="fname")
		   document.getElementById('fname').value = '';
	   else
		   document.getElementById('fname').value = '';
	   	   console.log("invalid file");
   }

}

function checkfile(uploadFile) {
	var validExts = new Array(".pdf");
	var fileExt = uploadFile.value;
	fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
	if (validExts.indexOf(fileExt) < 0) {
		alert("Invalid file selected, valid files are of "+ validExts.toString() + " types.");
		uploadFile.value= '';
		return false;
	} else
		return true;
}
