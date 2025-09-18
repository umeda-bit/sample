<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.MemberInformation" %>
<%@ page import="dao.MemberDao" %>
<%@ page import="controller.MemberMemoServlet" %>
<%@ page import="controller.MemberServlet" %>
<!DOCTYPE html>
<html>
		<!-- 一覧表示 -->
		<section class="py-5">
			<div class="container">
				<h1 class="my-4">会員一覧</h1>
				<!-- 会員一覧 -->
				<table class="table table-hover">
					<!-- 見出し部分 -->
					<thead class="thead-dark">
						<tr>
							<th scope="col">ユーザーID</th>
							<th scope="col">ユーザー名</th>
							<th scope="col">メールアドレス</th>
							<th scope="col">電話番号</th>
							<th scope="col">住所</th>
							<th scope="col">生年月日</th>
							<th scope="col">職業</th>
							<th scope="col">パスワード</th>
							<th scope="col">登録日</th>
							<th scope="col">更新日</th>
						</tr>
					</thead>
					<!-- 一覧部分 -->
					<!-- データベースから取得した学生の情報一覧をリクエストスコープから取得します -->
					<!-- eclipseが未検査キャストの警告を出しますが無視して大丈夫です -->
					<tbody>
					<%
					MemberDao memberDao = new MemberDao();
					List<MemberInformation> memberList = memberDao.findAllMember();
					%>
					<%
					 //拡張for文
					for(MemberInformation memberinfo : memberList) {
					%>
						<tr>
							<td><%= memberinfo.getUserId() %></td>
							<td><%= memberinfo.getUserName() %></td>
							<td><%= memberinfo.getUserMail() %></td>
							<td><%= memberinfo.getUserTel() %></td>
							<td><%= memberinfo.getUserAddress() %></td>
							<td><%= memberinfo.getUserBirthday() %></td>
							<td><%= memberinfo.getUserJob() %></td>
							<td><%= memberinfo.getUserPassword() %></td>
							<td><%= memberinfo.getUser_Created_Datetime() %></td>
							<td><%= memberinfo.getUser_Updated_Datetime() %></td>
						</tr>
					<% } %>
					</tbody>
				</table>
			</div>
		</section>
	</body>
</html>