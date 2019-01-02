<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>

<%
	//IE의 경우
	response.setStatus(200);
%>

<script>
	alert('<%=exception.getMessage()%>');
	history.back();
</script>