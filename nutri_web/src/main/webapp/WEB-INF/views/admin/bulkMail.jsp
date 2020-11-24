<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/adminHeader.jsp" %>


<body>
<div class="container">
  <h4>전체회원에게 메일 보내기</h4>
  <form action="${contextPath}/admin/bulkMailSending" method="post">
    <div align="center"><!-- 제목 -->
      <input type="text" name="title" size="120" style="width:100%" placeholder="제목을 입력해주세요" class="form-control" >
    </div>
    <p>
    <div align="center"><!-- 내용 --> 
      <textarea name="content" cols="120" rows="12" style="width:100%; resize:none" placeholder="내용#" class="form-control"></textarea>
    </div>
    <p>
    <div align="center">
      <input type="submit" value="전체메일 보내기" class="btn btn-warning">
    </div>
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
  </form>
</div>	
</body>
</html> 
<%@include file="../includes/adminFooter.jsp"%>
