<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
    
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#formbutton").click(function buttonclick(){
		var userpw = $("#userpw").val();
		var reuserpw = $("#reuserpw").val();
		var pwtest1 = /[0-9]/;
		var pwtest2 = /[A-Za-z]/;
		var pwtest3	= /[~!@#$^&*()_+|<>?:]/;

		if(!pwtest1.test(userpw)&&pwtest2.test(userpw)&&!pwtest3.test(userpw)){
			$('#checkpw').html('&nbsp;&nbsp;문자,숫자,특수문자로 입력하여야 합니다.');
			$('#checkpw').attr('class', 'text-warning');
			return false;
		} else if(userpw.length<8||userpw.length>20){
			$('#checkpw').html('&nbsp;&nbsp;8자이상 20자이하로 입력하여야 합니다.');
			$('#checkpw').attr('class', 'text-warning');
			return false;
		} else if(userpw.search(/\s/) > -1){
			$('#checkpw').html('&nbsp;&nbsp;&nbsp;공백 없이 입력하여야 합니다.');
			$('#checkpw').attr('class', 'text-warning');
			return false;
		} else {	
			$('#checkpw').html('');
				
				if(userpw != reuserpw){
					$('#checkrepw').html('&nbsp;&nbsp;&nbsp;서로 다른 비밀번호입니다.');
					$('#checkrepw').attr('class', 'text-warning');		
					return false;
				} else {
					$('#checkrepw').html('');
					
					var username = $("#username").val();
					var nametest = /^[가-힣]+$/;
					
					if(!nametest.test(username)){
						$('#checkname').html('&nbsp;&nbsp;&nbsp;한글로 입력하여야 합니다.');
						$('#checkname').attr('class', 'text-warning');	
						return false;
					} else {
						$('#checkname').html('');
						
								var userphone = $("#userphone").val();
								var phonetest = /^[0-9]+$/;
								
								if(!phonetest.test(userphone) || userphone.length != 8){
									$('#checkphone').html('&nbsp;&nbsp;&nbsp;휴대폰 번호가 올바르지 않습니다.');
									$('#checkphone').attr('class', 'text-warning');	
									return false;
								} else {
									$('#checkphone').html('');
									return true;
								}
					}
				}
			}
	})	
	
	$("#userpw").blur(function checkpw(){
		
		var userpw = $("#userpw").val();
		var pwtest1 = /[0-9]/;
		var pwtest2 = /[A-Za-z]/;
		var pwtest3	= /[~!@#$^&*()_+|<>?:]/;
		
		if(!pwtest1.test(userpw)||!pwtest2.test(userpw)||!pwtest3.test(userpw)){
			$('#checkpw').html('&nbsp;&nbsp;문자,숫자,특수문자로 입력하여야 합니다.');
			$('#checkpw').attr('class', 'text-warning');
		} else {
			if(userpw.length<8||userpw.length>20){
				$('#checkpw').html('&nbsp;&nbsp;8자이상 20자이하로 입력하여야 합니다.');
				$('#checkpw').attr('class', 'text-warning');
				} else {
					if(userpw.search(/\s/) == -1) {	
					$('#checkpw').html('&nbsp;&nbsp;&nbsp;사용 가능한 비밀번호입니다.');
					$('#checkpw').attr('class', 'text-success');	
				} else {
					$('#checkpw').html('&nbsp;&nbsp;&nbsp;공백 없이 입력하여야 합니다.');
					$('#checkpw').attr('class', 'text-warning');	
				}
			}
		}
	});
	
	$("#reuserpw").blur(function checkrepw(){
		
		if(userpw != reuserpw){
			$('#checkrepw').html('&nbsp;&nbsp;&nbsp;서로 다른 비밀번호입니다.');
			$('#checkrepw').attr('class', 'text-warning');		
		} else {
			$('#checkrepw').html('');
		}
	})
	
	$("#username").blur(function checkname(){
		
		var username = $("#username").val();
		var nametest = /^[가-힣]+$/;
		
		if(nametest.test(username)){
			$('#checkname').html('');
		} else {
			$('#checkname').html('&nbsp;&nbsp;&nbsp;한글로 입력하여야 합니다.');
			$('#checkname').attr('class', 'text-warning');	
		}
	});
	
	$("#userphone").blur(function checkphone(){
		
		var userphone = $("#userphone").val();
		var phonetest = /^[0-9]+$/;
		
		if(phonetest.test(userphone) && userphone.length == 8){
			$('#checkphone').html('');
		} else {
			$('#checkphone').html('&nbsp;&nbsp;&nbsp;휴대폰 번호가 올바르지 않습니다.');
			$('#checkphone').attr('class', 'text-warning');	
		}
	});
	
})
</script>

