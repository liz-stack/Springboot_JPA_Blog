let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data ={
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#password").val()
		}
		//console.log(data);
	}

		//$.ajax().done().fail(); //ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert요청
}

index.init();