function getBlocks() {
	ins_form = document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname = [ "task", "process_id", "gh_id" ];
	arr_fieldvalue = [ "getblock", getprocessid(), gh_id ];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_feedback", arr_fieldname,
			arr_fieldvalue, 'header_div');
}

function searchdata(page_no) {
	var gh_id = document.getElementById('sel_id').value;
	var startdate = document.getElementById('startdate').value;
	var enddate = document.getElementById('enddate').value;
	var category = document.getElementById('category').value;
	var feedback = document.getElementById('feedback').value;
	var sel_block = document.getElementById('sel_block').value;
	console.log(category);
	if (startdate == "" || enddate == "" || category == 0 || feedback == 0) {
		nic_alert("Please Enter data for searching", "N");
	} else {
		arr_fieldname = [ "task", "process_id", "gh_id", "startdate",
				"enddate", "category", "feedback", "sel_block", "page_no" ];
		arr_fieldvalue = [ "search", getprocessid(), gh_id, startdate, enddate,
				category, feedback, sel_block, page_no ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'listdiv');
	}
}

function searchtotaldata(page_no) {
	
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
    		arr_fieldname = [ "task", "process_id", "gh_id", "year", "month",
    				"startdate", "enddate", "sel_block", "page_no" ];
    		arr_fieldvalue = [ "totalsearch", getprocessid(), gh_id, year, month,startdate, enddate, sel_block, page_no ];
    		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,'listdiv');
    	}
    }else{
    	console.log("please select");
    	nic_alert("please select one radio button","N");
    	//alert("Please Select One");
    	return false;
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

//_____________________________feedback_11___________________________


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


// ____________________________feedback6_____________________
function searchdatafilter(page_no) {
	var gh_id = document.getElementById('sel_id').value;
	var fromdate = document.getElementById('fromdate').value;
	var todate = document.getElementById('todate').value;
	var category = document.getElementById('category').value;
	var sel_block = document.getElementById('sel_block').value;
	console.log(category);
	if (fromdate == "" || todate == "" && category == 0) {
		nic_alert("Please Enter data for searching", "N");
	} else {

		arr_fieldname = [ "task", "process_id", "gh_id", "fromdate", "todate",
				"category", "sel_block", "page_no" ];
		arr_fieldvalue = [ "searchdata", getprocessid(), gh_id, fromdate,
				todate, category, sel_block, page_no ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'home_value');
	}
}

function searchdatafilterone(page_no) {
	var gh_id = document.getElementById('sel_id').value;

	var sel_block = document.getElementById('sel_block').value;
	var req_id = document.getElementById('req_id').value;
	console.log(req_id);

	var name = document.getElementById('name').value;
	console.log(name);
	if (req_id == "" && name == "") {
		/*
		 * window.alert("hiii 111111"); nic_alert("Please Enter data for
		 * searching", "N");
		 */
	} else {
		/* window.alert("hiii 22222"); */
		arr_fieldname = [ "task", "process_id", "gh_id", "req_id", "name",
				"sel_block", "page_no" ];
		arr_fieldvalue = [ "searchdatafilter", getprocessid(), gh_id, req_id,
				name, sel_block, page_no ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'home_value');
	}
}

function list_of_feedback(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'showlist', gh_id, getprocessid(), page_no,
				sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}

function print_pdf_feedbackUser(category, feedback, startdate, enddate) {
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname = [ "process_id", "task", "category", "feedback",
			"startdate", "enddate", "gh_id", "sel_block" ];
	arr_fieldvalue = [ getprocessid(), "generateprintFeedbackUser", category,
			feedback, startdate, enddate, gh_id, sel_block ];
	post_field("doc_handler", arr_fieldname, arr_fieldvalue, true);
}

/*__________________________________print total feedback user______________________________________*/
function print_pdf_totalfeedbackUser() {
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var startdate = document.getElementById('startdate').value;
	var enddate = document.getElementById('enddate').value;
	var year = document.getElementById('date').value;
	var month = document.getElementById('sel_feedbackmonth').value;
	arr_fieldname = [ "process_id", "task","year","month","startdate", "enddate", "gh_id", "sel_block" ];
	arr_fieldvalue = [ getprocessid(), "generateprintTotalFeedbackUser",year,month, startdate, enddate, gh_id, sel_block ];
	post_field("doc_handler", arr_fieldname, arr_fieldvalue, true);
}

/*__________________________________print allotments pdf______________________________________*/



/* _____________________________print as pdf_________________________________*/

function printAsPdf() {
	var gh_id = document.getElementById('sel_id').value;
	var fromdate = document.getElementById('fromdate').value;
	var todate = document.getElementById('todate').value;
	var category = document.getElementById('category').value;
	var sel_block = document.getElementById('sel_block').value;
	console.log(category);

	arr_fieldname = [ "process_id", "task", "category", "fromdate", "todate",
			"gh_id", "sel_block" ];
	arr_fieldvalue = [ getprocessid(), "generatepdfFeedbackUser", category,
			fromdate, todate, gh_id, sel_block ];
	post_field("doc_handler", arr_fieldname, arr_fieldvalue, true);
}

function feedbackdetails(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'showlist', gh_id, getprocessid(), page_no,
				sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}
function feedbackreport(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'feedbackreport', gh_id, getprocessid(), page_no,
				sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}

//________________________________total feedback report___________________________________
function totalfeedbackreport(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'totalfeedbackreport', gh_id, getprocessid(),
				page_no, sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}

//_________________________totalallotmentreport________________________
function commentsreport(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'totalcommentsreport', gh_id, getprocessid(),
				page_no, sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}
function searchtotalcommentsdata(page_no) {
	
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
    		arr_fieldvalue = [ "searchtotalcommentsdata", getprocessid(), gh_id, year, month,startdate, enddate, sel_block, page_no ];
    		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,'listdiv');
    	}
    }else{
    	console.log("please select");
    	nic_alert("please select one radio button","N");
    	//alert("Please Select One");
    	return false;
    }	
}
//____________________________________________________

