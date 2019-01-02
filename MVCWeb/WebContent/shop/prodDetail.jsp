<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/top.jsp" />

<script src="js/jquery.magnifier.js"></script>

<div align="center" class="row">
	<c:if test="${pvo eq null}">
		<h2>해당 상품은 존재하지 않아요</h2>
	</c:if>
	<c:if test="${pvo ne null}">
		<div class="col-md-10 col-md-offset-1 table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th colspan="2"><h3>상품 정보</h3></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" width="50%">
							<%-- <a href="javascript:openPop('${pvo.pimage1}')">  --%>
								<img src="product_images/${pvo.pimage1 }" class="img img-responsive img-thumbnail magnify"
								data-magnifyby="2" data-magnifyduration="100">
							<!-- </a> -->
						</td>
						<td align="center" style="padding-left: 20px">
							<h4>
								<span class="label label-danger">${pvo.pspec}</span>
							</h4> 
							상품번호: ${pvo.pnum} <br> 
							상품이름: ${pvo.pname } <br> 
							정가:<del><fmt:formatNumber value="${pvo.price}" pattern="###,###" /></del>원<br>
							판매가:<span style="color: red; font-weight: bold">
								<fmt:formatNumber value="${pvo.saleprice}" pattern="###,###" /></span>원<br>
							할인율:<span style="color: red">${pvo.percent}%</span><br>
							POINT:<b style="color: green">[${pvo.point}]</b>POINT<br> 
							
							<!-- form시작---------- -->
							<form name="frm" id="frm" method="POST">
								<!-- 상품번호를 hidden으로 넘기자------ -->
								<input type="hidden" name="pnum" value="${pvo.pnum}">
								<!-- -------------------------------- -->
								<label for="oqty">상품갯수</label> <input type="number"
									name="oqty" id="oqty" min="1" max="50" size="2" value="1">
	
							</form> 
							<!-- form end------------ -->
							
							<button type="button" onclick="goCart(${pvo.pnum})" class="btn btn-primary">장바구니</button>
							<button type="button" onclick="goOrder(${pvo.pnum})" class="btn btn-warning">주문하기</button>
							<button type="button" onclick="goWish(${pvo.pnum})" class="btn btn-danger">위시리시트</button>
						</td>
					</tr>
					<tr style="border: 0">
						<td align="center"><img src="product_images/${pvo.pimage2}"
							class="img img-responsive"></td>
						<td align="center"><img src="product_images/${pvo.pimage3}"
							class="img img-responsive"></td>
					</tr>
					<tr>
						<td colspan="2">
							<p>상품설명</p> <pre>${pvo.pcontents}</pre>
						</td>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${loginUser eq null}">
				<button class="btn btn-success" data-target="#myModal" 
				data-toggle="modal">상품평쓰기</button>
				<span><b>로그인해야 이용 가능합니다.</b></span>
			</c:if>
			<c:if test="${loginUser ne null}">
				<!-- 리뷰 글쓰기 include--------------- -->
				<c:import url="reviewWrite.jsp"/>
				
				<!-- --------------------------------- -->
			</c:if>
			<!-- 리뷰 목록 보여주기------------------ -->
			<c:import url="reviewList.jsp" />
			<!-- ---------------------------------- -->
		</div>
	<!-- container end -->
	</c:if>
</div>
<!-- section end -->


<script type="text/javascript">
	/* 팝업창으로 상세 이미지를 보여주는 함수 */
	var openPop = function(pimg){
		var url = "product_images/"+pimg;
		var imgObj = new Image();
		imgObj.src = url;
		
		//팝업창의 폭
		var w = imgObj.width+80;
		var h = imgObj.height+80;
		win = window.open(url,"imgView","width="+w+",height="+h+",left=100");
	}
	
	var goCart = function(pnum){
		frm.action = "user/cartAdd.do";
		frm.submit();
	}
	
	var goOrder = function(pnum){
		frm.action = "user/order.do";
		frm.submit();
	}
	
	var goWish = function(pnum){
		frm.action = "wish.do";
		frm.submit();
	}
</script>


<jsp:include page="/foot.jsp" />
