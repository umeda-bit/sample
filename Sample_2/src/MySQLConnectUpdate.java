import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnectUpdate {
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			// ①JDBCドライバの登録（インスタンス化）
			Class.forName("com.mysql.cj.jdbc.Driver");

			// ②データベースへの接続
			String url  = "jdbc:mysql://localhost/user";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);

			// ③ステートメントオブジェクトの取得
			String sql = "UPDATE user_list SET name = 'スクー次郎' WHERE id = ?";
			ps = con.prepareStatement(sql);

			// ステートメントオブジェクトは「？」を任意の文字や数値に置き換えることが可能です
			//TODO:指定するIDはみなさまの環境に合わせて変更してください！
			ps.setInt(1, 5);

			// ④SQL操作
			int count = ps.executeUpdate();
			System.out.println(count + "件処理しました");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ⑥データベースとの接続を終了
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}