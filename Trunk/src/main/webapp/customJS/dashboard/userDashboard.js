$(document).ready(function(){
	$("#new_psw, #con_psw").keyup(checkPasswordMatch);
	 $('#btnOkWelcome').on('click',function (e){
		var ok =  $('#btnOkWelcome').val();
	$.ajax({
		 url: "./dashboard/btnOkWelcome.do",
		 data: {ok:ok},
		 type: "POST",
		 beforeSend: function(xhr) {
             xhr.setRequestHeader(header, token);
		},
		 success:function(result) {
			 console.log(result);
			 
		 }
		 });
	 });
	 
	$.validator.addMethod("passwordRegex", function(value, element) {
		return this.optional(element) || /^(?=.*\d)(?=.*[A-Z])(?=.*[@_.-]).{8,15}$/i.test(value);
	}, "Password must contain atleast 1 uppercase letter,1 number and 1 special char like(@,_,.,-)");
	
	var form = $("#passwordChangeForm");
	form.validate({
	    errorPlacement: function errorPlacement(error, element) { element.before(error); },
	    rules: {
/************************* Validation Details of Login Credentials ************************/
	    	old_psw:{
	    		required: true
	    	},
	    	
	    	new_psw : {
	    		required: true,
	    		minlength: 8,
				maxlength:15,
	    		passwordRegex:true
	    	},
	    	con_psw: {
	    		required: true,
	            equalTo: "#new_psw"
	        },
	    },
	   /* messages:{
	    	old_psw:{
	    		required: "Type your old password"
	    	},
	    	new_psw:{
	    		required: "Type your new password"
	    	},
	    	con_psw:{
	    		required: "Type your confirm password"
	    	}
	    }*/
	});
	$('#single_date').hide();
	 $('#all_dates').hide();
	 $('#default_date').show();
	/* ********************************************************************
	    *   Get user profile data from ngo_registration table(backend)
	    *******************************************************************/
	$.ajax({
			 url: "./dashboard/getUserProfileData.do",
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 console.log(result.data);
				 var userid=result.userId;
				 var data = result.data[0];
				 console.log(data);
				 if(result.success == true){
					 var regDate = data.created_date ;
					 var eventDate = result.EventDate;
					 var eventDate3 = result.EventDate3;
					 var userType = result.userType;
					 $('#validity').html(regDate.substring(0,10));
					 
					 if(data.user_type=="ngo"){
						 $('#user_type').html("NGO");
					 }else if(data.user_type=="IPFT" || data.user_type=="CIPET"){
						 $('#user_type').html("Deptt-Auto");
					 }else if(data.user_type=="OTHER_AUTONOMOUS"){
						 $('#user_type').html("Other-Auto");
					 }else{
						 $('#user_type').html(data.user_type);
					 }
					 /*$('#annextureIII_event_date').html("FormIII-"+eventDate3);*/
					 $('#name_hidden').val(data.contact_name); // for hidden field
					 if(userType == "NGO"){
						 $('#update_pro').hide();
						 $('.ngoid').show();
						 $('#ngo_unique_id').html(data.ngo_unique_id);
						 $('#name').html(data.contact_name);
//						 //swal("Hi " + data.contact_name + "!");
						 $('#showNameWelcome').html(data.contact_name);
						 console.log("asd: " + data.contact_name);
						 $('#reg_no').html(data.registration_no);
						 $('#reg_date').html(data.registration_date);
						 $('#pan_no').html(data.pan_no);
						 $('#address').html(data.address);
						 $('#state').html(data.state);
						 $('#district').html(data.district);
						 $('#city').html(data.city);
					 }else{
						 //alert("inside if");
						 $('#update_pro').show();
						 $('.ngoid').hide();
						 $('#user').html(userid);
						 $('#name_org').html(data.user_type);
						 $('#Address').val(data.org_address);
						 $('#web').val(data.website);
						 $('#pin').val(data.pincode);
						 
						 $('#chairman').val(data.contact_name);
						 $('#City').val(data.city);
						 $('#District').val(data.district);
						 
						 $('#reg').html(data.registration_no);
						 $('#pan').val(data.pan_no);
						 $('#State').val(data.state);
						 $('#org_email').val(data.email);
						 $('#org_mob').val(data.mobile);
						 $('#addr').val(data.address);
						 $('#name').html(data.contact_name);
						 $('#showNameWelcome').html(data.contact_name);
						 console.log("asdasda: " + data.contact_name);
						 //swal("Hi " + data.contact_name + "!");
						 $('#reg_no').html(data.registration_no);
						 //alert(data.registration_date);
						 $('#reg_date').html(data.registration_date);
						 $('#pan_no').html(data.pan_no);
						 $('#address').html(data.address);
						 $('#state').html(data.state);
						 $('#district').html(data.district);
						 $('#city').html(data.city);
						// alert("inside else");
						
					 }
				 }
				 
			 }
		});
	/* ********************************************************************
	    *   Get event_date from event_reg_history table(backend)
	    ******************************************************************/
	$.ajax({
		 url: "./dashboard/getEventDate.do",
		 beforeSend: function(xhr) {
             xhr.setRequestHeader(header, token);
		},
		 success:function(result) {
		 	 var EventDate = result.EventDate[0];
			 var EventDate3 = result.EventDate3[0];
			 console.log(EventDate);
			 console.log(EventDate3);
			 console.log(result.EventDate.length);
			 console.log(result.EventDate3.length);
			 if(result.success == true){
				 var event_start_date = null;
				 var event_end_date = null;
				 var event_start_date3 = null;
				 var event_end_date3 = null;
				 if(result.EventDate.length == 0 && result.EventDate3.length != 0){
					 //alert("212");
					 event_start_date3 = EventDate3.event_start_date3;
					 event_end_date3 = EventDate3.event_end_date3;
					 $('#single_date').show();
					 $('#all_dates').hide();
					 $('#default_date').hide();
					 $('#event_start_date').html("Start-"+event_start_date3);
					 $('#event_end_date').html("End-"+event_end_date3);
				 }else if(result.EventDate3.length == 0 && result.EventDate.length != 0){
					// alert("rrrrr");
					 event_start_date = EventDate.event_start_date;
					 console.log("event_start_date--"+event_start_date);
					 event_end_date = EventDate.event_end_date;
					 $('#single_date').show();
					 
					 $('#event_start_date').html("Start-"+event_start_date);
					 $('#event_end_date').html("End-"+event_end_date);
					 $('#all_dates').hide();
					 $('#default_date').hide();
				 }else if(result.EventDate3.length != 0 && result.EventDate.length != 0){
					 event_start_date = EventDate.event_start_date;
					 console.log("event_start_date--"+event_start_date);
					 event_end_date = EventDate.event_end_date;
					 event_start_date3 = EventDate3.event_start_date3;
					 event_end_date3 = EventDate3.event_end_date3;
					 $('#single_date').hide();
					 $('#all_dates').show();
					 $('#default_date').hide();
					 $('#annextureIII_event_start_date').html("StartDt-"+event_start_date3);
					 $('#annextureIII_event_end_date').html("EndDt-"+event_end_date3);
					 $('#annextureI_event_start_date').html("StartDt-"+event_start_date);
					 $('#annextureI_event_end_date').html("EndDt-"+event_end_date);
				 }
			 }
		 }
	});
	
	//On Process
	/* ********************************************************************
	    *   Get On Process Table Data from event_reg_history table(backend)
	    *******************************************************************/
	   	function getOnProcessTbl(table){
	   			//alert('hii')
	   			$.ajax({
	   				 url: "./dashboard/getOnProcessTableData.do",
	   				 dataType:'json',
	   				 beforeSend: function(xhr) {
			               xhr.setRequestHeader(header, token);
		  			},
	   				 success:function(result) {
	   					 console.log(result.data);
	   					 if(result.success == true){
	   						 table.clear().draw();
	   						 table.rows.add(result.data).draw();
	   						 dateArrOngoing = result.details;
	   						 console.log(dateArrOngoing);
	   					 }
	   					 
	   				 }
	   			});
	   		}
	   	var table = $("#tblOnProcessDetails").DataTable({   	
			 "paging":  true,
			 "lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
			 "processing": false,
		     "ordering": false,
		     "info": false,
		     "bFilter": false,
//		     "scrollX": true,
//		     "scrollCollapse": true,
		     "columnDefs":[
		                   {
	       	                "className":      'details-control',
	       	                "orderable":      false,
	       	                "data":           null,
	       	                "defaultContent": '',
	       	                "targets":0,
		       	            },  
							{"targets":1,"data":"form_application_id"},//event_reg_id
							{"targets":2,"data":"first_name"},
							{"targets":3,"data":null,
								"render": function (data) {
									if(data.form_id == "annexureIII") {
										return "Study/Survey/OnlineSurvey";
									}else if(data.form_id == "annexureI") {
										return "Seminar/Workshop/Conference/National Awards";
									}
								}
							},	
							{"targets":4,"data":"created_on"},
//							{"targets":5,"data":"role_name"},
							{"targets":5,"data":null,
								"render": function (data) {
									if(data.status == 0) {
										return "New";
									}if(data.status == 1) {
										return "Approved";
									}if(data.status == 2) {
										return "Rejected";
									}if(data.status == 3) {
										return "Pending";
									}
								}
							},
							{"targets":6,"data":"updated_date"},
							{"targets":7,"data":"remarks"},
							{"targets":8,"data":"financial_asistancefrom_dpcp"},
							{"targets":9,"data":"released_amount"}
				          ],
				   		
		});
	   	getOnProcessTbl(table);
	   	
	   	var dateArrOngoing = null,rProIdOngoing = null;
	   $('#tblOnProcessDetails tbody').on('click', 'td.details-control', function () {
		   var data = table.row( $(this).parents('tr') ).data();
	    	 rProIdOngoing = data.event_reg_id;
		 	if(dateArrOngoing != null){
		 		var tr = $(this).closest('tr');
	 	        var row = table.row( tr );
	 	 
	 	        if ( row.child.isShown() ) {
	 	            // This row is already open - close it
	 	            row.child.hide();
	 	            tr.removeClass('shown');
	 	        }
	 	        else {
	 	            // Open this row
	 	            row.child( format2(row.data()) ).show();
	 	            tr.addClass('shown');
	 	        } 
		 	}
	   });
	   
	   function format2 ( d ) {
		   
		  /* return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	        	'<tr>'+
		            '<td>Full name:</td>'+
		            '<td>''</td>'+
		        '</tr>'+
		        '<tr>'+
		            '<td>Extension number:</td>'+
		            '<td>'+d.extn+'</td>'+
		        '</tr>'+
		        '<tr>'+
		            '<td>Extra info:</td>'+
		            '<td>And any further details here (images etc)...</td>'+
		        '</tr>'+
		    '</table>';*/
		   
		   
		     // `d` is the original data object for the row
			 //alert(dateArr);
			 var valAray=[],val;
			 var count = 1;
			 var chek = "First Released Amount";
			 for(var i=0; i<dateArrOngoing.length;i++){
				 var pid=dateArrOngoing[i].event_reg_id;
					if(pid == rProIdOngoing){
						if(dateArrOngoing[i].form_id == "annexureI"){
							
						}else{
							if(count == 2){
								chek = "Second Released Amount";
							}
							if(count == 3){
								chek = "Third Released Amount";
							}
							 val =  '<div class="row"><div class="col-md-2">'+chek+'(Rs.):</div><div class="col-md-1">'+dateArrOngoing[i].released_amount+'</div></div>';
							 valAray.push(val);
							 count++;
						}
						/*if(chek == 1) {
							 chek=chek+1;
						}else {*/
							
				//		}
					}
					else{
						count = 1;
					}
			 }
		     return valAray;
		 }
			   	
	   	/********************
		 * Change Password  
		 * @author: lusi
		 * @date: 27 July 2017
		 * ***************************/		
		$('#btnchPsw').on('click',function () {
			//alert("btnchang password");
		       $('#changePswModal').modal('show');
		  });
		 
		
		 $('#btn_savepsw').on('click',function (e){
			  var newPsw = $('#new_psw').val();
			  var conformPsw = $('#con_psw').val();
			  var oldPsw = $('#old_psw').val(); 
			  var name_hidden = $('#name_hidden').val(); 
			  if(oldPsw==""){
		         swal('Password can not be left blank');
		         $('#old_psw').focus();
			  }else if((newPsw) == ""){
		         //
				 swal('New Password can not be left blank');
		         $('#new_psw').focus();
			  } else if((conformPsw) == ""){
		         swal('Confirm password can not be left blank');
		         $('#con_psw').focus();
			  }else if($.trim(oldPsw) == "" && $.trim(newPsw) == '' && $.trim(conformPsw) == ''){
				  swal('password can not be left blank');
				  $('#old_psw').focus();
			  }
			  else if(newPsw != conformPsw){
				  swal("Password must be same !");
				  $('#con_psw').focus();
			  }else if(newPsw.match(name_hidden)){
				  console.log(name_hidden + " :: " + newPsw);
				  
				  swal('Password can not be same as name');
				  $('#new_psw').focus();
			  }
			  else{
				  changePwdAdmin();
				  var newPsw = $('#new_psw').val();
				  var conformPsw = $('#con_psw').val();
				  var oldPsw = $('#old_psw').val(); 
				  $('#loading_changepsw').show();
				  
				  var Data = {
					  new_psw: newPsw,
					  con_psw: conformPsw, 
					  old_psw: oldPsw 
				  };
				  //alert("success");
				  $.ajax({
						 url: "./dashboard/saveChangePassword.do",
						 type: "POST",
						 beforeSend: function(xhr) {
				               xhr.setRequestHeader(header, token);
			   			},
			   			contentType : 'application/json',
			   			dataType: "json",
			   			data: JSON.stringify(Data),
						success:function(result) {
							 console.log(result);
							 $('#loading_changepsw').hide();
							 if(result.success){
								 $('#changePswModal').modal('hide');
								 swal(result.msg,"","success");
								 passwordResetFields();
							 }else{
								 $('#changePswModal').modal('hide');
							 	 swal(result.msg,"","error");
								 //alert("Status not Closed due to some error.");
							 	passwordResetFields();
							 }
						 },
						 error:function(jqXHR, status, error){
							 $('#loading_changepsw').hide();
						 	swal("error","","error");
						 	passwordResetFields();
						 }
				});
			  }
		  });
		 
		/* $.ajax({
				 url: "./dashboard/getOnProcessTableData.do",
				 dataType:'json',
				 success:function(result) {
					 console.log(result.data);
					 if(result.success == true){
						 table.clear().draw();
						 table.rows.add(result.data).draw();
						 
						 if(result.data)
					 }
					 
				 }
			});*/
		 /************************************************************************
			 * Update Profile Button Click
			 ************************************************************************/
			$('#btn_update').click(function(){
				var email =  $('#org_email').val();
				var mob =  $('#org_mob').val();
				var org_addr =  $('#Address').val();
				var name =  $('#chairman').val();
				var city =  $('#City').val();
				var district =  $('#District').val();
				var state =  $('#State').val();
				var pin =  $('#pin').val();
				var pan =  $('#pan').val();
				var web =  $('#web').val();
				var address =  $('#addr').val();
				
				var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				if(org_addr == ""){
					alert("Organizational Address Can't be blank")
					org_addr.focus();
					return false;
				}
				if(mob == ""){
					alert("Organizational Mobile Number Can't be blank")
					mob.focus();
					return false;
				}
				if (/^\d{10}$/.test(mob)) {
				    // value is ok, use it
				} else {
				    alert("Invalid number; must be ten digits")
				    number.focus()
				    return false
				}
				if(email == ""){
					alert("Organizational Email Can't be blank")
					email.focus();
					return false;
				}
				if (reg.test(email) == false) 
		        {
		            alert('Invalid Email Address');
		            email.focus();
		            return false;
		        }	
				if(name == ""){
					  alert("Chairman Name Cant't be blank");
					  name.focus();
					  return false;
				}
				if(city == ""){
					  alert("City Cant't be blank");
					  city.focus();
					  return false;
				}
				if(district == ""){
					  alert("District Cant't be blank");
					  district.focus();
					  return false;
				}
				if(state == ""){
					  alert("State Cant't be blank");
					  state.focus();
					  return false;
				}
				if(pin == ""){
					  alert("Pin Code Cant't be blank");
					  pin.focus();
					  return false;
				}
				if(pin.length != 6){
					  alert("Invalid Pin Code");
					  pin.focus();
					  return false;
				}
				if(pan == ""){
					  alert("Pan Number Cant't be blank");
					  pan.focus();
					  return false;
				}
				if(web == ""){
					  alert("Website URL Cant't be blank");
					  web.focus();
					  return false;
				}
				if(address == ""){
					  alert("Address Cant't be blank");
					  address.focus();
					  return false;
				}
				else{
					var email =  $('#org_email').val();
					var mob =  $('#org_mob').val();
					var org_addr =  $('#Address').val();
					var data = {
							"email" : email,
							 "mob"  : mob,
							 "org_addr" : org_addr,
							 "chairman" : name,
							 "city" : city,
							 "district" : district,
							 "state" : state,
							 "pin" : pin,
							 "pan" : pan,
							 "web" : web,
							 "address" : address
					}
					$.ajax({
		  				 url: "./dashboard/profileUpdate.do",
		  				 type: "POST",
		  				 dataType:'json',
		  				 contentType: "application/json",
		  	             data: JSON.stringify(data),
						 beforeSend: function(xhr) {
				               xhr.setRequestHeader(header, token);
			   			},
		  				 success:function(result) {
		  					 console.log(result.data);
		  					 if(result.success == true){
		  						$('#UpdateProfile').modal('hide');
		  						swal(result.msg,"","success");
		  					 }
		  					 
		  				 }
		  			});
				}
				
				
			});
		 
});

