<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="footer">
	<div style="font-size: smaller; padding: 5px 15px; background-color: #f8f8f8; border-color: #e7e7e7; color: #777;">
		<span>Bistro App Portal <spring:eval expression="@portalUtil.gitInfo" /></span>
	</div>			
</div>