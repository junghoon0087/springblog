let index={
    init:function(){
     $('#btn-save').on('click',()=>{
        this.save();
     });
     $('#btn-login').on('click',()=>{
        this.login();
     });
     $('#btn-update').on('click',()=>{
        this.update();
     });
     $('#btn-check').on('click',()=>{
      event.preventDefault();
        this.check();
     });

   },
   check:function(){
         let  username=$("#username").val();
          $.ajax({
              type:"GET",
              url:'/api/user/'+username,
              contentType:"application/json; charset=utf-8"
         }).done(function(resp){
         console.log(resp);
           if(resp=="OK"){
              alert("사용할수 있는 아이디입니다.");
            }else{
              alert("아이디가 중복되었습니다.");
             $("#username").val("");
              $("#username").focus();
            }
         }).fail(function(error){
          console.log(error);
              alert(JSON.stringify(error));
         });
      },

         update:function(){
              let data={
               id:$('#id').val(),
               password:$("#password").val(),
               email:$('#email').val()
              }
              $.ajax({
                  type:"PUT",
                  url:'/user',
                  data:JSON.stringify(data),
                  contentType:"application/json; charset=utf-8",
                  dataType:"json"
             }).done(function(resp){
                   alert("회원수정이 완료되었습니다.");
                   console.log(resp);
                   location.href="/";
             }).fail(function(error){
                  alert(JSON.stringify(error));
             });
          },
    save:function(){
          let data={
           username:$("#username").val(),
           password:$("#password").val(),
           email:$('#email').val()
          }
          $.ajax({
              type:"POST",
             // url:'/auth/joinProc',

                   url:'/api/user',
              data:JSON.stringify(data), // http body 데이터
              contentType:"application/json; charset=utf-8",
              dataType:"json"
         }).done(function(resp){
                alert("회원가입이 완료되었습니다.");
                 location.href="/auth/loginForm";
         }).fail(function(error){
            alert("회원가입에 실패하였습니다.");
         });
      },



login:function(){
   let data={
   username:$("#username").val(),
   password:$("#password").val()
 }
$.ajax({
   type:"POST",
   url:'/api/user/login',
   data:JSON.stringify(data), // http body 데이터
   contentType:"application/json; charset=utf-8",
   dataType:"json"
}).done(function(resp){
   console.log(resp);
   if(resp.data==1){
      alert("로그인이 완료되었습니다.");
      location.href="/";
   }else{
      alert("아이디와 비밀번호를 확인해주세요.");
      $('#username').val("");
      $('#password').val("");
      return;
    }
    }).fail(function(error){
    alert("서버 오류입니다.");
   });
  }
}
index.init();