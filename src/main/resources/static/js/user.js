let index = {
	init: function() {
	$("#btn-save").on("click",()=>{ //function(){}을 사용안하고 ,()=>화살표함수 쓴 이유는 this를 바인딩 하기 위해서임 !
		this.save();
		}); //누군가 btn-save 찾으면 on이란 함수는 첫번째파라미터는 어떤 이벤트가 일어날지 결정하고 2번쨰에 무엇을 할 지 정한다
   $("#btn-login").on("click",()=>{ //function(){}을 사용안하고 ,()=>화살표함수 쓴 이유는 this를 바인딩 하기 위해서임 !
		this.login(); //btn-login 함수를 클릭하면 login함수를 호출해라
		}); 
   
   },
		
		login: function(){
		//	alert('user의 save함수 호출됨'); 
			let data = {
		username: $("#username").val(), //username을 찾는다 어떻게? #username 로(#아아디값으로 찾아서 val로 값을  username에 바인딩함) date에 넣어준다
		password: $("#password").val(),
	};
	// ajax통신을 이용하여 json 데이터로 바꿔서 insert요청함
	// ajax 호출시 기본default는 비동기 호출임
	$.ajax({
		type: "POST", //데이터 보낼방식
		url: "/blog/api/user/login", //요청할 주소
		data: JSON.stringify(data),//보낼 데이터타입은 자바스크립트가 인식못하므로 제이슨으로함 , http body 데이터
		//회원가입 수행 요청해서 정상이면 done 수행 아니면 fail
		contentType: "application/json; charset=utf-8", //body타입이 어떤 타입인지 알려줌(mine라고함)
		datatype: "json" //요청을 서버로 해서 응답이 왓을때 기본적으론 문자열임 but json이라면? javascript 오브젝트로 변경해줌
	}).done(function(resp){
		alert("로그인인이 완료되었습니다.");
		//console.log(resp);
		location.href="/blog"; //로그인이 완료되면 /blog 간다
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
		},
		
			save: function(){
		//	alert('user의 save함수 호출됨'); 
			let data = {
		username: $("#username").val(), //username을 찾는다 어떻게? #username 로(#아아디값으로 찾아서 val로 값을  username에 바인딩함) date에 넣어준다
		password: $("#password").val(),
		email: $("#email").val(),
	};
	// ajax통신을 이용하여 json 데이터로 바꿔서 insert요청함
	// ajax 호출시 기본default는 비동기 호출임
	$.ajax({
		type: "POST", //데이터 보낼방식
		url: "/blog/api/user", //요청할 주소
		data: JSON.stringify(data),//보낼 데이터타입은 자바스크립트가 인식못하므로 제이슨으로함 , http body 데이터
		//회원가입 수행 요청해서 정상이면 done 수행 아니면 fail
		contentType: "application/json; charset=utf-8", //body타입이 어떤 타입인지 알려줌(mine라고함)
		datatype: "json" //요청을 서버로 해서 응답이 왓을때 기본적으론 문자열임 but json이라면? javascript 오브젝트로 변경해줌
	}).done(function(resp){
		alert("회원가입이 완료되었습니다.");
		//console.log(resp);
		location.href="/blog";
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
		}
	}  
	

	index.init(); // index에있는 init함수 호출함
	
	