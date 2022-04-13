'use strict'

function login() {
	let userId = document.querySelector('.user_id')
	let userPw = document.querySelector('.user_pw')
	console.log('아이디 : ' + userId.value)
	console.log('비밀번호 : ' + userPw.value)
	
	if(userId.value == "") {
		alert('아이디를 입력해주십시오.')
		return false
	}
	
	if(userPw.value == "") {
		alert('비밀번호를 입력해주십시오.')
		return false
	}
	
	let param = {
			"userId" : userId.value,
			"userPw" : userPw.value
	}
	
	fetch('/login.do', {
		method: 'POST',
		headers: { "Content-Type": "application/json; charset=UTF-8" },
		body: JSON.stringify(param)
	}).then(function(response) {
		return response.json()
	}).then(function(res) {
		if(res == 0) {
			alert('아이디를 찾을수 없습니다.')
			return false
		}
		
		if(res == 1) { //로그인 성공
			location.href = '/'
		}
		
		if(res == 2) {
			alert('비밀번호를 확인해 주십시오.')
			return false
		}
	})
}

function enterKeyEvent(event) { // 엔터눌렀을때 이벤트발생
	if(event.keyCode === 13) {
		login()
	}
}

function autoLogin() {

	let param = {
			"userId" : "tester",
			"userPw" : "test1234"
	}
	
	fetch('/login.do', {
		method: 'POST',
		headers: { "Content-Type": "application/json; charset=UTF-8" },
		body: JSON.stringify(param)
	}).then(function(response) {
		return response.json()
	}).then(function(res) {
		location.href = '/'
	})
}
