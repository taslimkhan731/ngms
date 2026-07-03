//item id---->unit_price
var x;

function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_foodbill", arr_fieldname, arr_fieldvalue, 'header_div');
}

function price(s)
{
	arr_fieldname =["task","process_id","item_id"];
	arr_fieldvalue =["getprice",getprocessid(),s];
	ajax_post_field("s_foodbill",handle_price,arr_fieldname,arr_fieldvalue);

}
function handle_price()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,6)== "result")	{
			//show_nicpopup(results);
			x="";
			x=results.substr(7);
			$('#unit_price').val(x);
			//
			}
	
	}
}	
	
//Other item textbox visible or not
	$(document).on('change', '#sel_item', function() {
		//$('.hide-others').addClass('hide');
	var selected_val = $('#sel_item').find(":selected").val();
	
			//$('#unit_price').val(selected_val);
			$('#no_unit').val(0);
			$('#total_food').val(0);
			if(selected_val=="7")
				{
				//$('.hide-others').attr('class','hide-others f show');
			
				
				//$('.hide-others').removeClass('hide');
				$('.hide-others').removeClass('hide');
				$('.hide-others').addClass('show');
				}
			else 
				{
			
				
				$('.hide-others').addClass('hide');
				
				//$('.hide-others').attr('class','hide-others f hide');
				
				
				
				};
		 });

	
	
//sum --------->no_unit*unit_price
	function addvalues()
	{
	$('#total_food').val($('#no_unit').val()*$('#unit_price').val());	
	}

//list display
	function aaa(){
			var hidvalue = document.getElementById('sel_parent').value;
			var item = document.getElementById('food').value;
			arr_fieldname =["task","process_id","hidvalue","item"];
			arr_fieldvalue =["foodguest",getprocessid(),hidvalue,item];
			ajax_post_field_div("s_foodbill",handle_foodguest,arr_fieldname,arr_fieldvalue,'data3');	
	
	}
	function handle_foodguest()
	{
		if(http.readyState == 4)
		{
			results = http.responseText;
			if(results.substr(0,7)!= "Message")	{
				//show_nicpopup(results);
				document.getElementById(resultDiv).innerHTML=results;
				//
				}
			else {
				nic_alert(results.substr(8),"V");
				}
		}
	}
//cancelfoodbill list
	function showcancel(seq_id,guest_id,item){
		if(confirm("Do you want to delete?")){
			var gh_id = document.getElementById('sel_id').value;
			arr_fieldname =["task","process_id","gh_id","seq_id","guest_id","item"];
			arr_fieldvalue =["cancelfoodbill",getprocessid(),gh_id,seq_id,guest_id,item];
			ajax_post_field("s_foodbill",handle_cancelfoodlist,arr_fieldname,arr_fieldvalue);
		}
	}
	function handle_cancelfoodlist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","N");
			aaa();
			document.getElementById("data3").innerHTML=results;
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
	

//first search list
	function foodbill(page_no)
	{
		var gh_id = document.getElementById('sel_id').value;
		var sel_block = document.getElementById('sel_block').value;
		if(gh_id==0){
			nic_alert("Please Select the  City","V");
		}
		else if(sel_block==0){
			nic_alert("Please Select the Guest House","V");
		}else{
				arr_fieldname =["task","process_id","gh_id","page_no"];
				arr_fieldvalue =["foodbilllist",getprocessid(),gh_id,page_no];
				ajax_post_field_div("s_foodbill",handle_foodbilllistc,arr_fieldname,arr_fieldvalue,'data1');	
		}
	}
	function handle_foodbilllistc()
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

//search guest list
	function searchguest(){
		var room_no=  document.getElementById('room_no').value;
		var name =  document.getElementById('name').value;
		var gh_id = document.getElementById('sel_id').value;
		if(VAL_search()){
			arr_fieldname=["task","process_id","gh_id","name","room_no"];
			arr_fieldvalue=["search",getprocessid(),gh_id,name,room_no];
			ajax_post_field_divH("s_foodbill",arr_fieldname,arr_fieldvalue,'data2');	
		}
	}

//add and list guest details
	function next(req_id)
	{
				var guest_id = document.getElementById('sel_parent').value;
				var item = document.getElementById('sel_item').value;
				if(guest_id==0){
					nic_alert("Please Select the guest name","V");
				}else if(item == 0){
					nic_alert("Please Select the Food Items","V");
				}else{
					var ins_form= document.getElementById('form-general');
					arr_fieldname =["task","process_id","req_id","guest_id"];
					arr_fieldvalue =["nextpage",getprocessid(),req_id,guest_id];
					ajax_post_form_field_div(ins_form,"s_foodbill",handle_save_food,arr_fieldname,arr_fieldvalue,'data2');	
				}
	}
	function handle_save_food(){
		if(http.readyState == 4){
			results = http.responseText;
			if(results.substr(0,7)!= "Message"){
				//close_nicpopup();
				//$('#form-general')[0].reset();
				
				$('#toreset').find('input:text').val('');   
				$('#sel_item').prop('selectedIndex',0);
				nic_alert("Item Added.","N");
				document.getElementById("data3").innerHTML=results;
								
			}else{
				nic_alert(results.substr(8),"V");
			}
		}
	}

	//--------------------------------------------------------------------------validation-------------------------------------------------------------------------------------------

	function VAL_guestdetails(){
		var name  = document.getElementById('a').value;
		var age  = document.getElementById('b').value;
		var gender  = document.getElementById('c').value;
		var designation=document.getElementById('d').value;
		var department  = document.getElementById('e').value;
		var address  = document.getElementById('f').value;	
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) && (val_designation(designation)) && (val_department(department)) && (val_address(address)))
			return true;
	}
	function VAL_editguestdetails(){
		var name  = document.getElementById('a').value;
		var age  = document.getElementById('b').value;
		var gender  = document.getElementById('c').value;
		var designation=document.getElementById('d').value;
		var department  = document.getElementById('e').value;
		var address  = document.getElementById('f').value;	
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) && (val_designation(designation)) && (val_department(department)) && (val_address(address)))
			return true;
	}
	function VAL_search(){
		var room_no=  document.getElementById('room_no').value;
		var name =  document.getElementById('name').value;
		if((room_no=="") && (name==""))
				{
				nic_alert("Please enter the any one data(room no,guest name)","V");
				return false;
				}
			else{
				if(!room_no==""){ 
					val_search(room_no);
					
				}
				else if(!name==""){ 
					val_search(name);
					
				}
				
				
			}
				return true;
			}