//*************************GE Number*****************************
function VAL_GE(p_val)
{
	if(p_val==null) 
	{
		alert("GE Number is Null");
		return false;
	}
	else if(p_val.match(/^[\/ &a-zA-Z0-9]+$/))
	{ 
		if(!isLEN(p_val,7,12))
		{
			alert("Invalid Length for GE Number. Min(7) and Max(12).");
			return false;
		}
		return true;
	}
	else
	{
		alert("Invalid GE Number. Allowed Characters are a-z A-Z 0-9 / space");
		return false;
	} 
}
//*************************File ID*****************************
function VAL_FL(p_val)
{
	if(p_val==null) 
	{
		alert("File ID is Null");
		return false;
	}
	else if(p_val.match(/^[0-9]+$/))
	{ 
		if(!isLEN(p_val,5,20))
		{
			alert("Invalid Length for File ID. Min(5) and Max(20).");
			return false;
		}
		return true;
	}
	else
	{
		alert("Invalid File ID. Only Numbers are Allowed");
		return false;
	} 
}
//*************************Event ID*****************************
function VAL_EV(p_val)
{
	if(p_val==null) 
	{
		alert("Event ID is Null");
		return false;
	}
	else if(p_val.match(/^[0-9]+$/))
	{
		if(!isLEN(p_val,5,20))
		{
			alert("Invalid Length for Event ID. Min(5) and Max(20).");
			return false;
		}
		return true;
	}
	else
	{
		alert("Invalid Event ID. Only Numbers are Allowed");
		return false;
	} 
}

