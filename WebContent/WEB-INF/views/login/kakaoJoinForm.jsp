<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%	String userid = (String) request.getAttribute("userid"); %>
	<%	String userpw = (String) request.getAttribute("userpw"); %>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<title>JIOBall Join</title>

<link rel="stylesheet" href="/assets/css/style.css">


</head>

<body>
		<div class="card col-lg-4 mx-auto" style="margin:10px auto;">
			<div class="card-body px-2 py-2">
			<form action="/login/join" method="post" id="form" onsubmit="return false">
				<div style="display:flex;">
				<div style="flex:0.6;"><h1>회원가입</h1></div>
				<div style="flex:0.4; text-align:right;" class="text-success"><br><h4>with kakao</h4></div></div><br>
						<input type="hidden" name="userid" value="<%= userid%>">
						<input type="hidden" name="userpw" value="<%= userpw%>">
						<label for="username">이름</label><div id="checkname" style="display:inline;"></div>
						<input type="text" name="username" id="username" class="form-control" required><br>	
						<label for="usergender">성별</label>
						<div style="display:flex;">
						<div style="flex:1;"></div>
						<div style="flex:1;">
						<input type="radio" name="usergender" id="usergender" value="1" checked>&nbsp;남</div>
						<div style="flex:1;">
						<input type="radio" name="usergender" id="usergender" value="2">&nbsp;여</div></div><br>
						<label for="userbirth">생년월일</label><div id="checkbirth" style="display:inline;"></div>
						<input type="text" name="userbirth" id="userbirth" class="form-control" placeholder="2001/01/01" required><br>
						<label for="usertelecom">전화번호</label><div id="checkphone" style="display:inline;"></div>
						<div style="display:flex;">
						<div style="flex:0.3;">
						<select name="usertelecom" id="usertelecom"  class="form-control form-select text-secondary">
						<option value="skt">SKT</option>
						<option value="kt">KT</option>
						<option value="lg">LG</option>
						<option value="skt알뜰폰">SKT알뜰폰</option>
						<option value="kt알뜰폰">KT알뜰폰</option>
						<option value="lg알뜰폰">LG알뜰폰</option>
						</select></div>
						<div style="flex:0.2;">
						<select name="userphone1" id="userphone1"  class="form-control form-select text-secondary">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
						</select></div>
						<div style="flex:0.7; text-align:left">
						<input type="text" class="form-control form-control" name="userphone" id="userphone" required></div></div><br>
				<label for="teamname">팀 이름</label><div id="checktname" style="display:inline;"></div>
				<input type="text" name="teamname" id="teamname" class="form-control" required><br>
				<label for="teamgender">팀 성별</label><br>
				<div style="display:flex;">
				<div style="flex:1;"></div>
				<div style="flex:1;">
				<input type="radio" name="teamgender" id="teamgender" value="1" checked>&nbsp;남</div>
				<div style="flex:1;">
				<input type="radio" name="teamgender" id="teamgender" value="2">&nbsp;여</div>
				<div style="flex:1;">
				<input type="radio" name="teamgender" id="teamgender" value="3">&nbsp;혼성</div></div><br>
				<label for="playlocalno">경기 지역</label>
				<div style="text-align:center;">
				<select name="playlocalno" id="playlocalno" class="form-control form-select text-secondary">
				<option value="1">김해</option>
				<option value="2">부산</option>
				<option value="3">양산</option>
				<option value="4">진주</option>
				<option value="5">창원</option>
				</select></div><br>
				<label for="teamintroduce">팀 소개</label><div class="text-primary" style="display:inline;">&nbsp;&nbsp;&nbsp;선택사항</div>
				<textarea name="teamintroduce" id="teamintroduce" class="form-control"></textarea><br>
				<button type="submit" class="btn btn-primary btn-block" id="formbutton" >회원가입 완료</button>
				<button type="button" class="btn btn-warning btn-block" onClick="location.href='/login/login'">돌아가기</button>
				</form>
			</div>
		</div>

<script src="/assets/vendors/js/vendor.bundle.base.js"></script>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var namevali = false;
	var birthvali = false;
	var phonevali = false;
	var tnamevali = false;
	
	
	$("#username").blur(function checkname(){
		
		var username = $("#username").val();
		var nametest = /^[가-힣]+$/;
		
		if(nametest.test(username)){
			$('#checkname').html('');
			namevali = true;
		} else {
			$('#checkname').html('&nbsp;&nbsp;&nbsp;한글로 입력하여야 합니다.');
			$('#checkname').attr('class', 'text-warning');	
			namevali = false;
		}
	});
	
	$("#userbirth").blur(function checkbirth(){
		
		var userbirth = new Date($("#userbirth").val());
		var day1 = new Date("1940/01/01");
		var day2 = new Date("2019/12/31");
		
		if(($("#userbirth").val()).length!=10) {
			$('#checkbirth').html('&nbsp;&nbsp;&nbsp;****/**/** 의 형식으로 입력하십시오.');
			$('#checkbirth').attr('class', 'text-warning');	
			birthvali = false;
		} else {
			if(userbirth-day1>0 && 0<day2-userbirth){
				$('#checkbirth').html('');
				birthvali = true;
			} else {
				$('#checkbirth').html('&nbsp;&nbsp;&nbsp;올바른 생년월일을 입력하십시오.');
				$('#checkbirth').attr('class', 'text-warning');	
			birthvali = false;
			}
		}
	});
	
	
	$("#userphone").blur(function checkphone(){
		
		var userphone = $("#userphone").val();
		var phonetest = /^[0-9]+$/;
		
		if(phonetest.test(userphone) && userphone.length == 8){
			$('#checkphone').html('');
			phonevali = true;
		} else {
			$('#checkphone').html('&nbsp;&nbsp;&nbsp;휴대폰 번호가 올바르지 않습니다.');
			$('#checkphone').attr('class', 'text-warning');	
			phonevali= false;
		}
	});
	
	$("#teamname").blur(function checktname(){
		
		var teamname = $("#teamname").val();
		var tnametest = /^[가-힣a-zA-Z]+$/; 
		
		if(!tnametest.test(teamname)){
			$('#checktname').html('&nbsp;&nbsp;&nbsp;한글과 영문만 입력 가능합니다.');
			$('#checktname').attr('class', 'text-warning');
			tnamevali = false;
		} else {
			$.ajax({
			url : '/login/join/checkname?teamname=' + teamname,
			type : 'get',
			dataType : 'html',
			success : function(data) {
				if (data == 0) {
					$('#checktname').html('&nbsp;&nbsp;&nbsp;사용 가능한 팀이름입니다');
					$('#checktname').attr('class', 'text-success');
					tnamevali = true;
				} else {
					$('#checktname').html('&nbsp;&nbsp;&nbsp;이미 존재하는 팀이름입니다.');
					$('#checktname').attr('class', 'text-warning');
					tnamevali = false;
					}	
				}	
			})	
		} 
	});
	
	$("#formbutton").click(function buttonclick(){
		
		if(namevali&&birthvali&&phonevali&&tnamevali) {
			
			$('#form').attr('onsubmit','return true');
	} else {
		
		$('#form').attr('onsubmit','return false');
	}
	
	});
	
})
</script>

</body>
</html>
