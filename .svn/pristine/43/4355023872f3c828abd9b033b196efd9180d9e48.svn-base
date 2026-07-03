
//1.conformation report list sub menu
function show_conformationGO(page_no){
	
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["createlistconformationGO",getprocessid(),page_no,gh_id,sel_block];
		ajax_post_field_div("s_confirmation_order",handle_show_form_conformationGO,arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_confirmation_order", arr_fieldname, arr_fieldvalue, 'header_div');
}
function handle_show_form_conformationGO()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//Search conform list
function searchconformlistGO(page_no){
		var gh_id = document.getElementById('sel_id').value;
		var process =  document.getElementById('sel_process').value;
//		var office =  document.getElementById('orderno').value;
		var office = "";
		var date = document.getElementById('date').value;
		if(process==0 && date==""){
			nic_alert("Please Enter data for searching","N");
		}else{
			arr_fieldname=["task","process_id","gh_id","process","office","date","page_no"];
			arr_fieldvalue=["searchconformlistGO",getprocessid(),gh_id,process,office,date,page_no];
			ajax_post_field_divH("s_confirmation_order",arr_fieldname,arr_fieldvalue,'home_valu');	
		}
}

function showconfGO(pro_id){
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_process').value;
	$('#popup').show();
		arr_fieldname=["task","process_id","pro_id","sel_block","gh_id"];
		arr_fieldvalue=["conformationshowGO",getprocessid(),pro_id,sel_block,gh_id];
		ajax_post_field("s_confirmation_order",handle_conformationGO,arr_fieldname,arr_fieldvalue);	
}

function handle_conformationGO(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}


function view_pdf_processGO(pro_id){
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_process').value;
	arr_fieldname =["process_id","task","pro_id","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"generateconfirmationpdf",pro_id,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function view_pdf_processReport(pro_id){
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_process').value;
	
	arr_fieldname =["process_id","task","pro_id","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"generatetestpdfreport",pro_id,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}