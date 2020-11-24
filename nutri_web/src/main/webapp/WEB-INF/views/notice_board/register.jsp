<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath}" scope="application"/>
<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">공 지 사 항 </h1>
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

        <form role="form" action="${contextPath }/notice_board/register" method="post">
          <div class="form-group">
            <label>제 목</label> <input class="form-control" name='title'>
          </div>

          <div class="form-group">
            <label>글 내용 </label>
            <textarea class="form-control" rows="10" name='content'></textarea>
          </div>

          <div class="form-group">
            <label>작 성 자 </label> <input class="form-control" name='writer'>
          </div>
          <button type="submit" class="btn btn-default">등 록</button>
          <button type="reset" class="btn btn-default">취 소</button>
           <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>