<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script defer src="/resources/js/join.js?ver=2"></script>
<div class="member_layout">
	<div class="member_box">
		<div>
			<input type="text" class="join_info user_id" placeholder="아이디" onchange="checkId()">
			<div class="id_chk chk_msg"></div>
			
			<input type="password" class="join_info user_pw" placeholder="비밀번호" onchange="checkPw()">
			<div class="user_pw_chk chk_msg"></div>

			<input type="password" class="join_info user_pw_re" placeholder="비밀번호확인" onchange="reCheckPw()">
			<div class="user_pw_rechk chk_msg"></div>
			
			<input type="text" class="join_info name" placeholder="이름">
		
			<input type="text" class="join_info phone_num" placeholder="휴대전화번호" onchange="checkPhoneNum()">
			<div class="phone_num_chk chk_msg"></div>
			
			<div class="email_box">
				<input type="text" class="email_id" placeholder="이메일"  onchange="checkEmail()">
				@
				<select class="email_addr" onchange="checkEmail()">
					<option disabled selected>메일선택</option>
					<option value="naver.com">naver.com</option>
					<option value="google.com">gmail.com</option>
					<option value="kakao.com">kakao.com</option>
					<option value="daum.net">daum.net</option>
					<option value="hanmail.net">hanmail.net</option>
				</select>
			</div>
		</div>
		
		<div>
			<input type="button" class="join_btn" value="가입하기" onclick="join()">
		</div>
	</div>
</div>
