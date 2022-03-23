<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h3 class="text-secondary">회원 탈퇴</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body">
			<div class="card col-lg-4 mx-auto" style="margin:120px auto;">
			<div class="card-body px-5 py-5">
				<h3 class="card-title text-left mb-3">탈퇴하시겠습니까?</h3>
				<br>
				<% if(session.getAttribute("userId").toString().substring(0, 7).equals("[kakao]")) { %> 
				<form action="/mypage/withdrawalkakao" method="post">
					<div class="form-group">
					</div><br>
					<button type="submit" class="btn btn-inverse-secondary btn-block">탈퇴</button>
				</form>
				<% } else { %>
					<form action="/mypage/withdrawal" method="post">
					<div class="form-group">
						<label>패스워드를 입력하십시오.</label> <input type="password" class="form-control p_input" name="userPw" required>
					</div><br>
					<button type="submit" class="btn btn-inverse-secondary btn-block">탈퇴</button>
				</form>
				<% } %>
			</div>
		</div>
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>