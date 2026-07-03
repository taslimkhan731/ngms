package slet;


import java.io.IOException;
import java.io.OutputStream;
import java.text.AttributedString;
import java.util.Random;

import javax.imageio.ImageIO;
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
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;

@WebServlet("/captcha")
public class captcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int height = 45;
	private int width = 150;
	
	public HttpSession sess_regen(HttpServletRequest p_request)  {
		HttpSession l_session	= p_request.getSession(true);
		ngsession l_s	= (ngsession)l_session.getAttribute("ngm_session");
		l_session	= p_request.getSession(true);
		if(l_s != null) l_session.setAttribute("ngm_session", l_s);
		
		return l_session;
	}
       public captcha() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
		ServletOutputStream out = response.getOutputStream();
		HttpSession l_session	= this.sess_regen(request);
		ngsession l_s			= (ngsession)l_session.getAttribute("ngm_session");
		if(l_s == null) { out.println("Illegal Attempt"); return; }
		String res = l_s.captcha_id;
        response.setContentType("image/jpg");
        BufferedImage image = new BufferedImage(120, 35, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(new Color(249,249,249));
        graphics.fillRect(0, 0, 120, 35);
        GradientPaint gradientPaint = new GradientPaint(20, 3, Color.BLUE, 15, 8, Color.BLACK, true);
        graphics.setPaint(gradientPaint);
        Font font = new Font("Arial",Font.BOLD , 18);
        graphics.setFont(font);
        graphics.drawString(res, 10, 18);
        graphics.dispose();
        JPEGCodec.createJPEGEncoder(out).encode(image);
        out.close();*/
		
		int cHeight = this.height;
		int cWidth = this.width;
		
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		Random r = new Random();
		int f = System.currentTimeMillis() > serialVersionUID ? 1 : 0;
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		String ch = token.substring(0, 5);// no of characters in
		ServletOutputStream out = response.getOutputStream();
		HttpSession l_session	= this.sess_regen(request);
		ngsession l_s			= (ngsession)l_session.getAttribute("ngm_session");
		if(l_s == null) { out.println("Illegal Attempt"); return; }
		l_s.captcha_id = ch;										
		Color[] color = { Color.RED, Color.BLUE,
				new Color(0.6662f, 0.4569f, 0.3232f), Color.BLACK,
				Color.LIGHT_GRAY, Color.YELLOW, Color.LIGHT_GRAY, Color.cyan,
				Color.GREEN, Color.black, Color.DARK_GRAY, Color.MAGENTA };

		
		BufferedImage image = new BufferedImage(cWidth, cHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		graphics2D.setColor(color[4]); // or the background color u want
		graphics2D.fillRect(0, 0, cWidth, cHeight);


		Color c = new Color(0.662f, 0.469f, 0.232f);
		GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.black,
				true);

		graphics2D.setPaint(gp);
		Font font = new Font("Verdana", Font.CENTER_BASELINE, 30);

		// Get the FontMetrics
		FontMetrics metrics = graphics2D.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = 0 + (cWidth - metrics.stringWidth(ch)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as
		// in java 2d 0 is top of the screen)
		int y = 0 + ((cHeight - metrics.getHeight()) / 2) + metrics.getAscent();

		// graphics2D.setFont(font);

		AttributedString as1 = new AttributedString(ch);
		as1.addAttribute(TextAttribute.FONT, font);
		as1.addAttribute(TextAttribute.STRIKETHROUGH,
				TextAttribute.STRIKETHROUGH_ON, 0, 5);

		graphics2D.drawString(as1.getIterator(), x, y);
		graphics2D.dispose();
		

		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "jpeg", outputStream);
		outputStream.close();
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
