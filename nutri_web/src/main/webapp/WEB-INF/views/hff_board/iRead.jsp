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
           <label>품목명</label><input class="form-control" name="PRDCT_NM" value="${ingredient.PRDCT_NM }" readonly="readonly">
         </div>
         
         <div class="form-group">
           <label>성분명</label><input class="form-control" name="SKLL_IX_IRDNT_RAWMTR" value="${ingredient.SKLL_IX_IRDNT_RAWMTRL }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>주된기능성</label><input class="form-control" name="PRIMARY_FNCLTY" value="${ingredient.PRIMARY_FNCLTY }" readonly="readonly">
         </div>
            <div class="panel panel-default">                        
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>일일섭취량 상한</th>
                                    <th>일일섭취량 하한</th>
                                    <th>단위</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                   <td>${ingredient.DAY_INTK_HIGHLIMIT }</td>
                                    <td>${ingredient.DAY_INTK_LOWLIMIT }</td>
                                    <td>${ingredient.INTK_LIMIT }</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
         	
         <div class="form-group">
           <label>섭취 시 주의사항</label> <input class="form-control" name=IFTKN_ATNT_MATR_CN value="${ingredient.IFTKN_ATNT_MATR_CN }" readonly="readonly">
   		 </div>
   		 
         <div class="form-group">
           <label>Remark</label> <input class="form-control" name=INTK_MEMO value="${ingredient.INTK_MEMO }" readonly="readonly">
   		 </div>	 
   		   
       	 <div class="form-group">
           <label>등록일시</label> <input class="form-control" name=CRET_DTM value="${ingredient.CRET_DTM }" readonly="readonly">
   		 </div>
         
		 <button data-oper="list" class="btn btn-info">목 록</button>
		 
		 <form id="operForm" action="${contextPath}/hff_board/modify" method="get">
	  		 <input type="hidden" id="INO" name="INO" value="${ingredient.INO}">
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
		operForm.find("#m_no").remove();
		operForm.attr("action","${contextPath}/hff_board/iList")
		operForm.submit();
	});
});
</script>
<%@include file="../includes/footer.jsp"%>