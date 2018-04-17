<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title>${param.title}</title>
</head>
<body>
	<div class="main-contents">
		<div class="header">
			<c:if test="${ empty loginUser }">
				<a href="signin">サインイン</a>
				<a href="signup">登録する</a>
			</c:if>
			<c:if test="${ not empty loginUser }">
				<a href="./">ホーム</a>
				<a href="userManagement">ユーザ管理</a>
				<a href="signout">サインアウト</a>
			</c:if>
		</div>
		<c:if test="${ not empty loginUser }">
			<div class="profile">
				<div class="name">
					<h2>
						Name:
						<c:out value="${loginUser.name}" />
					</h2>
					<span class="account"> @ <c:out value="${loginUser.account}" />
					</span>
				</div>
			</div>
		</c:if>
		${param.content}
		<div class="copyright">Copyright(c)OhtsukaJun</div>
	</div>
</body>
</html>