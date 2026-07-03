$(document).ready(function(){
	$.ajax({
		url:'./dashboard/metabaseUrl.do',
		type:'GET',
		async:false,
		success:function(result) {
			console.log(result);
			var url=result;
			console.log("SRC=="+url);
			$("#metabase_url").attr('src', url);
		},
		error:function(jqXHR, status, error){
			swal('error');
		}
	});
})