<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録</title>
</head>
<body>
	<%
	// サーブレットから詳細の情報を取得（新規登録の場合はすべて空文字）
			MemberInformation memberinfo = (MemberInformation) request.getAttribute("memberMemo");
	%>
	<form action="MemberMemoServlet" method="post">
		<div class="row">
			<div class="col-md-12 form-group">
				<label class="form-control-label">ユーザーID</label> <input type="text"
					name="user_id" value="<%=memberinfo.getUserID()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">ユーザー名</label> <input type="text"
					name="user_name" value="<%=memberinfo.getUserName()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">メールアドレス</label> <input type="text"
					name="mail" value="<%=memberinfo.getUserMail()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">電話番号</label> <input type="text"
					name="tel" value="<%=memberinfo.getUserTel()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">住所</label> <input type="text"
					name="address" value="<%=memberinfo.getUserAddress()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">生年月日</label> <input type="text"
					name="birthday" value="<%=memberinfo.getUserBirthday()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">職業</label> <input type="text"
					name="job" value="<%=memberinfo.getUserJob())%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">パスワード</label> <input type="text"
					name="password" value="<%=memberinfo.getUserPassword()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">登録日</label> <input type="text"
					name="created_datetime" value="<%=memberinfo.getUser_Created_Datetime()%>"
					required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">更新日</label> <input type="text"
					name="updated_datetime" value="<%=memberinfo.getUser_Updated_Datetime()%>"
					required="required" class="form-control">
			</div>
		</div>
	</form>
</body>
</html>