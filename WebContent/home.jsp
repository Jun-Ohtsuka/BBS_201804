<%@page pageEncoding="UTF-8" contentType="text/html" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/layout/layout.jsp">
	<c:param name="title" value="BBS" />
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
		<c:if test="${ not empty loginUser }">
			<form name="search" action="home" method="get">
				検索<br> カテゴリー：
				<input type="text" name="category" value="<c:out value="${searchText }" />">
				を含む(空白なら全件検索)<br> 投稿日時 ：
				<input type="date" name="startDate" value="<c:out value="${searchStartDate }" />">
				～
				<input type="date" name="endDate" value="<c:out value="${searchEndDate }" />">
				<input type="submit" value="検索実行">
			</form>

			<a href="newMessage">新規投稿</a>
			<div class="messages">
				<c:forEach items="${articles}" var="article">
					<form name="addComment" action="home" method="post">
						<input type="hidden" name="threadId" id="threadId" value="${article.id }">
						<input type="hidden" name="userId" id="userId" value="${loginUser.id }">
						<table class="thread" border="1" cellspacing="0">
							<tr>
								<th class="thread">Name：<span class="name"><c:out value="${article.name }" /></span>@<span class="account"><c:out value="${article.account}" />
								</span> 投稿日時：<fmt:formatDate value="${article.created_date }" pattern="yyy/MM/dd HH:mm:ss" />
								<input class="delete-button" type="submit" name="submit" value="この投稿を削除する" onclick='return confirm("本当に削除してよろしいですか？");' />
								</th>
							</tr>
							<tr>
								<td class="title">件名：<c:out value="${article.title }" /></td>
							</tr>
							<tr>
								<td class="category">カテゴリー：<c:out value="${article.category }" /></td>
							</tr>
							<tr>
								<td class="text"><pre><c:out value="${article.text }" /></pre></td>
							</tr>
							<tr>
								<td class="submit_button"><textarea name="comment" id="comment" cols="100" maxlength="500"></textarea> <input type="submit" value="コメントする" /></td>
							</tr>
							<c:forEach items="${comments}" var="comment">
								<c:if test="${comment.messageId == article.id }">
									<tr>
										<td class="text"><pre><c:out value="${comment.text }" /></pre></td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</form>
				</c:forEach>
			</div>
		</c:if>
	</c:param>
</c:import>
