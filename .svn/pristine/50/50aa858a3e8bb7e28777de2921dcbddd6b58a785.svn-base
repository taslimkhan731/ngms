$(document).ready(function(){	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var suppDoc = null;
	$('#supportDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math.round((file.size / (1024 * 1024)));
			if (size > 2) {
				swal('Identity document should not be greater than 2 mb');
				$('#supportDoc').html('Upload proof (image/pdf)');
				$('#supportDoc').val('');
			} else {
				suppDoc = file;
				$('#supportDoc').html(suppDoc.name);
			}
		} else {
			$('#supportDoc').html('Upload proof (jpg/pdf)');
		}
	});
	

	
	$("#getUpload").click(function() {
		var docType = $("#docType").val();
		var supportDoc = $("#supportDoc").val();
		if (docType == '') {
			swal('Document Type Should not be blank');
			return false;
		}
		if (supportDoc == '') {
			swal('file should not be blank');
			return false;
		}
		var form = $('#frmDocUpload')[0];
		var podata = new FormData(form);
		if (suppDoc != null ) {

			podata.append("supportDoc", suppDoc, suppDoc.name);

		}
		;
		console.log(podata);
		$.ajax({
			url : './guestUpload.do',
			type : "POST",
			data : podata,
			enctype : 'multipart/form-data',
			processData : false, // Important!
			contentType : false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data, textStatus, jqXHR) {
				if (data.status) {					
					swal({
						title : "Success!",
						text : data.message,
						type : "success"
					}, function() {
						window.location = "./buildSession.html";
					});
				} else {
					swal(data.message);
				}
			},
			error : function(jqXHR, status, error) {
				swal(error);
			}
		});
	})	
});

