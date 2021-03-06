<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><a href="<c:url value='/titles/show?id=${titles.title_id}' />">『${titles.title_name}』</a>の登場人物情報</h2>

        <c:if test="${sessionScope.login_user.user_id == titles.users.user_id}">
            <p><a href="<c:url value='/characters/new?id=${titles.title_id}' />">新規登場人物の登録</a></p>
            <p><a href="<c:url value='/status/new?id=${titles.title_id}' />">新規年度別詳細情報の登録</a></p>
        </c:if>

        <table id="character_list">
            <tbody>
                <tr>
                    <th class="chara_name">登場人物名</th>
                    <th class="player_name">登録名</th>
                    <th class="chara_team">所属球団</th>
                    <th class="chara_type">人物分類</th>
                    <th class="chara_action">操作</th>
                </tr>
                <c:forEach var="characters" items="${characters}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="character_name">
                            <a href="<c:url value='/characters/show?id=${characters.chara_id}' />">
                                <c:out value="${characters.chara_name}" />
                            </a>
                        </td>
                        <td class="player_name">
                            <a href="<c:url value='/characters/show?id=${characters.chara_id}' />">
                                <c:if test="${characters.now_status.chara_flag == 0}">
                                    <c:out value="${characters.now_status.players.player_name}" />
                                </c:if>
                                <c:if test="${characters.now_status.chara_flag == 1}">
                                    <c:out value="${characters.now_status.not_players.not_player_name}" />
                                </c:if>
                            </a>
                        </td>
                        <td class="chara_team">
                            <c:if test="${characters.now_status.chara_flag == 0}">
                                <a href="<c:url value='/teams/show?id=${characters.now_status.players.teams.team_id}' />">
                                    <c:out value="${characters.now_status.players.teams.team_name}" />
                                </a>
                            </c:if>
                        </td>
                        <td class="chara_type">
                            <c:choose>
                                <c:when test="${characters.now_status.chara_flag == 0}">
                                    <a href="<c:url value='/status/show?id=${characters.now_status.now_id}' />">
                                        <c:choose>
                                            <c:when test="${characters.now_status.players.posision1 == 0}">
                                                未分類
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 1}">
                                                投手
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 2}">
                                                捕手
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 3}">
                                                内野手
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 4}">
                                                外野手
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 5}">
                                                監督
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 6}">
                                                ヘッドコーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 7}">
                                                投手コーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 8}">
                                                打撃コーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 9}">
                                                走塁コーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 10}">
                                                バッテリーコーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 11}">
                                                内野守備コーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 12}">
                                                外野守備コーチ
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 13}">
                                                トレーナー
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 14}">
                                                打撃投手
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 15}">
                                                チームドクター
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 16}">
                                                通訳
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 17}">
                                                オーナー
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 18}">
                                                球団幹部
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 19}">
                                                マスコット
                                            </c:when>
                                            <c:when test="${characters.now_status.players.posision1 == 20}">
                                                その他球団職員
                                            </c:when>
                                        </c:choose>
                                    </a>
                                </c:when>
                                <c:when test="${characters.now_status.chara_flag == 1}">
                                    <a href="<c:url value='/now_status/show?id=${characters.now_status.not_players.posision1}' />">
                                        <c:when test="${characters.now_status.not_players.posision1 == 0}">
                                            未分類
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 1}">
                                            OB・OG
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 2}">
                                            ファン
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 3}">
                                            選手親族
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 4}">
                                            審判
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 5}">
                                            コミッショナー
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 6}">
                                            その他球界関係者
                                        </c:when>
                                        <c:when test="${characters.now_status.not_players.posision1 == 7}">
                                            その他一般人
                                        </c:when>
                                    </a>
                                </c:when>
                            </c:choose>
                        </td>

                        <td class="character_action"><a href="<c:url value='/characters/show?id=${characters.chara_id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${characters_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((characters_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/characters/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>
