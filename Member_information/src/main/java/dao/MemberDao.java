package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CampusException;
import model.MemberInformation;

/**
 * 学生情報関連のDAOクラス
 */
public class MemberDao extends BaseDao {
	/**
	 * コンストラクタ<br>
	 * スーパークラスのコンストラクタ（DB接続）を実施します
	 * @throws CampusException DB接続に失敗した場合に発生します
	 */
	public MemberDao() throws CampusException {
		super();
	}

	/**
	 * 会員情報テーブルに登録されているすべての学生情報を検索します<br>
	 * 検索結果が0件の場合は空のリストを返却します
	 * @return 会員情報の入ったリスト
	 * @throws CampusException 検索失敗の際に発生します
	 **/
	public List<MemberInformation> findAllMember() throws CampusException {
		// 会員情報のリスト
		ArrayList<MemberInformation> memberList = new ArrayList<>();
		try {
			// SQL文
			String sql = "SELECT * FROM user_info";

			// 検索の実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// 検索結果から会員情報の各項目を取得してリストに格納
			while (rs.next()) {

				String userid = rs.getString("user_id");
				String username = rs.getString("user_name");
				String usermail = rs.getString("mail");
				String usertel = rs.getString("tel");
				String useraddress = rs.getString("address");
				String userbirthday = rs.getString("birthday");
				String userjob = rs.getString("job");
				String userpassword = rs.getString("password");
				String user_created_datetime = rs.getString("created_datetime");
				String user_updated_datetime = rs.getString("updated_datetime");

				MemberInformation member = new MemberInformation(userid, username, usermail,
						usertel, useraddress, userbirthday, userjob,
						userpassword, user_created_datetime, user_updated_datetime);
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("会員情報の取得に失敗しました");
		}

		// 会員情報のリストを返却
		return memberList;
	}

	/**
	 * 指定された会員情報をDBに新規追加します<br>
	 * 登録先は会員情報テーブル"user_info"です
	 * @param memberMemo 会員情報
	 * @throws CampusException 会員情報の登録に失敗した際に発生します
	 */
	public void insertMember(MemberInformation memberinfo) throws CampusException {
		// 学生メモ情報からDBに登録する値を取得
		//String userid = memberMemo.getUserId(); // ユーザーID
		String username = memberinfo.getUserName(); // ユーザー名
		String usermail = memberinfo.getUserMail(); // メールアドレス
		String usertel = memberinfo.getUserTel(); // 電話番号
		String useraddress = memberinfo.getUserAddress();
		String userbirthday = memberinfo.getUserBirthday();
		String userjob = memberinfo.getUserJob();
		String userpassword = memberinfo.getUserPassword();
		//String user_created_datetime = memberMemo.getUser_Created_Datetime();
		//String user_updated_datetime = memberMemo.getUser_Updated_Datetime();

		try {
			// 会員情報テーブルへの追加
			String sql = "INSERT INTO user_info(user_name, mail,"
					+ "tel, address, birthday,"
					+ "job, password)"
					+ "VALUE(?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, usermail);
			ps.setString(3, usertel);
			ps.setString(4, useraddress);
			ps.setString(5, userbirthday);
			ps.setString(6, userjob);
			ps.setString(7, userpassword);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("会員情報の登録に失敗しました");
		}
	}

	public List<MemberInformation> findMember(String user_id, String user_name) throws CampusException {
		// 会員情報のリスト
		ArrayList<MemberInformation> memberList = new ArrayList<>();
		
		try {
			// SQL文
			String sql = "SELECT * FROM user_info WHERE user_id=? OR user_name=?";

			// 検索の実行
			ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, user_name);
			rs = ps.executeQuery();

			// 検索結果から会員情報の各項目を取得してリストに格納
			while (rs.next()) {

				String userid = rs.getString("user_id");
				String username = rs.getString("user_name");
				String usermail = rs.getString("mail");
				String usertel = rs.getString("tel");
				String useraddress = rs.getString("address");
				String userbirthday = rs.getString("birthday");
				String userjob = rs.getString("job");
				String userpassword = rs.getString("password");
				String user_created_datetime = rs.getString("created_datetime");
				String user_updated_datetime = rs.getString("updated_datetime");

				MemberInformation member = new MemberInformation(userid, username, usermail,
						usertel, useraddress, userbirthday, userjob,
						userpassword, user_created_datetime, user_updated_datetime);
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("会員情報の取得に失敗しました");
		}

		return memberList;

	}

}