//*************************** OFFICE ORDER NUMBER(GO) *********************
function VAL_GO(p_val)
{
	if(p_val==null) 
	{
		nic_alert("Order Number is Null","N");
		return false;
	}
	else if(p_val.match(/^[-\/a-zA-Z0-9.]+$/))
	{ 
		if(!isLEN(p_val,3,30))
		{
			nic_alert("Invalid Length for Order Number. Min(3) and Max(30).","N");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Invalid Order Number. Allowed Characters are a-z A-Z 0-9 / .","N");
		return false;
	}  
}
//*************************** ORDER NUMBER *********************
function VAL_ORDNO(p_val)
{
	if(p_val==null) 
	{
		alert("Order Number is Null");
		return false;
	}
	else if(p_val.match(/^[-\/()a-zA-Z0-9]+$/))
	{ 
		if(!isLEN(p_val,3,20))
		{
			alert("Invalid Length for Order Number. Min(3) and Max(20).");
			return false;
		}
		return true;
	}
	else
	{
		alert("Invalid Order Number. Allowed Characters are a-z A-Z 0-9 () / ");
		return false;
	}  
}
//----------------------------------------------?-----------------------------
function VAL_ANSS(p_val)
{
	if(p_val.match(/^[= _,-.?\/ &a-zA-Z0-9]+$/))
	{ return true;}
	else
	{return false;}  
}
//*************************Alphabetic*****************************
function VAL_A(p_val)
{
	if(p_val.match(/^[a-zA-Z]+$/))
	{ return true;}
	else
	{ return false; }
}
//*************************Alphabetic with '-'*****************************
function VAL_AH(p_val)
{
	if(p_val.match(/^[0A-Z]+$/))
	{ return true;}
	else
	{ return false; }
}
/*//*************************Alpha Numeric with '_'*****************************
function VAL_ANH(p_val)
{
	if(p_val.match(/^[_\sa-zA-Z0-9]+$/))
	{ return true;}
	else
	{ return false; }
}*/
//JESWIN*************************Alpha Numeric with '-' and space'*****************************
function VAL_ANHS(p_val)
{
	if(p_val.match(/^[-\sa-zA-Z]+$/))
	{ return true;}
	else
	{ return false; }
}
//*************************Alpha Numeric**************************
function VAL_AN(p_val)
{

	if(p_val.match(/^[a-zA-Z0-9]+$/))
	{ return true;}
	else
	{return false;}  
}
//*********************Alphabetic with space**********************
function VAL_AS(p_val)
{
	if(p_val.match(/^[ a-zA-Z]+$/))
	{ return true;}
	else
	{ return false; }
} 
//*********************Alphabetic with dot**********************
function VAL_AD(p_val)
{
	if(p_val.match(/^[a-zA-Z.]+$/))
	{ return true;}
	else
	{ return false;}  
}
//********************Alphanumeric with space and comma***********
function isANSC(val)
{
	if (val.match(/[, a-zA-Z0-9]+$/))
	{
		return true;
	}
	return false;
}
//********************Alphanumeric with space***********
function isANS(val)
{
	if (val.match(/[ a-zA-Z0-9]+$/))
	{
		return true;
	}
	return false;
}
//********************************Address***********************
function isvalidaddress(addr)
{
	len=addr.length;
	for(var i=0;i<len;i++)
	{
		if(addr.charCodeAt(i)==60 || addr.charCodeAt(i)==62 || addr.charCodeAt(i)==33 || addr.charCodeAt(i)==36 || addr.charCodeAt(i)==37 || addr.charCodeAt(i)==59 || addr.charCodeAt(i)==61 || addr.charCodeAt(i)==35 || addr.charCodeAt(i)==94 || addr.charCodeAt(i)==63 || addr.charCodeAt(i)==124 || addr.charCodeAt(i)==64 || addr.charCodeAt(i)==42 || addr.charCodeAt(i)==43 || addr.charCodeAt(i)==95 || addr.charCodeAt(i)==96 || addr.charCodeAt(i)==126 || addr.charCodeAt(i)==123 || addr.charCodeAt(i)==125 || addr.charCodeAt(i)==91 || addr.charCodeAt(i)==93)
			return false;
	}
	return true;
}
//**********************ANS with bracket*************************
function VAL_ANSB(p_val)
{
	if(p_val.match(/^[()_,-.\/ &a-zA-Z0-9]+$/))
	{ return true;}
	else
	{return false;}  
}
//**********************Alphabet with '_'*************************
function VAL_AHA(p_val)
{
	if(p_val.match(/^[_\sa-zA-Z]+$/))
	{ return true;}
	else
	{ return false; }
}
//***********************Alpha Numeric Special******************
function VAL_ANS(p_val)
{
	if(p_val.match(/^[_,-.\/ &a-zA-Z0-9]+$/))
	{ return true;}
	else
	{return false;}  
}
function VAL_ANSP(p_val,p_match)
{
	var temp1='/^[';
	var temp2='&a-zA-Z0-9]+$/'; 
	var temp3=eval(temp1+p_match+temp2);
	if(p_val.match(temp3))
	{ return true;}
	else
	{return false;}  
}
//*****************************Remarks****************************
function VAL_REMARK(p_val)
{
	if(p_val.match(/^[,-.\/ ()&a-zA-Z0-9\n\r]+$/))
	{ return true;}
	else
	{return false;} 
}
function VAL_SPCE(p_val)
{
	if(p_val.match(/[ a-zA-Z]/))
	{ return true;}
	else
	{return false;} 
}
//*************************** SCALE *******************************
function VAL_SCALE(p_scale)
{
	if(p_scale.indexOf('-') == -1)
	{
		return false;
	}
	else if(!(p_scale.match(/^[-0-9]+$/))){
		return false;	
	}

	else
	{
		var scale=p_scale.split("-");
		var min=scale[0];
		var max=scale[1];
		if (!VAL_N(min))
		{
			return false;
		}
		else if(!isLEN(min,3,6)){
			return false;
		}
		if (!VAL_N(max))
		{
			return false;
		}
		else if(!isLEN(max,3,6)){
			return false;
		}
		/*if(max<1000||min<1000){
			return false;
		}*/
		if(parseInt(max)<parseInt(min)){
			return false;
		}
		return true;
	}

	return true;
}

//********************* FINANCIAL YEAR *************************

function VAL_FYEAR(p_fyear)
{
	if(p_fyear.indexOf('-') == -1)
	{
		return false;
	}
	else if(!(p_fyear.match(/^[-0-9]+$/))){
		return false;	
	}
	else
	{
		var fyear=p_fyear.split("-");
		var first=fyear[0];
		var second=fyear[1];
		if (!VAL_N(first))
		{
			return false;
		}
		else if(!isLEN(first,4,4)){
			return false;
		}
		if (!VAL_N(second))
		{
			return false;
		}
		else if(!isLEN(second,4,4)){
			return false;
		}
		if((second-first)!=1){
			return false;

		}
		// sec-firs!=1(rtrn flse)
		return true;
	}

	return true;
}


//*****************************Numeric**************************
function VAL_N(p_val)
{
	if(p_val.match(/^[0-9]+$/))
	{ return true;}
	else
	{ return false;}  
}

function VAL_N1(p_val)
{
	if(p_val.match(/^[0-9]+$/))
	{ return true;}
	else
	{ return false;}  
}

function VAL_SPL(p_val)
{
	if(p_val.match(/^[$ 0-9]+$/))
	{ return true;}
	else
	{ return false;}  
}

//*Ajeesh****************************Numeric**************************
function VAL_NC(p_val)
{
	if(p_val.match(/^[-: 0-9]+$/))
	{ return true;}
	else
	{ return false;}  
}
//**************************Numeric Decimal*********************
function VAL_ND(p_val)
{
	if(p_val.match(/^[.0-9]+$/))
	{ return true;}
	else
	{ return false;}  
}
//************************Check Equality************************
//***for radio buttons, YES/NO, MALE/FEMALE checking************
function VAL_CE(p_val,p_cval1,p_cval2)
{
	if(p_val==p_cval1 || p_val==p_cval2)
	{ return true;}
	else
	{ return false; }
}
//************************Valid Name***************************
function VAL_NAME(str)
{
	newStr = str.replace(/[a-zA-Z\. ]/ig, '');
	if (newStr == '')
		return true;
	else
		return false;
}
//*********************************  Email   *******************
function VAL_E(p_str)
{
	var testresults;
	var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if(filter.test(p_str))
	{testresults=true;}
	else{
		testresults=false;
	}
	return (testresults);
}
//*****************************Mobile***************************
/*function VAL_MOB(iNumber)
{
    var returnval = true;
	if (iNumber.length<10 || iNumber.length>15) returnval =  false;
    for (var i=0;i<iNumber.length;i++) 
	{
       var c = iNumber.charAt(i);
       if (! (c == '+' || c == '-' || isDigit(c))) 
       {
           returnval =  false;
           break;
       }
    }
    return returnval;
}*/
function VAL_MOB(iNumber)
{
	var returnval = true;
	if (iNumber.length<10 || iNumber.length>15) returnval =  false;
	else  if (! (VAL_N(iNumber)))  returnval =  false;

	return returnval;
}
//*****************************Phone***************************
function VAL_PHONE(iNumber)
{
	var returnval = true;
	if (iNumber.length<7 ||	iNumber.length>15) returnval =  false;
	for (var i=0;i<iNumber.length;i++) 
	{
		var c = iNumber.charAt(i);
		if (! (c == '+' || c == '-' || VAL_N(c))) 
		{
			returnval =  false;
			break;
		}
	}
	return returnval;
}
//*****************************Phone***************************
/*function VAL_PHONE(iNumber)
{
    var returnval = true;
	if (iNumber.length<7 ||	iNumber.length>15) returnval =  false;
    for (var i=0;i<iNumber.length;i++) 
	{
        var c = iNumber.charAt(i);
        if (! (c == '+' || c == '-' || isDigit(c))) 
		{
           returnval =  false;
           break;
        }
   }
   return returnval;
}*/
//*****************************State****************************
function VAL_ST(p_val)
{
	if(p_val.match(/^[ a-zA-Z&]+$/))
	{ return true;}
	else
	{ return false;}  
}
/////////////////////////
//**********************AN with _ *************************
function VAL_ANHYP(p_val)
{
	if(p_val.match(/^[_,A-Z0-9]+$/))
	{ return true;}
	else
	{return false;}  
}
/////////////////////////////
//***************************Check Length***********************
function isLEN(pval,pmin,pmax)
{
	lentext = escape(pval).length;
	if(lentext >= pmin && lentext <= pmax) 
		return true; 
	else return false;
}
//****************************Malayalam*************************
function isMAL(val)
{
	if (val.match(/[. &a-zA-Z0-9\.]+$/))
		return false;
	else
		return true;
}

//*****************************Valid Path***********************
function isvalidfilepath(filepath)
{
	len=filepath.length;
	for(var i=0;i<len;i++)
	{
		if(filepath.charCodeAt(i)==47 || filepath.charCodeAt(i)==42  || filepath.charCodeAt(i)==62 || filepath.charCodeAt(i)==63 || filepath.charCodeAt(i)==60 || filepath.charCodeAt(i)==34 || filepath.charCodeAt(i)==124 )
			return false;
	}
	return true;
}
//********************************Password Checking*************
function VAL_ispwdok(ppwd)
{
	minLen=6;
	minAZ=2;
	minDigits=1;
	UpperandLower=1;
	if (ppwd.length < minLen) { return false;}
	else if (minAZ && !/[a-z]/i.test(ppwd) || ppwd.match(/[A-Z]/gi).length < minAZ) { return false;}
	else if (minDigits && !/\d/.test(ppwd) || ppwd.match(/\d/g).length < minDigits ) { return false;}
	else if (UpperandLower && !/[a-z]/g.test(ppwd) || !/[A-Z]/g.test(ppwd) ) { return false;}	
	else if (!isspchok(ppwd)) { return false;}

	return true;
}
function isspchok(ppwd)
{
	var retvalue = 0;
	var spchars = "!@#$%^&*()[]{}|<>?~_"; 
	for (var i = 0; i < ppwd.length; i++) {
		if (spchars.indexOf(ppwd.charAt(i)) != -1) {
			retvalue =retvalue + 1;
		}
	}
	if(retvalue > 0) return true;
	else return false;
}
//***************************Date*******************************
function VAL_D( p_value ) {
	/************************************************
Validates a string contains only
    valid dates with 2 digit month, 2 digit day,
    4 digit year. Date separator can be( . - /)
	 *************************************************/
	var objRegExp = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/;


	if(!objRegExp.test(p_value))
		return false;
	else{
		var strSeparator = p_value.substring(2,3) ;
		var arrayDate = p_value.split(strSeparator); 
		var arrayLookup = { '01' : 31,'03' : 31, 
				'04' : 30,'05' : 31,
				'06' : 30,'07' : 31,
				'08' : 31,'09' : 30,
				'10' : 31,'11' : 30,'12' : 31};
		var intDay = parseInt(arrayDate[0],10); 

		if(arrayLookup[arrayDate[1]] != null) {
			if(intDay <= arrayLookup[arrayDate[1]] && intDay != 0)
				return true;
		}

		var intMonth = parseInt(arrayDate[1],10);
		if (intMonth == 2) { 
			var intYear = parseInt(arrayDate[2]);
			if (intDay > 0 && intDay < 29) {
				return true;
			}
			else if (intDay == 29) {
				if ((intYear % 4 == 0) && (intYear % 100 != 0) || 
						(intYear % 400 == 0)) {
					return true;
				}   
			}
		}
	}  
	return false; 
}


function VAL_D_new( p_value1 ) {

	var objRegExp = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/;
	var strSeparator = p_value1.substring(4,5) ;
	var p_value = p_value1.split(strSeparator).reverse().join(strSeparator);

	if(!objRegExp.test(p_value))
		return false;
	else{
		var strSeparator = p_value1.substring(4,5) ;
		var arrayDate = p_value.split(strSeparator); 
		var arrayLookup = { '01' : 31,'03' : 31, 
				'04' : 30,'05' : 31,
				'06' : 30,'07' : 31,
				'08' : 31,'09' : 30,
				'10' : 31,'11' : 30,'12' : 31};
		var intDay = parseInt(arrayDate[0],10); 

		if(arrayLookup[arrayDate[1]] != null) {
			if(intDay <= arrayLookup[arrayDate[1]] && intDay != 0)
				return true;
		}

		var intMonth = parseInt(arrayDate[1],10);
		if (intMonth == 2) { 
			var intYear = parseInt(arrayDate[2]);
			if (intDay > 0 && intDay < 29) {
				return true;
			}
			else if (intDay == 29) {
				if ((intYear % 4 == 0) && (intYear % 100 != 0) || 
						(intYear % 400 == 0)) {
					return true;
				}   
			}
		}
	}  
	return false; 
}

//---------------------------------------------need checking-------------------------------------------------------//
function isANSL(val)
{
	if (val.match(/[. &a-zA-Z0-9\/]+$/))
	{
		return true;
	}
	return false;
}
function isAU(val)
{
	if (val.match(/[. &a-zA-Z\_]+$/))
	{
		return true;
	}

	return false;
}
function isANU(val)
{
	if (val.match(/[. &a-zA-Z0-9\_]+$/))
	{
		return true;
	}

	return false;
}
function strim(s)
{
	return srtrim(sltrim(s));
}

function trim(s)
{
	return srtrim(sltrim(s));
}


function srtrim(s)
{
	var r=s.length -1;
	while(r > 0 && (s.charAt(r) == ' ' || ascii_value(s.charAt(r))==13 || ascii_value(s.charAt(r))==10))				  
	{	r-=1;	}
	return s.substring(0, r+1);
}


function sltrim(s)
{
	var l=0;
	while(l < s.length && (s.charAt(l) == ' ' || ascii_value(s.charAt(l))==13 || ascii_value(s.charAt(l))==10))
	{	l++; }
	return s.substring(l, s.length);
}
function isValidEmail(stremail)
{
	if (stremail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z]+)*\.[A-Za-z]+$/) == -1) 
		return false;
	else
		return true;
}
function dateComp(eventdate)
{
	var strSeparator2;
	var today = new Date();
	var  dd=today.getDate();
	var  yy=today.getFullYear();
	var mm =today.getMonth()+1;

	var to_date= dd +"-"+ mm +"-"+ yy;	


	var strSeparator = eventdate.substring(2,3); 
	var arrayDate 	 =  eventdate.split(strSeparator);
	var eventdate_day=parseInt(arrayDate[0],10);
	var eventdate_month=parseInt(arrayDate[1],10);
	var eventdate_year=parseInt(arrayDate[2],10);
	//-------------
	if(dd<10)
	{
		strSeparator2 = to_date.substring(1,2);
	}
	else
	{
		strSeparator2 = to_date.substring(2,3);	
	}
	//var strSeparator2 = to_date.substring(2,3);
	var arrayDate2 =  to_date.split(strSeparator2);
	var to_date_day=parseInt(arrayDate2[0],10);
	var to_date_month=parseInt(arrayDate2[1],10);
	var to_date_year=parseInt(arrayDate2[2],10);


	var date1=new Date(eventdate_year,eventdate_month,eventdate_day);
	var date2=new Date(to_date_year,to_date_month,to_date_day);


	if(date1 < date2)
		return false;
	else
		return true;

}
function dateCompTwo(date1,date2)
{
	
	var strSeparator  =  date1.substring(2,3); 
	var arrayDate 	  =  date1.split(strSeparator);
	var date1_day     =  parseInt(arrayDate[0],10);
	var date1_month   =  parseInt(arrayDate[1],10);
	var date1_year    =  parseInt(arrayDate[2],10);
	//-------------
	var strSeparator2 =  date2.substring(2,3); 
	var arrayDate2    =  date2.split(strSeparator2);
	var date2_day     =  parseInt(arrayDate2[0],10);
	var date2_month   =  parseInt(arrayDate2[1],10);
	var date2_year    =  parseInt(arrayDate2[2],10);

	

	var date1         =  new Date(date1_year,date1_month-1,date1_day);
	var date2         =  new Date(date2_year,date2_month-1,date2_day);
	
	if(date1 >= date2)
		return false;
	else
		return true;	
}

