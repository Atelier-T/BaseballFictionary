<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <div id="important">
            <p><a href="<c:url value='/status/player/new?id=${titles.title_id}' />">
                選手、監督、コーチ、オーナー、その他球団関係者
            </a></p>

            <p><a href="<c:url value='/status/not/new?id=${titles.title_id}' />">
                球団関係者以外(OB・OGなどの球界関係者、ファンなど)
            </a></p>
        </div>

    </c:param>
</c:import>
