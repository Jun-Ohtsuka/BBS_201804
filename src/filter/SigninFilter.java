package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/newMessage", "/home", "/home.jsp", "/index.jsp", "/userManagement", "/signup", "/settings"})
public class SigninFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		List<String> messages = new ArrayList<>();
        HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("loginUser") == null){
			messages.add("ログインしてください");
			session.setAttribute("messages", messages);
			((HttpServletResponse)response).sendRedirect("signin");
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig config){

	}

	public void destroy(){

	}

}
