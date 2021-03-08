<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<label for="player_name">*登録名(空欄の場合、「登場人物名」が自動で登録されます)</label><br />
<input type="text" name="player_name" value="${not_players.not_player_name}" />
<br /><br />

<label for="player_name">*登録名の読み方(全角カタカナ)(空欄の場合、「登場人物名の読み方」が自動で登録されます)</label><br />
<input type="text" name="player_name_read" value="${not_players.not_player_name_read}" pattern="[\u30A1-\u30FC]*" />
<br /><br />

<label for="chara_type">※「人物分類」はメインを「1」に入れるようにして下さい。</label><br /><br />

    <label for="chara_type1">人物分類1</label><br />
        <select name="chara_type1">
        <option value="0"<c:if test="${not_players.chara_type1 == 0}"> selected</c:if>>未分類</option>
        <option value="1"<c:if test="${not_players.chara_type1 == 1}"> selected</c:if>>OB・OG</option>
        <option value="2"<c:if test="${not_players.chara_type1 == 2}"> selected</c:if>>ファン</option>
        <option value="3"<c:if test="${not_players.chara_type1 == 3}"> selected</c:if>>選手親族</option>
        <option value="4"<c:if test="${not_players.chara_type1 == 4}"> selected</c:if>>審判</option>
        <option value="5"<c:if test="${not_players.chara_type1 == 5}"> selected</c:if>>コミッショナー</option>
        <option value="6"<c:if test="${not_players.chara_type1 == 6}"> selected</c:if>>その他球界関係者</option>
        <option value="7"<c:if test="${not_players.chara_type1 == 7}"> selected</c:if>>その他一般人</option>
        </select>
        <br /><br />

    <label for="chara_type2">人物分類2</label><br />
        <select name="chara_type2">
        <option value="0"<c:if test="${not_players.chara_type2 == 0}"> selected</c:if>>未分類</option>
        <option value="1"<c:if test="${not_players.chara_type2 == 1}"> selected</c:if>>OB・OG</option>
        <option value="2"<c:if test="${not_players.chara_type2 == 2}"> selected</c:if>>ファン</option>
        <option value="3"<c:if test="${not_players.chara_type2 == 3}"> selected</c:if>>選手親族</option>
        <option value="4"<c:if test="${not_players.chara_type2 == 4}"> selected</c:if>>審判</option>
        <option value="5"<c:if test="${not_players.chara_type2 == 5}"> selected</c:if>>コミッショナー</option>
        <option value="6"<c:if test="${not_players.chara_type2 == 6}"> selected</c:if>>その他球界関係者</option>
        <option value="7"<c:if test="${not_players.chara_type2 == 7}"> selected</c:if>>その他一般人</option>
        </select>
        <br /><br />

    <label for="chara_type3">人物分類3</label><br />
        <select name="chara_type3">
        <option value="0"<c:if test="${not_players.chara_type3 == 0}"> selected</c:if>>未分類</option>
        <option value="1"<c:if test="${not_players.chara_type3 == 1}"> selected</c:if>>OB・OG</option>
        <option value="2"<c:if test="${not_players.chara_type3 == 2}"> selected</c:if>>ファン</option>
        <option value="3"<c:if test="${not_players.chara_type3 == 3}"> selected</c:if>>選手親族</option>
        <option value="4"<c:if test="${not_players.chara_type3 == 4}"> selected</c:if>>審判</option>
        <option value="5"<c:if test="${not_players.chara_type3 == 5}"> selected</c:if>>コミッショナー</option>
        <option value="6"<c:if test="${not_players.chara_type3 == 6}"> selected</c:if>>その他球界関係者</option>
        <option value="7"<c:if test="${not_players.chara_type3 == 7}"> selected</c:if>>その他一般人</option>
        </select>
        <br /><br />

<label for="chara_type_detail">人物分類　詳細</label><br />
<textarea name="chara_type_detail" rows="10" cols="50">${not_players.chara_type_detail}</textarea>
<br /><br />

<label for="not_player_information">その立場としての特徴</label><br />
<textarea name="not_player_information" rows="10" cols="50">${not_players.not_player_information}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>
<br /><br />