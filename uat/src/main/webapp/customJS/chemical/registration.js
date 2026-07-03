
	
	$(document).ready(function() {
		$.validator.addMethod("usernameRegex", function(value, element) {
			return this.optional(element) || /^[a-zA-Z0-9]*$/i.test(value);
		}, "Username must contain only letters, numbers");
		
		$.validator.addMethod("ngoNameRegEx", function(value, element) {
			return this.optional(element) ||  /^[a-zA-Z\s]+$/.test(value);
		}, "The  name can only consist of alphabetical and space");
		
		$.validator.addMethod("mobNoRegEx", function(value, element) {
			return this.optional(element) ||  /^[1-9]{1}[0-9]{9}$/.test(value);
		}, "please Enter a Valid MobNo.");
		
		
		$.validator.addMethod("emailRegEx", function(value, element) {
			return this.optional(element) ||  /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/.test(value);
		}, "please Enter a Valid Email");
		
		var form = $("#example-form");
		form.validate({
		    errorPlacement: function errorPlacement(error, element) { element.before(error); },
		    rules: {
		    	txt_input_uid:{
		    		required: true,
					minlength: 6,
					usernameRegex: true,
		    	},
		    	
		    	txt_input_pwd : {
		    		required: true,
		    	},
		    	txt_input_confm_pws: {
		    		required: true,
		            equalTo: "#txt_input_pwd"
		        },
		    	txt_input_ngo_name: {
                    ngoNameRegEx: true,
                    required: true,
                },
                txt_input_director_name: {
                	ngoNameRegEx: true,
                    required: true,
                },
                txt_input_secretary_name: {
                	ngoNameRegEx: true,
                    required: true,
                },
                txt_input_treasurer_name: {
                	ngoNameRegEx: true,
                    required: true,
                },
                txt_input_authorised_person:{
                	ngoNameRegEx: true,
                    required: true,
                },
		    	
                txt_regd_date: {
                	 date: {
                         format: 'YYYY/MM/DD',
                         message: 'The birthday is not valid'
                     }
                },
                txt_input_regd_city: {
                	ngoNameRegEx: true,
                    required: true,
                },
                dpdn_registered_with: {
                	required: true,
                },
                dpdn_ngo_type:{
                	required: true,
                },
                txt_input_authorised_email: {
                	emailRegEx:true,
                	required:true
                },
                txt_input_city: {
                	ngoNameRegEx: true,
                    required: true,
                },
                txt_input_district: {
                	ngoNameRegEx: true,
                    required: true,
                },
                txt_input_state: {
                	ngoNameRegEx: true,
                    required: true,
                },
                txt_input_mobNo: {
                	mobNoRegEx:true,
                	required:true
                },
                image_captcha : {
		    		required: true,
		    	},
		    	input_image_captcha: {
		    		required: true,
		            equalTo: "#image_captcha"
		        },
		    }
		});
		form.children("div").steps({
		    headerTag: "h3",
		    bodyTag: "section",
		    transitionEffect: "slideLeft",
		    onStepChanging: function (event, currentIndex, newIndex)
		    {
		        form.validate().settings.ignore = ":disabled,:hidden";
		        return form.valid();
		    },
		    onFinishing: function (event, currentIndex)
		    {
		        form.validate().settings.ignore = ":disabled";
		        return form.valid();
		    },
		    onFinished: function (event, currentIndex)
		    {
		        alert("Submitted!");
		       // var formdata = new FormData($(this)[0]);
		        console.log(form);
		    }
		});
		
		//in registration page verify button click function event
		$("#btn_verify_nitiAayogId").click(function() {
			var nitiAayogId = $("#txt_input_nitiaayog_unique_id").val();
			validateNitiAayogId(nitiAayogId);//function that will check whether  the NitiAayog Id is valid or not
		})
		
		function validateNitiAayogId(nitiAayogId) {
			$.ajax({
				url : './validateNitiAayogId.do',
				type : 'POST',
				data:{nitiAayogId:nitiAayogId},
				success : function(result) {
					
				}
			})
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	});
	
	
	
