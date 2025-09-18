<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録</title>
</head>
<body>
	<form action="MemberMemoServlet" method="post">
		<div class="row">
			<div class="col-md-12 form-group">
				<label class="form-control-label">ユーザー名</label> <input type="text"
					name="user_name" required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">メールアドレス</label> <input type="text"
					name="mail" required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">電話番号</label> <input type="text"
					name="tel" required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">住所</label> <input type="text"
					name="address" required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">生年月日</label> <input type="text"
					name="birthday" required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">職業</label> <input type="text"
					name="job" required="required" class="form-control">
			</div>
			<div class="col-md-12 form-group">
				<label class="form-control-label">パスワード</label> <input type="text"
					name="password" required="required" class="form-control">
			</div>
		<button
			class="btn btn-lg btn-primary btn-block btn-login font-weight-bold mb-2"
			type="submit">登録</button>
	</form>
</body>
</html>