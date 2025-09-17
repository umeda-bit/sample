package controller;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.MemberDao;
import exception.CampusException;
import model.MemberInformation;
import model.Staff;

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
			List<Member> memberList = memberDao.findAllMember();

			// 取得した学生一覧をリクエストスコープにセット
			request.setAttribute("memberList", memberList);

			// 一覧画面を表示する準備
			nextPage = "list.jsp";

		} catch(CampusException e) {
			// 一覧処理中に例外が発生した場合はログイン画面に遷移させる
			// 一覧が表示できない可能性があるため
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			// ログイン画面を表示する準備
			nextPage = "login.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	/**
	 * 今回詳細画面（新規登録も含む）を表示するために使います
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// おまけ機能（一覧に戻る）を判定
		// リクエストがなければ詳細画面を表示する準備
		String list = request.getParameter("list");
		if(list == null) {

			// 詳細画面に表示する学生番号をリクエストパラメータから取得
			// リクエストURLのクエリストリングに学生番号が表示されています
			String findMemberNumber = request.getParameter("member_number");

			// ログインしている職員の情報を取得
			// DBの登録（更新）処理の際に使用します
			HttpSession session = request.getSession(false);
			Staff staff = (Staff)session.getAttribute("staff");
			MemberInformation memberMemo = null;
			try {
				if(findMemberNumber != null) {
					// 学生番号が指定されている場合は詳細表示（更新）
					// 学生番号に紐づく情報を検索してセット
					MemberDao memberDao = new MemberDao();
					memberMemo = memberDao.findMemberMemo(findMemberNumber);
				} else {
					// 学生番号が指定されていない場合は新規登録
					// スタッフの情報だけセット（その他の情報は空）
					String staffId = staff.getStaffId();
					String staffName = staff.getStaffName();
					memberMemo = new MemberInformation(staffId, staffName);
				}
				// 次の画面に情報をセット
				request.setAttribute("memberMemo", memberMemo);

			} catch (CampusException e) {
				e.printStackTrace();
				String message = e.getMessage();
				request.setAttribute("message", message);
				request.setAttribute("error", "true");
			}
			// 次の画面に遷移
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("detail.jsp");
			requestDispatcher.forward(request, response);

		} else {
			// backのリクエストがある場合は一覧画面を表示
			// 今回はdoPostメソッドに処理を移譲するようにしています
			doPost(request, response);
		}
	}
}