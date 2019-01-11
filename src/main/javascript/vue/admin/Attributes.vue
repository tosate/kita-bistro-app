<template>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-body">
			<div id="attributesError" class="alert alert-danger" role="alert" style="text-align: center; display: none;" ref="alert"></div>
			<form class="form-inline" v-on:submit.prevent="onSubmit">
				<label for="classificationInput">Kategorie</label>
				<input id="classificationInput" v-model="attributeData.classification" class="form-control mb-2 mr-sm-2" required>
				<label for="nameInput">Name</label>
				<input id="nameInput" v-model="attributeData.name" class="form-control mb-2 mr-sm-2" required>
				<button type="submit" class="btn btn-primary my-2">Hinzufügen</button>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<table class="table table-striped tabele-hover">
				<thead>
					<tr>
						<th scope="col">Kategorie</th>
						<th>Name</th>
						<th>Aktionen</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="attribute in attributes" v-bind:key="attribute.id">
						<template v-if="editId == attribute.id">
							<td><input v-model="editAttributeData.classification" type="text"></td>
							<td><input v-model="editAttributeData.name" type="text"></td>
							<td>
								<button class="btn btn-default btn-xs" @click.prevent="onEditSubmit(attribute.id)">
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
							<td>{{attribute.classification}}</td>
							<td>{{attribute.name}}</td>
							<td>
								<button class="btn btn-default btn-xs" @click.prevent="editAttribute(attribute)">
									<span class="glyphicon glyphicon-edit" style="display: block;"></span>
									Bearbeiten
								</button>
								<button class="btn btn-default btn-xs" @click.prevent="deleteAttribute(attribute.id)">
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
				attributeData: {
					classification: '',
					name: ''
				},
				editAttributeData: {
					id: '',
					classification: '',
					name: ''
				},
				attributes: []
		};
		return data;
	},
	methods: {
		fetch: function() {
			var vm = this;
			$.get("/admin/fetch/attributes", {
			}).done(function (data) {
				$(vm.$refs.alert).hide();
				console.log(data);
				vm.attributes = data;
			}).fail(function () {
				$(vm.$refs.alert).show();
				$("#attributesError").html("Fehler beim Laden der Besonderheiten");
			});
		},
		onSubmit: function() {
			var vm = this;
			$.get("/admin/create/attribute", {
				attributeClassification: vm.attributeData.classification,
				attributeName: vm.attributeData.name
			}).done(function(controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
					vm.attributeData.classification = '';
					vm.attributeData.name = '';
					vm.fetch();
				} else {
					console.log(controllerResponse.message);
					$(vm.$refs.alert).show();
					$("#attributesError").html("Fehler beim Anlegen der Besonderheit");
				}
			}).fail(function (controllerResponse) {
				$(vm.$refs.alert).show();
				$("#attributesError").html("Fehler beim Hinzufügen der Besonderheit: " + controllerResponse.message);
			});
		},
		editAttribute: function(attribute) {
			var vm = this;
			vm.editId = attribute.id;
			vm.editAttributeData.id = attribute.id;
			vm.editAttributeData.classification = attribute.classification;
			vm.editAttributeData.name = attribute.name;
		},
		onEditSubmit: function(id) {
			var vm = this;
			$.get("/admin/update/attribute", {
				attributeId: vm.editAttributeData.id,
				attributeClassification: vm.editAttributeData.classification,
				attributeName: vm.editAttributeData.name
			}).done(function (controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
					vm.editId = '';
					vm.editAttributeData.id = '';
					vm.editAttributeData.classification = '';
					vm.editAttributeData.name = '';
					vm.fetch();
				} else {
					console.log(controllerResponse.message);
					$(vm.$refs.alert).show();
					$("#attributesError").html(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				$(vm.$refs.alert).show();
				$("#attributesError").html("Fehler bei der Aktualisierung der Besonderheit: " + controllerResponse.message);
			});
		},
		onCancel: function() {
			var vm = this;
			vm.editId = '';
			vm.editAttributeData.id = '';
			vm.editAttributeData.classification = '';
			vm.editAttributeData.name = '';
		},
		deleteAttribute: function(id) {
			var vm = this;
			$.get("/admin/delete/attribute", {
				attributeId: id
			}).done(function (controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
					vm.fetch();
				} else {
					console.log(controllerResponse.message);
					$(vm.$refs.alert).show();
					$("#attributesError").html(controllerResponse.message);
				}
			}).fail( function (controllerResponse) {
				$(vm.$refs.alert).show();
				$("#attributesError").html("Fehler beim Löschen der Besonderheit: " + controllerResponse.message);
			});
		}
	}
}
</script>