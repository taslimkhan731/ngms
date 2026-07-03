$(document).ready(function(){
	var del_01 = `<tr>
		<td>
		<div class="h-card row">
		<div class="h-card-img col-5">
		<img src="./img/Odisha_bhawan_Delhi.jpeg" alt="Guest House">
		</div>
		<div class="h-card-content col-7">
		<div class="hc-title">Odisha Bhavan, New Delhi</div>
		<div class="hc-sub-title">Chanakyapuri</div>
		<div class="hc-sub-title">New Delhi, Delhi 110011</div>
		<div class="hc-sub-title">Contact: 011 2467 9201</div>
		<a href="./guest/registration.html?id=01">
		<button class="btn btn-primary hc-btn">book</button>
		</a>
		</div>
		</div>
		</td>
		</tr>`;
	var del_02 = `<tr>
		<td>
		<div class="h-card row">
		<div class="h-card-img col-5">
		<img src="./img/4.jpeg" alt="Guest House">
		</div>
		<div class="h-card-content col-7">
		<div class="hc-title">Odisha Niwas, New Delhi</div>
		<div class="hc-sub-title">Chanakyapuri</div>
		<div class="hc-sub-title">New Delhi, Delhi 110011</div>
		<div class="hc-sub-title">Contact: 011 2467 9201</div>
		<a href="./guest/registration.html?id=02">
		<button class="btn btn-primary hc-btn">book</button>
		</a>
		</div>
		</div>
		</td>
		</tr>`;
	var del_03 = `<tr>
		<td>
		<div class="h-card row">
		<div class="h-card-img col-5">
		<img src="./img/orissa-sadan-dwarka.jpg" alt="Guest House">
		</div>
		<div class="h-card-content col-7">
		<div class="hc-title">Odisha Sadan, New Delhi</div>
		<div class="hc-sub-title">Dwarka</div>
		<div class="hc-sub-title">New Delhi, Delhi 110011</div>
		<div class="hc-sub-title">Contact: 011 2803 6465</div>
		<a href="./guest/registration.html?id=03">
		<button class="btn btn-primary hc-btn">book</button>
		</a>
		</div>
		</div>
		</td>
		</tr>`;
	var mum_04 = `<tr>
		<td>
		<div class="h-card row">
		<div class="h-card-img col-5">
		<img src="./img/3.jpg" alt="Guest House">
		</div>
		<div class="h-card-content col-7">
		<div class="hc-title">Odisha Bhawan, Mumbai</div>
		<div class="hc-sub-title">Plot No.-5,Sector 30A</div>
		<div class="hc-sub-title">Vashi, Mumbai</div>
		<div class="hc-sub-title">Contact: 022-20873372/74</div>
		<a href="./guest/registration.html?id=04">
		<button class="btn btn-primary hc-btn">book</button>
		</a>
		</div>
		</div>
		</td>
		</tr>`;
	var kol_05 = `<tr>
		<td>
		<div class="h-card row">
		<div class="h-card-img col-5">
		<img src="./img/Utkal_bhawan_Kolkatta4.jpg" alt="Guest House" style="height: 106px; width: 100%;">
		</div>
		<div class="h-card-content col-7">
		<div class="hc-title">Utkal Bhawan, Kolkata</div>
		<div class="hc-sub-title">55, Lenin Sarani Rd</div>
		<div class="hc-sub-title"> Janbazar, Taltala, Kolkata</div>
		<div class="hc-sub-title">Contact: 033 2265 1195</div>
		<a href="./guest/registration.html?id=05">
		<button class="btn btn-primary hc-btn">book</button>
		</a>
		</div>
		</div>
		</td>
		</tr>`;
	var che_06 = `<tr>
		<td>
		<div class="h-card row">
		<div class="h-card-img col-5">
		<img src="./img/25-9-20183.jpeg" alt="Guest House">
		</div>
		<div class="h-card-content col-7">
		<div class="hc-title">Odisha Bhawan, Chennai</div>
		<div class="hc-sub-title">Velachery - Tambaram Main Rd</div>
		<div class="hc-sub-title"> Rose Avenue, Kovilambakkam</div>
		<div class="hc-sub-title">Contact: 044-2987929</div>
		<a href="./guest/registration.html?id=06">
		<button class="btn btn-primary hc-btn">book</button>
		</a>
		</div>
		</div>
		</td>
		</tr>`;

	$('#guestHouseView').find('#ghBody').append(del_01);
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
	});
});

/**************************************************
 *   Get The PEDNING REQUESTS FROM ADDRESS ERROR MARK
 ***************************************************/
function getDashboardData(){
	var id = $('#blockId').val();
	console.log('Value ' + id);
	//$('#loading').show();
	$.ajax({
		url: './guest/getGuestDashboard.do',
		type: "GET",	
		headers : {"X-CSRF-TOKEN" : token},		 
		success:function(result) {
			if(result.success){
				$('#loading').hide();
				$.each(result.data, function(key, value) {
					/*if('01' == value.blockId){
						if(value.appliedCount != null){
							$('#01').html(value.appliedCount);
						}
						if(value.actionCount != null){
							$('#001').html(value.actionCount);
						}
					}else if('02' == value.blockId){
						if(value.appliedCount != null){
							$('#02').html(value.appliedCount);
						}
						if(value.actionCount != null){
							$('#002').html(value.actionCount);
						}
					}else if('03' == value.blockId){
						if(value.appliedCount != null){
							$('#03').html(value.appliedCount);
						}
						if(value.actionCount != null){
							$('#003').html(value.actionCount);
						}
					}else if('04' == value.blockId){
						if(value.appliedCount != null){
							$('#04').html(value.appliedCount);
						}
						if(value.actionCount != null){
							$('#004').html(value.actionCount);
						}
					}else if('05' == value.blockId){
						if(value.appliedCount != null){
							$('#05').html(value.appliedCount);
						}
						if(value.actionCount != null){
							$('#005').html(value.actionCount);
						}
					}else if('06' == value.blockId){
						if(value.appliedCount != null){
							$('#06').html(value.appliedCount);
						}
						if(value.actionCount != null){
							$('#006').html(value.actionCount);
						}
					}*/
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