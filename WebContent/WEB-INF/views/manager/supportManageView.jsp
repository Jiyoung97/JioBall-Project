
<%@page import="dto.SupportFile"%>
<%@page import="dto.Support"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Support view = (Support)request.getAttribute("supportView");%>
<% SupportFile file = (SupportFile)request.getAttribute("supportFile");%>
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

function submitContents( elClickedObj ){
	oEditors.getById["answer"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	try{
		elClickedObj.form.submit();
	}catch(e){}
}

</script>

<%@ include file="../../layout/headerManager.jsp" %>

<div class="page-header">

	<h2 class="page-title">문의내역</h2>
</div>

<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">
			
			
<div style="width: 800px; margin: 0 auto;">




<div style=" border-top: 2px solid white; border-bottom: 1px solid; height: 50px; width: 800px; display: table-cell; vertical-align: middle;">

	<div style="float: left;"><p>
	<small><%= view.getSupportType() %></small>
	&nbsp;&nbsp;<b style="font-size:1.3em"><%= view.getSupportTitle() %></b></p></div>

	<div style="float: right;"><%=(view.getSupportDate()).substring(0,11)%></div>
	
</div>

<br>

<div style="height: 400px; border-top: 2px solid white; border-bottom: 1px solid; background-color: #343a40;">
<p><%= view.getSupportContent() %></p>
</div>
<br>
<div>
	<p><span class="mdi mdi-download"></span>&nbsp;&nbsp;
	<%if(file.getSupportFileOriginName()!=null){ %><a href="<%= request.getContextPath()%>/upload/<%=file.getSupportFileStoredName()%>" download ="<%=file.getSupportFileOriginName()%>">
	<%= file.getSupportFileOriginName() %></a><%}else{ %>파일없음<%} %></p>
</div>

<div style=" border-top: 2px solid white; border-bottom: 1px solid; height: 50px; width: 800px; display: table-cell; vertical-align: middle;">

	<div style="float: left;"><b style="font-size:1.3em">답변</b></div>

	<div style="float: right;"><%if(view.getSupportAnswerDate() !=null){ %>
	<%= (view.getSupportAnswerDate()).substring(0,11) %>
	<%} else{ %><%} %></div>
	
</div>
<br>

<div style=" border-top: 2px solid white; border-bottom: 1px solid; background-color: white;">
<form action="/manager/support/update" method="post">
	<input type="hidden" name="supportno" value="<%=view.getSupportNo() %>" />
	<textarea id="answer" name="answer" style="width: 800px;"><%if(view.getSupportAnswer()!=null){ %><%=view.getSupportAnswer() %><%}else{ %><%} %>
	</textarea>
</form>
</div>




<br>
<div class="text-center">
<br>
	<button type="button" id="btnWrite" class="btn btn-primary" ><%if(view.getSupportAnswer()!=null){ %>수정<%}else{ %>작성<%} %></button>
	<button type="button" id="btnCancel" class="btn btn-secondary">취소</button>
<br>
</div>

</div>
			
			
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "answer",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreate: "createSEditor2"
	
})

</script>
			
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footerManager.jsp" %>