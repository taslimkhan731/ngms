$(document).ready(function () {
	
	 $('#filter-popover').on('click', function () {
        event.stopPropagation();
     });

    let cityData = $('#cityUserlocssd').val().trim();
    let cityDetails = JSON.parse(cityData);

    $("#city").html("");
    $("#city").append('<option value="">--Select City--</option>');
    cityDetails.forEach(option => {
        $("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
        $("#splitCity").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
    });
    let size = Object.keys(cityDetails).length;
    if (size == 1) {
        $('#city').val(cityDetails[0].gh_id);
        setTimeout(function () {
            $('#city').trigger('change');
        }, 1000);
    }

    $('#city').on('change', function () {
        let id = $('#city').val();
        getGuestHouseListDtls(id);
    });

    let getGuestHouseListDtls = function (id) {
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

    $("#gHouse").change(function () {
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        if (cityId != "" && blockId != "") {
            checkForShift(cityId, blockId);
            setTimeout(function () {
                let status = $('#shiftStatus').val();
                if (status == "true") {
                    loadGuestDetails();
                } else if (status == "false") {
                    let msg = $('#shiftMsg').val();
                    table.clear().draw();
                    swal(msg);
                    return false;
                } else {
                    swal("Please open shift");
                    table.clear.draw();
                    return false;
                }
            }, 1000);
        }
    });
    
    $('#filterBookingId').on('keyup', function () {
        this.value = this.value.toUpperCase();
    });
    
    $("#filterFromDate").datetimepicker({
        useCurrent: false,
        format: 'DD-MM-YYYY'
    });
    
    $('#filterSearch').click(function () {        
    	loadGuestDetails();
    	$('#filter-popover').unbind();   
    	setTimeout(function(){
    		$('#filter-popover').bind('click',preventClick);
    	}, 1000);
    });
    
    function preventClick() {
       event.stopPropagation();
    }
    
    $('#resetFiterBtn').click(function () {
        $('#filterBookingId').val('');
        $('#filterFromDate').val('');
        $('#filterMobNum').val('');
        loadGuestDetails();
    });

    let loadGuestDetails = () => {
        let cityId = $('#city').val();
        let blockId = $('#gHouse').val();
        let bookingId = $('#filterBookingId').val();
        let mobNo = $('#filterMobNum').val();
        let fromDate = $('#filterFromDate').val();
        let data = {
            "cId": cityId,
            "gId": blockId,
            "bookingId":bookingId,
            "mobNo":mobNo,
            "fromDate":fromDate
        };
        $.ajax({
            url: "./admin/getGuestListForFoodBill.do",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $('#loading').hide();
                table.clear().draw();
                table.rows.add(data.data).draw(); // Add new data
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

    };

    var table = $("#guestDataTable").DataTable({
        "paging": true,
        "searching": true,
        "scrollX": true,
        "lengthMenu": [[10, 20, 30, -1],
            [10, 20, 30, "All"]],
        "processing": false,
        "ordering": true,
        "info": false,
        "bFilter": false,
        "fixedHeader": {
            header: true,
            footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [
            {
                "targets": 0, "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {"targets": 1, "data": "bookingId"},
            {"targets": 2, "data": "fromDate"},
            {"targets": 3, "data": "toDate"},
            {"targets": 4, "data": "noRooms"},
            {"targets": 5, "data": "name"},
            {
                "targets": 6, "data": null, render: function (data, type, row, meta) {
                    return '<button type="button" id="addFood" title="Add Food Bill" class="btn btn-sm btn-add"><i class="fa fa-plus" aria-hidden="true"></i></button> '
                }
            }],
    });

    let bookingId = "";
    let guestId = "";
    let foodItems = [];

    $('#guestDataTable tbody').on('click', '.btn-add', function () {
        let data = table.row($(this).parents('tr')).data();
        bookingId = data.bookingId;
        guestId = data.guestId;
        console.log(data);
        $('#foodDetailModal').modal('show');
        let guest = data.name;
        console.log(guest.indexOf(','));
        if (guest.indexOf(',') !== -1) {
            guest = guest.slice(0, guest.indexOf(','));
        }
        $('#guestName').val(guest);
        getFoodItems();
    });

    let getFoodItems = () => {
        $.ajax({
            url: "./admin/getAllFoodItems.do",
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                foodItems = data.data;
                $('#loading').hide();
                $("#item").html("");
                $("#item").append('<option value="">---Select---</option>');
                data.data.forEach(option => {
                    $("#item").append('<option value="' + option.itemId + '">' + option.itemTitle + '</option>');
                })
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

    $('#item').on('change', function () {
        let id = $('#item').val();
        if (id == "") {
            $('#price').val("");
            return false;
        }
        let price = foodItems.find(x => x.itemId == id).unitPrice;
        $('#price').val(price);
    });

    $('#item, #unit').on('change keyup', function () {
        let price = $('#price').val();
        let unit = $('#unit').val();
        let total = price * unit;
        $('#total').val(total);
    });

    $('#addFoodBill').on('click', function () {
        let item = $('#item').val();
        let unit = $('#unit').val();
        let price = $('#price').val();
        let total = $('#total').val();
        let foodTime = $('#foodTime').val();
        if(item == ""){
            swal("Please select item");
            return false;
        }
        if(unit == "" || unit == 0){
            swal("Please enter no of units");
            return false;
        }
        let data = {
            "bookingId": bookingId,
            "guestId": guestId,
            "itemId": item,
            "unitPrice": price,
            "noOfUnits": unit,
            "total": total,
            "foodTime": foodTime
        };

        $.ajax({
            url: "./admin/addFoodBill.do",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            beforeSend: function (xhr) {
                $('#loading').show();
                xhr.setRequestHeader(header, token);
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $('#loading').hide();
                swal(data.msg);
                $('#foodDetailModal').modal('hide');
                loadGuestDetails();
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
    });
    
    $('#foodDetailModal').on('hidden.bs.modal', function() {
    	$('#price').val("");
    	$('#unit').val("");
    	$('#total').val("");
    });

});