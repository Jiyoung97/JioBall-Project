<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>JIOBall FindId</title>

<link rel="stylesheet" href="/assets/css/style.css">

<style type="text/css">
</style>

</head>
<body>
<div class="card">
	<div class="card-body">
		<h3 class="text-secondary">비밀번호 찾기</h3><br>
		<form action="/login/findpw" method="post">
			<label for="userid">아이디 *</label>
			<input type="text" name="userid" id="userid" class="form-control" required><br>
			<label	for="usertelecom">전화번호 *</label><br>
			<div style="display:flex;">
			<div style="flex:0.3;">
			<select name="usertelecom" id="usertelecom" class="form-control form-select text-secondary">
				<option value="skt">SKT</option>
				<option value="kt">KT</option>
				<option value="lg">LG</option>
				<option value="skt알뜰폰">SKT알뜰폰</option>
				<option value="kt알뜰폰">KT알뜰폰</option>
				<option value="lg알뜰폰">LG알뜰폰</option>
			</select></div>
			<div style="flex:0.8;">
			 <input type="text" name="userphone" id="userphone" class="form-control" required></div>
			 </div><br><br>
			<div style="text-align: center">
				<button class="btn btn-primary btn-fw">완료</button>
			</div>
		</form>
	</div>
	</div>
	<script src="/assets/vendors/js/vendor.bundle.base.js"></script>
</body>
</html>