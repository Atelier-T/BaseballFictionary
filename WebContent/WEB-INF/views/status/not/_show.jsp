<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="character_list">
    <tbody>
        <tr>
            <th>登録名</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.not_players.not_player_name}" /></a></td>
        </tr>

        <tr>
            <th>登録名(読み方)</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.not_players.not_player_name_read}" /></a></td>
        </tr>

        <tr>
            <th>人物分類1</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.not_players.chara_type1 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 1}">
                        OB・OG
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 2}">
                        ファン
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 3}">
                        選手親族
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 4}">
                        審判
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 5}">
                        コミッショナー
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 6}">
                        その他球界関係者
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type1 == 7}">
                        その他一般人
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>人物分類2</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.not_players.chara_type2 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 1}">
                        OB・OG
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 2}">
                        ファン
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 3}">
                        選手親族
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 4}">
                        審判
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 5}">
                        コミッショナー
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 6}">
                        その他球界関係者
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type2 == 7}">
                        その他一般人
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>人物分類3</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.not_players.chara_type3 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 1}">
                        OB・OG
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 2}">
                        ファン
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 3}">
                        選手親族
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 4}">
                        審判
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 5}">
                        コミッショナー
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 6}">
                        その他球界関係者
                    </c:when>
                    <c:when test="${now_status.not_players.chara_type3 == 7}">
                        その他一般人
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>人物分類　詳細</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.not_players.chara_type_detail}" /></a></td>
        </tr>

        <tr>
            <th>その他補足情報</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.not_players.chara_type_detail}" /></a></td>
        </tr>

        <tr>
        <th>登録日時</th>
            <td>
                <fmt:formatDate value="${now_status.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
        </tr>

        <tr>
            <th>更新日時</th>
            <td>
                <fmt:formatDate value="${now_status.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
        </tr>

    </tbody>
</table>