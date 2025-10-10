<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<!-- 共通パーツ（ヘッダー）読み込み -->
<jsp:include page="common/ShoppingPage_header.jsp" />
</head>
<body>
	<!-- ロゴの表示・検索フォーム・カートへの遷移ボタン -->
	<jsp:include page="common/navi.jsp" />
	<h1>購入画面</h1>
	<!-- 商品リスト -->
	<section class="py-5">
		<div class="container">
			<div id="itemList" class="fs-2" style="color: red;"></div>
			<%
			if (request.getAttribute("message") != null) {
			%>
			<h3 style="color: red;"><%=request.getAttribute("message")%></h3>
			<%
			} else {
			%>
			<!-- 商品陳列部分 -->
			<form action="ShoppingCartServlet" method="post">
				<div class="row" id="item">
					<div class="col-md-3 form-group">
						<a href="RegisterServlet?item_cd=1" style="text-align: center;">
							<!-- 画像 --> <img src="" alt="商品１" width="200" height="200"
							id="image">
						</a>
					</div>
					<div class="col-md-3 form-group">
						<!-- 商品名 -->
						<span id="item_name"></span> <br> <br>
						<!-- 数量 -->
						<label>個数：</label> <input class="fs-5" id="quantity"
							name="quantity" style="border: none;" readonly></input><br>
						<br>
						<!-- 小計 -->
						<span>小計(￥)</span>
						<h3 id="subTotal"></h3>
					</div>
					<br>
				</div>
				<!-- 商品コード取得用(ユーザーからは不可視) -->
				<input id="item_cd" name="item_cd" hidden></input>
				<!-- 会員ID -->
				<label>会員ID：</label> <input type="text" name="member_id"
					placeholder="会員IDを入力" required></input>
				<!-- 決済方法 -->
				<label>決済方法：</label> <select name="payment_method"
					id="payment_method" style="margin: 5px; padding: 5px;">
					<option value="0">代引き</option>
					<option value="1">クレジット</option>
					<option value="2">現金</option>
				</select> <br>
				<!-- 備考 -->
				<label>備考</label> <input type="text" name="remarks"
					placeholder="空欄でも構いません。" style="margin: 5px; padding: 5px;"></input>
				<!-- 購入ボタン -->
				<h4 style="margin: 20px; padding: 20px;">
					<button class="btn btn-warning btn-form display-4 fw-bold"
						style="border: 2px solid black;" onclick="deleteItemInfo()">注文確定</button>
				</h4>
			</form>
			<%
			}
			%>
		</div>
	</section>
	<script>
		//セッションから取得
		const quantity = sessionStorage.getItem('quantity');
		const item_name = sessionStorage.getItem('item_name');
		const item_cd = sessionStorage.getItem('item_cd');

		const price = sessionStorage.getItem('price');
		const image = document.getElementById("image");
		//画像URL
		image.src = 'images/item_' + item_cd + '.png';
		//商品名の表示
		document.getElementById("item_name").innerHTML = "商品名：" + item_name;
		//数量の表示
		document.getElementById("quantity").value = quantity;
		//小計の表示
		document.getElementById("subTotal").innerHTML = price * quantity;
		//商品コードの出力
		document.getElementById("item_cd").value = item_cd;

		//セッションに保存されているデータを削除
		function deleteItemInfo(){
			  sessionStorage.removeItem('quantity');
			  sessionStorage.removeItem('item_name');
			  sessionStorage.removeItem('item_cd');
			  sessionStorage.removeItem('price');
			}
	</script>
	<!-- 共通パーツ（フッター） -->
	<jsp:include page="common/footer.jsp" />
</body>
</html>