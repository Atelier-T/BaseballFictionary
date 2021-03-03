<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${sessionScope.login_user.user_flag == 0}">
                <c:choose>
                    <c:when test="${users != null}">
                        <h2>${users.user_name} のユーザ情報　編集ページ</h2>
                        <p>（パスワードは変更する場合のみ入力してください）</p>
                        <form method="POST" action="<c:url value='/users/update' />">
                            <c:import url="_form.jsp" />
                        </form>

                        <p><a href="#" onclick="confirmDestroy();">このユーザ情報を削除する</a></p>
                        <form method="POST" action="<c:url value='/users/destroy' />">
                            <input type="hidden" name="_token" value="${_token}" />
                        </form>
                        <script>
                            function confirmDestroy() {
                                if(confirm("本当に削除してよろしいですか？")) {
                                    document.forms[1].submit();
                                }
                            }
                        </script>
                    </c:when>
                    <c:otherwise>
                        <h2>お探しのデータは見つかりませんでした。</h2>
                    </c:otherwise>
                </c:choose>

                <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
            </c:when>

            <c:otherwise>
                <h2>ユーザの閲覧や編集をする権限がありません。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>