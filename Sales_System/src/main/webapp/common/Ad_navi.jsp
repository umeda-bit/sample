<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- ナビゲーション -->
<nav class="navbar navbar-expand-lg bg-success-subtle shadow">
	<div class="container">
		<!-- Topページへのリンク
		<a class="navbar-brand fs-3 fw-bold" href="Top.jsp"
			style="color: red;">楽〇市場</a> -->
		<!-- カートへのリンク -->
		<ul class="navbar-nav ml-auto">
			<li><a class="nav-link rounded-pill" href="Search.jsp"><span
					class="fs-6 fw-bold">検索</span></a></li>
			<li><a class="nav-link" href="Register.jsp"><span
					class="fs-6 fw-bold">商品登録</span></a></li>
		</ul>
	</div>
	<!-- CSS（bootstrap）の読み込み ※これがないとドロップダウンが有効化されない-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous">
		
	</script>
	<!-- CSS（bootstrap）の読み込み ※ゴミ箱アイコンなどを使用可能にする。 -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"
		rel="stylesheet">
	<!-- 未実装機能使用時ののメッセージ出力 -->
	<script>
		function Message() {
			alert("まだこの機能はありません。");
		}
	</script>
</nav>