<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
<button class='btn btn-secondary' onclick="history.back()">돌아가기</button>

<c:if test="${board.user.id==principal.id || board.user.id==null}">
<!-- <button class='btn btn-warning' id="btn-update">수정</button> -->
<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
<button class='btn btn-danger' id="btn-delete">삭제</button>
</c:if>

    <div class="m-3">
     글번호 : <span id="id"><i>${board.id}</i></span>
     작성자 : <span><i>${board.user.username}</i></span>
     조회수 : <span><i>${board.count}</i></span>
    </div>
    <div class="m-3 form-group">
      <label class="form-label">제목(Title)</label>
      <h2> ${board.title}</h2>
    </div>
 <hr>
    <div class="m-3 form-group">
      <label class="form-label">내용(Content)</label>
      <div>${board.content}</div>
    </div>
 </div>
 <hr>

 <div class="card">
    <form>
      <input type="hidden" id="boardId" value="${board.id}">
       <div class="card-body">
         <textarea class="form-control" id="reply-content"></textarea>
       </div>
     <div class="card-footer">
         <button class="btn btn-primary" id="btn-reply-save" type="button">등록</button>
     </div>
    </form>
   </div>
 <div class="card">
   <div class="card-header">댓글리스트</div>
    <ul class="list-group" id="reply-box">
    <c:forEach var="reply" items="${board.replies}">
      <li class="list-group-item d-flex justify-content-between" id="reply-1">
       <div>${reply.content}</div>
        <div class="d-flex">
         <div class="font-italic m-1"> 작성자 : ${reply.user.username} </div>
          <c:if test="${reply.user.id eq principal.id}">
           <button onClick="index.replyDelete(${board.id},${reply.id})" class="badge">삭제</button>
         </c:if>
      </div>
     <!-- <button class="badge btn-danger btn">삭제</button> -->
   </div>
   </li>
  </c:forEach>
 </ul>
 </div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>