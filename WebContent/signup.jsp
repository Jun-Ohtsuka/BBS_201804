<%@page pageEncoding="UTF-8" contentType="text/html" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/layout/layout.jsp">
	<c:param name="title" value="ユーザ登録" />
	<c:param name="content">
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li>
							<c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>
		<form action="signup" method="post">
			<label for="name">名前</label>
			<input name="name" id="name" />
			<br />
			<label for="account">アカウント名</label>
			<input name="account" id="account" />
			<br />
			<label for="password">パスワード</label>
			<input name="password" type="password" id="password" />
			<br />
			<label for="password">パスワード確認用</label>
			<input name="checkPassword" type="password" id="checkPassword" />
			<br />
			<label for="email">メールアドレス</label>
			<input name="email" id="email" />
			<br />
			<input type="submit" value="登録" />
			<br /> <a href="./">戻る</a>
		</form>
	</c:param>
</c:import>