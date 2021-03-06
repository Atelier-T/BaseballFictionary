<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select name="status_year">
<c:forEach var="now_status" items="${now_status}" varStatus="status">
    <option value="${now_status.now_id}"<c:if test="${now_status.now_id == characters.now_status.now_id}"> selected</c:if>>
        <c:out value="${now_status.now_year}" />
        年度(
        <c:choose>
            <c:when test="${now_status.chara_flag == 0}">
                <c:choose>
                    <c:when test="${now_status.players.posision1 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 1}">
                        投手
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 2}">
                        捕手
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 3}">
                        内野手
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 4}">
                        外野手
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 5}">
                        監督
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 6}">
                        ヘッドコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 7}">
                        投手コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 8}">
                        打撃コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 9}">
                        走塁コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 10}">
                        バッテリーコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 11}">
                        内野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 12}">
                        外野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 13}">
                        トレーナー
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 14}">
                        打撃投手
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 15}">
                        チームドクター
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 16}">
                        通訳
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 17}">
                        オーナー
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 18}">
                        球団幹部
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 19}">
                        マスコット
                    </c:when>
                    <c:when test="${now_status.players.posision1 == 20}">
                        その他球団職員
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${now_status.chara_flag == 1}">
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
            </c:when>
        </c:choose>
        )
    </option>
</c:forEach>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button class="status_change" type="submit">詳細情報の年度を変更する</button>
<br /><br />