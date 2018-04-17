package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Comment;
import beans.UserMessage;
import service.CommentService;
import service.MessageService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/index.jsp","/home"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//カテゴリーの入力値を取得し検索用のテキストへ変換
		String category = request.getParameter("category");
		request.setAttribute("searchText", category);
		if(category == null || category.isEmpty()){
			category = "%%";
		}else{
			category = "%" + category + "%";
		}

		//日付開始日の入力値を取得し検索用に変換
		String startDate = request.getParameter("startDate");
		request.setAttribute("searchStartDate", startDate);
		if(startDate == null || startDate.isEmpty()){
			startDate = "1900-01-01 00:00:00";
		}else{
			startDate = startDate + " 23:59:59";
		}

		String endDate = request.getParameter("endDate");
		request.setAttribute("searchEndDate", endDate);
		if(endDate == null || endDate.isEmpty()){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = sdf.format(date);
		}else{
			endDate = endDate + " 23:59:59";
		}

		List<UserMessage> messages = new MessageService().getMessage(category, startDate, endDate);
		List<Comment> comments = new CommentService().getComment();

		request.setAttribute("articles", messages);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Comment comment = new Comment();
		comment.setMessageId(Integer.parseInt(request.getParameter("threadId")));
		comment.setText(request.getParameter("comment"));
		comment.setUserId(Integer.parseInt(request.getParameter("userId")));

		new CommentService().register(comment);

		response.sendRedirect("./");
	}
}
