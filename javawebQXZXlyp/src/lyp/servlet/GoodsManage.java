package lyp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

import lyp.entity.GoodsInfo;
import lyp.entity.GoodsType;
import lyp.entity.PageModel;
import lyp.service.GoodsService;
import lyp.service.TypeService;
import lyp.serviceImpl.GoodsServiceImpl;
import lyp.serviceImpl.TypeServiceImpl;

public class GoodsManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gs = new GoodsServiceImpl();
	TypeService ts = new TypeServiceImpl();
	PageModel<GoodsInfo> pm = new PageModel<GoodsInfo>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		String goodsId = request.getParameter("id");
		String op = request.getParameter("op");
		String realPath = this.getServletContext().getRealPath("/product");
		if ("toAdd".equals(op)) {
			toAdd(request, response);
		} else if ("goodsDel".equals(op)) {
			delGoods(request, response, path, session, goodsId);
		} else if ("toUpdate".equals(op)) {
			toUpdate(request, response, goodsId);
		} else if ("manage".equals(op)) {
			manage(request, response);
		} else if ("goodsAdd".equals(op)) {
			addGoods(request, response, session, realPath);
		} else if ("goodsUpdate".equals(op)) {
			updateGoods(request, response, session, realPath);
		} else if ("query".equals(op)) {
			query(request, response, session, realPath);
		} else if ("delcheck".equals(op)) {
			delcheck(request, response, session, path);
		}
	}

	private void delcheck(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path)
			throws ServletException, IOException {
		String cleckid = request.getParameter("cleckid");
		if (!cleckid.equals("") && cleckid != null) {
			try {
				gs.delCheckGoods(cleckid);
				session.setAttribute("msg", "删除成功!");
				response.sendRedirect(path + "/back/goodsManage?op=manage");
			} catch (Exception e) {
				request.setAttribute("msg", "删除失败!");
				request.getRequestDispatcher("/back/goodsManage?op=manage").forward(request, response);
			}
		} else {
			request.setAttribute("msg", "删除失败!");
			request.getRequestDispatcher("/back/goodsManage?op=manage").forward(request, response);
		}
	}

	private void delGoods(HttpServletRequest request, HttpServletResponse response, String path, HttpSession session,
			String goodsId) throws ServletException, IOException {
		try {
			gs.delGoods(Integer.parseInt(goodsId));
			session.setAttribute("msg", "商品删除成功");
			response.sendRedirect(path + "/back/goodsManage?op=manage");
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败," + e.getMessage());
			request.getRequestDispatcher("/back/goodsManage.jsp").forward(request, response);
		}
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GoodsType> typeList = Collections.synchronizedList(ts.selectAll());
		request.setAttribute("typeList", typeList);
		request.getRequestDispatcher("/back/goodsAdd.jsp").forward(request, response);
	}

	private void toUpdate(HttpServletRequest request, HttpServletResponse response, String goodsId)
			throws IOException, ServletException {
		GoodsInfo goods = gs.selectById(Integer.parseInt(goodsId));
		List<GoodsType> typeList = Collections.synchronizedList(ts.selectAll());
		request.setAttribute("typeList", typeList);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);
	}

	// 添加商品
	private void addGoods(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String realPath) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.setCharset("UTF-8");
		su.setMaxFileSize(2097152);
		su.setAllowedFilesList("jpg,png,gif,bmp,jpeg");
		su.initialize(this.getServletConfig(), request, response);
		int typeId = 0;
		try {
			su.upload();
			typeId = Integer.parseInt(su.getRequest().getParameter("typeId"));
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败,商品编号转换出错!");
			this.toAdd(request, response);
		}
		String goodsName = su.getRequest().getParameter("goodsName");
		String priceStr = su.getRequest().getParameter("price");
		float price = 0;
		if (priceStr != null && !priceStr.equals("")) {
			try {
				price = Float.parseFloat(priceStr);
				if (price < 0) {
					request.setAttribute("msg", "操作失败,价格输入错误!");
					this.toAdd(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("msg", "操作失败,价格输入错误!");
				this.toAdd(request, response);
			}
		}
		String discountStr = su.getRequest().getParameter("discount");
		float discount = 10;
		if (discountStr != null && !discountStr.equals("")) {
			try {
				discount = Float.parseFloat(discountStr);
				if (discount <= 0 || discount > 10) {
					request.setAttribute("msg", "操作失败,折扣输入错误! 范围: 0<折扣<=10");
					this.toAdd(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("msg", "操作失败,折扣输入错误! 范围: 0<折扣<=10");
				this.toAdd(request, response);
			}
		}

		int status = su.getRequest().getParameter("status") == null ? 1 : 0;
		int isRecommend = su.getRequest().getParameter("isRecommend") == null ? 1 : 0;
		int isNew = su.getRequest().getParameter("isNew") == null ? 1 : 0;
		List<String> list = new ArrayList<String>();
		String remark = su.getRequest().getParameter("remark");
		Files files = su.getFiles();
		if (files.getSize()>0) {
			for (int i = 0; i < files.getCount(); i++) {
				File file = files.getFile(i);
				String photoName = file.getFileName();
				try {
					file.saveAs(realPath + "/" + photoName);
				} catch (Exception e) {
					request.setAttribute("msg", "操作失败");
					this.toAdd(request, response);
				}
				list.add(photoName);
			}
		} else {
			list.add("photoError.png");
		}
		GoodsInfo goods = new GoodsInfo();
		goods.setGoodsName(goodsName);
		goods.getGoodsType().setTypeId(typeId);
		goods.setPrice(price);
		goods.setDiscount(discount);
		goods.setStatus(status);
		goods.setIsRecommend(isRecommend);
		goods.setIsNew(isNew);
		goods.setPhoto(list.get(0));// 只有一个图片 0表示取第一个
		goods.setRemark(remark);
		try {
			gs.addGoods(goods);
			session.setAttribute("msg", "商品添加成功!");
			request.getRequestDispatcher("/back/goodsManage?op=manage").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败,");
			this.toAdd(request, response);
		}
	}

	// 修改商品
	private void updateGoods(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String realPath) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.setCharset("UTF-8");
		su.setAllowedFilesList("jpg,png,gif,bmp,jpeg");
		su.initialize(this.getServletConfig(), request, response);
		try {
			su.upload();
			int typeId = Integer.parseInt(su.getRequest().getParameter("typeId"));
			int goodsId = Integer.parseInt(su.getRequest().getParameter("goodsId"));
			String goodsName = su.getRequest().getParameter("goodsName");
			String priceStr = su.getRequest().getParameter("price");
			float price = 0;
			if (priceStr != null) {
				try {
					price = Float.parseFloat(priceStr);
					if (price < 0) {
						request.setAttribute("msg", "操作失败,价格不能为负数!");
						request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);
					}
				} catch (Exception e) {
					request.setAttribute("msg", "操作失败,价格输入错误!");
					request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);
				}
			}
			String discountStr = su.getRequest().getParameter("discount");
			float discount = 10;
			if (discountStr != null && !discountStr.equals("")) {
				try {
					discount = Float.parseFloat(discountStr);
					if (discount <= 0 || discount > 10) {
						request.setAttribute("msg", "折扣输入错误! 范围: 0<折扣<=10,如:8.6");
						request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);
					}
				} catch (Exception e) {
					request.setAttribute("msg", "折扣输入错误! 范围: 0<折扣<=10,如:8.6");
					request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);
				}
			}
			int status = su.getRequest().getParameter("status") == null ? 1 : 0;
			int isRecommend = su.getRequest().getParameter("isRecommend") == null ? 1 : 0;
			int isNew = su.getRequest().getParameter("isNew") == null ? 1 : 0;
			List<String> list = new ArrayList<String>();
			String remark = su.getRequest().getParameter("remark");
			String photo1 = su.getRequest().getParameter("photo1");
			String photoName = null;
			Files files = su.getFiles();
			if (files.getSize()>0) {
				for (int i = 0; i < files.getCount(); i++) {
					File file = files.getFile(i);
					try {
						photoName = file.getFileName();
						file.saveAs(realPath + "/" + photoName);
					} catch (Exception e) {}
				}
				list.add(photoName);
			} else {
				list.add(photo1);
			}
			GoodsInfo goods = new GoodsInfo();
			goods.setGoodsId(goodsId);
			goods.getGoodsType().setTypeId(typeId);
			goods.setGoodsName(goodsName);
			goods.setPrice(price);
			goods.setDiscount(discount);
			goods.setStatus(status);
			goods.setIsRecommend(isRecommend);
			goods.setIsNew(isNew);
			goods.setPhoto(list.get(0));// 只有一个图片 0表示取第一个
			goods.setRemark(remark);
			try {
				gs.updateGoods(goods);
				session.setAttribute("msg", "商品修改成功!");
				request.getRequestDispatcher("/back/goodsManage?op=manage").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", "操作失败!");
				request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);

			}
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败!");
			request.getRequestDispatcher("/back/goodsUpdate.jsp").forward(request, response);
		}
	}

	private void manage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
		}
		if (pm.getTotalPage() == 0) {
			pageNo = 1;
		}
		pm.setPageNo(pageNo);
		pm.setPageSize(8);
		pm = gs.getGoodsPage(pm);
		request.setAttribute("pm", pm);
		request.setAttribute("op", "manage");
		request.getRequestDispatcher("/back/goodsManage.jsp").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response, HttpSession session, String realPath)
			throws ServletException, IOException {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
		}
		if (pm.getTotalPage() == 0) {
			pageNo = 1;
		}
		pm.setPageNo(pageNo);
		pm.setPageSize(10);
		String keywords = request.getParameter("keywords");
		if (keywords != null && !keywords.equals("")) {
			pm = gs.getGoodsPage(pm, keywords);
			request.setAttribute("pm", pm);
			request.setAttribute("keywords", keywords);
			request.setAttribute("op", "query");
			request.getRequestDispatcher("/back/goodsManage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/back/goodsManage?op=manage").forward(request, response);
		}
	}

}
