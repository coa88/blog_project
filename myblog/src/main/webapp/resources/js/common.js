'use strict'

function linkPage(pageNo) {
	console.log('pageNo : ' + pageNo)
	location.href = `?pageNo=`+pageNo
}

function delPost(iBoard) {
	
	if(confirm('삭제 하시겠습니까?')) {
		fetch(`/delPost/${iBoard}.do`, {
			method: 'DELETE',
		}).then(function(res) {
			return res.json()
		}).then(function(response) {
			alert('삭제하였습니다.')
			return location.reload()
		})
	}
}

function setNeighbor(loginId,hostId) {
	console.log('로그인 : ' + loginId)
	console.log('호스트 : ' + hostId)
	
	let neighborStat = document.querySelector('#neighborStat')
	let neighborChk = neighborStat.getAttribute('data-neighborChk') //1: 좋아요, 0:안 좋아요	
	neighborChk = 1 - neighborChk
	let iconType = neighborChk == 1 ? 'plus' : 'minus'
	let strType = neighborChk == 1 ? '추가' : '취소'
	
	if(!loginId) {
		location.href='/login.do'
		return false
	}
	
	let param = {
				"userId" : loginId,
				"neighborId" : hostId
			}
	if(confirm(`이웃${strType} 하시겠습니까?`)) {
	
		fetch(`/setNeighbor.do?userId=${loginId}&neighborId=${hostId}`, {
			method: 'GET',
		}).then(function(response) {
			return response.json()
		}).then(function(res) {
			return location.reload();
		})
	}
}