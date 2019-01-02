<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//Context Name 얻어오기
	String myctx = request.getContextPath();
	//System.out.println("myctx="+myctx); //=> /MyWeb
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=myctx %>/CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-ex-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=myctx%>/index.do"><span>Brand</span></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="<%=myctx%>/index.do">Home</a></li>
					<li><a href="<%=myctx%>/member/join.jsp">Join</a></li>
					
					<c:if test="${loginUser eq null}">
						<li><a href="#myModal" data-toggle="modal" >Login</a></li>
					</c:if>
					<c:if test="${loginUser ne null}">
						<li class="bg bg-info"><a href="#"><b>${loginUser.userid}</b>님 로그인중</a></li>
						<li><a href="<%=myctx %>/logout.do">Logout</a></li>
					</c:if>
					
					<li><a href="<%=myctx%>/user/userList.jsp">Members</a></li>
					<li><a href="<%=myctx%>/user/cartList.do">Cart</a></li>
					<li><a href="<%=myctx%>/admin/prodInput.do">Products</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="nav nav-pills">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">Messages</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">