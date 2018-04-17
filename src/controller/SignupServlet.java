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

import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;

/**
 * Servlet implementation class signup
 */
@WebServlet(urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setAccount(request.getParameter("account"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));

			new UserService().register(user);

			response.sendRedirect("./");
		} else {
			request.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}

		if (StringUtils.isEmpty(checkPassword) == true) {
			messages.add("パスワード確認用を入力してください");
		}

		if(!StringUtils.equals(password, checkPassword)){
			messages.add("パスワードが一致しません");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
