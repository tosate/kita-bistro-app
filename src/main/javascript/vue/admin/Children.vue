<template>
<div class="panel-group">
	<div id="createEditChildDialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">Kind hinzufügen</div>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form v-on:submit.prevent="onChildFormSubmit">
						<div class="form-group">
							<label for="firstNameInput">Vorname</label>
							<input id="firstNameInput" class="form-control" placeholder="Vorname" required>
						</div>
						<div class="form-group">
							<label for="lastNameInput">Nachname</label>
							<input id="lastNameInput" class="form-control" placeholder="Nachname" required>
						</div>
						<div class="form-group">
							<label for="kigaGroupSelect">Gruppe</label>
							<select id="kigaGroupSelect" class="form-control">
								<option v-for="group in groups" v-bind:value="group.id">
									{{ group.name }}
								</option>
							</select>
						</div>
						<div class="form-group">
							<label for="kitaStartDateInput">Kita Start</label>
							<input id="kitaStartDateInput" type="date" required>
						</div>
						<div class="form-group">
							<label for="kitaEndDateInput">Kita Ende</label>
							<input id="kitaEndDateInput" type="date" required>
						</div>
						<div class="form-check">
							<label for="checkBreakfast">Frühstück</label>
							<label id="checkBreakfast" class="switch">
								<input type="checkbox" id="checkboxBreakfast">
								<span class="slider round"></span>
							</label>
							<label for="checkLunch">Mittagessen</label>
							<label id="checkLunch" class="switch">
								<input type="checkbox" id="checkboxLunch">
								<span class="slider round"></span>
							</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
					<button type="button" class="btn btn-primary" v-on:click="createNewChild">Kind speichern</button>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<button type="button" class="btn btn-default" v-on:click="showModalChildDialog">Kind hinzufügen</button>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<input id="filterInput" type="text" placeholder="Suchen..." v-on:keyup="onKeyUp">
			<br>
			<table id="childrenTable" class="table table-striped table-hover">
				<thead>
					<tr>
						<td>Vorname</td>
						<td scope="col">Nachname</td>
						<td>Aktionen</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="child in children" v-bind:key="child.id">
						<td>{{child.firstName}}</td>
						<td>{{child.lastName}}</td>
						<td>
							<button class="btn btn-default btn-xs">
								<span class="glyphicon glyphicon-edit" style="display: block;"></span>
								Bearbeiten
							</button>
							<button class="btn btn-default btn-xs">
								<span class="glyphicon glyphicon-trash" style="display: block;"></span>
								Löschen
							</button>
							<!-- Abwesenheit -->
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</template>

<script>
export default {
	data: function () {
		var data = {
			children: [],
			groups: []
		};
		return data;
	},
	methods: {
		initChildrenTab: function() {
			this.fetchGroups();
			this.fetchChildren();
		},
		fetchChildren: function() {
			var vm = this;
			$.get("admin/fetch/children" , {
			}).done(function(data) {
				$(vm.$refs.alert).hide();
				console.log(data);
				vm.children = data;
			}).fail(function() {
				$(vm.$refs.alert).show();
			});
		},
		fetchGroups: function() {
			var vm = this;
			$.get("/admin/fetch/groups", {
			}).done(function (data) {
				$(vm.$refs.alert).hide();
				console.log(data);
				vm.groups = data;
			}).fail(function () {
				$(vm.$refs.alert).show();
			});
		},
		onKeyUp: function() {
			var value = $("#filterInput").val().toLowerCase();
			$("#childrenTable tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		},
		showModalChildDialog: function() {
			$("#createEditChildDialog").modal();
		},
		createNewChild: function() {
			var vm = this;
			$.get("/admin/create/child", {
				firstName: $("#firstNameInput").val(),
				lastName: $("#lastNameInput").val(),
				groupIdValue: $("#kigaGroupSelect").val(),
				kitaStartDate: $("#kitaStartDateInput").val(),
				kitaEndDate: $("#kitaEndDateInput").val(),
				hasBreakfast: $("#checkboxBreakfast").is(":checked"),
				hasLunch: $("#checkboxLunch").is(":checked")
			}).done(function(controllerResponse) {
				if(controllerResponse.success) {
					$("#firstNameInput").val('');
					$("#lastNameInput").val('');
					$("#kitaStartDateInput").val('');
					$("#kitaEndDateInput").val('');
					$("#checkboxBreakfast").prop('checked', false);
					$("#checkboxLunch").prop('checked', false);
					$("#createEditChildDialog").modal('hide');
					vm.fetchChildren();
				} else {
					console.log(controllerResponse.message);
					alert(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				alert("Fehler beim Hinzufügen des Kindes: " + controllerResponse.message);
			});
		}
	}
}
</script>