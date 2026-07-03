$(document).ready(function(){
	  
	
	$('#if_yes').hide();
	var a=[];
	var b=[];
	var data=[];
	var question_no = new Array(1, 2, 3,4,5,6,7,8);
	var answer= [];
	$('#yes').click(function(){
		$("#by_email_star").hide();
		$("#by_telephone_star").hide();
		$("#help_desk_star").hide();
		$("#direct_visit_star").hide();
		$("#rate").hide();
		$('#if_yes').show();}
		);
	$('#no').click(function(){
		$('#if_yes').hide();}
	);
	$('#by_email').click(function(){
//		alert("email");
		$("#rate").show();
		$("#by_email_star").show();
		$("#by_telephone_star").hide();
		$("#help_desk_star").hide();
		$("#direct_visit_star").hide();
		
		/*$("#by_telephone_star").hide();
		//$("#by_telephone_star").children().prop('disabled', true);
		//$("#by_telephone_star").parent().children('li.star').off('hover');
		$("#help_desk_star").children().prop('disabled', true);
		$("#direct_visit_star").children().prop('disabled', true);*/
	});
	$('#by_telephone').click(function(){
		$("#rate").show();
		$("#by_telephone_star").show();
		$("#by_email_star").hide();
		$("#help_desk_star").hide();
		$("#direct_visit_star").hide();
	});
	$('#help_desk').click(function(){
		$("#rate").show();
		$("#help_desk_star").show();
		$("#by_email_star").hide();
		$("#by_telephone_star").hide();
		$("#direct_visit_star").hide();
	});
	$('#direct_visit').click(function(){
		$("#rate").show();
		$("#direct_visit_star").show();
		$("#by_email_star").hide();
		$("#by_telephone_star").hide();
		$("#help_desk_star").hide();
	});
	  /* 1. Visualizing things on Hover - See next part for action on click */
	  $('#stars li').on('mouseover', function(){
	    var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
	   
	    // Now highlight all the stars that's not after the current hovered star
	    $(this).parent().children('li.star').each(function(e){
	      if (e < onStar) {
	        $(this).addClass('hover');
	      }
	      else {
	        $(this).removeClass('hover');
	      }
	    });
	    
	  }).on('mouseout', function(){
	    $(this).parent().children('li.star').each(function(e){
	      $(this).removeClass('hover');
	    });
	  });
	  
	  
	  /* 2. Action to perform on click */
	  $('#stars li').on('click', function(){
	    var onStar = parseInt($(this).data('value'), 10); // The star currently selected
	    var stars = $(this).parent().children('li.star');
	    
	    for (i = 0; i < stars.length; i++) {
	      $(stars[i]).removeClass('selected');
	    }
	    
	    for (i = 0; i < onStar; i++) {
	      $(stars[i]).addClass('selected');
	    }
	    
	    // JUST RESPONSE (Not needed)
	    var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
	    var msg = "";
	    if (ratingValue > 1) {
	        msg = "Thanks! You rated this " + ratingValue + " stars.";
	    }
	    else {
	        msg = "We will improve ourselves. You rated this " + ratingValue + " stars.";
	    }
	    responseMessage(msg);
	    //a.push(ratingValue);
	    //console.log(ratingValue);
	    
	  });
	  
	  function objectifyForm(formArray) {//serialize data function
		  console.log(formArray);
		   var returnArray = {};
		   for (var i = 0; i < formArray.length; i++){
		     returnArray[formArray[i]['name']] = formArray[i]['value'];
		   }
		   return returnArray;
		 }
	  
	  
	  $("#submit").on('click', function(){
		  //console.log($(a));
		 //console.log($('#q1 li.selected').last().data('value'));
		 //alert("button clicked");
		 	qu1={}
		 	qu1 ["name"] = "1";
		 	qu1 ["value"] = $('#q1 li.selected').last().data('value');
		 	b.push(qu1);
		 	qu2={}
		 	qu2 ["name"] = "2";
		 	qu2 ["value"] =$('input[name=optradio]:checked').val();
		 	b.push(qu2);
		 	qu3={}
		 	qu3 ["name"] = "3";
		 	qu3 ["value"] =$('#q3 li.selected').last().data('value');
		 	b.push(qu3);
		 	qu4={}
		 	qu4 ["name"] = "4";
		 	qu4 ["value"] =$('input[name=radiobutton]:checked').val();
		 	b.push(qu4);
		 	qu5={}
		 	qu5 ["name"] = "5";
		 	qu5 ["value"] = $('#q5 li.selected').last().data('value');
		 	b.push(qu5);
		 	qu6={}
		 	qu6 ["name"] = "6";
		 	qu6 ["value"] =$('input[name=radiobtn]:checked').val();
		 	b.push(qu6);
		 	qu7={}
		 	qu7 ["name"] = "7";
		 	qu7 ["value"] =$('#q7 li.selected').last().data('value');
		 	b.push(qu7);
		 	console.log(b);
		 	answer.push($('#q1 li.selected').last().data('value'));
		 	answer.push($('input[name=optradio]:checked').val());
		 	answer.push($('#q3 li.selected').last().data('value'));
		 	answer.push($('input[name=radiobutton]:checked').val());
		 	answer.push($('#q5 li.selected').last().data('value'));
		 	answer.push($('input[name=radiobtn]:checked').val());
		 	answer.push($('#q7 li.selected').last().data('value'));
		 	answer.push($('#suggestions').val());
		 	//console.log(answer);
		 	s={}
		 	s ["name"] ="qus_type";
		 	s["value"] = question_no;
		 	
		 	var postData = $("#formUserDetails").serializeArray();
			postData.push(s);
			x={}
		 	x["name"] ="answer";
		 	x["value"] = answer;
			postData.push(x);
			console.log(postData);
			var surveyData = JSON.stringify(objectifyForm(postData));
			/*data.push(postData);
			data.push(b);
			console.log(data);
			var surveyData = JSON.stringify(objectifyForm(data));
			console.log(surveyData);*/
			$.ajax({
				 url: "./setting/saveEmployeeDetails.do",
				 type: "POST",
	             contentType:"application/json",
	             dataType:"JSON",
	             data: surveyData,
	             beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success: function(data, textStatus, jqXHR) {
		                if (data.success) {
		                	// 	resetForm_CreateLocation();
		                	alert(data.msg);
			              }
		            },
				 error:function(jqXHR, status, error){
				 	alert(error);
				 	//resetForm_CreateLocation();
				 }
			});
					//alert("end");
		  });
	  
	});