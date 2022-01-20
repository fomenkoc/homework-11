package com.gmail.fomenkoc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import com.gmail.fomenkoc.dao.impl.RoleDao;
import com.gmail.fomenkoc.shared.FilterService;

@WebFilter(filterName = "users", urlPatterns = { "/users.jsp" })
public class UsersFilter extends HttpFilter implements Filter {
	private FilterService filterService = FilterService.getFilterService();
	private RoleDao roleDao = new RoleDao();

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		filterService.doFilterValidation(request, response, chain,
														roleDao.readStaff());
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
