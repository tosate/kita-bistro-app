<script type="text/javascript">
	groupNames = ${groupNames};
	meals = ${meals}
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
				<label for="inputGroupName" class="fancy-input fancy-input-select" style="margin-right: 20px; width: 180px;">
					<span class="placeholder" style="z-index: 3; font-size: 12px; top: 5px;">Gruppe</span>
					<select id="inputGroupName" name="inputGroupName" class="form-control"></select>
				</label>
				
				<label for="inputMeal" class="fancy-input fancy-input-select" style="margin-right: 20px; width: 180px;">
					<span class="placeholder" style="z-index: 3; font-size: 12px; top: 5px;">Mahlzeit</span>
					<select id="inputMeal" name="inputMeal" class="form-control"></select>
				</label>
			</div>
		</form>
	</div>
</div>