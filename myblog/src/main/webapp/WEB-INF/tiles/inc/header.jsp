<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 


<c:choose>
	<c:when test="${sessionScope.loginUser eq null}">
		<div id="blog-gnb">
			<a href="/login.do"><span>로그인</span></a>
			<a href="/join.do"><span>회원가입</span></a>
		</div>
	</c:when>
	<c:otherwise>
		<div id="blog-gnb">
			<a href="/${loginUser.userId}.do"><span>내 블로그</span></a>
			<a href="/logout.do"><span>로그아웃</span></a>
		</div>
	</c:otherwise>
</c:choose>

<div id="blog-title">
	<h2><a href="/main.do">MY BLOG</a></h2>
</div>