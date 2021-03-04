<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<label for="player_name">*登録名(空欄の場合、「登場人物名」が自動で登録されます)</label><br />
<input type="text" name="player_name" value="${players.player_name}" />
<br /><br />

<label for="player_name">*登録名の読み方(全角カタカナ)(空欄の場合、「登場人物名の読み方」が自動で登録されます)</label><br />
<input type="text" name="player_name_read" value="${players.player_name_read}" pattern="[\u30A1-\u30FC]*" />
<br /><br />

<label for="team_name">球団名</label><br />
<select name="team_name">
<c:forEach var="teams" items="${teams}" varStatus="status">
    <option value="${teams.team_id}"<c:if test="${teams.team_name == teams}"> selected</c:if>>${teams.team_name}</option>
</c:forEach>
</select>
<br /><br />

<label for="posision">※「ポジション・役職」はメインを「1」に入れるようにして下さい。</label><br /><br />
<c:forEach var="i" begin="1" end="5" varStatus="status">
    <label for="posision${i}">ポジション・役職${i}</label><br />
        <select name="posision${i}">
        <option value="0"<c:if test="players.posision${i} == 0"> selected</c:if>>未分類</option>
        <option value="1"<c:if test="players.posision${i} == 1"> selected</c:if>>投手</option>
        <option value="2"<c:if test="players.posision${i} == 2"> selected</c:if>>捕手</option>
        <option value="3"<c:if test="players.posision${i} == 3"> selected</c:if>>内野手</option>
        <option value="4"<c:if test="players.posision${i} == 4"> selected</c:if>>外野手</option>
        <option value="5"<c:if test="players.posision${i} == 5"> selected</c:if>>監督</option>
        <option value="6"<c:if test="players.posision${i} == 6"> selected</c:if>>ヘッドコーチ</option>
        <option value="7"<c:if test="players.posision${i} == 7"> selected</c:if>>投手コーチ</option>
        <option value="8"<c:if test="players.posision${i} == 8"> selected</c:if>>打撃コーチ</option>
        <option value="9"<c:if test="players.posision${i} == 9"> selected</c:if>>走塁コーチ</option>
        <option value="10"<c:if test="players.posision${i} == 10"> selected</c:if>>バッテリーコーチ</option>
        <option value="11"<c:if test="players.posision${i} == 11"> selected</c:if>>内野守備コーチ</option>
        <option value="12"<c:if test="players.posision${i} == 12"> selected</c:if>>外野守備コーチ</option>
        <option value="13"<c:if test="players.posision${i} == 13"> selected</c:if>>トレーナー</option>
        <option value="14"<c:if test="players.posision${i} == 14"> selected</c:if>>打撃投手</option>
        <option value="15"<c:if test="players.posision${i} == 15"> selected</c:if>>チームドクター</option>
        <option value="16"<c:if test="players.posision${i} == 16"> selected</c:if>>通訳</option>
        <option value="17"<c:if test="players.posision${i} == 17"> selected</c:if>>オーナー</option>
        <option value="18"<c:if test="players.posision${i} == 18"> selected</c:if>>球団幹部</option>
        <option value="19"<c:if test="players.posision${i} == 19"> selected</c:if>>マスコット</option>
        <option value="20"<c:if test="players.posision${i} == 20"> selected</c:if>>その他球団職員</option>
        </select>
        <br /><br />
</c:forEach>

<label for="posision_detail">ポジション・役職　詳細</label><br />
<textarea name="posision_detail" rows="10" cols="50">${players.posision_detail}</textarea>
<br /><br />

<label for="number">背番号(半角数字)</label><br />
<input type="text" name="number" value="${players.number}" pattern="^[0-9]+$" />
<br /><br />

<label for="throwing">投</label><br />
<select name="throwing">
    <option value="0"<c:if test="${players.throwing == 0}"> selected</c:if>>右</option>
    <option value="1"<c:if test="${players.throwing == 1}"> selected</c:if>>左</option>
    <option value="2"<c:if test="${players.throwing == 2}"> selected</c:if>>両</option>
</select>
<br /><br />

<label for="batting">打</label><br />
<select name="batting">
    <option value="0"<c:if test="${players.batting == 0}"> selected</c:if>>右</option>
    <option value="1"<c:if test="${players.batting == 1}"> selected</c:if>>左</option>
    <option value="2"<c:if test="${players.batting == 2}"> selected</c:if>>両</option>
</select>
<br /><br />

<label for="player_type1">登録分類1</label><br />
<select name="player_type1">
    <option value="0"<c:if test="${players.player_type1 == 0}"> selected</c:if>>日本人</option>
    <option value="1"<c:if test="${players.player_type1 == 1}"> selected</c:if>>外国人</option>
    <option value="2"<c:if test="${players.player_type1 == 2}"> selected</c:if>>その他</option>
</select>
<br /><br />

<label for="player_type2">登録分類2</label><br />
<select name="player_type2">
    <option value="0"<c:if test="${players.player_type2 == 0}"> selected</c:if>>支配下</option>
    <option value="1"<c:if test="${players.player_type2 == 1}"> selected</c:if>>育成</option>
    <option value="2"<c:if test="${players.player_type2 == 2}"> selected</c:if>>その他</option>
</select>
<br /><br />

<label for="salary">年俸(単位:円)</label><br />
<input type="number" name="salary" value="${players.salary}" pattern="^[0-9]+$" />
<br /><br />

<label for="music">登場曲など</label><br />
<input type="text" name="music" value="${players.music}" />
<br /><br />

<label for="player_information">選手・その役職としての特徴</label><br />
<textarea name="player_information" rows="10" cols="50">${players.player_information}</textarea>
<br /><br />

<label for="performance">主な実績</label><br />
<textarea name="performance" rows="10" cols="50">${players.performance}</textarea>
<br /><br />

<label for="ather_player_information">その他補足情報</label><br />
<textarea name="ather_player_information" rows="10" cols="50">${players.ather_player_information}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>
<br /><br />