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
		<form class="fancy-form" id="searchForm" novalidate="novalidate">
			<div class="row">
				<div class="form-group" style="margin-right: 20px;">
					<label for="inputGroupName">Bereich</label>
					<select id="inputGroupName" name="inputGroupName" class="form-control m1-sm-2 mr-sm-4 my-2"></select>
				</div>
				<div class="form-group" style="margin-right: 20px;">
					<label for="inputMealType">Mahlzeit</label>
					<select id="inputMealType" name="inputMealType" class="form-control m1-sm-2 mr-sm-4 my-2"></select>
				</div>
				<div>
					<button ttId="search.search" type="submit" class="btn btn-primary" id="searchBtn">
						<span class="glyphicon glyphicon-search" style="display: block;"></span>Suchen
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div>
	<div id="searchResult" class="panel-body"></div>
</div>