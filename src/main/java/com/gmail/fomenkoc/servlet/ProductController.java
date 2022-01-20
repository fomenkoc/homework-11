package com.gmail.fomenkoc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.fomenkoc.dao.impl.ProdDao;
import com.gmail.fomenkoc.domain.Prod;

@WebServlet(name = "product", urlPatterns = { "/product" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdDao prodDao = new ProdDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String prodID = request.getParameter("prodID");
		Prod prod = prodDao.read(Integer.parseInt(prodID));
		request.setAttribute("prod", prod);
		request.getRequestDispatcher("singleProduct.jsp")
												.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		Prod prod  = new Prod(name, description, getValidatedPrice(price));
		prodDao.create(prod);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private double getValidatedPrice(String price) {
		
		if(price == null || price.isEmpty()) {
			return 0;
		}
		return Double.parseDouble(price);
	}
	
}
