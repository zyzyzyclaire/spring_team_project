<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<!-- icon -->
<script src="https://kit.fontawesome.com/259d5f1852.js" crossorigin="anonymous"></script>
<style>
	.main
	{
		height: 1000px;
		text-align: center;
		margin-top: 100px;
		
		font-size: 13px;
	    line-height: 1.2;
	    color: #666;
	    font-family: 'Noto Sans KR', 'CJONLYONENEW', '맑은 고딕', '돋움', Dotum, sans-serif;
	    font-weight: 400;
	}
	
	.carousel-inner > .carousel-item > img {
      top: 0;
      left: 0;
      width: 100%;
      height: 600px;
    }
     
    .chart {
    	margin: 5px 250px 0px 250px;
    	height: 500px;
    	text-align: center;
    }
    .chart_top {
    	margin: 50px 0px 10px 10px;
    	/* display: flex; */
    }
    .chart_title {
    	float: left;
    	padding-bottom: 30px;
    	text-align: left;
    	font-size: 30px;
	    font-weight: bold;
    }
    #chart_btn {
    	margin-top: 10px;
    	float: right;
    /* 	margin-left: 100px; */
    }
    
    .inner_chart {
    	padding: 50px 30px;
    	text-align: center;
    }
    .list_td {
    	padding-right: 30px;
    }
    .main_img {
    	width:200px;
    	height:250px;
    }
    .fa-star {
    	color: yellow;
    }
    
    /* 한줄 말줄임 */
    .main_movie_title {
	    display: inline-block;
	    text-overflow: 
	    ellipsis;white-space: 
	    nowrap;overflow: 
	    hidden;
	    width: 150px;
    }
    
    	
	/*레이어 팝업 영역*/
	#popup1, #popup2, #popup3 {
	 opacity: 0;
	 display: none;
	 position: relative;
	 width: auto;
	 border: black;
	 padding: 20px;
	 background-color: black;
	}  
    
</style>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
		function main_movie_list(){
			location.href="main_movie_list";
		}
	      
		$(function(){
			$("#ad1").click(function(){ //레이어 팝업 열기 버튼 클릭 시
				$('#popup1').bPopup(); //  
			});
		});
		$(function(){
			$("#ad2").click(function(){ //레이어 팝업 열기 버튼 클릭 시
				$('#popup2').bPopup(); //  
			});
		});
		$(function(){
			$("#ad3").click(function(){ //레이어 팝업 열기 버튼 클릭 시
				$('#popup3').bPopup(); //  
			});
		});
    </script>
</head>
<body>
<jsp:include page="mainHeader.jsp" flush="false"></jsp:include> 

	<div id="index_div">
		<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
			<div class="carousel-indicators">
			  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
			  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
			  <div class="carousel-item active">
				<img src="https://caching2.lottecinema.co.kr/lotte_image/2022/Witch/Witch_1920774.jpg" class="d-block w-100" alt="..." id="ad1">
			  </div>
			            <div id="popup1">
						   <p>
						   	 <iframe width="1280" height="720" src="https://www.youtube.com/embed/Iif1qC2GRGs?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						   </p>
						</div>
			  <div class="carousel-item">
				<img src="https://caching2.lottecinema.co.kr/lotte_image/2022/Ong/Top_1920774.jpg" class="d-block w-100" alt="..." id="ad2">
			  </div>
			            <div id="popup2">
						   <p>
						   	 <iframe width="1280" height="720" src="https://www.youtube.com/embed/Mrj9XACVJ8U?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						   </p>
						</div>
			  <div class="carousel-item">
				<img src="https://caching2.lottecinema.co.kr/lotte_image/2022/Buz/0531/Buz_1920774.jpg" class="d-block w-100" alt="..." id="ad3">
			  </div>
			            <div id="popup3">
						   <p>
						     <iframe width="1280" height="720" src="https://www.youtube.com/embed/xTrGcHuD_C4?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						   </p>
						</div>
			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			  <span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			  <span class="carousel-control-next-icon" aria-hidden="true"></span>
			  <span class="visually-hidden">Next</span>
			</button>
		</div>
		
 		<div class="chart">
			<div class="inner_chart">
				<div class="chart_top">
					<div class="chart_title">무비차트</div>
					<div>
						<button class="btn btn-outline-secondary btn-sm" id="chart_btn" onclick="main_movie_list()">전체보기</button>
					</div>
				</div>
				<table>
					<tr>
						<c:forEach items="${list }" var="dto" begin="0" end="5">
							<td class="list_td"  style = "cursor:pointer;" onClick = "location.href='movieDisplay?m_code=${dto.m_code}'">
								<div><img src='${pageContext.request.contextPath}/resources/${dto.m_position}' class="main_img rounded"></div>
								<div class="main_movie_title">${dto.m_title }</div> 
								<div><i class="fa-solid fa-star"></i>&nbsp;${dto.m_rate }</div>
								
				 				<input type="hidden" id="m_code_hidden" value="${dto.m_code }">				
							</td>
						</c:forEach>
					</tr>
				</table>	
			</div>			
		</div>
	
		
		
	</div>
</body>

  <jsp:include page="mainFooter.jsp" flush="false"></jsp:include>

</html>