function datediff(p_val,from_date)
{
	tfromdate=from_date.split(/[ ,]+/);
	var splitfrom=tfromdate[0].split(/[-,]+/);
	var finfrom = splitfrom[1]+"/"+splitfrom[0]+"/"+splitfrom[2];

	ttodate=p_val.split(/[ ,]+/);
	var splitto=ttodate[0].split(/[-,]+/);
	var finto = splitto[1]+"/"+splitto[0]+"/"+splitto[2];


	finto=new Date(finto);
	finfrom=new Date(finfrom);

	var one_day=1000*60*60*24;
	var date1_ms = finto.getTime();
	var date2_ms = finfrom.getTime();
	var difference_ms = date1_ms- date2_ms ;

	var dif_date=Math.round(difference_ms/one_day);


	if(dif_date<5)
	{			
		return false;
	}
	else 
		return true;
}
function datediff_one(p_val,from_date)
{
	tfromdate=from_date.split(/[ ,]+/);
	var splitfrom=tfromdate[0].split(/[-,]+/);
	var finfrom = splitfrom[1]+"/"+splitfrom[0]+"/"+splitfrom[2];

	ttodate=p_val.split(/[ ,]+/);
	var splitto=ttodate[0].split(/[-,]+/);
	var finto = splitto[1]+"/"+splitto[0]+"/"+splitto[2];


	finto=new Date(finto);
	finfrom=new Date(finfrom);

	var one_day=1000*60*60*24;
	var date1_ms = finto.getTime();
	var date2_ms = finfrom.getTime();
	var difference_ms = date1_ms- date2_ms ;

	var dif_date=Math.round(difference_ms/one_day);


	if(dif_date<1)
	{			
		return false;
	}
	else 
		return true;
}
function datediff_purpose(p_val,from_date)
{
	tfromdate=from_date.split(/[ ,]+/);
	var splitfrom=tfromdate[0].split(/[-,]+/);
	var finfrom = splitfrom[1]+"/"+splitfrom[0]+"/"+splitfrom[2];

	ttodate=p_val.split(/[ ,]+/);
	var splitto=ttodate[0].split(/[-,]+/);
	var finto = splitto[1]+"/"+splitto[0]+"/"+splitto[2];


	finto=new Date(finto);
	finfrom=new Date(finfrom);

	var one_day=1000*60*60*24;
	var date1_ms = finto.getTime();
	var date2_ms = finfrom.getTime();
	var difference_ms = date1_ms- date2_ms ;

	var dif_date=Math.round(difference_ms/one_day);


	if(dif_date<7)
	{			
		return false;
	}
	else 
		return true;
}

