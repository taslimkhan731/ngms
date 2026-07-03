$(document).ready(function() {
	console.log('changed');
	$('#sel').on('change', function() {
		console.log('changed2');
		//alert( this.value );
	});
	$('#go').on('click', function(){
		console.log('click');
		if ($('#sel').val()==3) {
			$('#reportIframe').attr('src','http://localhost:8082/birt/frameset?__report=sos.rptdesign&sample=my+parameter&__showtitle=false' );
		}
		else {
			$('#reportIframe').attr('src','http://localhost:8082/birt/frameset?__report=No-of-Govt.rptdesign&sample=my+parameter&__showtitle=false' );
		}
	});
});