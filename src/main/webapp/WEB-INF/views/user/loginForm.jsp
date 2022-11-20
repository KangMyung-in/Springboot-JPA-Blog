<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<%--  ../은 내폴더에서 1칸 올라간다는뜻   --%>
<%-- "layout폴더에 있는 header.jsp 합침--%>

<%--원래는 id값 들고가서 jquery에서 json만들어서 전송했음--%>
<div class="container">
		<form action="/auth/loginProc" method="post" >
			<div class="form-group">
				<label for="username">Username:</label>
				 <input type="text" name="username"class="form-control"  placeholder="Enter username" id="username">
			</div>



			<div class="form-group">
				<label for="password">Password:</label>
				 <input type="password"  name="password" class="form-control"  placeholder="Enter password" id="password">
			</div>
			
			<button id="btn-login" class="btn btn-primary">로그인</button>
		</form>
	
	</div>

</body>
	</html>

</div>


<%@ include file="../layout/footer.jsp"%>
<%--layout폴더에 있는 footer.jsp 파일을 합침--%>