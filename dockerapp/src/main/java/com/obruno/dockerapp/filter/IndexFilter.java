package com.obruno.dockerapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		try {

			if (request instanceof HttpServletRequest) {
				String url = ((HttpServletRequest) request).getRequestURL().toString();
				String queryString = ((HttpServletRequest) request).getQueryString();
				System.out.println(url + "?" + queryString);
				
				if (url.endsWith("dockerapp/"))
					((HttpServletResponse) response).sendRedirect(
							((HttpServletRequest) request).getContextPath() + "/index.xhtml");
				else
					chain.doFilter(request, response);
			} 

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
