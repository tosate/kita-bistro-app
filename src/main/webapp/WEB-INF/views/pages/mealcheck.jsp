<script type="text/javascript">
	groupNames = ${groupNames};
	mealTypes = ${mealTypes};
</script>

<!-- Modal -->
<div id="actionModalDialog" class="modal fade" tabindex="-1">
	<div class="modal-dialog" role="document" style="width: 50%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form class="fancy-form" id="searchForm" novalidate="novalidate">
			<div id="formFields">
				<label>Gruppe</label>
				<select id="inputGroupName" name="inputGroupName" class="form-control" style="margin-right: 20px; width: 150px;"></select>
				
				<label>Mahlzeit</label>
				<select id="inputMealType" name="inputMealType" class="form-control" style="margin-right: 20px; width: 150px;"></select>
				
				<div class="pull-right">
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