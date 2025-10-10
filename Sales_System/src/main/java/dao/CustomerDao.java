package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import exception.SalesSystemException;
import model.OrderInformation;

/**
 * 注文情報関連のDAOクラス
 */
public class CustomerDao extends BaseDao {
	/**
	 * スーパークラスのコンストラクタ（DB接続）を実施
	 * @throws MembershipException DB接続失敗時に発生
	 */
	public CustomerDao() throws SalesSystemException {
		super();
	}

	public HashMap<String, String> findItem_Info(String item_cd) throws SalesSystemException {

		// 商品名を格納する変数
		HashMap<String, String> hashmap = new HashMap<String, String>();

		String item_name = null;
		String price = null;
		String url = null;

		try {
			// SQL文
			String sql = "SELECT item_name, price FROM m_item WHERE item_cd=?";

			// SQL実行
			ps = con.prepareStatement(sql);
			ps.setString(1, item_cd);
			rs = ps.executeQuery();

			// 商品名を取得
			if (rs.next()) {
				item_name = rs.getString("item_name");
				price = rs.getString("price");
			}

			// 商品の画像URLを取得
			url = "item_" + item_cd;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("該当の商品はありませんでした。");
		}

		hashmap.put("item_name", item_name);
		hashmap.put("price", price);
		hashmap.put("url", url);

		// 商品名を返却
		return hashmap;

	}

	public void InsertOrderInfo(OrderInformation orderinfo) throws SalesSystemException {

		//商品コード
		String item_cd = orderinfo.getItem_Cd();

		//数量
		String quantity = orderinfo.getQuantity();

		//会員ID
		String member_id = orderinfo.getMember_Id();

		//支払方法
		String payment_method = orderinfo.getPayment_Method();

		//備考
		String remarks = orderinfo.getRemarks();

		//注文日・登録日付
		String order_date = LocalDateTime.now().toString();
		String regist_datetime = LocalDateTime.now().toString();

		//納品日(注文日から３日後に設定)
		String delivery_date = LocalDateTime.now().plusDays(3).toString();

		//注文番号
		int order_no = 0;
		//DBから取得した値を格納。
		String result = "";
		//SQL文
		String sql = "";

		try {
			// 一意制約を満たす注文番号を採番するまでループ処理を行う。
			while (result != "採番完了") {

				result = "採番完了";

				//注文番号を"１"から採番する。
				order_no++;
				sql = "SELECT order_no FROM `order` WHERE order_no=?";

				// SQL実行
				ps = con.prepareStatement(sql);
				ps.setInt(1, order_no);
				rs = ps.executeQuery();

				// 検索結果から会員情報の各項目を取得してリストに格納
				// 既に採番されている場合のみ処理する。
				while (rs.next()) {
					//既採番されている番号をループで表示
					result = rs.getString("order_no");
				}

				//【エラーチェック】既に採番されている番号を確認可能。
				System.out.println("既採番：" + result);

			}

			//エラーチェック用
			System.out.println(order_no);
			System.out.println(item_cd);

			//orderテーブルにデータ登録するSQL文
			sql = "INSERT INTO `order` (order_no, member_id, order_date, "
					+ "payment_method, delivery_date, remarks, "
					+ "regist_datetime) VALUES (?,?,?,?,?,?,?)";
			//orderテーブルに注文番号、支払方法、備考を登録
			ps = con.prepareStatement(sql);
			ps.setInt(1, order_no);
			ps.setString(2, member_id);
			ps.setString(3, order_date);
			ps.setString(4, payment_method);
			ps.setString(5, delivery_date);
			ps.setString(6, remarks);
			ps.setString(7, regist_datetime);
			ps.executeUpdate();
			
			//税抜金額
			int amount = 0;
			//税抜販売価格
			int price = 0;
			//注文行番号
			int row_no = 1;

			//金額を取得
			sql = "SELECT price FROM m_item WHERE item_cd=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, item_cd);
			ps.executeQuery();
			//ResultSetを採番時のものから更新
			rs = ps.executeQuery();
			// 検索結果から商品情報の税抜販売価格を取得してリストに格納
			while (rs.next()) {
				//DBから取得した値を格納
				price = rs.getInt("price");
				//エラーチェック
				System.out.println(price);
			}

			//税抜金額
			amount = price * Integer.parseInt(quantity);
			//order_detailテーブルにデータ登録するSQL文
			sql = "INSERT INTO order_detail (order_no, row_no, item_cd, quantity, amount) VALUES (?,?,?,?,?)";
			//orderテーブルに注文番号、商品コード、数量を登録
			ps = con.prepareStatement(sql);
			ps.setInt(1, order_no);
			ps.setInt(2, row_no);
			ps.setString(3, item_cd);
			ps.setString(4, quantity);
			ps.setInt(5, amount);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("注文登録に失敗しました。");
		}

	}

}