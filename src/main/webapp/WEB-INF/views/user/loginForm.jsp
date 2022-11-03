<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<%--  ../은 내폴더에서 1칸 올라간다는뜻   --%>
<%-- "layout폴더에 있는 header.jsp 합침--%>


<div class="container">


		<form >
			<div class="form-group">
				<label for="username">Username:</label> <input type="text'" class="form-control"  placeholder="Enter username" id="username">
			</div>



			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control"  placeholder="Enter password" id="password">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
				</label>
			</div>
			
		</form>
	<button id="btn-login" class="btn btn-primary">로그인</button>
	</div>

</body>
	</html>

</div>

<script src= "/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
<%--layout폴더에 있는 footer.jsp 파일을 합침--%>