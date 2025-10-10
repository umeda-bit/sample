package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exception.SalesSystemException;
import model.SalesInformation;

/**
 * 売上情報関連のDAOクラス
 */
public class MemberDao extends BaseDao {
	/**
	 * スーパークラスのコンストラクタ（DB接続）を実施
	 * @throws MembershipException DB接続失敗時に発生
	 */
	public MemberDao() throws SalesSystemException {
		super();
	}
	
	public String findItem_Name(SalesInformation salesinfo) throws SalesSystemException {
		
		// 商品名を格納する変数
		String item_name = null;
		
		// 入力された商品コードを変数に格納
		String item_cd = salesinfo.getItem_Cd();
		
		try {
			// SQL文
			String sql = "SELECT item_name FROM m_item WHERE item_cd=?";
			
			// SQL実行
			ps = con.prepareStatement(sql);
			ps.setString(1, item_cd);
			rs = ps.executeQuery();
			
			// 商品名を取得
			if (rs.next()) {
				item_name = rs.getString("item_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("該当の商品はありませんでした。");
		}
		
		// 商品名を返却
		return item_name;
		
	}
	
	/**
	 * 4つのテーブル(order, order_detail, member, m_item)に登録されている売上情報を検索
	 * 検索結果が0件の場合は空のリストを返却
	 * @return 売上情報の入ったリスト
	 * @throws MembershipException 検索失敗の際に発生
	 **/
	public List<SalesInformation> findOrders(SalesInformation salesinfo) throws SalesSystemException {
		// 売上情報を格納するリスト
		ArrayList<SalesInformation> orderList = new ArrayList<>();

		// 入力された項目値をカプセル化
		String order_date_start = salesinfo.getOrder_Date_Start();
		String order_date_end = salesinfo.getOrder_Date_End();
		String member_id = salesinfo.getMember_Id();
		String payment_method = salesinfo.getPayment_Method();
		String delivery_date_flag = salesinfo.getDelivery_Date_Flag();
		String item_cd = salesinfo.getItem_Cd();

		// SQL文の条件式を格納する変数
		// 初期値は全検索
		String date_term = "(order_date LIKE '%')";
		String member_id_term = "(member_id LIKE '%')";
		String payment_method_term = "(payment_method LIKE '%')";
		String delivery_date_flag_term = "(delivery_date LIKE '%')";
		String item_cd_term = "(order_detail.item_cd LIKE '%')";
		
		// 【エラーチェック】入力値をコンソールで確認
		System.out.println("開始日："+order_date_start);  
		System.out.println("終了日："+order_date_end);
		System.out.println("会員ID："+member_id);
		System.out.println("支払方法："+payment_method);
		System.out.println("納品済or未納："+delivery_date_flag);
		System.out.println("商品コード："+item_cd);
		
		// 入力された値に応じて分岐処理
		// 注文日付
		// orderテーブル
		if (!order_date_start.equals("") && !order_date_end.equals("")) {

			date_term = "order_date BETWEEN '" + order_date_start + "' AND '" + order_date_end+"'";

		} else if (!order_date_start.equals("") && order_date_end.equals("")) {

			date_term = "order_date='" + order_date_start+"'";

		} else if (order_date_start.equals("") && !order_date_end.equals("")) {

			date_term = "order_date='" + order_date_end+"'";

		} else {
			
		}

		// 会員ID
		// orderテーブル
		// 会員IDが入力された場合は、日付以外の検索条件を無視
		if (!member_id.equals("")) {

			member_id_term = "member_id=" + member_id;

		} else {

			// 決済方法
			// orderテーブル
			if (payment_method!=null && payment_method.equals("0")) {

				payment_method_term = "payment_method=0";

			} else if (payment_method!=null && payment_method.equals("1")) {

				payment_method_term = "payment_method=1";

			} else if (payment_method!=null && payment_method.equals("2")) {

				payment_method_term = "payment_method=2";

			} else {

			}

			// 登録or未登録判定
			// m_itemテーブル
			if (delivery_date_flag.equals("未納品")) {

				delivery_date_flag_term = "delivery_date IS NULL";

			} else if (delivery_date_flag.equals("納品済")) {

				delivery_date_flag_term = "delivery_date IS NOT NULL";

			} else {

			}

			// 商品コード
			// order_detailテーブル
			if (!item_cd.equals("")) {

				item_cd_term = "order_detail.item_cd=" + item_cd;

			} else {

			}
		}
		try {
			// SQL文
			String sql = "SELECT * FROM `order` "
					+ "INNER JOIN order_detail ON `order`.order_no = order_detail.order_no "
					+ "INNER JOIN `member` ON `order`.member_id = `member`.user_id "
					+ "INNER JOIN m_item ON order_detail.item_cd = m_item.item_cd "
					+ "WHERE "
					+ date_term + " AND "
					+ member_id_term + " AND "
					+ payment_method_term + " AND "
					+ delivery_date_flag_term + " AND "
					+ item_cd_term;
			
			// SQL文の内容をコンソールで確認
			System.out.println(sql); 
			
			// 検索の実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// 検索結果から会員情報の各項目を取得してリストに格納
			while (rs.next()) {

				String order_no_value = rs.getString("order_no"); //注文番号 orderテーブル
				Timestamp order_date_value = rs.getTimestamp("order_date"); //注文日 orderテーブル
				String member_id_value = rs.getString("member_id"); //会員ID orderテーブル	
				String user_name_value = rs.getString("user_name"); //会員名 memberテーブル
				String payment_method_value = rs.getString("payment_method"); //支払方法 orderテーブル
				String total_amount_value = rs.getString("total_amount"); //税込合計金額 orderテーブル
				String delivery_date_value = rs.getString("delivery_date"); //納品済or未納 m_itemテーブル
				String remarks_value = rs.getString("remarks"); //備考 orderテーブル
				int row_no_value = rs.getInt("row_no"); //注文行
				String item_cd_value = rs.getString("item_cd"); //商品コード
				String item_name_value = rs.getString("item_name"); //商品名
				String unit_price_value = rs.getString("unit_price"); //単価
				String quantity_value = rs.getString("quantity"); //数量
				String subtotal_value = rs.getString("subtotal"); //小計

				SalesInformation orderinfo = new SalesInformation(order_no_value, order_date_value, member_id_value,
						user_name_value, payment_method_value, total_amount_value, delivery_date_value,
						remarks_value, row_no_value, item_cd_value, item_name_value, unit_price_value, quantity_value, subtotal_value);

				orderList.add(orderinfo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("注文情報の取得に失敗しました");
		}
	
		// 売上情報格納リストを返却
		return orderList;

	}

}