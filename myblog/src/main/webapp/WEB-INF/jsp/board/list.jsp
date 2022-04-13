<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div id="main-area">
	<div id="left-area">
		<div id="blog-profile">
			<img src="/resources/images/basic_profile_img.gif">
			<div id="user-name">
				<div>${hostNm}</div>
				<div>(${hostId})</div>			
			</div>
		</div>

		<div class="blog-host-btn">
			<c:if test="${hostId == loginUser.userId}">
				<i class="fas fa-pencil-alt"></i>
				<a href="/${loginUser.userId}/write.do" class="host-btn write-btn">글쓰기</a>
				
				<i class="fas fa-cog"></i>
				<a href="/${loginUser.userId}/setting.do" class="host-btn setting-btn">관리</a>
			</c:if>
			<c:if test="${hostId != loginUser.userId}">
				<span id="neighborStat" data-neighborChk="${neighborChk}" onclick="setNeighbor('${loginUser.userId}','${hostId}')">
					<c:choose>
						<c:when test="${neighborChk == 0 || neighborChk == null}">
							<i class="fas fa-user-plus"></i>
							<span class="host-btn">이웃추가</span>
						</c:when>
						<c:otherwise>
							<i class="fas fa-user-minus"></i>
							<span class="host-btn">이웃취소</span>
						</c:otherwise>
					</c:choose>
				</span>
			</c:if>
		</div>
		
		
		<div id="category-list">
		 	<h3>카테고리</h3>
			<div id="category-body">
				<ul>
					<li class="category_li"><a href="/${hostId}.do">전체</a></li>
					<c:forEach items="${cgList}" var="cgList">
						<li class="category_li">
							<a href="/${hostId}/${cgList.categoryName}.do">${cgList.categoryName}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="content-area">
	
		<c:forEach items="${data}" var="item">
			<div class="post-area">
				<div class="post-category">${item.categoryName }</div>
				<div class="post-title">${item.title}</div>
				<div class="post-info">
					<span class="profile-img-s">
						<img src="/resources/images/basic_profile_img.gif">
					</span>
					<span class="post-name">${item.nm}</span>
					<span class="post-date">${item.postDt}</span>
				</div>
				<div class="post-contents">${item.content}</div>
				<div class="post-btn">
					<a href="/${loginUser.userId}/modify.do?iBoard=${item.iBoard}" class="post-btn mod-btn">수정</a>
					<span class="post-btn del-btn" onclick="delPost(${item.iBoard},'${item.userId}')">삭제</span>
				</div>
			</div>
		</c:forEach>
		
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage(${pageNo})"/>

	</div>
	
	
</div>