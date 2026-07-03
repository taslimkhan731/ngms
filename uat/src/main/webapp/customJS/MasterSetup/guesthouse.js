$(document).ready(function() {
					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");
					var suppDoc = null;
					$('#supportDoc').change(function(e) {
										var file = e.target.files[0];
										if (file != undefined) {
											var size = Math
													.round((file.size / (1024 * 1024)));
											if (size > 2) {
												swal('Identity document should not be greater than 2 mb');
												$('#supportDocName')
														.html('Upload proof (image/pdf)');
												$('#supportDoc').val('');
											} else {
												suppDoc = file;
												$('#supportDocName').html(
														suppDoc.name);
											}
										} else {
											$('#supportDocName').html(
													'Upload proof (jpg/pdf)');
										}
									});
					// swal("44444")
					isEdit = false;
					var table = $("#UserGHDetails")
							.DataTable(
									{
										"paging" : true,
										"lengthMenu" : [ [ 10, 20, 30, -1 ],
												[ 10, 20, 30, "All" ] ],
										"processing" : false,
										"ordering" : false,
										"info" : true,
										"bFilter" : true,
										"searching" : true,
										"scrollX" : true,
										"scrollCollapse" : true,
										"columnDefs" : [
												{
													"className" : "dt-center",
													"targets" : "_all"
												},
												{
													"targets" : 0,
													"data" : "ghId"
												},
												{
													"width" : "15%",
													"targets" : 1,
													"data" : "ghName"
												},
												{
													"targets" : 2,
													"data" : "cityName"
												},
												{
													"targets" : 3,
													"data" : "totalRooms"
												},
												{
													"targets" : 4,
													"data" : "totalFloor"
												},
												{
													"width" : "20%",
													"targets" : 5,
													"data" : "address"
												},
												{
													"targets" : 6,
													"data" : "contact_num"
												},
												{
													"targets" : 7,
													"data" : "emailId"
												},
												{
													"width" : "10%",
													"targets" : 8,
													"data" : null,
													"defaultContent" : '<button type="button" action="deleteGH" id="deleteGH" title="Delete"  class="btn btn-sm btn-dlt"><i class="fa fa-trash"></i></button>'
															+ '<button type="button" id="Edit" action="Edit" title="Edit" class="btn btn-sm btn-edit ml-1"><i class="fa fa-edit"></i></button></span></button>'
												}

										],

									});
					getGHTableData(table);
					getAllCity();

					$('#new_city_form')
							.bootstrapValidator(
									{
										btnSubmit : 'button[type="submit"]',
										framework : 'bootstrap',
										fields : {
											cityId: {
												validators : {
													notEmpty : {
														message : 'select a valid option'
													}
												}
											},
											ghName : {
												validators : {
													stringLength : {
														min : 3,
														max : 20,
														message : 'Guest house name must be more than 2 and less than 20 characters long'
													},
													notEmpty : {
														message : 'Guest house name must not be empty'
													},
													regexp : {
														regexp : /^[A-Za-z\s]*$/,
														message : 'Guest house name must contain only letters'
													}
												}
											},
											totalFloor : {
												validators : {
													notEmpty : {
														message : 'The total floor must not be empty'
													},
													regexp : {
														regexp : /^[0-9.]*$/,
														message : 'Total number of floor must contain only number'
													}
												}
											},
											totalRooms : {
												validators : {
													notEmpty : {
														message : 'The total number of room must not be empty'
													},
													regexp : {
														regexp : /^[0-9.]*$/,
														message : 'Total number of floor must contain only number'
													}
												}
											},
											address : {
												validators : {
													stringLength : {
														min : 3,
														max : 20,
														message : 'Guest house name must be more than 2 and less than 20 characters long'
													},
													notEmpty : {
														message : 'Guest house name must not be empty'
													},
													/*regexp : {
														regexp : /^[^\s,]+(?:,\s*[^\s,]+)*$/,
														message : 'Guest house name must contain only letters'
													}*/
												}
											},
											address_details : {
												validators : {
													stringLength : {
														min : 3,
														max : 20,
														message : 'Guest house name must be more than 2 and less than 20 characters long'
													},
													notEmpty : {
														message : 'Guest house name must not be empty'
													},
													/*regexp : {
														regexp : /^[^\s,]+(?:,\s*[^\s,]+)*$/,
														message : 'Guest house name must contain only letters'
													}*/
												}
											},
											contact_num : {
												validators : {
													notEmpty : {
														message : 'mobile is required and can\'t be empty'
													},
													digits : {
														message : 'Only digits are allowed in mobile number.'
													},
													regexp : {
														regexp : /^[9 8 7 6]\d{9}$/,
														message : 'Mobile number should be of 10 digits only.'
													}
												}
											},
											emailId : {
												validators : {
													notEmpty : {
														message : 'field can\'t be empty'
													}
													
												}
											}
											
										}
									}).on('success.form.bv', function(e) {
								e.preventDefault();
								submitGuestHouseDetails();
							});

					/***********************************************************
					 * Form Data to the Create Group table
					 **********************************************************/
					var submitGuestHouseDetails = function() {

						var type = "";

						if (isEdit) {
							type = "UPDATE"
						} else {
							type = "ADD"
						}

						/*
						 * var cityid = $("#city_name").val(); var ghname =
						 * $("#gh_name").val(); var totfloor =
						 * $("#total_floor").val(); var id =
						 * $("#blockId").val(); var totrooms =
						 * $("#total_room").val(); var address=
						 * $("#address").val(); var contact_num=
						 * $("#contact_num").val();
						 */
						var form = $('#new_city_form')[0];
						var podata = new FormData(form);
						/*
						 * var Data = { cityId : cityid, ghName : ghname,
						 * totalFloor : totfloor, type : type, ghId : id,
						 * totalRooms : totrooms, address : address, contact_num :
						 * contact_num };
						 */
						if (suppDoc != null) {
							podata.append("supportDoc", suppDoc, suppDoc.name);
						};
			
						$.ajax({
							url : "./mdm/saveUserGHTable/"+type+".do",
							type : "POST",
							data : podata,
							enctype : 'multipart/form-data',
							processData : false, // Important!
							contentType : false,
							beforeSend : function(xhr) {
								xhr.setRequestHeader(header, token);
							},
							success : function(result) {
								$('#loading').hide();
								if (result.success) {
									swal(result.msg, "", "success");
									$('#new_city_form').bootstrapValidator(
											'resetForm', true);
									resetFormCreateGH();
									getGHTableData(table);
								} else {
									swal(result.msg, "", "error");
									resetFormCreateGH();
									getGHTableData(table);
								}
							},
							error : function(jqXHR, status, error) {
								swal(error);
							}

						});
					}
					/***********************************************************
					 * Edit button click function
					 **********************************************************/
					$('#UserGHDetails tbody').on(
							'click',
							'button[action=Edit]',
							function() {
								// swal('hii')
								var data = table.row($(this).parents('tr'))
										.data();
								isEdit = true;
								$("#city_name").val(data.cityId);
								$("#gh_name").val(data.ghName);
								$("#total_floor").val(data.totalFloor);
								$("#total_room").val(data.totalRooms);
								$("#ghId").val(data.ghId);
								$("#address").val(data.address);
								$("#contact_num").val(data.contact_num);
								$("#emailId").val(data.emailId);
								$("#address_details").val(data.address_details);
								var suppdocname = null;
					   			if(data.support_document!=null){
					   				var fileArr = data.support_document.split(/(\\|\/)/);
					   				suppdocname = fileArr[fileArr.length -1];
					   			}
					   			if(suppdocname!=null){
					   				console.log("support doc name");
					   				$("#supportDocName").html(suppdocname);
					   			}
								$('#addGuestHouse').html('Update');
							});

					/***********************************************************
					 * Delete button click function
					 **********************************************************/

					$('#UserGHDetails tbody').on(
							'click',
							'button[action=deleteGH]',
							function() {
								var data = table.row($(this).parents('tr'))
										.data();
								var Data = {
									ghId : data.ghId
								};

								$.ajax({
									url : "./mdm/deleteCreateUserGH.do",
									type : "POST",
									contentType : 'application/json',
									dataType : "json",
									data : JSON.stringify(Data),
									beforeSend : function(xhr) {
										$('#loading').show();
										xhr.setRequestHeader(header, token);
									},
									success : function(result) {
										$('#loading').hide();
										if (result.success) {
											getGHTableData(table);
											swal(result.msg, "", "success");
										} else {
											swal(result.msg, "", "error");
										}
									},
									error : function(jqXHR, status, error) {
										swal(error);
									}
								});
							});
					
					
				})//end of the ready function

