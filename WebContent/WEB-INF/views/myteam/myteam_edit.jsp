<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>JIOBALL - MYTEAM 수정</title>

<link rel="stylesheet" href="/assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="/assets/vendors/select2/select2.min.css">
<link rel="stylesheet" href="/assets/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
<link rel="stylesheet" href="/assets/vendors/jvectormap/jquery-jvectormap.css">
<link rel="stylesheet" href="/assets/vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="/assets/vendors/owl-carousel-2/owl.carousel.min.css">
<link rel="stylesheet" href="/assets/vendors/owl-carousel-2/owl.theme.default.min.css">
<link rel="stylesheet" href="/assets/css/style.css">
<link rel="shortcut icon" href="/assets/images/favicon.png">

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/assets/js/file-upload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	// 팀 정보 수정 버튼
	$("#btnEdit").click(function() {
		$(this).parents("form").submit();
	})
	
	// 팀 정보 수정 취소 버튼
	$("#btnCancel").click(function() {
		window.close();
	})
})
</script>

</head>

<body>

<div class="content-wrapper text-secondary">
	<div class="page-header">
		<h3 class="page-title">MY TEAM 수정</h3>
	</div>
	<div class="row">
		<div class="col-lg-12 grid-margin">
			<div class="card">
				<div class="card-body">
					<form action="/myteam/edit" method="post" class="forms-sample">
						<table class="table text-secondary">
							<tr>
								<th>팀 이름</th>
								<td><input type="text" class="form-control form-input text-secondary" name="teamName" placeholder="팀 이름을 입력해주세요"></td>
							</tr>
							<tr>
								<th>팀 소개</th>
								<td><textarea class="form-control textarea-input text-secondary" name="teamIntroduce" placeholder="내용을 입력해주세요"></textarea></td>
							</tr>
							<tr>
								<th>선호 종목</th>
								<td>
									<div class="form-check text-secondary">
										<label for="futsal" class="form-check-label form-radio-label text-secondary">
										<input type="radio" id="futsal" class="form-radio-input text-secondary" name="playTypeNo" value="1" checked>풋살</label>
									</div>
									
									<div class="form-check text-secondary">
										<label for="football" class="form-check-label form-radio-label text-secondary">
										<input type="radio" id="football" class="form-radio-input form-check-primary text-secondary" name="playTypeNo" value="2">축구</label>
									</div>	
								</td>
							</tr>
							<tr>
								<th>선호 지역</th>
								<td>
									<select class="form-control form-select text-secondary" name="playLocalNo">
										<option value="1">김해</option>
										<option value="2" selected>부산</option>
										<option value="3">양산</option>
										<option value="4">진주</option>
										<option value="5">창원</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>팀 성별</th>
								<td>
									<div class="form-check">
										<label for="m" class="form-check-label form-radio-label">
										<input type="radio" id="m" class="form-radio-input text-secondary" name="teamGender" value="1" checked>남성</label>
									</div>
									<div class="form-check">
										<label for="f" class="form-check-label form-radio-label">
										<input type="radio" id="f" class="form-radio-input form-check-primary text-secondary" name="teamGender" value="2">여성</label>
									</div>	
									<div class="form-check">
										<label for="h" class="form-check-label form-radio-label">
										<input type="radio" id="h" class="form-radio-input form-check-primary text-secondary" name="teamGender" value="3">혼성</label>
									</div>	
								</td>
							</tr>
							<tr>
								<th>유니폼 색상</th>
								<td>
									<select class="form-control form-select text-secondary" name="teamUniform">
										<option value="빨강">빨강</option>
										<option value="주황">주황</option>
										<option value="노랑">노랑</option>
										<option value="초록" selected>초록</option>
										<option value="파랑">파랑</option>
										<option value="남색">남색</option>
										<option value="보라">보라</option>
									</select>
								</td>
							</tr>
							<tr>
								<th></th>
								<td></td>
							</tr>
						</table>
						<div style="text-align: center;">
							<button type="button" id="btnCancel" class="btn btn-secondary btn-icon-text btn-cancle">취소</button>
							<button type="button" id="btnEdit" class="btn btn-primary btn-icon-text btn-ground-insert-submit">수정</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/assets/vendors/js/vendor.bundle.base.js"></script>
<script src="/assets/vendors/chart.js/Chart.min.js"></script>
<script src="/assets/vendors/progressbar.js/progressbar.min.js"></script>
<script src="/assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
<script src="/assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="/assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
<script src="/assets/js/off-canvas.js"></script>
<script src="/assets/js/hoverable-collapse.js"></script>
<script src="/assets/js/misc.js"></script>
<script src="/assets/js/settings.js"></script>
<script src="/assets/js/todolist.js"></script>
<script src="/assets/js/dashboard.js"></script>
</body>
</html>
 

