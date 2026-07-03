package slet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.JPEGCodec;


@WebServlet("/captchaimport")
public class captchaimport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       public captchaimport() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
		ServletOutputStream out = response.getOutputStream();
		
		HttpSession l_session	= request.getSession(true);
		String res  = (String)l_session.getAttribute("imp_capid");
		response.setContentType("image/jpg");
        BufferedImage image = new BufferedImage(100, 40, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(new Color(41,54,63));
        graphics.fillRect(0, 0, 100, 83);
        GradientPaint gradientPaint = new GradientPaint(10, 1, Color.WHITE, 10, 1, Color.DARK_GRAY, true);
        graphics.setPaint(gradientPaint);
        Font font = new Font("Georgia", Font.ITALIC , 24);
        graphics.setFont(font);
        graphics.drawString(res, 8, 25);
        graphics.dispose();
        JPEGCodec.createJPEGEncoder(out).encode(image);
        out.close();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
