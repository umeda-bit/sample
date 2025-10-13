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
	<h2>カート内の商品</h2>
	<!-- 商品リスト -->
	<section class="py-5">
		<!-- 商品陳列部分 -->
		<div class="container" id="itemList">
			<!-- カート内が空の場合にメッセージが表示される。 -->
			<div class="fs-4 fw-bold" id="emptyMessage" style="color: red;"></div>
			<script>
			var k = 0;
			const quantity = [];
			const item_name = []; 
			const item_cd = [];
			const price = [];
			const item_element = [];
			const image = [];
			var subTotal = 0;
			var Total = 0;
			//注文数カウンター		
			const quantity_element = [];
			const downbutton = [];
			const upbutton = [];
			const amount = [];
			const deleteItemButton = [];
			</script>
			<%
			for (int i = 1; i <= 4; i++) {
			%>
			<div class="row justify-content-center border border-dotted"
				id="item<%=i%>"
				style="border: 2px solid black; margin: 10px; padding: 10px;">
				<div class="col-md-2 form-group">
					<a href="OrderServlet?item_cd=<%=i%>"> <!-- 画像 --> <img src=""
						alt="リンク切れ" width="100" height="100" id="image<%=i%>">
					</a>
				</div>
				<div class="col-md-2 form-group">
					<!-- 商品名 -->
					<span id="item_name<%=i%>"></span> <br>
					<!-- 小計 -->
					<span>小計(￥)</span>
					<h3 id="amount<%=i%>">***小計を表示***</h3>
				</div>
				<!-- 数量カウンター -->
				<div class="col-md-2 form-group" id="counter">
					<button type="button" id="minus<%=i%>">－</button>
					<input type="text" pattern="[0-9]*" min="1" max="99" value="0"
						class="quantity" name="quantity" id="quantity<%=i%>" size="1">
					<button type="button" id="plus<%=i%>">＋</button>
					<br> <br>
				</div>
				<!-- 削除ボタン -->
				<div class="col-md-3">
					<button class="btn btn-sm btn-outline-danger"
						id="deleteItemButton<%=i%>">
						<i class="bi bi-trash"></i>
					</button>
				</div>
			</div>
			<script>
			quantity.push(sessionStorage.getItem('quantity:'+(k+1)));
			item_name.push(sessionStorage.getItem('item_name:'+(k+1)));
			item_cd.push(sessionStorage.getItem('item_cd:'+(k+1)));
			price.push(sessionStorage.getItem('price:'+(k+1)));
						
			image.push(document.getElementById("image"+(k+1)));
			item_element.push(document.getElementById('item'+(k+1)));
	
			//画像URL
			image[k].src = 'images/item_' + item_cd[k] + '.png';
			//商品名の表示
			document.getElementById("item_name"+(k+1)).innerHTML = "商品名：" + item_name[k];
			//小計の表示
			subTotal= price[k]*quantity[k];
			document.getElementById("amount"+(k+1)).innerHTML = subTotal;
			quantity_element.push(document.getElementById('quantity'+(k+1)));
			downbutton.push(document.getElementById('minus'+(k+1)));
			upbutton.push(document.getElementById('plus'+(k+1)));
			//削除用
			deleteItemButton.push(document.getElementById('deleteItemButton'+(k+1)));
			  
			//初期値は注文画面からセッションで取得
			quantity_element[k].value = quantity[k];

			//合計金額の初期値		
			Total += subTotal;
			
			//数量0の商品は非表示
			if(quantity[k]==null){
					item_element[k].remove();
				}

			k++;
			</script>
			<%
			}
			%>
		</div>
		<div class="container">
			<div class="row justify-content-center">
				<!-- 合計金額 -->
				<div class="col-md-3">
					<p>
						合計金額：<span class="fs-4" id="Total"></span>
					</p>
					<!-- 購入ボタン -->
					<button class="btn btn-warning btn-form display-4">
						<!-- 最終購入画面へ遷移(Payment.jsp) -->
						<a class="navbar-brand" href="Payment.jsp" onclick="Verified()">購入する</a>
					</button>
				</div>
			</div>
		</div>
	</section>
	<script>
	const Total_element = document.getElementById("Total");
	Total_element.innerHTML = Total;
	for(let k=0;k<4;k++){
		//即時関数
		(function(n){
			//ボタンが押されたら減算
			downbutton[k].addEventListener('click', (event) => {
				//0未満にならないようにする
				if(quantity_element[k].value >= 2) {
					quantity_element[k].value--;
					document.getElementById('amount'+(k+1)).innerHTML = price[k]*quantity_element[k].value;
					//合計金額格納変数を0に戻す
					Total = 0;
					for(let l=0;l<4;l++){
						Total += price[l]*quantity_element[l].value;
						}
					Total_element.innerHTML = Total;
					}
				});

			//ボタンが押されたら加算
			upbutton[k].addEventListener('click', (event) => {
				quantity_element[k].value++;
				document.getElementById('amount'+(k+1)).innerHTML = price[k]*quantity_element[k].value;
				//合計金額格納変数を0に戻す
				Total = 0;
				for(let l=0;l<4;l++){
					Total += price[l]*quantity_element[l].value;
					}
				Total_element.innerHTML = Total;
				});

			//フォームに入力された値をもとに計算
			quantity_element[k].addEventListener('input', (event) => {
				document.getElementById('amount'+(k+1)).innerHTML = price[k]*quantity_element[k].value;
				//合計金額格納変数を0に戻す
				Total = 0;
				//更新された合計金額を表示
				for(let l=0;l<4;l++){
					Total += price[l]*quantity_element[l].value;
					}
				Total_element.innerHTML = Total;
				});

			//ゴミ箱アイコン押下時に削除
			deleteItemButton[k].addEventListener('click', (event) => {
				confirm("商品をカートから削除しますか？");
				while (item_element[k].firstChild) {
					item_element[k].removeChild(item_element[k].firstChild);
					}
				//セッションに保存されているデータを削除
				sessionStorage.removeItem('quantity:'+(k+1));
				sessionStorage.removeItem('item_name:'+(k+1));
				sessionStorage.removeItem('item_cd:'+(k+1));
				sessionStorage.removeItem('price:'+(k+1));
				item_element[k].innerHTML = '<h4 style="color: red;">アイテムを削除しました。</h4>';
				});

			})(k);

		}

	//購入ボタン押下時に処理を行う
	function Verified(){
		for(let i=0;i<4;i++){
			  const quantity = quantity_element.value;
			  //数量
			  sessionStorage.setItem('quantity:'+item_cd, quantity);
			  //商品名
			  sessionStorage.setItem('item_name:'+item_cd, item_name);
			  //商品コード
			  sessionStorage.setItem('item_cd:'+item_cd, item_cd);
			  //単価
			  sessionStorage.setItem('price:'+item_cd, price);
			  
			sessionStorage.setItem('item_cd:'+(i+1),quantity_element[i].value);
			sessionStorage.setItem('quantity:'+(i+1),quantity_element[i].value);
			
			}
		}
	
	</script>
	<!-- 共通パーツ（フッター） -->
	<jsp:include page="common/footer.jsp" />
</body>
</html>