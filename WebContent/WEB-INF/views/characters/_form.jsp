<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<p>*の付いた項目は入力必須</p>
<br /><br />

<label for="chara_name">*登場人物名</label><br />
<input type="text" name="chara_name" value="${characters.chara_name}" />
<br /><br />

<label for="chara_name">*登場人物名の読み方(全角カタカナ)</label><br />
<input type="text" name="chara_name_read" value="${characters.chara_name_read}" pattern="[\u30A1-\u30FC]*" />
<br /><br />

<label for="original">原典</label><br />
<input type="text" name="original" value="${characters.original}" />
<br /><br />

<label for="chara_model">モデル</label><br />
<input type="text" name="chara_model" value="${characters.chara_model}" />
<br /><br />

<label for="chara_priority">*物語上での重要度(決められない場合は「未分類」のままでもOK)</label><br />
<select name="chara_priority">
    <option value="0"<c:if test="0"> selected</c:if>>未分類</option>
    <option value="1"<c:if test="1"> selected</c:if>>主役級</option>
    <option value="2"<c:if test="2"> selected</c:if>>準主役級</option>
    <option value="3"<c:if test="3"> selected</c:if>>レギュラー級</option>
    <option value="4"<c:if test="4"> selected</c:if>>準レギュラー級</option>
    <option value="5"<c:if test="5"> selected</c:if>>モブ</option>
</select>
<br /><br />

<label for="birth_year">誕生年度(年数のみ、半角数字)</label><br />
<label for="birth_year">※これと作品情報の「作中年度」「経過年数」から、登場人物の年齢を算出します。</label><br />
<input type="number" name="birth_year" value="${characters.birth_year}" pattern=^[0-9]+$ />
<br /><br />

<label for="birth_place">出身地</label><br />
<input type="text" name="birth_place" value="${characters.birth_place}" />
<br /><br />

<label for="appearance">初登場話(数値のみ、半角数字)</label><br />
<input type="number" name="appearance" value="${characters.appearance}" pattern=^[0-9]+$ />
<br /><br />

<label for="appearance_flag">*未登場/登場済(作者以外には登場済のみが表示)</label><br />
<select name="appearance_flag">
    <option value="0"<c:if test="${characters.appearance_flag == 0}"> selected</c:if>>未登場</option>
    <option value="1"<c:if test="${characters.appearance_flag == 1}"> selected</c:if>>登場済</option>
</select>
<br /><br />

<label for="chara_information">登場人物紹介</label><br />
<textarea name="chara_information" rows="10" cols="50">${characters.chara_information}</textarea>
<br /><br />

<p>※選手としての細かいデータなどは別のページで設定します。</p>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>