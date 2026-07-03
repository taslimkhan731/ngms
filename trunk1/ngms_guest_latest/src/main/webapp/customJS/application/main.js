function openTab(evt, tabName) {
	// Declare all variables
	var i, tabcontent, tablinks;
	var v = document.getElementById("rm")

	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	document.getElementById(tabName).style.display = "block";
	evt.currentTarget.className += " active";
}

function deleteRow(row) {
	var x = document.getElementById('guestTable').getElementsByTagName('tbody')[0];
	var len = x.rows.length;
	var i = row.parentNode.parentNode.rowIndex;
	console.log(row.parentNode.parentNode.rowIndex, len);
	if (len > 1) {
		console.log(len)
		document.getElementById('guestTable').getElementsByTagName('tbody')[0]
				.deleteRow(i - 1);
	}
	this.modifyRowId1();
}

function modifyRowId() {
	var x = document.getElementById('guestTable').getElementsByTagName('tbody')[0];
	var len = x.rows.length;
	for (var i = 0; i < len; i++) {
		var r = x.rows[i].cells[0];
		r.innerHTML = i + 1;
		console.log("Successful")
	}
}
function insRow() {
	var x = document.getElementById('guestTable').getElementsByTagName('tbody')[0];
	console.log(x);
	var new_row = x.rows[0].cloneNode(true);

	var len = x.rows.length;
	console.log(len)
	new_row.cells[0].innerHTML = len + 1;
	x.appendChild(new_row);
}

function deleteRow1(row) {
	var x = document.getElementById('guestTable1')
			.getElementsByTagName('tbody')[0];
	var len = x.rows.length;
	var i = row.parentNode.parentNode.rowIndex;
	console.log(row.parentNode.parentNode.rowIndex, len);
	if (len > 1) {
		console.log(len)
		document.getElementById('guestTable1').getElementsByTagName('tbody')[0]
				.deleteRow(i - 1);
	}
	this.modifyRowId1();
}

function modifyRowId1() {
	var x = document.getElementById('guestTable1')
			.getElementsByTagName('tbody')[0];
	var len = x.rows.length;
	for (var i = 0; i < len; i++) {
		var r = x.rows[i].cells[0];
		r.innerHTML = i + 1;
		console.log("Successful")
	}
}
function insRow1() {
	var x = document.getElementById('guestTable1')
			.getElementsByTagName('tbody')[0];
	console.log(x);
	var new_row = x.rows[0].cloneNode(true);

	var len = x.rows.length;
	console.log(len)
	new_row.cells[0].innerHTML = len + 1;
	x.appendChild(new_row);
}

var convertSerializeArrayToObject = function(data) {
	var returnObject = {};
	for (var i = 0; i < data.length; i++) {
		returnObject[data[i]['name']] = data[i]['value'];
	}
	return returnObject;
}

var convertSerializeArrayToFd = function(data) {
	var fd = new FormData();
	for (var i = 0; i < data.length; i++) {
		fd.append(data[i].name, data[i].value);
	}
	return fd;
}
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

$(document).ready(function(){
	$('#verSidebarToggler').on('click', function(){
		if($('#sidebarLayout').hasClass('extend')){
			$('#sidebarLayout').removeClass('extend');
		} else {
			$('#sidebarLayout').addClass('extend')
		}
	})
})