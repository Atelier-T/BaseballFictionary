<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><a href="<c:url value='/titles/show?id=${titles.title_id}' />">『${titles.title_name}』</a>の各種データ・ツール</h2>

        <div id="important">
            <p><a href="<c:url value='/leagues/index?id=${titles.title_id}' />">リーグ情報</a></p>
            <p><a href="<c:url value='/teams/index?id=${titles.title_id}' />">球団情報</a></p>
            <c:choose>
                <c:when test="${sessionScope.login_user.user_id == titles.users.user_id}">
                    <p><a href="<c:url value='/characters/index?id=${titles.title_id}' />">登場人物情報(年度別詳細情報)</a></p>
                </c:when>
                <c:otherwise>
                    <p><a href="<c:url value='/characters/index?id=${titles.title_id}' />">登場人物情報</a></p>
                </c:otherwise>
            </c:choose>
            <p>用語集</p>
        </div>
        <br /><br />

        <c:if test="${sessionScope.login_user.user_id == titles.users.user_id}">
            <h3>各種ツール</h3>
            <p><a href="<c:url value='/data/tool1/index?id=${titles.title_id}' />">詳細情報一斉登録ツール</a></p>
        </c:if>

    <p><a href="<c:url value="/titles/index?id=${titles.title_id}" />">一覧に戻る</a></p>
    </c:param>
</c:import>
