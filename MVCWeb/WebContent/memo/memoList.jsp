<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- Latest compiled and minified CSS ---------------------- -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!----------------------------------------------------------->
</head>
<body>
	<div class="container">
		<!-- 글쓰기 form include-------------- -->
		<c:import url="/memo/memo.jsp" />
		<!-- ------------------------------- -->

		<div class="row mt-5">
			<div class="col-md-10 offset-md-1">
				<!-- <h1 class="text-center text-primary m-5">::한줄 메모장 목록::</h1> -->
				<table class="table table-hover">
					<tr class="bg-primary text-white">
						<th width="10%">글번호</th>
						<th width="60%">글내용</th>
						<th width="20%">작성자</th>
						<th width="10%">삭제</th>
					</tr>

					<!-- eq연산자: '=='과 동일  
						 ne연산자: '!='과 동일
						 empty연산자: 'memoArr.size()==0'과 동일
						 or연삱: '||'과 동일
					-->
					<c:if test="${memoArr eq null || empty memoArr}">
						<tr>
							<td colspan="4"><b>데이터가 없습니다.</b></td>
						</tr>
					</c:if>

					<c:if test="${memoArr ne null}">
						<!-- --------------- -->
						<c:forEach var="memo" items="${memoArr}">
							<tr>
								<td>${memo.idx}</td>
								<td>${memo.msg} <span
									class="float-right badge badge-success"> ${memo.wdate} </span>
								</td>
								<td>${memo.name}</td>
								<td><a class="btn btn-info"
									href="javascript:goDel('${memo.idx}')">삭제</a>
								<td>
							</tr>
							<!-- --------------- -->
						</c:forEach>
					</c:if>
					<tr>
						<td colspan="2">
							<!-- page navigation -->
							<ul class="pagination">
								<c:forEach var="i" begin="1" end="${pageCount}" step="1">
									<%-- [<a href="memo-list.do?cpage=${i}">${i}</a>] --%>
									<c:if test="${i eq cpage}">
										<li class="page-item active"><a class="page-link"
											href="memo-list.do?cpage=${i}"> ${i} </a></li>
									</c:if>
									<c:if test="${i ne cpage}">
										<li class="page-item"><a class="page-link"
											href="memo-list.do?cpage=${i}"> ${i} </a></li>
									</c:if>
								</c:forEach>
							</ul>
						</td>
						<td colspan="2" class="text-right">
							<button class="btn btn-danger">
								총 게시글 수 <span class="badge badge-light">${totalCount}</span>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<script>
		function goDel(idx){
			var yn=confirm(idx+'번 글을 삭제할까요?');
			if(yn){
				location.href = "memo-del.do?idx="+idx;
			}
		}
	</script>
</body>
</html>