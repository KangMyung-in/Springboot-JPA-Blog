let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		
		$("#btn-update").on("click", () => {
			this.update(); //	this.update(); 호출함
		});
		
			$("#btn-reply-save").on("click", () => {
			this.replySave(); //	
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax({
			type: "POST",
			url: "/api/board", // /api/board 로 맵핑된곳으로 데이터를 날려줌
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})

	},
	
	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id, // /api/board 로 맵핑된곳으로 데이터를 날려줌
			dataType: "json",
			contentType: "application/json; charset=utf-8",
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})

	},
	
	update: function() {
		let id = $("#id").val(); //id를 가지고
		
		let data = { //data 를 가지고
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/"+id, // 여기로 요청함 즉 /api/board 로 맵핑된곳으로 데이터를 날려줌
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp) {
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})

	},
	
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val(), //content 를 가져옴
		};
	
	$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply` , // 이렇게 맵핑된곳에 데이터 날린다 이것도 boardApi에 만들어주는거 잊지말기
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) { //boardApi에서 응답해주면 여기로(resp) 옴
			alert("댓글쓰기가 완료되었습니다.");
			location.href = `/board/${data.boardId}`; //다시 해당 페이지로 돌아감
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})

	},
	
		replyDelete: function(boardId,replyId) { //boardId,replyId 2개를 받는다
		
	$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}` , // 이렇게 맵핑된곳에 데이터 날린다 이것도 boardApi에 만들어주는거 잊지말기
			dataType: "json"
		}).done(function(resp) { //boardApi에서 응답해주면 여기로(resp) 옴
			alert("댓글삭제 성공.");
			location.href = `/board/${boardId}`; //다시 해당 페이지로 돌아감
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})

	},
}
index.init();
