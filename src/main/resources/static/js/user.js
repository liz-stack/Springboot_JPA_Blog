let index = {
	init: function() {
		$("#btn-save").on("click", () => { //()=>{} 쓰는이유?  this를 바인딩하기 위해서 사용.
			this.save();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		$.ajax({//회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
			dataType: "json"//서버에서 응답이 왔을 때(default:byte->String) json이라면 javascript Object 로 변경해라 
			//데이터 타입 적어주지 않아도 json리턴해주면 자바오브젝트로 자동 변환(구버전은 안됨)
			
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다");
			console.log(resp);
			location.href = "/blog";
			
		}).fail(function(error) { // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert요청!
			alert(JSON.stringify(error));
		});
	}

}

index.init();