package common;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class validation{
	
/**********************Alphabet/AlphaNumeric/AlphaNumericSpecial**************************/	
	public boolean cAlphabet(String p_value) {
		 return p_value.matches("[a-zA-Z]*");
	}
	public boolean cAlphabetbkt(String p_value) {
		 return p_value.matches("[a-z ()A-Z]*");
	}
	
	public boolean cAlphaNumeric(String p_value){
	   	 return p_value.matches("[a-zA-Z0-9]*");
	}
	
	public boolean cAlphaSpecial(String p_value, String p_allowedSpl){
		 String rg="[a-zA-Z"+p_allowedSpl+"]*";
		 if (p_value.matches(rg)){
			 return true;
		  }
	  	else
	  		return false;
	}
	
	public boolean cAlphaNumericSpecial(String p_value, String p_allowedSpl){
		 String rg="[,'-._/ ()&a-zA-Z0-9\n\r]*";
		 if (p_value.matches(rg)){
			 return true;
		  }
	  	else
	  		return false;
	}
	
	public boolean cRemark(String p_value){
		 String rg="[,'-./ ()&a-zA-Z0-9\n\r]*";
		 if (p_value.matches(rg)){
			 return true;
		  }
	  	else
	  		return false;
	}
	//newStr = str.replace(/[a-zA-Z\. ]/ig, '');
	public boolean cNAME(String p_value){
		 String rg="[./ a-zA-Z]*";
		 if (p_value.matches(rg)){
			 return true;
		  }
	  	else
	  		return false; 
	}
	public boolean cOrdno(String p_value){
		 String rg="[,-./ ()&a-zA-Z0-9]*";
		 if (p_value.matches(rg)){
			 return true;
		  }
	  	else
	  		return false;
	}
	
/********************************** FINANCIAL YEAR **************************************/
	public boolean cFYEAR(String p_value){
		String min="";
		String max="";
		StringTokenizer arr_min = new StringTokenizer(p_value, "-");
		while (arr_min.hasMoreTokens()) {
			min = arr_min.nextToken();
			max=arr_min.nextToken();
		}
		if(!cLength(min, 4,4)||!cLength(max, 4, 4)){
			return false;
		}
		else
		{
			return min.matches("[0-9]+")&& max.matches("[0-9]+");
		}
	}	
/********************************** SCALE ***********************************************/
	public boolean cScale(String p_value){
		String min="";
		String max="";
		StringTokenizer arr_min = new StringTokenizer(p_value, "-");
		while (arr_min.hasMoreTokens()) {
			min = arr_min.nextToken();
			max=arr_min.nextToken();
		}
		if(!cLength(min, 4,6)||!cLength(max, 4, 6)){
			return false;
		}
		else
		{
			return min.matches("[0-9]+")&& max.matches("[0-9]+");
		}
	}
/******************************Y/N*******************************************************/
	public boolean cYN(String p_value)
	{
		if((p_value.equals("Y")) || (p_value.equals("N")))
		{
			return true;
		}
		else
			return false;
	}
	public boolean cpay( int pay, int p_min, int p_max){ 
	     if(pay >=p_min && pay<=p_max){
	 	    return true;
	   	 }
 	     else{
 	    	return false;
 	     }
	} 
/*****************************Length*****************************************************/
	public boolean cLength( String p_value, int p_min, int p_max){ 
		boolean status=false;
	     if(p_value.length()>=p_min&&p_value.length()<=p_max){
	    	 
	 	   // return true;
	 	    status=true;
	   	 }
   	     else{
   	    	//return false;
   	    	status=false;
   	     }
	     System.out.println("statusstatusstatusstatus "+status+" age "+p_value);
	     return status;
	} 
	public boolean cLength1( int p_value, int p_min, int p_max){ 
		boolean status=false;
	     if(p_value>=p_min&&p_value<=p_max){
	    	 
	 	   // return true;
	 	    status=true;
	   	 }
   	     else{
   	    	//return false;
   	    	status=false;
   	     }
	     System.out.println("statusstatus2323"+status+" age "+p_value);
	     return status;
	} 
	
/***************************Numeric/Integer/Float****************************************/
	public boolean cDecimal( String p_value){
		 return p_value.matches("[0-9]*[.]?[0-9]+$");
	}  

	public boolean cNumeric(String p_value){
		 return p_value.matches("[0-9 ]*");
	}

	public boolean cFloat(String p_value){
		 return p_value.matches("[0-9]*[.][0-9]+$");
	}
	
/***************************Password//ConfirmPassword*************************************/	
    public boolean cPassword(String p_value){
	 	 return p_value.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%-]).{6,20})");
   	}

    public boolean cPasswordConfirm(String p_value,String p_pswd) {
	  	 return p_value.matches(p_pswd);
	}
    
 /*********************************Email//TextArea******************************************/  
	public boolean cEmail(String p_value){
 		return p_value.matches("[a-zA-Z_.!@#$%^&*~0-9]*[@][a-z]+[.][a-z.]{2,6}+");
   }

	public boolean cText( String p_value){
		 return (p_value.matches("[a-zA-Z!@#$%^&*()_]*" ));
	}
	
