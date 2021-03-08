<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="character_list">
    <tbody>
        <tr>
            <th>登録名</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.player_name}" /></a></td>
        </tr>

        <tr>
            <th>登録名(読み方)</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.player_name_read}" /></a></td>
        </tr>

        <tr>
            <th>球団名</th>
            <td><a href="<c:url value='/teams/show?id=${now_status.players.teams.team_id}' />"><c:out value="${now_status.players.teams.team_name}" /></a></td>
        </tr>

        <tr>
            <th>ポジション・役職1</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
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
            </a></td>
        </tr>

        <tr>
            <th>ポジション・役職2</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.posision2 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 1}">
                        投手
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 2}">
                        捕手
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 3}">
                        内野手
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 4}">
                        外野手
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 5}">
                        監督
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 6}">
                        ヘッドコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 7}">
                        投手コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 8}">
                        打撃コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 9}">
                        走塁コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 10}">
                        バッテリーコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 11}">
                        内野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 12}">
                        外野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 13}">
                        トレーナー
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 14}">
                        打撃投手
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 15}">
                        チームドクター
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 16}">
                        通訳
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 17}">
                        オーナー
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 18}">
                        球団幹部
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 19}">
                        マスコット
                    </c:when>
                    <c:when test="${now_status.players.posision2 == 20}">
                        その他球団職員
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>ポジション・役職3</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.posision3 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 1}">
                        投手
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 2}">
                        捕手
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 3}">
                        内野手
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 4}">
                        外野手
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 5}">
                        監督
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 6}">
                        ヘッドコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 7}">
                        投手コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 8}">
                        打撃コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 9}">
                        走塁コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 10}">
                        バッテリーコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 11}">
                        内野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 12}">
                        外野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 13}">
                        トレーナー
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 14}">
                        打撃投手
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 15}">
                        チームドクター
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 16}">
                        通訳
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 17}">
                        オーナー
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 18}">
                        球団幹部
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 19}">
                        マスコット
                    </c:when>
                    <c:when test="${now_status.players.posision3 == 20}">
                        その他球団職員
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>ポジション・役職4</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.posision4 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 1}">
                        投手
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 2}">
                        捕手
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 3}">
                        内野手
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 4}">
                        外野手
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 5}">
                        監督
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 6}">
                        ヘッドコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 7}">
                        投手コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 8}">
                        打撃コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 9}">
                        走塁コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 10}">
                        バッテリーコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 11}">
                        内野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 12}">
                        外野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 13}">
                        トレーナー
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 14}">
                        打撃投手
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 15}">
                        チームドクター
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 16}">
                        通訳
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 17}">
                        オーナー
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 18}">
                        球団幹部
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 19}">
                        マスコット
                    </c:when>
                    <c:when test="${now_status.players.posision4 == 20}">
                        その他球団職員
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>ポジション・役職5</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.posision5 == 0}">
                        未分類
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 1}">
                        投手
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 2}">
                        捕手
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 3}">
                        内野手
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 4}">
                        外野手
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 5}">
                        監督
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 6}">
                        ヘッドコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 7}">
                        投手コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 8}">
                        打撃コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 9}">
                        走塁コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 10}">
                        バッテリーコーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 11}">
                        内野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 12}">
                        外野守備コーチ
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 13}">
                        トレーナー
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 14}">
                        打撃投手
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 15}">
                        チームドクター
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 16}">
                        通訳
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 17}">
                        オーナー
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 18}">
                        球団幹部
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 19}">
                        マスコット
                    </c:when>
                    <c:when test="${now_status.players.posision5 == 20}">
                        その他球団職員
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>ポジション・役職　詳細</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.posision_detail}" /></a></td>
        </tr>

        <tr>
            <th>背番号</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.number}" /></a></td>
        </tr>

        <tr>
            <th>投</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.throwing == 0}">
                        右
                    </c:when>
                    <c:when test="${now_status.players.throwing == 1}">
                        左
                    </c:when>
                    <c:when test="${now_status.players.throwing == 2}">
                        両
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>打</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.batting == 0}">
                        右
                    </c:when>
                    <c:when test="${now_status.players.batting == 1}">
                        左
                    </c:when>
                    <c:when test="${now_status.players.batting == 2}">
                        両
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>登録分類1</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.player_type1 == 0}">
                        日本人
                    </c:when>
                    <c:when test="${now_status.players.player_type1 == 1}">
                        外国人
                    </c:when>
                    <c:when test="${now_status.players.player_type1 == 2}">
                        その他
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>登録分類2</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                <c:choose>
                    <c:when test="${now_status.players.player_type2 == 0}">
                        支配下
                    </c:when>
                    <c:when test="${now_status.players.player_type2 == 1}">
                        育成
                    </c:when>
                    <c:when test="${now_status.players.player_type2 == 2}">
                        その他
                    </c:when>
                </c:choose>
            </a></td>
        </tr>

        <tr>
            <th>年俸</th>
            <td>
                <c:if test="${now_status.players.salary != null}">
                    <a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                        <c:out value="${now_status.players.salary}" />円
                    </a>
                </c:if>
            </td>
        </tr>

        <tr>
            <th>登場曲など</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.music}" /></a></td>
        </tr>

        <tr>
            <th>選手・その役職としての特徴</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.player_information}" /></a></td>
        </tr>

        <tr>
            <th>主な実績</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.performance}" /></a></td>
        </tr>

        <tr>
            <th>その他補足情報</th>
            <td><a href="<c:url value='/status/show?id=${now_status.now_id}' />"><c:out value="${now_status.players.ather_player_information}" /></a></td>
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