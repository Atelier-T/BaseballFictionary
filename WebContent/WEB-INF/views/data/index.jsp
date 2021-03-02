<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><a href="<c:url value='/titles/show?id=${titles.title_id}' />">『${titles.title_name}』</a>の各種データ</h2>

        <div id="important">
            <p><a href="<c:url value='/leagues/index?id=${titles.title_id}' />">リーグ情報</a></p>
            <p><a href="<c:url value='/teams/index?id=${titles.title_id}' />">球団情報</a></p>
            <p><a href="<c:url value='/characters/index?id=${titles.title_id}' />">登場人物情報</a></p>
            <p>各種用語</p>
        </div>

    </c:param>
</c:import>
