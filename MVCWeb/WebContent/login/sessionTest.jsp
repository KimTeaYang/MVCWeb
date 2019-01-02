<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>

<div class="text-center">
	<h1>Session Test</h1>
	<h2>session에 저장된 모든 정보를 꺼내오자</h2>
	<h2 style='color:red'>JSESSIONID:<%=session.getId() %></h2>
	<%
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()){
			String key = en.nextElement();
			Object val = session.getAttribute(key);
			out.println("<h3>"+key+": "+val+"</h3>");
		}
	%>
</div>

<jsp:include page="/foot.jsp"/>