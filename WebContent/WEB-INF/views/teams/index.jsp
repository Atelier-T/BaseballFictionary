<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>『${titles.title_name}』の球団情報</h2>

        <c:if test="${sessionScope.login_user.user_id == titles.users.user_id}">
            <p><a href="<c:url value='/teams/new?id=${titles.title_id}' />">新規球団の登録</a></p>
        </c:if>

        <table id="team_list">
            <tbody>
                <tr>
                    <th class="team_name">球団名</th>
                    <th class="league_name">リーグ名</th>
                    <th class="team_action">操作</th>
                </tr>
                <c:forEach var="teams" items="${teams}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="team_name"><a href="<c:url value='/teams/show?id=${teams.team_id}' />"><c:out value="${teams.team_name}" /></a></td>
                        <td class="league_name"><a href="<c:url value='/leagues/show?id=${teams.leagues.league_id}' />"><c:out value="${teams.leagues.league_name}" /></a></td>
                        <td class="team_action"><a href="<c:url value='/teams/show?id=${teams.team_id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${teams_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((teams_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/teams/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>