function datediff_public(p_val,from_date)
{
	tfromdate=from_date.split(/[ ,]+/);
	var splitfrom=tfromdate[0].split(/[-,]+/);
	var finfrom = splitfrom[1]+"/"+splitfrom[0]+"/"+splitfrom[2];

	ttodate=p_val.split(/[ ,]+/);
	var splitto=ttodate[0].split(/[-,]+/);
	var finto = splitto[1]+"/"+splitto[0]+"/"+splitto[2];


	finto=new Date(finto);
	finfrom=new Date(finfrom);

	var one_day=1000*60*60*24;
	var date1_ms = finto.getTime();
	var date2_ms = finfrom.getTime();
	var difference_ms = date1_ms- date2_ms ;

	var dif_date=Math.round(difference_ms/one_day);
	if(dif_date<=7)
	{			
		return false;
	}
	else 
		return true;
}

function dateComp_allotment(date1,date2,date3)
{
	//var strSeparator = date1.substring(2,3); 
	var arrayDate 	 =  date1.split("-");
	var date1_day=parseInt(arrayDate[0],10);
	var date1_month=parseInt(arrayDate[1],10);
	var date1_year=parseInt(arrayDate[2],10);
	//-------------
	//var strSeparator2 = date2.substring(2,3); 
	var arrayDate2 =  date2.split("-");
	var date2_day=parseInt(arrayDate2[0],10);
	var date2_month=parseInt(arrayDate2[1],10);
	var date2_year=parseInt(arrayDate2[2],10);

	//var strSeparator3 = date2.substring(2,3); 
	var arrayDate3 =  date3.split("-");
	var date3_day=parseInt(arrayDate3[2],10);
	var date3_month=parseInt(arrayDate3[1],10);
	var date3_year=parseInt(arrayDate3[0],10);


	var date1=new Date(date1_year,date1_month-1,date1_day);
	var date2=new Date(date2_year,date2_month-1,date2_day);
	var date3=new Date(date3_year,date3_month-1,date3_day);

	if(date1 <= date3 && date2>=date3)
		return false;
	else
		return true;	
}

