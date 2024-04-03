<%@include file="header.jsp"%>
<h1>Login</h1>
<form id="Login"  method="post" action="/login">
<label for="username">Username:</label>
<input type="text" id="username" name="username" required><br>
<label for="password">Password:</label>
<input type="password" id="password" name="password" style="margin-top: 10px" required><br>
<input type="submit" value="Login" style="background-color:darkgray; color: white;width: 120px;height: 30px;margin-left: 50px;margin-top: 10px">
</form>
<%@include file="footer.jsp"%>