<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h3 class="text-secondary">개인 정보 변경</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
		<form action="/mypage/userinfo" method="post" id="form" >
			<div class="card-body">
						<br>
						<% if(userInfo.getUserId().toString().substring(0, 7).equals("[kakao]")) {%>
						<label for="userid">이메일</label>
						<h4 class="text-secondary"><%= userInfo.getUserId().substring(7) %></h4><hr>
						<label for="userpw">비밀번호</label><div id="checkpw" style="display:inline;"></div><br>
						<h4 class="text-secondary">카카오 로그인을 이용하신 분은 비밀번호를 변경할 수 없습니다.</h4><br>
						<input type="hidden" name="userpw" id="userpw" value="<%= userInfo.getUserPw() %>">
						<input type="hidden" name="reuserpw" id="reuserpw" class="form-control" value="<%= userInfo.getUserPw() %>">
						<% } else { %>
						<label for="userid">이메일</label>
						<h4 class="text-secondary"><%= userInfo.getUserId()%></h4>
						<br>
						<label for="userpw">비밀번호</label><div id="checkpw" style="display:inline;"></div>
						<input type="password" name="userpw" id="userpw" class="form-control" value="<%= userInfo.getUserPw() %>"  required/><br>		
						<label for="reuserpw">비밀번호 확인</label><div id="checkrepw" style="display:inline;"></div>
						<input type="password" name="reuserpw" id="reuserpw" class="form-control" required><br><% } %>	
						<label for="username">이름</label><div id="checkname" style="display:inline;"></div>
						<input type="text" name="username" id="username" class="form-control" value="<%= userInfo.getUserName() %>" required><br>	
						<label for="usertelecom">전화번호</label><div id="checkphone" style="display:inline;"></div>
						<div style="display:flex;">
						<div style="flex:0.3;">
						<% if("skt".equals(userInfo.getUserTelecom()) || "SKT".equals(userInfo.getUserTelecom())) {%>
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt" selected>SKT</option>
						<option value="kt">KT</option>
						<option value="lg">LG</option>
						<option value="skt알뜰폰">SKT알뜰폰</option>
						<option value="kt알뜰폰">KT알뜰폰</option>
						<option value="lg알뜰폰">LG알뜰폰</option>
						</select>
						<% } else if("kt".equals(userInfo.getUserTelecom()) || "KT".equals(userInfo.getUserTelecom())) { %>
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt">SKT</option>
						<option value="kt" selected>KT</option>
						<option value="lg">LG</option>
						<option value="skt알뜰폰">SKT알뜰폰</option>
						<option value="kt알뜰폰">KT알뜰폰</option>
						<option value="lg알뜰폰">LG알뜰폰</option>
						</select>
						<% } else if("lg".equals(userInfo.getUserTelecom()) || "LGU".equals(userInfo.getUserTelecom())) { %>
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt">SKT</option>
						<option value="kt">KT</option>
						<option value="lg" selected>LG</option>
						<option value="skt알뜰폰">SKT알뜰폰</option>
						<option value="kt알뜰폰">KT알뜰폰</option>
						<option value="lg알뜰폰">LG알뜰폰</option>
						</select>
						<% } else if("skt알뜰폰".equals(userInfo.getUserTelecom())) { %>
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt">SKT</option>
						<option value="kt">KT</option>
						<option value="lg">LG</option>
						<option value="skt알뜰폰" selected>SKT알뜰폰</option>
						<option value="kt알뜰폰">KT알뜰폰</option>
						<option value="lg알뜰폰">LG알뜰폰</option>
						</select>
						<% } else if("kt알뜰폰".equals(userInfo.getUserTelecom())) { %>
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt">SKT</option>
						<option value="kt">KT</option>
						<option value="lg">LG</option>
						<option value="skt알뜰폰">SKT알뜰폰</option>
						<option value="kt알뜰폰" selected>KT알뜰폰</option>
						<option value="lg알뜰폰">LG알뜰폰</option>
						</select>
						<% } else if("lg알뜰폰".equals(userInfo.getUserTelecom())) { %>
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt">SKT</option>
						<option value="kt" selected>KT</option>
						<option value="lg">LG</option>
						<option value="skt알뜰폰">SKT알뜰폰</option>
						<option value="kt알뜰폰">KT알뜰폰</option>
						<option value="lg알뜰폰" selected>LG알뜰폰</option>
						</select>
						<% } %></div>
						<div style="flex:0.2;">
						<select name="userphone1" id="userphone1"  class="form-control form-select text-secondary">
						<option value="010" selected>010</option>
						<option value="011">011</option>
						<option value="017">017</option>
						</select></div>
						<div style="flex:0.7; text-align:left">
						<input type="text" class="form-control form-control" name="userphone" id="userphone" value="<%= userInfo.getUserPhone().substring(3) %>" required></div></div><hr><hr>
						<button type="submit" class="btn btn-primary btn-block" id="formbutton">변경 완료</button>
					</form>
				</div>
			</div>
		</div>

<%@ include file="../../layout/footer.jsp" %>
