$(document).ready(function()
{
	$('.multiradio input[type=radio]').change(function() {
		$('.multiradio input[type=radio]:checked').not(this).prop('checked', false);
	});
});


function save_changeprofile(){
	ins_form= document.getElementById('form-changepubprofile');
	
	if(validate_changepublicprofile()){
	$('#form-changepubprofile').submit();
//		arr_fieldname =["task","process_id"];
//		arr_fieldvalue =["savechangeprofile", getprocessid()];
//		$('#popup').show();
//		ajax_post_form_field(ins_form, "s_changepublicprofile",handle_save_changeprofile,arr_fieldname,arr_fieldvalue);
	}	
}

function handle_save_changeprofile()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			nic_alert("Verification Code is sent","N");
			$('#popup').hide();
			document.getElementById('form-change').innerHTML= results;
			$('.hide_div input').attr('disabled','');
		}
		else{
			if(results.substr(0,8)!= "Message1"){
				/*alert(results.substr(9));*/
				nic_alert(results.substr(9),"N");
 				arr_fieldname=['task','process_id'];
				arr_fieldvalue=["abc",getprocessid()];
				$('#popup').hide();
				post_field("s_publicdataentry",arr_fieldname,arr_fieldvalue,false);
			}else{
				 
				nic_alert(results.substr(9),"V");
				$('#popup').hide();
			}
			
		}
		 
	
	}
} 

function cancelchange(){
	arr_fieldname=['task','process_id'];
	arr_fieldvalue=["abc",getprocessid()];
	post_field("s_publicdataentry",arr_fieldname,arr_fieldvalue,false);
	
}

function getAnotherCodeChange(){
	ins_form= document.getElementById('form-changepubprofile');
	//if(validate_personaldetails()){
		arr_fieldname=['task','process_id'];
		arr_fieldvalue=["changeGetcode",getprocessid()];
		ajax_post_form_field(ins_form,"s_changepublicprofile",handle_getAnotherCodeChange,arr_fieldname,arr_fieldvalue);
		
	//}	
}
function handle_getAnotherCodeChange()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			nic_alert("Verification Code is sent","N");
			document.getElementById('form-change').innerHTML= results;
			$('.hide_div input').attr('disabled','');
		}
		else
		{
			
			if(results.substr(0,8)!= "Message1"){
				/*alert(results.substr(9));*/
				 
				arr_fieldname=['task','process_id'];
				arr_fieldvalue=["abc",getprocessid()];
				post_field("s_publicdataentry",arr_fieldname,arr_fieldvalue,false);
			}else{
				nic_alert(results.substr(9),"V");
			}
			
		}
	}
}

function isDetailsValidChange(){
	ins_form= document.getElementById('form-changepubprofile');
	//if(validate_savedetails()){
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["savedetails",getprocessid()];
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_changepublicprofile",handle_isDetailsValidChange,arr_fieldname,arr_fieldvalue);
	//}
		
	
}

