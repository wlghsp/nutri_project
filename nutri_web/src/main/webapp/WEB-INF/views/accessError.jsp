<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../views/includes/adminHeader.jsp" %>


<h1>Access Denied Page</h1>

<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage() }"/></h2>

<h2><c:out value="${msg}"/></h2>


<script type="text/javascript">
$(function(){
	if(${msg ne null}){
		alert('${msg}');
	};		
	
}) 
</script>

<%@include file="../views/includes/adminFooter.jsp"%>