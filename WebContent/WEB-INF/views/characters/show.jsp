<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${characters != null}">
                <c:choose>
                    <c:when test="${sessionScope.login_user.user_id != characters.titles.users.user_id && characters.appearance_flag == 0}">
                        <h2>未登場人物です。作者以外には表示できません。</h2>
                    </c:when>
                    <c:otherwise>
                        <h2><a href="<c:url value='/characters/show?id=${characters.chara_id}' />"><c:out value="${characters.chara_name}" /></a>の登場人物情報　詳細</h2>

                        <table>
                            <tbody>
                                <tr>
                                    <th>登場人物名</th>
                                    <td><a href="<c:url value='/characters/show?id=${characters.chara_id}' />"><c:out value="${characters.chara_name}" /></a></td>
                                </tr>
                                <tr>
                                    <th>登場人物名(読み方)</th>
                                    <td><a href="<c:url value='/characters/show?id=${characters.chara_id}' />"><c:out value="${characters.chara_name_read}" /></a></td>
                                </tr>
                                <tr>
                                    <th>作品名</th>
                                    <td><a href="<c:url value='/titles/show?id=${characters.titles.title_id}' />"><c:out value="${characters.titles.title_name}" /></a></td>
                                </tr>
                                <tr>
                                    <th>最新の詳細情報</th>
                                    <td>
                                        <c:choose>
                                            <c:when test="${characters.now_status == null}">
                                            </c:when>
                                            <c:when test="${characters.now_status != null}">
                                                <a href="<c:url value='/status/show?id=${characters.now_status.now_id}' />">
                                                    <c:out value="${characters.now_status.now_year}" />年度
                                                    <c:if test="${characters.birth_year != null && characters.titles.elapsed_year != null && characters.birth_year != null}">
                                                        (
                                                        <c:out value="${characters.titles.year + characters.titles.elapsed_year - characters.birth_year}" />
                                                        歳時、(
                                                        <c:choose>
                                                            <c:when test="${characters.now_status.chara_flag == 0}">
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
                                                            </c:when>
                                                            <c:when test="${characters.now_status.chara_flag == 1}">
                                                                <c:choose>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 0}">
                                                                        未分類
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 1}">
                                                                        OB・OG
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 2}">
                                                                        ファン
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 3}">
                                                                        選手親族
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 4}">
                                                                        審判
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 5}">
                                                                        コミッショナー
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 6}">
                                                                        その他球界関係者
                                                                    </c:when>
                                                                    <c:when test="${characters.now_status.not_players.chara_type1 == 7}">
                                                                        その他一般人
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:when>
                                                        </c:choose>
                                                        )
                                                    </c:if>
                                                </a>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <th>原典</th>
                                    <td>
                                        <c:out value="${characters.original}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>モデル</th>
                                    <td>
                                        <c:out value="${characters.chara_model}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>物語上での重要度</th>
                                    <td>
                                        <c:choose>
                                            <c:when test="${characters.chara_priority == 0}">
                                                未分類
                                            </c:when>
                                            <c:when test="${characters.chara_priority == 1}">
                                                主役級
                                            </c:when>
                                            <c:when test="${characters.chara_priority == 2}">
                                                準主役級
                                            </c:when>
                                            <c:when test="${characters.chara_priority == 3}">
                                                レギュラー級
                                            </c:when>
                                            <c:when test="${characters.chara_priority == 4}">
                                                準レギュラー級
                                            </c:when>
                                            <c:when test="${characters.chara_priority == 5}">
                                                モブ
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <th>誕生年度</th>
                                    <td>
                                        <c:if test="${characters.birth_year != null}">
                                            <c:out value="${characters.birth_year}" />年
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>年齢</th>
                                    <td>
                                        <c:if test="${characters.birth_year != null && characters.titles.elapsed_year != null && characters.birth_year != null}">
                                            <c:out value="${characters.titles.year + characters.titles.elapsed_year - characters.birth_year}" />歳
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>出身地</th>
                                    <td>
                                        <c:out value="${characters.birth_place}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>初登場話</th>
                                    <td>
                                        <c:if test="${characters.appearance != null}">
                                            第<c:out value="${characters.appearance}" />話
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>未登場/登場済</th>
                                    <td>
                                        <c:choose>
                                            <c:when test="${characters.appearance_flag == 0}">
                                                未登場
                                            </c:when>
                                            <c:when test="${characters.appearance_flag == 1}">
                                                登場済
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <th>登場人物紹介</th>
                                    <td>
                                        <c:out value="${characters.chara_information}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>登録日時</th>
                                    <td>
                                        <fmt:formatDate value="${characters.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>更新日時</th>
                                    <td>
                                        <fmt:formatDate value="${characters.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <c:if test="${sessionScope.login_user.user_id == characters.titles.users.user_id}">
                            <p><a href="<c:url value="/characters/edit?id=${characters.chara_id}" />">登場人物情報を編集する(最新の詳細情報のみ↓で)</a></p>

                            <div id="status_change">
                                <br />
                                <p>【詳細情報　年度切り替え】</p>
                                <form method="POST" action="<c:url value='/status/change' />">
                                    <c:import url="_statusChange.jsp" />
                                </form>
                            </div>

                        </c:if>

                        <br /><br />
                        <h3>${characters.chara_name}の詳細情報　一覧</h3>

                        <table id="status_list">
                            <tbody>
                                <tr>
                                    <th class="now_year">年度</th>
                                    <th class="chara_type">人物分類</th>
                                </tr>
                                <c:forEach var="now_status" items="${now_status}" varStatus="status">
                                <tr>
                                    <td class="now_year">
                                        <a href="<c:url value='/status/show?id=${now_status.now_id}' />">
                                        <c:out value="${now_status.now_year}" />
                                        </a>
                                    </td>
                                    <td class="chara_type">
                                        <c:choose>
                                            <c:when test="${now_status.chara_flag == 0}">
                                                <a href="<c:url value='/status/show?id=${now_status.now_id}' />">
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
                                                </a>
                                            </c:when>
                                            <c:when test="${now_status.chara_flag == 1}">
                                                <a href="<c:url value='/status/show?id=${now_status.now_id}' />">
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
                                                </a>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div id="pagination">
                        （全 ${status_count} 件）<br />
                        <c:forEach var="i" begin="1" end="${((status_count - 1) / 15) + 1}" step="1">
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

                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しの登場人物は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/characters/index?id=${characters.titles.title_id}" />">一覧に戻る</a></p>
    </c:param>
</c:import>