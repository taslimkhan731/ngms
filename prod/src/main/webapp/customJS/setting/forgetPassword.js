$(document).ready(function(){
	
	/************************************
	 * Click on Button send Mail
	 ***********************************/
	$('#btnSend').click(function(){
		
		var email = $('#email').val();
		if(email != '') {
			$.ajax({
				url: "./sendMail.do",
				type: "POST",
				data:{email:email},
				 beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				success: function(result) {
					swal(result)
					if (result =="success") {
						$('#email').val("");
					}
				},
			})
		}
		else {
			swal("Please Type Your Email");
		}
		
	});
	
	
	
})
	