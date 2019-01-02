<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

<form name="rf" id="rf" role="form" method="POST" enctype="multipart/form-data">
	<!-- 파일 업로드시 post방식. enctype="multipart/form-data" -->

	<!-- hidden data[상품번호,회원번호]--------- -->
	<input type="hidden" id="pnum_fk" name="pnum_fk" value="${pvo.pnum}">
	<input type="hidden" id="midx_fk" name="midx_fk" value="${loginUser.idx}">
	
	<!--평가점수  -->
	<table class="table table-responsive">
		<tr>
			<th colspan="4" class="text-center">
				<h3>::상품 후기 쓰기::</h3>
			</th>
		</tr>
		<tr>
			<th>평가점수</th>
				<td class="text-left">
					<input type="radio" name="score1" id="score1" value="1">1점
					<input type="radio" name="score1" id="score2" value="2">2점
					<input type="radio" name="score1" id="score3" value="3">3점
					<input type="radio" name="score1" id="score4" value="4">4점
					<input type="radio" name="score1" id="score5" value="5">5점 
				<!-- 
					<span class="star" title="">☆</span>
					<span class="star"  title="">☆</span>
					<span class="star"  title="">☆</span>
					<span class="star"  title="">☆</span>
					<span class="star"  title="">☆</span> 
				-->
				</td>
			<th>작성자</th>
			<td><span id="uname">${loginUser.name}</span> <span id="uid"
				class="label label-warning">${loginUser.userid}</span></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">
				<input type="text" name="title" id="title"
				placeholder="Title" class="form-control">
			</td>
		</tr>
		<tr>
			<th>상품평</th>
			<td colspan="3">
				<textarea name="content" id="content" rows="2" class="form-control" placeholder="Content"></textarea>
			</td>
		</tr>
		<tr>
			<th>이미지업로드</th>
			<td colspan="2"><input type="file" name="mfilename"
				id="filename" accept=""></td>
			<td><a type="button" class="btn btn-success" onclick="send()">글쓰기</a>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	var send = function(){
		
		if(!$('input[name^="score"]').is(':checked')){
			//평가 점수에 선택을 안했다면
			alert('점수를 선택하세요');
			//$(this).focus();
			return false;
		}
		
		if(!$('#title').val()){
			alert('제목을 입력하세요.');
			$('#title').focus();
			return false;
		}
		
		if(!$('#content').val()){
			alert('상품평을 입력하세요');
			$('#content').focus();
			return false;
		}
		
		rf.action="user/reviewWrite.do";
		
		$('#rf').submit();
	}
</script>













