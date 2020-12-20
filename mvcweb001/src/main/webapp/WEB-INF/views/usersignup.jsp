<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<form class="form-horizontal" action='' method="POST">
  <fieldset>
    <div id="legend">
      <legend class="">Register</legend>
    </div>
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username">userid</label>
      <div class="controls">
        <input type="text" id="userid" name="userid" placeholder="" class="input-xlarge">
        <p class="help-block">userid can contain any letters or numbers, without spaces</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="text" id="useremail" name="useremail" placeholder="" class="input-xlarge">
        <p class="help-block">Please provide your E-mail</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="passcode" name="passcode" placeholder="" class="input-xlarge">
        <p class="help-block">Password should be at least 4 characters</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password -->
      <label class="control-label"  for="password_confirm">Password (Confirm)</label>
      <div class="controls">
        <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="input-xlarge">
        <p class="help-block">Please confirm password</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success" id="submit">Register</button>
      </div>
    </div>
  </fieldset>
</form>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script language="javascript">
$(document)
.on("click","#submit",function(){
	if($("#userid").val()==""){
		alert("invalid id")
		return false;
	}
	if($("#useremail").val()==""){
		alert("invalid useremail")
		return false;
	}
	if($("#passcode").val()==""){
		alert("invalid userpassword")
		return false;
	}
	if($("#passcode").val()!=$("#password_confirm").val()){
		alert("invalid userpassword")
		return false;
	}
	
	$.ajax({
		  url:'dosignup',//호출될 서블릿의 경로(이름)
		  method:'post',//데이터 전송방식 get/post
		  //전송할 데이터(쿼리스트링)//
		  data:'userid='+$('input[name=userid]').val()+'&useremail='+$('input[name=useremail]').val()+'&passcode='+$('input[name=passcode]').val(),
		  dataType:'txt',//반환받을 데이터 타입 
		  beforeSend:function(){},
		  success:function(txt){//호출 성공해서 완료하면 
			

		},
		
		complete:function(txt){
			alert(txt);
			window.location.href = 'http://localhost:8181/java/list';

		} 
		
	    })
	    
})


</script>
