<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
	lastNames = ${lastNames};
</script>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
	<li class="active"><a href="#children" data-toggle="tab" data-tabname="children">Kinder</a></li>
	<li><a href="#child-attributes" data-toggle="tab" data-tabname="child-attributes">Besondere Eigenschaften</a></li>
	<li><a href="#groups" data-toggle="tab" data-tabname="groups">Bereiche</a></li>
</ul>

<!-- Tab pages -->
<div class="tab-content">
	<div role="tabpanel" class="tab-pane active" id="children">
		<h2>Kinder</h2>
		<div id="manage-children"></div>
	</div>
	<div role="tabpanel" class="tab-pane" id="child-attributes">
		<h2>Besondere Eigenschaften</h2>
		<div id="manage-child-attributes"></div>
	</div>
	<div role="tabpanel" class="tab-pane" id="groups">
		<h2>Bereiche</h2>
		<div id="manage-groups"></div>
	</div>
</div>