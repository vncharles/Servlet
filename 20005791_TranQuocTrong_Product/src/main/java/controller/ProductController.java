package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import dao.ProductDAO;
import entity.CartItem;
import entity.Product;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/DBShop")
	private DataSource dataSource;
	
	private ProductDAO productDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			productDAO = new ProductDAO(dataSource);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			if(command==null) command = "LIST";
			
			switch (command) {
			case "LIST":
				listProduct(request, response);
				break;
			case "ADD":
				addProduct(request, response);
				break;
			case "DETAIL":
				detailProduct(request, response);
				break;
			case "DELETE":
				deleteProduct(request, response);
				break;
			case "ADD_TO_CART":
				addToCart(request, response);
				break;
			case "DONE_ADD_TO_CART":
				buyProduct(request, response);
				break;
			case "CART":
				cart(request, response);
				break;
			case "GET_FORM_PRODUCT":
				getFormProduct(request, response);
				break;
			case "UPDATE":
				updateProduct(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpectedd value : " + command);
			}
				
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.valueOf(request.getParameter("productId"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.valueOf(request.getParameter("price"));
		
		Product product = new Product(id, name, description, price);
		
		productDAO.updateProduct(product);
		
		listProduct(request, response);
	}



	private void getFormProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("productId");
		Product product = productDAO.getDetail(Long.valueOf(id));
		
		request.setAttribute("PRODUCT", product);
		request.getRequestDispatcher("/form_product.jsp").forward(request, response);
	}



	private void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cart");
		Double totalPrice = (Double) request.getAttribute("totalPrice");
		
		request.setAttribute("cart", cartItems);
		request.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}



	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("productId");
		Product product = productDAO.getDetail(Long.valueOf(id));
		
		request.setAttribute("PRODUCT", product);
		request.getRequestDispatcher("/addToCart.jsp").forward(request, response);
		
	}



	private void buyProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    HttpSession session = request.getSession();
	    List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
	    Double totalPrice = (Double) session.getAttribute("totalPrice");
	    
	    if(cartItems==null) {
	    	cartItems = new ArrayList<CartItem>();
	    	totalPrice = 0.0;
	    	session.setAttribute("totalPrice", totalPrice);
	    	session.setAttribute("cart", cartItems);
	    } 
	    
    	String id = request.getParameter("productId");
    	String quantity = request.getParameter("quantity");
    	
    	boolean flag = true;
    	for(CartItem item: cartItems) {
    		if(item.getProduct().getId()==Long.valueOf(id)) {
    			CartItem newCartItem = new CartItem(item.getProduct(), item.getQuantity()+Integer.valueOf(quantity), item.getProduct().getPrice()*(Integer.valueOf(quantity)+item.getQuantity()));
    			cartItems.remove(item);
    			cartItems.add(newCartItem);
    			totalPrice += item.getProduct().getPrice()*Integer.valueOf(quantity);
    			flag = false;
    			break;
    		}
    	};
    	
    	if(flag) {
			Product product = productDAO.getDetail(Long.valueOf(id));
			CartItem cartItem = new CartItem(product, Integer.valueOf(quantity), product.getPrice()*Integer.valueOf(quantity));
			
			cartItems.add(cartItem);
			totalPrice += cartItem.getProduct().getPrice()*cartItem.getQuantity();
    	}
    	
    	request.getSession().setAttribute("cart", cartItems);
    	request.getSession().setAttribute("totalPrice", totalPrice);
	    
	    
//	    request.setAttribute("cart", cartItems);
		cart(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("productId");
		productDAO.deleteProduct(Long.valueOf(id));
		
		listProduct(request, response);
	}



	private void detailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("productId");
		Product product = productDAO.getDetail(Long.valueOf(id));
		
		request.setAttribute("PRODUCT", product);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}



	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.valueOf(request.getParameter("price"));
		
		Product product = new Product(null, name, description, price);
		productDAO.addProduct(product);
		
		listProduct(request, response);
		
	}



	private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> listProduct = productDAO.getAll();
		request.setAttribute("LIST_PRODUCT", listProduct);
		request.getRequestDispatcher("/listProduct.jsp").forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
