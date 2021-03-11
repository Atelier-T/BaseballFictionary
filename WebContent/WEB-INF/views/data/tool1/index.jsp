<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>『<c:out value="${titles.title_name}" />』の詳細情報一斉登録ツール</h2>
        <p id="setsumei">登場人物の、指定の年度の詳細情報を自動で作成します。<br />
        指定年度の前年度、それがなければ最新年度の詳細情報をコピーする形となります。<br />
        詳細情報が1つも登録されていない登場人物のものは作成されません。<br />
        ※作成したくない登場人物がいる場合は下のチェックボックスにチェックを入れて下さい。</p>
        <br /><br />

        <form method="POST" action="<c:url value='/data/tool1/create' />">
            <c:import url="_form.jsp" />
        </form>

    <p><a href="<c:url value="/titles/index?id=${titles.title_id}" />">一覧に戻る</a></p>
    </c:param>
</c:import>
