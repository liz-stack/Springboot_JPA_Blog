let index = {
	init: function() {
		$("#btn-save").on("click", () => { //()=>{} 쓰는이유?  this를 바인딩하기 위해서 사용.
			this.save();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax({//회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다");
			console.log(resp);
			location.href = "/";

		}).fail(function(error) { // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert요청!
			alert(JSON.stringify(error));
		});
	},
	
}

index.init();