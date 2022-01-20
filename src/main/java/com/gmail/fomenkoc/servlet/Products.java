package com.gmail.fomenkoc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.fomenkoc.dao.impl.ProdDao;
import com.gmail.fomenkoc.domain.Prod;
import com.google.gson.Gson;


@WebServlet(name = "products", urlPatterns = { "/products" })
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdDao prodDao = new ProdDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Prod> prods = prodDao.readAll();
		String json = new Gson().toJson(prods);
		response.setContentType("applacation/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