function guestfeedbackreport(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'guestfeedbackreport', gh_id, getprocessid(),
				page_no, sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}

// ________________________________total feedback
// page____________________________________________
function guestfeedbackreport(page_no) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	if (validateChecking()) {
		arr_fieldname = [ "task", "gh_id", "process_id", "page_no", "sel_block" ];
		arr_fieldvalue = [ 'guestfeedbackreport', gh_id, getprocessid(),
				page_no, sel_block ];
		ajax_post_field_divH("s_feedback", arr_fieldname, arr_fieldvalue,
				'list');
	}
}

function viewfeedback(req_id, feedbackid) {
	gh_id = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	$('#popup').show();
	arr_fieldname = [ "task", "process_id", "gh_id", "sel_block", "req_id",
			"feedbackid" ];
	arr_fieldvalue = [ "showviewfeedback", getprocessid(), gh_id, sel_block,
			req_id, feedbackid ];
	ajax_post_field_div("s_feedback", handle_show_form_moderation,
			arr_fieldname, arr_fieldvalue, 'home_value');
}
function saveFeedback(reqid, feedbackid) {
	console.log(feedbackid);
	var remarksReason = document.getElementById('remarksReason').value;
	var higherText = document.getElementById('higherText').value;
	var templateid = document.getElementById('templateid').value;
	var ins_form = document.getElementById('form_mod');
	console.log(remarksReason);
	if (remarksReason == "") {
		nic_alert("Remarks should not be blank", "V");
		return false
	}
	arr_fieldname = [ "task", "process_id", "reqid", "feedbackid", "remarks",
			"templateid", "remarkOne" ];
	arr_fieldvalue = [ "verifyFeedback", getprocessid(), reqid, feedbackid,
			remarksReason, templateid, higherText ];
	// ajax_post_form_field(ins_form,"s_feedback",handle_cancel,arr_fieldname,arr_fieldvalue);
	ajax_post_field("s_feedback", handle_cancel, arr_fieldname, arr_fieldvalue);
}
function print_pdf_totalcomments() {
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var startdate = document.getElementById('startdate').value;
	var enddate = document.getElementById('enddate').value;
	var year = document.getElementById('date').value;
	var month = document.getElementById('sel_feedbackmonth').value;
	arr_fieldname = [ "process_id", "task","year","month","startdate", "enddate", "gh_id", "sel_block" ];
	arr_fieldvalue = [ getprocessid(), "printcomments",year,month, startdate, enddate, gh_id, sel_block ];
	post_field("doc_handler", arr_fieldname, arr_fieldvalue, true);
}
function handle_cancel() {
	if (http.readyState == 4) {
		results = http.responseText;
		if (results.substr(0, 7) != "Message") {
			nic_alert("Feedback Replied Successfully", "N");
			close_nicpopup();
			$('#myModal').modal('hide');
			$('#popup').hide();
			feedbackdetails('1')
			// menu_url_call('s_dataentry?task=abc',getprocessid());
		} else {
			nic_alert(results.substr(8), "V");
			$('#popup').hide();
		}
	}
}
function handle_show_form_moderation() {
	if (http.readyState == 4) {
		results = http.responseText;
		if (results.substr(0, 7) != "Message") {
			show_nicpopup(results);
			$('#popup').hide();
		} else {
			nic_alert(results.substr(8), "V");
			$('#popup').hide();
		}
	}
}
function toggleCheckbox(e) {
	document.getElementById(e.value).style.display = e.checked ? "block"
			: "none";

}

var max_length = 100;
function abc(e) {
	var len = max_length - $(e).val().length;
	$('.GFG').text(len);

};

// ----------------------------------------------validation--------------------------------------------------------------------

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

$('input[type="checkbox"]').click(function() {
	var val = $(this).attr("value");
	$("." + val).toggle();
});
