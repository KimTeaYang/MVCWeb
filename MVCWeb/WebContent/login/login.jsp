<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Cookie[] cks = request.getCookies();
	String uid="";
	boolean isSave = false;
	if(cks!=null){
		for (Cookie c : cks) {
			String key = c.getName();
			
			if (key.equals("saveId")) {
				uid = c.getValue();
				isSave=true;
				break;
			}
		}
	}
%>

<script type="text/javascript">
	var loginCheck = function(){
		if(!loginF.userid.value){
			alert('아이디를 입력하세요');
			loginF.userid.focus();
			return;
		}
		if(!loginF.pwd.value){
			alert('비밀번호를 입력하세요');
			loginF.pwd.focus();
			return;
		}
		
		loginF.submit();
	}
</script>

<!--Modal 다이얼로그 시작-->
<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Login Page</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body"> 
				<form name="loginF" action="${pageContext.request.contextPath}/loginEnd.do" method="post">
					<table class="table table-borderless">
						<tr>
							<td>아이디</td>
							<td><input class="form-control" type="text" name="userid"
								placeholder="아이디를 입력하세요" value="<%=uid%>"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input class="form-control" type="password" name="pwd"
								placeholder="비밀번호를 입력하세요"></td>
						</tr>
						
						<tr>
							<td colspan="2" class="text-right"><label for="saveId"
								class="form-check-label"> <input type="checkbox" 
								<%=(isSave)?"checked":"" %> name="saveId" 
								id="saveId" class="form-check-input"> ID 저장
							</label>
								<button class="btn btn-info" onclick="loginCheck()" 
								type="button">Login</button>
								<button class="btn btn-warning" type="reset">Reset</button>
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">close</button></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer"></div>
		</div>
	</div>
</div>