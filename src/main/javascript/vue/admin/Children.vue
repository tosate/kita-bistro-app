<template>
<div class="panel-group">
	<div id="confirmDeleteDialog" class="modal fade" tabindx="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">Kind löschen</div>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="confirmDeleteDialogError" class="alert alert-danger" role="alert" style="text-align: center; display: none;" ref="deleteAlert"></div>
					{{ editChildData.firstName}} {{ editChildData.lastName }} wirklich löschen?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Abbrechen</button>
					<button type="button" class="btn btn-primary" v-on:click="deleteChild">Kind löschen</button>
				</div>
			</div>
		</div>
	</div>
	<div id="createEditChildDialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">Kind speichern</div>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="createEditChildDialogError" class="alert alert-danger" role="alert" style="text-align: center; display: none;" ref="saveAlert"></div>
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
						<div class="row">
							<div class="col-xs-5">
								<label for="allAttributesList">Alle Besonderheiten</label>
								<select id="allAttributesList" class="form-control" multiple="multiple" size="8">
									<option v-for="attribute in attributesPickList" v-bind:value="attribute.id">
										{{ attribute.name }}
									</option>
								</select>
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn btn-block" v-on:click="moveAllOptionsToSelectedList" style="margin-top: 30px;">
									<span class="glyphicon glyphicon-forward" style="display: block;"></span>
								</button>
								<button type="button" class="btn btn-block" v-on:click="moveOptionToSelectedList">
									<span class="glyphicon glyphicon-chevron-right" style="display: block;"></span>
								</button>
								<button type="button" class="btn btn-block" v-on:click="moveOptionToPickList">
									<span class="glyphicon glyphicon-chevron-left" style="display: block;"></span>
								</button>
								<button type="button" class="btn btn-block" v-on:click="moveAllOptionsToPickList">
									<span class="glyphicon glyphicon-backward" style="display: block;"></span>
								</button>
							</div>
							<div class="col-xs-5">
								<label for="selectedAttributesList">Gewählte Besonderheiten</label>
								<select id="selectedAttributesList" class="form-control" multiple="multiple" size="8">
									<option v-for="attribute in editChildData.attributes" v-bind:value="attribute.id">
										{{ attribute.name }}
									</option>
								</select>
							</div>
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
			<div id="childrenError" class="alert alert-danger" role="alert" style="text-align: center; display: none;" ref="alert"></div>
			<button type="button" class="btn btn-default" v-on:click="openModalChildDialog">Kind hinzufügen</button>
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
							<button class="btn btn-default btn-xs" v-on:click="openUpdateChild(child)">
								<span class="glyphicon glyphicon-edit" style="display: block;"></span>
								Bearbeiten
							</button>
							<button class="btn btn-default btn-xs" v-on:click="openConfirmDeleteChild(child)">
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
			allAttributes: [],
			attributesPickList: [],
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
			this.fetchAttributes();
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
		stringDateToLong: function(date) {
			if(date != null) {
				return new Date(date).getTime();
			} else {
				return newDate().getTime();
			}
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
				$("#childrenError").html("Fehler beim Laden der Kinder!");
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
				$("#childrenError").html("Fehler beim Laden der Bereiche!");
			});
		},
		fetchAttributes: function() {
			var vm = this;
			$.get("/admin/fetch/attributes", {
			}).done(function(data) {
				$(vm.$refs.alert).hide();
				console.log(data);
				vm.allAttributes = data;
			}).fail(function() {
				$(vm.$refs.alert).show();
				$("#childError").html("Fehler beim Laden der Besonderheiten!");
			});
		},
		onKeyUp: function() {
			var value = $("#filterInput").val().toLowerCase();
			$("#childrenTable tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		},
		openModalChildDialog: function() {
			var vm = this;
			$("#createEditChildDialog").modal();
			vm.editChildData.id = 0;
			vm.editChildData.firstName = '';
			vm.editChildData.lastName = '';
			vm.editChildData.kitaStartString = vm.getDateIso(new Date());
			vm.editChildData.kitaEndString = vm.getDateIso(new Date());
			vm.editChildData.breakfast = '';
			vm.editChildData.lunch;
			vm.editChildData.attributes = [];
			vm.attributesPickList = vm.allAttributes.slice();
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
				kitaStartLong: vm.stringDateToLong(vm.editChildData.kitaStartString),
				kitaEndLong: vm.stringDateToLong(vm.editChildData.kitaEndString),
				hasBreakfast: vm.editChildData.breakfast,
				hasLunch: vm.editChildData.lunch,
				attributes: this.getSelectedAttributeIds()
			}).done(function(controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.saveAlert).hide();
					$("#createEditChildDialog").modal('hide');
					vm.fetchChildren();
				} else {
					$(vm.$refs.saveAlert).show();
					console.log(controllerResponse.message);
					$("#createEditChildDialogError").html(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				$(vm.$refs.saveAlert).show();
				$("#createEditChildDialogError").html("Fehler beim Hinzufügen des Kindes: " + controllerResponse.message);
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
			vm.editChildData.attributes = child.attributes;
			this.calculateAttributesPickList(vm.editChildData.attributes);
			vm.updateMode = true;
		},
		openConfirmDeleteChild: function(child) {
			var vm = this;
			$("#confirmDeleteDialog").modal();
			vm.editChildData.childId = child.id;
			vm.editChildData.firstName = child.firstName;
			vm.editChildData.lastName = child.lastName;
			vm.editChildData.groupId = child.group.id;
			vm.editChildData.kitaStartString = vm.getDateIso(new Date(child.kitaStart));
			vm.editChildData.kitaEndString = vm.getDateIso(new Date(child.kitaEnd));
			vm.editChildData.breakfast = child.breakfast;
			vm.editChildData.lunch = child.lunch;
			vm.editChildData.attributes = child.attributes;
		},
		deleteChild: function() {
			var vm = this;
			$.get("/admin/delete/child", {
				childId: vm.editChildData.childId
			}).done(function(controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.deleteAlert).hide();
					$("#createEditChildDialog").modal('hide');
					vm.fetchChildren();
				} else {
					console.log(controllerResponse.message);
					$(vm.$refs.deleteAlert).show();
					$("#confirmDeleteDialogError").html(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				$(vm.$refs.deleteAlert).show();
				$("#confirmDeleteDialogError").html("Fehler beim Löschen des Kindes: " + controllerResponse.message);
			});
		},
		moveAllOptionsToSelectedList: function() {
			$("#allAttributesList > option").each(function() {
				$(this).remove().appendTo("#selectedAttributesList");
			});
		},
		moveOptionToSelectedList: function() {
			$("#allAttributesList > option:selected").each(function() {
				$(this).remove().appendTo("#selectedAttributesList");
			});
		},
		moveOptionToPickList: function() {
			$("#selectedAttributesList > option:selected").each(function() {
				$(this).remove().appendTo("#allAttributesList");
			});
		},
		moveAllOptionsToPickList: function() {
			$("#selectedAttributesList > option").each(function() {
				$(this).remove().appendTo("#allAttributesList");
			});
		},
		calculateAttributesPickList: function(attributes) {
			var vm = this;
			vm.attributesPickList = vm.allAttributes.slice();
			attributes.forEach(function(attribute) {
				var index = vm.attributesPickList.map(function(attrib) {
					return attrib.id;
				}).indexOf(attribute.id);
				if(index >= 0) {
					vm.attributesPickList.splice(index, 1);
				}
			});
		},
		getSelectedAttributeIds: function() {
			var result = [];
			$("#selectedAttributesList > option").each(function() {
				result.push($(this).val());
			});
			return result;
		}
	}
}
</script>