$(document).ready(function(){
	var isDisabled = true;
	var tableReAllocation = $('#tblReAllocationDetails').DataTable({
		 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		 "pageLength": 10,	
		 "bFilter": true,
		 "bLengthChange": false,
		 "scrollX": true,
		 "autoWidth": false,
		 "columnDefs":[
		   {"className": "dt-center", "targets": "_all"},  
         {"targets":0,"data":null,
			   "defaultContent": '<button type="button" id="deleteReAllocation" action="deleteProject" class="btn btn-danger btn-sm btn-delete" style="border-radius: 0px !important" title="Delete"><span class="glyphicon glyphicon-trash"></span></button>'},
		   {"targets":1,"data":"project_id"},
		   {"targets":2,"data":"year_desc"},
		   {"targets":3,"data":"from_school_id"},
		   {"targets":4,"data":"from_school"},
		   {"targets":5,"data":"to_school_id"},
		   {"targets":6,"data":"to_school"},   
		   {"targets":7,"data":"district_name"},
		   {"targets":8,"data":"block_name"},
		   {"targets":9,"data":"work_desc"},
      ]
	});
	
	
	/*This function will retrieve all the fin year */
	var getFinancialYear = function getFinancialYear(){
		$.ajax({
			url:'./projectmonitoring/getFinancialYear.do',
			type: "POST",
			async:false,
			beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 		},
			success:function(result){
				if(result.status == false){
					 swal(result.msg);
				}else{
					var data = result.data;
					console.log(data)
					var option = '<option value="All">--Select Financial Year (All)--</option>';
					for(var i = 0;i<data.length;i++){
						option += '<option value="'+data[i].year_id+'">'+data[i].year_desc+'</option>';
					}
					// for fin year filter
					$('#finyear').val('');
					$('#finyear').empty();
					$('#finyear').append(option);
					$("#finyear").prop("selectedIndex", 0);
				}
			}
			
		});
	}
	/*call to get all fin year details*/
	getFinancialYear();
	
	
	var DistrictDetails = function DistrictDetails() {
		$.ajax({
			 url: "./mastersetup/getDistrictDetails.do",
			 type: "POST",	
			 async:false,
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
			success: function(result)
            {
				if(result.status == false){
					 swal(result.msg);
				}else{
					var data = result.data;
					//console.log(data)
					 var option = '<option selected value="All">--Select District (All)--</option>';
					 for(var i = 0;i<data.length;i++){
							option += '<option value="'+data[i].district_id+'">'+data[i].district_name+'</option>';
						}
						$('#districtfilter').val('');
						$('#districtfilter').empty();
						$('#districtfilter').append(option);
						$("#districtfilter").prop("selectedIndex", 0);
				 }
		 },
		    failure: function () {
		        swal("Failed!");
		    }
			 
		});
	}
	DistrictDetails();
	
	var getBlockDetails = function getBlockDetails(districtId) {
		$.ajax({
			url: "./mastersetup/getBlockDetailsByDistrictID/"+districtId+".do",
			 type: "POST",	
			 async:false,
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
			},
		
			 success:function(result) {
					if(result.status == false){
						 swal(result.msg);
					}else{
						var data = result.data;
						console.log('getBlocks ==> ', data)
						 var option = '<option selected value="All">--Select Block (All)--</option>';
						 for(var i = 0;i<data.length;i++){
								option += '<option value="'+data[i].id+'">'+data[i].name+'</option>';
							}
							$('#blockfilterReAlloc').val('');
							$('#blockfilterReAlloc').empty();
							$('#blockfilterReAlloc').append(option);
							$("#blockfilterReAlloc").prop("selectedIndex", 0);
					 }
			 }
		});
	}
	
	
	//Filter for sub category
	var getSubCategoryDetails = function getSubCategoryDetails(){
		$.ajax({
			url:'./projectmonitoring/getSubCategoryDetailsForFilter.do',
			type: "POST",
			async:false,
			beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 		},
			success:function(result){
				if(result.status == false){
					 swal(result.msg);
				}else{
					var data = result.data;
					//console.log(data)
					var option = '<option selected value="All">--Select SubCategory (All)--</option>';
					for(var i = 0;i<data.length;i++){
						option += '<option value="'+data[i].work_desc_id+'">'+data[i].work_desc+'</option>';
					}

					// for sub category filter
					$('#subcategoryfilter').val('');
					$('#subcategoryfilter').empty();
					$('#subcategoryfilter').append(option);
					$("#subcategoryfilter").prop("selectedIndex", 0)
				}
			}
			
		});
	}
	getSubCategoryDetails();
	
	/*This function will retrieve all the Projects from master_project table*/
	var getReAllocatedProjectDetails = function getReAllocatedProjectDetails(){
		$('#loading').show();
		district=$('#districtfilter').val();
		if(isDisabled){
			block= 'All';
		}else{
			block= $('#blockfilterReAlloc').val();
		}
		finyear= $('#finyear').val();
		subcategory = $('#subcategoryfilter').val();
		console.log("district= "+district);
		console.log("block= "+block);
		console.log("finyear= "+finyear);
		console.log("subcategory="+subcategory);
		$.ajax({
			url:"./mastersetup/getReAllocatedProjectDetails/"+block+"/"+finyear+"/"+district+"/"+subcategory+".do",
			type: "POST",
			async:false,
			beforeSend: function(xhr) {
	                xhr.setRequestHeader(header, token);
			 		},
			success:function(result){
				if(result.status == false){
					 swal(result.msg);
				}else{
					$('#loading').hide();
					var data = result.data;
					//console.log(data)
					 console.log(result);
					 tableReAllocation.clear();
					 tableReAllocation.rows.add(result.data).draw();
					
						console.log(data);
				}
			}
			
		});
	}
	/*call to get all the project details*/
	getReAllocatedProjectDetails();
	
	//calling getReAllocatedProjectDetails on filters
	
	$('#finyear').on('change', function(){
		$("#blockfilterReAlloc").prop("selectedIndex", 0);
		$("#districtfilter").prop("selectedIndex", 0);
		$("#subcategoryfilter").prop("selectedIndex", 0);
		getReAllocatedProjectDetails();
	})	
	
	$('#districtfilter').on('change', function(){
		$("#subcategoryfilter").prop("selectedIndex", 0);
		isDisabled = false;
		$("#blockfilterReAlloc").removeAttr('disabled');
		var districtId = $('#districtfilter').val();
		getBlockDetails(districtId);
		getReAllocatedProjectDetails()
	})
	
	$('#blockfilterReAlloc').on('change', function(){
		$("#subcategoryfilter").prop("selectedIndex", 0);
		getReAllocatedProjectDetails()
	})
		
	$('#subcategoryfilter').on('change', function(){
		getReAllocatedProjectDetails()
	})
	
	//on clicking the delete button, first the released amount for that project id will be checked.
	$('#tblReAllocationDetails tbody').on( 'click', 'button[action=deleteProject]', function () {
		var data = tableReAllocation.row( $(this).parents('tr') ).data();
		var sData = {
					project_id : data.project_id,
					from_school_id : data.from_school_id
					};
		
		$.confirm({
			icon: 'glyphicon glyphicon-trash',/*'fa fa-spinner fa-spin',*/
    	    title: 'Confirm!',
    	    content: 'Deleting this record will also delete any <b style="color: #ed7567">Released Amount</b> associated with it!',
    	    draggable: true,
    	    theme: "modern",
    	    type: 'red',
    	    typeAnimated: true,
    	    buttons: {
    	        'delete': function () {
    	        	deleteReAllocationProject(sData);
    	        },
    	        cancel: function () {
    	            $.alert('Canceled!');
    	        },
    	    }
    	});
	});

	
	var deleteReAllocationProject = function(sData){
		$.ajax({
			 url: './mastersetup/deleteReleasedAmount.do',
			 type: "POST",
			 beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
	 		 },
	 		 data: JSON.stringify(sData),
	         contentType:"application/json",
	         dataType:"JSON",
			 success: function(data) {
				 swal(data.msg);
				 getReAllocatedProjectDetails();
	           },
			 error:function(data){
			 	swal(data.msg);
			 }
		});
	}
	

});