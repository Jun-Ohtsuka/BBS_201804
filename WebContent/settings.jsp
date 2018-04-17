<%@page pageEncoding="UTF-8" contentType="text/html" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/layout/layout.jsp">
	<c:param name="title" value="ユーザ編集" />
	<c:param name="content">
		<h1>ユーザ編集画面</h1>

		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li>
							<c:out value="${message}" />
						</li>
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>

		<form action="settings" method="post">
			<br />
			<input name="id" value="${editUser.id}" id="id" type="hidden" />
			<label for="name">名前</label>
			<input name="name" value="${editUser.name}" id="name" />
			<br />

			<label for="account">アカウント名</label>
			<input name="account" value="${editUser.account}" />
			<br />

			<label for="password">パスワード</label>
			<input name="password" type="password" id="password" />
			<br />

			<label for="password">パスワード確認用</label>
			<input name="checkPassword" type="password" id="password" />
			<br />

			<label for="email">メールアドレス</label>
			<input name="email" value="${editUser.email}" id="email" />
			<br />

			<label for="email">所属</label>
			<select name="branch">
				<c:forEach items="${branchs}" var="branch">
					<option value="${branch.id}" <c:if test="${branch.id == editUser.branchId }">selected</c:if>><c:out value="${branch.name }" /></option>
				</c:forEach>
			</select>
			<br />

			<label for="email">役職</label>
			<select name="position">
				<c:forEach items="${positions}" var="position">
					<option value="${position.id}" <c:if test="${position.id == editUser.positionId }">selected</c:if>><c:out value="${position.name }" /></option>
				</c:forEach>
			</select>
			<br />

			<input type="submit" value="登録" />
		</form>
	</c:param>
</c:import>