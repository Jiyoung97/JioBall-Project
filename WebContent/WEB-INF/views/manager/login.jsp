<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>MANAGER Login</title>

<link rel="stylesheet" href="/assets/css/style.css">

</head>

<body>


	<div class="content-wrapper" style="width: 100vw; height: 100vh;">
		<div class="card col-lg-4 mx-auto" style="margin:120px auto;">
			<div class="card-body px-5 py-5">
				<h1 class="card-title text-left mb-3">관리자 로그인</h1>
				<br>
				<form action="/manager/login" method="post">
					<div class="form-group">
						<label>아이디 *</label> <input type="text" class="form-control p_input" name="managerid" required>
					</div>
					<div class="form-group">
						<label>패스워드 *</label> <input type="password" class="form-control p_input" name="managerpw" required>
					</div><br>
					<button type="submit" class="btn btn-primary btn-block enter-btn">로그인</button>
				</form>
			</div>
		</div>
	</div>


	<script src="/assets/vendors/js/vendor.bundle.base.js"></script>

</body>
</html>