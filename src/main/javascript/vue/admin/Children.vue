<template>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-body">
			<form class="fancy-form" id="searchChildrenForm" v-on:submit.prevent="onSearchSubmit" novalidate="novalidate">
				<div id="searchFormFields">
					<label for="inputLastName" class="fancy-input fancy-input-select" style="margin-right: 20px; width: 150px;">
						<span class="placeholder" style="z-index: 3; font-size: 12px; top: 5px;">Nachname</span>
						<select id="inputLastName" name="inputLastName" class="form-control"></select>
					</label>
					<div class="pull-right">
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-search" style="display: block;"></span>Suchen
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<table class="table table-striped table-hover">
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
			children: []
		};
		return data;
	},
	methods: {
		onSearchSubmit: function() {
			var vm = this;
			$.get("admin/fetch/children" , {
				lastName: $("#inputLastName").val()
			}).done(function(data) {
				$(vm.$refs.alert).hide();
				console.log(data);
				vm.children = data;
			}).fail(function() {
				$(vm.$refs.alert).show();
			});
		}
	}
}
</script>