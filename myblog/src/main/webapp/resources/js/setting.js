'use strict'

//카테고리 이름 수정
function updBtn(categoryPk,categoryName) {
	let liBtn = document.querySelector('.li_num_'+categoryPk)
	liBtn.innerHTML = '<input type="text" class="updName">'
		+'<button class="li_option_btn" onclick="updCategory(\''+categoryPk+'\',\''+categoryName+'\')">확인</button>'
		+'<button class="li_option_btn" onclick="updCancel(\''+categoryPk+'\',\''+categoryName+'\')">취소</button>'
	console.log('수정 : ' + categoryPk + ', 이름: ' + categoryName)
}

//수정 확인
function updCategory(categoryPk) {
	let updName = document.querySelector('.updName')
	let liBtn = document.querySelector('.li_num_'+categoryPk)
	liBtn.innerHTML = '<span class="li_value">'+updName.value+'</span>'
		+ '<button class="li_option_btn" onclick="updBtn(\''+categoryPk+'\',\''+updName.value+'\')">수정</button>'
		+ '<button class="li_option_btn" onclick="delBtn(\''+categoryPk+'\')">삭제</button>'		

		let param = {
			"type" : "upd",
			"categoryPk" : categoryPk,
			"categoryName" : updName.value,
		}

		cgParams.push(param)
}

//수정 취소
function updCancel(categoryPk,categoryName) {
	let liBtn = document.querySelector('.li_num_'+categoryPk)
	liBtn.innerHTML = '<span class="li_value">'+categoryName+'</span>'
		+ '<button class="li_option_btn" onclick="updBtn(\''+categoryPk+'\',\''+categoryName+'\')">수정</button>'
		+ '<button class="li_option_btn" onclick="delBtn(\''+categoryPk+'\')">삭제</button>'		
}

//카테고리 삭제버튼클릭
function delBtn(categoryPk) {
	let nodeName = 'li_num_'+categoryPk
	
	let param = {
			"type" : "del",
			"categoryPk" : categoryPk,
	}
		
	if(confirm('카테고리를 삭제하시겠습니까? 게시물도 전부 삭제됩니다.')) {
		cgParams.push(param)
	    document.getElementsByClassName(nodeName)[0].style.display = "none";
	}
	
	
}

//추가버튼 클릭
let addCg = document.querySelector('.addCg')
function addCategory(userId) {	
	addCg.innerHTML = '<input type="text" class="menu_set_li addCg_name">'
		+ '<button class="addCg_btn addCg_Confirm" onclick="cgConfirm(\'' + userId +'\')">확인</button>'
		+ '<button class="addCg_btn addCg_Cancel" onclick="addCgCancel()">취소</button>'
}

//추가 확인
let cgParams = []
function cgConfirm(userId) { 
	let newCgName = document.querySelector('.addCg_name')

	let param = {
		"type" : "ins",
		"userId" : userId,
		"categoryName" : newCgName.value,
	}

	cgParams.push(param)

	let menuUl = document.querySelector('#menu_set_ul')
	let li = document.createElement('li')
	let text = document.createTextNode(newCgName.value)

	li.appendChild(text);	
	li.setAttribute('class', 'menu_set_li')
	menuUl.appendChild(li);
	
	addCgCancel()

}

//추가 취소
function addCgCancel() {  
	addCg.innerHTML = ''
}

// 카테고리 수정
function modCategory(userId) {
	let link = '/'+userId+'.do'
	
	fetch('/modCategory.do', {
		method: 'POST',
		headers: { "Content-Type": "application/json; charset=UTF-8" },
		body: JSON.stringify(cgParams)
	}).then(function(response) {
		return response.json()
	}).then(function(res) {
		if(confirm('설정을 적용하시겠습니까?')) {
			location.href=link
		}
	})
}