
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_allotment_full_list", arr_fieldname, arr_fieldvalue, 'header_div');
}
function allotmentfulllist(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["roomsearch",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_allotment_full_list",handleallotmentfulllist, arr_fieldname,arr_fieldvalue,'list');
	}
}
function totalallotmentsreport(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'totalallotmentsreport', gh_id, getprocessid(),
				page_no, sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}

function searchtotalallotmentdata(page_no) {
	
	console.log("hi");
	var gh_id = document.getElementById('sel_id').value;
	var year = document.getElementById('date').value;
	var month = document.getElementById('sel_feedbackmonth').value;
	var startdate = document.getElementById('startdate').value;
	var enddate = document.getElementById('enddate').value;
	var sel_block = document.getElementById('sel_block').value;

	let radiochecked=false;
	const radioButtons = document.querySelectorAll('input[name="check"]');
	//console.log("hi");
    for(const radioButton of radioButtons){
    	if (radioButton.checked) {
    		radiochecked=true;
    		break;
    	}
    }   

    if(radiochecked){
    	var forward = true;
    	var selected = document.querySelector('input[name="check"]:checked').id;
    	if (selected == 'rdobtnone') {
    		if (year == "") {
    			nic_alert("Please Enter year", "N");
    			forward = false;
    			console.log(selected);
    		} else if (month == "") {
    			nic_alert("Please Enter month", "N");
    			forward = false;
    			console.log(selected);
    		}

    	} else if (selected == 'rdobtntwo') {
    		if (startdate == "") {
    			nic_alert("please Enter StartDate", "N");
    			forward = false;
    			console.log(selected);
    		} else if (enddate == "") {
    			nic_alert("please Enter Enddate", "N");
    			forward = false;
    			console.log()
    		}
    	}else if(selected ==""){
    		nic_alert("please Enter data");
    	}
    	
    	if (forward == true) {
    		arr_fieldname = [ "task", "process_id", "gh_id", "year", "month","startdate", "enddate", "sel_block", "page_no" ];
    		arr_fieldvalue = [ "searchtotalallotmentdata", getprocessid(), gh_id, year, month,startdate, enddate, sel_block, page_no ];
    		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,'listdiv');
    	}
    }else{
    	console.log("please select");
    	nic_alert("please select one radio button","N");
    	//alert("Please Select One");
    	return false;
    }	
}
function print_pdf_totalallotment() {
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var startdate = document.getElementById('startdate').value;
	var enddate = document.getElementById('enddate').value;
	var year = document.getElementById('date').value;
	var month = document.getElementById('sel_feedbackmonth').value;
	arr_fieldname = [ "process_id", "task","year","month","startdate", "enddate", "gh_id", "sel_block" ];
	arr_fieldvalue = [ getprocessid(), "printallotment",year,month, startdate, enddate, gh_id, sel_block ];
	post_field("doc_handler", arr_fieldname, arr_fieldvalue, true);
}
function handleallotmentfulllist(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			}else{
			nic_alert(results.substr(8),"N");
		}
	}
}
function goallotmentfulllist(){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(VAL_submit())
	{
		arr_fieldname=["task","process_id","from","gh_id","sel_block"];
		arr_fieldvalue=["doprocess",getprocessid(),from,gh_id,sel_block];//allotprocessid
		ajax_post_field_div("s_allotment_full_list",handlegoallotmentfulllist, arr_fieldname,arr_fieldvalue,'listlist');
	}
}
function handlegoallotmentfulllist(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}
function EnableDisableTextBox() {
	var selected = document.querySelector('input[name="check"]:checked').id;
	var date = document.getElementById("date");
	var sel_feedbackmonth = document.getElementById("sel_feedbackmonth");
	var startdate = document.getElementById("startdate");
	var enddate = document.getElementById("enddate");
	if (selected == 'rdobtntwo') {
		date.value = "";
		sel_feedbackmonth.value = "";
		date.disabled = true;
		sel_feedbackmonth.disabled = true;
		startdate.disabled = false;
		enddate.disabled = false;
	} else if (selected == 'rdobtnone') {
		startdate.value = "";
		enddate.value = "";
		date.disabled = false;
		sel_feedbackmonth.disabled = false;
		startdate.disabled = true;
		enddate.disabled = true;
	}
}
function validateChecking() {
	gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if (gh_id == 0) {
		nic_alert("Please Select the  City", "V");
	} else if (sel_block == 0) {
		nic_alert("Please Select the guest house", "V");
	} else {
		return true;
	}
}
function VAL_submit(){
	 
	var from_date  = document.getElementById('date').value;
	if(val_fromdate(from_date))
		return true;
}
