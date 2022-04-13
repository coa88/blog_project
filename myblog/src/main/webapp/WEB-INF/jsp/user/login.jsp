<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script defer src="/resources/js/login.js?ver=2"></script>

<div class="member_layout">
	<div class="member_box">
		<div>
			<input type="text" class="login_info user_id" placeholder="아이디">
		</div>
		<div>
			<input type="password" class="login_info user_pw" placeholder="비밀번호"  onkeyup="enterKeyEvent(event)">
		</div>
		<div>
			<input type="button" class="login_btn" value="로그인" onclick="login()">
		</div>
		<div>
			<input type="button" class="auto_login_btn" value="자동로그인" onclick="autoLogin()">
		</div>
	</div>
</div>
