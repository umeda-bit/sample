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
	 * 学生テーブルに登録されているすべての学生情報を検索します<br>
	 * 検索結果が0件の場合は空のリストを返却します
	 * @return 学生情報の入ったリスト
	 * @throws CampusException 検索失敗の際に発生します
	 **/
	public List<MemberInformation> findAllMember() throws CampusException {
		// 学生情報のリスト
		ArrayList<MemberInformation> memberList = new ArrayList<>();
		try {
			// SQL文
			String sql = "SELECT * FROM member";

			// 検索の実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// 検索結果から学生番号と学生名を取得してリストに格納
			while(rs.next()) {
				String userid = rs.getString("user_id");
				String username = rs.getString("user_name");
				MemberInformation member = new MemberInformation(user_id, user_name);
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("学生情報の取得に失敗しました");
		}

		// 学生情報のリストを返却
		return memberList;
	}

	/**
	 * 学生テーブルから指定された学生番号で登録されている学生情報を検索します<br>
	 * 検索結果が0件の場合はnullを返却します
	 * @param findMemberNumber 学生番号
	 * @return 学生情報
	 * @throws CampusException 検索失敗の際に発生します
	 */
	public MemberInformation findMember(String findMemberNumber) throws CampusException {
		// 学生情報
		MemberInformation member = null;
		try {
			// SQL文
			String sql = "SELECT * FROM member WHERE member_number = ?";

			// SQL文に学生番号をセットして検索を実行
			ps = con.prepareStatement(sql);
			ps.setString(1, findMemberNumber);
			rs = ps.executeQuery();

			// 検索結果から学生情報を作成
			while(rs.next()) {
				String userid = rs.getString("userid");
				String username = rs.getString("username");
				member = new MemberInformation(userid, username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生情報の取得に失敗しました");
		}
		// 学生情報を返却
		return member;
	}

	/**
	 * メモおよび学生テーブルから指定された学生番号で登録されている学生メモ情報を検索します<br>
	 * 検索結果が0件の場合はnullを返却します
	 * @param findMemberNumber 学生番号
	 * @return 学生メモ情報
	 * @throws CampusException 検索失敗の際に発生します
	 */
	public MemberInformation findMemberMemo(String findMemberNumber) throws CampusException {
		// 学生メモ情報
		// このクラスは学生情報とメモ情報を管理しています
		MemberInformation memberMemo = null;
		try {
			// SQL文
			String sql =
				"SELECT "	// 検索内容
				+ "member.member_number, "
				+ "member.member_name, "
				+ "staff.staff_id, "
				+ "staff.staff_name, "
				+ "memo.memo_id, "
				+ "memo.memo "
				+ "FROM "	// 検索対象（JOINで結合）
				+ "memo JOIN "
				+ "member ON memo.member_number = member.member_number JOIN "
				+ "staff ON memo.updated_staff_id = staff.staff_id "
				+ "WHERE "	// 検索条件
				+ "memo.member_number = ?";

			// SQL文に学生番号をセットして検索を実行
			ps = con.prepareStatement(sql);
			ps.setString(1, findMemberNumber);
			rs = ps.executeQuery();

			// 検索結果から学生メモ情報を作成
			while(rs.next()) {
				String memberNumber = rs.getString("member_number");
				String memberName = rs.getString("member_name");
				String staffId = rs.getString("staff_id");
				String staffName = rs.getString("staff_name");
				String memoId = rs.getString("memo_id");
				String memo = rs.getString("memo");
				memberMemo =
					new MemberInformation(memberNumber, memberName, staffId, staffName, memoId, memo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生メモ情報の取得に失敗しました");
		}
		// 学生メモ情報を返却
		return memberMemo;
	}

	/**
	 * 指定された学生メモ情報をDBに新規追加します<br>
	 * 登録先は学生テーブルとメモテーブルです
	 * @param memberMemo 学生メモ情報
	 * @throws CampusException 学生メモ情報の登録に失敗した際に発生します
	 */
	public void insertMember(MemberInformation memberMemo) throws CampusException {
		// 学生メモ情報からDBに登録する値を取得
		String memberNumber = memberMemo.getMemberNumber();	// 学生番号
		String memberName = memberMemo.getMemberName();	// 学生名
		String staffId = memberMemo.getStaffId();	// スタッフID
		String memo = memberMemo.getMemo();	// メモ

		try {
			// 学生テーブルへの追加
			String sql = "INSERT INTO member(member_number, member_name) VALUE(?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, memberNumber);
			ps.setString(2, memberName);
			ps.executeUpdate();

			// メモテーブルへの追加
			sql = "INSERT INTO memo(member_number, updated_staff_id, memo) VALUE(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, memberNumber);
			ps.setString(2, staffId);
			ps.setString(3, memo);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("学生情報の登録に失敗しました");
		}
	}

	/**
	 * 指定されたメモ情報を更新します<br>
	 * 今回は更新対象はメモのみにしています
	 * @param memberMemo 更新情報
	 * @param memoId 更新対象とするメモのID
	 * @throws CampusException メモ情報の更新に失敗した際に発生します
	 */
	public void updateMemo(MemberInformation memberMemo, String memoId) throws CampusException {
		// メモとスタッフを取得
		String memo = memberMemo.getMemo();
		String staffId = memberMemo.getStaffId();
		try {
			// 更新処理
			String sql = "UPDATE memo SET memo = ?, updated_staff_id = ? WHERE memo_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, memo);
			ps.setString(2, staffId);
			ps.setString(3, memoId);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("メモの更新に失敗しました");
		}
	}

	/**
	 * メモテーブルから指定された学生番号に該当するメモIDを取得します<br>
	 * 検索結果が無い場合はnullを返却します
	 * @param memberNumber 学生番号
	 * @return メモID
	 * @throws CampusException メモIDの取得に失敗した場合に発生します
	 */
	public String findMemoId(String memberNumber) throws CampusException {
		// メモID
		String memoId = null;
		try {
			// メモIDの検索
			String sql = "SELECT memo_id FROM memo WHERE member_number = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, memberNumber);
			rs = ps.executeQuery();
			while(rs.next()) {
				memoId = rs.getString("memo_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("メモIDの取得に失敗しました");
		}
		// メモIDの返却
		return memoId;
	}
}