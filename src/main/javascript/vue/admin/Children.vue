<template>
<div class="panel-group">
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
			children: []
		};
		return data;
	},
	methods: {
		fetch: function() {
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
		onKeyUp: function() {
			var value = $("#filterInput").val().toLowerCase();
			$("#childrenTable tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		}
	}
}
</script>