function cmptime(date1,date2)
{



	var arrayDate 	 =  date1.split(" ");

	var time1 = arrayDate[1].split(":");
	var startHour=parseInt(time1[0],10);
	var startMinute=parseInt(time1[1],10);

	//-------------

	var arrayDate2 =  date2.split(" ");
	var time12 = arrayDate2[1].split(":");
	var endHour=parseInt(time12[0],10);
	var endMinute=parseInt(time12[1],10);




	var startTimeObject = new Date();
	startTimeObject.setHours(startHour, startMinute,'00');

	var endTimeObject = new Date(startTimeObject);
	endTimeObject.setHours(endHour, endMinute,'00');
	if(startTimeObject > endTimeObject)
	{

		return false;
	}

	else

		return true;	

}

function dateCompLTodays(eventdate)
{
	var strSeparator2;
	var today = new Date();
	var  dd=today.getDate();
	var  yy=today.getFullYear();
	var mm =today.getMonth()+1;

	var to_date= dd +"-"+ mm +"-"+ yy;	


	var strSeparator = eventdate.substring(2,3); 
	var arrayDate 	 =  eventdate.split(strSeparator);
	var eventdate_day=parseInt(arrayDate[0],10);
	var eventdate_month=parseInt(arrayDate[1],10);
	var eventdate_year=parseInt(arrayDate[2],10);
	//-------------
	if(dd<10)
	{
		strSeparator2 = to_date.substring(1,2);
	}
	else
	{
		strSeparator2 = to_date.substring(2,3);	
	}
	//var strSeparator2 = to_date.substring(2,3);
	var arrayDate2 =  to_date.split(strSeparator2);
	var to_date_day=parseInt(arrayDate2[0],10);
	var to_date_month=parseInt(arrayDate2[1],10);
	var to_date_year=parseInt(arrayDate2[2],10);


	var date1=new Date(eventdate_year,eventdate_month,eventdate_day);
	var date2=new Date(to_date_year,to_date_month,to_date_day);


	if(date1 >= date2)
		return false;
	else
		return true;

}
function dateCompLTodays2(eventdate)
{
	var strSeparator2;
	var today = new Date();
	var  dd=today.getDate();
	var  yy=today.getFullYear();
	var mm =today.getMonth()+1;

	var to_date= dd +"-"+ mm +"-"+ yy;	


	var strSeparator = eventdate.substring(2,3); 
	var arrayDate 	 =  eventdate.split(strSeparator);
	var eventdate_day=parseInt(arrayDate[0],10);
	var eventdate_month=parseInt(arrayDate[1],10);
	var eventdate_year=parseInt(arrayDate[2],10);
	//-------------
	if(dd<10)
	{
		strSeparator2 = to_date.substring(1,2);
	}
	else
	{
		strSeparator2 = to_date.substring(2,3);	
	}
	//var strSeparator2 = to_date.substring(2,3);
	var arrayDate2 =  to_date.split(strSeparator2);
	var to_date_day=parseInt(arrayDate2[0],10);
	var to_date_month=parseInt(arrayDate2[1],10);
	var to_date_year=parseInt(arrayDate2[2],10);


	var date1=new Date(eventdate_year,eventdate_month,eventdate_day);
	var date2=new Date(to_date_year,to_date_month,to_date_day);


	if(date1 > date2)
		return false;
	else
		return true;

}

