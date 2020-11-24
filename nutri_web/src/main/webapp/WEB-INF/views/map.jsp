<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>


<html>
<head>
    <meta charset="utf-8">
    <title>카테고리별 장소 검색하기</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=87f60356c787d8dad8a88bcc4ae30f49&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=87f60356c787d8dad8a88bcc4ae30f49"></script>
<link rel="stylesheet" href="/bteam/resources//css/map.css">
<script src="/bteam/resources//js/map.js"></script>


</head>
<body>
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <ul id="category">
        <li id="PM9" data-order="2"> 
            <span class="category_bg pharmacy"></span>
            약국
        </li>  
    </ul>
</div>
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->

</body>