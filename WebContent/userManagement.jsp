<%@page pageEncoding="UTF-8" contentType="text/html" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/layout/layout.jsp">
	<c:param name="title" value="ユーザ管理" />
	<c:param name="content">
		<c:if test="${ not empty messages }">
			<div class="messages">
				<ul>
					<c:forEach items="${messages}" var="message">
						<li>
							<c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="messages" scope="session" />
		</c:if>

	<a href="signup">ユーザ作成</a>
		<table class="users" border="1">
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>アカウント</th>
				<th>E-mail</th>
				<th>所属</th>
				<th>役職</th>
				<th>状態</th>
				<th>状態変更</th>
				<th>登録日</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td class="id"><c:out value="${user.id}" /></td>
					<td class="name"><a href="settings?id=${user.id}"><c:out value="${user.name}" /></a></td>
					<td class="account"><c:out value="${user.account}" /></td>
					<td class="email"><c:out value="${user.email}" /></td>
					<td class="branch"><c:out value="${user.branchId}" /></td>
					<td class="position"><c:out value="${user.positionId}" /></td>
					<form action="userManagement" method="post">
						<input type="hidden" name="id" value="${user.id}">
						<input type="hidden" name="name" value="${user.account}">
						<c:if test="${ user.freeze == 0 }">
							<input type="hidden" name="freezeVal" value="1">
							<td class="deleted">利用可能</td>
							<td><c:if test="${user.id != loginUser.id }">
							<input id="submit_button" type="submit" value="停止" onclick="return confirm('「${user.name}」を停止します。\nよろしいですか？');">
							</c:if></td>
						</c:if>
						<c:if test="${ user.freeze == 1 }">
							<input type="hidden" name="freezeVal" value="0">
							<td class="deleted">利用不可</td>
							<td><input id="submit_button" type="submit" value="停止解除" onclick="return confirm('「${user.name}」の停止を解除します。\nよろしいですか？');"></td>
						</c:if>
					</form>
					<td class="create_date"><fmt:formatDate value="${user.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:param>
</c:import>