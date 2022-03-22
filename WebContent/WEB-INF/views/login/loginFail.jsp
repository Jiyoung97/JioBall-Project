<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>JIOBall Login</title>

<link rel="stylesheet" href="/assets/css/style.css">

</head>

<body>

	<div class="content-wrapper" style="width: 100vw; height: 100vh;">
		<div class="card col-lg-4 mx-auto" style="margin: 120px auto;">
			<div class="card-body px-5 py-5">
				<h1 class="card-title text-left mb-3">로그인</h1>
				<br>
				<form action="/login/login" method="post">
					<div class="form-group">
						<label>이메일 *</label> 
						<div class ="text-danger">잘못된 아이디 또는 비밀번호입니다.</div>
						<input type="text"
							class="form-control p_input" name="userid" required>
					</div>
					<div class="form-group">
						<label>패스워드 *</label> 
						<div class ="text-danger">잘못된 아이디 또는 비밀번호입니다.</div>
						<input type="password"
							class="form-control p_input" name="userpw" required>
					</div>
					<br>
					<button type="submit" class="btn btn-primary btn-block enter-btn">로그인</button>
					<button type="button" class="btn btn-warning btn-block enter-btn"
						id="kakaologin">카카오톡 로그인</button>
					<button type="button" class="btn btn-info btn-block enter-btn"
						onClick="location.href='/login/join'">회원가입</button>
					<br>
					<div style="text-align: center">
						<p class="sign-up" style="margin-bottom: 0;">
							아이디를 잊으셨나요? <a class="text-primary" onclick="openPopId()">아이디
								찾기</a><br> 비밀번호를 잊으셨나요? <a class="text-primary"
								onclick="openPopPw()">비밀번호 찾기</a>
					</div>
				</form>
				<form id="form-kakao-login" method="post" action="/login/kakaologin">
		    			<input type="hidden" name="userkakaoid"/>
		    	</form>
			</div>
		</div>
	</div>

	<script src="/assets/vendors/js/vendor.bundle.base.js"></script>
	<script type="text/javascript">
function openPopId() {
		
		var width = 450;
		var height = 400;
		var top = (window.screen.height/4);
		var left = (window.screen.width/2) - (width/2);
		
		var popup = window.open('/login/findid',"FindId","width="+width+",height="+height+",top="+top+",left="+left);
	
	}
	
function openPopPw() {
		
		var width = 450;
		var height = 400;
		var top = (window.screen.height/4);
		var left = (window.screen.width/2) - (width/2);
		
		var popup = window.open('/login/findpw',"FindId","width="+width+",height="+height+",top="+top+",left="+left);
	
	}

</script>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.2.4.js"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript">
	
       Kakao.init('a774b2d598acd9f4c5943080d82c7ca8');
       

       $("#kakaologin").on("click", function(){
    	   
    	   Kakao.Auth.login({
   			success:function(auth){
   				Kakao.API.request({
   					url: '/v2/user/me',
   					success: function(response){
   						var account = response.kakao_account;
   						
   						$('#form-kakao-login input[name=userkakaoid]').val(account.email);
   						document.querySelector('#form-kakao-login').submit();
   					}
   				});
   			}
   		}); 
   	}) 
	</script>
</body>
</html>