$(document).ready(function() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var suppDoc = null;
	$('#supportDoc').change(function(e) {
		var file = e.target.files[0];
		if (file != undefined) {
			var size = Math.round((file.size / (1024 * 1024)));
			if (size > 2) {
				alert('Identity document should not be greater than 2 mb');
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
		/*alert("file uploaded ");*/
		var docType = $("docType").val();
		var supportDoc = $("#supportDoc").val();
		if (docType == '') {
			alert('Document Type Should not be blank');
		}
		if (supportDoc == '') {
			alert('file should not be blank');
			return false;
		}
		var form = $('#frmDocUpload')[0];
		var podata = new FormData(form);
		if (suppDoc != null) {
			podata.append("supportDoc", suppDoc, suppDoc.name)
		};
		console.log(podata);
		$.ajax({
			url : "./fileupload.do",
			type : "POST",
			data : podata,
			enctype : 'multipart/form-data',
			processData : false, // Important!
			contentType : false,
			cache: false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				/*alert("file uploadeed successfully------"+data);
				var result=data;
				
				if (result.status) {
					alert(result.message);
					window.location="./buildSession.html";*/
				var result=data;
				console.log("Result-------------->"+result);
				if(result.status){
					if(data.message != null){
						alert(data.message);
						window.location="./buildSession.html";
					}
				
				} else {
					alert(data.message);
				}
			},
			error : function(jqXHR, status, error) {
				console.log(error);
				swal(error);
			}
		});
	})
});