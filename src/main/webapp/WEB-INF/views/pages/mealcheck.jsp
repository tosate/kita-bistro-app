<script type="text/javascript">
	groupNames = ${groupNames};
	mealTypes = ${mealTypes}
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
				<label for="inputGroupName" class="fancy-input fancy-input-select" style="margin-right: 20px; width: 150px;">
					<span class="placeholder" style="z-index: 3; font-size: 12px; top: 5px;">Gruppe</span>
					<select id="inputGroupName" name="inputGroupName" class="form-control"></select>
				</label>
				
				<label for="inputMealType" class="fancy-input fancy-input-select" style="margin-right: 20px; width: 150px;">
					<span class="placeholder" style="z-index: 3; font-size: 12px; top: 5px;">Mahlzeit</span>
					<select id="inputMealType" name="inputMealType" class="form-control"></select>
				</label>
				
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