function VAL_NEG(p_val)
{
	if(p_val.match(/^[-0-9]+$/))
	{ return true;}
	else
	{ return false;}  
}

//**************************Numeric Decimal Accepts .5*********************
function VAL_ND_S(p_val)
{
	if(p_val.match(/^[0-9]+(\.[5]{1})?$/))
	{ return true;}
	else
	{ return false;}  
}

function verify(s1,s2,s3){
	var today=new Date();
	var  dd=today.getDate();
	var  yy=today.getFullYear();
	var mm =today.getMonth()+1;
	if(((dd +"-"+ mm +"-"+ yy)==(s1+"-"+s2+"-"+s3))){}
	else
	{document.write("Please check your System date");
	}		
}

function isANSD(val)
{
	if (val.match(/[@.a-zA-Z0-9]+$/))
	{
		return true;
	}
	return false;
}
//*****************************date****************************************//
function isDATE(p_val)
{
	if(p_val.match(/^[-\/0-9]+$/))
	{ 
		if(!isLEN(p_val,3,20))
		{
			alert("Invalid Length for Order Number. Min(3) and Max(20).");
			return false;
		}
		return true;
	}
	else
	{
		alert("Invalid Order Number. Allowed Characters are a-z A-Z 0-9 () / ");
		return false;
	}  
}
/////////////////////////////
function dateCompTwo1less2(date1,date2)
{
	var strSeparator = date1.substring(2,3); 
	var arrayDate 	 =  date1.split(strSeparator);
	var date1_day=parseInt(arrayDate[0],10);
	var date1_month=parseInt(arrayDate[1],10);
	var date1_year=parseInt(arrayDate[2],10);
	//-------------
	var strSeparator2 = date2.substring(2,3); 
	var arrayDate2 =  date2.split(strSeparator2);
	var date2_day=parseInt(arrayDate2[0],10);
	var date2_month=parseInt(arrayDate2[1],10);
	var date2_year=parseInt(arrayDate2[2],10);


	var date1=new Date(date1_year,date1_month-1,date1_day);
	var date2=new Date(date2_year,date2_month-1,date2_day);
	if(date1 > date2)
		return false;
	else
		return true;	
}

