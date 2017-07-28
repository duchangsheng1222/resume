<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<html>
<head>

</head>

<body>

<h1>欢迎使用！</h1>
<%
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
		%>
			Authentication object as a String:
			<%=auth.toString()%><BR>
			<BR>
		<%
			}
		%>
</body>



</html>