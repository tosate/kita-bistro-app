<script type="text/javascript">
	groupNames = ${groupNames};
	mealTypes = ${mealTypes};
</script>

<div id="noticeModalDialog" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Notiz erfassen</div>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="noticeDialogError" class="alert alert-danger" role="alert" style="text-align:center; display: none;"></div>
				<form id="saveNoticeForm">
					<div class="form-group">
						<label for="noticeTextarea">Notiz</label>
						<textarea id="noticeTextarea" rows="2" cols="60" class="form-control"></textarea>
						<input type="hidden" id="mealId"></input>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
					Abbrechen
				</button>
				<button id="saveNotice" type="button" class="btn btn-primary">
					Notiz speichern
				</button>
			</div>
		</div>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form class="form-inline" id="searchForm" novalidate="novalidate">
			<div class="form-group" style="margin-right: 20px;">
				<label for="inputGroupName">Bereich</label>
				<select id="inputGroupName" name="inputGroupName" class="form-control m1-sm-2 mr-sm-4 my-2"></select>
			</div>
			<div class="form-group" style="margin-right: 20px;">
				<label for="inputMealType">Mahlzeit</label>
				<select id="inputMealType" name="inputMealType" class="form-control m1-sm-2 mr-sm-4 my-2"></select>
			</div>
			<button ttId="search.search" type="submit" class="btn btn-primary" id="searchBtn" style="margin-right: 50px;">
				<span class="glyphicon glyphicon-search" style="margin-right: 5px;"></span>Suchen
			</button>
			<div class="form-group">
				<label for="hideEatenSwitch">Status 'gegessen' verstecken</label>
				<label id="hideEatenSwitch" class="switch">
					<input type="checkbox" id="hideEatenCheckbox">
					<span class="slider round"></span>
				</label>
			</div>
		</form>
	</div>
</div>

<div id="searchingSpinner" style="display: none; margin: 200px auto; text-align: center;">
	<span class="glyphicon glyphicon-search"
		style="display: block; font-size: 200%; margin-bottom: 5px;"></span>
	<div>Searching</div>
	<div>
		<span class="dots"><span>.</span><span>.</span><span>.</span></span>
	</div>
</div>

<div id="searchWarning" class="alert alert-warning"
	style="width: 90%; text-align: center; margin: 200px auto; font-weight: bold; display: none;">
	Keine Ergebnisse gefunden!
</div>

<div>
	<div id="searchResult" class="panel-body"></div>
</div>
