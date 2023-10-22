package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.TinTucDAO;
import entity.DanhMuc;
import entity.TinTuc;

/**
 * Servlet implementation class TinTucFormServlet
 */
@WebServlet("/TinTucFormServlet")
public class TinTucFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/QUANLYDANHMUC")
	private DataSource dataSource;
	private TinTucDAO tinTucDAO = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TinTucFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
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
			System.out.println(function);
			switch (function) {
			case "UPDATE":
				updateTinTuc(request, response);
				break;
			case "ADD" : 
				addTinTuc(request, response);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	private void addTinTuc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDung");
		String lienKet = request.getParameter("lienKet");
		DanhMuc danhMuc = new DanhMuc();
		danhMuc.setMaDM(1);
		TinTuc tinTuc = new TinTuc(tieuDe, noiDung, lienKet, danhMuc);
		tinTucDAO.add(tinTuc);
		request.getRequestDispatcher("DanhSachTinTucServlet").forward(request, response);
	}

	private void updateTinTuc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maTT = Integer.parseInt(request.getParameter("id"));
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDung");
		String lienKet = request.getParameter("lienKet");
		System.out.println(tieuDe);
		
		TinTuc tinTuc = new TinTuc(maTT, tieuDe, noiDung, lienKet);
		tinTucDAO.updateTinTuc(tinTuc);
		request.getRequestDispatcher("DanhSachTinTucServlet").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
