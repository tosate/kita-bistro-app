<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
	<li class="active"><a href="#children" data-toggle="tab" data-tabname="children">Kinder</a></li>
	<li><a href="#child-attributes" data-toggle="tab" data-tabname="child-attributes">Besondere Eingenschaften</a></li>
	<li><a href="#groups" data-toggle="tab" data-tabname="groups">Gruppen</a></li>
</ul>

<!-- Tab pages -->
<div class="tab-content">
	<div role="tabpanel" class="tab-pane active" id="children">
		<h2>Kinder</h2>
		<div id="component-demo"></div>
	</div>
	<div role="tabpanel" class="tab-pane" id="child-attributes">
		<h2>Besondere Eingenschaften</h2>
	</div>
	<div role="tabpanel" class="tab-pane" id="groups">
		<h2>Gruppen</h2>
		<div id="groups"></div>
	</div>
</div>