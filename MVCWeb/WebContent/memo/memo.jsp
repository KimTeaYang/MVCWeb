<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memo.jsp</title>

<!--디바이스 장치에 따라 디바이스 폭에 맞춰 UI를 보여줌-->
<meta name="viewport" content="width=device-width, initial-scale=1">
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


<script type="text/javascript">
	function check(){
		if(!f.name.value){
			window.alert('작성자를 입력하세요');
			f.name.focus();
			return;
		}
		
		if(!f.msg.value){
			alert('메모내용을 입력하세요');
			f.msg.focus();
			return;
		}
		
		var len = f.msg.value.length;
		if(len>100){
			alert('메모내용은 100자를 초과하면 안되요');
			f.msg.select();
			return;
		}
			
		f.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row" style="margin-top:50px">
			<div class="col-md-10 offset-md-1">
				<h1 class="text-center">::한줄 메모장::</h1>
				<form name="f" action="memo-insert.do" method="get">
					<table class="table" style="margin-top:20px">
						<tr>
							<td style="width:25%"><h3>작성자</h3></td>
							<td style="width:75%">
								<input type="text" name="name"
								placeholder="작성자를 입력하세요"
								class="form-control">
							</td>
						</tr>
						<tr>
							<td><h3>메모내용</h3></td>
							<td>
								<input type="text" name="msg"
								placeholder="메모내용을 입력하세요"
								class="form-control">
							</td>
						</tr>
						<tr>
							<td colspan="2" class="text-right">
								<button type="button" onclick="check()" class="btn btn-primary">메모남기기</button>
								<button type="reset" class="btn btn-warning">다시 쓰기</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>