package model;

/**
 * スタッフ情報クラス
 */
public class Staff {
	private String staffId; // スタッフID
	private String staffName; // スタッフ名
	private String loginPassword; // ログインパスワード

	/**
	 * コンストラクタ
	 * @param staffId スタッフID
	 * @param staffName スタッフ名
	 * @param loginPassword ログインパスワード
	 */
	public Staff(String staffId, String staffName, String loginPassword) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.loginPassword = loginPassword;
	}

	/**
	 * スタッフIDを返します
	 * @return staffId スタッフID
	 */
	public String getStaffId() {
		return staffId;
	}
	/**
	 * スタッフIDをセットします
	 * @param staffId セットする staffId
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	/**
	 * スタッフ名を返します
	 * @return staffName スタッフ名
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * スタッフ名をセットします
	 * @param staffName セットする staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	/**
	 * ログインパスワードを返します
	 * @return loginPassword ログインパスワード
	 */
	public String getLoginPassword() {
		return loginPassword;
	}
	/**
	 * ログインパスワードをセットします
	 * @param loginPassword セットする loginPassword
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}