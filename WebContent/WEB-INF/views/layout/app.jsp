<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>BaseBallFictionary</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">BaseBallFictionary</a></h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_user.user_flag == 0}">
                        <a href="<c:url value='/users/index' />">ユーザ管理</a>&nbsp;
                    </c:if>
                    <a href="<c:url value='/titles/index' />">作品情報</a>&nbsp;
                    <div id="user_name">
                        <c:if test="${sessionScope.login_user != null}">
                            <c:out value="${sessionScope.login_user.user_name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                            <a href="<c:url value='/logout' />">ログアウト</a>
                        </c:if>
                        <c:if test="${sessionScope.login_user == null}">
                            <a href="<c:url value='/login' />">ログイン</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Atelier T
            </div>
        </div>
    </body>
</html>