function VAL_DD( p_value ) {

	var objRegExp = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/;

	//check to see if in correct format
	if(!objRegExp.test(p_value))
		return false; //doesn't match pattern, bad date
	else{
		var strSeparator = p_value.substring(2,3);
		var arrayDate = p_value.split(strSeparator); 
		//create a lookup for months not equal to Feb.
		var arrayLookup = { '01' : 31,'03' : 31, 
				'04' : 30,'05' : 31,
				'06' : 30,'07' : 31,
				'08' : 31,'09' : 30,
				'10' : 31,'11' : 30,'12' : 31};
		var intDay = parseInt(arrayDate[0],10); 

		//check if month value and day value agree
		if(arrayLookup[arrayDate[1]] != null) {
			if(intDay <= arrayLookup[arrayDate[1]] && intDay != 0)
				return true; //found in lookup table, good date
		}

		//check for February (bugfix 20050322)
		//bugfix  for parseInt kevin
		//bugfix  biss year  O.Jp Voutat
		var intMonth = parseInt(arrayDate[1],10);
		if (intMonth == 2) { 
			var intYear = parseInt(arrayDate[2]);
			if (intDay > 0 && intDay < 29) {
				return true;
			}
			else if (intDay == 29) {
				if ((intYear % 4 == 0) && (intYear % 100 != 0) || 
						(intYear % 400 == 0)) {
					// year div by 4 and ((not div by 100) or div by 400) ->ok
					return true;
				}   
			}
		}
	}  
	return false; 
	//any other values, bad date
}// end of fn VAL_D

