<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/header.jsp"%>
<%-- "layout폴더에 있는 header.jsp 합침--%>

<div class="container">

	<c:forEach var="board" items="${boards.content}">
		<!-- index.jsp 에서 넘어온값 받아서 board 변수에 널어준다-->
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title }</h4><!-- 이 줄 에서 버그있으면 지웠다 다시써주기 -->

				<a href="/board/${board.id }" class="btn btn-primary">상세보기</a>
			</div>
	</c:forEach>

	<ul class="pagination justify-content-center">
		<!-- justify-content-center(end,start) 은 가운데 정렬 문법임 -->
		<c:choose>
			<c:when test="${boards.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item "><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
			</c:otherwise>
		</c:choose>
			<c:choose>
			<c:when test="${boards.last }">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
			</c:when>
			<c:otherwise>
			<li class="page-item"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
			</c:otherwise>
		</c:choose>

		
	</ul>

</div>



</div>

<%@ include file="layout/footer.jsp"%>
<%--layout폴더에 있는 footer.jsp 파일을 합침--%>