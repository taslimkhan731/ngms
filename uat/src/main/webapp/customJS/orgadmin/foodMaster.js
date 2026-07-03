$('document').ready(function () {
    $('#filter-popover').on('click', function () {
        event.stopPropagation();
    });
    let cityData = $('#cityUserlocssd').val().trim();
    let cityDetails = JSON.parse(cityData);
    let guestDetails;

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
        // getAllBillingList();
    });

    $("#itemList").show();
    $("#editList").hide();
    $("#deleteList").hide();

    $('#go').on('click', function () {
        $('#foodDetailModal').modal('show');
    });

    $('#add').on('click', function () {
        document.getElementById("modalTitle").innerHTML = "Add Food Details";
        document.getElementById("modalBtn").innerHTML = "Add";
        $('#item').prop('disabled', false);
        $("#item").val('');
        $("#rate").val('');
        $('#addModal').modal('show');

    });
    $('#foodMasterDataTable tbody').on('click', '#edit', function () {
        document.getElementById("modalTitle").innerHTML = "Edit Food Details";
        document.getElementById("modalBtn").innerHTML = "Update";
        var data = contable.row($(this).parents('tr')).data();
        $("#item").val(data.itemTitle);
        $("#rate").val(data.unitPrice);
        $("#itemId").val(data.itemId);
        console.log("***************", data);
        $('#addModal').modal('show');
    });

    var contable = $("#foodMasterDataTable").DataTable({
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
                render: function (data,
                                  type, row, meta) {
                    return meta.row
                        + meta.settings._iDisplayStart
                        + 1;
                }
            },
            {"targets": 1, "data": "itemTitle"},
            {"targets": 2, "data": "unitPrice"},
            {"targets": 3, "data": null,
                render: function (data, type, row, meta) {
                    return '<button type="button" id="edit" title="Edit" class="btn btn-edit btn-sm mr-1"><i class="fa fa-edit" aria-hidden="true"></i></button> ' +
                    '<button type="button" id="delete" title="Delete" class="btn btn-dlt btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>';
                }
            }
        ],
    });

    let loadDataIntoFoodMaster = () => {
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
                $('#loading').hide();
                contable.clear();
                contable.rows.add(data.data).draw();
            }
        });
    }
    loadDataIntoFoodMaster();

    $('#view').on('click', function () {
        document.getElementById("headerName").innerHTML = "Food Items List";
        $('#edit').removeClass('custom-btn-active');
        $('#view').addClass('custom-btn-active');
        $('#delete').removeClass('custom-btn-active');
        $("#editList").hide();
        $("#deleteList").hide();
        $("#itemList").show();
        loadDataIntoFoodMaster();
    });

    $('#modalBtn').on('click', function () {
        let btn = $('#modalBtn').text();
        let item = $("#item").val();
        let rate = $("#rate").val();

        if (item.trim() == '') {
            swal("Please Provide Item Name");
            return false;
        }
        if (rate.trim() == '') {
            swal("Please Enter Rate");
            return false;
        }

        if (btn === 'Add') {
            let data = {
                "itemTitle": item,
                "unitPrice": rate
            }
            $.ajax({
                url: "./admin/addFoodItem.do",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                beforeSend: function (xhr) {
                    $('#loading').show();
                    xhr.setRequestHeader(header, token);
                },
                success: function (data, textStatus, jqXHR) {
                    $('#loading').hide();
                    $('#addModal').modal('hide');
                    swal(data.msg);
                    loadDataIntoFoodMaster();
                    $('#view').trigger('click');
                },error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
            });
        } else if (btn === 'Update') {
            let id = $("#itemId").val();
            let data = {
                "itemId": id,
                "itemTitle": item,
                "unitPrice": rate
            }
            $.ajax({
                url: "./admin/updateFoodItem.do",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                beforeSend: function (xhr) {
                    $('#loading').show();
                    xhr.setRequestHeader(header, token);
                },
                success: function (data, textStatus, jqXHR) {
                    $('#loading').hide();
                    $('#addModal').modal('hide');
                    swal(data.msg);
                    loadDataIntoFoodMaster();
                    $('#view').trigger('click');
                },error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
            });
        }
    });

   
    $('#foodMasterDataTable tbody').on('click', '#delete', function () {
        var data = contable.row($(this).parents('tr')).data();
        console.log(data);
        swal({
                title: "Are you sure? ",
                text: "Are you sure to delete!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false
            },
            function () {
                $('#loading').show();
                $.ajax({
                    url: './admin/deleteFoodItem/' + data.itemId + '.do',
                    type: "GET",
                    beforeSend: function (xhr) {
                        $('#loading').show();
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (result, textStatus, jqXHR) {
                        $('#loading').hide();
                        if (result.status) {
                            swal(result.msg); 
                        	loadDataIntoFoodMaster();
                            $('#view').trigger('click');
                        } else {
                            swal(result.msg);
                        }
                    }
                });
            });
    });
     $('#rate').on('keydown', function(event) {
        // Allow control keys (backspace, delete, tab, etc.) and numeric keys
        if (
            event.key === 'Backspace' || 
            event.key === 'Delete' || 
            event.key === 'Tab' || 
            event.key === 'Enter' || 
            (event.key >= '0' && event.key <= '9')
        ) {
            return; // Allow this input
        }
        // Prevent the negative sign and any other character input
        if (event.key === '-') {
            event.preventDefault(); // Prevent negative sign
        }
      });
	    $('#rate').on('input', function() {
	        if (this.value < 0) {
	            this.value = 0;
	        }
	    });
});