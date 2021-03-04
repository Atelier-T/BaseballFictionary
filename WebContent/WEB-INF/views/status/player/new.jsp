<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>年度別詳細情報　新規登録ページ</h2>

        <p><a href="<c:url value='/status/new?id=${titles.title_id}' />">前に戻る</a></p>

        <form method="POST" action="<c:url value='/status/player/create?id=${titles.title_id}' />">
            <c:import url="../_form.jsp" />
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='/status/new?id=${titles.title_id}' />">前に戻る</a></p>
        <p><a href="<c:url value='/characters/index?id=${titles.title_id}' />">一覧に戻る</a></p>
    </c:param>
</c:import>