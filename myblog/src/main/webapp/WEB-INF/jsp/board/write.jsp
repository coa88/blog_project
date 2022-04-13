<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 

<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script defer src="/resources/js/toast-editor.js?ver=8"></script>

<input type="hidden" name="iBoard" value="${param.iBoard}">

<select id="selCategory" name="category">
	<c:choose>
		<c:when test="${data.categoryPk == null}">
			<option value="none" selected disabled>카테고리 선택</option>
		</c:when>
		<c:otherwise>
			<option value="${data.categoryPk}" selected disabled>${data.categoryName}</option>
		</c:otherwise>
	</c:choose>
	<c:forEach items="${cgData}" var="item">
	<option value="${item.categoryPk}">${item.categoryName}</option>
	</c:forEach>
</select>

<input type="text" id="post-detail-title" value="${data.title }">
<div id="editor">${data.content }</div>

<c:choose>
	<c:when test="${data == null}">
		<button id="write-btn" onclick="uploadPost(${param.iBoard})">글등록</button>
	</c:when>
	<c:otherwise>
		<button id="write-btn" onclick="uploadPost(${param.iBoard})">글수정</button>
	</c:otherwise>
</c:choose>