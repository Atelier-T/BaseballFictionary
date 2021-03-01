<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${teams != null}">
                <h2>球団情報　詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>球団名</th>
                            <td><a href="<c:url value='/teams/show?id=${teams.team_id}' />"><c:out value="${teams.team_name}" /></a></td>
                        </tr>
                        <tr>
                            <th>所属リーグ名</th>
                            <td><a href="<c:url value='/leagues/show?id=${teams.leagues.league_id}' />"><c:out value="${teams.leagues.league_name}" /></a></td>
                        </tr>
                        <tr>
                            <th>作品名</th>
                            <td><a href="<c:url value='/titles/show?id=${teams.titles.title_id}' />"><c:out value="${teams.titles.title_name}" /></a></td>
                        </tr>
                        <tr>
                            <th>球団紹介</th>
                            <td>
                                <c:out value="${teams.team_information}" />
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${teams.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${teams.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.user_id == teams.titles.users.user_id}">
                    <p><a href="<c:url value="/teams/edit?id=${teams.team_id}" />">球団情報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しの球団は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/teams/index?id=${teams.titles.title_id}" />">一覧に戻る</a></p>
    </c:param>
</c:import>