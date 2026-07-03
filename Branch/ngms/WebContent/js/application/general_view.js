//REQUEST ID
function view_all_requestid(req_id,gh_id){
	arr_fieldname=["task","process_id","req_id","gh_id"];
	arr_fieldvalue=["generalViewReqGh",getprocessid(),req_id,gh_id];
	ajax_post_field("s_general_view",handle_view_all_requestid,arr_fieldname,arr_fieldvalue);	
}

function handle_view_all_requestid()
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
function view_all_requestid_confirmed(req_id,gh_id){
	arr_fieldname=["task","process_id","req_id","gh_id"];
	arr_fieldvalue=["generalViewReqGhConfirmed",getprocessid(),req_id,gh_id];
	ajax_post_field("s_general_view",handle_view_all_requestid,arr_fieldname,arr_fieldvalue);	
}

//ADVANCE RECEIPT ID
function viewAdvanceReceipt(adv_id,gh_id)
{
		var sel_block  = document.getElementById('sel_block').value;
		arr_fieldname =["task","process_id","gh_id","adv_id","sel_block"];
		arr_fieldvalue =["viewAdvReceipt",getprocessid(),gh_id,adv_id,sel_block];
		ajax_post_field_div("s_general_view",handle_viewAdvanceReceipt,arr_fieldname,arr_fieldvalue,'viewdata');	
}
function handle_viewAdvanceReceipt()
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

//RECEIPT ID

function viewReceipt(rec_id,gh_id)
{
		var sel_block  = document.getElementById('sel_block').value;
	    arr_fieldname=["task","process_id","gh_id","rec_id","sel_block"];
		arr_fieldvalue=["viewReceipt",getprocessid(),gh_id,rec_id,sel_block];
		ajax_post_field("s_general_view",handle_viewReceipt,arr_fieldname,arr_fieldvalue);
}
function handle_viewReceipt(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

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

