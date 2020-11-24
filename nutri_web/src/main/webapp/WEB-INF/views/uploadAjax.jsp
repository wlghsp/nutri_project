<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.uploadResult {
		width: 100%;
		background-color: gray;
	}
	
	.uploadResult ul {
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li {
		list-style: none;
		padding: 10px;
	}
	
	.uploadResult ul li img {
		width: 100px;
	}
	
	.bigPictureWrapper {
		position: absolute;
		display: none;
		justify-content: center;
		align-items: center;
		top:0%;
		width:100%;
		height:100%;
		background-color: gray; 
		z-index: 100;
	}
	
	.bigPicture {
		position: relative;
		display:flex;
		justify-content: center;
		align-items: center;
	}
	
	/* 실제 이미지는 '.bigPicutre'안에 <img>태그를 생성하기 때문에 추가 */
	.bigPicutre img {
		width: 600px;
	}
</style>
<!-- header.jsp 페이지에서 복사 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	// 화면에 원본 이미지 표시
	function showImage(fileCallPath) {
		//alert(fileCallPath);
		$(".bigPictureWrapper").css("display","flex").show();
	  
		// <img> 태그를 추가하고 jQuery의 animate()를 이용해서 지정된 시간 동안 화면에서 열리는 효과를 처리
		$(".bigPicture")
		.html("<img src='${contextPath}/display?fileName=" + fileCallPath + "'>")
		.animate({width:'100%', height: '100%'}, 1000);
	}

	$(document).ready(function() {
		// 특정한 확장자 파일 업로드 제한용 정규식
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		// 1byte = 2^3 = 8bit
		// 1kB = 2^10bit = 1024bit
		// 1MB = 2^10K = 1024 * 1024 = 1048576bit
		var maxSize = 5242880; // 등록 파일 용량을 5MB로 제한

		// 특정크기 및 특정 확장자 파일 업로드 제한
		function checkExtension(fileName, fileSize) {
			if (fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}

			if (regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			return true;
		}

		// 첨부 파일을 업로드 한 뒤 복사
		var cloneObj = $(".uploadDiv").clone();
		
		// jQuery를 이용하는 경우의 파일 업로드는 FormData객체를 이용(브라우저 제약이 있음 IE 10이상)
		// FormData는 가상의 <form>태그와 같음.
		// Ajax를 이용하는 파일 업로드는 FormData를 이용해서 필요한 파라미터를 담아서 전송하는 방식
		$("#uploadBtn").on("click", function(e) {
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			// 첨부 파일 데이터 formData에 추가
			for (var i = 0; i < files.length; i++) {
				if (!checkExtension(files[i].name, files[i].size)) {
					return false;
				}
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url : "${contextPath}/uploadAjaxAction",
				processData : false,
				contentType : false,
				data : formData,
				type : "POST",
				dataType : "json",
				success : function(result) {
					console.log(result);
					showUploadedFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			}); // $.ajax
		});		
		
		var uploadResult = $(".uploadResult ul");
		
		function showUploadedFile(uploadResultArr) {
			var str = "";
			$(uploadResultArr).each(function(i, obj) {
				if (!obj.image) {
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					
					str += "<li><div><a href='${contextPath}/download?fileName=" + fileCallPath +"'>" + 
						  "<img src='${contextPath}/resources/img/attach.png'>" + obj.fileName + "</a>" +
					      "<span data-file=\'" + fileCallPath + "\' data-type='file'>x</span>" + 
					      "</div></li>";
				} else {
					// 파일 이름에 포함된 공백 문자나 한글 이름 문제 처리를 위해 encoding 처리
					// (크롬, IE의 경우 서로 다르게 처리되어서 첨부파일에 문제가 발생할 수 있기 때문)
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
					var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
					
					originPath = originPath.replace(new RegExp(/\\/g), "/");
					
					str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">" + 
						  "<img src='${contextPath}/display?fileName=" + fileCallPath + "'></a>" + 
						  "<span data-file=\'" + fileCallPath + "\' data-type='image'>x</span>" + 
						  "</li>";
				}
			});
			
			uploadResult.append(str);
		}
		
		// 원본 이미지 혹은 주변 배경을 선택하면 이미지를 화면 중앙으로 작게 점차 줄여 줌.
		$(".bigPictureWrapper").on("click", function(e) {
			$(".bigPicture").animate({width:"0%", height: "0%"}, 1000);
			
			// 1초 후에 자동으로 배경창을 안 보이도록 처리 '=>(ES6의 화살표 함수)'는 크롬에서는 정상 작동
			// IE 11에서는 제대로 동작하지 않음.
			setTimeout(() => {
				$(this).hide();
			}, 1000);
			
			// IE 11 대응
			/* setTimeout(function() {
				$(".bigPictureWrapper").hide();
			}, 1000); */
		});
		
		// 첨부 파일 삭제
		$(".uploadResult").on("click","span", function(e) {
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			
			console.log(targetFile);
			
			// 첨부 파일의 경로와 이름, 파일의 종류(이미지 혹은 일반 파일)를 전송
			$.ajax({
				url: "${contextPath}/deleteFile",
				data: {fileName: targetFile, type:type},
				dataType:"text",
				type: "POST",
				success: function(result){
					alert(result);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>Upload With Ajax</h1>
	<!-- 실제 원본 이미지를 보여주는 영역 -->
	<div class='bigPictureWrapper'>
		<div class='bigPicture'>
  		</div>
	</div>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	
	<div class="uploadResult">
		<ul>
		
		</ul>
	</div>
	<button id="uploadBtn">Upload</button>
</body>
</html>
