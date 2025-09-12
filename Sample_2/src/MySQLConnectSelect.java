import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnectSelect {
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// ①JDBCドライバの登録（インスタンス化）
			Class.forName("com.mysql.cj.jdbc.Driver");

			// ②データベースへの接続
			String url  = "jdbc:mysql://localhost/user";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);

			// ③ステートメントオブジェクトの取得
			String sql = "SELECT * FROM user_list";
			ps = con.prepareStatement(sql);

			// ④SQL操作
			rs = ps.executeQuery();

			// ⑤結果の取得
			while(rs.next()) {
				System.out.print("id:" + rs.getInt("id") + " ");
				System.out.print("name:" + rs.getString("name") + " ");
				System.out.println("age:" + rs.getInt("age"));
			}

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
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}