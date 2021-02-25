<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${titles != null}">
                <h2>作品情報　詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>作品名</th>
                            <td><c:out value="${titles.title_name}" /></td>
                        </tr>
                        <tr>
                            <th>作者名</th>
                            <td><c:out value="${titles.users.user_name}" /></td>
                        </tr>
                        <tr>
                            <th>URL</th>
                            <td><a href="<c:url value="${titles.title_name}" />" ></a></td>
                        </tr>
                        <tr>
                            <th>開始時の作中年度</th>
                            <td>
                                <c:out value="${titles.year}" />
                            </td>
                        </tr>
                        <tr>
                            <th>作中の経過年数</th>
                            <td>
                                <c:out value="${titles.elapsed_year}" />
                            </td>
                        </tr>
                        <tr>
                            <th>作品紹介</th>
                            <td>
                                <c:out value="${titles.title_information}" />
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${titles.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${titles.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.user_id == titles.users.user_id}">
                    <p><a href="<c:url value="/titles/edit?id=${titles.title_id}" />">作品情報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しの作品は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/titles/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>