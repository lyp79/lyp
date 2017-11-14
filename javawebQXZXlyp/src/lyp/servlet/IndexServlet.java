package lyp.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.service.FrontService;
import lyp.serviceImpl.FrontServiceImpl;

public class IndexServlet extends HttpServlet {
	FrontService fs = new FrontServiceImpl();

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("rawtypes")
		Map<String,List> indexMap = fs.getIndexMap();
		request.setAttribute("indexMap", indexMap);
		request.getRequestDispatcher("/front/index.jsp").forward(request, response);
	}

}
