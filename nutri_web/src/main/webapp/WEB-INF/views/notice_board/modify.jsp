<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath}" scope="application"/>
<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">공 지 사 항</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading" style="height: 40px;"></div>
      <!-- /.panel-heading -->
      <div class="panel-body">
		<form action="${contextPath}/notice_board/modify" method="post" role="form">
		  <input type="hidden" name="pageNum" value="${cri.pageNum }">
		  <input type="hidden" name="amount" value="${cri.amount }">
		  <input type="hidden" name="type" value="${cri.type }">
		  <input type="hidden" name="keyword" value="${cri.keyword }">
          <div class="form-group">
            <label>글 번호</label> <input class="form-control" name="nbno" value="${board.nbno }" readonly="readonly">
          </div>

          <div class="form-group">
            <label>제 목</label> <input class="form-control" name="title" value="${board.title }">
          </div>

          <div class="form-group">
            <label>내 용</label>
            <textarea class="form-control" rows="10" name="content">${board.content }</textarea>
          </div>

          <div class="form-group">
            <label>작 성 자</label> <input class="form-control" name="writer" value="${board.writer }" readonly="readonly">
          </div>
          <button type="submit" data-oper="modify" class="btn btn-default">수정</button>
          <button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
          <button type="submit" data-oper="list" class="btn btn-info">목록</button>
          <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
		</form>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form");
		
		$("button").on("click", function(e) {
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if (operation === "remove") {
				formObj.attr("action", "${contextPath}/notice_board/remove");
			} else if (operation === "list") {
				formObj.attr("action", "${contextPath}/notice_board/list")
				       .attr("method", "get");
				
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty();
				
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
				//self.location = "${contextPath}/board/list";
				
				//return;
			}
			formObj.submit();
		});
	});
</script>
<%@include file="../includes/footer.jsp"%>