/**************************MobileNumber//LandPhone//PinCode******************************/
    /*public boolean cPhoneMobile(String p_value, int p_length){
		if(p_value.length()==p_length){
			return p_value.matches("[987][0-9]*");
		}
		else{
			return false;
		}
	}*/
    public boolean cPhoneMobile(String p_value){
    	if (!(p_value.length()<10 || p_value.length()>15)){
			//return p_value.matches("[987][0-9]*");
    		return p_value.matches("[0-9]*");
		}
		else{
			return false;
		}
	}

    public boolean cPhoneLand(String p_value){
         return p_value.matches("\\d{4}-\\d{7}");
	 }

	public boolean cPin(String p_value){
		if (p_value.length()==6) {	
			return p_value.matches("[1-9][0-9]*");
		 }
		else{
			return false;
		}
	}

	public boolean db_datecheck(String date)
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");  
		 Date testDate = null;

	    try
	    {
	      testDate = (Date) sdf.parse(date);
	    }
	    catch (Exception e)
	    {
	      return false;
	    }


	    if (!sdf.format(testDate).equals(date)) 
	    {
	      return false;
	    }

	    return true;
	}
	
	public boolean db_datecheck2(String pdate)
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy aa");
		 Date date=null;
		 
	    try
	    {
	      date =(Date) sdf.parse(pdate);
	    }
	    catch (Exception e)
	    {
	      return false;
	    }
		 
	    if (!sdf.format(date).equals(pdate)) 
	    {
	      return false;
	    }

	    return true;
	}
	
