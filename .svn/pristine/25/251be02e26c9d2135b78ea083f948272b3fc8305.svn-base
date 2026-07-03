
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_dataentry", arr_fieldname, arr_fieldvalue, 'header_div');
}

function getDesg(){
	ins_form= document.getElementById('formmod');
	var category = document.getElementById('category').value;
	
	arr_fieldname =["task","process_id","category"];
	arr_fieldvalue =["getdesignation",getprocessid(),category];
	ajax_post_form_field_divH(ins_form, "s_dataentry", arr_fieldname, arr_fieldvalue, 'desgtype');
}

function getExtension(){
	if($("#ext").is(":checked")){
		$("#extdata").removeClass('hide');
	}
	else{
		$("#extdata").addClass('hide');
	}	

}

function getExistingReq(){
	var gh_id = document.getElementById('sel_id').value;
	$('#nic-popup').modal('show');
	$('#popup').show();
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =['getExistingReq',getprocessid(),gh_id];	
	ajax_post_field_div("s_dataentry",handle_getExistingReq,arr_fieldname,arr_fieldvalue,'dataEntry');
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

function search_existing(){
	ins_form= document.getElementById('form-guest-selection');
	var gh_id = document.getElementById('gh_id').value;
	var req_id = document.getElementById('req_id').value;
	var name = document.getElementById('name').value;
	var date = document.getElementById('date').value;
	var desig = document.getElementById('desig').value;
	if(req_id==""&&name==""&&date==""&&desig==""){
		nic_alert("Please enter data to search","N");
	}else{
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =["selectedExistingReq",getprocessid(),gh_id];
		ajax_post_form_field_divH(ins_form, "s_dataentry", arr_fieldname, arr_fieldvalue, 'selectedReq');
	}
}
//EDITED BY ATHIRA
//selectExisting
function selectExisting(p_req_id,name,age,gender,designation,department,address){
	close_nicpopup();

	$('#exist_req_id').val(p_req_id);
	if(name=='null'){
		$('#name0').val("");
	}else{
		$('#name0').val(name);}
	if(age=='null'){
		$('#age0').val("");
	}else{
		$('#age0').val(age);}
	if(gender== 'null'){
		$('#gender0').val("");
	}else{
		$('#gender0').val(gender);
	}
	if(designation=='null'){
		$('#desg0').val("");
	}else{
		$('#desg0').val(designation);
	}
	alert("dept :"+department);
	if(department=='null'){
		$('#dept0').val('');
	}else{
		$('#dept0').val(department);

	}
	if(address=='null'){
		$('#address0').val("");
	}else{
		$('#address0').val(address);
	}

//	END EDITING	
}

//new dataentry main menu
function show_form_new()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the city","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =['newdataentry',getprocessid(),gh_id];	
		ajax_post_field_div("s_dataentry",handle_show_form_new,arr_fieldname,arr_fieldvalue,'dataEntry');
	}

}
function editGuestDetails(req_id){
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	val_ghid(gh_id);
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id","page_no"];
		arr_fieldvalue =["editguestdetails",getprocessid(),gh_id,req_id,"1"];
		ajax_post_field_div( "s_dataentry",handle_show_form_new,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
	else{

		if(gh_id==0){
			nic_alert("Please Select the  City","V");
		}
		else if(sel_block==0){
			nic_alert("Please Select the guest house","V");
		}else{
			return false;
		}
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

//Update dataentry main menu
function get_list_gh2(page_no)   //list gad_details(REQ) data
{
	val_ghid(gh_id);
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the city","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['createlistdata',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'dataEntry');
	}	
}
function showcancelReservation(page_no)   //list gad_details(REQ) data
{
	val_ghid(gh_id);
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the city","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['cancellistdata',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'dataEntry');
	}	
}

//Cancel dataentry main menu
function show_form_cancel(page_no)//list gad_details(status!='CAN') data
{

	val_ghid(gh_id);
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the city","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","gh_id","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =['createlistcancel',gh_id,getprocessid(),page_no,gh_id,sel_block];	
		ajax_post_field_div("s_dataentry",handle_show_form_cancelsel,arr_fieldname,arr_fieldvalue,'dataEntry');
	}
}

function handle_show_form_cancelsel()
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
//end

function view_conformation(page_no){

	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the city","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["createlistconformation",getprocessid(),page_no,gh_id,sel_block];
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'dataEntry');
	}
}

