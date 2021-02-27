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
        <h2>作品情報　一覧</h2>
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

        <div id="pagination">
            （全 ${titles_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((titles_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/titles/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.login_user != null}">
            <p><a href="<c:url value='/titles/new' />">新規作品の登録</a></p>
        </c:if>
        <c:if test="${sessionScope.login_user == null}">
            <p><a href="<c:url value='/titles/new' />">新規作品の登録(ログインページへ)</a></p>
        </c:if>

    </c:param>
</c:import>