<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/top.jsp" />
<div align="center" class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1 table-responsive">
				<h1>${loginUser.name}[${loginUser.userid}]님 장바구니</h1>
				<!-- 주문 폼 시작--------- -->
				<form name="orderF" id="orderF">
					<table class="table table-bordered">
						<tr class="info">
							<th class="text-center">번호</th>
							<th class="text-center">상품명</th>
							<th class="text-center">수량</th>
							<th class="text-center">단가</th>
							<th class="text-center">금액</th>
							<th class="text-center">삭제</th>
						</tr>
						
						<!-- ---------------- -->
						<c:forEach var="p" varStatus="st" items="${cartList}">
							<tr>
								<td><input type="checkbox" name="pnum"> ${p.pnum}</td>
								<td><img width="160px" class="img img-responsive"
									src="../product_images/${p.pimage1}"> <br> a</td>
								<td><input type="number" name="oqty" id="oqty${st.index}" size="3"
									value="${p.pqty}" min="0" max="50">개
									<button type="button" onclick="edit('${p.pnum}','${st.index}')"
										class="btn btn-default">수정</button></td>
								<td class="text-right"><fmt:formatNumber value="${p.saleprice}"
										pattern="#,###" />원<br> <span class="label label-danger">
										${p.point}</span> POINT</td>
								<td class="text-right"><b> <fmt:formatNumber value="${p.totalPrice }"
											pattern="#,###" />원<br> <span
										class="label label-primary"> ${p.totalPoint}</span> POINT
								</b></td>
								<td><a href="javascript:del('${p.pnum}')" class="btn btn-default"
									role="button">삭제</a></td>
							</tr>
						</c:forEach>
						<!-- ---------------- -->
						
						<tr>
							<td colspan="3" class="text-right"><b>장바구니 총액:</b> <span
								style="color: red"> <fmt:formatNumber value="${cartMap.cartTotalPrice}"
										pattern="#,###" /> 원
							</span><br> <b>장바구니 총포인트:</b> <span class="label label-info">
									${cartMap.cartTotalPoint}</span>POINT</td>
							<td colspan="3">
								<button type="button" class="btn btn-warning">주문하기</button>
								<button type="button" onclick="history.go(-2)"
									class="btn btn-success">계속쇼핑</button>
							</td>
						</tr>

					</table>



				</form>
				<!-- 주문 폼 끝------------ -->

			</div>

		</div>

	</div>
</div>
<!-- 삭제 form 시작------------ -->
<form name="df" id="df" method="POST" action="cartDel.do">
	<input type="hidden" name="pnum" id="delPnum">
</form>
<!-- -------------------------- -->

<!-- 수정 form 시작---------------- -->
<form name="ef" id="ef" method="get" action="cartEdit.do">
	<input type="hidden" name="pnum" id="editPnum"> <input
		type="hidden" name="oqty" id="editOqty">
</form>
<!-- -------------------------- -->
<script type="text/javascript">
	var del = function(pnum){
		var yn=confirm(pnum+'번 상품을 삭제할까요?');
		if(yn){
			$('#delPnum').val(pnum);
			df.submit();
		}
	}
	
	var edit = function(pnum,index){
		$('#editPnum').val(pnum);
		$('#editOqty').val($('#oqty'+index).val());
		$('#ef').prop('method','POST');
		$('#ef').submit();
	}
</script>

<jsp:include page="/foot.jsp" />