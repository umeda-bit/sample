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
	<h1>カート内の商品</h1>
	<!-- 商品リスト -->
	<section class="py-5">
		<!-- 商品陳列部分 -->
		<div class="container" id="itemList">
			<!-- カート内が空の場合にメッセージが表示される。 -->
			<div class="fs-4 fw-bold" id="emptyMessage" style="color: red;"></div>
			<%
			for (int i = 1; i <= 4; i++) {
			%>
			<div class="row" id="item<%=i%>">
				<div class="col-md-3 form-group">
					<a href="OrderServlet?item_cd=<%=i%>" style="text-align: center;">
						<!-- 画像 --> <img src="" alt="リンク切れ" width="200" height="200"
						id="image<%=i%>">
					</a>
				</div>
				<div class="col-md-3 form-group">
					<!-- 商品名 -->
					<span id="item_name"></span> <br> <br>
					<!-- 数量カウンター -->
					<div class="counter" id="counter">
						<button type="button" id="minus<%=i%>">－</button>
						<input type="text" pattern="[0-9]*" min="1" max="99" value="0"
							class="quantity" name="quantity" id="quantity<%=i%>" size="1">
						<button type="button" id="plus<%=i%>">＋</button>
						<br> <br>
						<!-- 小計 -->
						<span>小計(￥)</span>
						<h3 id="amount<%=i%>">***小計を表示***</h3>
					</div>
					<br>
					<!-- 削除ボタン -->
					<button class="btn btn-sm btn-outline-danger"
						onclick="deleteItem(<%=i%>)">
						<i class="bi bi-trash"></i>
					</button>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<!-- 購入ボタン -->
		<button class="btn btn-warning btn-form display-4">
			<!-- 最終購入画面へ遷移(Payment.jsp) -->
			<a class="navbar-brand" href="Payment.jsp" onclick="Verified()">購入</a>
		</button>
	</section>
	<script>
	const quantity = [];
	const item_name = []; 
	const item_cd = [];
	const price = [];
	const item_element = [];
	const image = [];
		
		//セッションから取得した値を配列に格納
		for(var i=1;i<=4;i++){
			quantity.push = sessionStorage.getItem('quantity:'+i);
			item_name.push = sessionStorage.getItem('item_name:'+i);
			item_cd.push = sessionStorage.getItem('item_cd:'+i);
			price.push = sessionStorage.getItem('price:'+i);
			
			image.push = document.getElementById("image"+i);
			item_element.push = document.getElementById('item'+i);
		}
	
     var j=0;
		for(var i=0;i<4;i++){
			if(item_cd[i]==null){
				item_element[i].remove();
				j++;
			}
		
		//画像URL
		image.src = 'images/item_' + item_cd[i] + '.png';
		//商品名の表示
		document.getElementById("item_name").innerHTML = "商品名：" + item_name[i];
		//小計の表示
		document.getElementById("amount"+(i+1)).innerHTML = price[i]*quantity[i];

		//注文数カウンター		
		  const quantity_element = [];
		  const downbutton = [];
		  const upbutton = [];
		  
		  quantity_element.push = document.getElementById('quantity'+(i+1));
		  downbutton.push = document.getElementById('minus'+(i+1));
		  upbutton.push = document.getElementById('plus'+(i+1));
		  
		//初期値は注文画面からセッションで取得
		  quantity_element[i].value = quantity[i];
		  
		//ボタンが押されたら減算
		  downbutton.addEventListener('click', (event) => {
		//0未満にならないようにする
		  if(quantity_element.value >= 2) {
			  quantity_element.value--;
			  document.getElementById("amount").innerHTML = price*quantity_element.value;
		  }
		  });

		//ボタンが押されたら加算
		  upbutton.addEventListener('click', (event) => {
			  quantity_element.value++;
			  document.getElementById("amount").innerHTML = price*quantity_element.value;
		  });

			if(j==4){
				document.getElementById("emptyMessage").innerHTML ="カート内にアイテムはありません。";
			}
		}

		//削除ボタン
		  function deleteItem(i){
			  confirm("商品をカートから削除しますか？");
			  while (item_element.firstChild) {
				  item_element.removeChild(item_element.firstChild);
				}
				//セッションに保存されているデータを削除
			  sessionStorage.removeItem('quantity'+i);
			  sessionStorage.removeItem('item_name'+i);
			  sessionStorage.removeItem('item_cd'+i);
			  sessionStorage.removeItem('price'+i);
			  item_element[i].innerHTML = '<h4 style="color: red;">アイテムを削除しました。</h4>';
			  }
		  function Verified(){
			  for(var i=1;i<=4;i++){
				  sessionStorage.setItem('quantity'+i,quantity_element[i].value);
				  }
			  }
	</script>
	<!-- 共通パーツ（フッター） -->
	<jsp:include page="common/footer.jsp" />
</body>
</html>