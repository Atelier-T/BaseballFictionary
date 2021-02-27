<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${users != null}">
                <h2>${users.user_name} のユーザ情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <c:if test="${sessionScope.login_user.user_id == users.user_id}">
                            <tr>
                                <th>ユーザID</th>
                                <td><c:out value="${users.user_id}" /></td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>ユーザ名</th>
                            <td><a href="<c:url value='/users/show?id=${users.user_id}' />"><c:out value="${users.user_name}" /></a></td>
                        </tr>
                        <c:if test="${sessionScope.login_user.user_id == users.user_id}">
                            <tr>
                                <th>ユーザタイプ</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${users.user_flag == 0}">管理者</c:when>
                                        <c:when test="${users.user_flag == 1}">ユーザ</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>登録日時</th>
                                <td>
                                    <fmt:formatDate value="${users.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                            </tr>
                            <tr>
                                <th>更新日時</th>
                                <td>
                                    <fmt:formatDate value="${users.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>

                <br />
                <br />
                <br />

                <h3>【${users.user_name}の作品　一覧】</h3>
                <table id="title_list">
                    <tbody>
                        <tr>
                            <th class="user_name">作品名</th>
                            <th class="title_name">作者名</th>
                            <th class="title_action">操作1</th>
                            <th class="title_action">操作2</th>
                            <th class="title_action">操作3</th>
                        </tr>
                        <c:forEach var="titles" items="${titles}" varStatus="status">
                            <tr class="row${status.count % 2}">
                                <td class="user_name"><a href="<c:url value='/titles/show?id=${titles.title_id}' />"><c:out value="${titles.title_name}" /></a></td>
                                <td class="title_name"><a href="<c:url value='/users/show?id=${titles.users.user_id}' />"><c:out value="${titles.users.user_name}" /></a></td>
                                <td class="title_action"><a href="<c:url value='/titles/show?id=${titles.title_id}' />">詳細を見る</a></td>
                                <td class="title_action"><a href="<c:url value='/data/index?id=${titles.title_id}' />">各種データ</a></td>
                                <td class="title_action"><a href="<c:url value='/bookmark/cleate?id=${titles.title_id}' />">ブックマークする</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.user_flag == 0}">
                    <p><a href="<c:url value='/users/edit?id=${users.user_id}' />">このユーザ情報を編集する</a></p>
                </c:if>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>