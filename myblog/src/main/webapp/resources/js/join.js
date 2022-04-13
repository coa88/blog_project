'use strict'

let userId = document.querySelector('.user_id')
let userPw = document.querySelector('.user_pw')
let userPwRe = document.querySelector('.user_pw_re')
let name = document.querySelector('.name')
let phoneNum = document.querySelector('.phone_num')
let emailId = document.querySelector('.email_id')
let emailAddr = document.querySelector('.email_addr')

let userIdVal = userId.value

let passId = 0
let passPw = 0
let passEmail = 0
let passPhoneNum = 0

function join() {
	
	console.log('아이디 : ' + userId.value)
	console.log('비번 : ' + userPw.value)
	console.log('비번재 : ' + userPwRe.value)
	console.log('이름 : ' + name.value)
	console.log('전번 : ' + phoneNum.value)
	console.log('메일아이디 : ' + emailId.value)
	console.log('메일주소 : ' + emailAddr.value)
	console.log(emailId.value +'@'+ emailAddr.value)
	console.log('passId : ' + passId)
	console.log('passPw : ' + passPw)
	console.log('passEmail : ' + passEmail)
	console.log('passPhoneNum : ' + passPhoneNum)
	
	if(passId == 0) {
		alert('아이디를 확인해주세요.')
		return false
	}
	
	if(passPw == 0) {
		alert('비밀번호를 확인해주세요.')
		return false
	}
	
	if(name.value == "") {
		alert('이름을 작성해주세요.')
		return false
	}
	
	if(passEmail == 0) {
		alert('메일을 확인해주세요.')
		return false
	}
	
	if(passPhoneNum == 0) {
		alert('휴대전화번호를 확인해주세요.')
		return false
	}
	
	let param = {
				"userId" : userId.value,
				"userPw" : userPw.value,
				"nm" : name.value,
				"userMail" : userId.value, 
				"phoneNum" : phoneNum.value 
			}
	
	fetch('/join.do', {
		method: 'POST',
		headers: { "Content-Type": "application/json; charset=UTF-8" },
		body: JSON.stringify(param)
	}).then(function(response) {
		return response.json()
	}).then(function(res) {
		if(res.data == 0) {
			alert('회원정보를 확인하여 주십시오.')
		}
		
		if(res.data == 1) {
			alert('회원가입이 완료되었습니다.')
			location.href = '/'
		}
		return
	})

}

//아이디 체크
function checkId() {
	var regExpId = /^[a-z0-9]{5,15}$/
	let idChk = document.getElementsByClassName("id_chk")[0]
	
	//공백체크
	checkSpace(userId.value)
	
	// 아이디 정규식
	if(!regExpId.test(userId.value)) {
		idChk.innerHTML="영 소문자, 숫자 5~15자리로 입력해주세요."
		passId = 0
		return false
	} 
	
	const param = {"userId" : userId.value };
	
	fetch('/checkId.do', {
		method: 'POST',
		headers: {"Content-Type": "application/json; charset=UTF-8",},
		body: JSON.stringify(param)
	}).then(function(response) {
		console.log("asdasdasds : "+response)
		return response.json();
	}).then(function(res) {
		console.log('dddd : ' + res)
		if(res.data == 0) {
			idChk.innerHTML="사용 가능한 아이디입니다."
			passId = 1
		}
		
		if(res.data == 1) {
			idChk.innerHTML="이미 사용 중인 아이디 입니다."
			passId = 0
		}
		return
	})
	
}

//비밀번호 체크
function checkPw() {
	let pattern1 = /[0-9]/;				// 숫자
	let pattern2 = /[a-zA-Z]/;			// 문자
	let pwChk = document.getElementsByClassName("user_pw_chk")[0]
	
	userPwRe.value = null
	
	checkSpace(userPw.value)
	
	if(!pattern1.test(userPw.value) || !pattern2.test(userPw.value) || userPw.value.length < 8) {
		pwChk.innerHTML="비밀번호는 8자리 이상 문자, 숫자로 구성하여야 합니다."
	} else {
		pwChk.innerHTML=""
	}
}

//비밀번호 확인 체크
function reCheckPw() {
	let pwReChk = document.getElementsByClassName("user_pw_rechk")[0]
	
	
	if(userPw.value != userPwRe.value) {
		pwReChk.innerHTML="비밀번호를 확인해 주십시오"
		passPw = 0
	} else {
		pwReChk.innerHTML="사용 가능한 비밀번호입니다."
		passPw = 1
	}
}

//이메일 체크
function checkEmail() {
	checkSpace(emailId.value)

	if(emailId.value == '' || emailId.value == null){
		passEmail = 0
	} else if(emailAddr.value == '' || emailAddr.value == "메일선택") {
		passEmail = 0
	} else {
		passEmail = 1
	}
	
}

//휴대폰번호 체크
function checkPhoneNum() {
	let phoneNumChk = document.getElementsByClassName("phone_num_chk")[0]

    var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    if (regPhone.test(phoneNum.value) === true) {
    	phoneNumChk.innerHTML=""
    	passPhoneNum = 1
    } else {
    	phoneNumChk.innerHTML="번호를 확인하여 주십시오."
    	passPhoneNum = 0
    }
	
}

//공백체크
function checkSpace(str) { 
	if(str.search(/\s/) != -1) {
		alert("공백은 사용할 수 없습니다.")
	}
}



