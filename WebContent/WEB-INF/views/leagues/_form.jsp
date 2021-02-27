<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="league_name">リーグ名</label><br />
<input type="text" name="league_name" value="${leagues.league_name}" />
<br /><br />

<label for="name">作品名</label><br />
<c:out value="${titles.title_name}" />
<br /><br />

<label for="country_flag">国内/国外</label><br />
<select name="country_flag">
    <option value="0"<c:if test="${leagues.country_flag == 0}"> selected</c:if>>国内</option>
    <option value="1"<c:if test="${leagues.country_flag == 1}"> selected</c:if>>国外</option>
</select>
<br /><br />

<label for="title_information">リーグ紹介</label><br />
<textarea name="content" rows="10" cols="50">${titles.title_information}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>