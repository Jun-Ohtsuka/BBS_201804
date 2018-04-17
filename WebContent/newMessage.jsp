<%@page pageEncoding="UTF-8" contentType="text/html" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/layout/layout.jsp">
	<c:param name="title" value="新規投稿" />
	<c:param name="content">
	<div class="form-area">
		<form action="newMessage" method="post">
			title<br />
			<input type="text" name="title" maxlength="30" size="40"/>
			<br>text<br />
			<textarea name="text" cols="100" rows="5" maxlength="1000"></textarea>
			<br>category<br />
			<input type="text" name="category" maxlength="10">
			<br />
			<input type="submit" value="新規投稿">
		</form>
	</div>
	</c:param>
</c:import>