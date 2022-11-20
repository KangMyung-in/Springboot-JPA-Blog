<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>
<%-- "layout폴더에 있는 header.jsp 합침--%>


<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<button id=btn-update class="btn btn-warning">수정</button>
	
	<button id=btn-delete class="btn btn-danger">삭제</button>
	
	<br /> <br />
	<div>
		글번호 : <span id="id"><i>${board.id}</i></span> 
		작성자 : <span><i>${board.user.username}</i></span>
	</div>
	<br>
	<div>
		<h3>${board.title}</h3>
		<!-- 오류나면 지웠다 다시쓰기 -->
	</div>
	<hr />
	<div>
		<div>${board.content}</div>
	</div>

	<hr />
</div>

<script src="/js/board.js"></script>
<!-- board.js 로 넘겨줌 -->

<%@ include file="../layout/footer.jsp"%>
<%--layout폴더에 있는 footer.jsp 파일을 합침--%>