/*************************************Date*************************************************/
 // p_date1  p_comp p_date2
 	public boolean cDateComp(String p_date1,String p_date2,String p_comp) throws Exception { 
	        	String t4="";
				String t5="";
				String t6="";
				String t10="";
				String t11="";
				String t12="";
				String l_date1=p_date1.replaceAll("[/.]", "-");
				String l_date2=p_date2.replaceAll("[/.]", "-");
				StringTokenizer arr_date1 = new StringTokenizer(l_date1, "-");
				while (arr_date1.hasMoreTokens()) {
					t4= arr_date1.nextToken();
					t5 = arr_date1.nextToken();
					t6 = arr_date1.nextToken();
				}
				int t1=Integer.parseInt( t4 );
				int t2=Integer.parseInt( t5 );
				int t3=Integer.parseInt( t6 );
				t2=t2-1;
				StringTokenizer arr_date2 = new StringTokenizer(l_date2, "-");
				while (arr_date2.hasMoreTokens()) {
					t10= arr_date2.nextToken();
					t11 = arr_date2.nextToken();
					t12= arr_date2.nextToken();
				}
				int t7=Integer.parseInt( t10 );
				int t8=Integer.parseInt( t11 );
				int t9=Integer.parseInt( t12 );
				t8=t8-1;						
					    GregorianCalendar ins_gcal = new GregorianCalendar(t3,t2,t1);
					    ins_gcal.setLenient(false);
			       		GregorianCalendar ins_gcal1 = new GregorianCalendar(t9,t8,t7);
			       		ins_gcal1.setLenient(false);
			    		if(p_comp==">") {		
				       		if(ins_gcal.compareTo(ins_gcal1)>0)
				    			return true;
				       		else
				       			return false;
			    		}
			    		else if(p_comp==">="){
			    			if(ins_gcal.compareTo(ins_gcal1)>=0)
			    				return true;
				       		else
				       			return false;
			    		}
			    		else if(p_comp=="<"){
			    			if(ins_gcal.compareTo(ins_gcal1)<0)
			    				return true;
				       		else
				       			return false;
			    		}
			    		else if(p_comp=="<="){
			    			if(ins_gcal.compareTo(ins_gcal1)<=0)
			    				return true;
				       		else
				       			return false;
			    		}
			    		else if(p_comp=="=="){
			    			if(ins_gcal.compareTo(ins_gcal1)==0)
			    				return true;
				       		else
				       			return false; 
			    		}
			       		else 
			       			return true;
 	}
 	
 	public boolean cDate(String p_value,String p_format){
 		try {
 				String f1="dmy";
 				String f2="mdy";
 				String f3="ymd";
 				String t4="";
 				String t5="";
 				String t6="";
 				String t_date=p_value.replaceAll("[/.]", "-");
 				StringTokenizer arr_date = new StringTokenizer(t_date, "-");
  				while (arr_date.hasMoreTokens()) {
 					t4= arr_date.nextToken();
 					t5 = arr_date.nextToken();
 					t6 = arr_date.nextToken();
 				}	
 				 GregorianCalendar ins_gcal=new GregorianCalendar();
 				ins_gcal.clear();
 				ins_gcal.setLenient(false);
 						int t1=Integer.parseInt( t4 );
 						int t2=Integer.parseInt( t5 );
 						int t3=Integer.parseInt( t6 );
 						if (p_format.equals(f1)){
 							t2=t2-1;										
 						try{
 								GregorianCalendar ins_gcal1 = new GregorianCalendar(t3,t2,t1);
 								ins_gcal1.setLenient(false);
 			       		     }
 						catch (Exception exp){
 							return false;
 						}
 						return true;
 						}
 						else if (p_format.equals(f2)){
 							t1=t1-1;
 							try{
 							GregorianCalendar ins_gcal2 = new GregorianCalendar(t3,t1,t2);
 							ins_gcal2.setLenient(false);
 								}
 							catch (Exception exp){				
 								return false;
 							}
 							return true;
  						}
 						else if (p_format.equals(f3)){						
 							t2=t2-1;
 							try{
 							GregorianCalendar ins_gcal3 = new GregorianCalendar(t1,t2,t3);
 							ins_gcal3.setLenient(false);
 							}
 							catch (Exception exp){
 								return false;
 							}
 							return true;
 						}
 				        else
 				        	return false;
 					}
 				catch(Exception exp){
 					return false;
 				}
 	}
 	public boolean cHashPassword(String p_value)
    {
    	return p_value.matches("((?=.*\\d)(?=.*[a-z]).{6,64})");
   	}

 	public boolean cDateTimeDB(String p_value,String p_format) {
		try {
			//R_24-88-2012//StringTokenizer arr_dateTime = new StringTokenizer(p_value, " ");
			StringTokenizer arr_dateTime = new StringTokenizer(p_value, ":");
			String l_date="";
			String l_time="";
			while (arr_dateTime.hasMoreTokens()) {
				l_date = arr_dateTime.nextToken();
				l_time=arr_dateTime.nextToken();
			}
				String f1="dmy";
				String f2="mdy";
				String f3="ymd";
				String t4="";
				String t5="";
				String t6="";
				String t_date=l_date.replaceAll("[/.]", "-");
				StringTokenizer arr_date = new StringTokenizer(t_date, "-");
				while (arr_date.hasMoreTokens()) {
					t4= arr_date.nextToken();
					t5 = arr_date.nextToken();
					t6 = arr_date.nextToken();
				}
					    GregorianCalendar ins_gcal=new GregorianCalendar();
					    ins_gcal.clear();
						ins_gcal.setLenient(false);
						int t1=Integer.parseInt( t4 );
						int t2=Integer.parseInt( t5 );
						int t3=Integer.parseInt( t6 );
						if (p_format.equals(f1)){
						t2=t2-1;
						try{
						GregorianCalendar ins_gcal2 = new GregorianCalendar(t3,t2,t1);
			       		ins_gcal2.setLenient(false);
						}
						catch (Exception exp){
							return false;
						}
						return l_time.matches("(1[012]|0?[1-9])(\\s)?(?i)(AM|PM)");
						//return l_time.matches("(AM|PM)");
						}
						else if (p_format.equals(f2)){
							t1=t1-1;
							try{
							GregorianCalendar ins_gcal2 = new GregorianCalendar(t3,t1,t2);
				       		ins_gcal2.setLenient(false);
							}
							catch (Exception exp){
								return false;
							}
							return l_time.matches("(1[012]|0?[1-9])(\\s)?(?i)(AM|PM)");
							//return l_time.matches("(AM|PM)");
						}
						else if (p_format.equals(f3)){
							t2=t2-1;
							try{
							GregorianCalendar ins_gcal2 = new GregorianCalendar(t1,t2,t3);
				       		ins_gcal2.setLenient(false);
							}
							catch (Exception exp){
								return false;
							}
							return l_time.matches("(1[012]|0?[1-9])(\\s)?(?i)(AM|PM)");
							//return l_time.matches("(AM|PM)");
						}
						else
							return false;
				}
				catch(Exception exp){						
					return false;	
				}	
	}
 	
 	public boolean cDateTime(String p_value,String p_format) {
		try {
			StringTokenizer arr_dateTime = new StringTokenizer(p_value, ":");
			String l_date="";
			String l_time="";
			while (arr_dateTime.hasMoreTokens()) {
				l_date = arr_dateTime.nextToken();
				l_time=arr_dateTime.nextToken();
			}
				String f1="dmy";
				String f2="mdy";
				String f3="ymd";
				String t4="";
				String t5="";
				String t6="";
				String t_date=l_date.replaceAll("[/.]", "-");
				StringTokenizer arr_date = new StringTokenizer(t_date, "-");
				while (arr_date.hasMoreTokens()) {
					t4= arr_date.nextToken();
					t5 = arr_date.nextToken();
					t6 = arr_date.nextToken();
				}
					    GregorianCalendar ins_gcal=new GregorianCalendar();
					    ins_gcal.clear();
						ins_gcal.setLenient(false);
						int t1=Integer.parseInt( t4 );
						int t2=Integer.parseInt( t5 );
						int t3=Integer.parseInt( t6 );
						if (p_format.equals(f1)){
						t2=t2-1;
						try{
						GregorianCalendar ins_gcal2 = new GregorianCalendar(t3,t2,t1);
			       		ins_gcal2.setLenient(false);
						}
						catch (Exception exp){
							return false;
						}						
						return l_time.matches("(1[012]|0?[1-9])(\\s)?(?i)(am|pm)");
						}
						else if (p_format.equals(f2)){
							t1=t1-1;
							try{
							GregorianCalendar ins_gcal2 = new GregorianCalendar(t3,t1,t2);
				       		ins_gcal2.setLenient(false);
							}
							catch (Exception exp){
								return false;
							}				
							return l_time.matches("(1[012]|0?[1-9])(\\s)?(?i)(am|pm)");
						}
						else if (p_format.equals(f3)){
							t2=t2-1;
							try{
							GregorianCalendar ins_gcal2 = new GregorianCalendar(t1,t2,t3);
				       		ins_gcal2.setLenient(false);
							}
							catch (Exception exp){
								return false;
							}
							return l_time.matches("(1[012]|0?[1-9])(\\s)?(?i)(am|pm)");
						}
						else
							return false;
				}
				catch(Exception exp){						
					return false;	
				}	
	}
 	
 	public boolean cDateTimeComp(String p_date1,String p_date2,String p_comp) throws Exception 
 	{
    	String t4="";
		String t5="";
		String t6="";
		
		String t10="";
		String t11="";
		String t12="";
		
		String l_date3="";
		String l_date4="";
		String l_mi1="";
		String l_mi2="";
		Integer h1=0;
		Integer h2=0;
		
		StringTokenizer arr_date3=new StringTokenizer(p_date1,":");
		StringTokenizer arr_date4=new StringTokenizer(p_date2,":");
		
		while(arr_date3.hasMoreTokens()){
			 l_date3=arr_date3.nextToken();
			 l_mi1=arr_date3.nextToken();
		}
		while(arr_date4.hasMoreTokens()){
		l_date4=arr_date4.nextToken();		
		l_mi2=arr_date4.nextToken();
		}
        if(l_mi1.endsWith("am")){
        	h1=Integer.parseInt(l_mi1.replace("am", ""));
        }
        else if(l_mi1.endsWith("AM")){
        	h1=Integer.parseInt(l_mi1.replace("AM", ""));
        }
        else if(l_mi1.endsWith("pm")){
        	h1=Integer.parseInt(l_mi1.replace("pm", ""));
        	h1+=12;
        }
        else if(l_mi1.endsWith("PM")){
        	h1=Integer.parseInt(l_mi1.replace("PM", ""));
        	h1+=12;
        }
        
        if(l_mi2.endsWith("am")){
        	h2=Integer.parseInt(l_mi2.replace("am", ""));
        }
        else if(l_mi2.endsWith("AM")){
        	h2=Integer.parseInt(l_mi2.replace("AM", ""));
        }
        else if(l_mi2.endsWith("pm")){
        	h2=Integer.parseInt(l_mi2.replace("pm", ""));
        	h2+=12;
        }
        else if(l_mi2.endsWith("PM")){
        	h2=Integer.parseInt(l_mi2.replace("PM", ""));
        	h2+=12;
        }
		String l_date1=l_date3.replaceAll("[/.]", "-");
		String l_date2=l_date4.replaceAll("[/.]", "-");
		StringTokenizer arr_date1 = new StringTokenizer(l_date1, "-");
		while (arr_date1.hasMoreTokens()) {
			t4= arr_date1.nextToken();
			t5 = arr_date1.nextToken();
			t6 = arr_date1.nextToken();
		}
		int t1=Integer.parseInt( t4 );
		int t2=Integer.parseInt( t5 );
		int t3=Integer.parseInt( t6 );
		t2=t2-1;
		StringTokenizer arr_date2 = new StringTokenizer(l_date2, "-");
		while (arr_date2.hasMoreTokens()) {
			t10= arr_date2.nextToken();
			t11 = arr_date2.nextToken();
			t12= arr_date2.nextToken();
		}
		int t7=Integer.parseInt( t10 );
		int t8=Integer.parseInt( t11 );
		int t9=Integer.parseInt( t12 );
		t8=t8-1;						
			    GregorianCalendar ins_gcal = new GregorianCalendar(t3,t2,t1,h1,0);
			    ins_gcal.setLenient(false);
	       		GregorianCalendar ins_gcal1 = new GregorianCalendar(t9,t8,t7,h2,0);
	       		ins_gcal1.setLenient(false);
	    		if(p_comp==">") {		
		       		if(ins_gcal.compareTo(ins_gcal1)>0)
		       			return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp==">="){
	    			if(ins_gcal.compareTo(ins_gcal1)>=0)
	    				return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp=="<"){
	    			if(ins_gcal.compareTo(ins_gcal1)<0)
	    				return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp=="<="){
	    			if(ins_gcal.compareTo(ins_gcal1)<=0)
	    				return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp=="=="){
	    			if(ins_gcal.compareTo(ins_gcal1)==0)
	    				return true;
		       		else
		       			return false; 
	    		}
	       		else 
	       			return false;
}
 	public boolean cDateTimeCompDB(String p_date1,String p_date2,String p_comp) throws Exception 
 	{
    	String t4="";
		String t5="";
		String t6="";
		
		String t10="";
		String t11="";
		String t12="";
		
		String l_date3="";
		String l_date4="";
		String l_mi1="";
		String l_mi2="";
		Integer h1=0;
		Integer h2=0;
		
			 l_date3=p_date1.substring(0, 10);
			 l_mi1=p_date1.substring(11);
		
			l_date4=p_date2.substring(0, 10);
			l_mi2=p_date2.substring(11);
        
        if(l_mi1.equals("AM")){
        	h1=1; 
        }
        else if(l_mi1.equals("PM")){
        	h1=13;  
        }
        
        if(l_mi2.equals("AM")){
        	h2=1;  
        }
        
        else if(l_mi2.equals("PM")){
        	h2=13;  
        }
        
		String l_date1=l_date3.replaceAll("[/.]", "-");
		String l_date2=l_date4.replaceAll("[/.]", "-");
		StringTokenizer arr_date1 = new StringTokenizer(l_date1, "-");
		while (arr_date1.hasMoreTokens()) {
			t4= arr_date1.nextToken();
			t5 = arr_date1.nextToken();
			t6 = arr_date1.nextToken();
		}
		int t1=Integer.parseInt( t4 );
		int t2=Integer.parseInt( t5 );
		int t3=Integer.parseInt( t6 );
		t2=t2-1;
		StringTokenizer arr_date2 = new StringTokenizer(l_date2, "-");
		while (arr_date2.hasMoreTokens()) {
			t10= arr_date2.nextToken();
			t11 = arr_date2.nextToken();
			t12= arr_date2.nextToken();
		}
		int t7=Integer.parseInt( t10 );
		int t8=Integer.parseInt( t11 );
		int t9=Integer.parseInt( t12 );
		t8=t8-1;						
			    GregorianCalendar ins_gcal = new GregorianCalendar(t3,t2,t1,h1,0);
			    ins_gcal.setLenient(false);
	       		GregorianCalendar ins_gcal1 = new GregorianCalendar(t9,t8,t7,h2,0);
	       		ins_gcal1.setLenient(false);
	    		if(p_comp==">") {		
		       		if(ins_gcal.compareTo(ins_gcal1)>0)
		       			return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp==">="){
	    			if(ins_gcal.compareTo(ins_gcal1)>=0)
	    				return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp=="<"){
	    			if(ins_gcal.compareTo(ins_gcal1)<0)
	    				return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp=="<="){
	    			if(ins_gcal.compareTo(ins_gcal1)<=0)
	    				return true;
		       		else
		       			return false;
	    		}
	    		else if(p_comp=="=="){
	    			if(ins_gcal.compareTo(ins_gcal1)==0)
	    				return true;
		       		else
		       			return false; 
	    		}
	       		else 
	       			return false;
}
 	
 	public boolean cNegative( String p_value){
		 return p_value.matches("[-]?[0-9]+$");
	}
}//End Class