$(document).ready(function(){
	var isEdit = false;

	/****************************************************
	 * dynamic table creation in location module
	 *****************************************************/
	var table = $('#tblLocationDetails').DataTable({
		
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "columnDefs":[
		   {"className": "dt-center", "targets": "_all"},  
		   {"targets":0,"data":"loc_name"},
           {"targets":1,"data":"address"},
           {"targets":2,"data":"po_box"},
           {"targets":3,"data":"city_id"},
           {"targets":4,"data":"postal_code"},
           {"targets":5,"data":"state"},
           {"targets":6,"data":"country_id"},
           {"targets":7,"data": null, 
		    	"defaultContent": '<button type="button" id="Edit" action="Edit" class="btn btn-default btn-sm btn-edit"><span class="glyphicon glyphicon-edit"></span></button> <button type="button" action="Delete" id="Delete" class="btn btn-default btn-sm btn-delete"><span class="glyphicon glyphicon-trash"></span></button>'}                  
		  
        ]
	});
	$('#formLocationDetails').bootstrapValidator({
        container: '#messages',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	loc_name: {
                validators: {
                    notEmpty: {
                        message: 'The Location name is required and cannot be empty'
                    },
			        regexp: {
			            regexp: /^[a-z\s]+$/i,
			            message: 'The Location name can consist of alphabetical characters and spaces only'
			        }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                    stringLength: {
                        max: 100,
                        message: 'The title must be less than 100 characters long'
                    }
                }
            },
            country_id: {
                validators: {
                    notEmpty: {
                        message: 'The country name is required and cannot be empty'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'The state is required and cannot be empty'
                    },
                    regexp: {
			            regexp: /^[a-z\s]+$/i,
			            message: 'The state name can consist of alphabetical characters and spaces only'
			        }
                }
            },
            city_id: {
                validators: {
                    notEmpty: {
                        message: 'The city is required and cannot be empty'
                    }
                }
            },
            po_box: {
                validators: {
                    notEmpty: {
                        message: 'The post box name is required and cannot be empty'
                    },
                    regexp: {
			            regexp: /^[a-z\s]+$/i,
			            message: 'The post box name can consist of alphabetical characters and spaces only'
			        }
                }
            },
            postal_code: {
                validators: {
                    notEmpty: {
                        message: 'The postal code is required and cannot be empty'
                    },
                    regexp: {
			            regexp: /^[0-9]+$/i,
			            message: 'The postal code can consist of numbers only'
			        },
			        stringLength: {
                        max: 6,
                        message: 'The postal code must be less than 6 characters long'
                    }
                }
            }
        }
    });
	
	var locationCountryName = function() {		
		$.ajax({
			 url: "./setting/getCountryDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			success: function(result)
            {	var option = '<option value="">Select Any Country</option>'
//				 $("#city_country_name").append($("<option     />").val('NA').text('Select any one'));
				result.data.forEach(function(data){
                	console.log(data.country_id);
                	option += '<option value="'+data.country_id+'">'+data.country_name+'</option>'
//                    $("#city_country_name").append($("<option     />").val(data.country_id).text(data.country_name));
                });
            $("#country").html("");
            $("#country").append(option);
            },
		    failure: function () {
		        alert("Failed!");
		    }
			 
		});
	}
	locationCountryName();
	
	$("#country").on('change', function() {
		 var country=this.value;
		 locationCityName(country);
		})
	
	
	var locationCityName = function(country) {		
		$.ajax({
			 url: "./setting/getlocationCityDetails/"+country+".do",
			 type: "get",
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			success: function(result)
            {	var option = '<option value="">Select Any One</option>'
//				 $("#city_country_name").append($("<option     />").val('NA').text('Select any one'));
				result.data.forEach(function(data){
                	console.log('hiiiii');
                	console.log(data.city_id);
                	option += '<option value="'+data.city_id+'">'+data.city_name+'</option>'
//                    $("#city_country_name").append($("<option     />").val(data.country_id).text(data.country_name));
                });
            $("#city").html("");
            $("#city").append(option);
            },
		    failure: function () {
		        alert("Failed!");
		    }
			 
		});
	}
	
	
	
	
	
	
	/**********************************************************
	 * function to get all Location details present in the table
	 * table used:admin.Location 
	 * AccessControllerController
	 **********************************************************/
	
	var getDataToLocationDetailsTable = function() {		
		$.ajax({
			 url: "./setting/getLocationDetails.do",
			 type: "POST",			
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 //console.log(result);
				 table.clear();
				 table.rows.add(result.data).draw();
			 },
			 error:function(jqXHR, status, error){
			 	alert(error);
			 }
		});
	}
	
	getDataToLocationDetailsTable();
	

	/*******************************************************************************************
	 * This method url is present in AccessControllerController.java class
	 * Table Used admin.Location
	 * taking the input value FormData
	 * modified on: 26-Oct-2017 By: Prajesh
	 ********************************************************************************************/

	 function objectifyForm(formArray) {//serialize data function

		   var returnArray = {};
		   for (var i = 0; i < formArray.length; i++){
		     returnArray[formArray[i]['name']] = formArray[i]['value'];
		   }
		   return returnArray;
		 }
	 
		var saveOrUpdateLocation = function saveOrUpdateLocation(type) {
		//alert(type);
		var postData = $("#formLocationDetails").serializeArray();
		console.log(postData);
		var formURL = "./setting/saveLocationDetails.do";
		if(type=="ADD"){
			//alert("hiii");
			$.ajax({
				 url: formURL,
				 type: "POST",
//				 beforeSend: function(xhr) {
//		                xhr.setRequestHeader(header, token);
//	      		},
				 data: JSON.stringify(objectifyForm(postData)),
	             contentType:"application/json",
	             dataType:"JSON",
	             beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
		                if (data.success) {
		                	resetForm_CreateLocation();
		                	alert(data.msg);
		                	getDataToLocationDetailsTable();
			              }
		            },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 	resetForm_CreateLocation();
				 }
			});
		}
		else if(type=="UPDATE"){
			console.log("Inside update...............");
			$.ajax({
				 url: "./setting/updateLocationDetails.do",
				 type: "POST",
//				 beforeSend: function(xhr) {
//		                xhr.setRequestHeader(header, token);
//	      		},
				 data: JSON.stringify(objectifyForm(postData)),
		         contentType:"application/json",
		         dataType:"JSON",
		         beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
					 if (data.success) {
						 	resetForm_CreateLocation();
		                	alert(data.msg);
		                	getDataToLocationDetailsTable();
		                	
			            }else{
			            	  resetForm_CreateLocation();
			            	  alert(data.msg);
			              }
		            },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 }
			});
		}
		else{
			alert("error");
		}

	}

	
	/********************************************************
	 * function to push form submit on button submit event
	 *******************************************************/
        
	$("#location_form_button").click(function() {
		var loc_name=$("#loc_name").val();
		var address=$("#address").val();
		var country=$("#country").val();
		var state=$("#state").val();
		var city=$("#city").val();
		var po_box=$("#po_box").val();
		var postal_code=$("#postal_code").val();
	    //Store the Confirmation Message Object ...
	    var message = document.getElementById('confirmMessage');
	    //Set the colors we will be using ...
	    var goodColor = "#66cc66";
	    var badColor = "#ff6666";
	    
		var type = "";	
  		if(isEdit){
  			type = 'UPDATE'
  			console.log("inside update.");
  			type = 'UPDATE';
  			if($("#loc_name").val()==''){
				alert("Enter Your location name");
				$("#loc_name").focus();
			}
			else if($("#address").val()==''){
				alert("Enter Your address");
				$("#address").focus();
			}
			else if($("#country").val()==''){
				alert("Enter Your country Name");
				$("#country").focus();
			}
			else if($("#state").val()==''){
				alert("Enter a Valid state");
				$("#state").focus();
			}
			else if($("#city").val()==''){
				alert("Enter Your Valid city Name");
				$("#city").focus();
			}
			else if($("#po_box").val()==''){
				alert("Enter Your Valid po box Name");
				$("#po_box").focus();
			}
			else if($("#postal_code").val()==''){
				alert("Enter Your Valid postal_code");
				$("#postal_code").focus();
			}
			else{
				saveOrUpdateLocation(type);
			}
  		}else{
  			console.log("inside add.")
  			type = 'ADD'
  				if($("#loc_name").val()==''){
  					alert("Enter Your location name");
  					$("#loc_name").focus();
  				}
  				else if($("#address").val()==''){
  					alert("Enter Your address");
  					$("#address").focus();
  				}
  				else if($("#country").val()==''){
  					alert("Enter Your country Name");
  					$("#country").focus();
  				}
  				else if($("#state").val()==''){
  					alert("Enter a Valid state");
  					$("#state").focus();
  				}
  				else if($("#city").val()==''){
  					alert("Enter Your Valid city Name");
  					$("#city").focus();
  				}
  				else if($("#po_box").val()==''){
  					alert("Enter Your Valid po box Name");
  					$("#po_box").focus();
  				}
  				else if($("#postal_code").val()==''){
  					alert("Enter Your Valid postal_code");
  					$("#postal_code").focus();
  				}
  				else{
  					 
  					saveOrUpdateLocation(type);
  					//$("#formLocationDetails").submit(type);	
  				}
  		}
			
			
    });
	
	/*********************************************
	 * Edit button click function
	 *********************************************/

	 $('#tblLocationDetails tbody').on( 'click', 'button[action=Edit]', function () {		
	 alert(" edit button click..........");
	 $('#location_form_button').val('update');
	 var data = table.row( $(this).parents('tr') ).data();
	 console.log(data);
	    $('#loc_name').val(data.loc_name);
		$('#address').val(data.address);
		$('#country').val(data.country);
		$('#state').val(data.state);
		$('#city').val(data.city);
		$('#po_box').val(data.po_box);
		$('#postal_code').val(data.postal_code);
		isEdit = true;
		    
		  });
	
	/********************************************************
	 * function to delete button click event
	 *******************************************************/
	
	$('#tblLocationDetails tbody').on('click', '.btn-delete', function () {
        var data = table.row( $(this).parents('tr') ).data();
        var locationIdData =
        {
        		loc_id:data.loc_id
        };
        console.log(locationIdData);
        console.log(data);
        $.ajax({
			 url: "./setting/deleteLocation.do",
			 type: "POST",		
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			 },
			 contentType : 'application/json',
	   		 dataType: "json",
	   		 data: JSON.stringify(locationIdData),
			 success:function(result) {
				 console.log(result);
				 if(result.success){
					 alert("Data deleted Sucessfully");
				 	getDataToLocationDetailsTable();
				 }else{
				 	alert(result.msg);	
				 }
			 },
			 error:function(jqXHR, status, error){
			 	alert(error);
			 }
		});
    });


});


/********************************************************
 * function to change password to SHA512
 *******************************************************//*
function changePwdLocation()
{
	var password = $("input[name=password]").val();
	var cnf_password = $("input[name=confirm_Password]").val();
	
	var encSaltSHAPass = sha512Hash(password);
	var encSaltSHACnfPass = sha512Hash(cnf_password);
	if(password == '' || cnf_password == ''){
		$("input[name=password]").val("");//changed
		$("input[name=confirm_Password]").val("");//changed
	}else{
		$("input[name=password]").val(encSaltSHAPass);//changed
		$("input[name=confirm_Password]").val(encSaltSHACnfPass);//changed
	}
	console.log(encSaltSHAPass);
	console.log(encSaltSHACnfPass);
	return true;
}*/

var resetForm_CreateLocation = function(){
	$("input[name=loc_name]").val('');
	$("input[name=address]").val('');
	$("input[name=country]").val('');
	$("input[name=state]").val('');
	$("input[name=city]").val('');
	$("input[name=po_box]").val('');
	$("input[name=postal_code]").val('');
	$("#loc_name").prop('readonly', false);
	$('#location_form_button').val('Add');
}
