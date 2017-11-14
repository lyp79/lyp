package lyp.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.utils.YzmPic;

public class YzmServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		//设置客户端不缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		double rm = Math.random()*10000;
		String str = String.valueOf((int)rm);
		YzmPic pic= new YzmPic(String.valueOf(rm));
		pic.drawClient();
		
		str = pic.authenticationCode;
		//把验证码对应的字符串存储在session中
		request.getSession().setAttribute("code", str);
		BufferedImage bufimg = pic.image;
		
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(bufimg, "JPEG", sos);
		sos.flush();
		sos.close();
	}

}
