<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- Scripts for hashing password -->
<script type="text/javascript" src="js/components/core-min.js"></script>
<script type="text/javascript" src="js/rollups/sha1.js"></script>
<script type="text/javascript" src="js/components/enc-base64-min.js"></script>
<script type="text/javascript" src="js/components/enc-utf16-min.js"></script>


<body>
<h1>Please enter your user details here!</h1>
<form name="registerForm" action="books" method="post" onsubmit="return validateRegistrationForm()">
  User name(email ID):<br>
  <input type="text" name="emailId">
  <br>
  password:<br>
  <input type="password" name="password" >
  <br>
  First name:<br>
  <input type="text" name="firstname">
  <br>
  Last name:<br>
  <input type="text" name="lastname" >
  <br>
  Home address:<br>
  <input type= "text" name="homeAddress">
  <br>
  Alternative address:<br>
  <input type= "text" name="alternativeAddress">
  <br>
  Phone number:<br>
  <input type= "text" maxLength=10 name="phoneNumber" onkeypress='return isNumberKey(event)'>
  <br>
  <input type= "hidden" name= "senderPage" value="registerUser">
  <br><br>
  <input type="submit" value="Submit">
</form> 
<p style="color:Tomato;">
<span id="nameErrMsg" class="error"></span> <br />
</body>

<script>
function validateRegistrationForm(){
	/* var email = document.getElementById("loginform").elements["emailId"].value;
	var pass = document.getElementById("loginform").elements["password"].value; */
	
	var email = document.registerForm.emailId.value;
	var pass = document.registerForm.password.value; 
	var phone = document.registerForm.phoneNumber.value;
	var errorMsg = document.getElementById("nameErrMsg");
	var homeAddress = document.registerForm.homeAddress.value;
	
	
	
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
	if (phone.length != 10){
		errorMsg.innerHTML="phone  must be  10 digits";
		return false;	
	}
	if (homeAddress==null || homeAddress==""){
		errorMsg.innerHTML="Home Address cannot be blank";
		return false;
	}
	
	var hash =  CryptoJS.SHA1(pass);
	//remove later
	//alert("hashed password" + hash);
	document.registerForm.password.value = hash;	
	alert(document.registerForm.password.value);
		
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

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)){
        return false;
    }
    return true;
}

</script>
</html>

