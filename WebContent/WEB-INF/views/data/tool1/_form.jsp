<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<details>
    <summary class="check_box_form_a"><strong>
        球団関係者(クリックで開く)
    </strong></summary>
        <c:forEach var="league" items="${leagues}" varStatus="status">
            <details>
                <summary class="check_box_form_b"><strong>
                    <c:out value="${league.league_name}" />(クリックで開く)
                </strong></summary>
                    <c:forEach var="team" items="${teams}" varStatus="status">
                        <c:if test="${team.leagues.league_id == league.league_id}">
                            <details>
                                <summary class="check_box_form_c"><strong>
                                    <c:out value="${team.team_name}" />(クリックで開く)
                                </strong></summary>
                                    <div class="check_box_form_d">
                                        <c:forEach var="character" items="${characters}" varStatus="status">
                                            <c:if test="${character.now_status.chara_flag == 0}">
                                                <c:if test="${character.now_status.players.teams.team_id == team.team_id}">
                                                   <input type="checkbox" name="NG1_${status.index}" value="${character.chara_id}">
                                                       <c:out value="${character.chara_name}" /><br />
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                            </details>
                        </c:if>
                    </c:forEach>
            </details>
        </c:forEach>
</details>
<br /><br />

<details>
    <summary class="check_box_form_a"><strong>
        球団関係者以外(クリックで開く)
    </strong></summary>
        <div class="check_box_form_b">
            <c:forEach var="character" items="${characters}" varStatus="status">
                <c:if test="${character.now_status.chara_flag == 1}">
                    <input type="checkbox" name="NG2_${status.index}" value="${character.chara_id}">
                        <c:out value="${character.chara_name}" /><br />
                </c:if>
            </c:forEach>
        </div>
</details>
<br /><br />

<label for="create_year">作成する年度(年数のみ、半角数字)</label><br />
<input type="number" name="create_year" pattern=^[0-9]+$ list="year_list" /><br />
    <datalist id="year_list">
        <option value="${titles.year + titles.elapsed_year}">現在の作中年度</option>
    </datalist>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">一斉登録</button>
<br /><br />