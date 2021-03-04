<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>編集する情報の、人物分類を選択して下さい。</h2>

        <p>※選手を選手以外に変更する事も出来ます。</p>

        <div id="important">
            <p><a href="<c:url value='/status/player/edit?id=${now_status.now_id}' />">
                選手、監督、コーチ、オーナー、その他球団関係者
            </a></p>

            <p><a href="<c:url value='/status/not/edit?id=${now_status.now_id}' />">
                球団関係者以外(OB・OGなどの球界関係者、ファンなど)
            </a></p>
        </div>

        <p><a href="<c:url value="/characters/index?id=${now_status.characters.titles.title_id}" />">一覧に戻る</a></p>

    </c:param>
</c:import>
