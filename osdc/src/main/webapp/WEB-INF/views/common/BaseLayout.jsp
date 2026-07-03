<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link href="./img/Emblem_of_India.png" rel="shortcut icon"
          title="Indian Emblem"/>
    <title>OBMS</title>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base
            href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>

    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="theme-color" content="#042048"/>
    <!-- for session timeout -->
    <meta http-equiv="refresh"
          content="<%=session.getMaxInactiveInterval()%>;url=login.html"/>

    <!-- Bootstrap -->
    <link href="./plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          media="screen">

    <link
            href="./plugins/datatables/extensions/css/dataTables.bootstrap4.min.css"
            rel="stylesheet" media="screen">
    <link
            href="./plugins/datatables/extensions/autofill/css/autoFill.bootstrap4.min.css"
            rel="stylesheet" media="screen">
    <link
            href="./plugins/datatables/extensions/buttons/css/buttons.bootstrap4.min.css"
            rel="stylesheet" media="screen">
    <!-- <link
        href="./plugins/datatables/extensions/responsive/css/responsive.bootstrap4.min.css"
        rel="stylesheet" media="screen"> -->
    <link
            href="./plugins/datatables/extensions/rowreorder/css/rowReorder.bootstrap4.min.css"
            rel="stylesheet" media="screen">

    <link href="./assets/css/main.css" rel="stylesheet" media="screen">

    <link href="./plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" media="screen">
    <link
            href="./plugins/datetimepicker/css/bootstrap-datetimepicker.min.css"
            rel="stylesheet" media="screen">
    <link rel="stylesheet" href="./assets/css/bootstrapValidator.min.css"/>

    <!-- Bootstrap Datepicker -->
    <!-- <link href="./plugins/boootstrap-datepicker/bootstrap-datepicker3.min.css" rel="stylesheet"> -->
    <!-- Sweet Alert Import -->
    <link href="./plugins/sweet-alert/sweetalert.css" rel="stylesheet">

    <!-- For jquery  -->
    <script src="./plugins/jquery/jquery.min.js"></script>
    <script src="./plugins/bootstrap/js/popper.min.js"></script>
    <script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="./plugins/datetimepicker/moment.min.js"></script>
    <script
            src="./plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <!-- <link href="./customCSS/sysadmin/view.css" rel="stylesheet"
        media="screen"> -->
    <script type="text/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
    </script>
    <script src="./plugins/websocket/sockjs.min.js"></script>
    <script src="./plugins/websocket/stomp.min.js"></script>
    <link rel="stylesheet" href="./assets/css/font.css" media="screen">

</head>
<style>
    #loading {
        width: 100%;
        height: 100%;
        top: 0px;
        left: 0px;
        position: fixed;
        display: block;
        opacity: 0.7;
        background-color: #fff;
        z-index: 1111;
        text-align: center;
    }

    #loading-image {
        position: absolute;
        top: 50%;
        left: 50%;
        z-index: 100;
    }

    .error {
        color: red;
    }

    #id-inst {
        float: right;
        color: red;
        font-size: 11px;
    }
</style>
<body class="body-wrapper">
<div id="loading" class="wrapper">
    <img id="loading-image" src="./img/ajax-loader.gif" alt="Loading..."/>
</div>
<!-- Page code starts -->
<div>
    <tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>
<div class="container-fluid">
    <div class="title-body-container">
        <tiles:insertAttribute name="body"></tiles:insertAttribute>
    </div>
</div>
<div>
    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>

<script
        src="./plugins/datatables/extensions/js/jquery.dataTables.min.js"></script>
<script
        src="./plugins/datatables/extensions/js/dataTables.bootstrap4.min.js"></script>

<script
        src="./plugins/datatables/extensions/autofill/js/dataTables.autoFill.min.js"></script>
<script
        src="./plugins/datatables/extensions/autofill/js/autoFill.bootstrap4.min.js"></script>

<script
        src="./plugins/datatables/extensions/buttons/js/dataTables.buttons.min.js"></script>
<script
        src="./plugins/datatables/extensions/buttons/js/buttons.bootstrap4.min.js"></script>
<script
        src="./plugins/datatables/extensions/buttons/js/buttons.flash.min.js"></script>
<script
        src="./plugins/datatables/extensions/buttons/js/buttons.html5.min.js"></script>
<script
        src="./plugins/datatables/extensions/buttons/js/buttons.print.min.js"></script>
<script src="./plugins/datatables/extensions/buttons/js/pdfmake.min.js"></script>
<script src="./plugins/datatables/extensions/buttons/js/vfs_fonts.js"></script>
<script src="./plugins/datatables/extensions/buttons/js/jszip.min.js"></script>

<!-- <script
    src="./plugins/datatables/extensions/responsive/js/dataTables.responsive.min.js"></script>
<script
    src="./plugins/datatables/extensions/responsive/js/responsive.bootstrap4.min.js"></script> -->

<script
        src="./plugins/datatables/extensions/rowreorder/js/dataTables.rowReorder.min.js"></script>
<script
        src="./plugins/datatables/extensions/rowreorder/js/rowReorder.bootstrap4.min.js"></script>

<!-- Jquery Validation  -->
<script src="./plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="./plugins/jquery-validation/jquery-confirm.min.js"></script>
<!-- Bootstrap Datepicker -->
<!-- <script src="./plugins/boootstrap-datepicker/bootstrap-datepicker.min.js"></script> -->
<!-- Sweet Alert  Import -->
<script src="./plugins/sweet-alert/sweetalert.min.js"></script>
<!-- Overlay Loader -->
<script src="./plugins/overlay-loader/overlay-loader.js"></script>
<!-- sha512 conversion  -->
<script src="./customJS/common/sha512.js"></script>
<script src="./customJS/application/main.js"></script>

<script src="./assets/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
    /* var config = {
     '.chosen-select'           : {},
     '.chosen-select-deselect'  : {allow_single_deselect:true},
     '.chosen-select-no-single' : {disable_search_threshold:10},
     '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
     '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
     $(selector).chosen(config[selector]);
    } */

    $(window).on("load", function () {
        $('#loading').hide();
    });
</script>
<input type="hidden" id="shiftStatus" value="">
<input type="hidden" id="shiftMsg" value="">
<input type="hidden" id="sameUser" value="">
<c:if test="${sessionScope.showNameWelcomeSession == true}">
</c:if>

<script type="text/javascript">
    let contextPath = "${pageContext.servletContext.contextPath}";
    console.log("contextPath: " + contextPath);
</script>

</body>
</html>