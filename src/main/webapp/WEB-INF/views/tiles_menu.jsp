<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>

<!-- Static navbar -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header" style="height: 100%;">
			<a class="navbar-brand" href="/mealcheck">
				<img src="/img/logo-kita.png" style="display: inline-block;top: 0px;position: absolute;" />
				<span style="font-family: 'museo_sans700', Tahoma, Geneva, sans-serif; font-size: 50px; line-height: 50px; margin-left: 120px;">Bistro App</span>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li ttId="menu.mealcheck" style="text-align: center;">
					<a href="mealcheck">
						<span class="glyphicon glyphicon-cutlery" style="display: block;font-size: 200%; margin-bottom: 5px;"></span>
						Meal check
					</a>
			</ul>
		</div>
	</div>
</nav>