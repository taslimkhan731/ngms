  $(document).ready(function(){
	$("#new_psw, #con_psw").keyup(checkPasswordMatch);
	/* ********************************************************************
	    *   Get cards data from app_event_registration and app3_event_registration table(backend)
	    *********************************************************************************************/
	$.ajax({
			 url: "./dashboard/getAdminCardsData.do",
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 if(result.success == true){
					 var length = result.list.size;
					 var list = result.list;
					 var activeFinYear = result.activeFinYear;
					 console.log("list for approver cards------"+result.list);
					 var length = list.length;
					 console.log("length---"+length);
					 //for admin cards
					 var listForAdminCards = result.listAdminCards;
					 console.log("list for admin cards-------"+result.listAdminCards);
					 var listForAdminCardsLength = listForAdminCards.length;
					 
					 var s0I= 0,s1I= 0,s2I= 0,s3I= 0,s0III= 0,s1III= 0,s2III= 0,s3III= 0;
					 var s0 = 0;var s1 = 0;var s2 = 0;var s3 = 0;
					 
					 if(length != 0){
						 for(i=0; i<length;i++){
							 console.log("inside for loop");
							var status = list[i].status;
							console.log("status---"+status);
							var statusValue = list[i].status_value;
							var formId = list[i].form_id;
							if(status==0 && formId=="annexureI"){
								console.log("inside 0I if");
								s0I = statusValue;
								console.log("s0I---"+s0I);
							}if(status==1 && formId=="annexureI"){
								console.log("inside 1I if");
								s1I = statusValue;
								console.log("s1I---"+s1I);
							}if(status==2 && formId=="annexureI"){
								console.log("inside 2I if");
								s2I = statusValue;
								console.log("s2I---"+s2I);
							}if(status==3 && formId=="annexureI"){
								console.log("inside 3I if");
								s3I = statusValue;
								console.log("s3I---"+s3I);
							}if(status==0 && formId=="annexureIII"){
								console.log("inside 0III if");
								s0III = statusValue;
								console.log("s0III---"+s0III);
							}if(status==1 && formId=="annexureIII"){
								console.log("inside 1III if");
								s1III = statusValue;
								console.log("s1III---"+s1III);
							}if(status==2 && formId=="annexureIII"){
								console.log("inside 2III if");
								s2III = statusValue;
								console.log("s2III---"+s2III);
							}if(status==3 && formId=="annexureIII"){
								console.log("inside 3III if");
								s3III = statusValue;
								console.log("s3III---"+s3III);
							}
							
						 }
					 }else{
						 for(i=0; i<listForAdminCardsLength;i++){
							 console.log("inside for loop");
							var status = listForAdminCards[i].status;
							console.log("status---"+status);
							var statusValue = listForAdminCards[i].status_value;
							var formId = listForAdminCards[i].form_id;
							if(status==0 && formId=="annexureI"){
								console.log("inside 0I if");
								s0I = statusValue;
								console.log("s0I---"+s0I);
							}if(status==1 && formId=="annexureI"){
								console.log("inside 1I if");
								s1I = statusValue;
								console.log("s1I---"+s1I);
							}if(status==2 && formId=="annexureI"){
								console.log("inside 2I if");
								s2I = statusValue;
								console.log("s2I---"+s2I);
							}if(status==3 && formId=="annexureI"){
								console.log("inside 3I if");
								s3I = statusValue;
								console.log("s3I---"+s3I);
							}if(status==0 && formId=="annexureIII"){
								console.log("inside 0III if");
								s0III = statusValue;
								console.log("s0III---"+s0III);
							}if(status==1 && formId=="annexureIII"){
								console.log("inside 1III if");
								s1III = statusValue;
								console.log("s1III---"+s1III);
							}if(status==2 && formId=="annexureIII"){
								console.log("inside 2III if");
								s2III = statusValue;
								console.log("s2III---"+s2III);
							}if(status==3 && formId=="annexureIII"){
								console.log("inside 3III if");
								s3III = statusValue;
								console.log("s3III---"+s3III);
							}
							
						 }
					 }
					 
					 
					 
					 	s0 = parseInt(s0I) + parseInt(s0III);
						s1 = parseInt(s1I) + parseInt(s1III);
						s2 = parseInt(s2I) + parseInt(s2III);
						s3 = parseInt(s3I) + parseInt(s3III);
						console.log("s0--"+s0+" "+"s1--"+s1+" "+"s2--"+s2+" "+"s3--"+s3+" ");
						$('#no_of_new').html(s0);
						$('#no_of_pending').html(s3);
						$('#no_of_approve').html(s1);
						$('#no_of_rejected').html(s2);
						$('#currnt_fin_yr').html(activeFinYear[0].financial_yr);
				 }
				 
			 }
		});
	
	//Application Statistics
	/* ********************************************************************
	    *   Get On Application Statistics Table Data from Database
	    *******************************************************************/
	   	function getApplicationStatisticsTbl(table){
	   			//alert('hii')
	   			$.ajax({
	   				 url: "./dashboard/getApplicationStatisticsTableData.do",
	   				 dataType:'json',
	   				 beforeSend: function(xhr) {
			               xhr.setRequestHeader(header, token);
		  			},
	   				 success:function(result) {
	   					 console.log(result);
	   					 if(result.success == true){
	   						 table.clear().draw();
	   						 table.rows.add(result.data).draw();
	   					 }
	   					 
	   				 }
	   			});
	   		}
	   	var table = $("#tblAppStatisticsDetails").DataTable({   	
			 "paging":  false,
			 "lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
			 "processing": false,
		     "ordering": false,
		     "info": false,
		     "bFilter": false,
		     "scrollX": true,
		     "scrollCollapse": true,
		     "bAutoWidth": false,
		     "columnDefs":[
							{"targets":0,"data":null,
								"render": function (data) {
									if(data.org_type === "CIPET"){
										return "Deptt-Auto(CIPET)";
									}if(data.org_type === "IPFT"){
										return "Deptt-Auto(IPFT)";
									}if(data.org_type === "OTHER_AUTONOMOUS"){
										return "Other-Auto";
									}else{
										return data.org_type;
									}
				                 } 
							},
							{"targets":1,"data":null,
								"render": function (data) {
									if(data.application_type == "annexureI"){
										return "Seminar/Workshop/Conference/National Awards";
									}else if(data.application_type == "annexureIII"){
										return "Study/Survey/OnlineSurvey";
									}
				                 } 
								},
							{"targets":2,"data":"total_applied",
								 "render": function (data) {
					                 	console.log("data(total_applied)= "+data);
					                 	var str="";
					                 		str = '<span class="label label-pill label-success">'+data+'</span>';
					                 	return str;
					                 }    
							 }
				          ],
				   		
		});

	   	   
	   	getApplicationStatisticsTbl(table);
	   	
	  //Registration Data
		/* ********************************************************************
		    *   Get On Registration Data Table Data from Database
		    *******************************************************************/
	   	var orgType = null;
	   	var totalReg = null;
	   	//var appOtherAutonomousTotal = 0;
	   	function getRegistrationTableTbl(table){
	   		$.ajax({
  				 url: "./dashboard/getRegistrationTableData.do",
  				 dataType:'json',
  				 beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
  				 success:function(result) {
  					if(result.success == true){
  						var app = result.app;
  						console.log(result.data);
  						var app3 = result.app3;
  						console.log("app3--"+app3);
  						var appNgoTotal = app[0].total_app_ngo;
  						console.log("appNgoTotal--"+appNgoTotal);
  						var appGovtTotal = app[0].total_app_govt;
  						console.log("appGovtTotal--"+appGovtTotal);
  						var appOtherAutonomousTotal = app[0].total_app_OtherAutonomous;
  						console.log("appOtherAutonomousTotal--"+appOtherAutonomousTotal);
  						var appPSUsTotal = app[0].total_app_PSUs;
  						console.log("appPSUsTotal--"+appPSUsTotal);
  						var appCIPETTotal = app[0].total_app_CIPET;
  						console.log("appCIPETTotal--"+appCIPETTotal);
  						var appIPFTTotal = app[0].total_app_IPFT;
  						console.log("appIPFTTotal--"+appIPFTTotal);
  						
  						var app3NgoTotal = app3[0].total_app_ngo3;
  						console.log("app3NgoTotal--"+app3NgoTotal);
  						var app3GovtTotal = app3[0].total_app_govt3;
  						console.log("app3GovtTotal--"+app3GovtTotal);
  						
  						var appOtherAutonomousTotal3 = app3[0].total_app_OtherAutonomous3;
  						console.log("appOtherAutonomousTotal3--"+appOtherAutonomousTotal3);
  						var appPSUsTotal3 = app3[0].total_app_PSUs3;
  						console.log("appPSUsTotal3--"+appPSUsTotal3);
  						var appCIPETTotal3 = app3[0].total_app_CIPET3;
  						console.log("appCIPETTotal3--"+appCIPETTotal3);
  						var appIPFTTotal3 = app3[0].total_app_IPFT3;
  						console.log("appIPFTTotal3--"+appIPFTTotal3);
  						
  						var govtApply = parseInt(appGovtTotal) + parseInt(app3GovtTotal);
  						console.log("govtApply--"+govtApply);
  						var ngoApply = parseInt(appNgoTotal) + parseInt(app3NgoTotal);
  						console.log("ngoApply--"+ngoApply);
  						var OtherAutonomousTotal = parseInt(appOtherAutonomousTotal) + parseInt(appOtherAutonomousTotal3);
  						var PSUsTotal = parseInt(appPSUsTotal) + parseInt(appPSUsTotal3);
  						var CIPETTotal = parseInt(appCIPETTotal) + parseInt(appCIPETTotal3);
  						var IPFTTotal = parseInt(appIPFTTotal) + parseInt(appIPFTTotal3);
  						console.log("PSUsTotal--"+PSUsTotal);
  						console.log("CIPETTotal--"+CIPETTotal);
  						console.log("IPFTTotal--"+IPFTTotal);
  						console.log("OtherAutonomousTotal--"+OtherAutonomousTotal);
  						reg_table.clear().draw();
  						reg_table.rows.add(result.data).draw();
  						$('#total_ngo_apply').html(ngoApply);
  						$('#total_gov_apply').html(govtApply);
  						$('#total_ipft_apply').html(IPFTTotal);
  						$('#total_oa_apply').html(OtherAutonomousTotal);
  						$('#total_psus_apply').html(PSUsTotal);
  						$('#total_cipet_apply').html(CIPETTotal);
  					 }
  				 }
  			});
	   	}
		   			
	   	var reg_table = $("#tblRegistrationDataDetails").DataTable({   	
		 "paging":  false,
		 "lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		 "processing": false,
	     "ordering": false,
	     "info": false,
	     "bFilter": false,
	     "scrollX": true,
	     "retrieve": true,
	     "scrollCollapse": true,//org_type
	     "bAutoWidth": false,
	     "columnDefs":[
	                   {"targets":0,"data":null,
	                	   
	                	   "render": function (data) {
								if(data.org_type === "CIPET"){
									return "Deptt-Auto(CIPET)";
								}if(data.org_type === "IPFT"){
									return "Deptt-Auto(IPFT)";
								}if(data.org_type === "OTHER_AUTONOMOUS"){
									return "Other-Auto";
								}else{
									return data.org_type;
								}
			                 } 
	                	},
						/*{"targets":0,"data":null,
							"defaultContent": "",
							"render":function(data){
								if(data.org_type === 'approver' || data.org_type === 'cipetapprover'){
									
								}
							}
						},*/
						{"targets":1,"data":"total_register"},
						{"targets":2,"data": null,
							"defaultContent": "",
				               "render": function (data) {
				                 	//console.log("data= "+data);
				                 	var str="";
				                 	if(data.org_type === 'NGO'){
				                 		str = '<span class="label label-pill label-success" id="total_ngo_apply"></span>';
				                 	}else if(data.org_type === 'GOVT'){
				                 		str = '<span class="label label-pill label-success" id="total_gov_apply"></span>';
				                 	}
				                 	else if(data.org_type === 'IPFT'){
				                 		str = '<span class="label label-pill label-success" id="total_ipft_apply"></span>';
				                 	}
				                 	else if(data.org_type === 'OTHER_AUTONOMOUS'){
				                 		str = '<span class="label label-pill label-success" id="total_oa_apply"></span>';
				                 	}
				                 	else if(data.org_type === 'PSU'){
				                 		str = '<span class="label label-pill label-success" id="total_psus_apply"></span>';
				                 	}
				                 	else if(data.org_type === 'CIPET'){
				                 		str = '<span class="label label-pill label-success" id="total_cipet_apply"></span>';
				                 	}
				                 	return str;
				                 }    
						}
			          ],
			   		
	});
	 getRegistrationTableTbl(reg_table);
	 
	 $('#btn_savepsw').on('click',function (e){
		  var newPsw = $('#new_psw').val();
		  var conformPsw = $('#con_psw').val();
		  var oldPsw = $('#old_psw').val(); 
		  console.log(newPsw + "::" + conformPsw + "::" + oldPsw);
		  if(oldPsw==""){
			  swal('Password can not be left blank');
	         $('#old_psw').focus();
		  }else if((newPsw) == ""){
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
							 swal(result.msg);
							 passwordResetFields();
						 }else{
							 $('#changePswModal').modal('hide');
							 swal(result.msg);
							 //alert("Status not Closed due to some error.");
						 	passwordResetFields();
						 }
					 },
					 error:function(jqXHR, status, error){
						 $('#loading_changepsw').hide();
						 swal(result.msg);
					 	passwordResetFields();
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