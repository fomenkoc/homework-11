package com.gmail.fomenkoc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.fomenkoc.dao.impl.CartDao;
import com.gmail.fomenkoc.dao.impl.ProdDao;
import com.gmail.fomenkoc.domain.Cart;
import com.gmail.fomenkoc.domain.Prod;
import com.gmail.fomenkoc.dto.CartDto;
import com.google.gson.Gson;


@WebServlet(name = "cart", urlPatterns = { "/cart" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDao cartDao = new CartDao();
	private ProdDao prodDao = new ProdDao();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer prodID = Integer.parseInt(request.getParameter("prodID"));
		HttpSession session = request.getSession();
		Integer userID = (Integer) session.getAttribute("userID");
		
		Cart cart = new Cart(userID, prodID);
		cartDao.create(cart);
		
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Integer userID = Integer
				.parseInt(session.getAttribute("userID").toString());
		List<Cart> carts = cartDao.readByUserID(userID);
		Map<Integer, Prod> idToProd = prodDao.readAllMap();
		List<CartDto> listOfCartDtos = map(carts, idToProd);

		String json = new Gson().toJson(listOfCartDtos);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}
	
	public List<CartDto> map(List<Cart> carts, Map<Integer, Prod> idToProd) {
		return carts.stream().map(cart -> {
			CartDto cartDto = new CartDto();
			Prod prod = idToProd.get(cart.getProdID());
			cartDto.cartID = cart.getCartID();
			cartDto.userID = cart.getUserID();
			cartDto.prodID = prod.getProdID();
			cartDto.prodName = prod.getProdName();
			cartDto.description = prod.getDescription();
			cartDto.price = cart.getPrice();
			cartDto.quantity = cart.getQuantity();
			cartDto.sum = cart.getSum();

			return cartDto;
		}).collect(Collectors.toList());
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cartID = req.getParameter("cartID");
		cartDao.delete(Integer.parseInt(cartID));
		resp.setContentType("text");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("Success");
	}

}
