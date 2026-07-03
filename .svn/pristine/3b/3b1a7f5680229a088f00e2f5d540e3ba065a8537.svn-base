$(document).ready(function(){
   // swal("44444")
	 isEdit = false;
  var table = $("#UserParameterDetails").DataTable({   	
		 "paging":  true,
		 "lengthMenu": [[10,20, 30, -1], [10,20, 30, "All"]],
		 "processing": false,
	     "ordering": false,
	     "info": false,
	     "bFilter": false,
	     "scrollX": true,
	     "scrollCollapse": true,
	     "columnDefs":[
	             {"className": "dt-center", "targets": "_all"},
			     {"targets":0,"data":"param_id"},
			     {"targets":1,"data":"param_name"},
			     {"targets":2,"data":"tbl_name"},  
			     {"targets":3,"data": null, 
			     "defaultContent": '<button type="button" action="Delete" id="Delete" class="btn btn-secondary btn-sm btn-delete"><i class="fa fa-trash"></i></button>'+
			     '<button type="button" id="Edit" action="Edit" class="btn btn-secondary ml-1 mr-1 btn-sm btn-edit"><i class="fa fa-edit"></i></button>'}             
                     
			      ],
			   		
	});
  getParameterTableData(table);
  
  /* *******************************************
	 *    Form Data to the Create Parameter table
	 ***********************************************/
	   
   $('#add_parameter_button').click(function(){
		//swal("Add button click.....................");
		var type = "";
		var param_name = $("#parameter_name").val();
		var tbl_name= $("#table_name").val();
		var id= $("#parameter_id").val();
		if(isEdit){
			type = "UPDATE"
		}else{
			type = "ADD"
		}
		//swal(type);
		if(param_name==''){
			swal("Please Enter Parameter Name!");
			$("#parameter_name").focus();
		}
		else if(tbl_name==''){
			swal("Please Enter tbl_name!");
			$("#table_name").focus();
		}else{
			
			swal(param_name);
			
			swal(tbl_name);
			
			console.log(id);
			var Data = {
					param_name:param_name,tbl_name:tbl_name,type:type,param_id:id	
			};
			console.log(Data);
			$.ajax({
				 url: "./setting/saveUserParameterTable.do",
				 type: "POST",
				 contentType : 'application/json',
	       		 dataType: "json",
	       		 data: JSON.stringify(Data),
	       		 beforeSend: function(xhr) {
		               xhr.setRequestHeader(header, token);
	  			},
				 success:function(result) {
					 if(result.success){
						 swal(result.msg,"","success");
			             getParameterTableData(table);
			             resetFormCreateParameter();
					 }else{
						 swal(result.msg,"","error");
			             getParameterTableData(table);	
			             resetFormCreateParameter();
					 }
				 },
				 error:function(jqXHR, status, error){
				 	swal("error");
				 }
			});
		}
   }) ; 
   
   /*********************************************
    * Edit button click function
    *********************************************/
   $('#UserParameterDetails tbody').on( 'click', 'button[action=Edit]', function () {
  	 //swal('hii')
           var data = table.row( $(this).parents('tr') ).data();
           isEdit = true;
           $("#parameter_name").val(data.param_name); 
           $("#table_name").val(data.tbl_name);
           $("#parameter_id").val(data.param_id);
           $('#add_parameter_button').html('Update');
   });   
   
   /*********************************************
    * Delete button click function
    *********************************************/
   
   $('#UserParameterDetails tbody').on( 'click', 'button[action=Delete]', function () {
       var data = table.row( $(this).parents('tr') ).data();
       var Data = {
    		   param_id: data.param_id
       }
       swal('hii')
       $.ajax({
			 url: "./setting/deleteCreateParameter.do",
			 type: "POST",		
			 contentType : 'application/json',
	   		 dataType: "json",
	   		 data: JSON.stringify(Data),
	   		 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			 success:function(result) {
				 if(result.success){
					 getParameterTableData(table);
					swal(result.msg,"","success");
				 }else{
				 	swal(result.msg,"","error");	
				 }
			 },
			 error:function(jqXHR, status, error){
					swal("error");
			 }
		});
   });
})
		 		    
      function getParameterTableData(table){
 		//swal('hii')
 		$.ajax({
 			 url: "./setting/getParameterTableData.do",
 			 type: "POST",	
 			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
 			 success:function(result) {
 				 table.clear().draw();
 				 table.rows.add(result.data).draw();
 			 }
 		});
 	}
     
var resetFormCreateParameter = function(){
	$('#add_parameter_button').html('Add');
	isEdit = false;
	$("#parameter_name").val(''); 
	$("#table_name").val('');
	$("#parameter_id").val('');
}