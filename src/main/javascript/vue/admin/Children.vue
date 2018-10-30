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
							<input id="firstNameInput" v-model="editChildData.firstName" class="form-control" placeholder="Vorname" required>
						</div>
						<div class="form-group">
							<label for="lastNameInput">Nachname</label>
							<input id="lastNameInput" v-model="editChildData.lastName" class="form-control" placeholder="Nachname" required>
						</div>
						<div class="form-group">
							<label for="kigaGroupSelect">Gruppe</label>
							<select id="kigaGroupSelect" v-model="editChildData.groupId" class="form-control">
								<option v-for="group in groups" v-bind:value="group.id">
									{{ group.name }}
								</option>
							</select>
						</div>
						<div class="form-group">
							<label for="kitaStartDateInput">Kita Start</label>
							<input id="kitaStartDateInput" v-model="editChildData.kitaStartString" type="date" required>
						</div>
						<div class="form-group">
							<label for="kitaEndDateInput">Kita Ende</label>
							<input id="kitaEndDateInput" v-model="editChildData.kitaEndString" type="date" required>
						</div>
						<div class="form-check">
							<label for="checkBreakfast">Frühstück</label>
							<label id="checkBreakfast" class="switch">
								<input type="checkbox" id="checkboxBreakfast" v-model="editChildData.breakfast">
								<span class="slider round"></span>
							</label>
							<label for="checkLunch">Mittagessen</label>
							<label id="checkLunch" class="switch">
								<input type="checkbox" id="checkboxLunch" v-model="editChildData.lunch">
								<span class="slider round"></span>
							</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
					<button type="button" class="btn btn-primary" v-on:click="saveChild(updateMode)">Kind speichern</button>
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
							<button class="btn btn-default btn-xs" @click="openUpdateChild(child)">
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
			groups: [],
			editChildData: {
				childId: 0,
				firstName: '',
				lastName: '',
				groupId: 0,
				kitaStartString: this.getDateIso(new Date()),
				kitaEndString: this.getDateIso(new Date()),
				breakfast: false,
				lunch: false,
				attributes: []
			},
			updateMode: false
		};
		return data;
	},
	methods: {
		initChildrenTab: function() {
			this.fetchGroups();
			this.fetchChildren();
		},
		getDateIso: function(date) {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var dt = date.getDate();
			
			if(dt < 10) {
				dt = '0' + dt;
			}
			if(month < 10) {
				month = '0' + month;
			}
			
			return year + '-' + month + '-' + dt;
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
			var vm = this;
			$("#createEditChildDialog").modal();
			vm.updateMode = false;
		},
		saveChild: function(updateMode) {
			var vm = this;
			$.get("/admin/save/child", {
				updateMode: vm.updateMode,
				childId: vm.editChildData.childId,
				firstName: vm.editChildData.firstName,
				lastName: vm.editChildData.lastName,
				groupId: vm.editChildData.groupId,
				kitaStartLong: new Date(vm.editChildData.kitaStartString).getTime(),
				kitaEndLong: new Date(vm.editChildData.kitaEndString).getTime(),
				hasBreakfast: vm.editChildData.breakfast,
				hasLunch: vm.editChildData.lunch
			}).done(function(controllerResponse) {
				if(controllerResponse.success) {
					vm.editChildData.id = 0;
					vm.editChildData.firstName = '';
					vm.editChildData.lastName = '';
					vm.editChildData.kitaStartString = vm.getDateIso(new Date());
					vm.editChildData.kitaEndString = vm.getDateIso(new Date());
					vm.editChildData.breakfast = '';
					vm.editChildData.lunch;
					$("#createEditChildDialog").modal('hide');
					vm.fetchChildren();
				} else {
					console.log(controllerResponse.message);
					alert(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				alert("Fehler beim Hinzufügen des Kindes: " + controllerResponse.message);
			});
		},
		openUpdateChild: function(child) {
			var vm = this;
			$("#createEditChildDialog").modal();
			vm.editChildData.childId = child.id;
			vm.editChildData.firstName = child.firstName;
			vm.editChildData.lastName = child.lastName;
			vm.editChildData.groupId = child.group.id;
			vm.editChildData.kitaStartString = vm.getDateIso(new Date(child.kitaStart));
			vm.editChildData.kitaEndString = vm.getDateIso(new Date(child.kitaEnd));
			vm.editChildData.breakfast = child.breakfast;
			vm.editChildData.lunch = child.lunch;
			vm.updateMode = true;
		}
	}
}
</script>