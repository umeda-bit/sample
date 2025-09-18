package model;

/**
 * 会員情報クラス
 */
public class MemberInformation {

	private String userid; // ユーザーID
	private String username; // ユーザー名
	private String usermail; // メールアドレス
	private String usertel; // 電話番号
	private String useraddress; // 住所
	private String userbirthday; // 生年月日
	private String userjob; //職業
	private String userpassword; //パスワード
	private String user_created_datetime; //登録日
	private String user_updated_datetime; //更新日

	/**
	 * コンストラクタ
	 * @param username
	 * @param usermail
	 * @param usertel
	 * @param useraddress
	 * @param userbirthday
	 * @param userjob
	 * @param userpassword
	 */
	public MemberInformation(String username, String usermail,
			String usertel, String useraddress,
			String userbirthday, String userjob, String userpassword) {
		//this.userid = userid;
		this.username = username;
		this.usermail = usermail;
		this.usertel = usertel;
		this.useraddress = useraddress;
		this.userbirthday = userbirthday;
		this.userjob = userjob;
		this.userpassword = userpassword;
		//this.user_created_datetime = user_created_datetime;
		//this.user_updated_datetime = user_updated_datetime;
	}
	
	public MemberInformation(String userid, String username, String usermail,
			String usertel, String useraddress,
			String userbirthday, String userjob, String userpassword, String user_created_datetime, String user_updated_datetime) {
		this.userid = userid;
		this.username = username;
		this.usermail = usermail;
		this.usertel = usertel;
		this.useraddress = useraddress;
		this.userbirthday = userbirthday;
		this.userjob = userjob;
		this.userpassword = userpassword;
		this.user_created_datetime = user_created_datetime;
		this.user_updated_datetime = user_updated_datetime;
	}

	/**
	 * ユーザーIDを返します
	 * @return studentName
	 */
	public String getUserId() {
		return userid;
	}

	/**
	 * ユーザーIDをセットします
	 * @param studentName セットする studentName
	 */
	public void setUserId(String userid) {
		this.userid = userid;
	}
	
	/**
	 * ユーザー名を返します
	 * @return studentName
	 */
	public String getUserName() {
		return username;
	}

	/**
	 * ユーザー名をセットします
	 * @param studentName セットする studentName
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * メールアドレスを返します
	 * @return staffId スタッフID
	 */
	public String getUserMail() {
		return usermail;
	}

	/**
	 * メールアドレスをセットします
	 * @param staffId セットする staffId
	 */
	public void setUserMail(String usermail) {
		this.usermail = usermail;
	}

	/**
	 * 電話番号を返します
	 * @return staffName スタッフ名
	 */
	public String getUserTel() {
		return usertel;
	}

	/**
	 * 電話番号をセットします
	 * @param staffName セットする staffName
	 */
	public void setUserTel(String usertel) {
		this.usertel = usertel;
	}

	/**
	 * 住所を返します
	 * @return staffName スタッフ名
	 */
	public String getUserAddress() {
		return useraddress;
	}

	/**
	 * 生年月日をセットします
	 * @param staffName セットする staffName
	 */
	public void setUserBirthday(String userbirthday) {
		this.userbirthday = userbirthday;
	}

	/**
	 * 生年月日を返します
	 * @return staffName スタッフ名
	 */
	public String getUserBirthday() {
		return userbirthday;
	}

	/**
	 * 住所をセットします
	 * @param staffName セットする staffName
	 */
	public void setUserAddress(String useraddress) {
		this.useraddress = useraddress;
	}

	/**
	 * 職業を返します
	 * @return staffName スタッフ名
	 */
	public String getUserJob() {
		return userjob;
	}

	/**
	 * 職業をセットします
	 * @param staffName セットする staffName
	 */
	public void setUserJob(String userjob) {
		this.userjob = userjob;
	}

	/**
	 * パスワードを返します
	 * @return staffName スタッフ名
	 */
	public String getUserPassword() {
		return userpassword;
	}

	/**
	 * パスワードをセットします
	 * @param staffName セットする staffName
	 */
	public void setUserPassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	/**
	 * 登録日を返します
	 * @return staffName スタッフ名
	 */
	public String getUser_Created_Datetime() {
		return user_created_datetime;
	}

	/**
	 * 登録日をセットします
	 * @param staffName セットする staffName
	 */
	public void setUser_Created_Datetime(String user_created_datetime) {
		this.user_created_datetime = user_created_datetime;
	}
	
	/**
	 * 更新日を返します
	 * @return staffName スタッフ名
	 */
	public String getUser_Updated_Datetime() {
		return user_updated_datetime;
	}

	/**
	 * 更新日をセットします
	 * @param staffName セットする staffName
	 */
	public void setUser_pdated_Datetime(String user_updated_datetime) {
		this.user_updated_datetime = user_updated_datetime;
	}

}