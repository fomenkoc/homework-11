package com.gmail.fomenkoc.shared;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gmail.fomenkoc.domain.Role;

public class FilterService {
	private static FilterService filterService;

	private FilterService() {

	}

	public static FilterService getFilterService() {
		if (filterService == null) {
			filterService = new FilterService();
		}

		return filterService;
	}

	public void doFilterValidation(ServletRequest req, ServletResponse resp,
			FilterChain chain, List<Role> roles)
			throws IOException, ServletException {

		try {
			HttpSession session = ((HttpServletRequest) req).getSession();
			Role role = roles.stream().filter(
					r -> r.getRoleID() == session.getAttribute("roleID"))
					.findFirst().orElse(null);

			if (role != null && roles.contains(role)) {
				chain.doFilter(req, resp);
			} else {
				((HttpServletRequest) req).getRequestDispatcher("index.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			((HttpServletRequest) req).getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
