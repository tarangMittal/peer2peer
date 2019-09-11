<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<h2>Log In</h2>

<!-- Scripts for hashing password -->
<script type="text/javascript" src="js/components/core-min.js"></script>
<script type="text/javascript" src="js/rollups/sha1.js"></script>
<script type="text/javascript" src="js/components/enc-base64-min.js"></script>
<script type="text/javascript" src="js/components/enc-utf16-min.js"></script>


</head>
<style>
.error {
color: #E00000;
 }
</style>
<body>

<form name=loginform action="login" method="post" onsubmit="return validateLoginForm()">
  Username:<br>
  <input type="text" name="emailId" value="">
  <br>
  Password:<br>
  <input type="password" name="password" value="">
  <input type= "hidden" name= "senderPage" value="login">
  <br><br>
  <input type="submit" value="Login">
</form> 
<p><a href="http://localhost:8080/booksforyou/registerUser.jsp">
New to BooksForYou? Sign Up</a></p>

<p style="color:Tomato;">
<% 
String loginMsg= (String)request.getAttribute("loginMsg");
if(loginMsg!= null){
	out.println(loginMsg);
}
%>
</p>
<span id="nameErrMsg" class="error"></span> <br />
</body>

<script>
function validateLoginForm(){
	/* var email = document.getElementById("loginform").elements["emailId"].value;
	var pass = document.getElementById("loginform").elements["password"].value; */
	
	var email = document.loginform.emailId.value;
	var pass = document.loginform.password.value; 
	var errorMsg = document.getElementById("nameErrMsg");
	
	
	if (validateEmail(email,errorMsg) == false){
		return false;
	};
	
	if (pass==null || pass==""){
		errorMsg.innerHTML="password cannot be empty";
		return false;
	}
	if (pass.length < 6){
		errorMsg.innerHTML="password  must be at least 6 chars";
		return false;
	}
	
	var hash =  CryptoJS.SHA1(pass);
	//remove later
	//alert("hashed password" + hash);
	document.loginform.password.value = hash;	
	alert(document.loginform.password.value);
		
}

function validateEmail(email,errorMsg){
	if (email==null || email==""){
		errorMsg.innerHTML="username cannot be blank";
		return false;
	}
	var atPosition = email.indexOf("@");
	var dotPosition = email.indexOf(".");
	var comPosition = email.indexOf("com");
	if (atPosition < 0){
		errorMsg.innerHTML="invalid emailId";
		return false;
	}
	if (dotPosition < 0){
		errorMsg.innerHTML="invalid emailId";
		return false;
	}
	if (comPosition < 0){
		errorMsg.innerHTML="invalid emailId";
		return false;
	}
}

</script>

</html>