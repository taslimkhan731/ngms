package common;

import java.io.FileOutputStream;



import com.onbarcode.barcode.QRCode;

public class nic_barcode {


public boolean save_barcode_image(String filenamewithpath, String data)
{
		boolean retvalue =false;
		try {
			FileOutputStream  f0 = new FileOutputStream(filenamewithpath+".jpeg");
			QRCode barcode = new QRCode();   
			barcode.setData(data);     
			barcode.drawBarcode(f0);   
			retvalue =true;
		}
		catch (Exception e) {
			retvalue =false;
			}
		return retvalue;
} // end of method
} //end of the class