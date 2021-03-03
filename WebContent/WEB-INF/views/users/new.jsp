<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <c:choose>
            <c:when test="${sessionScope.login_user.user_flag == 0}">
                <h2>ユーザ　新規登録ページ</h2>

                <form method="POST" action="<c:url value='/users/create' />">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
            </c:when>

            <c:otherwise>
                <h2>ユーザの閲覧や編集をする権限がありません。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>