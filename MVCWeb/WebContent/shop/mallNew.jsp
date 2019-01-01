<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h2><span class="label label-danger">NEW</span></h2>
			</div>
		</div>
		<div class="row">
		<c:if test="${newList==null || empty newList}">
			<div class="col-md-3">
				<h3>상품 준비 중</h3>
			</div>
		</c:if>
		
		<c:if test="${newList!=null && not empty newList }">
			<c:forEach var="pd" items="${newList}" varStatus="status">
			<!-- varStatus속성을 이용하면 반복문의 상태정보를 얻어낼 수 있다. 
				반복문 횟수 : ${status.count}
				인덱스 번호 : ${status.index}
			-->
				<div class="col-md-3">
					<a href="prodDetail.do?pnum=${pd.pnum}"> 
						<img src="product_images/${pd.pimage1}" class="img-responsive" alt="상품이미지">
					</a>
					<h2>${pd.pname}</h2>
					<p>
						<del><fmt:formatNumber value="${pd.price}" pattern="###,###" /></del>원<br> 
						<span style="color: blue"><b><fmt:formatNumber value="${pd.saleprice}" pattern="###,###" /></b></span>원<br> 
						<span class="label label-warning">${pd.percent}%할인</span><br> 
						<span style="color: green"><b>${pd.point}</b></span>POINT<br>
					</p>
				</div> <!-- col-md-3 end -->
				
				<c:if test="${status.count mod 4 ==0}">
					</div>
					<div class="row"><!-- 줄바꿈. 기존행을 닫고 새로운 행을 시작하자 -->
				</c:if>
				
			</c:forEach>
		</c:if>
		</div>
		<!--row end  -->
	</div>
	<!-- container end -->
</div>
<!-- section end -->

