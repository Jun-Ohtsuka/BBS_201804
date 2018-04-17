package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.UserService;

/**
 * Servlet implementation class UserManegment
 */
@WebServlet(urlPatterns = {"/userManagement"})
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//DBからユーザー情報取得
		List<User> users = new UserService().getUserAll();

		request.setAttribute("users", users);
		request.getRequestDispatcher("userManagement.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String editUserId = request.getParameter("id");
		String editFreeze = request.getParameter("freezeVal");
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		new UserService().updateDeleted(Integer.parseInt(editUserId), Integer.parseInt(editFreeze));
		String name = request.getParameter("name");
		if(editFreeze.equals("1")){
			messages.add(name + "を停止しました");
		}else{
			messages.add(name + "の停止を解除しました");
		}

		session.setAttribute("messages", messages);
		response.sendRedirect("userManagement");
	}

}
