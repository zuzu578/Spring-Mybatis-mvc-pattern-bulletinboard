<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<style>
a { text-decoration:none } 
</style>
<body>
<form method="POST" action="write">
   <table class="table table-striped">
    <!-- 글쓰기  -->
   		
    	<tr><td algin=right>제목</td> <td><div class="input-group mb-3">
  
  <input type="text" class="form-control" id="bTitle" name="bTitle" placeholder="" aria-label="Username" aria-describedby="basic-addon1">
</div></td></tr>
    	<tr><td algin=right>작성자</td> <td><div class="input-group mb-3">
 
  <input type="text" class="form-control" id="bName" name="bName" placeholder="" aria-label="Username" aria-describedby="basic-addon1">
</div></td></tr>
    	<tr><td algin=right>내용</td><td><div class="input-group">
  
  <textarea class="form-control" name="bContent" id="bContent" aria-label="With textarea"></textarea>
</div></td></tr>
    	
    </table>
    <div class="Button-items">
    <button type="button" class="btn btn-info"><a href="list">list</a> </button>
    <button type="button" id="submit" name="submit"class="btn btn-success">Success</button>

    </div>
    </form>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script language="javascript">
$(document)
.on("click","#submit",function(){
	if($("#bTitle").val()==""){
		alert("제목을 입력하세요.");
		return false;
		
	}
	if($("#bName").val()==""){
		alert("작성자 이름을 입력하세요.");
		return false;
		
	}
	if($("#bContent").val()==""){
		alert("내용을 입력하세요.");
		return false;
		
	}
})

</script>
