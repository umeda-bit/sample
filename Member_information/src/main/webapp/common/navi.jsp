<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Staff"%>

<!-- ナビゲーション -->
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
	<div class="container">

		<!-- 一覧表示へのリンクを入れています -->
		<ul class="navbar-nav ml-auto">
			<li class="nav-item active">
			<a class="nav-link" href="list.jsp">一覧表示</a>
			</li>
			<li class="nav-item active">
			<a class="nav-link"href="Register.jsp">登録</a>
			</li>
			<li class="nav-item active">
			<a class="nav-link"href="Search.jsp">検索</a>
			</li>
			<li class="nav-item active">
			<a class="nav-link"href="Delete.jsp">編集</a>
			</li>
		</ul>

	</div>
</nav>