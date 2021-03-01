<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${leagues != null}">
                <h2>リーグ情報　詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>リーグ名</th>
                            <td><a href="<c:url value='/leagues/show?id=${leagues.league_id}' />"><c:out value="${leagues.league_name}" /></a></td>
                        </tr>
                        <tr>
                            <th>作品名</th>
                            <td><a href="<c:url value='/titles/show?id=${leagues.titles.title_id}' />"><c:out value="${leagues.titles.title_name}" /></a></td>
                        </tr>
                        <tr>
                            <th>リーグ紹介</th>
                            <td>
                                <c:out value="${leagues.league_information}" />
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${leagues.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${leagues.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.user_id == leagues.titles.users.user_id}">
                    <p><a href="<c:url value="/leagues/edit?id=${leagues.league_id}" />">リーグ情報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しの球団は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/leagues/index?id=${leagues.titles.title_id}" />">一覧に戻る</a></p>
    </c:param>
</c:import>