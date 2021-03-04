<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${now_status != null}">
                <h2>${now_status.characters.chara_name}の詳細情報　編集</h2>

                <p><a href="<c:url value='/status/new?id=${now_status.now_id}' />">前に戻る</a></p>

                <form method="POST" action="<c:url value='/status/player/update' />">
                    <c:import url="../_form.jsp" />
                    <c:import url="_form.jsp" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>お探しの球団情報は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/status/new?id=${now_status.now_id}' />">前に戻る</a></p>
        <p><a href="<c:url value="/characters/index?id=${now_status.characters.titles.title_id}" />">一覧に戻る</a></p>
    </c:param>
</c:import>