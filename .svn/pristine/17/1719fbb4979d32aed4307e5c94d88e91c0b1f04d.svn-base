$('document').ready(function() {
	
    var cityData = $('#cityUserlocssd').val().trim();
    var cityDetails = JSON.parse(cityData);
    $("#city").html("");
    $("#city").append('<option value="">--Select City--</option>');
    
    cityDetails.forEach(option => {
        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
    })
    let size = Object.keys(cityDetails).length;
    if (size == 1) {
        $('#city').val(cityDetails[0].gh_id);
        setTimeout(function () {
            $('#city').trigger('change');
        }, 1000);
    }
 
    
    $('#city').on('change', function () {
	     $('#guestReportBtn').removeClass("custom-btn-active");
        var id = $('#city').val();
        getGuestReport();
        getGuestHouseListDtls(id);
    });
	var getGuestHouseListDtls = function (id) {
        blockId = id;
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
            },error: function (jqXHR, status, error) {
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
          getGuestReport();
    });
    
    $('#filterCategory').append(
        "<option value='C1'>Category 1</option>" +
        "<option value='C2'>Category 2</option>" +
        "<option value='C3'>Category 3</option>" +
        "<option value='C4'>Category 4</option>"
    );
    
	 $("#filterBookingDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
	
		
var	table = $("#dailyReportTbl")
	.DataTable(
			{
				
				"paging" : true,
				"searching" : false,
				"scrollX": true,
				"lengthMenu" : [ [ 10, 20, 30, -1 ],
					[ 10, 20, 30, "All" ] ],
					"processing" : false,
					"ordering" : true,
					"info" : false,
					"bFilter" : false,
					"bLengthChange": false,
					"fixedHeader" : {
						header : true,
						footer : true
					},
					"scrollCollapse" : true,			
					"columnDefs" : [
						{
							"targets" : 0,
							"data" : null,
							render : function(data,
									type, row, meta) {
								return meta.row
								+ meta.settings._iDisplayStart
								+ 1;
							}
						}, {
							"targets" : 1,
							"data" : "bookingId"
						}, {
							"targets" : 2,
							"data" : "fromDate",
							
						},{
							"targets" : 3,
							"data" : "toDate"
						}, {
							"targets" : 4,
							"data" : "catgId"
						}, {
							"targets" : 5,
							"data" : "purpose"
						}, {
							"targets" : 6,
							"data" : "statusDesc"

						}],
			});

	
	var getGuestReport = function () {
    	var city = $('#city').val();
        var gHouse = $('#gHouse').val();
        let fromDate = $('#filterBookingDate').val();
        let category = $('#filterCategory').val();
        if (fromDate == '') {
        	fromDate = moment().format('DD-MM-YYYY');
        }
        let postData = {  
        	gHouse:gHouse,
        	city:city,
            frmDate: fromDate,
            category:category,
            reqType: 'AL',
			type:'individual',
			orderBy : 'categoryWise'
			
        };

        $.ajax({
            url: './admin/getAllGuestDetails.do',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            beforeSend: function (xhr) {
            	 $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
            	 $('#loading').hide();
                console.log(result);
                table.clear();
                table.rows.add(result.data.data).draw();
            },
            error: function (jqXHR, status, error) {
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

	getGuestReport();
	$('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
    
   

  
    $('#filterSearch').click(function () {        
    	getGuestReport();
    	$('#filter-popover').unbind();
    	setTimeout(function(){
    		$('#filter-popover').bind('click',preventClick);
    	}, 1000);
    });
    

    function preventClick() {
       event.stopPropagation();
    }
    
    $('#resetFiterBtn').click(function () {
 
        $('#filterBookingDate').val('');
        $('#filterCategory').val('');
		getGuestReport();
    });
    
  

});
