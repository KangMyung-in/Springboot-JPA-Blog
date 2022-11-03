<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<%--  ../은 내폴더에서 1칸 올라간다는뜻   --%>
<%-- "layout폴더에 있는 header.jsp 합침--%>


<div class="container">

	<!DOCTYPE html>
	<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Stacked form</h2>
		<form action="/action_page.php">
			<div class="form-group">
				<label for="username">Username:</label> 
				<input type="text'"class="form-control"  placeholder="Enter username" id="username">
			</div>
			
				<div class="form-group">
				<label for="password">Password:</label>
				 <input type="password" class="form-control"  placeholder="Enter password" id="password">
			</div>
			
			<div class="form-group">
				<label for="email">Email:</label>
				 <input type="email" class="form-control" placeholder="Enter Email" id="email">
			</div>
			
		
				
		</form>
		<button id="btn-save"class="btn btn-primary">회원가입 완료</button>
		
	</div>

</body>
	</html>

</div>

<script src= "/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
<%--layout폴더에 있는 footer.jsp 파일을 합침--%>