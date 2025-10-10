package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import exception.SalesSystemException;
import model.OrderInformation;

/**
 * 検索画面のサーブレットクラス
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 画面に入力された注文情報を取得
		request.setCharacterEncoding("UTF-8");
		String quantity = request.getParameter("quantity");
		String item_cd = request.getParameter("item_cd");
		String payment_method = request.getParameter("payment_method");
		String remarks = request.getParameter("remarks");
		String member_id = request.getParameter("member_id"); 

		String nextPage = null;

		try {

			CustomerDao customerDao = new CustomerDao();
			OrderInformation orderinfo = new OrderInformation(item_cd, quantity,
					payment_method, remarks, member_id);
			
			customerDao.InsertOrderInfo(orderinfo);
			
			String message = "購入が完了しました。"; 
			request.setAttribute("message", message);
			
			// 検索結果表示画面へ遷移する準備
			nextPage = "Payment.jsp";

		} catch (SalesSystemException e) {
			// エラーの場合、メッセージをSearch.jspに表示
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			// 検索結果表示画面へ遷移する準備
			nextPage = "Payment.jsp";

		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}