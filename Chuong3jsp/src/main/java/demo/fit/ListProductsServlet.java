package demo.fit;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class ListProductsServlet
 */
@WebServlet("/ListProductsServlet")
public class ListProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductUtil productUtil;
	@Resource(name = "jdbc/Shop")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		// create our student db util ... and pass in the conn pool / datasource
		try {
			productUtil = new ProductUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	public ListProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			// route to the appropriate method
			switch (theCommand) {
			case "LIST":
				listProducts(request, response);
				break;
			case "ADD":
				addProduct(request, response);
				break;
			case "BUY":
				buyProduct(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpectedd value : " + theCommand);
			}
		} catch (Exception e) {
			throw new ServletException(e);
			// TODO: handle exception
		}

	}

	/**
	 * @throws Exception
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> products = productUtil.getProducts();
		request.setAttribute("PRODUCT_LIST", products);
		RequestDispatcher dispathcher = request.getRequestDispatcher("/listProduct.jsp");
		dispathcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("productName");
		String des = request.getParameter("productDes");
		Long p = Long.parseLong(request.getParameter("productPrice"));
		BigDecimal price = BigDecimal.valueOf(p);
		Product productnew = new Product(name, des, price);
		productUtil.addProduct(productnew);
		listProducts(request, response);
	}

	protected void LoadProduct(HttpServletRequest request, HttpServletResponse respose) throws Exception {
		String theproductID = request.getParameter("productId");
		Product theproduct = productUtil.getProductID(theproductID);
		request.setAttribute("PRODUCT", theproduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AddtoCart.jsp");
		dispatcher.forward(request, respose);

	}
	protected void buyProduct (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
	}

}