//Save button new dataentry
function save_data()
{
	//var ins_form= document.getElementById('form-general');
	var ins_form= document.getElementById('formmod');
	console.log(ins_form);

	gh_id  = document.getElementById('sel_id').value;
	sel_block  = document.getElementById('sel_block').value;
	count=document.getElementById('rowCnt').value;
	var duringstay= $("input[name='duringstay']:checked").val();
	var checkedval;
	var checkedval1;
	var extval;
	var familyval;
	var roomtype;
	//checked & unchecked
	if($("#chk").is(":checked")){
		if(VAL_savevehicle())return true;
		checkedval = 1;
		roomtype="RC";
	}
	else{
		checkedval = 0;
		roomtype="R";
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
		}/*else if(exist_req_id!="G"&&exist_req_id!="H"&&exist_req_id!="P"){
				nic_alert("Invalid existing request ID for extension","V");
				return false;
			}*/
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
		arr_fieldname=["task","process_id","gh_id","sel_block","checkedval","checkedval1","extval","familyval","duringsty","roomtype"];
		arr_fieldvalue=["savedataform",getprocessid(),gh_id,sel_block,checkedval,checkedval1,extval,familyval,duringstay,roomtype];
		ajax_post_form_field(ins_form,"s_dataentry",handle_saveform_data,arr_fieldname,arr_fieldvalue);
	}
}
function handle_saveform_data(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){

			nic_alert("Created Successfully.","N");
			/*$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";*/
			show_form_new();

		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

//Search data list
function searchdata(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var date = document.getElementById('date').value;
	if(req_id==""&&desig==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","req_id","name","desig","date","page_no"];
		arr_fieldvalue=["search",getprocessid(),gh_id,req_id,name,desig,date,page_no];
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

//view sub menu 
function showview2(req_id){
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformview",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_dataentry",handle_show_form_dataentry,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{

		if(gh_id==0){
			nic_alert("Please Select the city","V");
		}
		else if(sel_block==0){
			nic_alert("Please Select the guest house","V");
		}else{
			return false;
		}
	}
}
function handle_show_form_dataentry()
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

//edit sub menu
function showedit2(req_id,page_no){
	gh_id  = document.getElementById('sel_id').value;
	val_ghid(gh_id);
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id","page_no"];
		arr_fieldvalue =["showformedit",getprocessid(),gh_id,req_id,page_no];
		ajax_post_field_div( "s_dataentry",handle_show_form_new,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
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

function handle_show_form_dataentry1()
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

function deletelist2(reqid){
	gh_id  = document.getElementById('sel_id').value;
	//VAL_vcid(vc_id);
	//if(validate_creation("saveformmod")){
	if(confirm("Do you want to delete?")){
		$('#popup').show();
		arr_fieldname=["task","process_id","reqid","gh_id"];
		arr_fieldvalue=["deletelist",getprocessid(),reqid,gh_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_dataentry",handledeletelist,arr_fieldname,arr_fieldvalue);	
	}

	//}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","N");
			get_list_gh2('1');
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function showcancel2(reqid){
	if(confirm("Do you want to cancel?")){
		$('#popup').show();
		arr_fieldname=["task","process_id","reqid"];
		arr_fieldvalue=["cancelformdata",getprocessid(),reqid];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_dataentry",handle_cancel,arr_fieldname,arr_fieldvalue);	
	}
	//}	
}
function handle_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("cancelled","N");
			get_list_gh2('1');
			$('#popup').hide();
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//click update function
function editform_data()
{
	var ins_form= document.getElementById('formmod');
	console.log(ins_form);
	req_id1  = document.getElementById('reqid').value;
	count=document.getElementById('rowCnt').value;
	var checkedval;
	var checkedval1;
	var extval;
	var roomtype;
	var duringstay= $("input[name='duringstay']:checked").val();
	if($("#chk").is(":checked")){
		if(VAL_savevehicle())return true;
		checkedval = 1;
//		roomtype="RC";
	}
	else{
		checkedval = 0;
//		roomtype="R";
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
	
	
	var category= document.getElementById('category').value;
	
	
	if(VAL_savedataentry()){
		arr_fieldname=["task","process_id","checkedval","checkedval1","extval","req_id","familyval","cate","duringstay"];
		arr_fieldvalue=["editformdata",getprocessid(),checkedval,checkedval1,extval,req_id1,familyval,category,duringstay];
		ajax_post_form_field(ins_form,"s_dataentry",handle_showeditdataentry,arr_fieldname,arr_fieldvalue);
	}
}
function handle_showeditdataentry(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){

			nic_alert("Update Successfully.","N");

			close_nicpopup();
			get_list_gh2('1');

		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

//Search cancel list
function searchcancel(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var date = document.getElementById('date').value;
	if(req_id==""&&desig==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","req_id","name","desig","date","page_no"];
		arr_fieldvalue=["searchcancel",getprocessid(),gh_id,req_id,name,desig,date,page_no];
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}
function searchcancelcancel(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var date = document.getElementById('date').value;
	if(req_id==""&&desig==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","req_id","name","desig","date","page_no"];
		arr_fieldvalue=["searchcancel1",getprocessid(),gh_id,req_id,name,desig,date,page_no];
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

//Search conform list
function searchconformlist(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var process =  document.getElementById('sel_process').value;
//	var office =  document.getElementById('orderno').value;
	var office = "";
	var date = document.getElementById('date').value;
	if(process==0 && date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","process","office","date","page_no"];
		arr_fieldvalue=["searchconformlist",getprocessid(),gh_id,process,office,date,page_no];
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

function showcancel3(req_id)
{
	//req_id= $("input[type='radio']:checked").val();
	if(confirm("Do you want to cancel?")){
		arr_fieldname =["task","process_id","req_id"];
		arr_fieldvalue =['fullcancel',getprocessid(),req_id];	
		ajax_post_field_div("s_dataentry",handle_show_form_cancel,arr_fieldname,arr_fieldvalue,'dataEntry');
	}
}

function showcancel4(req_id)
{
	//req_id= $("input[type='radio']:checked").val();
	if(confirm("Do you want to cancel?")){
		arr_fieldname =["task","process_id","req_id"];
		arr_fieldvalue =['cancelreservation',getprocessid(),req_id];	
		ajax_post_field_div("s_dataentry",handle_show_form_cancel_reservation,arr_fieldname,arr_fieldvalue,'dataEntry');
	}
}
function handle_show_form_cancel_reservation(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Cancelled","V");
			//get_list_gh2();
			showcancelReservation('1');
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function handle_show_form_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Cancelled","V");
			//get_list_gh2();
			show_form_cancel('1');
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function viewconf(pro_id){
	$('#popup').show();
	arr_fieldname=["task","process_id","pro_id"];
	arr_fieldvalue=["conformationshow",getprocessid(),pro_id];
	ajax_post_field("s_dataentry",handle_conformationview,arr_fieldname,arr_fieldvalue);	


}
function handle_conformationview(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML="";
			$('#popup').hide();

		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function view_pdf_process(pro_id){
	arr_fieldname =["process_id","task","pro_id"];
	arr_fieldvalue =[getprocessid(),"generatetestpdf",pro_id];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function showEditGuestDetails(page_no)
{
	val_ghid(gh_id);
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['listeditguestdetails',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_div("s_dataentry",handle_show_form_cancelsel,arr_fieldname,arr_fieldvalue,'dataEntry');
	}
}




function doeditGuestDetails()
{
	var ins_form= document.getElementById('formmod');
	console.log(ins_form);
	req_id1  = document.getElementById('reqid').value;
	count=document.getElementById('rowCnt').value;
	var checkedval;
	var checkedval1;
	var extval;
	var duringstay= $("input[name='duringstay']:checked").val();
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
	
	
	var category= document.getElementById('category').value;
	
	
	if(VAL_savedataentry()){
		arr_fieldname=["task","process_id","checkedval","checkedval1","extval","req_id","familyval","cate","duringstay"];
		arr_fieldvalue=["editformdata",getprocessid(),checkedval,checkedval1,extval,req_id1,familyval,category,duringstay];
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
//--------------------------------validation---------------------------------------------------------------------



function VAL_savedataentry(){
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var category= document.getElementById('category').value;
	
	console.log(from_date);
	if(purpose=='Onduty'||purpose=='civil service(PT)'){
		if((val_fromdate(from_date)) && (val_fromdatenottakeprevusdate(from_date)) && (val_todateonduty(to_date)) && (val_purpose(purpose)) && val_category(category) &&(val_mobileno(mobile_no)) /*&& (val_emailid(email_id))*/ && (VAL_roommf())/* && (val_address(address1)) */&& (VAL_guest()))
		{
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
	}else{
		if((val_ghid(gh_id)) && (val_purpose(purpose)) && (val_category(category)) &&  (val_fromdate_public(from_date)) &&(val_todate_purpose(to_date,purpose)) &&(val_pmobileno(mobile_no)) /*&& (val_pemailid(email_id))*/ && (VAL_roommf()) /*&& (val_address(address1)) */&& (VAL_guest()))
		{
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
//		var desg  = document.getElementById('desg'+i).value;
//		var dept	=document.getElementById('dept'+i).value;
		var address=document.getElementById('address'+i).value;
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) /*&& (val_designation(desg)) && (val_department(dept))*/ && (val_address1(address)))
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

function VAL_savevehicle(){
	var mode_of_arrival  = document.getElementById('mode').value;
	var arrival_time  = document.getElementById('arrival').value;
	var arrival_place  = document.getElementById('place').value;
	var no_seats_req  = document.getElementById('seat').value;
	var ftno = document.getElementById('ftno').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_no_seats_req(no_seats_req)) && (val_arrival_time(arrival_time)) && (val_arrival_place(arrival_place)) ) return false;
	return true;
}

function VAL_savevehicle1(){
	var mode_of_arrival  = document.getElementById('mode1').value;
	var arrival_time  = document.getElementById('arrival1').value;
	var arrival_place  = document.getElementById('place1').value;
	var no_seats_req  = document.getElementById('seat1').value;
	var ftno = document.getElementById('ftno1').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_no_seats_req(no_seats_req)) && (val_arrival_time1(arrival_time)) && (val_arrival_place(arrival_place)) ) return false;
	return true;
}
///////////



$(document).ready(function() {



	$('#dynamicTable').on('click', '.autoc', function() {
		var $curRow = $(this).closest('tr'),
		$newRow = $curRow.clone(true);

		console.log($curRow);
		// $curRow.after($newRow);
		//  console.log('added');
	});






	$(document).on('change', '#roomtype', function() {
		var roomtype = $('#roomtype').find(":selected").val();
		if(roomtype=="D")
		{
			$('.roombox').hide();
			$('#rooms').attr('disabled','disabled');
		}
		else
		{
			$('.roombox').show();

			$('#rooms').removeAttr('disabled','disabled');
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




//$('#delete_record').addClass("disabled");



//Please dont change this functions Start

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
	chkBoxCell.appendChild(chkBoxColElem);
}


function addcell(p_index,p_type,p_name,p_size,p_ptrRef,tableRow)
{
	var chkBoxCell = tableRow.insertCell(p_index);
	var chkBoxColElem = document.createElement('input');
	chkBoxColElem.type = p_type;
//	chkBoxColElem.name = p_name;
	chkBoxColElem.size = p_size;
	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
	if(p_index == 2){
		chkBoxColElem.onkeypress = function(event) {  return restrictInput(this, event, digitsOnly); };
	}else if(p_index == 4){
		chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Designation'); };
	}else if(p_index == 5){
		chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Department'); };
	}
	chkBoxCell.appendChild(chkBoxColElem);
}



function addcell2(p_index,p_type,p_name,p_size,p_ptrRef,tableRow)
{
	var chkBoxCell = tableRow.insertCell(p_index);
	var chkBoxColElem = document.createElement('input');
	chkBoxColElem.type = p_type;
	chkBoxColElem.size = p_size;
	chkBoxColElem.onkeyup = function(event) {  getguestlist_db(event,this,p_ptrRef); };
	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
	chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Name'); };
	chkBoxCell.appendChild(chkBoxColElem);
}






function AddRow()
{

	$(".autocomplete-ul").remove();
	var tableId = document.getElementById('dynamicTable');
	var noOfRows = tableId.rows.length;
	var ptrRef = noOfRows-1;
	arr_value =["0","M","F"];
	arr_text =["--select--","Male","Female"];
	var tableRow = tableId.insertRow(noOfRows);	

	arr_fields=["name","age","gender","desg","dept","address"];

	addcell(0,'checkbox','chkbox',2,ptrRef,tableRow);
	addcell2(1,'text',arr_fields[0],30,ptrRef,tableRow);
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
				txt_dept = document.getElementById("dept" + k);
				txt_address = document.getElementById("address" + k);


				cb.id = "chkbox" + i; //dont change
				cb.name = "chkbox" + i;

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
//Please dont change this functions End

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


/*


function go_search(){
		var gh_id = document.getElementById('sel_id').value;
		var desig =  document.getElementById('desig').value;
				var req_id=  document.getElementById('requestid').value;
				var name =  document.getElementById('name').value;
				var date = document.getElementById('date').value;
				if(req_id==""&&desig==0&&name==""&&date==""){
					nic_alert("Please Enter data for searching","N");
				}else{
					arr_fieldname=["task","process_id","req_id","designation","gh_id","name","date"];
					arr_fieldvalue=["getsearchrequest",getprocessid(),req_id,desig,gh_id,name,date];
					ajax_post_field_divH("s_dataentry", arr_fieldname,arr_fieldvalue,'search');
				}
	}


//end
 */