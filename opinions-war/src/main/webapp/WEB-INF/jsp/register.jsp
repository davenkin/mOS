<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styles/register.css">
</head>
<body>
<div id="register_form">
	<h1>Register Opinions!</h1>
	<form action="register" method="post">
	<div class="register_section">
	<label for="userName">Name:</label>
	<br/>
	<input type="text" name="userName" id="userNameBox"/>
	</div>
	<div class="register_section">
	<label for="userEmail">Email:</label>
	<br/>
        	<input type="text" name="userEmail" id="userEmailBox"/>
        	</div>
        	<div class="register_section">
	<label for="userPassword">Password:</label>
         <br/>
    	<input type="password" name="userPassword" id="userPasswordBox"/>
    	</div>
    	<div class="register_section">
        <button id="submitButton" name="submitButton" value="submit" type="submit">Go-></button>
    	</div>
	</form>
	</div>
</body>
</html>