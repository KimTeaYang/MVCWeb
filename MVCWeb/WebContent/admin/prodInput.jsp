<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/top.jsp"/>

<script type="text/javascript">

	$(function(){
		
		
	});//$(function) end
</script>


<div class="col-md-10 col-mad-offset-1">
<h1>MyShop ADMIN [Product 등록]</h1>

<!----------------------------  -->
<form  action="prodInsert.do" method="POST"
 enctype="multipart/form-data">
 <!-- 상품 이미지 파일을 업로드 하려면 enctype="multipary/form-data" -->
<table class="table table-condensed table-bordered">
	<thead>
		<tr>
			<th colspan="2">
			<h3>:::Product Register:::</h3>
			</th>
		</tr>		
	</thead>
	<tbody>
		<tr>
			<td width="20%"><b>카테고리</b></td>
			<td width="80%">
			<div class="col-md-5" style="padding-left:0">
			<!-- 상위 카테고리------------ -->
			<select name="upCode"
			 onchange="selectDCate(this.value)" class="form-control">
			 	<option value=''>::상위 카테고리::</option>
			 	<option value="1">컴퓨터.디지털.가전</option>
			 	<option value="2">식품.생필품</option>
			 	<option value="3">패션의류</option>
			</select>
			<!-- ---------------------- -->
			</div>
			<div class="col-md-5" style="padding-left:5px">
			<span id="selectDcg">
			<!-- 하위 카테고리------------ -->
			<select name="downCode"
			 onchange="" class="form-control">
			 	<option value=''>::하위 카테고리::</option>
			 	<option value="1">노트북.PC</option>
			 	<option value="2">핸드폰</option>
			 	<option value="3">대형가전</option>
			</select>
			<!-- ---------------------- -->
			</span>
			</div>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품명</b></td>
			<td width="80%">
			<input type="text" name="pname" id="pname"  class="form-control">
			</td>
		</tr>
		<tr>
			<td width="20%"><b>제조사</b></td>
			<td width="80%">
			<input type="text" name="pcompany" id="pcompany" class="form-control">
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품스펙</b></td>
			<td width="80%">
			<div class="col-md-5" style="padding-left:0">
				<select name="pspec" id="pspec" class="form-control">
					<option value="NEW" selected>NEW</option>
					<option value="HIT">HIT</option>
					<option value="BEST">BEST</option>
				</select>
			</div>				
			</td>
		</tr>
		<tr>
			<td>상품이미지</td>
			<td>
			<input type="file" name="pimage1"  class="form-control"><br>
			<input type="file" name="pimage2"  class="form-control"><br>
			<input type="file" name="pimage3" class="form-control"><br>
			</td>
		</tr>
		
		<tr>
			<td width="20%"><b>상품수량</b></td>
			<td width="80%">
			<div class="col-md-5 form-inline" style="padding-left:0">
			<input type="number" name="pqty" id="pqty" class="form-control form-inline"> 개
			</div>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품정가</b></td>
			<td width="80%">
			<div class="col-md-5 form-inline" style="padding-left:0">
			<input type="text" name="price" id="price" class="form-control"> 원
			</div>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품판매가</b></td>
			<td width="80%">
			<div class="col-md-5 form-inline" style="padding-left:0">
			<input type="text" name="saleprice" id="saleprice" class="form-control"> 원
			</div>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품설명</b></td>
			<td width="80%">
			<textarea name="pcontents" id="pcontents"
			 rows="5" cols="60"  class="form-control"></textarea>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>포인트</b></td>
			<td width="80%">
			<div class="col-md-6 form-inline" style="padding-left:0">
			<input type="number" name="point" id="point" class="form-control"><span>POINT</span>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
			<button class="btn btn-success">상품등록</button>
			</td>
		</tr>
	</tbody>
</table> 					
</form>
</div>
<c:import url="/foot.jsp"/>


