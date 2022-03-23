<%@page import="dto.GroundInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% List<GroundInfo> list = (List)request.getAttribute("list");%>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>
</head>
<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h3 class="page-title">게시글 작성</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">
			
<div style="width: 1200px;">
<form action="/match/write" method="post">
<table class="table text-secondary">
	
	
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" class="form-control text-secondary" ></td>
	</tr>
	
	<tr>
		<th>내용</th>
		<td><textarea id="content" name="comment" class="form-control text-secondary" ></textarea></td>
	</tr>
	<tr>
		<th>경기일자</th>
		<td><input type="text" name="playDate" class="form-control text-secondary" placeholder="ex)22/03/24"></td>
	</tr>
	<tr>
		<th>경기인원수</th>
		<td><select = name="person" class="form-control text-secondary">
			<option value="1">5:5</option>
			<option value="2">6:6</option>
			<option value="3">11:11</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>경기종목</th>
		<td><select = name="playType" class="form-control text-secondary">
			<option value="1">풋살</option>
			<option value="2">축구</option>
			</select>
		</td>
	</tr>
	
	
	<tr>
		<th>구장</th>
		<td><select name="groundNo" class="form-control text-secondary" >
			<% for (int i = 0; i < list.size(); i++) { %>
			<option value="<%=list.get(i).getGroundNo() %>"><%=list.get(i).getGroundName()%></option>
			<%} %>
			</select>
		</td>
	
	</tr>
	
	<tr>
		<th>경기 지역</th>
		<td><select name="local" class="form-control text-secondary" >
		<option value="gimhae">김해</option>
		<option value="busan">부산</option>
		<option value="yangsan">양산</option>
		<option value="jinju">진주</option>
		<option value="changwon">창원</option>
		</select>
		</td>
	
	</tr>
	

</table>
</form>
</div>
<br>
<div class="text-center">	
	<button type="submit" id="btnWrite" class="btn btn-primary" >작성</button>
	<button type="button" id="btnCancel" class="btn btn-secondary">취소</button>
</div>
			
			
			</div>
		</div>
	</div>
</div>


<%@ include file="../../layout/footer.jsp" %>