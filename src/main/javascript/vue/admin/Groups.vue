<template>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-body">
			<form v-on:submit.prevent="onSubmit">
				<div class="row">
					<div class="form-group" style="margin-right: 20px;">
						<label for="nameInput">Name</label>
						<input id="nameInput" v-model="groupData.name" class="form-control m1-sm-2 mr-sm-4 my-2" required>
					</div>
					<button type="submit" class="btn btn-primary my-2">Hinzufügen</button>
				</div>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">Name</th>
						<th>Aktionen</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="group in groups" v-bind:key="group.id">
						<template v-if="editId == group.id">
							<td><input v-model="editGroupData.name" type="text"></td>
							<td>
								<button class="btn btn-default btn-xs" @click.prevent="onEditSubmit(group.id)">
									<span class="glyphicon glyphicon-save" style="display: block;"></span>
									Speichern
								</button>
								<button class="btn btn-default btn-xs" @click.prevent="onCancel">
									<span class="glyphicon glyphicon-remove" style="display: block;"></span>
									Abbrechen
								</button>
							</td>
						</template>
						<template v-else>
							<td>{{group.name}}</td>
							<td>
								<button class="btn btn-default btn-xs" @click.prevent="editGroup(group)">
									<span class="glyphicon glyphicon-edit" style="display: block;"></span>
									Bearbeiten
								</button>
								<button class="btn btn-default btn-xs" @click.prevent="deleteGroup(group.id)">
									<span class="glyphicon glyphicon-trash" style="display: block;"></span>
									Löschen
								</button>
							</td>
						</template>
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
				editId: '',
				groupData: {
					name: ''
				},
				editGroupData: {
					id : '',
					name: ''
				},
				groups: []
			};
		return data;
	},
	controllerResponse: function () {
		var controllerResponse = { success: Boolean, message: "" };
		return controllerResponse;
	},
	methods: {
		fetch: function() {
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
		onSubmit: function() {
			var vm = this;
			$.get("/admin/create/group", {
				groupName: vm.groupData.name
			}).done(function(controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
					vm.groupData.name = '';
					vm.fetch();
				} else {
					console.log(controllerResponse.message);
					alert(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				alert("Fehler beim Hinzufügen der Gruppe: " + controllerResponse.message);
			});
		},
		editGroup: function(group) {
			var vm = this;
			vm.editId = group.id;
			vm.editGroupData.id = group.id;
			vm.editGroupData.name = group.name;
		},
		onEditSubmit: function(id) {
			var vm = this;
			$.get("/admin/update/group", {
				groupId: vm.editGroupData.id,
				groupName: vm.editGroupData.name
			}).done(function (controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
					vm.editId = '';
					vm.editGroupData.id = '';
					vm.editGroupData.name = '';
					vm.fetch();
				} else {
					console.log(controllerResponse.message);
					alert(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				alert("Fehler bei der Aktualisierung der Gruppe: " + controllerResponse.message);
			});
		},
		onCancel: function() {
			var vm = this;
			vm.editId = '';
			vm.editGroupData.id = '';
			vm.editGroupData.name = '';
		},
		deleteGroup: function(id) {
			var vm = this;
			$.get("/admin/delete/group", {
				groupId: id
			}).done(function (controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
					vm.fetch();
				} else {
					console.log(controllerResponse.message);
					alert(controllerResponse.message);
				}
			}).fail( function (controllerResponse) {
				alert("Fehler beim Löschen der Gruppe: " + controllerResponse.message);
			});
		}
	}
}
</script>