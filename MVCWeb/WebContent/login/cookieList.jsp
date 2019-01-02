<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>

<div class="text-center">
	<h1>쿠키 목록 보기</h1>
	
	<%
		/* 
			쿠키가 전송되면 웹브라우저는 저장된 쿠키를 매번 서버에 요청을 보낼때마다
			가지고 간다. 그러면 웹서버는 브라우저가 전송한 쿠키를 이용해서 필요한 
			작업을 수항핸다.
		*/
		Cookie[] cks = request.getCookies();
		boolean isPop = false;
	
		if(cks!=null){
			out.println("<h2>클로부터 넘어온 쿠키들</h2>");
		}
	
		for(Cookie c:cks){
			String key = c.getName();
			String value = c.getValue();
			
			if(key.equals("isPopup")){
				isPop = Boolean.parseBoolean(value);
			}
		%>
			<h3><%=key %> => <%=value %></h3>
		<%
		}
		
		if(isPop){
			%>
				<script>
					window.open("sessionTest.jsp",
							"pop","width=400,height=400");
				</script>
			<%
		}
	%>
</div>

<jsp:include page="/foot.jsp"/>