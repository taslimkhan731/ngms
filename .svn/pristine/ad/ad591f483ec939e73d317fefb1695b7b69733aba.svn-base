$('document').ready(function () {
	 var blockId = 0;
	 var uploadFile = null;
	 var cityData = $('#cityUserlocssd').val().trim();
	 var cityDetails = JSON.parse(cityData);
	 var uploadId=null;
	  $('#splitCityGH').hide();
    $("#city").html("");
    $("#city").append('<option value="">--Select City--</option>');
    $("#splitCity").append('<option value="">--Select City--</option>');
    cityDetails.forEach(option => {
        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
        $("#splitCity").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
    })
    let size = Object.keys(cityDetails).length;
    if (size == 1) {
        $('#city').val(cityDetails[0].gh_id);
        setTimeout(function () {
            $('#city').trigger('change');
        }, 1000);
    }
    $('#city').on('change', function () {
        var id = $('#city').val();
        getGuestHouseListDtls(id);
        getOfficeOrderDocUploadList();
    });

    var getGuestHouseListDtls = function (id) {
        $.ajax({
            url: "./admin/getGuestHouseListDetails/" + id + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function () {
                $('#loading').show();
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $('#loading').hide();
                $("#gHouse").html("");
                $("#gHouse").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    $("#gHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
                })
                let size = Object.keys(data).length;
                if (size == 1) {
                    $('#gHouse').val(data[0].id);
                }
                $('#gHouse').trigger('change');
            }, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
        });
    }
    var getGuestHouseListDtlsForSplit = function (id) {
        $.ajax({
            url: "./admin/getGuestHouseListDetails/" + id + ".do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function () {
                $('#loading').show();
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $('#loading').hide();
                $("#splitGHouse").html("");
                $("#splitGHouse").append('<option value="">---Select---</option>');
                data.forEach(option => {
                    $("#splitGHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
                })
                let size = Object.keys(data).length;
                if (size == 1) {
                    blockId = (data[0].id)
                    $('#splitGHouse').val(blockId);
                }
            }, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
        });
    }
    
    $('#gHouse').on('change', function () {
        getOfficeOrderDocUploadList();
    });   

    $("#docDt").datetimepicker({
        useCurrent: false,
        maxDate:moment().endOf('day'),
        format: 'DD-MM-YYYY',
    });

    $('#docUpload').change(function (e) {
        var file = e.target.files[0];
        if (file != undefined) {
            var size = Math.round((file.size / (1024 * 1024)));
            if (size > 2) {
                swal('Category document should not be greater than 2 mb');
                $('#uploadDocName').html('Choose file');
                $('#docUpload').val('');
            } else {
            	uploadFile = file;
                $('#uploadDocName').html(uploadFile.name);
            }
        } else {
            $('#uploadDocName').html('Choose file');
        }
    });
	 let isCityAndGHSelected = function () {
	        let city = $('#city').val();
	        let gHouse = $('#gHouse').val();
	        if (city == "") {
	            swal("Select City");
	            return false;
	        }
	        if (gHouse == "") {
	            swal("Select Guest House");
	            return false;
	        }
	        return true;
	    }
	 $('#createDE').on('click', function () {
	    	if (!isCityAndGHSelected())
	            return false;
	        $('#uploadOfficeOrderDoc').modal('show');
	        var cityId = $('#city').val();
	        var guestHouseId =  $('#gHouse').val();
	        $('#guestHouse').val(guestHouseId);
	        $('#cityId').val(cityId);
	    });
	 
	 $('#frmDocUpload').bootstrapValidator({
			btnSubmit: 'button[type="submit"]',
			framework : 'bootstrap',
			fields : {

				documentDate: {
					 validators: {
		                    notEmpty: {
		                        message: 'field can\'t be empty'
		                    },
		                    date: {
		                        format: 'DD-MM-YYYY'
		                    }
		                }
				},
				docUpload : {
					validators : {
						notEmpty : {
							message : 'Please select document to upload'
						}

					}
				}
				
			}
		}).on('success.form.bv', function (e) {
	        e.preventDefault();
	        submitOfficeOrderDetails();
	    });

		var submitOfficeOrderDetails = function(){
			
			var formData = $('#frmDocUpload').serializeArray();
			var podata = new FormData();
			if (uploadFile != null ) {		
				podata.append("docUpload", uploadFile, uploadFile.name);
				podata.append("splitCity",$('#city').val());
				podata.append("splitGHouse",$('#gHouse').val());
				podata.append("documentDate",$('#docDt').val());
			};
			console.log("podata________________________"+JSON.stringify(podata));
			$.ajax({
				url : './admin/documentForOfficeOrder.do',
				type : "POST",
				data : podata,
				enctype : 'multipart/form-data',
				processData : false, // Important!
				contentType : false,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(data, textStatus, jqXHR) {
					$('#uploadOfficeOrderDoc').modal('hide');
					getOfficeOrderDocUploadList();
					if (data.status) {
						swal({
							title : "Success!",
							text : data.message,
							type : "success"
						})
					} else {
						swal(data.message);
					}
				},
				error : function(jqXHR, status, error) {
					swal(error);
				}
			});

		}
		
		var officeorderDocUploadTBL = $("#confirmListDataTable").DataTable({ 
			"paging": true,
	        "searching": true,
	        "responsive": true,
	        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
	        "processing": false,
			"ordering": false,
			"info": false,
			"bFilter": false,
			"fixedHeader": {
				header: true,
				footer: true
			},
			"scrollCollapse": true,
			"columnDefs":[		
				{ className: "all", "targets": [ 0, 1,2 ] },
				{
	                "targets": 0, "data": null,
	                render: function (data, type, row, meta) {
	                    return meta.row + meta.settings._iDisplayStart + 1;
	                }
				},
				{"targets":1,"data":"splitCity"},
				{"targets":2,"data":"splitGHouse"},
				{"targets":3,"data":"documentDate"},
				{"targets":4,"data":"createdBy"},
				{"targets": 5, "data": null, render: function (data, type, row, meta) {
	                return `<button type="button" id="view" action="View" title="View" class="btn btn-view btn-sm"><i class="fa fa-eye" aria-hidden="true"></i></button> `
	            }}		
				], 	  
		});
		var getOfficeOrderDocUploadList = function(){
			console.log("inside mehtod");
			 var city = $('#city').val();
		     var gHouse = $('#gHouse').val();
		     console.log("  "+city+"  "+gHouse);
		     $("#loading").show();
			$.ajax({
				url: "./admin/getOfficeOrderDocUploadList.do",
				type: "GET",
				contentType : "application/json",
				dataType : "JSON",
				data:{
					cId:city,
			        gId:gHouse,
			    },
				success: function(result) {
					$("#loading").hide();
					officeorderDocUploadTBL.clear().draw();
					officeorderDocUploadTBL.rows.add(result.data).draw();
					
				}, error:function(jqXHR, status, error){
					swal(error);
				}
		});
	}
		getOfficeOrderDocUploadList();
		
		 $('#confirmListDataTable tbody').on('click', '#view', function () {
		        var data = officeorderDocUploadTBL.row($(this).closest('tr')).data() != undefined ? officeorderDocUploadTBL.row($(this).closest('tr')).data() : officeorderDocUploadTBL.row($(this)).data();
		        uploadId = data.uploadId;
			 var fileUrl = "./imagedownload/officeOrder_"+uploadId +".do";
			    var link = $('<a>', {
			      href: fileUrl,
			      target: '_blank'
			    })[0];
			    
			    var event = new MouseEvent('click', {
			      view: window,
			      bubbles: false,
			      cancelable: true
			    });
			    
			    link.dispatchEvent(event);
			  });		

		    $("#uploadOfficeOrderDoc").on("hide.bs.modal", function () {
		        $(this).find('form').trigger('reset');
		        $('#frmDocUpload')[0].reset();
		        $('#docUpload').val('');
		        $('#uploadDocName').html('Choose File');
		    });
});