function handle_isDetailsValidChange()
{
	if(http.readyState == 4)
	{	
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{	
			nic_alert("Successfully Changed.","N");
			var n=results.split("-");
			/*arr_fieldname=['task','process_id'];
			arr_fieldvalue=[n[1],getprocessid()];
			post_field(n[0],arr_fieldname,arr_fieldvalue,false);
			*/
			arr_fieldname=['task','process_id'];
			arr_fieldvalue=["abc",getprocessid()];
			post_field("s_publicdataentry",arr_fieldname,arr_fieldvalue,false);
			$('#popup').hide();
		} 
		else
		{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}


function validate_changepublicprofile() {
 
	//ins_form			= document.getElementById('form_general');
	
	var name = document.getElementById('txt_name').value;//
	var yy = document.getElementById('txt_yy').value;//
	var mm = document.getElementById('txt_mm').value;//
	var dd = document.getElementById('txt_dd').value;//
	var state = document.getElementById('txt_state').value;//
//	var eaddress = document.getElementById('txt_eaddress').value;//
	var mobile = document.getElementById('txt_mobile').value;//
//	var userid = document.getElementById('username').value;//

	
	//-----------------------------prefered userid
//	if(userid==""){
//		nic_alert("Please Enter Your Preffred User ID ","V");
//		return false;
//	}
//	else if (!VAL_E(userid)){
//		nic_alert("Please Enter Valid User ID(mail ID).","V");
//		return false;
//	}
//	
//	else if (!isLEN(userid,10,50)){
//		nic_alert("Please Enter  Valid User ID.","V");
//		return false;
//	}
//----------------------------------------------------name
		if(name==""){
			nic_alert("Please Enter Your Name ","V");
			return false;
		}
		else if (!VAL_NAME(name)){
			nic_alert("Please Enter Valid Name. Allowed Special Charector is Space and dot","V");
			return false;
		}
		else if (!isLEN(name,3,50)){
			nic_alert("Please Enter  Valid Name. Minimum Length is 3 and Maximum Length is 50","V");
			return false;
		}
		
//---------------------------------------------------date
 
		if(dd!=""){
			
		 if (!VAL_N(dd)){
			nic_alert("Please Enter Valid dd","V");
			return false;
		}
		else if (!isLEN(dd,2,2)){
			nic_alert("Please Enter  Valid dd. Length is 2","V");
			return false;
		}
		 
		}else{
			nic_alert("Please Enter Your dd ","V");
			return false;
		
		}
 
		if(mm!=""){
			
		 if (!VAL_N(mm)){
			nic_alert("Please Enter Valid mm","V");
			return false;
		}
		else if (!isLEN(mm,2,2)){
			nic_alert("Please Enter  Valid mm. Length is 2","V");
			return false;
		}
		}
		else{
			nic_alert("Please Enter Your mm ","V");
			return false;
		
		}
		if(yy!=""){
			
		if (!VAL_N(yy)){
			nic_alert("Please Enter Valid yy","V");
			return false;
		}
		else if (!isLEN(yy,4,4)){
			nic_alert("Please Enter  Valid Address. Length is 4","V");
			return false;
		}
		}else{

			nic_alert("Please Enter Your yy ","V");
			return false;
		}



//----------------------------------------mobile
		if(mobile==""){
				nic_alert("Please Enter Mobile no ","V");
				return false;
			}
			
		else if (!VAL_N(mobile)){
			nic_alert("Please Enter Valid Mobile No.Enter numeric value only","V");
			return false;
		}
		else if (!isLEN(mobile,10,10)){
			nic_alert("Please Enter Valid Mobile Number.Should contain only 10 numbers.","V");
			return false;
		}
		
//---------------------------------------------------email
//		if(eaddress!=""){
//			if (!VAL_E(eaddress)){
//				nic_alert("Please Enter Valid Email. It should be of mail id format ","V");
//				return false;
//			}
//		}	else{
//			
//			nic_alert("Please enter email id","V");
//			return false;
//		}

		/*if ((chkemail==true) && (email=="")){
			nic_alert ('Enter Email Address to send Message',"V");
			return false;
		}	*/	

		//-------------------------------------------------state
		if(state==""){
			nic_alert("Please Enter Your state Name ","V");
			return false;
		}
		else if (!VAL_AS(state)){
			nic_alert("Please Enter Valid State Name.Numbers,Special characters and Space is not allowed ","V");
			return false;
		}
		else if (!isLEN(state,3,50)){
			nic_alert("Please Enter  Valid state Name. Minimum Length is 3 and Maximum Length is 50","V");
			return false;
		}
		return true;		  
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
	var validExts = new Array(".pdf",".jpg",".jpeg");
	var fileExt = uploadFile.value;
	fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
	if (validExts.indexOf(fileExt) < 0) {
		alert("Invalid file selected, valid files are of image or pdf types.");
		uploadFile.value= '';
		return false;
	} else
		return true;
}