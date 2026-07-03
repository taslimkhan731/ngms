package common;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class conversion
{
	public String getToday() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    String today = sdf.format(cal.getTime());  
	    return today;
	}
	
	public String toDBDate(String date)throws Exception
	{
			String t4="";
			String t5="";
			String t6="";	
			String t_date1=date.replaceAll("[/.]", "-");
			StringTokenizer arr_date1 = new StringTokenizer(t_date1, "-");
			   
			while (arr_date1.hasMoreTokens()) 
			{
				t4= arr_date1.nextToken();
				t5 = arr_date1.nextToken();
				t6 = arr_date1.nextToken();
			}		
			int t1=Integer.parseInt( t4 );
			int t2=Integer.parseInt( t5 );
			int t3=Integer.parseInt( t6 );
			t2=t2-1;
		    GregorianCalendar Gcal = new GregorianCalendar(t3,t2,t1);
		    Gcal.setLenient(false);
		    String s3 = String.format("%1$tm/%1$te/%1$tY", Gcal);
		    return s3;	
	}
	public String toDateFormat(String date,String p_format)throws Exception
	{
			String s3	=	"";
			String t4	=	"";
			String t5	=	"";
			String t6	=	"";	
			String t_date1=date.replaceAll("[/.]", "-");
			StringTokenizer arr_date1 = new StringTokenizer(t_date1, "-");
			   
			while (arr_date1.hasMoreTokens()) 
			{
				t4= arr_date1.nextToken();
				t5 = arr_date1.nextToken();
				t6 = arr_date1.nextToken();
			}		
			int t1=Integer.parseInt( t4 );
			int t2=Integer.parseInt( t5 );
			int t3=Integer.parseInt( t6 );
			t2=t2-1;
		    GregorianCalendar Gcal = new GregorianCalendar(t3,t2,t1);
		    Gcal.setLenient(false);
		    
		    if(p_format.equals("dmy"))
		    	 s3 = String.format("%1$te-%1$tm-%1$tY", Gcal);
		    else  if(p_format.equals("ymd"))
		    	s3 = String.format("%1$tY-%1$tm-%1$te", Gcal);
		    else  if(p_format.equals("mdy"))
		    	s3 = String.format("%1$tm-%1$te-%1$tY", Gcal);
		    
			return s3;	
	}
	public String toDateFormat_seperator(String date,String p_seperator)throws Exception
	{
			String t_date1=date.replaceAll("[/.-]", p_seperator);
			return t_date1;	
	}
	    
	
}//end of class