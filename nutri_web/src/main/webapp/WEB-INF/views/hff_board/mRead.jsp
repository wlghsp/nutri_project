<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">상세 정보</h1>
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

         <div class="form-group">
           <label>원료명</label><input class="form-control" name="APLC_RAWMTRL_NM" value="${material.APLC_RAWMTRL_NM }" readonly="readonly">
         </div>
         <div class="form-group">
           <label>기능성내용</label><input class="form-control" name="FNCLTY_CN" value="${material.FNCLTY_CN }" readonly="readonly">
         </div>
         <div class="form-group">
           <label>섭취 시 주의사항</label><input class="form-control" name="IFTKN_ATNT_MATR_CN" value="${material.IFTKN_ATNT_MATR_CN }" readonly="readonly">
         </div>
		 <div class="form-group">
           <label>1일 섭취량</label><input class="form-control" name="DAY_INTK_CN" value="${material.DAY_INTK_CN }" readonly="readonly">
         </div>
         <div class="form-group">
           <label>인정일자/인정번호</label><input class="form-control" name="PRMS_DT " value="${material.PRMS_DT }/${material.HF_FNCLTY_MTRAL_RCOGN_NO }" readonly="readonly">
         </div>      
         <div class="form-group">
           <label>업체명</label><input class="form-control" name="BSSH_NM" value="${material.BSSH_NM }" readonly="readonly">
         </div>      
         <div class="form-group">
           <label>업종/업체주소</label> <input class="form-control" name=ADDR value="${material.INDUTY_NM}/${material.ADDR }" readonly="readonly">
   		 </div>		 
                  
		 <button data-oper="list" class="btn btn-info">목 록</button>
		 
		 <form id="operForm" action="#" method="get">
	  		 <input type="hidden" id="MNO" name="MNO" value="${material.MNO}">
	  		 <input type="hidden" id="pageNum" name="pageNum" value="${cri.pageNum}">
	  		 <input type="hidden" id="amount" name="amount" value="${cri.amount}">
			 <input type="hidden" name="type" value="${cri.type}">
			 <input type="hidden" name="keyword" value="${cri.keyword}">
		 </form>
      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<div>
<br>
<br>
<br>
</div>
<!-- /.row -->

<script type="text/javascript">
window.onload = function() {
	history.pushState(null, null, location.href);
    window.onpopstate = function() {
        history.go(1);
	};
}; 
</script>
<script type="text/javascript">
$(document).ready(function() {
	var operForm = $("#operForm");
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.attr("action","${contextPath }/hff_board/mList")
		operForm.submit();
	});
});
</script>
<%@include file="../includes/footer.jsp"%>