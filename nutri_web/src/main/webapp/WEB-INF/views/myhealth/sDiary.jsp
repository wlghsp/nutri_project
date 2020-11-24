<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 구글 차트 호출을 위한 js 파일 -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script>
//구글 차트 라이브러리 로딩
//google객체는 위쪽 google src안에 들어있음
google.load('visualization','1',{
    'packages' : ['corechart']
});

	var nickname = "${login.nickname}";
	
	 //날짜형식 변경하고 싶으시면 이 부분 수정하세요.
    var chartDateformat = 'yyyy년MM월dd일';


//로딩이 완료되면 drawChart 함수를 호출
  google.setOnLoadCallback(drawDailyChart); //라이브러리를 불러오는 작업이 완료되었으면 drawChart작업을 실행하라는 뜻.
  //google.setOnLoadCallback(drawWeeklyChart); //라이브러리를 불러오는 작업이 완료되었으면 drawChart작업을 실행하라는 뜻.
 //google.setOnLoadCallback(drawMonthlyChart); */ //라이브러리를 불러오는 작업이 완료되었으면 drawChart작업을 실행하라는 뜻.
    
    function drawDailyChart() {
        var jsonData = $.ajax({ //비동기적 방식으로 호출한다는 의미이다.
            url : "${contextPath}/myhealth/sDiaryDaily?nickname=" + nickname,     
            dataType : "json",
            async : false
        }).responseText; //제이슨파일을 text파일로 읽어들인다는 뜻
        console.log(jsonData);
        //데이터테이블 생성
        var data
        = new google.visualization.DataTable(jsonData);
        //제이슨 형식을 구글의 테이블 형식으로 바꿔주기 위해서 집어넣음
        //차트를 출력할 div
        //LineChart, ColumnChart, PieChart에 따라서 차트의 형식이 바뀐다.
        
        //var chart = new google.visualization.PieChart(
                //document.getElementByld('chart_div')); //원형 그래프
        
       /*  var chart
         = new google.visualization.LineChart(
                document.getElementById('chart_div')); //선 그래프  */
                
	     var options = {
	             title: '나의 운동량',
	             bars: 'vertical',
	             height :600,
	             width :'100%',
	             legend: { position: "top" },
	             isStacked: false,
	             tooltip:{textStyle : {fontSize:12}, showColorCode : true},
	             animation: { //차트가 뿌려질때 실행될 애니메이션 효과
	                 startup: true,
	                 duration: 1000,
	                 easing: 'linear' 
	             },
	             hAxis: {
	               title: '4월 일별 데이터',
	               format: 'MM/dd',
	               viewWindow: {
	                 min: [7, 30, 0],
	                 max: [17, 30, 0]
	               }
	             },
	             vAxis: {
	               title: 'Rating (scale of 1-10)'
	             }
	           };
                
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
                //차트 객체.draw(데이터 테이블, 옵션) //막대그래프
                //cuveType : "function" => 곡선처리
                chart.draw(data, options); 
                window.addEventListener('resize', function() { chart.draw(data, options); }, false);
    }
    
    function drawWeeklyChart() {
        var jsonData = $.ajax({ //비동기적 방식으로 호출한다는 의미이다.
            url : "${contextPath}/myhealth/sDiaryWeekly?nickname=" + nickname,     
            dataType : "json",
            async : false
        }).responseText; //제이슨파일을 text파일로 읽어들인다는 뜻
        console.log(jsonData);
        //데이터테이블 생성
        var data
        = new google.visualization.DataTable(jsonData);
        //제이슨 형식을 구글의 테이블 형식으로 바꿔주기 위해서 집어넣음
        //차트를 출력할 div
        //LineChart, ColumnChart, PieChart에 따라서 차트의 형식이 바뀐다.
        
        //var chart = new google.visualization.PieChart(
                //document.getElementByld('chart_div')); //원형 그래프
        
       /*  var chart
         = new google.visualization.LineChart(
                document.getElementById('chart_div')); //선 그래프  */
                
	     var options = {
	             title: '나의 운동량',
	             bars: 'vertical',
	             height :600,
	             width :'100%',
	             legend: { position: "top" },
	             isStacked: false,
	             tooltip:{textStyle : {fontSize:12}, showColorCode : true},
	             animation: { //차트가 뿌려질때 실행될 애니메이션 효과
	                 startup: true,
	                 duration: 1000,
	                 easing: 'linear' 
	             },
	             hAxis: {
	               title: '4월 주별 데이터',
	               format: 'MM/dd',
	               viewWindow: {
	                 min: [7, 30, 0],
	                 max: [17, 30, 0]
	               }
	             },
	             vAxis: {
	               title: 'Rating (scale of 1-10)'
	             }
	           };
                
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
                //차트 객체.draw(데이터 테이블, 옵션) //막대그래프
                chart.draw(data, options); 
                window.addEventListener('resize', function() { chart.draw(data, options); }, false);     
    }
    
    function drawMonthlyChart() {
        var jsonData = $.ajax({ //비동기적 방식으로 호출한다는 의미이다.
            url : "${contextPath}/myhealth/sDiaryMonthly?nickname=" + nickname,     
            dataType : "json",
            async : false
        }).responseText; //제이슨파일을 text파일로 읽어들인다는 뜻
        console.log(jsonData);
        //데이터테이블 생성
        var data
        = new google.visualization.DataTable(jsonData);
        //제이슨 형식을 구글의 테이블 형식으로 바꿔주기 위해서 집어넣음
        //차트를 출력할 div
        //LineChart, ColumnChart, PieChart에 따라서 차트의 형식이 바뀐다.
        
        //var chart = new google.visualization.PieChart(
                //document.getElementByld('chart_div')); //원형 그래프
        
       /*  var chart
         = new google.visualization.LineChart(
                document.getElementById('chart_div')); //선 그래프  */
                
	     var options = {
	             title: '나의 운동량',
	             bars: 'vertical',
	             height :600,
	             width :'100%',
	             legend: { position: "top" },
	             isStacked: false,
	             tooltip:{textStyle : {fontSize:12}, showColorCode : true},
	             animation: { //차트가 뿌려질때 실행될 애니메이션 효과
	                 startup: true,
	                 duration: 1000,
	                 easing: 'linear' 
	             },
	             hAxis: {
	               title: '2020년 월별 데이터',
	               format: 'MM/dd',
	               viewWindow: {
	                 min: [7, 30, 0],
	                 max: [17, 30, 0]
	               }
	             },
	             vAxis: {
	               title: 'Rating (scale of 1-10)'
	             }
	           };
                
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
                //차트 객체.draw(데이터 테이블, 옵션) //막대그래프
                chart.draw(data, options); 
                //반응형 그래프 출력 - 반응형 그래프를 원하지 않을 시 제거하거나 주석처리 하세요.
        window.addEventListener('resize', function() { chart.draw(data, options); }, false);
    }
 
</script>
</head>
<body> 
<div>
    <button id="dailyBtn" type="button" class="btn btn-outline btn-success" onclick="drawDailyChart()">일간</button>
    <button id="weeklyBtn"  class="btn btn-outline btn-warning" onclick="drawWeeklyChart()">주간</button>
	<button id="MonthlyBtn"  class="btn btn-outline btn-info" onclick="drawMonthlyChart()">월간</button>
</div>    
    <br><br>
    <div id="chart_div"></div> 
</body>
</html>
