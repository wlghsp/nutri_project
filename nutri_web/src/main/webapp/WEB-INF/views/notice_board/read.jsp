<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
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

         <div class="form-group">
           <label>글 번호</label> <input class="form-control" name="nbno" value="${board.nbno }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>제 목</label> <input class="form-control" name="title" value="${board.title }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>내 용</label>
           <textarea class="form-control" rows="10" name="content" readonly="readonly">${board.content }</textarea>
         </div>

         <div class="form-group">
           <label>작 성 자</label> <input class="form-control" name="writer" value="${board.writer }" readonly="readonly">
         </div>
         <!-- html의 data- 속성 이용 -->
         <%-- <button data-oper="modify" class="btn btn-default">
         	<a href="${contextPath }/board/modify?bno=${board.bno}">Modify</a>
         </button>
         <button data-oper="list" class="btn btn-default">
         	<a href="${contextPath }/board/list">List</a>
         </button> --%>
         <sec:authorize access="isAuthenticated()">
       		<button data-oper='modify' class="btn btn-default">수 정</button>
         </sec:authorize>
		 <button data-oper="list" class="btn btn-info">목 록</button>
		 
		 <form id="operForm" action="${contextPath}/boad/modify" method="get">
	  		 <input type="hidden" id="nbno" name="nbno" value="${board.nbno}">
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
<!-- /.row -->


<script type="text/javascript" src="${contextPath}/resources/js/reply.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	var nbnoValue = "${board.nbno}";
	var replyUL = $(".chat");
	  
	showList(1);
	
	function showList(page) {
		console.log("show list " + page);
		   
		replyService.getList(
				{nbno:nbnoValue, contextPath:"${contextPath}", page: page || 1 }, 
				function(replyCnt, list) {
					console.log("replyCnt: "+ replyCnt );
					console.log("list: " + list);
					console.log(list);
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					  
					var str="";
					 
					if(list == null || list.length == 0){
						return;
					}
					 
					for (var i = 0, len = list.length || 0; i < len; i++) {
						str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
						str +="  <div><div class='header'><strong class='primary-font'>["
							+ list[i].rno+"] "+list[i].replyer+"</strong>";
						str +="    <small class='pull-right text-muted'>"
							+ replyService.displayTime(list[i].updatedate)+"</small></div>";
						str +="    <p>"+list[i].replytext+"</p></div></li>";
					}
					 
					replyUL.html(str);
					 
					showReplyPage(replyCnt);
		});//end function
	}//end showList
	
    var pageNum = 1;
    var replyPageFooter = $(".panel-footer");
    
    function showReplyPage(replyCnt) {
    	var endNum = Math.ceil(pageNum / 10.0) * 10;
    	var startNum = endNum - 9;
    	
    	var prev = startNum != 1;
    	var next = false;
    	
    	if(endNum * 10 >= replyCnt) {
    		endNum = Math.ceil(replyCnt/10.0);
    	}
    	
    	if(endNum * 10 < replyCnt) {
    		next = true;
    	}
    	
    	var str = "<ul class='pagination pull-right'>";
    	
    	if(prev) {
    		str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
    	}
    	
    	for(var i = startNum ; i <= endNum; i++) {
    		var active = pageNum == i? "active":"";
    		
    		str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
    	}
    	
    	if(next) {
    		str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
    	}
    	
    	str += "</ul></div>";
    	
    	console.log(str);
    	
    	replyPageFooter.html(str);
    }
     
    replyPageFooter.on("click","li a", function(e) {
    	e.preventDefault();
    	console.log("page click");
    	
    	var targetPageNum = $(this).attr("href");
    	
    	console.log("targetPageNum: " + targetPageNum);
    	
    	pageNum = targetPageNum;
    	
    	showList(pageNum);
     });     
	
    var modal = $(".modal");
    var modalInputReplyText = modal.find("input[name='replytext']");
    var modalInputReplyer = modal.find("input[name='replyer']");
    var modalInputReplyDate = modal.find("input[name='replyDate']");
    
    var modalModifyBtn = $("#modalModifyBtn");
    var modalRemoveBtn = $("#modalRemoveBtn");
    var modalRegisterBtn = $("#modalRegisterBtn");
    
    $("#modalCloseBtn").on("click", function(e){
    	modal.modal("hide");
    });
    
 	var replyer = null;
    
    
    replyer = '${login.nickname}';   
    
    var csrfHeaderName ="${_csrf.headerName}"; 
    var csrfTokenValue="${_csrf.token}";	
    
    $(document).ajaxSend(function(e, xhr, options) { 
        xhr.setRequestHeader(csrfHeaderName, csrfTokenValue); 
      });
    
    $("#addReplyBtn").on("click", function(e){
    	modal.find("input").val("");
    	modal.find("input[name='replyer']").val(replyer);
    	modalInputReplyDate.closest("div").hide();
    	modal.find("button[id !='modalCloseBtn']").hide();
    	
    	modalRegisterBtn.show();
    	
    	$(".modal").modal("show");
    });
    
    modalRegisterBtn.on("click", function(e) {
    	var reply = {
    			replytext: modalInputReplyText.val(),
    			replyer:modalInputReplyer.val(),
    			contextPath:"${contextPath}",
    			nbno:nbnoValue
    		};
    	
        replyService.add(reply, function(result){
        	alert(result);
        	
        	modal.find("input").val("");
        	modal.modal("hide");
        	
        	showList(-1);
        });
	});
    
    $(".chat").on("click", "li", function(e){
    	var reply = {
    		rno : $(this).data("rno"),
    		contextPath:"${contextPath}",
    	};

    	replyService.get(reply, function(reply){
    		modalInputReplyText.val(reply.replytext);
    		modalInputReplyer.val(reply.replyer);
    		modalInputReplyDate.val(replyService.displayTime(reply.regdate)).attr("readonly","readonly");
    		modal.data("rno", reply.rno);
    		
    		modal.find("button[id !='modalCloseBtn']").hide();
    		modalModifyBtn.show();
    		modalRemoveBtn.show();
    		
    		$(".modal").modal("show");
    	});
    });
    
    modalModifyBtn.on("click", function(e){
    	var reply = {
    			rno:modal.data("rno"), 
    			replytext: modalInputReplyText.val(),
    			contextPath:"${contextPath}"
    		};
    	
    	replyService.update(reply, function(result) {
    		alert(result);
    		
     	    modal.modal("hide");
     	    showList(pageNum);
     	});
    });

    modalRemoveBtn.on("click", function (e) {
    	var reply = {
    			rno : modal.data("rno"), 
    			contextPath:"${contextPath}"
    		};
    	
    	replyService.remove(reply, function(result) {
    		alert(result);
    		
    		modal.modal("hide");
    		showList(pageNum);
    	});
    });
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	var operForm = $("#operForm"); 
		$("button[data-oper='modify']").on("click", function(e){
	  	operForm.attr("action","${contextPath}/notice_board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#nbno").remove();
		operForm.attr("action","${contextPath}/notice_board/list")
		operForm.submit();
	});  
});
</script>
<%@include file="../includes/footer.jsp"%>