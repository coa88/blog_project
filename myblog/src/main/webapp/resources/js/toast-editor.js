'use strict'

const Editor = toastui.Editor;
 
const editor = new Editor({
	  el: document.querySelector('#editor'),
	  height: '600px',
	  initialEditType: 'wysiwyg',
	  previewStyle: 'vertical',
	  hooks: {
		  addImageBlobHook: (blob, callback) => {
			  let imageUrl = imageUpload(blob);
			  setTimeout(function() {
					console.log("이미지경로 : " + saveUrl)
					callback(saveUrl, "alt text");
				},100)
		  }
	  }
	});

let saveUrl

function imageUpload(blob) {
	
	let formData = new FormData()
	formData.append('img', blob)

	fetch('/imageUpload.do', {
		method: 'POST',
		body: formData
	}).then(function(res) {
		return res.json()
	}).then(function(response) {
		saveUrl = response.imageUrl
		console.log(saveUrl)
		return saveUrl
	})
}


function uploadPost(iBoard) {
	let titleVal = document.querySelector("#post-detail-title").value
	let contentVal = editor.getHTML()
	let selCategory = document.getElementById("selCategory")
	let categoryPk = selCategory.options[selCategory.selectedIndex].value
	let postUrl;
	
	if(iBoard == undefined) {
		postUrl = '/write.do'
	} else {
		postUrl = '/modify.do'
	}
	
	console.log('iboard : ' + iBoard)
	console.log('postUrl : ' + postUrl)
	
	if(titleVal == '') {
		alert('제목을 입력해 주십시오.')
		return false
	}
	
	if(categoryPk =='none') {
		alert('카테고리를 선택해 주십시오.')
		return false
	}
	
	let formData = new FormData();
	formData.append('title', titleVal)
	formData.append('content', contentVal)
	formData.append('categoryPk', categoryPk)
	if(iBoard != undefined) {
		formData.append('iBoard', iBoard)
	}
	
	fetch(postUrl, {
		method: 'POST',
		body: formData
	}).then(function(res) {
		console.log(res)
		return res.json()
	}).then(function(data) {
		if(!data.result == '1') {
			alert('글 작성을 실패하였습니다.')
		}
		
		return location.href='/' + data.userId + '.do'
	})
	
}
