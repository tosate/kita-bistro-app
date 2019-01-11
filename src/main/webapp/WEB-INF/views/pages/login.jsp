<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="ieWarning" class="alert alert-danger" style="padding: 15px; text-aling: center; width: 50%; margin: 100 px auto; font-weight: bold; display: none;">
Internet Explorer wird nicht unterstützt!<br/> Bitte nutzen Sie Apple Safari, Mozilla Firefox oder Google Chrome.
</div>

<c:if test="${param.error != null}">
<div class="alert alert-danger" role="alert" style="padding: 5px 15px; text-align: center; width: 50%; margin: 100px auto; font-weight: bold;">
	Ungültige Zugangsdaten - Bitte versuchen Sie es erneut!
	<button type="button" class="close" data-dismiss="alert" aria-label="Schließen">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
</c:if>

<form class="form-signin" name="loginForm" action="<c:url value='/login' />" method="POST">
	<label for="username" class="sr-only">Benutzername</label>
	<input type="text" name="username" placeholder="Benutzername" class="form-control" style="margin-bottom: 0px;" required autofocus>
	<label for="password" class="sr-only">Passwort</label>
	<input type="password" name="password" class="form-control" placeholder="Passwort" required>
	<label class="checkbox" style="text-align: center;">
		<input type="checkbox" name="remember-me">angemeldet bleiben
	</label>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<button id="form-signin-submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Anmelden</button>
</form>