<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		submitContents($("#btnWrite"));
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

//에디터 작성내용 <textarea>에 반영
function submitContents( elClickedObj ){
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	try{
		elClickedObj.form.submit();
	}catch(e){}
}
</script>
</head>
<%@ include file="../../layout/headerManager.jsp" %>

<div class="page-header">

	<h3 class="page-title">공지사항 작성</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1000px;">
			<div class="card-body">
			
<div style="width: 800px;">
<form action="/manager/notice/write" method="post">
<table class="table text-secondary">
	
	<tr>
		<th>공지사항 제목</th>
		<td><input type="text" name="title" class="form-control text-secondary" ></td>
	</tr>
	
	<tr>
		<th>공지사항 내용</th>
		<td><div style="background-color: white;"><textarea id="content" name="content" style="height: 400px"></textarea></div></td>
	</tr>
	

</table>
</form>
</div>
<br><br><br>
<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-primary" >작성</button>
	<button type="button" id="btnCancel" class="btn btn-secondary">취소</button>
</div>
			
			
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreate: "createSEditor2"
	
})

</script>
<%@ include file="../../layout/footerManager.jsp" %>