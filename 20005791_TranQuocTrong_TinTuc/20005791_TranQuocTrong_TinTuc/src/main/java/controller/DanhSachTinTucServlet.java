package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.DanhMucDAO;
import dao.TinTucDAO;
import entity.DanhMuc;
import entity.TinTuc;

/**
 * Servlet implementation class DanhSachTinTucServlet
 */
@WebServlet("/DanhSachTinTucServlet")
public class DanhSachTinTucServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/QUANLYDANHMUC")
	private DataSource dataSource;
	
	private DanhMucDAO danhMucDAO;
	private TinTucDAO tinTucDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachTinTucServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			danhMucDAO = new DanhMucDAO(dataSource);
			tinTucDAO = new TinTucDAO(dataSource);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String function = request.getParameter("function");
			if(function==null) function = "GET_LIST";
			switch (function) {
			case "GET_LIST":
				getTinTuc(request, response);
				break;
			case "VIEW" :
				xemChiTiet(request, response);
				break;
			case "UPDATE":
				suaTinTuc(request, response);
				break;
			case "DELETE":
				xoaTinTuc(request, response);
				break;
			case "ADD" : 
				themTinTuc(request, response);
				break;
			default:
				
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	private void themTinTuc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("TinTucForm.jsp").forward(request, response);
		request.getRequestDispatcher("DanhSachTinTuc.jsp").forward(request, response);
	}

	private void xoaTinTuc(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		tinTucDAO.deleteTinTuc(id);
		
		
	}

	private void suaTinTuc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		TinTuc tinTuc = tinTucDAO.getTinTucById(id);
		request.setAttribute("TINTUC", tinTuc);
		request.setAttribute("update", "update");
		request.getRequestDispatcher("TinTucForm.jsp").forward(request, response);
		request.getRequestDispatcher("DanhSachTinTuc.jsp").forward(request, response);
	}

	private void xemChiTiet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		TinTuc tinTuc = tinTucDAO.getTinTucById(id);
		System.out.println(tinTuc.toString());
		request.setAttribute("TINTUC", tinTuc);
		request.getRequestDispatcher("TinTucForm.jsp").forward(request, response);
		
	}

	private void getTinTuc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TinTuc> list = tinTucDAO.getAllByDanhMuc(1);	
		request.setAttribute("tinTucList", list);
//		List<DanhMuc> list = danhMucDAO.getAll();
//		request.setAttribute("danhMucList", list);
		request.getRequestDispatcher("DanhSachTinTuc.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