function getGHTableData(table) {
	$.ajax({
		url : "./mdm/getDataGHTable.do",
		type : "GET",
		beforeSend : function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success : function(result) {
			$('#loading').hide();
			table.clear().draw();
			table.rows.add(result.data).draw();
		}
	});
}

// get all city details
var getAllCity = function getAllCity() {
	$.ajax({
		url : "./setting/getAllDistrict.do",
		type : "GET",
		beforeSend : function(xhr) {
			$('#loading').show();
			xhr.setRequestHeader(header, token);
		},
		success : function(result) {
			$('#loading').hide();
			var cityList = result.data;
			var option = '<option value="">---SELECT----</option>';
			for (var i = 0; i < cityList.length; i++) {
				option += '<option value="' + cityList[i].cityId + '">'
						+ cityList[i].cityName + '</option>';
			}
			$('#city_name').html("");
			$('#city_name').empty();
			$('#city_name').append(option);
		},
		error : function(jqXHR, status, error) {
			(error);
		}
	});
}

var resetFormCreateGH = function() {
	isEdit = false;
	$("#city_name").val('');
	$("#gh_name").val('');
	$("#total_floor").val('');
	$("#total_room").val('');
	$("#blockId").val('');
	$("#address").val('');
	$("#contact_num").val('');
	$("#address_details").val('');
	$("#supportDoc").val('');
	$('#addGuestHouse').html('Add'); // default value
}
