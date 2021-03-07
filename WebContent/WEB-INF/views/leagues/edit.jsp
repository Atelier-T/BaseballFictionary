<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${leagues != null}">
                <h2>リーグ情報　編集</h2>
                <form method="POST" action="<c:url value='/leagues/update' />">
                    <c:import url="_form.jsp" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>お探しのリーグ情報は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/leagues/index?id=${leagues.titles.title_id}" />">一覧に戻る</a></p>
        <p><a href="#" onclick="confirmDestroy();">この球団情報を削除する</a></p>
        <form method="POST" action="${pageContext.request.contextPath}/leagues/destroy">
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
        function confirmDestroy() {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[1].submit();
            }
        }
        </script>
    </c:param>
</c:import>