function str_to_datetime(pdate)
{

	try
	{
		tday = parseInt(pdate.substring(0,2));
		tmon = parseInt(pdate.substring(3,5));
		tyr = parseInt(pdate.substring(6,10));
		thr = parseInt(pdate.substring(11,13));
		tmi = parseInt(pdate.substring(14,16));
		var tempdatetime = new Date(tyr, tmon-1,tday, thr, tmi,0);
		return tempdatetime;
	}
	catch(err)
	{
		alert("Check Dates");
		return null;
	}

}

function isdateandtime_LTE(pdate1,pdate2)
{

	var date1= str_to_datetime(pdate1);

	var date2= str_to_datetime(pdate2);



	if(date1 <= date2)
		return false;
	else
		return true;	
}

function isdateandtime_LT(pdate1,pdate2)
{

	var date1= str_to_datetime(pdate1);

	var date2= str_to_datetime(pdate2);



	if(date1 < date2)
		return false;
	else
		return true;	
}

function isdateandtime_GT(pdate1,pdate2)
{

	var date1= str_to_datetime(pdate1);

	var date2= str_to_datetime(pdate2);



	if(date1 > date2)
		return false;
	else
		return true;	
}

function isdateandtime_GTE(pdate1,pdate2)
{
	//formmat dd-mm-yyyy hh:mm  or dd/mm/yyyy hh:mm 

	var date1= str_to_datetime(pdate1);

	var date2= str_to_datetime(pdate2);



	if(date1 >= date2)
		return false;
	else
		return true;	
}


function isdateandtime_between(pdate1,pdate2,pdate3)
{

	var date1= str_to_datetime(pdate1);

	var date2= str_to_datetime(pdate2);
	var date3= str_to_datetime(pdate3);



	if(date1 <= date3 && date1>=date2)
		return false;
	else
		return true;	
}