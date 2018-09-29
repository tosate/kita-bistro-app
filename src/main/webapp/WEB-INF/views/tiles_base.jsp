<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<tilesx:useAttribute id="additionalJsFiles" name="additionalJsFilesList" classname="java.util.List" ignore="true" />

<!DOCTYPE html>
<html>
	<head>
		<title>Kita gGmbh Trier - Bistro App</title>
		
		<script src="/webjars/BistroAppPortal/shared.bundle.js"></script>
		<% /************ costum page js : *************/ %>
		<c:forEach var="script" items="${additionalJsFiles}">
			<script src="${script}"></script>
		</c:forEach>
		<link rel="stylesheet" type="text/css" class="ui" href="css/portal.css" />
	</head>
	<body>
		<div class="container">
			<!--  menu -->
			<tiles:insertAttribute name="menu" />
			<!-- content -->
			<tiles:insertAttribute name="content" />
		</div>
		<!-- footer -->
		<tiles:insertAttribute name="footer" />
	</body>
</html>