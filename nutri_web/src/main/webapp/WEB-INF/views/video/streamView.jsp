<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
  <div class="col-lg-12">
    <h1 class="page-header">동영상게시판</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Notice Board Read Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

         <div class="form-group">
           <label>No</label> <input class="form-control" name="bno" value="${board.nbno }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>Title</label> <input class="form-control" name="title" value="${board.title }" readonly="readonly">
         </div>

         <div class="form-group" style="height: 500px">
           <label>Text area</label>
           <iframe width="100%" height="100%" src="https://www.youtube.com/embed/wP5rGmrTyjg" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
         </div>
      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
       <button data-oper="list" class="btn btn-info" style="border-color: #ff9902; background-color:#ff9902">List</button>
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<br>
<br>
<br>
<br>
<br>

<%@ include file="..//includes/footer.jsp" %>