<%@page import="dto.Matching"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  Matching matching = (Matching) request.getAttribute("matching");%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>JIOBALL - REVIEW </title>

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
	
	// 리뷰 [작성] 버튼
	$("#btnSubmit").click(function() {
		$(this).parents("form").submit();
	});
	
	// 리뷰작성 [취소] 버튼
	$("#btnCancel").click(function() {
		window.close();
	});
	
})
</script>

<style>

/* 매너점수 별 찍기 스타일 */

.starpoint_wrap{display:inline-block;}
.starpoint_box{position:relative;background:url(https://hsecode.github.io/images_codepen/codepen_sp_star.png) 0 0 no-repeat;font-size:0;}
.starpoint_box .starpoint_bg{display:block;position:absolute;top:0;left:0;height:18px;background:url(https://hsecode.github.io/images_codepen/codepen_sp_star.png) 0 -20px no-repeat;pointer-events:none;}
.starpoint_box .label_star{display:inline-block;width:10px;height:18px;box-sizing:border-box;}
.starpoint_box .star_radio{opacity:0;width:0;height:0;position:absolute;}
.starpoint_box .star_radio:nth-of-type(1):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(1):checked ~ .starpoint_bg{width:10%;}
.starpoint_box .star_radio:nth-of-type(2):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(2):checked ~ .starpoint_bg{width:20%;}
.starpoint_box .star_radio:nth-of-type(3):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(3):checked ~ .starpoint_bg{width:30%;}
.starpoint_box .star_radio:nth-of-type(4):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(4):checked ~ .starpoint_bg{width:40%;}
.starpoint_box .star_radio:nth-of-type(5):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(5):checked ~ .starpoint_bg{width:50%;}
.starpoint_box .star_radio:nth-of-type(6):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(6):checked ~ .starpoint_bg{width:60%;}
.starpoint_box .star_radio:nth-of-type(7):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(7):checked ~ .starpoint_bg{width:70%;}
.starpoint_box .star_radio:nth-of-type(8):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(8):checked ~ .starpoint_bg{width:80%;}
.starpoint_box .star_radio:nth-of-type(9):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(9):checked ~ .starpoint_bg{width:90%;}
.starpoint_box .star_radio:nth-of-type(10):hover ~ .starpoint_bg,
.starpoint_box .star_radio:nth-of-type(10):checked ~ .starpoint_bg{width:100%;}

.blind{position:absolute;clip:rect(0 0 0 0);margin:-1px;width:1px;height: 1px;overflow:hidden;}

</style>
</head>

<body>

<div class="content-wrapper text-secondary">
	<div class="page-header">
		<h3 class="page-title">REVIEW 작성</h3>
	</div>
	<div class="row">
		<div class="col-lg-12 grid-margin">
			<div class="card">
				<div class="card-body">
					<form action="/myteam/matching/review" method="post" class="forms-sample">
						<table class="table text-secondary">
							<tr>
								<th>상대팀명</th>
								<td>
									<%	if( (Integer)session.getAttribute("teamNo") == matching.getInviteTeamNo()) { %>
										<%=matching.getJoinTeamName() %>
									<%	} else if( (Integer)session.getAttribute("teamNo") == matching.getJoinTeamNo()) { %>
										<%=matching.getInviteTeamName() %>
									<%	} %>
								</td>
							</tr>
							<tr>
								<th>매너점수</th>
								<td>
								<div class="starpoint_wrap">
									<div class="starpoint_box">
									    <label for="starpoint_1" class="label_star" title="0.5"><span class="blind">0.5점</span></label>
									    <label for="starpoint_2" class="label_star" title="1"><span class="blind">1점</span></label>
									    <label for="starpoint_3" class="label_star" title="1.5"><span class="blind">1.5점</span></label>
									    <label for="starpoint_4" class="label_star" title="2"><span class="blind">2점</span></label>
									    <label for="starpoint_5" class="label_star" title="2.5"><span class="blind">2.5점</span></label>
									    <label for="starpoint_6" class="label_star" title="3"><span class="blind">3점</span></label>
									    <label for="starpoint_7" class="label_star" title="3.5"><span class="blind">3.5점</span></label>
									    <label for="starpoint_8" class="label_star" title="4"><span class="blind">4점</span></label>
									    <label for="starpoint_9" class="label_star" title="4.5"><span class="blind">4.5점</span></label>
									    <label for="starpoint_10" class="label_star" title="5"><span class="blind">5점</span></label>
									    <input type="radio" name="reviewManner" id="starpoint_1" class="star_radio" value="0.5">
									    <input type="radio" name="reviewManner" id="starpoint_2" class="star_radio" value="1">
									    <input type="radio" name="reviewManner" id="starpoint_3" class="star_radio" value="1.5">
									    <input type="radio" name="reviewManner" id="starpoint_4" class="star_radio" value="2">
									    <input type="radio" name="reviewManner" id="starpoint_5" class="star_radio" value="2.5">
									    <input type="radio" name="reviewManner" id="starpoint_6" class="star_radio" value="3">
									    <input type="radio" name="reviewManner" id="starpoint_7" class="star_radio" value="3.5">
									    <input type="radio" name="reviewManner" id="starpoint_8" class="star_radio" value="4">
									    <input type="radio" name="reviewManner" id="starpoint_9" class="star_radio" value="4.5">
									    <input type="radio" name="reviewManner" id="starpoint_10" class="star_radio" value="5">
									    <span class="starpoint_bg"></span>
									</div>
								</div>

								</td>
							</tr>
							<tr>
								<th>경기 결과(이긴 팀)</th>
								<td>
									<div class="form-check ">
										<label for="inviteTeamWin" class="form-check-label form-radio-label text-secondary">
										<input type="radio" id="inviteTeamWin" class="form-radio-input text-secondary" name="reviewResult" value="1" checked>
										<%=matching.getInviteTeamName() %>
										</label>
									</div>
									
									<div class="form-check">
										<label for="joinTeamWin" class="form-check-label form-radio-label text-secondary">
										<input type="radio" id="joinTeamWin" class="form-radio-input form-check-primary text-secondary" name="reviewResult" value="2">
										<%=matching.getJoinTeamName() %>
										</label>
									</div>	
								</td>
							</tr>
						</table>
						<br>
						<div style="text-align: center;">
							<button type="button" id="btnCancel" class="btn btn-secondary btn-icon-text btn-cancle">취소</button>
							<button type="button" id="btnSubmit" class="btn btn-primary btn-icon-text btn-ground-insert-submit">확인</button>
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
 

