$(document).ready(function() {
	$('#loading').hide();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");


 /********************************
  * Validation
  * Forget Password
  * @author:
  ********************************/
});

var resetForm = function(){
	$("input[name=employeeNumber]").val('');
	$("input[name=employeeEmail]").val('');
}
var showError = function(msg){
	//alert(1)
	$('#errmsg').text(msg);
	$('#errmsg').show();
}


function checkform()
{
	if($('#employeeNumber').val() != null && $('#employeeNumber').val() != '' && 
			($('#employeeEmail').val() != null && $('#employeeEmail').val() != '')){
	  $('#loading').show();
	  return true;
  }else{
	  showError('Please input valid details!');
	  return false;
  }
}