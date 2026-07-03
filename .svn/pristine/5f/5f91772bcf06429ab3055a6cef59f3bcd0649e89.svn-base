var results1="",pobj1;
eve1="";
if (typeof String.prototype.startsWith != 'function') {
	// see below for better implementation!
	String.prototype.startsWith = function(str) {
		return this.indexOf(str) == 0;
	};
}


var sel_rowindex;
function getguestlist_db(eve, pobj,prowindex) {
	sel_rowindex = prowindex;
	if (trim(pobj.value) == "")
		return 0;
	 var keyID = eve.keyCode ? eve.keyCode : eve.which ? eve.which : eve.charCode;
	 if (keyID == 8 && keyID ==32) {
			pobj.value = pobj.value.substr(0, pobj.value.length - 1);
		
		}
		/*alert(pobj.value == "");*/
		if (pobj.value == "")
			return 0;
		
	pobj1=pobj;
	eve1=eve;

	arr_fieldname =["task","process_id","sdata"];
	arr_fieldvalue =["abc",getprocessid(),pobj.value];
	
	ajax_post_field("autocomplete",handle_checkinlist,arr_fieldname,arr_fieldvalue);

}


function handle_checkinlist(){
	
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			
		/*alert("Results"+results); */
		var resobj = JSON.parse(results);
		getlist_from_json(pobj1, resobj,eve1);	
		}else{
			/*nic_alert(results.substr(8),"N");*/
		}
	}
}


function getlist_from_json(n, guests,eve) {


	if (n.value == "")
		return 0;
	 var keyID = eve.keyCode ? eve.keyCode : eve.which ? eve.which : eve.charCode;
	if (keyID == 8 && keyID ==32) {
		n.value = n.value.substr(0, n.value.length - 1);
		n.backspace = false;
		$('#searchstr').hide();
	}
	if (n.value == "")
		return 0;
	
	tmp = n.value;
	var resarr = [];
	if (tmp == "")
		return 0;
	 
	for ( var pi in guests) {
		 var item = guests[pi];
		tmp2 = item.name;
		 
		
		if ((tmp2.toUpperCase()).startsWith(tmp.toUpperCase())) {		
			resarr.push(item);

		}
	}
	showvalue_div(resarr);
	
	n.backspace = false;
	return 0;
}


	var selguest;
	
	function showvalue_div(pguests)
	{
		
		
		document.getElementById("searchstr").innerHTML ="";
		
		$('#searchstr').show();
		selguest = pguests;
		
		
/*		alert(pguests+"selg");*/
		
		if(pguests.length >=1)
		{
		var tempstr="<ul class='autocomplete-ul'>";
		for (var k in pguests) {
			
			var tempguest = pguests[k];
			
		/*	alert(pguests[k]+"    tempguest");*/
			tname = tempguest.name;
			tage = tempguest.age;
			tgender = tempguest.gender;
			tdesig = tempguest.designation;
			tdept = tempguest.department;
			taddress = tempguest.address;
			tempstr =tempstr.concat("<li><a onclick=showguest(").concat(k).concat(")><strong>").concat(tname).concat("</strong>-").concat(tage).concat("-").concat(tgender).concat("-").concat(tdesig).concat("-").concat(tdept).concat("-").concat(taddress).concat("</a></li>");
		}	
		tempstr=tempstr.concat("</ul>");
		document.getElementById("searchstr").innerHTML =tempstr;
		}
		
	}
	

	

	
	function showguest(pindex) {


		for ( var vindex in selguest) {
			if (vindex == pindex) {
				var tempguest = selguest[vindex];
				
			/*	alert(selguest[vindex] +" indddd");*/
				 //var $itemrow = $(this).closest('tr');
				 
				
				tname = tempguest.name;
				tage = tempguest.age;
			
				tgender = tempguest.gender;
				tdesig = tempguest.designation;
				tdept = tempguest.department;
				taddress = tempguest.address;
				
		var fname ='name'+sel_rowindex;
		var fage ='age'+sel_rowindex;
		var fgender ='gender'+sel_rowindex;
		var fdesg ='desg'+sel_rowindex;
		var fdept ='dept'+sel_rowindex;
		var faddress ='address'+sel_rowindex;
		
	//	$("#palmsForm-music_style").val(fgender);
	                
				document.getElementById(fname).value = tname;
				document.getElementById(fage).value = tage;
				document.getElementById(fgender).value = tgender;
				document.getElementById(fdesg).value = tdesig;
				document.getElementById(fdept).value = tdept;
				document.getElementById(faddress).value = taddress;
				
				//document.getElementById('gender').value = tempguest.gender;
				//document.getElementById('desg0').value = tdesig;
				//document.getElementById('dept0').value = tdept;
			//	document.getElementById('address0').value = taddress;
			}

		}
		$('#searchstr').hide();

	}
