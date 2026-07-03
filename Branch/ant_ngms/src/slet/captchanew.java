package slet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import template.ngsession;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import common.*;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.util.Random;


@WebServlet("/captchanew")
public class captchanew extends HttpServlet {
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(captchanew.class);
	private static final long serialVersionUID = 1L;
	public HttpSession sess_regen(HttpServletRequest p_request)  {
		HttpSession l_session	= p_request.getSession(true);
		ngsession l_s			= (ngsession)l_session.getAttribute("ngm_session");
		l_session	= p_request.getSession(true);
		if(l_s != null) l_session.setAttribute("ngm_session", l_s);

		return l_session;
	}
       public captchanew() {
        super();
       
    }

       public String encodeToString(BufferedImage image, String type) {
           String imageString = null;
           ByteArrayOutputStream bos = new ByteArrayOutputStream();

           try {
               ImageIO.write(image, type, bos);
               byte[] imageBytes = bos.toByteArray();

               BASE64Encoder encoder = new BASE64Encoder();
               imageString = encoder.encode(imageBytes);

               bos.close();
           } catch (IOException e) {
        	   logger.error("captcha new :"+e);
               e.printStackTrace();
           }
           return imageString;
       }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utility ins_utility=new utility();	
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
		ServletOutputStream out = response.getOutputStream();
		HttpSession l_session	= this.sess_regen(request);
		ngsession l_s			= (ngsession)l_session.getAttribute("ngm_session");
		if(l_s == null) { out.println("Illegal Attempt"); return; }
		String captcha = ins_utility.getCapcha();
		l_s.captcha_id	= captcha;
		response.setContentType("image/jpg");
        BufferedImage image = new BufferedImage(120, 35, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(new Color(249,249,249));
        graphics.fillRect(0, 0, 120, 35);
        GradientPaint gradientPaint = new GradientPaint(20, 3, Color.BLUE, 15, 8, Color.BLACK, true);
        graphics.setPaint(gradientPaint);
        Font font = new Font("Arial",Font.BOLD , 20);
        graphics.setFont(font);
        graphics.drawString(captcha, 10, 18);
        graphics.dispose();
        String outstr = encodeToString(image,"jpeg");
        
//        String outstr2 = "<img  align='left' class='img-responsive' src=\"data:image/bmp;base64,"+outstr+"\" > <a  onClick='captcha_refresh()'><i class='fa fa-refresh'></i> Refresh Captcha</a>  ";
       
//        out.print(outstr2);		
        out.close();
        
	}

}
