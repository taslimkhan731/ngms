package com.stl.ngms.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.AttributedString;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {
static final Logger logger = Logger.getLogger(CaptchaController.class);
	
	private int height = 45;
	private int width = 150;
    public static final String CAPTCHA_KEY = "STL_CAPTCHA";
    private static final int CAPTCHA_LENGTH = 5;
	private final static SecureRandom srandom = new SecureRandom();
	private static final long serialVersionUID = 1512371749422L;
	
	@RequestMapping(value = { "/getCaptcha" }, method = RequestMethod.GET)
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "height", required = false) String h,
			@RequestParam(value = "width", required = false) String w,
			@RequestParam(value = "status", required = false) String status) throws IOException {
		int cHeight = this.height;
		int cWidth = this.width;
		if(!StringUtils.isEmpty(h) && !StringUtils.isEmpty(w)){
			cHeight=Integer.parseInt(h);
			cWidth=Integer.parseInt(w);
		}
		
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        Random r = new Random();
        int f = System.currentTimeMillis() > serialVersionUID ? 1 : 0;
        String token = Long.toString(Math.abs(r.nextLong()), 36);
        String ch = token.substring(0, CAPTCHA_LENGTH);//no of characters in captcha image
//        HttpSession session = request.getSession(true);
        Color[] color = { Color.RED, Color.BLUE,
                new Color(0.6662f, 0.4569f, 0.3232f), Color.BLACK,
                Color.LIGHT_GRAY, Color.YELLOW, Color.LIGHT_GRAY, Color.cyan,
                Color.GREEN, Color.black, Color.DARK_GRAY, Color.MAGENTA };
        
//        if (StringUtils.equals(status, "refresh")) {
//            session.setAttribute(CAPTCHA_KEY, ch);
//            response.setContentType("plain/text");
//            response.setHeader("Cache-Control", "no-cache");
//            response.getWriter().write(ch);
//
//        } else {
            BufferedImage image = new BufferedImage(cWidth, cHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            // graphics2D.setBackground(Color.RED);
            graphics2D.setColor(color[4]); // or the background color u want
            graphics2D.fillRect(0, 0, cWidth, cHeight);
            // Hashtable<TextAttribute, Object> map = new
            // Hashtable<TextAttribute, Object>();
            
//            Color c = Color.BLACK;
//            GradientPaint gp = new GradientPaint(30, 30, color[2 << f], 15, 25,
//                    color[3 << f], true);
            
            Color c = new Color(0.662f, 0.469f, 0.232f);
            GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.black, true);
            
            graphics2D.setPaint(gp);
            Font font = new Font("Verdana", Font.CENTER_BASELINE, 20);
            
            // Get the FontMetrics
            FontMetrics metrics = graphics2D.getFontMetrics(font);
            // Determine the X coordinate for the text
            int x = 0 + (cWidth - metrics.stringWidth(ch)) / 2;
            // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
            int y = 0 + ((cHeight - metrics.getHeight()) / 2) + metrics.getAscent();
            
//            graphics2D.setFont(font);
            
            AttributedString as1 = new AttributedString(ch);
            as1.addAttribute(TextAttribute.FONT, font);
            as1.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, 0, CAPTCHA_LENGTH);
            
            graphics2D.drawString(as1.getIterator(), x, y);
            graphics2D.dispose();
            session.setAttribute(CAPTCHA_KEY, ch);
            logger.info(ch);
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            outputStream.close();

//        }
		return;
	}//captcha request
}
