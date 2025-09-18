package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.MemberDao;
import exception.CampusException;
import model.MemberInformation;

/**
 * 学生情報画面のサーブレットクラス
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	/**
	 * ログイン処理後に連動して動きます<br>
	 * LoginServletがdoPostからの転送になるのでこちらもdoPostで動かしています
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = null;
		try {
			// 学生一覧の取得
			MemberDao memberDao = new MemberDao();
			List<MemberInformation> memberList = memberDao.findAllMember();

			// 取得した学生一覧をリクエストスコープにセット
			request.setAttribute("memberList", memberList);

			// 一覧画面を表示する準備
			nextPage = "list.jsp";

		} catch (CampusException e) {
			// 一覧処理中に例外が発生した場合はログイン画面に遷移させる
			// 一覧が表示できない可能性があるため
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			// ログイン画面を表示する準備
			nextPage = "Register.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}