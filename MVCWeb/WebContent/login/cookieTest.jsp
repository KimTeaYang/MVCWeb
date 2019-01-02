<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>

<div class="text-center">
	<h1>Cookie Test</h1>
	
	
	<%
		/* 
			사용자의 간단한 정보들은 쿠키에 저장하여 활용할 수 있다.
			쿠키는 클라이언트 쪽 메모리나 하드디스크 파일로 저장된다.
			1. 쿠키 저장 단계
			2. 쿠키 꺼내오기
			
			1. 쿠키 저장단계
			 [1] 쿠키 생성 단계(key, value)
			 [2] 쿠키 속성 설정 단계
			 [3] 쿠키 전송 단계 => 응답(response)에 쿠키를 포함시켜 클라이언트
			 쪽으로 전송된다.
		*/
		
		// [1] 쿠키 생성 단계
		Cookie ck1 = new Cookie("visitId","King");
		
		Cookie ck2 = new Cookie("isPopup","true");
		
		// [2] 쿠키 속성 설정 단계
		//   [expiry: 유효시간, path: 경로, domain: 도메인 등....]
		ck1.setMaxAge(7*24*60*60); // 7일간 유효
		ck2.setMaxAge(24*60*60); // 1일간 유효
		/* 쿠키 보관식나은 setMaxAge()로 설정한다.
		해당 시간이 지나면 쿠키는 자동으로 삭제된다. 
		쿠키를 강제로 삭제하고자 한다면 setMaxAge(0); 하면 된다.*/
		
		ck2.setPath("/");
		//ck2.setDomain("도메인명");
		
		response.addCookie(ck1);
		response.addCookie(ck2);
	%>
	
	<h2>쿠키 저장 완료</h2>
	<h3><a href="cookieList.jsp">쿠키 보러 가기</a></h3>
</div>

<jsp:include page="/foot.jsp"/>