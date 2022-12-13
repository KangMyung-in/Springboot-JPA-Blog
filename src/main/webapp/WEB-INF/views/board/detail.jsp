<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<%-- "layout폴더에 있는 header.jsp 합침--%>


<div class="container">
	<!-- container  역할을 중앙배치 역할을함-->

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<!--  updateForm 으로 이동-->
		<button id=btn-delete class="btn btn-danger">삭제</button>
	</c:if>

	<br /> <br />
	<div>
		글번호 : <span id="id"><i>${board.id}</i></span> 작성자 : <span><i>${board.user.username}</i></span>
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

	<div class="card">
	<form>
	<input type="hidden" id="userId" value="${principal.user.id }"/><!-- id="userId"를 board.js에 만들어 줘야됨 -->
	<input type="hidden" id="boardId" value="${board.id }"/> <!-- id="boardId"를 board.js에 만들어 줘야됨 -->
		<div class="card-body">
			<textarea id="reply-content" class="form-control" rows="1" cols=""></textarea>
		</div>
		<div class="card-footer">
			<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>  <!-- form 안에 있으면 기본적으로 submit 속성이므로 따로 설정 해야줘됨 -->
		</div>
		</form>
	</div>
	<br />

	<div class="card">
	
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="reply--1" class="list-group-item d-flex justify-content-between">
					<!-- display가 block 로 되어잇으면 화면끝까지 차지하므로 flex(가로로 길게 슬수 있게?) 로 바꿔줌  ustify-content-between"은 2개를 좌우로 나눠줌-->
					
					<li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username } &nbsp;</div>
						<!-- &nbsp; 한칸띄움 -->
						<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
						<!-- badge는 크기 줄여줌 -->
					</div>
				</li>
			</c:forEach>

		</ul>
	</div>
</div>

<script src="/js/board.js"></script>
<!-- board.js 로 넘겨줌 -->

<%@ include file="../layout/footer.jsp"%>
<%--layout폴더에 있는 footer.jsp 파일을 합침--%>