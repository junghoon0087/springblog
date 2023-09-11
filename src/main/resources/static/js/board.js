let index={
    init:function(){
      $('#btn-write').on('click',()=>{
      this.save();
      });
      $('#btn-delete').on('click',()=>{
             this.deleteById();
           });
      $('#btn-update').on('click',()=>{
                   this.update();
                 });
      $('#btn-reply-save').on('click',()=>{
               this.replySave();
            });


    },
    replyDelete : function(boardId, replyId){
      $.ajax({
        type: "DELETE",
        url: `/api/board/${boardId}/reply/${replyId}`,
        dataType: "json"
       }).done(function(resp){
      alert("댓글삭제 성공");
      location.href = `/board/${boardId}`;
     }).fail(function(error){
      alert(JSON.stringify(error));
     });
    },
    replySave:function(){
        let data={
          boardId:$("#boardId").val(),
          content:$("#reply-content").val(),
        }
       console.log(data);

       $.ajax({
          type:"POST",
          url:`/api/board/${data.boardId}/reply`,
          data:JSON.stringify(data),
           contentType:"application/json; charset=utf-8",
          dataType:"json"
        }).done(function(resp){
           console.log(resp)
           alert("댓글이 등록되었습니다.");
           location.href=`/board/${data.boardId}`;
        }).fail(function(error){
              console.log(error);
             alert(JSON.stringify(error));
        });
     },
    //글삭제
    deleteById:function(){
        var id=$('#id').text();
        $.ajax({
          type:"DELETE",
          url:'/api/board/'+id
        }).done(function(resp){
            alert("삭제가 되었습니다.");
           location.href="/";
        }).fail(function(error){
          alert(JSON.stringify(error));
        });
     },
    //글쓰기완료
    save:function(){

            let data={
                title:$('#title').val(),
                content:$('#content').val(),

            }

            $.ajax({
              type:"POST",
              url:"/api/board",
              data:JSON.stringify(data),
              contentType:"application/json;utf-8",
              dataType:"json"
            }).done(function(resp){
              alert("글쓰기가 완료되었습니다.");
              console.log(resp);
              location.href="/";
            }).fail(function(error){
              console.log(JSON.stringify(error))
            });
        },
        login:function(){
              let data={
                username:$("#title").val(),
                password:$("#password").val()
             }
             $.ajax({
               type:"POST",
               url:'/api/user/login',
               data:JSON.stringify(data), // http body 데이터
               contentType:"application/json; charset=utf-8",
               dataType:"json"
             }).done(function(resp){
              alert("로그인이 완료되었습니다.");
               console.log(resp);
               location.href="/";
            }).fail(function(error){
              alert(JSON.stringify(error));
            });
          },
          update:function(){
                        let id=$('#id').val();
                        let data={
                          title:$("#title").val(),
                          content:$("#content").val(),
                       }
                       console.log(data);
                       $.ajax({
                         type:"PUT",
                         url:'/api/board/'+id,
                         data:JSON.stringify(data),
                         contentType:"application/json; charset=utf-8",
                         dataType:"json"
                       }).done(function(resp){
                        alert("글 수정이 완료되었습니다.");

                         location.href="/";
                      }).fail(function(error){
                        alert(JSON.stringify(error));
                      });
                    }


}

index.init();