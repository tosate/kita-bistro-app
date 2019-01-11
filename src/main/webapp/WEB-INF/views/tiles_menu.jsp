<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:url var="mainUrl" value="/mealcheck" />

<!-- Static navbar -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header" style="height: 100%;">
			<a class="navbar-brand" href="/mealcheck"> <span
				style="font-family: 'museo_sans700', Tahoma, Geneva, sans-serif; font-size: 30px; line-height: 50px; margin-left: 10px;">Bistro
					App</span>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li ttId="menu.mealcheck" style="text-align: center;"><a
					href="mealcheck"> <span class="glyphicon glyphicon-cutlery"
						style="display: block; font-size: 200%; margin-bottom: 5px;"></span>
						Essenliste
				</a></li>
				<li ttId="menu.analysis" style="text-align: center;"><a
					href="analysis"> <span class="glyphicon glyphicon-blackboard"
					style="display: block; font-size: 200%; margin-bottom: 5px;"></span>
					Auswerten
				</a></li>
				<sec:authorize access="isAuthenticated()">
					<li ttId="menu.admin" style="text-align: center;"><a
						href="admin"> <span class="glyphicon glyphicon-wrench"
							style="display: block; font-size: 200%; margin-bottom: 5px;"></span>
							Einstellungen
					</a></li>
					<li ttId="menu.logout" style="text-align: center;"><a
						href="logout"> <span class="glyphicon glyphicon-off"
							style="display: block; font-size: 200%; margin-bottom: 5px;"></span>
							Abmelden
					</a></li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li style="text-align: center;"><a href="login"> <span
							class="glyphicon glyphicon-log-in"
							style="display: block; font-size: 200%; margin-bottom: 5px;"></span>
							Anmelden
					</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>