var passwordResetFields  = function(){
	$('#new_psw').val("");
	 $('#con_psw').val("");
	 $('#old_psw').val("");
}

function checkPasswordMatch() {
    var password = $("#new_psw").val();
    var confirmPassword = $("#con_psw").val();
    console.log((password != confirmPassword))
    console.log(!this.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/))
    if ((password == confirmPassword) && (this.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/))){
    	$("#passwordMatch").html("Passwords matched.");
    	$("#btn_savepsw").removeAttr("disabled");
    }else{
    	$("#passwordMatch").html("Passwords do not match!");
    	$("#btn_savepsw").attr("disabled","disabled");
    }
}

function changePwdAdmin()
{
	var old_password = $("input[name=old_psw]").val();
	var password = $("input[name=new_psw]").val();
	var cnf_password = $("input[name=con_psw]").val();
	
	var encSaltSHAOldPass = sha512Hash(old_password);
	var encSaltSHAPass = sha512Hash(password);
	var encSaltSHACnfPass = sha512Hash(cnf_password);
	
	$("input[name=old_psw]").val(encSaltSHAOldPass);//changed
	$("input[name=new_psw]").val(encSaltSHAPass);//changed
	$("input[name=con_psw]").val(encSaltSHACnfPass);//changed
	
	console.log(encSaltSHAPass);
	console.log(encSaltSHACnfPass);
	return true;
}
