package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.MemberDao;
import exception.CampusException;
import model.MemberInformation;

/**
 * 学生詳細画面のサーブレットクラス
 */
@WebServlet("/MemberMemoServlet")
public class MemberMemoServlet extends HttpServlet {
	/**
	 * 学生情報およびメモ情報の登録・更新処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションからスタッフ情報を取得
		//HttpSession session = request.getSession(false);
		//Staff staff = (Staff)session.getAttribute("staff");
		//String staffId = staff.getStaffId();
		//String staffName = staff.getStaffName();

		// 画面に表示（入力）された会員情報を取得
		request.setCharacterEncoding("UTF-8");
		//String userid = request.getParameter("user_id");
		String username = request.getParameter("user_name");
		String usermail = request.getParameter("mail");
		String usertel = request.getParameter("tel");
		String useraddress = request.getParameter("address");
		String userbirthday = request.getParameter("birthday");
		String userjob = request.getParameter("job");
		String userpassword = request.getParameter("password");
		//String user_created_datetime = request.getParameter("created_datetime");
		//String user_updated_datetime = request.getParameter("updated_datetime");

		String message = null; // 処理後に画面に表示するメッセージ
		try {

			MemberDao memberDao = new MemberDao();

			// DAOクラスに渡すために会員情報クラスに値を格納
			MemberInformation memberInfo = new MemberInformation(username, usermail, usertel,
					useraddress, userbirthday, userjob, userpassword);

			// 新規登録
			memberDao.insertMember(memberInfo);
			message = "会員情報を登録しました";

			// 新規登録または更新した情報を再度画面に表示
			request.setAttribute("memberMemo", memberInfo);

		} catch (CampusException e) {
			message = e.getMessage();
			request.setAttribute("error", "true");
			e.printStackTrace();
		}
		// 次の画面に遷移
		request.setAttribute("message", message);
		request.getRequestDispatcher("Register.jsp").forward(request, response);
	}
}