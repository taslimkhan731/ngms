$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var homeDoc = null;
	$('#homeDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math
					.round((file.size / (1024 * 1024)));
			if (size > 2) {
				swal('Identity document should not be greater than 2 mb');
				$('#homeDocName').html('Upload proof pdf');
				$('#homeDoc').val('');
			} else {
				homeDoc = file;
				$('#homeDocName').html(homeDoc.name);
			}
		} else {
			$('#homeDocName').html('Upload proof pdf');
		}
	});
	var table = $("#documentUploadDetails").DataTable({
		"paging": true,
        "searching": false,
        "scrollX": false,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "processing": false,
        "ordering": false,
        "info": false,
        "bFilter": true,
        "bLengthChange": true,
        "fixedHeader": {
            header: true,
            footer: true
        },
        "scrollCollapse": true,
        "columnDefs": [
            {
                "className": "text-center text-nowrap",
                "targets": "_all"
            },
            {
                "targets": 0, "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {"targets": 1, "data": "bookingId"},
            {"targets": 2, "data": "name"},
            {
                "targets": 3, "data": null,
                render: function (data, type, row, meta) {
                    return '<button type="button" id="edit" title="Edit" class="btn btn-edit btn-sm" action="select" ><i class="fa fa-edit" aria-hidden="true"></i></button> '                    
                }
            }
        ],
	});
	//getDataFromDocumentsTable();
	
	$('#addDocument').on('click', function () {
    	var homeDocType = $('#homeDocType').val();
        var homeDocument = $('#homeDoc').val();
        if(homeDocType == "" || homeDocType == null){
        	swal('Please select document type')
        }
        if(homeDocument == "" || homeDocument == null){
        	swal('Please select document')
        }
        submitDocumentDetails();
    });
					
	var submitDocumentDetails = function() {
		var form = $('#documentUploadForm')[0];
		var podata = new FormData(form);
		
		if (homeDoc != null) {
			podata.append("homeDoc", homeDoc, homeDoc.name);
		};

		$.ajax({
			url : "./mdm/saveDocumentDetails.do",
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
					$('#documentUploadForm').trigger("reset");
					$('#homeDocName').html("Choose File");
					/*$('#documentUploadForm')[0].reset();*/
				} else {
					swal(result.msg, "", "error");
					$('#documentUploadForm').trigger("reset");
					$('#homeDocName').html("Choose File");
					/*$('#documentUploadForm')[0].reset();*/
				}
			},
			error : function(jqXHR, status, error) {
				swal(error);
			}

		});
	}
	
	var getDataFromDocuments = function () {
        $.ajax({
            url: './mdm/getDataFromDocumentsTable.do',
            type: "GET",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                console.log(result);
                table.clear();
                table.rows.add(result.data).draw();
            },
            error: function (jqXHR, status, error) {
                swal(error);
            }
        });
    }
})
