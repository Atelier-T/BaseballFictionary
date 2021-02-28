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
<label for="team_name">球団名</label><br />
<input type="text" name="team_name" value="${teams.team_name}" />
<br /><br />

<label for="league_name">リーグ名</label><br />
<select name="league_name">
<c:forEach var="leagues" items="${leagues}" varStatus="status">
    <option value="${leagues}"<c:if test="${leagues.league_name == leagues}"> selected</c:if>>${leagues.league_name}</option>
</c:forEach>
</select>
<br /><br />

<label for="name">作品名</label><br />
    <c:choose>
       <c:when test="${titles != null}">
           <c:out value="${titles.title_name}" />
       </c:when>
       <c:when test="${teams.titles != null}">
           <c:out value="${teams.titles.title_name}" />
       </c:when>
    </c:choose>
<br /><br />


<label for="team_information">球団紹介</label><br />
<textarea name="team_information" rows="10" cols="50">${teams.team_information}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>