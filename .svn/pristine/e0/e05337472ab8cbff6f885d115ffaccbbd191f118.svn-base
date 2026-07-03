$(document).ready(function(){
	/*$('#guestHouseView').find('#ghBody').append(del_01);
	$('#guestHouseView').find('#ghBody').append(del_02);
	$('#guestHouseView').find('#ghBody').append(del_03);
	getDashboardData();

	$('#city').on('change', function() {
		if($('#city').val() == '01'){
			$("#ghBody").empty();
			$('#guestHouseView').find('#ghBody').append(del_01);
			$('#guestHouseView').find('#ghBody').append(del_02);
			$('#guestHouseView').find('#ghBody').append(del_03);
		}else if($('#city').val() == '02'){
			$("#ghBody").empty();
			$('#guestHouseView').find('#ghBody').append(mum_04);
		}else if($('#city').val() == '03'){
			$("#ghBody").empty();
			$('#guestHouseView').find('#ghBody').append(kol_05);
		}else if($('#city').val() == '04'){
			$("#ghBody").empty();
			$('#guestHouseView').find('#ghBody').append(che_06);
		}
	});*/
	
	function cityFun(id) {
		alert('Hiii');
    	//$('')
    }
});

/**************************************************
 *   Get The PEDNING REQUESTS FROM ADDRESS ERROR MARK
 ***************************************************/
function getDashboardData(){
	//var id = $('#blockId').val();
	var id = '02';
	console.log('Value ' + id);
	//$('#loading').show();
	$.ajax({
		url: '../guest/getGuestDashboard.do',
		type: "GET",	
		headers : {"X-CSRF-TOKEN" : token},		 
		success:function(result) {
			if(result.success){
				$('#loading').hide();
				$.each(result.data, function(key, value) {					
				});

				$.each(result.city, function(key, value) {
					$('#city')
					.append($('<option>', { value : value.cityId })
							.text(value.cityName));
				});
			}else{
				swal(result.msg);
			}
		}
	});
}