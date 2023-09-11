<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang = 'en'>
<head>
     <title>join.jsp</title>
     <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<div class="container">

<form>
   <div class="form-group">
     <label for="username">유저네임</label>
        <input type="text" id="username">
     </div>
   <div class="form-group">
        <label for="password">패스워드</label>
        <input type="password" id="password">
   </div>

    <div class="form-group">
      <label for="email">이메일</label>
       <input type="email" id="email">
    </div>
</form>
     <button id="join--submit" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/join.js"></script>
</body>
</html>