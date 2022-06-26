<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">

	.main
	{
		font-size: 13px;
	    line-height: 1.2;
	    color: #666;
	    font-family: 'Noto Sans KR', 'CJONLYONENEW', '맑은 고딕', '돋움', Dotum, sans-serif;
	    font-weight: 400;
	}
	  .pageInfo{
	      list-style : none;
	      display: inline-block;
	    margin: 50px 0 0 0;      
	  }
	  .pageInfo li{
	      float: left;
	    font-size: 20px;
	    padding: 7px;
	    font-weight: 500;
	  }
	 a:link {color:black; text-decoration: none;}
	 a:visited {color:black; text-decoration: none;}
	 a:hover {color:black; text-decoration: underline;}
	 .active{
      background-color: #cdd5ec;
  }
      #content_area
    {
        /* min-width : 1120px;	
    	max-width : 1280px;	
    	margin:auto; */

        /* float: left; */
        margin: 5px 250px 0px 250px;
        /* display: flex;  */
        
    }
  
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


<script type="text/javascript">
	$(".pageInfo a").on("click", function(e) {
        e.preventDefault();
        moveForm.find("input[name='pageNum']").val($(this).attr("href"));
        moveForm.attr("action", "/noticeList");
        moveForm.submit();
	})
</script>
<script type="text/javascript">
function searchCheck1() {

	     	if (!document.getElementById('search').value) {
	     		alert("검색할 내용을 입력해주세요.");
	     		history.back();
			} else {
				document.search_frm.submit();
			}
	      }
</script>
</head>
<body>
<jsp:include page="../main/mainHeader.jsp" flush="false"></jsp:include>
<div class="main">
	<div id="content_area">
		<div id="title">
			<h3>공지사항</h3>
		</div><hr>
		<div align="right">
			<form action="noticeSearch" method="post" name="search_frm">
				<table>
					<tr>
						<td>
							<select class="form-control" name="kind" style="width: 60">
								<option value="제목">제목</option>
								<option value="내용">내용</option>
							</select>
						</td>
						<td>
							<input class="form-control" type="search" placeholder="검색" name="title" id="search" style="width: 200">
						</td>
						<td>
							<button class="btn btn-outline-secondary btn-sm" onclick="searchCheck1()">검색</button>
						</td>
					</tr>
				</table>
			</form>
		</div><hr>
	</div>
	<div id="content_area">
			<table cellspacing="0" class="table">
				<tr height="25">
					<td width="40" align="center">번호</td>
					<td width="550" align="center">글제목</td>
					<td width="120" align="center">작성자</td>
					<td width="130" align="center">작성일</td>
					<td width="60" align="center">조회수</td>
				</tr>
				<c:set var="num" value="${pageMaker.total - ((pageMaker.cri.pageNum - 1) * 10)}"></c:set>
				<c:forEach items="${list}" var="dto">
					<tr align="center">
						<td>${num}</td>
						<td align="left">
							<a href="noticeShow?n_code=${dto.n_code}">${dto.n_title}</a>
						</td>
						<td>${dto.u_id}</td>
						<td><fmt:formatDate pattern="yyyy/MM/dd" value="${dto.n_date}"/> </td>
						<td>${dto.n_view}</td>
					</tr>
				<c:set var="num" value="${num-1 }"></c:set>
				</c:forEach>
			</table>
		</div>
		

		<div id="content_area" align="center">
			<div class="pageInfo_wrap">
				<div class="pageInfo_area">
					<ul id="pageInfo" class="pageInfo">
					
					    <!-- 이전페이지 버튼 -->
		                <c:if test="${pageMaker.prev}">
		                    <li class="pageInfo_btn previous"><a href="?pageNum=${pageMaker.startPage-1}">Previous</a></li>
		                </c:if>
					
						<!-- 각 번호 페이지 버튼 -->
		                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		                    <li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? "active":"" }"><a href="?pageNum=${num}">${num}</a></li>
		                </c:forEach>
		                
				        <!-- 다음페이지 버튼 -->
		                <c:if test="${pageMaker.next}">
		                    <li class="pageInfo_btn next"><a href="?pageNum=${pageMaker.endPage + 1 }">Next</a></li>
		                </c:if>  
		                
					</ul>
				</div>
			</div>
			
			<form id="moveForm" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
			</form>
		</div>
		</div>
		<div id="content_area" align="right">
		<div id="writeBtn">
		<%
		String u_auth = (String)session.getAttribute("u_auth");
		%>
		<c:set var="admin" value="A"/>
		<c:if test="${admin eq u_auth}"> <!-- 관리자만 작성 버튼 활성화 -->
			<input type="button" class="btn btn-outline-secondary btn-sm" value="글 작성" onclick="location.href='noticeWrite_view'">&nbsp;&nbsp;
		</c:if>
			<input type="button" class="btn btn-outline-secondary btn-sm" value="메인으로" onclick="location.href='index'">
		</div>
</div>
<jsp:include page="../main/mainFooter.jsp" flush="false"></jsp:include>
</body>
</html>