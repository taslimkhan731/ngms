     
     $(document).ready(function() { 
    	 
			var current_fs, next_fs, previous_fs; //fieldsets
			var opacity;
			var current = 1;
			var steps = $("fieldset").length;
			setProgressBar(current);
			$('#captchaImgDtls').mousedown(function(e) {
				e.preventDefault();
			});
			var getCaptchaCode= function() {
				$('#captchaImgDtls').attr('src','./captcha/getCaptcha.html?'+Math.random());
			}
			$("#captchaBtnReload").click(function(){
//				alert(10);
				getCaptchaCode();
			})

			$("#captcha").bind("paste", function(e) {
				e.preventDefault();
			});
			
			$("input[name='foodradio']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="foodradio"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="foodradio"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="foodradio"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="foodradio"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="foodradio"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });



			 $("input[name='housekeeping']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="housekeeping"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });


			 $("input[name='checkincheckout']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="checkincheckout"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });


			 $("input[name='conduct']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="conduct"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="conduct"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="conduct"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="conduct"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="conduct"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });




			 $("input[name='clean']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="clean"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="clean"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="clean"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="clean"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="clean"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });
			 
			 $("input[name='overall']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="overall"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="overall"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="overall"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="overall"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="overall"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });
			 $("input[name='foodradio']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="foodradio"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="foodradio"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="foodradio"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="foodradio"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="foodradio"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });



			 $("input[name='housekeeping']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="housekeeping"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="housekeeping"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });


			 $("input[name='checkincheckout']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="checkincheckout"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="checkincheckout"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });


			 $("input[name='conduct']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="conduct"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="conduct"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="conduct"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="conduct"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="conduct"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });




			 $("input[name='clean']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="clean"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="clean"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="clean"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="clean"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="clean"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });
			 
			 $("input[name='overall']").click(function () {
				 /* emoji color */
				 var i=0;
				$(this).parent().parent().children('label').each(function (index) {
					i=parseInt(index+1);
					$(this).removeClass('label-radio'+i).addClass('lbl-grey');
					console.log(i);
					console.log($(this).attr('class'));
					if(index==0){
						$(this).children().first().removeClass('firsticon-bold').addClass('firsticon');
					}else if(index==1){
						$(this).children().first().removeClass('secondicon-bold').addClass('secondicon');
					}else if(index==2){
						$(this).children().first().removeClass('thirdicon-bold').addClass('thirdicon');
					}else if(index==3){
						$(this).children().first().removeClass('fouricon-bold').addClass('fouricon');
					}else if(index==4){
						$(this).children().first().removeClass('fifthicon-bold').addClass('fifthicon');
					}
				});
				
				if($('input[name="overall"]:checked').val()==1){
					
					$(this).parent().removeClass('lbl-grey').addClass('label-radio5');
					$(this).prev().addClass('fifthicon-bold');
				}else if($('input[name="overall"]:checked').val()==2){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio4');
				 	$(this).prev().addClass('fouricon-bold');
				}else if($('input[name="overall"]:checked').val()==3){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio3');
				 	$(this).prev().addClass('thirdicon-bold');
				}else if($('input[name="overall"]:checked').val()==4){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio2');
					$(this).prev().addClass('secondicon-bold');
				}else if($('input[name="overall"]:checked').val()==5){
					$(this).parent().removeClass('lbl-grey').addClass('label-radio1');
				 	$(this).prev().addClass('firsticon-bold');
				}
				 
			 });
    	 
				$("#select").click(function() {
						$(".modalView").show();
						console.log("clicked");
						$('#myModalView').modal('show');
						var foodvalueval=$("input[name='foodradio']:checked").val();
						var housekeepingvalue=$("input[name='housekeeping']:checked").val();
						var checkincheckoutvalue=$("input[name='checkincheckout']:checked").val();
						var conductvalue=$("input[name='conduct']:checked").val();
						var cleanvalue=$("input[name='clean']:checked").val();
						var overall=$("input[name='overall']:checked").val();
						var sms=$('#description').val();
						$('input[name="foodradio"][type="radio"][value="' + foodvalueval +'"]').prop('checked', true);
						$('input[name="housekeeping"][type="radio"][value="' + housekeepingvalue +'"]').prop('checked', true);
						$('input[name="checkincheckout"][type="radio"][value="' + checkincheckoutvalue +'"]').prop('checked', true);
						$('input[name="conduct"][type="radio"][value="' + conductvalue +'"]').prop('checked', true);
						$('input[name="clean"][type="radio"][value="' + cleanvalue +'"]').prop('checked', true);
						$('input[name="overall"][type="radio"][value="' + overall +'"]').prop('checked', true);
						$('#description1').val(sms);
						$('#name1').val($('#name').val());
						$('#requestId1').val($('#requestId').val());
						$('#mobile1').val($('#mobile').val());
					})
					$(function() {
						$('input[name="checkincheckout"]').change(function() {
							$("#checkinbtn").prop('disabled', false);
						});
						$('input[name="housekeeping"]').change(function() {
							$("#housekeepingbtn").prop('disabled', false);
						});
						$('input[name="clean"]').change(function() {
							$("#cleanbtn").prop('disabled', false);
						});
						$('input[name="foodradio"]').change(function() {
							$("#foodbtn").prop('disabled', false);
						});					
						$('input[name="conduct"]').change(function() {
							$("#conductbtn").prop('disabled', false);
						});
						$('input[name="overall"]').change(function() {
							$("#overallbtn").prop('disabled', false);
						});
						
					});
					
								$(".next").click(
										function() {

											current_fs = $(this).parent();
											next_fs = $(this).parent().next();
											//Add Class Active
											$("#progressbar li").eq(
													$("fieldset")
															.index(next_fs))
													.addClass("active");
											$("#progressbar li strong").eq(
													$("fieldset")
															.index(next_fs))
													.addClass("text-dark");
											//show the next fieldset
										
											
												
											
											next_fs.show();
											//hide the current fieldset with style
											current_fs.animate({
												opacity : 0
											}, {
												step : function(now) {
													// for making fielset appear animation
													opacity = 1 - now;

													current_fs.css({
														'display' : 'none',
														'position' : 'relative'
													});
													next_fs.css({
														'opacity' : opacity
													});
												},
												duration : 500
											});
											setProgressBar(++current);
										});

								$(".previous").click(
										function() {

											current_fs = $(this).parent();
											previous_fs = $(this).parent()
													.prev();

											$("#progressbar li").eq(
													$("fieldset").index(
															current_fs))
													.removeClass("active");
											$("#progressbar li strong").eq(
													$("fieldset").index(
															current_fs))
													.removeClass("text-dark");

											previous_fs.show();

											current_fs.animate({
												opacity : 0
											}, {
												step : function(now) {
													opacity = 1 - now;
													current_fs.css({
														'display' : 'none',
														'position' : 'relative'
													});
													previous_fs.css({
														'opacity' : opacity
													});
												},
												duration : 500
											});
											setProgressBar(--current);
										});

								function setProgressBar(curStep) {
									var percent = parseFloat(100 / steps)
											* curStep;
									percent = percent.toFixed();
									$(".progress-bar").css("width",
											percent + "%")
								}

	 $("#submitbtn").click(function() {
		 
		 var captcha=$("#captcha").val();
		 if(captcha==''){
			 swal('Enter Your Captcha');
			 return false;
		 }
		 
		 postData={
				 "captcha":captcha
		 }
		 $.ajax({
				url : "./validatecaptcha.do",
				type : "POST",
				beforeSend: function(xhr) {
					$(".modalView").show();
					xhr.setRequestHeader(header, token);
				},
				data: JSON.stringify(postData),
				contentType:"application/json",
				dataType:"JSON",
				success: function(data) {
					console.log(data);
					if (data.status) {
						postData = {
							    "name" : $('#name').val(),
							    "mobile":$('#mobile').val(),
							    "requestId": $('#requestId').val(),
								"food":$("input[name='foodradio']:checked").val(),
						        "housekeeping":$("input[name='housekeeping']:checked").val(),
						        "checkincheckout":$("input[name='checkincheckout']:checked").val(),
						        "conduct":$("input[name='conduct']:checked").val(),
						        "clean":$("input[name='clean']:checked").val(),
						        "overall":$("input[name='overall']:checked").val(),
						        "feedbacksms":$('#description').val()
						}
						
						$.ajax({
							url: './savefeedbackDetails.do',
							type: "POST",
							beforeSend: function(xhr) {
								xhr.setRequestHeader(header, token);
							},
							data: JSON.stringify(postData),
							contentType:"application/json",
							dataType:"JSON",					
							success:function(result) {
								console.log(result);
								if(result.status){
									alert(result.message);
									$('#msform').trigger("reset");
									window.location="./login.html";
								}else{
									alert(result.message);
								}
								
							}
						}); 
					}else{
						swal(data.message);
					}
				},
				error:function(jqXHR, status, error){
					console.log(error);
					alert(error);
				}
			});
		 
		 
			
		
	 })						

});
			