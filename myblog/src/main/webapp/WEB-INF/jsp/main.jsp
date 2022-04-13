<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div id="main-area">
	<div id="search-area">
		<input type="text" id="search-bar" placeholder="검색">
	</div>
	
	<div id="left-area">
		<div id="category-list">
		
			<c:choose>
				<c:when test="${neighborList != null}">
				 	<h3>이웃목록</h3>
				</c:when>
				<c:otherwise>
				 	<h3>추천블로그</h3>
				</c:otherwise>
			</c:choose>
			<div id="category-body">
				<ul>
				<c:choose>
					<c:when test="${neighborList != null}">
						<c:forEach items="${neighborList}" var="nbData">
							<li class="user_li">
								<a href="/${nbData.neighborId}.do">${nbData.neighborId}</a>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach items="${userList}" var="userData">
							<li class="user_li">
								<a href="/${userData.userId}.do">${userData.userId}</a>
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="content-area">
		<c:forEach items="${data}" var="item">
			<div class="post-area">
				<div class="post-title">
					<a href="/${item.userId}.do">${item.title}</a>
				</div>
				<div class="post-info">
					<span class="profile-img-s">
						<img src="/resources/images/basic_profile_img.gif">
					</span>
					<a href="/${item.userId}.do" class="post-name">${item.nm}</a>
					<span class="post-date">${item.postDt}</span>
				</div>
				<div class="post-contents">${item.content}</div>
			</div>
		</c:forEach>
		
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage(${pageNo})"/>

	</div>
	
</div>