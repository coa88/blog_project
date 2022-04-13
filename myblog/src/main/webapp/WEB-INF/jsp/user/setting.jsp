<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script defer src="/resources/js/setting.js?ver=3"></script>

<div class="setting_layout">
	<h3>카테고리 설정</h3>
	<div class="setting_box">
		<div class="setting_guide_msg">블로그에 메뉴를 추가/삭제하고 순서를 변경할 수 있습니다.</div>
		<div class="setting_menu_list">
			<ul id="menu_set_ul">
				<c:forEach items="${cgList}" var="cgList">
					<li class="menu_set_li li_num_${cgList.categoryPk}">
						<span class="li_value">${cgList.categoryName}</span>
						<button class="li_option_btn" onclick="updBtn('${cgList.categoryPk}','${cgList.categoryName}')">수정</button>
						<button class="li_option_btn" onclick="delBtn('${cgList.categoryPk}')">삭제</button>
					</li>
				</c:forEach>
			</ul>
			<div class="addCg"></div>
			<div class="menu_set_add_li" onclick="addCategory('${loginUser.userId}')"><span class="li_marker">+</span>추가</div>
		</div>
		<button class="setting_compl_btn" onclick="modCategory('${loginUser.userId}')">적용</button>
	</div>
</div>

