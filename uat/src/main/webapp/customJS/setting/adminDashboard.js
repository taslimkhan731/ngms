$(document).ready(function(){	
var getTotalCount = function() {
		 var city = $('#city').val();
	     var gHouse = $('#gHouse').val();
		$.ajax({
			url: "./setting/dashboardCount.do",
			type: "GET",
			contentType : "application/json",
			dataType : "JSON",
			data:{
				cId:city,
		        gId:gHouse
		    },		
			success:function(result) {
				$('#loading').hide();
				$('#moderator').html(result.data.noOfModerator);
				$('#receptionist').html(result.data.noOfReceptionist);
				$('#guest').html(result.data.noOfGuest);
				$('#approver').html(result.data.noOfApprover);
				$('#manager').html(result.data.noOfManager);
				$('#city').html(result.data.noOfCity);
				$('#guestHouse').html(result.data.noOfGuestHouse);
				$('#totalUser').html(result.data.totalUser);
				},
			error:function(jqXHR, status, error){
				swal(error,"","error");
			}
		});
	}
	getTotalCount();
});