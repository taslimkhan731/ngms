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

var convertSerializeArrayToObject = function (data) {
    var returnObject = {};
    for (var i = 0; i < data.length; i++) {
        returnObject[data[i]['name']] = data[i]['value'];
    }
    return returnObject;
}

var convertSerializeArrayToFd = function (data) {
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


let checkForShift = (cityId, blockId, page) => {
    if(undefined === page) {
        page = "";
    }
    $.ajax({
        url: "./admin/checkForShift.do",
        type: "GET",
        data: {
            cityId: cityId,
            blockId: blockId,
            page: page
        },
        success: function (data) {
            console.log(data);
            if (data.status) {
                $('#shiftStatus').val(true);
            } else {
                $('#shiftStatus').val(false);
                $('#shiftMsg').val(data.message);
                $('#sameUser').val(data.sameUser);
            }
        },error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                $('#loading').hide();
            }
    });
}

var a = ['', 'One ', 'Two ', 'Three ', 'Four ', 'Five ', 'Six ', 'Seven ', 'Eight ', 'Nine ', 'Ten ', 'Eleven ', 'Twelve ', 'Thirteen ', 'Fourteen ', 'Fifteen ', 'Sixteen ', 'Seventeen ', 'Eighteen ', 'Nineteen '];
var b = ['', '', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];

let inWords = (num) => {
    if ((num = num.toString()).length > 9) return 'overflow';
    let n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return;
    var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'Crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'Lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'Thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'Hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'Only ' :
        (str != '') ? ' Only' : 'Zero';
    return str;
}

$(document).ready(function () {
    $('#verSidebarToggler').on('click', function () {
        if ($('#sidebarLayout').hasClass('extend')) {
            $('#sidebarLayout').removeClass('extend');
        } else {
            $('#sidebarLayout').addClass('extend')
        }
    })
});

let stompClient = null;
let wsOrderId = null;
let wsBlockId = null;

function connect() {
    var socket = new SockJS(contextPath + '/ws/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/paymentStatus', function(data){
            console.log("Connected");
            $('#loading').hide();
            let message = JSON.parse(data.body);
            if (message.status == "success") {
                $('#online').prop('disabled', true);
                $('#payOnline').prop('disabled', true);
                $('#onlineAmount').prop('readonly', true);
                $('.paymentClose').prop("hidden", true);
                $('#submitReceipt').prop('disabled', false);
                $('#showStatus').html("<i class='fa fa-check'></i> Payment Successful");
                $('#showStatus').css("color", "green")
            } else if (message.status == "pending") {
                $('#showStatus').html("<i class='fa fa-hourglass'></i> Payment Pending. Check Status");
                $('#showStatus').css("color", "orange");
                wsOrderId = message.orderId;
                wsBlockId = message.blockId;
                $('#checkStatus').prop('hidden', false);
                $('#payOnline').prop('disabled', true);
                $('.paymentClose').prop("hidden", true);
            } else {
                $('#showStatus').html("<i class='fa fa-times'></i> Payment Failed");
                $('#showStatus').css("color", "red");
            }
            console.log(data)
            console.log(message);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}

$('#checkStatus').on('click', function () {
    $.ajax({
        type: "GET",
        url: "./admin/checkPaymentStatus/" +  wsOrderId + "/" + wsBlockId + ".do",
        beforeSend: function (xhr) {
            $('#loading').show();
        }, success: function (data) {
            $('#loading').hide();
            if (data == "success") {
                $('#online').prop('disabled', true);
                $('#payOnline').prop('disabled', true);
                $('#onlineAmount').prop('readonly', true);
                $('#checkStatus').prop('hidden', true);
                $('#showStatus').html("<i class='fa fa-check'></i> Payment Successful");
                $('#showStatus').css("color", "green")
                $('.paymentClose').prop("hidden", true);
                $('#submitReceipt').prop('disabled', false);
            } else if (data == "pending") {
                $('#checkStatus').prop('hidden', false);
                $('#payOnline').prop('disabled', true);
                $('#showStatus').html("<i class='fa fa-hourglass'></i> Payment Pending. Check Status");
                $('#showStatus').css("color", "orange");
                $('#submitReceipt').prop('disabled', true);
                $('.paymentClose').prop("hidden", true);
            } else {
                $('#checkStatus').prop('hidden', true);
                $('#payOnline').prop('disabled', false);
                $('#showStatus').html("<i class='fa fa-times'></i> Payment Failed");
                $('#showStatus').css("color", "red");
                $('#submitReceipt').prop('disabled', true);
                $('.paymentClose').prop("hidden", false);
            }
        }, error: function () {
            $('#loading').hide();
            swal("Something went wrong");
        }
    });
});
