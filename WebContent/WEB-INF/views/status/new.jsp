<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <c:choose>
            <c:when test="${characters_check == 0}">
                <h2>先に登場人物を登録して下さい。</h2>
                <p><a href="<c:url value='/characters/new?id=${titles.title_id}' />">新規登場人物の登録</a></p>
            </c:when>
            <c:otherwise>
                <h2>登録する情報の、人物分類を選択して下さい。</h2>

                <div id="important">
                    <p><a href="<c:url value='/status/player/new?id=${titles.title_id}' />">
                        選手、監督、コーチ、オーナー、その他球団関係者
                    </a></p>

                    <p><a href="<c:url value='/status/not/new?id=${titles.title_id}' />">
                        球団関係者以外(OB・OGなどの球界関係者、ファンなど)
                    </a></p>
                </div>

            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/characters/index?id=${titles.title_id}" />">一覧に戻る</a></p>

    </c:param>
</c:import>
