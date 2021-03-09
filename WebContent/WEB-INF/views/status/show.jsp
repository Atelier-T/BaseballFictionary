<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${now_status != null}">
                <c:choose>
                    <c:when test="${sessionScope.login_user.user_id != now_status.characters.titles.users.user_id && now_status.characters.appearance_flag == 0}">
                        <h2>未登場人物です。作者以外には表示できません。</h2>
                    </c:when>
                    <c:otherwise>
                        <h2><a href="<c:url value='/characters/show?id=${now_status.now_id}' />"><c:out value="${now_status.characters.chara_name}" /></a>の<a href="<c:url value='/status/year/show?id=${now_status.now_year}' />"><c:out value="${now_status.now_year}" />年度</a>　詳細情報</h2>

                        <c:if test="${sessionScope.login_user.user_id == now_status.characters.titles.users.user_id}">
                            <p><a href="<c:url value="/status/new?id=${now_status.characters.titles.title_id}&n_id=${now_status.now_id}" />">この情報を元に詳細情報を新規作成</a></p>
                            <p><a href="<c:url value="/status/edit?id=${now_status.now_id}" />">この詳細情報を編集する</a></p>
                        </c:if>

                        <c:choose>
                            <c:when test="${now_status.chara_flag == 0}">

                                <c:import url='player/_show.jsp' />

                            </c:when>

                            <c:when test="${now_status.chara_flag == 1}">

                                <c:import url='not/_show.jsp'/>

                            </c:when>
                        </c:choose>

                        <c:if test="${sessionScope.login_user.user_id == now_status.characters.titles.users.user_id}">
                            <p><a href="<c:url value="/status/new?id=${now_status.characters.titles.title_id}&n_id=${now_status.now_id}" />">この情報を元に詳細情報を新規作成</a></p>
                            <p><a href="<c:url value="/status/edit?id=${now_status.now_id}" />">詳細情報を編集する</a></p>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <c:otherwise>
                <h2>お探しの詳細情報は見つかりませんでした。</h2>
            </c:otherwise>

        </c:choose>

        <p><a href="<c:url value="/characters/show?id=${now_status.characters.chara_id}" />">人物詳細に戻る</a></p>
    </c:param>
</c:import>