<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<label for="user_name">ユーザ名(半角英数字6文字以上)</label><br />
<input type="text" name="user_name" value="${users.user_name}" pattern="^([a-zA-Z0-9]{6,})$" />
<br /><br />

<label for="password">パスワード(半角英数字6文字以上)</label><br />
<input type="password" name="password" pattern="^([a-zA-Z0-9]{6,})$" />
<br /><br />

<label for="user_flag">ユーザタイプ</label><br />
<select name="user_flag">
    <option value="0"<c:if test="${users.user_flag == 0}"> selected</c:if>>管理者</option>
    <option value="1"<c:if test="${users.user_flag == 1}"> selected</c:if>>ユーザ</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>