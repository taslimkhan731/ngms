/**
 * 
 */
$("document").ready(function(){
	
	var cityData = $('#cityUserlocssd').val().trim();
	var cityDetails = JSON.parse(cityData);
	$("#city").append('<option value="">--Select City--</option>');
	cityDetails.forEach(option => {
		$("#city").append('<option value="' + option.gh_id + '">' + option.gh_name + '</option>');
	})
	let size = Object.keys(cityDetails).length;
	if (size == 1) {
		$('#city').val(cityDetails[0].gh_id);
		setTimeout(function() {
			$('#city').trigger('change');
		}, 1000);
	}

	$("#city").change(function() {
		var id = $('#city').val();
		getGuestHouseListDtls(id);
	});

	/* get guest house details through city id  */
	var getGuestHouseListDtls = function(id) {
		$.ajax({
			url: "./admin/getGuestHouseListDetails/" + id + ".do",
			type: "GET",
			contentType: "application/json",
			beforeSend: function() {
				$('#loading').show();
			},
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				$('#loading').hide();
				$("#gHouse").html("");
				$("#gHouse").append('<option value="">---Select---</option>');
				data.forEach(option => {
					$("#gHouse").append('<option value="' + option.id + '">' + option.name + '</option>');
				})
				let size = Object.keys(data).length;
				if (size == 1) {
					$('#gHouse').val(data[0].id);
				}
				$('#gHouse').trigger('change');
			}, error: function(jqXHR, status, error) {
				swal(error);
			}
		});
	}
	
	
	
	
	$("#searchDt").datetimepicker({
            useCurrent: false,
            format: 'DD-MM-YYYY'
            // format: 'YYYY-MM-DD'
        });
    	$('#filter-popover').on('click', function () {
            event.stopPropagation();
        });
    	$('#filterSearch').click(function () {        
        	$('#filter-popover').unbind();
        	setTimeout(function(){
        		$('#filter-popover').bind('click',preventClick);
        	}, 1000);
        });
    	function preventClick() {
    	       event.stopPropagation();
    	}
	
	
var dataValues =[];
var getDashbardCount = function () {
	var city='ALL';
	var gHouse='ALL';
	if($("#searchDt").val()==""){
		var today = new Date();
		var day = today.getDate();
  		var month = today.getMonth() + 1; 
  		var year = today.getFullYear();
		if (day < 10) {
		    day = "0" + day;
		  }
		  if (month < 10) {
		    month = "0" + month;
		  }
		$("#searchDtView").val(day+"-"+month+"-"+year);
	}else{
		$("#searchDtView").val($("#searchDt").val());
	}
	if($("#city").val()!=""){
		city=$("#city").val();
	}
	if($("#gHouse").val()!=""){
		gHouse=$("#gHouse").val();
	}
		postData={
			city:city,
			gHouse:gHouse,
			date:$("#searchDt").val()
		}
        $.ajax({
            url: "./admin/getAdminDashboradCount.do",
            type: "POST",
			data: JSON.stringify(postData),
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
 				$('#loading').show();
            },
            success: function (data, textStatus, jqXHR) {
               $('#loading').hide();
			   console.log(data);
				$(".occupancy_aviable").text(data.data.totalBooking);
				$("#totalBooking").text(data.data.totalBooking);
				$("#online").text(data.data.online);
				$("#offline").text(data.data.offline);
				$("#cancelled").text(data.data.cancelled);
				$("#moderation").text(data.data.moderation);
				$("#allotment").text(data.data.alloted);
				$("#approved").text(data.data.approved);
				$("#checkIn").text(data.data.checkIn);
				$("#checkOut").text(data.data.checkOut);
				$("#excellent").text(data.data.excellent);
				$("#good").text(data.data.good);
				$("#average").text(data.data.average);
				$("#poor").text(data.data.poor);
				$("#vpoor").text(data.data.vpoor);
				$("#pos").text(data.data.posAmount);
				$("#cash").text(data.data.cash);
				$("#onlineAmount").text(data.data.onlineAmount);
				$("#total").text(data.data.totalAmount);
				if(data.data.c1==0 && data.data.c2==0 && data.data.c3==0 && data.data.c4==0){
					 //category
			   const ctx = document.getElementById('myChart');
					const conf = {
					  datasets: [{
					    label: 'Not Available',
					    data: [100],
					    backgroundColor: [
					      'rgba(97, 97, 97, 1)'
					    ],
					    hoverOffset: 10
					  }],
					  options: {
					    cutout: '50%',
					    plugins: {
					      tooltip: {
					        callbacks: {
					          label: function(context) {
					            return context.dataset.label || '';
					          }
					        }
					      }
					    }
					  }
					};
					
					const existingChart = Chart.getChart(ctx);
					if (existingChart) {
					  existingChart.destroy();
					}
					
					// Create a new chart with the specified configuration
					const myChart = new Chart(ctx, {
					  type: 'doughnut',
					  data: conf,
					  options: conf.options
					});

				}else{
				 //category
				    const ctx = document.getElementById('myChart');
				    const conf = {
				    		  datasets: [{
				    		    label:['Category 1', 'Category 2', 'Category 3', 'Category 4'],
				    		    data:[data.data.c1,data.data.c2,data.data.c3,data.data.c4],
				    		    backgroundColor: [
				    		      '#aff7f7',
				    		      '#FFBBCA',
				    		      '#EFEDC6',
				    		      '#B4F8C8'
				    		      
				    		    ],
				    		    hoverOffset: 4
				    		  }]
				    		};
				   let count1=[data.data.c1Count,data.data.c2Count,data.data.c3Count,data.data.c4Count];
				   const options = {
					    plugins: {
					        tooltip: {
					            callbacks: {
					                label: (context) => {
					                    const labelIndex = context.dataIndex;
					                    const dataset = context.dataset;
					                    const count = count1[labelIndex];
					                    return `${dataset.label[labelIndex]}: ${count}`;
					                }
					            }
					        }
					    }
					};
				   const existingChart = Chart.getChart(ctx);
					if (existingChart) {
					  existingChart.destroy();
					}
				
				// Create the new Chart
				new Chart(ctx, {
				  type: 'doughnut',
				  data: conf,
				  options: options
				});
			}
	if(data.data.p1==0 && data.data.p2==0 && data.data.p3==0 && data.data.p4==0){
	  //purpose
	  	const ctx1 = document.getElementById('myChart1');
			const dataValues1 = [100];
			const conf1 = {
			  datasets: [{
			    label: "Not Available",
			    data: dataValues1,
			    backgroundColor: [
			      'rgba(97, 97, 97, 1)'
			    ],
			    hoverOffset: 4,
			  }],
			  options: {
			    cutout: '50%',
			    plugins: {
			      tooltip: {
			        callbacks: {
			          label: function(context) {
			            return context.dataset.label || '';
			          }
			        }
			      }
			    }
			  }
			};
			
			const existingChart1 = Chart.getChart(ctx1);
			if (existingChart1) {
			  existingChart1.destroy();
			}
			
			// Create the new Chart with customized tooltips
			new Chart(ctx1, {
			  type: 'doughnut',
			  data: conf1,
			  options: conf1.options
			});

		}else{
			const ctx1 = document.getElementById('myChart1');
		    const dataValues1 = [data.data.p1,data.data.p2,data.data.p3,data.data.p4];
		    const conf1 = {
		    		  datasets: [{
		    		    label:['Official','Private','Student','Patient'],
		    		    data:dataValues1,
		    		    backgroundColor: [
		    		      '#A58CB3',
		    		      '#838BC2',
		    		      '#EAE7FA',
		    		      '#D9A1A0'
		    		    ],
		    		    hoverOffset: 4
		    		  }]
		    		};
		    		let count1=[data.data.p1Count,data.data.p2Count,data.data.p3Count,data.data.p4Count];
				   const options = {
					    plugins: {
					        tooltip: {
					            callbacks: {
					                label: (context) => {
					                    const labelIndex = context.dataIndex;
					                    const dataset = context.dataset;
					                    const count = count1[labelIndex];
					                    return `${dataset.label[labelIndex]}: ${count}`;
					                }
					            }
					        }
					    }
					};
		    		
		  			 const existingChart1 = Chart.getChart(ctx1);
						if (existingChart1) {
						  existingChart1.destroy();
						}
						
						// Create the new Chart
						new Chart(ctx1, {
						  type: 'doughnut',
						  data: conf1,
						   options: options
						});
				}
				const daybar  = [data.data.bar[0].percentage_84, data.data.bar[1].percentage_84, data.data.bar[2].percentage_84, data.data.bar[3].percentage_84, data.data.bar[4].percentage_84];
				const daybar1 = [data.data.bar[0].days, data.data.bar[1].days, data.data.bar[2].days, data.data.bar[3].days, data.data.bar[4].days];
				const daybar2 = [data.data.bar[0].count, data.data.bar[1].count, data.data.bar[2].count, data.data.bar[3].count, data.data.bar[4].count];
			/*	const chartContainer = document.getElementById('chartContainer');
				const xAxis = document.getElementById('xAxis');
				
				chartContainer.innerHTML = '';
				xAxis.innerHTML = '';
				
				for (let i = 0; i < daybar.length; i++) {
				    const bar = document.createElement('div');
				    bar.className = 'bar';
				    var hv=daybar[i]*2;
				    if(hv>=90){
				    	hv=hv-30;
				    }
				    bar.style.height =hv+ '%';
				    bar.dataset.percentage = daybar2[i]; 
				    bar.name=daybar2[i];
				    chartContainer.appendChild(bar);
				    const xTick = document.createElement('div');
				    xTick.className = 'x-tick';
				    xTick.textContent = daybar1[i];
				    xAxis.appendChild(xTick);
				
				    //circle
				  const circeLabel = document.createElement('div');
				    circeLabel.className = 'circle-label' + i;
				    bar.appendChild(circeLabel);
				    // Add hover event listener to display percentage
				      $(chartContainer).on('mouseenter', '.bar', function () {
				            showPercentageOnHover(this);
				        });
				
				        $(chartContainer).on('mouseleave', '.bar', function () {
				            hidePercentageOnHover(this);
				        });
		        }*/
		      
		       var ctxbar = document.getElementById('myBarChart').getContext('2d');
		       var backgroundColors = [];
				for (var i = 0; i < 5; i++) {
				    if (i % 2 === 0) {
				        backgroundColors.push('#0FA958');
				    } else {
				        backgroundColors.push('#71E8A9'); 
				    }
				}					  
				var data = {
					  labels: daybar1,
					  datasets: [{
					    label: '',
					    data:daybar,
					    backgroundColor: backgroundColors,
					    borderColor: '#025F01',
					    borderWidth: 0.1
					  }]
					};
					var config = {
					  type: 'bar',
					  data: data,
					  options: {
					    scales: {
					      y: {
					        display: false 
					      },
					      x: {
				                beginAtZero: true,
				                ticks: {
				                	 font: {
				                        weight: 'bold'
				                    },
				                    color: '#000000'
				                }
				            }
					    },
					    plugins: {
				            legend: {
				                display: false
				            },
					        tooltip: {
					            callbacks: {
					                 label: (context) => {
						                    const labelIndex = context.dataIndex;
						                    const dataset = context.dataset;
						                    const count = daybar2[labelIndex];
						                    return `${count}`;
						            }
					            }
					        }
				        },
					  }
					};
				 const existingChartctxbar1 = Chart.getChart(ctxbar);
					if (existingChartctxbar1) {
					  existingChartctxbar1.destroy();
					}		
				  var myChart = new Chart(ctxbar, config);
				  myChart.data.datasets[0].barPercentage = 0.5;
				  myChart.data.datasets[0].categoryPercentage = 0.4; 
				  myChart.update();  				        		
            }, error: function (jqXHR, status, error) {
                if (jqXHR.status == 400) {
                    swal(jqXHR.responseJSON.msg);
                } else if (jqXHR.status == 404) {
                    swal('Requested page not found. [404]');
                }
                console.log(jqXHR);
                /*swal(jqXHR.responseText);*/
                $('#loading').hide();
            }
        });
    }
getDashbardCount();

$("#filterSearch").on('click',function(){
	getDashbardCount();
});
$("#resetBtn").on('click',function(){
	$("#city").val("");
	$("#gHouse").val("");
	$("#searchDt").val("");
	getDashbardCount();
});
	function showPercentageOnHover(barElement) {
        const percentage = barElement.dataset.percentage;
        const tooltip = document.createElement('div');
        tooltip.className = 'tooltip';
        tooltip.textContent = percentage;
        barElement.appendChild(tooltip);
        setTimeout(() => {
        tooltip.classList.add('active');
    }, 10);
    }

    function hidePercentageOnHover(barElement) {
        const tooltip = barElement.querySelector('.tooltip');
        if (tooltip) {
            barElement.removeChild(tooltip);
        }
    }	    
});

