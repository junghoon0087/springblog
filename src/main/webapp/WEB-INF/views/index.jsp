<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./layout/header.jsp" %>

<div class="container mt-5 pt-5">
  <c:forEach var="board" items="${boards.content}">
   <div class="card m-1">
    <div class="card-body">
      <h4 class="card-title">${board.title}</h4>
       <p class="card-text">${board.content}</p>
       <p class="card-text">작성자 : ${board.user.username}</p>
      <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
    </div>
   </div>
  </c:forEach>


 <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
    <c:choose>
     <c:when test="${boards.first}">
      <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
    </c:when>
    <c:otherwise>
     <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
    </c:otherwise>
   </c:choose>
   <c:choose>
    <c:when test="${boards.last}">
     <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
    </c:when>
   <c:otherwise>
     <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
   </c:otherwise>
  </c:choose>
 </ul>
</nav>


</div>

<%@ include file="./layout/footer.jsp" %>